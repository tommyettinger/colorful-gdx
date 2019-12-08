package com.github.tommyettinger.colorful;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.NumberUtils;

/**
 * Created by Tommy Ettinger on 9/12/2019.
 */
public class Basics {

    public static final Color COLOR_WHITE = new Color(1f, 0.5f, 0.5f, 0.5f);
    public static final Color COLOR_BLACK = new Color(0f, 0.5f, 0.5f, 0f);
    public static final Color COLOR_NEUTRAL = new Color(0.5f, 0.5f, 0.5f, 0.5f);
    
    public static final float FLOAT_WHITE = FloatColorTools.floatColor(1f, 0.5f, 0.5f, 0.5f);
    public static final float FLOAT_BLACK = FloatColorTools.floatColor(0f, 0.5f, 0.5f, 0f);
    public static final float FLOAT_GRAY = FloatColorTools.floatColor(0.35f, 0.5f, 0.5f, 0.1f);
    public static final float FLOAT_NEUTRAL = FloatColorTools.floatColor(0.5f, 0.5f, 0.5f, 0.5f);
    public static final float FLOAT_HOT = FloatColorTools.floatColor(0.225f, 1.0f, 0.58f, 0.85f);
    public static final float FLOAT_COLD = FloatColorTools.floatColor(0.3f, 0.0f, 0.45f, 0.4f);

    /**
     * The "luma" of the given libGDX Color, which is like its lightness, in YCwCmA format; ranges from 0f to 1f .
     * @param color a libGDX Color
     * @return the luma as a float from 0.0f to 1.0f
     */
    public static float luma(final Color color)
    {
        return color.r * 0.375f + color.g * 0.5f + color.b * 0.125f;
    }
    /**
     * The "chroma warm" of the given libGDX Color, which when combined with chroma mild describes the shade and
     * saturation of a color, in YCwCmA format; ranges from 0f to 1f .
     * @param color a libGDX Color
     * @return the chroma warm as a float from 0f to 1f
     */
    public static float chromaWarm(final Color color)
    {
        return (color.r - color.b) * 0.5f + 0.5f;
    }

    /**
     * The "chroma mild" of the given libGDX Color, which when combined with chroma warm describes the shade and
     * saturation of a color, in YCwCmA format; ranges from 0f to 1f .
     * @param color a libGDX Color
     * @return the chroma warm as a float from 0f to 1f
     */
    public static float chromaMild(final Color color)
    {
        return (color.g - color.b) * 0.5f + 0.5f;
    }

    /**
     * Gets a color as a packed float given floats representing luma (Y, akin to lightness), chroma warm (Cw, one of two
     * kinds of chroma used here), chroma mild (Cm, the other kind of chroma), and alpha. Luma should be between 0 and
     * 255, inclusive, with 0 used for very dark colors (almost only black), and 255 used for very light colors (almost
     * only white). The two chroma values range from 0 to 255, and unlike YCbCr and YCoCg, there's some aesthetic value
     * in changing just one chroma value. When warm is high and mild is low, the color is more reddish; when both are
     * low it is more bluish, and when mild is high and warm is low, the color tends to be greenish, and when both are
     * high it tends to be brown or yellow. When warm and mild are both near 128, the color is closer to gray. Alpha is
     * the multiplicative opacity of the color, and acts like RGBA's alpha.
     * <br>
     * This method clamps the resulting color's YCwCmA values, so any values can technically be given to this as luma,
     * warm, and mild, but they will only be reversible from the returned float color to the original Y, Cw, and Cm
     * values if the original values were in the range that {@link #chromaWarm(Color)}, {@link #chromaMild(Color)}, and
     * {@link #luma(Color)} return.
     *
     * @param luma       0 to 255, luma or Y component of YCwCmA, with 128 meaning "no change" and 255 brightening
     * @param warm       0 to 255, "chroma warm" or Cw component of YCwCmA, with 255 more red or yellow
     * @param mild       0 to 255, "chroma mild" or Cm component of YCwCmA, with 255 more green or yellow
     * @param alpha      0 to 254, 0 makes the color transparent and 254 (or 255) makes it opaque 
     * @return a float encoding a color with the given properties
     */
    public static float getYCwCmA(int luma, int warm, int mild, int alpha) {
        return NumberUtils.intBitsToFloat((alpha << 24 & 0xFE000000) | (mild << 16 & 0xFF0000)
                | (warm << 8 & 0xFF00) | (luma & 0xFF));
    }

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
//            + "   v_color.a = v_color.a * (255.0/254.0);\n"
            + "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
            + "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
            + "}\n";
    /**
     * Where the magic happens; this converts a batch color from the YCwCmA format (used by colorful) to RGBA.
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
//                    "   vec3 ycc = vec3(v_color.r * dot(tgt.rgb, bright), ((v_color.g - 0.5) * 2.0 + tgt.r - tgt.b), ((v_color.b - 0.5) * 2.0 + tgt.g - tgt.b));\n" +
                    //use the following line to increase contrast
//                    "   vec3 ycc = vec3(v_color.r * dot(sin(tgt.rgb * 1.5707963267948966) * sqrt(tgt.rgb), bright), ((v_color.g - 0.5) * 2.0 + tgt.r - tgt.b), ((v_color.b - 0.5) * 2.0 + tgt.g - tgt.b));\n" +
                    //use the following line to increase contrast more
                    "   vec3 ycc = vec3(v_color.r * pow(dot(tgt.rgb, bright), 1.25) * 2.0, ((v_color.g - 0.5) * 2.0 + tgt.r - tgt.b), ((v_color.b - 0.5) * 2.0 + tgt.g - tgt.b));\n" +
                    
                    "   gl_FragColor = clamp(vec4(dot(ycc, vec3(1.0, 0.625, -0.5)), dot(ycc, vec3(1.0, -0.375, 0.5)), dot(ycc, vec3(1.0, -0.375, -0.5)), v_color.a * tgt.a), 0.0, 1.0);\n" +
                    "}";
}
