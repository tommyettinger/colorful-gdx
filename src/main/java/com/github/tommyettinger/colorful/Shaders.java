package com.github.tommyettinger.colorful;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Constants that contain the critical shader code to construct a {@link ShaderProgram} that can render the rest of this
 * library. The shader code is meant for use in a {@link com.badlogic.gdx.graphics.g2d.SpriteBatch}; a convenience
 * method, {@link #makeBatch()}, is provided to generate and return a SpriteBatch that uses the correct ShaderProgram.
 * <br>
 * Created by Tommy Ettinger on 12/2/2019.
 */
public class Shaders {
    /**
     * This is the default vertex shader from libGDX.
     */
    public static final String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
            + "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
            + "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "uniform mat4 u_projTrans;\n"
            + "varying vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n"
            + "\n"
            + "void main()\n"
            + "{\n"
            + "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
            + "   v_color.a = v_color.a * (255.0/254.0);\n"
            + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
            + "}\n";
    /**
     * A simple shader that uses additive blending with "normal" RGBA colors (alpha is still multiplicative).
     * With the default SpriteBatch ShaderProgram, white is the neutral color, 50% gray darkens a color by about 50%,
     * and black darkens a color to black, but nothing can brighten a color. With this, 50% gray is the neutral color,
     * white adds 0.5 to the RGB channels (brightening it and also desaturating it), and black subtracts 0.5 from the
     * RGB channels (darkening and desaturating, but not to black unless the color is already somewhat dark). When
     * tinting with white, this looks like <a href="https://i.imgur.com/iAb1rig.png">The Mona Lisa on the left</a>, when
     * tinting with 50% gray, it makes no change, and when tinting with black, it almost reaches all black, but some
     * faint part of the image is still barely visible.
     */
    public static final String fragmentShaderRGBA =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "   gl_FragColor = clamp(vec4(tgt.rgb * pow((v_color.rgb + 0.1) * 1.666, vec3(1.5)), v_color.a * tgt.a), 0.0, 1.0);\n" +
                    "}";
    /**
     * A simple shader that uses multiplicative blending with "normal" RGBA colors, but internally uses gamma correction
     * to make changes in color smoother. With the default SpriteBatch ShaderProgram, white is the neutral color, 50%
     * gray darkens a color by about 50%, and black darkens a color to black, but nothing can brighten a color. With
     * this, 50% gray is the neutral color, white multiplies the RGB channels by about 2 (brightening it and slightly
     * desaturating it), and black multiplies the RGB channels by 0 (reducing the color always to black). When tinting
     * with white, this looks like <a href="https://i.imgur.com/gKRSzKv.png">The Mona Lisa on the left</a>; when tinting
     * with 50% gray, it makes no change, and when tinting with black, it produces an all-black image.
     * <br>
     * Credit for finding this goes to CypherCove, who uses a similar version in
     * <a href="https://github.com/CypherCove/gdx-tween/blob/5047eeae9250d1f1c52e87aaf572956045a523f9/gdx-tween/src/main/java/com/cyphercove/gdxtween/graphics/GtColor.java">gdx=tween</a>.
     */
    public static final String fragmentShaderGammaRGBA =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D(u_texture, v_texCoords);\n" +
                    "   gl_FragColor = clamp(vec4(sqrt(tgt.rgb * tgt.rgb * v_color.rgb * v_color.rgb * 4.0), v_color.a * tgt.a), 0.0, 1.0);\n" +
                    "}";
    //// save the result as shader, and set it on your batch with
    // ShaderProgram shader = makeRGBAShader();
    // batch.setShader(shader)
    /**
     * A simple helper method that builds the simplest shader here; this shader allows tinting with light colors to
     * lighten an image. You can assign the result to a SpriteBatch with its
     * {@link SpriteBatch#setShader(ShaderProgram)} method.
     * @return a ShaderProgram that uses the RGBA shader {@link #fragmentShaderRGBA}
     */
    public static ShaderProgram makeRGBAShader()
    {
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShaderRGBA);
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return shader;
    }
    /**
     * A simple helper method that builds the simplest shader here; this shader allows tinting with light colors to
     * lighten an image, and also smooths changes well. You can assign the result to a SpriteBatch with its
     * {@link SpriteBatch#setShader(ShaderProgram)} method.
     * @return a ShaderProgram that uses the RGBA shader {@link #fragmentShaderGammaRGBA}
     */
    public static ShaderProgram makeGammaRGBAShader()
    {
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShaderGammaRGBA);
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return shader;
    }

    /**
     * Where the magic happens; this converts a batch color from the YCwCmA format (used by colorful) to RGBA.
     * The vertex color will be split up into 4 channels just as a normal shader does, but the channels here are
     * luma, chromatic warmth, chromatic mildness, and alpha; alpha acts just like a typical RGBA shader, but the others
     * are additive instead of multiplicative, with 0.5 as a neutral value. This does not support the "tweak" features
     * that {@link ColorfulBatch} does, which include multiplicative counterparts to the additive operations this
     * supports on luma, chromatic warmth, and chromatic mildness, plus a contrast adjustment. If you want to adjust
     * contrast globally, you can use {@link #makeBatch(float)} to set the contrast for a new Batch with a new shader.
     */
    public static final String fragmentShader =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
