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
     * A somewhat-experimental shader that takes colors in Hue, Saturation, Lightness, Alpha format, where:
     * <ul>
     *     <li>Hue can range between 0.0 and 1.0 and is added to the hue angle of the color being rendered, which also
     *         starts in the 0.0-1.0 range (corresponding to 0-360 degrees). This is always a "hue shift" and does not
     *         set the hue to a specific angle. The shift will move colors from red to orange to yellow to green to blue
     *         to purple and back to red. A hue shift of 0.0 will not change the rendered hue.</li>
     *     <li>Saturation can range from 0.0 to 1.0, where values less than 0.5 reduce saturation and values greater
     *         than 0.5 increase it. If saturation goes too high, it will be clamped at the most saturated color for its
     *         hue; the clamping happens sooner for lighter and darker colors.</li>
     *     <li>Lightness can range from 0.0 to 1.0, where values less than 0.5 reduce lightness and values greater
     *         than 0.5 increase it. The lightness will be clamped as well.</li>
     *     <li>Alpha is normal multiplicative alpha, from 0.0 to 1.0.</li>
     * </ul>
     * If using LibGDX Color objects, hue is set with r, saturation is set with g, lightness is set with b, and alpha is
     * still set with a.
     */
    public static final String fragmentShaderHSL =
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
                    "   float hue = (v_color.r - 0.375) * 6.283185307179586;\n" +
                    "   float sat = v_color.g * 2.0;\n" +
                    "   vec3 ycc = vec3(v_color.b - 0.5 + dot(tgt.rgb, bright), cos(hue) * sat + tgt.g - (tgt.b + tgt.r) * 0.5, sin(hue) * sat + tgt.b - tgt.r);\n" +
                    //// Use this to change non-visible colors to be limited to the outer band of possible results
                    "   gl_FragColor = clamp(vec4(dot(ycc, vec3(1.0, -0.5, -0.375)), dot(ycc, vec3(1.0, 0.5, 0.125)), dot(ycc, vec3(1.0, -0.5, 0.625)), v_color.a * tgt.a), 0.0, 1.0);\n" +
                    //// Use this instead to not render colors that are outside the visible range, without clamping
//                    "   gl_FragColor = vec4(dot(ycc, vec3(1.0, -0.5, -0.375)), dot(ycc, vec3(1.0, 0.5, 0.125)), dot(ycc, vec3(1.0, -0.5, 0.625)), v_color.a * tgt.a);\n" +
//                    "   if(any(notEqual(gl_FragColor.rgb, clamp(gl_FragColor.rgb, 0.0, 1.0)))) discard;\n" +
                    "}";

}
