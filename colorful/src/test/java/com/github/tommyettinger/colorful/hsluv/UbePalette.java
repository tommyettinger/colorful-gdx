package com.github.tommyettinger.colorful.hsluv;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;

import java.util.Comparator;

import static com.github.tommyettinger.colorful.hsluv.ColorTools.*;

/**
 * A palette of predefined colors as packed HSLuv floats, the kind {@link ColorTools} works with.
 * You can access colors by their constant name, such as {@code OCEAN_BLUE}, by the {@link #NAMED} map using
 * {@code NAMED.get("Ocean Blue", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access a float
 * color from NAMED, you need to give a default value if the name is not found; {@code 256f} is a good default because
 * it will not occur in a valid HSLuv color. You can access the names in a specific order with {@link #NAMES} (which is
 * alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow to blue
 * (with gray around here) to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * Created by Tommy Ettinger on 10/13/2020.
 */
public class UbePalette {
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(256);
    public static final FloatArray LIST = new FloatArray(256);


    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, H 0.0, S 0.0, L 0.0, alpha 0.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code 0x0.0p0F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float UBE_TRANSPARENT = 0x0.0p0F;
    static { NAMED.put("transparent", 0x0.0p0F); LIST.add(0x0.0p0F); }

    /**
     * This color constant "pure black" has RGBA8888 code {@code 000000FF}, H 0.0, S 0.0, L 0.0, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.0p125F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_BLACK = -0x1.0p125F;
    static { NAMED.put("pure black", -0x1.0p125F); LIST.add(-0x1.0p125F); }

    /**
     * This color constant "almost black" has RGBA8888 code {@code 141414FF}, H 0.16078432, S 0.0, L 0.0627451, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.200052p125F}.
     * <pre>
     * <font style='background-color: #141414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #141414; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #141414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #141414'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #141414'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #141414'>&nbsp;@&nbsp;</font><font style='background-color: #141414; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #141414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #141414; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_BLACK = -0x1.200052p125F;
    static { NAMED.put("almost black", -0x1.200052p125F); LIST.add(-0x1.200052p125F); }

    /**
     * This color constant "lead black" has RGBA8888 code {@code 252525FF}, H 0.16078432, S 0.0, L 0.13725491, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.460052p125F}.
     * <pre>
     * <font style='background-color: #252525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #252525; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #252525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #252525'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #252525'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #252525'>&nbsp;@&nbsp;</font><font style='background-color: #252525; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #252525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #252525; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLACK = -0x1.460052p125F;
    static { NAMED.put("lead black", -0x1.460052p125F); LIST.add(-0x1.460052p125F); }

    /**
     * This color constant "black lead" has RGBA8888 code {@code 363636FF}, H 0.16078432, S 0.0, L 0.20784314, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.6a0052p125F}.
     * <pre>
     * <font style='background-color: #363636;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #363636; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #363636;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #363636'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #363636'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #363636'>&nbsp;@&nbsp;</font><font style='background-color: #363636; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #363636;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #363636; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LEAD = -0x1.6a0052p125F;
    static { NAMED.put("black lead", -0x1.6a0052p125F); LIST.add(-0x1.6a0052p125F); }

    /**
     * This color constant "pure lead" has RGBA8888 code {@code 494949FF}, H 0.16078432, S 0.0, L 0.28627452, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.920052p125F}.
     * <pre>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494949; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #494949; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494949; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_LEAD = -0x1.920052p125F;
    static { NAMED.put("pure lead", -0x1.920052p125F); LIST.add(-0x1.920052p125F); }

    /**
     * This color constant "gray lead" has RGBA8888 code {@code 5B5B5BFF}, H 0.16078432, S 0.0, L 0.35686275, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.b60052p125F}.
     * <pre>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #5B5B5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LEAD = -0x1.b60052p125F;
    static { NAMED.put("gray lead", -0x1.b60052p125F); LIST.add(-0x1.b60052p125F); }

    /**
     * This color constant "lead gray" has RGBA8888 code {@code 6D6D6DFF}, H 0.16470589, S 0.0, L 0.42745098, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.da0054p125F}.
     * <pre>
     * <font style='background-color: #6D6D6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D6D6D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D6D6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D6D6D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D6D6D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D6D6D'>&nbsp;@&nbsp;</font><font style='background-color: #6D6D6D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D6D6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D6D6D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GRAY = -0x1.da0054p125F;
    static { NAMED.put("lead gray", -0x1.da0054p125F); LIST.add(-0x1.da0054p125F); }

    /**
     * This color constant "pure gray" has RGBA8888 code {@code 7F7F7FFF}, H 0.16078432, S 0.0, L 0.49803922, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.fe0052p125F}.
     * <pre>
     * <font style='background-color: #7F7F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F7F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F7F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F7F7F'>&nbsp;@&nbsp;</font><font style='background-color: #7F7F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_GRAY = -0x1.fe0052p125F;
    static { NAMED.put("pure gray", -0x1.fe0052p125F); LIST.add(-0x1.fe0052p125F); }

    /**
     * This color constant "silver gray" has RGBA8888 code {@code 939393FF}, H 0.16470589, S 0.0, L 0.57254905, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.240054p126F}.
     * <pre>
     * <font style='background-color: #939393;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #939393; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #939393;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #939393'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #939393'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #939393'>&nbsp;@&nbsp;</font><font style='background-color: #939393; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #939393;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #939393; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GRAY = -0x1.240054p126F;
    static { NAMED.put("silver gray", -0x1.240054p126F); LIST.add(-0x1.240054p126F); }

    /**
     * This color constant "gray silver" has RGBA8888 code {@code A5A5A5FF}, H 0.16078432, S 0.0, L 0.6431373, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.480052p126F}.
     * <pre>
     * <font style='background-color: #A5A5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5A5A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5A5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A5A5A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A5A5A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A5A5A5'>&nbsp;@&nbsp;</font><font style='background-color: #A5A5A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5A5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5A5A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SILVER = -0x1.480052p126F;
    static { NAMED.put("gray silver", -0x1.480052p126F); LIST.add(-0x1.480052p126F); }

    /**
     * This color constant "pure silver" has RGBA8888 code {@code B7B7B7FF}, H 0.16470589, S 0.0, L 0.7137255, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.6c0054p126F}.
     * <pre>
     * <font style='background-color: #B7B7B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7B7B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7B7B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B7B7B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B7B7B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B7B7B7'>&nbsp;@&nbsp;</font><font style='background-color: #B7B7B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7B7B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7B7B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_SILVER = -0x1.6c0054p126F;
    static { NAMED.put("pure silver", -0x1.6c0054p126F); LIST.add(-0x1.6c0054p126F); }

    /**
     * This color constant "white silver" has RGBA8888 code {@code CACACAFF}, H 0.16078432, S 0.0, L 0.7882353, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.920052p126F}.
     * <pre>
     * <font style='background-color: #CACACA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CACACA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CACACA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CACACA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CACACA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CACACA'>&nbsp;@&nbsp;</font><font style='background-color: #CACACA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CACACA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CACACA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SILVER = -0x1.920052p126F;
    static { NAMED.put("white silver", -0x1.920052p126F); LIST.add(-0x1.920052p126F); }

    /**
     * This color constant "silver white" has RGBA8888 code {@code DCDCDCFF}, H 0.16078432, S 0.0, L 0.8627451, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.b80052p126F}.
     * <pre>
     * <font style='background-color: #DCDCDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCDCDC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCDCDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DCDCDC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DCDCDC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DCDCDC'>&nbsp;@&nbsp;</font><font style='background-color: #DCDCDC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCDCDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCDCDC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_WHITE = -0x1.b80052p126F;
    static { NAMED.put("silver white", -0x1.b80052p126F); LIST.add(-0x1.b80052p126F); }

    /**
     * This color constant "almost white" has RGBA8888 code {@code EEEEEEFF}, H 0.16078432, S 0.0, L 0.93333334, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.dc0052p126F}.
     * <pre>
     * <font style='background-color: #EEEEEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEEEEE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEEEEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EEEEEE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EEEEEE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EEEEEE'>&nbsp;@&nbsp;</font><font style='background-color: #EEEEEE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEEEEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEEEEE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_WHITE = -0x1.dc0052p126F;
    static { NAMED.put("almost white", -0x1.dc0052p126F); LIST.add(-0x1.dc0052p126F); }

    /**
     * This color constant "pure white" has RGBA8888 code {@code FFFFFFFF}, H 0.16078432, S 0.0, L 1.0, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.fe0052p126F}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_WHITE = -0x1.fe0052p126F;
    static { NAMED.put("pure white", -0x1.fe0052p126F); LIST.add(-0x1.fe0052p126F); }

    /**
     * This color constant "black red" has RGBA8888 code {@code 7C4C4DFF}, H 0.02745098, S 0.2784314, L 0.34901962, alpha 1.0, and chroma 0.33877465.
     * It can be represented as a packed float with the constant {@code -0x1.b28e0ep125F}.
     * <pre>
     * <font style='background-color: #7C4C4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C4C4D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C4C4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C4C4D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C4C4D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C4C4D'>&nbsp;@&nbsp;</font><font style='background-color: #7C4C4D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C4C4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C4C4D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_RED = -0x1.b28e0ep125F;
    static { NAMED.put("black red", -0x1.b28e0ep125F); LIST.add(-0x1.b28e0ep125F); }

    /**
     * This color constant "lead red" has RGBA8888 code {@code 975D5EFF}, H 0.03137255, S 0.28235295, L 0.42352942, alpha 1.0, and chroma 0.42609513.
     * It can be represented as a packed float with the constant {@code -0x1.d8901p125F}.
     * <pre>
     * <font style='background-color: #975D5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #975D5E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #975D5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #975D5E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #975D5E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #975D5E'>&nbsp;@&nbsp;</font><font style='background-color: #975D5E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #975D5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #975D5E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_RED = -0x1.d8901p125F;
    static { NAMED.put("lead red", -0x1.d8901p125F); LIST.add(-0x1.d8901p125F); }

    /**
     * This color constant "gray red" has RGBA8888 code {@code B17173FF}, H 0.02745098, S 0.2627451, L 0.50980395, alpha 1.0, and chroma 0.43210408.
     * It can be represented as a packed float with the constant {@code -0x1.04860ep126F}.
     * <pre>
     * <font style='background-color: #B17173;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B17173; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B17173;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B17173'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B17173'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B17173'>&nbsp;@&nbsp;</font><font style='background-color: #B17173; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B17173;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B17173; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_RED = -0x1.04860ep126F;
    static { NAMED.put("gray red", -0x1.04860ep126F); LIST.add(-0x1.04860ep126F); }

    /**
     * This color constant "silver red" has RGBA8888 code {@code BE9192FF}, H 0.02745098, S 0.22745098, L 0.60784316, alpha 1.0, and chroma 0.24950731.
     * It can be represented as a packed float with the constant {@code -0x1.36740ep126F}.
     * <pre>
     * <font style='background-color: #BE9192;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE9192; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE9192;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE9192'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE9192'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE9192'>&nbsp;@&nbsp;</font><font style='background-color: #BE9192; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE9192;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE9192; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_RED = -0x1.36740ep126F;
    static { NAMED.put("silver red", -0x1.36740ep126F); LIST.add(-0x1.36740ep126F); }

    /**
     * This color constant "white red" has RGBA8888 code {@code CEB0B1FF}, H 0.02745098, S 0.23137255, L 0.7137255, alpha 1.0, and chroma 0.15870725.
     * It can be represented as a packed float with the constant {@code -0x1.6c760ep126F}.
     * <pre>
     * <font style='background-color: #CEB0B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEB0B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEB0B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEB0B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEB0B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEB0B1'>&nbsp;@&nbsp;</font><font style='background-color: #CEB0B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEB0B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEB0B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_RED = -0x1.6c760ep126F;
    static { NAMED.put("white red", -0x1.6c760ep126F); LIST.add(-0x1.6c760ep126F); }

    /**
     * This color constant "black brown" has RGBA8888 code {@code 6A605AFF}, H 0.11764706, S 0.15294118, L 0.38431373, alpha 1.0, and chroma 0.09236578.
     * It can be represented as a packed float with the constant {@code -0x1.c44e3cp125F}.
     * <pre>
     * <font style='background-color: #6A605A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A605A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A605A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A605A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A605A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A605A'>&nbsp;@&nbsp;</font><font style='background-color: #6A605A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A605A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A605A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BROWN = -0x1.c44e3cp125F;
    static { NAMED.put("black brown", -0x1.c44e3cp125F); LIST.add(-0x1.c44e3cp125F); }

    /**
     * This color constant "lead brown" has RGBA8888 code {@code 82766FFF}, H 0.11372549, S 0.14901961, L 0.47058824, alpha 1.0, and chroma 0.11177581.
     * It can be represented as a packed float with the constant {@code -0x1.f04c3ap125F}.
     * <pre>
     * <font style='background-color: #82766F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82766F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82766F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #82766F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #82766F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #82766F'>&nbsp;@&nbsp;</font><font style='background-color: #82766F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82766F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82766F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BROWN = -0x1.f04c3ap125F;
    static { NAMED.put("lead brown", -0x1.f04c3ap125F); LIST.add(-0x1.f04c3ap125F); }

    /**
     * This color constant "gray brown" has RGBA8888 code {@code 978981FF}, H 0.11372549, S 0.14509805, L 0.54509807, alpha 1.0, and chroma 0.12500879.
     * It can be represented as a packed float with the constant {@code -0x1.164a3ap126F}.
     * <pre>
     * <font style='background-color: #978981;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #978981; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #978981;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #978981'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #978981'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #978981'>&nbsp;@&nbsp;</font><font style='background-color: #978981; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #978981;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #978981; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BROWN = -0x1.164a3ap126F;
    static { NAMED.put("gray brown", -0x1.164a3ap126F); LIST.add(-0x1.164a3ap126F); }

    /**
     * This color constant "silver brown" has RGBA8888 code {@code AC9D94FF}, H 0.11764706, S 0.14117648, L 0.62352943, alpha 1.0, and chroma 0.13478667.
     * It can be represented as a packed float with the constant {@code -0x1.3e483cp126F}.
     * <pre>
     * <font style='background-color: #AC9D94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC9D94; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC9D94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC9D94'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC9D94'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC9D94'>&nbsp;@&nbsp;</font><font style='background-color: #AC9D94; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC9D94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC9D94; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BROWN = -0x1.3e483cp126F;
    static { NAMED.put("silver brown", -0x1.3e483cp126F); LIST.add(-0x1.3e483cp126F); }

    /**
     * This color constant "white brown" has RGBA8888 code {@code C5B5ACFF}, H 0.11372549, S 0.1254902, L 0.7176471, alpha 1.0, and chroma 0.12301306.
     * It can be represented as a packed float with the constant {@code -0x1.6e403ap126F}.
     * <pre>
     * <font style='background-color: #C5B5AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5B5AC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5B5AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C5B5AC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C5B5AC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C5B5AC'>&nbsp;@&nbsp;</font><font style='background-color: #C5B5AC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5B5AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5B5AC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BROWN = -0x1.6e403ap126F;
    static { NAMED.put("white brown", -0x1.6e403ap126F); LIST.add(-0x1.6e403ap126F); }

    /**
     * This color constant "black orange" has RGBA8888 code {@code 715950FF}, H 0.08627451, S 0.2784314, L 0.36862746, alpha 1.0, and chroma 0.19894557.
     * It can be represented as a packed float with the constant {@code -0x1.bc8e2cp125F}.
     * <pre>
     * <font style='background-color: #715950;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #715950; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #715950;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #715950'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #715950'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #715950'>&nbsp;@&nbsp;</font><font style='background-color: #715950; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #715950;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #715950; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_ORANGE = -0x1.bc8e2cp125F;
    static { NAMED.put("black orange", -0x1.bc8e2cp125F); LIST.add(-0x1.bc8e2cp125F); }

    /**
     * This color constant "lead orange" has RGBA8888 code {@code 886B61FF}, H 0.08235294, S 0.27058825, L 0.44313726, alpha 1.0, and chroma 0.238704.
     * It can be represented as a packed float with the constant {@code -0x1.e28a2ap125F}.
     * <pre>
     * <font style='background-color: #886B61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #886B61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #886B61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #886B61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #886B61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #886B61'>&nbsp;@&nbsp;</font><font style='background-color: #886B61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #886B61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #886B61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_ORANGE = -0x1.e28a2ap125F;
    static { NAMED.put("lead orange", -0x1.e28a2ap125F); LIST.add(-0x1.e28a2ap125F); }

    /**
     * This color constant "gray orange" has RGBA8888 code {@code A07E73FF}, H 0.078431375, S 0.2627451, L 0.52156866, alpha 1.0, and chroma 0.2799643.
     * It can be represented as a packed float with the constant {@code -0x1.0a8628p126F}.
     * <pre>
     * <font style='background-color: #A07E73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A07E73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A07E73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A07E73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A07E73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A07E73'>&nbsp;@&nbsp;</font><font style='background-color: #A07E73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A07E73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A07E73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_ORANGE = -0x1.0a8628p126F;
    static { NAMED.put("gray orange", -0x1.0a8628p126F); LIST.add(-0x1.0a8628p126F); }

    /**
     * This color constant "silver orange" has RGBA8888 code {@code BB9487FF}, H 0.08235294, S 0.2627451, L 0.6117647, alpha 1.0, and chroma 0.3137889.
     * It can be represented as a packed float with the constant {@code -0x1.38862ap126F}.
     * <pre>
     * <font style='background-color: #BB9487;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB9487; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB9487;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB9487'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB9487'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB9487'>&nbsp;@&nbsp;</font><font style='background-color: #BB9487; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB9487;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB9487; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_ORANGE = -0x1.38862ap126F;
    static { NAMED.put("silver orange", -0x1.38862ap126F); LIST.add(-0x1.38862ap126F); }

    /**
     * This color constant "white orange" has RGBA8888 code {@code CEB1A9FF}, H 0.078431375, S 0.21568628, L 0.7137255, alpha 1.0, and chroma 0.17522179.
     * It can be represented as a packed float with the constant {@code -0x1.6c6e28p126F}.
     * <pre>
     * <font style='background-color: #CEB1A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEB1A9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEB1A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEB1A9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEB1A9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEB1A9'>&nbsp;@&nbsp;</font><font style='background-color: #CEB1A9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEB1A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEB1A9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_ORANGE = -0x1.6c6e28p126F;
    static { NAMED.put("white orange", -0x1.6c6e28p126F); LIST.add(-0x1.6c6e28p126F); }

    /**
     * This color constant "black saffron" has RGBA8888 code {@code 6C625CFF}, H 0.11764706, S 0.14901961, L 0.39215687, alpha 1.0, and chroma 0.09178348.
     * It can be represented as a packed float with the constant {@code -0x1.c84c3cp125F}.
     * <pre>
     * <font style='background-color: #6C625C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C625C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C625C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C625C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C625C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C625C'>&nbsp;@&nbsp;</font><font style='background-color: #6C625C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C625C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C625C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_SAFFRON = -0x1.c84c3cp125F;
    static { NAMED.put("black saffron", -0x1.c84c3cp125F); LIST.add(-0x1.c84c3cp125F); }

    /**
     * This color constant "lead saffron" has RGBA8888 code {@code 857971FF}, H 0.12156863, S 0.15686275, L 0.48235294, alpha 1.0, and chroma 0.11544028.
     * It can be represented as a packed float with the constant {@code -0x1.f6503ep125F}.
     * <pre>
     * <font style='background-color: #857971;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #857971; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #857971;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #857971'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #857971'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #857971'>&nbsp;@&nbsp;</font><font style='background-color: #857971; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #857971;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #857971; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_SAFFRON = -0x1.f6503ep125F;
    static { NAMED.put("lead saffron", -0x1.f6503ep125F); LIST.add(-0x1.f6503ep125F); }

    /**
     * This color constant "gray saffron" has RGBA8888 code {@code 9B8E85FF}, H 0.1254902, S 0.15294118, L 0.5647059, alpha 1.0, and chroma 0.12796977.
     * It can be represented as a packed float with the constant {@code -0x1.204e4p126F}.
     * <pre>
     * <font style='background-color: #9B8E85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B8E85; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B8E85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B8E85'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B8E85'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B8E85'>&nbsp;@&nbsp;</font><font style='background-color: #9B8E85; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B8E85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B8E85; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SAFFRON = -0x1.204e4p126F;
    static { NAMED.put("gray saffron", -0x1.204e4p126F); LIST.add(-0x1.204e4p126F); }

    /**
     * This color constant "silver saffron" has RGBA8888 code {@code B0A197FF}, H 0.12156863, S 0.14901961, L 0.6392157, alpha 1.0, and chroma 0.14256777.
     * It can be represented as a packed float with the constant {@code -0x1.464c3ep126F}.
     * <pre>
     * <font style='background-color: #B0A197;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0A197; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0A197;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0A197'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0A197'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0A197'>&nbsp;@&nbsp;</font><font style='background-color: #B0A197; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0A197;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0A197; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_SAFFRON = -0x1.464c3ep126F;
    static { NAMED.put("silver saffron", -0x1.464c3ep126F); LIST.add(-0x1.464c3ep126F); }

    /**
     * This color constant "white saffron" has RGBA8888 code {@code C6B6ABFF}, H 0.1254902, S 0.14509805, L 0.72156864, alpha 1.0, and chroma 0.15192428.
     * It can be represented as a packed float with the constant {@code -0x1.704a4p126F}.
     * <pre>
     * <font style='background-color: #C6B6AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6B6AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6B6AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6B6AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6B6AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6B6AB'>&nbsp;@&nbsp;</font><font style='background-color: #C6B6AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6B6AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6B6AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SAFFRON = -0x1.704a4p126F;
    static { NAMED.put("white saffron", -0x1.704a4p126F); LIST.add(-0x1.704a4p126F); }

    /**
     * This color constant "black yellow" has RGBA8888 code {@code 626252FF}, H 0.23921569, S 0.28235295, L 0.38039216, alpha 1.0, and chroma 0.12828124.
     * It can be represented as a packed float with the constant {@code -0x1.c2907ap125F}.
     * <pre>
     * <font style='background-color: #626252;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626252; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626252;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #626252'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #626252'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #626252'>&nbsp;@&nbsp;</font><font style='background-color: #626252; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626252;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626252; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_YELLOW = -0x1.c2907ap125F;
    static { NAMED.put("black yellow", -0x1.c2907ap125F); LIST.add(-0x1.c2907ap125F); }

    /**
     * This color constant "lead yellow" has RGBA8888 code {@code 777865FF}, H 0.24313726, S 0.27450982, L 0.4627451, alpha 1.0, and chroma 0.15089513.
     * It can be represented as a packed float with the constant {@code -0x1.ec8c7cp125F}.
     * <pre>
     * <font style='background-color: #777865;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #777865; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #777865;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #777865'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #777865'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #777865'>&nbsp;@&nbsp;</font><font style='background-color: #777865; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #777865;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #777865; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_YELLOW = -0x1.ec8c7cp125F;
    static { NAMED.put("lead yellow", -0x1.ec8c7cp125F); LIST.add(-0x1.ec8c7cp125F); }

    /**
     * This color constant "gray yellow" has RGBA8888 code {@code 8A8A74FF}, H 0.23921569, S 0.28235295, L 0.53333336, alpha 1.0, and chroma 0.1772041.
     * It can be represented as a packed float with the constant {@code -0x1.10907ap126F}.
     * <pre>
     * <font style='background-color: #8A8A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A8A74; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A8A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A8A74'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A8A74'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A8A74'>&nbsp;@&nbsp;</font><font style='background-color: #8A8A74; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A8A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A8A74; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_YELLOW = -0x1.10907ap126F;
    static { NAMED.put("gray yellow", -0x1.10907ap126F); LIST.add(-0x1.10907ap126F); }

    /**
     * This color constant "silver yellow" has RGBA8888 code {@code A0A188FF}, H 0.24313726, S 0.27058825, L 0.61960787, alpha 1.0, and chroma 0.1954781.
     * It can be represented as a packed float with the constant {@code -0x1.3c8a7cp126F}.
     * <pre>
     * <font style='background-color: #A0A188;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0A188; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0A188;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A0A188'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A0A188'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A0A188'>&nbsp;@&nbsp;</font><font style='background-color: #A0A188; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0A188;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0A188; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_YELLOW = -0x1.3c8a7cp126F;
    static { NAMED.put("silver yellow", -0x1.3c8a7cp126F); LIST.add(-0x1.3c8a7cp126F); }

    /**
     * This color constant "white yellow" has RGBA8888 code {@code BABA9EFF}, H 0.23921569, S 0.26666668, L 0.7176471, alpha 1.0, and chroma 0.21982232.
     * It can be represented as a packed float with the constant {@code -0x1.6e887ap126F}.
     * <pre>
     * <font style='background-color: #BABA9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BABA9E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BABA9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BABA9E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BABA9E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BABA9E'>&nbsp;@&nbsp;</font><font style='background-color: #BABA9E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BABA9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BABA9E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_YELLOW = -0x1.6e887ap126F;
    static { NAMED.put("white yellow", -0x1.6e887ap126F); LIST.add(-0x1.6e887ap126F); }

    /**
     * This color constant "black lime" has RGBA8888 code {@code 555E4EFF}, H 0.30980393, S 0.25882354, L 0.35686275, alpha 1.0, and chroma 0.12540391.
     * It can be represented as a packed float with the constant {@code -0x1.b6849ep125F}.
     * <pre>
     * <font style='background-color: #555E4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #555E4E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #555E4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #555E4E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #555E4E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #555E4E'>&nbsp;@&nbsp;</font><font style='background-color: #555E4E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #555E4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #555E4E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LIME = -0x1.b6849ep125F;
    static { NAMED.put("black lime", -0x1.b6849ep125F); LIST.add(-0x1.b6849ep125F); }

    /**
     * This color constant "lead lime" has RGBA8888 code {@code 68715FFF}, H 0.3019608, S 0.2509804, L 0.43137255, alpha 1.0, and chroma 0.14257869.
     * It can be represented as a packed float with the constant {@code -0x1.dc809ap125F}.
     * <pre>
     * <font style='background-color: #68715F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68715F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68715F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68715F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68715F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68715F'>&nbsp;@&nbsp;</font><font style='background-color: #68715F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68715F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68715F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_LIME = -0x1.dc809ap125F;
    static { NAMED.put("lead lime", -0x1.dc809ap125F); LIST.add(-0x1.dc809ap125F); }

    /**
     * This color constant "gray lime" has RGBA8888 code {@code 7C8772FF}, H 0.3019608, S 0.24705882, L 0.5137255, alpha 1.0, and chroma 0.1657225.
     * It can be represented as a packed float with the constant {@code -0x1.067e9ap126F}.
     * <pre>
     * <font style='background-color: #7C8772;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C8772; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C8772;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C8772'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C8772'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C8772'>&nbsp;@&nbsp;</font><font style='background-color: #7C8772; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C8772;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C8772; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LIME = -0x1.067e9ap126F;
    static { NAMED.put("gray lime", -0x1.067e9ap126F); LIST.add(-0x1.067e9ap126F); }

    /**
     * This color constant "silver lime" has RGBA8888 code {@code 93A087FF}, H 0.3019608, S 0.24705882, L 0.60784316, alpha 1.0, and chroma 0.19382434.
     * It can be represented as a packed float with the constant {@code -0x1.367e9ap126F}.
     * <pre>
     * <font style='background-color: #93A087;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93A087; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93A087;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93A087'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93A087'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93A087'>&nbsp;@&nbsp;</font><font style='background-color: #93A087; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93A087;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93A087; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_LIME = -0x1.367e9ap126F;
    static { NAMED.put("silver lime", -0x1.367e9ap126F); LIST.add(-0x1.367e9ap126F); }

    /**
     * This color constant "white lime" has RGBA8888 code {@code ADBC9FFF}, H 0.3019608, S 0.24313726, L 0.7137255, alpha 1.0, and chroma 0.22079283.
     * It can be represented as a packed float with the constant {@code -0x1.6c7c9ap126F}.
     * <pre>
     * <font style='background-color: #ADBC9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ADBC9F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ADBC9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ADBC9F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ADBC9F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ADBC9F'>&nbsp;@&nbsp;</font><font style='background-color: #ADBC9F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ADBC9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ADBC9F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_LIME = -0x1.6c7c9ap126F;
    static { NAMED.put("white lime", -0x1.6c7c9ap126F); LIST.add(-0x1.6c7c9ap126F); }

    /**
     * This color constant "black green" has RGBA8888 code {@code 546854FF}, H 0.3529412, S 0.27058825, L 0.3882353, alpha 1.0, and chroma 0.17388517.
     * It can be represented as a packed float with the constant {@code -0x1.c68ab4p125F}.
     * <pre>
     * <font style='background-color: #546854;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #546854; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #546854;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #546854'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #546854'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #546854'>&nbsp;@&nbsp;</font><font style='background-color: #546854; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #546854;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #546854; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_GREEN = -0x1.c68ab4p125F;
    static { NAMED.put("black green", -0x1.c68ab4p125F); LIST.add(-0x1.c68ab4p125F); }

    /**
     * This color constant "lead green" has RGBA8888 code {@code 678067FF}, H 0.3529412, S 0.2784314, L 0.4745098, alpha 1.0, and chroma 0.21700339.
     * It can be represented as a packed float with the constant {@code -0x1.f28eb4p125F}.
     * <pre>
     * <font style='background-color: #678067;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #678067; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #678067;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #678067'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #678067'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #678067'>&nbsp;@&nbsp;</font><font style='background-color: #678067; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #678067;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #678067; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GREEN = -0x1.f28eb4p125F;
    static { NAMED.put("lead green", -0x1.f28eb4p125F); LIST.add(-0x1.f28eb4p125F); }

    /**
     * This color constant "gray green" has RGBA8888 code {@code 799578FF}, H 0.3529412, S 0.28235295, L 0.5529412, alpha 1.0, and chroma 0.25414363.
     * It can be represented as a packed float with the constant {@code -0x1.1a90b4p126F}.
     * <pre>
     * <font style='background-color: #799578;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #799578; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #799578;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #799578'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #799578'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #799578'>&nbsp;@&nbsp;</font><font style='background-color: #799578; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #799578;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #799578; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.1a90b4p126F;
    static { NAMED.put("gray green", -0x1.1a90b4p126F); LIST.add(-0x1.1a90b4p126F); }

    /**
     * This color constant "silver green" has RGBA8888 code {@code 8AA989FF}, H 0.3529412, S 0.27450982, L 0.627451, alpha 1.0, and chroma 0.27772573.
     * It can be represented as a packed float with the constant {@code -0x1.408cb4p126F}.
     * <pre>
     * <font style='background-color: #8AA989;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8AA989; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8AA989;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8AA989'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8AA989'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8AA989'>&nbsp;@&nbsp;</font><font style='background-color: #8AA989; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8AA989;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8AA989; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.408cb4p126F;
    static { NAMED.put("silver green", -0x1.408cb4p126F); LIST.add(-0x1.408cb4p126F); }

    /**
     * This color constant "white green" has RGBA8888 code {@code 9EC19DFF}, H 0.3529412, S 0.27058825, L 0.7176471, alpha 1.0, and chroma 0.30928826.
     * It can be represented as a packed float with the constant {@code -0x1.6e8ab4p126F}.
     * <pre>
     * <font style='background-color: #9EC19D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9EC19D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9EC19D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9EC19D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9EC19D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9EC19D'>&nbsp;@&nbsp;</font><font style='background-color: #9EC19D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9EC19D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9EC19D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_GREEN = -0x1.6e8ab4p126F;
    static { NAMED.put("white green", -0x1.6e8ab4p126F); LIST.add(-0x1.6e8ab4p126F); }

    /**
     * This color constant "black cyan" has RGBA8888 code {@code 546262FF}, H 0.53333336, S 0.2509804, L 0.37254903, alpha 1.0, and chroma 0.08014169.
     * It can be represented as a packed float with the constant {@code -0x1.be811p125F}.
     * <pre>
     * <font style='background-color: #546262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #546262; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #546262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #546262'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #546262'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #546262'>&nbsp;@&nbsp;</font><font style='background-color: #546262; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #546262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #546262; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_CYAN = -0x1.be811p125F;
    static { NAMED.put("black cyan", -0x1.be811p125F); LIST.add(-0x1.be811p125F); }

    /**
     * This color constant "lead cyan" has RGBA8888 code {@code 657575FF}, H 0.53333336, S 0.24705882, L 0.44313726, alpha 1.0, and chroma 0.09331132.
     * It can be represented as a packed float with the constant {@code -0x1.e27f1p125F}.
     * <pre>
     * <font style='background-color: #657575;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #657575; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #657575;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #657575'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #657575'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #657575'>&nbsp;@&nbsp;</font><font style='background-color: #657575; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #657575;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #657575; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_CYAN = -0x1.e27f1p125F;
    static { NAMED.put("lead cyan", -0x1.e27f1p125F); LIST.add(-0x1.e27f1p125F); }

    /**
     * This color constant "gray cyan" has RGBA8888 code {@code 778A8AFF}, H 0.53333336, S 0.24705882, L 0.5254902, alpha 1.0, and chroma 0.109680496.
     * It can be represented as a packed float with the constant {@code -0x1.0c7f1p126F}.
     * <pre>
     * <font style='background-color: #778A8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #778A8A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #778A8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #778A8A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #778A8A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #778A8A'>&nbsp;@&nbsp;</font><font style='background-color: #778A8A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #778A8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #778A8A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_CYAN = -0x1.0c7f1p126F;
    static { NAMED.put("gray cyan", -0x1.0c7f1p126F); LIST.add(-0x1.0c7f1p126F); }

    /**
     * This color constant "silver cyan" has RGBA8888 code {@code 8BA1A1FF}, H 0.53333336, S 0.24705882, L 0.6117647, alpha 1.0, and chroma 0.12632416.
     * It can be represented as a packed float with the constant {@code -0x1.387f1p126F}.
     * <pre>
     * <font style='background-color: #8BA1A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BA1A1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BA1A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8BA1A1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8BA1A1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8BA1A1'>&nbsp;@&nbsp;</font><font style='background-color: #8BA1A1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BA1A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BA1A1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_CYAN = -0x1.387f1p126F;
    static { NAMED.put("silver cyan", -0x1.387f1p126F); LIST.add(-0x1.387f1p126F); }

    /**
     * This color constant "white cyan" has RGBA8888 code {@code A3BCBCFF}, H 0.53333336, S 0.23921569, L 0.7137255, alpha 1.0, and chroma 0.14074408.
     * It can be represented as a packed float with the constant {@code -0x1.6c7b1p126F}.
     * <pre>
     * <font style='background-color: #A3BCBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3BCBC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3BCBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A3BCBC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A3BCBC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A3BCBC'>&nbsp;@&nbsp;</font><font style='background-color: #A3BCBC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3BCBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3BCBC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_CYAN = -0x1.6c7b1p126F;
    static { NAMED.put("white cyan", -0x1.6c7b1p126F); LIST.add(-0x1.6c7b1p126F); }

    /**
     * This color constant "black blue" has RGBA8888 code {@code 61608AFF}, H 0.7411765, S 0.25490198, L 0.39215687, alpha 1.0, and chroma 0.33706075.
     * It can be represented as a packed float with the constant {@code -0x1.c8837ap125F}.
     * <pre>
     * <font style='background-color: #61608A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61608A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61608A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61608A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61608A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61608A'>&nbsp;@&nbsp;</font><font style='background-color: #61608A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61608A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61608A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BLUE = -0x1.c8837ap125F;
    static { NAMED.put("black blue", -0x1.c8837ap125F); LIST.add(-0x1.c8837ap125F); }

    /**
     * This color constant "lead blue" has RGBA8888 code {@code 7A799FFF}, H 0.7411765, S 0.24705882, L 0.4862745, alpha 1.0, and chroma 0.29521266.
     * It can be represented as a packed float with the constant {@code -0x1.f87f7ap125F}.
     * <pre>
     * <font style='background-color: #7A799F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A799F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A799F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A799F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A799F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A799F'>&nbsp;@&nbsp;</font><font style='background-color: #7A799F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A799F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A799F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLUE = -0x1.f87f7ap125F;
    static { NAMED.put("lead blue", -0x1.f87f7ap125F); LIST.add(-0x1.f87f7ap125F); }

    /**
     * This color constant "gray blue" has RGBA8888 code {@code 9190B0FF}, H 0.7411765, S 0.23921569, L 0.57254905, alpha 1.0, and chroma 0.24167542.
     * It can be represented as a packed float with the constant {@code -0x1.247b7ap126F}.
     * <pre>
     * <font style='background-color: #9190B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9190B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9190B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9190B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9190B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9190B0'>&nbsp;@&nbsp;</font><font style='background-color: #9190B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9190B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9190B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BLUE = -0x1.247b7ap126F;
    static { NAMED.put("gray blue", -0x1.247b7ap126F); LIST.add(-0x1.247b7ap126F); }

    /**
     * This color constant "silver blue" has RGBA8888 code {@code A4A4BEFF}, H 0.7411765, S 0.23137255, L 0.64705884, alpha 1.0, and chroma 0.19166514.
     * It can be represented as a packed float with the constant {@code -0x1.4a777ap126F}.
     * <pre>
     * <font style='background-color: #A4A4BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A4BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A4BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4A4BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4A4BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4A4BE'>&nbsp;@&nbsp;</font><font style='background-color: #A4A4BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A4BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A4BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BLUE = -0x1.4a777ap126F;
    static { NAMED.put("silver blue", -0x1.4a777ap126F); LIST.add(-0x1.4a777ap126F); }

    /**
     * This color constant "white blue" has RGBA8888 code {@code B9B8CDFF}, H 0.74509805, S 0.23529412, L 0.7254902, alpha 1.0, and chroma 0.14877352.
     * It can be represented as a packed float with the constant {@code -0x1.72797cp126F}.
     * <pre>
     * <font style='background-color: #B9B8CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9B8CD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9B8CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B9B8CD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B9B8CD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B9B8CD'>&nbsp;@&nbsp;</font><font style='background-color: #B9B8CD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9B8CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9B8CD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BLUE = -0x1.72797cp126F;
    static { NAMED.put("white blue", -0x1.72797cp126F); LIST.add(-0x1.72797cp126F); }

    /**
     * This color constant "black violet" has RGBA8888 code {@code 695C83FF}, H 0.77254903, S 0.2509804, L 0.38431373, alpha 1.0, and chroma 0.32415387.
     * It can be represented as a packed float with the constant {@code -0x1.c4818ap125F}.
     * <pre>
     * <font style='background-color: #695C83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #695C83; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #695C83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #695C83'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #695C83'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #695C83'>&nbsp;@&nbsp;</font><font style='background-color: #695C83; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #695C83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #695C83; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_VIOLET = -0x1.c4818ap125F;
    static { NAMED.put("black violet", -0x1.c4818ap125F); LIST.add(-0x1.c4818ap125F); }

    /**
     * This color constant "lead violet" has RGBA8888 code {@code 7E7197FF}, H 0.7764706, S 0.22352941, L 0.46666667, alpha 1.0, and chroma 0.28208765.
     * It can be represented as a packed float with the constant {@code -0x1.ee738cp125F}.
     * <pre>
     * <font style='background-color: #7E7197;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E7197; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E7197;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E7197'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E7197'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E7197'>&nbsp;@&nbsp;</font><font style='background-color: #7E7197; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E7197;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E7197; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_VIOLET = -0x1.ee738cp125F;
    static { NAMED.put("lead violet", -0x1.ee738cp125F); LIST.add(-0x1.ee738cp125F); }

    /**
     * This color constant "gray violet" has RGBA8888 code {@code 9086A7FF}, H 0.77254903, S 0.21568628, L 0.5411765, alpha 1.0, and chroma 0.23870061.
     * It can be represented as a packed float with the constant {@code -0x1.146f8ap126F}.
     * <pre>
     * <font style='background-color: #9086A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9086A7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9086A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9086A7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9086A7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9086A7'>&nbsp;@&nbsp;</font><font style='background-color: #9086A7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9086A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9086A7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_VIOLET = -0x1.146f8ap126F;
    static { NAMED.put("gray violet", -0x1.146f8ap126F); LIST.add(-0x1.146f8ap126F); }

    /**
     * This color constant "silver violet" has RGBA8888 code {@code A39BB6FF}, H 0.77254903, S 0.20784314, L 0.61960787, alpha 1.0, and chroma 0.19091484.
     * It can be represented as a packed float with the constant {@code -0x1.3c6b8ap126F}.
     * <pre>
     * <font style='background-color: #A39BB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A39BB6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A39BB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A39BB6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A39BB6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A39BB6'>&nbsp;@&nbsp;</font><font style='background-color: #A39BB6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A39BB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A39BB6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_VIOLET = -0x1.3c6b8ap126F;
    static { NAMED.put("silver violet", -0x1.3c6b8ap126F); LIST.add(-0x1.3c6b8ap126F); }

    /**
     * This color constant "white violet" has RGBA8888 code {@code BBB5CAFF}, H 0.77254903, S 0.21568628, L 0.7176471, alpha 1.0, and chroma 0.14413388.
     * It can be represented as a packed float with the constant {@code -0x1.6e6f8ap126F}.
     * <pre>
     * <font style='background-color: #BBB5CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBB5CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBB5CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BBB5CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BBB5CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BBB5CA'>&nbsp;@&nbsp;</font><font style='background-color: #BBB5CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBB5CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBB5CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_VIOLET = -0x1.6e6f8ap126F;
    static { NAMED.put("white violet", -0x1.6e6f8ap126F); LIST.add(-0x1.6e6f8ap126F); }

    /**
     * This color constant "black purple" has RGBA8888 code {@code 6A5579FF}, H 0.8039216, S 0.2784314, L 0.3647059, alpha 1.0, and chroma 0.29048088.
     * It can be represented as a packed float with the constant {@code -0x1.ba8f9ap125F}.
     * <pre>
     * <font style='background-color: #6A5579;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A5579; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A5579;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A5579'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A5579'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A5579'>&nbsp;@&nbsp;</font><font style='background-color: #6A5579; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A5579;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A5579; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_PURPLE = -0x1.ba8f9ap125F;
    static { NAMED.put("black purple", -0x1.ba8f9ap125F); LIST.add(-0x1.ba8f9ap125F); }

    /**
     * This color constant "lead purple" has RGBA8888 code {@code 7F6690FF}, H 0.8039216, S 0.2784314, L 0.43529412, alpha 1.0, and chroma 0.34485534.
     * It can be represented as a packed float with the constant {@code -0x1.de8f9ap125F}.
     * <pre>
     * <font style='background-color: #7F6690;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F6690; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F6690;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F6690'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F6690'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F6690'>&nbsp;@&nbsp;</font><font style='background-color: #7F6690; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F6690;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F6690; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_PURPLE = -0x1.de8f9ap125F;
    static { NAMED.put("lead purple", -0x1.de8f9ap125F); LIST.add(-0x1.de8f9ap125F); }

    /**
     * This color constant "gray purple" has RGBA8888 code {@code 947BA5FF}, H 0.8039216, S 0.24705882, L 0.5176471, alpha 1.0, and chroma 0.30459374.
     * It can be represented as a packed float with the constant {@code -0x1.087f9ap126F}.
     * <pre>
     * <font style='background-color: #947BA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #947BA5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #947BA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #947BA5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #947BA5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #947BA5'>&nbsp;@&nbsp;</font><font style='background-color: #947BA5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #947BA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #947BA5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_PURPLE = -0x1.087f9ap126F;
    static { NAMED.put("gray purple", -0x1.087f9ap126F); LIST.add(-0x1.087f9ap126F); }

    /**
     * This color constant "silver purple" has RGBA8888 code {@code A996B8FF}, H 0.8039216, S 0.23921569, L 0.6117647, alpha 1.0, and chroma 0.23950805.
     * It can be represented as a packed float with the constant {@code -0x1.387b9ap126F}.
     * <pre>
     * <font style='background-color: #A996B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A996B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A996B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A996B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A996B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A996B8'>&nbsp;@&nbsp;</font><font style='background-color: #A996B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A996B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A996B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PURPLE = -0x1.387b9ap126F;
    static { NAMED.put("silver purple", -0x1.387b9ap126F); LIST.add(-0x1.387b9ap126F); }

    /**
     * This color constant "white purple" has RGBA8888 code {@code C0B2CBFF}, H 0.8039216, S 0.23529412, L 0.7137255, alpha 1.0, and chroma 0.17065755.
     * It can be represented as a packed float with the constant {@code -0x1.6c799ap126F}.
     * <pre>
     * <font style='background-color: #C0B2CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0B2CB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0B2CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0B2CB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0B2CB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0B2CB'>&nbsp;@&nbsp;</font><font style='background-color: #C0B2CB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0B2CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0B2CB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_PURPLE = -0x1.6c799ap126F;
    static { NAMED.put("white purple", -0x1.6c799ap126F); LIST.add(-0x1.6c799ap126F); }

    /**
     * This color constant "black magenta" has RGBA8888 code {@code 765A76FF}, H 0.85490197, S 0.25882354, L 0.3882353, alpha 1.0, and chroma 0.24750951.
     * It can be represented as a packed float with the constant {@code -0x1.c685b4p125F}.
     * <pre>
     * <font style='background-color: #765A76;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #765A76; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #765A76;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #765A76'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #765A76'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #765A76'>&nbsp;@&nbsp;</font><font style='background-color: #765A76; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #765A76;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #765A76; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_MAGENTA = -0x1.c685b4p125F;
    static { NAMED.put("black magenta", -0x1.c685b4p125F); LIST.add(-0x1.c685b4p125F); }

    /**
     * This color constant "lead magenta" has RGBA8888 code {@code 927192FF}, H 0.85490197, S 0.24705882, L 0.48235294, alpha 1.0, and chroma 0.2910327.
     * It can be represented as a packed float with the constant {@code -0x1.f67fb4p125F}.
     * <pre>
     * <font style='background-color: #927192;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #927192; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #927192;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #927192'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #927192'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #927192'>&nbsp;@&nbsp;</font><font style='background-color: #927192; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #927192;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #927192; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_MAGENTA = -0x1.f67fb4p125F;
    static { NAMED.put("lead magenta", -0x1.f67fb4p125F); LIST.add(-0x1.f67fb4p125F); }

    /**
     * This color constant "gray magenta" has RGBA8888 code {@code AA84AAFF}, H 0.85490197, S 0.24313726, L 0.5647059, alpha 1.0, and chroma 0.33211374.
     * It can be represented as a packed float with the constant {@code -0x1.207db4p126F}.
     * <pre>
     * <font style='background-color: #AA84AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA84AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA84AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA84AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA84AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA84AA'>&nbsp;@&nbsp;</font><font style='background-color: #AA84AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA84AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA84AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_MAGENTA = -0x1.207db4p126F;
    static { NAMED.put("gray magenta", -0x1.207db4p126F); LIST.add(-0x1.207db4p126F); }

    /**
     * This color constant "silver magenta" has RGBA8888 code {@code B999B9FF}, H 0.85490197, S 0.20784314, L 0.63529414, alpha 1.0, and chroma 0.24090706.
     * It can be represented as a packed float with the constant {@code -0x1.446bb4p126F}.
     * <pre>
     * <font style='background-color: #B999B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B999B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B999B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B999B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B999B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B999B9'>&nbsp;@&nbsp;</font><font style='background-color: #B999B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B999B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B999B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_MAGENTA = -0x1.446bb4p126F;
    static { NAMED.put("silver magenta", -0x1.446bb4p126F); LIST.add(-0x1.446bb4p126F); }

    /**
     * This color constant "white magenta" has RGBA8888 code {@code CAB2CAFF}, H 0.85490197, S 0.20392157, L 0.7254902, alpha 1.0, and chroma 0.17521533.
     * It can be represented as a packed float with the constant {@code -0x1.7269b4p126F}.
     * <pre>
     * <font style='background-color: #CAB2CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CAB2CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CAB2CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CAB2CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CAB2CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CAB2CA'>&nbsp;@&nbsp;</font><font style='background-color: #CAB2CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CAB2CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CAB2CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_MAGENTA = -0x1.7269b4p126F;
    static { NAMED.put("white magenta", -0x1.7269b4p126F); LIST.add(-0x1.7269b4p126F); }

    /**
     * This color constant "drab red" has RGBA8888 code {@code 9E4144FF}, H 0.02745098, S 0.56078434, L 0.37254903, alpha 1.0, and chroma 0.72741044.
     * It can be represented as a packed float with the constant {@code -0x1.bf1e0ep125F}.
     * <pre>
     * <font style='background-color: #9E4144;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E4144; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E4144;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E4144'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E4144'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E4144'>&nbsp;@&nbsp;</font><font style='background-color: #9E4144; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E4144;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E4144; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED = -0x1.bf1e0ep125F;
    static { NAMED.put("drab red", -0x1.bf1e0ep125F); LIST.add(-0x1.bf1e0ep125F); }

    /**
     * This color constant "faded red" has RGBA8888 code {@code D15F61FF}, H 0.03137255, S 0.5019608, L 0.50980395, alpha 1.0, and chroma 0.8398732.
     * It can be represented as a packed float with the constant {@code -0x1.05001p126F}.
     * <pre>
     * <font style='background-color: #D15F61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D15F61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D15F61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D15F61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D15F61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D15F61'>&nbsp;@&nbsp;</font><font style='background-color: #D15F61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D15F61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D15F61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_RED = -0x1.05001p126F;
    static { NAMED.put("faded red", -0x1.05001p126F); LIST.add(-0x1.05001p126F); }

    /**
     * This color constant "pale red" has RGBA8888 code {@code DD9E9FFF}, H 0.03137255, S 0.4392157, L 0.6784314, alpha 1.0, and chroma 0.3583715.
     * It can be represented as a packed float with the constant {@code -0x1.5ae01p126F}.
     * <pre>
     * <font style='background-color: #DD9E9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD9E9F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD9E9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD9E9F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD9E9F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD9E9F'>&nbsp;@&nbsp;</font><font style='background-color: #DD9E9F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD9E9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD9E9F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED = -0x1.5ae01p126F;
    static { NAMED.put("pale red", -0x1.5ae01p126F); LIST.add(-0x1.5ae01p126F); }

    /**
     * This color constant "drab brown" has RGBA8888 code {@code 7A6659FF}, H 0.11372549, S 0.29411766, L 0.41568628, alpha 1.0, and chroma 0.19588666.
     * It can be represented as a packed float with the constant {@code -0x1.d4963ap125F}.
     * <pre>
     * <font style='background-color: #7A6659;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A6659; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A6659;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A6659'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A6659'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A6659'>&nbsp;@&nbsp;</font><font style='background-color: #7A6659; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A6659;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A6659; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN = -0x1.d4963ap125F;
    static { NAMED.put("drab brown", -0x1.d4963ap125F); LIST.add(-0x1.d4963ap125F); }

    /**
     * This color constant "faded brown" has RGBA8888 code {@code A08776FF}, H 0.11764706, S 0.29411766, L 0.54509807, alpha 1.0, and chroma 0.24793744.
     * It can be represented as a packed float with the constant {@code -0x1.16963cp126F}.
     * <pre>
     * <font style='background-color: #A08776;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A08776; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A08776;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A08776'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A08776'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A08776'>&nbsp;@&nbsp;</font><font style='background-color: #A08776; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A08776;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A08776; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BROWN = -0x1.16963cp126F;
    static { NAMED.put("faded brown", -0x1.16963cp126F); LIST.add(-0x1.16963cp126F); }

    /**
     * This color constant "pale brown" has RGBA8888 code {@code CAAA95FF}, H 0.11764706, S 0.2901961, L 0.6862745, alpha 1.0, and chroma 0.30237386.
     * It can be represented as a packed float with the constant {@code -0x1.5e943cp126F}.
     * <pre>
     * <font style='background-color: #CAAA95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CAAA95; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CAAA95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CAAA95'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CAAA95'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CAAA95'>&nbsp;@&nbsp;</font><font style='background-color: #CAAA95; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CAAA95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CAAA95; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN = -0x1.5e943cp126F;
    static { NAMED.put("pale brown", -0x1.5e943cp126F); LIST.add(-0x1.5e943cp126F); }

    /**
     * This color constant "drab orange" has RGBA8888 code {@code 885946FF}, H 0.078431375, S 0.5294118, L 0.39215687, alpha 1.0, and chroma 0.42954808.
     * It can be represented as a packed float with the constant {@code -0x1.c90e28p125F}.
     * <pre>
     * <font style='background-color: #885946;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #885946; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #885946;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #885946'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #885946'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #885946'>&nbsp;@&nbsp;</font><font style='background-color: #885946; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #885946;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #885946; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE = -0x1.c90e28p125F;
    static { NAMED.put("drab orange", -0x1.c90e28p125F); LIST.add(-0x1.c90e28p125F); }

    /**
     * This color constant "faded orange" has RGBA8888 code {@code B4775EFF}, H 0.08235294, S 0.5254902, L 0.52156866, alpha 1.0, and chroma 0.54107356.
     * It can be represented as a packed float with the constant {@code -0x1.0b0c2ap126F}.
     * <pre>
     * <font style='background-color: #B4775E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4775E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4775E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4775E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4775E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4775E'>&nbsp;@&nbsp;</font><font style='background-color: #B4775E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4775E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4775E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_ORANGE = -0x1.0b0c2ap126F;
    static { NAMED.put("faded orange", -0x1.0b0c2ap126F); LIST.add(-0x1.0b0c2ap126F); }

    /**
     * This color constant "pale orange" has RGBA8888 code {@code DDA08AFF}, H 0.078431375, S 0.41960785, L 0.6784314, alpha 1.0, and chroma 0.4071556.
     * It can be represented as a packed float with the constant {@code -0x1.5ad628p126F}.
     * <pre>
     * <font style='background-color: #DDA08A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDA08A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDA08A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDA08A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDA08A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDA08A'>&nbsp;@&nbsp;</font><font style='background-color: #DDA08A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDA08A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDA08A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE = -0x1.5ad628p126F;
    static { NAMED.put("pale orange", -0x1.5ad628p126F); LIST.add(-0x1.5ad628p126F); }

    /**
     * This color constant "drab saffron" has RGBA8888 code {@code 7B695AFF}, H 0.12941177, S 0.30980393, L 0.42352942, alpha 1.0, and chroma 0.19376534.
     * It can be represented as a packed float with the constant {@code -0x1.d89e42p125F}.
     * <pre>
     * <font style='background-color: #7B695A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B695A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B695A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B695A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B695A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B695A'>&nbsp;@&nbsp;</font><font style='background-color: #7B695A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B695A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B695A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON = -0x1.d89e42p125F;
    static { NAMED.put("drab saffron", -0x1.d89e42p125F); LIST.add(-0x1.d89e42p125F); }

    /**
     * This color constant "faded saffron" has RGBA8888 code {@code A48C79FF}, H 0.1254902, S 0.3019608, L 0.5647059, alpha 1.0, and chroma 0.25265828.
     * It can be represented as a packed float with the constant {@code -0x1.209a4p126F}.
     * <pre>
     * <font style='background-color: #A48C79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A48C79; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A48C79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A48C79'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A48C79'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A48C79'>&nbsp;@&nbsp;</font><font style='background-color: #A48C79; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A48C79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A48C79; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_SAFFRON = -0x1.209a4p126F;
    static { NAMED.put("faded saffron", -0x1.209a4p126F); LIST.add(-0x1.209a4p126F); }

    /**
     * This color constant "pale saffron" has RGBA8888 code {@code C9AC95FF}, H 0.1254902, S 0.29803923, L 0.69411767, alpha 1.0, and chroma 0.3013354.
     * It can be represented as a packed float with the constant {@code -0x1.62984p126F}.
     * <pre>
     * <font style='background-color: #C9AC95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9AC95; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9AC95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9AC95'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9AC95'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9AC95'>&nbsp;@&nbsp;</font><font style='background-color: #C9AC95; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9AC95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9AC95; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON = -0x1.62984p126F;
    static { NAMED.put("pale saffron", -0x1.62984p126F); LIST.add(-0x1.62984p126F); }

    /**
     * This color constant "drab yellow" has RGBA8888 code {@code 6A6A47FF}, H 0.23921569, S 0.54509807, L 0.40784314, alpha 1.0, and chroma 0.2649915.
     * It can be represented as a packed float with the constant {@code -0x1.d1167ap125F}.
     * <pre>
     * <font style='background-color: #6A6A47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A6A47; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A6A47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A6A47'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A6A47'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A6A47'>&nbsp;@&nbsp;</font><font style='background-color: #6A6A47; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A6A47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A6A47; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW = -0x1.d1167ap125F;
    static { NAMED.put("drab yellow", -0x1.d1167ap125F); LIST.add(-0x1.d1167ap125F); }

    /**
     * This color constant "faded yellow" has RGBA8888 code {@code 8B8B5EFF}, H 0.23921569, S 0.5411765, L 0.53333336, alpha 1.0, and chroma 0.33964118.
     * It can be represented as a packed float with the constant {@code -0x1.11147ap126F}.
     * <pre>
     * <font style='background-color: #8B8B5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B8B5E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B8B5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B8B5E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B8B5E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B8B5E'>&nbsp;@&nbsp;</font><font style='background-color: #8B8B5E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B8B5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B8B5E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_YELLOW = -0x1.11147ap126F;
    static { NAMED.put("faded yellow", -0x1.11147ap126F); LIST.add(-0x1.11147ap126F); }

    /**
     * This color constant "pale yellow" has RGBA8888 code {@code B2B27AFF}, H 0.23921569, S 0.5254902, L 0.68235296, alpha 1.0, and chroma 0.41389146.
     * It can be represented as a packed float with the constant {@code -0x1.5d0c7ap126F}.
     * <pre>
     * <font style='background-color: #B2B27A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2B27A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2B27A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2B27A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2B27A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2B27A'>&nbsp;@&nbsp;</font><font style='background-color: #B2B27A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2B27A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2B27A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.5d0c7ap126F;
    static { NAMED.put("pale yellow", -0x1.5d0c7ap126F); LIST.add(-0x1.5d0c7ap126F); }

    /**
     * This color constant "drab lime" has RGBA8888 code {@code 566645FF}, H 0.3019608, S 0.49411765, L 0.38039216, alpha 1.0, and chroma 0.2485272.
     * It can be represented as a packed float with the constant {@code -0x1.c2fc9ap125F}.
     * <pre>
     * <font style='background-color: #566645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #566645; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #566645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #566645'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #566645'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #566645'>&nbsp;@&nbsp;</font><font style='background-color: #566645; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #566645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #566645; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME = -0x1.c2fc9ap125F;
    static { NAMED.put("drab lime", -0x1.c2fc9ap125F); LIST.add(-0x1.c2fc9ap125F); }

    /**
     * This color constant "faded lime" has RGBA8888 code {@code 758A5FFF}, H 0.3019608, S 0.4862745, L 0.5137255, alpha 1.0, and chroma 0.32618397.
     * It can be represented as a packed float with the constant {@code -0x1.06f89ap126F}.
     * <pre>
     * <font style='background-color: #758A5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #758A5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #758A5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #758A5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #758A5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #758A5F'>&nbsp;@&nbsp;</font><font style='background-color: #758A5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #758A5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #758A5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_LIME = -0x1.06f89ap126F;
    static { NAMED.put("faded lime", -0x1.06f89ap126F); LIST.add(-0x1.06f89ap126F); }

    /**
     * This color constant "pale lime" has RGBA8888 code {@code 9BB77FFF}, H 0.30588236, S 0.47843137, L 0.6784314, alpha 1.0, and chroma 0.42007512.
     * It can be represented as a packed float with the constant {@code -0x1.5af49cp126F}.
     * <pre>
     * <font style='background-color: #9BB77F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BB77F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BB77F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9BB77F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9BB77F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9BB77F'>&nbsp;@&nbsp;</font><font style='background-color: #9BB77F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BB77F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BB77F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME = -0x1.5af49cp126F;
    static { NAMED.put("pale lime", -0x1.5af49cp126F); LIST.add(-0x1.5af49cp126F); }

    /**
     * This color constant "drab green" has RGBA8888 code {@code 4A7549FF}, H 0.3529412, S 0.5411765, L 0.41960785, alpha 1.0, and chroma 0.3749504.
     * It can be represented as a packed float with the constant {@code -0x1.d714b4p125F}.
     * <pre>
     * <font style='background-color: #4A7549;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A7549; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A7549;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A7549'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A7549'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A7549'>&nbsp;@&nbsp;</font><font style='background-color: #4A7549; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A7549;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A7549; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.d714b4p125F;
    static { NAMED.put("drab green", -0x1.d714b4p125F); LIST.add(-0x1.d714b4p125F); }

    /**
     * This color constant "faded green" has RGBA8888 code {@code 639B61FF}, H 0.3529412, S 0.54509807, L 0.5529412, alpha 1.0, and chroma 0.4906384.
     * It can be represented as a packed float with the constant {@code -0x1.1b16b4p126F}.
     * <pre>
     * <font style='background-color: #639B61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #639B61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #639B61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #639B61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #639B61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #639B61'>&nbsp;@&nbsp;</font><font style='background-color: #639B61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #639B61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #639B61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_GREEN = -0x1.1b16b4p126F;
    static { NAMED.put("faded green", -0x1.1b16b4p126F); LIST.add(-0x1.1b16b4p126F); }

    /**
     * This color constant "pale green" has RGBA8888 code {@code 7CC07AFF}, H 0.3529412, S 0.5294118, L 0.6862745, alpha 1.0, and chroma 0.5811951.
     * It can be represented as a packed float with the constant {@code -0x1.5f0eb4p126F}.
     * <pre>
     * <font style='background-color: #7CC07A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CC07A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CC07A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7CC07A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7CC07A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7CC07A'>&nbsp;@&nbsp;</font><font style='background-color: #7CC07A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CC07A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CC07A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.5f0eb4p126F;
    static { NAMED.put("pale green", -0x1.5f0eb4p126F); LIST.add(-0x1.5f0eb4p126F); }

    /**
     * This color constant "drab cyan" has RGBA8888 code {@code 4C6C6CFF}, H 0.53333336, S 0.5019608, L 0.4, alpha 1.0, and chroma 0.17176744.
     * It can be represented as a packed float with the constant {@code -0x1.cd011p125F}.
     * <pre>
     * <font style='background-color: #4C6C6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C6C6C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C6C6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4C6C6C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4C6C6C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4C6C6C'>&nbsp;@&nbsp;</font><font style='background-color: #4C6C6C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C6C6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C6C6C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN = -0x1.cd011p125F;
    static { NAMED.put("drab cyan", -0x1.cd011p125F); LIST.add(-0x1.cd011p125F); }

    /**
     * This color constant "faded cyan" has RGBA8888 code {@code 668E8EFF}, H 0.53333336, S 0.4862745, L 0.5254902, alpha 1.0, and chroma 0.21587907.
     * It can be represented as a packed float with the constant {@code -0x1.0cf91p126F}.
     * <pre>
     * <font style='background-color: #668E8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #668E8E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #668E8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #668E8E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #668E8E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #668E8E'>&nbsp;@&nbsp;</font><font style='background-color: #668E8E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #668E8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #668E8E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_CYAN = -0x1.0cf91p126F;
    static { NAMED.put("faded cyan", -0x1.0cf91p126F); LIST.add(-0x1.0cf91p126F); }

    /**
     * This color constant "pale cyan" has RGBA8888 code {@code 85B7B7FF}, H 0.53333336, S 0.4745098, L 0.6784314, alpha 1.0, and chroma 0.26667172.
     * It can be represented as a packed float with the constant {@code -0x1.5af31p126F}.
     * <pre>
     * <font style='background-color: #85B7B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #85B7B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #85B7B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #85B7B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #85B7B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #85B7B7'>&nbsp;@&nbsp;</font><font style='background-color: #85B7B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #85B7B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #85B7B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN = -0x1.5af31p126F;
    static { NAMED.put("pale cyan", -0x1.5af31p126F); LIST.add(-0x1.5af31p126F); }

    /**
     * This color constant "drab blue" has RGBA8888 code {@code 6663B3FF}, H 0.7411765, S 0.49411765, L 0.42352942, alpha 1.0, and chroma 0.6384417.
     * It can be represented as a packed float with the constant {@code -0x1.d8fd7ap125F}.
     * <pre>
     * <font style='background-color: #6663B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6663B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6663B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6663B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6663B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6663B3'>&nbsp;@&nbsp;</font><font style='background-color: #6663B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6663B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6663B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE = -0x1.d8fd7ap125F;
    static { NAMED.put("drab blue", -0x1.d8fd7ap125F); LIST.add(-0x1.d8fd7ap125F); }

    /**
     * This color constant "faded blue" has RGBA8888 code {@code 8F8DCBFF}, H 0.7411765, S 0.47058824, L 0.57254905, alpha 1.0, and chroma 0.47542706.
     * It can be represented as a packed float with the constant {@code -0x1.24f17ap126F}.
     * <pre>
     * <font style='background-color: #8F8DCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8DCB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8DCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8DCB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8DCB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8DCB'>&nbsp;@&nbsp;</font><font style='background-color: #8F8DCB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8DCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8DCB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.24f17ap126F;
    static { NAMED.put("faded blue", -0x1.24f17ap126F); LIST.add(-0x1.24f17ap126F); }

    /**
     * This color constant "pale blue" has RGBA8888 code {@code B0AFDBFF}, H 0.7411765, S 0.45882353, L 0.69803923, alpha 1.0, and chroma 0.32135078.
     * It can be represented as a packed float with the constant {@code -0x1.64eb7ap126F}.
     * <pre>
     * <font style='background-color: #B0AFDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0AFDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0AFDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0AFDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0AFDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0AFDB'>&nbsp;@&nbsp;</font><font style='background-color: #B0AFDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0AFDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0AFDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.64eb7ap126F;
    static { NAMED.put("pale blue", -0x1.64eb7ap126F); LIST.add(-0x1.64eb7ap126F); }

    /**
     * This color constant "drab violet" has RGBA8888 code {@code 785AAAFF}, H 0.77254903, S 0.4745098, L 0.4117647, alpha 1.0, and chroma 0.6311361.
     * It can be represented as a packed float with the constant {@code -0x1.d2f38ap125F}.
     * <pre>
     * <font style='background-color: #785AAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #785AAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #785AAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #785AAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #785AAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #785AAA'>&nbsp;@&nbsp;</font><font style='background-color: #785AAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #785AAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #785AAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET = -0x1.d2f38ap125F;
    static { NAMED.put("drab violet", -0x1.d2f38ap125F); LIST.add(-0x1.d2f38ap125F); }

    /**
     * This color constant "faded violet" has RGBA8888 code {@code 9680C0FF}, H 0.77254903, S 0.42352942, L 0.5411765, alpha 1.0, and chroma 0.46872118.
     * It can be represented as a packed float with the constant {@code -0x1.14d98ap126F}.
     * <pre>
     * <font style='background-color: #9680C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9680C0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9680C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9680C0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9680C0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9680C0'>&nbsp;@&nbsp;</font><font style='background-color: #9680C0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9680C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9680C0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_VIOLET = -0x1.14d98ap126F;
    static { NAMED.put("faded violet", -0x1.14d98ap126F); LIST.add(-0x1.14d98ap126F); }

    /**
     * This color constant "pale violet" has RGBA8888 code {@code B6A8D5FF}, H 0.77254903, S 0.41568628, L 0.68235296, alpha 1.0, and chroma 0.31540805.
     * It can be represented as a packed float with the constant {@code -0x1.5cd58ap126F}.
     * <pre>
     * <font style='background-color: #B6A8D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6A8D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6A8D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6A8D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6A8D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6A8D5'>&nbsp;@&nbsp;</font><font style='background-color: #B6A8D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6A8D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6A8D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.5cd58ap126F;
    static { NAMED.put("pale violet", -0x1.5cd58ap126F); LIST.add(-0x1.5cd58ap126F); }

    /**
     * This color constant "drab purple" has RGBA8888 code {@code 804F9BFF}, H 0.80784315, S 0.54509807, L 0.3882353, alpha 1.0, and chroma 0.5948539.
     * It can be represented as a packed float with the constant {@code -0x1.c7179cp125F}.
     * <pre>
     * <font style='background-color: #804F9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #804F9B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #804F9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #804F9B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #804F9B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #804F9B'>&nbsp;@&nbsp;</font><font style='background-color: #804F9B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #804F9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #804F9B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE = -0x1.c7179cp125F;
    static { NAMED.put("drab purple", -0x1.c7179cp125F); LIST.add(-0x1.c7179cp125F); }

    /**
     * This color constant "faded purple" has RGBA8888 code {@code A370C2FF}, H 0.8039216, S 0.4745098, L 0.5176471, alpha 1.0, and chroma 0.5850134.
     * It can be represented as a packed float with the constant {@code -0x1.08f39ap126F}.
     * <pre>
     * <font style='background-color: #A370C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A370C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A370C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A370C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A370C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A370C2'>&nbsp;@&nbsp;</font><font style='background-color: #A370C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A370C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A370C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_PURPLE = -0x1.08f39ap126F;
    static { NAMED.put("faded purple", -0x1.08f39ap126F); LIST.add(-0x1.08f39ap126F); }

    /**
     * This color constant "pale purple" has RGBA8888 code {@code C2A2D8FF}, H 0.8039216, S 0.45490196, L 0.6784314, alpha 1.0, and chroma 0.37374374.
     * It can be represented as a packed float with the constant {@code -0x1.5ae99ap126F}.
     * <pre>
     * <font style='background-color: #C2A2D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2A2D8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2A2D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C2A2D8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C2A2D8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C2A2D8'>&nbsp;@&nbsp;</font><font style='background-color: #C2A2D8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2A2D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2A2D8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.5ae99ap126F;
    static { NAMED.put("pale purple", -0x1.5ae99ap126F); LIST.add(-0x1.5ae99ap126F); }

    /**
     * This color constant "drab magenta" has RGBA8888 code {@code 915691FF}, H 0.85490197, S 0.49019608, L 0.41960785, alpha 1.0, and chroma 0.50540465.
     * It can be represented as a packed float with the constant {@code -0x1.d6fbb4p125F}.
     * <pre>
     * <font style='background-color: #915691;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #915691; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #915691;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #915691'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #915691'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #915691'>&nbsp;@&nbsp;</font><font style='background-color: #915691; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #915691;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #915691; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA = -0x1.d6fbb4p125F;
    static { NAMED.put("drab magenta", -0x1.d6fbb4p125F); LIST.add(-0x1.d6fbb4p125F); }

    /**
     * This color constant "faded magenta" has RGBA8888 code {@code C274C2FF}, H 0.85490197, S 0.4862745, L 0.56078434, alpha 1.0, and chroma 0.659935.
     * It can be represented as a packed float with the constant {@code -0x1.1ef9b4p126F}.
     * <pre>
     * <font style='background-color: #C274C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C274C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C274C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C274C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C274C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C274C2'>&nbsp;@&nbsp;</font><font style='background-color: #C274C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C274C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C274C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_MAGENTA = -0x1.1ef9b4p126F;
    static { NAMED.put("faded magenta", -0x1.1ef9b4p126F); LIST.add(-0x1.1ef9b4p126F); }

    /**
     * This color constant "pale magenta" has RGBA8888 code {@code D69FD6FF}, H 0.85490197, S 0.41568628, L 0.6901961, alpha 1.0, and chroma 0.40622127.
     * It can be represented as a packed float with the constant {@code -0x1.60d5b4p126F}.
     * <pre>
     * <font style='background-color: #D69FD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D69FD6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D69FD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D69FD6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D69FD6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D69FD6'>&nbsp;@&nbsp;</font><font style='background-color: #D69FD6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D69FD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D69FD6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA = -0x1.60d5b4p126F;
    static { NAMED.put("pale magenta", -0x1.60d5b4p126F); LIST.add(-0x1.60d5b4p126F); }

    /**
     * This color constant "deep pure red" has RGBA8888 code {@code B12D31FF}, H 0.03137255, S 0.8156863, L 0.37254903, alpha 1.0, and chroma 1.0869267.
     * It can be represented as a packed float with the constant {@code -0x1.bfa01p125F}.
     * <pre>
     * <font style='background-color: #B12D31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B12D31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B12D31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B12D31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B12D31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B12D31'>&nbsp;@&nbsp;</font><font style='background-color: #B12D31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B12D31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B12D31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_RED = -0x1.bfa01p125F;
    static { NAMED.put("deep pure red", -0x1.bfa01p125F); LIST.add(-0x1.bfa01p125F); }

    /**
     * This color constant "true pure red" has RGBA8888 code {@code EA474CFF}, H 0.03137255, S 0.7490196, L 0.50980395, alpha 1.0, and chroma 1.2532482.
     * It can be represented as a packed float with the constant {@code -0x1.057e1p126F}.
     * <pre>
     * <font style='background-color: #EA474C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA474C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA474C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EA474C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EA474C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EA474C'>&nbsp;@&nbsp;</font><font style='background-color: #EA474C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA474C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA474C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_RED = -0x1.057e1p126F;
    static { NAMED.put("true pure red", -0x1.057e1p126F); LIST.add(-0x1.057e1p126F); }

    /**
     * This color constant "bright pure red" has RGBA8888 code {@code EF9697FF}, H 0.03137255, S 0.6509804, L 0.6784314, alpha 1.0, and chroma 0.53115773.
     * It can be represented as a packed float with the constant {@code -0x1.5b4c1p126F}.
     * <pre>
     * <font style='background-color: #EF9697;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF9697; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF9697;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF9697'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF9697'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF9697'>&nbsp;@&nbsp;</font><font style='background-color: #EF9697; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF9697;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF9697; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_RED = -0x1.5b4c1p126F;
    static { NAMED.put("bright pure red", -0x1.5b4c1p126F); LIST.add(-0x1.5b4c1p126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code A8513AFF}, H 0.05882353, S 0.7372549, L 0.41568628, alpha 1.0, and chroma 0.7746176.
     * It can be represented as a packed float with the constant {@code -0x1.d5781ep125F}.
     * <pre>
     * <font style='background-color: #A8513A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8513A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8513A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8513A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8513A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8513A'>&nbsp;@&nbsp;</font><font style='background-color: #A8513A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8513A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8513A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.d5781ep125F;
    static { NAMED.put("deep brown red", -0x1.d5781ep125F); LIST.add(-0x1.d5781ep125F); }

    /**
     * This color constant "true brown red" has RGBA8888 code {@code DB6B4EFF}, H 0.05490196, S 0.7254902, L 0.54509807, alpha 1.0, and chroma 1.0343465.
     * It can be represented as a packed float with the constant {@code -0x1.17721cp126F}.
     * <pre>
     * <font style='background-color: #DB6B4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB6B4E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB6B4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB6B4E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB6B4E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB6B4E'>&nbsp;@&nbsp;</font><font style='background-color: #DB6B4E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB6B4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB6B4E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_RED = -0x1.17721cp126F;
    static { NAMED.put("true brown red", -0x1.17721cp126F); LIST.add(-0x1.17721cp126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code EB9C8EFF}, H 0.05490196, S 0.5882353, L 0.6862745, alpha 1.0, and chroma 0.4969119.
     * It can be represented as a packed float with the constant {@code -0x1.5f2c1cp126F}.
     * <pre>
     * <font style='background-color: #EB9C8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB9C8E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB9C8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EB9C8E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EB9C8E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EB9C8E'>&nbsp;@&nbsp;</font><font style='background-color: #EB9C8E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB9C8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB9C8E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.5f2c1cp126F;
    static { NAMED.put("bright brown red", -0x1.5f2c1cp126F); LIST.add(-0x1.5f2c1cp126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code 8E563AFF}, H 0.08235294, S 0.6901961, L 0.39215687, alpha 1.0, and chroma 0.5411459.
     * It can be represented as a packed float with the constant {@code -0x1.c9602ap125F}.
     * <pre>
     * <font style='background-color: #8E563A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E563A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E563A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E563A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E563A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E563A'>&nbsp;@&nbsp;</font><font style='background-color: #8E563A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E563A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E563A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.c9602ap125F;
    static { NAMED.put("deep red brown", -0x1.c9602ap125F); LIST.add(-0x1.c9602ap125F); }

    /**
     * This color constant "true red brown" has RGBA8888 code {@code BC744FFF}, H 0.08627451, S 0.68235296, L 0.52156866, alpha 1.0, and chroma 0.6801037.
     * It can be represented as a packed float with the constant {@code -0x1.0b5c2cp126F}.
     * <pre>
     * <font style='background-color: #BC744F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC744F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC744F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BC744F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BC744F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BC744F'>&nbsp;@&nbsp;</font><font style='background-color: #BC744F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC744F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC744F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_BROWN = -0x1.0b5c2cp126F;
    static { NAMED.put("true red brown", -0x1.0b5c2cp126F); LIST.add(-0x1.0b5c2cp126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code E89C79FF}, H 0.08627451, S 0.5411765, L 0.6784314, alpha 1.0, and chroma 0.5473135.
     * It can be represented as a packed float with the constant {@code -0x1.5b142cp126F}.
     * <pre>
     * <font style='background-color: #E89C79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E89C79; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E89C79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E89C79'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E89C79'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E89C79'>&nbsp;@&nbsp;</font><font style='background-color: #E89C79; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E89C79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E89C79; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.5b142cp126F;
    static { NAMED.put("bright red brown", -0x1.5b142cp126F); LIST.add(-0x1.5b142cp126F); }

    /**
     * This color constant "deep pure brown" has RGBA8888 code {@code 836652FF}, H 0.11372549, S 0.43137255, L 0.42352942, alpha 1.0, and chroma 0.29252464.
     * It can be represented as a packed float with the constant {@code -0x1.d8dc3ap125F}.
     * <pre>
     * <font style='background-color: #836652;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #836652; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #836652;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #836652'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #836652'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #836652'>&nbsp;@&nbsp;</font><font style='background-color: #836652; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #836652;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #836652; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BROWN = -0x1.d8dc3ap125F;
    static { NAMED.put("deep pure brown", -0x1.d8dc3ap125F); LIST.add(-0x1.d8dc3ap125F); }

    /**
     * This color constant "true pure brown" has RGBA8888 code {@code AF896EFF}, H 0.11372549, S 0.43529412, L 0.5647059, alpha 1.0, and chroma 0.38758412.
     * It can be represented as a packed float with the constant {@code -0x1.20de3ap126F}.
     * <pre>
     * <font style='background-color: #AF896E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF896E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF896E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF896E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF896E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF896E'>&nbsp;@&nbsp;</font><font style='background-color: #AF896E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF896E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF896E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BROWN = -0x1.20de3ap126F;
    static { NAMED.put("true pure brown", -0x1.20de3ap126F); LIST.add(-0x1.20de3ap126F); }

    /**
     * This color constant "bright pure brown" has RGBA8888 code {@code D5A887FF}, H 0.11764706, S 0.43137255, L 0.6901961, alpha 1.0, and chroma 0.45179936.
     * It can be represented as a packed float with the constant {@code -0x1.60dc3cp126F}.
     * <pre>
     * <font style='background-color: #D5A887;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5A887; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5A887;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5A887'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5A887'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5A887'>&nbsp;@&nbsp;</font><font style='background-color: #D5A887; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5A887;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5A887; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BROWN = -0x1.60dc3cp126F;
    static { NAMED.put("bright pure brown", -0x1.60dc3cp126F); LIST.add(-0x1.60dc3cp126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code 856048FF}, H 0.105882354, S 0.5372549, L 0.40784314, alpha 1.0, and chroma 0.36818433.
     * It can be represented as a packed float with the constant {@code -0x1.d11236p125F}.
     * <pre>
     * <font style='background-color: #856048;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #856048; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #856048;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #856048'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #856048'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #856048'>&nbsp;@&nbsp;</font><font style='background-color: #856048; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #856048;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #856048; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.d11236p125F;
    static { NAMED.put("deep orange brown", -0x1.d11236p125F); LIST.add(-0x1.d11236p125F); }

    /**
     * This color constant "true orange brown" has RGBA8888 code {@code AE7E60FF}, H 0.101960786, S 0.5254902, L 0.53333336, alpha 1.0, and chroma 0.476823.
     * It can be represented as a packed float with the constant {@code -0x1.110c34p126F}.
     * <pre>
     * <font style='background-color: #AE7E60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE7E60; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE7E60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE7E60'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE7E60'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE7E60'>&nbsp;@&nbsp;</font><font style='background-color: #AE7E60; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE7E60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE7E60; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_BROWN = -0x1.110c34p126F;
    static { NAMED.put("true orange brown", -0x1.110c34p126F); LIST.add(-0x1.110c34p126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code DEA27CFF}, H 0.101960786, S 0.5176471, L 0.68235296, alpha 1.0, and chroma 0.5647443.
     * It can be represented as a packed float with the constant {@code -0x1.5d0834p126F}.
     * <pre>
     * <font style='background-color: #DEA27C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEA27C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEA27C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEA27C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEA27C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEA27C'>&nbsp;@&nbsp;</font><font style='background-color: #DEA27C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEA27C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEA27C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.5d0834p126F;
    static { NAMED.put("bright orange brown", -0x1.5d0834p126F); LIST.add(-0x1.5d0834p126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code 825740FF}, H 0.09019608, S 0.5803922, L 0.38039216, alpha 1.0, and chroma 0.41461194.
     * It can be represented as a packed float with the constant {@code -0x1.c3282ep125F}.
     * <pre>
     * <font style='background-color: #825740;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #825740; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #825740;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #825740'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #825740'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #825740'>&nbsp;@&nbsp;</font><font style='background-color: #825740; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #825740;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #825740; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.c3282ep125F;
    static { NAMED.put("deep brown orange", -0x1.c3282ep125F); LIST.add(-0x1.c3282ep125F); }

    /**
     * This color constant "true brown orange" has RGBA8888 code {@code AF7759FF}, H 0.09411765, S 0.5686275, L 0.5137255, alpha 1.0, and chroma 0.52604896.
     * It can be represented as a packed float with the constant {@code -0x1.07223p126F}.
     * <pre>
     * <font style='background-color: #AF7759;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF7759; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF7759;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF7759'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF7759'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF7759'>&nbsp;@&nbsp;</font><font style='background-color: #AF7759; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF7759;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF7759; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_ORANGE = -0x1.07223p126F;
    static { NAMED.put("true brown orange", -0x1.07223p126F); LIST.add(-0x1.07223p126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code E19F7EFF}, H 0.09019608, S 0.49411765, L 0.6784314, alpha 1.0, and chroma 0.5109917.
     * It can be represented as a packed float with the constant {@code -0x1.5afc2ep126F}.
     * <pre>
     * <font style='background-color: #E19F7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E19F7E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E19F7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E19F7E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E19F7E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E19F7E'>&nbsp;@&nbsp;</font><font style='background-color: #E19F7E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E19F7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E19F7E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.5afc2ep126F;
    static { NAMED.put("bright brown orange", -0x1.5afc2ep126F); LIST.add(-0x1.5afc2ep126F); }

    /**
     * This color constant "deep pure orange" has RGBA8888 code {@code 9E5935FF}, H 0.08235294, S 0.7921569, L 0.41960785, alpha 1.0, and chroma 0.6631188.
     * It can be represented as a packed float with the constant {@code -0x1.d7942ap125F}.
     * <pre>
     * <font style='background-color: #9E5935;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E5935; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E5935;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E5935'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E5935'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E5935'>&nbsp;@&nbsp;</font><font style='background-color: #9E5935; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E5935;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E5935; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_ORANGE = -0x1.d7942ap125F;
    static { NAMED.put("deep pure orange", -0x1.d7942ap125F); LIST.add(-0x1.d7942ap125F); }

    /**
     * This color constant "true pure orange" has RGBA8888 code {@code CF7749FF}, H 0.08235294, S 0.77254903, L 0.5529412, alpha 1.0, and chroma 0.84015256.
     * It can be represented as a packed float with the constant {@code -0x1.1b8a2ap126F}.
     * <pre>
     * <font style='background-color: #CF7749;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF7749; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF7749;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CF7749'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CF7749'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CF7749'>&nbsp;@&nbsp;</font><font style='background-color: #CF7749; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF7749;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF7749; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_ORANGE = -0x1.1b8a2ap126F;
    static { NAMED.put("true pure orange", -0x1.1b8a2ap126F); LIST.add(-0x1.1b8a2ap126F); }

    /**
     * This color constant "bright pure orange" has RGBA8888 code {@code EF9C79FF}, H 0.08235294, S 0.627451, L 0.6862745, alpha 1.0, and chroma 0.59738463.
     * It can be represented as a packed float with the constant {@code -0x1.5f402ap126F}.
     * <pre>
     * <font style='background-color: #EF9C79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF9C79; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF9C79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF9C79'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF9C79'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF9C79'>&nbsp;@&nbsp;</font><font style='background-color: #EF9C79; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF9C79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF9C79; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_ORANGE = -0x1.5f402ap126F;
    static { NAMED.put("bright pure orange", -0x1.5f402ap126F); LIST.add(-0x1.5f402ap126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code 865D46FF}, H 0.09803922, S 0.54509807, L 0.4, alpha 1.0, and chroma 0.38611904.
     * It can be represented as a packed float with the constant {@code -0x1.cd1632p125F}.
     * <pre>
     * <font style='background-color: #865D46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #865D46; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #865D46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #865D46'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #865D46'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #865D46'>&nbsp;@&nbsp;</font><font style='background-color: #865D46; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #865D46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #865D46; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.cd1632p125F;
    static { NAMED.put("deep saffron orange", -0x1.cd1632p125F); LIST.add(-0x1.cd1632p125F); }

    /**
     * This color constant "true saffron orange" has RGBA8888 code {@code AF7B5EFF}, H 0.09803922, S 0.53333336, L 0.5254902, alpha 1.0, and chroma 0.49012083.
     * It can be represented as a packed float with the constant {@code -0x1.0d1032p126F}.
     * <pre>
     * <font style='background-color: #AF7B5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF7B5E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF7B5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF7B5E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF7B5E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF7B5E'>&nbsp;@&nbsp;</font><font style='background-color: #AF7B5E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF7B5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF7B5E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_ORANGE = -0x1.0d1032p126F;
    static { NAMED.put("true saffron orange", -0x1.0d1032p126F); LIST.add(-0x1.0d1032p126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code DFA07EFF}, H 0.09411765, S 0.49411765, L 0.6784314, alpha 1.0, and chroma 0.52310956.
     * It can be represented as a packed float with the constant {@code -0x1.5afc3p126F}.
     * <pre>
     * <font style='background-color: #DFA07E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFA07E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFA07E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DFA07E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DFA07E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DFA07E'>&nbsp;@&nbsp;</font><font style='background-color: #DFA07E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFA07E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFA07E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.5afc3p126F;
    static { NAMED.put("bright saffron orange", -0x1.5afc3p126F); LIST.add(-0x1.5afc3p126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code 87654EFF}, H 0.10980392, S 0.49411765, L 0.42352942, alpha 1.0, and chroma 0.34283355.
     * It can be represented as a packed float with the constant {@code -0x1.d8fc38p125F}.
     * <pre>
     * <font style='background-color: #87654E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87654E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87654E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87654E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87654E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87654E'>&nbsp;@&nbsp;</font><font style='background-color: #87654E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87654E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87654E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.d8fc38p125F;
    static { NAMED.put("deep orange saffron", -0x1.d8fc38p125F); LIST.add(-0x1.d8fc38p125F); }

    /**
     * This color constant "true orange saffron" has RGBA8888 code {@code B68A6BFF}, H 0.11372549, S 0.4862745, L 0.57254905, alpha 1.0, and chroma 0.43856114.
     * It can be represented as a packed float with the constant {@code -0x1.24f83ap126F}.
     * <pre>
     * <font style='background-color: #B68A6B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B68A6B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B68A6B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B68A6B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B68A6B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B68A6B'>&nbsp;@&nbsp;</font><font style='background-color: #B68A6B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B68A6B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B68A6B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_SAFFRON = -0x1.24f83ap126F;
    static { NAMED.put("true orange saffron", -0x1.24f83ap126F); LIST.add(-0x1.24f83ap126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code DDA883FF}, H 0.10980392, S 0.47843137, L 0.69803923, alpha 1.0, and chroma 0.50725955.
     * It can be represented as a packed float with the constant {@code -0x1.64f438p126F}.
     * <pre>
     * <font style='background-color: #DDA883;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDA883; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDA883;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDA883'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDA883'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDA883'>&nbsp;@&nbsp;</font><font style='background-color: #DDA883; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDA883;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDA883; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.64f438p126F;
    static { NAMED.put("bright orange saffron", -0x1.64f438p126F); LIST.add(-0x1.64f438p126F); }

    /**
     * This color constant "deep pure saffron" has RGBA8888 code {@code 7E644EFF}, H 0.1254902, S 0.45882353, L 0.4117647, alpha 1.0, and chroma 0.28454715.
     * It can be represented as a packed float with the constant {@code -0x1.d2ea4p125F}.
     * <pre>
     * <font style='background-color: #7E644E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E644E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E644E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E644E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E644E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E644E'>&nbsp;@&nbsp;</font><font style='background-color: #7E644E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E644E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E644E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_SAFFRON = -0x1.d2ea4p125F;
    static { NAMED.put("deep pure saffron", -0x1.d2ea4p125F); LIST.add(-0x1.d2ea4p125F); }

    /**
     * This color constant "true pure saffron" has RGBA8888 code {@code A58568FF}, H 0.12941177, S 0.45490196, L 0.5411765, alpha 1.0, and chroma 0.35904238.
     * It can be represented as a packed float with the constant {@code -0x1.14e842p126F}.
     * <pre>
     * <font style='background-color: #A58568;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A58568; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A58568;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A58568'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A58568'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A58568'>&nbsp;@&nbsp;</font><font style='background-color: #A58568; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A58568;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A58568; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_SAFFRON = -0x1.14e842p126F;
    static { NAMED.put("true pure saffron", -0x1.14e842p126F); LIST.add(-0x1.14e842p126F); }

    /**
     * This color constant "bright pure saffron" has RGBA8888 code {@code D0A884FF}, H 0.12941177, S 0.44705883, L 0.6862745, alpha 1.0, and chroma 0.4390911.
     * It can be represented as a packed float with the constant {@code -0x1.5ee442p126F}.
     * <pre>
     * <font style='background-color: #D0A884;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0A884; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0A884;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0A884'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0A884'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0A884'>&nbsp;@&nbsp;</font><font style='background-color: #D0A884; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0A884;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0A884; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_SAFFRON = -0x1.5ee442p126F;
    static { NAMED.put("bright pure saffron", -0x1.5ee442p126F); LIST.add(-0x1.5ee442p126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code 78602EFF}, H 0.16470589, S 0.81960785, L 0.3882353, alpha 1.0, and chroma 0.41414613.
     * It can be represented as a packed float with the constant {@code -0x1.c7a254p125F}.
     * <pre>
     * <font style='background-color: #78602E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78602E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78602E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #78602E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #78602E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #78602E'>&nbsp;@&nbsp;</font><font style='background-color: #78602E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78602E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78602E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.c7a254p125F;
    static { NAMED.put("deep yellow saffron", -0x1.c7a254p125F); LIST.add(-0x1.c7a254p125F); }

    /**
     * This color constant "true yellow saffron" has RGBA8888 code {@code A08040FF}, H 0.16470589, S 0.8039216, L 0.5176471, alpha 1.0, and chroma 0.53490156.
     * It can be represented as a packed float with the constant {@code -0x1.099a54p126F}.
     * <pre>
     * <font style='background-color: #A08040;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A08040; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A08040;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A08040'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A08040'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A08040'>&nbsp;@&nbsp;</font><font style='background-color: #A08040; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A08040;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A08040; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_SAFFRON = -0x1.099a54p126F;
    static { NAMED.put("true yellow saffron", -0x1.099a54p126F); LIST.add(-0x1.099a54p126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code D1A856FF}, H 0.16470589, S 0.7882353, L 0.6784314, alpha 1.0, and chroma 0.67336917.
     * It can be represented as a packed float with the constant {@code -0x1.5b9254p126F}.
     * <pre>
     * <font style='background-color: #D1A856;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1A856; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1A856;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1A856'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1A856'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1A856'>&nbsp;@&nbsp;</font><font style='background-color: #D1A856; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1A856;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1A856; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.5b9254p126F;
    static { NAMED.put("bright yellow saffron", -0x1.5b9254p126F); LIST.add(-0x1.5b9254p126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code 776B3AFF}, H 0.2, S 0.73333335, L 0.41960785, alpha 1.0, and chroma 0.3727369.
     * It can be represented as a packed float with the constant {@code -0x1.d77666p125F}.
     * <pre>
     * <font style='background-color: #776B3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #776B3A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #776B3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #776B3A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #776B3A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #776B3A'>&nbsp;@&nbsp;</font><font style='background-color: #776B3A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #776B3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #776B3A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.d77666p125F;
    static { NAMED.put("deep saffron yellow", -0x1.d77666p125F); LIST.add(-0x1.d77666p125F); }

    /**
     * This color constant "true saffron yellow" has RGBA8888 code {@code A08F50FF}, H 0.19607843, S 0.72156864, L 0.56078434, alpha 1.0, and chroma 0.48521656.
     * It can be represented as a packed float with the constant {@code -0x1.1f7064p126F}.
     * <pre>
     * <font style='background-color: #A08F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A08F50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A08F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A08F50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A08F50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A08F50'>&nbsp;@&nbsp;</font><font style='background-color: #A08F50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A08F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A08F50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_YELLOW = -0x1.1f7064p126F;
    static { NAMED.put("true saffron yellow", -0x1.1f7064p126F); LIST.add(-0x1.1f7064p126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code C4B163FF}, H 0.2, S 0.7137255, L 0.6901961, alpha 1.0, and chroma 0.5777846.
     * It can be represented as a packed float with the constant {@code -0x1.616c66p126F}.
     * <pre>
     * <font style='background-color: #C4B163;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4B163; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4B163;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4B163'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4B163'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4B163'>&nbsp;@&nbsp;</font><font style='background-color: #C4B163; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4B163;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4B163; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.616c66p126F;
    static { NAMED.put("bright saffron yellow", -0x1.616c66p126F); LIST.add(-0x1.616c66p126F); }

    /**
     * This color constant "deep pure yellow" has RGBA8888 code {@code 6A6A30FF}, H 0.23921569, S 0.8156863, L 0.40392157, alpha 1.0, and chroma 0.39284363.
     * It can be represented as a packed float with the constant {@code -0x1.cfa07ap125F}.
     * <pre>
     * <font style='background-color: #6A6A30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A6A30; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A6A30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A6A30'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A6A30'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A6A30'>&nbsp;@&nbsp;</font><font style='background-color: #6A6A30; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A6A30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A6A30; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_YELLOW = -0x1.cfa07ap125F;
    static { NAMED.put("deep pure yellow", -0x1.cfa07ap125F); LIST.add(-0x1.cfa07ap125F); }

    /**
     * This color constant "true pure yellow" has RGBA8888 code {@code 8B8B41FF}, H 0.23921569, S 0.8039216, L 0.5294118, alpha 1.0, and chroma 0.50106215.
     * It can be represented as a packed float with the constant {@code -0x1.0f9a7ap126F}.
     * <pre>
     * <font style='background-color: #8B8B41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B8B41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B8B41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B8B41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B8B41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B8B41'>&nbsp;@&nbsp;</font><font style='background-color: #8B8B41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B8B41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B8B41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_YELLOW = -0x1.0f9a7ap126F;
    static { NAMED.put("true pure yellow", -0x1.0f9a7ap126F); LIST.add(-0x1.0f9a7ap126F); }

    /**
     * This color constant "bright pure yellow" has RGBA8888 code {@code B3B456FF}, H 0.23921569, S 0.7882353, L 0.68235296, alpha 1.0, and chroma 0.62083715.
     * It can be represented as a packed float with the constant {@code -0x1.5d927ap126F}.
     * <pre>
     * <font style='background-color: #B3B456;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3B456; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3B456;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B3B456'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B3B456'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B3B456'>&nbsp;@&nbsp;</font><font style='background-color: #B3B456; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3B456;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3B456; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_YELLOW = -0x1.5d927ap126F;
    static { NAMED.put("bright pure yellow", -0x1.5d927ap126F); LIST.add(-0x1.5d927ap126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code 5E6433FF}, H 0.25490198, S 0.7411765, L 0.3764706, alpha 1.0, and chroma 0.33671376.
     * It can be represented as a packed float with the constant {@code -0x1.c17a82p125F}.
     * <pre>
     * <font style='background-color: #5E6433;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E6433; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E6433;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5E6433'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5E6433'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5E6433'>&nbsp;@&nbsp;</font><font style='background-color: #5E6433; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E6433;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E6433; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.c17a82p125F;
    static { NAMED.put("deep lime yellow", -0x1.c17a82p125F); LIST.add(-0x1.c17a82p125F); }

    /**
     * This color constant "true lime yellow" has RGBA8888 code {@code 808948FF}, H 0.25882354, S 0.7294118, L 0.5137255, alpha 1.0, and chroma 0.44822612.
     * It can be represented as a packed float with the constant {@code -0x1.077484p126F}.
     * <pre>
     * <font style='background-color: #808948;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808948; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808948;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808948'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808948'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808948'>&nbsp;@&nbsp;</font><font style='background-color: #808948; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808948;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808948; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_YELLOW = -0x1.077484p126F;
    static { NAMED.put("true lime yellow", -0x1.077484p126F); LIST.add(-0x1.077484p126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code AAB561FF}, H 0.25882354, S 0.7176471, L 0.68235296, alpha 1.0, and chroma 0.57325625.
     * It can be represented as a packed float with the constant {@code -0x1.5d6e84p126F}.
     * <pre>
     * <font style='background-color: #AAB561;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAB561; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAB561;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAB561'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAB561'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAB561'>&nbsp;@&nbsp;</font><font style='background-color: #AAB561; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAB561;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAB561; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.5d6e84p126F;
    static { NAMED.put("bright lime yellow", -0x1.5d6e84p126F); LIST.add(-0x1.5d6e84p126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code 5F7131FF}, H 0.28235295, S 0.81960785, L 0.41568628, alpha 1.0, and chroma 0.4274036.
     * It can be represented as a packed float with the constant {@code -0x1.d5a29p125F}.
     * <pre>
     * <font style='background-color: #5F7131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F7131; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F7131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5F7131'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5F7131'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5F7131'>&nbsp;@&nbsp;</font><font style='background-color: #5F7131; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F7131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F7131; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.d5a29p125F;
    static { NAMED.put("deep yellow lime", -0x1.d5a29p125F); LIST.add(-0x1.d5a29p125F); }

    /**
     * This color constant "true yellow lime" has RGBA8888 code {@code 7F9543FF}, H 0.28235295, S 0.80784315, L 0.54901963, alpha 1.0, and chroma 0.54860675.
     * It can be represented as a packed float with the constant {@code -0x1.199c9p126F}.
     * <pre>
     * <font style='background-color: #7F9543;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F9543; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F9543;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F9543'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F9543'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F9543'>&nbsp;@&nbsp;</font><font style='background-color: #7F9543; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F9543;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F9543; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_LIME = -0x1.199c9p126F;
    static { NAMED.put("true yellow lime", -0x1.199c9p126F); LIST.add(-0x1.199c9p126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code 9FBB56FF}, H 0.28235295, S 0.7921569, L 0.6901961, alpha 1.0, and chroma 0.6639121.
     * It can be represented as a packed float with the constant {@code -0x1.61949p126F}.
     * <pre>
     * <font style='background-color: #9FBB56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FBB56; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FBB56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9FBB56'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9FBB56'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9FBB56'>&nbsp;@&nbsp;</font><font style='background-color: #9FBB56; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FBB56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FBB56; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.61949p126F;
    static { NAMED.put("bright yellow lime", -0x1.61949p126F); LIST.add(-0x1.61949p126F); }

    /**
     * This color constant "deep pure lime" has RGBA8888 code {@code 546D36FF}, H 0.30588236, S 0.7372549, L 0.39607844, alpha 1.0, and chroma 0.39040408.
     * It can be represented as a packed float with the constant {@code -0x1.cb789cp125F}.
     * <pre>
     * <font style='background-color: #546D36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #546D36; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #546D36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #546D36'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #546D36'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #546D36'>&nbsp;@&nbsp;</font><font style='background-color: #546D36; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #546D36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #546D36; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_LIME = -0x1.cb789cp125F;
    static { NAMED.put("deep pure lime", -0x1.cb789cp125F); LIST.add(-0x1.cb789cp125F); }

    /**
     * This color constant "true pure lime" has RGBA8888 code {@code 6F8F49FF}, H 0.30588236, S 0.7294118, L 0.52156866, alpha 1.0, and chroma 0.5023692.
     * It can be represented as a packed float with the constant {@code -0x1.0b749cp126F}.
     * <pre>
     * <font style='background-color: #6F8F49;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F8F49; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F8F49;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F8F49'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F8F49'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F8F49'>&nbsp;@&nbsp;</font><font style='background-color: #6F8F49; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F8F49;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F8F49; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_LIME = -0x1.0b749cp126F;
    static { NAMED.put("true pure lime", -0x1.0b749cp126F); LIST.add(-0x1.0b749cp126F); }

    /**
     * This color constant "bright pure lime" has RGBA8888 code {@code 91BB61FF}, H 0.30588236, S 0.7137255, L 0.6784314, alpha 1.0, and chroma 0.6266694.
     * It can be represented as a packed float with the constant {@code -0x1.5b6c9cp126F}.
     * <pre>
     * <font style='background-color: #91BB61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91BB61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91BB61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #91BB61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #91BB61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #91BB61'>&nbsp;@&nbsp;</font><font style='background-color: #91BB61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91BB61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91BB61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_LIME = -0x1.5b6c9cp126F;
    static { NAMED.put("bright pure lime", -0x1.5b6c9cp126F); LIST.add(-0x1.5b6c9cp126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 4F7732FF}, H 0.32156864, S 0.81960785, L 0.42352942, alpha 1.0, and chroma 0.48998275.
     * It can be represented as a packed float with the constant {@code -0x1.d9a2a4p125F}.
     * <pre>
     * <font style='background-color: #4F7732;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F7732; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F7732;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F7732'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F7732'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F7732'>&nbsp;@&nbsp;</font><font style='background-color: #4F7732; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F7732;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F7732; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.d9a2a4p125F;
    static { NAMED.put("deep green lime", -0x1.d9a2a4p125F); LIST.add(-0x1.d9a2a4p125F); }

    /**
     * This color constant "true green lime" has RGBA8888 code {@code 6CA046FF}, H 0.32156864, S 0.8039216, L 0.5686275, alpha 1.0, and chroma 0.6351191.
     * It can be represented as a packed float with the constant {@code -0x1.239aa4p126F}.
     * <pre>
     * <font style='background-color: #6CA046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6CA046; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6CA046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6CA046'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6CA046'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6CA046'>&nbsp;@&nbsp;</font><font style='background-color: #6CA046; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6CA046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6CA046; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_LIME = -0x1.239aa4p126F;
    static { NAMED.put("true green lime", -0x1.239aa4p126F); LIST.add(-0x1.239aa4p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code 85C357FF}, H 0.32156864, S 0.7921569, L 0.69803923, alpha 1.0, and chroma 0.7552042.
     * It can be represented as a packed float with the constant {@code -0x1.6594a4p126F}.
     * <pre>
     * <font style='background-color: #85C357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #85C357; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #85C357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #85C357'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #85C357'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #85C357'>&nbsp;@&nbsp;</font><font style='background-color: #85C357; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #85C357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #85C357; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.6594a4p126F;
    static { NAMED.put("bright green lime", -0x1.6594a4p126F); LIST.add(-0x1.6594a4p126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 477438FF}, H 0.3372549, S 0.7372549, L 0.4117647, alpha 1.0, and chroma 0.4601663.
     * It can be represented as a packed float with the constant {@code -0x1.d378acp125F}.
     * <pre>
     * <font style='background-color: #477438;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #477438; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #477438;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #477438'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #477438'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #477438'>&nbsp;@&nbsp;</font><font style='background-color: #477438; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #477438;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #477438; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.d378acp125F;
    static { NAMED.put("deep lime green", -0x1.d378acp125F); LIST.add(-0x1.d378acp125F); }

    /**
     * This color constant "true lime green" has RGBA8888 code {@code 5E984BFF}, H 0.3372549, S 0.73333335, L 0.5372549, alpha 1.0, and chroma 0.5895003.
     * It can be represented as a packed float with the constant {@code -0x1.1376acp126F}.
     * <pre>
     * <font style='background-color: #5E984B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E984B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E984B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5E984B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5E984B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5E984B'>&nbsp;@&nbsp;</font><font style='background-color: #5E984B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E984B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E984B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_GREEN = -0x1.1376acp126F;
    static { NAMED.put("true lime green", -0x1.1376acp126F); LIST.add(-0x1.1376acp126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code 79C162FF}, H 0.3372549, S 0.70980394, L 0.6862745, alpha 1.0, and chroma 0.7148872.
     * It can be represented as a packed float with the constant {@code -0x1.5f6aacp126F}.
     * <pre>
     * <font style='background-color: #79C162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #79C162; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #79C162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #79C162'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #79C162'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #79C162'>&nbsp;@&nbsp;</font><font style='background-color: #79C162; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #79C162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #79C162; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.5f6aacp126F;
    static { NAMED.put("bright lime green", -0x1.5f6aacp126F); LIST.add(-0x1.5f6aacp126F); }

    /**
     * This color constant "deep pure green" has RGBA8888 code {@code 2F6F2DFF}, H 0.3529412, S 0.8156863, L 0.38431373, alpha 1.0, and chroma 0.5190216.
     * It can be represented as a packed float with the constant {@code -0x1.c5a0b4p125F}.
     * <pre>
     * <font style='background-color: #2F6F2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F6F2D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F6F2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F6F2D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F6F2D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F6F2D'>&nbsp;@&nbsp;</font><font style='background-color: #2F6F2D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F6F2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F6F2D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_GREEN = -0x1.c5a0b4p125F;
    static { NAMED.put("deep pure green", -0x1.c5a0b4p125F); LIST.add(-0x1.c5a0b4p125F); }

    /**
     * This color constant "true pure green" has RGBA8888 code {@code 42963FFF}, H 0.3529412, S 0.80784315, L 0.5176471, alpha 1.0, and chroma 0.6835863.
     * It can be represented as a packed float with the constant {@code -0x1.099cb4p126F}.
     * <pre>
     * <font style='background-color: #42963F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42963F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42963F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #42963F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #42963F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #42963F'>&nbsp;@&nbsp;</font><font style='background-color: #42963F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42963F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42963F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_GREEN = -0x1.099cb4p126F;
    static { NAMED.put("true pure green", -0x1.099cb4p126F); LIST.add(-0x1.099cb4p126F); }

    /**
     * This color constant "bright pure green" has RGBA8888 code {@code 59C455FF}, H 0.3529412, S 0.7882353, L 0.6784314, alpha 1.0, and chroma 0.8563659.
     * It can be represented as a packed float with the constant {@code -0x1.5b92b4p126F}.
     * <pre>
     * <font style='background-color: #59C455;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #59C455; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #59C455;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #59C455'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #59C455'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #59C455'>&nbsp;@&nbsp;</font><font style='background-color: #59C455; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #59C455;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #59C455; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_GREEN = -0x1.5b92b4p126F;
    static { NAMED.put("bright pure green", -0x1.5b92b4p126F); LIST.add(-0x1.5b92b4p126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 3B765AFF}, H 0.4117647, S 0.7411765, L 0.41568628, alpha 1.0, and chroma 0.33659458.
     * It can be represented as a packed float with the constant {@code -0x1.d57ad2p125F}.
     * <pre>
     * <font style='background-color: #3B765A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B765A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B765A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B765A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B765A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B765A'>&nbsp;@&nbsp;</font><font style='background-color: #3B765A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B765A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B765A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.d57ad2p125F;
    static { NAMED.put("deep cyan green", -0x1.d57ad2p125F); LIST.add(-0x1.d57ad2p125F); }

    /**
     * This color constant "true cyan green" has RGBA8888 code {@code 519D79FF}, H 0.4117647, S 0.7254902, L 0.5568628, alpha 1.0, and chroma 0.43477333.
     * It can be represented as a packed float with the constant {@code -0x1.1d72d2p126F}.
     * <pre>
     * <font style='background-color: #519D79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #519D79; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #519D79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #519D79'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #519D79'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #519D79'>&nbsp;@&nbsp;</font><font style='background-color: #519D79; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #519D79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #519D79; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_GREEN = -0x1.1d72d2p126F;
    static { NAMED.put("true cyan green", -0x1.1d72d2p126F); LIST.add(-0x1.1d72d2p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code 65C295FF}, H 0.4117647, S 0.7137255, L 0.6862745, alpha 1.0, and chroma 0.5182553.
     * It can be represented as a packed float with the constant {@code -0x1.5f6cd2p126F}.
     * <pre>
     * <font style='background-color: #65C295;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65C295; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65C295;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65C295'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65C295'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65C295'>&nbsp;@&nbsp;</font><font style='background-color: #65C295; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65C295;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65C295; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.5f6cd2p126F;
    static { NAMED.put("bright cyan green", -0x1.5f6cd2p126F); LIST.add(-0x1.5f6cd2p126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 337265FF}, H 0.47058824, S 0.8156863, L 0.40392157, alpha 1.0, and chroma 0.29461268.
     * It can be represented as a packed float with the constant {@code -0x1.cfa0fp125F}.
     * <pre>
     * <font style='background-color: #337265;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #337265; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #337265;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #337265'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #337265'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #337265'>&nbsp;@&nbsp;</font><font style='background-color: #337265; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #337265;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #337265; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.cfa0fp125F;
    static { NAMED.put("deep green cyan", -0x1.cfa0fp125F); LIST.add(-0x1.cfa0fp125F); }

    /**
     * This color constant "true green cyan" has RGBA8888 code {@code 459585FF}, H 0.4745098, S 0.8039216, L 0.5294118, alpha 1.0, and chroma 0.3729653.
     * It can be represented as a packed float with the constant {@code -0x1.0f9af2p126F}.
     * <pre>
     * <font style='background-color: #459585;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #459585; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #459585;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #459585'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #459585'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #459585'>&nbsp;@&nbsp;</font><font style='background-color: #459585; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #459585;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #459585; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_CYAN = -0x1.0f9af2p126F;
    static { NAMED.put("true green cyan", -0x1.0f9af2p126F); LIST.add(-0x1.0f9af2p126F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code 5ABFABFF}, H 0.4745098, S 0.7921569, L 0.6784314, alpha 1.0, and chroma 0.46199802.
     * It can be represented as a packed float with the constant {@code -0x1.5b94f2p126F}.
     * <pre>
     * <font style='background-color: #5ABFAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5ABFAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5ABFAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5ABFAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5ABFAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5ABFAB'>&nbsp;@&nbsp;</font><font style='background-color: #5ABFAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5ABFAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5ABFAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.5b94f2p126F;
    static { NAMED.put("bright green cyan", -0x1.5b94f2p126F); LIST.add(-0x1.5b94f2p126F); }

    /**
     * This color constant "deep pure cyan" has RGBA8888 code {@code 3F7575FF}, H 0.53333336, S 0.7294118, L 0.42352942, alpha 1.0, and chroma 0.26377192.
     * It can be represented as a packed float with the constant {@code -0x1.d9751p125F}.
     * <pre>
     * <font style='background-color: #3F7575;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F7575; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F7575;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F7575'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F7575'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F7575'>&nbsp;@&nbsp;</font><font style='background-color: #3F7575; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F7575;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F7575; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_CYAN = -0x1.d9751p125F;
    static { NAMED.put("deep pure cyan", -0x1.d9751p125F); LIST.add(-0x1.d9751p125F); }

    /**
     * This color constant "true pure cyan" has RGBA8888 code {@code 579E9EFF}, H 0.53333336, S 0.72156864, L 0.57254905, alpha 1.0, and chroma 0.34703413.
     * It can be represented as a packed float with the constant {@code -0x1.25711p126F}.
     * <pre>
     * <font style='background-color: #579E9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #579E9E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #579E9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #579E9E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #579E9E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #579E9E'>&nbsp;@&nbsp;</font><font style='background-color: #579E9E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #579E9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #579E9E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_CYAN = -0x1.25711p126F;
    static { NAMED.put("true pure cyan", -0x1.25711p126F); LIST.add(-0x1.25711p126F); }

    /**
     * This color constant "bright pure cyan" has RGBA8888 code {@code 6BC1C1FF}, H 0.53333336, S 0.70980394, L 0.69803923, alpha 1.0, and chroma 0.40932927.
     * It can be represented as a packed float with the constant {@code -0x1.656b1p126F}.
     * <pre>
     * <font style='background-color: #6BC1C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6BC1C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6BC1C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6BC1C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6BC1C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6BC1C1'>&nbsp;@&nbsp;</font><font style='background-color: #6BC1C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6BC1C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6BC1C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_CYAN = -0x1.656b1p126F;
    static { NAMED.put("bright pure cyan", -0x1.656b1p126F); LIST.add(-0x1.656b1p126F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 377282FF}, H 0.6039216, S 0.8156863, L 0.41568628, alpha 1.0, and chroma 0.3340282.
     * It can be represented as a packed float with the constant {@code -0x1.d5a134p125F}.
     * <pre>
     * <font style='background-color: #377282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #377282; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #377282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #377282'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #377282'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #377282'>&nbsp;@&nbsp;</font><font style='background-color: #377282; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #377282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #377282; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.d5a134p125F;
    static { NAMED.put("deep blue cyan", -0x1.d5a134p125F); LIST.add(-0x1.d5a134p125F); }

    /**
     * This color constant "true blue cyan" has RGBA8888 code {@code 4A96AAFF}, H 0.6039216, S 0.80784315, L 0.54509807, alpha 1.0, and chroma 0.42793953.
     * It can be represented as a packed float with the constant {@code -0x1.179d34p126F}.
     * <pre>
     * <font style='background-color: #4A96AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A96AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A96AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A96AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A96AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A96AA'>&nbsp;@&nbsp;</font><font style='background-color: #4A96AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A96AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A96AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_CYAN = -0x1.179d34p126F;
    static { NAMED.put("true blue cyan", -0x1.179d34p126F); LIST.add(-0x1.179d34p126F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 5FBBD5FF}, H 0.60784316, S 0.7882353, L 0.68235296, alpha 1.0, and chroma 0.5210846.
     * It can be represented as a packed float with the constant {@code -0x1.5d9336p126F}.
     * <pre>
     * <font style='background-color: #5FBBD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5FBBD5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5FBBD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5FBBD5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5FBBD5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5FBBD5'>&nbsp;@&nbsp;</font><font style='background-color: #5FBBD5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5FBBD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5FBBD5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.5d9336p126F;
    static { NAMED.put("bright blue cyan", -0x1.5d9336p126F); LIST.add(-0x1.5d9336p126F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 3D688EFF}, H 0.6745098, S 0.74509805, L 0.39215687, alpha 1.0, and chroma 0.4425282.
     * It can be represented as a packed float with the constant {@code -0x1.c97d58p125F}.
     * <pre>
     * <font style='background-color: #3D688E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D688E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D688E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3D688E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3D688E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3D688E'>&nbsp;@&nbsp;</font><font style='background-color: #3D688E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D688E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D688E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.c97d58p125F;
    static { NAMED.put("deep cyan blue", -0x1.c97d58p125F); LIST.add(-0x1.c97d58p125F); }

    /**
     * This color constant "true cyan blue" has RGBA8888 code {@code 548ABBFF}, H 0.6745098, S 0.7254902, L 0.52156866, alpha 1.0, and chroma 0.5658598.
     * It can be represented as a packed float with the constant {@code -0x1.0b7358p126F}.
     * <pre>
     * <font style='background-color: #548ABB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #548ABB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #548ABB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #548ABB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #548ABB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #548ABB'>&nbsp;@&nbsp;</font><font style='background-color: #548ABB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #548ABB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #548ABB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_BLUE = -0x1.0b7358p126F;
    static { NAMED.put("true cyan blue", -0x1.0b7358p126F); LIST.add(-0x1.0b7358p126F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code 7FB3E6FF}, H 0.6745098, S 0.60784316, L 0.68235296, alpha 1.0, and chroma 0.4855147.
     * It can be represented as a packed float with the constant {@code -0x1.5d3758p126F}.
     * <pre>
     * <font style='background-color: #7FB3E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FB3E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FB3E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FB3E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FB3E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FB3E6'>&nbsp;@&nbsp;</font><font style='background-color: #7FB3E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FB3E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FB3E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.5d3758p126F;
    static { NAMED.put("bright cyan blue", -0x1.5d3758p126F); LIST.add(-0x1.5d3758p126F); }

    /**
     * This color constant "deep pure blue" has RGBA8888 code {@code 615CD7FF}, H 0.7411765, S 0.7372549, L 0.42352942, alpha 1.0, and chroma 0.95259553.
     * It can be represented as a packed float with the constant {@code -0x1.d9797ap125F}.
     * <pre>
     * <font style='background-color: #615CD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #615CD7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #615CD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #615CD7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #615CD7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #615CD7'>&nbsp;@&nbsp;</font><font style='background-color: #615CD7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #615CD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #615CD7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BLUE = -0x1.d9797ap125F;
    static { NAMED.put("deep pure blue", -0x1.d9797ap125F); LIST.add(-0x1.d9797ap125F); }

    /**
     * This color constant "true pure blue" has RGBA8888 code {@code 8A87E4FF}, H 0.7411765, S 0.69803923, L 0.5647059, alpha 1.0, and chroma 0.71800786.
     * It can be represented as a packed float with the constant {@code -0x1.21657ap126F}.
     * <pre>
     * <font style='background-color: #8A87E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A87E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A87E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A87E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A87E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A87E4'>&nbsp;@&nbsp;</font><font style='background-color: #8A87E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A87E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A87E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BLUE = -0x1.21657ap126F;
    static { NAMED.put("true pure blue", -0x1.21657ap126F); LIST.add(-0x1.21657ap126F); }

    /**
     * This color constant "bright pure blue" has RGBA8888 code {@code ADABEDFF}, H 0.7411765, S 0.68235296, L 0.69411767, alpha 1.0, and chroma 0.48462954.
     * It can be represented as a packed float with the constant {@code -0x1.635d7ap126F}.
     * <pre>
     * <font style='background-color: #ADABED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ADABED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ADABED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ADABED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ADABED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ADABED'>&nbsp;@&nbsp;</font><font style='background-color: #ADABED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ADABED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ADABED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BLUE = -0x1.635d7ap126F;
    static { NAMED.put("bright pure blue", -0x1.635d7ap126F); LIST.add(-0x1.635d7ap126F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 6C54CAFF}, H 0.7529412, S 0.6745098, L 0.40784314, alpha 1.0, and chroma 0.8851376.
     * It can be represented as a packed float with the constant {@code -0x1.d1598p125F}.
     * <pre>
     * <font style='background-color: #6C54CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C54CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C54CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C54CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C54CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C54CA'>&nbsp;@&nbsp;</font><font style='background-color: #6C54CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C54CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C54CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.d1598p125F;
    static { NAMED.put("deep violet blue", -0x1.d1598p125F); LIST.add(-0x1.d1598p125F); }

    /**
     * This color constant "true violet blue" has RGBA8888 code {@code 8C7BD9FF}, H 0.7529412, S 0.6392157, L 0.53333336, alpha 1.0, and chroma 0.7053709.
     * It can be represented as a packed float with the constant {@code -0x1.11478p126F}.
     * <pre>
     * <font style='background-color: #8C7BD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C7BD9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C7BD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C7BD9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C7BD9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C7BD9'>&nbsp;@&nbsp;</font><font style='background-color: #8C7BD9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C7BD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C7BD9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_BLUE = -0x1.11478p126F;
    static { NAMED.put("true violet blue", -0x1.11478p126F); LIST.add(-0x1.11478p126F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code B1A8E6FF}, H 0.7529412, S 0.60784316, L 0.6862745, alpha 1.0, and chroma 0.44587287.
     * It can be represented as a packed float with the constant {@code -0x1.5f378p126F}.
     * <pre>
     * <font style='background-color: #B1A8E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1A8E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1A8E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1A8E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1A8E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1A8E6'>&nbsp;@&nbsp;</font><font style='background-color: #B1A8E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1A8E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1A8E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.5f378p126F;
    static { NAMED.put("bright violet blue", -0x1.5f378p126F); LIST.add(-0x1.5f378p126F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 763FD1FF}, H 0.7647059, S 0.8156863, L 0.38039216, alpha 1.0, and chroma 1.0953258.
     * It can be represented as a packed float with the constant {@code -0x1.c3a186p125F}.
     * <pre>
     * <font style='background-color: #763FD1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #763FD1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #763FD1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #763FD1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #763FD1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #763FD1'>&nbsp;@&nbsp;</font><font style='background-color: #763FD1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #763FD1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #763FD1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.c3a186p125F;
    static { NAMED.put("deep blue violet", -0x1.c3a186p125F); LIST.add(-0x1.c3a186p125F); }

    /**
     * This color constant "true blue violet" has RGBA8888 code {@code 926FE0FF}, H 0.7647059, S 0.7137255, L 0.5137255, alpha 1.0, and chroma 0.8247161.
     * It can be represented as a packed float with the constant {@code -0x1.076d86p126F}.
     * <pre>
     * <font style='background-color: #926FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #926FE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #926FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #926FE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #926FE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #926FE0'>&nbsp;@&nbsp;</font><font style='background-color: #926FE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #926FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #926FE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_VIOLET = -0x1.076d86p126F;
    static { NAMED.put("true blue violet", -0x1.076d86p126F); LIST.add(-0x1.076d86p126F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code B7A3ECFF}, H 0.7647059, S 0.68235296, L 0.6784314, alpha 1.0, and chroma 0.5193768.
     * It can be represented as a packed float with the constant {@code -0x1.5b5d86p126F}.
     * <pre>
     * <font style='background-color: #B7A3EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7A3EC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7A3EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B7A3EC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B7A3EC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B7A3EC'>&nbsp;@&nbsp;</font><font style='background-color: #B7A3EC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7A3EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7A3EC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.5b5d86p126F;
    static { NAMED.put("bright blue violet", -0x1.5b5d86p126F); LIST.add(-0x1.5b5d86p126F); }

    /**
     * This color constant "deep pure violet" has RGBA8888 code {@code 824ECBFF}, H 0.77254903, S 0.69803923, L 0.41568628, alpha 1.0, and chroma 0.9256243.
     * It can be represented as a packed float with the constant {@code -0x1.d5658ap125F}.
     * <pre>
     * <font style='background-color: #824ECB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #824ECB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #824ECB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #824ECB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #824ECB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #824ECB'>&nbsp;@&nbsp;</font><font style='background-color: #824ECB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #824ECB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #824ECB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_VIOLET = -0x1.d5658ap125F;
    static { NAMED.put("deep pure violet", -0x1.d5658ap125F); LIST.add(-0x1.d5658ap125F); }

    /**
     * This color constant "true pure violet" has RGBA8888 code {@code 9F7CDBFF}, H 0.77254903, S 0.63529414, L 0.5529412, alpha 1.0, and chroma 0.6860901.
     * It can be represented as a packed float with the constant {@code -0x1.1b458ap126F}.
     * <pre>
     * <font style='background-color: #9F7CDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F7CDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F7CDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F7CDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F7CDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F7CDB'>&nbsp;@&nbsp;</font><font style='background-color: #9F7CDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F7CDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F7CDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_VIOLET = -0x1.1b458ap126F;
    static { NAMED.put("true pure violet", -0x1.1b458ap126F); LIST.add(-0x1.1b458ap126F); }

    /**
     * This color constant "bright pure violet" has RGBA8888 code {@code BBA6E7FF}, H 0.77254903, S 0.6156863, L 0.6901961, alpha 1.0, and chroma 0.45476723.
     * It can be represented as a packed float with the constant {@code -0x1.613b8ap126F}.
     * <pre>
     * <font style='background-color: #BBA6E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBA6E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBA6E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BBA6E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BBA6E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BBA6E7'>&nbsp;@&nbsp;</font><font style='background-color: #BBA6E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBA6E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBA6E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_VIOLET = -0x1.613b8ap126F;
    static { NAMED.put("bright pure violet", -0x1.613b8ap126F); LIST.add(-0x1.613b8ap126F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 8B3FCAFF}, H 0.78431374, S 0.81960785, L 0.4, alpha 1.0, and chroma 1.027702.
     * It can be represented as a packed float with the constant {@code -0x1.cda39p125F}.
     * <pre>
     * <font style='background-color: #8B3FCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B3FCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B3FCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B3FCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B3FCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B3FCA'>&nbsp;@&nbsp;</font><font style='background-color: #8B3FCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B3FCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B3FCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.cda39p125F;
    static { NAMED.put("deep purple violet", -0x1.cda39p125F); LIST.add(-0x1.cda39p125F); }

    /**
     * This color constant "true purple violet" has RGBA8888 code {@code A56BE1FF}, H 0.78431374, S 0.7137255, L 0.5254902, alpha 1.0, and chroma 0.8298187.
     * It can be represented as a packed float with the constant {@code -0x1.0d6d9p126F}.
     * <pre>
     * <font style='background-color: #A56BE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A56BE1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A56BE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A56BE1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A56BE1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A56BE1'>&nbsp;@&nbsp;</font><font style='background-color: #A56BE1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A56BE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A56BE1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_VIOLET = -0x1.0d6d9p126F;
    static { NAMED.put("true purple violet", -0x1.0d6d9p126F); LIST.add(-0x1.0d6d9p126F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code C19FECFF}, H 0.78431374, S 0.68235296, L 0.6784314, alpha 1.0, and chroma 0.5351286.
     * It can be represented as a packed float with the constant {@code -0x1.5b5d9p126F}.
     * <pre>
     * <font style='background-color: #C19FEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C19FEC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C19FEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C19FEC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C19FEC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C19FEC'>&nbsp;@&nbsp;</font><font style='background-color: #C19FEC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C19FEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C19FEC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.5b5d9p126F;
    static { NAMED.put("bright purple violet", -0x1.5b5d9p126F); LIST.add(-0x1.5b5d9p126F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 944AC3FF}, H 0.79607844, S 0.73333335, L 0.42352942, alpha 1.0, and chroma 0.9159294.
     * It can be represented as a packed float with the constant {@code -0x1.d97796p125F}.
     * <pre>
     * <font style='background-color: #944AC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #944AC3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #944AC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #944AC3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #944AC3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #944AC3'>&nbsp;@&nbsp;</font><font style='background-color: #944AC3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #944AC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #944AC3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.d97796p125F;
    static { NAMED.put("deep violet purple", -0x1.d97796p125F); LIST.add(-0x1.d97796p125F); }

    /**
     * This color constant "true violet purple" has RGBA8888 code {@code B27ADCFF}, H 0.79607844, S 0.6313726, L 0.5686275, alpha 1.0, and chroma 0.6883243.
     * It can be represented as a packed float with the constant {@code -0x1.234396p126F}.
     * <pre>
     * <font style='background-color: #B27ADC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B27ADC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B27ADC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B27ADC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B27ADC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B27ADC'>&nbsp;@&nbsp;</font><font style='background-color: #B27ADC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B27ADC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B27ADC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_PURPLE = -0x1.234396p126F;
    static { NAMED.put("true violet purple", -0x1.234396p126F); LIST.add(-0x1.234396p126F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code C8A4E7FF}, H 0.79607844, S 0.60784316, L 0.69411767, alpha 1.0, and chroma 0.4636517.
     * It can be represented as a packed float with the constant {@code -0x1.633796p126F}.
     * <pre>
     * <font style='background-color: #C8A4E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8A4E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8A4E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8A4E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8A4E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8A4E7'>&nbsp;@&nbsp;</font><font style='background-color: #C8A4E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8A4E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8A4E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.633796p126F;
    static { NAMED.put("bright violet purple", -0x1.633796p126F); LIST.add(-0x1.633796p126F); }

    /**
     * This color constant "deep pure purple" has RGBA8888 code {@code 983FC2FF}, H 0.8039216, S 0.81960785, L 0.4117647, alpha 1.0, and chroma 0.9622385.
     * It can be represented as a packed float with the constant {@code -0x1.d3a39ap125F}.
     * <pre>
     * <font style='background-color: #983FC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #983FC2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #983FC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #983FC2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #983FC2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #983FC2'>&nbsp;@&nbsp;</font><font style='background-color: #983FC2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #983FC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #983FC2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_PURPLE = -0x1.d3a39ap125F;
    static { NAMED.put("deep pure purple", -0x1.d3a39ap125F); LIST.add(-0x1.d3a39ap125F); }

    /**
     * This color constant "true pure purple" has RGBA8888 code {@code B869E2FF}, H 0.8039216, S 0.7058824, L 0.5411765, alpha 1.0, and chroma 0.8322915.
     * It can be represented as a packed float with the constant {@code -0x1.15699ap126F}.
     * <pre>
     * <font style='background-color: #B869E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B869E2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B869E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B869E2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B869E2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B869E2'>&nbsp;@&nbsp;</font><font style='background-color: #B869E2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B869E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B869E2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_PURPLE = -0x1.15699ap126F;
    static { NAMED.put("true pure purple", -0x1.15699ap126F); LIST.add(-0x1.15699ap126F); }

    /**
     * This color constant "bright pure purple" has RGBA8888 code {@code CD9CECFF}, H 0.8039216, S 0.68235296, L 0.68235296, alpha 1.0, and chroma 0.553318.
     * It can be represented as a packed float with the constant {@code -0x1.5d5d9ap126F}.
     * <pre>
     * <font style='background-color: #CD9CEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD9CEC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD9CEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD9CEC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD9CEC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD9CEC'>&nbsp;@&nbsp;</font><font style='background-color: #CD9CEC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD9CEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD9CEC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_PURPLE = -0x1.5d5d9ap126F;
    static { NAMED.put("bright pure purple", -0x1.5d5d9ap126F); LIST.add(-0x1.5d5d9ap126F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code 8F40A3FF}, H 0.8235294, S 0.7411765, L 0.38039216, alpha 1.0, and chroma 0.74977654.
     * It can be represented as a packed float with the constant {@code -0x1.c37ba4p125F}.
     * <pre>
     * <font style='background-color: #8F40A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F40A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F40A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F40A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F40A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F40A3'>&nbsp;@&nbsp;</font><font style='background-color: #8F40A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F40A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F40A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.c37ba4p125F;
    static { NAMED.put("deep magenta purple", -0x1.c37ba4p125F); LIST.add(-0x1.c37ba4p125F); }

    /**
     * This color constant "true magenta purple" has RGBA8888 code {@code BE5BD7FF}, H 0.8235294, S 0.7058824, L 0.5176471, alpha 1.0, and chroma 0.9261384.
     * It can be represented as a packed float with the constant {@code -0x1.0969a4p126F}.
     * <pre>
     * <font style='background-color: #BE5BD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE5BD7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE5BD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE5BD7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE5BD7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE5BD7'>&nbsp;@&nbsp;</font><font style='background-color: #BE5BD7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE5BD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE5BD7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_PURPLE = -0x1.0969a4p126F;
    static { NAMED.put("true magenta purple", -0x1.0969a4p126F); LIST.add(-0x1.0969a4p126F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code D399E6FF}, H 0.8235294, S 0.6156863, L 0.6784314, alpha 1.0, and chroma 0.5397277.
     * It can be represented as a packed float with the constant {@code -0x1.5b3ba4p126F}.
     * <pre>
     * <font style='background-color: #D399E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D399E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D399E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D399E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D399E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D399E6'>&nbsp;@&nbsp;</font><font style='background-color: #D399E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D399E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D399E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.5b3ba4p126F;
    static { NAMED.put("bright magenta purple", -0x1.5b3ba4p126F); LIST.add(-0x1.5b3ba4p126F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code A63EB1FF}, H 0.8392157, S 0.8156863, L 0.41960785, alpha 1.0, and chroma 0.86869854.
     * It can be represented as a packed float with the constant {@code -0x1.d7a1acp125F}.
     * <pre>
     * <font style='background-color: #A63EB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A63EB1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A63EB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A63EB1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A63EB1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A63EB1'>&nbsp;@&nbsp;</font><font style='background-color: #A63EB1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A63EB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A63EB1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.d7a1acp125F;
    static { NAMED.put("deep purple magenta", -0x1.d7a1acp125F); LIST.add(-0x1.d7a1acp125F); }

    /**
     * This color constant "true purple magenta" has RGBA8888 code {@code D65DE4FF}, H 0.8392157, S 0.7411765, L 0.56078434, alpha 1.0, and chroma 0.95352656.
     * It can be represented as a packed float with the constant {@code -0x1.1f7bacp126F}.
     * <pre>
     * <font style='background-color: #D65DE4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D65DE4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D65DE4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D65DE4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D65DE4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D65DE4'>&nbsp;@&nbsp;</font><font style='background-color: #D65DE4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D65DE4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D65DE4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_MAGENTA = -0x1.1f7bacp126F;
    static { NAMED.put("true purple magenta", -0x1.1f7bacp126F); LIST.add(-0x1.1f7bacp126F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code E296EDFF}, H 0.8392157, S 0.6862745, L 0.69411767, alpha 1.0, and chroma 0.6098793.
     * It can be represented as a packed float with the constant {@code -0x1.635facp126F}.
     * <pre>
     * <font style='background-color: #E296ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E296ED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E296ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E296ED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E296ED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E296ED'>&nbsp;@&nbsp;</font><font style='background-color: #E296ED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E296ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E296ED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.635facp126F;
    static { NAMED.put("bright purple magenta", -0x1.635facp126F); LIST.add(-0x1.635facp126F); }

    /**
     * This color constant "deep pure magenta" has RGBA8888 code {@code 9E429EFF}, H 0.85490197, S 0.7372549, L 0.40392157, alpha 1.0, and chroma 0.73265624.
     * It can be represented as a packed float with the constant {@code -0x1.cf79b4p125F}.
     * <pre>
     * <font style='background-color: #9E429E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E429E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E429E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E429E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E429E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E429E'>&nbsp;@&nbsp;</font><font style='background-color: #9E429E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E429E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E429E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_MAGENTA = -0x1.cf79b4p125F;
    static { NAMED.put("deep pure magenta", -0x1.cf79b4p125F); LIST.add(-0x1.cf79b4p125F); }

    /**
     * This color constant "true pure magenta" has RGBA8888 code {@code CD58CDFF}, H 0.85490197, S 0.7294118, L 0.5294118, alpha 1.0, and chroma 0.9380729.
     * It can be represented as a packed float with the constant {@code -0x1.0f75b4p126F}.
     * <pre>
     * <font style='background-color: #CD58CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD58CD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD58CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD58CD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD58CD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD58CD'>&nbsp;@&nbsp;</font><font style='background-color: #CD58CD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD58CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD58CD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_MAGENTA = -0x1.0f75b4p126F;
    static { NAMED.put("true pure magenta", -0x1.0f75b4p126F); LIST.add(-0x1.0f75b4p126F); }

    /**
     * This color constant "bright pure magenta" has RGBA8888 code {@code E691E6FF}, H 0.85490197, S 0.6156863, L 0.68235296, alpha 1.0, and chroma 0.61778873.
     * It can be represented as a packed float with the constant {@code -0x1.5d3bb4p126F}.
     * <pre>
     * <font style='background-color: #E691E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E691E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E691E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E691E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E691E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E691E6'>&nbsp;@&nbsp;</font><font style='background-color: #E691E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E691E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E691E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_MAGENTA = -0x1.5d3bb4p126F;
    static { NAMED.put("bright pure magenta", -0x1.5d3bb4p126F); LIST.add(-0x1.5d3bb4p126F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code A13380FF}, H 0.91764706, S 0.8156863, L 0.37254903, alpha 1.0, and chroma 0.7293247.
     * It can be represented as a packed float with the constant {@code -0x1.bfa1d4p125F}.
     * <pre>
     * <font style='background-color: #A13380;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A13380; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A13380;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A13380'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A13380'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A13380'>&nbsp;@&nbsp;</font><font style='background-color: #A13380; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A13380;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A13380; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.bfa1d4p125F;
    static { NAMED.put("deep red magenta", -0x1.bfa1d4p125F); LIST.add(-0x1.bfa1d4p125F); }

    /**
     * This color constant "true red magenta" has RGBA8888 code {@code DB48B0FF}, H 0.9137255, S 0.8117647, L 0.5137255, alpha 1.0, and chroma 0.98494065.
     * It can be represented as a packed float with the constant {@code -0x1.079fd2p126F}.
     * <pre>
     * <font style='background-color: #DB48B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB48B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB48B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB48B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB48B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB48B0'>&nbsp;@&nbsp;</font><font style='background-color: #DB48B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB48B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB48B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_MAGENTA = -0x1.079fd2p126F;
    static { NAMED.put("true red magenta", -0x1.079fd2p126F); LIST.add(-0x1.079fd2p126F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code ED90CCFF}, H 0.9137255, S 0.67058825, L 0.6784314, alpha 1.0, and chroma 0.5429485.
     * It can be represented as a packed float with the constant {@code -0x1.5b57d2p126F}.
     * <pre>
     * <font style='background-color: #ED90CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED90CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED90CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ED90CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ED90CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ED90CC'>&nbsp;@&nbsp;</font><font style='background-color: #ED90CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED90CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED90CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.5b57d2p126F;
    static { NAMED.put("bright red magenta", -0x1.5b57d2p126F); LIST.add(-0x1.5b57d2p126F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code B53E70FF}, H 0.9764706, S 0.7372549, L 0.41568628, alpha 1.0, and chroma 0.82876223.
     * It can be represented as a packed float with the constant {@code -0x1.d579f2p125F}.
     * <pre>
     * <font style='background-color: #B53E70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B53E70; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B53E70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B53E70'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B53E70'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B53E70'>&nbsp;@&nbsp;</font><font style='background-color: #B53E70; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B53E70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B53E70; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.d579f2p125F;
    static { NAMED.put("deep magenta red", -0x1.d579f2p125F); LIST.add(-0x1.d579f2p125F); }

    /**
     * This color constant "true magenta red" has RGBA8888 code {@code E16094FF}, H 0.972549, S 0.6156863, L 0.54901963, alpha 1.0, and chroma 0.75812846.
     * It can be represented as a packed float with the constant {@code -0x1.193bfp126F}.
     * <pre>
     * <font style='background-color: #E16094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E16094; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E16094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E16094'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E16094'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E16094'>&nbsp;@&nbsp;</font><font style='background-color: #E16094; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E16094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E16094; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_RED = -0x1.193bfp126F;
    static { NAMED.put("true magenta red", -0x1.193bfp126F); LIST.add(-0x1.193bfp126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code E999B4FF}, H 0.9764706, S 0.5921569, L 0.6862745, alpha 1.0, and chroma 0.43469468.
     * It can be represented as a packed float with the constant {@code -0x1.5f2ff2p126F}.
     * <pre>
     * <font style='background-color: #E999B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E999B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E999B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E999B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E999B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E999B4'>&nbsp;@&nbsp;</font><font style='background-color: #E999B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E999B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E999B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.5f2ff2p126F;
    static { NAMED.put("bright magenta red", -0x1.5f2ff2p126F); LIST.add(-0x1.5f2ff2p126F); }

    /**
     * This color constant "bold pure red" has RGBA8888 code {@code FF0F24FF}, H 0.02745098, S 1.0, L 0.5019608, alpha 1.0, and chroma 1.699085.
     * It can be represented as a packed float with the constant {@code -0x1.01fe0ep126F}.
     * <pre>
     * <font style='background-color: #FF0F24;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0F24; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0F24;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0F24'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0F24'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0F24'>&nbsp;@&nbsp;</font><font style='background-color: #FF0F24; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0F24;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0F24; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_RED = -0x1.01fe0ep126F;
    static { NAMED.put("bold pure red", -0x1.01fe0ep126F); LIST.add(-0x1.01fe0ep126F); }

    /**
     * This color constant "bold brown red" has RGBA8888 code {@code DA5725FF}, H 0.05882353, S 0.96862745, L 0.5019608, alpha 1.0, and chroma 1.2184371.
     * It can be represented as a packed float with the constant {@code -0x1.01ee1ep126F}.
     * <pre>
     * <font style='background-color: #DA5725;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA5725; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA5725;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA5725'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA5725'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA5725'>&nbsp;@&nbsp;</font><font style='background-color: #DA5725; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA5725;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA5725; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_RED = -0x1.01ee1ep126F;
    static { NAMED.put("bold brown red", -0x1.01ee1ep126F); LIST.add(-0x1.01ee1ep126F); }

    /**
     * This color constant "bold red brown" has RGBA8888 code {@code C1692FFF}, H 0.08235294, S 0.9137255, L 0.5019608, alpha 1.0, and chroma 0.9074839.
     * It can be represented as a packed float with the constant {@code -0x1.01d22ap126F}.
     * <pre>
     * <font style='background-color: #C1692F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1692F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1692F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1692F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1692F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1692F'>&nbsp;@&nbsp;</font><font style='background-color: #C1692F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1692F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1692F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_BROWN = -0x1.01d22ap126F;
    static { NAMED.put("bold red brown", -0x1.01d22ap126F); LIST.add(-0x1.01d22ap126F); }

    /**
     * This color constant "bold pure brown" has RGBA8888 code {@code A27855FF}, H 0.11764706, S 0.58431375, L 0.5019608, alpha 1.0, and chroma 0.45587787.
     * It can be represented as a packed float with the constant {@code -0x1.012a3cp126F}.
     * <pre>
     * <font style='background-color: #A27855;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A27855; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A27855;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A27855'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A27855'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A27855'>&nbsp;@&nbsp;</font><font style='background-color: #A27855; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A27855;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A27855; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BROWN = -0x1.012a3cp126F;
    static { NAMED.put("bold pure brown", -0x1.012a3cp126F); LIST.add(-0x1.012a3cp126F); }

    /**
     * This color constant "bold orange brown" has RGBA8888 code {@code AD744AFF}, H 0.105882354, S 0.7019608, L 0.5019608, alpha 1.0, and chroma 0.5866336.
     * It can be represented as a packed float with the constant {@code -0x1.016636p126F}.
     * <pre>
     * <font style='background-color: #AD744A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD744A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD744A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD744A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD744A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD744A'>&nbsp;@&nbsp;</font><font style='background-color: #AD744A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD744A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD744A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_BROWN = -0x1.016636p126F;
    static { NAMED.put("bold orange brown", -0x1.016636p126F); LIST.add(-0x1.016636p126F); }

    /**
     * This color constant "bold brown orange" has RGBA8888 code {@code B57044FF}, H 0.09411765, S 0.75686276, L 0.5019608, alpha 1.0, and chroma 0.6850679.
     * It can be represented as a packed float with the constant {@code -0x1.01823p126F}.
     * <pre>
     * <font style='background-color: #B57044;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B57044; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B57044;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B57044'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B57044'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B57044'>&nbsp;@&nbsp;</font><font style='background-color: #B57044; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B57044;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B57044; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_ORANGE = -0x1.01823p126F;
    static { NAMED.put("bold brown orange", -0x1.01823p126F); LIST.add(-0x1.01823p126F); }

    /**
     * This color constant "bold pure orange" has RGBA8888 code {@code CA6413FF}, H 0.078431375, S 1.0, L 0.5019608, alpha 1.0, and chroma 1.0277785.
     * It can be represented as a packed float with the constant {@code -0x1.01fe28p126F}.
     * <pre>
     * <font style='background-color: #CA6413;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA6413; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA6413;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA6413'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA6413'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA6413'>&nbsp;@&nbsp;</font><font style='background-color: #CA6413; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA6413;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA6413; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_ORANGE = -0x1.01fe28p126F;
    static { NAMED.put("bold pure orange", -0x1.01fe28p126F); LIST.add(-0x1.01fe28p126F); }

    /**
     * This color constant "bold saffron orange" has RGBA8888 code {@code B17248FF}, H 0.09803922, S 0.72156864, L 0.5019608, alpha 1.0, and chroma 0.63512516.
     * It can be represented as a packed float with the constant {@code -0x1.017032p126F}.
     * <pre>
     * <font style='background-color: #B17248;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B17248; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B17248;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B17248'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B17248'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B17248'>&nbsp;@&nbsp;</font><font style='background-color: #B17248; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B17248;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B17248; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_ORANGE = -0x1.017032p126F;
    static { NAMED.put("bold saffron orange", -0x1.017032p126F); LIST.add(-0x1.017032p126F); }

    /**
     * This color constant "bold orange saffron" has RGBA8888 code {@code A7764EFF}, H 0.11372549, S 0.65882355, L 0.5019608, alpha 1.0, and chroma 0.5253267.
     * It can be represented as a packed float with the constant {@code -0x1.01503ap126F}.
     * <pre>
     * <font style='background-color: #A7764E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7764E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7764E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A7764E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A7764E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A7764E'>&nbsp;@&nbsp;</font><font style='background-color: #A7764E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7764E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7764E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_SAFFRON = -0x1.01503ap126F;
    static { NAMED.put("bold orange saffron", -0x1.01503ap126F); LIST.add(-0x1.01503ap126F); }

    /**
     * This color constant "bold pure saffron" has RGBA8888 code {@code A07953FF}, H 0.1254902, S 0.60784316, L 0.5019608, alpha 1.0, and chroma 0.45546156.
     * It can be represented as a packed float with the constant {@code -0x1.01364p126F}.
     * <pre>
     * <font style='background-color: #A07953;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A07953; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A07953;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A07953'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A07953'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A07953'>&nbsp;@&nbsp;</font><font style='background-color: #A07953; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A07953;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A07953; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_SAFFRON = -0x1.01364p126F;
    static { NAMED.put("bold pure saffron", -0x1.01364p126F); LIST.add(-0x1.01364p126F); }

    /**
     * This color constant "bold yellow saffron" has RGBA8888 code {@code A27B00FF}, H 0.16470589, S 1.0, L 0.5019608, alpha 1.0, and chroma 0.6463561.
     * It can be represented as a packed float with the constant {@code -0x1.01fe54p126F}.
     * <pre>
     * <font style='background-color: #A27B00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A27B00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A27B00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A27B00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A27B00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A27B00'>&nbsp;@&nbsp;</font><font style='background-color: #A27B00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A27B00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A27B00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_SAFFRON = -0x1.01fe54p126F;
    static { NAMED.put("bold yellow saffron", -0x1.01fe54p126F); LIST.add(-0x1.01fe54p126F); }

    /**
     * This color constant "bold saffron yellow" has RGBA8888 code {@code 938025FF}, H 0.19607843, S 0.96862745, L 0.5019608, alpha 1.0, and chroma 0.58709395.
     * It can be represented as a packed float with the constant {@code -0x1.01ee64p126F}.
     * <pre>
     * <font style='background-color: #938025;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #938025; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #938025;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #938025'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #938025'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #938025'>&nbsp;@&nbsp;</font><font style='background-color: #938025; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #938025;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #938025; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_YELLOW = -0x1.01ee64p126F;
    static { NAMED.put("bold saffron yellow", -0x1.01ee64p126F); LIST.add(-0x1.01ee64p126F); }

    /**
     * This color constant "bold pure yellow" has RGBA8888 code {@code 858500FF}, H 0.23921569, S 1.0, L 0.5019608, alpha 1.0, and chroma 0.592825.
     * It can be represented as a packed float with the constant {@code -0x1.01fe7ap126F}.
     * <pre>
     * <font style='background-color: #858500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #858500; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #858500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #858500'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #858500'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #858500'>&nbsp;@&nbsp;</font><font style='background-color: #858500; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #858500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #858500; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_YELLOW = -0x1.01fe7ap126F;
    static { NAMED.put("bold pure yellow", -0x1.01fe7ap126F); LIST.add(-0x1.01fe7ap126F); }

    /**
     * This color constant "bold lime yellow" has RGBA8888 code {@code 7C8724FF}, H 0.25882354, S 0.972549, L 0.5019608, alpha 1.0, and chroma 0.58472824.
     * It can be represented as a packed float with the constant {@code -0x1.01f084p126F}.
     * <pre>
     * <font style='background-color: #7C8724;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C8724; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C8724;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C8724'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C8724'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C8724'>&nbsp;@&nbsp;</font><font style='background-color: #7C8724; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C8724;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C8724; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_YELLOW = -0x1.01f084p126F;
    static { NAMED.put("bold lime yellow", -0x1.01f084p126F); LIST.add(-0x1.01f084p126F); }

    /**
     * This color constant "bold yellow lime" has RGBA8888 code {@code 708A00FF}, H 0.28235295, S 1.0, L 0.5019608, alpha 1.0, and chroma 0.6243221.
     * It can be represented as a packed float with the constant {@code -0x1.01fe9p126F}.
     * <pre>
     * <font style='background-color: #708A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #708A00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #708A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #708A00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #708A00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #708A00'>&nbsp;@&nbsp;</font><font style='background-color: #708A00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #708A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #708A00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_LIME = -0x1.01fe9p126F;
    static { NAMED.put("bold yellow lime", -0x1.01fe9p126F); LIST.add(-0x1.01fe9p126F); }

    /**
     * This color constant "bold pure lime" has RGBA8888 code {@code 648D24FF}, H 0.30588236, S 0.972549, L 0.5019608, alpha 1.0, and chroma 0.64609045.
     * It can be represented as a packed float with the constant {@code -0x1.01f09cp126F}.
     * <pre>
     * <font style='background-color: #648D24;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #648D24; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #648D24;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #648D24'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #648D24'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #648D24'>&nbsp;@&nbsp;</font><font style='background-color: #648D24; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #648D24;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #648D24; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_LIME = -0x1.01f09cp126F;
    static { NAMED.put("bold pure lime", -0x1.01f09cp126F); LIST.add(-0x1.01f09cp126F); }

    /**
     * This color constant "bold green lime" has RGBA8888 code {@code 529000FF}, H 0.32156864, S 1.0, L 0.5019608, alpha 1.0, and chroma 0.70295113.
     * It can be represented as a packed float with the constant {@code -0x1.01fea4p126F}.
     * <pre>
     * <font style='background-color: #529000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #529000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #529000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #529000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #529000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #529000'>&nbsp;@&nbsp;</font><font style='background-color: #529000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #529000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #529000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_LIME = -0x1.01fea4p126F;
    static { NAMED.put("bold green lime", -0x1.01fea4p126F); LIST.add(-0x1.01fea4p126F); }

    /**
     * This color constant "bold lime green" has RGBA8888 code {@code 479124FF}, H 0.3372549, S 0.972549, L 0.5019608, alpha 1.0, and chroma 0.7334328.
     * It can be represented as a packed float with the constant {@code -0x1.01f0acp126F}.
     * <pre>
     * <font style='background-color: #479124;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #479124; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #479124;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #479124'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #479124'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #479124'>&nbsp;@&nbsp;</font><font style='background-color: #479124; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #479124;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #479124; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_GREEN = -0x1.01f0acp126F;
    static { NAMED.put("bold lime green", -0x1.01f0acp126F); LIST.add(-0x1.01f0acp126F); }

    /**
     * This color constant "bold pure green" has RGBA8888 code {@code 109600FF}, H 0.3529412, S 1.0, L 0.5019608, alpha 1.0, and chroma 0.8220117.
     * It can be represented as a packed float with the constant {@code -0x1.01feb4p126F}.
     * <pre>
     * <font style='background-color: #109600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #109600; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #109600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #109600'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #109600'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #109600'>&nbsp;@&nbsp;</font><font style='background-color: #109600; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #109600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #109600; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_GREEN = -0x1.01feb4p126F;
    static { NAMED.put("bold pure green", -0x1.01feb4p126F); LIST.add(-0x1.01feb4p126F); }

    /**
     * This color constant "bold cyan green" has RGBA8888 code {@code 269165FF}, H 0.4117647, S 0.96862745, L 0.5019608, alpha 1.0, and chroma 0.526646.
     * It can be represented as a packed float with the constant {@code -0x1.01eed2p126F}.
     * <pre>
     * <font style='background-color: #269165;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #269165; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #269165;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #269165'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #269165'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #269165'>&nbsp;@&nbsp;</font><font style='background-color: #269165; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #269165;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #269165; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_GREEN = -0x1.01eed2p126F;
    static { NAMED.put("bold cyan green", -0x1.01eed2p126F); LIST.add(-0x1.01eed2p126F); }

    /**
     * This color constant "bold green cyan" has RGBA8888 code {@code 00917EFF}, H 0.4745098, S 1.0, L 0.5019608, alpha 1.0, and chroma 0.44126895.
     * It can be represented as a packed float with the constant {@code -0x1.01fef2p126F}.
     * <pre>
     * <font style='background-color: #00917E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00917E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00917E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00917E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00917E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00917E'>&nbsp;@&nbsp;</font><font style='background-color: #00917E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00917E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00917E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_CYAN = -0x1.01fef2p126F;
    static { NAMED.put("bold green cyan", -0x1.01fef2p126F); LIST.add(-0x1.01fef2p126F); }

    /**
     * This color constant "bold pure cyan" has RGBA8888 code {@code 288E8EFF}, H 0.53333336, S 0.972549, L 0.5019608, alpha 1.0, and chroma 0.4135402.
     * It can be represented as a packed float with the constant {@code -0x1.01f11p126F}.
     * <pre>
     * <font style='background-color: #288E8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #288E8E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #288E8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #288E8E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #288E8E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #288E8E'>&nbsp;@&nbsp;</font><font style='background-color: #288E8E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #288E8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #288E8E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_CYAN = -0x1.01f11p126F;
    static { NAMED.put("bold pure cyan", -0x1.01f11p126F); LIST.add(-0x1.01f11p126F); }

    /**
     * This color constant "bold blue cyan" has RGBA8888 code {@code 008DA5FF}, H 0.6039216, S 1.0, L 0.5019608, alpha 1.0, and chroma 0.49027142.
     * It can be represented as a packed float with the constant {@code -0x1.01ff34p126F}.
     * <pre>
     * <font style='background-color: #008DA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #008DA5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #008DA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #008DA5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #008DA5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #008DA5'>&nbsp;@&nbsp;</font><font style='background-color: #008DA5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #008DA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #008DA5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_CYAN = -0x1.01ff34p126F;
    static { NAMED.put("bold blue cyan", -0x1.01ff34p126F); LIST.add(-0x1.01ff34p126F); }

    /**
     * This color constant "bold cyan blue" has RGBA8888 code {@code 2D87C5FF}, H 0.6745098, S 0.96862745, L 0.5019608, alpha 1.0, and chroma 0.7287281.
     * It can be represented as a packed float with the constant {@code -0x1.01ef58p126F}.
     * <pre>
     * <font style='background-color: #2D87C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D87C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D87C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D87C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D87C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D87C5'>&nbsp;@&nbsp;</font><font style='background-color: #2D87C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D87C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D87C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_BLUE = -0x1.01ef58p126F;
    static { NAMED.put("bold cyan blue", -0x1.01ef58p126F); LIST.add(-0x1.01ef58p126F); }

    /**
     * This color constant "bold pure blue" has RGBA8888 code {@code 736DFFFF}, H 0.7411765, S 0.9490196, L 0.5019608, alpha 1.0, and chroma 1.105383.
     * It can be represented as a packed float with the constant {@code -0x1.01e57ap126F}.
     * <pre>
     * <font style='background-color: #736DFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #736DFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #736DFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #736DFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #736DFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #736DFF'>&nbsp;@&nbsp;</font><font style='background-color: #736DFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #736DFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #736DFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BLUE = -0x1.01e57ap126F;
    static { NAMED.put("bold pure blue", -0x1.01e57ap126F); LIST.add(-0x1.01e57ap126F); }

    /**
     * This color constant "bold violet blue" has RGBA8888 code {@code 866AF2FF}, H 0.7529412, S 0.85882354, L 0.5019608, alpha 1.0, and chroma 1.0038126.
     * It can be represented as a packed float with the constant {@code -0x1.01b78p126F}.
     * <pre>
     * <font style='background-color: #866AF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #866AF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #866AF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #866AF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #866AF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #866AF2'>&nbsp;@&nbsp;</font><font style='background-color: #866AF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #866AF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #866AF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_BLUE = -0x1.01b78p126F;
    static { NAMED.put("bold violet blue", -0x1.01b78p126F); LIST.add(-0x1.01b78p126F); }

    /**
     * This color constant "bold blue violet" has RGBA8888 code {@code 975EFFFF}, H 0.7647059, S 0.9490196, L 0.5019608, alpha 1.0, and chroma 1.1192222.
     * It can be represented as a packed float with the constant {@code -0x1.01e586p126F}.
     * <pre>
     * <font style='background-color: #975EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #975EFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #975EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #975EFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #975EFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #975EFF'>&nbsp;@&nbsp;</font><font style='background-color: #975EFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #975EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #975EFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_VIOLET = -0x1.01e586p126F;
    static { NAMED.put("bold blue violet", -0x1.01e586p126F); LIST.add(-0x1.01e586p126F); }

    /**
     * This color constant "bold pure violet" has RGBA8888 code {@code 9D5FF2FF}, H 0.77254903, S 0.85882354, L 0.5019608, alpha 1.0, and chroma 1.0220835.
     * It can be represented as a packed float with the constant {@code -0x1.01b78ap126F}.
     * <pre>
     * <font style='background-color: #9D5FF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D5FF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D5FF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D5FF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D5FF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D5FF2'>&nbsp;@&nbsp;</font><font style='background-color: #9D5FF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D5FF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D5FF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_VIOLET = -0x1.01b78ap126F;
    static { NAMED.put("bold pure violet", -0x1.01b78ap126F); LIST.add(-0x1.01b78ap126F); }

    /**
     * This color constant "bold purple violet" has RGBA8888 code {@code AF4EFFFF}, H 0.78431374, S 0.9490196, L 0.5019608, alpha 1.0, and chroma 1.1503712.
     * It can be represented as a packed float with the constant {@code -0x1.01e59p126F}.
     * <pre>
     * <font style='background-color: #AF4EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF4EFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF4EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF4EFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF4EFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF4EFF'>&nbsp;@&nbsp;</font><font style='background-color: #AF4EFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF4EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF4EFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_VIOLET = -0x1.01e59p126F;
    static { NAMED.put("bold purple violet", -0x1.01e59p126F); LIST.add(-0x1.01e59p126F); }

    /**
     * This color constant "bold violet purple" has RGBA8888 code {@code B64EF2FF}, H 0.79607844, S 0.85882354, L 0.5019608, alpha 1.0, and chroma 1.0666307.
     * It can be represented as a packed float with the constant {@code -0x1.01b796p126F}.
     * <pre>
     * <font style='background-color: #B64EF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B64EF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B64EF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B64EF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B64EF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B64EF2'>&nbsp;@&nbsp;</font><font style='background-color: #B64EF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B64EF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B64EF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_PURPLE = -0x1.01b796p126F;
    static { NAMED.put("bold violet purple", -0x1.01b796p126F); LIST.add(-0x1.01b796p126F); }

    /**
     * This color constant "bold pure purple" has RGBA8888 code {@code C636FFFF}, H 0.8039216, S 0.95686275, L 0.5019608, alpha 1.0, and chroma 1.2119372.
     * It can be represented as a packed float with the constant {@code -0x1.01e99ap126F}.
     * <pre>
     * <font style='background-color: #C636FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C636FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C636FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C636FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C636FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C636FF'>&nbsp;@&nbsp;</font><font style='background-color: #C636FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C636FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C636FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_PURPLE = -0x1.01e99ap126F;
    static { NAMED.put("bold pure purple", -0x1.01e99ap126F); LIST.add(-0x1.01e99ap126F); }

    /**
     * This color constant "bold magenta purple" has RGBA8888 code {@code CF31F0FF}, H 0.8235294, S 0.972549, L 0.5019608, alpha 1.0, and chroma 1.2837408.
     * It can be represented as a packed float with the constant {@code -0x1.01f1a4p126F}.
     * <pre>
     * <font style='background-color: #CF31F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF31F0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF31F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CF31F0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CF31F0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CF31F0'>&nbsp;@&nbsp;</font><font style='background-color: #CF31F0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF31F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF31F0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_PURPLE = -0x1.01f1a4p126F;
    static { NAMED.put("bold magenta purple", -0x1.01f1a4p126F); LIST.add(-0x1.01f1a4p126F); }

    /**
     * This color constant "bold purple magenta" has RGBA8888 code {@code DE00EFFF}, H 0.8392157, S 1.0, L 0.5019608, alpha 1.0, and chroma 1.26354.
     * It can be represented as a packed float with the constant {@code -0x1.01ffacp126F}.
     * <pre>
     * <font style='background-color: #DE00EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE00EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE00EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DE00EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DE00EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DE00EF'>&nbsp;@&nbsp;</font><font style='background-color: #DE00EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE00EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE00EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_MAGENTA = -0x1.01ffacp126F;
    static { NAMED.put("bold purple magenta", -0x1.01ffacp126F); LIST.add(-0x1.01ffacp126F); }

    /**
     * This color constant "bold pure magenta" has RGBA8888 code {@code D92FD9FF}, H 0.85490197, S 0.972549, L 0.5019608, alpha 1.0, and chroma 1.189663.
     * It can be represented as a packed float with the constant {@code -0x1.01f1b4p126F}.
     * <pre>
     * <font style='background-color: #D92FD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D92FD9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D92FD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D92FD9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D92FD9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D92FD9'>&nbsp;@&nbsp;</font><font style='background-color: #D92FD9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D92FD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D92FD9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_MAGENTA = -0x1.01f1b4p126F;
    static { NAMED.put("bold pure magenta", -0x1.01f1b4p126F); LIST.add(-0x1.01f1b4p126F); }

    /**
     * This color constant "bold red magenta" has RGBA8888 code {@code EF00BAFF}, H 0.9137255, S 1.0, L 0.5019608, alpha 1.0, and chroma 1.1871291.
     * It can be represented as a packed float with the constant {@code -0x1.01ffd2p126F}.
     * <pre>
     * <font style='background-color: #EF00BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF00BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF00BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF00BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF00BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF00BA'>&nbsp;@&nbsp;</font><font style='background-color: #EF00BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF00BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF00BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_MAGENTA = -0x1.01ffd2p126F;
    static { NAMED.put("bold red magenta", -0x1.01ffd2p126F); LIST.add(-0x1.01ffd2p126F); }

    /**
     * This color constant "bold magenta red" has RGBA8888 code {@code EF298AFF}, H 0.9764706, S 0.96862745, L 0.5019608, alpha 1.0, and chroma 1.3036034.
     * It can be represented as a packed float with the constant {@code -0x1.01eff2p126F}.
     * <pre>
     * <font style='background-color: #EF298A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF298A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF298A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF298A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF298A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF298A'>&nbsp;@&nbsp;</font><font style='background-color: #EF298A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF298A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF298A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_RED = -0x1.01eff2p126F;
    static { NAMED.put("bold magenta red", -0x1.01eff2p126F); LIST.add(-0x1.01eff2p126F); }

    /**
     * All names for colors in this palette, in alphabetical order. You can fetch the corresponding packed float color
     * by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES = NAMED.keys().toArray();
    static { NAMES.sort(); }
    /**
     * All names for colors in this palette, sorted by hue from red to yellow to green to blue, with grayscale colors
     * before any other colors. You can fetch the corresponding packed float color by looking up a name in
     * {@link #NAMED}.
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
                final float c1 = NAMED.get(o1, UBE_TRANSPARENT), c2 = NAMED.get(o2, UBE_TRANSPARENT);
                if(ColorTools.alphaInt(c1) < 128) return -10000;
                else if(ColorTools.alphaInt(c2) < 128) return 10000;
                final float s1 = ColorTools.channelS(c1), s2 = ColorTools.channelS(c2);
                if(s1 <= 0.05f && s2 > 0.05f)
                    return -1000;
                else if(s1 > 0.05f && s2 <= 0.05f)
                    return 1000;
                else if(s1 <= 0.05f && s2 <= 0.05f)
                    return (int)Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
                else
                    return 2 * (int)Math.signum(ColorTools.channelH(c1) - ColorTools.channelH(c2))
                            + (int)Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
            }
        });
        NAMES_BY_LIGHTNESS.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Float.compare(ColorTools.channelL(NAMED.get(o1, UBE_TRANSPARENT)), ColorTools.channelL(NAMED.get(o2, UBE_TRANSPARENT)));
            }
        });
    }

    /**
     * Changes the existing RGBA Color instances in {@link Colors} to use HSLuv and so be able to be shown normally by
     * {@link ColorfulBatch} or a Batch using {@link com.github.tommyettinger.colorful.Shaders#fragmentShaderCielab}.
     * Any colors used in libGDX text markup look up their values in Colors, so calling this can help display fonts
     * where markup is enabled. This only needs to be called once, and if you call {@link #appendToKnownColors()}, then
     * that should be done after this to avoid mixing RGBA and HSLuv colors.
     * <br>
     * This is a duplicate of a method with the same name in SimplePalette; you should still only call this method once,
     * regardless of where it was from.
     */
    public static void editKnownColors(){
        for(Color c : Colors.getColors().values()) {
            final float f = ColorTools.fromColor(c);
            c.set(channelH(f), channelS(f), channelL(f), c.a);
        }
    }

    /**
     * Appends HSLuv-compatible Color instances to the map in {@link Colors}, using the names in {@link #NAMES} (which
     * are "Title Cased" instead of "ALL UPPER CASE"). If you intend to still use the existing values in Colors, you
     * should call {@link #editKnownColors()} first; otherwise you can just always use "Title Cased" color names.
     * <br>
     * This can be used alongside the method with the same name in SimplePalette, since that uses "lower cased" names.
     */
    public static void appendToKnownColors(){
        for(ObjectFloatMap.Entry<String> ent : NAMED) {
            final float f = ent.value;
            Colors.put(ent.key, new Color(channelH(f), channelS(f), channelL(f), alpha(f)));
        }
    }
}