//                    "const vec3 bright = vec3(0.75, 1.0, 0.25);\n" +
                    "const vec3 bright = vec3(0.375, 0.5, 0.125);\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    //use the following line to match the color exactly
                    "   vec3 ycc = vec3(v_color.r - 0.5 + dot(tgt.rgb, bright), ((v_color.g - 0.5) * 2.0 + tgt.r - tgt.b), ((v_color.b - 0.5) * 2.0 + tgt.g - tgt.b));\n" +
                    //use the following line to increase contrast
//                    "   vec3 ycc = vec3(v_color.r * dot(sin(tgt.rgb * 1.5707963267948966) * sqrt(tgt.rgb), bright), ((v_color.g - 0.5) * 2.0 + tgt.r - tgt.b), ((v_color.b - 0.5) * 2.0 + tgt.g - tgt.b));\n" +
                    //use the following line to increase contrast more
//                    "   vec3 ycc = vec3(v_color.r * pow(dot(tgt.rgb, bright), 1.25), ((v_color.g - 0.5) * 2.0 + tgt.r - tgt.b), ((v_color.b - 0.5) * 2.0 + tgt.g - tgt.b));\n" +

                    "   gl_FragColor = clamp(vec4(dot(ycc, vec3(1.0, 0.625, -0.5)), dot(ycc, vec3(1.0, -0.375, 0.5)), dot(ycc, vec3(1.0, -0.375, -0.5)), v_color.a * tgt.a), 0.0, 1.0);\n" +
                    "}";
    /**
     * A variant on {@link #fragmentShader} that adjusts luma to make mid-range colors darker, while keeping light
     * colors light. This is not the same as {@link ColorfulBatch} even when the contrast for ColorfulBatch's tweak is
     * the same as what this uses (1.375 here, which is roughly 0.875f in a tweak value). ColorfulBatch does some work
     * in the vertex shader so it may be a little faster than this approach, and it seems to have less severe effects on
     * the overall brightness of an image that has adjusted contrast.
     */
    public static final String fragmentShaderHigherContrast =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "const float contrast =    1.375   ;\n" + // you can make contrast a uniform if you want
                    "const vec3 bright = vec3(0.375, 0.5, 0.125) * (4.0 / 3.0);\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "   vec3 ycc = vec3(v_color.r - 0.5 + pow(dot(tgt.rgb, bright), contrast) * 0.75, ((v_color.g - 0.5) * 2.0 + tgt.r - tgt.b), ((v_color.b - 0.5) * 2.0 + tgt.g - tgt.b));\n" +
                    "   gl_FragColor = clamp(vec4(dot(ycc, vec3(1.0, 0.625, -0.5)), dot(ycc, vec3(1.0, -0.375, 0.5)), dot(ycc, vec3(1.0, -0.375, -0.5)), v_color.a * tgt.a), 0.0, 1.0);\n" +
                    "}";

    /**
     * An alternative shader that effectively reduces luma contrast, bringing all but the darkest colors to the
     * upper-mid luma range. This is not the same as {@link ColorfulBatch} even when the contrast for ColorfulBatch's
     * tweak is the same as what this uses (0.625 here, which is roughly 0.125f in a tweak value). ColorfulBatch does
     * some work in the vertex shader so it may be a little faster than this approach, and it seems to have less severe
     * effects on the overall brightness of an image that has adjusted contrast.
     */
    public static final String fragmentShaderLowerContrast = fragmentShaderHigherContrast.replace("   1.375   ", "0.625");

    /**
     * Prepares and returns a new SpriteBatch that uses the default {@link #vertexShader} and {@link #fragmentShader}
     * from this class, making it able to render YCwCmA colors from the rest of this library. It won't be a
     * {@link ColorfulBatch} (those can adjust colors in more ways); you can simply use {@code new ColorfulBatch()} to
     * make one of those. Note that a SpriteBatch won't be able to render a {@link ColorfulSprite}, but ColorfulBatch
     * can.
     * @return a freshly allocated SpriteBatch that will also have a new ShaderProgram for rendering YCwCmA
     */
    public static SpriteBatch makeBatch()
    {
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return new SpriteBatch(1000, shader);
    }

    /**
     * Prepares and returns a new SpriteBatch that uses the default {@link #vertexShader} and {@link #fragmentShader}
     * from this class, making it able to render YCwCmA colors from the rest of this library. This also takes a
     * {@code contrast} parameter; if greater than 1.0 it will make light colors lighter and dark colors darker, while
     * if it is less than 1.0 it will make all but the darkest colors closer to the upper-middle-range of lightness.
     * If you want to adjust contrast per-sprite, use a {@link ColorfulBatch} (those can adjust colors in more ways);
     * you can simply use {@code new ColorfulBatch()} to make one of those. Note that a SpriteBatch won't be able
     * to render a {@link ColorfulSprite}, but ColorfulBatch can. ColorfulBatch also will calculate contrast differently
     * from the shader this uses, including doing some work in the vertex shader (which may be faster). It also takes a
     * contrast in its tweak value that is limited to a 0.0 to 1.0 range, rather than 0.1 to 2.0 here (this can
     * technically tolerate 0.01 to 10.0, but those extremes aren't recommended).
     * @param contrast how much contrast should be emphasized; higher than 1.0 is more contrasting, and this should usually be between 0.1 and 2.0
     * @return a freshly allocated SpriteBatch that will also have a new ShaderProgram for rendering YCwCmA
     */
    public static SpriteBatch makeBatch(final float contrast)
    {
        ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShaderHigherContrast.replace("   1.375   ", Float.toString(MathUtils.clamp(contrast, 0.01f, 10f))));
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return new SpriteBatch(1000, shader);
    }

    /**
     * Prepares and returns a new SpriteBatch that uses {@link #vertexShaderHSLC} and {@link #fragmentShaderHSLC}
     * from this class, making it interpret the SpriteBatch's color, as set by 
     * {@link SpriteBatch#setColor(float, float, float, float)}, to be hue rotation, saturation change, lightness
     * change, and contrast change. All of these are neutral when their value is 0.5f. Hue rotates clockwise or
     * counterclockwise when it goes toward 0.0, and the other way when it goes toward 1.0; 0.0 and 1.0 both refer to
     * the 180 degree rotation, but I don't know which goes which way (it also depends on how you visualize hue).
     * Saturation becomes more grayscale as it goes towards 0.0, and more vivid as it goes towards 1.0. Lightness gets
     * darker towards 0.0, lighter towards 1.0 (any lightness above 0.5 will brighten the image, unlike the default
     * shader and batch color). Contrast affects changes in lightness; low contrast makes all lightness closer to the
     * mid-range, while high contrast makes even small changes in the mid-range of an image's color have stark lightness
     * changes in the result.
     * @return a freshly allocated SpriteBatch that will also have a new ShaderProgram for rendering YCwCmA
     */
    public static SpriteBatch makeBatchHSLC()
    {
        ShaderProgram shader = new ShaderProgram(vertexShaderHSLC, fragmentShaderHSLC);
        if(!shader.isCompiled())
            throw new GdxRuntimeException("Couldn't compile shader: " + shader.getLog());
        return new SpriteBatch(1000, shader);
    }

    /**
     * Similar to {@link #fragmentShader}, but this uses the very perceptually-accurate IPT color space as described by
     * Ebner and Fairchild, instead of the custom YCwCm color space. IPT doesn't really need that much more computation
     * to be done by the shader, but tends to make gradual changes in color much smoother. If comparing to YCwCm, then
     * Y (luma) is like I (intensity) here, so when a Batch color has 0 for I (stored in the r channel), it makes the
     * image very dark, while if the Batch color has 1 for I, it makes the image very light. Cw and Cm can be graphed
     * like a color wheel, and in their case, the 4 corners of a square graph are red, yellow, green, and blue. IPT is
     * less geometrical, and the corners are roughly purple, orange, green, and cyan, while the centers of the edges of
     * the square are close to red, yellow, green, and blue. <a href="https://i.imgur.com/A3n4qmM.png">See this capture
     * of the IPT space</a> if you want a better picture. The P (short for protan, a term from ophthalmology) channel
     * (stored in g) is the left-to-right axis there, with P==0 making colors green or blue (cooler), and P==1 making
     * colors orange, red, or purple (somewhat warmer). The T (short for tritan, also from ophthalmology) channel
     * (stored in b) is the down-to-up axis there, with T==0 making colors more blue or purple, and T==1 making colors
     * more green, yellow, brown, or orange. Where protan can be viewed as going from cool to warm as its value
     * increases, tritan can be viewed as going from artificial to natural, or perhaps fluid to solid. Alpha is treated
     * exactly as it is in the standard libGDX fragment shader, with the alpha in the batch color multiplied by the
     * alpha in the image pixel to get the result alpha.
     */
    public static String fragmentShaderIPT =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "void main()\n" +
                    "{\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    vec3 ipt = (mat3(+0.4000, +6.6825, +1.0741, +0.4000, -7.2765, +0.4763, +0.2000, +0.5940, -1.5504) * \n" +
                    "        (pow(mat3(0.313921, 0.151693, 0.017700, 0.639468, 0.748209, 0.109400, 0.0465970, 0.1000044, 0.8729000) * (tgt.rgb), vec3(0.43))))\n" +
                    "        + v_color.rgb - 0.5;\n" +
                    "    vec3 back = mat3(1.0, 1.0, 1.0, 0.06503950, -0.07591241, 0.02174116, 0.15391950, 0.09991275, -0.50766750) * ipt;\n" +
                    "    back = mat3(5.432622, -1.10517, 0.028104, -4.67910, 2.311198, -0.19466, 0.246257, -0.20588, 1.166325) * \n" +
                    "        (pow(abs(back), vec3(2.3256)) * sign(back));\n" +
                    "    gl_FragColor = vec4(clamp(back, 0.0, 1.0), v_color.a * tgt.a);\n" +
                    "}";


    /**
     * Credit to Sam Hocevar, https://gamedev.stackexchange.com/a/59808 .
     */
    public static final String partialCodeHSL =
            "const float eps = 1.0e-10;\n" +
