package com.github.tommyettinger.colorful.rgb;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Mesh.VertexDataType;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.NumberUtils;

/** Draws batched quads using indices.
 * <p>
 * This is an optimized version of the SpriteBatch that maintains an LFU texture-cache to combine draw calls with different
 * textures effectively.
 * <p>
 * Use this Batch if you frequently utilize more than a single texture between calling {@link #begin()} and {@link #end()}. An
 * example would be if your Atlas is spread over multiple Textures or if you draw with individual Textures.
 *
 * @see Batch
 * @see SpriteBatch
 *
 * @author mzechner (Original SpriteBatch)
 * @author Nathan Sweet (Original SpriteBatch)
 * @author VaTTeRGeR (TextureArray Extension) */
public class TextureArrayColorfulBatch extends ColorfulBatch {
    private int idx = 0;

    public final int spriteVertexSize = 6;//Size of a ColorfulSprite
    public final int spriteFloatSize = spriteVertexSize * 4 + 4;//Sprite.SPRITE_SIZE;
    /**
     * The name of the attribute used for the tweak color in GLSL shaders.
     */
    public static final String TWEAK_ATTRIBUTE = "a_tweak";
    public static final String TEXTURE_INDEX_ATTRIBUTE = "a_texture_index";

    /** The maximum number of available texture units for the fragment shader */
    private static int maxTextureUnits = -1;

    /** Textures in use (index: Texture Unit, value: Texture) */
    private final Texture[] usedTextures;

    /** LFU Array (index: Texture Unit Index - value: Access frequency) */
    private final int[] usedTexturesLFU;

    /** Gets sent to the fragment shader as a uniform "uniform sampler2d[X] u_textures" */
    private final IntBuffer textureUnitIndicesBuffer;

    private static String shaderErrorLog = null;

    /**
     * A constant packed float that can be assigned to this ColorfulBatch's tweak with {@link #setTweak(float)} to make
     * all the tweak adjustments virtually imperceptible. When this is set as the tweak, it won't change the
     * lightness multiplier or lightness contrast, and it won't change either chromatic value multiplier.
     */
    public static final float TWEAK_RESET = ColorTools.rgb(0.5f, 0.5f, 0.5f, 0.5f);

    /** The current number of textures in the LFU cache. Gets reset when calling {@link #begin()} **/
    protected int currentTextureLFUSize = 0;

    /** The current number of texture swaps in the LFU cache. Gets reset when calling {@link #begin()} **/
    protected int currentTextureLFUSwaps = 0;

    /** Constructs a new TextureArrayColorfulBatch with a size of 1000, one buffer, and the default shader.
     * @see TextureArrayColorfulBatch#TextureArrayColorfulBatch(int, ShaderProgram) */
    public TextureArrayColorfulBatch() {
        this(1000);
    }

    /** Constructs a TextureArrayColorfulBatch with one buffer and the default shader.
     * @see TextureArrayColorfulBatch#TextureArrayColorfulBatch(int, ShaderProgram) */
    public TextureArrayColorfulBatch(int size) {
        this(size, null);
    }

