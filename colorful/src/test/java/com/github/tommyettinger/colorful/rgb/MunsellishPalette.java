package com.github.tommyettinger.colorful.rgb;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;

import java.util.Comparator;

import static com.github.tommyettinger.colorful.rgb.ColorTools.lightness;

/**
 * A palette of predefined colors as packed RGB(A) floats, the kind {@link ColorTools} works with.
 * You can access colors by their constant name, such as {@code OCEAN_BLUE}, by the {@link #NAMED} map using
 * {@code NAMED.get("Ocean Blue", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access a float
 * color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default because it
 * is the same as fully-transparent black. You can access the names in a specific order with {@link #NAMES} (which is
 * alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow to blue
 * (with gray around here) to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the lightness of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * Created by Tommy Ettinger on 12/2/2020.
 */
public class MunsellishPalette {
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(256);
    public static final FloatArray LIST = new FloatArray(256);

    /**
     * This color constant "Munsellish Transparent" has RGBA8888 code {@code 00000000}, hue 0.0, saturation 0.0, lightness 0.0, and alpha 0.0.
     * It can be represented as a packed float with the constant {@code 0x0.0p0F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MUNSELLISH_TRANSPARENT = 0x0.0p0F;
    static { NAMED.put("Munsellish Transparent", 0x0.0p0F); LIST.add(0x0.0p0F); }

    /**
     * This color constant "Black" has RGBA8888 code {@code 000000FF}, hue 0.0, saturation 0.0, lightness 0.0, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0p125F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK = -0x1.0p125F;
    static { NAMED.put("Black", -0x1.0p125F); LIST.add(-0x1.0p125F); }

    /**
     * This color constant "Dark Gray" has RGBA8888 code {@code 404040FF}, hue 0.0, saturation 0.0, lightness 0.2509804, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.80808p125F}.
     * <pre>
     * <font style='background-color: #404040;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #404040; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #404040;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #404040'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #404040'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #404040'>&nbsp;@&nbsp;</font><font style='background-color: #404040; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #404040;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #404040; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY = -0x1.80808p125F;
    static { NAMED.put("Dark Gray", -0x1.80808p125F); LIST.add(-0x1.80808p125F); }

    /**
     * This color constant "Medium Gray" has RGBA8888 code {@code 808080FF}, hue 0.0, saturation 0.0, lightness 0.5019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0101p126F}.
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUM_GRAY = -0x1.0101p126F;
    static { NAMED.put("Medium Gray", -0x1.0101p126F); LIST.add(-0x1.0101p126F); }

    /**
     * This color constant "Light Gray" has RGBA8888 code {@code ABABABFF}, hue 0.0, saturation 0.0, lightness 0.67058825, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.575756p126F}.
     * <pre>
     * <font style='background-color: #ABABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABABAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABABAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABABAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABABAB'>&nbsp;@&nbsp;</font><font style='background-color: #ABABAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABABAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY = -0x1.575756p126F;
    static { NAMED.put("Light Gray", -0x1.575756p126F); LIST.add(-0x1.575756p126F); }

    /**
     * This color constant "Gray White" has RGBA8888 code {@code D5D5D5FF}, hue 0.0, saturation 0.0, lightness 0.8352941, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ababaap126F}.
     * <pre>
     * <font style='background-color: #D5D5D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5D5D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5D5D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5D5D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5D5D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5D5D5'>&nbsp;@&nbsp;</font><font style='background-color: #D5D5D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5D5D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5D5D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_WHITE = -0x1.ababaap126F;
    static { NAMED.put("Gray White", -0x1.ababaap126F); LIST.add(-0x1.ababaap126F); }

    /**
     * This color constant "White" has RGBA8888 code {@code FFFFFFFF}, hue 0.0, saturation 0.0, lightness 1.0, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fffffep126F}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE = -0x1.fffffep126F;
    static { NAMED.put("White", -0x1.fffffep126F); LIST.add(-0x1.fffffep126F); }

    /**
     * This color constant "Vivid Pink" has RGBA8888 code {@code FF94A8FF}, hue 0.96884733, saturation 0.41960782, lightness 0.79019606, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5129fep126F}.
     * <pre>
     * <font style='background-color: #FF94A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF94A8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF94A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF94A8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF94A8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF94A8'>&nbsp;@&nbsp;</font><font style='background-color: #FF94A8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF94A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF94A8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PINK = -0x1.5129fep126F;
    static { NAMED.put("Vivid Pink", -0x1.5129fep126F); LIST.add(-0x1.5129fep126F); }

    /**
     * This color constant "Strong Pink" has RGBA8888 code {@code FDA4B1FF}, hue 0.97565544, saturation 0.3490196, lightness 0.8176471, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.6349fap126F}.
     * <pre>
     * <font style='background-color: #FDA4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDA4B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDA4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDA4B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDA4B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDA4B1'>&nbsp;@&nbsp;</font><font style='background-color: #FDA4B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDA4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDA4B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PINK = -0x1.6349fap126F;
    static { NAMED.put("Strong Pink", -0x1.6349fap126F); LIST.add(-0x1.6349fap126F); }

    /**
     * This color constant "Deep Pink" has RGBA8888 code {@code F67E92FF}, hue 0.9722222, saturation 0.47058824, lightness 0.7294118, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.24fdecp126F}.
     * <pre>
     * <font style='background-color: #F67E92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F67E92; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F67E92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F67E92'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F67E92'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F67E92'>&nbsp;@&nbsp;</font><font style='background-color: #F67E92; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F67E92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F67E92; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PINK = -0x1.24fdecp126F;
    static { NAMED.put("Deep Pink", -0x1.24fdecp126F); LIST.add(-0x1.24fdecp126F); }

    /**
     * This color constant "Light Pink" has RGBA8888 code {@code FED1DDFF}, hue 0.95555556, saturation 0.17647058, lightness 0.9078431, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.bba3fcp126F}.
     * <pre>
     * <font style='background-color: #FED1DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FED1DD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FED1DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FED1DD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FED1DD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FED1DD'>&nbsp;@&nbsp;</font><font style='background-color: #FED1DD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FED1DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FED1DD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PINK = -0x1.bba3fcp126F;
    static { NAMED.put("Light Pink", -0x1.bba3fcp126F); LIST.add(-0x1.bba3fcp126F); }

    /**
     * This color constant "Moderate Pink" has RGBA8888 code {@code EFB3BDFF}, hue 0.9722222, saturation 0.2352941, lightness 0.8196079, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.7b67dep126F}.
     * <pre>
     * <font style='background-color: #EFB3BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFB3BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFB3BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFB3BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFB3BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFB3BD'>&nbsp;@&nbsp;</font><font style='background-color: #EFB3BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFB3BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFB3BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PINK = -0x1.7b67dep126F;
    static { NAMED.put("Moderate Pink", -0x1.7b67dep126F); LIST.add(-0x1.7b67dep126F); }

    /**
     * This color constant "Dark Pink" has RGBA8888 code {@code D9929AFF}, hue 0.98122066, saturation 0.27843136, lightness 0.7117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.3525b2p126F}.
     * <pre>
     * <font style='background-color: #D9929A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9929A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9929A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9929A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9929A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9929A'>&nbsp;@&nbsp;</font><font style='background-color: #D9929A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9929A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9929A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PINK = -0x1.3525b2p126F;
    static { NAMED.put("Dark Pink", -0x1.3525b2p126F); LIST.add(-0x1.3525b2p126F); }

    /**
     * This color constant "Pale Pink" has RGBA8888 code {@code FADBE3FF}, hue 0.9569892, saturation 0.12156862, lightness 0.9196079, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c7b7f4p126F}.
     * <pre>
     * <font style='background-color: #FADBE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FADBE3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FADBE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FADBE3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FADBE3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FADBE3'>&nbsp;@&nbsp;</font><font style='background-color: #FADBE3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FADBE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FADBE3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PINK = -0x1.c7b7f4p126F;
    static { NAMED.put("Pale Pink", -0x1.c7b7f4p126F); LIST.add(-0x1.c7b7f4p126F); }

    /**
     * This color constant "Grayish Pink" has RGBA8888 code {@code DFBAC4FF}, hue 0.954955, saturation 0.14509803, lightness 0.80196077, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.8975bep126F}.
     * <pre>
     * <font style='background-color: #DFBAC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFBAC4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFBAC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DFBAC4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DFBAC4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DFBAC4'>&nbsp;@&nbsp;</font><font style='background-color: #DFBAC4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFBAC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFBAC4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PINK = -0x1.8975bep126F;
    static { NAMED.put("Grayish Pink", -0x1.8975bep126F); LIST.add(-0x1.8975bep126F); }

    /**
     * This color constant "Pinkish White" has RGBA8888 code {@code F4E7F1FF}, hue 0.8717949, saturation 0.05098039, lightness 0.9313726, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e3cfe8p126F}.
     * <pre>
     * <font style='background-color: #F4E7F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4E7F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4E7F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F4E7F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F4E7F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F4E7F1'>&nbsp;@&nbsp;</font><font style='background-color: #F4E7F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4E7F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4E7F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINKISH_WHITE = -0x1.e3cfe8p126F;
    static { NAMED.put("Pinkish White", -0x1.e3cfe8p126F); LIST.add(-0x1.e3cfe8p126F); }

    /**
     * This color constant "Pinkish Gray" has RGBA8888 code {@code D9C3C9FF}, hue 0.95454544, saturation 0.086274505, lightness 0.80784315, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9387b2p126F}.
     * <pre>
     * <font style='background-color: #D9C3C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9C3C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9C3C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9C3C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9C3C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9C3C9'>&nbsp;@&nbsp;</font><font style='background-color: #D9C3C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9C3C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9C3C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINKISH_GRAY = -0x1.9387b2p126F;
    static { NAMED.put("Pinkish Gray", -0x1.9387b2p126F); LIST.add(-0x1.9387b2p126F); }

    /**
     * This color constant "Vivid Red" has RGBA8888 code {@code FF003EFF}, hue 0.9594771, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.7c01fep125F}.
     * <pre>
     * <font style='background-color: #FF003E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF003E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF003E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF003E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF003E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF003E'>&nbsp;@&nbsp;</font><font style='background-color: #FF003E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF003E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF003E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_RED = -0x1.7c01fep125F;
    static { NAMED.put("Vivid Red", -0x1.7c01fep125F); LIST.add(-0x1.7c01fep125F); }

    /**
     * This color constant "Strong Red" has RGBA8888 code {@code EC2E54FF}, hue 0.96666664, saturation 0.74509805, lightness 0.5529412, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a85dd8p125F}.
     * <pre>
     * <font style='background-color: #EC2E54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC2E54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC2E54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EC2E54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EC2E54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EC2E54'>&nbsp;@&nbsp;</font><font style='background-color: #EC2E54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC2E54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC2E54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_RED = -0x1.a85dd8p125F;
    static { NAMED.put("Strong Red", -0x1.a85dd8p125F); LIST.add(-0x1.a85dd8p125F); }

    /**
     * This color constant "Deep Red" has RGBA8888 code {@code B30039FF}, hue 0.94692737, saturation 0.7019608, lightness 0.3509804, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.720166p125F}.
     * <pre>
     * <font style='background-color: #B30039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B30039; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B30039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B30039'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B30039'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B30039'>&nbsp;@&nbsp;</font><font style='background-color: #B30039; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B30039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B30039; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED = -0x1.720166p125F;
    static { NAMED.put("Deep Red", -0x1.720166p125F); LIST.add(-0x1.720166p125F); }

    /**
     * This color constant "Very Deep Red" has RGBA8888 code {@code 810031FF}, hue 0.9366925, saturation 0.5058824, lightness 0.2529412, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.620102p125F}.
     * <pre>
     * <font style='background-color: #810031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #810031; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #810031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #810031'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #810031'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #810031'>&nbsp;@&nbsp;</font><font style='background-color: #810031; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #810031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #810031; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_RED = -0x1.620102p125F;
    static { NAMED.put("Very Deep Red", -0x1.620102p125F); LIST.add(-0x1.620102p125F); }

    /**
     * This color constant "Moderate Red" has RGBA8888 code {@code DD4A62FF}, hue 0.9727891, saturation 0.5764706, lightness 0.57843137, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c495bap125F}.
     * <pre>
     * <font style='background-color: #DD4A62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD4A62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD4A62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD4A62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD4A62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD4A62'>&nbsp;@&nbsp;</font><font style='background-color: #DD4A62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD4A62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD4A62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_RED = -0x1.c495bap125F;
    static { NAMED.put("Moderate Red", -0x1.c495bap125F); LIST.add(-0x1.c495bap125F); }

    /**
     * This color constant "Dark Red" has RGBA8888 code {@code 9C243FFF}, hue 0.9625, saturation 0.47058827, lightness 0.37647057, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.7e4938p125F}.
     * <pre>
     * <font style='background-color: #9C243F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C243F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C243F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C243F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C243F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C243F'>&nbsp;@&nbsp;</font><font style='background-color: #9C243F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C243F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C243F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_RED = -0x1.7e4938p125F;
    static { NAMED.put("Dark Red", -0x1.7e4938p125F); LIST.add(-0x1.7e4938p125F); }

    /**
     * This color constant "Very Dark Red" has RGBA8888 code {@code 671135FF}, hue 0.9302326, saturation 0.3372549, lightness 0.23529413, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.6a22cep125F}.
     * <pre>
     * <font style='background-color: #671135;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #671135; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #671135;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #671135'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #671135'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #671135'>&nbsp;@&nbsp;</font><font style='background-color: #671135; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #671135;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #671135; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_RED = -0x1.6a22cep125F;
    static { NAMED.put("Very Dark Red", -0x1.6a22cep125F); LIST.add(-0x1.6a22cep125F); }

    /**
     * This color constant "Light Grayish Red" has RGBA8888 code {@code CA99A0FF}, hue 0.97619045, saturation 0.19215685, lightness 0.6960785, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.413394p126F}.
     * <pre>
     * <font style='background-color: #CA99A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA99A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA99A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA99A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA99A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA99A0'>&nbsp;@&nbsp;</font><font style='background-color: #CA99A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA99A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA99A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_RED = -0x1.413394p126F;
    static { NAMED.put("Light Grayish Red", -0x1.413394p126F); LIST.add(-0x1.413394p126F); }

    /**
     * This color constant "Grayish Red" has RGBA8888 code {@code C16570FF}, hue 0.98007244, saturation 0.36078432, lightness 0.5764706, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e0cb82p125F}.
     * <pre>
     * <font style='background-color: #C16570;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C16570; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C16570;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C16570'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C16570'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C16570'>&nbsp;@&nbsp;</font><font style='background-color: #C16570; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C16570;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C16570; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_RED = -0x1.e0cb82p125F;
    static { NAMED.put("Grayish Red", -0x1.e0cb82p125F); LIST.add(-0x1.e0cb82p125F); }

    /**
     * This color constant "Dark Grayish Red" has RGBA8888 code {@code 724251FF}, hue 0.9479167, saturation 0.18823528, lightness 0.3529412, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a284e4p125F}.
     * <pre>
     * <font style='background-color: #724251;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #724251; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #724251;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #724251'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #724251'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #724251'>&nbsp;@&nbsp;</font><font style='background-color: #724251; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #724251;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #724251; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_RED = -0x1.a284e4p125F;
    static { NAMED.put("Dark Grayish Red", -0x1.a284e4p125F); LIST.add(-0x1.a284e4p125F); }

    /**
     * This color constant "Blackish Red" has RGBA8888 code {@code 4B2B35FF}, hue 0.9479167, saturation 0.1254902, lightness 0.23137255, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.6a5696p125F}.
     * <pre>
     * <font style='background-color: #4B2B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2B35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B2B35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B2B35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B2B35'>&nbsp;@&nbsp;</font><font style='background-color: #4B2B35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2B35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_RED = -0x1.6a5696p125F;
    static { NAMED.put("Blackish Red", -0x1.6a5696p125F); LIST.add(-0x1.6a5696p125F); }

    /**
     * This color constant "Reddish Gray" has RGBA8888 code {@code AB9198FF}, hue 0.9551282, saturation 0.10196078, lightness 0.61960787, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.312356p126F}.
     * <pre>
     * <font style='background-color: #AB9198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB9198; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB9198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB9198'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB9198'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB9198'>&nbsp;@&nbsp;</font><font style='background-color: #AB9198; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB9198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB9198; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REDDISH_GRAY = -0x1.312356p126F;
    static { NAMED.put("Reddish Gray", -0x1.312356p126F); LIST.add(-0x1.312356p126F); }

    /**
     * This color constant "Dark Reddish Gray" has RGBA8888 code {@code 7E5C65FF}, hue 0.9558824, saturation 0.13333333, lightness 0.42745098, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.cab8fcp125F}.
     * <pre>
     * <font style='background-color: #7E5C65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E5C65; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E5C65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E5C65'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E5C65'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E5C65'>&nbsp;@&nbsp;</font><font style='background-color: #7E5C65; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E5C65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E5C65; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_GRAY = -0x1.cab8fcp125F;
    static { NAMED.put("Dark Reddish Gray", -0x1.cab8fcp125F); LIST.add(-0x1.cab8fcp125F); }

    /**
     * This color constant "Vivid Yellowish Pink" has RGBA8888 code {@code FF9576FF}, hue 0.0377129, saturation 0.5372549, lightness 0.73137254, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ed2bfep125F}.
     * <pre>
     * <font style='background-color: #FF9576;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF9576; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF9576;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF9576'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF9576'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF9576'>&nbsp;@&nbsp;</font><font style='background-color: #FF9576; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF9576;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF9576; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOWISH_PINK = -0x1.ed2bfep125F;
    static { NAMED.put("Vivid Yellowish Pink", -0x1.ed2bfep125F); LIST.add(-0x1.ed2bfep125F); }

    /**
     * This color constant "Strong Yellowish Pink" has RGBA8888 code {@code FDA397FF}, hue 0.019607844, saturation 0.39999998, lightness 0.7921569, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.2f47fap126F}.
     * <pre>
     * <font style='background-color: #FDA397;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDA397; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDA397;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDA397'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDA397'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDA397'>&nbsp;@&nbsp;</font><font style='background-color: #FDA397; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDA397;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDA397; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_PINK = -0x1.2f47fap126F;
    static { NAMED.put("Strong Yellowish Pink", -0x1.2f47fap126F); LIST.add(-0x1.2f47fap126F); }

    /**
     * This color constant "Deep Yellowish Pink" has RGBA8888 code {@code FA7C7FFF}, hue 0.99603176, saturation 0.49411765, lightness 0.73333335, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fef9f4p125F}.
     * <pre>
     * <font style='background-color: #FA7C7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA7C7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA7C7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA7C7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA7C7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA7C7F'>&nbsp;@&nbsp;</font><font style='background-color: #FA7C7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA7C7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA7C7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_PINK = -0x1.fef9f4p125F;
    static { NAMED.put("Deep Yellowish Pink", -0x1.fef9f4p125F); LIST.add(-0x1.fef9f4p125F); }

    /**
     * This color constant "Light Yellowish Pink" has RGBA8888 code {@code FFD0C1FF}, hue 0.04032258, saturation 0.24313724, lightness 0.8784314, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.83a1fep126F}.
     * <pre>
     * <font style='background-color: #FFD0C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD0C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD0C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD0C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD0C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD0C1'>&nbsp;@&nbsp;</font><font style='background-color: #FFD0C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD0C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD0C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_PINK = -0x1.83a1fep126F;
    static { NAMED.put("Light Yellowish Pink", -0x1.83a1fep126F); LIST.add(-0x1.83a1fep126F); }

    /**
     * This color constant "Moderate Yellowish Pink" has RGBA8888 code {@code F2B5A5FF}, hue 0.034632035, saturation 0.30196077, lightness 0.79803926, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.4b6be4p126F}.
     * <pre>
     * <font style='background-color: #F2B5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2B5A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2B5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2B5A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2B5A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2B5A5'>&nbsp;@&nbsp;</font><font style='background-color: #F2B5A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2B5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2B5A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_PINK = -0x1.4b6be4p126F;
    static { NAMED.put("Moderate Yellowish Pink", -0x1.4b6be4p126F); LIST.add(-0x1.4b6be4p126F); }

    /**
     * This color constant "Dark Yellowish Pink" has RGBA8888 code {@code DD918EFF}, hue 0.006329114, saturation 0.3098039, lightness 0.71176475, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.1d23bap126F}.
     * <pre>
     * <font style='background-color: #DD918E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD918E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD918E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD918E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD918E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD918E'>&nbsp;@&nbsp;</font><font style='background-color: #DD918E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD918E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD918E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_PINK = -0x1.1d23bap126F;
    static { NAMED.put("Dark Yellowish Pink", -0x1.1d23bap126F); LIST.add(-0x1.1d23bap126F); }

    /**
     * This color constant "Pale Yellowish Pink" has RGBA8888 code {@code FDDCDEFF}, hue 0.989899, saturation 0.12941176, lightness 0.927451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.bdb9fap126F}.
     * <pre>
     * <font style='background-color: #FDDCDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDDCDE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDDCDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDDCDE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDDCDE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDDCDE'>&nbsp;@&nbsp;</font><font style='background-color: #FDDCDE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDDCDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDDCDE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOWISH_PINK = -0x1.bdb9fap126F;
    static { NAMED.put("Pale Yellowish Pink", -0x1.bdb9fap126F); LIST.add(-0x1.bdb9fap126F); }

    /**
     * This color constant "Grayish Yellowish Pink" has RGBA8888 code {@code DEB8BCFF}, hue 0.98245615, saturation 0.1490196, lightness 0.79607844, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.7971bcp126F}.
     * <pre>
     * <font style='background-color: #DEB8BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEB8BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEB8BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEB8BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEB8BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEB8BC'>&nbsp;@&nbsp;</font><font style='background-color: #DEB8BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEB8BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEB8BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOWISH_PINK = -0x1.7971bcp126F;
    static { NAMED.put("Grayish Yellowish Pink", -0x1.7971bcp126F); LIST.add(-0x1.7971bcp126F); }

    /**
     * This color constant "Brownish Pink" has RGBA8888 code {@code DEBCB8FF}, hue 0.01754386, saturation 0.1490196, lightness 0.79607844, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.7179bcp126F}.
     * <pre>
     * <font style='background-color: #DEBCB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEBCB8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEBCB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEBCB8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEBCB8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEBCB8'>&nbsp;@&nbsp;</font><font style='background-color: #DEBCB8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEBCB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEBCB8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_PINK = -0x1.7179bcp126F;
    static { NAMED.put("Brownish Pink", -0x1.7179bcp126F); LIST.add(-0x1.7179bcp126F); }

    /**
     * This color constant "Vivid Reddish Orange" has RGBA8888 code {@code FF512EFF}, hue 0.027910685, saturation 0.81960785, lightness 0.5901961, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5ca3fep125F}.
     * <pre>
     * <font style='background-color: #FF512E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF512E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF512E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF512E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF512E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF512E'>&nbsp;@&nbsp;</font><font style='background-color: #FF512E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF512E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF512E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_REDDISH_ORANGE = -0x1.5ca3fep125F;
    static { NAMED.put("Vivid Reddish Orange", -0x1.5ca3fep125F); LIST.add(-0x1.5ca3fep125F); }

    /**
     * This color constant "Strong Reddish Orange" has RGBA8888 code {@code F16F4EFF}, hue 0.033742327, saturation 0.6392157, lightness 0.6254902, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9cdfe2p125F}.
     * <pre>
     * <font style='background-color: #F16F4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F16F4E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F16F4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F16F4E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F16F4E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F16F4E'>&nbsp;@&nbsp;</font><font style='background-color: #F16F4E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F16F4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F16F4E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_ORANGE = -0x1.9cdfe2p125F;
    static { NAMED.put("Strong Reddish Orange", -0x1.9cdfe2p125F); LIST.add(-0x1.9cdfe2p125F); }

    /**
     * This color constant "Deep Reddish Orange" has RGBA8888 code {@code DC320AFF}, hue 0.031746034, saturation 0.8235294, lightness 0.45098037, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.1465b8p125F}.
     * <pre>
     * <font style='background-color: #DC320A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC320A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC320A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC320A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC320A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC320A'>&nbsp;@&nbsp;</font><font style='background-color: #DC320A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC320A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC320A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_ORANGE = -0x1.1465b8p125F;
    static { NAMED.put("Deep Reddish Orange", -0x1.1465b8p125F); LIST.add(-0x1.1465b8p125F); }

    /**
     * This color constant "Moderate Reddish Orange" has RGBA8888 code {@code E37C64FF}, hue 0.031496063, saturation 0.49803922, lightness 0.64117646, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c8f9c6p125F}.
     * <pre>
     * <font style='background-color: #E37C64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E37C64; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E37C64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E37C64'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E37C64'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E37C64'>&nbsp;@&nbsp;</font><font style='background-color: #E37C64; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E37C64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E37C64; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_ORANGE = -0x1.c8f9c6p125F;
    static { NAMED.put("Moderate Reddish Orange", -0x1.c8f9c6p125F); LIST.add(-0x1.c8f9c6p125F); }

    /**
     * This color constant "Dark Reddish Orange" has RGBA8888 code {@code CD4525FF}, hue 0.03174603, saturation 0.65882355, lightness 0.47450984, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.4a8b9ap125F}.
     * <pre>
     * <font style='background-color: #CD4525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD4525; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD4525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD4525'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD4525'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD4525'>&nbsp;@&nbsp;</font><font style='background-color: #CD4525; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD4525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD4525; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_ORANGE = -0x1.4a8b9ap125F;
    static { NAMED.put("Dark Reddish Orange", -0x1.4a8b9ap125F); LIST.add(-0x1.4a8b9ap125F); }

    /**
     * This color constant "Grayish Reddish Orange" has RGBA8888 code {@code D28477FF}, hue 0.023809537, saturation 0.35686275, lightness 0.64509803, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ef09a4p125F}.
     * <pre>
     * <font style='background-color: #D28477;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28477; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28477;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D28477'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D28477'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D28477'>&nbsp;@&nbsp;</font><font style='background-color: #D28477; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28477;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28477; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_ORANGE = -0x1.ef09a4p125F;
    static { NAMED.put("Grayish Reddish Orange", -0x1.ef09a4p125F); LIST.add(-0x1.ef09a4p125F); }

    /**
     * This color constant "Strong Reddish Brown" has RGBA8888 code {@code B51C01FF}, hue 0.024999999, saturation 0.7058824, lightness 0.35686275, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.02396ap125F}.
     * <pre>
     * <font style='background-color: #B51C01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B51C01; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B51C01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B51C01'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B51C01'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B51C01'>&nbsp;@&nbsp;</font><font style='background-color: #B51C01; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B51C01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B51C01; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_BROWN = -0x1.02396ap125F;
    static { NAMED.put("Strong Reddish Brown", -0x1.02396ap125F); LIST.add(-0x1.02396ap125F); }

    /**
     * This color constant "Deep Reddish Brown" has RGBA8888 code {@code 890014FF}, hue 0.9756691, saturation 0.5372549, lightness 0.26862746, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.280112p125F}.
     * <pre>
     * <font style='background-color: #890014;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #890014; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #890014;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #890014'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #890014'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #890014'>&nbsp;@&nbsp;</font><font style='background-color: #890014; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #890014;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #890014; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_BROWN = -0x1.280112p125F;
    static { NAMED.put("Deep Reddish Brown", -0x1.280112p125F); LIST.add(-0x1.280112p125F); }

    /**
     * This color constant "Light Reddish Brown" has RGBA8888 code {@code C8897DFF}, hue 0.026666682, saturation 0.29411766, lightness 0.6372549, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fb139p125F}.
     * <pre>
     * <font style='background-color: #C8897D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8897D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8897D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8897D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8897D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8897D'>&nbsp;@&nbsp;</font><font style='background-color: #C8897D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8897D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8897D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_REDDISH_BROWN = -0x1.fb139p125F;
    static { NAMED.put("Light Reddish Brown", -0x1.fb139p125F); LIST.add(-0x1.fb139p125F); }

    /**
     * This color constant "Moderate Reddish Brown" has RGBA8888 code {@code AA4742FF}, hue 0.00801282, saturation 0.40784314, lightness 0.46274513, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.848f54p125F}.
     * <pre>
     * <font style='background-color: #AA4742;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA4742; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA4742;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA4742'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA4742'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA4742'>&nbsp;@&nbsp;</font><font style='background-color: #AA4742; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA4742;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA4742; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_BROWN = -0x1.848f54p125F;
    static { NAMED.put("Moderate Reddish Brown", -0x1.848f54p125F); LIST.add(-0x1.848f54p125F); }

    /**
     * This color constant "Dark Reddish Brown" has RGBA8888 code {@code 662129FF}, hue 0.98067635, saturation 0.27058822, lightness 0.2647059, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5242ccp125F}.
     * <pre>
     * <font style='background-color: #662129;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #662129; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #662129;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #662129'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #662129'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #662129'>&nbsp;@&nbsp;</font><font style='background-color: #662129; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #662129;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #662129; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_BROWN = -0x1.5242ccp125F;
    static { NAMED.put("Dark Reddish Brown", -0x1.5242ccp125F); LIST.add(-0x1.5242ccp125F); }

    /**
     * This color constant "Light Grayish Reddish Brown" has RGBA8888 code {@code B78F88FF}, hue 0.024822695, saturation 0.18431371, lightness 0.62549025, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.111f6ep126F}.
     * <pre>
     * <font style='background-color: #B78F88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B78F88; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B78F88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B78F88'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B78F88'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B78F88'>&nbsp;@&nbsp;</font><font style='background-color: #B78F88; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B78F88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B78F88; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_REDDISH_BROWN = -0x1.111f6ep126F;
    static { NAMED.put("Light Grayish Reddish Brown", -0x1.111f6ep126F); LIST.add(-0x1.111f6ep126F); }

    /**
     * This color constant "Grayish Reddish Brown" has RGBA8888 code {@code 925854FF}, hue 0.010752686, saturation 0.24313727, lightness 0.4509804, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a8b124p125F}.
     * <pre>
     * <font style='background-color: #925854;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #925854; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #925854;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #925854'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #925854'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #925854'>&nbsp;@&nbsp;</font><font style='background-color: #925854; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #925854;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #925854; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_BROWN = -0x1.a8b124p125F;
    static { NAMED.put("Grayish Reddish Brown", -0x1.a8b124p125F); LIST.add(-0x1.a8b124p125F); }

    /**
     * This color constant "Dark Grayish Reddish Brown" has RGBA8888 code {@code 603434FF}, hue 0.0, saturation 0.17254902, lightness 0.2901961, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.6868cp125F}.
     * <pre>
     * <font style='background-color: #603434;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #603434; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #603434;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #603434'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #603434'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #603434'>&nbsp;@&nbsp;</font><font style='background-color: #603434; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #603434;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #603434; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_REDDISH_BROWN = -0x1.6868cp125F;
    static { NAMED.put("Dark Grayish Reddish Brown", -0x1.6868cp125F); LIST.add(-0x1.6868cp125F); }

    /**
     * This color constant "Vivid Orange" has RGBA8888 code {@code FF8C38FF}, hue 0.070351765, saturation 0.78039217, lightness 0.6098039, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.7119fep125F}.
     * <pre>
     * <font style='background-color: #FF8C38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8C38; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8C38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF8C38'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF8C38'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF8C38'>&nbsp;@&nbsp;</font><font style='background-color: #FF8C38; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8C38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8C38; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_ORANGE = -0x1.7119fep125F;
    static { NAMED.put("Vivid Orange", -0x1.7119fep125F); LIST.add(-0x1.7119fep125F); }

    /**
     * This color constant "Strong Orange" has RGBA8888 code {@code FF8F2FFF}, hue 0.07692308, saturation 0.8156863, lightness 0.5921569, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5f1ffep125F}.
     * <pre>
     * <font style='background-color: #FF8F2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8F2F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8F2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF8F2F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF8F2F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF8F2F'>&nbsp;@&nbsp;</font><font style='background-color: #FF8F2F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8F2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8F2F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_ORANGE = -0x1.5f1ffep125F;
    static { NAMED.put("Strong Orange", -0x1.5f1ffep125F); LIST.add(-0x1.5f1ffep125F); }

    /**
     * This color constant "Deep Orange" has RGBA8888 code {@code E86A00FF}, hue 0.07614943, saturation 0.9098039, lightness 0.45490196, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.00d5dp125F}.
     * <pre>
     * <font style='background-color: #E86A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E86A00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E86A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E86A00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E86A00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E86A00'>&nbsp;@&nbsp;</font><font style='background-color: #E86A00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E86A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E86A00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE = -0x1.00d5dp125F;
    static { NAMED.put("Deep Orange", -0x1.00d5dp125F); LIST.add(-0x1.00d5dp125F); }

    /**
     * This color constant "Light Orange" has RGBA8888 code {@code FFBE9BFF}, hue 0.058333334, saturation 0.39215684, lightness 0.8039216, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.377dfep126F}.
     * <pre>
     * <font style='background-color: #FFBE9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBE9B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBE9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBE9B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBE9B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBE9B'>&nbsp;@&nbsp;</font><font style='background-color: #FFBE9B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBE9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBE9B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_ORANGE = -0x1.377dfep126F;
    static { NAMED.put("Light Orange", -0x1.377dfep126F); LIST.add(-0x1.377dfep126F); }

    /**
     * This color constant "Moderate Orange" has RGBA8888 code {@code F09C73FF}, hue 0.054666676, saturation 0.49019608, lightness 0.6960785, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e739ep125F}.
     * <pre>
     * <font style='background-color: #F09C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F09C73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F09C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F09C73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F09C73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F09C73'>&nbsp;@&nbsp;</font><font style='background-color: #F09C73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F09C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F09C73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_ORANGE = -0x1.e739ep125F;
    static { NAMED.put("Moderate Orange", -0x1.e739ep125F); LIST.add(-0x1.e739ep125F); }

    /**
     * This color constant "Brownish Orange" has RGBA8888 code {@code D7712DFF}, hue 0.06666666, saturation 0.6666667, lightness 0.5098039, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5ae3aep125F}.
     * <pre>
     * <font style='background-color: #D7712D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7712D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7712D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7712D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7712D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7712D'>&nbsp;@&nbsp;</font><font style='background-color: #D7712D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7712D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7712D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_ORANGE = -0x1.5ae3aep125F;
    static { NAMED.put("Brownish Orange", -0x1.5ae3aep125F); LIST.add(-0x1.5ae3aep125F); }

    /**
     * This color constant "Strong Brown" has RGBA8888 code {@code AE5000FF}, hue 0.07662836, saturation 0.68235296, lightness 0.34117648, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.00a15cp125F}.
     * <pre>
     * <font style='background-color: #AE5000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE5000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE5000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE5000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE5000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE5000'>&nbsp;@&nbsp;</font><font style='background-color: #AE5000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE5000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE5000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BROWN = -0x1.00a15cp125F;
    static { NAMED.put("Strong Brown", -0x1.00a15cp125F); LIST.add(-0x1.00a15cp125F); }

    /**
     * This color constant "Deep Brown" has RGBA8888 code {@code 79200AFF}, hue 0.033033036, saturation 0.43529412, lightness 0.25686276, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.1440f2p125F}.
     * <pre>
     * <font style='background-color: #79200A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #79200A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #79200A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #79200A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #79200A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #79200A'>&nbsp;@&nbsp;</font><font style='background-color: #79200A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #79200A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #79200A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN = -0x1.1440f2p125F;
    static { NAMED.put("Deep Brown", -0x1.1440f2p125F); LIST.add(-0x1.1440f2p125F); }

    /**
     * This color constant "Light Brown" has RGBA8888 code {@code D5855EFF}, hue 0.054621857, saturation 0.46666667, lightness 0.6019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.bd0baap125F}.
     * <pre>
     * <font style='background-color: #D5855E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5855E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5855E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5855E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5855E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5855E'>&nbsp;@&nbsp;</font><font style='background-color: #D5855E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5855E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5855E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BROWN = -0x1.bd0baap125F;
    static { NAMED.put("Light Brown", -0x1.bd0baap125F); LIST.add(-0x1.bd0baap125F); }

    /**
     * This color constant "Moderate Brown" has RGBA8888 code {@code 995538FF}, hue 0.04982818, saturation 0.3803922, lightness 0.40980393, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.70ab32p125F}.
     * <pre>
     * <font style='background-color: #995538;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #995538; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #995538;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #995538'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #995538'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #995538'>&nbsp;@&nbsp;</font><font style='background-color: #995538; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #995538;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #995538; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BROWN = -0x1.70ab32p125F;
    static { NAMED.put("Moderate Brown", -0x1.70ab32p125F); LIST.add(-0x1.70ab32p125F); }

    /**
     * This color constant "Dark Brown" has RGBA8888 code {@code 63290EFF}, hue 0.05294118, saturation 0.33333334, lightness 0.22156863, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.1c52c6p125F}.
     * <pre>
     * <font style='background-color: #63290E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63290E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63290E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #63290E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #63290E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #63290E'>&nbsp;@&nbsp;</font><font style='background-color: #63290E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63290E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63290E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BROWN = -0x1.1c52c6p125F;
    static { NAMED.put("Dark Brown", -0x1.1c52c6p125F); LIST.add(-0x1.1c52c6p125F); }

    /**
     * This color constant "Light Grayish Brown" has RGBA8888 code {@code B88D7AFF}, hue 0.05107528, saturation 0.24313727, lightness 0.6, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f51b7p125F}.
     * <pre>
     * <font style='background-color: #B88D7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B88D7A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B88D7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B88D7A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B88D7A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B88D7A'>&nbsp;@&nbsp;</font><font style='background-color: #B88D7A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B88D7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B88D7A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_BROWN = -0x1.f51b7p125F;
    static { NAMED.put("Light Grayish Brown", -0x1.f51b7p125F); LIST.add(-0x1.f51b7p125F); }

    /**
     * This color constant "Grayish Brown" has RGBA8888 code {@code 8D5C4FFF}, hue 0.03494623, saturation 0.24313727, lightness 0.43137255, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9eb91ap125F}.
     * <pre>
     * <font style='background-color: #8D5C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D5C4F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D5C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8D5C4F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8D5C4F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8D5C4F'>&nbsp;@&nbsp;</font><font style='background-color: #8D5C4F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D5C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D5C4F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_BROWN = -0x1.9eb91ap125F;
    static { NAMED.put("Grayish Brown", -0x1.9eb91ap125F); LIST.add(-0x1.9eb91ap125F); }

    /**
     * This color constant "Dark Grayish Brown" has RGBA8888 code {@code 5B372FFF}, hue 0.030303027, saturation 0.17254902, lightness 0.27058825, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5e6eb6p125F}.
     * <pre>
     * <font style='background-color: #5B372F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B372F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B372F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B372F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B372F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B372F'>&nbsp;@&nbsp;</font><font style='background-color: #5B372F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B372F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B372F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_BROWN = -0x1.5e6eb6p125F;
    static { NAMED.put("Dark Grayish Brown", -0x1.5e6eb6p125F); LIST.add(-0x1.5e6eb6p125F); }

    /**
     * This color constant "Light Brownish Gray" has RGBA8888 code {@code AB938DFF}, hue 0.033333335, saturation 0.11764705, lightness 0.6117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.1b2756p126F}.
     * <pre>
     * <font style='background-color: #AB938D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB938D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB938D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB938D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB938D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB938D'>&nbsp;@&nbsp;</font><font style='background-color: #AB938D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB938D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB938D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BROWNISH_GRAY = -0x1.1b2756p126F;
    static { NAMED.put("Light Brownish Gray", -0x1.1b2756p126F); LIST.add(-0x1.1b2756p126F); }

    /**
     * This color constant "Brownish Gray" has RGBA8888 code {@code 7D625FFF}, hue 0.016666668, saturation 0.11764705, lightness 0.43137255, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.bec4fap125F}.
     * <pre>
     * <font style='background-color: #7D625F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D625F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D625F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7D625F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7D625F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7D625F'>&nbsp;@&nbsp;</font><font style='background-color: #7D625F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D625F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D625F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_GRAY = -0x1.bec4fap125F;
    static { NAMED.put("Brownish Gray", -0x1.bec4fap125F); LIST.add(-0x1.bec4fap125F); }

    /**
     * This color constant "Brilliant Orange Yellow" has RGBA8888 code {@code FFCE71FF}, hue 0.10915494, saturation 0.5568627, lightness 0.72156864, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e39dfep125F}.
     * <pre>
     * <font style='background-color: #FFCE71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFCE71; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFCE71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFCE71'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFCE71'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFCE71'>&nbsp;@&nbsp;</font><font style='background-color: #FFCE71; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFCE71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFCE71; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_ORANGE_YELLOW = -0x1.e39dfep125F;
    static { NAMED.put("Brilliant Orange Yellow", -0x1.e39dfep125F); LIST.add(-0x1.e39dfep125F); }

    /**
     * This color constant "Strong Orange Yellow" has RGBA8888 code {@code FFAF2AFF}, hue 0.10406887, saturation 0.8352941, lightness 0.58235294, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.555ffep125F}.
     * <pre>
     * <font style='background-color: #FFAF2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFAF2A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFAF2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFAF2A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFAF2A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFAF2A'>&nbsp;@&nbsp;</font><font style='background-color: #FFAF2A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFAF2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFAF2A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_ORANGE_YELLOW = -0x1.555ffep125F;
    static { NAMED.put("Strong Orange Yellow", -0x1.555ffep125F); LIST.add(-0x1.555ffep125F); }

    /**
     * This color constant "Deep Orange Yellow" has RGBA8888 code {@code EE9300FF}, hue 0.102941185, saturation 0.93333334, lightness 0.46666667, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0127dcp125F}.
     * <pre>
     * <font style='background-color: #EE9300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE9300; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE9300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EE9300'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EE9300'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EE9300'>&nbsp;@&nbsp;</font><font style='background-color: #EE9300; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE9300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE9300; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_YELLOW = -0x1.0127dcp125F;
    static { NAMED.put("Deep Orange Yellow", -0x1.0127dcp125F); LIST.add(-0x1.0127dcp125F); }

    /**
     * This color constant "Light Orange Yellow" has RGBA8888 code {@code FFD198FF}, hue 0.09223302, saturation 0.40392154, lightness 0.7980392, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.31a3fep126F}.
     * <pre>
     * <font style='background-color: #FFD198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD198; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD198'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD198'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD198'>&nbsp;@&nbsp;</font><font style='background-color: #FFD198; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD198; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_ORANGE_YELLOW = -0x1.31a3fep126F;
    static { NAMED.put("Light Orange Yellow", -0x1.31a3fep126F); LIST.add(-0x1.31a3fep126F); }

    /**
     * This color constant "Moderate Orange Yellow" has RGBA8888 code {@code F7B56EFF}, hue 0.08637469, saturation 0.5372549, lightness 0.7, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.dd6beep125F}.
     * <pre>
     * <font style='background-color: #F7B56E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7B56E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7B56E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7B56E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7B56E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7B56E'>&nbsp;@&nbsp;</font><font style='background-color: #F7B56E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7B56E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7B56E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_ORANGE_YELLOW = -0x1.dd6beep125F;
    static { NAMED.put("Moderate Orange Yellow", -0x1.dd6beep125F); LIST.add(-0x1.dd6beep125F); }

    /**
     * This color constant "Dark Orange Yellow" has RGBA8888 code {@code E39331FF}, hue 0.09176031, saturation 0.69803923, lightness 0.54117644, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.6327c6p125F}.
     * <pre>
     * <font style='background-color: #E39331;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E39331; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E39331;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E39331'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E39331'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E39331'>&nbsp;@&nbsp;</font><font style='background-color: #E39331; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E39331;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E39331; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_ORANGE_YELLOW = -0x1.6327c6p125F;
    static { NAMED.put("Dark Orange Yellow", -0x1.6327c6p125F); LIST.add(-0x1.6327c6p125F); }

    /**
     * This color constant "Pale Orange Yellow" has RGBA8888 code {@code F7D2B3FF}, hue 0.075980395, saturation 0.26666665, lightness 0.8352941, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.67a5eep126F}.
     * <pre>
     * <font style='background-color: #F7D2B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7D2B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7D2B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7D2B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7D2B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7D2B3'>&nbsp;@&nbsp;</font><font style='background-color: #F7D2B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7D2B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7D2B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_YELLOW = -0x1.67a5eep126F;
    static { NAMED.put("Pale Orange Yellow", -0x1.67a5eep126F); LIST.add(-0x1.67a5eep126F); }

    /**
     * This color constant "Strong Yellowish Brown" has RGBA8888 code {@code BC7700FF}, hue 0.10549645, saturation 0.7372549, lightness 0.36862746, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.00ef78p125F}.
     * <pre>
     * <font style='background-color: #BC7700;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC7700; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC7700;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BC7700'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BC7700'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BC7700'>&nbsp;@&nbsp;</font><font style='background-color: #BC7700; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC7700;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC7700; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_BROWN = -0x1.00ef78p125F;
    static { NAMED.put("Strong Yellowish Brown", -0x1.00ef78p125F); LIST.add(-0x1.00ef78p125F); }

    /**
     * This color constant "Deep Yellowish Brown" has RGBA8888 code {@code 854E00FF}, hue 0.09774436, saturation 0.52156866, lightness 0.26078433, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.009d0ap125F}.
     * <pre>
     * <font style='background-color: #854E00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #854E00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #854E00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #854E00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #854E00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #854E00'>&nbsp;@&nbsp;</font><font style='background-color: #854E00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #854E00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #854E00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_BROWN = -0x1.009d0ap125F;
    static { NAMED.put("Deep Yellowish Brown", -0x1.009d0ap125F); LIST.add(-0x1.009d0ap125F); }

    /**
     * This color constant "Light Yellowish Brown" has RGBA8888 code {@code E0A775FF}, hue 0.07788163, saturation 0.41960785, lightness 0.6686275, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.eb4fcp125F}.
     * <pre>
     * <font style='background-color: #E0A775;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0A775; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0A775;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0A775'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0A775'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0A775'>&nbsp;@&nbsp;</font><font style='background-color: #E0A775; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0A775;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0A775; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_BROWN = -0x1.eb4fcp125F;
    static { NAMED.put("Light Yellowish Brown", -0x1.eb4fcp125F); LIST.add(-0x1.eb4fcp125F); }

    /**
     * This color constant "Moderate Yellowish Brown" has RGBA8888 code {@code A57748FF}, hue 0.08422939, saturation 0.3647059, lightness 0.4647059, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.90ef4ap125F}.
     * <pre>
     * <font style='background-color: #A57748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A57748; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A57748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A57748'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A57748'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A57748'>&nbsp;@&nbsp;</font><font style='background-color: #A57748; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A57748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A57748; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_BROWN = -0x1.90ef4ap125F;
    static { NAMED.put("Moderate Yellowish Brown", -0x1.90ef4ap125F); LIST.add(-0x1.90ef4ap125F); }

    /**
     * This color constant "Dark Yellowish Brown" has RGBA8888 code {@code 6C421FFF}, hue 0.075757585, saturation 0.3019608, lightness 0.272549, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.3e84d8p125F}.
     * <pre>
     * <font style='background-color: #6C421F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C421F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C421F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C421F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C421F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C421F'>&nbsp;@&nbsp;</font><font style='background-color: #6C421F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C421F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C421F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_BROWN = -0x1.3e84d8p125F;
    static { NAMED.put("Dark Yellowish Brown", -0x1.3e84d8p125F); LIST.add(-0x1.3e84d8p125F); }

    /**
     * This color constant "Light Grayish Yellowish Brown" has RGBA8888 code {@code CDA899FF}, hue 0.048076924, saturation 0.20392156, lightness 0.7019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.33519ap126F}.
     * <pre>
     * <font style='background-color: #CDA899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDA899; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDA899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CDA899'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CDA899'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CDA899'>&nbsp;@&nbsp;</font><font style='background-color: #CDA899; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDA899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDA899; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_YELLOWISH_BROWN = -0x1.33519ap126F;
    static { NAMED.put("Light Grayish Yellowish Brown", -0x1.33519ap126F); LIST.add(-0x1.33519ap126F); }

    /**
     * This color constant "Grayish Yellowish Brown" has RGBA8888 code {@code A07765FF}, hue 0.050847452, saturation 0.23137257, lightness 0.5117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.caef4p125F}.
     * <pre>
     * <font style='background-color: #A07765;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A07765; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A07765;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A07765'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A07765'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A07765'>&nbsp;@&nbsp;</font><font style='background-color: #A07765; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A07765;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A07765; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOWISH_BROWN = -0x1.caef4p125F;
    static { NAMED.put("Grayish Yellowish Brown", -0x1.caef4p125F); LIST.add(-0x1.caef4p125F); }

    /**
     * This color constant "Dark Grayish Yellowish Brown" has RGBA8888 code {@code 664D3BFF}, hue 0.069767445, saturation 0.16862746, lightness 0.3156863, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.769accp125F}.
     * <pre>
     * <font style='background-color: #664D3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #664D3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #664D3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #664D3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #664D3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #664D3B'>&nbsp;@&nbsp;</font><font style='background-color: #664D3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #664D3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #664D3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_YELLOWISH_BROWN = -0x1.769accp125F;
    static { NAMED.put("Dark Grayish Yellowish Brown", -0x1.769accp125F); LIST.add(-0x1.769accp125F); }

    /**
     * This color constant "Vivid Yellow" has RGBA8888 code {@code FFCC00FF}, hue 0.13333334, saturation 1.0, lightness 0.5, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0199fep125F}.
     * <pre>
     * <font style='background-color: #FFCC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFCC00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFCC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFCC00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFCC00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFCC00'>&nbsp;@&nbsp;</font><font style='background-color: #FFCC00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFCC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFCC00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOW = -0x1.0199fep125F;
    static { NAMED.put("Vivid Yellow", -0x1.0199fep125F); LIST.add(-0x1.0199fep125F); }

    /**
     * This color constant "Brilliant Yellow" has RGBA8888 code {@code FFD95DFF}, hue 0.12757203, saturation 0.6352941, lightness 0.68235296, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.bbb3fep125F}.
     * <pre>
     * <font style='background-color: #FFD95D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD95D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD95D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD95D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD95D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD95D'>&nbsp;@&nbsp;</font><font style='background-color: #FFD95D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD95D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD95D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOW = -0x1.bbb3fep125F;
    static { NAMED.put("Brilliant Yellow", -0x1.bbb3fep125F); LIST.add(-0x1.bbb3fep125F); }

    /**
     * This color constant "Strong Yellow" has RGBA8888 code {@code F0BB1AFF}, hue 0.12538941, saturation 0.8392157, lightness 0.52156866, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.3577ep125F}.
     * <pre>
     * <font style='background-color: #F0BB1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0BB1A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0BB1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0BB1A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0BB1A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0BB1A'>&nbsp;@&nbsp;</font><font style='background-color: #F0BB1A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0BB1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0BB1A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOW = -0x1.3577ep125F;
    static { NAMED.put("Strong Yellow", -0x1.3577ep125F); LIST.add(-0x1.3577ep125F); }

    /**
     * This color constant "Deep Yellow" has RGBA8888 code {@code D29F00FF}, hue 0.12619048, saturation 0.8235294, lightness 0.4117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.013fa4p125F}.
     * <pre>
     * <font style='background-color: #D29F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D29F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D29F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D29F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D29F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D29F00'>&nbsp;@&nbsp;</font><font style='background-color: #D29F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D29F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D29F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW = -0x1.013fa4p125F;
    static { NAMED.put("Deep Yellow", -0x1.013fa4p125F); LIST.add(-0x1.013fa4p125F); }

    /**
     * This color constant "Light Yellow" has RGBA8888 code {@code FADF98FF}, hue 0.1207483, saturation 0.3843137, lightness 0.7882353, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.31bff4p126F}.
     * <pre>
     * <font style='background-color: #FADF98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FADF98; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FADF98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FADF98'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FADF98'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FADF98'>&nbsp;@&nbsp;</font><font style='background-color: #FADF98; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FADF98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FADF98; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOW = -0x1.31bff4p126F;
    static { NAMED.put("Light Yellow", -0x1.31bff4p126F); LIST.add(-0x1.31bff4p126F); }

    /**
     * This color constant "Moderate Yellow" has RGBA8888 code {@code EABB56FF}, hue 0.113738745, saturation 0.5803921, lightness 0.627451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ad77d4p125F}.
     * <pre>
     * <font style='background-color: #EABB56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EABB56; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EABB56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EABB56'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EABB56'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EABB56'>&nbsp;@&nbsp;</font><font style='background-color: #EABB56; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EABB56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EABB56; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOW = -0x1.ad77d4p125F;
    static { NAMED.put("Moderate Yellow", -0x1.ad77d4p125F); LIST.add(-0x1.ad77d4p125F); }

    /**
     * This color constant "Dark Yellow" has RGBA8888 code {@code CB9F3CFF}, hue 0.11538462, saturation 0.56078434, lightness 0.51568633, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.793f96p125F}.
     * <pre>
     * <font style='background-color: #CB9F3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB9F3C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB9F3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB9F3C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB9F3C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB9F3C'>&nbsp;@&nbsp;</font><font style='background-color: #CB9F3C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB9F3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB9F3C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOW = -0x1.793f96p125F;
    static { NAMED.put("Dark Yellow", -0x1.793f96p125F); LIST.add(-0x1.793f96p125F); }

    /**
     * This color constant "Pale Yellow" has RGBA8888 code {@code F7E1BDFF}, hue 0.10344828, saturation 0.22745097, lightness 0.85490197, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.7bc3eep126F}.
     * <pre>
     * <font style='background-color: #F7E1BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7E1BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7E1BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7E1BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7E1BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7E1BD'>&nbsp;@&nbsp;</font><font style='background-color: #F7E1BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7E1BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7E1BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.7bc3eep126F;
    static { NAMED.put("Pale Yellow", -0x1.7bc3eep126F); LIST.add(-0x1.7bc3eep126F); }

    /**
     * This color constant "Grayish Yellow" has RGBA8888 code {@code DEBF84FF}, hue 0.10925926, saturation 0.35294116, lightness 0.69411767, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.097fbcp126F}.
     * <pre>
     * <font style='background-color: #DEBF84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEBF84; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEBF84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEBF84'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEBF84'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEBF84'>&nbsp;@&nbsp;</font><font style='background-color: #DEBF84; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEBF84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEBF84; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOW = -0x1.097fbcp126F;
    static { NAMED.put("Grayish Yellow", -0x1.097fbcp126F); LIST.add(-0x1.097fbcp126F); }

    /**
     * This color constant "Dark Grayish Yellow" has RGBA8888 code {@code C1A162FF}, hue 0.11052632, saturation 0.37254903, lightness 0.57058823, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c54382p125F}.
     * <pre>
     * <font style='background-color: #C1A162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1A162; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1A162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1A162'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1A162'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1A162'>&nbsp;@&nbsp;</font><font style='background-color: #C1A162; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1A162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1A162; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_YELLOW = -0x1.c54382p125F;
    static { NAMED.put("Dark Grayish Yellow", -0x1.c54382p125F); LIST.add(-0x1.c54382p125F); }

    /**
     * This color constant "Yellowish White" has RGBA8888 code {@code F9E7E4FF}, hue 0.023809524, saturation 0.082352936, lightness 0.9352941, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c9cff2p126F}.
     * <pre>
     * <font style='background-color: #F9E7E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9E7E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9E7E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F9E7E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F9E7E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F9E7E4'>&nbsp;@&nbsp;</font><font style='background-color: #F9E7E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9E7E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9E7E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOWISH_WHITE = -0x1.c9cff2p126F;
    static { NAMED.put("Yellowish White", -0x1.c9cff2p126F); LIST.add(-0x1.c9cff2p126F); }

    /**
     * This color constant "Yellowish Gray" has RGBA8888 code {@code D5C7B7FF}, hue 0.08888889, saturation 0.11764705, lightness 0.7764706, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.6f8faap126F}.
     * <pre>
     * <font style='background-color: #D5C7B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5C7B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5C7B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5C7B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5C7B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5C7B7'>&nbsp;@&nbsp;</font><font style='background-color: #D5C7B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5C7B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5C7B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOWISH_GRAY = -0x1.6f8faap126F;
    static { NAMED.put("Yellowish Gray", -0x1.6f8faap126F); LIST.add(-0x1.6f8faap126F); }

    /**
     * This color constant "Light Olive Brown" has RGBA8888 code {@code B1892FFF}, hue 0.11538462, saturation 0.50980395, lightness 0.43921572, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5f1362p125F}.
     * <pre>
     * <font style='background-color: #B1892F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1892F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1892F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1892F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1892F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1892F'>&nbsp;@&nbsp;</font><font style='background-color: #B1892F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1892F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1892F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE_BROWN = -0x1.5f1362p125F;
    static { NAMED.put("Light Olive Brown", -0x1.5f1362p125F); LIST.add(-0x1.5f1362p125F); }

    /**
     * This color constant "Moderate Olive Brown" has RGBA8888 code {@code 8A6713FF}, hue 0.11764705, saturation 0.4666667, lightness 0.30784315, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.26cf14p125F}.
     * <pre>
     * <font style='background-color: #8A6713;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A6713; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A6713;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A6713'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A6713'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A6713'>&nbsp;@&nbsp;</font><font style='background-color: #8A6713; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A6713;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A6713; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE_BROWN = -0x1.26cf14p125F;
    static { NAMED.put("Moderate Olive Brown", -0x1.26cf14p125F); LIST.add(-0x1.26cf14p125F); }

    /**
     * This color constant "Dark Olive Brown" has RGBA8888 code {@code 583A14FF}, hue 0.09313724, saturation 0.26666668, lightness 0.21176471, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.2874bp125F}.
     * <pre>
     * <font style='background-color: #583A14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #583A14; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #583A14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #583A14'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #583A14'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #583A14'>&nbsp;@&nbsp;</font><font style='background-color: #583A14; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #583A14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #583A14; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE_BROWN = -0x1.2874bp125F;
    static { NAMED.put("Dark Olive Brown", -0x1.2874bp125F); LIST.add(-0x1.2874bp125F); }

    /**
     * This color constant "Vivid Greenish Yellow" has RGBA8888 code {@code F7E800FF}, hue 0.1565452, saturation 0.96862745, lightness 0.48431373, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.01d1eep125F}.
     * <pre>
     * <font style='background-color: #F7E800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7E800; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7E800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7E800'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7E800'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7E800'>&nbsp;@&nbsp;</font><font style='background-color: #F7E800; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7E800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7E800; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_GREENISH_YELLOW = -0x1.01d1eep125F;
    static { NAMED.put("Vivid Greenish Yellow", -0x1.01d1eep125F); LIST.add(-0x1.01d1eep125F); }

    /**
     * This color constant "Brilliant Greenish Yellow" has RGBA8888 code {@code F5E736FF}, hue 0.15445027, saturation 0.7490196, lightness 0.5862745, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.6dcfeap125F}.
     * <pre>
     * <font style='background-color: #F5E736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E736; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5E736'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5E736'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5E736'>&nbsp;@&nbsp;</font><font style='background-color: #F5E736; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E736; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREENISH_YELLOW = -0x1.6dcfeap125F;
    static { NAMED.put("Brilliant Greenish Yellow", -0x1.6dcfeap125F); LIST.add(-0x1.6dcfeap125F); }

    /**
     * This color constant "Strong Greenish Yellow" has RGBA8888 code {@code D3C81CFF}, hue 0.15664846, saturation 0.7176471, lightness 0.46862745, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.3991a6p125F}.
     * <pre>
     * <font style='background-color: #D3C81C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3C81C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3C81C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3C81C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3C81C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3C81C'>&nbsp;@&nbsp;</font><font style='background-color: #D3C81C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3C81C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3C81C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREENISH_YELLOW = -0x1.3991a6p125F;
    static { NAMED.put("Strong Greenish Yellow", -0x1.3991a6p125F); LIST.add(-0x1.3991a6p125F); }

    /**
     * This color constant "Deep Greenish Yellow" has RGBA8888 code {@code B6AB00FF}, hue 0.15659341, saturation 0.7137255, lightness 0.35686275, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.01576cp125F}.
     * <pre>
     * <font style='background-color: #B6AB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6AB00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6AB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6AB00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6AB00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6AB00'>&nbsp;@&nbsp;</font><font style='background-color: #B6AB00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6AB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6AB00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREENISH_YELLOW = -0x1.01576cp125F;
    static { NAMED.put("Deep Greenish Yellow", -0x1.01576cp125F); LIST.add(-0x1.01576cp125F); }

    /**
     * This color constant "Light Greenish Yellow" has RGBA8888 code {@code FAE686FF}, hue 0.13793103, saturation 0.45490193, lightness 0.7529412, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0dcdf4p126F}.
     * <pre>
     * <font style='background-color: #FAE686;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAE686; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAE686;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FAE686'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FAE686'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FAE686'>&nbsp;@&nbsp;</font><font style='background-color: #FAE686; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAE686;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAE686; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_YELLOW = -0x1.0dcdf4p126F;
    static { NAMED.put("Light Greenish Yellow", -0x1.0dcdf4p126F); LIST.add(-0x1.0dcdf4p126F); }

    /**
     * This color constant "Moderate Greenish Yellow" has RGBA8888 code {@code D0C556FF}, hue 0.15163934, saturation 0.47843137, lightness 0.5764706, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ad8bap125F}.
     * <pre>
     * <font style='background-color: #D0C556;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0C556; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0C556;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0C556'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0C556'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0C556'>&nbsp;@&nbsp;</font><font style='background-color: #D0C556; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0C556;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0C556; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREENISH_YELLOW = -0x1.ad8bap125F;
    static { NAMED.put("Moderate Greenish Yellow", -0x1.ad8bap125F); LIST.add(-0x1.ad8bap125F); }

    /**
     * This color constant "Dark Greenish Yellow" has RGBA8888 code {@code B4A730FF}, hue 0.15025252, saturation 0.5176471, lightness 0.44705883, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.614f68p125F}.
     * <pre>
     * <font style='background-color: #B4A730;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4A730; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4A730;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4A730'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4A730'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4A730'>&nbsp;@&nbsp;</font><font style='background-color: #B4A730; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4A730;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4A730; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_YELLOW = -0x1.614f68p125F;
    static { NAMED.put("Dark Greenish Yellow", -0x1.614f68p125F); LIST.add(-0x1.614f68p125F); }

    /**
     * This color constant "Pale Greenish Yellow" has RGBA8888 code {@code F5E6ABFF}, hue 0.13288288, saturation 0.29019606, lightness 0.8156863, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.57cdeap126F}.
     * <pre>
     * <font style='background-color: #F5E6AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E6AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E6AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5E6AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5E6AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5E6AB'>&nbsp;@&nbsp;</font><font style='background-color: #F5E6AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E6AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E6AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREENISH_YELLOW = -0x1.57cdeap126F;
    static { NAMED.put("Pale Greenish Yellow", -0x1.57cdeap126F); LIST.add(-0x1.57cdeap126F); }

    /**
     * This color constant "Grayish Greenish Yellow" has RGBA8888 code {@code D6C37DFF}, hue 0.13108616, saturation 0.34901962, lightness 0.6647059, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fb87acp125F}.
     * <pre>
     * <font style='background-color: #D6C37D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6C37D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6C37D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6C37D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6C37D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6C37D'>&nbsp;@&nbsp;</font><font style='background-color: #D6C37D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6C37D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6C37D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_GREENISH_YELLOW = -0x1.fb87acp125F;
    static { NAMED.put("Grayish Greenish Yellow", -0x1.fb87acp125F); LIST.add(-0x1.fb87acp125F); }

    /**
     * This color constant "Light Olive" has RGBA8888 code {@code A78F25FF}, hue 0.13589744, saturation 0.50980395, lightness 0.4, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.4b1f4ep125F}.
     * <pre>
     * <font style='background-color: #A78F25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A78F25; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A78F25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A78F25'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A78F25'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A78F25'>&nbsp;@&nbsp;</font><font style='background-color: #A78F25; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A78F25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A78F25; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE = -0x1.4b1f4ep125F;
    static { NAMED.put("Light Olive", -0x1.4b1f4ep125F); LIST.add(-0x1.4b1f4ep125F); }

    /**
     * This color constant "Moderate Olive" has RGBA8888 code {@code 7A6E14FF}, hue 0.14705881, saturation 0.4, lightness 0.2784314, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.28dcf4p125F}.
     * <pre>
     * <font style='background-color: #7A6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A6E14; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A6E14'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A6E14'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A6E14'>&nbsp;@&nbsp;</font><font style='background-color: #7A6E14; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A6E14; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE = -0x1.28dcf4p125F;
    static { NAMED.put("Moderate Olive", -0x1.28dcf4p125F); LIST.add(-0x1.28dcf4p125F); }

    /**
     * This color constant "Dark Olive" has RGBA8888 code {@code 4B3D00FF}, hue 0.13555555, saturation 0.29411766, lightness 0.14705883, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.007a96p125F}.
     * <pre>
     * <font style='background-color: #4B3D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B3D00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B3D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B3D00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B3D00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B3D00'>&nbsp;@&nbsp;</font><font style='background-color: #4B3D00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B3D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B3D00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE = -0x1.007a96p125F;
    static { NAMED.put("Dark Olive", -0x1.007a96p125F); LIST.add(-0x1.007a96p125F); }

    /**
     * This color constant "Light Grayish Olive" has RGBA8888 code {@code A79871FF}, hue 0.12037037, saturation 0.21176472, lightness 0.54901963, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e3314ep125F}.
     * <pre>
     * <font style='background-color: #A79871;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A79871; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A79871;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A79871'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A79871'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A79871'>&nbsp;@&nbsp;</font><font style='background-color: #A79871; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A79871;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A79871; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_OLIVE = -0x1.e3314ep125F;
    static { NAMED.put("Light Grayish Olive", -0x1.e3314ep125F); LIST.add(-0x1.e3314ep125F); }

    /**
     * This color constant "Grayish Olive" has RGBA8888 code {@code 786840FF}, hue 0.11904762, saturation 0.21960783, lightness 0.36078432, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.80d0fp125F}.
     * <pre>
     * <font style='background-color: #786840;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #786840; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #786840;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #786840'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #786840'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #786840'>&nbsp;@&nbsp;</font><font style='background-color: #786840; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #786840;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #786840; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_OLIVE = -0x1.80d0fp125F;
    static { NAMED.put("Grayish Olive", -0x1.80d0fp125F); LIST.add(-0x1.80d0fp125F); }

    /**
     * This color constant "Dark Grayish Olive" has RGBA8888 code {@code 484121FF}, hue 0.13675213, saturation 0.15294118, lightness 0.20588236, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.42829p125F}.
     * <pre>
     * <font style='background-color: #484121;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #484121; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #484121;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #484121'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #484121'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #484121'>&nbsp;@&nbsp;</font><font style='background-color: #484121; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #484121;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #484121; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_OLIVE = -0x1.42829p125F;
    static { NAMED.put("Dark Grayish Olive", -0x1.42829p125F); LIST.add(-0x1.42829p125F); }

    /**
     * This color constant "Light Olive Gray" has RGBA8888 code {@code AC9684FF}, hue 0.075, saturation 0.15686274, lightness 0.59607846, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.092d58p126F}.
     * <pre>
     * <font style='background-color: #AC9684;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC9684; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC9684;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC9684'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC9684'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC9684'>&nbsp;@&nbsp;</font><font style='background-color: #AC9684; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC9684;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC9684; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE_GRAY = -0x1.092d58p126F;
    static { NAMED.put("Light Olive Gray", -0x1.092d58p126F); LIST.add(-0x1.092d58p126F); }

    /**
     * This color constant "Olive Gray" has RGBA8888 code {@code 766352FF}, hue 0.0787037, saturation 0.14117646, lightness 0.39215687, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a4c6ecp125F}.
     * <pre>
     * <font style='background-color: #766352;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #766352; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #766352;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #766352'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #766352'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #766352'>&nbsp;@&nbsp;</font><font style='background-color: #766352; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #766352;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #766352; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_GRAY = -0x1.a4c6ecp125F;
    static { NAMED.put("Olive Gray", -0x1.a4c6ecp125F); LIST.add(-0x1.a4c6ecp125F); }

    /**
     * This color constant "Vivid Yellow Green" has RGBA8888 code {@code B0ED00FF}, hue 0.209564, saturation 0.92941177, lightness 0.46470588, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.01db6p125F}.
     * <pre>
     * <font style='background-color: #B0ED00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0ED00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0ED00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0ED00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0ED00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0ED00'>&nbsp;@&nbsp;</font><font style='background-color: #B0ED00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0ED00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0ED00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOW_GREEN = -0x1.01db6p125F;
    static { NAMED.put("Vivid Yellow Green", -0x1.01db6p125F); LIST.add(-0x1.01db6p125F); }

    /**
     * This color constant "Brilliant Yellow Green" has RGBA8888 code {@code CBED54FF}, hue 0.20370372, saturation 0.6, lightness 0.6294118, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a9db96p125F}.
     * <pre>
     * <font style='background-color: #CBED54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBED54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBED54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBED54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBED54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBED54'>&nbsp;@&nbsp;</font><font style='background-color: #CBED54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBED54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBED54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOW_GREEN = -0x1.a9db96p125F;
    static { NAMED.put("Brilliant Yellow Green", -0x1.a9db96p125F); LIST.add(-0x1.a9db96p125F); }

    /**
     * This color constant "Strong Yellow Green" has RGBA8888 code {@code 91B615FF}, hue 0.20496894, saturation 0.6313726, lightness 0.39803922, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.2b6d22p125F}.
     * <pre>
     * <font style='background-color: #91B615;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91B615; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91B615;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #91B615'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #91B615'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #91B615'>&nbsp;@&nbsp;</font><font style='background-color: #91B615; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91B615;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91B615; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOW_GREEN = -0x1.2b6d22p125F;
    static { NAMED.put("Strong Yellow Green", -0x1.2b6d22p125F); LIST.add(-0x1.2b6d22p125F); }

    /**
     * This color constant "Deep Yellow Green" has RGBA8888 code {@code 558400FF}, hue 0.22601011, saturation 0.5176471, lightness 0.25882354, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0108aap125F}.
     * <pre>
     * <font style='background-color: #558400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #558400; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #558400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #558400'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #558400'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #558400'>&nbsp;@&nbsp;</font><font style='background-color: #558400; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #558400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #558400; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_GREEN = -0x1.0108aap125F;
    static { NAMED.put("Deep Yellow Green", -0x1.0108aap125F); LIST.add(-0x1.0108aap125F); }

    /**
     * This color constant "Light Yellow Green" has RGBA8888 code {@code D7E997FF}, hue 0.20325205, saturation 0.3215686, lightness 0.7529412, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.2fd3aep126F}.
     * <pre>
     * <font style='background-color: #D7E997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7E997; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7E997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7E997'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7E997'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7E997'>&nbsp;@&nbsp;</font><font style='background-color: #D7E997; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7E997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7E997; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOW_GREEN = -0x1.2fd3aep126F;
    static { NAMED.put("Light Yellow Green", -0x1.2fd3aep126F); LIST.add(-0x1.2fd3aep126F); }

    /**
     * This color constant "Moderate Yellow Green" has RGBA8888 code {@code 9BAF5CFF}, hue 0.20682731, saturation 0.3254902, lightness 0.5235294, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.b95f36p125F}.
     * <pre>
     * <font style='background-color: #9BAF5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BAF5C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BAF5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9BAF5C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9BAF5C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9BAF5C'>&nbsp;@&nbsp;</font><font style='background-color: #9BAF5C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BAF5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BAF5C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOW_GREEN = -0x1.b95f36p125F;
    static { NAMED.put("Moderate Yellow Green", -0x1.b95f36p125F); LIST.add(-0x1.b95f36p125F); }

    /**
     * This color constant "Pale Yellow Green" has RGBA8888 code {@code E3E4BBFF}, hue 0.17073172, saturation 0.1607843, lightness 0.8137255, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.77c9c6p126F}.
     * <pre>
     * <font style='background-color: #E3E4BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3E4BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3E4BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3E4BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3E4BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3E4BB'>&nbsp;@&nbsp;</font><font style='background-color: #E3E4BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3E4BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3E4BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_GREEN = -0x1.77c9c6p126F;
    static { NAMED.put("Pale Yellow Green", -0x1.77c9c6p126F); LIST.add(-0x1.77c9c6p126F); }

    /**
     * This color constant "Grayish Yellow Green" has RGBA8888 code {@code AAAD8DFF}, hue 0.18229167, saturation 0.12549019, lightness 0.6156863, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.1b5b54p126F}.
     * <pre>
     * <font style='background-color: #AAAD8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAAD8D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAAD8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAAD8D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAAD8D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAAD8D'>&nbsp;@&nbsp;</font><font style='background-color: #AAAD8D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAAD8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAAD8D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOW_GREEN = -0x1.1b5b54p126F;
    static { NAMED.put("Grayish Yellow Green", -0x1.1b5b54p126F); LIST.add(-0x1.1b5b54p126F); }

    /**
     * This color constant "Strong Olive Green" has RGBA8888 code {@code 336D00FF}, hue 0.2553517, saturation 0.42745098, lightness 0.21372549, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.00da66p125F}.
     * <pre>
     * <font style='background-color: #336D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #336D00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #336D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #336D00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #336D00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #336D00'>&nbsp;@&nbsp;</font><font style='background-color: #336D00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #336D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #336D00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_OLIVE_GREEN = -0x1.00da66p125F;
    static { NAMED.put("Strong Olive Green", -0x1.00da66p125F); LIST.add(-0x1.00da66p125F); }

    /**
     * This color constant "Moderate Olive Green" has RGBA8888 code {@code 57721AFF}, hue 0.21780303, saturation 0.34509805, lightness 0.2745098, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.34e4aep125F}.
     * <pre>
     * <font style='background-color: #57721A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57721A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57721A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57721A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57721A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57721A'>&nbsp;@&nbsp;</font><font style='background-color: #57721A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57721A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57721A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE_GREEN = -0x1.34e4aep125F;
    static { NAMED.put("Moderate Olive Green", -0x1.34e4aep125F); LIST.add(-0x1.34e4aep125F); }

    /**
     * This color constant "Dark Olive Green" has RGBA8888 code {@code 294705FF}, hue 0.24242425, saturation 0.25882354, lightness 0.14901961, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0a8e52p125F}.
     * <pre>
     * <font style='background-color: #294705;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #294705; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #294705;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #294705'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #294705'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #294705'>&nbsp;@&nbsp;</font><font style='background-color: #294705; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #294705;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #294705; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE_GREEN = -0x1.0a8e52p125F;
    static { NAMED.put("Dark Olive Green", -0x1.0a8e52p125F); LIST.add(-0x1.0a8e52p125F); }

    /**
     * This color constant "Grayish Olive Green" has RGBA8888 code {@code 627052FF}, hue 0.24444446, saturation 0.11764705, lightness 0.38039216, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a4e0c4p125F}.
     * <pre>
     * <font style='background-color: #627052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #627052; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #627052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #627052'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #627052'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #627052'>&nbsp;@&nbsp;</font><font style='background-color: #627052; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #627052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #627052; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_OLIVE_GREEN = -0x1.a4e0c4p125F;
    static { NAMED.put("Grayish Olive Green", -0x1.a4e0c4p125F); LIST.add(-0x1.a4e0c4p125F); }

    /**
     * This color constant "Dark Grayish Olive Green" has RGBA8888 code {@code 3E452CFF}, hue 0.21333337, saturation 0.098039225, lightness 0.22156863, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.588a7cp125F}.
     * <pre>
     * <font style='background-color: #3E452C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E452C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E452C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3E452C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3E452C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3E452C'>&nbsp;@&nbsp;</font><font style='background-color: #3E452C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E452C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E452C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_OLIVE_GREEN = -0x1.588a7cp125F;
    static { NAMED.put("Dark Grayish Olive Green", -0x1.588a7cp125F); LIST.add(-0x1.588a7cp125F); }

    /**
     * This color constant "Vivid Yellowish Green" has RGBA8888 code {@code 20ED30FF}, hue 0.34634146, saturation 0.8039216, lightness 0.527451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.61da4p125F}.
     * <pre>
     * <font style='background-color: #20ED30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20ED30; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20ED30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #20ED30'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #20ED30'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #20ED30'>&nbsp;@&nbsp;</font><font style='background-color: #20ED30; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20ED30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20ED30; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOWISH_GREEN = -0x1.61da4p125F;
    static { NAMED.put("Vivid Yellowish Green", -0x1.61da4p125F); LIST.add(-0x1.61da4p125F); }

    /**
     * This color constant "Brilliant Yellowish Green" has RGBA8888 code {@code 80ED83FF}, hue 0.3379205, saturation 0.42745095, lightness 0.71568626, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.07dbp126F}.
     * <pre>
     * <font style='background-color: #80ED83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80ED83; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80ED83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #80ED83'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #80ED83'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #80ED83'>&nbsp;@&nbsp;</font><font style='background-color: #80ED83; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80ED83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80ED83; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOWISH_GREEN = -0x1.07dbp126F;
    static { NAMED.put("Brilliant Yellowish Green", -0x1.07dbp126F); LIST.add(-0x1.07dbp126F); }

    /**
     * This color constant "Strong Yellowish Green" has RGBA8888 code {@code 20B14DFF}, hue 0.38505748, saturation 0.5686275, lightness 0.4098039, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9b624p125F}.
     * <pre>
     * <font style='background-color: #20B14D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20B14D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20B14D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #20B14D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #20B14D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #20B14D'>&nbsp;@&nbsp;</font><font style='background-color: #20B14D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20B14D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20B14D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_GREEN = -0x1.9b624p125F;
    static { NAMED.put("Strong Yellowish Green", -0x1.9b624p125F); LIST.add(-0x1.9b624p125F); }

    /**
     * This color constant "Deep Yellowish Green" has RGBA8888 code {@code 1A8313FF}, hue 0.3229167, saturation 0.43921572, lightness 0.29411766, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.270634p125F}.
     * <pre>
     * <font style='background-color: #1A8313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A8313; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A8313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1A8313'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1A8313'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1A8313'>&nbsp;@&nbsp;</font><font style='background-color: #1A8313; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A8313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A8313; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_GREEN = -0x1.270634p125F;
    static { NAMED.put("Deep Yellowish Green", -0x1.270634p125F); LIST.add(-0x1.270634p125F); }

    /**
     * This color constant "Very Deep Yellowish Green" has RGBA8888 code {@code 005807FF}, hue 0.3465909, saturation 0.34509805, lightness 0.17254902, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0ebp125F}.
     * <pre>
     * <font style='background-color: #005807;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #005807; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #005807;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #005807'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #005807'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #005807'>&nbsp;@&nbsp;</font><font style='background-color: #005807; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #005807;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #005807; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_YELLOWISH_GREEN = -0x1.0ebp125F;
    static { NAMED.put("Very Deep Yellowish Green", -0x1.0ebp125F); LIST.add(-0x1.0ebp125F); }

    /**
     * This color constant "Very Light Yellowish Green" has RGBA8888 code {@code D1F5CAFF}, hue 0.30620155, saturation 0.16862744, lightness 0.8764706, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.95eba2p126F}.
     * <pre>
     * <font style='background-color: #D1F5CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1F5CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1F5CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1F5CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1F5CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1F5CA'>&nbsp;@&nbsp;</font><font style='background-color: #D1F5CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1F5CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1F5CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_YELLOWISH_GREEN = -0x1.95eba2p126F;
    static { NAMED.put("Very Light Yellowish Green", -0x1.95eba2p126F); LIST.add(-0x1.95eba2p126F); }

    /**
     * This color constant "Light Yellowish Green" has RGBA8888 code {@code 99DC98FF}, hue 0.33088237, saturation 0.26666665, lightness 0.7294118, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.31b932p126F}.
     * <pre>
     * <font style='background-color: #99DC98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99DC98; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99DC98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #99DC98'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #99DC98'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #99DC98'>&nbsp;@&nbsp;</font><font style='background-color: #99DC98; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99DC98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99DC98; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_GREEN = -0x1.31b932p126F;
    static { NAMED.put("Light Yellowish Green", -0x1.31b932p126F); LIST.add(-0x1.31b932p126F); }

    /**
     * This color constant "Moderate Yellowish Green" has RGBA8888 code {@code 61AA70FF}, hue 0.3675799, saturation 0.28627452, lightness 0.5235294, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e154c2p125F}.
     * <pre>
     * <font style='background-color: #61AA70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61AA70; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61AA70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61AA70'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61AA70'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61AA70'>&nbsp;@&nbsp;</font><font style='background-color: #61AA70; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61AA70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61AA70; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_GREEN = -0x1.e154c2p125F;
    static { NAMED.put("Moderate Yellowish Green", -0x1.e154c2p125F); LIST.add(-0x1.e154c2p125F); }

    /**
     * This color constant "Dark Yellowish Green" has RGBA8888 code {@code 327643FF}, hue 0.37500003, saturation 0.26666665, lightness 0.32941177, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.86ec64p125F}.
     * <pre>
     * <font style='background-color: #327643;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #327643; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #327643;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #327643'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #327643'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #327643'>&nbsp;@&nbsp;</font><font style='background-color: #327643; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #327643;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #327643; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_GREEN = -0x1.86ec64p125F;
    static { NAMED.put("Dark Yellowish Green", -0x1.86ec64p125F); LIST.add(-0x1.86ec64p125F); }

    /**
     * This color constant "Very Dark Yellowish Green" has RGBA8888 code {@code 124A1FFF}, hue 0.37202382, saturation 0.21960786, lightness 0.18039216, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.3e9424p125F}.
     * <pre>
     * <font style='background-color: #124A1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #124A1F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #124A1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #124A1F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #124A1F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #124A1F'>&nbsp;@&nbsp;</font><font style='background-color: #124A1F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #124A1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #124A1F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_YELLOWISH_GREEN = -0x1.3e9424p125F;
    static { NAMED.put("Very Dark Yellowish Green", -0x1.3e9424p125F); LIST.add(-0x1.3e9424p125F); }

    /**
     * This color constant "Vivid Green" has RGBA8888 code {@code 00FCAAFF}, hue 0.44576722, saturation 0.9882353, lightness 0.49411765, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.55f8p126F}.
     * <pre>
     * <font style='background-color: #00FCAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FCAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FCAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FCAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FCAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FCAA'>&nbsp;@&nbsp;</font><font style='background-color: #00FCAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FCAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FCAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_GREEN = -0x1.55f8p126F;
    static { NAMED.put("Vivid Green", -0x1.55f8p126F); LIST.add(-0x1.55f8p126F); }

    /**
     * This color constant "Brilliant Green" has RGBA8888 code {@code 32E4B3FF}, hue 0.45411986, saturation 0.69803923, lightness 0.54509807, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.67c864p126F}.
     * <pre>
     * <font style='background-color: #32E4B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32E4B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32E4B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #32E4B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #32E4B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #32E4B3'>&nbsp;@&nbsp;</font><font style='background-color: #32E4B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32E4B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32E4B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREEN = -0x1.67c864p126F;
    static { NAMED.put("Brilliant Green", -0x1.67c864p126F); LIST.add(-0x1.67c864p126F); }

    /**
     * This color constant "Strong Green" has RGBA8888 code {@code 00A375FF}, hue 0.45296526, saturation 0.6392157, lightness 0.31960785, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.eb46p125F}.
     * <pre>
     * <font style='background-color: #00A375;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00A375; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00A375;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00A375'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00A375'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00A375'>&nbsp;@&nbsp;</font><font style='background-color: #00A375; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00A375;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00A375; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREEN = -0x1.eb46p125F;
    static { NAMED.put("Strong Green", -0x1.eb46p125F); LIST.add(-0x1.eb46p125F); }

    /**
     * This color constant "Very Light Green" has RGBA8888 code {@code AAEFD2FF}, hue 0.4299517, saturation 0.27058822, lightness 0.80196077, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a5df54p126F}.
     * <pre>
     * <font style='background-color: #AAEFD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAEFD2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAEFD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAEFD2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAEFD2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAEFD2'>&nbsp;@&nbsp;</font><font style='background-color: #AAEFD2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAEFD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAEFD2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_GREEN = -0x1.a5df54p126F;
    static { NAMED.put("Very Light Green", -0x1.a5df54p126F); LIST.add(-0x1.a5df54p126F); }

    /**
     * This color constant "Light Green" has RGBA8888 code {@code 6BC49FFF}, hue 0.43071163, saturation 0.34901962, lightness 0.59411764, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.3f88d6p126F}.
     * <pre>
     * <font style='background-color: #6BC49F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6BC49F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6BC49F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6BC49F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6BC49F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6BC49F'>&nbsp;@&nbsp;</font><font style='background-color: #6BC49F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6BC49F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6BC49F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREEN = -0x1.3f88d6p126F;
    static { NAMED.put("Light Green", -0x1.3f88d6p126F); LIST.add(-0x1.3f88d6p126F); }

    /**
     * This color constant "Moderate Green" has RGBA8888 code {@code 31906EFF}, hue 0.4403509, saturation 0.37254906, lightness 0.37843135, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.dd2062p125F}.
     * <pre>
     * <font style='background-color: #31906E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31906E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31906E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #31906E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #31906E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #31906E'>&nbsp;@&nbsp;</font><font style='background-color: #31906E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31906E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31906E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREEN = -0x1.dd2062p125F;
    static { NAMED.put("Moderate Green", -0x1.dd2062p125F); LIST.add(-0x1.dd2062p125F); }

    /**
     * This color constant "Dark Green" has RGBA8888 code {@code 0D6649FF}, hue 0.4456929, saturation 0.34901962, lightness 0.2254902, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.92cc1ap125F}.
     * <pre>
     * <font style='background-color: #0D6649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D6649; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D6649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0D6649'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0D6649'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0D6649'>&nbsp;@&nbsp;</font><font style='background-color: #0D6649; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D6649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D6649; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREEN = -0x1.92cc1ap125F;
    static { NAMED.put("Dark Green", -0x1.92cc1ap125F); LIST.add(-0x1.92cc1ap125F); }

    /**
     * This color constant "Very Dark Green" has RGBA8888 code {@code 0F4034FF}, hue 0.4591837, saturation 0.19215688, lightness 0.15490197, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.68801ep125F}.
     * <pre>
     * <font style='background-color: #0F4034;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F4034; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F4034;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F4034'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F4034'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F4034'>&nbsp;@&nbsp;</font><font style='background-color: #0F4034; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F4034;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F4034; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_GREEN = -0x1.68801ep125F;
    static { NAMED.put("Very Dark Green", -0x1.68801ep125F); LIST.add(-0x1.68801ep125F); }

    /**
     * This color constant "Very Pale Green" has RGBA8888 code {@code CEE6E0FF}, hue 0.45833334, saturation 0.09411764, lightness 0.85490197, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c1cd9cp126F}.
     * <pre>
     * <font style='background-color: #CEE6E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEE6E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEE6E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEE6E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEE6E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEE6E0'>&nbsp;@&nbsp;</font><font style='background-color: #CEE6E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEE6E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEE6E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_GREEN = -0x1.c1cd9cp126F;
    static { NAMED.put("Very Pale Green", -0x1.c1cd9cp126F); LIST.add(-0x1.c1cd9cp126F); }

    /**
     * This color constant "Pale Green" has RGBA8888 code {@code 9CBAB3FF}, hue 0.46111113, saturation 0.11764705, lightness 0.67058825, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.677538p126F}.
     * <pre>
     * <font style='background-color: #9CBAB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CBAB3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CBAB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9CBAB3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9CBAB3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9CBAB3'>&nbsp;@&nbsp;</font><font style='background-color: #9CBAB3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CBAB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CBAB3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.677538p126F;
    static { NAMED.put("Pale Green", -0x1.677538p126F); LIST.add(-0x1.677538p126F); }

    /**
     * This color constant "Grayish Green" has RGBA8888 code {@code 609A7AFF}, hue 0.40804598, saturation 0.227451, lightness 0.49019608, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f534cp125F}.
     * <pre>
     * <font style='background-color: #609A7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #609A7A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #609A7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #609A7A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #609A7A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #609A7A'>&nbsp;@&nbsp;</font><font style='background-color: #609A7A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #609A7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #609A7A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_GREEN = -0x1.f534cp125F;
    static { NAMED.put("Grayish Green", -0x1.f534cp125F); LIST.add(-0x1.f534cp125F); }

    /**
     * This color constant "Dark Grayish Green" has RGBA8888 code {@code 3C6C4FFF}, hue 0.39930558, saturation 0.1882353, lightness 0.32941177, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9ed878p125F}.
     * <pre>
     * <font style='background-color: #3C6C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C6C4F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C6C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C6C4F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C6C4F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C6C4F'>&nbsp;@&nbsp;</font><font style='background-color: #3C6C4F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C6C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C6C4F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_GREEN = -0x1.9ed878p125F;
    static { NAMED.put("Dark Grayish Green", -0x1.9ed878p125F); LIST.add(-0x1.9ed878p125F); }

    /**
     * This color constant "Blackish Green" has RGBA8888 code {@code 1F4A37FF}, hue 0.4263566, saturation 0.16862747, lightness 0.20588236, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.6e943ep125F}.
     * <pre>
     * <font style='background-color: #1F4A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F4A37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F4A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1F4A37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1F4A37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1F4A37'>&nbsp;@&nbsp;</font><font style='background-color: #1F4A37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F4A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F4A37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_GREEN = -0x1.6e943ep125F;
    static { NAMED.put("Blackish Green", -0x1.6e943ep125F); LIST.add(-0x1.6e943ep125F); }

    /**
     * This color constant "Greenish White" has RGBA8888 code {@code E0FBE6FF}, hue 0.3703704, saturation 0.10588235, lightness 0.9313726, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.cdf7cp126F}.
     * <pre>
     * <font style='background-color: #E0FBE6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0FBE6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0FBE6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0FBE6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0FBE6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0FBE6'>&nbsp;@&nbsp;</font><font style='background-color: #E0FBE6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0FBE6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0FBE6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_WHITE = -0x1.cdf7cp126F;
    static { NAMED.put("Greenish White", -0x1.cdf7cp126F); LIST.add(-0x1.cdf7cp126F); }

    /**
     * This color constant "Light Greenish Gray" has RGBA8888 code {@code C1DCCAFF}, hue 0.3888889, saturation 0.10588235, lightness 0.8098039, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.95b982p126F}.
     * <pre>
     * <font style='background-color: #C1DCCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1DCCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1DCCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1DCCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1DCCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1DCCA'>&nbsp;@&nbsp;</font><font style='background-color: #C1DCCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1DCCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1DCCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_GRAY = -0x1.95b982p126F;
    static { NAMED.put("Light Greenish Gray", -0x1.95b982p126F); LIST.add(-0x1.95b982p126F); }

    /**
     * This color constant "Greenish Gray" has RGBA8888 code {@code 87AD92FF}, hue 0.38157895, saturation 0.1490196, lightness 0.6039216, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.255b0ep126F}.
     * <pre>
     * <font style='background-color: #87AD92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87AD92; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87AD92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87AD92'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87AD92'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87AD92'>&nbsp;@&nbsp;</font><font style='background-color: #87AD92; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87AD92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87AD92; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_GRAY = -0x1.255b0ep126F;
    static { NAMED.put("Greenish Gray", -0x1.255b0ep126F); LIST.add(-0x1.255b0ep126F); }

    /**
     * This color constant "Dark Greenish Gray" has RGBA8888 code {@code 597F64FF}, hue 0.38157895, saturation 0.1490196, lightness 0.42352942, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c8feb2p125F}.
     * <pre>
     * <font style='background-color: #597F64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #597F64; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #597F64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #597F64'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #597F64'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #597F64'>&nbsp;@&nbsp;</font><font style='background-color: #597F64; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #597F64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #597F64; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_GRAY = -0x1.c8feb2p125F;
    static { NAMED.put("Dark Greenish Gray", -0x1.c8feb2p125F); LIST.add(-0x1.c8feb2p125F); }

    /**
     * This color constant "Vivid Bluish Green" has RGBA8888 code {@code 65FFDEFF}, hue 0.46428573, saturation 0.60392153, lightness 0.69803923, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.bdfecap126F}.
     * <pre>
     * <font style='background-color: #65FFDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65FFDE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65FFDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65FFDE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65FFDE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65FFDE'>&nbsp;@&nbsp;</font><font style='background-color: #65FFDE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65FFDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65FFDE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_BLUISH_GREEN = -0x1.bdfecap126F;
    static { NAMED.put("Vivid Bluish Green", -0x1.bdfecap126F); LIST.add(-0x1.bdfecap126F); }

    /**
     * This color constant "Brilliant Bluish Green" has RGBA8888 code {@code 1EE9DEFF}, hue 0.49096882, saturation 0.79607844, lightness 0.5156863, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.bdd23cp126F}.
     * <pre>
     * <font style='background-color: #1EE9DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1EE9DE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1EE9DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1EE9DE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1EE9DE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1EE9DE'>&nbsp;@&nbsp;</font><font style='background-color: #1EE9DE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1EE9DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1EE9DE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_BLUISH_GREEN = -0x1.bdd23cp126F;
    static { NAMED.put("Brilliant Bluish Green", -0x1.bdd23cp126F); LIST.add(-0x1.bdd23cp126F); }

    /**
     * This color constant "Strong Bluish Green" has RGBA8888 code {@code 00A796FF}, hue 0.48303396, saturation 0.654902, lightness 0.327451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.2d4ep126F}.
     * <pre>
     * <font style='background-color: #00A796;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00A796; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00A796;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00A796'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00A796'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00A796'>&nbsp;@&nbsp;</font><font style='background-color: #00A796; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00A796;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00A796; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BLUISH_GREEN = -0x1.2d4ep126F;
    static { NAMED.put("Strong Bluish Green", -0x1.2d4ep126F); LIST.add(-0x1.2d4ep126F); }

    /**
     * This color constant "Very Light Bluish Green" has RGBA8888 code {@code 8BF2F1FF}, hue 0.4983819, saturation 0.40392154, lightness 0.7470588, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e3e516p126F}.
     * <pre>
     * <font style='background-color: #8BF2F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BF2F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BF2F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8BF2F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8BF2F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8BF2F1'>&nbsp;@&nbsp;</font><font style='background-color: #8BF2F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BF2F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BF2F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_BLUISH_GREEN = -0x1.e3e516p126F;
    static { NAMED.put("Very Light Bluish Green", -0x1.e3e516p126F); LIST.add(-0x1.e3e516p126F); }

    /**
     * This color constant "Light Bluish Green" has RGBA8888 code {@code 5CC0C6FF}, hue 0.509434, saturation 0.41568628, lightness 0.5686275, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.8d80b8p126F}.
     * <pre>
     * <font style='background-color: #5CC0C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CC0C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CC0C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5CC0C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5CC0C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5CC0C6'>&nbsp;@&nbsp;</font><font style='background-color: #5CC0C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CC0C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CC0C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUISH_GREEN = -0x1.8d80b8p126F;
    static { NAMED.put("Light Bluish Green", -0x1.8d80b8p126F); LIST.add(-0x1.8d80b8p126F); }

    /**
     * This color constant "Moderate Bluish Green" has RGBA8888 code {@code 209296FF}, hue 0.50564975, saturation 0.46274513, lightness 0.35686278, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.2d244p126F}.
     * <pre>
     * <font style='background-color: #209296;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #209296; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #209296;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #209296'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #209296'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #209296'>&nbsp;@&nbsp;</font><font style='background-color: #209296; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #209296;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #209296; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BLUISH_GREEN = -0x1.2d244p126F;
    static { NAMED.put("Moderate Bluish Green", -0x1.2d244p126F); LIST.add(-0x1.2d244p126F); }

    /**
     * This color constant "Dark Bluish Green" has RGBA8888 code {@code 176063FF}, hue 0.506579, saturation 0.29803923, lightness 0.23921567, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c6c02ep125F}.
     * <pre>
     * <font style='background-color: #176063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #176063; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #176063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #176063'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #176063'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #176063'>&nbsp;@&nbsp;</font><font style='background-color: #176063; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #176063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #176063; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUISH_GREEN = -0x1.c6c02ep125F;
    static { NAMED.put("Dark Bluish Green", -0x1.c6c02ep125F); LIST.add(-0x1.c6c02ep125F); }

    /**
     * This color constant "Very Dark Bluish Green" has RGBA8888 code {@code 004043FF}, hue 0.5074627, saturation 0.2627451, lightness 0.13137256, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.868p125F}.
     * <pre>
     * <font style='background-color: #004043;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #004043; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #004043;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #004043'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #004043'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #004043'>&nbsp;@&nbsp;</font><font style='background-color: #004043; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #004043;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #004043; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_BLUISH_GREEN = -0x1.868p125F;
    static { NAMED.put("Very Dark Bluish Green", -0x1.868p125F); LIST.add(-0x1.868p125F); }

    /**
     * This color constant "Brilliant Greenish Blue" has RGBA8888 code {@code 00D1FCFF}, hue 0.52843916, saturation 0.9882353, lightness 0.49411765, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f9a2p126F}.
     * <pre>
     * <font style='background-color: #00D1FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00D1FC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00D1FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00D1FC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00D1FC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00D1FC'>&nbsp;@&nbsp;</font><font style='background-color: #00D1FC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00D1FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00D1FC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREENISH_BLUE = -0x1.f9a2p126F;
    static { NAMED.put("Brilliant Greenish Blue", -0x1.f9a2p126F); LIST.add(-0x1.f9a2p126F); }

    /**
     * This color constant "Strong Greenish Blue" has RGBA8888 code {@code 009BD2FF}, hue 0.5436508, saturation 0.8235294, lightness 0.4117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a536p126F}.
     * <pre>
     * <font style='background-color: #009BD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #009BD2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #009BD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #009BD2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #009BD2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #009BD2'>&nbsp;@&nbsp;</font><font style='background-color: #009BD2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #009BD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #009BD2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREENISH_BLUE = -0x1.a536p126F;
    static { NAMED.put("Strong Greenish Blue", -0x1.a536p126F); LIST.add(-0x1.a536p126F); }

    /**
     * This color constant "Very Light Greenish Blue" has RGBA8888 code {@code 9CE3FCFF}, hue 0.5434028, saturation 0.37647057, lightness 0.8, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f9c738p126F}.
     * <pre>
     * <font style='background-color: #9CE3FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CE3FC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CE3FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9CE3FC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9CE3FC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9CE3FC'>&nbsp;@&nbsp;</font><font style='background-color: #9CE3FC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CE3FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CE3FC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_GREENISH_BLUE = -0x1.f9c738p126F;
    static { NAMED.put("Very Light Greenish Blue", -0x1.f9c738p126F); LIST.add(-0x1.f9c738p126F); }

    /**
     * This color constant "Light Greenish Blue" has RGBA8888 code {@code 5EBDE3FF}, hue 0.5476191, saturation 0.52156866, lightness 0.62941176, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c77abcp126F}.
     * <pre>
     * <font style='background-color: #5EBDE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5EBDE3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5EBDE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5EBDE3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5EBDE3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5EBDE3'>&nbsp;@&nbsp;</font><font style='background-color: #5EBDE3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5EBDE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5EBDE3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_BLUE = -0x1.c77abcp126F;
    static { NAMED.put("Light Greenish Blue", -0x1.c77abcp126F); LIST.add(-0x1.c77abcp126F); }

    /**
     * This color constant "Moderate Greenish Blue" has RGBA8888 code {@code 1E8DB4FF}, hue 0.54333335, saturation 0.5882353, lightness 0.4117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.691a3cp126F}.
     * <pre>
     * <font style='background-color: #1E8DB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E8DB4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E8DB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1E8DB4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1E8DB4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1E8DB4'>&nbsp;@&nbsp;</font><font style='background-color: #1E8DB4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E8DB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E8DB4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREENISH_BLUE = -0x1.691a3cp126F;
    static { NAMED.put("Moderate Greenish Blue", -0x1.691a3cp126F); LIST.add(-0x1.691a3cp126F); }

    /**
     * This color constant "Dark Greenish Blue" has RGBA8888 code {@code 005E7FFF}, hue 0.5433071, saturation 0.49803922, lightness 0.24901961, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.febcp125F}.
     * <pre>
     * <font style='background-color: #005E7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #005E7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #005E7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #005E7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #005E7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #005E7F'>&nbsp;@&nbsp;</font><font style='background-color: #005E7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #005E7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #005E7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_BLUE = -0x1.febcp125F;
    static { NAMED.put("Dark Greenish Blue", -0x1.febcp125F); LIST.add(-0x1.febcp125F); }

    /**
     * This color constant "Very Dark Greenish Blue" has RGBA8888 code {@code 003D53FF}, hue 0.5441767, saturation 0.3254902, lightness 0.1627451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a67ap125F}.
     * <pre>
     * <font style='background-color: #003D53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #003D53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #003D53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #003D53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #003D53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #003D53'>&nbsp;@&nbsp;</font><font style='background-color: #003D53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #003D53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #003D53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_GREENISH_BLUE = -0x1.a67ap125F;
    static { NAMED.put("Very Dark Greenish Blue", -0x1.a67ap125F); LIST.add(-0x1.a67ap125F); }

    /**
     * This color constant "Vivid Blue" has RGBA8888 code {@code 3272EAFF}, hue 0.6086957, saturation 0.72156864, lightness 0.5568628, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.d4e464p126F}.
     * <pre>
     * <font style='background-color: #3272EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3272EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3272EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3272EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3272EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3272EA'>&nbsp;@&nbsp;</font><font style='background-color: #3272EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3272EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3272EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_BLUE = -0x1.d4e464p126F;
    static { NAMED.put("Vivid Blue", -0x1.d4e464p126F); LIST.add(-0x1.d4e464p126F); }

    /**
     * This color constant "Brilliant Blue" has RGBA8888 code {@code 63AFF7FF}, hue 0.5810811, saturation 0.5803921, lightness 0.6784314, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ef5ec6p126F}.
     * <pre>
     * <font style='background-color: #63AFF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63AFF7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63AFF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #63AFF7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #63AFF7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #63AFF7'>&nbsp;@&nbsp;</font><font style='background-color: #63AFF7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63AFF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63AFF7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_BLUE = -0x1.ef5ec6p126F;
    static { NAMED.put("Brilliant Blue", -0x1.ef5ec6p126F); LIST.add(-0x1.ef5ec6p126F); }

    /**
     * This color constant "Strong Blue" has RGBA8888 code {@code 2C7FE4FF}, hue 0.59148556, saturation 0.72156864, lightness 0.5333333, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c8fe58p126F}.
     * <pre>
     * <font style='background-color: #2C7FE4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2C7FE4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2C7FE4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2C7FE4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2C7FE4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2C7FE4'>&nbsp;@&nbsp;</font><font style='background-color: #2C7FE4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2C7FE4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2C7FE4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BLUE = -0x1.c8fe58p126F;
    static { NAMED.put("Strong Blue", -0x1.c8fe58p126F); LIST.add(-0x1.c8fe58p126F); }

    /**
     * This color constant "Deep Blue" has RGBA8888 code {@code 0D38B0FF}, hue 0.6226994, saturation 0.6392157, lightness 0.37058824, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.60701ap126F}.
     * <pre>
     * <font style='background-color: #0D38B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D38B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D38B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0D38B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0D38B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0D38B0'>&nbsp;@&nbsp;</font><font style='background-color: #0D38B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D38B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D38B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE = -0x1.60701ap126F;
    static { NAMED.put("Deep Blue", -0x1.60701ap126F); LIST.add(-0x1.60701ap126F); }

    /**
     * This color constant "Very Light Blue" has RGBA8888 code {@code A8D3FFFF}, hue 0.5842912, saturation 0.34117645, lightness 0.82941175, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ffa75p126F}.
     * <pre>
     * <font style='background-color: #A8D3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8D3FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8D3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8D3FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8D3FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8D3FF'>&nbsp;@&nbsp;</font><font style='background-color: #A8D3FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8D3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8D3FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_BLUE = -0x1.ffa75p126F;
    static { NAMED.put("Very Light Blue", -0x1.ffa75p126F); LIST.add(-0x1.ffa75p126F); }

    /**
     * This color constant "Light Blue" has RGBA8888 code {@code 84B7EBFF}, hue 0.5841424, saturation 0.40392154, lightness 0.71960783, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.d76f08p126F}.
     * <pre>
     * <font style='background-color: #84B7EB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #84B7EB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #84B7EB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #84B7EB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #84B7EB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #84B7EB'>&nbsp;@&nbsp;</font><font style='background-color: #84B7EB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #84B7EB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #84B7EB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUE = -0x1.d76f08p126F;
    static { NAMED.put("Light Blue", -0x1.d76f08p126F); LIST.add(-0x1.d76f08p126F); }

    /**
     * This color constant "Moderate Blue" has RGBA8888 code {@code 267EC9FF}, hue 0.57668716, saturation 0.6392157, lightness 0.46862745, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.92fc4cp126F}.
     * <pre>
     * <font style='background-color: #267EC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #267EC9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #267EC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #267EC9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #267EC9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #267EC9'>&nbsp;@&nbsp;</font><font style='background-color: #267EC9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #267EC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #267EC9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BLUE = -0x1.92fc4cp126F;
    static { NAMED.put("Moderate Blue", -0x1.92fc4cp126F); LIST.add(-0x1.92fc4cp126F); }

    /**
     * This color constant "Dark Blue" has RGBA8888 code {@code 16457FFF}, hue 0.5920635, saturation 0.4117647, lightness 0.29215685, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fe8a2cp125F}.
     * <pre>
     * <font style='background-color: #16457F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #16457F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #16457F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #16457F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #16457F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #16457F'>&nbsp;@&nbsp;</font><font style='background-color: #16457F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #16457F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #16457F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUE = -0x1.fe8a2cp125F;
    static { NAMED.put("Dark Blue", -0x1.fe8a2cp125F); LIST.add(-0x1.fe8a2cp125F); }

    /**
     * This color constant "Very Pale Blue" has RGBA8888 code {@code CBDEFBFF}, hue 0.6006945, saturation 0.18823528, lightness 0.8901961, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f7bd96p126F}.
     * <pre>
     * <font style='background-color: #CBDEFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBDEFB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBDEFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBDEFB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBDEFB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBDEFB'>&nbsp;@&nbsp;</font><font style='background-color: #CBDEFB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBDEFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBDEFB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_BLUE = -0x1.f7bd96p126F;
    static { NAMED.put("Very Pale Blue", -0x1.f7bd96p126F); LIST.add(-0x1.f7bd96p126F); }

    /**
     * This color constant "Pale Blue" has RGBA8888 code {@code 9FB2D3FF}, hue 0.6057693, saturation 0.20392156, lightness 0.7254902, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a7653ep126F}.
     * <pre>
     * <font style='background-color: #9FB2D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FB2D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FB2D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9FB2D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9FB2D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9FB2D3'>&nbsp;@&nbsp;</font><font style='background-color: #9FB2D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FB2D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FB2D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.a7653ep126F;
    static { NAMED.put("Pale Blue", -0x1.a7653ep126F); LIST.add(-0x1.a7653ep126F); }

    /**
     * This color constant "Grayish Blue" has RGBA8888 code {@code 5A7DA2FF}, hue 0.5856482, saturation 0.28235295, lightness 0.49411768, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.44fab4p126F}.
     * <pre>
     * <font style='background-color: #5A7DA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A7DA2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A7DA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A7DA2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A7DA2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A7DA2'>&nbsp;@&nbsp;</font><font style='background-color: #5A7DA2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A7DA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A7DA2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_BLUE = -0x1.44fab4p126F;
    static { NAMED.put("Grayish Blue", -0x1.44fab4p126F); LIST.add(-0x1.44fab4p126F); }

    /**
     * This color constant "Dark Grayish Blue" has RGBA8888 code {@code 3B516EFF}, hue 0.59477127, saturation 0.2, lightness 0.33137256, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.dca276p125F}.
     * <pre>
     * <font style='background-color: #3B516E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B516E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B516E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B516E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B516E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B516E'>&nbsp;@&nbsp;</font><font style='background-color: #3B516E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B516E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B516E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_BLUE = -0x1.dca276p125F;
    static { NAMED.put("Dark Grayish Blue", -0x1.dca276p125F); LIST.add(-0x1.dca276p125F); }

    /**
     * This color constant "Blackish Blue" has RGBA8888 code {@code 21354EFF}, hue 0.5925926, saturation 0.1764706, lightness 0.21764706, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9c6a42p125F}.
     * <pre>
     * <font style='background-color: #21354E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #21354E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #21354E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #21354E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #21354E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #21354E'>&nbsp;@&nbsp;</font><font style='background-color: #21354E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #21354E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #21354E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_BLUE = -0x1.9c6a42p125F;
    static { NAMED.put("Blackish Blue", -0x1.9c6a42p125F); LIST.add(-0x1.9c6a42p125F); }

    /**
     * This color constant "Bluish White" has RGBA8888 code {@code ECE9F8FF}, hue 0.70000005, saturation 0.058823526, lightness 0.9431373, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f1d3d8p126F}.
     * <pre>
     * <font style='background-color: #ECE9F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECE9F8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECE9F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECE9F8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECE9F8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECE9F8'>&nbsp;@&nbsp;</font><font style='background-color: #ECE9F8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECE9F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECE9F8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_WHITE = -0x1.f1d3d8p126F;
    static { NAMED.put("Bluish White", -0x1.f1d3d8p126F); LIST.add(-0x1.f1d3d8p126F); }

    /**
     * This color constant "Light Bluish Gray" has RGBA8888 code {@code C6C5D9FF}, hue 0.675, saturation 0.07843137, lightness 0.8117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.b38b8cp126F}.
     * <pre>
     * <font style='background-color: #C6C5D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6C5D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6C5D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6C5D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6C5D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6C5D9'>&nbsp;@&nbsp;</font><font style='background-color: #C6C5D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6C5D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6C5D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUISH_GRAY = -0x1.b38b8cp126F;
    static { NAMED.put("Light Bluish Gray", -0x1.b38b8cp126F); LIST.add(-0x1.b38b8cp126F); }

    /**
     * This color constant "Bluish Gray" has RGBA8888 code {@code 9499AAFF}, hue 0.6287879, saturation 0.086274505, lightness 0.62352943, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.553328p126F}.
     * <pre>
     * <font style='background-color: #9499AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9499AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9499AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9499AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9499AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9499AA'>&nbsp;@&nbsp;</font><font style='background-color: #9499AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9499AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9499AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_GRAY = -0x1.553328p126F;
    static { NAMED.put("Bluish Gray", -0x1.553328p126F); LIST.add(-0x1.553328p126F); }

    /**
     * This color constant "Dark Bluish Gray" has RGBA8888 code {@code 61687BFF}, hue 0.6217949, saturation 0.10196078, lightness 0.43137255, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f6d0c2p125F}.
     * <pre>
     * <font style='background-color: #61687B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61687B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61687B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61687B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61687B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61687B'>&nbsp;@&nbsp;</font><font style='background-color: #61687B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61687B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61687B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUISH_GRAY = -0x1.f6d0c2p125F;
    static { NAMED.put("Dark Bluish Gray", -0x1.f6d0c2p125F); LIST.add(-0x1.f6d0c2p125F); }

    /**
     * This color constant "Vivid Purplish Blue" has RGBA8888 code {@code 534DEDFF}, hue 0.6729167, saturation 0.62745094, lightness 0.6156863, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.da9aa6p126F}.
     * <pre>
     * <font style='background-color: #534DED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #534DED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #534DED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #534DED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #534DED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #534DED'>&nbsp;@&nbsp;</font><font style='background-color: #534DED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #534DED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #534DED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLISH_BLUE = -0x1.da9aa6p126F;
    static { NAMED.put("Vivid Purplish Blue", -0x1.da9aa6p126F); LIST.add(-0x1.da9aa6p126F); }

    /**
     * This color constant "Brilliant Purplish Blue" has RGBA8888 code {@code 909BF5FF}, hue 0.64851487, saturation 0.3960784, lightness 0.7627451, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.eb372p126F}.
     * <pre>
     * <font style='background-color: #909BF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #909BF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #909BF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #909BF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #909BF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #909BF5'>&nbsp;@&nbsp;</font><font style='background-color: #909BF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #909BF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #909BF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLISH_BLUE = -0x1.eb372p126F;
    static { NAMED.put("Brilliant Purplish Blue", -0x1.eb372p126F); LIST.add(-0x1.eb372p126F); }

    /**
     * This color constant "Strong Purplish Blue" has RGBA8888 code {@code 626DD0FF}, hue 0.65000004, saturation 0.43137255, lightness 0.6, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a0dac4p126F}.
     * <pre>
     * <font style='background-color: #626DD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626DD0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626DD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #626DD0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #626DD0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #626DD0'>&nbsp;@&nbsp;</font><font style='background-color: #626DD0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626DD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626DD0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_BLUE = -0x1.a0dac4p126F;
    static { NAMED.put("Strong Purplish Blue", -0x1.a0dac4p126F); LIST.add(-0x1.a0dac4p126F); }

    /**
     * This color constant "Deep Purplish Blue" has RGBA8888 code {@code 352FA4FF}, hue 0.6752137, saturation 0.45882356, lightness 0.4137255, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.485e6ap126F}.
     * <pre>
     * <font style='background-color: #352FA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #352FA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #352FA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #352FA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #352FA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #352FA4'>&nbsp;@&nbsp;</font><font style='background-color: #352FA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #352FA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #352FA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_BLUE = -0x1.485e6ap126F;
    static { NAMED.put("Deep Purplish Blue", -0x1.485e6ap126F); LIST.add(-0x1.485e6ap126F); }

    /**
     * This color constant "Very Light Purplish Blue" has RGBA8888 code {@code C4CDFFFF}, hue 0.641243, saturation 0.23137254, lightness 0.8843137, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ff9b88p126F}.
     * <pre>
     * <font style='background-color: #C4CDFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4CDFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4CDFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4CDFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4CDFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4CDFF'>&nbsp;@&nbsp;</font><font style='background-color: #C4CDFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4CDFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4CDFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_PURPLISH_BLUE = -0x1.ff9b88p126F;
    static { NAMED.put("Very Light Purplish Blue", -0x1.ff9b88p126F); LIST.add(-0x1.ff9b88p126F); }

    /**
     * This color constant "Light Purplish Blue" has RGBA8888 code {@code 98A4DFFF}, hue 0.63849765, saturation 0.27843136, lightness 0.73529416, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.bf493p126F}.
     * <pre>
     * <font style='background-color: #98A4DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98A4DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98A4DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98A4DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98A4DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98A4DF'>&nbsp;@&nbsp;</font><font style='background-color: #98A4DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98A4DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98A4DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_BLUE = -0x1.bf493p126F;
    static { NAMED.put("Light Purplish Blue", -0x1.bf493p126F); LIST.add(-0x1.bf493p126F); }

    /**
     * This color constant "Moderate Purplish Blue" has RGBA8888 code {@code 5960AFFF}, hue 0.6531008, saturation 0.3372549, lightness 0.5176471, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5ec0b2p126F}.
     * <pre>
     * <font style='background-color: #5960AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5960AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5960AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5960AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5960AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5960AF'>&nbsp;@&nbsp;</font><font style='background-color: #5960AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5960AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5960AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_BLUE = -0x1.5ec0b2p126F;
    static { NAMED.put("Moderate Purplish Blue", -0x1.5ec0b2p126F); LIST.add(-0x1.5ec0b2p126F); }

    /**
     * This color constant "Dark Purplish Blue" has RGBA8888 code {@code 2F2A73FF}, hue 0.6780822, saturation 0.2862745, lightness 0.30784315, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e6545ep125F}.
     * <pre>
     * <font style='background-color: #2F2A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F2A73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F2A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F2A73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F2A73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F2A73'>&nbsp;@&nbsp;</font><font style='background-color: #2F2A73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F2A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F2A73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_BLUE = -0x1.e6545ep125F;
    static { NAMED.put("Dark Purplish Blue", -0x1.e6545ep125F); LIST.add(-0x1.e6545ep125F); }

    /**
     * This color constant "Very Pale Purplish Blue" has RGBA8888 code {@code D1D6FDFF}, hue 0.6477273, saturation 0.17254901, lightness 0.90588236, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fbada2p126F}.
     * <pre>
     * <font style='background-color: #D1D6FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1D6FD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1D6FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1D6FD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1D6FD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1D6FD'>&nbsp;@&nbsp;</font><font style='background-color: #D1D6FD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1D6FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1D6FD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_PURPLISH_BLUE = -0x1.fbada2p126F;
    static { NAMED.put("Very Pale Purplish Blue", -0x1.fbada2p126F); LIST.add(-0x1.fbada2p126F); }

    /**
     * This color constant "Pale Purplish Blue" has RGBA8888 code {@code 9EA5CEFF}, hue 0.6423611, saturation 0.18823528, lightness 0.7137255, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9d4b3cp126F}.
     * <pre>
     * <font style='background-color: #9EA5CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9EA5CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9EA5CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9EA5CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9EA5CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9EA5CE'>&nbsp;@&nbsp;</font><font style='background-color: #9EA5CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9EA5CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9EA5CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLISH_BLUE = -0x1.9d4b3cp126F;
    static { NAMED.put("Pale Purplish Blue", -0x1.9d4b3cp126F); LIST.add(-0x1.9d4b3cp126F); }

    /**
     * This color constant "Grayish Purplish Blue" has RGBA8888 code {@code 545F9BFF}, hue 0.6408451, saturation 0.2784314, lightness 0.46862745, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.36bea8p126F}.
     * <pre>
     * <font style='background-color: #545F9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #545F9B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #545F9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #545F9B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #545F9B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #545F9B'>&nbsp;@&nbsp;</font><font style='background-color: #545F9B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #545F9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #545F9B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_BLUE = -0x1.36bea8p126F;
    static { NAMED.put("Grayish Purplish Blue", -0x1.36bea8p126F); LIST.add(-0x1.36bea8p126F); }

    /**
     * This color constant "Vivid Violet" has RGBA8888 code {@code 9240F8FF}, hue 0.74094206, saturation 0.7215686, lightness 0.6117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f08124p126F}.
     * <pre>
     * <font style='background-color: #9240F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9240F8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9240F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9240F8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9240F8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9240F8'>&nbsp;@&nbsp;</font><font style='background-color: #9240F8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9240F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9240F8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_VIOLET = -0x1.f08124p126F;
    static { NAMED.put("Vivid Violet", -0x1.f08124p126F); LIST.add(-0x1.f08124p126F); }

    /**
     * This color constant "Brilliant Violet" has RGBA8888 code {@code AB91EEFF}, hue 0.71326166, saturation 0.36470586, lightness 0.75098044, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.dd2356p126F}.
     * <pre>
     * <font style='background-color: #AB91EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB91EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB91EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB91EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB91EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB91EE'>&nbsp;@&nbsp;</font><font style='background-color: #AB91EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB91EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB91EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_VIOLET = -0x1.dd2356p126F;
    static { NAMED.put("Brilliant Violet", -0x1.dd2356p126F); LIST.add(-0x1.dd2356p126F); }

    /**
     * This color constant "Strong Violet" has RGBA8888 code {@code 7C47D0FF}, hue 0.7311436, saturation 0.5372549, lightness 0.5470588, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a08ef8p126F}.
     * <pre>
     * <font style='background-color: #7C47D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C47D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C47D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C47D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C47D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C47D0'>&nbsp;@&nbsp;</font><font style='background-color: #7C47D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C47D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C47D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_VIOLET = -0x1.a08ef8p126F;
    static { NAMED.put("Strong Violet", -0x1.a08ef8p126F); LIST.add(-0x1.a08ef8p126F); }

    /**
     * This color constant "Deep Violet" has RGBA8888 code {@code 570D97FF}, hue 0.75603867, saturation 0.5411765, lightness 0.32156864, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.2e1aaep126F}.
     * <pre>
     * <font style='background-color: #570D97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #570D97; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #570D97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #570D97'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #570D97'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #570D97'>&nbsp;@&nbsp;</font><font style='background-color: #570D97; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #570D97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #570D97; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET = -0x1.2e1aaep126F;
    static { NAMED.put("Deep Violet", -0x1.2e1aaep126F); LIST.add(-0x1.2e1aaep126F); }

    /**
     * This color constant "Very Light Violet" has RGBA8888 code {@code D6C8FFFF}, hue 0.70909095, saturation 0.21568626, lightness 0.89215684, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ff91acp126F}.
     * <pre>
     * <font style='background-color: #D6C8FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6C8FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6C8FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6C8FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6C8FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6C8FF'>&nbsp;@&nbsp;</font><font style='background-color: #D6C8FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6C8FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6C8FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_VIOLET = -0x1.ff91acp126F;
    static { NAMED.put("Very Light Violet", -0x1.ff91acp126F); LIST.add(-0x1.ff91acp126F); }

    /**
     * This color constant "Light Violet" has RGBA8888 code {@code AE9DDFFF}, hue 0.709596, saturation 0.2588235, lightness 0.74509805, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.bf3b5cp126F}.
     * <pre>
     * <font style='background-color: #AE9DDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE9DDF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE9DDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE9DDF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE9DDF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE9DDF'>&nbsp;@&nbsp;</font><font style='background-color: #AE9DDF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE9DDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE9DDF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_VIOLET = -0x1.bf3b5cp126F;
    static { NAMED.put("Light Violet", -0x1.bf3b5cp126F); LIST.add(-0x1.bf3b5cp126F); }

    /**
     * This color constant "Moderate Violet" has RGBA8888 code {@code 7552B8FF}, hue 0.7238562, saturation 0.4, lightness 0.52156866, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.70a4eap126F}.
     * <pre>
     * <font style='background-color: #7552B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7552B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7552B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7552B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7552B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7552B8'>&nbsp;@&nbsp;</font><font style='background-color: #7552B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7552B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7552B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_VIOLET = -0x1.70a4eap126F;
    static { NAMED.put("Moderate Violet", -0x1.70a4eap126F); LIST.add(-0x1.70a4eap126F); }

    /**
     * This color constant "Dark Violet" has RGBA8888 code {@code 482B74FF}, hue 0.7328767, saturation 0.2862745, lightness 0.3117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e8569p125F}.
     * <pre>
     * <font style='background-color: #482B74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #482B74; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #482B74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #482B74'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #482B74'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #482B74'>&nbsp;@&nbsp;</font><font style='background-color: #482B74; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #482B74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #482B74; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_VIOLET = -0x1.e8569p125F;
    static { NAMED.put("Dark Violet", -0x1.e8569p125F); LIST.add(-0x1.e8569p125F); }

    /**
     * This color constant "Very Pale Violet" has RGBA8888 code {@code DED1F6FF}, hue 0.72522527, saturation 0.14509803, lightness 0.8921569, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.eda3bcp126F}.
     * <pre>
     * <font style='background-color: #DED1F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DED1F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DED1F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DED1F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DED1F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DED1F6'>&nbsp;@&nbsp;</font><font style='background-color: #DED1F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DED1F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DED1F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_VIOLET = -0x1.eda3bcp126F;
    static { NAMED.put("Very Pale Violet", -0x1.eda3bcp126F); LIST.add(-0x1.eda3bcp126F); }

    /**
     * This color constant "Pale Violet" has RGBA8888 code {@code AFA0CFFF}, hue 0.71985817, saturation 0.18431371, lightness 0.71960783, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9f415ep126F}.
     * <pre>
     * <font style='background-color: #AFA0CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFA0CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFA0CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFA0CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFA0CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFA0CF'>&nbsp;@&nbsp;</font><font style='background-color: #AFA0CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFA0CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFA0CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.9f415ep126F;
    static { NAMED.put("Pale Violet", -0x1.9f415ep126F); LIST.add(-0x1.9f415ep126F); }

    /**
     * This color constant "Grayish Violet" has RGBA8888 code {@code 7359A0FF}, hue 0.7276995, saturation 0.2784314, lightness 0.4882353, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.40b2e6p126F}.
     * <pre>
     * <font style='background-color: #7359A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7359A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7359A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7359A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7359A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7359A0'>&nbsp;@&nbsp;</font><font style='background-color: #7359A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7359A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7359A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_VIOLET = -0x1.40b2e6p126F;
    static { NAMED.put("Grayish Violet", -0x1.40b2e6p126F); LIST.add(-0x1.40b2e6p126F); }

    /**
     * This color constant "Vivid Purple" has RGBA8888 code {@code DA32FDFF}, hue 0.80459774, saturation 0.79607844, lightness 0.59411764, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.fa65b4p126F}.
     * <pre>
     * <font style='background-color: #DA32FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA32FD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA32FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA32FD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA32FD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA32FD'>&nbsp;@&nbsp;</font><font style='background-color: #DA32FD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA32FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA32FD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLE = -0x1.fa65b4p126F;
    static { NAMED.put("Vivid Purple", -0x1.fa65b4p126F); LIST.add(-0x1.fa65b4p126F); }

    /**
     * This color constant "Brilliant Purple" has RGBA8888 code {@code DB9FEFFF}, hue 0.7916667, saturation 0.31372547, lightness 0.78039217, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.df3fb6p126F}.
     * <pre>
     * <font style='background-color: #DB9FEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB9FEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB9FEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB9FEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB9FEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB9FEF'>&nbsp;@&nbsp;</font><font style='background-color: #DB9FEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB9FEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB9FEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLE = -0x1.df3fb6p126F;
    static { NAMED.put("Brilliant Purple", -0x1.df3fb6p126F); LIST.add(-0x1.df3fb6p126F); }

    /**
     * This color constant "Strong Purple" has RGBA8888 code {@code BB51D6FF}, hue 0.7994988, saturation 0.52156866, lightness 0.57843137, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.aca376p126F}.
     * <pre>
     * <font style='background-color: #BB51D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB51D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB51D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB51D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB51D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB51D6'>&nbsp;@&nbsp;</font><font style='background-color: #BB51D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB51D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB51D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLE = -0x1.aca376p126F;
    static { NAMED.put("Strong Purple", -0x1.aca376p126F); LIST.add(-0x1.aca376p126F); }

    /**
     * This color constant "Deep Purple" has RGBA8888 code {@code 86209EFF}, hue 0.80158734, saturation 0.49411768, lightness 0.372549, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.3c410cp126F}.
     * <pre>
     * <font style='background-color: #86209E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86209E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86209E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #86209E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #86209E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #86209E'>&nbsp;@&nbsp;</font><font style='background-color: #86209E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86209E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86209E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE = -0x1.3c410cp126F;
    static { NAMED.put("Deep Purple", -0x1.3c410cp126F); LIST.add(-0x1.3c410cp126F); }

    /**
     * This color constant "Very Deep Purple" has RGBA8888 code {@code 620079FF}, hue 0.8016529, saturation 0.4745098, lightness 0.2372549, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f200c4p125F}.
     * <pre>
     * <font style='background-color: #620079;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #620079; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #620079;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #620079'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #620079'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #620079'>&nbsp;@&nbsp;</font><font style='background-color: #620079; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #620079;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #620079; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_PURPLE = -0x1.f200c4p125F;
    static { NAMED.put("Very Deep Purple", -0x1.f200c4p125F); LIST.add(-0x1.f200c4p125F); }

    /**
     * This color constant "Very Light Purple" has RGBA8888 code {@code F0C7FAFF}, hue 0.80065364, saturation 0.19999999, lightness 0.88039213, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f58fep126F}.
     * <pre>
     * <font style='background-color: #F0C7FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0C7FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0C7FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0C7FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0C7FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0C7FA'>&nbsp;@&nbsp;</font><font style='background-color: #F0C7FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0C7FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0C7FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_PURPLE = -0x1.f58fep126F;
    static { NAMED.put("Very Light Purple", -0x1.f58fep126F); LIST.add(-0x1.f58fep126F); }

    /**
     * This color constant "Light Purple" has RGBA8888 code {@code CCA2E0FF}, hue 0.7795699, saturation 0.24313724, lightness 0.75686276, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c14598p126F}.
     * <pre>
     * <font style='background-color: #CCA2E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCA2E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCA2E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CCA2E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CCA2E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CCA2E0'>&nbsp;@&nbsp;</font><font style='background-color: #CCA2E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCA2E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCA2E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLE = -0x1.c14598p126F;
    static { NAMED.put("Light Purple", -0x1.c14598p126F); LIST.add(-0x1.c14598p126F); }

    /**
     * This color constant "Moderate Purple" has RGBA8888 code {@code AC64BEFF}, hue 0.8, saturation 0.3529412, lightness 0.5686275, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.7cc958p126F}.
     * <pre>
     * <font style='background-color: #AC64BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC64BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC64BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC64BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC64BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC64BE'>&nbsp;@&nbsp;</font><font style='background-color: #AC64BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC64BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC64BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLE = -0x1.7cc958p126F;
    static { NAMED.put("Moderate Purple", -0x1.7cc958p126F); LIST.add(-0x1.7cc958p126F); }

    /**
     * This color constant "Dark Purple" has RGBA8888 code {@code 743E86FF}, hue 0.7916667, saturation 0.28235298, lightness 0.38431373, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0c7ce8p126F}.
     * <pre>
     * <font style='background-color: #743E86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #743E86; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #743E86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #743E86'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #743E86'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #743E86'>&nbsp;@&nbsp;</font><font style='background-color: #743E86; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #743E86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #743E86; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLE = -0x1.0c7ce8p126F;
    static { NAMED.put("Dark Purple", -0x1.0c7ce8p126F); LIST.add(-0x1.0c7ce8p126F); }

    /**
     * This color constant "Very Dark Purple" has RGBA8888 code {@code 4F1E63FF}, hue 0.78502417, saturation 0.27058825, lightness 0.25294116, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c63c9ep125F}.
     * <pre>
     * <font style='background-color: #4F1E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F1E63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F1E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F1E63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F1E63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F1E63'>&nbsp;@&nbsp;</font><font style='background-color: #4F1E63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F1E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F1E63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_PURPLE = -0x1.c63c9ep125F;
    static { NAMED.put("Very Dark Purple", -0x1.c63c9ep125F); LIST.add(-0x1.c63c9ep125F); }

    /**
     * This color constant "Very Pale Purple" has RGBA8888 code {@code ECD6F7FF}, hue 0.7777778, saturation 0.12941176, lightness 0.9039216, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.efadd8p126F}.
     * <pre>
     * <font style='background-color: #ECD6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECD6F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECD6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECD6F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECD6F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECD6F7'>&nbsp;@&nbsp;</font><font style='background-color: #ECD6F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECD6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECD6F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_PURPLE = -0x1.efadd8p126F;
    static { NAMED.put("Very Pale Purple", -0x1.efadd8p126F); LIST.add(-0x1.efadd8p126F); }

    /**
     * This color constant "Pale Purple" has RGBA8888 code {@code C0A6CCFF}, hue 0.78070176, saturation 0.1490196, lightness 0.7254902, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.994d8p126F}.
     * <pre>
     * <font style='background-color: #C0A6CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0A6CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0A6CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0A6CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0A6CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0A6CC'>&nbsp;@&nbsp;</font><font style='background-color: #C0A6CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0A6CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0A6CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.994d8p126F;
    static { NAMED.put("Pale Purple", -0x1.994d8p126F); LIST.add(-0x1.994d8p126F); }

    /**
     * This color constant "Grayish Purple" has RGBA8888 code {@code 9B729FFF}, hue 0.8185185, saturation 0.17647061, lightness 0.5352942, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.3ee536p126F}.
     * <pre>
     * <font style='background-color: #9B729F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B729F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B729F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B729F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B729F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B729F'>&nbsp;@&nbsp;</font><font style='background-color: #9B729F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B729F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B729F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLE = -0x1.3ee536p126F;
    static { NAMED.put("Grayish Purple", -0x1.3ee536p126F); LIST.add(-0x1.3ee536p126F); }

    /**
     * This color constant "Dark Grayish Purple" has RGBA8888 code {@code 6B4C73FF}, hue 0.79914534, saturation 0.15294117, lightness 0.3745098, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e698d6p125F}.
     * <pre>
     * <font style='background-color: #6B4C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B4C73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B4C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6B4C73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6B4C73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6B4C73'>&nbsp;@&nbsp;</font><font style='background-color: #6B4C73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B4C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B4C73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_PURPLE = -0x1.e698d6p125F;
    static { NAMED.put("Dark Grayish Purple", -0x1.e698d6p125F); LIST.add(-0x1.e698d6p125F); }

    /**
     * This color constant "Blackish Purple" has RGBA8888 code {@code 462B48FF}, hue 0.8218391, saturation 0.1137255, lightness 0.2254902, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.90568cp125F}.
     * <pre>
     * <font style='background-color: #462B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #462B48; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #462B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #462B48'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #462B48'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #462B48'>&nbsp;@&nbsp;</font><font style='background-color: #462B48; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #462B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #462B48; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_PURPLE = -0x1.90568cp125F;
    static { NAMED.put("Blackish Purple", -0x1.90568cp125F); LIST.add(-0x1.90568cp125F); }

    /**
     * This color constant "Purplish White" has RGBA8888 code {@code F2E7F7FF}, hue 0.78125, saturation 0.062745094, lightness 0.9372549, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.efcfe4p126F}.
     * <pre>
     * <font style='background-color: #F2E7F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2E7F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2E7F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2E7F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2E7F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2E7F7'>&nbsp;@&nbsp;</font><font style='background-color: #F2E7F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2E7F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2E7F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_WHITE = -0x1.efcfe4p126F;
    static { NAMED.put("Purplish White", -0x1.efcfe4p126F); LIST.add(-0x1.efcfe4p126F); }

    /**
     * This color constant "Light Purplish Gray" has RGBA8888 code {@code D3C3DBFF}, hue 0.7777778, saturation 0.09411764, lightness 0.8117647, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.b787a6p126F}.
     * <pre>
     * <font style='background-color: #D3C3DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3C3DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3C3DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3C3DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3C3DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3C3DB'>&nbsp;@&nbsp;</font><font style='background-color: #D3C3DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3C3DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3C3DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_GRAY = -0x1.b787a6p126F;
    static { NAMED.put("Light Purplish Gray", -0x1.b787a6p126F); LIST.add(-0x1.b787a6p126F); }

    /**
     * This color constant "Purplish Gray" has RGBA8888 code {@code A395AAFF}, hue 0.7777778, saturation 0.082352936, lightness 0.6254902, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.552b46p126F}.
     * <pre>
     * <font style='background-color: #A395AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A395AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A395AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A395AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A395AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A395AA'>&nbsp;@&nbsp;</font><font style='background-color: #A395AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A395AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A395AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_GRAY = -0x1.552b46p126F;
    static { NAMED.put("Purplish Gray", -0x1.552b46p126F); LIST.add(-0x1.552b46p126F); }

    /**
     * This color constant "Dark Purplish Gray" has RGBA8888 code {@code 73627CFF}, hue 0.775641, saturation 0.10196078, lightness 0.43529412, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.f8c4e6p125F}.
     * <pre>
     * <font style='background-color: #73627C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73627C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73627C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73627C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73627C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73627C'>&nbsp;@&nbsp;</font><font style='background-color: #73627C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73627C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73627C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_GRAY = -0x1.f8c4e6p125F;
    static { NAMED.put("Dark Purplish Gray", -0x1.f8c4e6p125F); LIST.add(-0x1.f8c4e6p125F); }

    /**
     * This color constant "Vivid Reddish Purple" has RGBA8888 code {@code FD1BD7FF}, hue 0.8613569, saturation 0.8862745, lightness 0.54901963, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ae37fap126F}.
     * <pre>
     * <font style='background-color: #FD1BD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD1BD7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD1BD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD1BD7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD1BD7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD1BD7'>&nbsp;@&nbsp;</font><font style='background-color: #FD1BD7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD1BD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD1BD7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_REDDISH_PURPLE = -0x1.ae37fap126F;
    static { NAMED.put("Vivid Reddish Purple", -0x1.ae37fap126F); LIST.add(-0x1.ae37fap126F); }

    /**
     * This color constant "Strong Reddish Purple" has RGBA8888 code {@code D147B7FF}, hue 0.8647343, saturation 0.54117644, lightness 0.54901963, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.6e8fa2p126F}.
     * <pre>
     * <font style='background-color: #D147B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D147B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D147B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D147B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D147B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D147B7'>&nbsp;@&nbsp;</font><font style='background-color: #D147B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D147B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D147B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_PURPLE = -0x1.6e8fa2p126F;
    static { NAMED.put("Strong Reddish Purple", -0x1.6e8fa2p126F); LIST.add(-0x1.6e8fa2p126F); }

    /**
     * This color constant "Deep Reddish Purple" has RGBA8888 code {@code 9B1786FF}, hue 0.8598485, saturation 0.5176471, lightness 0.34901962, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.0c2f36p126F}.
     * <pre>
     * <font style='background-color: #9B1786;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B1786; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B1786;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B1786'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B1786'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B1786'>&nbsp;@&nbsp;</font><font style='background-color: #9B1786; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B1786;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B1786; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_PURPLE = -0x1.0c2f36p126F;
    static { NAMED.put("Deep Reddish Purple", -0x1.0c2f36p126F); LIST.add(-0x1.0c2f36p126F); }

    /**
     * This color constant "Very Deep Reddish Purple" has RGBA8888 code {@code 6C0066FF}, hue 0.8425926, saturation 0.42352942, lightness 0.21176471, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.cc00d8p125F}.
     * <pre>
     * <font style='background-color: #6C0066;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C0066; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C0066;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C0066'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C0066'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C0066'>&nbsp;@&nbsp;</font><font style='background-color: #6C0066; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C0066;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C0066; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_REDDISH_PURPLE = -0x1.cc00d8p125F;
    static { NAMED.put("Very Deep Reddish Purple", -0x1.cc00d8p125F); LIST.add(-0x1.cc00d8p125F); }

    /**
     * This color constant "Light Reddish Purple" has RGBA8888 code {@code D78EC2FF}, hue 0.8812785, saturation 0.2862745, lightness 0.7, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.851daep126F}.
     * <pre>
     * <font style='background-color: #D78EC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D78EC2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D78EC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D78EC2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D78EC2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D78EC2'>&nbsp;@&nbsp;</font><font style='background-color: #D78EC2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D78EC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D78EC2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_REDDISH_PURPLE = -0x1.851daep126F;
    static { NAMED.put("Light Reddish Purple", -0x1.851daep126F); LIST.add(-0x1.851daep126F); }

    /**
     * This color constant "Moderate Reddish Purple" has RGBA8888 code {@code BF5BAEFF}, hue 0.8616667, saturation 0.39215687, lightness 0.55294114, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.5cb77ep126F}.
     * <pre>
     * <font style='background-color: #BF5BAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF5BAE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF5BAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF5BAE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF5BAE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF5BAE'>&nbsp;@&nbsp;</font><font style='background-color: #BF5BAE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF5BAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF5BAE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_PURPLE = -0x1.5cb77ep126F;
    static { NAMED.put("Moderate Reddish Purple", -0x1.5cb77ep126F); LIST.add(-0x1.5cb77ep126F); }

    /**
     * This color constant "Dark Reddish Purple" has RGBA8888 code {@code 823973FF}, hue 0.86757994, saturation 0.28627455, lightness 0.36666667, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e67304p125F}.
     * <pre>
     * <font style='background-color: #823973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #823973; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #823973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #823973'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #823973'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #823973'>&nbsp;@&nbsp;</font><font style='background-color: #823973; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #823973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #823973; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_PURPLE = -0x1.e67304p125F;
    static { NAMED.put("Dark Reddish Purple", -0x1.e67304p125F); LIST.add(-0x1.e67304p125F); }

    /**
     * This color constant "Very Dark Reddish Purple" has RGBA8888 code {@code 581C58FF}, hue 0.8333334, saturation 0.23529413, lightness 0.22745098, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.b038bp125F}.
     * <pre>
     * <font style='background-color: #581C58;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #581C58; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #581C58;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #581C58'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #581C58'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #581C58'>&nbsp;@&nbsp;</font><font style='background-color: #581C58; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #581C58;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #581C58; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_REDDISH_PURPLE = -0x1.b038bp125F;
    static { NAMED.put("Very Dark Reddish Purple", -0x1.b038bp125F); LIST.add(-0x1.b038bp125F); }

    /**
     * This color constant "Pale Reddish Purple" has RGBA8888 code {@code C696BCFF}, hue 0.8680556, saturation 0.18823528, lightness 0.68235296, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.792d8cp126F}.
     * <pre>
     * <font style='background-color: #C696BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C696BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C696BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C696BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C696BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C696BC'>&nbsp;@&nbsp;</font><font style='background-color: #C696BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C696BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C696BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_REDDISH_PURPLE = -0x1.792d8cp126F;
    static { NAMED.put("Pale Reddish Purple", -0x1.792d8cp126F); LIST.add(-0x1.792d8cp126F); }

    /**
     * This color constant "Grayish Reddish Purple" has RGBA8888 code {@code AD6A9AFF}, hue 0.880597, saturation 0.2627451, lightness 0.5470588, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.34d55ap126F}.
     * <pre>
     * <font style='background-color: #AD6A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD6A9A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD6A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD6A9A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD6A9A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD6A9A'>&nbsp;@&nbsp;</font><font style='background-color: #AD6A9A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD6A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD6A9A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_PURPLE = -0x1.34d55ap126F;
    static { NAMED.put("Grayish Reddish Purple", -0x1.34d55ap126F); LIST.add(-0x1.34d55ap126F); }

    /**
     * This color constant "Brilliant Purplish Pink" has RGBA8888 code {@code FFB4F1FF}, hue 0.86444443, saturation 0.29411763, lightness 0.85294116, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e369fep126F}.
     * <pre>
     * <font style='background-color: #FFB4F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFB4F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFB4F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFB4F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFB4F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFB4F1'>&nbsp;@&nbsp;</font><font style='background-color: #FFB4F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFB4F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFB4F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLISH_PINK = -0x1.e369fep126F;
    static { NAMED.put("Brilliant Purplish Pink", -0x1.e369fep126F); LIST.add(-0x1.e369fep126F); }

    /**
     * This color constant "Strong Purplish Pink" has RGBA8888 code {@code FB9AD7FF}, hue 0.895189, saturation 0.38039213, lightness 0.7941177, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.af35f6p126F}.
     * <pre>
     * <font style='background-color: #FB9AD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FB9AD7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FB9AD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FB9AD7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FB9AD7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FB9AD7'>&nbsp;@&nbsp;</font><font style='background-color: #FB9AD7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FB9AD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FB9AD7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_PINK = -0x1.af35f6p126F;
    static { NAMED.put("Strong Purplish Pink", -0x1.af35f6p126F); LIST.add(-0x1.af35f6p126F); }

    /**
     * This color constant "Deep Purplish Pink" has RGBA8888 code {@code EB81C1FF}, hue 0.8993711, saturation 0.41568625, lightness 0.71372545, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.8303d6p126F}.
     * <pre>
     * <font style='background-color: #EB81C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB81C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB81C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EB81C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EB81C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EB81C1'>&nbsp;@&nbsp;</font><font style='background-color: #EB81C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB81C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB81C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_PINK = -0x1.8303d6p126F;
    static { NAMED.put("Deep Purplish Pink", -0x1.8303d6p126F); LIST.add(-0x1.8303d6p126F); }

    /**
     * This color constant "Light Purplish Pink" has RGBA8888 code {@code FDC0E5FF}, hue 0.8989071, saturation 0.23921567, lightness 0.872549, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.cb81fap126F}.
     * <pre>
     * <font style='background-color: #FDC0E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDC0E5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDC0E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDC0E5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDC0E5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDC0E5'>&nbsp;@&nbsp;</font><font style='background-color: #FDC0E5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDC0E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDC0E5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_PINK = -0x1.cb81fap126F;
    static { NAMED.put("Light Purplish Pink", -0x1.cb81fap126F); LIST.add(-0x1.cb81fap126F); }

    /**
     * This color constant "Moderate Purplish Pink" has RGBA8888 code {@code EBA9CFFF}, hue 0.9040404, saturation 0.2588235, lightness 0.7921569, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.9f53d6p126F}.
     * <pre>
     * <font style='background-color: #EBA9CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBA9CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBA9CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBA9CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBA9CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBA9CF'>&nbsp;@&nbsp;</font><font style='background-color: #EBA9CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBA9CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBA9CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_PINK = -0x1.9f53d6p126F;
    static { NAMED.put("Moderate Purplish Pink", -0x1.9f53d6p126F); LIST.add(-0x1.9f53d6p126F); }

    /**
     * This color constant "Dark Purplish Pink" has RGBA8888 code {@code DC8EB1FF}, hue 0.9252137, saturation 0.30588233, lightness 0.70980394, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.631db8p126F}.
     * <pre>
     * <font style='background-color: #DC8EB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC8EB1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC8EB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC8EB1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC8EB1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC8EB1'>&nbsp;@&nbsp;</font><font style='background-color: #DC8EB1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC8EB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC8EB1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_PINK = -0x1.631db8p126F;
    static { NAMED.put("Dark Purplish Pink", -0x1.631db8p126F); LIST.add(-0x1.631db8p126F); }

    /**
     * This color constant "Pale Purplish Pink" has RGBA8888 code {@code F5D3E9FF}, hue 0.89215684, saturation 0.13333333, lightness 0.89411765, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.d3a7eap126F}.
     * <pre>
     * <font style='background-color: #F5D3E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5D3E9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5D3E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5D3E9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5D3E9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5D3E9'>&nbsp;@&nbsp;</font><font style='background-color: #F5D3E9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5D3E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5D3E9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLISH_PINK = -0x1.d3a7eap126F;
    static { NAMED.put("Pale Purplish Pink", -0x1.d3a7eap126F); LIST.add(-0x1.d3a7eap126F); }

    /**
     * This color constant "Grayish Purplish Pink" has RGBA8888 code {@code DDAFC8FF}, hue 0.9094203, saturation 0.18039215, lightness 0.7764706, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.915fbap126F}.
     * <pre>
     * <font style='background-color: #DDAFC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDAFC8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDAFC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDAFC8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDAFC8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDAFC8'>&nbsp;@&nbsp;</font><font style='background-color: #DDAFC8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDAFC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDAFC8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_PINK = -0x1.915fbap126F;
    static { NAMED.put("Grayish Purplish Pink", -0x1.915fbap126F); LIST.add(-0x1.915fbap126F); }

    /**
     * This color constant "Vivid Purplish Red" has RGBA8888 code {@code FF2A9AFF}, hue 0.91236305, saturation 0.8352941, lightness 0.58235294, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.3455fep126F}.
     * <pre>
     * <font style='background-color: #FF2A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF2A9A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF2A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF2A9A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF2A9A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF2A9A'>&nbsp;@&nbsp;</font><font style='background-color: #FF2A9A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF2A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF2A9A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLISH_RED = -0x1.3455fep126F;
    static { NAMED.put("Vivid Purplish Red", -0x1.3455fep126F); LIST.add(-0x1.3455fep126F); }

    /**
     * This color constant "Strong Purplish Red" has RGBA8888 code {@code E4338AFF}, hue 0.9180791, saturation 0.69411767, lightness 0.5470588, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.1467c8p126F}.
     * <pre>
     * <font style='background-color: #E4338A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4338A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4338A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E4338A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E4338A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E4338A'>&nbsp;@&nbsp;</font><font style='background-color: #E4338A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4338A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4338A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_RED = -0x1.1467c8p126F;
    static { NAMED.put("Strong Purplish Red", -0x1.1467c8p126F); LIST.add(-0x1.1467c8p126F); }

    /**
     * This color constant "Deep Purplish Red" has RGBA8888 code {@code B00070FF}, hue 0.8939394, saturation 0.6901961, lightness 0.34509805, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e0016p125F}.
     * <pre>
     * <font style='background-color: #B00070;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B00070; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B00070;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B00070'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B00070'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B00070'>&nbsp;@&nbsp;</font><font style='background-color: #B00070; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B00070;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B00070; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_RED = -0x1.e0016p125F;
    static { NAMED.put("Deep Purplish Red", -0x1.e0016p125F); LIST.add(-0x1.e0016p125F); }

    /**
     * This color constant "Very Deep Purplish Red" has RGBA8888 code {@code 730052FF}, hue 0.8811594, saturation 0.4509804, lightness 0.2254902, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a400e6p125F}.
     * <pre>
     * <font style='background-color: #730052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #730052; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #730052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #730052'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #730052'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #730052'>&nbsp;@&nbsp;</font><font style='background-color: #730052; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #730052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #730052; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_PURPLISH_RED = -0x1.a400e6p125F;
    static { NAMED.put("Very Deep Purplish Red", -0x1.a400e6p125F); LIST.add(-0x1.a400e6p125F); }

    /**
     * This color constant "Moderate Purplish Red" has RGBA8888 code {@code D74B8EFF}, hue 0.9202381, saturation 0.5490196, lightness 0.5686275, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.1c97aep126F}.
     * <pre>
     * <font style='background-color: #D74B8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D74B8E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D74B8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D74B8E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D74B8E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D74B8E'>&nbsp;@&nbsp;</font><font style='background-color: #D74B8E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D74B8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D74B8E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_RED = -0x1.1c97aep126F;
    static { NAMED.put("Moderate Purplish Red", -0x1.1c97aep126F); LIST.add(-0x1.1c97aep126F); }

    /**
     * This color constant "Dark Purplish Red" has RGBA8888 code {@code 922D61FF}, hue 0.9141914, saturation 0.39607847, lightness 0.3745098, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c25b24p125F}.
     * <pre>
     * <font style='background-color: #922D61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #922D61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #922D61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #922D61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #922D61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #922D61'>&nbsp;@&nbsp;</font><font style='background-color: #922D61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #922D61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #922D61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_RED = -0x1.c25b24p125F;
    static { NAMED.put("Dark Purplish Red", -0x1.c25b24p125F); LIST.add(-0x1.c25b24p125F); }

    /**
     * This color constant "Very Dark Purplish Red" has RGBA8888 code {@code 631244FF}, hue 0.89711934, saturation 0.31764707, lightness 0.22941178, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.8824c6p125F}.
     * <pre>
     * <font style='background-color: #631244;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #631244; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #631244;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #631244'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #631244'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #631244'>&nbsp;@&nbsp;</font><font style='background-color: #631244; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #631244;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #631244; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_PURPLISH_RED = -0x1.8824c6p125F;
    static { NAMED.put("Very Dark Purplish Red", -0x1.8824c6p125F); LIST.add(-0x1.8824c6p125F); }

    /**
     * This color constant "Light Grayish Purplish Red" has RGBA8888 code {@code CB97ABFF}, hue 0.9358974, saturation 0.20392156, lightness 0.69411767, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.572f96p126F}.
     * <pre>
     * <font style='background-color: #CB97AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB97AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB97AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB97AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB97AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB97AB'>&nbsp;@&nbsp;</font><font style='background-color: #CB97AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB97AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB97AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_PURPLISH_RED = -0x1.572f96p126F;
    static { NAMED.put("Light Grayish Purplish Red", -0x1.572f96p126F); LIST.add(-0x1.572f96p126F); }

    /**
     * This color constant "Grayish Purplish Red" has RGBA8888 code {@code BD638AFF}, hue 0.92777777, saturation 0.3529412, lightness 0.5647059, and alpha 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.14c77ap126F}.
     * <pre>
     * <font style='background-color: #BD638A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD638A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD638A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD638A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD638A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD638A'>&nbsp;@&nbsp;</font><font style='background-color: #BD638A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD638A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD638A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_RED = -0x1.14c77ap126F;
    static { NAMED.put("Grayish Purplish Red", -0x1.14c77ap126F); LIST.add(-0x1.14c77ap126F); }
    
    /**
     * All names for colors in this palette, in alphabetical order. You can fetch the corresponding packed float color
     * by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES = NAMED.keys().toArray();
    static { NAMES.sort(); }
    /**
     * All names for colors in this palette, with grayscale first, then sorted by hue from red to yellow to green to
     * blue. You can fetch the corresponding packed float color by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES_BY_HUE = new Array<>(NAMES);
    /**
     * All names for colors in this palette, sorted by lightness from black to white. You can fetch the
     * corresponding packed float color by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES_BY_LIGHTNESS = new Array<>(NAMES);
    static {
        NAMES_BY_HUE.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                final float c1 = NAMED.get(o1, MUNSELLISH_TRANSPARENT), c2 = NAMED.get(o2, MUNSELLISH_TRANSPARENT);
                if(ColorTools.alphaInt(c1) < 128) return -10000;
                else if(ColorTools.alphaInt(c2) < 128) return 10000;
                final float s1 = ColorTools.saturation(c1), s2 = ColorTools.saturation(c2);
                if(s1 <= 0x1p-6f && s2 > 0x1p-6f)
                    return -1000;
                else if(s1 > 0x1p-6f && s2 <= 0x1p-6f)
                    return 1000;
                else if(s1 <= 0x1p-6f && s2 <= 0x1p-6f)
                    return (int)Math.signum(ColorTools.lightness(c1) - ColorTools.lightness(c2));
                else
                    return 2 * (int)Math.signum(ColorTools.hue(c1) - ColorTools.hue(c2))
                            + (int)Math.signum(ColorTools.lightness(c1) - ColorTools.lightness(c2));
            }
        });
        NAMES_BY_LIGHTNESS.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Float.compare(lightness(NAMED.get(o1, MUNSELLISH_TRANSPARENT)), lightness(NAMED.get(o2, MUNSELLISH_TRANSPARENT)));
            }
        });
    }
    /**
     * Appends standard RGBA Color instances to the map in {@link Colors}, using the names in {@link #NAMES} (which
     * are "Title Cased" instead of "ALL_UPPER_CASE"). This doesn't need any changes to be made to Colors in order for
     * it to be compatible; just remember that the colors originally in Colors use "UPPER_CASE" and these use "Title
     * Case".
     */
    public static void appendToKnownColors(){
        for(ObjectFloatMap.Entry<String> ent : NAMED) {
            Colors.put(ent.key, ColorTools.toColor(new Color(), ent.value));
        }
    }

}