//                    //Call this to go from the official HSL hue distribution (where blue is opposite yellow) to a
//                    //different distribution that matches primary colors in painting (where purple is opposite yellow).
//                    "float official2primaries(float hue) {\n" +
//                    "    return  hue * (  2.137\n" +
//                    "          + hue * (  0.542\n" +
//                    "          + hue * (-15.141\n" +
//                    "          + hue * ( 30.120\n" +
//                    "          + hue * (-22.541\n" +
//                    "          + hue *   5.883)))));\n" +
//                    "}\n" +
//                    //Call this to go to the official HSL hue distribution (where blue is opposite yellow) from a
//                    //different distribution that matches primary colors in painting (where purple is opposite yellow).
//                    "float primaries2official(float hue) {\n" +
//                    "    return  hue * (  0.677\n" +
//                    "          + hue * ( -0.123\n" +
//                    "          + hue * (-11.302\n" +
//                    "          + hue * ( 46.767\n" +
//                    "          + hue * (-58.493\n" +
//                    "          + hue *   23.474)))));\n" +
//                    "}\n" +
                    "vec4 rgb2hsl(vec4 c)\n" +
                    "{\n" +
                    "    const vec4 J = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);\n" +
                    "    vec4 p = mix(vec4(c.bg, J.wz), vec4(c.gb, J.xy), step(c.b, c.g));\n" +
                    "    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));\n" +
                    "    float d = q.x - min(q.w, q.y);\n" +
                    "    float l = q.x * (1.0 - 0.5 * d / (q.x + eps));\n" +
                    "    return vec4(abs(q.z + (q.w - q.y) / (6.0 * d + eps)), (q.x - l) / (min(l, 1.0 - l) + eps), l, c.a);\n" +
                    "}\n" +
                    "\n" +
                    "vec4 hsl2rgb(vec4 c)\n" +
                    "{\n" +
                    "    const vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);\n" +
                    "    vec3 p = abs(fract(c.x + K.xyz) * 6.0 - K.www);\n" +
                    "    float v = (c.z + c.y * min(c.z, 1.0 - c.z));\n" +
                    "    return vec4(v * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), 2.0 * (1.0 - c.z / (v + eps))), c.w);\n" +
                    "}";

    /**
     * Adjusted-hue version of {@link #partialCodeHSL}, supplying HSL to-and-from RGB conversions with a smaller range
     * of hue used for cyan and a larger range for orange. Not currently used. Credit to Sam Hocevar,
     * https://gamedev.stackexchange.com/a/59808 .
     */
    public static final String partialCodeHSLStretched =
            "const float eps = 1.0e-10;\n" +
                    //// maybe not blue enough?