    /** Constructs a new TextureArrayColorfulBatch. Sets the projection matrix to an orthographic projection with y-axis point
     * upwards, x-axis point to the right and the origin being in the bottom left corner of the screen. The projection will be
     * pixel perfect with respect to the current screen resolution.
     * <p>
     * The defaultShader specifies the shader to use. Note that the names for uniforms for this default shader are different than
     * the ones expect for shaders set with {@link #setShader(ShaderProgram)}.
     * @param size The max number of sprites in a single batch. Max of 8191.
     * @param defaultShader The default shader to use. This is not owned by the TextureArrayColorfulBatch and must be disposed
     *           separately.
     * @throws IllegalStateException Thrown if the device does not support texture arrays. Make sure to implement a Fallback to
     *            {@link SpriteBatch} in case Texture Arrays are not supported on a clients device.
     * @see #createDefaultShader(int)
     * @see #getMaxTextureUnits() */
    public TextureArrayColorfulBatch(int size, ShaderProgram defaultShader) throws IllegalStateException {
        // 32767 is max vertex index, so 32767 / 4 vertices per sprite = 8191 sprites max.
        if (size > 8191) throw new IllegalArgumentException("Can't have more than 8191 sprites per batch: " + size);

        getMaxTextureUnits();

        if (maxTextureUnits == 0) {
            throw new IllegalStateException(
                    "Texture Arrays are not supported on this device:" + System.lineSeparator() + shaderErrorLog);
        }

        if (defaultShader == null) {
            shader = createDefaultShader(maxTextureUnits);
            ownsShader = true;

        } else {
            shader = defaultShader;
            ownsShader = false;
        }

        usedTextures = new Texture[maxTextureUnits];
        usedTexturesLFU = new int[maxTextureUnits];

        // This contains the numbers 0 ... maxTextureUnits - 1. We send these to the shader as a uniform.
        textureUnitIndicesBuffer = BufferUtils.newIntBuffer(maxTextureUnits);
        for (int i = 0; i < maxTextureUnits; i++) {
            textureUnitIndicesBuffer.put(i);
        }
        textureUnitIndicesBuffer.flip();

        VertexDataType vertexDataType = (Gdx.gl30 != null) ? VertexDataType.VertexBufferObjectWithVAO : VertexDataType.VertexArray;

        // The vertex data is extended with one float for the texture index and one float for the tweak.
        mesh = new Mesh(vertexDataType, false, size * 4, size * 6,
                new VertexAttribute(Usage.Position, 2, ShaderProgram.POSITION_ATTRIBUTE),
                new VertexAttribute(Usage.ColorPacked, 4, ShaderProgram.COLOR_ATTRIBUTE),
                new VertexAttribute(Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE + "0"),
                new VertexAttribute(VertexAttributes.Usage.ColorPacked, 4, TWEAK_ATTRIBUTE),
                new VertexAttribute(Usage.Generic, 1, TEXTURE_INDEX_ATTRIBUTE));

        projectionMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        vertices = new float[size * spriteFloatSize];

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
    }

    /**
     * Returns a new instance of the default shader used by TextureArrayColorfulBatch for GL2 when no shader is
     * specified. Does not have any {@code #version}
     * specified in the shader source. This expects an extra attribute (relative to a normal SpriteBatch) that is used
     * for the tweak. You may want to set the code to prepend before you call this, as with:
     * {@code ShaderProgram.prependVertexCode = "#version 110\n";
     * ShaderProgram.prependFragmentCode = "#version 110\n";}
     * The actual version can be different, and may need to be different for compatibility with some hardware.
     * @see #getMaxTextureUnits()
     * @param maxTextureUnits look this up once with {@link #getMaxTextureUnits()} for the current hardware
     * @return the default ShaderProgram for this Batch
     */
    public static ShaderProgram createDefaultShader (int maxTextureUnits) {
        // The texture index is just passed to the fragment shader, maybe there's a more elegant way.
        String vertexShader =   "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
                              + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
                              + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
                              + "attribute float " + TEXTURE_INDEX_ATTRIBUTE + ";\n"
                              + "attribute vec4 " + TWEAK_ATTRIBUTE + ";\n"
                              + "uniform mat4 u_projTrans;\n"
                              + "varying vec4 v_color;\n"
                              + "varying vec4 v_tweak;\n"
                              + "varying vec2 v_texCoords;\n"
                              + "varying float v_texture_index;\n"
                              + "\n"
                              + "void main()\n"
                              + "{\n"
                              + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
                              + "   v_color.rgb = v_color.rgb - 0.5;\n"
                              + "   v_color.a = v_color.a * (255.0/254.0);\n"
                              + "   v_tweak = " + TWEAK_ATTRIBUTE + ";\n"
                              + "   v_tweak.a = v_tweak.a * (255.0/254.0);\n"
                              + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
                              + "   v_texture_index = " + TEXTURE_INDEX_ATTRIBUTE + ";\n"
                              + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
                              + "}\n";

        // The texture is simply selected from an array of textures
        String fragmentShader =   "#ifdef GL_ES\n"
                                + "#define LOWP lowp\n"
                                + "precision mediump float;\n"
                                + "#else\n"
                                + "#define LOWP\n"
                                + "#endif\n"
                                + "varying LOWP vec4 v_color;\n"
                                + "varying LOWP vec4 v_tweak;\n"
                                + "varying vec2 v_texCoords;\n"
                                + "varying float v_texture_index;\n"
                                + "uniform sampler2D u_textures[" + maxTextureUnits + "];\n"
                                + "vec3 barronSpline(vec3 x, float shape) {\n"
                                + "        const float turning = 0.5;\n"
                                + "        vec3 d = turning - x;\n"
                                + "        return mix(\n"
                                + "          ((1. - turning) * (x - 1.)) / (1. - (x + shape * d)) + 1.,\n"
                                + "          (turning * x) / (1.0e-20 + (x + shape * d)),\n"
                                + "          step(0.0, d));\n"
                                + "}\n"
                                + "void main()\n"//
                                + "{\n"
                                + "  int index = int(v_texture_index);"
                                + "  vec4 tgt = texture2D(u_textures[index], v_texCoords);\n"
                                + "  tgt.rgb = barronSpline(clamp(tgt.rgb * v_tweak.rgb * 2.0 + v_color.rgb, 0.0, 1.0), v_tweak.a * 1.5 + 0.25);\n"
                                + "  tgt.a *= v_color.a;\n"
                                + "  gl_FragColor = tgt;\n"
                                + "}";

        final ApplicationType appType = Gdx.app.getType();

        if (appType == ApplicationType.Android || appType == ApplicationType.iOS || appType == ApplicationType.WebGL) {
            fragmentShader = "#version 100\n" + fragmentShader;
        } else {
            fragmentShader = "#version 150\n" + fragmentShader;
        }

        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);

