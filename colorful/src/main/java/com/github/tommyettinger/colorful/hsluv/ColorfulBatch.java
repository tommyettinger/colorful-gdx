package com.github.tommyettinger.colorful.hsluv;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.NumberUtils;

import java.nio.Buffer;

/**
 * A substitute for {@link com.badlogic.gdx.graphics.g2d.SpriteBatch} that adds an additional attribute to store an
 * extra color's worth of channels, used to modify the H, S, and L channels of a color by multiplication (called the
 * "tweak") while the primary color affects the color additively (just called the color, often drawn from
 * {@code Palette} or generated with {@link ColorTools}). This ColorfulBatch uses the HSLuv color space where
 * SpriteBatch normally uses RGBA, which means that in the batch color, red maps to additive H (hue), green maps to
 * additive S (saturation), blue maps to additive L (lightness), and alpha continues to be multiplicative alpha.
 * Additive values cause no change when they are 0.5, but cause a sharp increase at 1.0 and a sharp decrease at
 * 0.0. In the tweak, red maps to multiplicative H, green maps to multiplicative S, blue maps to multiplicative L, and
 * alpha maps to "exponential-like" contrast. Like with the additive values, 0.5 causes no change for multiplicative
 * values, but you can think of the existing color as being temporarily shifted down by 0.5 for each of the HSL
 * channels, so each HSL channel's range is -0.5 to 0.5 before being multiplied by the corresponding tweak value (times
 * 2.0, which means multiplicative tweak values for S that are less than 0.5 make colors closer to grayscale and
 * values greater than 0.5 make colors more vibrant) and then shifted back to its prior range. Contrast is a special
 * value; 0.5 still means "no change," but lower values will make L changes less distinct between similar colors, while
 * higher values will "sharpen" the changes in L.
 * <br>
 * Created by Tommy Ettinger on 1/21/2021.
 */
public class ColorfulBatch implements Batch {
    public static final int SPRITE_SIZE = 24;
    public static final String TWEAK_ATTRIBUTE = "a_tweak";

    private Mesh mesh;

    private final float[] vertices;
    private int idx = 0;
    private Texture lastTexture = null;
    private float invTexWidth = 0, invTexHeight = 0;

    private boolean drawing = false;

    private final Matrix4 transformMatrix = new Matrix4();
    private final Matrix4 projectionMatrix = new Matrix4();
    private final Matrix4 combinedMatrix = new Matrix4();

    private boolean blendingDisabled = false;
    private int blendSrcFunc = GL20.GL_SRC_ALPHA;
    private int blendDstFunc = GL20.GL_ONE_MINUS_SRC_ALPHA;
    private int blendSrcFuncAlpha = GL20.GL_SRC_ALPHA;
    private int blendDstFuncAlpha = GL20.GL_ONE_MINUS_SRC_ALPHA;

    private final ShaderProgram shader;
    private ShaderProgram customShader = null;
    private boolean ownsShader;

    protected float color = ColorTools.hsluv(0f, 0f, 128f/255f, 1f);
    private final Color tempColor = new Color(0f, 0f, 128f/255f, 1f); // HSL from Palette.GRAY

    /**
     * A constant packed float that can be assigned to this ColorfulBatch's tweak with {@link #setTweak(float)} to make
     * all of the tweak adjustments virtually imperceptible. When this is set as the tweak, it won't change the
     * L multiplier or contrast, and it won't change the A multiplier or the B multiplier.
     */
    public static final float TWEAK_RESET = ColorTools.hsluv(0.5f, 0.5f, 0.5f, 0.5f);
    protected float tweak = TWEAK_RESET;

    /** Number of render calls since the last {@link #begin()}. **/
    public int renderCalls = 0;

    /** Number of rendering calls, ever. Will not be reset unless set manually. **/
    public int totalRenderCalls = 0;

    /** The maximum number of sprites rendered in one batch so far. **/
    public int maxSpritesInBatch = 0;

    /** Constructs a new ColorfulBatch with a size of 1000, one buffer, and the default shader.
     * @see #ColorfulBatch(int, ShaderProgram) */
    public ColorfulBatch() {
        this(1000, null);
    }

    /** Constructs a ColorfulBatch with one buffer and the default shader.
     * @see #ColorfulBatch(int, ShaderProgram)  */
    public ColorfulBatch(int size) {
        this(size, null);
    }

    /** Constructs a new ColorfulBatch. Sets the projection matrix to an orthographic projection with y-axis point upwards, x-axis
     * point to the right and the origin being in the bottom left corner of the screen. The projection will be pixel perfect with
     * respect to the current screen resolution.
     * <p>
     * The defaultShader specifies the shader to use. Note that the names for uniforms for this default shader are different than
     * the ones expect for shaders set with {@link #setShader(ShaderProgram)}. See {@link #createDefaultShader()}.
     * @param size The max number of sprites in a single batch. Max of 10922.
     * @param defaultShader The default shader to use. This is not owned by the ColorfulBatch and must be disposed separately. */
    public ColorfulBatch(int size, ShaderProgram defaultShader) {
        // 65535 is max vertex index, so 65535 / 6 vertices per sprite = 10922 sprites max.
        if (size > 10922) throw new IllegalArgumentException("Can't have more than 10922 sprites per batch: " + size);

        Mesh.VertexDataType vertexDataType = (Gdx.gl30 != null) ? Mesh.VertexDataType.VertexBufferObjectWithVAO : Mesh.VertexDataType.VertexArray;

        mesh = new Mesh(vertexDataType, false, size * 4, size * 6,
                new VertexAttribute(VertexAttributes.Usage.Position, 2, ShaderProgram.POSITION_ATTRIBUTE),
                new VertexAttribute(VertexAttributes.Usage.ColorPacked, 4, ShaderProgram.COLOR_ATTRIBUTE),
                new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE + "0"),
                new VertexAttribute(VertexAttributes.Usage.ColorPacked, 4, TWEAK_ATTRIBUTE));

        projectionMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        vertices = new float[size * SPRITE_SIZE];

        int len = size * 6;
        short[] indices = new short[len];
        short j = 0;
        for (int i = 0; i < len; i += 6, j += 4) {
            indices[i] = j;
            indices[i + 1] = (short)(j + 1);
            indices[i + 2] = (short)(j + 2);
            indices[i + 3] = (short)(j + 2);
            indices[i + 4] = (short)(j + 3);
            indices[i + 5] = j;
        }
        mesh.setIndices(indices);