//                    //Call this to go from the official HSL hue distribution (where blue is opposite yellow) to a
//                    //different distribution that matches primary colors in painting (where purple is opposite yellow).
//                    "float official2primaries(float hue) {\n" +
//                    "    return  hue * (  1.630\n" +
//                    "          + hue * (  5.937\n" +
//                    "          + hue * (-38.485\n" +
//                    "          + hue * ( 76.101\n" +
//                    "          + hue * (-63.977\n" +
//                    "          + hue *   19.794)))));\n" +
//                    "}\n" +
//                    //Call this to go to the official HSL hue distribution (where blue is opposite yellow) from a
//                    //different distribution that matches primary colors in painting (where purple is opposite yellow).
//                    "float primaries2official(float hue) {\n" +
//                    "    return  hue * (  1.463\n" +
//                    "          + hue * ( -9.834\n" +
//                    "          + hue * ( 31.153\n" +
//                    "          + hue * (-35.066\n" +
//                    "          + hue *   13.284))));\n" +
//                    "}\n" +
                    //// still weirdly high orange
//                    "float official2primaries(float hue) {\n" +
//                    "    return  hue * (  2.634\n" +
//                    "          + hue * ( -4.703\n" +
//                    "          + hue * (  3.659\n" +
//                    "          + hue * (  0.829\n" +
//                    "          + hue *   -1.419))));\n" +
//                    "}\n" +
//                    "float primaries2official(float hue) {\n" +
//                    "    return  hue * (  1.163\n" +
//                    "          + hue * ( -7.102\n" +
//                    "          + hue * ( 22.709\n" +
//                    "          + hue * (-25.502\n" +
//                    "          + hue *    9.732))));\n" +
//                    "}\n" +
//                    "const float ROOT   = 16.0;\n" +
//                    "const float SQUARE = ROOT * ROOT;\n" +
                    "float official2primaries(float hue) {\n" +
