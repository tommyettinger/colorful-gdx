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
     * This color constant "black red" has RGBA8888 code {@code 943D3FFF}, H 0.03137255, S 0.5529412, L 0.34901962, alpha 1.0, and chroma 0.6911386.
     * It can be represented as a packed float with the constant {@code -0x1.b31a1p125F}.
     * <pre>
     * <font style='background-color: #943D3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #943D3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #943D3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #943D3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #943D3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #943D3F'>&nbsp;@&nbsp;</font><font style='background-color: #943D3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #943D3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #943D3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_RED = -0x1.b31a1p125F;
    static { NAMED.put("black red", -0x1.b31a1p125F); LIST.add(-0x1.b31a1p125F); }

    /**
     * This color constant "lead red" has RGBA8888 code {@code B44C4EFF}, H 0.03137255, S 0.54509807, L 0.42745098, alpha 1.0, and chroma 0.82993037.
     * It can be represented as a packed float with the constant {@code -0x1.db161p125F}.
     * <pre>
     * <font style='background-color: #B44C4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B44C4E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B44C4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B44C4E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B44C4E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B44C4E'>&nbsp;@&nbsp;</font><font style='background-color: #B44C4E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B44C4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B44C4E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_RED = -0x1.db161p125F;
    static { NAMED.put("lead red", -0x1.db161p125F); LIST.add(-0x1.db161p125F); }

    /**
     * This color constant "gray red" has RGBA8888 code {@code D15F61FF}, H 0.03137255, S 0.5019608, L 0.50980395, alpha 1.0, and chroma 0.8398732.
     * It can be represented as a packed float with the constant {@code -0x1.05001p126F}.
     * <pre>
     * <font style='background-color: #D15F61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D15F61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D15F61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D15F61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D15F61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D15F61'>&nbsp;@&nbsp;</font><font style='background-color: #D15F61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D15F61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D15F61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_RED = -0x1.05001p126F;
    static { NAMED.put("gray red", -0x1.05001p126F); LIST.add(-0x1.05001p126F); }

    /**
     * This color constant "silver red" has RGBA8888 code {@code D78687FF}, H 0.03137255, S 0.43529412, L 0.60784316, alpha 1.0, and chroma 0.48317093.
     * It can be represented as a packed float with the constant {@code -0x1.36de1p126F}.
     * <pre>
     * <font style='background-color: #D78687;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D78687; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D78687;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D78687'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D78687'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D78687'>&nbsp;@&nbsp;</font><font style='background-color: #D78687; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D78687;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D78687; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_RED = -0x1.36de1p126F;
    static { NAMED.put("silver red", -0x1.36de1p126F); LIST.add(-0x1.36de1p126F); }

    /**
     * This color constant "white red" has RGBA8888 code {@code E0AAABFF}, H 0.02745098, S 0.43529412, L 0.7176471, alpha 1.0, and chroma 0.29299197.
     * It can be represented as a packed float with the constant {@code -0x1.6ede0ep126F}.
     * <pre>
     * <font style='background-color: #E0AAAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0AAAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0AAAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0AAAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0AAAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0AAAB'>&nbsp;@&nbsp;</font><font style='background-color: #E0AAAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0AAAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0AAAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_RED = -0x1.6ede0ep126F;
    static { NAMED.put("white red", -0x1.6ede0ep126F); LIST.add(-0x1.6ede0ep126F); }

    /**
     * This color constant "black brown" has RGBA8888 code {@code 715E52FF}, H 0.11372549, S 0.29411766, L 0.38039216, alpha 1.0, and chroma 0.17973216.
     * It can be represented as a packed float with the constant {@code -0x1.c2963ap125F}.
     * <pre>
     * <font style='background-color: #715E52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #715E52; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #715E52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #715E52'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #715E52'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #715E52'>&nbsp;@&nbsp;</font><font style='background-color: #715E52; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #715E52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #715E52; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BROWN = -0x1.c2963ap125F;
    static { NAMED.put("black brown", -0x1.c2963ap125F); LIST.add(-0x1.c2963ap125F); }

    /**
     * This color constant "lead brown" has RGBA8888 code {@code 8A7465FF}, H 0.11764706, S 0.29803923, L 0.47058824, alpha 1.0, and chroma 0.21873577.
     * It can be represented as a packed float with the constant {@code -0x1.f0983cp125F}.
     * <pre>
     * <font style='background-color: #8A7465;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A7465; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A7465;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A7465'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A7465'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A7465'>&nbsp;@&nbsp;</font><font style='background-color: #8A7465; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A7465;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A7465; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BROWN = -0x1.f0983cp125F;
    static { NAMED.put("lead brown", -0x1.f0983cp125F); LIST.add(-0x1.f0983cp125F); }

    /**
     * This color constant "gray brown" has RGBA8888 code {@code A08776FF}, H 0.11764706, S 0.29411766, L 0.54509807, alpha 1.0, and chroma 0.24793744.
     * It can be represented as a packed float with the constant {@code -0x1.16963cp126F}.
     * <pre>
     * <font style='background-color: #A08776;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A08776; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A08776;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A08776'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A08776'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A08776'>&nbsp;@&nbsp;</font><font style='background-color: #A08776; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A08776;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A08776; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BROWN = -0x1.16963cp126F;
    static { NAMED.put("gray brown", -0x1.16963cp126F); LIST.add(-0x1.16963cp126F); }

    /**
     * This color constant "silver brown" has RGBA8888 code {@code B79A87FF}, H 0.11764706, S 0.2901961, L 0.62352943, alpha 1.0, and chroma 0.2770615.
     * It can be represented as a packed float with the constant {@code -0x1.3e943cp126F}.
     * <pre>
     * <font style='background-color: #B79A87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B79A87; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B79A87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B79A87'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B79A87'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B79A87'>&nbsp;@&nbsp;</font><font style='background-color: #B79A87; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B79A87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B79A87; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BROWN = -0x1.3e943cp126F;
    static { NAMED.put("silver brown", -0x1.3e943cp126F); LIST.add(-0x1.3e943cp126F); }

    /**
     * This color constant "white brown" has RGBA8888 code {@code D1B29EFF}, H 0.11764706, S 0.26666668, L 0.7176471, alpha 1.0, and chroma 0.26920757.
     * It can be represented as a packed float with the constant {@code -0x1.6e883cp126F}.
     * <pre>
     * <font style='background-color: #D1B29E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1B29E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1B29E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1B29E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1B29E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1B29E'>&nbsp;@&nbsp;</font><font style='background-color: #D1B29E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1B29E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1B29E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BROWN = -0x1.6e883cp126F;
    static { NAMED.put("white brown", -0x1.6e883cp126F); LIST.add(-0x1.6e883cp126F); }

    /**
     * This color constant "black orange" has RGBA8888 code {@code 805341FF}, H 0.078431375, S 0.53333336, L 0.36862746, alpha 1.0, and chroma 0.407396.
     * It can be represented as a packed float with the constant {@code -0x1.bd1028p125F}.
     * <pre>
     * <font style='background-color: #805341;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #805341; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #805341;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #805341'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #805341'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #805341'>&nbsp;@&nbsp;</font><font style='background-color: #805341; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #805341;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #805341; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_ORANGE = -0x1.bd1028p125F;
    static { NAMED.put("black orange", -0x1.bd1028p125F); LIST.add(-0x1.bd1028p125F); }

    /**
     * This color constant "lead orange" has RGBA8888 code {@code 99654FFF}, H 0.08235294, S 0.53333336, L 0.44313726, alpha 1.0, and chroma 0.47048905.
     * It can be represented as a packed float with the constant {@code -0x1.e3102ap125F}.
     * <pre>
     * <font style='background-color: #99654F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99654F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99654F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #99654F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #99654F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #99654F'>&nbsp;@&nbsp;</font><font style='background-color: #99654F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99654F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99654F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_ORANGE = -0x1.e3102ap125F;
    static { NAMED.put("lead orange", -0x1.e3102ap125F); LIST.add(-0x1.e3102ap125F); }

    /**
     * This color constant "gray orange" has RGBA8888 code {@code B4775EFF}, H 0.08235294, S 0.5254902, L 0.52156866, alpha 1.0, and chroma 0.54107356.
     * It can be represented as a packed float with the constant {@code -0x1.0b0c2ap126F}.
     * <pre>
     * <font style='background-color: #B4775E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4775E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4775E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4775E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4775E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4775E'>&nbsp;@&nbsp;</font><font style='background-color: #B4775E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4775E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4775E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_ORANGE = -0x1.0b0c2ap126F;
    static { NAMED.put("gray orange", -0x1.0b0c2ap126F); LIST.add(-0x1.0b0c2ap126F); }

    /**
     * This color constant "silver orange" has RGBA8888 code {@code D28C70FF}, H 0.078431375, S 0.50980395, L 0.6117647, alpha 1.0, and chroma 0.63006085.
     * It can be represented as a packed float with the constant {@code -0x1.390428p126F}.
     * <pre>
     * <font style='background-color: #D28C70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28C70; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28C70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D28C70'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D28C70'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D28C70'>&nbsp;@&nbsp;</font><font style='background-color: #D28C70; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28C70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28C70; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_ORANGE = -0x1.390428p126F;
    static { NAMED.put("silver orange", -0x1.390428p126F); LIST.add(-0x1.390428p126F); }

    /**
     * This color constant "white orange" has RGBA8888 code {@code E0AB9AFF}, H 0.078431375, S 0.42352942, L 0.7137255, alpha 1.0, and chroma 0.3440719.
     * It can be represented as a packed float with the constant {@code -0x1.6cd828p126F}.
     * <pre>
     * <font style='background-color: #E0AB9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0AB9A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0AB9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0AB9A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0AB9A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0AB9A'>&nbsp;@&nbsp;</font><font style='background-color: #E0AB9A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0AB9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0AB9A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_ORANGE = -0x1.6cd828p126F;
    static { NAMED.put("white orange", -0x1.6cd828p126F); LIST.add(-0x1.6cd828p126F); }

    /**
     * This color constant "black saffron" has RGBA8888 code {@code 726153FF}, H 0.12941177, S 0.3137255, L 0.39215687, alpha 1.0, and chroma 0.18214116.
     * It can be represented as a packed float with the constant {@code -0x1.c8a042p125F}.
     * <pre>
     * <font style='background-color: #726153;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #726153; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #726153;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #726153'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #726153'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #726153'>&nbsp;@&nbsp;</font><font style='background-color: #726153; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #726153;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #726153; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_SAFFRON = -0x1.c8a042p125F;
    static { NAMED.put("black saffron", -0x1.c8a042p125F); LIST.add(-0x1.c8a042p125F); }

    /**
     * This color constant "lead saffron" has RGBA8888 code {@code 8C7867FF}, H 0.13333334, S 0.30980393, L 0.48235294, alpha 1.0, and chroma 0.21554944.
     * It can be represented as a packed float with the constant {@code -0x1.f69e44p125F}.
     * <pre>
     * <font style='background-color: #8C7867;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C7867; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C7867;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C7867'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C7867'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C7867'>&nbsp;@&nbsp;</font><font style='background-color: #8C7867; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C7867;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C7867; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_SAFFRON = -0x1.f69e44p125F;
    static { NAMED.put("lead saffron", -0x1.f69e44p125F); LIST.add(-0x1.f69e44p125F); }

    /**
     * This color constant "gray saffron" has RGBA8888 code {@code A48C79FF}, H 0.1254902, S 0.3019608, L 0.5647059, alpha 1.0, and chroma 0.25265828.
     * It can be represented as a packed float with the constant {@code -0x1.209a4p126F}.
     * <pre>
     * <font style='background-color: #A48C79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A48C79; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A48C79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A48C79'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A48C79'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A48C79'>&nbsp;@&nbsp;</font><font style='background-color: #A48C79; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A48C79;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A48C79; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SAFFRON = -0x1.209a4p126F;
    static { NAMED.put("gray saffron", -0x1.209a4p126F); LIST.add(-0x1.209a4p126F); }

    /**
     * This color constant "silver saffron" has RGBA8888 code {@code BA9F8AFF}, H 0.1254902, S 0.29803923, L 0.6392157, alpha 1.0, and chroma 0.2795779.
     * It can be represented as a packed float with the constant {@code -0x1.46984p126F}.
     * <pre>
     * <font style='background-color: #BA9F8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA9F8A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA9F8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA9F8A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA9F8A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA9F8A'>&nbsp;@&nbsp;</font><font style='background-color: #BA9F8A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA9F8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA9F8A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_SAFFRON = -0x1.46984p126F;
    static { NAMED.put("silver saffron", -0x1.46984p126F); LIST.add(-0x1.46984p126F); }

    /**
     * This color constant "white saffron" has RGBA8888 code {@code D2B49CFF}, H 0.12941177, S 0.29803923, L 0.7254902, alpha 1.0, and chroma 0.30777562.
     * It can be represented as a packed float with the constant {@code -0x1.729842p126F}.
     * <pre>
     * <font style='background-color: #D2B49C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B49C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B49C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2B49C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2B49C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2B49C'>&nbsp;@&nbsp;</font><font style='background-color: #D2B49C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B49C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B49C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SAFFRON = -0x1.729842p126F;
    static { NAMED.put("white saffron", -0x1.729842p126F); LIST.add(-0x1.729842p126F); }

    /**
     * This color constant "black yellow" has RGBA8888 code {@code 636342FF}, H 0.23921569, S 0.54509807, L 0.38039216, alpha 1.0, and chroma 0.24765405.
     * It can be represented as a packed float with the constant {@code -0x1.c3167ap125F}.
     * <pre>
     * <font style='background-color: #636342;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #636342; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #636342;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #636342'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #636342'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #636342'>&nbsp;@&nbsp;</font><font style='background-color: #636342; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #636342;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #636342; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_YELLOW = -0x1.c3167ap125F;
    static { NAMED.put("black yellow", -0x1.c3167ap125F); LIST.add(-0x1.c3167ap125F); }

    /**
     * This color constant "lead yellow" has RGBA8888 code {@code 787951FF}, H 0.23921569, S 0.54509807, L 0.4627451, alpha 1.0, and chroma 0.2991545.
     * It can be represented as a packed float with the constant {@code -0x1.ed167ap125F}.
     * <pre>
     * <font style='background-color: #787951;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #787951; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #787951;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #787951'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #787951'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #787951'>&nbsp;@&nbsp;</font><font style='background-color: #787951; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #787951;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #787951; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_YELLOW = -0x1.ed167ap125F;
    static { NAMED.put("lead yellow", -0x1.ed167ap125F); LIST.add(-0x1.ed167ap125F); }

    /**
     * This color constant "gray yellow" has RGBA8888 code {@code 8B8B5EFF}, H 0.23921569, S 0.5411765, L 0.53333336, alpha 1.0, and chroma 0.33964118.
     * It can be represented as a packed float with the constant {@code -0x1.11147ap126F}.
     * <pre>
     * <font style='background-color: #8B8B5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B8B5E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B8B5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B8B5E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B8B5E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B8B5E'>&nbsp;@&nbsp;</font><font style='background-color: #8B8B5E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B8B5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B8B5E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_YELLOW = -0x1.11147ap126F;
    static { NAMED.put("gray yellow", -0x1.11147ap126F); LIST.add(-0x1.11147ap126F); }

    /**
     * This color constant "silver yellow" has RGBA8888 code {@code A2A26EFF}, H 0.23921569, S 0.5372549, L 0.61960787, alpha 1.0, and chroma 0.38750127.
     * It can be represented as a packed float with the constant {@code -0x1.3d127ap126F}.
     * <pre>
     * <font style='background-color: #A2A26E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2A26E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2A26E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2A26E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2A26E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2A26E'>&nbsp;@&nbsp;</font><font style='background-color: #A2A26E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2A26E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2A26E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_YELLOW = -0x1.3d127ap126F;
    static { NAMED.put("silver yellow", -0x1.3d127ap126F); LIST.add(-0x1.3d127ap126F); }

    /**
     * This color constant "white yellow" has RGBA8888 code {@code BBBC80FF}, H 0.23921569, S 0.5294118, L 0.72156864, alpha 1.0, and chroma 0.43855652.
     * It can be represented as a packed float with the constant {@code -0x1.710e7ap126F}.
     * <pre>
     * <font style='background-color: #BBBC80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBBC80; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBBC80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BBBC80'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BBBC80'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BBBC80'>&nbsp;@&nbsp;</font><font style='background-color: #BBBC80; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBBC80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBBC80; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_YELLOW = -0x1.710e7ap126F;
    static { NAMED.put("white yellow", -0x1.710e7ap126F); LIST.add(-0x1.710e7ap126F); }

    /**
     * This color constant "black lime" has RGBA8888 code {@code 506041FF}, H 0.30588236, S 0.49019608, L 0.35686275, alpha 1.0, and chroma 0.23445664.
     * It can be represented as a packed float with the constant {@code -0x1.b6fa9cp125F}.
     * <pre>
     * <font style='background-color: #506041;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #506041; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #506041;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #506041'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #506041'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #506041'>&nbsp;@&nbsp;</font><font style='background-color: #506041; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #506041;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #506041; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LIME = -0x1.b6fa9cp125F;
    static { NAMED.put("black lime", -0x1.b6fa9cp125F); LIST.add(-0x1.b6fa9cp125F); }

    /**
     * This color constant "lead lime" has RGBA8888 code {@code 61744FFF}, H 0.30588236, S 0.49019608, L 0.43137255, alpha 1.0, and chroma 0.28188187.
     * It can be represented as a packed float with the constant {@code -0x1.dcfa9cp125F}.
     * <pre>
     * <font style='background-color: #61744F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61744F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61744F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61744F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61744F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61744F'>&nbsp;@&nbsp;</font><font style='background-color: #61744F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61744F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61744F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_LIME = -0x1.dcfa9cp125F;
    static { NAMED.put("lead lime", -0x1.dcfa9cp125F); LIST.add(-0x1.dcfa9cp125F); }

    /**
     * This color constant "gray lime" has RGBA8888 code {@code 758A5FFF}, H 0.3019608, S 0.4862745, L 0.5137255, alpha 1.0, and chroma 0.32618397.
     * It can be represented as a packed float with the constant {@code -0x1.06f89ap126F}.
     * <pre>
     * <font style='background-color: #758A5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #758A5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #758A5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #758A5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #758A5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #758A5F'>&nbsp;@&nbsp;</font><font style='background-color: #758A5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #758A5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #758A5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LIME = -0x1.06f89ap126F;
    static { NAMED.put("gray lime", -0x1.06f89ap126F); LIST.add(-0x1.06f89ap126F); }

    /**
     * This color constant "silver lime" has RGBA8888 code {@code 8BA471FF}, H 0.3019608, S 0.4862745, L 0.60784316, alpha 1.0, and chroma 0.3814955.
     * It can be represented as a packed float with the constant {@code -0x1.36f89ap126F}.
     * <pre>
     * <font style='background-color: #8BA471;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BA471; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BA471;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8BA471'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8BA471'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8BA471'>&nbsp;@&nbsp;</font><font style='background-color: #8BA471; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BA471;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BA471; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_LIME = -0x1.36f89ap126F;
    static { NAMED.put("silver lime", -0x1.36f89ap126F); LIST.add(-0x1.36f89ap126F); }

    /**
     * This color constant "white lime" has RGBA8888 code {@code A3C186FF}, H 0.30588236, S 0.4745098, L 0.7176471, alpha 1.0, and chroma 0.43833235.
     * It can be represented as a packed float with the constant {@code -0x1.6ef29cp126F}.
     * <pre>
     * <font style='background-color: #A3C186;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3C186; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3C186;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A3C186'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A3C186'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A3C186'>&nbsp;@&nbsp;</font><font style='background-color: #A3C186; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3C186;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3C186; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_LIME = -0x1.6ef29cp126F;
    static { NAMED.put("white lime", -0x1.6ef29cp126F); LIST.add(-0x1.6ef29cp126F); }

    /**
     * This color constant "black green" has RGBA8888 code {@code 446D43FF}, H 0.3529412, S 0.54901963, L 0.3882353, alpha 1.0, and chroma 0.3528105.
     * It can be represented as a packed float with the constant {@code -0x1.c718b4p125F}.
     * <pre>
     * <font style='background-color: #446D43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #446D43; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #446D43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #446D43'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #446D43'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #446D43'>&nbsp;@&nbsp;</font><font style='background-color: #446D43; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #446D43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #446D43; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_GREEN = -0x1.c718b4p125F;
    static { NAMED.put("black green", -0x1.c718b4p125F); LIST.add(-0x1.c718b4p125F); }

    /**
     * This color constant "lead green" has RGBA8888 code {@code 548553FF}, H 0.3529412, S 0.54509807, L 0.4745098, alpha 1.0, and chroma 0.42483765.
     * It can be represented as a packed float with the constant {@code -0x1.f316b4p125F}.
     * <pre>
     * <font style='background-color: #548553;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #548553; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #548553;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #548553'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #548553'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #548553'>&nbsp;@&nbsp;</font><font style='background-color: #548553; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #548553;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #548553; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GREEN = -0x1.f316b4p125F;
    static { NAMED.put("lead green", -0x1.f316b4p125F); LIST.add(-0x1.f316b4p125F); }

    /**
     * This color constant "gray green" has RGBA8888 code {@code 639B61FF}, H 0.3529412, S 0.54509807, L 0.5529412, alpha 1.0, and chroma 0.4906384.
     * It can be represented as a packed float with the constant {@code -0x1.1b16b4p126F}.
     * <pre>
     * <font style='background-color: #639B61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #639B61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #639B61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #639B61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #639B61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #639B61'>&nbsp;@&nbsp;</font><font style='background-color: #639B61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #639B61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #639B61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.1b16b4p126F;
    static { NAMED.put("gray green", -0x1.1b16b4p126F); LIST.add(-0x1.1b16b4p126F); }

    /**
     * This color constant "silver green" has RGBA8888 code {@code 71B06FFF}, H 0.3529412, S 0.5372549, L 0.627451, alpha 1.0, and chroma 0.5435489.
     * It can be represented as a packed float with the constant {@code -0x1.4112b4p126F}.
     * <pre>
     * <font style='background-color: #71B06F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #71B06F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #71B06F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #71B06F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #71B06F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #71B06F'>&nbsp;@&nbsp;</font><font style='background-color: #71B06F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #71B06F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #71B06F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.4112b4p126F;
    static { NAMED.put("silver green", -0x1.4112b4p126F); LIST.add(-0x1.4112b4p126F); }

    /**
     * This color constant "white green" has RGBA8888 code {@code 82C980FF}, H 0.3529412, S 0.5254902, L 0.72156864, alpha 1.0, and chroma 0.6035984.
     * It can be represented as a packed float with the constant {@code -0x1.710cb4p126F}.
     * <pre>
     * <font style='background-color: #82C980;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82C980; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82C980;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #82C980'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #82C980'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #82C980'>&nbsp;@&nbsp;</font><font style='background-color: #82C980; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82C980;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82C980; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_GREEN = -0x1.710cb4p126F;
    static { NAMED.put("white green", -0x1.710cb4p126F); LIST.add(-0x1.710cb4p126F); }

    /**
     * This color constant "black cyan" has RGBA8888 code {@code 476464FF}, H 0.53333336, S 0.49019608, L 0.36862746, alpha 1.0, and chroma 0.15491542.
     * It can be represented as a packed float with the constant {@code -0x1.bcfb1p125F}.
     * <pre>
     * <font style='background-color: #476464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #476464; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #476464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #476464'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #476464'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #476464'>&nbsp;@&nbsp;</font><font style='background-color: #476464; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #476464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #476464; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_CYAN = -0x1.bcfb1p125F;
    static { NAMED.put("black cyan", -0x1.bcfb1p125F); LIST.add(-0x1.bcfb1p125F); }

    /**
     * This color constant "lead cyan" has RGBA8888 code {@code 567978FF}, H 0.5254902, S 0.49411765, L 0.44705883, alpha 1.0, and chroma 0.18765025.
     * It can be represented as a packed float with the constant {@code -0x1.e4fd0cp125F}.
     * <pre>
     * <font style='background-color: #567978;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #567978; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #567978;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #567978'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #567978'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #567978'>&nbsp;@&nbsp;</font><font style='background-color: #567978; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #567978;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #567978; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_CYAN = -0x1.e4fd0cp125F;
    static { NAMED.put("lead cyan", -0x1.e4fd0cp125F); LIST.add(-0x1.e4fd0cp125F); }

    /**
     * This color constant "gray cyan" has RGBA8888 code {@code 668E8EFF}, H 0.53333336, S 0.4862745, L 0.5254902, alpha 1.0, and chroma 0.21587907.
     * It can be represented as a packed float with the constant {@code -0x1.0cf91p126F}.
     * <pre>
     * <font style='background-color: #668E8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #668E8E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #668E8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #668E8E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #668E8E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #668E8E'>&nbsp;@&nbsp;</font><font style='background-color: #668E8E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #668E8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #668E8E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_CYAN = -0x1.0cf91p126F;
    static { NAMED.put("gray cyan", -0x1.0cf91p126F); LIST.add(-0x1.0cf91p126F); }

    /**
     * This color constant "silver cyan" has RGBA8888 code {@code 77A5A5FF}, H 0.53333336, S 0.48235294, L 0.6117647, alpha 1.0, and chroma 0.24663287.
     * It can be represented as a packed float with the constant {@code -0x1.38f71p126F}.
     * <pre>
     * <font style='background-color: #77A5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #77A5A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #77A5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #77A5A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #77A5A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #77A5A5'>&nbsp;@&nbsp;</font><font style='background-color: #77A5A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #77A5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #77A5A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_CYAN = -0x1.38f71p126F;
    static { NAMED.put("silver cyan", -0x1.38f71p126F); LIST.add(-0x1.38f71p126F); }

    /**
     * This color constant "white cyan" has RGBA8888 code {@code 8CC1C1FF}, H 0.53333336, S 0.4745098, L 0.7176471, alpha 1.0, and chroma 0.28056142.
     * It can be represented as a packed float with the constant {@code -0x1.6ef31p126F}.
     * <pre>
     * <font style='background-color: #8CC1C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8CC1C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8CC1C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8CC1C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8CC1C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8CC1C1'>&nbsp;@&nbsp;</font><font style='background-color: #8CC1C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8CC1C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8CC1C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_CYAN = -0x1.6ef31p126F;
    static { NAMED.put("white cyan", -0x1.6ef31p126F); LIST.add(-0x1.6ef31p126F); }

    /**
     * This color constant "black blue" has RGBA8888 code {@code 5E5AADFF}, H 0.7411765, S 0.5019608, L 0.39215687, alpha 1.0, and chroma 0.6637504.
     * It can be represented as a packed float with the constant {@code -0x1.c9017ap125F}.
     * <pre>
     * <font style='background-color: #5E5AAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E5AAD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E5AAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5E5AAD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5E5AAD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5E5AAD'>&nbsp;@&nbsp;</font><font style='background-color: #5E5AAD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E5AAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E5AAD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BLUE = -0x1.c9017ap125F;
    static { NAMED.put("black blue", -0x1.c9017ap125F); LIST.add(-0x1.c9017ap125F); }

    /**
     * This color constant "lead blue" has RGBA8888 code {@code 7775BEFF}, H 0.7411765, S 0.48235294, L 0.4862745, alpha 1.0, and chroma 0.57636756.
     * It can be represented as a packed float with the constant {@code -0x1.f8f77ap125F}.
     * <pre>
     * <font style='background-color: #7775BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7775BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7775BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7775BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7775BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7775BE'>&nbsp;@&nbsp;</font><font style='background-color: #7775BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7775BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7775BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLUE = -0x1.f8f77ap125F;
    static { NAMED.put("lead blue", -0x1.f8f77ap125F); LIST.add(-0x1.f8f77ap125F); }

    /**
     * This color constant "gray blue" has RGBA8888 code {@code 8F8DCBFF}, H 0.7411765, S 0.47058824, L 0.57254905, alpha 1.0, and chroma 0.47542706.
     * It can be represented as a packed float with the constant {@code -0x1.24f17ap126F}.
     * <pre>
     * <font style='background-color: #8F8DCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8DCB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8DCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8DCB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8DCB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8DCB'>&nbsp;@&nbsp;</font><font style='background-color: #8F8DCB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8DCB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8DCB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BLUE = -0x1.24f17ap126F;
    static { NAMED.put("gray blue", -0x1.24f17ap126F); LIST.add(-0x1.24f17ap126F); }

    /**
     * This color constant "silver blue" has RGBA8888 code {@code A3A1D5FF}, H 0.74509805, S 0.46666667, L 0.64705884, alpha 1.0, and chroma 0.38694584.
     * It can be represented as a packed float with the constant {@code -0x1.4aef7cp126F}.
     * <pre>
     * <font style='background-color: #A3A1D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3A1D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3A1D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A3A1D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A3A1D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A3A1D5'>&nbsp;@&nbsp;</font><font style='background-color: #A3A1D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3A1D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3A1D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BLUE = -0x1.4aef7cp126F;
    static { NAMED.put("silver blue", -0x1.4aef7cp126F); LIST.add(-0x1.4aef7cp126F); }

    /**
     * This color constant "white blue" has RGBA8888 code {@code B8B7DEFF}, H 0.7411765, S 0.44705883, L 0.7294118, alpha 1.0, and chroma 0.2779806.
     * It can be represented as a packed float with the constant {@code -0x1.74e57ap126F}.
     * <pre>
     * <font style='background-color: #B8B7DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8B7DE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8B7DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B8B7DE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B8B7DE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B8B7DE'>&nbsp;@&nbsp;</font><font style='background-color: #B8B7DE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8B7DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8B7DE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BLUE = -0x1.74e57ap126F;
    static { NAMED.put("white blue", -0x1.74e57ap126F); LIST.add(-0x1.74e57ap126F); }

    /**
     * This color constant "black violet" has RGBA8888 code {@code 7053A1FF}, H 0.77254903, S 0.49019608, L 0.38431373, alpha 1.0, and chroma 0.633113.
     * It can be represented as a packed float with the constant {@code -0x1.c4fb8ap125F}.
     * <pre>
     * <font style='background-color: #7053A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7053A1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7053A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7053A1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7053A1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7053A1'>&nbsp;@&nbsp;</font><font style='background-color: #7053A1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7053A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7053A1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_VIOLET = -0x1.c4fb8ap125F;
    static { NAMED.put("black violet", -0x1.c4fb8ap125F); LIST.add(-0x1.c4fb8ap125F); }

    /**
     * This color constant "lead violet" has RGBA8888 code {@code 856AB4FF}, H 0.77254903, S 0.4392157, L 0.46666667, alpha 1.0, and chroma 0.5513146.
     * It can be represented as a packed float with the constant {@code -0x1.eee18ap125F}.
     * <pre>
     * <font style='background-color: #856AB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #856AB4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #856AB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #856AB4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #856AB4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #856AB4'>&nbsp;@&nbsp;</font><font style='background-color: #856AB4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #856AB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #856AB4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_VIOLET = -0x1.eee18ap125F;
    static { NAMED.put("lead violet", -0x1.eee18ap125F); LIST.add(-0x1.eee18ap125F); }

    /**
     * This color constant "gray violet" has RGBA8888 code {@code 9680C0FF}, H 0.77254903, S 0.42352942, L 0.5411765, alpha 1.0, and chroma 0.46872118.
     * It can be represented as a packed float with the constant {@code -0x1.14d98ap126F}.
     * <pre>
     * <font style='background-color: #9680C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9680C0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9680C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9680C0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9680C0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9680C0'>&nbsp;@&nbsp;</font><font style='background-color: #9680C0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9680C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9680C0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_VIOLET = -0x1.14d98ap126F;
    static { NAMED.put("gray violet", -0x1.14d98ap126F); LIST.add(-0x1.14d98ap126F); }

    /**
     * This color constant "silver violet" has RGBA8888 code {@code A896CCFF}, H 0.77254903, S 0.41960785, L 0.61960787, alpha 1.0, and chroma 0.38543186.
     * It can be represented as a packed float with the constant {@code -0x1.3cd78ap126F}.
     * <pre>
     * <font style='background-color: #A896CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A896CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A896CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A896CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A896CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A896CC'>&nbsp;@&nbsp;</font><font style='background-color: #A896CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A896CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A896CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_VIOLET = -0x1.3cd78ap126F;
    static { NAMED.put("silver violet", -0x1.3cd78ap126F); LIST.add(-0x1.3cd78ap126F); }

    /**
     * This color constant "white violet" has RGBA8888 code {@code BFB2DAFF}, H 0.7764706, S 0.4117647, L 0.7176471, alpha 1.0, and chroma 0.2768341.
     * It can be represented as a packed float with the constant {@code -0x1.6ed38cp126F}.
     * <pre>
     * <font style='background-color: #BFB2DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFB2DA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFB2DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFB2DA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFB2DA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFB2DA'>&nbsp;@&nbsp;</font><font style='background-color: #BFB2DA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFB2DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFB2DA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_VIOLET = -0x1.6ed38cp126F;
    static { NAMED.put("white violet", -0x1.6ed38cp126F); LIST.add(-0x1.6ed38cp126F); }

    /**
     * This color constant "black purple" has RGBA8888 code {@code 784A92FF}, H 0.8039216, S 0.54509807, L 0.3647059, alpha 1.0, and chroma 0.5686879.
     * It can be represented as a packed float with the constant {@code -0x1.bb179ap125F}.
     * <pre>
     * <font style='background-color: #784A92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #784A92; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #784A92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #784A92'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #784A92'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #784A92'>&nbsp;@&nbsp;</font><font style='background-color: #784A92; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #784A92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #784A92; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_PURPLE = -0x1.bb179ap125F;
    static { NAMED.put("black purple", -0x1.bb179ap125F); LIST.add(-0x1.bb179ap125F); }

    /**
     * This color constant "lead purple" has RGBA8888 code {@code 8F59AEFF}, H 0.8039216, S 0.54509807, L 0.43529412, alpha 1.0, and chroma 0.6751393.
     * It can be represented as a packed float with the constant {@code -0x1.df179ap125F}.
     * <pre>
     * <font style='background-color: #8F59AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F59AE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F59AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F59AE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F59AE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F59AE'>&nbsp;@&nbsp;</font><font style='background-color: #8F59AE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F59AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F59AE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_PURPLE = -0x1.df179ap125F;
    static { NAMED.put("lead purple", -0x1.df179ap125F); LIST.add(-0x1.df179ap125F); }

    /**
     * This color constant "gray purple" has RGBA8888 code {@code A370C2FF}, H 0.8039216, S 0.4745098, L 0.5176471, alpha 1.0, and chroma 0.5850134.
     * It can be represented as a packed float with the constant {@code -0x1.08f39ap126F}.
     * <pre>
     * <font style='background-color: #A370C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A370C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A370C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A370C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A370C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A370C2'>&nbsp;@&nbsp;</font><font style='background-color: #A370C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A370C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A370C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_PURPLE = -0x1.08f39ap126F;
    static { NAMED.put("gray purple", -0x1.08f39ap126F); LIST.add(-0x1.08f39ap126F); }

    /**
     * This color constant "silver purple" has RGBA8888 code {@code B58ED0FF}, H 0.8039216, S 0.46666667, L 0.6117647, alpha 1.0, and chroma 0.467237.
     * It can be represented as a packed float with the constant {@code -0x1.38ef9ap126F}.
     * <pre>
     * <font style='background-color: #B58ED0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B58ED0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B58ED0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B58ED0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B58ED0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B58ED0'>&nbsp;@&nbsp;</font><font style='background-color: #B58ED0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B58ED0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B58ED0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PURPLE = -0x1.38ef9ap126F;
    static { NAMED.put("silver purple", -0x1.38ef9ap126F); LIST.add(-0x1.38ef9ap126F); }

    /**
     * This color constant "white purple" has RGBA8888 code {@code C9ADDDFF}, H 0.8039216, S 0.45882353, L 0.7176471, alpha 1.0, and chroma 0.32787877.
     * It can be represented as a packed float with the constant {@code -0x1.6eeb9ap126F}.
     * <pre>
     * <font style='background-color: #C9ADDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9ADDD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9ADDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9ADDD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9ADDD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9ADDD'>&nbsp;@&nbsp;</font><font style='background-color: #C9ADDD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9ADDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9ADDD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_PURPLE = -0x1.6eeb9ap126F;
    static { NAMED.put("white purple", -0x1.6eeb9ap126F); LIST.add(-0x1.6eeb9ap126F); }

    /**
     * This color constant "black magenta" has RGBA8888 code {@code 874F87FF}, H 0.85490197, S 0.49803922, L 0.3882353, alpha 1.0, and chroma 0.47626826.
     * It can be represented as a packed float with the constant {@code -0x1.c6ffb4p125F}.
     * <pre>
     * <font style='background-color: #874F87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #874F87; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #874F87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #874F87'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #874F87'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #874F87'>&nbsp;@&nbsp;</font><font style='background-color: #874F87; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #874F87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #874F87; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_MAGENTA = -0x1.c6ffb4p125F;
    static { NAMED.put("black magenta", -0x1.c6ffb4p125F); LIST.add(-0x1.c6ffb4p125F); }

    /**
     * This color constant "lead magenta" has RGBA8888 code {@code A763A7FF}, H 0.85490197, S 0.49411765, L 0.48235294, alpha 1.0, and chroma 0.5820654.
     * It can be represented as a packed float with the constant {@code -0x1.f6fdb4p125F}.
     * <pre>
     * <font style='background-color: #A763A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A763A7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A763A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A763A7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A763A7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A763A7'>&nbsp;@&nbsp;</font><font style='background-color: #A763A7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A763A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A763A7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_MAGENTA = -0x1.f6fdb4p125F;
    static { NAMED.put("lead magenta", -0x1.f6fdb4p125F); LIST.add(-0x1.f6fdb4p125F); }

    /**
     * This color constant "gray magenta" has RGBA8888 code {@code C274C2FF}, H 0.85490197, S 0.4862745, L 0.56078434, alpha 1.0, and chroma 0.659935.
     * It can be represented as a packed float with the constant {@code -0x1.1ef9b4p126F}.
     * <pre>
     * <font style='background-color: #C274C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C274C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C274C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C274C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C274C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C274C2'>&nbsp;@&nbsp;</font><font style='background-color: #C274C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C274C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C274C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_MAGENTA = -0x1.1ef9b4p126F;
    static { NAMED.put("gray magenta", -0x1.1ef9b4p126F); LIST.add(-0x1.1ef9b4p126F); }

    /**
     * This color constant "silver magenta" has RGBA8888 code {@code CE8DCEFF}, H 0.85490197, S 0.41568628, L 0.63529414, alpha 1.0, and chroma 0.48181412.
     * It can be represented as a packed float with the constant {@code -0x1.44d5b4p126F}.
     * <pre>
     * <font style='background-color: #CE8DCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8DCE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8DCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE8DCE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE8DCE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE8DCE'>&nbsp;@&nbsp;</font><font style='background-color: #CE8DCE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8DCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8DCE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_MAGENTA = -0x1.44d5b4p126F;
    static { NAMED.put("silver magenta", -0x1.44d5b4p126F); LIST.add(-0x1.44d5b4p126F); }

    /**
     * This color constant "white magenta" has RGBA8888 code {@code DAAADAFF}, H 0.85490197, S 0.40784314, L 0.7254902, alpha 1.0, and chroma 0.35043067.
     * It can be represented as a packed float with the constant {@code -0x1.72d1b4p126F}.
     * <pre>
     * <font style='background-color: #DAAADA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAAADA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAAADA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAAADA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAAADA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAAADA'>&nbsp;@&nbsp;</font><font style='background-color: #DAAADA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAAADA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAAADA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_MAGENTA = -0x1.72d1b4p126F;
    static { NAMED.put("white magenta", -0x1.72d1b4p126F); LIST.add(-0x1.72d1b4p126F); }

    /**
     * This color constant "drab red" has RGBA8888 code {@code AE3135FF}, H 0.03137255, S 0.77254903, L 0.37254903, alpha 1.0, and chroma 1.029445.
     * It can be represented as a packed float with the constant {@code -0x1.bf8a1p125F}.
     * <pre>
     * <font style='background-color: #AE3135;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE3135; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE3135;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE3135'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE3135'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE3135'>&nbsp;@&nbsp;</font><font style='background-color: #AE3135; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE3135;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE3135; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED = -0x1.bf8a1p125F;
    static { NAMED.put("drab red", -0x1.bf8a1p125F); LIST.add(-0x1.bf8a1p125F); }

    /**
     * This color constant "faded red" has RGBA8888 code {@code E64B50FF}, H 0.02745098, S 0.7137255, L 0.50980395, alpha 1.0, and chroma 1.1737752.
     * It can be represented as a packed float with the constant {@code -0x1.056c0ep126F}.
     * <pre>
     * <font style='background-color: #E64B50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E64B50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E64B50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E64B50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E64B50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E64B50'>&nbsp;@&nbsp;</font><font style='background-color: #E64B50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E64B50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E64B50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_RED = -0x1.056c0ep126F;
    static { NAMED.put("faded red", -0x1.056c0ep126F); LIST.add(-0x1.056c0ep126F); }

    /**
     * This color constant "pale red" has RGBA8888 code {@code EC9799FF}, H 0.02745098, S 0.6156863, L 0.6784314, alpha 1.0, and chroma 0.49764475.
     * It can be represented as a packed float with the constant {@code -0x1.5b3a0ep126F}.
     * <pre>
     * <font style='background-color: #EC9799;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC9799; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC9799;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EC9799'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EC9799'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EC9799'>&nbsp;@&nbsp;</font><font style='background-color: #EC9799; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC9799;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC9799; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED = -0x1.5b3a0ep126F;
    static { NAMED.put("pale red", -0x1.5b3a0ep126F); LIST.add(-0x1.5b3a0ep126F); }

    /**
     * This color constant "drab brown" has RGBA8888 code {@code 806552FF}, H 0.11372549, S 0.4117647, L 0.41568628, alpha 1.0, and chroma 0.27424133.
     * It can be represented as a packed float with the constant {@code -0x1.d4d23ap125F}.
     * <pre>
     * <font style='background-color: #806552;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #806552; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #806552;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #806552'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #806552'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #806552'>&nbsp;@&nbsp;</font><font style='background-color: #806552; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #806552;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #806552; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN = -0x1.d4d23ap125F;
    static { NAMED.put("drab brown", -0x1.d4d23ap125F); LIST.add(-0x1.d4d23ap125F); }

    /**
     * This color constant "faded brown" has RGBA8888 code {@code A7856CFF}, H 0.11764706, S 0.41568628, L 0.54509807, alpha 1.0, and chroma 0.35041824.
     * It can be represented as a packed float with the constant {@code -0x1.16d43cp126F}.
     * <pre>
     * <font style='background-color: #A7856C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7856C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7856C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A7856C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A7856C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A7856C'>&nbsp;@&nbsp;</font><font style='background-color: #A7856C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7856C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7856C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BROWN = -0x1.16d43cp126F;
    static { NAMED.put("faded brown", -0x1.16d43cp126F); LIST.add(-0x1.16d43cp126F); }

    /**
     * This color constant "pale brown" has RGBA8888 code {@code D2A889FF}, H 0.11764706, S 0.40784314, L 0.6862745, alpha 1.0, and chroma 0.42495784.
     * It can be represented as a packed float with the constant {@code -0x1.5ed03cp126F}.
     * <pre>
     * <font style='background-color: #D2A889;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2A889; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2A889;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2A889'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2A889'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2A889'>&nbsp;@&nbsp;</font><font style='background-color: #D2A889; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2A889;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2A889; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN = -0x1.5ed03cp126F;
    static { NAMED.put("pale brown", -0x1.5ed03cp126F); LIST.add(-0x1.5ed03cp126F); }

    /**
     * This color constant "drab orange" has RGBA8888 code {@code 925435FF}, H 0.08235294, S 0.7490196, L 0.39215687, alpha 1.0, and chroma 0.58726627.
     * It can be represented as a packed float with the constant {@code -0x1.c97e2ap125F}.
     * <pre>
     * <font style='background-color: #925435;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #925435; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #925435;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #925435'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #925435'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #925435'>&nbsp;@&nbsp;</font><font style='background-color: #925435; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #925435;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #925435; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE = -0x1.c97e2ap125F;
    static { NAMED.put("drab orange", -0x1.c97e2ap125F); LIST.add(-0x1.c97e2ap125F); }

    /**
     * This color constant "faded orange" has RGBA8888 code {@code C17149FF}, H 0.08235294, S 0.7372549, L 0.52156866, alpha 1.0, and chroma 0.7591181.
     * It can be represented as a packed float with the constant {@code -0x1.0b782ap126F}.
     * <pre>
     * <font style='background-color: #C17149;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C17149; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C17149;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C17149'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C17149'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C17149'>&nbsp;@&nbsp;</font><font style='background-color: #C17149; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C17149;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C17149; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_ORANGE = -0x1.0b782ap126F;
    static { NAMED.put("faded orange", -0x1.0b782ap126F); LIST.add(-0x1.0b782ap126F); }

    /**
     * This color constant "pale orange" has RGBA8888 code {@code EC9A78FF}, H 0.078431375, S 0.5921569, L 0.6784314, alpha 1.0, and chroma 0.5745841.
     * It can be represented as a packed float with the constant {@code -0x1.5b2e28p126F}.
     * <pre>
     * <font style='background-color: #EC9A78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC9A78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC9A78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EC9A78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EC9A78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EC9A78'>&nbsp;@&nbsp;</font><font style='background-color: #EC9A78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC9A78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC9A78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE = -0x1.5b2e28p126F;
    static { NAMED.put("pale orange", -0x1.5b2e28p126F); LIST.add(-0x1.5b2e28p126F); }

    /**
     * This color constant "drab saffron" has RGBA8888 code {@code 806852FF}, H 0.12941177, S 0.43529412, L 0.42352942, alpha 1.0, and chroma 0.27225256.
     * It can be represented as a packed float with the constant {@code -0x1.d8de42p125F}.
     * <pre>
     * <font style='background-color: #806852;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #806852; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #806852;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #806852'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #806852'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #806852'>&nbsp;@&nbsp;</font><font style='background-color: #806852; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #806852;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #806852; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON = -0x1.d8de42p125F;
    static { NAMED.put("drab saffron", -0x1.d8de42p125F); LIST.add(-0x1.d8de42p125F); }

    /**
     * This color constant "faded saffron" has RGBA8888 code {@code AB8B6FFF}, H 0.12941177, S 0.42745098, L 0.5647059, alpha 1.0, and chroma 0.35103363.
     * It can be represented as a packed float with the constant {@code -0x1.20da42p126F}.
     * <pre>
     * <font style='background-color: #AB8B6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB8B6F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB8B6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB8B6F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB8B6F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB8B6F'>&nbsp;@&nbsp;</font><font style='background-color: #AB8B6F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB8B6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB8B6F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_SAFFRON = -0x1.20da42p126F;
    static { NAMED.put("faded saffron", -0x1.20da42p126F); LIST.add(-0x1.20da42p126F); }

    /**
     * This color constant "pale saffron" has RGBA8888 code {@code D1AA88FF}, H 0.12941177, S 0.42352942, L 0.69411767, alpha 1.0, and chroma 0.420281.
     * It can be represented as a packed float with the constant {@code -0x1.62d842p126F}.
     * <pre>
     * <font style='background-color: #D1AA88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1AA88; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1AA88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1AA88'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1AA88'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1AA88'>&nbsp;@&nbsp;</font><font style='background-color: #D1AA88; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1AA88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1AA88; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON = -0x1.62d842p126F;
    static { NAMED.put("pale saffron", -0x1.62d842p126F); LIST.add(-0x1.62d842p126F); }

    /**
     * This color constant "drab yellow" has RGBA8888 code {@code 6B6B35FF}, H 0.23921569, S 0.76862746, L 0.40784314, alpha 1.0, and chroma 0.37365705.
     * It can be represented as a packed float with the constant {@code -0x1.d1887ap125F}.
     * <pre>
     * <font style='background-color: #6B6B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B6B35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B6B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6B6B35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6B6B35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6B6B35'>&nbsp;@&nbsp;</font><font style='background-color: #6B6B35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B6B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B6B35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW = -0x1.d1887ap125F;
    static { NAMED.put("drab yellow", -0x1.d1887ap125F); LIST.add(-0x1.d1887ap125F); }

    /**
     * This color constant "faded yellow" has RGBA8888 code {@code 8C8C47FF}, H 0.23921569, S 0.7607843, L 0.53333336, alpha 1.0, and chroma 0.47746658.
     * It can be represented as a packed float with the constant {@code -0x1.11847ap126F}.
     * <pre>
     * <font style='background-color: #8C8C47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C8C47; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C8C47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C8C47'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C8C47'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C8C47'>&nbsp;@&nbsp;</font><font style='background-color: #8C8C47; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C8C47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C8C47; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_YELLOW = -0x1.11847ap126F;
    static { NAMED.put("faded yellow", -0x1.11847ap126F); LIST.add(-0x1.11847ap126F); }

    /**
     * This color constant "pale yellow" has RGBA8888 code {@code B3B45DFF}, H 0.23921569, S 0.74509805, L 0.6862745, alpha 1.0, and chroma 0.58991605.
     * It can be represented as a packed float with the constant {@code -0x1.5f7c7ap126F}.
     * <pre>
     * <font style='background-color: #B3B45D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3B45D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3B45D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B3B45D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B3B45D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B3B45D'>&nbsp;@&nbsp;</font><font style='background-color: #B3B45D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3B45D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3B45D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.5f7c7ap126F;
    static { NAMED.put("pale yellow", -0x1.5f7c7ap126F); LIST.add(-0x1.5f7c7ap126F); }

    /**
     * This color constant "drab lime" has RGBA8888 code {@code 516837FF}, H 0.30588236, S 0.69411767, L 0.38039216, alpha 1.0, and chroma 0.35339403.
     * It can be represented as a packed float with the constant {@code -0x1.c3629cp125F}.
     * <pre>
     * <font style='background-color: #516837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #516837; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #516837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #516837'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #516837'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #516837'>&nbsp;@&nbsp;</font><font style='background-color: #516837; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #516837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #516837; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME = -0x1.c3629cp125F;
    static { NAMED.put("drab lime", -0x1.c3629cp125F); LIST.add(-0x1.c3629cp125F); }

    /**
     * This color constant "faded lime" has RGBA8888 code {@code 6F8D4CFF}, H 0.30588236, S 0.69411767, L 0.5137255, alpha 1.0, and chroma 0.47129926.
     * It can be represented as a packed float with the constant {@code -0x1.07629cp126F}.
     * <pre>
     * <font style='background-color: #6F8D4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F8D4C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F8D4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F8D4C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F8D4C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F8D4C'>&nbsp;@&nbsp;</font><font style='background-color: #6F8D4C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F8D4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F8D4C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_LIME = -0x1.07629cp126F;
    static { NAMED.put("faded lime", -0x1.07629cp126F); LIST.add(-0x1.07629cp126F); }

    /**
     * This color constant "pale lime" has RGBA8888 code {@code 93BA67FF}, H 0.30588236, S 0.67058825, L 0.6784314, alpha 1.0, and chroma 0.5887938.
     * It can be represented as a packed float with the constant {@code -0x1.5b569cp126F}.
     * <pre>
     * <font style='background-color: #93BA67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93BA67; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93BA67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93BA67'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93BA67'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93BA67'>&nbsp;@&nbsp;</font><font style='background-color: #93BA67; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93BA67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93BA67; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME = -0x1.5b569cp126F;
    static { NAMED.put("pale lime", -0x1.5b569cp126F); LIST.add(-0x1.5b569cp126F); }

    /**
     * This color constant "drab green" has RGBA8888 code {@code 387936FF}, H 0.3529412, S 0.77254903, L 0.41960785, alpha 1.0, and chroma 0.5352553.
     * It can be represented as a packed float with the constant {@code -0x1.d78ab4p125F}.
     * <pre>
     * <font style='background-color: #387936;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #387936; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #387936;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #387936'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #387936'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #387936'>&nbsp;@&nbsp;</font><font style='background-color: #387936; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #387936;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #387936; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.d78ab4p125F;
    static { NAMED.put("drab green", -0x1.d78ab4p125F); LIST.add(-0x1.d78ab4p125F); }

    /**
     * This color constant "faded green" has RGBA8888 code {@code 4C9F4AFF}, H 0.3529412, S 0.75686276, L 0.5529412, alpha 1.0, and chroma 0.6812461.
     * It can be represented as a packed float with the constant {@code -0x1.1b82b4p126F}.
     * <pre>
     * <font style='background-color: #4C9F4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C9F4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C9F4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4C9F4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4C9F4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4C9F4A'>&nbsp;@&nbsp;</font><font style='background-color: #4C9F4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C9F4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C9F4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_GREEN = -0x1.1b82b4p126F;
    static { NAMED.put("faded green", -0x1.1b82b4p126F); LIST.add(-0x1.1b82b4p126F); }

    /**
     * This color constant "pale green" has RGBA8888 code {@code 60C65DFF}, H 0.3529412, S 0.7490196, L 0.6901961, alpha 1.0, and chroma 0.8265362.
     * It can be represented as a packed float with the constant {@code -0x1.617eb4p126F}.
     * <pre>
     * <font style='background-color: #60C65D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #60C65D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #60C65D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #60C65D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #60C65D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #60C65D'>&nbsp;@&nbsp;</font><font style='background-color: #60C65D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #60C65D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #60C65D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.617eb4p126F;
    static { NAMED.put("pale green", -0x1.617eb4p126F); LIST.add(-0x1.617eb4p126F); }

    /**
     * This color constant "drab cyan" has RGBA8888 code {@code 3E6E6EFF}, H 0.53333336, S 0.69803923, L 0.4, alpha 1.0, and chroma 0.23886411.
     * It can be represented as a packed float with the constant {@code -0x1.cd651p125F}.
     * <pre>
     * <font style='background-color: #3E6E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E6E6E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E6E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3E6E6E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3E6E6E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3E6E6E'>&nbsp;@&nbsp;</font><font style='background-color: #3E6E6E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E6E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E6E6E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN = -0x1.cd651p125F;
    static { NAMED.put("drab cyan", -0x1.cd651p125F); LIST.add(-0x1.cd651p125F); }

    /**
     * This color constant "faded cyan" has RGBA8888 code {@code 549191FF}, H 0.53333336, S 0.68235296, L 0.5254902, alpha 1.0, and chroma 0.3029271.
     * It can be represented as a packed float with the constant {@code -0x1.0d5d1p126F}.
     * <pre>
     * <font style='background-color: #549191;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #549191; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #549191;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #549191'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #549191'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #549191'>&nbsp;@&nbsp;</font><font style='background-color: #549191; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #549191;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #549191; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_CYAN = -0x1.0d5d1p126F;
    static { NAMED.put("faded cyan", -0x1.0d5d1p126F); LIST.add(-0x1.0d5d1p126F); }

    /**
     * This color constant "pale cyan" has RGBA8888 code {@code 6EBBBBFF}, H 0.53333336, S 0.67058825, L 0.68235296, alpha 1.0, and chroma 0.37884152.
     * It can be represented as a packed float with the constant {@code -0x1.5d571p126F}.
     * <pre>
     * <font style='background-color: #6EBBBB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6EBBBB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6EBBBB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6EBBBB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6EBBBB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6EBBBB'>&nbsp;@&nbsp;</font><font style='background-color: #6EBBBB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6EBBBB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6EBBBB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN = -0x1.5d571p126F;
    static { NAMED.put("pale cyan", -0x1.5d571p126F); LIST.add(-0x1.5d571p126F); }

    /**
     * This color constant "drab blue" has RGBA8888 code {@code 625DD1FF}, H 0.7411765, S 0.69803923, L 0.42352942, alpha 1.0, and chroma 0.90192556.
     * It can be represented as a packed float with the constant {@code -0x1.d9657ap125F}.
     * <pre>
     * <font style='background-color: #625DD1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #625DD1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #625DD1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #625DD1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #625DD1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #625DD1'>&nbsp;@&nbsp;</font><font style='background-color: #625DD1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #625DD1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #625DD1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE = -0x1.d9657ap125F;
    static { NAMED.put("drab blue", -0x1.d9657ap125F); LIST.add(-0x1.d9657ap125F); }

    /**
     * This color constant "faded blue" has RGBA8888 code {@code 8C8AE0FF}, H 0.7411765, S 0.65882355, L 0.57254905, alpha 1.0, and chroma 0.6655979.
     * It can be represented as a packed float with the constant {@code -0x1.25517ap126F}.
     * <pre>
     * <font style='background-color: #8C8AE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C8AE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C8AE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C8AE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C8AE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C8AE0'>&nbsp;@&nbsp;</font><font style='background-color: #8C8AE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C8AE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C8AE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.25517ap126F;
    static { NAMED.put("faded blue", -0x1.25517ap126F); LIST.add(-0x1.25517ap126F); }

    /**
     * This color constant "pale blue" has RGBA8888 code {@code AFADEAFF}, H 0.7411765, S 0.6392157, L 0.69803923, alpha 1.0, and chroma 0.44769382.
     * It can be represented as a packed float with the constant {@code -0x1.65477ap126F}.
     * <pre>
     * <font style='background-color: #AFADEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFADEA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFADEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFADEA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFADEA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFADEA'>&nbsp;@&nbsp;</font><font style='background-color: #AFADEA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFADEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFADEA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.65477ap126F;
    static { NAMED.put("pale blue", -0x1.65477ap126F); LIST.add(-0x1.65477ap126F); }

    /**
     * This color constant "drab violet" has RGBA8888 code {@code 7F4FC4FF}, H 0.77254903, S 0.67058825, L 0.4117647, alpha 1.0, and chroma 0.8919362.
     * It can be represented as a packed float with the constant {@code -0x1.d3578ap125F}.
     * <pre>
     * <font style='background-color: #7F4FC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F4FC4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F4FC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F4FC4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F4FC4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F4FC4'>&nbsp;@&nbsp;</font><font style='background-color: #7F4FC4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F4FC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F4FC4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET = -0x1.d3578ap125F;
    static { NAMED.put("drab violet", -0x1.d3578ap125F); LIST.add(-0x1.d3578ap125F); }

    /**
     * This color constant "faded violet" has RGBA8888 code {@code 9B7AD5FF}, H 0.77254903, S 0.6, L 0.5411765, alpha 1.0, and chroma 0.66402173.
     * It can be represented as a packed float with the constant {@code -0x1.15338ap126F}.
     * <pre>
     * <font style='background-color: #9B7AD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B7AD5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B7AD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B7AD5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B7AD5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B7AD5'>&nbsp;@&nbsp;</font><font style='background-color: #9B7AD5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B7AD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B7AD5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_VIOLET = -0x1.15338ap126F;
    static { NAMED.put("faded violet", -0x1.15338ap126F); LIST.add(-0x1.15338ap126F); }

    /**
     * This color constant "pale violet" has RGBA8888 code {@code BAA5E3FF}, H 0.77254903, S 0.57254905, L 0.68235296, alpha 1.0, and chroma 0.43442997.
     * It can be represented as a packed float with the constant {@code -0x1.5d258ap126F}.
     * <pre>
     * <font style='background-color: #BAA5E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BAA5E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BAA5E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BAA5E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BAA5E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BAA5E3'>&nbsp;@&nbsp;</font><font style='background-color: #BAA5E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BAA5E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BAA5E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.5d258ap126F;
    static { NAMED.put("pale violet", -0x1.5d258ap126F); LIST.add(-0x1.5d258ap126F); }

    /**
     * This color constant "drab purple" has RGBA8888 code {@code 8D40B2FF}, H 0.8039216, S 0.76862746, L 0.3882353, alpha 1.0, and chroma 0.8523534.
     * It can be represented as a packed float with the constant {@code -0x1.c7899ap125F}.
     * <pre>
     * <font style='background-color: #8D40B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D40B2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D40B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8D40B2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8D40B2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8D40B2'>&nbsp;@&nbsp;</font><font style='background-color: #8D40B2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D40B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D40B2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE = -0x1.c7899ap125F;
    static { NAMED.put("drab purple", -0x1.c7899ap125F); LIST.add(-0x1.c7899ap125F); }

    /**
     * This color constant "faded purple" has RGBA8888 code {@code B162DBFF}, H 0.8039216, S 0.6745098, L 0.5176471, alpha 1.0, and chroma 0.8315893.
     * It can be represented as a packed float with the constant {@code -0x1.09599ap126F}.
     * <pre>
     * <font style='background-color: #B162DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B162DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B162DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B162DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B162DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B162DB'>&nbsp;@&nbsp;</font><font style='background-color: #B162DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B162DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B162DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_PURPLE = -0x1.09599ap126F;
    static { NAMED.put("faded purple", -0x1.09599ap126F); LIST.add(-0x1.09599ap126F); }

    /**
     * This color constant "pale purple" has RGBA8888 code {@code CA9CE9FF}, H 0.8039216, S 0.6509804, L 0.6784314, alpha 1.0, and chroma 0.5348402.
     * It can be represented as a packed float with the constant {@code -0x1.5b4d9ap126F}.
     * <pre>
     * <font style='background-color: #CA9CE9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA9CE9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA9CE9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA9CE9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA9CE9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA9CE9'>&nbsp;@&nbsp;</font><font style='background-color: #CA9CE9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA9CE9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA9CE9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.5b4d9ap126F;
    static { NAMED.put("pale purple", -0x1.5b4d9ap126F); LIST.add(-0x1.5b4d9ap126F); }

    /**
     * This color constant "drab magenta" has RGBA8888 code {@code A148A0FF}, H 0.85882354, S 0.69803923, L 0.41960785, alpha 1.0, and chroma 0.7150876.
     * It can be represented as a packed float with the constant {@code -0x1.d765b6p125F}.
     * <pre>
     * <font style='background-color: #A148A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A148A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A148A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A148A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A148A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A148A0'>&nbsp;@&nbsp;</font><font style='background-color: #A148A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A148A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A148A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA = -0x1.d765b6p125F;
    static { NAMED.put("drab magenta", -0x1.d765b6p125F); LIST.add(-0x1.d765b6p125F); }

    /**
     * This color constant "faded magenta" has RGBA8888 code {@code D562D5FF}, H 0.85490197, S 0.6862745, L 0.56078434, alpha 1.0, and chroma 0.93135995.
     * It can be represented as a packed float with the constant {@code -0x1.1f5fb4p126F}.
     * <pre>
     * <font style='background-color: #D562D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D562D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D562D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D562D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D562D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D562D5'>&nbsp;@&nbsp;</font><font style='background-color: #D562D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D562D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D562D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_MAGENTA = -0x1.1f5fb4p126F;
    static { NAMED.put("faded magenta", -0x1.1f5fb4p126F); LIST.add(-0x1.1f5fb4p126F); }

    /**
     * This color constant "pale magenta" has RGBA8888 code {@code E496E4FF}, H 0.85490197, S 0.5803922, L 0.6901961, alpha 1.0, and chroma 0.5671769.
     * It can be represented as a packed float with the constant {@code -0x1.6129b4p126F}.
     * <pre>
     * <font style='background-color: #E496E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E496E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E496E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E496E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E496E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E496E4'>&nbsp;@&nbsp;</font><font style='background-color: #E496E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E496E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E496E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA = -0x1.6129b4p126F;
    static { NAMED.put("pale magenta", -0x1.6129b4p126F); LIST.add(-0x1.6129b4p126F); }

    /**
     * This color constant "deep pure red" has RGBA8888 code {@code BA1E26FF}, H 0.02745098, S 0.9529412, L 0.37254903, alpha 1.0, and chroma 1.236089.
     * It can be represented as a packed float with the constant {@code -0x1.bfe60ep125F}.
     * <pre>
     * <font style='background-color: #BA1E26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA1E26; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA1E26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA1E26'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA1E26'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA1E26'>&nbsp;@&nbsp;</font><font style='background-color: #BA1E26; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA1E26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA1E26; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_RED = -0x1.bfe60ep125F;
    static { NAMED.put("deep pure red", -0x1.bfe60ep125F); LIST.add(-0x1.bfe60ep125F); }

    /**
     * This color constant "true pure red" has RGBA8888 code {@code F5383FFF}, H 0.03137255, S 0.87058824, L 0.50980395, alpha 1.0, and chroma 1.456655.
     * It can be represented as a packed float with the constant {@code -0x1.05bc1p126F}.
     * <pre>
     * <font style='background-color: #F5383F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5383F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5383F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5383F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5383F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5383F'>&nbsp;@&nbsp;</font><font style='background-color: #F5383F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5383F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5383F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_RED = -0x1.05bc1p126F;
    static { NAMED.put("true pure red", -0x1.05bc1p126F); LIST.add(-0x1.05bc1p126F); }

    /**
     * This color constant "bright pure red" has RGBA8888 code {@code F79294FF}, H 0.02745098, S 0.7529412, L 0.68235296, alpha 1.0, and chroma 0.5978741.
     * It can be represented as a packed float with the constant {@code -0x1.5d800ep126F}.
     * <pre>
     * <font style='background-color: #F79294;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F79294; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F79294;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F79294'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F79294'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F79294'>&nbsp;@&nbsp;</font><font style='background-color: #F79294; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F79294;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F79294; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_RED = -0x1.5d800ep126F;
    static { NAMED.put("bright pure red", -0x1.5d800ep126F); LIST.add(-0x1.5d800ep126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code AE4C2FFF}, H 0.05490196, S 0.84313726, L 0.41568628, alpha 1.0, and chroma 0.9292606.
     * It can be represented as a packed float with the constant {@code -0x1.d5ae1cp125F}.
     * <pre>
     * <font style='background-color: #AE4C2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE4C2F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE4C2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE4C2F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE4C2F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE4C2F'>&nbsp;@&nbsp;</font><font style='background-color: #AE4C2F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE4C2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE4C2F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.d5ae1cp125F;
    static { NAMED.put("deep brown red", -0x1.d5ae1cp125F); LIST.add(-0x1.d5ae1cp125F); }

    /**
     * This color constant "true brown red" has RGBA8888 code {@code E36640FF}, H 0.05882353, S 0.8352941, L 0.54509807, alpha 1.0, and chroma 1.1352837.
     * It can be represented as a packed float with the constant {@code -0x1.17aa1ep126F}.
     * <pre>
     * <font style='background-color: #E36640;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E36640; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E36640;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E36640'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E36640'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E36640'>&nbsp;@&nbsp;</font><font style='background-color: #E36640; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E36640;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E36640; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_RED = -0x1.17aa1ep126F;
    static { NAMED.put("true brown red", -0x1.17aa1ep126F); LIST.add(-0x1.17aa1ep126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code F29988FF}, H 0.05882353, S 0.6784314, L 0.6862745, alpha 1.0, and chroma 0.5814511.
     * It can be represented as a packed float with the constant {@code -0x1.5f5a1ep126F}.
     * <pre>
     * <font style='background-color: #F29988;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F29988; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F29988;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F29988'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F29988'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F29988'>&nbsp;@&nbsp;</font><font style='background-color: #F29988; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F29988;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F29988; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.5f5a1ep126F;
    static { NAMED.put("bright brown red", -0x1.5f5a1ep126F); LIST.add(-0x1.5f5a1ep126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code 935430FF}, H 0.08627451, S 0.8039216, L 0.39215687, alpha 1.0, and chroma 0.6101406.
     * It can be represented as a packed float with the constant {@code -0x1.c99a2cp125F}.
     * <pre>
     * <font style='background-color: #935430;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #935430; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #935430;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #935430'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #935430'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #935430'>&nbsp;@&nbsp;</font><font style='background-color: #935430; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #935430;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #935430; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.c99a2cp125F;
    static { NAMED.put("deep red brown", -0x1.c99a2cp125F); LIST.add(-0x1.c99a2cp125F); }

    /**
     * This color constant "true red brown" has RGBA8888 code {@code C27143FF}, H 0.08627451, S 0.7882353, L 0.52156866, alpha 1.0, and chroma 0.785637.
     * It can be represented as a packed float with the constant {@code -0x1.0b922cp126F}.
     * <pre>
     * <font style='background-color: #C27143;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C27143; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C27143;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C27143'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C27143'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C27143'>&nbsp;@&nbsp;</font><font style='background-color: #C27143; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C27143;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C27143; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_BROWN = -0x1.0b922cp126F;
    static { NAMED.put("true red brown", -0x1.0b922cp126F); LIST.add(-0x1.0b922cp126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code EF996FFF}, H 0.08627451, S 0.627451, L 0.6784314, alpha 1.0, and chroma 0.63456637.
     * It can be represented as a packed float with the constant {@code -0x1.5b402cp126F}.
     * <pre>
     * <font style='background-color: #EF996F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF996F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF996F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF996F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF996F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF996F'>&nbsp;@&nbsp;</font><font style='background-color: #EF996F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF996F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF996F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.5b402cp126F;
    static { NAMED.put("bright red brown", -0x1.5b402cp126F); LIST.add(-0x1.5b402cp126F); }

    /**
     * This color constant "deep pure brown" has RGBA8888 code {@code 86664DFF}, H 0.11764706, S 0.50980395, L 0.42352942, alpha 1.0, and chroma 0.33826348.
     * It can be represented as a packed float with the constant {@code -0x1.d9043cp125F}.
     * <pre>
     * <font style='background-color: #86664D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86664D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86664D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #86664D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #86664D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #86664D'>&nbsp;@&nbsp;</font><font style='background-color: #86664D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86664D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86664D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BROWN = -0x1.d9043cp125F;
    static { NAMED.put("deep pure brown", -0x1.d9043cp125F); LIST.add(-0x1.d9043cp125F); }

    /**
     * This color constant "true pure brown" has RGBA8888 code {@code B38868FF}, H 0.11372549, S 0.5019608, L 0.5647059, alpha 1.0, and chroma 0.44694388.
     * It can be represented as a packed float with the constant {@code -0x1.21003ap126F}.
     * <pre>
     * <font style='background-color: #B38868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B38868; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B38868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B38868'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B38868'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B38868'>&nbsp;@&nbsp;</font><font style='background-color: #B38868; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B38868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B38868; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BROWN = -0x1.21003ap126F;
    static { NAMED.put("true pure brown", -0x1.21003ap126F); LIST.add(-0x1.21003ap126F); }

    /**
     * This color constant "bright pure brown" has RGBA8888 code {@code DAA780FF}, H 0.11764706, S 0.49411765, L 0.69411767, alpha 1.0, and chroma 0.5201748.
     * It can be represented as a packed float with the constant {@code -0x1.62fc3cp126F}.
     * <pre>
     * <font style='background-color: #DAA780;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAA780; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAA780;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAA780'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAA780'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAA780'>&nbsp;@&nbsp;</font><font style='background-color: #DAA780; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAA780;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAA780; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BROWN = -0x1.62fc3cp126F;
    static { NAMED.put("bright pure brown", -0x1.62fc3cp126F); LIST.add(-0x1.62fc3cp126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code 895F42FF}, H 0.105882354, S 0.61960787, L 0.40784314, alpha 1.0, and chroma 0.4246213.
     * It can be represented as a packed float with the constant {@code -0x1.d13c36p125F}.
     * <pre>
     * <font style='background-color: #895F42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #895F42; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #895F42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #895F42'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #895F42'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #895F42'>&nbsp;@&nbsp;</font><font style='background-color: #895F42; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #895F42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #895F42; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.d13c36p125F;
    static { NAMED.put("deep orange brown", -0x1.d13c36p125F); LIST.add(-0x1.d13c36p125F); }

    /**
     * This color constant "true orange brown" has RGBA8888 code {@code B27D59FF}, H 0.105882354, S 0.6, L 0.53333336, alpha 1.0, and chroma 0.5308359.
     * It can be represented as a packed float with the constant {@code -0x1.113236p126F}.
     * <pre>
     * <font style='background-color: #B27D59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B27D59; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B27D59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B27D59'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B27D59'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B27D59'>&nbsp;@&nbsp;</font><font style='background-color: #B27D59; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B27D59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B27D59; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_BROWN = -0x1.113236p126F;
    static { NAMED.put("true orange brown", -0x1.113236p126F); LIST.add(-0x1.113236p126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code E3A073FF}, H 0.101960786, S 0.5921569, L 0.68235296, alpha 1.0, and chroma 0.6460333.
     * It can be represented as a packed float with the constant {@code -0x1.5d2e34p126F}.
     * <pre>
     * <font style='background-color: #E3A073;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3A073; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3A073;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3A073'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3A073'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3A073'>&nbsp;@&nbsp;</font><font style='background-color: #E3A073; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3A073;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3A073; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.5d2e34p126F;
    static { NAMED.put("bright orange brown", -0x1.5d2e34p126F); LIST.add(-0x1.5d2e34p126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code 86563AFF}, H 0.09411765, S 0.6666667, L 0.38039216, alpha 1.0, and chroma 0.46245503.
     * It can be represented as a packed float with the constant {@code -0x1.c3543p125F}.
     * <pre>
     * <font style='background-color: #86563A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86563A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86563A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #86563A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #86563A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #86563A'>&nbsp;@&nbsp;</font><font style='background-color: #86563A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86563A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86563A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.c3543p125F;
    static { NAMED.put("deep brown orange", -0x1.c3543p125F); LIST.add(-0x1.c3543p125F); }

    /**
     * This color constant "true brown orange" has RGBA8888 code {@code B47550FF}, H 0.09411765, S 0.6627451, L 0.5137255, alpha 1.0, and chroma 0.6131191.
     * It can be represented as a packed float with the constant {@code -0x1.07523p126F}.
     * <pre>
     * <font style='background-color: #B47550;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B47550; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B47550;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B47550'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B47550'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B47550'>&nbsp;@&nbsp;</font><font style='background-color: #B47550; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B47550;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B47550; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_ORANGE = -0x1.07523p126F;
    static { NAMED.put("true brown orange", -0x1.07523p126F); LIST.add(-0x1.07523p126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code E79D75FF}, H 0.09411765, S 0.57254905, L 0.6784314, alpha 1.0, and chroma 0.6061428.
     * It can be represented as a packed float with the constant {@code -0x1.5b243p126F}.
     * <pre>
     * <font style='background-color: #E79D75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E79D75; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E79D75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E79D75'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E79D75'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E79D75'>&nbsp;@&nbsp;</font><font style='background-color: #E79D75; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E79D75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E79D75; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.5b243p126F;
    static { NAMED.put("bright brown orange", -0x1.5b243p126F); LIST.add(-0x1.5b243p126F); }

    /**
     * This color constant "deep pure orange" has RGBA8888 code {@code A45628FF}, H 0.078431375, S 0.90588236, L 0.41960785, alpha 1.0, and chroma 0.78474444.
     * It can be represented as a packed float with the constant {@code -0x1.d7ce28p125F}.
     * <pre>
     * <font style='background-color: #A45628;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A45628; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A45628;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A45628'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A45628'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A45628'>&nbsp;@&nbsp;</font><font style='background-color: #A45628; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A45628;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A45628; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_ORANGE = -0x1.d7ce28p125F;
    static { NAMED.put("deep pure orange", -0x1.d7ce28p125F); LIST.add(-0x1.d7ce28p125F); }

    /**
     * This color constant "true pure orange" has RGBA8888 code {@code D67337FF}, H 0.08235294, S 0.89411765, L 0.5529412, alpha 1.0, and chroma 0.9723593.
     * It can be represented as a packed float with the constant {@code -0x1.1bc82ap126F}.
     * <pre>
     * <font style='background-color: #D67337;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D67337; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D67337;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D67337'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D67337'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D67337'>&nbsp;@&nbsp;</font><font style='background-color: #D67337; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D67337;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D67337; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_ORANGE = -0x1.1bc82ap126F;
    static { NAMED.put("true pure orange", -0x1.1bc82ap126F); LIST.add(-0x1.1bc82ap126F); }

    /**
     * This color constant "bright pure orange" has RGBA8888 code {@code F6996FFF}, H 0.078431375, S 0.72156864, L 0.6862745, alpha 1.0, and chroma 0.67347515.
     * It can be represented as a packed float with the constant {@code -0x1.5f7028p126F}.
     * <pre>
     * <font style='background-color: #F6996F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6996F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6996F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6996F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6996F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6996F'>&nbsp;@&nbsp;</font><font style='background-color: #F6996F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6996F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6996F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_ORANGE = -0x1.5f7028p126F;
    static { NAMED.put("bright pure orange", -0x1.5f7028p126F); LIST.add(-0x1.5f7028p126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code 895C40FF}, H 0.09803922, S 0.6313726, L 0.4, alpha 1.0, and chroma 0.4472314.
     * It can be represented as a packed float with the constant {@code -0x1.cd4232p125F}.
     * <pre>
     * <font style='background-color: #895C40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #895C40; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #895C40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #895C40'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #895C40'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #895C40'>&nbsp;@&nbsp;</font><font style='background-color: #895C40; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #895C40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #895C40; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.cd4232p125F;
    static { NAMED.put("deep saffron orange", -0x1.cd4232p125F); LIST.add(-0x1.cd4232p125F); }

    /**
     * This color constant "true saffron orange" has RGBA8888 code {@code B47956FF}, H 0.09411765, S 0.61960787, L 0.5254902, alpha 1.0, and chroma 0.58553934.
     * It can be represented as a packed float with the constant {@code -0x1.0d3c3p126F}.
     * <pre>
     * <font style='background-color: #B47956;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B47956; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B47956;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B47956'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B47956'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B47956'>&nbsp;@&nbsp;</font><font style='background-color: #B47956; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B47956;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B47956; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_ORANGE = -0x1.0d3c3p126F;
    static { NAMED.put("true saffron orange", -0x1.0d3c3p126F); LIST.add(-0x1.0d3c3p126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code E49E76FF}, H 0.09411765, S 0.56078434, L 0.6784314, alpha 1.0, and chroma 0.59368783.
     * It can be represented as a packed float with the constant {@code -0x1.5b1e3p126F}.
     * <pre>
     * <font style='background-color: #E49E76;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E49E76; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E49E76;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E49E76'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E49E76'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E49E76'>&nbsp;@&nbsp;</font><font style='background-color: #E49E76; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E49E76;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E49E76; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.5b1e3p126F;
    static { NAMED.put("bright saffron orange", -0x1.5b1e3p126F); LIST.add(-0x1.5b1e3p126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code 8A6448FF}, H 0.10980392, S 0.57254905, L 0.42352942, alpha 1.0, and chroma 0.39725158.
     * It can be represented as a packed float with the constant {@code -0x1.d92438p125F}.
     * <pre>
     * <font style='background-color: #8A6448;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A6448; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A6448;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A6448'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A6448'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A6448'>&nbsp;@&nbsp;</font><font style='background-color: #8A6448; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A6448;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A6448; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.d92438p125F;
    static { NAMED.put("deep orange saffron", -0x1.d92438p125F); LIST.add(-0x1.d92438p125F); }

    /**
     * This color constant "true orange saffron" has RGBA8888 code {@code BA8863FF}, H 0.10980392, S 0.5647059, L 0.57254905, alpha 1.0, and chroma 0.52109146.
     * It can be represented as a packed float with the constant {@code -0x1.252038p126F}.
     * <pre>
     * <font style='background-color: #BA8863;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA8863; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA8863;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA8863'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA8863'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA8863'>&nbsp;@&nbsp;</font><font style='background-color: #BA8863; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA8863;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA8863; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_SAFFRON = -0x1.252038p126F;
    static { NAMED.put("true orange saffron", -0x1.252038p126F); LIST.add(-0x1.252038p126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code E2A77AFF}, H 0.11372549, S 0.5568628, L 0.7019608, alpha 1.0, and chroma 0.5949357.
     * It can be represented as a packed float with the constant {@code -0x1.671c3ap126F}.
     * <pre>
     * <font style='background-color: #E2A77A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2A77A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2A77A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2A77A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2A77A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2A77A'>&nbsp;@&nbsp;</font><font style='background-color: #E2A77A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2A77A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2A77A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.671c3ap126F;
    static { NAMED.put("bright orange saffron", -0x1.671c3ap126F); LIST.add(-0x1.671c3ap126F); }

    /**
     * This color constant "deep pure saffron" has RGBA8888 code {@code 806449FF}, H 0.12941177, S 0.53333336, L 0.4117647, alpha 1.0, and chroma 0.32462856.
     * It can be represented as a packed float with the constant {@code -0x1.d31042p125F}.
     * <pre>
     * <font style='background-color: #806449;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #806449; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #806449;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #806449'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #806449'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #806449'>&nbsp;@&nbsp;</font><font style='background-color: #806449; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #806449;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #806449; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_SAFFRON = -0x1.d31042p125F;
    static { NAMED.put("deep pure saffron", -0x1.d31042p125F); LIST.add(-0x1.d31042p125F); }

    /**
     * This color constant "true pure saffron" has RGBA8888 code {@code A98461FF}, H 0.12941177, S 0.53333336, L 0.5411765, alpha 1.0, and chroma 0.42094624.
     * It can be represented as a packed float with the constant {@code -0x1.151042p126F}.
     * <pre>
     * <font style='background-color: #A98461;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A98461; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A98461;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A98461'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A98461'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A98461'>&nbsp;@&nbsp;</font><font style='background-color: #A98461; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A98461;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A98461; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_SAFFRON = -0x1.151042p126F;
    static { NAMED.put("true pure saffron", -0x1.151042p126F); LIST.add(-0x1.151042p126F); }

    /**
     * This color constant "bright pure saffron" has RGBA8888 code {@code D4A67CFF}, H 0.1254902, S 0.5137255, L 0.68235296, alpha 1.0, and chroma 0.51143056.
     * It can be represented as a packed float with the constant {@code -0x1.5d064p126F}.
     * <pre>
     * <font style='background-color: #D4A67C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4A67C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4A67C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4A67C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4A67C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4A67C'>&nbsp;@&nbsp;</font><font style='background-color: #D4A67C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4A67C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4A67C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_SAFFRON = -0x1.5d064p126F;
    static { NAMED.put("bright pure saffron", -0x1.5d064p126F); LIST.add(-0x1.5d064p126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code 7B5F1FFF}, H 0.16470589, S 0.9490196, L 0.3882353, alpha 1.0, and chroma 0.4795376.
     * It can be represented as a packed float with the constant {@code -0x1.c7e454p125F}.
     * <pre>
     * <font style='background-color: #7B5F1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B5F1F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B5F1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B5F1F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B5F1F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B5F1F'>&nbsp;@&nbsp;</font><font style='background-color: #7B5F1F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B5F1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B5F1F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.c7e454p125F;
    static { NAMED.put("deep yellow saffron", -0x1.c7e454p125F); LIST.add(-0x1.c7e454p125F); }

    /**
     * This color constant "true yellow saffron" has RGBA8888 code {@code A37F2DFF}, H 0.16470589, S 0.93333334, L 0.5176471, alpha 1.0, and chroma 0.6210077.
     * It can be represented as a packed float with the constant {@code -0x1.09dc54p126F}.
     * <pre>
     * <font style='background-color: #A37F2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A37F2D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A37F2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A37F2D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A37F2D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A37F2D'>&nbsp;@&nbsp;</font><font style='background-color: #A37F2D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A37F2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A37F2D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_SAFFRON = -0x1.09dc54p126F;
    static { NAMED.put("true yellow saffron", -0x1.09dc54p126F); LIST.add(-0x1.09dc54p126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code D6A73DFF}, H 0.16078432, S 0.91764706, L 0.6784314, alpha 1.0, and chroma 0.7927244.
     * It can be represented as a packed float with the constant {@code -0x1.5bd452p126F}.
     * <pre>
     * <font style='background-color: #D6A73D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6A73D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6A73D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6A73D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6A73D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6A73D'>&nbsp;@&nbsp;</font><font style='background-color: #D6A73D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6A73D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6A73D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.5bd452p126F;
    static { NAMED.put("bright yellow saffron", -0x1.5bd452p126F); LIST.add(-0x1.5bd452p126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code 796B2FFF}, H 0.2, S 0.84705883, L 0.41960785, alpha 1.0, and chroma 0.43054104.
     * It can be represented as a packed float with the constant {@code -0x1.d7b066p125F}.
     * <pre>
     * <font style='background-color: #796B2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #796B2F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #796B2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #796B2F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #796B2F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #796B2F'>&nbsp;@&nbsp;</font><font style='background-color: #796B2F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #796B2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #796B2F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.d7b066p125F;
    static { NAMED.put("deep saffron yellow", -0x1.d7b066p125F); LIST.add(-0x1.d7b066p125F); }

    /**
     * This color constant "true saffron yellow" has RGBA8888 code {@code A28F41FF}, H 0.19607843, S 0.8352941, L 0.56078434, alpha 1.0, and chroma 0.5616909.
     * It can be represented as a packed float with the constant {@code -0x1.1faa64p126F}.
     * <pre>
     * <font style='background-color: #A28F41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A28F41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A28F41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A28F41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A28F41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A28F41'>&nbsp;@&nbsp;</font><font style='background-color: #A28F41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A28F41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A28F41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_YELLOW = -0x1.1faa64p126F;
    static { NAMED.put("true saffron yellow", -0x1.1faa64p126F); LIST.add(-0x1.1faa64p126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code C7B152FF}, H 0.2, S 0.81960785, L 0.69411767, alpha 1.0, and chroma 0.6669092.
     * It can be represented as a packed float with the constant {@code -0x1.63a266p126F}.
     * <pre>
     * <font style='background-color: #C7B152;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7B152; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7B152;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7B152'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7B152'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7B152'>&nbsp;@&nbsp;</font><font style='background-color: #C7B152; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7B152;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7B152; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.63a266p126F;
    static { NAMED.put("bright saffron yellow", -0x1.63a266p126F); LIST.add(-0x1.63a266p126F); }

    /**
     * This color constant "deep pure yellow" has RGBA8888 code {@code 6A6A21FF}, H 0.23921569, S 0.9411765, L 0.4, alpha 1.0, and chroma 0.4490167.
     * It can be represented as a packed float with the constant {@code -0x1.cde07ap125F}.
     * <pre>
     * <font style='background-color: #6A6A21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A6A21; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A6A21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A6A21'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A6A21'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A6A21'>&nbsp;@&nbsp;</font><font style='background-color: #6A6A21; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A6A21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A6A21; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_YELLOW = -0x1.cde07ap125F;
    static { NAMED.put("deep pure yellow", -0x1.cde07ap125F); LIST.add(-0x1.cde07ap125F); }

    /**
     * This color constant "true pure yellow" has RGBA8888 code {@code 8B8C2EFF}, H 0.23921569, S 0.92941177, L 0.5294118, alpha 1.0, and chroma 0.57927674.
     * It can be represented as a packed float with the constant {@code -0x1.0fda7ap126F}.
     * <pre>
     * <font style='background-color: #8B8C2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B8C2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B8C2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B8C2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B8C2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B8C2E'>&nbsp;@&nbsp;</font><font style='background-color: #8B8C2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B8C2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B8C2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_YELLOW = -0x1.0fda7ap126F;
    static { NAMED.put("true pure yellow", -0x1.0fda7ap126F); LIST.add(-0x1.0fda7ap126F); }

    /**
     * This color constant "bright pure yellow" has RGBA8888 code {@code B4B53EFF}, H 0.23921569, S 0.9137255, L 0.6862745, alpha 1.0, and chroma 0.72342336.
     * It can be represented as a packed float with the constant {@code -0x1.5fd27ap126F}.
     * <pre>
     * <font style='background-color: #B4B53E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4B53E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4B53E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4B53E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4B53E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4B53E'>&nbsp;@&nbsp;</font><font style='background-color: #B4B53E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4B53E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4B53E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_YELLOW = -0x1.5fd27ap126F;
    static { NAMED.put("bright pure yellow", -0x1.5fd27ap126F); LIST.add(-0x1.5fd27ap126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code 5D6429FF}, H 0.25882354, S 0.84705883, L 0.37254903, alpha 1.0, and chroma 0.38244462.
     * It can be represented as a packed float with the constant {@code -0x1.bfb084p125F}.
     * <pre>
     * <font style='background-color: #5D6429;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D6429; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D6429;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5D6429'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5D6429'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5D6429'>&nbsp;@&nbsp;</font><font style='background-color: #5D6429; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D6429;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D6429; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.bfb084p125F;
    static { NAMED.put("deep lime yellow", -0x1.bfb084p125F); LIST.add(-0x1.bfb084p125F); }

    /**
     * This color constant "true lime yellow" has RGBA8888 code {@code 80893BFF}, H 0.25882354, S 0.8352941, L 0.5137255, alpha 1.0, and chroma 0.51329124.
     * It can be represented as a packed float with the constant {@code -0x1.07aa84p126F}.
     * <pre>
     * <font style='background-color: #80893B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80893B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80893B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #80893B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #80893B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #80893B'>&nbsp;@&nbsp;</font><font style='background-color: #80893B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80893B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80893B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_YELLOW = -0x1.07aa84p126F;
    static { NAMED.put("true lime yellow", -0x1.07aa84p126F); LIST.add(-0x1.07aa84p126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code A9B650FF}, H 0.25882354, S 0.8235294, L 0.68235296, alpha 1.0, and chroma 0.65783507.
     * It can be represented as a packed float with the constant {@code -0x1.5da484p126F}.
     * <pre>
     * <font style='background-color: #A9B650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9B650; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9B650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A9B650'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A9B650'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A9B650'>&nbsp;@&nbsp;</font><font style='background-color: #A9B650; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9B650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9B650; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.5da484p126F;
    static { NAMED.put("bright lime yellow", -0x1.5da484p126F); LIST.add(-0x1.5da484p126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code 5E7222FF}, H 0.28235295, S 0.94509804, L 0.41568628, alpha 1.0, and chroma 0.4928434.
     * It can be represented as a packed float with the constant {@code -0x1.d5e29p125F}.
     * <pre>
     * <font style='background-color: #5E7222;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E7222; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E7222;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5E7222'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5E7222'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5E7222'>&nbsp;@&nbsp;</font><font style='background-color: #5E7222; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E7222;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E7222; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.d5e29p125F;
    static { NAMED.put("deep yellow lime", -0x1.d5e29p125F); LIST.add(-0x1.d5e29p125F); }

    /**
     * This color constant "true yellow lime" has RGBA8888 code {@code 7D9630FF}, H 0.28235295, S 0.92941177, L 0.54901963, alpha 1.0, and chroma 0.6311641.
     * It can be represented as a packed float with the constant {@code -0x1.19da9p126F}.
     * <pre>
     * <font style='background-color: #7D9630;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D9630; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D9630;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7D9630'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7D9630'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7D9630'>&nbsp;@&nbsp;</font><font style='background-color: #7D9630; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D9630;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D9630; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_LIME = -0x1.19da9p126F;
    static { NAMED.put("true yellow lime", -0x1.19da9p126F); LIST.add(-0x1.19da9p126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code 9DBC3EFF}, H 0.28235295, S 0.9137255, L 0.6862745, alpha 1.0, and chroma 0.7618593.
     * It can be represented as a packed float with the constant {@code -0x1.5fd29p126F}.
     * <pre>
     * <font style='background-color: #9DBC3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9DBC3E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9DBC3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9DBC3E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9DBC3E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9DBC3E'>&nbsp;@&nbsp;</font><font style='background-color: #9DBC3E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9DBC3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9DBC3E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.5fd29p126F;
    static { NAMED.put("bright yellow lime", -0x1.5fd29p126F); LIST.add(-0x1.5fd29p126F); }

    /**
     * This color constant "deep pure lime" has RGBA8888 code {@code 516E2BFF}, H 0.30588236, S 0.85490197, L 0.39607844, alpha 1.0, and chroma 0.4527026.
     * It can be represented as a packed float with the constant {@code -0x1.cbb49cp125F}.
     * <pre>
     * <font style='background-color: #516E2B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #516E2B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #516E2B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #516E2B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #516E2B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #516E2B'>&nbsp;@&nbsp;</font><font style='background-color: #516E2B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #516E2B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #516E2B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_LIME = -0x1.cbb49cp125F;
    static { NAMED.put("deep pure lime", -0x1.cbb49cp125F); LIST.add(-0x1.cbb49cp125F); }

    /**
     * This color constant "true pure lime" has RGBA8888 code {@code 6C913CFF}, H 0.30588236, S 0.8352941, L 0.52156866, alpha 1.0, and chroma 0.5752938.
     * It can be represented as a packed float with the constant {@code -0x1.0baa9cp126F}.
     * <pre>
     * <font style='background-color: #6C913C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C913C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C913C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C913C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C913C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C913C'>&nbsp;@&nbsp;</font><font style='background-color: #6C913C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C913C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C913C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_LIME = -0x1.0baa9cp126F;
    static { NAMED.put("true pure lime", -0x1.0baa9cp126F); LIST.add(-0x1.0baa9cp126F); }

    /**
     * This color constant "bright pure lime" has RGBA8888 code {@code 8DBC50FF}, H 0.30588236, S 0.81960785, L 0.6784314, alpha 1.0, and chroma 0.71963686.
     * It can be represented as a packed float with the constant {@code -0x1.5ba29cp126F}.
     * <pre>
     * <font style='background-color: #8DBC50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DBC50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DBC50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8DBC50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8DBC50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8DBC50'>&nbsp;@&nbsp;</font><font style='background-color: #8DBC50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DBC50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DBC50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_LIME = -0x1.5ba29cp126F;
    static { NAMED.put("bright pure lime", -0x1.5ba29cp126F); LIST.add(-0x1.5ba29cp126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 4A7822FF}, H 0.32156864, S 0.9490196, L 0.42352942, alpha 1.0, and chroma 0.5673484.
     * It can be represented as a packed float with the constant {@code -0x1.d9e4a4p125F}.
     * <pre>
     * <font style='background-color: #4A7822;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A7822; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A7822;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A7822'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A7822'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A7822'>&nbsp;@&nbsp;</font><font style='background-color: #4A7822; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A7822;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A7822; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.d9e4a4p125F;
    static { NAMED.put("deep green lime", -0x1.d9e4a4p125F); LIST.add(-0x1.d9e4a4p125F); }

    /**
     * This color constant "true green lime" has RGBA8888 code {@code 65A132FF}, H 0.32156864, S 0.9254902, L 0.5686275, alpha 1.0, and chroma 0.7311614.
     * It can be represented as a packed float with the constant {@code -0x1.23d8a4p126F}.
     * <pre>
     * <font style='background-color: #65A132;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65A132; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65A132;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65A132'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65A132'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65A132'>&nbsp;@&nbsp;</font><font style='background-color: #65A132; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65A132;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65A132; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_LIME = -0x1.23d8a4p126F;
    static { NAMED.put("true green lime", -0x1.23d8a4p126F); LIST.add(-0x1.23d8a4p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code 7DC53EFF}, H 0.32156864, S 0.9137255, L 0.69411767, alpha 1.0, and chroma 0.8666773.
     * It can be represented as a packed float with the constant {@code -0x1.63d2a4p126F}.
     * <pre>
     * <font style='background-color: #7DC53E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DC53E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DC53E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DC53E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DC53E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DC53E'>&nbsp;@&nbsp;</font><font style='background-color: #7DC53E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DC53E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DC53E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.63d2a4p126F;
    static { NAMED.put("bright green lime", -0x1.63d2a4p126F); LIST.add(-0x1.63d2a4p126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 41762DFF}, H 0.3372549, S 0.85490197, L 0.4117647, alpha 1.0, and chroma 0.5335971.
     * It can be represented as a packed float with the constant {@code -0x1.d3b4acp125F}.
     * <pre>
     * <font style='background-color: #41762D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #41762D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #41762D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #41762D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #41762D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #41762D'>&nbsp;@&nbsp;</font><font style='background-color: #41762D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #41762D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #41762D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.d3b4acp125F;
    static { NAMED.put("deep lime green", -0x1.d3b4acp125F); LIST.add(-0x1.d3b4acp125F); }

    /**
     * This color constant "true lime green" has RGBA8888 code {@code 56993EFF}, H 0.3372549, S 0.83137256, L 0.5372549, alpha 1.0, and chroma 0.6683105.
     * It can be represented as a packed float with the constant {@code -0x1.13a8acp126F}.
     * <pre>
     * <font style='background-color: #56993E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56993E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56993E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #56993E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #56993E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #56993E'>&nbsp;@&nbsp;</font><font style='background-color: #56993E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56993E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56993E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_GREEN = -0x1.13a8acp126F;
    static { NAMED.put("true lime green", -0x1.13a8acp126F); LIST.add(-0x1.13a8acp126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code 6FC350FF}, H 0.3372549, S 0.8235294, L 0.6862745, alpha 1.0, and chroma 0.8294271.
     * It can be represented as a packed float with the constant {@code -0x1.5fa4acp126F}.
     * <pre>
     * <font style='background-color: #6FC350;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6FC350; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6FC350;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6FC350'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6FC350'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6FC350'>&nbsp;@&nbsp;</font><font style='background-color: #6FC350; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6FC350;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6FC350; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.5fa4acp126F;
    static { NAMED.put("bright lime green", -0x1.5fa4acp126F); LIST.add(-0x1.5fa4acp126F); }

    /**
     * This color constant "deep pure green" has RGBA8888 code {@code 22711EFF}, H 0.3529412, S 0.9490196, L 0.38431373, alpha 1.0, and chroma 0.60386163.
     * It can be represented as a packed float with the constant {@code -0x1.c5e4b4p125F}.
     * <pre>
     * <font style='background-color: #22711E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #22711E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #22711E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #22711E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #22711E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #22711E'>&nbsp;@&nbsp;</font><font style='background-color: #22711E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #22711E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #22711E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_GREEN = -0x1.c5e4b4p125F;
    static { NAMED.put("deep pure green", -0x1.c5e4b4p125F); LIST.add(-0x1.c5e4b4p125F); }

    /**
     * This color constant "true pure green" has RGBA8888 code {@code 31982CFF}, H 0.3529412, S 0.9372549, L 0.5176471, alpha 1.0, and chroma 0.79309285.
     * It can be represented as a packed float with the constant {@code -0x1.09deb4p126F}.
     * <pre>
     * <font style='background-color: #31982C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31982C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31982C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #31982C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #31982C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #31982C'>&nbsp;@&nbsp;</font><font style='background-color: #31982C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31982C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31982C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_GREEN = -0x1.09deb4p126F;
    static { NAMED.put("true pure green", -0x1.09deb4p126F); LIST.add(-0x1.09deb4p126F); }

    /**
     * This color constant "bright pure green" has RGBA8888 code {@code 43C73DFF}, H 0.3529412, S 0.9137255, L 0.6784314, alpha 1.0, and chroma 0.9927028.
     * It can be represented as a packed float with the constant {@code -0x1.5bd2b4p126F}.
     * <pre>
     * <font style='background-color: #43C73D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43C73D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43C73D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #43C73D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #43C73D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #43C73D'>&nbsp;@&nbsp;</font><font style='background-color: #43C73D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43C73D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43C73D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_GREEN = -0x1.5bd2b4p126F;
    static { NAMED.put("bright pure green", -0x1.5bd2b4p126F); LIST.add(-0x1.5bd2b4p126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 307857FF}, H 0.4117647, S 0.8509804, L 0.41960785, alpha 1.0, and chroma 0.38997662.
     * It can be represented as a packed float with the constant {@code -0x1.d7b2d2p125F}.
     * <pre>
     * <font style='background-color: #307857;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #307857; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #307857;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #307857'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #307857'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #307857'>&nbsp;@&nbsp;</font><font style='background-color: #307857; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #307857;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #307857; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.d7b2d2p125F;
    static { NAMED.put("deep cyan green", -0x1.d7b2d2p125F); LIST.add(-0x1.d7b2d2p125F); }

    /**
     * This color constant "true cyan green" has RGBA8888 code {@code 439F75FF}, H 0.4117647, S 0.83137256, L 0.5568628, alpha 1.0, and chroma 0.49822673.
     * It can be represented as a packed float with the constant {@code -0x1.1da8d2p126F}.
     * <pre>
     * <font style='background-color: #439F75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #439F75; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #439F75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #439F75'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #439F75'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #439F75'>&nbsp;@&nbsp;</font><font style='background-color: #439F75; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #439F75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #439F75; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_GREEN = -0x1.1da8d2p126F;
    static { NAMED.put("true cyan green", -0x1.1da8d2p126F); LIST.add(-0x1.1da8d2p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code 54C491FF}, H 0.4117647, S 0.81960785, L 0.6862745, alpha 1.0, and chroma 0.5951393.
     * It can be represented as a packed float with the constant {@code -0x1.5fa2d2p126F}.
     * <pre>
     * <font style='background-color: #54C491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54C491; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54C491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #54C491'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #54C491'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #54C491'>&nbsp;@&nbsp;</font><font style='background-color: #54C491; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54C491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54C491; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.5fa2d2p126F;
    static { NAMED.put("bright cyan green", -0x1.5fa2d2p126F); LIST.add(-0x1.5fa2d2p126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 237365FF}, H 0.4745098, S 0.94509804, L 0.40392157, alpha 1.0, and chroma 0.33880532.
     * It can be represented as a packed float with the constant {@code -0x1.cfe2f2p125F}.
     * <pre>
     * <font style='background-color: #237365;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #237365; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #237365;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #237365'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #237365'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #237365'>&nbsp;@&nbsp;</font><font style='background-color: #237365; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #237365;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #237365; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.cfe2f2p125F;
    static { NAMED.put("deep green cyan", -0x1.cfe2f2p125F); LIST.add(-0x1.cfe2f2p125F); }

    /**
     * This color constant "true green cyan" has RGBA8888 code {@code 319785FF}, H 0.4745098, S 0.93333334, L 0.5294118, alpha 1.0, and chroma 0.43300363.
     * It can be represented as a packed float with the constant {@code -0x1.0fdcf2p126F}.
     * <pre>
     * <font style='background-color: #319785;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #319785; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #319785;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #319785'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #319785'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #319785'>&nbsp;@&nbsp;</font><font style='background-color: #319785; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #319785;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #319785; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_CYAN = -0x1.0fdcf2p126F;
    static { NAMED.put("true green cyan", -0x1.0fdcf2p126F); LIST.add(-0x1.0fdcf2p126F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code 41C1ABFF}, H 0.4745098, S 0.9137255, L 0.6784314, alpha 1.0, and chroma 0.53289866.
     * It can be represented as a packed float with the constant {@code -0x1.5bd2f2p126F}.
     * <pre>
     * <font style='background-color: #41C1AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #41C1AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #41C1AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #41C1AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #41C1AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #41C1AB'>&nbsp;@&nbsp;</font><font style='background-color: #41C1AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #41C1AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #41C1AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.5bd2f2p126F;
    static { NAMED.put("bright green cyan", -0x1.5bd2f2p126F); LIST.add(-0x1.5bd2f2p126F); }

    /**
     * This color constant "deep pure cyan" has RGBA8888 code {@code 337676FF}, H 0.53333336, S 0.84705883, L 0.42352942, alpha 1.0, and chroma 0.30631578.
     * It can be represented as a packed float with the constant {@code -0x1.d9b11p125F}.
     * <pre>
     * <font style='background-color: #337676;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #337676; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #337676;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #337676'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #337676'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #337676'>&nbsp;@&nbsp;</font><font style='background-color: #337676; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #337676;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #337676; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_CYAN = -0x1.d9b11p125F;
    static { NAMED.put("deep pure cyan", -0x1.d9b11p125F); LIST.add(-0x1.d9b11p125F); }

    /**
     * This color constant "true pure cyan" has RGBA8888 code {@code 48A0A0FF}, H 0.53333336, S 0.83137256, L 0.57254905, alpha 1.0, and chroma 0.39984366.
     * It can be represented as a packed float with the constant {@code -0x1.25a91p126F}.
     * <pre>
     * <font style='background-color: #48A0A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48A0A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48A0A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #48A0A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #48A0A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #48A0A0'>&nbsp;@&nbsp;</font><font style='background-color: #48A0A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48A0A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48A0A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_CYAN = -0x1.25a91p126F;
    static { NAMED.put("true pure cyan", -0x1.25a91p126F); LIST.add(-0x1.25a91p126F); }

    /**
     * This color constant "bright pure cyan" has RGBA8888 code {@code 59C3C3FF}, H 0.53333336, S 0.81960785, L 0.69803923, alpha 1.0, and chroma 0.47265092.
     * It can be represented as a packed float with the constant {@code -0x1.65a31p126F}.
     * <pre>
     * <font style='background-color: #59C3C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #59C3C3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #59C3C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #59C3C3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #59C3C3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #59C3C3'>&nbsp;@&nbsp;</font><font style='background-color: #59C3C3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #59C3C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #59C3C3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_CYAN = -0x1.65a31p126F;
    static { NAMED.put("bright pure cyan", -0x1.65a31p126F); LIST.add(-0x1.65a31p126F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 277386FF}, H 0.60784316, S 0.94509804, L 0.41568628, alpha 1.0, and chroma 0.39278713.
     * It can be represented as a packed float with the constant {@code -0x1.d5e336p125F}.
     * <pre>
     * <font style='background-color: #277386;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #277386; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #277386;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #277386'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #277386'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #277386'>&nbsp;@&nbsp;</font><font style='background-color: #277386; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #277386;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #277386; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.d5e336p125F;
    static { NAMED.put("deep blue cyan", -0x1.d5e336p125F); LIST.add(-0x1.d5e336p125F); }

    /**
     * This color constant "true blue cyan" has RGBA8888 code {@code 3597AFFF}, H 0.60784316, S 0.93333334, L 0.54509807, alpha 1.0, and chroma 0.5017791.
     * It can be represented as a packed float with the constant {@code -0x1.17dd36p126F}.
     * <pre>
     * <font style='background-color: #3597AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3597AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3597AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3597AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3597AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3597AF'>&nbsp;@&nbsp;</font><font style='background-color: #3597AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3597AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3597AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_CYAN = -0x1.17dd36p126F;
    static { NAMED.put("true blue cyan", -0x1.17dd36p126F); LIST.add(-0x1.17dd36p126F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 45BDDAFF}, H 0.6039216, S 0.9137255, L 0.68235296, alpha 1.0, and chroma 0.5951791.
     * It can be represented as a packed float with the constant {@code -0x1.5dd334p126F}.
     * <pre>
     * <font style='background-color: #45BDDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #45BDDA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #45BDDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #45BDDA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #45BDDA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #45BDDA'>&nbsp;@&nbsp;</font><font style='background-color: #45BDDA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #45BDDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #45BDDA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.5dd334p126F;
    static { NAMED.put("bright blue cyan", -0x1.5dd334p126F); LIST.add(-0x1.5dd334p126F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 336894FF}, H 0.6745098, S 0.84705883, L 0.39215687, alpha 1.0, and chroma 0.50308466.
     * It can be represented as a packed float with the constant {@code -0x1.c9b158p125F}.
     * <pre>
     * <font style='background-color: #336894;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #336894; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #336894;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #336894'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #336894'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #336894'>&nbsp;@&nbsp;</font><font style='background-color: #336894; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #336894;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #336894; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.c9b158p125F;
    static { NAMED.put("deep cyan blue", -0x1.c9b158p125F); LIST.add(-0x1.c9b158p125F); }

    /**
     * This color constant "true cyan blue" has RGBA8888 code {@code 468BC3FF}, H 0.6745098, S 0.8392157, L 0.52156866, alpha 1.0, and chroma 0.6545621.
     * It can be represented as a packed float with the constant {@code -0x1.0bad58p126F}.
     * <pre>
     * <font style='background-color: #468BC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #468BC3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #468BC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #468BC3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #468BC3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #468BC3'>&nbsp;@&nbsp;</font><font style='background-color: #468BC3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #468BC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #468BC3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_BLUE = -0x1.0bad58p126F;
    static { NAMED.put("true cyan blue", -0x1.0bad58p126F); LIST.add(-0x1.0bad58p126F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code 75B3EEFF}, H 0.6745098, S 0.7019608, L 0.6784314, alpha 1.0, and chroma 0.5682446.
     * It can be represented as a packed float with the constant {@code -0x1.5b6758p126F}.
     * <pre>
     * <font style='background-color: #75B3EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #75B3EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #75B3EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #75B3EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #75B3EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #75B3EE'>&nbsp;@&nbsp;</font><font style='background-color: #75B3EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #75B3EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #75B3EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.5b6758p126F;
    static { NAMED.put("bright cyan blue", -0x1.5b6758p126F); LIST.add(-0x1.5b6758p126F); }

    /**
     * This color constant "deep pure blue" has RGBA8888 code {@code 5E57EAFF}, H 0.7411765, S 0.85490197, L 0.42352942, alpha 1.0, and chroma 1.1046054.
     * It can be represented as a packed float with the constant {@code -0x1.d9b57ap125F}.
     * <pre>
     * <font style='background-color: #5E57EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E57EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E57EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5E57EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5E57EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5E57EA'>&nbsp;@&nbsp;</font><font style='background-color: #5E57EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E57EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E57EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BLUE = -0x1.d9b57ap125F;
    static { NAMED.put("deep pure blue", -0x1.d9b57ap125F); LIST.add(-0x1.d9b57ap125F); }

    /**
     * This color constant "true pure blue" has RGBA8888 code {@code 8884F1FF}, H 0.7411765, S 0.8117647, L 0.5647059, alpha 1.0, and chroma 0.8349867.
     * It can be represented as a packed float with the constant {@code -0x1.219f7ap126F}.
     * <pre>
     * <font style='background-color: #8884F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8884F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8884F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8884F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8884F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8884F1'>&nbsp;@&nbsp;</font><font style='background-color: #8884F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8884F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8884F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BLUE = -0x1.219f7ap126F;
    static { NAMED.put("true pure blue", -0x1.219f7ap126F); LIST.add(-0x1.219f7ap126F); }

    /**
     * This color constant "bright pure blue" has RGBA8888 code {@code ACAAF6FF}, H 0.7411765, S 0.7882353, L 0.69411767, alpha 1.0, and chroma 0.55983067.
     * It can be represented as a packed float with the constant {@code -0x1.63937ap126F}.
     * <pre>
     * <font style='background-color: #ACAAF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACAAF6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACAAF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ACAAF6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ACAAF6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ACAAF6'>&nbsp;@&nbsp;</font><font style='background-color: #ACAAF6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACAAF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACAAF6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BLUE = -0x1.63937ap126F;
    static { NAMED.put("bright pure blue", -0x1.63937ap126F); LIST.add(-0x1.63937ap126F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 6E4FD9FF}, H 0.7529412, S 0.76862746, L 0.40784314, alpha 1.0, and chroma 1.0086452.
     * It can be represented as a packed float with the constant {@code -0x1.d1898p125F}.
     * <pre>
     * <font style='background-color: #6E4FD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E4FD9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E4FD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E4FD9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E4FD9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E4FD9'>&nbsp;@&nbsp;</font><font style='background-color: #6E4FD9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E4FD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E4FD9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.d1898p125F;
    static { NAMED.put("deep violet blue", -0x1.d1898p125F); LIST.add(-0x1.d1898p125F); }

    /**
     * This color constant "true violet blue" has RGBA8888 code {@code 8C79E5FF}, H 0.7529412, S 0.73333335, L 0.53333336, alpha 1.0, and chroma 0.80922914.
     * It can be represented as a packed float with the constant {@code -0x1.11778p126F}.
     * <pre>
     * <font style='background-color: #8C79E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C79E5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C79E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C79E5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C79E5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C79E5'>&nbsp;@&nbsp;</font><font style='background-color: #8C79E5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C79E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C79E5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_BLUE = -0x1.11778p126F;
    static { NAMED.put("true violet blue", -0x1.11778p126F); LIST.add(-0x1.11778p126F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code B1A6EEFF}, H 0.7529412, S 0.7019608, L 0.68235296, alpha 1.0, and chroma 0.521854.
     * It can be represented as a packed float with the constant {@code -0x1.5d678p126F}.
     * <pre>
     * <font style='background-color: #B1A6EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1A6EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1A6EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1A6EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1A6EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1A6EE'>&nbsp;@&nbsp;</font><font style='background-color: #B1A6EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1A6EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1A6EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.5d678p126F;
    static { NAMED.put("bright violet blue", -0x1.5d678p126F); LIST.add(-0x1.5d678p126F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 7B30E6FF}, H 0.7647059, S 0.9411765, L 0.38039216, alpha 1.0, and chroma 1.2638375.
     * It can be represented as a packed float with the constant {@code -0x1.c3e186p125F}.
     * <pre>
     * <font style='background-color: #7B30E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B30E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B30E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B30E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B30E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B30E6'>&nbsp;@&nbsp;</font><font style='background-color: #7B30E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B30E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B30E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.c3e186p125F;
    static { NAMED.put("deep blue violet", -0x1.c3e186p125F); LIST.add(-0x1.c3e186p125F); }

    /**
     * This color constant "true blue violet" has RGBA8888 code {@code 956AEFFF}, H 0.7647059, S 0.827451, L 0.5137255, alpha 1.0, and chroma 0.95612687.
     * It can be represented as a packed float with the constant {@code -0x1.07a786p126F}.
     * <pre>
     * <font style='background-color: #956AEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #956AEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #956AEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #956AEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #956AEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #956AEF'>&nbsp;@&nbsp;</font><font style='background-color: #956AEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #956AEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #956AEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_VIOLET = -0x1.07a786p126F;
    static { NAMED.put("true blue violet", -0x1.07a786p126F); LIST.add(-0x1.07a786p126F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code B8A1F5FF}, H 0.7647059, S 0.78431374, L 0.6784314, alpha 1.0, and chroma 0.59698486.
     * It can be represented as a packed float with the constant {@code -0x1.5b9186p126F}.
     * <pre>
     * <font style='background-color: #B8A1F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8A1F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8A1F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B8A1F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B8A1F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B8A1F5'>&nbsp;@&nbsp;</font><font style='background-color: #B8A1F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8A1F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8A1F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.5b9186p126F;
    static { NAMED.put("bright blue violet", -0x1.5b9186p126F); LIST.add(-0x1.5b9186p126F); }

    /**
     * This color constant "deep pure violet" has RGBA8888 code {@code 8745DAFF}, H 0.77254903, S 0.8039216, L 0.41568628, alpha 1.0, and chroma 1.066028.
     * It can be represented as a packed float with the constant {@code -0x1.d59b8ap125F}.
     * <pre>
     * <font style='background-color: #8745DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8745DA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8745DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8745DA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8745DA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8745DA'>&nbsp;@&nbsp;</font><font style='background-color: #8745DA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8745DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8745DA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_VIOLET = -0x1.d59b8ap125F;
    static { NAMED.put("deep pure violet", -0x1.d59b8ap125F); LIST.add(-0x1.d59b8ap125F); }

    /**
     * This color constant "true pure violet" has RGBA8888 code {@code A278E6FF}, H 0.77254903, S 0.7294118, L 0.5529412, alpha 1.0, and chroma 0.7877331.
     * It can be represented as a packed float with the constant {@code -0x1.1b758ap126F}.
     * <pre>
     * <font style='background-color: #A278E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A278E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A278E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A278E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A278E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A278E6'>&nbsp;@&nbsp;</font><font style='background-color: #A278E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A278E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A278E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_VIOLET = -0x1.1b758ap126F;
    static { NAMED.put("true pure violet", -0x1.1b758ap126F); LIST.add(-0x1.1b758ap126F); }

    /**
     * This color constant "bright pure violet" has RGBA8888 code {@code BDA4EFFF}, H 0.77254903, S 0.70980394, L 0.6901961, alpha 1.0, and chroma 0.5242858.
     * It can be represented as a packed float with the constant {@code -0x1.616b8ap126F}.
     * <pre>
     * <font style='background-color: #BDA4EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDA4EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDA4EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BDA4EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BDA4EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BDA4EF'>&nbsp;@&nbsp;</font><font style='background-color: #BDA4EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDA4EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDA4EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_VIOLET = -0x1.616b8ap126F;
    static { NAMED.put("bright pure violet", -0x1.616b8ap126F); LIST.add(-0x1.616b8ap126F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 922FDBFF}, H 0.78431374, S 0.94509804, L 0.4, alpha 1.0, and chroma 1.1850535.
     * It can be represented as a packed float with the constant {@code -0x1.cde39p125F}.
     * <pre>
     * <font style='background-color: #922FDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #922FDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #922FDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #922FDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #922FDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #922FDB'>&nbsp;@&nbsp;</font><font style='background-color: #922FDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #922FDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #922FDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.cde39p125F;
    static { NAMED.put("deep purple violet", -0x1.cde39p125F); LIST.add(-0x1.cde39p125F); }

    /**
     * This color constant "true purple violet" has RGBA8888 code {@code AB64EFFF}, H 0.78431374, S 0.8235294, L 0.5254902, alpha 1.0, and chroma 0.9574832.
     * It can be represented as a packed float with the constant {@code -0x1.0da59p126F}.
     * <pre>
     * <font style='background-color: #AB64EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB64EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB64EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB64EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB64EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB64EF'>&nbsp;@&nbsp;</font><font style='background-color: #AB64EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB64EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB64EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_VIOLET = -0x1.0da59p126F;
    static { NAMED.put("true purple violet", -0x1.0da59p126F); LIST.add(-0x1.0da59p126F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code C59CF5FF}, H 0.78431374, S 0.7882353, L 0.6784314, alpha 1.0, and chroma 0.61816573.
     * It can be represented as a packed float with the constant {@code -0x1.5b939p126F}.
     * <pre>
     * <font style='background-color: #C59CF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C59CF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C59CF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C59CF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C59CF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C59CF5'>&nbsp;@&nbsp;</font><font style='background-color: #C59CF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C59CF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C59CF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.5b939p126F;
    static { NAMED.put("bright purple violet", -0x1.5b939p126F); LIST.add(-0x1.5b939p126F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 9B3FD0FF}, H 0.79607844, S 0.84313726, L 0.42352942, alpha 1.0, and chroma 1.0530739.
     * It can be represented as a packed float with the constant {@code -0x1.d9af96p125F}.
     * <pre>
     * <font style='background-color: #9B3FD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B3FD0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B3FD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B3FD0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B3FD0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B3FD0'>&nbsp;@&nbsp;</font><font style='background-color: #9B3FD0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B3FD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B3FD0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.d9af96p125F;
    static { NAMED.put("deep violet purple", -0x1.d9af96p125F); LIST.add(-0x1.d9af96p125F); }

    /**
     * This color constant "true violet purple" has RGBA8888 code {@code B775E7FF}, H 0.79607844, S 0.7254902, L 0.5686275, alpha 1.0, and chroma 0.79093164.
     * It can be represented as a packed float with the constant {@code -0x1.237396p126F}.
     * <pre>
     * <font style='background-color: #B775E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B775E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B775E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B775E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B775E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B775E7'>&nbsp;@&nbsp;</font><font style='background-color: #B775E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B775E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B775E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_PURPLE = -0x1.237396p126F;
    static { NAMED.put("true violet purple", -0x1.237396p126F); LIST.add(-0x1.237396p126F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code CBA1EFFF}, H 0.79607844, S 0.7058824, L 0.69411767, alpha 1.0, and chroma 0.5384342.
     * It can be represented as a packed float with the constant {@code -0x1.636996p126F}.
     * <pre>
     * <font style='background-color: #CBA1EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBA1EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBA1EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBA1EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBA1EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBA1EF'>&nbsp;@&nbsp;</font><font style='background-color: #CBA1EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBA1EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBA1EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.636996p126F;
    static { NAMED.put("bright violet purple", -0x1.636996p126F); LIST.add(-0x1.636996p126F); }

    /**
     * This color constant "deep pure purple" has RGBA8888 code {@code A12FD1FF}, H 0.8039216, S 0.9411765, L 0.4117647, alpha 1.0, and chroma 1.1049628.
     * It can be represented as a packed float with the constant {@code -0x1.d3e19ap125F}.
     * <pre>
     * <font style='background-color: #A12FD1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A12FD1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A12FD1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A12FD1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A12FD1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A12FD1'>&nbsp;@&nbsp;</font><font style='background-color: #A12FD1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A12FD1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A12FD1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_PURPLE = -0x1.d3e19ap125F;
    static { NAMED.put("deep pure purple", -0x1.d3e19ap125F); LIST.add(-0x1.d3e19ap125F); }

    /**
     * This color constant "true pure purple" has RGBA8888 code {@code C05FF0FF}, H 0.8039216, S 0.81960785, L 0.5411765, alpha 1.0, and chroma 0.96638286.
     * It can be represented as a packed float with the constant {@code -0x1.15a39ap126F}.
     * <pre>
     * <font style='background-color: #C05FF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C05FF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C05FF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C05FF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C05FF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C05FF0'>&nbsp;@&nbsp;</font><font style='background-color: #C05FF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C05FF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C05FF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_PURPLE = -0x1.15a39ap126F;
    static { NAMED.put("true pure purple", -0x1.15a39ap126F); LIST.add(-0x1.15a39ap126F); }

    /**
     * This color constant "bright pure purple" has RGBA8888 code {@code D299F5FF}, H 0.8039216, S 0.78431374, L 0.6862745, alpha 1.0, and chroma 0.6276063.
     * It can be represented as a packed float with the constant {@code -0x1.5f919ap126F}.
     * <pre>
     * <font style='background-color: #D299F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D299F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D299F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D299F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D299F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D299F5'>&nbsp;@&nbsp;</font><font style='background-color: #D299F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D299F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D299F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_PURPLE = -0x1.5f919ap126F;
    static { NAMED.put("bright pure purple", -0x1.5f919ap126F); LIST.add(-0x1.5f919ap126F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code 9736ADFF}, H 0.8235294, S 0.8509804, L 0.38431373, alpha 1.0, and chroma 0.86950034.
     * It can be represented as a packed float with the constant {@code -0x1.c5b3a4p125F}.
     * <pre>
     * <font style='background-color: #9736AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9736AD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9736AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9736AD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9736AD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9736AD'>&nbsp;@&nbsp;</font><font style='background-color: #9736AD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9736AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9736AD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.c5b3a4p125F;
    static { NAMED.put("deep magenta purple", -0x1.c5b3a4p125F); LIST.add(-0x1.c5b3a4p125F); }

    /**
     * This color constant "true magenta purple" has RGBA8888 code {@code C74FE3FF}, H 0.8235294, S 0.80784315, L 0.5176471, alpha 1.0, and chroma 1.0599139.
     * It can be represented as a packed float with the constant {@code -0x1.099da4p126F}.
     * <pre>
     * <font style='background-color: #C74FE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C74FE3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C74FE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C74FE3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C74FE3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C74FE3'>&nbsp;@&nbsp;</font><font style='background-color: #C74FE3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C74FE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C74FE3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_PURPLE = -0x1.099da4p126F;
    static { NAMED.put("true magenta purple", -0x1.099da4p126F); LIST.add(-0x1.099da4p126F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code D995EEFF}, H 0.8235294, S 0.7058824, L 0.6784314, alpha 1.0, and chroma 0.61879605.
     * It can be represented as a packed float with the constant {@code -0x1.5b69a4p126F}.
     * <pre>
     * <font style='background-color: #D995EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D995EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D995EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D995EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D995EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D995EE'>&nbsp;@&nbsp;</font><font style='background-color: #D995EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D995EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D995EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.5b69a4p126F;
    static { NAMED.put("bright magenta purple", -0x1.5b69a4p126F); LIST.add(-0x1.5b69a4p126F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code B02DBDFF}, H 0.8392157, S 0.94509804, L 0.41960785, alpha 1.0, and chroma 1.0065209.
     * It can be represented as a packed float with the constant {@code -0x1.d7e3acp125F}.
     * <pre>
     * <font style='background-color: #B02DBD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B02DBD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B02DBD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B02DBD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B02DBD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B02DBD'>&nbsp;@&nbsp;</font><font style='background-color: #B02DBD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B02DBD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B02DBD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.d7e3acp125F;
    static { NAMED.put("deep purple magenta", -0x1.d7e3acp125F); LIST.add(-0x1.d7e3acp125F); }

    /**
     * This color constant "true purple magenta" has RGBA8888 code {@code E14DF1FF}, H 0.8392157, S 0.85490197, L 0.56078434, alpha 1.0, and chroma 1.0998348.
     * It can be represented as a packed float with the constant {@code -0x1.1fb5acp126F}.
     * <pre>
     * <font style='background-color: #E14DF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E14DF1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E14DF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E14DF1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E14DF1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E14DF1'>&nbsp;@&nbsp;</font><font style='background-color: #E14DF1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E14DF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E14DF1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_MAGENTA = -0x1.1fb5acp126F;
    static { NAMED.put("true purple magenta", -0x1.1fb5acp126F); LIST.add(-0x1.1fb5acp126F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code EA90F6FF}, H 0.8392157, S 0.7921569, L 0.69411767, alpha 1.0, and chroma 0.703975.
     * It can be represented as a packed float with the constant {@code -0x1.6395acp126F}.
     * <pre>
     * <font style='background-color: #EA90F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA90F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA90F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EA90F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EA90F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EA90F6'>&nbsp;@&nbsp;</font><font style='background-color: #EA90F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA90F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA90F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.6395acp126F;
    static { NAMED.put("bright purple magenta", -0x1.6395acp126F); LIST.add(-0x1.6395acp126F); }

    /**
     * This color constant "deep pure magenta" has RGBA8888 code {@code A637A6FF}, H 0.85490197, S 0.8509804, L 0.40392157, alpha 1.0, and chroma 0.84567237.
     * It can be represented as a packed float with the constant {@code -0x1.cfb3b4p125F}.
     * <pre>
     * <font style='background-color: #A637A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A637A6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A637A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A637A6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A637A6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A637A6'>&nbsp;@&nbsp;</font><font style='background-color: #A637A6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A637A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A637A6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_MAGENTA = -0x1.cfb3b4p125F;
    static { NAMED.put("deep pure magenta", -0x1.cfb3b4p125F); LIST.add(-0x1.cfb3b4p125F); }

    /**
     * This color constant "true pure magenta" has RGBA8888 code {@code D84AD8FF}, H 0.85490197, S 0.8392157, L 0.5294118, alpha 1.0, and chroma 1.0792882.
     * It can be represented as a packed float with the constant {@code -0x1.0fadb4p126F}.
     * <pre>
     * <font style='background-color: #D84AD8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D84AD8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D84AD8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D84AD8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D84AD8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D84AD8'>&nbsp;@&nbsp;</font><font style='background-color: #D84AD8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D84AD8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D84AD8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_MAGENTA = -0x1.0fadb4p126F;
    static { NAMED.put("true pure magenta", -0x1.0fadb4p126F); LIST.add(-0x1.0fadb4p126F); }

    /**
     * This color constant "bright pure magenta" has RGBA8888 code {@code EE8BEEFF}, H 0.85490197, S 0.7058824, L 0.68235296, alpha 1.0, and chroma 0.7082928.
     * It can be represented as a packed float with the constant {@code -0x1.5d69b4p126F}.
     * <pre>
     * <font style='background-color: #EE8BEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE8BEE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE8BEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EE8BEE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EE8BEE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EE8BEE'>&nbsp;@&nbsp;</font><font style='background-color: #EE8BEE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE8BEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE8BEE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_MAGENTA = -0x1.5d69b4p126F;
    static { NAMED.put("bright pure magenta", -0x1.5d69b4p126F); LIST.add(-0x1.5d69b4p126F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code AA2485FF}, H 0.91764706, S 0.94509804, L 0.37254903, alpha 1.0, and chroma 0.8450349.
     * It can be represented as a packed float with the constant {@code -0x1.bfe3d4p125F}.
     * <pre>
     * <font style='background-color: #AA2485;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA2485; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA2485;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA2485'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA2485'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA2485'>&nbsp;@&nbsp;</font><font style='background-color: #AA2485; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA2485;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA2485; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.bfe3d4p125F;
    static { NAMED.put("deep red magenta", -0x1.bfe3d4p125F); LIST.add(-0x1.bfe3d4p125F); }

    /**
     * This color constant "true red magenta" has RGBA8888 code {@code E735B6FF}, H 0.91764706, S 0.93333334, L 0.5137255, alpha 1.0, and chroma 1.1358057.
     * It can be represented as a packed float with the constant {@code -0x1.07ddd4p126F}.
     * <pre>
     * <font style='background-color: #E735B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E735B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E735B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E735B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E735B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E735B6'>&nbsp;@&nbsp;</font><font style='background-color: #E735B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E735B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E735B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_MAGENTA = -0x1.07ddd4p126F;
    static { NAMED.put("true red magenta", -0x1.07ddd4p126F); LIST.add(-0x1.07ddd4p126F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code F68AD0FF}, H 0.91764706, S 0.7764706, L 0.6784314, alpha 1.0, and chroma 0.6231221.
     * It can be represented as a packed float with the constant {@code -0x1.5b8dd4p126F}.
     * <pre>
     * <font style='background-color: #F68AD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F68AD0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F68AD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F68AD0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F68AD0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F68AD0'>&nbsp;@&nbsp;</font><font style='background-color: #F68AD0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F68AD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F68AD0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.5b8dd4p126F;
    static { NAMED.put("bright red magenta", -0x1.5b8dd4p126F); LIST.add(-0x1.5b8dd4p126F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code BE3371FF}, H 0.9764706, S 0.84705883, L 0.41568628, alpha 1.0, and chroma 0.95219487.
     * It can be represented as a packed float with the constant {@code -0x1.d5b1f2p125F}.
     * <pre>
     * <font style='background-color: #BE3371;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE3371; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE3371;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE3371'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE3371'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE3371'>&nbsp;@&nbsp;</font><font style='background-color: #BE3371; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE3371;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE3371; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.d5b1f2p125F;
    static { NAMED.put("deep magenta red", -0x1.d5b1f2p125F); LIST.add(-0x1.d5b1f2p125F); }

    /**
     * This color constant "true magenta red" has RGBA8888 code {@code EB5695FF}, H 0.972549, S 0.70980394, L 0.54901963, alpha 1.0, and chroma 0.8740207.
     * It can be represented as a packed float with the constant {@code -0x1.196bfp126F}.
     * <pre>
     * <font style='background-color: #EB5695;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB5695; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB5695;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EB5695'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EB5695'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EB5695'>&nbsp;@&nbsp;</font><font style='background-color: #EB5695; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB5695;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB5695; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_RED = -0x1.196bfp126F;
    static { NAMED.put("true magenta red", -0x1.196bfp126F); LIST.add(-0x1.196bfp126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code F195B5FF}, H 0.972549, S 0.6901961, L 0.6901961, alpha 1.0, and chroma 0.49810454.
     * It can be represented as a packed float with the constant {@code -0x1.6161fp126F}.
     * <pre>
     * <font style='background-color: #F195B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F195B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F195B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F195B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F195B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F195B5'>&nbsp;@&nbsp;</font><font style='background-color: #F195B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F195B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F195B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.6161fp126F;
    static { NAMED.put("bright magenta red", -0x1.6161fp126F); LIST.add(-0x1.6161fp126F); }

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
