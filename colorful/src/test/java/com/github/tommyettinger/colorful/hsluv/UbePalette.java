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
     * This color constant "pure black" has RGBA8888 code {@code 007F7FFF}, H 0.53333336, S 1.0, L 0.44313726, alpha 1.0, and chroma 0.37768868.
     * It can be represented as a packed float with the constant {@code -0x1.e3ff1p125F}.
     * <pre>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #007F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_BLACK = -0x1.e3ff1p125F;
    static { NAMED.put("pure black", -0x1.e3ff1p125F); LIST.add(-0x1.e3ff1p125F); }

    /**
     * This color constant "almost black" has RGBA8888 code {@code 121212FF}, H 0.16078432, S 0.0, L 0.05490196, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.1c0052p125F}.
     * <pre>
     * <font style='background-color: #121212;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #121212; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #121212;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #121212'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #121212'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #121212'>&nbsp;@&nbsp;</font><font style='background-color: #121212; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #121212;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #121212; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_BLACK = -0x1.1c0052p125F;
    static { NAMED.put("almost black", -0x1.1c0052p125F); LIST.add(-0x1.1c0052p125F); }

    /**
     * This color constant "lead black" has RGBA8888 code {@code 242424FF}, H 0.16470589, S 0.0, L 0.13333334, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.440054p125F}.
     * <pre>
     * <font style='background-color: #242424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #242424; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #242424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #242424'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #242424'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #242424'>&nbsp;@&nbsp;</font><font style='background-color: #242424; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #242424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #242424; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLACK = -0x1.440054p125F;
    static { NAMED.put("lead black", -0x1.440054p125F); LIST.add(-0x1.440054p125F); }

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
     * This color constant "pure lead" has RGBA8888 code {@code 484848FF}, H 0.16470589, S 0.0, L 0.28235295, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.900054p125F}.
     * <pre>
     * <font style='background-color: #484848;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #484848; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #484848;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #484848'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #484848'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #484848'>&nbsp;@&nbsp;</font><font style='background-color: #484848; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #484848;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #484848; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_LEAD = -0x1.900054p125F;
    static { NAMED.put("pure lead", -0x1.900054p125F); LIST.add(-0x1.900054p125F); }

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
     * This color constant "silver gray" has RGBA8888 code {@code 919191FF}, H 0.16078432, S 0.0, L 0.5647059, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.200052p126F}.
     * <pre>
     * <font style='background-color: #919191;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #919191; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #919191;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #919191'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #919191'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #919191'>&nbsp;@&nbsp;</font><font style='background-color: #919191; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #919191;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #919191; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GRAY = -0x1.200052p126F;
    static { NAMED.put("silver gray", -0x1.200052p126F); LIST.add(-0x1.200052p126F); }

    /**
     * This color constant "gray silver" has RGBA8888 code {@code A3A3A3FF}, H 0.16078432, S 0.0, L 0.63529414, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.440052p126F}.
     * <pre>
     * <font style='background-color: #A3A3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3A3A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3A3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A3A3A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A3A3A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A3A3A3'>&nbsp;@&nbsp;</font><font style='background-color: #A3A3A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3A3A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3A3A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SILVER = -0x1.440052p126F;
    static { NAMED.put("gray silver", -0x1.440052p126F); LIST.add(-0x1.440052p126F); }

    /**
     * This color constant "pure silver" has RGBA8888 code {@code B6B6B6FF}, H 0.16078432, S 0.0, L 0.70980394, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.6a0052p126F}.
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_SILVER = -0x1.6a0052p126F;
    static { NAMED.put("pure silver", -0x1.6a0052p126F); LIST.add(-0x1.6a0052p126F); }

    /**
     * This color constant "white silver" has RGBA8888 code {@code C8C8C8FF}, H 0.16078432, S 0.0, L 0.78039217, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.8e0052p126F}.
     * <pre>
     * <font style='background-color: #C8C8C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8C8C8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8C8C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8C8C8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8C8C8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8C8C8'>&nbsp;@&nbsp;</font><font style='background-color: #C8C8C8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8C8C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8C8C8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SILVER = -0x1.8e0052p126F;
    static { NAMED.put("white silver", -0x1.8e0052p126F); LIST.add(-0x1.8e0052p126F); }

    /**
     * This color constant "silver white" has RGBA8888 code {@code DADADAFF}, H 0.16470589, S 0.0, L 0.85490197, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.b40054p126F}.
     * <pre>
     * <font style='background-color: #DADADA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DADADA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DADADA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DADADA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DADADA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DADADA'>&nbsp;@&nbsp;</font><font style='background-color: #DADADA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DADADA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DADADA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_WHITE = -0x1.b40054p126F;
    static { NAMED.put("silver white", -0x1.b40054p126F); LIST.add(-0x1.b40054p126F); }

    /**
     * This color constant "almost white" has RGBA8888 code {@code ECECECFF}, H 0.16470589, S 0.0, L 0.9254902, alpha 1.0, and chroma 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.d80054p126F}.
     * <pre>
     * <font style='background-color: #ECECEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECECEC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECECEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECECEC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECECEC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECECEC'>&nbsp;@&nbsp;</font><font style='background-color: #ECECEC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECECEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECECEC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_WHITE = -0x1.d80054p126F;
    static { NAMED.put("almost white", -0x1.d80054p126F); LIST.add(-0x1.d80054p126F); }

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
     * This color constant "black red" has RGBA8888 code {@code 704242FF}, H 0.03137255, S 0.29803923, L 0.30588236, alpha 1.0, and chroma 0.32684085.
     * It can be represented as a packed float with the constant {@code -0x1.9c981p125F}.
     * <pre>
     * <font style='background-color: #704242;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #704242; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #704242;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #704242'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #704242'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #704242'>&nbsp;@&nbsp;</font><font style='background-color: #704242; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #704242;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #704242; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_RED = -0x1.9c981p125F;
    static { NAMED.put("black red", -0x1.9c981p125F); LIST.add(-0x1.9c981p125F); }

    /**
     * This color constant "lead red" has RGBA8888 code {@code 935656FF}, H 0.03137255, S 0.3137255, L 0.40392157, alpha 1.0, and chroma 0.45225626.
     * It can be represented as a packed float with the constant {@code -0x1.cea01p125F}.
     * <pre>
     * <font style='background-color: #935656;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #935656; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #935656;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #935656'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #935656'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #935656'>&nbsp;@&nbsp;</font><font style='background-color: #935656; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #935656;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #935656; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_RED = -0x1.cea01p125F;
    static { NAMED.put("lead red", -0x1.cea01p125F); LIST.add(-0x1.cea01p125F); }

    /**
     * This color constant "gray red" has RGBA8888 code {@code AF7777FF}, H 0.03137255, S 0.21568628, L 0.52156866, alpha 1.0, and chroma 0.34343866.
     * It can be represented as a packed float with the constant {@code -0x1.0a6e1p126F}.
     * <pre>
     * <font style='background-color: #AF7777;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF7777; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF7777;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF7777'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF7777'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF7777'>&nbsp;@&nbsp;</font><font style='background-color: #AF7777; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF7777;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF7777; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_RED = -0x1.0a6e1p126F;
    static { NAMED.put("gray red", -0x1.0a6e1p126F); LIST.add(-0x1.0a6e1p126F); }

    /**
     * This color constant "silver red" has RGBA8888 code {@code C8A2A2FF}, H 0.03137255, S 0.23921569, L 0.6666667, alpha 1.0, and chroma 0.20580617.
     * It can be represented as a packed float with the constant {@code -0x1.547a1p126F}.
     * <pre>
     * <font style='background-color: #C8A2A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8A2A2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8A2A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8A2A2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8A2A2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8A2A2'>&nbsp;@&nbsp;</font><font style='background-color: #C8A2A2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8A2A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8A2A2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_RED = -0x1.547a1p126F;
    static { NAMED.put("silver red", -0x1.547a1p126F); LIST.add(-0x1.547a1p126F); }

    /**
     * This color constant "white red" has RGBA8888 code {@code E4D2D2FF}, H 0.03137255, S 0.26666668, L 0.8392157, alpha 1.0, and chroma 0.08925995.
     * It can be represented as a packed float with the constant {@code -0x1.ac881p126F}.
     * <pre>
     * <font style='background-color: #E4D2D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4D2D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4D2D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E4D2D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E4D2D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E4D2D2'>&nbsp;@&nbsp;</font><font style='background-color: #E4D2D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4D2D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4D2D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_RED = -0x1.ac881p126F;
    static { NAMED.put("white red", -0x1.ac881p126F); LIST.add(-0x1.ac881p126F); }

    /**
     * This color constant "black brown" has RGBA8888 code {@code 7E5E4EFF}, H 0.09411765, S 0.4117647, L 0.39607844, alpha 1.0, and chroma 0.2970848.
     * It can be represented as a packed float with the constant {@code -0x1.cad23p125F}.
     * <pre>
     * <font style='background-color: #7E5E4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E5E4E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E5E4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E5E4E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E5E4E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E5E4E'>&nbsp;@&nbsp;</font><font style='background-color: #7E5E4E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E5E4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E5E4E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BROWN = -0x1.cad23p125F;
    static { NAMED.put("black brown", -0x1.cad23p125F); LIST.add(-0x1.cad23p125F); }

    /**
     * This color constant "lead brown" has RGBA8888 code {@code A17B68FF}, H 0.09803922, S 0.38431373, L 0.5137255, alpha 1.0, and chroma 0.34573984.
     * It can be represented as a packed float with the constant {@code -0x1.06c432p126F}.
     * <pre>
     * <font style='background-color: #A17B68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A17B68; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A17B68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A17B68'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A17B68'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A17B68'>&nbsp;@&nbsp;</font><font style='background-color: #A17B68; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A17B68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A17B68; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BROWN = -0x1.06c432p126F;
    static { NAMED.put("lead brown", -0x1.06c432p126F); LIST.add(-0x1.06c432p126F); }

    /**
     * This color constant "gray brown" has RGBA8888 code {@code B69889FF}, H 0.101960786, S 0.25490198, L 0.6156863, alpha 1.0, and chroma 0.26426703.
     * It can be represented as a packed float with the constant {@code -0x1.3a8234p126F}.
     * <pre>
     * <font style='background-color: #B69889;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B69889; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B69889;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B69889'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B69889'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B69889'>&nbsp;@&nbsp;</font><font style='background-color: #B69889; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B69889;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B69889; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BROWN = -0x1.3a8234p126F;
    static { NAMED.put("gray brown", -0x1.3a8234p126F); LIST.add(-0x1.3a8234p126F); }

    /**
     * This color constant "silver brown" has RGBA8888 code {@code CBB6ABFF}, H 0.10980392, S 0.16078432, L 0.7254902, alpha 1.0, and chroma 0.1467772.
     * It can be represented as a packed float with the constant {@code -0x1.725238p126F}.
     * <pre>
     * <font style='background-color: #CBB6AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBB6AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBB6AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBB6AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBB6AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBB6AB'>&nbsp;@&nbsp;</font><font style='background-color: #CBB6AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBB6AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBB6AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BROWN = -0x1.725238p126F;
    static { NAMED.put("silver brown", -0x1.725238p126F); LIST.add(-0x1.725238p126F); }

    /**
     * This color constant "white brown" has RGBA8888 code {@code E5DAD5FF}, H 0.105882354, S 0.19607843, L 0.8627451, alpha 1.0, and chroma 0.07043544.
     * It can be represented as a packed float with the constant {@code -0x1.b86436p126F}.
     * <pre>
     * <font style='background-color: #E5DAD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5DAD5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5DAD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5DAD5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5DAD5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5DAD5'>&nbsp;@&nbsp;</font><font style='background-color: #E5DAD5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5DAD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5DAD5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BROWN = -0x1.b86436p126F;
    static { NAMED.put("white brown", -0x1.b86436p126F); LIST.add(-0x1.b86436p126F); }

    /**
     * This color constant "black orange" has RGBA8888 code {@code 786048FF}, H 0.13333334, S 0.49411765, L 0.39215687, alpha 1.0, and chroma 0.28182542.
     * It can be represented as a packed float with the constant {@code -0x1.c8fc44p125F}.
     * <pre>
     * <font style='background-color: #786048;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #786048; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #786048;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #786048'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #786048'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #786048'>&nbsp;@&nbsp;</font><font style='background-color: #786048; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #786048;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #786048; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_ORANGE = -0x1.c8fc44p125F;
    static { NAMED.put("black orange", -0x1.c8fc44p125F); LIST.add(-0x1.c8fc44p125F); }

    /**
     * This color constant "lead orange" has RGBA8888 code {@code 987A5CFF}, H 0.13333334, S 0.49411765, L 0.49803922, alpha 1.0, and chroma 0.3543605.
     * It can be represented as a packed float with the constant {@code -0x1.fefc44p125F}.
     * <pre>
     * <font style='background-color: #987A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #987A5C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #987A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #987A5C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #987A5C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #987A5C'>&nbsp;@&nbsp;</font><font style='background-color: #987A5C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #987A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #987A5C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_ORANGE = -0x1.fefc44p125F;
    static { NAMED.put("lead orange", -0x1.fefc44p125F); LIST.add(-0x1.fefc44p125F); }

    /**
     * This color constant "gray orange" has RGBA8888 code {@code B0967DFF}, H 0.13725491, S 0.3529412, L 0.6039216, alpha 1.0, and chroma 0.29794842.
     * It can be represented as a packed float with the constant {@code -0x1.34b446p126F}.
     * <pre>
     * <font style='background-color: #B0967D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0967D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0967D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0967D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0967D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0967D'>&nbsp;@&nbsp;</font><font style='background-color: #B0967D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0967D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0967D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_ORANGE = -0x1.34b446p126F;
    static { NAMED.put("gray orange", -0x1.34b446p126F); LIST.add(-0x1.34b446p126F); }

    /**
     * This color constant "silver orange" has RGBA8888 code {@code C8B7A5FF}, H 0.14901961, S 0.21176471, L 0.7254902, alpha 1.0, and chroma 0.20189132.
     * It can be represented as a packed float with the constant {@code -0x1.726c4cp126F}.
     * <pre>
     * <font style='background-color: #C8B7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8B7A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8B7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8B7A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8B7A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8B7A5'>&nbsp;@&nbsp;</font><font style='background-color: #C8B7A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8B7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8B7A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_ORANGE = -0x1.726c4cp126F;
    static { NAMED.put("silver orange", -0x1.726c4cp126F); LIST.add(-0x1.726c4cp126F); }

    /**
     * This color constant "white orange" has RGBA8888 code {@code E4DCD3FF}, H 0.15686275, S 0.14901961, L 0.8666667, alpha 1.0, and chroma 0.07690018.
     * It can be represented as a packed float with the constant {@code -0x1.ba4c5p126F}.
     * <pre>
     * <font style='background-color: #E4DCD3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4DCD3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4DCD3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E4DCD3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E4DCD3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E4DCD3'>&nbsp;@&nbsp;</font><font style='background-color: #E4DCD3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4DCD3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4DCD3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_ORANGE = -0x1.ba4c5p126F;
    static { NAMED.put("white orange", -0x1.ba4c5p126F); LIST.add(-0x1.ba4c5p126F); }

    /**
     * This color constant "black saffron" has RGBA8888 code {@code 7A6B56FF}, H 0.16078432, S 0.38431373, L 0.42745098, alpha 1.0, and chroma 0.21553072.
     * It can be represented as a packed float with the constant {@code -0x1.dac452p125F}.
     * <pre>
     * <font style='background-color: #7A6B56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A6B56; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A6B56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A6B56'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A6B56'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A6B56'>&nbsp;@&nbsp;</font><font style='background-color: #7A6B56; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A6B56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A6B56; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_SAFFRON = -0x1.dac452p125F;
    static { NAMED.put("black saffron", -0x1.dac452p125F); LIST.add(-0x1.dac452p125F); }

    /**
     * This color constant "lead saffron" has RGBA8888 code {@code 9D8D75FF}, H 0.16470589, S 0.3372549, L 0.56078434, alpha 1.0, and chroma 0.2418451.
     * It can be represented as a packed float with the constant {@code -0x1.1eac54p126F}.
     * <pre>
     * <font style='background-color: #9D8D75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D8D75; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D8D75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D8D75'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D8D75'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D8D75'>&nbsp;@&nbsp;</font><font style='background-color: #9D8D75; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D8D75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D8D75; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_SAFFRON = -0x1.1eac54p126F;
    static { NAMED.put("lead saffron", -0x1.1eac54p126F); LIST.add(-0x1.1eac54p126F); }

    /**
     * This color constant "gray saffron" has RGBA8888 code {@code B5A997FF}, H 0.16862746, S 0.21568628, L 0.6666667, alpha 1.0, and chroma 0.1794677.
     * It can be represented as a packed float with the constant {@code -0x1.546e56p126F}.
     * <pre>
     * <font style='background-color: #B5A997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5A997; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5A997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B5A997'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B5A997'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B5A997'>&nbsp;@&nbsp;</font><font style='background-color: #B5A997; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5A997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5A997; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SAFFRON = -0x1.546e56p126F;
    static { NAMED.put("gray saffron", -0x1.546e56p126F); LIST.add(-0x1.546e56p126F); }

    /**
     * This color constant "silver saffron" has RGBA8888 code {@code CBC2B6FF}, H 0.16470589, S 0.12941177, L 0.7607843, alpha 1.0, and chroma 0.12255663.
     * It can be represented as a packed float with the constant {@code -0x1.844254p126F}.
     * <pre>
     * <font style='background-color: #CBC2B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBC2B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBC2B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBC2B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBC2B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBC2B6'>&nbsp;@&nbsp;</font><font style='background-color: #CBC2B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBC2B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBC2B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_SAFFRON = -0x1.844254p126F;
    static { NAMED.put("silver saffron", -0x1.844254p126F); LIST.add(-0x1.844254p126F); }

    /**
     * This color constant "white saffron" has RGBA8888 code {@code E4DFD8FF}, H 0.16862746, S 0.101960786, L 0.8745098, alpha 1.0, and chroma 0.056057345.
     * It can be represented as a packed float with the constant {@code -0x1.be3456p126F}.
     * <pre>
     * <font style='background-color: #E4DFD8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4DFD8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4DFD8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E4DFD8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E4DFD8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E4DFD8'>&nbsp;@&nbsp;</font><font style='background-color: #E4DFD8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4DFD8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4DFD8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SAFFRON = -0x1.be3456p126F;
    static { NAMED.put("white saffron", -0x1.be3456p126F); LIST.add(-0x1.be3456p126F); }

    /**
     * This color constant "black yellow" has RGBA8888 code {@code 7E7E4AFF}, H 0.23921569, S 0.6627451, L 0.48235294, alpha 1.0, and chroma 0.37835714.
     * It can be represented as a packed float with the constant {@code -0x1.f7527ap125F}.
     * <pre>
     * <font style='background-color: #7E7E4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E7E4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E7E4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E7E4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E7E4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E7E4A'>&nbsp;@&nbsp;</font><font style='background-color: #7E7E4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E7E4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E7E4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_YELLOW = -0x1.f7527ap125F;
    static { NAMED.put("black yellow", -0x1.f7527ap125F); LIST.add(-0x1.f7527ap125F); }

    /**
     * This color constant "lead yellow" has RGBA8888 code {@code A2A261FF}, H 0.23921569, S 0.64705884, L 0.61960787, alpha 1.0, and chroma 0.4666986.
     * It can be represented as a packed float with the constant {@code -0x1.3d4a7ap126F}.
     * <pre>
     * <font style='background-color: #A2A261;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2A261; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2A261;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2A261'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2A261'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2A261'>&nbsp;@&nbsp;</font><font style='background-color: #A2A261; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2A261;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2A261; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_YELLOW = -0x1.3d4a7ap126F;
    static { NAMED.put("lead yellow", -0x1.3d4a7ap126F); LIST.add(-0x1.3d4a7ap126F); }

    /**
     * This color constant "gray yellow" has RGBA8888 code {@code B5B582FF}, H 0.23921569, S 0.47843137, L 0.69411767, alpha 1.0, and chroma 0.38270372.
     * It can be represented as a packed float with the constant {@code -0x1.62f47ap126F}.
     * <pre>
     * <font style='background-color: #B5B582;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5B582; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5B582;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B5B582'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B5B582'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B5B582'>&nbsp;@&nbsp;</font><font style='background-color: #B5B582; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5B582;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5B582; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_YELLOW = -0x1.62f47ap126F;
    static { NAMED.put("gray yellow", -0x1.62f47ap126F); LIST.add(-0x1.62f47ap126F); }

    /**
     * This color constant "silver yellow" has RGBA8888 code {@code CBCBA7FF}, H 0.23921569, S 0.30980393, L 0.78431374, alpha 1.0, and chroma 0.27648935.
     * It can be represented as a packed float with the constant {@code -0x1.909e7ap126F}.
     * <pre>
     * <font style='background-color: #CBCBA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBCBA7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBCBA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBCBA7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBCBA7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBCBA7'>&nbsp;@&nbsp;</font><font style='background-color: #CBCBA7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBCBA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBCBA7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_YELLOW = -0x1.909e7ap126F;
    static { NAMED.put("silver yellow", -0x1.909e7ap126F); LIST.add(-0x1.909e7ap126F); }

    /**
     * This color constant "white yellow" has RGBA8888 code {@code E5E5D3FF}, H 0.23921569, S 0.13725491, L 0.89411765, alpha 1.0, and chroma 0.1374453.
     * It can be represented as a packed float with the constant {@code -0x1.c8467ap126F}.
     * <pre>
     * <font style='background-color: #E5E5D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5E5D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5E5D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5E5D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5E5D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5E5D3'>&nbsp;@&nbsp;</font><font style='background-color: #E5E5D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5E5D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5E5D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_YELLOW = -0x1.c8467ap126F;
    static { NAMED.put("white yellow", -0x1.c8467ap126F); LIST.add(-0x1.c8467ap126F); }

    /**
     * This color constant "black lime" has RGBA8888 code {@code 647146FF}, H 0.28235295, S 0.5921569, L 0.42352942, alpha 1.0, and chroma 0.31440902.
     * It can be represented as a packed float with the constant {@code -0x1.d92e9p125F}.
     * <pre>
     * <font style='background-color: #647146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #647146; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #647146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #647146'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #647146'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #647146'>&nbsp;@&nbsp;</font><font style='background-color: #647146; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #647146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #647146; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LIME = -0x1.d92e9p125F;
    static { NAMED.put("black lime", -0x1.d92e9p125F); LIST.add(-0x1.d92e9p125F); }

    /**
     * This color constant "lead lime" has RGBA8888 code {@code 81925BFF}, H 0.28235295, S 0.5921569, L 0.54509807, alpha 1.0, and chroma 0.39945152.
     * It can be represented as a packed float with the constant {@code -0x1.172e9p126F}.
     * <pre>
     * <font style='background-color: #81925B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81925B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81925B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #81925B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #81925B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #81925B'>&nbsp;@&nbsp;</font><font style='background-color: #81925B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81925B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81925B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_LIME = -0x1.172e9p126F;
    static { NAMED.put("lead lime", -0x1.172e9p126F); LIST.add(-0x1.172e9p126F); }

    /**
     * This color constant "gray lime" has RGBA8888 code {@code 9EAD7CFF}, H 0.2784314, S 0.45882353, L 0.6509804, alpha 1.0, and chroma 0.3617619.
     * It can be represented as a packed float with the constant {@code -0x1.4cea8ep126F}.
     * <pre>
     * <font style='background-color: #9EAD7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9EAD7C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9EAD7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9EAD7C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9EAD7C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9EAD7C'>&nbsp;@&nbsp;</font><font style='background-color: #9EAD7C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9EAD7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9EAD7C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LIME = -0x1.4cea8ep126F;
    static { NAMED.put("gray lime", -0x1.4cea8ep126F); LIST.add(-0x1.4cea8ep126F); }

    /**
     * This color constant "silver lime" has RGBA8888 code {@code BCC7A5FF}, H 0.2784314, S 0.28235295, L 0.7607843, alpha 1.0, and chroma 0.256245.
     * It can be represented as a packed float with the constant {@code -0x1.84908ep126F}.
     * <pre>
     * <font style='background-color: #BCC7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCC7A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCC7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BCC7A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BCC7A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BCC7A5'>&nbsp;@&nbsp;</font><font style='background-color: #BCC7A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCC7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCC7A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_LIME = -0x1.84908ep126F;
    static { NAMED.put("silver lime", -0x1.84908ep126F); LIST.add(-0x1.84908ep126F); }

    /**
     * This color constant "white lime" has RGBA8888 code {@code DFE4D3FF}, H 0.27450982, S 0.12156863, L 0.88235295, alpha 1.0, and chroma 0.12482838.
     * It can be represented as a packed float with the constant {@code -0x1.c23e8cp126F}.
     * <pre>
     * <font style='background-color: #DFE4D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFE4D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFE4D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DFE4D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DFE4D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DFE4D3'>&nbsp;@&nbsp;</font><font style='background-color: #DFE4D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFE4D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFE4D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_LIME = -0x1.c23e8cp126F;
    static { NAMED.put("white lime", -0x1.c23e8cp126F); LIST.add(-0x1.c23e8cp126F); }

    /**
     * This color constant "black green" has RGBA8888 code {@code 4C824CFF}, H 0.3529412, S 0.59607846, L 0.45882353, alpha 1.0, and chroma 0.44993666.
     * It can be represented as a packed float with the constant {@code -0x1.eb30b4p125F}.
     * <pre>
     * <font style='background-color: #4C824C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C824C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C824C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4C824C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4C824C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4C824C'>&nbsp;@&nbsp;</font><font style='background-color: #4C824C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C824C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C824C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_GREEN = -0x1.eb30b4p125F;
    static { NAMED.put("black green", -0x1.eb30b4p125F); LIST.add(-0x1.eb30b4p125F); }

    /**
     * This color constant "lead green" has RGBA8888 code {@code 67A667FF}, H 0.3529412, S 0.54901963, L 0.5921569, alpha 1.0, and chroma 0.526622.
     * It can be represented as a packed float with the constant {@code -0x1.2f18b4p126F}.
     * <pre>
     * <font style='background-color: #67A667;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #67A667; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #67A667;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #67A667'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #67A667'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #67A667'>&nbsp;@&nbsp;</font><font style='background-color: #67A667; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #67A667;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #67A667; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GREEN = -0x1.2f18b4p126F;
    static { NAMED.put("lead green", -0x1.2f18b4p126F); LIST.add(-0x1.2f18b4p126F); }

    /**
     * This color constant "gray green" has RGBA8888 code {@code 8ABA8AFF}, H 0.3529412, S 0.37254903, L 0.6784314, alpha 1.0, and chroma 0.40475008.
     * It can be represented as a packed float with the constant {@code -0x1.5abeb4p126F}.
     * <pre>
     * <font style='background-color: #8ABA8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8ABA8A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8ABA8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8ABA8A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8ABA8A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8ABA8A'>&nbsp;@&nbsp;</font><font style='background-color: #8ABA8A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8ABA8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8ABA8A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.5abeb4p126F;
    static { NAMED.put("gray green", -0x1.5abeb4p126F); LIST.add(-0x1.5abeb4p126F); }

    /**
     * This color constant "silver green" has RGBA8888 code {@code ACCEACFF}, H 0.3529412, S 0.23529412, L 0.76862746, alpha 1.0, and chroma 0.28599003.
     * It can be represented as a packed float with the constant {@code -0x1.8878b4p126F}.
     * <pre>
     * <font style='background-color: #ACCEAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACCEAC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACCEAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ACCEAC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ACCEAC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ACCEAC'>&nbsp;@&nbsp;</font><font style='background-color: #ACCEAC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACCEAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACCEAC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.8878b4p126F;
    static { NAMED.put("silver green", -0x1.8878b4p126F); LIST.add(-0x1.8878b4p126F); }

    /**
     * This color constant "white green" has RGBA8888 code {@code D4E6D4FF}, H 0.3529412, S 0.10980392, L 0.88235295, alpha 1.0, and chroma 0.11873166.
     * It can be represented as a packed float with the constant {@code -0x1.c238b4p126F}.
     * <pre>
     * <font style='background-color: #D4E6D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4E6D4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4E6D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4E6D4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4E6D4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4E6D4'>&nbsp;@&nbsp;</font><font style='background-color: #D4E6D4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4E6D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4E6D4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_GREEN = -0x1.c238b4p126F;
    static { NAMED.put("white green", -0x1.c238b4p126F); LIST.add(-0x1.c238b4p126F); }

    /**
     * This color constant "black cyan" has RGBA8888 code {@code 4B7979FF}, H 0.53333336, S 0.627451, L 0.44313726, alpha 1.0, and chroma 0.23698114.
     * It can be represented as a packed float with the constant {@code -0x1.e3411p125F}.
     * <pre>
     * <font style='background-color: #4B7979;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B7979; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B7979;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B7979'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B7979'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B7979'>&nbsp;@&nbsp;</font><font style='background-color: #4B7979; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B7979;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B7979; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_CYAN = -0x1.e3411p125F;
    static { NAMED.put("black cyan", -0x1.e3411p125F); LIST.add(-0x1.e3411p125F); }

    /**
     * This color constant "lead cyan" has RGBA8888 code {@code 5F9A9AFF}, H 0.53333336, S 0.63529414, L 0.56078434, alpha 1.0, and chroma 0.2997011.
     * It can be represented as a packed float with the constant {@code -0x1.1f451p126F}.
     * <pre>
     * <font style='background-color: #5F9A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F9A9A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F9A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5F9A9A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5F9A9A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5F9A9A'>&nbsp;@&nbsp;</font><font style='background-color: #5F9A9A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F9A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F9A9A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_CYAN = -0x1.1f451p126F;
    static { NAMED.put("lead cyan", -0x1.1f451p126F); LIST.add(-0x1.1f451p126F); }

    /**
     * This color constant "gray cyan" has RGBA8888 code {@code 80B0B0FF}, H 0.53333336, S 0.4745098, L 0.654902, alpha 1.0, and chroma 0.2582473.
     * It can be represented as a packed float with the constant {@code -0x1.4ef31p126F}.
     * <pre>
     * <font style='background-color: #80B0B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80B0B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80B0B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #80B0B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #80B0B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #80B0B0'>&nbsp;@&nbsp;</font><font style='background-color: #80B0B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80B0B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80B0B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_CYAN = -0x1.4ef31p126F;
    static { NAMED.put("gray cyan", -0x1.4ef31p126F); LIST.add(-0x1.4ef31p126F); }

    /**
     * This color constant "silver cyan" has RGBA8888 code {@code A7C8C8FF}, H 0.53333336, S 0.29411766, L 0.75686276, alpha 1.0, and chroma 0.18239607.
     * It can be represented as a packed float with the constant {@code -0x1.82971p126F}.
     * <pre>
     * <font style='background-color: #A7C8C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7C8C8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7C8C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A7C8C8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A7C8C8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A7C8C8'>&nbsp;@&nbsp;</font><font style='background-color: #A7C8C8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7C8C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7C8C8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_CYAN = -0x1.82971p126F;
    static { NAMED.put("silver cyan", -0x1.82971p126F); LIST.add(-0x1.82971p126F); }

    /**
     * This color constant "white cyan" has RGBA8888 code {@code D4E4E4FF}, H 0.53333336, S 0.1254902, L 0.8784314, alpha 1.0, and chroma 0.08875606.
     * It can be represented as a packed float with the constant {@code -0x1.c0411p126F}.
     * <pre>
     * <font style='background-color: #D4E4E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4E4E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4E4E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4E4E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4E4E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4E4E4'>&nbsp;@&nbsp;</font><font style='background-color: #D4E4E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4E4E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4E4E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_CYAN = -0x1.c0411p126F;
    static { NAMED.put("white cyan", -0x1.c0411p126F); LIST.add(-0x1.c0411p126F); }

    /**
     * This color constant "black blue" has RGBA8888 code {@code 4D4D84FF}, H 0.7411765, S 0.3372549, L 0.32156864, alpha 1.0, and chroma 0.44794148.
     * It can be represented as a packed float with the constant {@code -0x1.a4ad7ap125F}.
     * <pre>
     * <font style='background-color: #4D4D84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4D4D84; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4D4D84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4D4D84'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4D4D84'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4D4D84'>&nbsp;@&nbsp;</font><font style='background-color: #4D4D84; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4D4D84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4D4D84; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BLUE = -0x1.a4ad7ap125F;
    static { NAMED.put("black blue", -0x1.a4ad7ap125F); LIST.add(-0x1.a4ad7ap125F); }

    /**
     * This color constant "lead blue" has RGBA8888 code {@code 6C6CA8FF}, H 0.7411765, S 0.38039216, L 0.44313726, alpha 1.0, and chroma 0.48171902.
     * It can be represented as a packed float with the constant {@code -0x1.e2c37ap125F}.
     * <pre>
     * <font style='background-color: #6C6CA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C6CA8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C6CA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C6CA8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C6CA8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C6CA8'>&nbsp;@&nbsp;</font><font style='background-color: #6C6CA8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C6CA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C6CA8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLUE = -0x1.e2c37ap125F;
    static { NAMED.put("lead blue", -0x1.e2c37ap125F); LIST.add(-0x1.e2c37ap125F); }

    /**
     * This color constant "gray blue" has RGBA8888 code {@code 9292BFFF}, H 0.7411765, S 0.34901962, L 0.58431375, alpha 1.0, and chroma 0.34287986.
     * It can be represented as a packed float with the constant {@code -0x1.2ab37ap126F}.
     * <pre>
     * <font style='background-color: #9292BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9292BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9292BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9292BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9292BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9292BF'>&nbsp;@&nbsp;</font><font style='background-color: #9292BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9292BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9292BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BLUE = -0x1.2ab37ap126F;
    static { NAMED.put("gray blue", -0x1.2ab37ap126F); LIST.add(-0x1.2ab37ap126F); }

    /**
     * This color constant "silver blue" has RGBA8888 code {@code B4B4D3FF}, H 0.7411765, S 0.3372549, L 0.7137255, alpha 1.0, and chroma 0.22293302.
     * It can be represented as a packed float with the constant {@code -0x1.6cad7ap126F}.
     * <pre>
     * <font style='background-color: #B4B4D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4B4D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4B4D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4B4D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4B4D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4B4D3'>&nbsp;@&nbsp;</font><font style='background-color: #B4B4D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4B4D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4B4D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BLUE = -0x1.6cad7ap126F;
    static { NAMED.put("silver blue", -0x1.6cad7ap126F); LIST.add(-0x1.6cad7ap126F); }

    /**
     * This color constant "white blue" has RGBA8888 code {@code D7D7E7FF}, H 0.7411765, S 0.32156864, L 0.84705883, alpha 1.0, and chroma 0.10818315.
     * It can be represented as a packed float with the constant {@code -0x1.b0a57ap126F}.
     * <pre>
     * <font style='background-color: #D7D7E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7D7E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7D7E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7D7E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7D7E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7D7E7'>&nbsp;@&nbsp;</font><font style='background-color: #D7D7E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7D7E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7D7E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BLUE = -0x1.b0a57ap126F;
    static { NAMED.put("white blue", -0x1.b0a57ap126F); LIST.add(-0x1.b0a57ap126F); }

    /**
     * This color constant "black violet" has RGBA8888 code {@code 634E7DFF}, H 0.78431374, S 0.3529412, L 0.34117648, alpha 1.0, and chroma 0.37878296.
     * It can be represented as a packed float with the constant {@code -0x1.aeb59p125F}.
     * <pre>
     * <font style='background-color: #634E7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #634E7D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #634E7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #634E7D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #634E7D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #634E7D'>&nbsp;@&nbsp;</font><font style='background-color: #634E7D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #634E7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #634E7D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_VIOLET = -0x1.aeb59p125F;
    static { NAMED.put("black violet", -0x1.aeb59p125F); LIST.add(-0x1.aeb59p125F); }

    /**
     * This color constant "lead violet" has RGBA8888 code {@code 8167A0FF}, H 0.78431374, S 0.3372549, L 0.44705883, alpha 1.0, and chroma 0.4415446.
     * It can be represented as a packed float with the constant {@code -0x1.e4ad9p125F}.
     * <pre>
     * <font style='background-color: #8167A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8167A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8167A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8167A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8167A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8167A0'>&nbsp;@&nbsp;</font><font style='background-color: #8167A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8167A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8167A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_VIOLET = -0x1.e4ad9p125F;
    static { NAMED.put("lead violet", -0x1.e4ad9p125F); LIST.add(-0x1.e4ad9p125F); }

    /**
     * This color constant "gray violet" has RGBA8888 code {@code 9C87B4FF}, H 0.7882353, S 0.29411766, L 0.56078434, alpha 1.0, and chroma 0.3206894.
     * It can be represented as a packed float with the constant {@code -0x1.1e9792p126F}.
     * <pre>
     * <font style='background-color: #9C87B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C87B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C87B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C87B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C87B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C87B4'>&nbsp;@&nbsp;</font><font style='background-color: #9C87B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C87B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C87B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_VIOLET = -0x1.1e9792p126F;
    static { NAMED.put("gray violet", -0x1.1e9792p126F); LIST.add(-0x1.1e9792p126F); }

    /**
     * This color constant "silver violet" has RGBA8888 code {@code B9AACAFF}, H 0.7921569, S 0.28235295, L 0.6862745, alpha 1.0, and chroma 0.21925068.
     * It can be represented as a packed float with the constant {@code -0x1.5e9194p126F}.
     * <pre>
     * <font style='background-color: #B9AACA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9AACA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9AACA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B9AACA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B9AACA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B9AACA'>&nbsp;@&nbsp;</font><font style='background-color: #B9AACA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9AACA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9AACA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_VIOLET = -0x1.5e9194p126F;
    static { NAMED.put("silver violet", -0x1.5e9194p126F); LIST.add(-0x1.5e9194p126F); }

    /**
     * This color constant "white violet" has RGBA8888 code {@code DCD4E4FF}, H 0.8, S 0.27450982, L 0.8392157, alpha 1.0, and chroma 0.10605622.
     * It can be represented as a packed float with the constant {@code -0x1.ac8d98p126F}.
     * <pre>
     * <font style='background-color: #DCD4E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCD4E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCD4E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DCD4E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DCD4E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DCD4E4'>&nbsp;@&nbsp;</font><font style='background-color: #DCD4E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCD4E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCD4E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_VIOLET = -0x1.ac8d98p126F;
    static { NAMED.put("white violet", -0x1.ac8d98p126F); LIST.add(-0x1.ac8d98p126F); }

    /**
     * This color constant "black purple" has RGBA8888 code {@code 6B4677FF}, H 0.8235294, S 0.44705883, L 0.32941177, alpha 1.0, and chroma 0.39260107.
     * It can be represented as a packed float with the constant {@code -0x1.a8e5a4p125F}.
     * <pre>
     * <font style='background-color: #6B4677;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B4677; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B4677;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6B4677'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6B4677'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6B4677'>&nbsp;@&nbsp;</font><font style='background-color: #6B4677; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B4677;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B4677; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_PURPLE = -0x1.a8e5a4p125F;
    static { NAMED.put("black purple", -0x1.a8e5a4p125F); LIST.add(-0x1.a8e5a4p125F); }

    /**
     * This color constant "lead purple" has RGBA8888 code {@code 895998FF}, H 0.8235294, S 0.4627451, L 0.41960785, alpha 1.0, and chroma 0.51483095.
     * It can be represented as a packed float with the constant {@code -0x1.d6eda4p125F}.
     * <pre>
     * <font style='background-color: #895998;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #895998; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #895998;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #895998'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #895998'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #895998'>&nbsp;@&nbsp;</font><font style='background-color: #895998; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #895998;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #895998; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_PURPLE = -0x1.d6eda4p125F;
    static { NAMED.put("lead purple", -0x1.d6eda4p125F); LIST.add(-0x1.d6eda4p125F); }

    /**
     * This color constant "gray purple" has RGBA8888 code {@code A37AB1FF}, H 0.8235294, S 0.3254902, L 0.53333336, alpha 1.0, and chroma 0.4148906.
     * It can be represented as a packed float with the constant {@code -0x1.10a7a4p126F}.
     * <pre>
     * <font style='background-color: #A37AB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A37AB1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A37AB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A37AB1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A37AB1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A37AB1'>&nbsp;@&nbsp;</font><font style='background-color: #A37AB1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A37AB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A37AB1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_PURPLE = -0x1.10a7a4p126F;
    static { NAMED.put("gray purple", -0x1.10a7a4p126F); LIST.add(-0x1.10a7a4p126F); }

    /**
     * This color constant "silver purple" has RGBA8888 code {@code C0A4C9FF}, H 0.827451, S 0.29411766, L 0.6784314, alpha 1.0, and chroma 0.2618262.
     * It can be represented as a packed float with the constant {@code -0x1.5a97a6p126F}.
     * <pre>
     * <font style='background-color: #C0A4C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0A4C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0A4C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0A4C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0A4C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0A4C9'>&nbsp;@&nbsp;</font><font style='background-color: #C0A4C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0A4C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0A4C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PURPLE = -0x1.5a97a6p126F;
    static { NAMED.put("silver purple", -0x1.5a97a6p126F); LIST.add(-0x1.5a97a6p126F); }

    /**
     * This color constant "white purple" has RGBA8888 code {@code E0D2E5FF}, H 0.827451, S 0.3019608, L 0.8392157, alpha 1.0, and chroma 0.12807381.
     * It can be represented as a packed float with the constant {@code -0x1.ac9ba6p126F}.
     * <pre>
     * <font style='background-color: #E0D2E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0D2E5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0D2E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0D2E5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0D2E5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0D2E5'>&nbsp;@&nbsp;</font><font style='background-color: #E0D2E5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0D2E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0D2E5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_PURPLE = -0x1.ac9ba6p126F;
    static { NAMED.put("white purple", -0x1.ac9ba6p126F); LIST.add(-0x1.ac9ba6p126F); }

    /**
     * This color constant "black magenta" has RGBA8888 code {@code 804F80FF}, H 0.85490197, S 0.4509804, L 0.3764706, alpha 1.0, and chroma 0.4185275.
     * It can be represented as a packed float with the constant {@code -0x1.c0e7b4p125F}.
     * <pre>
     * <font style='background-color: #804F80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #804F80; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #804F80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #804F80'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #804F80'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #804F80'>&nbsp;@&nbsp;</font><font style='background-color: #804F80; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #804F80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #804F80; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_MAGENTA = -0x1.c0e7b4p125F;
    static { NAMED.put("black magenta", -0x1.c0e7b4p125F); LIST.add(-0x1.c0e7b4p125F); }

    /**
     * This color constant "lead magenta" has RGBA8888 code {@code A46CA4FF}, H 0.85490197, S 0.4, L 0.49803922, alpha 1.0, and chroma 0.48568678.
     * It can be represented as a packed float with the constant {@code -0x1.fecdb4p125F}.
     * <pre>
     * <font style='background-color: #A46CA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A46CA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A46CA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A46CA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A46CA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A46CA4'>&nbsp;@&nbsp;</font><font style='background-color: #A46CA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A46CA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A46CA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_MAGENTA = -0x1.fecdb4p125F;
    static { NAMED.put("lead magenta", -0x1.fecdb4p125F); LIST.add(-0x1.fecdb4p125F); }

    /**
     * This color constant "gray magenta" has RGBA8888 code {@code BA90BAFF}, H 0.85490197, S 0.25882354, L 0.6156863, alpha 1.0, and chroma 0.31644607.
     * It can be represented as a packed float with the constant {@code -0x1.3a85b4p126F}.
     * <pre>
     * <font style='background-color: #BA90BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA90BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA90BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA90BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA90BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA90BA'>&nbsp;@&nbsp;</font><font style='background-color: #BA90BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA90BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA90BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_MAGENTA = -0x1.3a85b4p126F;
    static { NAMED.put("gray magenta", -0x1.3a85b4p126F); LIST.add(-0x1.3a85b4p126F); }

    /**
     * This color constant "silver magenta" has RGBA8888 code {@code CEB0CEFF}, H 0.85490197, S 0.25490198, L 0.72156864, alpha 1.0, and chroma 0.22235975.
     * It can be represented as a packed float with the constant {@code -0x1.7083b4p126F}.
     * <pre>
     * <font style='background-color: #CEB0CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEB0CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEB0CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEB0CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEB0CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEB0CE'>&nbsp;@&nbsp;</font><font style='background-color: #CEB0CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEB0CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEB0CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_MAGENTA = -0x1.7083b4p126F;
    static { NAMED.put("silver magenta", -0x1.7083b4p126F); LIST.add(-0x1.7083b4p126F); }

    /**
     * This color constant "white magenta" has RGBA8888 code {@code E5D6E5FF}, H 0.85490197, S 0.24313726, L 0.85490197, alpha 1.0, and chroma 0.10602963.
     * It can be represented as a packed float with the constant {@code -0x1.b47db4p126F}.
     * <pre>
     * <font style='background-color: #E5D6E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5D6E5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5D6E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5D6E5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5D6E5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5D6E5'>&nbsp;@&nbsp;</font><font style='background-color: #E5D6E5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5D6E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5D6E5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_MAGENTA = -0x1.b47db4p126F;
    static { NAMED.put("white magenta", -0x1.b47db4p126F); LIST.add(-0x1.b47db4p126F); }

    /**
     * This color constant "drab red" has RGBA8888 code {@code 943030FF}, H 0.03137255, S 0.6862745, L 0.3254902, alpha 1.0, and chroma 0.8006204.
     * It can be represented as a packed float with the constant {@code -0x1.a75e1p125F}.
     * <pre>
     * <font style='background-color: #943030;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #943030; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #943030;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #943030'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #943030'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #943030'>&nbsp;@&nbsp;</font><font style='background-color: #943030; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #943030;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #943030; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED = -0x1.a75e1p125F;
    static { NAMED.put("drab red", -0x1.a75e1p125F); LIST.add(-0x1.a75e1p125F); }

    /**
     * This color constant "faded red" has RGBA8888 code {@code C95D5DFF}, H 0.03137255, S 0.4862745, L 0.49411765, alpha 1.0, and chroma 0.85013.
     * It can be represented as a packed float with the constant {@code -0x1.fcf81p125F}.
     * <pre>
     * <font style='background-color: #C95D5D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C95D5D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C95D5D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C95D5D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C95D5D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C95D5D'>&nbsp;@&nbsp;</font><font style='background-color: #C95D5D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C95D5D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C95D5D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_RED = -0x1.fcf81p125F;
    static { NAMED.put("faded red", -0x1.fcf81p125F); LIST.add(-0x1.fcf81p125F); }

    /**
     * This color constant "pale red" has RGBA8888 code {@code E7B5B5FF}, H 0.03137255, S 0.48235294, L 0.7529412, alpha 1.0, and chroma 0.27385393.
     * It can be represented as a packed float with the constant {@code -0x1.80f61p126F}.
     * <pre>
     * <font style='background-color: #E7B5B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7B5B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7B5B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7B5B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7B5B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7B5B5'>&nbsp;@&nbsp;</font><font style='background-color: #E7B5B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7B5B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7B5B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED = -0x1.80f61p126F;
    static { NAMED.put("pale red", -0x1.80f61p126F); LIST.add(-0x1.80f61p126F); }

    /**
     * This color constant "drab brown" has RGBA8888 code {@code A4603DFF}, H 0.08235294, S 0.74509805, L 0.44313726, alpha 1.0, and chroma 0.65730083.
     * It can be represented as a packed float with the constant {@code -0x1.e37c2ap125F}.
     * <pre>
     * <font style='background-color: #A4603D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4603D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4603D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4603D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4603D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4603D'>&nbsp;@&nbsp;</font><font style='background-color: #A4603D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4603D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4603D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN = -0x1.e37c2ap125F;
    static { NAMED.put("drab brown", -0x1.e37c2ap125F); LIST.add(-0x1.e37c2ap125F); }

    /**
     * This color constant "faded brown" has RGBA8888 code {@code CB9174FF}, H 0.09411765, S 0.47843137, L 0.6156863, alpha 1.0, and chroma 0.5238033.
     * It can be represented as a packed float with the constant {@code -0x1.3af43p126F}.
     * <pre>
     * <font style='background-color: #CB9174;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB9174; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB9174;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB9174'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB9174'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB9174'>&nbsp;@&nbsp;</font><font style='background-color: #CB9174; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB9174;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB9174; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BROWN = -0x1.3af43p126F;
    static { NAMED.put("faded brown", -0x1.3af43p126F); LIST.add(-0x1.3af43p126F); }

    /**
     * This color constant "pale brown" has RGBA8888 code {@code E6CABCFF}, H 0.105882354, S 0.36078432, L 0.8117647, alpha 1.0, and chroma 0.19078095.
     * It can be represented as a packed float with the constant {@code -0x1.9eb836p126F}.
     * <pre>
     * <font style='background-color: #E6CABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6CABC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6CABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6CABC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6CABC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6CABC'>&nbsp;@&nbsp;</font><font style='background-color: #E6CABC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6CABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6CABC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN = -0x1.9eb836p126F;
    static { NAMED.put("pale brown", -0x1.9eb836p126F); LIST.add(-0x1.9eb836p126F); }

    /**
     * This color constant "drab orange" has RGBA8888 code {@code 9C6936FF}, H 0.11764706, S 0.8235294, L 0.45490196, alpha 1.0, and chroma 0.58518505.
     * It can be represented as a packed float with the constant {@code -0x1.e9a43cp125F}.
     * <pre>
     * <font style='background-color: #9C6936;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C6936; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C6936;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C6936'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C6936'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C6936'>&nbsp;@&nbsp;</font><font style='background-color: #9C6936; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C6936;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C6936; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE = -0x1.e9a43cp125F;
    static { NAMED.put("drab orange", -0x1.e9a43cp125F); LIST.add(-0x1.e9a43cp125F); }

    /**
     * This color constant "faded orange" has RGBA8888 code {@code C99664FF}, H 0.1254902, S 0.63529414, L 0.62352943, alpha 1.0, and chroma 0.5825286.
     * It can be represented as a packed float with the constant {@code -0x1.3f444p126F}.
     * <pre>
     * <font style='background-color: #C99664;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C99664; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C99664;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C99664'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C99664'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C99664'>&nbsp;@&nbsp;</font><font style='background-color: #C99664; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C99664;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C99664; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_ORANGE = -0x1.3f444p126F;
    static { NAMED.put("faded orange", -0x1.3f444p126F); LIST.add(-0x1.3f444p126F); }

    /**
     * This color constant "pale orange" has RGBA8888 code {@code E6CFB8FF}, H 0.14509805, S 0.32156864, L 0.8235294, alpha 1.0, and chroma 0.20995273.
     * It can be represented as a packed float with the constant {@code -0x1.a4a44ap126F}.
     * <pre>
     * <font style='background-color: #E6CFB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6CFB8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6CFB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6CFB8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6CFB8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6CFB8'>&nbsp;@&nbsp;</font><font style='background-color: #E6CFB8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6CFB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6CFB8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE = -0x1.a4a44ap126F;
    static { NAMED.put("pale orange", -0x1.a4a44ap126F); LIST.add(-0x1.a4a44ap126F); }

    /**
     * This color constant "drab saffron" has RGBA8888 code {@code 9A7B4DFF}, H 0.15294118, S 0.6666667, L 0.5019608, alpha 1.0, and chroma 0.4465878.
     * It can be represented as a packed float with the constant {@code -0x1.01544ep126F}.
     * <pre>
     * <font style='background-color: #9A7B4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A7B4D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A7B4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A7B4D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A7B4D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A7B4D'>&nbsp;@&nbsp;</font><font style='background-color: #9A7B4D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A7B4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A7B4D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON = -0x1.01544ep126F;
    static { NAMED.put("drab saffron", -0x1.01544ep126F); LIST.add(-0x1.01544ep126F); }

    /**
     * This color constant "faded saffron" has RGBA8888 code {@code C4AC89FF}, H 0.16078432, S 0.4, L 0.6862745, alpha 1.0, and chroma 0.34916553.
     * It can be represented as a packed float with the constant {@code -0x1.5ecc52p126F}.
     * <pre>
     * <font style='background-color: #C4AC89;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4AC89; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4AC89;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4AC89'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4AC89'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4AC89'>&nbsp;@&nbsp;</font><font style='background-color: #C4AC89; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4AC89;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4AC89; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_SAFFRON = -0x1.5ecc52p126F;
    static { NAMED.put("faded saffron", -0x1.5ecc52p126F); LIST.add(-0x1.5ecc52p126F); }

    /**
     * This color constant "pale saffron" has RGBA8888 code {@code E2D6C5FF}, H 0.16862746, S 0.1882353, L 0.84313726, alpha 1.0, and chroma 0.13719846.
     * It can be represented as a packed float with the constant {@code -0x1.ae6056p126F}.
     * <pre>
     * <font style='background-color: #E2D6C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2D6C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2D6C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2D6C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2D6C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2D6C5'>&nbsp;@&nbsp;</font><font style='background-color: #E2D6C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2D6C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2D6C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON = -0x1.ae6056p126F;
    static { NAMED.put("pale saffron", -0x1.ae6056p126F); LIST.add(-0x1.ae6056p126F); }

    /**
     * This color constant "drab yellow" has RGBA8888 code {@code A7A736FF}, H 0.23921569, S 0.92941177, L 0.6313726, alpha 1.0, and chroma 0.6820171.
     * It can be represented as a packed float with the constant {@code -0x1.43da7ap126F}.
     * <pre>
     * <font style='background-color: #A7A736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7A736; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7A736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A7A736'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A7A736'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A7A736'>&nbsp;@&nbsp;</font><font style='background-color: #A7A736; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7A736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7A736; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW = -0x1.43da7ap126F;
    static { NAMED.put("drab yellow", -0x1.43da7ap126F); LIST.add(-0x1.43da7ap126F); }

    /**
     * This color constant "faded yellow" has RGBA8888 code {@code CECE69FF}, H 0.23921569, S 0.7490196, L 0.78431374, alpha 1.0, and chroma 0.66847426.
     * It can be represented as a packed float with the constant {@code -0x1.917e7ap126F}.
     * <pre>
     * <font style='background-color: #CECE69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CECE69; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CECE69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CECE69'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CECE69'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CECE69'>&nbsp;@&nbsp;</font><font style='background-color: #CECE69; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CECE69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CECE69; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_YELLOW = -0x1.917e7ap126F;
    static { NAMED.put("faded yellow", -0x1.917e7ap126F); LIST.add(-0x1.917e7ap126F); }

    /**
     * This color constant "pale yellow" has RGBA8888 code {@code E7E7B8FF}, H 0.23921569, S 0.34901962, L 0.89411765, alpha 1.0, and chroma 0.34950376.
     * It can be represented as a packed float with the constant {@code -0x1.c8b27ap126F}.
     * <pre>
     * <font style='background-color: #E7E7B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7E7B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7E7B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7E7B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7E7B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7E7B8'>&nbsp;@&nbsp;</font><font style='background-color: #E7E7B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7E7B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7E7B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.c8b27ap126F;
    static { NAMED.put("pale yellow", -0x1.c8b27ap126F); LIST.add(-0x1.c8b27ap126F); }

    /**
     * This color constant "drab lime" has RGBA8888 code {@code 769236FF}, H 0.2901961, S 0.8862745, L 0.53333336, alpha 1.0, and chroma 0.5963347.
     * It can be represented as a packed float with the constant {@code -0x1.11c494p126F}.
     * <pre>
     * <font style='background-color: #769236;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #769236; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #769236;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #769236'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #769236'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #769236'>&nbsp;@&nbsp;</font><font style='background-color: #769236; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #769236;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #769236; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME = -0x1.11c494p126F;
    static { NAMED.put("drab lime", -0x1.11c494p126F); LIST.add(-0x1.11c494p126F); }

    /**
     * This color constant "faded lime" has RGBA8888 code {@code A7C564FF}, H 0.28627452, S 0.7372549, L 0.7254902, alpha 1.0, and chroma 0.6518948.
     * It can be represented as a packed float with the constant {@code -0x1.737892p126F}.
     * <pre>
     * <font style='background-color: #A7C564;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7C564; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7C564;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A7C564'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A7C564'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A7C564'>&nbsp;@&nbsp;</font><font style='background-color: #A7C564; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7C564;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7C564; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_LIME = -0x1.737892p126F;
    static { NAMED.put("faded lime", -0x1.737892p126F); LIST.add(-0x1.737892p126F); }

    /**
     * This color constant "pale lime" has RGBA8888 code {@code D7E4B8FF}, H 0.27450982, S 0.3137255, L 0.87058824, alpha 1.0, and chroma 0.3183875.
     * It can be represented as a packed float with the constant {@code -0x1.bca08cp126F}.
     * <pre>
     * <font style='background-color: #D7E4B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7E4B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7E4B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7E4B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7E4B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7E4B8'>&nbsp;@&nbsp;</font><font style='background-color: #D7E4B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7E4B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7E4B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME = -0x1.bca08cp126F;
    static { NAMED.put("pale lime", -0x1.bca08cp126F); LIST.add(-0x1.bca08cp126F); }

    /**
     * This color constant "drab green" has RGBA8888 code {@code 38AC38FF}, H 0.3529412, S 0.9019608, L 0.5882353, alpha 1.0, and chroma 0.8598661.
     * It can be represented as a packed float with the constant {@code -0x1.2dccb4p126F}.
     * <pre>
     * <font style='background-color: #38AC38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38AC38; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38AC38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #38AC38'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #38AC38'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #38AC38'>&nbsp;@&nbsp;</font><font style='background-color: #38AC38; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38AC38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38AC38; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.2dccb4p126F;
    static { NAMED.put("drab green", -0x1.2dccb4p126F); LIST.add(-0x1.2dccb4p126F); }

    /**
     * This color constant "faded green" has RGBA8888 code {@code 74D174FF}, H 0.3529412, S 0.6392157, L 0.7372549, alpha 1.0, and chroma 0.7485415.
     * It can be represented as a packed float with the constant {@code -0x1.7946b4p126F}.
     * <pre>
     * <font style='background-color: #74D174;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #74D174; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #74D174;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #74D174'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #74D174'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #74D174'>&nbsp;@&nbsp;</font><font style='background-color: #74D174; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #74D174;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #74D174; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_GREEN = -0x1.7946b4p126F;
    static { NAMED.put("faded green", -0x1.7946b4p126F); LIST.add(-0x1.7946b4p126F); }

    /**
     * This color constant "pale green" has RGBA8888 code {@code BBE8BBFF}, H 0.3529412, S 0.27450982, L 0.8627451, alpha 1.0, and chroma 0.3647928.
     * It can be represented as a packed float with the constant {@code -0x1.b88cb4p126F}.
     * <pre>
     * <font style='background-color: #BBE8BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBE8BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBE8BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BBE8BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BBE8BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BBE8BB'>&nbsp;@&nbsp;</font><font style='background-color: #BBE8BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBE8BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBE8BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.b88cb4p126F;
    static { NAMED.put("pale green", -0x1.b88cb4p126F); LIST.add(-0x1.b88cb4p126F); }

    /**
     * This color constant "drab cyan" has RGBA8888 code {@code 3A9D9DFF}, H 0.53333336, S 0.90588236, L 0.5568628, alpha 1.0, and chroma 0.4245681.
     * It can be represented as a packed float with the constant {@code -0x1.1dcf1p126F}.
     * <pre>
     * <font style='background-color: #3A9D9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A9D9D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A9D9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A9D9D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A9D9D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A9D9D'>&nbsp;@&nbsp;</font><font style='background-color: #3A9D9D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A9D9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A9D9D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN = -0x1.1dcf1p126F;
    static { NAMED.put("drab cyan", -0x1.1dcf1p126F); LIST.add(-0x1.1dcf1p126F); }

    /**
     * This color constant "faded cyan" has RGBA8888 code {@code 69C7C7FF}, H 0.53333336, S 0.7411765, L 0.7176471, alpha 1.0, and chroma 0.4382323.
     * It can be represented as a packed float with the constant {@code -0x1.6f7b1p126F}.
     * <pre>
     * <font style='background-color: #69C7C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #69C7C7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #69C7C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #69C7C7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #69C7C7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #69C7C7'>&nbsp;@&nbsp;</font><font style='background-color: #69C7C7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #69C7C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #69C7C7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_CYAN = -0x1.6f7b1p126F;
    static { NAMED.put("faded cyan", -0x1.6f7b1p126F); LIST.add(-0x1.6f7b1p126F); }

    /**
     * This color constant "pale cyan" has RGBA8888 code {@code B9E5E5FF}, H 0.53333336, S 0.3372549, L 0.8627451, alpha 1.0, and chroma 0.2348075.
     * It can be represented as a packed float with the constant {@code -0x1.b8ad1p126F}.
     * <pre>
     * <font style='background-color: #B9E5E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9E5E5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9E5E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B9E5E5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B9E5E5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B9E5E5'>&nbsp;@&nbsp;</font><font style='background-color: #B9E5E5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9E5E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9E5E5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN = -0x1.b8ad1p126F;
    static { NAMED.put("pale cyan", -0x1.b8ad1p126F); LIST.add(-0x1.b8ad1p126F); }

    /**
     * This color constant "drab blue" has RGBA8888 code {@code 3939AFFF}, H 0.7411765, S 0.7294118, L 0.2901961, alpha 1.0, and chroma 0.9062672.
     * It can be represented as a packed float with the constant {@code -0x1.95757ap125F}.
     * <pre>
     * <font style='background-color: #3939AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3939AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3939AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3939AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3939AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3939AF'>&nbsp;@&nbsp;</font><font style='background-color: #3939AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3939AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3939AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE = -0x1.95757ap125F;
    static { NAMED.put("drab blue", -0x1.95757ap125F); LIST.add(-0x1.95757ap125F); }

    /**
     * This color constant "faded blue" has RGBA8888 code {@code 7D7DD4FF}, H 0.7411765, S 0.60784316, L 0.52156866, alpha 1.0, and chroma 0.6835757.
     * It can be represented as a packed float with the constant {@code -0x1.0b377ap126F}.
     * <pre>
     * <font style='background-color: #7D7DD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D7DD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D7DD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7D7DD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7D7DD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7D7DD4'>&nbsp;@&nbsp;</font><font style='background-color: #7D7DD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D7DD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D7DD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.0b377ap126F;
    static { NAMED.put("faded blue", -0x1.0b377ap126F); LIST.add(-0x1.0b377ap126F); }

    /**
     * This color constant "pale blue" has RGBA8888 code {@code C0C0EAFF}, H 0.7411765, S 0.56078434, L 0.7647059, alpha 1.0, and chroma 0.29963398.
     * It can be represented as a packed float with the constant {@code -0x1.871f7ap126F}.
     * <pre>
     * <font style='background-color: #C0C0EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0C0EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0C0EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0C0EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0C0EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0C0EA'>&nbsp;@&nbsp;</font><font style='background-color: #C0C0EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0C0EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0C0EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.871f7ap126F;
    static { NAMED.put("pale blue", -0x1.871f7ap126F); LIST.add(-0x1.871f7ap126F); }

    /**
     * This color constant "drab violet" has RGBA8888 code {@code 6C3DA3FF}, H 0.7764706, S 0.70980394, L 0.33333334, alpha 1.0, and chroma 0.77836907.
     * It can be represented as a packed float with the constant {@code -0x1.ab6b8cp125F}.
     * <pre>
     * <font style='background-color: #6C3DA3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C3DA3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C3DA3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C3DA3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C3DA3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C3DA3'>&nbsp;@&nbsp;</font><font style='background-color: #6C3DA3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C3DA3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C3DA3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET = -0x1.ab6b8cp125F;
    static { NAMED.put("drab violet", -0x1.ab6b8cp125F); LIST.add(-0x1.ab6b8cp125F); }

    /**
     * This color constant "faded violet" has RGBA8888 code {@code 9A72CAFF}, H 0.78431374, S 0.5411765, L 0.5176471, alpha 1.0, and chroma 0.63836634.
     * It can be represented as a packed float with the constant {@code -0x1.09159p126F}.
     * <pre>
     * <font style='background-color: #9A72CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A72CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A72CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A72CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A72CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A72CA'>&nbsp;@&nbsp;</font><font style='background-color: #9A72CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A72CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A72CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_VIOLET = -0x1.09159p126F;
    static { NAMED.put("faded violet", -0x1.09159p126F); LIST.add(-0x1.09159p126F); }

    /**
     * This color constant "pale violet" has RGBA8888 code {@code CEBBE5FF}, H 0.7882353, S 0.49411765, L 0.7607843, alpha 1.0, and chroma 0.28395167.
     * It can be represented as a packed float with the constant {@code -0x1.84fd92p126F}.
     * <pre>
     * <font style='background-color: #CEBBE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEBBE5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEBBE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEBBE5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEBBE5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEBBE5'>&nbsp;@&nbsp;</font><font style='background-color: #CEBBE5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEBBE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEBBE5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.84fd92p126F;
    static { NAMED.put("pale violet", -0x1.84fd92p126F); LIST.add(-0x1.84fd92p126F); }

    /**
     * This color constant "drab purple" has RGBA8888 code {@code 82339BFF}, H 0.8156863, S 0.8156863, L 0.34117648, alpha 1.0, and chroma 0.7614331.
     * It can be represented as a packed float with the constant {@code -0x1.afa1ap125F}.
     * <pre>
     * <font style='background-color: #82339B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82339B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82339B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #82339B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #82339B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #82339B'>&nbsp;@&nbsp;</font><font style='background-color: #82339B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82339B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82339B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE = -0x1.afa1ap125F;
    static { NAMED.put("drab purple", -0x1.afa1ap125F); LIST.add(-0x1.afa1ap125F); }

    /**
     * This color constant "faded purple" has RGBA8888 code {@code B061CBFF}, H 0.81960785, S 0.6156863, L 0.5058824, alpha 1.0, and chroma 0.8128705.
     * It can be represented as a packed float with the constant {@code -0x1.033ba2p126F}.
     * <pre>
     * <font style='background-color: #B061CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B061CB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B061CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B061CB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B061CB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B061CB'>&nbsp;@&nbsp;</font><font style='background-color: #B061CB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B061CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B061CB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_PURPLE = -0x1.033ba2p126F;
    static { NAMED.put("faded purple", -0x1.033ba2p126F); LIST.add(-0x1.033ba2p126F); }

    /**
     * This color constant "pale purple" has RGBA8888 code {@code DBB6E7FF}, H 0.827451, S 0.5254902, L 0.7607843, alpha 1.0, and chroma 0.34071812.
     * It can be represented as a packed float with the constant {@code -0x1.850da6p126F}.
     * <pre>
     * <font style='background-color: #DBB6E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBB6E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBB6E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBB6E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBB6E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBB6E7'>&nbsp;@&nbsp;</font><font style='background-color: #DBB6E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBB6E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBB6E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.850da6p126F;
    static { NAMED.put("pale purple", -0x1.850da6p126F); LIST.add(-0x1.850da6p126F); }

    /**
     * This color constant "drab magenta" has RGBA8888 code {@code A73EA7FF}, H 0.85490197, S 0.8, L 0.41568628, alpha 1.0, and chroma 0.8173837.
     * It can be represented as a packed float with the constant {@code -0x1.d599b4p125F}.
     * <pre>
     * <font style='background-color: #A73EA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A73EA7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A73EA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A73EA7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A73EA7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A73EA7'>&nbsp;@&nbsp;</font><font style='background-color: #A73EA7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A73EA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A73EA7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA = -0x1.d599b4p125F;
    static { NAMED.put("drab magenta", -0x1.d599b4p125F); LIST.add(-0x1.d599b4p125F); }

    /**
     * This color constant "faded magenta" has RGBA8888 code {@code CE7CCEFF}, H 0.85490197, S 0.48235294, L 0.6, alpha 1.0, and chroma 0.61384356.
     * It can be represented as a packed float with the constant {@code -0x1.32f7b4p126F}.
     * <pre>
     * <font style='background-color: #CE7CCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE7CCE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE7CCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE7CCE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE7CCE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE7CCE'>&nbsp;@&nbsp;</font><font style='background-color: #CE7CCE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE7CCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE7CCE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_MAGENTA = -0x1.32f7b4p126F;
    static { NAMED.put("faded magenta", -0x1.32f7b4p126F); LIST.add(-0x1.32f7b4p126F); }

    /**
     * This color constant "pale magenta" has RGBA8888 code {@code E7BEE7FF}, H 0.85490197, S 0.46666667, L 0.7921569, alpha 1.0, and chroma 0.29786593.
     * It can be represented as a packed float with the constant {@code -0x1.94efb4p126F}.
     * <pre>
     * <font style='background-color: #E7BEE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7BEE7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7BEE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7BEE7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7BEE7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7BEE7'>&nbsp;@&nbsp;</font><font style='background-color: #E7BEE7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7BEE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7BEE7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA = -0x1.94efb4p126F;
    static { NAMED.put("pale magenta", -0x1.94efb4p126F); LIST.add(-0x1.94efb4p126F); }

    /**
     * This color constant "deep pure red" has RGBA8888 code {@code AC1818FF}, H 0.03137255, S 0.972549, L 0.34117648, alpha 1.0, and chroma 1.1886889.
     * It can be represented as a packed float with the constant {@code -0x1.aff01p125F}.
     * <pre>
     * <font style='background-color: #AC1818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC1818; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC1818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC1818'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC1818'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC1818'>&nbsp;@&nbsp;</font><font style='background-color: #AC1818; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC1818;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC1818; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_RED = -0x1.aff01p125F;
    static { NAMED.put("deep pure red", -0x1.aff01p125F); LIST.add(-0x1.aff01p125F); }

    /**
     * This color constant "true pure red" has RGBA8888 code {@code E44242FF}, H 0.03137255, S 0.76862746, L 0.49019608, alpha 1.0, and chroma 1.3336627.
     * It can be represented as a packed float with the constant {@code -0x1.fb881p125F}.
     * <pre>
     * <font style='background-color: #E44242;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E44242; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E44242;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E44242'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E44242'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E44242'>&nbsp;@&nbsp;</font><font style='background-color: #E44242; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E44242;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E44242; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_RED = -0x1.fb881p125F;
    static { NAMED.put("true pure red", -0x1.fb881p125F); LIST.add(-0x1.fb881p125F); }

    /**
     * This color constant "bright pure red" has RGBA8888 code {@code F3A9A9FF}, H 0.03137255, S 0.6784314, L 0.73333335, alpha 1.0, and chroma 0.42613864.
     * It can be represented as a packed float with the constant {@code -0x1.775a1p126F}.
     * <pre>
     * <font style='background-color: #F3A9A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3A9A9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3A9A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3A9A9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3A9A9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3A9A9'>&nbsp;@&nbsp;</font><font style='background-color: #F3A9A9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3A9A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3A9A9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_RED = -0x1.775a1p126F;
    static { NAMED.put("bright pure red", -0x1.775a1p126F); LIST.add(-0x1.775a1p126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code BD3524FF}, H 0.039215688, S 0.9254902, L 0.4, alpha 1.0, and chroma 1.2307856.
     * It can be represented as a packed float with the constant {@code -0x1.cdd814p125F}.
     * <pre>
     * <font style='background-color: #BD3524;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD3524; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD3524;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD3524'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD3524'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD3524'>&nbsp;@&nbsp;</font><font style='background-color: #BD3524; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD3524;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD3524; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.cdd814p125F;
    static { NAMED.put("deep brown red", -0x1.cdd814p125F); LIST.add(-0x1.cdd814p125F); }

    /**
     * This color constant "true brown red" has RGBA8888 code {@code E06D5FFF}, H 0.047058824, S 0.5882353, L 0.56078434, alpha 1.0, and chroma 0.8480678.
     * It can be represented as a packed float with the constant {@code -0x1.1f2c18p126F}.
     * <pre>
     * <font style='background-color: #E06D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E06D5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E06D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E06D5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E06D5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E06D5F'>&nbsp;@&nbsp;</font><font style='background-color: #E06D5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E06D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E06D5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_RED = -0x1.1f2c18p126F;
    static { NAMED.put("true brown red", -0x1.1f2c18p126F); LIST.add(-0x1.1f2c18p126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code F0B9B2FF}, H 0.050980393, S 0.6, L 0.77254903, alpha 1.0, and chroma 0.32095426.
     * It can be represented as a packed float with the constant {@code -0x1.8b321ap126F}.
     * <pre>
     * <font style='background-color: #F0B9B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0B9B2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0B9B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0B9B2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0B9B2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0B9B2'>&nbsp;@&nbsp;</font><font style='background-color: #F0B9B2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0B9B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0B9B2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.8b321ap126F;
    static { NAMED.put("bright brown red", -0x1.8b321ap126F); LIST.add(-0x1.8b321ap126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code B83D1AFF}, H 0.047058824, S 0.9882353, L 0.40392157, alpha 1.0, and chroma 1.1766982.
     * It can be represented as a packed float with the constant {@code -0x1.cff818p125F}.
     * <pre>
     * <font style='background-color: #B83D1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B83D1A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B83D1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B83D1A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B83D1A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B83D1A'>&nbsp;@&nbsp;</font><font style='background-color: #B83D1A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B83D1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B83D1A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.cff818p125F;
    static { NAMED.put("deep red brown", -0x1.cff818p125F); LIST.add(-0x1.cff818p125F); }

    /**
     * This color constant "true red brown" has RGBA8888 code {@code E56B48FF}, H 0.05882353, S 0.7882353, L 0.56078434, alpha 1.0, and chroma 1.1000422.
     * It can be represented as a packed float with the constant {@code -0x1.1f921ep126F}.
     * <pre>
     * <font style='background-color: #E56B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E56B48; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E56B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E56B48'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E56B48'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E56B48'>&nbsp;@&nbsp;</font><font style='background-color: #E56B48; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E56B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E56B48; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_BROWN = -0x1.1f921ep126F;
    static { NAMED.put("true red brown", -0x1.1f921ep126F); LIST.add(-0x1.1f921ep126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code F3BBABFF}, H 0.07450981, S 0.6392157, L 0.78039217, alpha 1.0, and chroma 0.3540078.
     * It can be represented as a packed float with the constant {@code -0x1.8f4626p126F}.
     * <pre>
     * <font style='background-color: #F3BBAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3BBAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3BBAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3BBAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3BBAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3BBAB'>&nbsp;@&nbsp;</font><font style='background-color: #F3BBAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3BBAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3BBAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.8f4626p126F;
    static { NAMED.put("bright red brown", -0x1.8f4626p126F); LIST.add(-0x1.8f4626p126F); }

    /**
     * This color constant "deep pure brown" has RGBA8888 code {@code C25925FF}, H 0.06666667, S 0.95686275, L 0.47058824, alpha 1.0, and chroma 1.0372597.
     * It can be represented as a packed float with the constant {@code -0x1.f1e822p125F}.
     * <pre>
     * <font style='background-color: #C25925;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C25925; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C25925;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C25925'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C25925'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C25925'>&nbsp;@&nbsp;</font><font style='background-color: #C25925; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C25925;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C25925; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BROWN = -0x1.f1e822p125F;
    static { NAMED.put("deep pure brown", -0x1.f1e822p125F); LIST.add(-0x1.f1e822p125F); }

    /**
     * This color constant "true pure brown" has RGBA8888 code {@code E2926AFF}, H 0.08627451, S 0.6156863, L 0.64705884, alpha 1.0, and chroma 0.7277214.
     * It can be represented as a packed float with the constant {@code -0x1.4b3a2cp126F}.
     * <pre>
     * <font style='background-color: #E2926A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2926A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2926A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2926A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2926A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2926A'>&nbsp;@&nbsp;</font><font style='background-color: #E2926A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2926A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2926A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BROWN = -0x1.4b3a2cp126F;
    static { NAMED.put("true pure brown", -0x1.4b3a2cp126F); LIST.add(-0x1.4b3a2cp126F); }

    /**
     * This color constant "bright pure brown" has RGBA8888 code {@code F1C9B6FF}, H 0.09803922, S 0.5568628, L 0.81960785, alpha 1.0, and chroma 0.2672265.
     * It can be represented as a packed float with the constant {@code -0x1.a31c32p126F}.
     * <pre>
     * <font style='background-color: #F1C9B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1C9B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1C9B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F1C9B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F1C9B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F1C9B6'>&nbsp;@&nbsp;</font><font style='background-color: #F1C9B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1C9B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1C9B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BROWN = -0x1.a31c32p126F;
    static { NAMED.put("bright pure brown", -0x1.a31c32p126F); LIST.add(-0x1.a31c32p126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code C25C1BFF}, H 0.07450981, S 1.0, L 0.4745098, alpha 1.0, and chroma 1.0102981.
     * It can be represented as a packed float with the constant {@code -0x1.f3fe26p125F}.
     * <pre>
     * <font style='background-color: #C25C1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C25C1B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C25C1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C25C1B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C25C1B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C25C1B'>&nbsp;@&nbsp;</font><font style='background-color: #C25C1B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C25C1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C25C1B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.f3fe26p125F;
    static { NAMED.put("deep orange brown", -0x1.f3fe26p125F); LIST.add(-0x1.f3fe26p125F); }

    /**
     * This color constant "true orange brown" has RGBA8888 code {@code E68B51FF}, H 0.09019608, S 0.7882353, L 0.6313726, alpha 1.0, and chroma 0.9094785.
     * It can be represented as a packed float with the constant {@code -0x1.43922ep126F}.
     * <pre>
     * <font style='background-color: #E68B51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E68B51; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E68B51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E68B51'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E68B51'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E68B51'>&nbsp;@&nbsp;</font><font style='background-color: #E68B51; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E68B51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E68B51; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_BROWN = -0x1.43922ep126F;
    static { NAMED.put("true orange brown", -0x1.43922ep126F); LIST.add(-0x1.43922ep126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code F3C8ADFF}, H 0.11372549, S 0.6, L 0.8156863, alpha 1.0, and chroma 0.3239985.
     * It can be represented as a packed float with the constant {@code -0x1.a1323ap126F}.
     * <pre>
     * <font style='background-color: #F3C8AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3C8AD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3C8AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3C8AD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3C8AD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3C8AD'>&nbsp;@&nbsp;</font><font style='background-color: #F3C8AD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3C8AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3C8AD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.a1323ap126F;
    static { NAMED.put("bright orange brown", -0x1.a1323ap126F); LIST.add(-0x1.a1323ap126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code A95D20FF}, H 0.09019608, S 0.972549, L 0.4392157, alpha 1.0, and chroma 0.79838985.
     * It can be represented as a packed float with the constant {@code -0x1.e1f02ep125F}.
     * <pre>
     * <font style='background-color: #A95D20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A95D20; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A95D20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A95D20'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A95D20'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A95D20'>&nbsp;@&nbsp;</font><font style='background-color: #A95D20; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A95D20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A95D20; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.e1f02ep125F;
    static { NAMED.put("deep brown orange", -0x1.e1f02ep125F); LIST.add(-0x1.e1f02ep125F); }

    /**
     * This color constant "true brown orange" has RGBA8888 code {@code DC8C4CFF}, H 0.101960786, S 0.8117647, L 0.61960787, alpha 1.0, and chroma 0.8465138.
     * It can be represented as a packed float with the constant {@code -0x1.3d9e34p126F}.
     * <pre>
     * <font style='background-color: #DC8C4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC8C4C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC8C4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC8C4C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC8C4C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC8C4C'>&nbsp;@&nbsp;</font><font style='background-color: #DC8C4C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC8C4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC8C4C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_ORANGE = -0x1.3d9e34p126F;
    static { NAMED.put("true brown orange", -0x1.3d9e34p126F); LIST.add(-0x1.3d9e34p126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code EFCBAEFF}, H 0.1254902, S 0.50980395, L 0.81960785, alpha 1.0, and chroma 0.29039806.
     * It can be represented as a packed float with the constant {@code -0x1.a3044p126F}.
     * <pre>
     * <font style='background-color: #EFCBAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFCBAE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFCBAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFCBAE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFCBAE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFCBAE'>&nbsp;@&nbsp;</font><font style='background-color: #EFCBAE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFCBAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFCBAE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.a3044p126F;
    static { NAMED.put("bright brown orange", -0x1.a3044p126F); LIST.add(-0x1.a3044p126F); }

    /**
     * This color constant "deep pure orange" has RGBA8888 code {@code C47220FF}, H 0.09803922, S 0.99607843, L 0.5254902, alpha 1.0, and chroma 0.91537267.
     * It can be represented as a packed float with the constant {@code -0x1.0dfc32p126F}.
     * <pre>
     * <font style='background-color: #C47220;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C47220; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C47220;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C47220'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C47220'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C47220'>&nbsp;@&nbsp;</font><font style='background-color: #C47220; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C47220;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C47220; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_ORANGE = -0x1.0dfc32p126F;
    static { NAMED.put("deep pure orange", -0x1.0dfc32p126F); LIST.add(-0x1.0dfc32p126F); }

    /**
     * This color constant "true pure orange" has RGBA8888 code {@code E5A260FF}, H 0.12156863, S 0.73333335, L 0.6862745, alpha 1.0, and chroma 0.74844605.
     * It can be represented as a packed float with the constant {@code -0x1.5f763ep126F}.
     * <pre>
     * <font style='background-color: #E5A260;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5A260; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5A260;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5A260'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5A260'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5A260'>&nbsp;@&nbsp;</font><font style='background-color: #E5A260; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5A260;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5A260; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_ORANGE = -0x1.5f763ep126F;
    static { NAMED.put("true pure orange", -0x1.5f763ep126F); LIST.add(-0x1.5f763ep126F); }

    /**
     * This color constant "bright pure orange" has RGBA8888 code {@code F2D1B1FF}, H 0.13725491, S 0.5372549, L 0.8392157, alpha 1.0, and chroma 0.29013073.
     * It can be represented as a packed float with the constant {@code -0x1.ad1246p126F}.
     * <pre>
     * <font style='background-color: #F2D1B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2D1B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2D1B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2D1B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2D1B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2D1B1'>&nbsp;@&nbsp;</font><font style='background-color: #F2D1B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2D1B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2D1B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_ORANGE = -0x1.ad1246p126F;
    static { NAMED.put("bright pure orange", -0x1.ad1246p126F); LIST.add(-0x1.ad1246p126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code AB6F2CFF}, H 0.11764706, S 0.92156863, L 0.4862745, alpha 1.0, and chroma 0.69773763.
     * It can be represented as a packed float with the constant {@code -0x1.f9d63cp125F}.
     * <pre>
     * <font style='background-color: #AB6F2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB6F2C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB6F2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB6F2C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB6F2C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB6F2C'>&nbsp;@&nbsp;</font><font style='background-color: #AB6F2C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB6F2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB6F2C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.f9d63cp125F;
    static { NAMED.put("deep saffron orange", -0x1.f9d63cp125F); LIST.add(-0x1.f9d63cp125F); }

    /**
     * This color constant "true saffron orange" has RGBA8888 code {@code D59C5CFF}, H 0.12941177, S 0.7294118, L 0.6509804, alpha 1.0, and chroma 0.68283796.
     * It can be represented as a packed float with the constant {@code -0x1.4d7442p126F}.
     * <pre>
     * <font style='background-color: #D59C5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D59C5C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D59C5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D59C5C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D59C5C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D59C5C'>&nbsp;@&nbsp;</font><font style='background-color: #D59C5C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D59C5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D59C5C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_ORANGE = -0x1.4d7442p126F;
    static { NAMED.put("true saffron orange", -0x1.4d7442p126F); LIST.add(-0x1.4d7442p126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code EBD1B3FF}, H 0.14901961, S 0.39607844, L 0.83137256, alpha 1.0, and chroma 0.2532343.
     * It can be represented as a packed float with the constant {@code -0x1.a8ca4cp126F}.
     * <pre>
     * <font style='background-color: #EBD1B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBD1B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBD1B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBD1B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBD1B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBD1B3'>&nbsp;@&nbsp;</font><font style='background-color: #EBD1B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBD1B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBD1B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.a8ca4cp126F;
    static { NAMED.put("bright saffron orange", -0x1.a8ca4cp126F); LIST.add(-0x1.a8ca4cp126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code BA7D2EFF}, H 0.1254902, S 0.9372549, L 0.5372549, alpha 1.0, and chroma 0.7486032.
     * It can be represented as a packed float with the constant {@code -0x1.13de4p126F}.
     * <pre>
     * <font style='background-color: #BA7D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA7D2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA7D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA7D2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA7D2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA7D2E'>&nbsp;@&nbsp;</font><font style='background-color: #BA7D2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA7D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA7D2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.13de4p126F;
    static { NAMED.put("deep orange saffron", -0x1.13de4p126F); LIST.add(-0x1.13de4p126F); }

    /**
     * This color constant "true orange saffron" has RGBA8888 code {@code DCAF74FF}, H 0.14509805, S 0.61960787, L 0.7137255, alpha 1.0, and chroma 0.5904578.
     * It can be represented as a packed float with the constant {@code -0x1.6d3c4ap126F}.
     * <pre>
     * <font style='background-color: #DCAF74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCAF74; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCAF74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DCAF74'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DCAF74'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DCAF74'>&nbsp;@&nbsp;</font><font style='background-color: #DCAF74; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCAF74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCAF74; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_SAFFRON = -0x1.6d3c4ap126F;
    static { NAMED.put("true orange saffron", -0x1.6d3c4ap126F); LIST.add(-0x1.6d3c4ap126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code EED8BCFF}, H 0.15686275, S 0.40392157, L 0.85882354, alpha 1.0, and chroma 0.22365654.
     * It can be represented as a packed float with the constant {@code -0x1.b6ce5p126F}.
     * <pre>
     * <font style='background-color: #EED8BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EED8BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EED8BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EED8BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EED8BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EED8BC'>&nbsp;@&nbsp;</font><font style='background-color: #EED8BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EED8BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EED8BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.b6ce5p126F;
    static { NAMED.put("bright orange saffron", -0x1.b6ce5p126F); LIST.add(-0x1.b6ce5p126F); }

    /**
     * This color constant "deep pure saffron" has RGBA8888 code {@code A87A38FF}, H 0.13725491, S 0.85882354, L 0.50980395, alpha 1.0, and chroma 0.61911654.
     * It can be represented as a packed float with the constant {@code -0x1.05b646p126F}.
     * <pre>
     * <font style='background-color: #A87A38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A87A38; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A87A38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A87A38'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A87A38'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A87A38'>&nbsp;@&nbsp;</font><font style='background-color: #A87A38; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A87A38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A87A38; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_SAFFRON = -0x1.05b646p126F;
    static { NAMED.put("deep pure saffron", -0x1.05b646p126F); LIST.add(-0x1.05b646p126F); }

    /**
     * This color constant "true pure saffron" has RGBA8888 code {@code CEA76EFF}, H 0.15294118, S 0.61960787, L 0.6745098, alpha 1.0, and chroma 0.5457021.
     * It can be represented as a packed float with the constant {@code -0x1.593c4ep126F}.
     * <pre>
     * <font style='background-color: #CEA76E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEA76E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEA76E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEA76E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEA76E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEA76E'>&nbsp;@&nbsp;</font><font style='background-color: #CEA76E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEA76E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEA76E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_SAFFRON = -0x1.593c4ep126F;
    static { NAMED.put("true pure saffron", -0x1.593c4ep126F); LIST.add(-0x1.593c4ep126F); }

    /**
     * This color constant "bright pure saffron" has RGBA8888 code {@code E7D5B9FF}, H 0.16862746, S 0.28627452, L 0.84313726, alpha 1.0, and chroma 0.208656.
     * It can be represented as a packed float with the constant {@code -0x1.ae9256p126F}.
     * <pre>
     * <font style='background-color: #E7D5B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7D5B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7D5B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7D5B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7D5B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7D5B9'>&nbsp;@&nbsp;</font><font style='background-color: #E7D5B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7D5B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7D5B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_SAFFRON = -0x1.ae9256p126F;
    static { NAMED.put("bright pure saffron", -0x1.ae9256p126F); LIST.add(-0x1.ae9256p126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code 967C38FF}, H 0.1764706, S 0.84705883, L 0.49411765, alpha 1.0, and chroma 0.5238978.
     * It can be represented as a packed float with the constant {@code -0x1.fdb05ap125F}.
     * <pre>
     * <font style='background-color: #967C38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #967C38; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #967C38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #967C38'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #967C38'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #967C38'>&nbsp;@&nbsp;</font><font style='background-color: #967C38; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #967C38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #967C38; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.fdb05ap125F;
    static { NAMED.put("deep yellow saffron", -0x1.fdb05ap125F); LIST.add(-0x1.fdb05ap125F); }

    /**
     * This color constant "true yellow saffron" has RGBA8888 code {@code C5AB66FF}, H 0.18431373, S 0.6784314, L 0.6784314, alpha 1.0, and chroma 0.55394965.
     * It can be represented as a packed float with the constant {@code -0x1.5b5a5ep126F}.
     * <pre>
     * <font style='background-color: #C5AB66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5AB66; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5AB66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C5AB66'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C5AB66'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C5AB66'>&nbsp;@&nbsp;</font><font style='background-color: #C5AB66; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5AB66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5AB66; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_SAFFRON = -0x1.5b5a5ep126F;
    static { NAMED.put("true yellow saffron", -0x1.5b5a5ep126F); LIST.add(-0x1.5b5a5ep126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code E4D8B9FF}, H 0.19215687, S 0.27058825, L 0.84705883, alpha 1.0, and chroma 0.26577178.
     * It can be represented as a packed float with the constant {@code -0x1.b08a62p126F}.
     * <pre>
     * <font style='background-color: #E4D8B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4D8B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4D8B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E4D8B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E4D8B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E4D8B9'>&nbsp;@&nbsp;</font><font style='background-color: #E4D8B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4D8B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4D8B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.b08a62p126F;
    static { NAMED.put("bright yellow saffron", -0x1.b08a62p126F); LIST.add(-0x1.b08a62p126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code AF9E37FF}, H 0.20784314, S 0.92156863, L 0.6156863, alpha 1.0, and chroma 0.66666114.
     * It can be represented as a packed float with the constant {@code -0x1.3bd66ap126F}.
     * <pre>
     * <font style='background-color: #AF9E37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF9E37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF9E37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF9E37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF9E37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF9E37'>&nbsp;@&nbsp;</font><font style='background-color: #AF9E37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF9E37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF9E37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.3bd66ap126F;
    static { NAMED.put("deep saffron yellow", -0x1.3bd66ap126F); LIST.add(-0x1.3bd66ap126F); }

    /**
     * This color constant "true saffron yellow" has RGBA8888 code {@code D3C776FF}, H 0.21568628, S 0.6627451, L 0.77254903, alpha 1.0, and chroma 0.58531916.
     * It can be represented as a packed float with the constant {@code -0x1.8b526ep126F}.
     * <pre>
     * <font style='background-color: #D3C776;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3C776; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3C776;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3C776'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3C776'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3C776'>&nbsp;@&nbsp;</font><font style='background-color: #D3C776; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3C776;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3C776; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_YELLOW = -0x1.8b526ep126F;
    static { NAMED.put("true saffron yellow", -0x1.8b526ep126F); LIST.add(-0x1.8b526ep126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code E9E3BCFF}, H 0.21960784, S 0.30588236, L 0.88235295, alpha 1.0, and chroma 0.3030896.
     * It can be represented as a packed float with the constant {@code -0x1.c29c7p126F}.
     * <pre>
     * <font style='background-color: #E9E3BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9E3BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9E3BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9E3BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9E3BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9E3BC'>&nbsp;@&nbsp;</font><font style='background-color: #E9E3BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9E3BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9E3BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.c29c7p126F;
    static { NAMED.put("bright saffron yellow", -0x1.c29c7p126F); LIST.add(-0x1.c29c7p126F); }

    /**
     * This color constant "deep pure yellow" has RGBA8888 code {@code C0C01BFF}, H 0.23921569, S 1.0, L 0.7254902, alpha 1.0, and chroma 0.8324298.
     * It can be represented as a packed float with the constant {@code -0x1.73fe7ap126F}.
     * <pre>
     * <font style='background-color: #C0C01B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0C01B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0C01B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0C01B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0C01B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0C01B'>&nbsp;@&nbsp;</font><font style='background-color: #C0C01B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0C01B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0C01B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_YELLOW = -0x1.73fe7ap126F;
    static { NAMED.put("deep pure yellow", -0x1.73fe7ap126F); LIST.add(-0x1.73fe7ap126F); }

    /**
     * This color constant "true pure yellow" has RGBA8888 code {@code E6E64EFF}, H 0.23921569, S 0.8980392, L 0.8745098, alpha 1.0, and chroma 0.88208085.
     * It can be represented as a packed float with the constant {@code -0x1.bfca7ap126F}.
     * <pre>
     * <font style='background-color: #E6E64E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6E64E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6E64E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6E64E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6E64E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6E64E'>&nbsp;@&nbsp;</font><font style='background-color: #E6E64E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6E64E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6E64E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_YELLOW = -0x1.bfca7ap126F;
    static { NAMED.put("true pure yellow", -0x1.bfca7ap126F); LIST.add(-0x1.bfca7ap126F); }

    /**
     * This color constant "bright pure yellow" has RGBA8888 code {@code F3F3ACFF}, H 0.23921569, S 0.48235294, L 0.9372549, alpha 1.0, and chroma 0.5031406.
     * It can be represented as a packed float with the constant {@code -0x1.def67ap126F}.
     * <pre>
     * <font style='background-color: #F3F3AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3F3AC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3F3AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3F3AC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3F3AC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3F3AC'>&nbsp;@&nbsp;</font><font style='background-color: #F3F3AC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3F3AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3F3AC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_YELLOW = -0x1.def67ap126F;
    static { NAMED.put("bright pure yellow", -0x1.def67ap126F); LIST.add(-0x1.def67ap126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code 98A61FFF}, H 0.25882354, S 1.0, L 0.6156863, alpha 1.0, and chroma 0.72723484.
     * It can be represented as a packed float with the constant {@code -0x1.3bfe84p126F}.
     * <pre>
     * <font style='background-color: #98A61F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98A61F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98A61F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98A61F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98A61F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98A61F'>&nbsp;@&nbsp;</font><font style='background-color: #98A61F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98A61F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98A61F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.3bfe84p126F;
    static { NAMED.put("deep lime yellow", -0x1.3bfe84p126F); LIST.add(-0x1.3bfe84p126F); }

    /**
     * This color constant "true lime yellow" has RGBA8888 code {@code CDDC4BFF}, H 0.25490198, S 0.8980392, L 0.8235294, alpha 1.0, and chroma 0.8452598.
     * It can be represented as a packed float with the constant {@code -0x1.a5ca82p126F}.
     * <pre>
     * <font style='background-color: #CDDC4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDDC4B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDDC4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CDDC4B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CDDC4B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CDDC4B'>&nbsp;@&nbsp;</font><font style='background-color: #CDDC4B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDDC4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDDC4B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_YELLOW = -0x1.a5ca82p126F;
    static { NAMED.put("true lime yellow", -0x1.a5ca82p126F); LIST.add(-0x1.a5ca82p126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code E8EFADFF}, H 0.2509804, S 0.45490196, L 0.9137255, alpha 1.0, and chroma 0.4672715.
     * It can be represented as a packed float with the constant {@code -0x1.d2e88p126F}.
     * <pre>
     * <font style='background-color: #E8EFAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8EFAD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8EFAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E8EFAD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E8EFAD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E8EFAD'>&nbsp;@&nbsp;</font><font style='background-color: #E8EFAD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8EFAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8EFAD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.d2e88p126F;
    static { NAMED.put("bright lime yellow", -0x1.d2e88p126F); LIST.add(-0x1.d2e88p126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code A4C71CFF}, H 0.2784314, S 1.0, L 0.7254902, alpha 1.0, and chroma 0.86974716.
     * It can be represented as a packed float with the constant {@code -0x1.73fe8ep126F}.
     * <pre>
     * <font style='background-color: #A4C71C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4C71C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4C71C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4C71C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4C71C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4C71C'>&nbsp;@&nbsp;</font><font style='background-color: #A4C71C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4C71C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4C71C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.73fe8ep126F;
    static { NAMED.put("deep yellow lime", -0x1.73fe8ep126F); LIST.add(-0x1.73fe8ep126F); }

    /**
     * This color constant "true yellow lime" has RGBA8888 code {@code CBE75AFF}, H 0.27058825, S 0.8509804, L 0.85490197, alpha 1.0, and chroma 0.8444136.
     * It can be represented as a packed float with the constant {@code -0x1.b5b28ap126F}.
     * <pre>
     * <font style='background-color: #CBE75A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBE75A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBE75A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBE75A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBE75A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBE75A'>&nbsp;@&nbsp;</font><font style='background-color: #CBE75A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBE75A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBE75A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_LIME = -0x1.b5b28ap126F;
    static { NAMED.put("true yellow lime", -0x1.b5b28ap126F); LIST.add(-0x1.b5b28ap126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code E5F3AFFF}, H 0.26666668, S 0.4509804, L 0.9254902, alpha 1.0, and chroma 0.47664565.
     * It can be represented as a packed float with the constant {@code -0x1.d8e688p126F}.
     * <pre>
     * <font style='background-color: #E5F3AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5F3AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5F3AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5F3AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5F3AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5F3AF'>&nbsp;@&nbsp;</font><font style='background-color: #E5F3AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5F3AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5F3AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.d8e688p126F;
    static { NAMED.put("bright yellow lime", -0x1.d8e688p126F); LIST.add(-0x1.d8e688p126F); }

    /**
     * This color constant "deep pure lime" has RGBA8888 code {@code 86B222FF}, H 0.29411766, S 1.0, L 0.6392157, alpha 1.0, and chroma 0.80365217.
     * It can be represented as a packed float with the constant {@code -0x1.47fe96p126F}.
     * <pre>
     * <font style='background-color: #86B222;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86B222; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86B222;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #86B222'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #86B222'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #86B222'>&nbsp;@&nbsp;</font><font style='background-color: #86B222; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86B222;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86B222; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_LIME = -0x1.47fe96p126F;
    static { NAMED.put("deep pure lime", -0x1.47fe96p126F); LIST.add(-0x1.47fe96p126F); }

    /**
     * This color constant "true pure lime" has RGBA8888 code {@code B3DD51FF}, H 0.2901961, S 0.87058824, L 0.80784315, alpha 1.0, and chroma 0.8551019.
     * It can be represented as a packed float with the constant {@code -0x1.9dbc94p126F}.
     * <pre>
     * <font style='background-color: #B3DD51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3DD51; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3DD51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B3DD51'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B3DD51'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B3DD51'>&nbsp;@&nbsp;</font><font style='background-color: #B3DD51; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3DD51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3DD51; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_LIME = -0x1.9dbc94p126F;
    static { NAMED.put("true pure lime", -0x1.9dbc94p126F); LIST.add(-0x1.9dbc94p126F); }

    /**
     * This color constant "bright pure lime" has RGBA8888 code {@code DCEFAFFF}, H 0.2784314, S 0.42745098, L 0.90588236, alpha 1.0, and chroma 0.45233968.
     * It can be represented as a packed float with the constant {@code -0x1.ceda8ep126F}.
     * <pre>
     * <font style='background-color: #DCEFAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCEFAF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCEFAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DCEFAF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DCEFAF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DCEFAF'>&nbsp;@&nbsp;</font><font style='background-color: #DCEFAF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCEFAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCEFAF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_LIME = -0x1.ceda8ep126F;
    static { NAMED.put("bright pure lime", -0x1.ceda8ep126F); LIST.add(-0x1.ceda8ep126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 6DCB1CFF}, H 0.32941177, S 1.0, L 0.7058824, alpha 1.0, and chroma 0.9956303.
     * It can be represented as a packed float with the constant {@code -0x1.69fea8p126F}.
     * <pre>
     * <font style='background-color: #6DCB1C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6DCB1C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6DCB1C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6DCB1C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6DCB1C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6DCB1C'>&nbsp;@&nbsp;</font><font style='background-color: #6DCB1C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6DCB1C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6DCB1C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.69fea8p126F;
    static { NAMED.put("deep green lime", -0x1.69fea8p126F); LIST.add(-0x1.69fea8p126F); }

    /**
     * This color constant "true green lime" has RGBA8888 code {@code A2E965FF}, H 0.31764707, S 0.79607844, L 0.8352941, alpha 1.0, and chroma 0.8770938.
     * It can be represented as a packed float with the constant {@code -0x1.ab96a2p126F}.
     * <pre>
     * <font style='background-color: #A2E965;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2E965; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2E965;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2E965'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2E965'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2E965'>&nbsp;@&nbsp;</font><font style='background-color: #A2E965; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2E965;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2E965; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_LIME = -0x1.ab96a2p126F;
    static { NAMED.put("true green lime", -0x1.ab96a2p126F); LIST.add(-0x1.ab96a2p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code D2F4B4FF}, H 0.30588236, S 0.40392157, L 0.9137255, alpha 1.0, and chroma 0.42590263.
     * It can be represented as a packed float with the constant {@code -0x1.d2ce9cp126F}.
     * <pre>
     * <font style='background-color: #D2F4B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2F4B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2F4B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2F4B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2F4B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2F4B4'>&nbsp;@&nbsp;</font><font style='background-color: #D2F4B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2F4B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2F4B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.d2ce9cp126F;
    static { NAMED.put("bright green lime", -0x1.d2ce9cp126F); LIST.add(-0x1.d2ce9cp126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 47BB23FF}, H 0.34509805, S 1.0, L 0.6392157, alpha 1.0, and chroma 0.9835532.
     * It can be represented as a packed float with the constant {@code -0x1.47febp126F}.
     * <pre>
     * <font style='background-color: #47BB23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #47BB23; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #47BB23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #47BB23'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #47BB23'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #47BB23'>&nbsp;@&nbsp;</font><font style='background-color: #47BB23; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #47BB23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #47BB23; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.47febp126F;
    static { NAMED.put("deep lime green", -0x1.47febp126F); LIST.add(-0x1.47febp126F); }

    /**
     * This color constant "true lime green" has RGBA8888 code {@code 79DF5AFF}, H 0.34117648, S 0.8235294, L 0.78039217, alpha 1.0, and chroma 0.9495663.
     * It can be represented as a packed float with the constant {@code -0x1.8fa4aep126F}.
     * <pre>
     * <font style='background-color: #79DF5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #79DF5A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #79DF5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #79DF5A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #79DF5A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #79DF5A'>&nbsp;@&nbsp;</font><font style='background-color: #79DF5A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #79DF5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #79DF5A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_GREEN = -0x1.8fa4aep126F;
    static { NAMED.put("true lime green", -0x1.8fa4aep126F); LIST.add(-0x1.8fa4aep126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code BFEFB0FF}, H 0.33333334, S 0.39215687, L 0.8862745, alpha 1.0, and chroma 0.46400177.
     * It can be represented as a packed float with the constant {@code -0x1.c4c8aap126F}.
     * <pre>
     * <font style='background-color: #BFEFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFEFB0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFEFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFEFB0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFEFB0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFEFB0'>&nbsp;@&nbsp;</font><font style='background-color: #BFEFB0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFEFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFEFB0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.c4c8aap126F;
    static { NAMED.put("bright lime green", -0x1.c4c8aap126F); LIST.add(-0x1.c4c8aap126F); }

    /**
     * This color constant "deep pure green" has RGBA8888 code {@code 19B219FF}, H 0.3529412, S 1.0, L 0.6, alpha 1.0, and chroma 0.97092944.
     * It can be represented as a packed float with the constant {@code -0x1.33feb4p126F}.
     * <pre>
     * <font style='background-color: #19B219;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #19B219; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #19B219;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #19B219'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #19B219'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #19B219'>&nbsp;@&nbsp;</font><font style='background-color: #19B219; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #19B219;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #19B219; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_GREEN = -0x1.33feb4p126F;
    static { NAMED.put("deep pure green", -0x1.33feb4p126F); LIST.add(-0x1.33feb4p126F); }

    /**
     * This color constant "true pure green" has RGBA8888 code {@code 45E445FF}, H 0.3529412, S 0.9098039, L 0.7764706, alpha 1.0, and chroma 1.1158658.
     * It can be represented as a packed float with the constant {@code -0x1.8dd0b4p126F}.
     * <pre>
     * <font style='background-color: #45E445;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #45E445; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #45E445;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #45E445'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #45E445'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #45E445'>&nbsp;@&nbsp;</font><font style='background-color: #45E445; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #45E445;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #45E445; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_GREEN = -0x1.8dd0b4p126F;
    static { NAMED.put("true pure green", -0x1.8dd0b4p126F); LIST.add(-0x1.8dd0b4p126F); }

    /**
     * This color constant "bright pure green" has RGBA8888 code {@code AAF3AAFF}, H 0.3529412, S 0.4509804, L 0.88235295, alpha 1.0, and chroma 0.4876479.
     * It can be represented as a packed float with the constant {@code -0x1.c2e6b4p126F}.
     * <pre>
     * <font style='background-color: #AAF3AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAF3AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAF3AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAF3AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAF3AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAF3AA'>&nbsp;@&nbsp;</font><font style='background-color: #AAF3AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAF3AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAF3AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_GREEN = -0x1.c2e6b4p126F;
    static { NAMED.put("bright pure green", -0x1.c2e6b4p126F); LIST.add(-0x1.c2e6b4p126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 24C058FF}, H 0.37254903, S 1.0, L 0.654902, alpha 1.0, and chroma 0.8999131.
     * It can be represented as a packed float with the constant {@code -0x1.4ffebep126F}.
     * <pre>
     * <font style='background-color: #24C058;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #24C058; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #24C058;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #24C058'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #24C058'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #24C058'>&nbsp;@&nbsp;</font><font style='background-color: #24C058; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #24C058;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #24C058; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.4ffebep126F;
    static { NAMED.put("deep cyan green", -0x1.4ffebep126F); LIST.add(-0x1.4ffebep126F); }

    /**
     * This color constant "true cyan green" has RGBA8888 code {@code 66E18FFF}, H 0.38039216, S 0.7764706, L 0.7882353, alpha 1.0, and chroma 0.77574974.
     * It can be represented as a packed float with the constant {@code -0x1.938cc2p126F}.
     * <pre>
     * <font style='background-color: #66E18F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #66E18F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #66E18F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #66E18F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #66E18F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #66E18F'>&nbsp;@&nbsp;</font><font style='background-color: #66E18F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #66E18F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #66E18F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_GREEN = -0x1.938cc2p126F;
    static { NAMED.put("true cyan green", -0x1.938cc2p126F); LIST.add(-0x1.938cc2p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code B4F0C8FF}, H 0.39215687, S 0.38039216, L 0.8862745, alpha 1.0, and chroma 0.3273337.
     * It can be represented as a packed float with the constant {@code -0x1.c4c2c8p126F}.
     * <pre>
     * <font style='background-color: #B4F0C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4F0C8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4F0C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4F0C8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4F0C8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4F0C8'>&nbsp;@&nbsp;</font><font style='background-color: #B4F0C8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4F0C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4F0C8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.c4c2c8p126F;
    static { NAMED.put("bright cyan green", -0x1.c4c2c8p126F); LIST.add(-0x1.c4c2c8p126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 1ABE87FF}, H 0.41960785, S 1.0, L 0.654902, alpha 1.0, and chroma 0.6701582.
     * It can be represented as a packed float with the constant {@code -0x1.4ffed6p126F}.
     * <pre>
     * <font style='background-color: #1ABE87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1ABE87; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1ABE87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1ABE87'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1ABE87'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1ABE87'>&nbsp;@&nbsp;</font><font style='background-color: #1ABE87; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1ABE87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1ABE87; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.4ffed6p126F;
    static { NAMED.put("deep green cyan", -0x1.4ffed6p126F); LIST.add(-0x1.4ffed6p126F); }

    /**
     * This color constant "true green cyan" has RGBA8888 code {@code 4CE5B2FF}, H 0.43137255, S 0.9019608, L 0.8, alpha 1.0, and chroma 0.6885169.
     * It can be represented as a packed float with the constant {@code -0x1.99ccdcp126F}.
     * <pre>
     * <font style='background-color: #4CE5B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4CE5B2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4CE5B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4CE5B2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4CE5B2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4CE5B2'>&nbsp;@&nbsp;</font><font style='background-color: #4CE5B2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4CE5B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4CE5B2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_CYAN = -0x1.99ccdcp126F;
    static { NAMED.put("true green cyan", -0x1.99ccdcp126F); LIST.add(-0x1.99ccdcp126F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code ACF3DBFF}, H 0.44705883, S 0.46666667, L 0.89411765, alpha 1.0, and chroma 0.32680765.
     * It can be represented as a packed float with the constant {@code -0x1.c8eee4p126F}.
     * <pre>
     * <font style='background-color: #ACF3DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACF3DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACF3DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ACF3DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ACF3DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ACF3DB'>&nbsp;@&nbsp;</font><font style='background-color: #ACF3DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACF3DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACF3DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.c8eee4p126F;
    static { NAMED.put("bright green cyan", -0x1.c8eee4p126F); LIST.add(-0x1.c8eee4p126F); }

    /**
     * This color constant "deep pure cyan" has RGBA8888 code {@code 25C4C4FF}, H 0.53333336, S 1.0, L 0.6901961, alpha 1.0, and chroma 0.5708173.
     * It can be represented as a packed float with the constant {@code -0x1.61ff1p126F}.
     * <pre>
     * <font style='background-color: #25C4C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25C4C4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25C4C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #25C4C4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #25C4C4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #25C4C4'>&nbsp;@&nbsp;</font><font style='background-color: #25C4C4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25C4C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25C4C4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_CYAN = -0x1.61ff1p126F;
    static { NAMED.put("deep pure cyan", -0x1.61ff1p126F); LIST.add(-0x1.61ff1p126F); }

    /**
     * This color constant "true pure cyan" has RGBA8888 code {@code 6FE3E3FF}, H 0.53333336, S 0.7764706, L 0.81960785, alpha 1.0, and chroma 0.51678914.
     * It can be represented as a packed float with the constant {@code -0x1.a38d1p126F}.
     * <pre>
     * <font style='background-color: #6FE3E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6FE3E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6FE3E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6FE3E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6FE3E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6FE3E3'>&nbsp;@&nbsp;</font><font style='background-color: #6FE3E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6FE3E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6FE3E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_CYAN = -0x1.a38d1p126F;
    static { NAMED.put("true pure cyan", -0x1.a38d1p126F); LIST.add(-0x1.a38d1p126F); }

    /**
     * This color constant "bright pure cyan" has RGBA8888 code {@code BAF1F1FF}, H 0.53333336, S 0.39607844, L 0.90588236, alpha 1.0, and chroma 0.26060432.
     * It can be represented as a packed float with the constant {@code -0x1.cecb1p126F}.
     * <pre>
     * <font style='background-color: #BAF1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BAF1F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BAF1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BAF1F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BAF1F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BAF1F1'>&nbsp;@&nbsp;</font><font style='background-color: #BAF1F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BAF1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BAF1F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_CYAN = -0x1.cecb1p126F;
    static { NAMED.put("bright pure cyan", -0x1.cecb1p126F); LIST.add(-0x1.cecb1p126F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 1B8DC5FF}, H 0.6627451, S 1.0, L 0.5176471, alpha 1.0, and chroma 0.70011586.
     * It can be represented as a packed float with the constant {@code -0x1.09ff52p126F}.
     * <pre>
     * <font style='background-color: #1B8DC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1B8DC5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1B8DC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1B8DC5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1B8DC5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1B8DC5'>&nbsp;@&nbsp;</font><font style='background-color: #1B8DC5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1B8DC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1B8DC5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.09ff52p126F;
    static { NAMED.put("deep blue cyan", -0x1.09ff52p126F); LIST.add(-0x1.09ff52p126F); }

    /**
     * This color constant "true blue cyan" has RGBA8888 code {@code 57B7E7FF}, H 0.64705884, S 0.8392157, L 0.6745098, alpha 1.0, and chroma 0.6704321.
     * It can be represented as a packed float with the constant {@code -0x1.59ad4ap126F}.
     * <pre>
     * <font style='background-color: #57B7E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57B7E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57B7E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57B7E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57B7E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57B7E7'>&nbsp;@&nbsp;</font><font style='background-color: #57B7E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57B7E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57B7E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_CYAN = -0x1.59ad4ap126F;
    static { NAMED.put("true blue cyan", -0x1.59ad4ap126F); LIST.add(-0x1.59ad4ap126F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code AEDCF3FF}, H 0.627451, S 0.6117647, L 0.8352941, alpha 1.0, and chroma 0.28466183.
     * It can be represented as a packed float with the constant {@code -0x1.ab394p126F}.
     * <pre>
     * <font style='background-color: #AEDCF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEDCF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEDCF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AEDCF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AEDCF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AEDCF3'>&nbsp;@&nbsp;</font><font style='background-color: #AEDCF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEDCF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEDCF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.ab394p126F;
    static { NAMED.put("bright blue cyan", -0x1.ab394p126F); LIST.add(-0x1.ab394p126F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 2151AFFF}, H 0.7176471, S 0.96862745, L 0.3372549, alpha 1.0, and chroma 0.8721422.
     * It can be represented as a packed float with the constant {@code -0x1.adef6ep125F}.
     * <pre>
     * <font style='background-color: #2151AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2151AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2151AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2151AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2151AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2151AF'>&nbsp;@&nbsp;</font><font style='background-color: #2151AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2151AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2151AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.adef6ep125F;
    static { NAMED.put("deep cyan blue", -0x1.adef6ep125F); LIST.add(-0x1.adef6ep125F); }

    /**
     * This color constant "true cyan blue" has RGBA8888 code {@code 4F7FDDFF}, H 0.70980394, S 0.7882353, L 0.5058824, alpha 1.0, and chroma 0.9172723.
     * It can be represented as a packed float with the constant {@code -0x1.03936ap126F}.
     * <pre>
     * <font style='background-color: #4F7FDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F7FDD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F7FDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F7FDD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F7FDD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F7FDD'>&nbsp;@&nbsp;</font><font style='background-color: #4F7FDD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F7FDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F7FDD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_BLUE = -0x1.03936ap126F;
    static { NAMED.put("true cyan blue", -0x1.03936ap126F); LIST.add(-0x1.03936ap126F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code AEC4EFFF}, H 0.69803923, S 0.6392157, L 0.7647059, alpha 1.0, and chroma 0.3508902.
     * It can be represented as a packed float with the constant {@code -0x1.874764p126F}.
     * <pre>
     * <font style='background-color: #AEC4EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEC4EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEC4EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AEC4EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AEC4EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AEC4EF'>&nbsp;@&nbsp;</font><font style='background-color: #AEC4EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEC4EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEC4EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.874764p126F;
    static { NAMED.put("bright cyan blue", -0x1.874764p126F); LIST.add(-0x1.874764p126F); }

    /**
     * This color constant "deep pure blue" has RGBA8888 code {@code 1C1CCAFF}, H 0.7411765, S 0.99607843, L 0.25490198, alpha 1.0, and chroma 1.085324.
     * It can be represented as a packed float with the constant {@code -0x1.83fd7ap125F}.
     * <pre>
     * <font style='background-color: #1C1CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C1CCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C1CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C1CCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C1CCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C1CCA'>&nbsp;@&nbsp;</font><font style='background-color: #1C1CCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C1CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C1CCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BLUE = -0x1.83fd7ap125F;
    static { NAMED.put("deep pure blue", -0x1.83fd7ap125F); LIST.add(-0x1.83fd7ap125F); }

    /**
     * This color constant "true pure blue" has RGBA8888 code {@code 6363E9FF}, H 0.7411765, S 0.827451, L 0.45490196, alpha 1.0, and chroma 1.0332899.
     * It can be represented as a packed float with the constant {@code -0x1.e9a77ap125F}.
     * <pre>
     * <font style='background-color: #6363E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6363E9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6363E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6363E9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6363E9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6363E9'>&nbsp;@&nbsp;</font><font style='background-color: #6363E9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6363E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6363E9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BLUE = -0x1.e9a77ap125F;
    static { NAMED.put("true pure blue", -0x1.e9a77ap125F); LIST.add(-0x1.e9a77ap125F); }

    /**
     * This color constant "bright pure blue" has RGBA8888 code {@code B2B2F4FF}, H 0.7411765, S 0.7490196, L 0.7176471, alpha 1.0, and chroma 0.48776293.
     * It can be represented as a packed float with the constant {@code -0x1.6f7f7ap126F}.
     * <pre>
     * <font style='background-color: #B2B2F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2B2F4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2B2F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2B2F4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2B2F4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2B2F4'>&nbsp;@&nbsp;</font><font style='background-color: #B2B2F4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2B2F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2B2F4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BLUE = -0x1.6f7f7ap126F;
    static { NAMED.put("bright pure blue", -0x1.6f7f7ap126F); LIST.add(-0x1.6f7f7ap126F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 3A23B9FF}, H 0.74509805, S 0.9372549, L 0.25882354, alpha 1.0, and chroma 1.0011312.
     * It can be represented as a packed float with the constant {@code -0x1.85df7cp125F}.
     * <pre>
     * <font style='background-color: #3A23B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A23B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A23B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A23B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A23B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A23B9'>&nbsp;@&nbsp;</font><font style='background-color: #3A23B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A23B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A23B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.85df7cp125F;
    static { NAMED.put("deep violet blue", -0x1.85df7cp125F); LIST.add(-0x1.85df7cp125F); }

    /**
     * This color constant "true violet blue" has RGBA8888 code {@code 6C57DEFF}, H 0.7490196, S 0.78039217, L 0.42745098, alpha 1.0, and chroma 1.0058334.
     * It can be represented as a packed float with the constant {@code -0x1.db8f7ep125F}.
     * <pre>
     * <font style='background-color: #6C57DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C57DE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C57DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C57DE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C57DE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C57DE'>&nbsp;@&nbsp;</font><font style='background-color: #6C57DE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C57DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C57DE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_BLUE = -0x1.db8f7ep125F;
    static { NAMED.put("true violet blue", -0x1.db8f7ep125F); LIST.add(-0x1.db8f7ep125F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code BAB0EFFF}, H 0.7529412, S 0.6862745, L 0.7176471, alpha 1.0, and chroma 0.44917855.
     * It can be represented as a packed float with the constant {@code -0x1.6f5f8p126F}.
     * <pre>
     * <font style='background-color: #BAB0EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BAB0EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BAB0EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BAB0EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BAB0EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BAB0EF'>&nbsp;@&nbsp;</font><font style='background-color: #BAB0EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BAB0EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BAB0EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.6f5f8p126F;
    static { NAMED.put("bright violet blue", -0x1.6f5f8p126F); LIST.add(-0x1.6f5f8p126F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 4618AFFF}, H 0.7529412, S 0.99607843, L 0.24705882, alpha 1.0, and chroma 0.9503582.
     * It can be represented as a packed float with the constant {@code -0x1.7ffd8p125F}.
     * <pre>
     * <font style='background-color: #4618AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4618AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4618AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4618AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4618AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4618AF'>&nbsp;@&nbsp;</font><font style='background-color: #4618AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4618AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4618AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.7ffd8p125F;
    static { NAMED.put("deep blue violet", -0x1.7ffd8p125F); LIST.add(-0x1.7ffd8p125F); }

    /**
     * This color constant "true blue violet" has RGBA8888 code {@code 7544E4FF}, H 0.7607843, S 0.8392157, L 0.40392157, alpha 1.0, and chroma 1.1094826.
     * It can be represented as a packed float with the constant {@code -0x1.cfad84p125F}.
     * <pre>
     * <font style='background-color: #7544E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7544E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7544E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7544E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7544E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7544E4'>&nbsp;@&nbsp;</font><font style='background-color: #7544E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7544E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7544E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_VIOLET = -0x1.cfad84p125F;
    static { NAMED.put("true blue violet", -0x1.cfad84p125F); LIST.add(-0x1.cfad84p125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code C0AAF3FF}, H 0.76862746, S 0.74509805, L 0.70980394, alpha 1.0, and chroma 0.5101218.
     * It can be represented as a packed float with the constant {@code -0x1.6b7d88p126F}.
     * <pre>
     * <font style='background-color: #C0AAF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0AAF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0AAF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0AAF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0AAF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0AAF3'>&nbsp;@&nbsp;</font><font style='background-color: #C0AAF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0AAF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0AAF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.6b7d88p126F;
    static { NAMED.put("bright blue violet", -0x1.6b7d88p126F); LIST.add(-0x1.6b7d88p126F); }

    /**
     * This color constant "deep pure violet" has RGBA8888 code {@code 6B24BFFF}, H 0.76862746, S 0.9607843, L 0.3137255, alpha 1.0, and chroma 1.041882.
     * It can be represented as a packed float with the constant {@code -0x1.a1eb88p125F}.
     * <pre>
     * <font style='background-color: #6B24BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B24BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B24BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6B24BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6B24BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6B24BF'>&nbsp;@&nbsp;</font><font style='background-color: #6B24BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B24BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B24BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_VIOLET = -0x1.a1eb88p125F;
    static { NAMED.put("deep pure violet", -0x1.a1eb88p125F); LIST.add(-0x1.a1eb88p125F); }

    /**
     * This color constant "true pure violet" has RGBA8888 code {@code 9C63E1FF}, H 0.78039217, S 0.7372549, L 0.49803922, alpha 1.0, and chroma 0.8934112.
     * It can be represented as a packed float with the constant {@code -0x1.ff798ep125F}.
     * <pre>
     * <font style='background-color: #9C63E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C63E1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C63E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C63E1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C63E1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C63E1'>&nbsp;@&nbsp;</font><font style='background-color: #9C63E1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C63E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C63E1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_VIOLET = -0x1.ff798ep125F;
    static { NAMED.put("true pure violet", -0x1.ff798ep125F); LIST.add(-0x1.ff798ep125F); }

    /**
     * This color constant "bright pure violet" has RGBA8888 code {@code CFB3F0FF}, H 0.7882353, S 0.6745098, L 0.74509805, alpha 1.0, and chroma 0.4151264.
     * It can be represented as a packed float with the constant {@code -0x1.7d5992p126F}.
     * <pre>
     * <font style='background-color: #CFB3F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB3F0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB3F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFB3F0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFB3F0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFB3F0'>&nbsp;@&nbsp;</font><font style='background-color: #CFB3F0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB3F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB3F0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_VIOLET = -0x1.7d5992p126F;
    static { NAMED.put("bright pure violet", -0x1.7d5992p126F); LIST.add(-0x1.7d5992p126F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 741ABCFF}, H 0.78039217, S 1.0, L 0.3137255, alpha 1.0, and chroma 1.0092564.
     * It can be represented as a packed float with the constant {@code -0x1.a1ff8ep125F}.
     * <pre>
     * <font style='background-color: #741ABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #741ABC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #741ABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #741ABC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #741ABC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #741ABC'>&nbsp;@&nbsp;</font><font style='background-color: #741ABC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #741ABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #741ABC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.a1ff8ep125F;
    static { NAMED.put("deep purple violet", -0x1.a1ff8ep125F); LIST.add(-0x1.a1ff8ep125F); }

    /**
     * This color constant "true purple violet" has RGBA8888 code {@code A04AE5FF}, H 0.7882353, S 0.80784315, L 0.45882353, alpha 1.0, and chroma 1.050548.
     * It can be represented as a packed float with the constant {@code -0x1.eb9d92p125F}.
     * <pre>
     * <font style='background-color: #A04AE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A04AE5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A04AE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A04AE5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A04AE5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A04AE5'>&nbsp;@&nbsp;</font><font style='background-color: #A04AE5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A04AE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A04AE5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_VIOLET = -0x1.eb9d92p125F;
    static { NAMED.put("true purple violet", -0x1.eb9d92p125F); LIST.add(-0x1.eb9d92p125F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code D3ABF3FF}, H 0.8, S 0.7294118, L 0.7294118, alpha 1.0, and chroma 0.49252978.
     * It can be represented as a packed float with the constant {@code -0x1.757598p126F}.
     * <pre>
     * <font style='background-color: #D3ABF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3ABF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3ABF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3ABF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3ABF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3ABF3'>&nbsp;@&nbsp;</font><font style='background-color: #D3ABF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3ABF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3ABF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.757598p126F;
    static { NAMED.put("bright purple violet", -0x1.757598p126F); LIST.add(-0x1.757598p126F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 8C25C3FF}, H 0.79607844, S 0.972549, L 0.36078432, alpha 1.0, and chroma 1.0394211.
     * It can be represented as a packed float with the constant {@code -0x1.b9f196p125F}.
     * <pre>
     * <font style='background-color: #8C25C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C25C3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C25C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C25C3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C25C3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C25C3'>&nbsp;@&nbsp;</font><font style='background-color: #8C25C3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C25C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C25C3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.b9f196p125F;
    static { NAMED.put("deep violet purple", -0x1.b9f196p125F); LIST.add(-0x1.b9f196p125F); }

    /**
     * This color constant "true violet purple" has RGBA8888 code {@code BA6DE3FF}, H 0.8039216, S 0.7058824, L 0.5529412, alpha 1.0, and chroma 0.81242275.
     * It can be represented as a packed float with the constant {@code -0x1.1b699ap126F}.
     * <pre>
     * <font style='background-color: #BA6DE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA6DE3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA6DE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA6DE3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA6DE3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA6DE3'>&nbsp;@&nbsp;</font><font style='background-color: #BA6DE3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA6DE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA6DE3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_PURPLE = -0x1.1b699ap126F;
    static { NAMED.put("true violet purple", -0x1.1b699ap126F); LIST.add(-0x1.1b699ap126F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code DDB8F1FF}, H 0.8117647, S 0.6627451, L 0.77254903, alpha 1.0, and chroma 0.38431287.
     * It can be represented as a packed float with the constant {@code -0x1.8b539ep126F}.
     * <pre>
     * <font style='background-color: #DDB8F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDB8F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDB8F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDB8F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDB8F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDB8F1'>&nbsp;@&nbsp;</font><font style='background-color: #DDB8F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDB8F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDB8F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.8b539ep126F;
    static { NAMED.put("bright violet purple", -0x1.8b539ep126F); LIST.add(-0x1.8b539ep126F); }

    /**
     * This color constant "deep pure purple" has RGBA8888 code {@code 9A1BC4FF}, H 0.80784315, S 1.0, L 0.3764706, alpha 1.0, and chroma 1.0590438.
     * It can be represented as a packed float with the constant {@code -0x1.c1ff9cp125F}.
     * <pre>
     * <font style='background-color: #9A1BC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A1BC4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A1BC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A1BC4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A1BC4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A1BC4'>&nbsp;@&nbsp;</font><font style='background-color: #9A1BC4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A1BC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A1BC4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_PURPLE = -0x1.c1ff9cp125F;
    static { NAMED.put("deep pure purple", -0x1.c1ff9cp125F); LIST.add(-0x1.c1ff9cp125F); }

    /**
     * This color constant "true pure purple" has RGBA8888 code {@code C254E6FF}, H 0.8156863, S 0.7764706, L 0.52156866, alpha 1.0, and chroma 0.9844666.
     * It can be represented as a packed float with the constant {@code -0x1.0b8dap126F}.
     * <pre>
     * <font style='background-color: #C254E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C254E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C254E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C254E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C254E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C254E6'>&nbsp;@&nbsp;</font><font style='background-color: #C254E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C254E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C254E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_PURPLE = -0x1.0b8dap126F;
    static { NAMED.put("true pure purple", -0x1.0b8dap126F); LIST.add(-0x1.0b8dap126F); }

    /**
     * This color constant "bright pure purple" has RGBA8888 code {@code E2ADF3FF}, H 0.827451, S 0.7137255, L 0.7490196, alpha 1.0, and chroma 0.4872622.
     * It can be represented as a packed float with the constant {@code -0x1.7f6da6p126F}.
     * <pre>
     * <font style='background-color: #E2ADF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2ADF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2ADF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2ADF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2ADF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2ADF3'>&nbsp;@&nbsp;</font><font style='background-color: #E2ADF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2ADF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2ADF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_PURPLE = -0x1.7f6da6p126F;
    static { NAMED.put("bright pure purple", -0x1.7f6da6p126F); LIST.add(-0x1.7f6da6p126F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code 9521ACFF}, H 0.827451, S 0.98039216, L 0.35686275, alpha 1.0, and chroma 0.9205612.
     * It can be represented as a packed float with the constant {@code -0x1.b7f5a6p125F}.
     * <pre>
     * <font style='background-color: #9521AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9521AC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9521AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9521AC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9521AC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9521AC'>&nbsp;@&nbsp;</font><font style='background-color: #9521AC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9521AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9521AC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.b7f5a6p125F;
    static { NAMED.put("deep magenta purple", -0x1.b7f5a6p125F); LIST.add(-0x1.b7f5a6p125F); }

    /**
     * This color constant "true magenta purple" has RGBA8888 code {@code C54EDDFF}, H 0.827451, S 0.8039216, L 0.50980395, alpha 1.0, and chroma 1.0639085.
     * It can be represented as a packed float with the constant {@code -0x1.059ba6p126F}.
     * <pre>
     * <font style='background-color: #C54EDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C54EDD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C54EDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C54EDD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C54EDD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C54EDD'>&nbsp;@&nbsp;</font><font style='background-color: #C54EDD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C54EDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C54EDD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_PURPLE = -0x1.059ba6p126F;
    static { NAMED.put("true magenta purple", -0x1.059ba6p126F); LIST.add(-0x1.059ba6p126F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code E4AEEFFF}, H 0.8352941, S 0.654902, L 0.7529412, alpha 1.0, and chroma 0.4546954.
     * It can be represented as a packed float with the constant {@code -0x1.814faap126F}.
     * <pre>
     * <font style='background-color: #E4AEEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4AEEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4AEEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E4AEEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E4AEEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E4AEEF'>&nbsp;@&nbsp;</font><font style='background-color: #E4AEEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4AEEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4AEEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.814faap126F;
    static { NAMED.put("bright magenta purple", -0x1.814faap126F); LIST.add(-0x1.814faap126F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code BB1CC9FF}, H 0.8392157, S 1.0, L 0.43137255, alpha 1.0, and chroma 1.0937161.
     * It can be represented as a packed float with the constant {@code -0x1.ddffacp125F}.
     * <pre>
     * <font style='background-color: #BB1CC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB1CC9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB1CC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB1CC9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB1CC9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB1CC9'>&nbsp;@&nbsp;</font><font style='background-color: #BB1CC9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB1CC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB1CC9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.ddffacp125F;
    static { NAMED.put("deep purple magenta", -0x1.ddffacp125F); LIST.add(-0x1.ddffacp125F); }

    /**
     * This color constant "true purple magenta" has RGBA8888 code {@code DD60E8FF}, H 0.84313726, S 0.7372549, L 0.5764706, alpha 1.0, and chroma 0.93334746.
     * It can be represented as a packed float with the constant {@code -0x1.2779aep126F}.
     * <pre>
     * <font style='background-color: #DD60E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD60E8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD60E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD60E8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD60E8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD60E8'>&nbsp;@&nbsp;</font><font style='background-color: #DD60E8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD60E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD60E8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_MAGENTA = -0x1.2779aep126F;
    static { NAMED.put("true purple magenta", -0x1.2779aep126F); LIST.add(-0x1.2779aep126F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code EEB1F4FF}, H 0.84705883, S 0.70980394, L 0.77254903, alpha 1.0, and chroma 0.4778316.
     * It can be represented as a packed float with the constant {@code -0x1.8b6bbp126F}.
     * <pre>
     * <font style='background-color: #EEB1F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEB1F4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEB1F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EEB1F4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EEB1F4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EEB1F4'>&nbsp;@&nbsp;</font><font style='background-color: #EEB1F4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEB1F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEB1F4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.8b6bbp126F;
    static { NAMED.put("bright purple magenta", -0x1.8b6bbp126F); LIST.add(-0x1.8b6bbp126F); }

    /**
     * This color constant "deep pure magenta" has RGBA8888 code {@code B723B7FF}, H 0.85490197, S 0.99215686, L 0.41960785, alpha 1.0, and chroma 1.0229391.
     * It can be represented as a packed float with the constant {@code -0x1.d7fbb4p125F}.
     * <pre>
     * <font style='background-color: #B723B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B723B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B723B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B723B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B723B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B723B7'>&nbsp;@&nbsp;</font><font style='background-color: #B723B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B723B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B723B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_MAGENTA = -0x1.d7fbb4p125F;
    static { NAMED.put("deep pure magenta", -0x1.d7fbb4p125F); LIST.add(-0x1.d7fbb4p125F); }

    /**
     * This color constant "true pure magenta" has RGBA8888 code {@code DE55DEFF}, H 0.85490197, S 0.7882353, L 0.5568628, alpha 1.0, and chroma 1.0627661.
     * It can be represented as a packed float with the constant {@code -0x1.1d93b4p126F}.
     * <pre>
     * <font style='background-color: #DE55DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE55DE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE55DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DE55DE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DE55DE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DE55DE'>&nbsp;@&nbsp;</font><font style='background-color: #DE55DE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE55DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE55DE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_MAGENTA = -0x1.1d93b4p126F;
    static { NAMED.put("true pure magenta", -0x1.1d93b4p126F); LIST.add(-0x1.1d93b4p126F); }

    /**
     * This color constant "bright pure magenta" has RGBA8888 code {@code EFAFEFFF}, H 0.85490197, S 0.6392157, L 0.7647059, alpha 1.0, and chroma 0.46580067.
     * It can be represented as a packed float with the constant {@code -0x1.8747b4p126F}.
     * <pre>
     * <font style='background-color: #EFAFEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFAFEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFAFEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFAFEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFAFEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFAFEF'>&nbsp;@&nbsp;</font><font style='background-color: #EFAFEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFAFEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFAFEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_MAGENTA = -0x1.8747b4p126F;
    static { NAMED.put("bright pure magenta", -0x1.8747b4p126F); LIST.add(-0x1.8747b4p126F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code AD187BFF}, H 0.9372549, S 1.0, L 0.3647059, alpha 1.0, and chroma 0.89718837.
     * It can be represented as a packed float with the constant {@code -0x1.bbffdep125F}.
     * <pre>
     * <font style='background-color: #AD187B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD187B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD187B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD187B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD187B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD187B'>&nbsp;@&nbsp;</font><font style='background-color: #AD187B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD187B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD187B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.bbffdep125F;
    static { NAMED.put("deep red magenta", -0x1.bbffdep125F); LIST.add(-0x1.bbffdep125F); }

    /**
     * This color constant "true red magenta" has RGBA8888 code {@code E443AEFF}, H 0.9254902, S 0.8509804, L 0.52156866, alpha 1.0, and chroma 1.0586712.
     * It can be represented as a packed float with the constant {@code -0x1.0bb3d8p126F}.
     * <pre>
     * <font style='background-color: #E443AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E443AE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E443AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E443AE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E443AE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E443AE'>&nbsp;@&nbsp;</font><font style='background-color: #E443AE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E443AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E443AE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_MAGENTA = -0x1.0bb3d8p126F;
    static { NAMED.put("true red magenta", -0x1.0bb3d8p126F); LIST.add(-0x1.0bb3d8p126F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code F3AADAFF}, H 0.9098039, S 0.69411767, L 0.7529412, alpha 1.0, and chroma 0.4150943.
     * It can be represented as a packed float with the constant {@code -0x1.8163dp126F}.
     * <pre>
     * <font style='background-color: #F3AADA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3AADA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3AADA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3AADA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3AADA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3AADA'>&nbsp;@&nbsp;</font><font style='background-color: #F3AADA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3AADA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3AADA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.8163dp126F;
    static { NAMED.put("bright red magenta", -0x1.8163dp126F); LIST.add(-0x1.8163dp126F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code BE2457FF}, H 0.0, S 0.9372549, L 0.39215687, alpha 1.0, and chroma 1.0948336.
     * It can be represented as a packed float with the constant {@code -0x1.c9dep125F}.
     * <pre>
     * <font style='background-color: #BE2457;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE2457; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE2457;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE2457'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE2457'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE2457'>&nbsp;@&nbsp;</font><font style='background-color: #BE2457; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE2457;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE2457; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.c9dep125F;
    static { NAMED.put("deep magenta red", -0x1.c9dep125F); LIST.add(-0x1.c9dep125F); }

    /**
     * This color constant "true magenta red" has RGBA8888 code {@code E0608BFF}, H 0.9882353, S 0.6, L 0.54509807, alpha 1.0, and chroma 0.76676255.
     * It can be represented as a packed float with the constant {@code -0x1.1733f8p126F}.
     * <pre>
     * <font style='background-color: #E0608B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0608B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0608B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0608B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0608B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0608B'>&nbsp;@&nbsp;</font><font style='background-color: #E0608B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0608B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0608B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_RED = -0x1.1733f8p126F;
    static { NAMED.put("true magenta red", -0x1.1733f8p126F); LIST.add(-0x1.1733f8p126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code F0B2C6FF}, H 0.972549, S 0.627451, L 0.7607843, alpha 1.0, and chroma 0.32585803.
     * It can be represented as a packed float with the constant {@code -0x1.8541fp126F}.
     * <pre>
     * <font style='background-color: #F0B2C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0B2C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0B2C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0B2C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0B2C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0B2C6'>&nbsp;@&nbsp;</font><font style='background-color: #F0B2C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0B2C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0B2C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.8541fp126F;
    static { NAMED.put("bright magenta red", -0x1.8541fp126F); LIST.add(-0x1.8541fp126F); }

    /**
     * This color constant "bold pure red" has RGBA8888 code {@code FF0101FF}, H 0.03137255, S 1.0, L 0.49803922, alpha 1.0, and chroma 1.7587819.
     * It can be represented as a packed float with the constant {@code -0x1.fffe1p125F}.
     * <pre>
     * <font style='background-color: #FF0101;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0101; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0101;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0101'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0101'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0101'>&nbsp;@&nbsp;</font><font style='background-color: #FF0101; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0101;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0101; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_RED = -0x1.fffe1p125F;
    static { NAMED.put("bold pure red", -0x1.fffe1p125F); LIST.add(-0x1.fffe1p125F); }

    /**
     * This color constant "bold brown red" has RGBA8888 code {@code F2270DFF}, H 0.03529412, S 1.0, L 0.4862745, alpha 1.0, and chroma 1.714403.
     * It can be represented as a packed float with the constant {@code -0x1.f9fe12p125F}.
     * <pre>
     * <font style='background-color: #F2270D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2270D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2270D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2270D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2270D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2270D'>&nbsp;@&nbsp;</font><font style='background-color: #F2270D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2270D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2270D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_RED = -0x1.f9fe12p125F;
    static { NAMED.put("bold brown red", -0x1.f9fe12p125F); LIST.add(-0x1.f9fe12p125F); }

    /**
     * This color constant "bold red brown" has RGBA8888 code {@code FF3901FF}, H 0.039215688, S 1.0, L 0.5254902, alpha 1.0, and chroma 1.6220237.
     * It can be represented as a packed float with the constant {@code -0x1.0dfe14p126F}.
     * <pre>
     * <font style='background-color: #FF3901;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF3901; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF3901;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF3901'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF3901'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF3901'>&nbsp;@&nbsp;</font><font style='background-color: #FF3901; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF3901;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF3901; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_BROWN = -0x1.0dfe14p126F;
    static { NAMED.put("bold red brown", -0x1.0dfe14p126F); LIST.add(-0x1.0dfe14p126F); }

    /**
     * This color constant "bold pure brown" has RGBA8888 code {@code F2590DFF}, H 0.05490196, S 1.0, L 0.54509807, alpha 1.0, and chroma 1.4257207.
     * It can be represented as a packed float with the constant {@code -0x1.17fe1cp126F}.
     * <pre>
     * <font style='background-color: #F2590D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2590D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2590D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2590D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2590D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2590D'>&nbsp;@&nbsp;</font><font style='background-color: #F2590D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2590D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2590D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BROWN = -0x1.17fe1cp126F;
    static { NAMED.put("bold pure brown", -0x1.17fe1cp126F); LIST.add(-0x1.17fe1cp126F); }

    /**
     * This color constant "bold orange brown" has RGBA8888 code {@code FF6301FF}, H 0.05882353, S 1.0, L 0.58431375, alpha 1.0, and chroma 1.3720239.
     * It can be represented as a packed float with the constant {@code -0x1.2bfe1ep126F}.
     * <pre>
     * <font style='background-color: #FF6301;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6301; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6301;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6301'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6301'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6301'>&nbsp;@&nbsp;</font><font style='background-color: #FF6301; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6301;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6301; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_BROWN = -0x1.2bfe1ep126F;
    static { NAMED.put("bold orange brown", -0x1.2bfe1ep126F); LIST.add(-0x1.2bfe1ep126F); }

    /**
     * This color constant "bold brown orange" has RGBA8888 code {@code F2730DFF}, H 0.07450981, S 1.0, L 0.5921569, alpha 1.0, and chroma 1.2434042.
     * It can be represented as a packed float with the constant {@code -0x1.2ffe26p126F}.
     * <pre>
     * <font style='background-color: #F2730D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2730D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2730D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2730D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2730D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2730D'>&nbsp;@&nbsp;</font><font style='background-color: #F2730D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2730D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2730D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_ORANGE = -0x1.2ffe26p126F;
    static { NAMED.put("bold brown orange", -0x1.2ffe26p126F); LIST.add(-0x1.2ffe26p126F); }

    /**
     * This color constant "bold pure orange" has RGBA8888 code {@code F97F06FF}, H 0.08235294, S 1.0, L 0.627451, alpha 1.0, and chroma 1.222372.
     * It can be represented as a packed float with the constant {@code -0x1.41fe2ap126F}.
     * <pre>
     * <font style='background-color: #F97F06;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F97F06; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F97F06;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F97F06'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F97F06'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F97F06'>&nbsp;@&nbsp;</font><font style='background-color: #F97F06; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F97F06;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F97F06; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_ORANGE = -0x1.41fe2ap126F;
    static { NAMED.put("bold pure orange", -0x1.41fe2ap126F); LIST.add(-0x1.41fe2ap126F); }

    /**
     * This color constant "bold saffron orange" has RGBA8888 code {@code E3851CFF}, H 0.101960786, S 1.0, L 0.60784316, alpha 1.0, and chroma 1.0245811.
     * It can be represented as a packed float with the constant {@code -0x1.37fe34p126F}.
     * <pre>
     * <font style='background-color: #E3851C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3851C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3851C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3851C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3851C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3851C'>&nbsp;@&nbsp;</font><font style='background-color: #E3851C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3851C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3851C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_ORANGE = -0x1.37fe34p126F;
    static { NAMED.put("bold saffron orange", -0x1.37fe34p126F); LIST.add(-0x1.37fe34p126F); }

    /**
     * This color constant "bold orange saffron" has RGBA8888 code {@code E58C1AFF}, H 0.10980392, S 1.0, L 0.627451, alpha 1.0, and chroma 1.0041153.
     * It can be represented as a packed float with the constant {@code -0x1.41fe38p126F}.
     * <pre>
     * <font style='background-color: #E58C1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E58C1A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E58C1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E58C1A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E58C1A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E58C1A'>&nbsp;@&nbsp;</font><font style='background-color: #E58C1A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E58C1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E58C1A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_SAFFRON = -0x1.41fe38p126F;
    static { NAMED.put("bold orange saffron", -0x1.41fe38p126F); LIST.add(-0x1.41fe38p126F); }

    /**
     * This color constant "bold pure saffron" has RGBA8888 code {@code D38F2CFF}, H 0.12941177, S 0.96862745, L 0.6117647, alpha 1.0, and chroma 0.85659623.
     * It can be represented as a packed float with the constant {@code -0x1.39ee42p126F}.
     * <pre>
     * <font style='background-color: #D38F2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D38F2C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D38F2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D38F2C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D38F2C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D38F2C'>&nbsp;@&nbsp;</font><font style='background-color: #D38F2C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D38F2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D38F2C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_SAFFRON = -0x1.39ee42p126F;
    static { NAMED.put("bold pure saffron", -0x1.39ee42p126F); LIST.add(-0x1.39ee42p126F); }

    /**
     * This color constant "bold yellow saffron" has RGBA8888 code {@code CCA233FF}, H 0.16862746, S 0.9490196, L 0.654902, alpha 1.0, and chroma 0.77695906.
     * It can be represented as a packed float with the constant {@code -0x1.4fe456p126F}.
     * <pre>
     * <font style='background-color: #CCA233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCA233; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCA233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CCA233'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CCA233'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CCA233'>&nbsp;@&nbsp;</font><font style='background-color: #CCA233; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCA233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCA233; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_SAFFRON = -0x1.4fe456p126F;
    static { NAMED.put("bold yellow saffron", -0x1.4fe456p126F); LIST.add(-0x1.4fe456p126F); }

    /**
     * This color constant "bold saffron yellow" has RGBA8888 code {@code D7BF28FF}, H 0.20392157, S 0.99215686, L 0.74509805, alpha 1.0, and chroma 0.8566367.
     * It can be represented as a packed float with the constant {@code -0x1.7dfa68p126F}.
     * <pre>
     * <font style='background-color: #D7BF28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7BF28; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7BF28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7BF28'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7BF28'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7BF28'>&nbsp;@&nbsp;</font><font style='background-color: #D7BF28; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7BF28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7BF28; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_YELLOW = -0x1.7dfa68p126F;
    static { NAMED.put("bold saffron yellow", -0x1.7dfa68p126F); LIST.add(-0x1.7dfa68p126F); }

    /**
     * This color constant "bold pure yellow" has RGBA8888 code {@code FFFF01FF}, H 0.23921569, S 1.0, L 0.96862745, alpha 1.0, and chroma 0.96873754.
     * It can be represented as a packed float with the constant {@code -0x1.effe7ap126F}.
     * <pre>
     * <font style='background-color: #FFFF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF01; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF01'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF01'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF01'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF01; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF01; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_YELLOW = -0x1.effe7ap126F;
    static { NAMED.put("bold pure yellow", -0x1.effe7ap126F); LIST.add(-0x1.effe7ap126F); }

    /**
     * This color constant "bold lime yellow" has RGBA8888 code {@code DBF20DFF}, H 0.2627451, S 1.0, L 0.8980392, alpha 1.0, and chroma 1.0242358.
     * It can be represented as a packed float with the constant {@code -0x1.cbfe86p126F}.
     * <pre>
     * <font style='background-color: #DBF20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBF20D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBF20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBF20D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBF20D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBF20D'>&nbsp;@&nbsp;</font><font style='background-color: #DBF20D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBF20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBF20D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_YELLOW = -0x1.cbfe86p126F;
    static { NAMED.put("bold lime yellow", -0x1.cbfe86p126F); LIST.add(-0x1.cbfe86p126F); }

    /**
     * This color constant "bold yellow lime" has RGBA8888 code {@code CBFF01FF}, H 0.28627452, S 1.0, L 0.9254902, alpha 1.0, and chroma 1.0959718.
     * It can be represented as a packed float with the constant {@code -0x1.d9fe92p126F}.
     * <pre>
     * <font style='background-color: #CBFF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBFF01; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBFF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBFF01'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBFF01'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBFF01'>&nbsp;@&nbsp;</font><font style='background-color: #CBFF01; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBFF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBFF01; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_LIME = -0x1.d9fe92p126F;
    static { NAMED.put("bold yellow lime", -0x1.d9fe92p126F); LIST.add(-0x1.d9fe92p126F); }

    /**
     * This color constant "bold pure lime" has RGBA8888 code {@code ACF20DFF}, H 0.3019608, S 1.0, L 0.8666667, alpha 1.0, and chroma 1.0788691.
     * It can be represented as a packed float with the constant {@code -0x1.bbfe9ap126F}.
     * <pre>
     * <font style='background-color: #ACF20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACF20D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACF20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ACF20D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ACF20D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ACF20D'>&nbsp;@&nbsp;</font><font style='background-color: #ACF20D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACF20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACF20D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_LIME = -0x1.bbfe9ap126F;
    static { NAMED.put("bold pure lime", -0x1.bbfe9ap126F); LIST.add(-0x1.bbfe9ap126F); }

    /**
     * This color constant "bold green lime" has RGBA8888 code {@code 76FF01FF}, H 0.33333334, S 1.0, L 0.88235295, alpha 1.0, and chroma 1.2356075.
     * It can be represented as a packed float with the constant {@code -0x1.c3feaap126F}.
     * <pre>
     * <font style='background-color: #76FF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76FF01; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76FF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #76FF01'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #76FF01'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #76FF01'>&nbsp;@&nbsp;</font><font style='background-color: #76FF01; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76FF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76FF01; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_LIME = -0x1.c3feaap126F;
    static { NAMED.put("bold green lime", -0x1.c3feaap126F); LIST.add(-0x1.c3feaap126F); }

    /**
     * This color constant "bold lime green" has RGBA8888 code {@code 42F20DFF}, H 0.34901962, S 1.0, L 0.8235294, alpha 1.0, and chroma 1.262463.
     * It can be represented as a packed float with the constant {@code -0x1.a5feb2p126F}.
     * <pre>
     * <font style='background-color: #42F20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42F20D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42F20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #42F20D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #42F20D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #42F20D'>&nbsp;@&nbsp;</font><font style='background-color: #42F20D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42F20D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42F20D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_GREEN = -0x1.a5feb2p126F;
    static { NAMED.put("bold lime green", -0x1.a5feb2p126F); LIST.add(-0x1.a5feb2p126F); }

    /**
     * This color constant "bold pure green" has RGBA8888 code {@code 01FF01FF}, H 0.3529412, S 1.0, L 0.8627451, alpha 1.0, and chroma 1.3288879.
     * It can be represented as a packed float with the constant {@code -0x1.b9feb4p126F}.
     * <pre>
     * <font style='background-color: #01FF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #01FF01; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #01FF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #01FF01'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #01FF01'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #01FF01'>&nbsp;@&nbsp;</font><font style='background-color: #01FF01; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #01FF01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #01FF01; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_GREEN = -0x1.b9feb4p126F;
    static { NAMED.put("bold pure green", -0x1.b9feb4p126F); LIST.add(-0x1.b9feb4p126F); }

    /**
     * This color constant "bold cyan green" has RGBA8888 code {@code 0DF259FF}, H 0.3647059, S 1.0, L 0.81960785, alpha 1.0, and chroma 1.1790828.
     * It can be represented as a packed float with the constant {@code -0x1.a3febap126F}.
     * <pre>
     * <font style='background-color: #0DF259;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0DF259; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0DF259;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0DF259'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0DF259'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0DF259'>&nbsp;@&nbsp;</font><font style='background-color: #0DF259; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0DF259;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0DF259; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_GREEN = -0x1.a3febap126F;
    static { NAMED.put("bold cyan green", -0x1.a3febap126F); LIST.add(-0x1.a3febap126F); }

    /**
     * This color constant "bold green cyan" has RGBA8888 code {@code 01FFAAFF}, H 0.40784314, S 1.0, L 0.8784314, alpha 1.0, and chroma 0.8878825.
     * It can be represented as a packed float with the constant {@code -0x1.c1fedp126F}.
     * <pre>
     * <font style='background-color: #01FFAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #01FFAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #01FFAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #01FFAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #01FFAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #01FFAA'>&nbsp;@&nbsp;</font><font style='background-color: #01FFAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #01FFAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #01FFAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_CYAN = -0x1.c1fedp126F;
    static { NAMED.put("bold green cyan", -0x1.c1fedp126F); LIST.add(-0x1.c1fedp126F); }

    /**
     * This color constant "bold pure cyan" has RGBA8888 code {@code 0DF2F2FF}, H 0.53333336, S 1.0, L 0.8509804, alpha 1.0, and chroma 0.6879112.
     * It can be represented as a packed float with the constant {@code -0x1.b3ff1p126F}.
     * <pre>
     * <font style='background-color: #0DF2F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0DF2F2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0DF2F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0DF2F2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0DF2F2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0DF2F2'>&nbsp;@&nbsp;</font><font style='background-color: #0DF2F2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0DF2F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0DF2F2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_CYAN = -0x1.b3ff1p126F;
    static { NAMED.put("bold pure cyan", -0x1.b3ff1p126F); LIST.add(-0x1.b3ff1p126F); }

    /**
     * This color constant "bold blue cyan" has RGBA8888 code {@code 01AAFFFF}, H 0.6745098, S 1.0, L 0.6313726, alpha 1.0, and chroma 0.9312566.
     * It can be represented as a packed float with the constant {@code -0x1.43ff58p126F}.
     * <pre>
     * <font style='background-color: #01AAFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #01AAFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #01AAFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #01AAFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #01AAFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #01AAFF'>&nbsp;@&nbsp;</font><font style='background-color: #01AAFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #01AAFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #01AAFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_CYAN = -0x1.43ff58p126F;
    static { NAMED.put("bold blue cyan", -0x1.43ff58p126F); LIST.add(-0x1.43ff58p126F); }

    /**
     * This color constant "bold cyan blue" has RGBA8888 code {@code 0D59F2FF}, H 0.7254902, S 1.0, L 0.40392157, alpha 1.0, and chroma 1.2588465.
     * It can be represented as a packed float with the constant {@code -0x1.cfff72p125F}.
     * <pre>
     * <font style='background-color: #0D59F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D59F2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D59F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0D59F2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0D59F2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0D59F2'>&nbsp;@&nbsp;</font><font style='background-color: #0D59F2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D59F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D59F2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_BLUE = -0x1.cfff72p125F;
    static { NAMED.put("bold cyan blue", -0x1.cfff72p125F); LIST.add(-0x1.cfff72p125F); }

    /**
     * This color constant "bold pure blue" has RGBA8888 code {@code 0101FFFF}, H 0.7411765, S 1.0, L 0.29803923, alpha 1.0, and chroma 1.2761624.
     * It can be represented as a packed float with the constant {@code -0x1.99ff7ap125F}.
     * <pre>
     * <font style='background-color: #0101FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0101FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0101FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0101FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0101FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0101FF'>&nbsp;@&nbsp;</font><font style='background-color: #0101FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0101FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0101FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BLUE = -0x1.99ff7ap125F;
    static { NAMED.put("bold pure blue", -0x1.99ff7ap125F); LIST.add(-0x1.99ff7ap125F); }

    /**
     * This color constant "bold violet blue" has RGBA8888 code {@code 300DF2FF}, H 0.7411765, S 1.0, L 0.29803923, alpha 1.0, and chroma 1.2761624.
     * It can be represented as a packed float with the constant {@code -0x1.99ff7ap125F}.
     * <pre>
     * <font style='background-color: #300DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #300DF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #300DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #300DF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #300DF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #300DF2'>&nbsp;@&nbsp;</font><font style='background-color: #300DF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #300DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #300DF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_BLUE = -0x1.99ff7ap125F;
    static { NAMED.put("bold violet blue", -0x1.99ff7ap125F); LIST.add(-0x1.99ff7ap125F); }

    /**
     * This color constant "bold blue violet" has RGBA8888 code {@code 4E01FFFF}, H 0.7490196, S 1.0, L 0.32941177, alpha 1.0, and chroma 1.3159771.
     * It can be represented as a packed float with the constant {@code -0x1.a9ff7ep125F}.
     * <pre>
     * <font style='background-color: #4E01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E01FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E01FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E01FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E01FF'>&nbsp;@&nbsp;</font><font style='background-color: #4E01FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E01FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_VIOLET = -0x1.a9ff7ep125F;
    static { NAMED.put("bold blue violet", -0x1.a9ff7ep125F); LIST.add(-0x1.a9ff7ep125F); }

    /**
     * This color constant "bold pure violet" has RGBA8888 code {@code 760DF2FF}, H 0.7647059, S 1.0, L 0.35686275, alpha 1.0, and chroma 1.2646295.
     * It can be represented as a packed float with the constant {@code -0x1.b7ff86p125F}.
     * <pre>
     * <font style='background-color: #760DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #760DF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #760DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #760DF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #760DF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #760DF2'>&nbsp;@&nbsp;</font><font style='background-color: #760DF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #760DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #760DF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_VIOLET = -0x1.b7ff86p125F;
    static { NAMED.put("bold pure violet", -0x1.b7ff86p125F); LIST.add(-0x1.b7ff86p125F); }

    /**
     * This color constant "bold purple violet" has RGBA8888 code {@code 8E01FFFF}, H 0.77254903, S 1.0, L 0.39607844, alpha 1.0, and chroma 1.3299705.
     * It can be represented as a packed float with the constant {@code -0x1.cbff8ap125F}.
     * <pre>
     * <font style='background-color: #8E01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E01FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E01FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E01FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E01FF'>&nbsp;@&nbsp;</font><font style='background-color: #8E01FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E01FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_VIOLET = -0x1.cbff8ap125F;
    static { NAMED.put("bold purple violet", -0x1.cbff8ap125F); LIST.add(-0x1.cbff8ap125F); }

    /**
     * This color constant "bold violet purple" has RGBA8888 code {@code A30DF2FF}, H 0.7882353, S 1.0, L 0.41568628, alpha 1.0, and chroma 1.2747668.
     * It can be represented as a packed float with the constant {@code -0x1.d5ff92p125F}.
     * <pre>
     * <font style='background-color: #A30DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A30DF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A30DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A30DF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A30DF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A30DF2'>&nbsp;@&nbsp;</font><font style='background-color: #A30DF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A30DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A30DF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_PURPLE = -0x1.d5ff92p125F;
    static { NAMED.put("bold violet purple", -0x1.d5ff92p125F); LIST.add(-0x1.d5ff92p125F); }

    /**
     * This color constant "bold pure purple" has RGBA8888 code {@code C001FFFF}, H 0.8039216, S 1.0, L 0.46666667, alpha 1.0, and chroma 1.3237739.
     * It can be represented as a packed float with the constant {@code -0x1.efff9ap125F}.
     * <pre>
     * <font style='background-color: #C001FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C001FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C001FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C001FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C001FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C001FF'>&nbsp;@&nbsp;</font><font style='background-color: #C001FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C001FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C001FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_PURPLE = -0x1.efff9ap125F;
    static { NAMED.put("bold pure purple", -0x1.efff9ap125F); LIST.add(-0x1.efff9ap125F); }

    /**
     * This color constant "bold magenta purple" has RGBA8888 code {@code CC0DF2FF}, H 0.81960785, S 1.0, L 0.47843137, alpha 1.0, and chroma 1.2775759.
     * It can be represented as a packed float with the constant {@code -0x1.f5ffa2p125F}.
     * <pre>
     * <font style='background-color: #CC0DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC0DF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC0DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CC0DF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CC0DF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CC0DF2'>&nbsp;@&nbsp;</font><font style='background-color: #CC0DF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC0DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC0DF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_PURPLE = -0x1.f5ffa2p125F;
    static { NAMED.put("bold magenta purple", -0x1.f5ffa2p125F); LIST.add(-0x1.f5ffa2p125F); }

    /**
     * This color constant "bold purple magenta" has RGBA8888 code {@code EA01FFFF}, H 0.8392157, S 1.0, L 0.53333336, alpha 1.0, and chroma 1.3376548.
     * It can be represented as a packed float with the constant {@code -0x1.11ffacp126F}.
     * <pre>
     * <font style='background-color: #EA01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA01FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EA01FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EA01FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EA01FF'>&nbsp;@&nbsp;</font><font style='background-color: #EA01FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA01FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA01FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_MAGENTA = -0x1.11ffacp126F;
    static { NAMED.put("bold purple magenta", -0x1.11ffacp126F); LIST.add(-0x1.11ffacp126F); }

    /**
     * This color constant "bold pure magenta" has RGBA8888 code {@code F20DF2FF}, H 0.85490197, S 1.0, L 0.5411765, alpha 1.0, and chroma 1.3128066.
     * It can be represented as a packed float with the constant {@code -0x1.15ffb4p126F}.
     * <pre>
     * <font style='background-color: #F20DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F20DF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F20DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F20DF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F20DF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F20DF2'>&nbsp;@&nbsp;</font><font style='background-color: #F20DF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F20DF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F20DF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_MAGENTA = -0x1.15ffb4p126F;
    static { NAMED.put("bold pure magenta", -0x1.15ffb4p126F); LIST.add(-0x1.15ffb4p126F); }

    /**
     * This color constant "bold red magenta" has RGBA8888 code {@code FF01AAFF}, H 0.9490196, S 1.0, L 0.5254902, alpha 1.0, and chroma 1.3022852.
     * It can be represented as a packed float with the constant {@code -0x1.0dffe4p126F}.
     * <pre>
     * <font style='background-color: #FF01AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF01AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF01AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF01AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF01AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF01AA'>&nbsp;@&nbsp;</font><font style='background-color: #FF01AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF01AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF01AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_MAGENTA = -0x1.0dffe4p126F;
    static { NAMED.put("bold red magenta", -0x1.0dffe4p126F); LIST.add(-0x1.0dffe4p126F); }

    /**
     * This color constant "bold magenta red" has RGBA8888 code {@code F20D59FF}, H 0.007843138, S 1.0, L 0.48235294, alpha 1.0, and chroma 1.4811642.
     * It can be represented as a packed float with the constant {@code -0x1.f7fe04p125F}.
     * <pre>
     * <font style='background-color: #F20D59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F20D59; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F20D59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F20D59'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F20D59'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F20D59'>&nbsp;@&nbsp;</font><font style='background-color: #F20D59; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F20D59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F20D59; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_RED = -0x1.f7fe04p125F;
    static { NAMED.put("bold magenta red", -0x1.f7fe04p125F); LIST.add(-0x1.f7fe04p125F); }

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