//                    "    return (sqrt(hue * 0.9375 + 0.0625) - 0.25) * 1.333;\n" +
//                    "    return asin(sqrt(hue * SQUARE) * (1.0 / ROOT)) * (2.0 * 3.14159274);\n" +

//                    "    return asin((sqrt(hue * 0.9375 + 0.0625) - 0.25) * 2.666 - 1.0) * 0.318309886 + 0.5;\n" +

//                    "    return sqrt(sin((hue - 0.5) * 3.14159274) * 0.5f + 0.5f);\n" +
//                    "    return pow(hue, 0.5625);\n" +
//                    "    return sqrt(hue);\n" +
                    "    return (sqrt(hue + 0.050625) - 0.225) * 1.25;\n" +
                    "}\n" +
                    "float primaries2official(float hue) {\n" +
//                    "    hue = sin((hue) * (0.5 * 3.14159274));\n" +
//                    "    hue = (hue) * 0.75 + 0.25;\n" +
                    
//                    "    hue = sin((hue - 0.5) * 3.14159274);\n" +
//                    "    hue = (hue + 1.0) * 0.375 + 0.25;\n" +
//                    "    return (hue * hue - 0.0625) * (1.0 / 0.9375);\n" +
                    
//                    "    return asin(hue * hue * 2.0 - 1.0) * 0.318309886 + 0.5;\n" +
//                    "    return pow(hue, 1.77777);\n" +
                    "    return pow(hue * 0.8 + 0.225, 2.0) - 0.050625;\n" +
//                    "    return hue * hue;\n" +

//                    "    hue = sin((hue) * (0.5 * 3.14159274));\n" +
//                    "    hue = hue * ROOT;\n" +
//                    "    return (hue * hue) * (1.0 / SQUARE);\n" +

                    "}\n" +
                    //// way too much orange?