        if (!shader.isCompiled()) {
            throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
        }

        return shader;
    }

    @Override
    public void begin () {
        if (drawing) throw new IllegalStateException("TextureArrayColorfulBatch.end must be called before begin.");

        renderCalls = 0;

        currentTextureLFUSize = 0;
        currentTextureLFUSwaps = 0;

        Arrays.fill(usedTextures, null);
        Arrays.fill(usedTexturesLFU, 0);

        Gdx.gl.glDepthMask(false);

        if (customShader != null) {
            customShader.begin();
        } else {
            shader.begin();
        }

        setupMatrices();

        drawing = true;
    }

    @Override
    public void end () {
        if (!drawing) throw new IllegalStateException("TextureArrayColorfulBatch.begin must be called before end.");

        if (idx > 0) flush();

        drawing = false;

        GL20 gl = Gdx.gl;

        gl.glDepthMask(true);

        if (isBlendingEnabled()) {
            gl.glDisable(GL20.GL_BLEND);
        }

        if (customShader != null) {
            customShader.end();
        } else {
            shader.end();
        }
    }

    @Override
    public void dispose () {

        mesh.dispose();

        if (ownsShader && shader != null) {
            shader.dispose();
        }
    }


    @Override
    public void setColor (Color tint) {
        color = tint.toFloatBits();
    }

    /**
     * Sets the color to the result of {@link ColorTools#rgb(float, float, float, float)} on the same arguments.
     * For the RGB parameters, 0.5f is a neutral value (causing no change), while for alpha, 1.0f is a neutral
     * value. For RGB, higher values will add to the corresponding channel, while lower values will subtract from it.
     * @see ColorTools#rgb(float, float, float, float)
     * @param red additive red channel, from 0 to 1; 0.5 is neutral
     * @param green additive green channel, from 0 to 1; 0.5 is neutral
     * @param blue additive blue channel, from 0 to 1; 0.5 is neutral
     * @param alpha multiplicative opacity, from 0 to 1; 1.0 is neutral
     */
    @Override
    public void setColor (float red, float green, float blue, float alpha) {
        color = ColorTools.rgb(red, green, blue, alpha);
    }

    public void setColor (final float color) {
        setPackedColor(color);
    }

    public void setIntColor(final int color) {
        this.color = NumberUtils.intBitsToFloat(Integer.reverseBytes(color & -2));
    }

    /**
     * Sets the color with the given RGB and A parameters from 0 to 255.
     * For the RGB parameters, 127 is a neutral value (causing no change), while for alpha, 255 is a neutral
     * value. For RGB, higher values will add to the corresponding channel, while lower values will subtract from it.
     * @see ColorTools#rgb(float, float, float, float)
     * @param red additive red channel; ranges from 0 to 255, and 127 is neutral
     * @param green additive green channel; ranges from 0 to 255, and 127 is neutral
     * @param blue additive blue channel; ranges from 0 to 255, and 127 is neutral
     * @param alpha multiplicative opacity; ranges from 0 to 255 (254 is equivalent, since the lowest bit is discarded)
     */
    public void setIntColor(int red, int green, int blue, int alpha) {
        color = NumberUtils.intBitsToFloat((alpha << 24 & 0xFE000000)
                | (blue << 16 & 0xFF0000) | (green << 8 & 0xFF00) | (red & 0xFF));
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
        color.a = (intBits >>> 25) / 127f;
        return color;
    }

    @Override
    public float getPackedColor () {
        return color;
    }

    /**
     * Sets the multiplicative and contrast parts of the shader's color changes. 0.5 is a neutral value that should have
     * minimal effect on the image; using {@code (0.5f, 0.5f, 0.5f, 0.5f)} will effectively remove the tweak. You can
     * also use {@link #setTweak(float)} with {@link #TWEAK_RESET}, which is very slightly more efficient, to remove the
     * tweak or set it to a neutral value.
     * @param red multiplicative red channel, from 0 to 1; 0.5 is neutral
     * @param green multiplicative green channel, from 0 to 1; 0.5 is neutral
     * @param blue multiplicative blue channel, from 0 to 1; 0.5 is neutral
     * @param contrast affects how lightness changes, from 0 (low contrast, cloudy look) to 1 (high contrast, sharpened look); 0.5 is neutral
     */
    public void setTweak (float red, float green, float blue, float contrast) {
        tweak = ColorTools.rgb(red, green, blue, contrast);
    }
    /**
     * Sets the tweak using a single packed RGB float.
     * @see #setTweak(float, float, float, float)
     * @param tweak a packed RGB float, with contrast instead of alpha
     */
    public void setTweak (final float tweak) {
        this.tweak = tweak;
    }

    public float getTweak () {
        return tweak;
    }

    /**
     * Takes the tweak as an int in the format: red (8 bits), green (8 bits), blue (8 bits), contrast (7 bits),
     * (1 ignored bit at the end). An example would be 0xC820206E, which significantly emphasizes red (with red 0xC8,
     * a little over 3/4 of the max and higher than the neutral value of 0x80), significantly reduces green and blue
     * effect with green and blue multipliers of 0x20 (closer to 0, so most green and blue in the final color, if any,
     * will come from the additive color), and slightly reduces contrast (with contrast and the ignored bit as 0x6E,
     * which is less than the halfway point).
     * @param tweak the tweak to use as an integer, with red in the most significant bits and contrast in least
     */
    public void setIntTweak(final int tweak) {
        this.tweak = NumberUtils.intBitsToFloat(Integer.reverseBytes(tweak & -2));
    }

    /**
     * Sets the multiplicative and contrast parts of the shader's color changes. 127 is a neutral value that should have
     * minimal effect on the image; using {@code (127, 127, 127, 127)} will effectively remove the tweak.
     * @param red multiplicative red channel, from 0 to 255; 127 is neutral
     * @param green multiplicative green channel, from 0 to 255; 127 is neutral
     * @param blue multiplicative blue channel, from 0 to 255; 127 is neutral
     * @param contrast affects how lightness changes, from 0 (low contrast, cloudy look) to 255 (high contrast, sharpened look); 127 is neutral
     */
    public void setIntTweak(int red, int green, int blue, int contrast) {
        tweak = NumberUtils.intBitsToFloat((contrast << 24 & 0xFE000000)
                | (blue << 16 & 0xFF0000) | (green << 8 & 0xFF00) | (red & 0xFF));
    }

    /**
     * A convenience method that sets both the color (with {@link #setColor(float)}) and the tweak (with
     * {@link #setTweak(float)}) at the same time.
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
     * @param redAdd additive red channel, from 0 to 1; 0.5 is neutral
     * @param greenAdd additive green channel, from 0 to 1; 0.5 is neutral
     * @param blueAdd additive blue channel, from 0 to 1; 0.5 is neutral
     * @param alphaMul multiplicative alpha channel, from 0 to 1; 1.0 is neutral
     * @param redMul multiplicative red channel, from 0 to 1; 0.5 is neutral
     * @param greenMul multiplicative green channel, from 0 to 1; 0.5 is neutral
     * @param blueMul multiplicative blue channel, from 0 to 1; 0.5 is neutral
     * @param contrast affects how lightness changes, from 0 (low contrast, cloudy look) to 1 (high contrast, sharpened look); 0.5 is neutral
     */
    public void setTweakedColor (final float redAdd, final float greenAdd,
                                 final float blueAdd, final float alphaMul,
                                 final float redMul, final float greenMul,
                                 final float blueMul, final float contrast) {
        setColor(redAdd, greenAdd, blueAdd, alphaMul);
        setTweak(redMul, greenMul, blueMul, contrast);
    }

    @Override
    public void draw (Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX,
                      float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY) {
        if (!drawing) throw new IllegalStateException("TextureArrayColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        flushIfFull();

        final float ti = activateTexture(texture);

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

        final float color = this.color, tweak = this.tweak;

        vertices[idx++] = x1;
        vertices[idx++] = y1;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x2;
        vertices[idx++] = y2;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x3;
        vertices[idx++] = y3;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x4;
        vertices[idx++] = y4;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;
    }

    @Override
    public void draw (Texture texture, float x, float y, float width, float height, int srcX, int srcY, int srcWidth,
                      int srcHeight, boolean flipX, boolean flipY) {

        if (!drawing) throw new IllegalStateException("TextureArrayColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        flushIfFull();

        final float ti = activateTexture(texture);

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

        final float color = this.color, tweak = this.tweak;

        vertices[idx++] = x;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = fx2;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = fx2;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;
    }

    @Override
    public void draw (Texture texture, float x, float y, int srcX, int srcY, int srcWidth, int srcHeight) {
        if (!drawing) throw new IllegalStateException("TextureArrayColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        flushIfFull();

        final float ti = activateTexture(texture);

        final float u = srcX * invTexWidth;
        final float v = (srcY + srcHeight) * invTexHeight;
        final float u2 = (srcX + srcWidth) * invTexWidth;
        final float v2 = srcY * invTexHeight;
        final float fx2 = x + srcWidth;
        final float fy2 = y + srcHeight;

        final float color = this.color, tweak = this.tweak;

        vertices[idx++] = x;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = fx2;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = fx2;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;
    }

    @Override
    public void draw (Texture texture, float x, float y, float width, float height, float u, float v, float u2, float v2) {
        if (!drawing) throw new IllegalStateException("TextureArrayColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        flushIfFull();

        final float ti = activateTexture(texture);

        final float fx2 = x + width;
        final float fy2 = y + height;

        final float color = this.color, tweak = this.tweak;

        vertices[idx++] = x;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = fx2;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = fx2;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;
    }

    @Override
    public void draw (Texture texture, float x, float y) {
        draw(texture, x, y, texture.getWidth(), texture.getHeight());
    }

    @Override
    public void draw (Texture texture, float x, float y, float width, float height) {

        if (!drawing) throw new IllegalStateException("TextureArrayColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        flushIfFull();

        final float ti = activateTexture(texture);

        final float fx2 = x + width;
        final float fy2 = y + height;
        final float u = 0;
        final float v = 1;
        final float u2 = 1;
        final float v2 = 0;

        final float color = this.color, tweak = this.tweak;

        vertices[idx++] = x;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = fx2;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = fx2;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;
    }

    @Override
    public void draw (Texture texture, float[] spriteVertices, int offset, int count) {
        if (!drawing)
            throw new IllegalStateException("TextureArrayColorfulBatch.begin must be called before draw.");

        count = (count / 5) * 7;
        int verticesLength = vertices.length;
        int remainingVertices = verticesLength;

        flushIfFull();

        // Assigns a texture unit to this texture, flushing if none is available
        final float ti = activateTexture(texture);

        int copyCount = Math.min(remainingVertices, count);
        final float tweak = this.tweak;

        ////old way, breaks when libGDX code expects SPRITE_SIZE to be 20
        //System.arraycopy(spriteVertices, offset, vertices, idx, copyCount);
        ////new way, thanks mgsx
        for (int s = offset, v = idx, i = 0; i < copyCount; i += 7) {
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = tweak;
            vertices[v++] = ti;
        }
        idx += copyCount;
        count -= copyCount;
        while (count > 0) {
            offset += (copyCount / 7) * 5;
            flush();
            copyCount = Math.min(verticesLength, count);
            ////old way, breaks when libGDX code expects SPRITE_SIZE to be 20
            //System.arraycopy(spriteVertices, offset, vertices, 0, copyCount);
            ////new way, thanks mgsx
            for (int s = offset, v = 0, i = 0; i < copyCount; i += 7) {
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = tweak;
                vertices[v++] = ti;
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

        count = (count / 6) * 7;
        int verticesLength = vertices.length;
        int remainingVertices = verticesLength;

        flushIfFull();

        // Assigns a texture unit to this texture, flushing if none is available
        final float ti = activateTexture(texture);

        int copyCount = Math.min(remainingVertices, count);

        for (int s = offset, v = idx, i = 0; i < copyCount; i += 7) {
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = spriteVertices[s++];
            vertices[v++] = ti;
        }
        idx += copyCount;
        count -= copyCount;
        while (count > 0) {
            offset += (copyCount / 7) * 6;
            flush();
            copyCount = Math.min(verticesLength, count);

            for (int s = offset, v = 0, i = 0; i < copyCount; i += 7) {
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = spriteVertices[s++];
                vertices[v++] = ti;
            }
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
        if (!drawing) throw new IllegalStateException("TextureArrayColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        flushIfFull();

        final float ti = activateTexture(region.getTexture());

        final float fx2 = x + width;
        final float fy2 = y + height;
        final float u = region.getU();
        final float v = region.getV2();
        final float u2 = region.getU2();
        final float v2 = region.getV();

        final float color = this.color, tweak = this.tweak;

        vertices[idx++] = x;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = fx2;
        vertices[idx++] = fy2;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = fx2;
        vertices[idx++] = y;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;
    }

    @Override
    public void draw (TextureRegion region, float x, float y, float originX, float originY, float width, float height,
                      float scaleX, float scaleY, float rotation) {

        if (!drawing) throw new IllegalStateException("TextureArrayColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        flushIfFull();

        final float ti = activateTexture(region.getTexture());

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

        final float color = this.color, tweak = this.tweak;

        vertices[idx++] = x1;
        vertices[idx++] = y1;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x2;
        vertices[idx++] = y2;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x3;
        vertices[idx++] = y3;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x4;
        vertices[idx++] = y4;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;
    }

    @Override
    public void draw (TextureRegion region, float x, float y, float originX, float originY, float width, float height,
                      float scaleX, float scaleY, float rotation, boolean clockwise) {

        if (!drawing) throw new IllegalStateException("TextureArrayColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        flushIfFull();

        final float ti = activateTexture(region.getTexture());

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

        final float color = this.color, tweak = this.tweak;

        vertices[idx++] = x1;
        vertices[idx++] = y1;
        vertices[idx++] = color;
        vertices[idx++] = u1;
        vertices[idx++] = v1;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x2;
        vertices[idx++] = y2;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x3;
        vertices[idx++] = y3;
        vertices[idx++] = color;
        vertices[idx++] = u3;
        vertices[idx++] = v3;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x4;
        vertices[idx++] = y4;
        vertices[idx++] = color;
        vertices[idx++] = u4;
        vertices[idx++] = v4;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;
    }

    @Override
    public void draw (TextureRegion region, float width, float height, Affine2 transform) {
        if (!drawing) throw new IllegalStateException("TextureArrayColorfulBatch.begin must be called before draw.");

        float[] vertices = this.vertices;

        flushIfFull();

        final float ti = activateTexture(region.getTexture());

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
        float u2 = region.getU2();
        float v2 = region.getV();

        final float color = this.color, tweak = this.tweak;

        vertices[idx++] = x1;
        vertices[idx++] = y1;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x2;
        vertices[idx++] = y2;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x3;
        vertices[idx++] = y3;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v2;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;

        vertices[idx++] = x4;
        vertices[idx++] = y4;
        vertices[idx++] = color;
        vertices[idx++] = u2;
        vertices[idx++] = v;
        vertices[idx++] = tweak;
        vertices[idx++] = ti;
    }

    /** Flushes if the vertices array cannot hold an additional sprite ((spriteVertexSize + 1) * 4 vertices) anymore. */
    private void flushIfFull () {
        // original Sprite attribute size plus two extra floats per sprite vertex
        if (vertices.length - idx < spriteFloatSize) {
            flush();
        }
    }

    @Override
    public void flush () {
        if (idx == 0) return;

        renderCalls++;
        totalRenderCalls++;

        int spritesInBatch = idx / spriteFloatSize;
        if (spritesInBatch > maxSpritesInBatch) maxSpritesInBatch = spritesInBatch;
        int count = spritesInBatch * 6;

        // Bind the textures
        for (int i = 0; i < currentTextureLFUSize; i++) {
            usedTextures[i].bind(i);
        }

        // Set TEXTURE0 as active again before drawing.
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);

        Mesh mesh = this.mesh;

        mesh.setVertices(vertices, 0, idx);

        Buffer indices = (Buffer) mesh.getIndicesBuffer(true);
        indices.position(0);
        indices.limit(count);

        if (blendingDisabled) {
            Gdx.gl.glDisable(GL20.GL_BLEND);
        } else {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            if (blendSrcFunc != -1) Gdx.gl.glBlendFuncSeparate(blendSrcFunc, blendDstFunc, blendSrcFuncAlpha, blendDstFuncAlpha);
        }

        if (customShader != null) {
            mesh.render(customShader, GL20.GL_TRIANGLES, 0, count);
        } else {
            mesh.render(shader, GL20.GL_TRIANGLES, 0, count);
        }

        idx = 0;
    }

    /** Assigns Texture units and manages the LFU cache.
     * @param texture The texture that shall be loaded into the cache, if it is not already loaded.
     * @return The texture slot that has been allocated to the selected texture */
    private int activateTexture (Texture texture) {
        invTexWidth = 1.0f / texture.getWidth();
        invTexHeight = 1.0f / texture.getHeight();

        // This is our identifier for the textures
        final int textureHandle = texture.getTextureObjectHandle();

        // First try to see if the texture is already cached
        for (int i = 0; i < currentTextureLFUSize; i++) {
            // getTextureObjectHandle() just returns an int,
            // it's fine to call this method instead of caching the value.
            if (textureHandle == usedTextures[i].getTextureObjectHandle()) {
                // Increase the access counter.
                usedTexturesLFU[i]++;
                return i;
            }
        }

        // If a free texture unit is available we just use it
        // If not we have to flush and then throw out the least accessed one.
        if (currentTextureLFUSize < maxTextureUnits) {
            // Put the texture into the next free slot
            usedTextures[currentTextureLFUSize] = texture;
            // Increase the access counter.
            usedTexturesLFU[currentTextureLFUSize]++;
            return currentTextureLFUSize++;
        }

        // We have to flush if there is something in the pipeline already,
        // otherwise the texture index of previously rendered sprites gets invalidated
        if (idx > 0) {
            flush();
        }

        int slot = 0;
        int slotVal = usedTexturesLFU[0];

        int max = 0;
        int average = 0;

        // We search for the best candidate for a swap (least accessed) and collect some data
        for (int i = 0; i < maxTextureUnits; i++) {
            final int val = usedTexturesLFU[i];
            max = Math.max(val, max);
            average += val;
            if (val <= slotVal) {
                slot = i;
                slotVal = val;
            }
        }

        // The LFU weights will be normalized to the range 0...100
        final int normalizeRange = 100;

        for (int i = 0; i < maxTextureUnits; i++) {
            usedTexturesLFU[i] = usedTexturesLFU[i] * normalizeRange / max;
        }

        average = (average * normalizeRange) / (max * maxTextureUnits);

        // Give the new texture a fair (average) chance of staying.
        usedTexturesLFU[slot] = average;

        usedTextures[slot] = texture;

        // For statistics
        currentTextureLFUSwaps++;

        return slot;
    }

    /** @return The number of texture swaps the LFU cache performed since calling {@link #begin()}. */
    public int getTextureLFUSwaps () {
        return currentTextureLFUSwaps;
    }

    /** @return The current number of textures in the LFU cache. Gets reset when calling {@link #begin()}. */
    public int getTextureLFUSize () {
        return currentTextureLFUSize;
    }

    /** @return The maximum number of textures that the LFU cache can hold. This limit is imposed by the driver.
     * @see #getMaxTextureUnits() */
    public int getTextureLFUCapacity () {
        return getMaxTextureUnits();
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
    public void setBlendFunctionSeparate (int srcFuncColor, int dstFuncColor, int srcFuncAlpha, int dstFuncAlpha) {
        if (blendSrcFunc == srcFuncColor && blendDstFunc == dstFuncColor && blendSrcFuncAlpha == srcFuncAlpha
            && blendDstFuncAlpha == dstFuncAlpha) {
            return;
        }

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
    public int getBlendSrcFuncAlpha () {
        return blendSrcFuncAlpha;
    }

    @Override
    public int getBlendDstFuncAlpha () {
        return blendDstFuncAlpha;
    }

    @Override
    public boolean isBlendingEnabled () {
        return !blendingDisabled;
    }

    @Override
    public boolean isDrawing () {
        return drawing;
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

    public void setupMatrices () {
        combinedMatrix.set(projectionMatrix).mul(transformMatrix);
        if (customShader != null) {
            customShader.setUniformMatrix("u_projTrans", combinedMatrix);
            Gdx.gl20.glUniform1iv(customShader.fetchUniformLocation("u_textures", true), maxTextureUnits, textureUnitIndicesBuffer);
        } else {
            shader.setUniformMatrix("u_projTrans", combinedMatrix);
            Gdx.gl20.glUniform1iv(shader.fetchUniformLocation("u_textures", true), maxTextureUnits, textureUnitIndicesBuffer);
        }
    }

    /** Queries the number of supported textures in a texture array by trying the create the default shader.<br>
     * The first call of this method is very expensive, after that it simply returns a cached value.
     * @return the number of supported textures in a texture array or zero if this feature is unsupported on this device.
     * @see #setShader(ShaderProgram shader) */
    public static int getMaxTextureUnits () {
        if (maxTextureUnits == -1) {
            // Query the number of available texture units and decide on a safe number of texture units to use
            IntBuffer texUnitsQueryBuffer = BufferUtils.newIntBuffer(32);

            Gdx.gl.glGetIntegerv(GL20.GL_MAX_TEXTURE_IMAGE_UNITS, texUnitsQueryBuffer);

            int maxTextureUnitsLocal = texUnitsQueryBuffer.get();

            // Some OpenGL drivers (I'm looking at you, Intel!) do not report the right values,
            // so we take caution and test it first, reducing the number of slots if needed.
            // Will try to find the maximum amount of texture units supported.
            while (maxTextureUnitsLocal > 0) {
                try {
                    ShaderProgram tempProg = createDefaultShader(maxTextureUnitsLocal);
                    tempProg.dispose();
                    break;
                } catch (Exception e) {
                    maxTextureUnitsLocal /= 2;
                    shaderErrorLog = e.getMessage();
                }
            }
            maxTextureUnits = maxTextureUnitsLocal;
        }
        return maxTextureUnits;
    }

    /** Sets the shader to be used in a GLES 2.0 environment. Vertex position attribute is called "a_position", the texture
     * coordinates attribute is called "a_texCoord0", the color attribute is called "a_color", texture unit index is called
     * "a_texture_index", this needs to be converted to int with int(...) in the fragment shader. See
     * {@link ShaderProgram#POSITION_ATTRIBUTE}, {@link ShaderProgram#COLOR_ATTRIBUTE} and {@link ShaderProgram#TEXCOORD_ATTRIBUTE}
     * which gets "0" appended to indicate the use of the first texture unit. The combined transform and projection matrix is
     * uploaded via a mat4 uniform called "u_projTrans". The texture sampler array is passed via a uniform called "u_textures", see
     * {@link TextureArrayColorfulBatch#createDefaultShader(int)} for reference.
     * <p>
     * Call this method with a null argument to use the default shader.
     * <p>
     * This method will flush the batch before setting the new shader, you can call it in between {@link #begin()} and
     * {@link #end()}.
     * @param shader the {@link ShaderProgram} or null to use the default shader.
     * @see #createDefaultShader(int)
     * @see #getMaxTextureUnits() */
    @Override
    public void setShader (ShaderProgram shader) {
        if (drawing) {
            flush();

            if (customShader != null) {
                customShader.end();
            } else {
                this.shader.end();
            }
        }

        customShader = shader;

        if (drawing) {
            if (customShader != null) {
                customShader.begin();
            } else {
                this.shader.begin();
            }

            setupMatrices();
        }
    }

    @Override
    public ShaderProgram getShader () {
        if (customShader != null) return customShader;
        return shader;
    }
}