        if (defaultShader == null) {
            shader = createDefaultShader();
            ownsShader = true;
        } else
            shader = defaultShader;
    }

    /**
     * Makes a new instance of the default ShaderProgram used for this ColorfulBatch, without any {@code #version}
     * specified in the shader source. This expects an extra attribute (relative to a normal SpriteBatch) that is used
     * for the tweak. You may want to set the code to prepend before you call this, as with:
     * {@code ShaderProgram.prependVertexCode = "#version 110\n";
     * ShaderProgram.prependFragmentCode = "#version 110\n";}
     * The actual version can be different, and may need to be different for compatibility with some hardware.
     * @return a new instance of the default shader used by ColorfulBatch for GL2 when no shader is specified
     */
    public static ShaderProgram createDefaultShader () {
        String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
                + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
                + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
                + "attribute vec4 " + TWEAK_ATTRIBUTE + ";\n"
                + "uniform mat4 u_projTrans;\n"
                + "varying vec4 v_color;\n"
                + "varying vec4 v_tweak;\n"
                + "varying vec2 v_texCoords;\n"
                + "varying float v_lightFix;\n" +
                "const vec3 epsilon = vec3(0.00885645);\n" +
                "const float kappa = 9.032962962;\n" +
                "const mat3 m =" +
                "         mat3(+3.2404542, -1.5371385, -0.4985314,\n" +
                "              -0.9692660, +1.8760108, +0.0415560,\n" +
                "              +0.0556434, -0.2040259, +1.0572252);\n" +
                "float chromaLimit(float hue, float lightness) {\n" +
                "        float sn = sin(hue);\n" +
                "        float cs = cos(hue);\n" +
                "        float sub1 = (lightness + 0.16) / 1.16;\n" +
                "        sub1 *= sub1 * sub1;\n" +
                "        float sub2 = sub1 > epsilon.x ? sub1 : lightness / kappa;\n" +
                "        float result = 1.0e50;\n" +
                "        float top, rbottom, lbottom, bottom, C0, C1;\n" +
                "        vec3 channelM;\n" +
                "        channelM = m[0];\n" +
                "        top = (0.99915 * channelM.x + 1.05122 * channelM.y + 1.14460 * channelM.z) * sub2;\n" +
                "        rbottom = 0.86330 * channelM.z - 0.17266 * channelM.y;\n" +
                "        lbottom = 0.12949 * channelM.z - 0.38848 * channelM.x;\n" +
                "        bottom = (rbottom * sn + lbottom * cs) * sub2;\n" +
                "        C0 = lightness * top / bottom;\n" +
                "        if (C0 > 0. && C0 < result) {\n" +
                "          result = C0;\n" +
                "        }\n" +
                "        C1 = lightness * (top - 1.05122 * 1) / (bottom + 0.17266 * sn);\n" +
                "        if (C1 > 0. && C1 < result) {\n" +
                "          result = C1;\n" +
                "        }\n" +
                "        channelM = m[1];\n" +
                "        top = (0.99915 * channelM.x + 1.05122 * channelM.y + 1.14460 * channelM.z) * sub2;\n" +
                "        rbottom = 0.86330 * channelM.z - 0.17266 * channelM.y;\n" +
                "        lbottom = 0.12949 * channelM.z - 0.38848 * channelM.x;\n" +
                "        bottom = (rbottom * sn + lbottom * cs) * sub2;\n" +
                "        C0 = lightness * top / bottom;\n" +
                "        if (C0 > 0. && C0 < result) {\n" +
                "          result = C0;\n" +
                "        }\n" +
                "        C1 = lightness * (top - 1.05122 * 1) / (bottom + 0.17266 * sn);\n" +
                "        if (C1 > 0. && C1 < result) {\n" +
                "          result = C1;\n" +
                "        }\n" +
                "        channelM = m[2];\n" +
                "        top = (0.99915 * channelM.x + 1.05122 * channelM.y + 1.14460 * channelM.z) * sub2;\n" +
                "        rbottom = 0.86330 * channelM.z - 0.17266 * channelM.y;\n" +
                "        lbottom = 0.12949 * channelM.z - 0.38848 * channelM.x;\n" +
                "        bottom = (rbottom * sn + lbottom * cs) * sub2;\n" +
                "        C0 = lightness * top / bottom;\n" +
                "        if (C0 > 0. && C0 < result) {\n" +
                "          result = C0;\n" +
                "        }\n" +
                "        C1 = lightness * (top - 1.05122 * 1) / (bottom + 0.17266 * sn);\n" +
                "        if (C1 > 0. && C1 < result) {\n" +
                "          result = C1;\n" +
                "        }\n" +
                "        return result;\n" +
                "}\n" +
//                "float intersectLength (float sn, float cs, float line1, float line2) {\n" +
//                "    return line2 / (sn - line1 * cs);\n" +
//                "}\n" +
//                "float chromaLimit(float hue, float lightness) {\n" +
//                "        float sn = sin(hue);\n" +
//                "        float cs = cos(hue);\n" +
//                "        float sub1 = (lightness + 0.16) / 1.16;\n" +
//                "        sub1 *= sub1 * sub1;\n" +
//                "        float sub2 = sub1 > epsilon.x ? sub1 : lightness / kappa;\n" +
//                "        float mn = 1000.0;\n" +
//                "        vec3 ms = m[0] * sub2;\n" +
//                "        float msy, top1, top2, bottom, length;\n" +
//                "        msy = ms.y;\n" +
//                "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
//                "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
//                "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
//                "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
//                "        if (length >= 0.) mn = min(mn, length);\n" +
//                "        msy -= 1.0;\n" +
//                "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
//                "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
//                "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
//                "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
//                "        if (length >= 0.) mn = min(mn, length);\n" +
//                "        ms = m[1] * sub2;\n" +
//                "        msy = ms.y;\n" +
//                "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
//                "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
//                "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
//                "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
//                "        if (length >= 0.) mn = min(mn, length);\n" +
//                "        msy -= 1.0;\n" +
//                "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
//                "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
//                "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
//                "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
//                "        if (length >= 0.) mn = min(mn, length);\n" +
//                "        ms = m[2] * sub2;\n" +
//                "        msy = ms.y;\n" +
//                "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
//                "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
//                "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
//                "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
//                "        if (length >= 0.) mn = min(mn, length);\n" +
//                "        msy -= 1.0;\n" +
//                "        top1 = 2845.17 * ms.x - 948.39 * ms.z;\n" +
//                "        top2 = (8384.22 * ms.z + 7698.60 * msy + 7317.18 * ms.x) * lightness;\n" +
//                "        bottom = (6322.60 * ms.z - 1264.52 * msy);\n" +
//                "        length = intersectLength(sn, cs, top1 / bottom, top2 / bottom);\n" +
//                "        if (length >= 0.) mn = min(mn, length);\n" +
//                "        return mn;\n" +
//                "}\n" +
                "vec3 hsl2luv(vec3 c)\n" +
                "{\n" +
                "    float L = c.z;\n" +
                "    float C = chromaLimit(c.x, L) * c.y;\n" +
                "    float U = cos(c.x) * C;\n" +
                "    float V = sin(c.x) * C;\n" +
                "    return vec3(L, U, V);\n" +
                "}\n"
                + "void main()\n"
                + "{\n"
                + "   v_tweak = " + TWEAK_ATTRIBUTE + ";\n"
                + "   v_tweak.w = pow(v_tweak.w * (255.0/254.0) + 0.5, 1.709);\n"
                + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
                + "   v_color.w = v_color.w * (255.0/254.0);\n"
                + "   v_color.x *= 6.2831 * 2.0 * v_tweak.x;\n"
                + "   v_color.rgb = hsl2luv(v_color.rgb);\n"
                + "   v_lightFix = 1.0 + pow(v_tweak.w, 1.41421356);\n"
                + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
                + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
                + "}\n";
        String fragmentShader =
                "#ifdef GL_ES\n" +
                        "#define LOWP lowp\n" +
                        "precision mediump float;\n" +
                        "#else\n" +
                        "#define LOWP \n" +
                        "#endif\n" +
                        "varying vec2 v_texCoords;\n" +
                        "varying LOWP vec4 v_color;\n" +
                        "varying LOWP vec4 v_tweak;\n" +
                        "varying float v_lightFix;\n" +
                        "uniform sampler2D u_texture;\n" +
                        "const vec3 forward = vec3(1.0 / 3.0);\n" +
                        "const vec3 sRGBFrom = vec3(2.4);\n" +
                        "const vec3 sRGBThresholdFrom = vec3(0.04045);\n" +
                        "const vec3 sRGBTo = vec3(1.0 / 2.4);\n" +
                        "const vec3 sRGBThresholdTo = vec3(0.0031308);\n" +
                        "const vec3 epsilon = vec3(0.00885645);\n" +
                        "const float kappa = 9.032962962;\n" +
                        "const vec2 refUV = vec2(0.1978, 0.4683);\n" +
                        "const mat3 m =" +
                        "         mat3(+3.2404542, -1.5371385, -0.4985314,\n" +
                        "              -0.9692660, +1.8760108, +0.0415560,\n" +
                        "              +0.0556434, -0.2040259, +1.0572252);\n" +
                        "const mat3 mInv =" +
                        "         mat3(0.4124, 0.3576, 0.1805,\n" +
                        "              0.2126, 0.7152, 0.0722,\n" +
                        "              0.0193, 0.1192, 0.9505);\n" +
                        "vec3 linear(vec3 t){ return mix(pow((t + 0.055) * (1.0 / 1.055), sRGBFrom), t * (1.0/12.92), step(t, sRGBThresholdFrom)); }\n" +
                        "vec3 sRGB(vec3 t){ return mix(1.055 * pow(t, sRGBTo) - 0.055, 12.92*t, step(t, sRGBThresholdTo)); }\n" +
                        "float xyzF(float t){ return mix(pow(t,1./3.), 7.787037 * t + 0.139731, step(t, epsilon.x)); }\n" +
                        "vec3 xyzF(vec3 t){ return mix(pow(t, forward), 7.787037 * t + 0.139731, step(t, epsilon)); }\n" +
                        "float xyzR(float t){ return mix(t*t*t , 0.1284185 * (t - 0.139731), step(t, 0.20689655)); }\n" +
                        "float chromaLimit(float hue, float lightness) {\n" +
                        "        float sn = sin(hue);\n" +
                        "        float cs = cos(hue);\n" +
                        "        float sub1 = (lightness + 0.16) / 1.16;\n" +
                        "        sub1 *= sub1 * sub1;\n" +
                        "        float sub2 = sub1 > epsilon.x ? sub1 : lightness / kappa;\n" +
                        "        float result = 1.0e50;\n" +
                        "        float top, rbottom, lbottom, bottom, C0, C1;\n" +
                        "        vec3 channelM;\n" +
                        "        channelM = m[0];\n" +
                        "        top = (0.99915 * channelM.x + 1.05122 * channelM.y + 1.14460 * channelM.z) * sub2;\n" +
                        "        rbottom = 0.86330 * channelM.z - 0.17266 * channelM.y;\n" +
                        "        lbottom = 0.12949 * channelM.z - 0.38848 * channelM.x;\n" +
                        "        bottom = (rbottom * sn + lbottom * cs) * sub2;\n" +
                        "        C0 = lightness * top / bottom;\n" +
                        "        if (C0 > 0. && C0 < result) {\n" +
                        "          result = C0;\n" +
                        "        }\n" +
                        "        C1 = lightness * (top - 1.05122 * 1) / (bottom + 0.17266 * sn);\n" +
                        "        if (C1 > 0. && C1 < result) {\n" +
                        "          result = C1;\n" +
                        "        }\n" +
                        "        channelM = m[1];\n" +
                        "        top = (0.99915 * channelM.x + 1.05122 * channelM.y + 1.14460 * channelM.z) * sub2;\n" +
                        "        rbottom = 0.86330 * channelM.z - 0.17266 * channelM.y;\n" +
                        "        lbottom = 0.12949 * channelM.z - 0.38848 * channelM.x;\n" +
                        "        bottom = (rbottom * sn + lbottom * cs) * sub2;\n" +
                        "        C0 = lightness * top / bottom;\n" +
                        "        if (C0 > 0. && C0 < result) {\n" +
                        "          result = C0;\n" +
                        "        }\n" +
                        "        C1 = lightness * (top - 1.05122 * 1) / (bottom + 0.17266 * sn);\n" +
                        "        if (C1 > 0. && C1 < result) {\n" +
                        "          result = C1;\n" +
                        "        }\n" +
                        "        channelM = m[2];\n" +
                        "        top = (0.99915 * channelM.x + 1.05122 * channelM.y + 1.14460 * channelM.z) * sub2;\n" +
                        "        rbottom = 0.86330 * channelM.z - 0.17266 * channelM.y;\n" +
                        "        lbottom = 0.12949 * channelM.z - 0.38848 * channelM.x;\n" +
                        "        bottom = (rbottom * sn + lbottom * cs) * sub2;\n" +
                        "        C0 = lightness * top / bottom;\n" +
                        "        if (C0 > 0. && C0 < result) {\n" +
                        "          result = C0;\n" +
                        "        }\n" +
                        "        C1 = lightness * (top - 1.05122 * 1) / (bottom + 0.17266 * sn);\n" +
                        "        if (C1 > 0. && C1 < result) {\n" +
                        "          result = C1;\n" +
                        "        }\n" +
                        "        return result;\n" +
                        "}\n" +
                        "vec3 rgb2luv(vec3 c)\n" +
                        "{\n" +
                        "    c *= mInv;" +
                        "    float L = max(0.,1.16*pow(c.y, 1.0 / 3.0) - 0.16);\n" +
                        "    vec2 uv;\n" +
                        "    if(L < 0.0001) uv = vec2(0.0);\n" +
                        "    else uv = 13. * L * (vec2(4., 9.) * c.xy / (c.x + 15. * c.y + 3. * c.z) - refUV);\n" +
                        "    return vec3(L, uv);\n" +
                        "}\n" +
//                        "vec3 rgb2hsl(vec3 c)\n" +
//                        "{\n" +
//                        "    c *= mInv" +
//                        "    c = xyzF(c);\n" +
//                        "    float L = max(0.,1.16*c.y - 0.16);\n" +
//                        "    vec2 uv = L / (c.x + 15. * c.y + 3. * c.z) * vec2(4., 9.) * c.xy - refUV;\n" +
//                        "    vec3 luv = vec3(L, uv);\n" +
//                        "    float C = length(uv);\n" +
//                        "    float h = atan(uv.y, uv.x);\n" +
//                        "    float s = min(C / (chromaLimit(L, h) + 0.0001), 1);\n" +
//                        "    return vec3(h, s, L);\n" +
//                        "}\n" +
//                        "vec3 luv2rgb(vec3 c)\n" +
//                        "{\n" +
//                        "    float L = c.x;\n" +
//                        "    float U = c.y;\n" +
//                        "    float V = c.z;\n" +
//                        "    if (L <= 0.0001) {\n" +
//                        "        return vec3(0.0);\n" +
//                        "    } else if(L >= 0.9999) {\n" +
//                        "        return vec3(1.0);\n" +
//                        "    } else {\n" +
//                        "        c.y = (L + 0.16) / 1.16;\n" +
//                        "        float prop = c.y * c.y * c.y;\n" +
//                        "        if(prop > epsilon.x) c.y = prop;\n" +
//                        "        else c.y = (1.16 * c.y - 0.16) / kappa;\n" +
//                        "    }\n" +
//                        "    float iL = 1. / (13.0 * L);\n" +
//                        "    float varU = U * iL + refUV.x;\n" +
//                        "    float varV = V * iL + refUV.y;\n" +
//                        "    c.x = -9. * c.y * varU / (-4. * varV);\n" +
//                        "    c.z = (9 * c.y - 15 * varV * c.y - varV * c.x) / (3 * varV);\n" +
//                        "    vec3 rgb = c * mat3( 3.2406, -1.5372,-0.4986,\n" +
//                        "                        -0.9689,  1.8758, 0.0415,\n" +
//                        "                         0.0557, -0.2040, 1.0570);\n" +
//                        "    return rgb;\n" +
//                        "}\n" +
                        "float forwardLight(float L) {\n" +
                        "        const float shape = 0.8528, turning = 0.1;\n" +
                        "        float d = turning - L;\n" +
                        "        return mix(\n" +
                        "          ((1. - turning) * (L - 1.)) / (1. - (L + shape * d)) + 1.,\n" +
                        "          (turning * L) / (1.0e-20 + (L + shape * d)),\n" +
                        "          step(0.0, d));\n" +
                        "}\n" +
                        "float reverseLight(float L) {\n" +
                        "        const float shape = 1.1726, turning = 0.1;\n" +
                        "        float d = turning - L;\n" +
                        "        return mix(\n" +
                        "          ((1. - turning) * (L - 1.)) / (1. - (L + shape * d)) + 1.,\n" +
                        "          (turning * L) / (1.0e-20 + (L + shape * d)),\n" +
                        "          step(0.0, d));\n" +
                        "}\n" +
                        "vec3 luv2rgb(vec3 c)\n" +
                        "{\n" +
                        "    float L = reverseLight(c.x);\n" +
                        "    float U = c.y;\n" +
                        "    float V = c.z;\n" +
                        "    float lim = chromaLimit(atan(V, U), L);\n" +
                        "    float len = length(vec2(U,V));\n" +
                        "    if(len > lim) {\n" +
                        "      lim /= len;\n" +
                        "      U *= lim;\n" +
                        "      V *= lim;\n" +
                        "    }\n" +
                        "    if (L <= 0.0001) {\n" +
                        "        return vec3(0.0);\n" +
                        "    } else if(L >= 0.9999) {\n" +
                        "        return vec3(1.0);\n" +
                        "    } else {\n" +
                        "      if (L <= 0.08) {\n" +
                        "        c.y = L / kappa;\n" +
                        "      } else {\n" +
                        "        c.y = (L + 0.16) / 1.16;\n" +
                        "        c.y *= c.y * c.y;\n" +
                        "      }\n" +
                        "    }\n" +
                        "    float iL = 1. / (13.0 * L);\n" +
                        "    float varU = U * iL + refUV.x;\n" +
                        "    float varV = V * iL + refUV.y;\n" +
                        "    c.x = 9. * varU * c.y / (4. * varV);\n" +
                        "    c.z = (3. * c.y / varV) - c.x / 3. - 5. * c.y;\n" +
                        "    vec3 rgb = c * mat3( 3.2406, -1.5372,-0.4986,\n" +
                        "                        -0.9689,  1.8758, 0.0415,\n" +
                        "                         0.0557, -0.2040, 1.0570);\n" +
                        "    return rgb;\n" +
                        "}\n" +
                        "void main()\n" +
                        "{\n" +
                        "  vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                        "  vec3 luv = rgb2luv(linear(tgt.rgb));\n" +
                        "  luv.x = forwardLight(clamp(pow(luv.x, v_tweak.w) * v_lightFix * v_tweak.z + v_color.x - 0.5372549, 0.0, 1.0));\n" +
                        "  luv.yz = (luv.yz * v_tweak.y * 2.0) + (v_color.yz);\n" +
                        "  gl_FragColor = vec4(sRGB(clamp(luv2rgb(luv), 0.0, 1.0)), v_color.a * tgt.a);\n" +
                        "}";
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
        if (!shader.isCompiled()) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
        return shader;
    }

    @Override
    public void begin () {
        if (drawing) throw new IllegalStateException("ColorfulBatch.end must be called before begin.");
        renderCalls = 0;

        Gdx.gl.glDepthMask(false);
        if (customShader != null)
            customShader.bind();
        else
            shader.bind();
        setupMatrices();

        drawing = true;
    }

    @Override
    public void end () {
        if (!drawing) throw new IllegalStateException("ColorfulBatch.begin must be called before end.");
        if (idx > 0) flush();
        lastTexture = null;
        drawing = false;

        GL20 gl = Gdx.gl;
        gl.glDepthMask(true);
        if (isBlendingEnabled()) gl.glDisable(GL20.GL_BLEND);
    }

    @Override
    public void setColor (Color tint) {
        color = tint.toFloatBits();
    }

    /**
     * Sets the color to the result of {@link ColorTools#clamp(float, float, float, float)} on the same arguments.
     * For the H/S/L parameters, 0.5f is a neutral value (causing no change), while for alpha, 1.0f is a neutral
     * value. For H/S/L, higher values will add to the corresponding channel, while lower values will subtract from it.
     * @see ColorTools#clamp(float, float, float, float)
     * @param H hue; additive; from 0 to 1, with red at 0, orange above red, yellow above orange, etc.
     * @param S saturation; additive; from 0 to 1, with 0 desaturating and 1 very saturating
     * @param L lightness; additive; ranges from 0 (closer to black) to 1 (closer to white)
     * @param alpha opacity, from 0 to 1; multiplicative
     */
    @Override
    public void setColor (float H, float S, float L, float alpha) {
        color = ColorTools.clamp(H, S, L, alpha);
    }

    public void setColor (final float color) {
        setPackedColor(color);
    }

    public void setIntColor(final int color) {
        this.color = NumberUtils.intBitsToFloat(Integer.reverseBytes(color & -2));
    }

    /**
     * Sets the color with the given H/S/L and S parameters from 0 to 255.
     * For the H/S/L parameters, 127 or 128 is a neutral value (causing no change), while for alpha, 255 is a neutral
     * value. For H/S/L, higher values will add to the corresponding channel, while lower values will subtract from it.
     * @see ColorTools#hsluv(float, float, float, float)
     * @param H hue; additive; from 0 to 255, with red at 0, orange above red, yellow above orange, etc.
     * @param S saturation; additive; from 0 to 255, with 0 desaturating and 255 very saturating
     * @param L lightness; additive; ranges from 0 (closer to black) to 255 (closer to white)
     * @param alpha opacity, from 0 to 255 (254 is equivalent, since the lowest bit is discarded); multiplicative
     */
    public void setIntColor(int H, int S, int L, int alpha) {
        color = NumberUtils.intBitsToFloat((alpha << 24 & 0xFE000000)
                | (L << 16 & 0xFF0000) | (S << 8 & 0xFF00) | (H & 0xFF));
    }

    @Override
    public void setPackedColor (final float color) {
        this.color = color;
    }

    @Override
    public Color getColor () {
        final int intBits = NumberUtils.floatToRawIntBits(color);
        Color color = tempColor;
        color.r = (intBits & 0xff) / 255f;
        color.g = ((intBits >>> 8) & 0xff) / 255f;
        color.b = ((intBits >>> 16) & 0xff) / 255f;
        color.a = ((intBits >>> 24) & 0xff) / 255f;
        return color;
    }

    @Override
    public float getPackedColor () {
        return color;
    }

    /**
     * Sets the multiplicative and contrast parts of the shader's color changes. 0.5 is a neutral value that should have
     * minimal effect on the image; using {@code (0.5f, 0.5f, 0.5f, 0.5f)} will effectively remove the tweak.
     * @param H hue; multiplicative; probably doesn't work very well and should just be 0.5f
     * @param S saturation; multiplicative; from 0 to 1, with 0 forcing grayscale and 1 emphasizing saturation
     * @param L lightness; multiplicative; ranges from 0 (sets lightness to 0) to 1 (doubles lightness)
     * @param contrast affects how lightness changes; ranges from 0 (low contrast, cloudy look) to 1 (high contrast, sharpened look) 
     */
    public void setTweak (float H, float S, float L, float contrast) {
        tweak = ColorTools.clamp(H, S, L, contrast);
    }
    /**
     * Sets the tweak using a single packed CIELAB float.
     * @see #setTweak(float, float, float, float)
     * @param tweak a packed CIELAB float, with contrast instead of alpha
     */
    public void setTweak (final float tweak) {
        this.tweak = tweak;
    }
    
    public float getTweak () {
        return tweak;
    }

    /**
     * Takes the tweak as an int in the format: H (8 bits), S (8 bits), L (8 bits), contrast (7 bits),
     * (1 ignored bit at the end). An example would be 0x8020A06E, which doesn't change hue (using the default 0x80),
     * significantly reduces colorfulness with S multiplier 0x20 (closer to 0, so most colors will be almost grayscale),
     * raises lightness somewhat with L multiplier 0xA0 (above the neutral point of 0x80), and slightly reduces contrast
     * (with contrast and the ignored bit as 0x6E, which is less than the halfway point).
     * @param tweak the tweak to use as an integer, with L in the most significant bits and contrast in least
     */
    public void setIntTweak(final int tweak) {
        this.tweak = NumberUtils.intBitsToFloat(Integer.reverseBytes(tweak & -2));
    }

    /**
     * Sets the multiplicative and contrast parts of the shader's color changes. 128 is a neutral value that should have
     * minimal effect on the image; using {@code (128, 128, 128, 128)} will effectively remove the tweak.
     * @param H hue; multiplicative; probably doesn't work very well and should just be 128
     * @param S saturation; multiplicative; from 0 to 255, with 0 forcing grayscale and 255 emphasizing saturation
     * @param L lightness; multiplicative; ranges from 0 (sets lightness to 0) to 255 (doubles lightness)
     * @param contrast affects how lightness changes; ranges from 0 (low contrast, cloudy look) to 255 (high contrast, sharpened look) 
     */
    public void setIntTweak(int H, int S, int L, int contrast) {
        tweak = NumberUtils.intBitsToFloat((contrast << 24 & 0xFE000000)
                | (L << 16 & 0xFF0000) | (S << 8 & 0xFF00) | (H & 0xFF));
    }

    /**
     * A convenience method that sets both the color (with {@link #setColor(float)}) and the tweak (with
     * {@link #setTweak(float)}) at the same time, using two packed floats.
     * @param color the additive components and alpha, as a packed float
     * @param tweak the multiplicative components and contrast, as a packed float
     */
    public void setTweakedColor(final float color, final float tweak) {
        setColor(color);
        setTweak(tweak);
    }

    /**
     * A convenience method that sets both the color (with {@link #setColor(float, float, float, float)}) and the tweak
     * (with {@link #setTweak(float, float, float, float)}) at the same time.
     * @param addH hue; additive; from 0 to 1, with red at 0, orange above red, yellow above orange, etc.
     * @param addS saturation; additive; from 0 to 1, with 0 desaturating and 1 very saturating
     * @param addL lightness; additive; ranges from 0 (closer to black) to 1 (closer to white)
     * @param mulAlpha opacity, from 0 to 1; multiplicative
     * @param mulH hue; multiplicative; probably doesn't work very well and should just be 0.5f
     * @param mulS saturation; multiplicative; from 0 to 1, with 0 forcing grayscale and 1 emphasizing saturation
     * @param mulL lightness; multiplicative; ranges from 0 (sets lightness to 0) to 1 (doubles lightness)
     * @param contrast foggy-to-sharp lightness contrast; affects most other components; ranges from 0 (flat, foggy lightness) to 1 (sharp, crisp lightness)
     */
    public void setTweakedColor(final float addH, final float addS,
                                final float addL, final float mulAlpha,
                                final float mulH, final float mulS,
                                final float mulL, final float contrast) {
        setColor(addH, addS, addL, mulAlpha);
        setTweak(mulH, mulS, mulL, contrast);
    }

    @Override
    public void draw (Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX,
                      float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY) {
        if (!drawing) throw new IllegalStateException("ColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        if (texture != lastTexture)
            switchTexture(texture);
        else if (idx == vertices.length) //
            flush();

        // bottom left and top right corner points relative to origin
        final float worldOriginX = x + originX;
        final float worldOriginY = y + originY;
        float fx = -originX;
        float fy = -originY;
        float fx2 = width - originX;
        float fy2 = height - originY;

        // scale
        if (scaleX != 1 || scaleY != 1) {
            fx *= scaleX;
            fy *= scaleY;
            fx2 *= scaleX;
            fy2 *= scaleY;
        }

        // construct corner points, start from top left and go counter clockwise
        final float p1x = fx;
        final float p1y = fy;
        final float p2x = fx;
        final float p2y = fy2;
        final float p3x = fx2;
        final float p3y = fy2;
        final float p4x = fx2;
        final float p4y = fy;

        float x1;
        float y1;
        float x2;
        float y2;
        float x3;
        float y3;
        float x4;
        float y4;

        // rotate
        if (rotation != 0) {
            final float cos = MathUtils.cosDeg(rotation);
            final float sin = MathUtils.sinDeg(rotation);

            x1 = cos * p1x - sin * p1y;
            y1 = sin * p1x + cos * p1y;

            x2 = cos * p2x - sin * p2y;
            y2 = sin * p2x + cos * p2y;

            x3 = cos * p3x - sin * p3y;
            y3 = sin * p3x + cos * p3y;

            x4 = x1 + (x3 - x2);
            y4 = y3 - (y2 - y1);
        } else {
            x1 = p1x;
            y1 = p1y;

            x2 = p2x;
            y2 = p2y;

            x3 = p3x;
            y3 = p3y;

            x4 = p4x;
            y4 = p4y;
        }

        x1 += worldOriginX;
        y1 += worldOriginY;
        x2 += worldOriginX;
        y2 += worldOriginY;
        x3 += worldOriginX;
        y3 += worldOriginY;
        x4 += worldOriginX;
        y4 += worldOriginY;

        float u = srcX * invTexWidth;
        float v = (srcY + srcHeight) * invTexHeight;
        float u2 = (srcX + srcWidth) * invTexWidth;
        float v2 = srcY * invTexHeight;

        if (flipX) {
            float tmp = u;
            u = u2;
            u2 = tmp;
        }

        if (flipY) {
            float tmp = v;
            v = v2;
            v2 = tmp;
        }

        final float color = this.color;
        final float tweak = this.tweak;
        final int idx = this.idx;
        vertices[idx] = x1;
        vertices[idx + 1] = y1;
        vertices[idx + 2] = color;
        vertices[idx + 3] = u;
        vertices[idx + 4] = v;
        vertices[idx + 5] = tweak;

        vertices[idx + 6] = x2;
        vertices[idx + 7] = y2;
        vertices[idx + 8] = color;
        vertices[idx + 9] = u;
        vertices[idx + 10] = v2;
        vertices[idx + 11] = tweak;

        vertices[idx + 12] = x3;
        vertices[idx + 13] = y3;
        vertices[idx + 14] = color;
        vertices[idx + 15] = u2;
        vertices[idx + 16] = v2;
        vertices[idx + 17] = tweak;

        vertices[idx + 18] = x4;
        vertices[idx + 19] = y4;
        vertices[idx + 20] = color;
        vertices[idx + 21] = u2;
        vertices[idx + 22] = v;
        vertices[idx + 23] = tweak;
        this.idx = idx + 24;
    }

    @Override
    public void draw (Texture texture, float x, float y, float width, float height, int srcX, int srcY, int srcWidth,
                      int srcHeight, boolean flipX, boolean flipY) {
        if (!drawing) throw new IllegalStateException("ColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        if (texture != lastTexture)
            switchTexture(texture);
        else if (idx == vertices.length) //
            flush();

        float u = srcX * invTexWidth;
        float v = (srcY + srcHeight) * invTexHeight;
        float u2 = (srcX + srcWidth) * invTexWidth;
        float v2 = srcY * invTexHeight;
        final float fx2 = x + width;
        final float fy2 = y + height;

        if (flipX) {
            float tmp = u;
            u = u2;
            u2 = tmp;
        }

        if (flipY) {
            float tmp = v;
            v = v2;
            v2 = tmp;
        }

        final float color = this.color;
        final float tweak = this.tweak;
        final int idx = this.idx;
        vertices[idx] = x;
        vertices[idx + 1] = y;
        vertices[idx + 2] = color;
        vertices[idx + 3] = u;
        vertices[idx + 4] = v;
        vertices[idx + 5] = tweak;

        vertices[idx + 6] = x;
        vertices[idx + 7] = fy2;
        vertices[idx + 8] = color;
        vertices[idx + 9] = u;
        vertices[idx + 10] = v2;
        vertices[idx + 11] = tweak;

        vertices[idx + 12] = fx2;
        vertices[idx + 13] = fy2;
        vertices[idx + 14] = color;
        vertices[idx + 15] = u2;
        vertices[idx + 16] = v2;
        vertices[idx + 17] = tweak;

        vertices[idx + 18] = fx2;
        vertices[idx + 19] = y;
        vertices[idx + 20] = color;
        vertices[idx + 21] = u2;
        vertices[idx + 22] = v;
        vertices[idx + 23] = tweak;
        this.idx = idx + 24;
    }

    @Override
    public void draw (Texture texture, float x, float y, int srcX, int srcY, int srcWidth, int srcHeight) {
        if (!drawing) throw new IllegalStateException("ColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        if (texture != lastTexture)
            switchTexture(texture);
        else if (idx == vertices.length) //
            flush();

        final float u = srcX * invTexWidth;
        final float v = (srcY + srcHeight) * invTexHeight;
        final float u2 = (srcX + srcWidth) * invTexWidth;
        final float v2 = srcY * invTexHeight;
        final float fx2 = x + srcWidth;
        final float fy2 = y + srcHeight;

        final float color = this.color;
        final float tweak = this.tweak;
        final int idx = this.idx;
        vertices[idx] = x;
        vertices[idx + 1] = y;
        vertices[idx + 2] = color;
        vertices[idx + 3] = u;
        vertices[idx + 4] = v;
        vertices[idx + 5] = tweak;

        vertices[idx + 6] = x;
        vertices[idx + 7] = fy2;
        vertices[idx + 8] = color;
        vertices[idx + 9] = u;
        vertices[idx + 10] = v2;
        vertices[idx + 11] = tweak;

        vertices[idx + 12] = fx2;
        vertices[idx + 13] = fy2;
        vertices[idx + 14] = color;
        vertices[idx + 15] = u2;
        vertices[idx + 16] = v2;
        vertices[idx + 17] = tweak;

        vertices[idx + 18] = fx2;
        vertices[idx + 19] = y;
        vertices[idx + 20] = color;
        vertices[idx + 21] = u2;
        vertices[idx + 22] = v;
        vertices[idx + 23] = tweak;
        this.idx = idx + 24;
    }

    @Override
    public void draw (Texture texture, float x, float y, float width, float height, float u, float v, float u2, float v2) {
        if (!drawing) throw new IllegalStateException("ColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        if (texture != lastTexture)
            switchTexture(texture);
        else if (idx == vertices.length) //
            flush();

        final float fx2 = x + width;
        final float fy2 = y + height;

        final float color = this.color;
        final float tweak = this.tweak;
        final int idx = this.idx;
        vertices[idx] = x;
        vertices[idx + 1] = y;
        vertices[idx + 2] = color;
        vertices[idx + 3] = u;
        vertices[idx + 4] = v;
        vertices[idx + 5] = tweak;

        vertices[idx + 6] = x;
        vertices[idx + 7] = fy2;
        vertices[idx + 8] = color;
        vertices[idx + 9] = u;
        vertices[idx + 10] = v2;
        vertices[idx + 11] = tweak;

        vertices[idx + 12] = fx2;
        vertices[idx + 13] = fy2;
        vertices[idx + 14] = color;
        vertices[idx + 15] = u2;
        vertices[idx + 16] = v2;
        vertices[idx + 17] = tweak;

        vertices[idx + 18] = fx2;
        vertices[idx + 19] = y;
        vertices[idx + 20] = color;
        vertices[idx + 21] = u2;
        vertices[idx + 22] = v;
        vertices[idx + 23] = tweak;
        this.idx = idx + 24;
    }

    @Override
    public void draw (Texture texture, float x, float y) {
        draw(texture, x, y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void draw (Texture texture, float x, float y, float width, float height) {
        if (!drawing) throw new IllegalStateException("ColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        if (texture != lastTexture)
            switchTexture(texture);
        else if (idx == vertices.length) //
            flush();

        final float fx2 = x + width;
        final float fy2 = y + height;
        final float u = 0;
        final float v = 1;
        final float u2 = 1;
        final float v2 = 0;

        final float color = this.color;
        final float tweak = this.tweak;
        final int idx = this.idx;
        vertices[idx] = x;
        vertices[idx + 1] = y;
        vertices[idx + 2] = color;
        vertices[idx + 3] = u;
        vertices[idx + 4] = v;
        vertices[idx + 5] = tweak;

        vertices[idx + 6] = x;
        vertices[idx + 7] = fy2;
        vertices[idx + 8] = color;
        vertices[idx + 9] = u;
        vertices[idx + 10] = v2;
        vertices[idx + 11] = tweak;

        vertices[idx + 12] = fx2;
        vertices[idx + 13] = fy2;
        vertices[idx + 14] = color;
        vertices[idx + 15] = u2;
        vertices[idx + 16] = v2;
        vertices[idx + 17] = tweak;

        vertices[idx + 18] = fx2;
        vertices[idx + 19] = y;
        vertices[idx + 20] = color;
        vertices[idx + 21] = u2;
        vertices[idx + 22] = v;
        vertices[idx + 23] = tweak;
        this.idx = idx + 24;
    }

    /**
     * This is very different from the other overloads in this class; it assumes the float array it is given is in the
     * format libGDX uses to give to SpriteBatch, that is, in groups of 20 floats per sprite. ColorfulBatch uses 24
     * floats per sprite, to add tweak per color, so this does some conversion.
     * @param texture the Texture being drawn from; usually an atlas or some parent Texture with lots of TextureRegions
     * @param spriteVertices not the same format as {@link #vertices} in this class; should have a length that's a multiple of 20
     * @param offset where to start drawing vertices from {@code spriteVertices}
     * @param count how many vertices to draw from {@code spriteVertices} (20 vertices is one sprite)
     */
    @Override
    public void draw (Texture texture, float[] spriteVertices, int offset, int count) {
        if (!drawing) throw new IllegalStateException("ColorfulBatch.begin must be called before draw.");

        count = (count / 5) * 6;
        int verticesLength = vertices.length;
        int remainingVertices = verticesLength;
        if (texture != lastTexture)
            switchTexture(texture);
        else {
            remainingVertices -= idx;
            if (remainingVertices == 0) {
                flush();
                remainingVertices = verticesLength;
            }
        }
        int copyCount = Math.min(remainingVertices, count);
        final float tweak = this.tweak;

        ////old way, breaks when libGDX code expects SPRITE_SIZE to be 20
        //System.arraycopy(spriteVertices, offset, vertices, idx, copyCount);
        ////new way, thanks mgsx
        for (int s = offset, v = idx, i = 0; i < copyCount; i += 6) {
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = tweak;
        }
        idx += copyCount;
        count -= copyCount;
        while (count > 0) {
            offset += (copyCount / 6) * 5;
            flush();
            copyCount = Math.min(verticesLength, count);
            ////old way, breaks when libGDX code expects SPRITE_SIZE to be 20
            //System.arraycopy(spriteVertices, offset, vertices, 0, copyCount);
            ////new way, thanks mgsx
            for (int s = offset, v = 0, i = 0; i < copyCount; i += 6) {
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = tweak;
            }
            idx += copyCount;
            count -= copyCount;
        }
    }

    /**
     * Meant for code that uses ColorfulBatch specifically and can set an extra float (for the color tweak) per vertex,
     * this is just like {@link #draw(Texture, float[], int, int)} when used in other Batch implementations, but expects
     * {@code spriteVertices} to have a length that is a multiple of 24 instead of 20.
     * @param texture the Texture being drawn from; usually an atlas or some parent Texture with lots of TextureRegions
     * @param spriteVertices vertices formatted as this class uses them; length should be a multiple of 24
     * @param offset where to start drawing vertices from {@code spriteVertices}
     * @param count how many vertices to draw from {@code spriteVertices} (24 vertices is one sprite)
     */
    public void drawExactly (Texture texture, float[] spriteVertices, int offset, int count) {
        if (!drawing) throw new IllegalStateException("ColorfulBatch.begin must be called before draw.");

        int verticesLength = vertices.length;
        int remainingVertices = verticesLength;
        if (texture != lastTexture)
            switchTexture(texture);
        else {
            remainingVertices -= idx;
            if (remainingVertices == 0) {
                flush();
                remainingVertices = verticesLength;
            }
        }
        int copyCount = Math.min(remainingVertices, count);

        System.arraycopy(spriteVertices, offset, vertices, idx, copyCount);
        idx += copyCount;
        count -= copyCount;
        while (count > 0) {
            offset += copyCount;
            flush();
            copyCount = Math.min(verticesLength, count);
            System.arraycopy(spriteVertices, offset, vertices, 0, copyCount);
            idx += copyCount;
            count -= copyCount;
        }
    }

    @Override
    public void draw (TextureRegion region, float x, float y) {
        draw(region, x, y, region.getRegionWidth(), region.getRegionHeight());
    }

    @Override
    public void draw (TextureRegion region, float x, float y, float width, float height) {
        if (!drawing) throw new IllegalStateException("ColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        Texture texture = region.getTexture();
        if (texture != lastTexture) {
            switchTexture(texture);
        } else if (idx == vertices.length) {
            flush();
        }
        final float fx2 = x + width;
        final float fy2 = y + height;
        final float u = region.getU();
        final float v = region.getV2();
        final float u2 = region.getU2();
        final float v2 = region.getV();

        final float color = this.color;
        final float tweak = this.tweak;
        final int idx = this.idx;
        vertices[idx] = x;
        vertices[idx + 1] = y;
        vertices[idx + 2] = color;
        vertices[idx + 3] = u;
        vertices[idx + 4] = v;
        vertices[idx + 5] = tweak;

        vertices[idx + 6] = x;
        vertices[idx + 7] = fy2;
        vertices[idx + 8] = color;
        vertices[idx + 9] = u;
        vertices[idx + 10] = v2;
        vertices[idx + 11] = tweak;

        vertices[idx + 12] = fx2;
        vertices[idx + 13] = fy2;
        vertices[idx + 14] = color;
        vertices[idx + 15] = u2;
        vertices[idx + 16] = v2;
        vertices[idx + 17] = tweak;

        vertices[idx + 18] = fx2;
        vertices[idx + 19] = y;
        vertices[idx + 20] = color;
        vertices[idx + 21] = u2;
        vertices[idx + 22] = v;
        vertices[idx + 23] = tweak;
        this.idx = idx + 24;
    }

    @Override
    public void draw (TextureRegion region, float x, float y, float originX, float originY, float width, float height,
                      float scaleX, float scaleY, float rotation) {
        if (!drawing) throw new IllegalStateException("ColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        Texture texture = region.getTexture();
        if (texture != lastTexture) {
            switchTexture(texture);
        } else if (idx == vertices.length) //
            flush();

        // bottom left and top right corner points relative to origin
        final float worldOriginX = x + originX;
        final float worldOriginY = y + originY;
        float fx = -originX;
        float fy = -originY;
        float fx2 = width - originX;
        float fy2 = height - originY;

        // scale
        if (scaleX != 1 || scaleY != 1) {
            fx *= scaleX;
            fy *= scaleY;
            fx2 *= scaleX;
            fy2 *= scaleY;
        }

        // construct corner points, start from top left and go counter clockwise
        final float p1x = fx;
        final float p1y = fy;
        final float p2x = fx;
        final float p2y = fy2;
        final float p3x = fx2;
        final float p3y = fy2;
        final float p4x = fx2;
        final float p4y = fy;

        float x1;
        float y1;
        float x2;
        float y2;
        float x3;
        float y3;
        float x4;
        float y4;

        // rotate
        if (rotation != 0) {
            final float cos = MathUtils.cosDeg(rotation);
            final float sin = MathUtils.sinDeg(rotation);

            x1 = cos * p1x - sin * p1y;
            y1 = sin * p1x + cos * p1y;

            x2 = cos * p2x - sin * p2y;
            y2 = sin * p2x + cos * p2y;

            x3 = cos * p3x - sin * p3y;
            y3 = sin * p3x + cos * p3y;

            x4 = x1 + (x3 - x2);
            y4 = y3 - (y2 - y1);
        } else {
            x1 = p1x;
            y1 = p1y;

            x2 = p2x;
            y2 = p2y;

            x3 = p3x;
            y3 = p3y;

            x4 = p4x;
            y4 = p4y;
        }

        x1 += worldOriginX;
        y1 += worldOriginY;
        x2 += worldOriginX;
        y2 += worldOriginY;
        x3 += worldOriginX;
        y3 += worldOriginY;
        x4 += worldOriginX;
        y4 += worldOriginY;

        final float u = region.getU();
        final float v = region.getV2();
        final float u2 = region.getU2();
        final float v2 = region.getV();

        final float color = this.color;
        final float tweak = this.tweak;
        final int idx = this.idx;
        vertices[idx] = x1;
        vertices[idx + 1] = y1;
        vertices[idx + 2] = color;
        vertices[idx + 3] = u;
        vertices[idx + 4] = v;
        vertices[idx + 5] = tweak;

        vertices[idx + 6] = x2;
        vertices[idx + 7] = y2;
        vertices[idx + 8] = color;
        vertices[idx + 9] = u;
        vertices[idx + 10] = v2;
        vertices[idx + 11] = tweak;

        vertices[idx + 12] = x3;
        vertices[idx + 13] = y3;
        vertices[idx + 14] = color;
        vertices[idx + 15] = u2;
        vertices[idx + 16] = v2;
        vertices[idx + 17] = tweak;

        vertices[idx + 18] = x4;
        vertices[idx + 19] = y4;
        vertices[idx + 20] = color;
        vertices[idx + 21] = u2;
        vertices[idx + 22] = v;
        vertices[idx + 23] = tweak;
        this.idx = idx + 24;
    }

    @Override
    public void draw (TextureRegion region, float x, float y, float originX, float originY, float width, float height,
                      float scaleX, float scaleY, float rotation, boolean clockwise) {
        if (!drawing) throw new IllegalStateException("ColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        Texture texture = region.getTexture();
        if (texture != lastTexture) {
            switchTexture(texture);
        } else if (idx == vertices.length) //
            flush();

        // bottom left and top right corner points relative to origin
        final float worldOriginX = x + originX;
        final float worldOriginY = y + originY;
        float fx = -originX;
        float fy = -originY;
        float fx2 = width - originX;
        float fy2 = height - originY;

        // scale
        if (scaleX != 1 || scaleY != 1) {
            fx *= scaleX;
            fy *= scaleY;
            fx2 *= scaleX;
            fy2 *= scaleY;
        }

        // construct corner points, start from top left and go counter clockwise
        final float p1x = fx;
        final float p1y = fy;
        final float p2x = fx;
        final float p2y = fy2;
        final float p3x = fx2;
        final float p3y = fy2;
        final float p4x = fx2;
        final float p4y = fy;

        float x1;
        float y1;
        float x2;
        float y2;
        float x3;
        float y3;
        float x4;
        float y4;

        // rotate
        if (rotation != 0) {
            final float cos = MathUtils.cosDeg(rotation);
            final float sin = MathUtils.sinDeg(rotation);

            x1 = cos * p1x - sin * p1y;
            y1 = sin * p1x + cos * p1y;

            x2 = cos * p2x - sin * p2y;
            y2 = sin * p2x + cos * p2y;

            x3 = cos * p3x - sin * p3y;
            y3 = sin * p3x + cos * p3y;

            x4 = x1 + (x3 - x2);
            y4 = y3 - (y2 - y1);
        } else {
            x1 = p1x;
            y1 = p1y;

            x2 = p2x;
            y2 = p2y;

            x3 = p3x;
            y3 = p3y;

            x4 = p4x;
            y4 = p4y;
        }

        x1 += worldOriginX;
        y1 += worldOriginY;
        x2 += worldOriginX;
        y2 += worldOriginY;
        x3 += worldOriginX;
        y3 += worldOriginY;
        x4 += worldOriginX;
        y4 += worldOriginY;

        float u1, v1, u2, v2, u3, v3, u4, v4;
        if (clockwise) {
            u1 = region.getU2();
            v1 = region.getV2();
            u2 = region.getU();
            v2 = region.getV2();
            u3 = region.getU();
            v3 = region.getV();
            u4 = region.getU2();
            v4 = region.getV();
        } else {
            u1 = region.getU();
            v1 = region.getV();
            u2 = region.getU2();
            v2 = region.getV();
            u3 = region.getU2();
            v3 = region.getV2();
            u4 = region.getU();
            v4 = region.getV2();
        }

        final float color = this.color;
        final float tweak = this.tweak;
        final int idx = this.idx;
        vertices[idx] = x1;
        vertices[idx + 1] = y1;
        vertices[idx + 2] = color;
        vertices[idx + 3] = u1;
        vertices[idx + 4] = v1;
        vertices[idx + 5] = tweak;

        vertices[idx + 6] = x2;
        vertices[idx + 7] = y2;
        vertices[idx + 8] = color;
        vertices[idx + 9] = u2;
        vertices[idx + 10] = v2;
        vertices[idx + 11] = tweak;

        vertices[idx + 12] = x3;
        vertices[idx + 13] = y3;
        vertices[idx + 14] = color;
        vertices[idx + 15] = u3;
        vertices[idx + 16] = v3;
        vertices[idx + 17] = tweak;

        vertices[idx + 18] = x4;
        vertices[idx + 19] = y4;
        vertices[idx + 20] = color;
        vertices[idx + 21] = u4;
        vertices[idx + 22] = v4;
        vertices[idx + 23] = tweak;
        this.idx = idx + 24;
    }

    @Override
    public void draw (TextureRegion region, float width, float height, Affine2 transform) {
        if (!drawing) throw new IllegalStateException("ColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        Texture texture = region.getTexture();
        if (texture != lastTexture) {
            switchTexture(texture);
        } else if (idx == vertices.length) {
            flush();
        }

        // construct corner points
        float x1 = transform.m02;
        float y1 = transform.m12;
        float x2 = transform.m01 * height + transform.m02;
        float y2 = transform.m11 * height + transform.m12;
        float x3 = transform.m00 * width + transform.m01 * height + transform.m02;
        float y3 = transform.m10 * width + transform.m11 * height + transform.m12;
        float x4 = transform.m00 * width + transform.m02;
        float y4 = transform.m10 * width + transform.m12;

        float u = region.getU();
        float v = region.getV2();
        float u2 = region.getV2();
        float v2 = region.getV();

        final float color = this.color;
        final float tweak = this.tweak;
        final int idx = this.idx;
        vertices[idx] = x1;
        vertices[idx + 1] = y1;
        vertices[idx + 2] = color;
        vertices[idx + 3] = u;
        vertices[idx + 4] = v;
        vertices[idx + 5] = tweak;

        vertices[idx + 6] = x2;
        vertices[idx + 7] = y2;
        vertices[idx + 8] = color;
        vertices[idx + 9] = u;
        vertices[idx + 10] = v2;
        vertices[idx + 11] = tweak;

        vertices[idx + 12] = x3;
        vertices[idx + 13] = y3;
        vertices[idx + 14] = color;
        vertices[idx + 15] = u2;
        vertices[idx + 16] = v2;
        vertices[idx + 17] = tweak;

        vertices[idx + 18] = x4;
        vertices[idx + 19] = y4;
        vertices[idx + 20] = color;
        vertices[idx + 21] = u2;
        vertices[idx + 22] = v;
        vertices[idx + 23] = tweak;
        this.idx = idx + 24;
    }

    @SuppressWarnings("RedundantCast") // These casts are absolutely not redundant! Java 9 changed Buffer ABI.
    @Override
    public void flush () {
        if (idx == 0) return;

        renderCalls++;
        totalRenderCalls++;
        int spritesInBatch = idx / SPRITE_SIZE;
        if (spritesInBatch > maxSpritesInBatch) maxSpritesInBatch = spritesInBatch;
        int count = spritesInBatch * 6;

        lastTexture.bind();
        Mesh mesh = this.mesh;
        mesh.setVertices(vertices, 0, idx);
        ((Buffer)mesh.getIndicesBuffer()).position(0);
        ((Buffer)mesh.getIndicesBuffer()).limit(count);

        if (blendingDisabled) {
            Gdx.gl.glDisable(GL20.GL_BLEND);
        } else {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            if (blendSrcFunc != -1) Gdx.gl.glBlendFuncSeparate(blendSrcFunc, blendDstFunc, blendSrcFuncAlpha, blendDstFuncAlpha);
        }

        mesh.render(customShader != null ? customShader : shader, GL20.GL_TRIANGLES, 0, count);

        idx = 0;
    }

    @Override
    public void disableBlending () {
        if (blendingDisabled) return;
        flush();
        blendingDisabled = true;
    }

    @Override
    public void enableBlending () {
        if (!blendingDisabled) return;
        flush();
        blendingDisabled = false;
    }

    @Override
    public void setBlendFunction (int srcFunc, int dstFunc) {
        setBlendFunctionSeparate(srcFunc, dstFunc, srcFunc, dstFunc);
    }

    @Override
    public void setBlendFunctionSeparate(int srcFuncColor, int dstFuncColor, int srcFuncAlpha, int dstFuncAlpha) {
        if (blendSrcFunc == srcFuncColor && blendDstFunc == dstFuncColor && blendSrcFuncAlpha == srcFuncAlpha && blendDstFuncAlpha == dstFuncAlpha) return;
        flush();
        blendSrcFunc = srcFuncColor;
        blendDstFunc = dstFuncColor;
        blendSrcFuncAlpha = srcFuncAlpha;
        blendDstFuncAlpha = dstFuncAlpha;
    }

    @Override
    public int getBlendSrcFunc () {
        return blendSrcFunc;
    }

    @Override
    public int getBlendDstFunc () {
        return blendDstFunc;
    }

    @Override
    public int getBlendSrcFuncAlpha() {
        return blendSrcFuncAlpha;
    }

    @Override
    public int getBlendDstFuncAlpha() {
        return blendDstFuncAlpha;
    }

    @Override
    public void dispose () {
        mesh.dispose();
        if (ownsShader && shader != null) shader.dispose();
    }

    @Override
    public Matrix4 getProjectionMatrix () {
        return projectionMatrix;
    }

    @Override
    public Matrix4 getTransformMatrix () {
        return transformMatrix;
    }

    @Override
    public void setProjectionMatrix (Matrix4 projection) {
        if (drawing) flush();
        projectionMatrix.set(projection);
        if (drawing) setupMatrices();
    }

    @Override
    public void setTransformMatrix (Matrix4 transform) {
        if (drawing) flush();
        transformMatrix.set(transform);
        if (drawing) setupMatrices();
    }

    protected void setupMatrices () {
        combinedMatrix.set(projectionMatrix).mul(transformMatrix);
        if (customShader != null) {
            customShader.setUniformMatrix("u_projTrans", combinedMatrix);
            customShader.setUniformi("u_texture", 0);
        } else {
            shader.setUniformMatrix("u_projTrans", combinedMatrix);
            shader.setUniformi("u_texture", 0);
        }
    }

    protected void switchTexture (Texture texture) {
        flush();
        lastTexture = texture;
        invTexWidth = 1.0f / texture.getWidth();
        invTexHeight = 1.0f / texture.getHeight();
    }

    @Override
    public void setShader (ShaderProgram shader) {
        if (drawing) {
            flush();
        }
        customShader = shader;
        if (drawing) {
            if (customShader != null)
                customShader.bind();
            else
                this.shader.bind();
            setupMatrices();
        }
    }

    @Override
    public ShaderProgram getShader () {
        if (customShader == null) {
            return shader;
        }
        return customShader;
    }

    @Override
    public boolean isBlendingEnabled () {
        return !blendingDisabled;
    }

    public boolean isDrawing () {
        return drawing;
    }
    static public final int X1 = 0;
    static public final int Y1 = 1;
    static public final int C1 = 2;
    static public final int U1 = 3;
    static public final int V1 = 4;
    static public final int T1 = 5;
    static public final int X2 = 6;
    static public final int Y2 = 7;
    static public final int C2 = 8;
    static public final int U2 = 9;
    static public final int V2 = 10;
    static public final int T2 = 11;
    static public final int X3 = 12;
    static public final int Y3 = 13;
    static public final int C3 = 14;
    static public final int U3 = 15;
    static public final int V3 = 16;
    static public final int T3 = 17;
    static public final int X4 = 18;
    static public final int Y4 = 19;
    static public final int C4 = 20;
    static public final int U4 = 21;
    static public final int V4 = 22;
    static public final int T4 = 23;
}