//                    //Call this to go from the official HSL hue distribution (where blue is opposite yellow) to a
//                    //different distribution that matches primary colors in painting (where purple is opposite yellow).
//                    "float official2primaries(float hue) {\n" +
//                    "    return  hue * (  2.137\n" +
//                    "          + hue * (  0.542\n" +
//                    "          + hue * (-15.141\n" +
//                    "          + hue * ( 30.120\n" +
//                    "          + hue * (-22.541\n" +
//                    "          + hue *    5.883)))));\n" +
//                    "}\n" +
//                    //Call this to go to the official HSL hue distribution (where blue is opposite yellow) from a
//                    //different distribution that matches primary colors in painting (where purple is opposite yellow).
//                    "float primaries2official(float hue) {\n" +
//                    "    return  hue * (  0.677\n" +
//                    "          + hue * ( -0.123\n" +
//                    "          + hue * (-11.302\n" +
//                    "          + hue * ( 46.767\n" +
//                    "          + hue * (-58.493\n" +
//                    "          + hue *   23.474)))));\n" +
//                    "}\n" +
                    "vec4 rgb2hsl(vec4 c)\n" +
                    "{\n" +
                    "    const vec4 J = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);\n" +
                    "    vec4 p = mix(vec4(c.bg, J.wz), vec4(c.gb, J.xy), step(c.b, c.g));\n" +
                    "    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));\n" +
                    "\n" +
                    "    float d = q.x - min(q.w, q.y);\n" +
                    "    float l = q.x * (1.0 - 0.5 * d / (q.x + eps));\n" +
                    "    return vec4(official2primaries(abs(q.z + (q.w - q.y) / (6.0 * d + eps))), (q.x - l) / (min(l, 1.0 - l) + eps), l, c.a);\n" +
                    "}\n" +
                    "\n" +
                    "vec4 hsl2rgb(vec4 c)\n" +
                    "{\n" +
                    "    const vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);\n" +
                    "    vec3 p = abs(fract(primaries2official(c.x) + K.xyz) * 6.0 - K.www);\n" +
                    "    float v = (c.z + c.y * min(c.z, 1.0 - c.z));\n" +
                    "    return vec4(v * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), 2.0 * (1.0 - c.z / (v + eps))), c.w);\n" +
                    "}";

    public static final String fragmentShaderHSL2 =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    partialCodeHSL +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "   vec4 hsl = rgb2hsl(tgt);\n" +
                    "   hsl.x = fract((fract(v_color.x + 0.5 - hsl.x) - 0.5) * v_color.w + hsl.x);\n" +
                    "   hsl.yz = mix(hsl.yz, v_color.yz, v_color.w);\n" +
                    "   gl_FragColor = hsl2rgb(hsl);\n" +
                    "}";
    
    public static final String fragmentShaderRotateHSL2 =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    partialCodeHSL +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "   vec4 hsl = rgb2hsl(tgt);\n" +
                    "   hsl.x = fract(v_color.x + hsl.x + 0.5);\n" +
                    "   hsl.yz = clamp(hsl.yz * v_color.yz * 2.0, 0.0, 1.0);\n" +
                    "   gl_FragColor = hsl2rgb(hsl);\n" +
                    "}";

    /**
     * This is the default vertex shader from libGDX, but also sets a varying value for contrast. It is needed if you
     * use {@link #fragmentShaderHSLC}.
     */
    public static final String vertexShaderHSLC = "attribute vec4 a_position;\n"
            + "attribute vec4 a_color;\n"
            + "attribute vec2 a_texCoord0;\n"
            + "uniform mat4 u_projTrans;\n"
            + "varying vec4 v_color;\n"
            + "varying vec2 v_texCoords;\n" 
            + "varying float v_lightFix;\n"
            + "\n"
            + "void main()\n"
            + "{\n"
            + "   v_color = a_color;\n"
            + "   v_texCoords = a_texCoord0;\n"
            + "   v_color.a = pow(v_color.a * (255.0/254.0) + 0.5, 1.709);\n"    
            + "   v_lightFix = 1.0 + pow(v_color.a, 1.41421356);\n"
            + "   gl_Position =  u_projTrans * a_position;\n"
            + "}\n";

    /**
     * Allows changing Hue/Saturation/Lightness/Contrast, with hue as a rotation. Expects the vertex shader to be
     * {@link #vertexShaderHSLC}, which sets {@code varying float v_lightFix} so contrast can use it.
     * <br>
     * Credit for HLSL version goes to Andrey-Postelzhuk,
     * <a href="https://forum.unity.com/threads/hue-saturation-brightness-contrast-shader.260649/">Unity Forums</a>.
     * The YCC adaptation, and different approach to contrast (this has close to neutral contrast when a is 0.5,
     * while the original had a fair bit higher contrast than expected), is from this codebase.
     */
    public static final String fragmentShaderHSLC = 
                    "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying float v_lightFix;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "vec3 applyHue(vec3 rgb, float hue)\n" +
                    "{\n" +
                    "    vec3 k = vec3(0.57735);\n" +
                    "    float c = cos(hue);\n" +
                    "    //Rodrigues' rotation formula\n" +
                    "    return rgb * c + cross(k, rgb) * sin(hue) + k * dot(k, rgb) * (1.0 - c);\n" +
                    "}\n" +
                    "void main()\n" +
                    "{\n" +
                    "    float hue = 6.2831853 * (v_color.x - 0.5);\n" +
                    "    float saturation = v_color.y * 2.0;\n" +
                    "    float brightness = v_color.z - 0.5;\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    tgt.rgb = applyHue(tgt.rgb, hue);\n" +
                    "    tgt.rgb = vec3(\n" +
                    "     (0.5 * pow(dot(tgt.rgb, vec3(0.375, 0.5, 0.125)), v_color.w) * v_lightFix + brightness),\n" + // lightness
                    "     ((tgt.r - tgt.b) * saturation),\n" + // warmth
                    "     ((tgt.g - tgt.b) * saturation));\n" + // mildness
                    "    gl_FragColor = clamp(vec4(\n" +
                    "     dot(tgt.rgb, vec3(1.0, 0.625, -0.5)),\n" + // back to red
                    "     dot(tgt.rgb, vec3(1.0, -0.375, 0.5)),\n" + // back to green
                    "     dot(tgt.rgb, vec3(1.0, -0.375, -0.5)),\n" + // back to blue
                    "     tgt.a), 0.0, 1.0);\n" + // keep alpha, then clamp
                    "}";
    /**
     * Allows changing Hue/Saturation/Lightness/Alpha, with hue as a rotation. Expects the vertex shader to be
     * {@link #vertexShader}, unlike what {@link #fragmentShaderHSLC} expects.
     * <br>
     * Credit for HLSL version goes to Andrey-Postelzhuk,
     * <a href="https://forum.unity.com/threads/hue-saturation-brightness-contrast-shader.260649/">Unity Forums</a>.
     * The YCC adaptation, and change to use alpha, is from this codebase.
     */
    public static final String fragmentShaderHSLA = 
                    "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "vec3 applyHue(vec3 rgb, float hue)\n" +
                    "{\n" +
                    "    vec3 k = vec3(0.57735);\n" +
                    "    float c = cos(hue);\n" +
                    "    //Rodrigues' rotation formula\n" +
                    "    return rgb * c + cross(k, rgb) * sin(hue) + k * dot(k, rgb) * (1.0 - c);\n" +
                    "}\n" +
                    "void main()\n" +
                    "{\n" +
                    "    float hue = 6.2831853 * (v_color.x - 0.5);\n" +
                    "    float saturation = v_color.y * 2.0;\n" +
                    "    float brightness = v_color.z - 0.5;\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    tgt.rgb = applyHue(tgt.rgb, hue);\n" +
                    "    tgt.rgb = vec3(\n" +
                    "     (dot(tgt.rgb, vec3(0.375, 0.5, 0.125)) + brightness),\n" + // lightness
                    "     ((tgt.r - tgt.b) * saturation),\n" + // warmth
                    "     ((tgt.g - tgt.b) * saturation));\n" + // mildness
                    "    gl_FragColor = clamp(vec4(\n" +
                    "     dot(tgt.rgb, vec3(1.0, 0.625, -0.5)),\n" + // back to red
                    "     dot(tgt.rgb, vec3(1.0, -0.375, 0.5)),\n" + // back to green
                    "     dot(tgt.rgb, vec3(1.0, -0.375, -0.5)),\n" + // back to blue
                    "     tgt.a * v_color.w), 0.0, 1.0);\n" + // keep alpha, then clamp
                    "}";
    /**
     * GLSL: Takes an RGB vec3 and a float that represents a hue rotation in radians, and returns the rotated RGB vec3.
     * Credit for this challenging method goes to Andrey-Postelzhuk,
     * <a href="https://forum.unity.com/threads/hue-saturation-brightness-contrast-shader.260649/">Unity Forums</a>.
     */
    public static final String hueRodrigues =
            "vec3 applyHue(vec3 rgb, float hue)\n" +
            "{\n" +
            "    vec3 k = vec3(0.57735);\n" +
            "    float c = cos(hue);\n" +
            "    //Rodrigues' rotation formula\n" +
            "    return rgb * c + cross(k, rgb) * sin(hue) + k * dot(k, rgb) * (1.0 - c);\n" +
            "}\n";

    /**
     * Generally a lower-quality hue rotation than {@link #fragmentShaderHSLC}; this is here as a work in progress.
     * Expects the vertex shader to be {@link #vertexShaderHSLC}, which sets {@code varying float v_lightFix} so
     * contrast can use it.
     */
    public static final String fragmentShaderHSLC2 = 
                    "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying float v_lightFix;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                            partialCodeHSL +
                    "void main()\n" +
                    "{\n" +
                    "    float hue = (v_color.x - 0.5);\n" +
                    "    float saturation = v_color.y * 2.0;\n" +
                    "    float brightness = v_color.z - 0.5;\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    tgt = rgb2hsl(tgt);\n" +
                    "    tgt.r = fract(tgt.r + hue);\n" +
                    "    tgt = hsl2rgb(tgt);\n" +
                    "    tgt.rgb = vec3(\n" +
                    "     (0.5 * pow(dot(tgt.rgb, vec3(0.375, 0.5, 0.125)), v_color.w) * v_lightFix + brightness),\n" + // lightness
                    "     ((tgt.r - tgt.b) * saturation),\n" + // warmth
                    "     ((tgt.g - tgt.b) * saturation));\n" + // mildness
                    "    gl_FragColor = clamp(vec4(\n" +
                    "     dot(tgt.rgb, vec3(1.0, 0.625, -0.5)),\n" + // back to red
                    "     dot(tgt.rgb, vec3(1.0, -0.375, 0.5)),\n" + // back to green
                    "     dot(tgt.rgb, vec3(1.0, -0.375, -0.5)),\n" + // back to blue
                    "     tgt.a), 0.0, 1.0);\n" + // keep alpha, then clamp
                    "}";
    /**
     * Cycles lightness in a psychedelic way as hue and lightness change; not a general-purpose usage.
     * Expects the vertex shader to be {@link #vertexShaderHSLC}, which sets {@code varying float v_lightFix} so
     * contrast can use it.
     */
    public static final String fragmentShaderHSLC3 =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying float v_lightFix;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "vec3 applyHue(vec3 rgb, float hue)\n" +
                    "{\n" +
                    "    vec3 k = vec3(0.57735);\n" +
                    "    float c = cos(hue);\n" +
                    "    //Rodrigues' rotation formula\n" +
                    "    return rgb * c + cross(k, rgb) * sin(hue) + k * dot(k, rgb) * (1.0 - c);\n" +
                    "}\n" +
                    "void main()\n" +
                    "{\n" +
                    "    float hue = 6.2831853 * (v_color.x - 0.5);\n" +
                    "    float saturation = v_color.y * 2.0;\n" +
                    "    float brightness = v_color.z - 0.5;\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    tgt.rgb = applyHue(tgt.rgb, hue);\n" +
                    "    tgt.rgb = vec3(\n" +
                    "     (0.5 * pow(dot(tgt.rgb, vec3(0.375, 0.5, 0.125)), v_color.w) * v_lightFix),\n" + // lightness
                    "     ((tgt.r - tgt.b) * saturation),\n" + // warmth
                    "     ((tgt.g - tgt.b) * saturation));\n" + // mildness
                    "    tgt.r = sin((tgt.r + brightness) * 6.2831853) * 0.5 + 0.5;\n" +
                    "    gl_FragColor = clamp(vec4(\n" +
                    "     dot(tgt.rgb, vec3(1.0, 0.625, -0.5)),\n" + // back to red
                    "     dot(tgt.rgb, vec3(1.0, -0.375, 0.5)),\n" + // back to green
                    "     dot(tgt.rgb, vec3(1.0, -0.375, -0.5)),\n" + // back to blue
                    "     tgt.a), 0.0, 1.0);\n" + // keep alpha, then clamp
                    "}";
    /**
     * Cycles hue, but not lightness; otherwise this is like {@link #fragmentShaderHSLC3} without contrast, and keeping
     * alpha intact.
     * Internally, this uses Sam Hocevar's RGB/HSL conversion instead of Andrey-Postelzhuk's HSLC code.
     * Expects the vertex shader to be {@link #vertexShader}, not the HSLC variant.
     */
    public static final String fragmentShaderHSLPsychedelic =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    partialCodeHSL +
                    "void main()\n" +
                    "{\n" +
                    "    float hue = v_color.x - 0.5;\n" +
                    "    float saturation = v_color.y * 2.0;\n" +
                    "    float brightness = v_color.z - 0.5;\n" +
                    "    vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "    tgt = rgb2hsl(tgt);\n" +
                    "    tgt.x = fract(tgt.x + hue);\n" +
                    "    tgt.y = clamp(tgt.y * saturation, 0.0, 1.0);\n" +
                    "    tgt.z = clamp(brightness + tgt.z, 0.0, 1.0);\n" +
                    "    gl_FragColor = hsl2rgb(tgt);\n" +
                    "}";

    /**
     * One of the more useful HSL shaders here, this takes a batch color as hue, saturation, lightness, and intensity,
     * with hue as a target hue and intensity used to determine how much of the target color should be used. There is no
     * neutral value for hue, saturation, or lightness, but if intensity is 0, then the source color will be used
     * exactly. On the other hand, if intensity is 1.0, then all pixels will be the target color. Hue is specified from
     * 0.0 to 1.0, with 0.0 as red, about 0.3 as green, about 0.6 as blue, etc. Saturation is specified from 0.0 to 1.0,
     * with 0.0 as grayscale and 1.0 as a fully-saturated target color. Lightness is specified from 0.0 to 1.0, with 0.0
     * as black, the 0.3 to 0.7 range as most colors, and 1.0 white; saturation is clamped to a smaller value as
     * lightness moves away from 0.5 (toward black or white).
     */
    public static final String fragmentShaderHSLI =
            "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    partialCodeHSL +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D( u_texture, v_texCoords );\n" +
                    "   vec4 hsl = rgb2hsl(tgt);\n" +
                    "   hsl.xy = vec2(cos(hsl.x * 6.2831853), sin(hsl.x * 6.2831853)) * hsl.y;\n" +
                    "   vec3 tint = vec3(cos(v_color.x * 6.2831853) * v_color.y, sin(v_color.x * 6.2831853) * v_color.y, v_color.z);\n" +
                    "   hsl.xyz = mix(hsl.xyz, tint, v_color.w);\n" +
                    "   hsl.xy = vec2(fract(atan(hsl.y, hsl.x) / 6.2831853), length(hsl.xy));\n" +
                    "   gl_FragColor = hsl2rgb(hsl);\n" +
                    "}";
    
    public static final String fragmentShaderReplacement = 
                    "#ifdef GL_ES\n" +
                    "#define LOWP lowp\n" +
                    "precision mediump float;\n" +
                    "#else\n" +
                    "#define LOWP \n" +
                    "#endif\n" +
                    "varying vec2 v_texCoords;\n" +
                    "varying LOWP vec4 v_color;\n" +
                    "uniform sampler2D u_texture;\n" +
                    "uniform vec4 u_search;\n" +
                    "uniform vec4 u_replace;\n" +
                    "void main()\n" +
                    "{\n" +
                    "   vec4 tgt = texture2D(u_texture, v_texCoords);\n" +
                    "   float curve = smoothstep(0.0, 1.0, 1.25 - distance(tgt.rgb, u_search.rgb) * 2.0);\n" +
                    "   gl_FragColor = vec4(mix(tgt.rgb, u_replace.rgb, curve), tgt.a) * v_color;\n" +
                    "}";

}
