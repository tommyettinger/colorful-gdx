package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;

import java.util.Comparator;

/**
 * A palette of predefined colors as packed Oklab floats, the kind {@link ColorTools} works with. This uses a geometric
 * palette, Yam3, that is designed to be as consistent as possible in its support for hues while keeping lots of
 * grayscale, desaturated, and mid-saturation colors, and to have a coherent naming system. This is the successor to
 * Yam (2), and may still be adjusted as needed (this may or may not be in another version like Yam4). The original Yam
 * had too many colors that were indistinguishable from grayscale, despite being named like they had a non-gray color
 * included in their mix. Where Yam and Yam2 share the same shape and distribution of colors, Yam3 has more possible
 * lightness levels for the 25% and 75% saturation colors, and fewer total hues for the 50% and 100% saturation colors.
 * All have 255 opaque colors, plus pure transparent.
 * <br>
 * You can access colors by their constant name, such as {@code PALE_GREEN_CYAN}, by the {@link #NAMED} map using
 * {@code NAMED.get("pale green cyan", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access
 * a float color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default
 * because it will not occur in a valid Oklab color. You can access the names in a specific order with {@link #NAMES}
 * (which is alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow
 * to blue to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * The names here put the most relevant color last, so "deep green cyan" is more cyan than it is green. There are 12
 * words used for hue; in order, they are: red, brown, orange, saffron, yellow, lime, green, cyan, blue, violet, purple,
 * and magenta. There are also 5 words used for grayscale colors; in order from darkest to lightest, they are: black,
 * lead, gray, silver, and white. There are four very-desaturated (25%) colors per hue, each with a grayscale color name
 * and a hue color name (for each of the twelve hues); these include "silver red" and "black green". Going towards more
 * saturated colors (50%), there are drab, faded, and pale variants of each hue. Getting even more saturated (75%),
 * there are "deep", "true", and "bright" variants of "pure HUE1", "HUE2 HUE1", and "HUE1 HUE2", where the last hue is
 * more important, and hues are only paired with their neighbors in the order or used on their own as a pure hue. At the
 * most saturated edge (100%), the only variant is "bold", and there are again "pure HUE1", "HUE2 HUE1", and "HUE1 HUE2"
 * like with the 75% saturation colors, forming a rainbow-colored band with medium lightness consistently across it. In
 * this last one, "bold violet blue" is primarily blue with some violet tint, while "bold blue violet" has more violet
 * than blue.
 */
public class Yam3Palette {
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<>(256);
    public static final FloatArray LIST = new FloatArray(256);

    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922, alpha 0.0, hue 0.625, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code 0x0.fefep-126F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YAM_TRANSPARENT = 0x0.fefep-126F;
    static { NAMED.put("transparent", 0x0.fefep-126F); LIST.add(0x0.fefep-126F); }

    /**
     * This color constant "pure black" has RGBA8888 code {@code 000000FF}, L 0.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefep125F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_BLACK = -0x1.fefep125F;
    static { NAMED.put("pure black", -0x1.fefep125F); LIST.add(-0x1.fefep125F); }

    /**
     * This color constant "almost black" has RGBA8888 code {@code 0B0909FF}, L 0.06666667, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 0.007346189, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff0022p125F}.
     * <pre>
     * <font style='background-color: #0B0909;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0B0909; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0B0909;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0B0909'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0B0909'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0B0909'>&nbsp;@&nbsp;</font><font style='background-color: #0B0909; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0B0909;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0B0909; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_BLACK = -0x1.ff0022p125F;
    static { NAMED.put("almost black", -0x1.ff0022p125F); LIST.add(-0x1.ff0022p125F); }

    /**
     * This color constant "lead black" has RGBA8888 code {@code 1C1A19FF}, L 0.13725491, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.010973937, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefe46p125F}.
     * <pre>
     * <font style='background-color: #1C1A19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C1A19; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C1A19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C1A19'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C1A19'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C1A19'>&nbsp;@&nbsp;</font><font style='background-color: #1C1A19; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C1A19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C1A19; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLACK = -0x1.fefe46p125F;
    static { NAMED.put("lead black", -0x1.fefe46p125F); LIST.add(-0x1.fefe46p125F); }

    /**
     * This color constant "black lead" has RGBA8888 code {@code 302D2CFF}, L 0.20784314, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 0.0011072664, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff006ap125F}.
     * <pre>
     * <font style='background-color: #302D2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #302D2C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #302D2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #302D2C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #302D2C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #302D2C'>&nbsp;@&nbsp;</font><font style='background-color: #302D2C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #302D2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #302D2C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LEAD = -0x1.ff006ap125F;
    static { NAMED.put("black lead", -0x1.ff006ap125F); LIST.add(-0x1.ff006ap125F); }

    /**
     * This color constant "pure lead" has RGBA8888 code {@code 464241FF}, L 0.28235295, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 6.9875096E-4, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff009p125F}.
     * <pre>
     * <font style='background-color: #464241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #464241; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #464241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #464241'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #464241'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #464241'>&nbsp;@&nbsp;</font><font style='background-color: #464241; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #464241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #464241; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_LEAD = -0x1.ff009p125F;
    static { NAMED.put("pure lead", -0x1.ff009p125F); LIST.add(-0x1.ff009p125F); }

    /**
     * This color constant "gray lead" has RGBA8888 code {@code 5A5755FF}, L 0.3529412, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.375, saturation 0.0011072664, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.00feb4p126F}.
     * <pre>
     * <font style='background-color: #5A5755;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A5755; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A5755;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A5755'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A5755'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A5755'>&nbsp;@&nbsp;</font><font style='background-color: #5A5755; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A5755;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A5755; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LEAD = -0x1.00feb4p126F;
    static { NAMED.put("gray lead", -0x1.00feb4p126F); LIST.add(-0x1.00feb4p126F); }

    /**
     * This color constant "lead gray" has RGBA8888 code {@code 6F6B6AFF}, L 0.42352942, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 3.7021612E-4, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff00d8p125F}.
     * <pre>
     * <font style='background-color: #6F6B6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F6B6A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F6B6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F6B6A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F6B6A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F6B6A'>&nbsp;@&nbsp;</font><font style='background-color: #6F6B6A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F6B6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F6B6A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GRAY = -0x1.ff00d8p125F;
    static { NAMED.put("lead gray", -0x1.ff00d8p125F); LIST.add(-0x1.ff00d8p125F); }

    /**
     * This color constant "pure gray" has RGBA8888 code {@code 847F7EFF}, L 0.49411765, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 3.011028E-4, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff00fcp125F}.
     * <pre>
     * <font style='background-color: #847F7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #847F7E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #847F7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #847F7E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #847F7E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #847F7E'>&nbsp;@&nbsp;</font><font style='background-color: #847F7E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #847F7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #847F7E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_GRAY = -0x1.ff00fcp125F;
    static { NAMED.put("pure gray", -0x1.ff00fcp125F); LIST.add(-0x1.ff00fcp125F); }

    /**
     * This color constant "silver gray" has RGBA8888 code {@code 999492FF}, L 0.5686275, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 6.9875096E-4, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.010122p126F}.
     * <pre>
     * <font style='background-color: #999492;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #999492; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #999492;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #999492'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #999492'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #999492'>&nbsp;@&nbsp;</font><font style='background-color: #999492; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #999492;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #999492; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GRAY = -0x1.010122p126F;
    static { NAMED.put("silver gray", -0x1.010122p126F); LIST.add(-0x1.010122p126F); }

    /**
     * This color constant "gray silver" has RGBA8888 code {@code ACA7A5FF}, L 0.6392157, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.0010099735, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.010146p126F}.
     * <pre>
     * <font style='background-color: #ACA7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACA7A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACA7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ACA7A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ACA7A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ACA7A5'>&nbsp;@&nbsp;</font><font style='background-color: #ACA7A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACA7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACA7A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SILVER = -0x1.010146p126F;
    static { NAMED.put("gray silver", -0x1.010146p126F); LIST.add(-0x1.010146p126F); }

    /**
     * This color constant "pure silver" has RGBA8888 code {@code BEB9B7FF}, L 0.70980394, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.0017821341, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.01016ap126F}.
     * <pre>
     * <font style='background-color: #BEB9B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEB9B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEB9B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BEB9B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BEB9B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BEB9B7'>&nbsp;@&nbsp;</font><font style='background-color: #BEB9B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEB9B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEB9B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_SILVER = -0x1.01016ap126F;
    static { NAMED.put("pure silver", -0x1.01016ap126F); LIST.add(-0x1.01016ap126F); }

    /**
     * This color constant "white silver" has RGBA8888 code {@code D1CCCAFF}, L 0.78431374, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 0.0021499598, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff019p125F}.
     * <pre>
     * <font style='background-color: #D1CCCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1CCCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1CCCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1CCCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1CCCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1CCCA'>&nbsp;@&nbsp;</font><font style='background-color: #D1CCCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1CCCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1CCCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SILVER = -0x1.ff019p125F;
    static { NAMED.put("white silver", -0x1.ff019p125F); LIST.add(-0x1.ff019p125F); }

    /**
     * This color constant "silver white" has RGBA8888 code {@code E2DDDBFF}, L 0.85490197, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 0.004759072, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff01b4p125F}.
     * <pre>
     * <font style='background-color: #E2DDDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2DDDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2DDDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2DDDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2DDDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2DDDB'>&nbsp;@&nbsp;</font><font style='background-color: #E2DDDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2DDDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2DDDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_WHITE = -0x1.ff01b4p125F;
    static { NAMED.put("silver white", -0x1.ff01b4p125F); LIST.add(-0x1.ff01b4p125F); }

    /**
     * This color constant "almost white" has RGBA8888 code {@code F3EEEBFF}, L 0.9254902, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.375, saturation 0.0022981903, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.00ffd8p126F}.
     * <pre>
     * <font style='background-color: #F3EEEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3EEEB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3EEEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3EEEB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3EEEB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3EEEB'>&nbsp;@&nbsp;</font><font style='background-color: #F3EEEB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3EEEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3EEEB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_WHITE = -0x1.00ffd8p126F;
    static { NAMED.put("almost white", -0x1.00ffd8p126F); LIST.add(-0x1.00ffd8p126F); }

    /**
     * This color constant "pure white" has RGBA8888 code {@code FFFDFBFF}, L 0.99215686, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, saturation 0.09876543, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.fefffap125F}.
     * <pre>
     * <font style='background-color: #FFFDFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFDFB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFDFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFDFB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFDFB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFDFB'>&nbsp;@&nbsp;</font><font style='background-color: #FFFDFB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFDFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFDFB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_WHITE = -0x1.fefffap125F;
    static { NAMED.put("pure white", -0x1.fefffap125F); LIST.add(-0x1.fefffap125F); }

    /**
     * This color constant "black red" has RGBA8888 code {@code 62322CFF}, L 0.27450982, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.08601887, saturation 0.17767455, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090e8cp126F}.
     * <pre>
     * <font style='background-color: #62322C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #62322C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #62322C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #62322C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #62322C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #62322C'>&nbsp;@&nbsp;</font><font style='background-color: #62322C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #62322C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #62322C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_RED = -0x1.090e8cp126F;
    static { NAMED.put("black red", -0x1.090e8cp126F); LIST.add(-0x1.090e8cp126F); }

    /**
     * This color constant "lead red" has RGBA8888 code {@code 824C45FF}, L 0.36862746, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.12012012, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.0910bcp126F}.
     * <pre>
     * <font style='background-color: #824C45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #824C45; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #824C45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #824C45'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #824C45'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #824C45'>&nbsp;@&nbsp;</font><font style='background-color: #824C45; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #824C45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #824C45; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_RED = -0x1.0910bcp126F;
    static { NAMED.put("lead red", -0x1.0910bcp126F); LIST.add(-0x1.0910bcp126F); }

    /**
     * This color constant "gray red" has RGBA8888 code {@code A36760FF}, L 0.46666667, A 0.53333336, B 0.5137255, alpha 1.0, hue 0.062156405, saturation 0.07878329, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0710eep126F}.
     * <pre>
     * <font style='background-color: #A36760;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A36760; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A36760;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A36760'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A36760'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A36760'>&nbsp;@&nbsp;</font><font style='background-color: #A36760; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A36760;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A36760; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_RED = -0x1.0710eep126F;
    static { NAMED.put("gray red", -0x1.0710eep126F); LIST.add(-0x1.0710eep126F); }

    /**
     * This color constant "silver red" has RGBA8888 code {@code C7867DFF}, L 0.5803922, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.1395042, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.091128p126F}.
     * <pre>
     * <font style='background-color: #C7867D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7867D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7867D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7867D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7867D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7867D'>&nbsp;@&nbsp;</font><font style='background-color: #C7867D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7867D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7867D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_RED = -0x1.091128p126F;
    static { NAMED.put("silver red", -0x1.091128p126F); LIST.add(-0x1.091128p126F); }

    /**
     * This color constant "white red" has RGBA8888 code {@code EBA69DFF}, L 0.7058824, A 0.53333336, B 0.5137255, alpha 1.0, hue 0.062156405, saturation 0.34063995, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.071168p126F}.
     * <pre>
     * <font style='background-color: #EBA69D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBA69D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBA69D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBA69D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBA69D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBA69D'>&nbsp;@&nbsp;</font><font style='background-color: #EBA69D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBA69D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBA69D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_RED = -0x1.071168p126F;
    static { NAMED.put("white red", -0x1.071168p126F); LIST.add(-0x1.071168p126F); }

    /**
     * This color constant "black brown" has RGBA8888 code {@code 644D47FF}, L 0.3372549, A 0.5137255, B 0.50980395, alpha 1.0, hue 0.09872868, saturation 0.039106883, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.0506acp126F}.
     * <pre>
     * <font style='background-color: #644D47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #644D47; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #644D47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #644D47'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #644D47'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #644D47'>&nbsp;@&nbsp;</font><font style='background-color: #644D47; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #644D47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #644D47; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BROWN = -0x1.0506acp126F;
    static { NAMED.put("black brown", -0x1.0506acp126F); LIST.add(-0x1.0506acp126F); }

    /**
     * This color constant "lead brown" has RGBA8888 code {@code 856B65FF}, L 0.44313726, A 0.5137255, B 0.5058824, alpha 1.0, hue 0.06443131, saturation 0.0143840285, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.0306e2p126F}.
     * <pre>
     * <font style='background-color: #856B65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #856B65; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #856B65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #856B65'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #856B65'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #856B65'>&nbsp;@&nbsp;</font><font style='background-color: #856B65; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #856B65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #856B65; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BROWN = -0x1.0306e2p126F;
    static { NAMED.put("lead brown", -0x1.0306e2p126F); LIST.add(-0x1.0306e2p126F); }

    /**
     * This color constant "gray brown" has RGBA8888 code {@code A1867EFF}, L 0.5372549, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.01885192, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.050512p126F}.
     * <pre>
     * <font style='background-color: #A1867E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1867E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1867E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A1867E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A1867E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A1867E'>&nbsp;@&nbsp;</font><font style='background-color: #A1867E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1867E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1867E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BROWN = -0x1.050512p126F;
    static { NAMED.put("gray brown", -0x1.050512p126F); LIST.add(-0x1.050512p126F); }

    /**
     * This color constant "silver brown" has RGBA8888 code {@code BC9F97FF}, L 0.6313726, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.024151672, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.050542p126F}.
     * <pre>
     * <font style='background-color: #BC9F97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC9F97; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC9F97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BC9F97'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BC9F97'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BC9F97'>&nbsp;@&nbsp;</font><font style='background-color: #BC9F97; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC9F97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC9F97; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BROWN = -0x1.050542p126F;
    static { NAMED.put("silver brown", -0x1.050542p126F); LIST.add(-0x1.050542p126F); }

    /**
     * This color constant "white brown" has RGBA8888 code {@code DCBDB5FF}, L 0.7490196, A 0.5137255, B 0.5058824, alpha 1.0, hue 0.06443131, saturation 0.08259167, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.03077ep126F}.
     * <pre>
     * <font style='background-color: #DCBDB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCBDB5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCBDB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DCBDB5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DCBDB5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DCBDB5'>&nbsp;@&nbsp;</font><font style='background-color: #DCBDB5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCBDB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCBDB5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BROWN = -0x1.03077ep126F;
    static { NAMED.put("white brown", -0x1.03077ep126F); LIST.add(-0x1.03077ep126F); }

    /**
     * This color constant "black orange" has RGBA8888 code {@code 664A37FF}, L 0.32941177, A 0.5137255, B 0.5176471, alpha 1.0, hue 0.14477962, saturation 0.12307692, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0906a8p126F}.
     * <pre>
     * <font style='background-color: #664A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #664A37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #664A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #664A37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #664A37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #664A37'>&nbsp;@&nbsp;</font><font style='background-color: #664A37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #664A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #664A37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_ORANGE = -0x1.0906a8p126F;
    static { NAMED.put("black orange", -0x1.0906a8p126F); LIST.add(-0x1.0906a8p126F); }

    /**
     * This color constant "lead orange" has RGBA8888 code {@code 83644FFF}, L 0.41960785, A 0.5137255, B 0.5176471, alpha 1.0, hue 0.14477962, saturation 0.083319984, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0906d6p126F}.
     * <pre>
     * <font style='background-color: #83644F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83644F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83644F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #83644F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #83644F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #83644F'>&nbsp;@&nbsp;</font><font style='background-color: #83644F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83644F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83644F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_ORANGE = -0x1.0906d6p126F;
    static { NAMED.put("lead orange", -0x1.0906d6p126F); LIST.add(-0x1.0906d6p126F); }

    /**
     * This color constant "gray orange" has RGBA8888 code {@code A2816AFF}, L 0.52156866, A 0.5137255, B 0.5176471, alpha 1.0, hue 0.14477962, saturation 0.06279435, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.09070ap126F}.
     * <pre>
     * <font style='background-color: #A2816A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2816A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2816A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2816A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2816A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2816A'>&nbsp;@&nbsp;</font><font style='background-color: #A2816A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2816A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2816A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_ORANGE = -0x1.09070ap126F;
    static { NAMED.put("gray orange", -0x1.09070ap126F); LIST.add(-0x1.09070ap126F); }

    /**
     * This color constant "silver orange" has RGBA8888 code {@code C4A087FF}, L 0.6392157, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15979148, saturation 0.07227123, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b0746p126F}.
     * <pre>
     * <font style='background-color: #C4A087;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4A087; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4A087;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4A087'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4A087'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4A087'>&nbsp;@&nbsp;</font><font style='background-color: #C4A087; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4A087;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4A087; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_ORANGE = -0x1.0b0746p126F;
    static { NAMED.put("silver orange", -0x1.0b0746p126F); LIST.add(-0x1.0b0746p126F); }

    /**
     * This color constant "white orange" has RGBA8888 code {@code E7C1A7FF}, L 0.76862746, A 0.5137255, B 0.5176471, alpha 1.0, hue 0.14477962, saturation 0.17190082, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.090788p126F}.
     * <pre>
     * <font style='background-color: #E7C1A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7C1A7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7C1A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7C1A7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7C1A7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7C1A7'>&nbsp;@&nbsp;</font><font style='background-color: #E7C1A7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7C1A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7C1A7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_ORANGE = -0x1.090788p126F;
    static { NAMED.put("white orange", -0x1.090788p126F); LIST.add(-0x1.090788p126F); }

    /**
     * This color constant "black saffron" has RGBA8888 code {@code 6E5B43FF}, L 0.3764706, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.20763123, saturation 0.13974738, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b02cp126F}.
     * <pre>
     * <font style='background-color: #6E5B43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E5B43; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E5B43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E5B43'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E5B43'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E5B43'>&nbsp;@&nbsp;</font><font style='background-color: #6E5B43; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E5B43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E5B43; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_SAFFRON = -0x1.0b02cp126F;
    static { NAMED.put("black saffron", -0x1.0b02cp126F); LIST.add(-0x1.0b02cp126F); }

    /**
     * This color constant "lead saffron" has RGBA8888 code {@code 937E64FF}, L 0.49803922, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.20763123, saturation 0.09244444, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b02fep126F}.
     * <pre>
     * <font style='background-color: #937E64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #937E64; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #937E64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #937E64'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #937E64'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #937E64'>&nbsp;@&nbsp;</font><font style='background-color: #937E64; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #937E64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #937E64; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_SAFFRON = -0x1.0b02fep126F;
    static { NAMED.put("lead saffron", -0x1.0b02fep126F); LIST.add(-0x1.0b02fep126F); }

    /**
     * This color constant "gray saffron" has RGBA8888 code {@code B19B7FFF}, L 0.6039216, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.20763123, saturation 0.07197232, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b0334p126F}.
     * <pre>
     * <font style='background-color: #B19B7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B19B7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B19B7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B19B7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B19B7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B19B7F'>&nbsp;@&nbsp;</font><font style='background-color: #B19B7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B19B7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B19B7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SAFFRON = -0x1.0b0334p126F;
    static { NAMED.put("gray saffron", -0x1.0b0334p126F); LIST.add(-0x1.0b0334p126F); }

    /**
     * This color constant "silver saffron" has RGBA8888 code {@code CCB597FF}, L 0.7019608, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.20763123, saturation 0.060122557, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b0366p126F}.
     * <pre>
     * <font style='background-color: #CCB597;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCB597; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCB597;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CCB597'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CCB597'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CCB597'>&nbsp;@&nbsp;</font><font style='background-color: #CCB597; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCB597;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCB597; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_SAFFRON = -0x1.0b0366p126F;
    static { NAMED.put("silver saffron", -0x1.0b0366p126F); LIST.add(-0x1.0b0366p126F); }

    /**
     * This color constant "white saffron" has RGBA8888 code {@code E7CFB0FF}, L 0.80784315, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.20763123, saturation 0.13101538, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b039cp126F}.
     * <pre>
     * <font style='background-color: #E7CFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7CFB0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7CFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7CFB0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7CFB0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7CFB0'>&nbsp;@&nbsp;</font><font style='background-color: #E7CFB0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7CFB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7CFB0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SAFFRON = -0x1.0b039cp126F;
    static { NAMED.put("white saffron", -0x1.0b039cp126F); LIST.add(-0x1.0b039cp126F); }

    /**
     * This color constant "black yellow" has RGBA8888 code {@code 66663DFF}, L 0.39215687, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.23020373, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef8c8p126F}.
     * <pre>
     * <font style='background-color: #66663D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #66663D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #66663D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #66663D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #66663D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #66663D'>&nbsp;@&nbsp;</font><font style='background-color: #66663D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #66663D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #66663D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_YELLOW = -0x1.0ef8c8p126F;
    static { NAMED.put("black yellow", -0x1.0ef8c8p126F); LIST.add(-0x1.0ef8c8p126F); }

    /**
     * This color constant "lead yellow" has RGBA8888 code {@code 87875BFF}, L 0.5058824, A 0.49019608, B 0.5294118, alpha 1.0, hue 0.30119568, saturation 0.16023073, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.0efb02p126F}.
     * <pre>
     * <font style='background-color: #87875B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87875B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87875B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87875B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87875B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87875B'>&nbsp;@&nbsp;</font><font style='background-color: #87875B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87875B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87875B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_YELLOW = -0x1.0efb02p126F;
    static { NAMED.put("lead yellow", -0x1.0efb02p126F); LIST.add(-0x1.0efb02p126F); }

    /**
     * This color constant "gray yellow" has RGBA8888 code {@code A2A374FF}, L 0.6039216, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.13235116, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef934p126F}.
     * <pre>
     * <font style='background-color: #A2A374;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2A374; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2A374;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2A374'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2A374'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2A374'>&nbsp;@&nbsp;</font><font style='background-color: #A2A374; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2A374;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2A374; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_YELLOW = -0x1.0ef934p126F;
    static { NAMED.put("gray yellow", -0x1.0ef934p126F); LIST.add(-0x1.0ef934p126F); }

    /**
     * This color constant "silver yellow" has RGBA8888 code {@code C0C18FFF}, L 0.7176471, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.10744045, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef96ep126F}.
     * <pre>
     * <font style='background-color: #C0C18F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0C18F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0C18F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0C18F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0C18F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0C18F'>&nbsp;@&nbsp;</font><font style='background-color: #C0C18F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0C18F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0C18F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_YELLOW = -0x1.0ef96ep126F;
    static { NAMED.put("silver yellow", -0x1.0ef96ep126F); LIST.add(-0x1.0ef96ep126F); }

    /**
     * This color constant "white yellow" has RGBA8888 code {@code E2E3AFFF}, L 0.85490197, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.08583288, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef9b4p126F}.
     * <pre>
     * <font style='background-color: #E2E3AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2E3AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2E3AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2E3AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2E3AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2E3AF'>&nbsp;@&nbsp;</font><font style='background-color: #E2E3AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2E3AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2E3AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_YELLOW = -0x1.0ef9b4p126F;
    static { NAMED.put("white yellow", -0x1.0ef9b4p126F); LIST.add(-0x1.0ef9b4p126F); }

    /**
     * This color constant "black lime" has RGBA8888 code {@code 4B5E38FF}, L 0.34901962, A 0.47843137, B 0.5254902, alpha 1.0, hue 0.361777, saturation 0.20622222, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.0cf4b2p126F}.
     * <pre>
     * <font style='background-color: #4B5E38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B5E38; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B5E38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B5E38'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B5E38'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B5E38'>&nbsp;@&nbsp;</font><font style='background-color: #4B5E38; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B5E38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B5E38; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LIME = -0x1.0cf4b2p126F;
    static { NAMED.put("black lime", -0x1.0cf4b2p126F); LIST.add(-0x1.0cf4b2p126F); }

    /**
     * This color constant "lead lime" has RGBA8888 code {@code 677D53FF}, L 0.4509804, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.1379451, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf2e6p126F}.
     * <pre>
     * <font style='background-color: #677D53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #677D53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #677D53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #677D53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #677D53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #677D53'>&nbsp;@&nbsp;</font><font style='background-color: #677D53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #677D53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #677D53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_LIME = -0x1.0cf2e6p126F;
    static { NAMED.put("lead lime", -0x1.0cf2e6p126F); LIST.add(-0x1.0cf2e6p126F); }

    /**
     * This color constant "gray lime" has RGBA8888 code {@code 869D70FF}, L 0.5647059, A 0.47843137, B 0.5254902, alpha 1.0, hue 0.361777, saturation 0.10521542, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.0cf52p126F}.
     * <pre>
     * <font style='background-color: #869D70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #869D70; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #869D70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #869D70'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #869D70'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #869D70'>&nbsp;@&nbsp;</font><font style='background-color: #869D70; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #869D70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #869D70; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LIME = -0x1.0cf52p126F;
    static { NAMED.put("gray lime", -0x1.0cf52p126F); LIST.add(-0x1.0cf52p126F); }

    /**
     * This color constant "silver lime" has RGBA8888 code {@code A6C090FF}, L 0.6901961, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.07878329, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf36p126F}.
     * <pre>
     * <font style='background-color: #A6C090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6C090; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6C090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6C090'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6C090'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6C090'>&nbsp;@&nbsp;</font><font style='background-color: #A6C090; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6C090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6C090; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_LIME = -0x1.0cf36p126F;
    static { NAMED.put("silver lime", -0x1.0cf36p126F); LIST.add(-0x1.0cf36p126F); }

    /**
     * This color constant "white lime" has RGBA8888 code {@code C9E4B1FF}, L 0.83137256, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.069975674, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf3a8p126F}.
     * <pre>
     * <font style='background-color: #C9E4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9E4B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9E4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9E4B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9E4B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9E4B1'>&nbsp;@&nbsp;</font><font style='background-color: #C9E4B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9E4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9E4B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_LIME = -0x1.0cf3a8p126F;
    static { NAMED.put("white lime", -0x1.0cf3a8p126F); LIST.add(-0x1.0cf3a8p126F); }

    /**
     * This color constant "black green" has RGBA8888 code {@code 436E48FF}, L 0.3882353, A 0.4627451, B 0.5176471, alpha 1.0, hue 0.4295985, saturation 0.33176956, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.08ecc6p126F}.
     * <pre>
     * <font style='background-color: #436E48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #436E48; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #436E48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #436E48'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #436E48'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #436E48'>&nbsp;@&nbsp;</font><font style='background-color: #436E48; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #436E48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #436E48; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_GREEN = -0x1.08ecc6p126F;
    static { NAMED.put("black green", -0x1.08ecc6p126F); LIST.add(-0x1.08ecc6p126F); }

    /**
     * This color constant "lead green" has RGBA8888 code {@code 639267FF}, L 0.5058824, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.20491019, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed02p126F}.
     * <pre>
     * <font style='background-color: #639267;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #639267; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #639267;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #639267'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #639267'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #639267'>&nbsp;@&nbsp;</font><font style='background-color: #639267; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #639267;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #639267; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GREEN = -0x1.0aed02p126F;
    static { NAMED.put("lead green", -0x1.0aed02p126F); LIST.add(-0x1.0aed02p126F); }

    /**
     * This color constant "gray green" has RGBA8888 code {@code 7EAF82FF}, L 0.60784316, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.1622759, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed36p126F}.
     * <pre>
     * <font style='background-color: #7EAF82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7EAF82; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7EAF82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7EAF82'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7EAF82'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7EAF82'>&nbsp;@&nbsp;</font><font style='background-color: #7EAF82; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7EAF82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7EAF82; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.0aed36p126F;
    static { NAMED.put("gray green", -0x1.0aed36p126F); LIST.add(-0x1.0aed36p126F); }

    /**
     * This color constant "silver green" has RGBA8888 code {@code 95C899FF}, L 0.7019608, A 0.46666667, B 0.52156866, alpha 1.0, hue 0.40858525, saturation 0.09855177, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.0aef66p126F}.
     * <pre>
     * <font style='background-color: #95C899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #95C899; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #95C899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #95C899'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #95C899'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #95C899'>&nbsp;@&nbsp;</font><font style='background-color: #95C899; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #95C899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #95C899; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.0aef66p126F;
    static { NAMED.put("silver green", -0x1.0aef66p126F); LIST.add(-0x1.0aef66p126F); }

    /**
     * This color constant "white green" has RGBA8888 code {@code B1E7B5FF}, L 0.81960785, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.13614857, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aeda2p126F}.
     * <pre>
     * <font style='background-color: #B1E7B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1E7B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1E7B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1E7B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1E7B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1E7B5'>&nbsp;@&nbsp;</font><font style='background-color: #B1E7B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1E7B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1E7B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_GREEN = -0x1.0aeda2p126F;
    static { NAMED.put("white green", -0x1.0aeda2p126F); LIST.add(-0x1.0aeda2p126F); }

    /**
     * This color constant "black cyan" has RGBA8888 code {@code 416766FF}, L 0.3764706, A 0.4745098, B 0.49411765, alpha 1.0, hue 0.5360971, saturation 0.27374086, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.fcf2cp125F}.
     * <pre>
     * <font style='background-color: #416766;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #416766; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #416766;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #416766'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #416766'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #416766'>&nbsp;@&nbsp;</font><font style='background-color: #416766; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #416766;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #416766; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_CYAN = -0x1.fcf2cp125F;
    static { NAMED.put("black cyan", -0x1.fcf2cp125F); LIST.add(-0x1.fcf2cp125F); }

    /**
     * This color constant "lead cyan" has RGBA8888 code {@code 5D8585FF}, L 0.47843137, A 0.47843137, B 0.49019608, alpha 1.0, hue 0.56789327, saturation 0.16776788, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.faf4f4p125F}.
     * <pre>
     * <font style='background-color: #5D8585;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D8585; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D8585;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5D8585'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5D8585'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5D8585'>&nbsp;@&nbsp;</font><font style='background-color: #5D8585; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D8585;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D8585; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_CYAN = -0x1.faf4f4p125F;
    static { NAMED.put("lead cyan", -0x1.faf4f4p125F); LIST.add(-0x1.faf4f4p125F); }

    /**
     * This color constant "gray cyan" has RGBA8888 code {@code 77A2A2FF}, L 0.5803922, A 0.4745098, B 0.49019608, alpha 1.0, hue 0.55842525, saturation 0.17286702, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.faf328p125F}.
     * <pre>
     * <font style='background-color: #77A2A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #77A2A2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #77A2A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #77A2A2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #77A2A2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #77A2A2'>&nbsp;@&nbsp;</font><font style='background-color: #77A2A2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #77A2A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #77A2A2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_CYAN = -0x1.faf328p125F;
    static { NAMED.put("gray cyan", -0x1.faf328p125F); LIST.add(-0x1.faf328p125F); }

    /**
     * This color constant "silver cyan" has RGBA8888 code {@code 95C2C1FF}, L 0.69803923, A 0.4745098, B 0.49411765, alpha 1.0, hue 0.5360971, saturation 0.12657778, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.fcf364p125F}.
     * <pre>
     * <font style='background-color: #95C2C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #95C2C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #95C2C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #95C2C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #95C2C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #95C2C1'>&nbsp;@&nbsp;</font><font style='background-color: #95C2C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #95C2C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #95C2C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_CYAN = -0x1.fcf364p125F;
    static { NAMED.put("silver cyan", -0x1.fcf364p125F); LIST.add(-0x1.fcf364p125F); }

    /**
     * This color constant "white cyan" has RGBA8888 code {@code B4E4E3FF}, L 0.83137256, A 0.4745098, B 0.49411765, alpha 1.0, hue 0.5360971, saturation 0.09854671, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.fcf3a8p125F}.
     * <pre>
     * <font style='background-color: #B4E4E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4E4E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4E4E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4E4E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4E4E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4E4E3'>&nbsp;@&nbsp;</font><font style='background-color: #B4E4E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4E4E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4E4E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_CYAN = -0x1.fcf3a8p125F;
    static { NAMED.put("white cyan", -0x1.fcf3a8p125F); LIST.add(-0x1.fcf3a8p125F); }

    /**
     * This color constant "black blue" has RGBA8888 code {@code 2D4475FF}, L 0.2901961, A 0.49411765, B 0.4509804, alpha 1.0, hue 0.73098123, saturation 0.1083344, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.e6fc94p125F}.
     * <pre>
     * <font style='background-color: #2D4475;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D4475; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D4475;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D4475'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D4475'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D4475'>&nbsp;@&nbsp;</font><font style='background-color: #2D4475; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D4475;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D4475; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BLUE = -0x1.e6fc94p125F;
    static { NAMED.put("black blue", -0x1.e6fc94p125F); LIST.add(-0x1.e6fc94p125F); }

    /**
     * This color constant "lead blue" has RGBA8888 code {@code 4A659BFF}, L 0.4, A 0.49019608, B 0.45490196, alpha 1.0, hue 0.7159276, saturation 0.141824, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.e8faccp125F}.
     * <pre>
     * <font style='background-color: #4A659B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A659B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A659B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A659B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A659B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A659B'>&nbsp;@&nbsp;</font><font style='background-color: #4A659B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A659B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A659B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLUE = -0x1.e8faccp125F;
    static { NAMED.put("lead blue", -0x1.e8faccp125F); LIST.add(-0x1.e8faccp125F); }

    /**
     * This color constant "gray blue" has RGBA8888 code {@code 6380BBFF}, L 0.49411765, A 0.49411765, B 0.4509804, alpha 1.0, hue 0.73098123, saturation 0.21345004, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.e6fcfcp125F}.
     * <pre>
     * <font style='background-color: #6380BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6380BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6380BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6380BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6380BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6380BB'>&nbsp;@&nbsp;</font><font style='background-color: #6380BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6380BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6380BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BLUE = -0x1.e6fcfcp125F;
    static { NAMED.put("gray blue", -0x1.e6fcfcp125F); LIST.add(-0x1.e6fcfcp125F); }

    /**
     * This color constant "silver blue" has RGBA8888 code {@code 7999D6FF}, L 0.5803922, A 0.49019608, B 0.45490196, alpha 1.0, hue 0.7159276, saturation 0.29277316, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.e8fb28p125F}.
     * <pre>
     * <font style='background-color: #7999D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7999D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7999D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7999D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7999D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7999D6'>&nbsp;@&nbsp;</font><font style='background-color: #7999D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7999D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7999D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BLUE = -0x1.e8fb28p125F;
    static { NAMED.put("silver blue", -0x1.e8fb28p125F); LIST.add(-0x1.e8fb28p125F); }

    /**
     * This color constant "white blue" has RGBA8888 code {@code 8FB0F0FF}, L 0.6666667, A 0.49019608, B 0.4509804, alpha 1.0, hue 0.71857655, saturation 0.61538464, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e6fb54p125F}.
     * <pre>
     * <font style='background-color: #8FB0F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FB0F0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FB0F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FB0F0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FB0F0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FB0F0'>&nbsp;@&nbsp;</font><font style='background-color: #8FB0F0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FB0F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FB0F0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BLUE = -0x1.e6fb54p125F;
    static { NAMED.put("white blue", -0x1.e6fb54p125F); LIST.add(-0x1.e6fb54p125F); }

    /**
     * This color constant "black violet" has RGBA8888 code {@code 4F416DFF}, L 0.30588236, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.10961622, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed089cp125F}.
     * <pre>
     * <font style='background-color: #4F416D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F416D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F416D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F416D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F416D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F416D'>&nbsp;@&nbsp;</font><font style='background-color: #4F416D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F416D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F416D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_VIOLET = -0x1.ed089cp125F;
    static { NAMED.put("black violet", -0x1.ed089cp125F); LIST.add(-0x1.ed089cp125F); }

    /**
     * This color constant "lead violet" has RGBA8888 code {@code 6C5D8DFF}, L 0.40392157, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.071727045, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed08cep125F}.
     * <pre>
     * <font style='background-color: #6C5D8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C5D8D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C5D8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C5D8D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C5D8D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C5D8D'>&nbsp;@&nbsp;</font><font style='background-color: #6C5D8D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C5D8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C5D8D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_VIOLET = -0x1.ed08cep125F;
    static { NAMED.put("lead violet", -0x1.ed08cep125F); LIST.add(-0x1.ed08cep125F); }

    /**
     * This color constant "gray violet" has RGBA8888 code {@code 8576A9FF}, L 0.49019608, A 0.5137255, B 0.46666667, alpha 1.0, hue 0.8121564, saturation 0.0893648, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.ef06fap125F}.
     * <pre>
     * <font style='background-color: #8576A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8576A9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8576A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8576A9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8576A9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8576A9'>&nbsp;@&nbsp;</font><font style='background-color: #8576A9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8576A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8576A9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_VIOLET = -0x1.ef06fap125F;
    static { NAMED.put("gray violet", -0x1.ef06fap125F); LIST.add(-0x1.ef06fap125F); }

    /**
     * This color constant "silver violet" has RGBA8888 code {@code 9F8FC6FF}, L 0.58431375, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.18790519, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed092ap125F}.
     * <pre>
     * <font style='background-color: #9F8FC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F8FC6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F8FC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F8FC6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F8FC6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F8FC6'>&nbsp;@&nbsp;</font><font style='background-color: #9F8FC6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F8FC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F8FC6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_VIOLET = -0x1.ed092ap125F;
    static { NAMED.put("silver violet", -0x1.ed092ap125F); LIST.add(-0x1.ed092ap125F); }

    /**
     * This color constant "white violet" has RGBA8888 code {@code BEADE7FF}, L 0.69803923, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.39385164, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed0964p125F}.
     * <pre>
     * <font style='background-color: #BEADE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEADE7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEADE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BEADE7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BEADE7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BEADE7'>&nbsp;@&nbsp;</font><font style='background-color: #BEADE7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEADE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEADE7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_VIOLET = -0x1.ed0964p125F;
    static { NAMED.put("white violet", -0x1.ed0964p125F); LIST.add(-0x1.ed0964p125F); }

    /**
     * This color constant "black purple" has RGBA8888 code {@code 573A6AFF}, L 0.29803923, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.17123237, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0e98p125F}.
     * <pre>
     * <font style='background-color: #573A6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573A6A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573A6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #573A6A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #573A6A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #573A6A'>&nbsp;@&nbsp;</font><font style='background-color: #573A6A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573A6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573A6A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_PURPLE = -0x1.ed0e98p125F;
    static { NAMED.put("black purple", -0x1.ed0e98p125F); LIST.add(-0x1.ed0e98p125F); }

    /**
     * This color constant "lead purple" has RGBA8888 code {@code 735388FF}, L 0.3882353, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.11462663, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0ec6p125F}.
     * <pre>
     * <font style='background-color: #735388;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #735388; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #735388;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #735388'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #735388'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #735388'>&nbsp;@&nbsp;</font><font style='background-color: #735388; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #735388;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #735388; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_PURPLE = -0x1.ed0ec6p125F;
    static { NAMED.put("lead purple", -0x1.ed0ec6p125F); LIST.add(-0x1.ed0ec6p125F); }

    /**
     * This color constant "gray purple" has RGBA8888 code {@code 906FA7FF}, L 0.4862745, A 0.5254902, B 0.46666667, alpha 1.0, hue 0.85391617, saturation 0.08251881, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.ef0cf8p125F}.
     * <pre>
     * <font style='background-color: #906FA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #906FA7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #906FA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #906FA7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #906FA7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #906FA7'>&nbsp;@&nbsp;</font><font style='background-color: #906FA7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #906FA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #906FA7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_PURPLE = -0x1.ef0cf8p125F;
    static { NAMED.put("gray purple", -0x1.ef0cf8p125F); LIST.add(-0x1.ef0cf8p125F); }

    /**
     * This color constant "silver purple" has RGBA8888 code {@code B18DCAFF}, L 0.6, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.1972898, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0f32p125F}.
     * <pre>
     * <font style='background-color: #B18DCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B18DCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B18DCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B18DCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B18DCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B18DCA'>&nbsp;@&nbsp;</font><font style='background-color: #B18DCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B18DCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B18DCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PURPLE = -0x1.ed0f32p125F;
    static { NAMED.put("silver purple", -0x1.ed0f32p125F); LIST.add(-0x1.ed0f32p125F); }

    /**
     * This color constant "white purple" has RGBA8888 code {@code D4ADEEFF}, L 0.7254902, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.49233353, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0f72p125F}.
     * <pre>
     * <font style='background-color: #D4ADEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4ADEE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4ADEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4ADEE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4ADEE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4ADEE'>&nbsp;@&nbsp;</font><font style='background-color: #D4ADEE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4ADEE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4ADEE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_PURPLE = -0x1.ed0f72p125F;
    static { NAMED.put("white purple", -0x1.ed0f72p125F); LIST.add(-0x1.ed0f72p125F); }

    /**
     * This color constant "black magenta" has RGBA8888 code {@code 6D466CFF}, L 0.34509805, A 0.53333336, B 0.47843137, alpha 1.0, hue 0.90858525, saturation 0.11581103, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f510bp125F}.
     * <pre>
     * <font style='background-color: #6D466C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D466C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D466C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D466C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D466C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D466C'>&nbsp;@&nbsp;</font><font style='background-color: #6D466C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D466C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D466C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_MAGENTA = -0x1.f510bp125F;
    static { NAMED.put("black magenta", -0x1.f510bp125F); LIST.add(-0x1.f510bp125F); }

    /**
     * This color constant "lead magenta" has RGBA8888 code {@code 916690FF}, L 0.45882353, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.08251881, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f310eap125F}.
     * <pre>
     * <font style='background-color: #916690;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #916690; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #916690;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #916690'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #916690'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #916690'>&nbsp;@&nbsp;</font><font style='background-color: #916690; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #916690;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #916690; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_MAGENTA = -0x1.f310eap125F;
    static { NAMED.put("lead magenta", -0x1.f310eap125F); LIST.add(-0x1.f310eap125F); }

    /**
     * This color constant "gray magenta" has RGBA8888 code {@code AE80ADFF}, L 0.5568628, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.06568898, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f3111cp125F}.
     * <pre>
     * <font style='background-color: #AE80AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE80AD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE80AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE80AD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE80AD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE80AD'>&nbsp;@&nbsp;</font><font style='background-color: #AE80AD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE80AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE80AD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_MAGENTA = -0x1.f3111cp125F;
    static { NAMED.put("gray magenta", -0x1.f3111cp125F); LIST.add(-0x1.f3111cp125F); }

    /**
     * This color constant "silver magenta" has RGBA8888 code {@code C898C7FF}, L 0.64705884, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.117248, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f3114ap125F}.
     * <pre>
     * <font style='background-color: #C898C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C898C7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C898C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C898C7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C898C7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C898C7'>&nbsp;@&nbsp;</font><font style='background-color: #C898C7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C898C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C898C7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_MAGENTA = -0x1.f3114ap125F;
    static { NAMED.put("silver magenta", -0x1.f3114ap125F); LIST.add(-0x1.f3114ap125F); }

    /**
     * This color constant "white magenta" has RGBA8888 code {@code E5B2E4FF}, L 0.7529412, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.2659312, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f3118p125F}.
     * <pre>
     * <font style='background-color: #E5B2E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5B2E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5B2E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5B2E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5B2E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5B2E4'>&nbsp;@&nbsp;</font><font style='background-color: #E5B2E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5B2E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5B2E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_MAGENTA = -0x1.f3118p125F;
    static { NAMED.put("white magenta", -0x1.f3118p125F); LIST.add(-0x1.f3118p125F); }

    /**
     * This color constant "drab red" has RGBA8888 code {@code 953229FF}, L 0.34509805, A 0.56078434, B 0.53333336, alpha 1.0, hue 0.079836555, saturation 0.43671936, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.111ebp126F}.
     * <pre>
     * <font style='background-color: #953229;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #953229; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #953229;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #953229'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #953229'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #953229'>&nbsp;@&nbsp;</font><font style='background-color: #953229; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #953229;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #953229; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED = -0x1.111ebp126F;
    static { NAMED.put("drab red", -0x1.111ebp126F); LIST.add(-0x1.111ebp126F); }

    /**
     * This color constant "faded red" has RGBA8888 code {@code BC5044FF}, L 0.4509804, A 0.56078434, B 0.53333336, alpha 1.0, hue 0.079836555, saturation 0.30046272, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.111ee6p126F}.
     * <pre>
     * <font style='background-color: #BC5044;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC5044; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC5044;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BC5044'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BC5044'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BC5044'>&nbsp;@&nbsp;</font><font style='background-color: #BC5044; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC5044;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC5044; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_RED = -0x1.111ee6p126F;
    static { NAMED.put("faded red", -0x1.111ee6p126F); LIST.add(-0x1.111ee6p126F); }

    /**
     * This color constant "pale red" has RGBA8888 code {@code E77264FF}, L 0.5764706, A 0.56078434, B 0.53333336, alpha 1.0, hue 0.079836555, saturation 0.45351472, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.111f26p126F}.
     * <pre>
     * <font style='background-color: #E77264;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E77264; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E77264;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E77264'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E77264'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E77264'>&nbsp;@&nbsp;</font><font style='background-color: #E77264; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E77264;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E77264; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED = -0x1.111f26p126F;
    static { NAMED.put("pale red", -0x1.111f26p126F); LIST.add(-0x1.111f26p126F); }

    /**
     * This color constant "drab brown" has RGBA8888 code {@code 885E4CFF}, L 0.4117647, A 0.52156866, B 0.52156866, alpha 1.0, hue 0.125, saturation 0.13397925, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0b0ad2p126F}.
     * <pre>
     * <font style='background-color: #885E4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #885E4C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #885E4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #885E4C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #885E4C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #885E4C'>&nbsp;@&nbsp;</font><font style='background-color: #885E4C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #885E4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #885E4C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN = -0x1.0b0ad2p126F;
    static { NAMED.put("drab brown", -0x1.0b0ad2p126F); LIST.add(-0x1.0b0ad2p126F); }

    /**
     * This color constant "faded brown" has RGBA8888 code {@code AA7D69FF}, L 0.52156866, A 0.5176471, B 0.52156866, alpha 1.0, hue 0.14085212, saturation 0.093421206, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.0b090ap126F}.
     * <pre>
     * <font style='background-color: #AA7D69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA7D69; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA7D69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA7D69'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA7D69'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA7D69'>&nbsp;@&nbsp;</font><font style='background-color: #AA7D69; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA7D69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA7D69; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BROWN = -0x1.0b090ap126F;
    static { NAMED.put("faded brown", -0x1.0b090ap126F); LIST.add(-0x1.0b090ap126F); }

    /**
     * This color constant "pale brown" has RGBA8888 code {@code CD9C87FF}, L 0.6392157, A 0.52156866, B 0.52156866, alpha 1.0, hue 0.125, saturation 0.12220679, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0b0b46p126F}.
     * <pre>
     * <font style='background-color: #CD9C87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD9C87; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD9C87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD9C87'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD9C87'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD9C87'>&nbsp;@&nbsp;</font><font style='background-color: #CD9C87; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD9C87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD9C87; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN = -0x1.0b0b46p126F;
    static { NAMED.put("pale brown", -0x1.0b0b46p126F); LIST.add(-0x1.0b0b46p126F); }

    /**
     * This color constant "drab orange" has RGBA8888 code {@code 935C36FF}, L 0.41568628, A 0.52156866, B 0.5372549, alpha 1.0, hue 0.16646945, saturation 0.36179397, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.130ad4p126F}.
     * <pre>
     * <font style='background-color: #935C36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #935C36; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #935C36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #935C36'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #935C36'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #935C36'>&nbsp;@&nbsp;</font><font style='background-color: #935C36; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #935C36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #935C36; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE = -0x1.130ad4p126F;
    static { NAMED.put("drab orange", -0x1.130ad4p126F); LIST.add(-0x1.130ad4p126F); }

    /**
     * This color constant "faded orange" has RGBA8888 code {@code B87B53FF}, L 0.5294118, A 0.5254902, B 0.5372549, alpha 1.0, hue 0.1544865, saturation 0.26764297, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.130d0ep126F}.
     * <pre>
     * <font style='background-color: #B87B53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B87B53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B87B53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B87B53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B87B53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B87B53'>&nbsp;@&nbsp;</font><font style='background-color: #B87B53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B87B53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B87B53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_ORANGE = -0x1.130d0ep126F;
    static { NAMED.put("faded orange", -0x1.130d0ep126F); LIST.add(-0x1.130d0ep126F); }

    /**
     * This color constant "pale orange" has RGBA8888 code {@code E2A176FF}, L 0.67058825, A 0.52156866, B 0.5372549, alpha 1.0, hue 0.16646945, saturation 0.23282212, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.130b56p126F}.
     * <pre>
     * <font style='background-color: #E2A176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2A176; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2A176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2A176'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2A176'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2A176'>&nbsp;@&nbsp;</font><font style='background-color: #E2A176; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2A176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2A176; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE = -0x1.130b56p126F;
    static { NAMED.put("pale orange", -0x1.130b56p126F); LIST.add(-0x1.130b56p126F); }

    /**
     * This color constant "drab saffron" has RGBA8888 code {@code 9E7844FF}, L 0.49019608, A 0.50980395, B 0.5411765, alpha 1.0, hue 0.21279909, saturation 0.3497842, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.1504fap126F}.
     * <pre>
     * <font style='background-color: #9E7844;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E7844; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E7844;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E7844'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E7844'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E7844'>&nbsp;@&nbsp;</font><font style='background-color: #9E7844; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E7844;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E7844; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON = -0x1.1504fap126F;
    static { NAMED.put("drab saffron", -0x1.1504fap126F); LIST.add(-0x1.1504fap126F); }

    /**
     * This color constant "faded saffron" has RGBA8888 code {@code C59D65FF}, L 0.62352943, A 0.50980395, B 0.5411765, alpha 1.0, hue 0.21279909, saturation 0.25799307, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.15053ep126F}.
     * <pre>
     * <font style='background-color: #C59D65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C59D65; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C59D65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C59D65'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C59D65'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C59D65'>&nbsp;@&nbsp;</font><font style='background-color: #C59D65; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C59D65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C59D65; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_SAFFRON = -0x1.15053ep126F;
    static { NAMED.put("faded saffron", -0x1.15053ep126F); LIST.add(-0x1.15053ep126F); }

    /**
     * This color constant "pale saffron" has RGBA8888 code {@code E6BB80FF}, L 0.7411765, A 0.50980395, B 0.5411765, alpha 1.0, hue 0.21279909, saturation 0.23532382, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.15057ap126F}.
     * <pre>
     * <font style='background-color: #E6BB80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6BB80; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6BB80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6BB80'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6BB80'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6BB80'>&nbsp;@&nbsp;</font><font style='background-color: #E6BB80; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6BB80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6BB80; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON = -0x1.15057ap126F;
    static { NAMED.put("pale saffron", -0x1.15057ap126F); LIST.add(-0x1.15057ap126F); }

    /**
     * This color constant "drab yellow" has RGBA8888 code {@code 90963DFF}, L 0.54509807, A 0.4745098, B 0.5529412, alpha 1.0, hue 0.3214129, saturation 0.47456732, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1af316p126F}.
     * <pre>
     * <font style='background-color: #90963D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90963D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90963D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #90963D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #90963D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #90963D'>&nbsp;@&nbsp;</font><font style='background-color: #90963D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90963D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90963D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW = -0x1.1af316p126F;
    static { NAMED.put("drab yellow", -0x1.1af316p126F); LIST.add(-0x1.1af316p126F); }

    /**
     * This color constant "faded yellow" has RGBA8888 code {@code B4BB5EFF}, L 0.6784314, A 0.4745098, B 0.5529412, alpha 1.0, hue 0.3214129, saturation 0.36649323, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1af35ap126F}.
     * <pre>
     * <font style='background-color: #B4BB5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4BB5E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4BB5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4BB5E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4BB5E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4BB5E'>&nbsp;@&nbsp;</font><font style='background-color: #B4BB5E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4BB5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4BB5E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_YELLOW = -0x1.1af35ap126F;
    static { NAMED.put("faded yellow", -0x1.1af35ap126F); LIST.add(-0x1.1af35ap126F); }

    /**
     * This color constant "pale yellow" has RGBA8888 code {@code DAE280FF}, L 0.83137256, A 0.4745098, B 0.5529412, alpha 1.0, hue 0.3214129, saturation 0.2813063, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1af3a8p126F}.
     * <pre>
     * <font style='background-color: #DAE280;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAE280; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAE280;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAE280'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAE280'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAE280'>&nbsp;@&nbsp;</font><font style='background-color: #DAE280; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAE280;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAE280; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.1af3a8p126F;
    static { NAMED.put("pale yellow", -0x1.1af3a8p126F); LIST.add(-0x1.1af3a8p126F); }

    /**
     * This color constant "drab lime" has RGBA8888 code {@code 688D3FFF}, L 0.49019608, A 0.45882353, B 0.5411765, alpha 1.0, hue 0.375, saturation 0.32, and chroma 0.116009705.
     * It can be represented as a packed float with the constant {@code -0x1.14eafap126F}.
     * <pre>
     * <font style='background-color: #688D3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #688D3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #688D3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #688D3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #688D3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #688D3F'>&nbsp;@&nbsp;</font><font style='background-color: #688D3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #688D3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #688D3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME = -0x1.14eafap126F;
    static { NAMED.put("drab lime", -0x1.14eafap126F); LIST.add(-0x1.14eafap126F); }

    /**
     * This color constant "faded lime" has RGBA8888 code {@code 8BB460FF}, L 0.62352943, A 0.45882353, B 0.54509807, alpha 1.0, hue 0.36777616, saturation 0.28343925, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.16eb3ep126F}.
     * <pre>
     * <font style='background-color: #8BB460;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BB460; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BB460;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8BB460'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8BB460'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8BB460'>&nbsp;@&nbsp;</font><font style='background-color: #8BB460; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BB460;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BB460; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_LIME = -0x1.16eb3ep126F;
    static { NAMED.put("faded lime", -0x1.16eb3ep126F); LIST.add(-0x1.16eb3ep126F); }

    /**
     * This color constant "pale lime" has RGBA8888 code {@code B4E086FF}, L 0.7882353, A 0.45882353, B 0.54509807, alpha 1.0, hue 0.36777616, saturation 0.21289438, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.16eb92p126F}.
     * <pre>
     * <font style='background-color: #B4E086;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4E086; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4E086;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4E086'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4E086'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4E086'>&nbsp;@&nbsp;</font><font style='background-color: #B4E086; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4E086;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4E086; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME = -0x1.16eb92p126F;
    static { NAMED.put("pale lime", -0x1.16eb92p126F); LIST.add(-0x1.16eb92p126F); }

    /**
     * This color constant "drab green" has RGBA8888 code {@code 449F4DFF}, L 0.5176471, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.40979147, saturation 0.55510205, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df08p126F}.
     * <pre>
     * <font style='background-color: #449F4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #449F4D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #449F4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #449F4D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #449F4D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #449F4D'>&nbsp;@&nbsp;</font><font style='background-color: #449F4D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #449F4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #449F4D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.14df08p126F;
    static { NAMED.put("drab green", -0x1.14df08p126F); LIST.add(-0x1.14df08p126F); }

    /**
     * This color constant "faded green" has RGBA8888 code {@code 64C46CFF}, L 0.6431373, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.40979147, saturation 0.4045211, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df48p126F}.
     * <pre>
     * <font style='background-color: #64C46C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64C46C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64C46C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #64C46C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #64C46C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #64C46C'>&nbsp;@&nbsp;</font><font style='background-color: #64C46C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64C46C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64C46C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_GREEN = -0x1.14df48p126F;
    static { NAMED.put("faded green", -0x1.14df48p126F); LIST.add(-0x1.14df48p126F); }

    /**
     * This color constant "pale green" has RGBA8888 code {@code 83E68AFF}, L 0.76862746, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.40979147, saturation 0.3167538, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df88p126F}.
     * <pre>
     * <font style='background-color: #83E68A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83E68A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83E68A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #83E68A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #83E68A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #83E68A'>&nbsp;@&nbsp;</font><font style='background-color: #83E68A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83E68A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83E68A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.14df88p126F;
    static { NAMED.put("pale green", -0x1.14df88p126F); LIST.add(-0x1.14df88p126F); }

    /**
     * This color constant "drab cyan" has RGBA8888 code {@code 489595FF}, L 0.5137255, A 0.45882353, B 0.4862745, alpha 1.0, hue 0.5511957, saturation 0.49382716, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.f8eb06p125F}.
     * <pre>
     * <font style='background-color: #489595;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #489595; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #489595;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #489595'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #489595'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #489595'>&nbsp;@&nbsp;</font><font style='background-color: #489595; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #489595;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #489595; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN = -0x1.f8eb06p125F;
    static { NAMED.put("drab cyan", -0x1.f8eb06p125F); LIST.add(-0x1.f8eb06p125F); }

    /**
     * This color constant "faded cyan" has RGBA8888 code {@code 69B9B8FF}, L 0.6392157, A 0.45882353, B 0.49019608, alpha 1.0, hue 0.5372009, saturation 0.3697679, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.faeb46p125F}.
     * <pre>
     * <font style='background-color: #69B9B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #69B9B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #69B9B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #69B9B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #69B9B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #69B9B8'>&nbsp;@&nbsp;</font><font style='background-color: #69B9B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #69B9B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #69B9B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_CYAN = -0x1.faeb46p125F;
    static { NAMED.put("faded cyan", -0x1.faeb46p125F); LIST.add(-0x1.faeb46p125F); }

    /**
     * This color constant "pale cyan" has RGBA8888 code {@code 8CE1DFFF}, L 0.7882353, A 0.45882353, B 0.49019608, alpha 1.0, hue 0.5372009, saturation 0.28410304, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.faeb92p125F}.
     * <pre>
     * <font style='background-color: #8CE1DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8CE1DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8CE1DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8CE1DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8CE1DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8CE1DF'>&nbsp;@&nbsp;</font><font style='background-color: #8CE1DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8CE1DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8CE1DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN = -0x1.faeb92p125F;
    static { NAMED.put("pale cyan", -0x1.faeb92p125F); LIST.add(-0x1.faeb92p125F); }

    /**
     * This color constant "drab blue" has RGBA8888 code {@code 1D42A1FF}, L 0.30588236, A 0.49019608, B 0.41568628, alpha 1.0, hue 0.7315659, saturation 0.29650727, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.d4fa9cp125F}.
     * <pre>
     * <font style='background-color: #1D42A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D42A1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D42A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D42A1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D42A1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D42A1'>&nbsp;@&nbsp;</font><font style='background-color: #1D42A1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D42A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D42A1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE = -0x1.d4fa9cp125F;
    static { NAMED.put("drab blue", -0x1.d4fa9cp125F); LIST.add(-0x1.d4fa9cp125F); }

    /**
     * This color constant "faded blue" has RGBA8888 code {@code 3662C9FF}, L 0.40784314, A 0.4862745, B 0.41568628, alpha 1.0, hue 0.72430414, saturation 0.41657063, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.d4f8dp125F}.
     * <pre>
     * <font style='background-color: #3662C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3662C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3662C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3662C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3662C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3662C9'>&nbsp;@&nbsp;</font><font style='background-color: #3662C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3662C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3662C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.d4f8dp125F;
    static { NAMED.put("faded blue", -0x1.d4f8dp125F); LIST.add(-0x1.d4f8dp125F); }

    /**
     * This color constant "pale blue" has RGBA8888 code {@code 4D7DEAFF}, L 0.49803922, A 0.49019608, B 0.41568628, alpha 1.0, hue 0.7315659, saturation 0.65472966, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.d4fafep125F}.
     * <pre>
     * <font style='background-color: #4D7DEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4D7DEA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4D7DEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4D7DEA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4D7DEA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4D7DEA'>&nbsp;@&nbsp;</font><font style='background-color: #4D7DEA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4D7DEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4D7DEA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.d4fafep125F;
    static { NAMED.put("pale blue", -0x1.d4fafep125F); LIST.add(-0x1.d4fafep125F); }

    /**
     * This color constant "drab violet" has RGBA8888 code {@code 654099FF}, L 0.34901962, A 0.53333336, B 0.43529412, alpha 1.0, hue 0.82570946, saturation 0.28528544, and chroma 0.14500555.
     * It can be represented as a packed float with the constant {@code -0x1.df10b2p125F}.
     * <pre>
     * <font style='background-color: #654099;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #654099; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #654099;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #654099'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #654099'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #654099'>&nbsp;@&nbsp;</font><font style='background-color: #654099; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #654099;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #654099; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET = -0x1.df10b2p125F;
    static { NAMED.put("drab violet", -0x1.df10b2p125F); LIST.add(-0x1.df10b2p125F); }

    /**
     * This color constant "faded violet" has RGBA8888 code {@code 835CBCFF}, L 0.44705883, A 0.5372549, B 0.43529412, alpha 1.0, hue 0.8331495, saturation 0.2543748, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.df12e4p125F}.
     * <pre>
     * <font style='background-color: #835CBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #835CBC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #835CBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #835CBC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #835CBC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #835CBC'>&nbsp;@&nbsp;</font><font style='background-color: #835CBC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #835CBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #835CBC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_VIOLET = -0x1.df12e4p125F;
    static { NAMED.put("faded violet", -0x1.df12e4p125F); LIST.add(-0x1.df12e4p125F); }

    /**
     * This color constant "pale violet" has RGBA8888 code {@code A37AE1FF}, L 0.5568628, A 0.5372549, B 0.43529412, alpha 1.0, hue 0.8331495, saturation 0.470741, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.df131cp125F}.
     * <pre>
     * <font style='background-color: #A37AE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A37AE1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A37AE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A37AE1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A37AE1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A37AE1'>&nbsp;@&nbsp;</font><font style='background-color: #A37AE1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A37AE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A37AE1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.df131cp125F;
    static { NAMED.put("pale violet", -0x1.df131cp125F); LIST.add(-0x1.df131cp125F); }

    /**
     * This color constant "drab purple" has RGBA8888 code {@code 7A3896FF}, L 0.35686275, A 0.5568628, B 0.44313726, alpha 1.0, hue 0.875, saturation 0.40430263, and chroma 0.16020387.
     * It can be represented as a packed float with the constant {@code -0x1.e31cb6p125F}.
     * <pre>
     * <font style='background-color: #7A3896;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A3896; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A3896;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A3896'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A3896'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A3896'>&nbsp;@&nbsp;</font><font style='background-color: #7A3896; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A3896;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A3896; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE = -0x1.e31cb6p125F;
    static { NAMED.put("drab purple", -0x1.e31cb6p125F); LIST.add(-0x1.e31cb6p125F); }

    /**
     * This color constant "faded purple" has RGBA8888 code {@code 9C56BCFF}, L 0.4627451, A 0.5568628, B 0.4392157, alpha 1.0, hue 0.8697009, saturation 0.2851153, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.e11cecp125F}.
     * <pre>
     * <font style='background-color: #9C56BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C56BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C56BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C56BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C56BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C56BC'>&nbsp;@&nbsp;</font><font style='background-color: #9C56BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C56BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C56BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_PURPLE = -0x1.e11cecp125F;
    static { NAMED.put("faded purple", -0x1.e11cecp125F); LIST.add(-0x1.e11cecp125F); }

    /**
     * This color constant "pale purple" has RGBA8888 code {@code C479E6FF}, L 0.5921569, A 0.5568628, B 0.4392157, alpha 1.0, hue 0.8697009, saturation 0.4923161, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.e11d2ep125F}.
     * <pre>
     * <font style='background-color: #C479E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C479E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C479E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C479E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C479E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C479E6'>&nbsp;@&nbsp;</font><font style='background-color: #C479E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C479E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C479E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.e11d2ep125F;
    static { NAMED.put("pale purple", -0x1.e11d2ep125F); LIST.add(-0x1.e11d2ep125F); }

    /**
     * This color constant "drab magenta" has RGBA8888 code {@code 9A4799FF}, L 0.41960785, A 0.5647059, B 0.45882353, alpha 1.0, hue 0.90979147, saturation 0.3260696, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.eb20d6p125F}.
     * <pre>
     * <font style='background-color: #9A4799;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A4799; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A4799;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A4799'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A4799'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A4799'>&nbsp;@&nbsp;</font><font style='background-color: #9A4799; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A4799;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A4799; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA = -0x1.eb20d6p125F;
    static { NAMED.put("drab magenta", -0x1.eb20d6p125F); LIST.add(-0x1.eb20d6p125F); }

    /**
     * This color constant "faded magenta" has RGBA8888 code {@code C167BFFF}, L 0.5372549, A 0.5647059, B 0.45882353, alpha 1.0, hue 0.90979147, saturation 0.22479339, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.eb2112p125F}.
     * <pre>
     * <font style='background-color: #C167BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C167BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C167BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C167BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C167BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C167BF'>&nbsp;@&nbsp;</font><font style='background-color: #C167BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C167BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C167BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_MAGENTA = -0x1.eb2112p125F;
    static { NAMED.put("faded magenta", -0x1.eb2112p125F); LIST.add(-0x1.eb2112p125F); }

    /**
     * This color constant "pale magenta" has RGBA8888 code {@code E182DFFF}, L 0.6431373, A 0.5647059, B 0.45490196, alpha 1.0, hue 0.90311134, saturation 0.3551166, and chroma 0.15712644.
     * It can be represented as a packed float with the constant {@code -0x1.e92148p125F}.
     * <pre>
     * <font style='background-color: #E182DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E182DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E182DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E182DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E182DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E182DF'>&nbsp;@&nbsp;</font><font style='background-color: #E182DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E182DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E182DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA = -0x1.e92148p125F;
    static { NAMED.put("pale magenta", -0x1.e92148p125F); LIST.add(-0x1.e92148p125F); }

    /**
     * This color constant "deep pure red" has RGBA8888 code {@code C6221BFF}, L 0.40392157, A 0.5882353, B 0.54901963, alpha 1.0, hue 0.080711745, saturation 0.7485347, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.192ccep126F}.
     * <pre>
     * <font style='background-color: #C6221B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6221B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6221B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6221B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6221B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6221B'>&nbsp;@&nbsp;</font><font style='background-color: #C6221B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6221B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6221B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_RED = -0x1.192ccep126F;
    static { NAMED.put("deep pure red", -0x1.192ccep126F); LIST.add(-0x1.192ccep126F); }

    /**
     * This color constant "true pure red" has RGBA8888 code {@code DB362AFF}, L 0.45882353, A 0.5882353, B 0.54901963, alpha 1.0, hue 0.080711745, saturation 0.6176796, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.192ceap126F}.
     * <pre>
     * <font style='background-color: #DB362A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB362A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB362A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB362A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB362A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB362A'>&nbsp;@&nbsp;</font><font style='background-color: #DB362A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB362A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB362A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_RED = -0x1.192ceap126F;
    static { NAMED.put("true pure red", -0x1.192ceap126F); LIST.add(-0x1.192ceap126F); }

    /**
     * This color constant "bright pure red" has RGBA8888 code {@code F3493BFF}, L 0.52156866, A 0.5882353, B 0.54901963, alpha 1.0, hue 0.080711745, saturation 0.6572013, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.192d0ap126F}.
     * <pre>
     * <font style='background-color: #F3493B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3493B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3493B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3493B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3493B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3493B'>&nbsp;@&nbsp;</font><font style='background-color: #F3493B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3493B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3493B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_RED = -0x1.192d0ap126F;
    static { NAMED.put("bright pure red", -0x1.192d0ap126F); LIST.add(-0x1.192d0ap126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code B7503CFF}, L 0.44313726, A 0.5568628, B 0.5372549, alpha 1.0, hue 0.09232193, saturation 0.36355388, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131ce2p126F}.
     * <pre>
     * <font style='background-color: #B7503C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7503C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7503C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B7503C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B7503C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B7503C'>&nbsp;@&nbsp;</font><font style='background-color: #B7503C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7503C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7503C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.131ce2p126F;
    static { NAMED.put("deep brown red", -0x1.131ce2p126F); LIST.add(-0x1.131ce2p126F); }

    /**
     * This color constant "true brown red" has RGBA8888 code {@code C85D48FF}, L 0.49019608, A 0.5568628, B 0.5372549, alpha 1.0, hue 0.09232193, saturation 0.31780025, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131cfap126F}.
     * <pre>
     * <font style='background-color: #C85D48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C85D48; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C85D48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C85D48'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C85D48'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C85D48'>&nbsp;@&nbsp;</font><font style='background-color: #C85D48; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C85D48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C85D48; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_RED = -0x1.131cfap126F;
    static { NAMED.put("true brown red", -0x1.131cfap126F); LIST.add(-0x1.131cfap126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code DB6C56FF}, L 0.54509807, A 0.5568628, B 0.5372549, alpha 1.0, hue 0.09232193, saturation 0.35123092, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131d16p126F}.
     * <pre>
     * <font style='background-color: #DB6C56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB6C56; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB6C56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB6C56'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB6C56'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB6C56'>&nbsp;@&nbsp;</font><font style='background-color: #DB6C56; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB6C56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB6C56; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.131d16p126F;
    static { NAMED.put("bright brown red", -0x1.131d16p126F); LIST.add(-0x1.131d16p126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code AF5B45FF}, L 0.4509804, A 0.54509807, B 0.53333336, alpha 1.0, hue 0.101316266, saturation 0.2753977, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.1116e6p126F}.
     * <pre>
     * <font style='background-color: #AF5B45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF5B45; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF5B45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF5B45'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF5B45'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF5B45'>&nbsp;@&nbsp;</font><font style='background-color: #AF5B45; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF5B45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF5B45; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.1116e6p126F;
    static { NAMED.put("deep red brown", -0x1.1116e6p126F); LIST.add(-0x1.1116e6p126F); }

    /**
     * This color constant "true red brown" has RGBA8888 code {@code C16A53FF}, L 0.5058824, A 0.5411765, B 0.53333336, alpha 1.0, hue 0.10831932, saturation 0.23699374, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.111502p126F}.
     * <pre>
     * <font style='background-color: #C16A53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C16A53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C16A53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C16A53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C16A53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C16A53'>&nbsp;@&nbsp;</font><font style='background-color: #C16A53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C16A53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C16A53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_BROWN = -0x1.111502p126F;
    static { NAMED.put("true red brown", -0x1.111502p126F); LIST.add(-0x1.111502p126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code D77C63FF}, L 0.57254905, A 0.5411765, B 0.53333336, alpha 1.0, hue 0.10831932, saturation 0.24577056, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.111524p126F}.
     * <pre>
     * <font style='background-color: #D77C63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D77C63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D77C63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D77C63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D77C63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D77C63'>&nbsp;@&nbsp;</font><font style='background-color: #D77C63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D77C63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D77C63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.111524p126F;
    static { NAMED.put("bright red brown", -0x1.111524p126F); LIST.add(-0x1.111524p126F); }

    /**
     * This color constant "deep pure brown" has RGBA8888 code {@code AC6A52FF}, L 0.47843137, A 0.53333336, B 0.5294118, alpha 1.0, hue 0.115073085, saturation 0.20154887, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.0f10f4p126F}.
     * <pre>
     * <font style='background-color: #AC6A52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC6A52; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC6A52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC6A52'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC6A52'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC6A52'>&nbsp;@&nbsp;</font><font style='background-color: #AC6A52; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC6A52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC6A52; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BROWN = -0x1.0f10f4p126F;
    static { NAMED.put("deep pure brown", -0x1.0f10f4p126F); LIST.add(-0x1.0f10f4p126F); }

    /**
     * This color constant "true pure brown" has RGBA8888 code {@code C07B62FF}, L 0.5411765, A 0.53333336, B 0.5294118, alpha 1.0, hue 0.115073085, saturation 0.16686957, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.0f1114p126F}.
     * <pre>
     * <font style='background-color: #C07B62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C07B62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C07B62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C07B62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C07B62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C07B62'>&nbsp;@&nbsp;</font><font style='background-color: #C07B62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C07B62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C07B62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BROWN = -0x1.0f1114p126F;
    static { NAMED.put("true pure brown", -0x1.0f1114p126F); LIST.add(-0x1.0f1114p126F); }

    /**
     * This color constant "bright pure brown" has RGBA8888 code {@code D0886FFF}, L 0.5921569, A 0.53333336, B 0.5294118, alpha 1.0, hue 0.115073085, saturation 0.19379772, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.0f112ep126F}.
     * <pre>
     * <font style='background-color: #D0886F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0886F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0886F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0886F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0886F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0886F'>&nbsp;@&nbsp;</font><font style='background-color: #D0886F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0886F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0886F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BROWN = -0x1.0f112ep126F;
    static { NAMED.put("bright pure brown", -0x1.0f112ep126F); LIST.add(-0x1.0f112ep126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code B26B48FF}, L 0.4862745, A 0.53333336, B 0.5372549, alpha 1.0, hue 0.13382626, saturation 0.31397173, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.1310f8p126F}.
     * <pre>
     * <font style='background-color: #B26B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B26B48; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B26B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B26B48'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B26B48'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B26B48'>&nbsp;@&nbsp;</font><font style='background-color: #B26B48; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B26B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B26B48; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.1310f8p126F;
    static { NAMED.put("deep orange brown", -0x1.1310f8p126F); LIST.add(-0x1.1310f8p126F); }

    /**
     * This color constant "true orange brown" has RGBA8888 code {@code C37A56FF}, L 0.5411765, A 0.53333336, B 0.5372549, alpha 1.0, hue 0.13382626, saturation 0.26527905, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.131114p126F}.
     * <pre>
     * <font style='background-color: #C37A56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C37A56; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C37A56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C37A56'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C37A56'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C37A56'>&nbsp;@&nbsp;</font><font style='background-color: #C37A56; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C37A56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C37A56; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_BROWN = -0x1.131114p126F;
    static { NAMED.put("true orange brown", -0x1.131114p126F); LIST.add(-0x1.131114p126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code D78B65FF}, L 0.6039216, A 0.53333336, B 0.5372549, alpha 1.0, hue 0.13382626, saturation 0.24507494, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.131134p126F}.
     * <pre>
     * <font style='background-color: #D78B65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D78B65; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D78B65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D78B65'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D78B65'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D78B65'>&nbsp;@&nbsp;</font><font style='background-color: #D78B65; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D78B65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D78B65; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.131134p126F;
    static { NAMED.put("bright orange brown", -0x1.131134p126F); LIST.add(-0x1.131134p126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code B46C43FF}, L 0.49019608, A 0.5294118, B 0.5411765, alpha 1.0, hue 0.15127131, saturation 0.36871973, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.150efap126F}.
     * <pre>
     * <font style='background-color: #B46C43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B46C43; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B46C43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B46C43'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B46C43'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B46C43'>&nbsp;@&nbsp;</font><font style='background-color: #B46C43; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B46C43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B46C43; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.150efap126F;
    static { NAMED.put("deep brown orange", -0x1.150efap126F); LIST.add(-0x1.150efap126F); }

    /**
     * This color constant "true brown orange" has RGBA8888 code {@code C87C52FF}, L 0.54901963, A 0.53333336, B 0.5411765, alpha 1.0, hue 0.14168067, saturation 0.31034115, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.151118p126F}.
     * <pre>
     * <font style='background-color: #C87C52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C87C52; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C87C52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C87C52'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C87C52'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C87C52'>&nbsp;@&nbsp;</font><font style='background-color: #C87C52; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C87C52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C87C52; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_ORANGE = -0x1.151118p126F;
    static { NAMED.put("true brown orange", -0x1.151118p126F); LIST.add(-0x1.151118p126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code DD8E63FF}, L 0.61960787, A 0.53333336, B 0.5372549, alpha 1.0, hue 0.13382626, saturation 0.2763312, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.13113cp126F}.
     * <pre>
     * <font style='background-color: #DD8E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD8E63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD8E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD8E63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD8E63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD8E63'>&nbsp;@&nbsp;</font><font style='background-color: #DD8E63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD8E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD8E63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.13113cp126F;
    static { NAMED.put("bright brown orange", -0x1.13113cp126F); LIST.add(-0x1.13113cp126F); }

    /**
     * This color constant "deep pure orange" has RGBA8888 code {@code C76D2AFF}, L 0.5137255, A 0.5372549, B 0.5568628, alpha 1.0, hue 0.15767807, saturation 0.66546714, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.1d1306p126F}.
     * <pre>
     * <font style='background-color: #C76D2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C76D2A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C76D2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C76D2A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C76D2A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C76D2A'>&nbsp;@&nbsp;</font><font style='background-color: #C76D2A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C76D2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C76D2A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_ORANGE = -0x1.1d1306p126F;
    static { NAMED.put("deep pure orange", -0x1.1d1306p126F); LIST.add(-0x1.1d1306p126F); }

    /**
     * This color constant "true pure orange" has RGBA8888 code {@code DD7F3CFF}, L 0.5803922, A 0.5372549, B 0.5568628, alpha 1.0, hue 0.15767807, saturation 0.5559024, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.1d1328p126F}.
     * <pre>
     * <font style='background-color: #DD7F3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD7F3C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD7F3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD7F3C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD7F3C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD7F3C'>&nbsp;@&nbsp;</font><font style='background-color: #DD7F3C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD7F3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD7F3C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_ORANGE = -0x1.1d1328p126F;
    static { NAMED.put("true pure orange", -0x1.1d1328p126F); LIST.add(-0x1.1d1328p126F); }

    /**
     * This color constant "bright pure orange" has RGBA8888 code {@code F0904BFF}, L 0.6431373, A 0.5372549, B 0.5568628, alpha 1.0, hue 0.15767807, saturation 0.5110001, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.1d1348p126F}.
     * <pre>
     * <font style='background-color: #F0904B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0904B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0904B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0904B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0904B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0904B'>&nbsp;@&nbsp;</font><font style='background-color: #F0904B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0904B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0904B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_ORANGE = -0x1.1d1348p126F;
    static { NAMED.put("bright pure orange", -0x1.1d1348p126F); LIST.add(-0x1.1d1348p126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code C5762FFF}, L 0.5294118, A 0.5294118, B 0.5568628, alpha 1.0, hue 0.17402768, saturation 0.61895776, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.1d0f0ep126F}.
     * <pre>
     * <font style='background-color: #C5762F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5762F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5762F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C5762F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C5762F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C5762F'>&nbsp;@&nbsp;</font><font style='background-color: #C5762F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5762F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5762F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.1d0f0ep126F;
    static { NAMED.put("deep saffron orange", -0x1.1d0f0ep126F); LIST.add(-0x1.1d0f0ep126F); }

    /**
     * This color constant "true saffron orange" has RGBA8888 code {@code D8873FFF}, L 0.5921569, A 0.5294118, B 0.5568628, alpha 1.0, hue 0.17402768, saturation 0.5383159, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.1d0f2ep126F}.
     * <pre>
     * <font style='background-color: #D8873F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8873F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8873F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D8873F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D8873F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D8873F'>&nbsp;@&nbsp;</font><font style='background-color: #D8873F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8873F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8873F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_ORANGE = -0x1.1d0f2ep126F;
    static { NAMED.put("true saffron orange", -0x1.1d0f2ep126F); LIST.add(-0x1.1d0f2ep126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code EF9B51FF}, L 0.6666667, A 0.5294118, B 0.5568628, alpha 1.0, hue 0.17402768, saturation 0.47246537, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.1d0f54p126F}.
     * <pre>
     * <font style='background-color: #EF9B51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF9B51; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF9B51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF9B51'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF9B51'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF9B51'>&nbsp;@&nbsp;</font><font style='background-color: #EF9B51; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF9B51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF9B51; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.1d0f54p126F;
    static { NAMED.put("bright saffron orange", -0x1.1d0f54p126F); LIST.add(-0x1.1d0f54p126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code CB8223FF}, L 0.56078434, A 0.52156866, B 0.5647059, alpha 1.0, hue 0.19880433, saturation 0.7376924, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.210b1ep126F}.
     * <pre>
     * <font style='background-color: #CB8223;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB8223; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB8223;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB8223'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB8223'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB8223'>&nbsp;@&nbsp;</font><font style='background-color: #CB8223; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB8223;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB8223; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.210b1ep126F;
    static { NAMED.put("deep orange saffron", -0x1.210b1ep126F); LIST.add(-0x1.210b1ep126F); }

    /**
     * This color constant "true orange saffron" has RGBA8888 code {@code E19639FF}, L 0.63529414, A 0.52156866, B 0.5647059, alpha 1.0, hue 0.19880433, saturation 0.611034, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.210b44p126F}.
     * <pre>
     * <font style='background-color: #E19639;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E19639; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E19639;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E19639'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E19639'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E19639'>&nbsp;@&nbsp;</font><font style='background-color: #E19639; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E19639;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E19639; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_SAFFRON = -0x1.210b44p126F;
    static { NAMED.put("true orange saffron", -0x1.210b44p126F); LIST.add(-0x1.210b44p126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code F4A748FF}, L 0.69803923, A 0.52156866, B 0.5647059, alpha 1.0, hue 0.19880433, saturation 0.55960226, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.210b64p126F}.
     * <pre>
     * <font style='background-color: #F4A748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4A748; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4A748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F4A748'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F4A748'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F4A748'>&nbsp;@&nbsp;</font><font style='background-color: #F4A748; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4A748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4A748; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.210b64p126F;
    static { NAMED.put("bright orange saffron", -0x1.210b64p126F); LIST.add(-0x1.210b64p126F); }

    /**
     * This color constant "deep pure saffron" has RGBA8888 code {@code C58C33FF}, L 0.5764706, A 0.50980395, B 0.56078434, alpha 1.0, hue 0.22453669, saturation 0.60112786, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.1f0526p126F}.
     * <pre>
     * <font style='background-color: #C58C33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C58C33; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C58C33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C58C33'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C58C33'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C58C33'>&nbsp;@&nbsp;</font><font style='background-color: #C58C33; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C58C33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C58C33; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_SAFFRON = -0x1.1f0526p126F;
    static { NAMED.put("deep pure saffron", -0x1.1f0526p126F); LIST.add(-0x1.1f0526p126F); }

    /**
     * This color constant "true pure saffron" has RGBA8888 code {@code D99E44FF}, L 0.6431373, A 0.50980395, B 0.56078434, alpha 1.0, hue 0.22453669, saturation 0.54588234, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.1f0548p126F}.
     * <pre>
     * <font style='background-color: #D99E44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D99E44; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D99E44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D99E44'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D99E44'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D99E44'>&nbsp;@&nbsp;</font><font style='background-color: #D99E44; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D99E44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D99E44; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_SAFFRON = -0x1.1f0548p126F;
    static { NAMED.put("true pure saffron", -0x1.1f0548p126F); LIST.add(-0x1.1f0548p126F); }

    /**
     * This color constant "bright pure saffron" has RGBA8888 code {@code EEB156FF}, L 0.7176471, A 0.50980395, B 0.56078434, alpha 1.0, hue 0.22453669, saturation 0.45600647, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.1f056ep126F}.
     * <pre>
     * <font style='background-color: #EEB156;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEB156; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEB156;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EEB156'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EEB156'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EEB156'>&nbsp;@&nbsp;</font><font style='background-color: #EEB156; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEB156;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEB156; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_SAFFRON = -0x1.1f056ep126F;
    static { NAMED.put("bright pure saffron", -0x1.1f056ep126F); LIST.add(-0x1.1f056ep126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code C99D2AFF}, L 0.61960787, A 0.49803922, B 0.5686275, alpha 1.0, hue 0.2545336, saturation 0.71185946, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.22ff3cp126F}.
     * <pre>
     * <font style='background-color: #C99D2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C99D2A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C99D2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C99D2A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C99D2A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C99D2A'>&nbsp;@&nbsp;</font><font style='background-color: #C99D2A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C99D2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C99D2A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.22ff3cp126F;
    static { NAMED.put("deep yellow saffron", -0x1.22ff3cp126F); LIST.add(-0x1.22ff3cp126F); }

    /**
     * This color constant "true yellow saffron" has RGBA8888 code {@code DCAF3DFF}, L 0.6862745, A 0.49803922, B 0.5686275, alpha 1.0, hue 0.2545336, saturation 0.61911374, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.22ff5ep126F}.
     * <pre>
     * <font style='background-color: #DCAF3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCAF3D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCAF3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DCAF3D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DCAF3D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DCAF3D'>&nbsp;@&nbsp;</font><font style='background-color: #DCAF3D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCAF3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCAF3D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_SAFFRON = -0x1.22ff5ep126F;
    static { NAMED.put("true yellow saffron", -0x1.22ff5ep126F); LIST.add(-0x1.22ff5ep126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code F2C350FF}, L 0.7647059, A 0.49803922, B 0.5686275, alpha 1.0, hue 0.2545336, saturation 0.5433795, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.22ff86p126F}.
     * <pre>
     * <font style='background-color: #F2C350;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2C350; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2C350;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2C350'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2C350'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2C350'>&nbsp;@&nbsp;</font><font style='background-color: #F2C350; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2C350;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2C350; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.22ff86p126F;
    static { NAMED.put("bright yellow saffron", -0x1.22ff86p126F); LIST.add(-0x1.22ff86p126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code CFB63EFF}, L 0.6901961, A 0.4862745, B 0.5686275, alpha 1.0, hue 0.28142345, saturation 0.61538464, and chroma 0.13942632.
     * It can be represented as a packed float with the constant {@code -0x1.22f96p126F}.
     * <pre>
     * <font style='background-color: #CFB63E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB63E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB63E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFB63E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFB63E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFB63E'>&nbsp;@&nbsp;</font><font style='background-color: #CFB63E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB63E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB63E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.22f96p126F;
    static { NAMED.put("deep saffron yellow", -0x1.22f96p126F); LIST.add(-0x1.22f96p126F); }

    /**
     * This color constant "true saffron yellow" has RGBA8888 code {@code E4CA51FF}, L 0.76862746, A 0.4862745, B 0.5686275, alpha 1.0, hue 0.28142345, saturation 0.5416091, and chroma 0.13942632.
     * It can be represented as a packed float with the constant {@code -0x1.22f988p126F}.
     * <pre>
     * <font style='background-color: #E4CA51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4CA51; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4CA51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E4CA51'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E4CA51'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E4CA51'>&nbsp;@&nbsp;</font><font style='background-color: #E4CA51; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4CA51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4CA51; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_YELLOW = -0x1.22f988p126F;
    static { NAMED.put("true saffron yellow", -0x1.22f988p126F); LIST.add(-0x1.22f988p126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code F6DC61FF}, L 0.8392157, A 0.4862745, B 0.5686275, alpha 1.0, hue 0.28142345, saturation 0.4803469, and chroma 0.13942632.
     * It can be represented as a packed float with the constant {@code -0x1.22f9acp126F}.
     * <pre>
     * <font style='background-color: #F6DC61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6DC61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6DC61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6DC61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6DC61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6DC61'>&nbsp;@&nbsp;</font><font style='background-color: #F6DC61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6DC61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6DC61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.22f9acp126F;
    static { NAMED.put("bright saffron yellow", -0x1.22f9acp126F); LIST.add(-0x1.22f9acp126F); }

    /**
     * This color constant "deep pure yellow" has RGBA8888 code {@code BEC22BFF}, L 0.7019608, A 0.46666667, B 0.5764706, alpha 1.0, hue 0.31541443, saturation 0.73870015, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.26ef66p126F}.
     * <pre>
     * <font style='background-color: #BEC22B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEC22B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEC22B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BEC22B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BEC22B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BEC22B'>&nbsp;@&nbsp;</font><font style='background-color: #BEC22B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEC22B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEC22B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_YELLOW = -0x1.26ef66p126F;
    static { NAMED.put("deep pure yellow", -0x1.26ef66p126F); LIST.add(-0x1.26ef66p126F); }

    /**
     * This color constant "true pure yellow" has RGBA8888 code {@code D2D741FF}, L 0.78039217, A 0.46666667, B 0.5764706, alpha 1.0, hue 0.31541443, saturation 0.65668935, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.26ef8ep126F}.
     * <pre>
     * <font style='background-color: #D2D741;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2D741; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2D741;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2D741'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2D741'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2D741'>&nbsp;@&nbsp;</font><font style='background-color: #D2D741; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2D741;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2D741; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_YELLOW = -0x1.26ef8ep126F;
    static { NAMED.put("true pure yellow", -0x1.26ef8ep126F); LIST.add(-0x1.26ef8ep126F); }

    /**
     * This color constant "bright pure yellow" has RGBA8888 code {@code E8ED56FF}, L 0.87058824, A 0.46666667, B 0.5764706, alpha 1.0, hue 0.31541443, saturation 0.5669982, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.26efbcp126F}.
     * <pre>
     * <font style='background-color: #E8ED56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8ED56; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8ED56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E8ED56'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E8ED56'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E8ED56'>&nbsp;@&nbsp;</font><font style='background-color: #E8ED56; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8ED56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8ED56; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_YELLOW = -0x1.26efbcp126F;
    static { NAMED.put("bright pure yellow", -0x1.26efbcp126F); LIST.add(-0x1.26efbcp126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code A9C13CFF}, L 0.6784314, A 0.45882353, B 0.5686275, alpha 1.0, hue 0.33601886, saturation 0.60444444, and chroma 0.15944009.
     * It can be represented as a packed float with the constant {@code -0x1.22eb5ap126F}.
     * <pre>
     * <font style='background-color: #A9C13C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9C13C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9C13C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A9C13C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A9C13C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A9C13C'>&nbsp;@&nbsp;</font><font style='background-color: #A9C13C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9C13C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9C13C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.22eb5ap126F;
    static { NAMED.put("deep lime yellow", -0x1.22eb5ap126F); LIST.add(-0x1.22eb5ap126F); }

    /**
     * This color constant "true lime yellow" has RGBA8888 code {@code BCD650FF}, L 0.75686276, A 0.45882353, B 0.5686275, alpha 1.0, hue 0.33601886, saturation 0.521889, and chroma 0.15944009.
     * It can be represented as a packed float with the constant {@code -0x1.22eb82p126F}.
     * <pre>
     * <font style='background-color: #BCD650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCD650; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCD650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BCD650'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BCD650'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BCD650'>&nbsp;@&nbsp;</font><font style='background-color: #BCD650; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCD650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCD650; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_YELLOW = -0x1.22eb82p126F;
    static { NAMED.put("true lime yellow", -0x1.22eb82p126F); LIST.add(-0x1.22eb82p126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code D3EE66FF}, L 0.85490197, A 0.45882353, B 0.5686275, alpha 1.0, hue 0.33601886, saturation 0.45516017, and chroma 0.15944009.
     * It can be represented as a packed float with the constant {@code -0x1.22ebb4p126F}.
     * <pre>
     * <font style='background-color: #D3EE66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3EE66; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3EE66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3EE66'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3EE66'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3EE66'>&nbsp;@&nbsp;</font><font style='background-color: #D3EE66; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3EE66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3EE66; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.22ebb4p126F;
    static { NAMED.put("bright lime yellow", -0x1.22ebb4p126F); LIST.add(-0x1.22ebb4p126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code 9EC725FF}, L 0.68235296, A 0.44705883, B 0.5764706, alpha 1.0, hue 0.34638813, saturation 0.757512, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.26e55cp126F}.
     * <pre>
     * <font style='background-color: #9EC725;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9EC725; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9EC725;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9EC725'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9EC725'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9EC725'>&nbsp;@&nbsp;</font><font style='background-color: #9EC725; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9EC725;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9EC725; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.26e55cp126F;
    static { NAMED.put("deep yellow lime", -0x1.26e55cp126F); LIST.add(-0x1.26e55cp126F); }

    /**
     * This color constant "true yellow lime" has RGBA8888 code {@code B1DC3CFF}, L 0.7607843, A 0.44705883, B 0.5764706, alpha 1.0, hue 0.34638813, saturation 0.6574622, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.26e584p126F}.
     * <pre>
     * <font style='background-color: #B1DC3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1DC3C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1DC3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1DC3C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1DC3C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1DC3C'>&nbsp;@&nbsp;</font><font style='background-color: #B1DC3C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1DC3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1DC3C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_LIME = -0x1.26e584p126F;
    static { NAMED.put("true yellow lime", -0x1.26e584p126F); LIST.add(-0x1.26e584p126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code C3F050FF}, L 0.8392157, A 0.44705883, B 0.5764706, alpha 1.0, hue 0.34638813, saturation 0.594884, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.26e5acp126F}.
     * <pre>
     * <font style='background-color: #C3F050;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3F050; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3F050;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3F050'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3F050'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3F050'>&nbsp;@&nbsp;</font><font style='background-color: #C3F050; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3F050;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3F050; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.26e5acp126F;
    static { NAMED.put("bright yellow lime", -0x1.26e5acp126F); LIST.add(-0x1.26e5acp126F); }

    /**
     * This color constant "deep pure lime" has RGBA8888 code {@code 89BF34FF}, L 0.64705884, A 0.44313726, B 0.5686275, alpha 1.0, hue 0.3601329, saturation 0.62487715, and chroma 0.17755185.
     * It can be represented as a packed float with the constant {@code -0x1.22e34ap126F}.
     * <pre>
     * <font style='background-color: #89BF34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #89BF34; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #89BF34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #89BF34'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #89BF34'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #89BF34'>&nbsp;@&nbsp;</font><font style='background-color: #89BF34; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #89BF34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #89BF34; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_LIME = -0x1.22e34ap126F;
    static { NAMED.put("deep pure lime", -0x1.22e34ap126F); LIST.add(-0x1.22e34ap126F); }

    /**
     * This color constant "true pure lime" has RGBA8888 code {@code 9CD448FF}, L 0.72156864, A 0.44313726, B 0.5686275, alpha 1.0, hue 0.3601329, saturation 0.5462357, and chroma 0.17755185.
     * It can be represented as a packed float with the constant {@code -0x1.22e37p126F}.
     * <pre>
     * <font style='background-color: #9CD448;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CD448; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CD448;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9CD448'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9CD448'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9CD448'>&nbsp;@&nbsp;</font><font style='background-color: #9CD448; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CD448;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CD448; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_LIME = -0x1.22e37p126F;
    static { NAMED.put("true pure lime", -0x1.22e37p126F); LIST.add(-0x1.22e37p126F); }

    /**
     * This color constant "bright pure lime" has RGBA8888 code {@code B1EB5DFF}, L 0.8117647, A 0.44313726, B 0.5686275, alpha 1.0, hue 0.3601329, saturation 0.481557, and chroma 0.17755185.
     * It can be represented as a packed float with the constant {@code -0x1.22e39ep126F}.
     * <pre>
     * <font style='background-color: #B1EB5D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1EB5D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1EB5D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1EB5D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1EB5D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1EB5D'>&nbsp;@&nbsp;</font><font style='background-color: #B1EB5D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1EB5D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1EB5D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_LIME = -0x1.22e39ep126F;
    static { NAMED.put("bright pure lime", -0x1.22e39ep126F); LIST.add(-0x1.22e39ep126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 6AC829FF}, L 0.64705884, A 0.42352942, B 0.57254905, alpha 1.0, hue 0.37918407, saturation 0.6946698, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.24d94ap126F}.
     * <pre>
     * <font style='background-color: #6AC829;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AC829; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AC829;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6AC829'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6AC829'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6AC829'>&nbsp;@&nbsp;</font><font style='background-color: #6AC829; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AC829;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AC829; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.24d94ap126F;
    static { NAMED.put("deep green lime", -0x1.24d94ap126F); LIST.add(-0x1.24d94ap126F); }

    /**
     * This color constant "true green lime" has RGBA8888 code {@code 7EDE41FF}, L 0.7294118, A 0.42352942, B 0.57254905, alpha 1.0, hue 0.37918407, saturation 0.59831274, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.24d974p126F}.
     * <pre>
     * <font style='background-color: #7EDE41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7EDE41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7EDE41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7EDE41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7EDE41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7EDE41'>&nbsp;@&nbsp;</font><font style='background-color: #7EDE41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7EDE41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7EDE41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_LIME = -0x1.24d974p126F;
    static { NAMED.put("true green lime", -0x1.24d974p126F); LIST.add(-0x1.24d974p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code 8EF152FF}, L 0.8, A 0.42352942, B 0.57254905, alpha 1.0, hue 0.37918407, saturation 0.5349623, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.24d998p126F}.
     * <pre>
     * <font style='background-color: #8EF152;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8EF152; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8EF152;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8EF152'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8EF152'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8EF152'>&nbsp;@&nbsp;</font><font style='background-color: #8EF152; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8EF152;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8EF152; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.24d998p126F;
    static { NAMED.put("bright green lime", -0x1.24d998p126F); LIST.add(-0x1.24d998p126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 54C339FF}, L 0.62352943, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.6079561, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.20d73ep126F}.
     * <pre>
     * <font style='background-color: #54C339;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54C339; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54C339;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #54C339'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #54C339'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #54C339'>&nbsp;@&nbsp;</font><font style='background-color: #54C339; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54C339;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54C339; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.20d73ep126F;
    static { NAMED.put("deep lime green", -0x1.20d73ep126F); LIST.add(-0x1.20d73ep126F); }

    /**
     * This color constant "true lime green" has RGBA8888 code {@code 65D64BFF}, L 0.6901961, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.5418358, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.20d76p126F}.
     * <pre>
     * <font style='background-color: #65D64B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65D64B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65D64B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65D64B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65D64B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65D64B'>&nbsp;@&nbsp;</font><font style='background-color: #65D64B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65D64B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65D64B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_GREEN = -0x1.20d76p126F;
    static { NAMED.put("true lime green", -0x1.20d76p126F); LIST.add(-0x1.20d76p126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code 78EB5DFF}, L 0.76862746, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.4733222, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.20d788p126F}.
     * <pre>
     * <font style='background-color: #78EB5D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78EB5D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78EB5D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #78EB5D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #78EB5D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #78EB5D'>&nbsp;@&nbsp;</font><font style='background-color: #78EB5D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78EB5D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78EB5D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.20d788p126F;
    static { NAMED.put("bright lime green", -0x1.20d788p126F); LIST.add(-0x1.20d788p126F); }

    /**
     * This color constant "deep pure green" has RGBA8888 code {@code 29C742FF}, L 0.61960787, A 0.40784314, B 0.56078434, alpha 1.0, hue 0.40718868, saturation 0.895417, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.1ed13cp126F}.
     * <pre>
     * <font style='background-color: #29C742;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #29C742; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #29C742;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #29C742'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #29C742'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #29C742'>&nbsp;@&nbsp;</font><font style='background-color: #29C742; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #29C742;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #29C742; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_GREEN = -0x1.1ed13cp126F;
    static { NAMED.put("deep pure green", -0x1.1ed13cp126F); LIST.add(-0x1.1ed13cp126F); }

    /**
     * This color constant "true pure green" has RGBA8888 code {@code 3FDA53FF}, L 0.6862745, A 0.40784314, B 0.56078434, alpha 1.0, hue 0.40718868, saturation 0.7861616, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.1ed15ep126F}.
     * <pre>
     * <font style='background-color: #3FDA53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FDA53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FDA53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FDA53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FDA53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FDA53'>&nbsp;@&nbsp;</font><font style='background-color: #3FDA53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FDA53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FDA53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_GREEN = -0x1.1ed15ep126F;
    static { NAMED.put("true pure green", -0x1.1ed15ep126F); LIST.add(-0x1.1ed15ep126F); }

    /**
     * This color constant "bright pure green" has RGBA8888 code {@code 55F166FF}, L 0.76862746, A 0.40784314, B 0.56078434, alpha 1.0, hue 0.40718868, saturation 0.65628076, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.1ed188p126F}.
     * <pre>
     * <font style='background-color: #55F166;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55F166; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55F166;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #55F166'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #55F166'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #55F166'>&nbsp;@&nbsp;</font><font style='background-color: #55F166; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55F166;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55F166; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_GREEN = -0x1.1ed188p126F;
    static { NAMED.put("bright pure green", -0x1.1ed188p126F); LIST.add(-0x1.1ed188p126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 49C88EFF}, L 0.6509804, A 0.43137255, B 0.52156866, alpha 1.0, hue 0.451547, saturation 0.650163, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add4cp126F}.
     * <pre>
     * <font style='background-color: #49C88E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49C88E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49C88E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #49C88E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #49C88E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #49C88E'>&nbsp;@&nbsp;</font><font style='background-color: #49C88E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49C88E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49C88E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.0add4cp126F;
    static { NAMED.put("deep cyan green", -0x1.0add4cp126F); LIST.add(-0x1.0add4cp126F); }

    /**
     * This color constant "true cyan green" has RGBA8888 code {@code 5CDCA0FF}, L 0.72156864, A 0.43137255, B 0.52156866, alpha 1.0, hue 0.451547, saturation 0.57221806, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add7p126F}.
     * <pre>
     * <font style='background-color: #5CDCA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CDCA0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CDCA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5CDCA0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5CDCA0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5CDCA0'>&nbsp;@&nbsp;</font><font style='background-color: #5CDCA0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CDCA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CDCA0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_GREEN = -0x1.0add7p126F;
    static { NAMED.put("true cyan green", -0x1.0add7p126F); LIST.add(-0x1.0add7p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code 6DEEB0FF}, L 0.7921569, A 0.43137255, B 0.52156866, alpha 1.0, hue 0.451547, saturation 0.5074936, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add94p126F}.
     * <pre>
     * <font style='background-color: #6DEEB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6DEEB0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6DEEB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6DEEB0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6DEEB0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6DEEB0'>&nbsp;@&nbsp;</font><font style='background-color: #6DEEB0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6DEEB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6DEEB0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.0add94p126F;
    static { NAMED.put("bright cyan green", -0x1.0add94p126F); LIST.add(-0x1.0add94p126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 2FC8ACFF}, L 0.6509804, A 0.43137255, B 0.49803922, alpha 1.0, hue 0.5045336, saturation 0.87182224, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.fedd4cp125F}.
     * <pre>
     * <font style='background-color: #2FC8AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2FC8AC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2FC8AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2FC8AC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2FC8AC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2FC8AC'>&nbsp;@&nbsp;</font><font style='background-color: #2FC8AC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2FC8AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2FC8AC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.fedd4cp125F;
    static { NAMED.put("deep green cyan", -0x1.fedd4cp125F); LIST.add(-0x1.fedd4cp125F); }

    /**
     * This color constant "true green cyan" has RGBA8888 code {@code 44DBBDFF}, L 0.7176471, A 0.43137255, B 0.5019608, alpha 1.0, hue 0.4954664, saturation 0.71185946, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.00dd6ep126F}.
     * <pre>
     * <font style='background-color: #44DBBD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #44DBBD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #44DBBD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #44DBBD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #44DBBD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #44DBBD'>&nbsp;@&nbsp;</font><font style='background-color: #44DBBD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #44DBBD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #44DBBD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_CYAN = -0x1.00dd6ep126F;
    static { NAMED.put("true green cyan", -0x1.00dd6ep126F); LIST.add(-0x1.00dd6ep126F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code 5AF0D2FF}, L 0.8, A 0.43137255, B 0.49803922, alpha 1.0, hue 0.5045336, saturation 0.64790595, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.fedd98p125F}.
     * <pre>
     * <font style='background-color: #5AF0D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AF0D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AF0D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5AF0D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5AF0D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5AF0D2'>&nbsp;@&nbsp;</font><font style='background-color: #5AF0D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AF0D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AF0D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.fedd98p125F;
    static { NAMED.put("bright green cyan", -0x1.fedd98p125F); LIST.add(-0x1.fedd98p125F); }

    /**
     * This color constant "deep pure cyan" has RGBA8888 code {@code 48C7C6FF}, L 0.6666667, A 0.44313726, B 0.48235294, alpha 1.0, hue 0.54788184, saturation 0.6920623, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e354p125F}.
     * <pre>
     * <font style='background-color: #48C7C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48C7C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48C7C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #48C7C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #48C7C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #48C7C6'>&nbsp;@&nbsp;</font><font style='background-color: #48C7C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #48C7C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #48C7C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_CYAN = -0x1.f6e354p125F;
    static { NAMED.put("deep pure cyan", -0x1.f6e354p125F); LIST.add(-0x1.f6e354p125F); }

    /**
     * This color constant "true pure cyan" has RGBA8888 code {@code 5DDDDCFF}, L 0.7490196, A 0.44313726, B 0.48235294, alpha 1.0, hue 0.54788184, saturation 0.59093094, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e37ep125F}.
     * <pre>
     * <font style='background-color: #5DDDDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5DDDDC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5DDDDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5DDDDC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5DDDDC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5DDDDC'>&nbsp;@&nbsp;</font><font style='background-color: #5DDDDC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5DDDDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5DDDDC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_CYAN = -0x1.f6e37ep125F;
    static { NAMED.put("true pure cyan", -0x1.f6e37ep125F); LIST.add(-0x1.f6e37ep125F); }

    /**
     * This color constant "bright pure cyan" has RGBA8888 code {@code 6EF0EEFF}, L 0.81960785, A 0.44313726, B 0.4862745, alpha 1.0, hue 0.5376946, saturation 0.51676583, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.f8e3a2p125F}.
     * <pre>
     * <font style='background-color: #6EF0EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6EF0EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6EF0EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6EF0EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6EF0EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6EF0EE'>&nbsp;@&nbsp;</font><font style='background-color: #6EF0EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6EF0EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6EF0EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_CYAN = -0x1.f8e3a2p125F;
    static { NAMED.put("bright pure cyan", -0x1.f8e3a2p125F); LIST.add(-0x1.f8e3a2p125F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 27A7CAFF}, L 0.5686275, A 0.4509804, B 0.4627451, alpha 1.0, hue 0.6034426, saturation 0.78238446, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.ece722p125F}.
     * <pre>
     * <font style='background-color: #27A7CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #27A7CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #27A7CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #27A7CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #27A7CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #27A7CA'>&nbsp;@&nbsp;</font><font style='background-color: #27A7CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #27A7CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #27A7CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.ece722p125F;
    static { NAMED.put("deep blue cyan", -0x1.ece722p125F); LIST.add(-0x1.ece722p125F); }

    /**
     * This color constant "true blue cyan" has RGBA8888 code {@code 3DBADEFF}, L 0.63529414, A 0.4509804, B 0.4627451, alpha 1.0, hue 0.6034426, saturation 0.70115554, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.ece744p125F}.
     * <pre>
     * <font style='background-color: #3DBADE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3DBADE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3DBADE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3DBADE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3DBADE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3DBADE'>&nbsp;@&nbsp;</font><font style='background-color: #3DBADE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3DBADE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3DBADE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_CYAN = -0x1.ece744p125F;
    static { NAMED.put("true blue cyan", -0x1.ece744p125F); LIST.add(-0x1.ece744p125F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 51CEF4FF}, L 0.70980394, A 0.4509804, B 0.45882353, alpha 1.0, hue 0.6112048, saturation 0.6499009, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.eae76ap125F}.
     * <pre>
     * <font style='background-color: #51CEF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51CEF4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51CEF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #51CEF4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #51CEF4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #51CEF4'>&nbsp;@&nbsp;</font><font style='background-color: #51CEF4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51CEF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51CEF4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.eae76ap125F;
    static { NAMED.put("bright blue cyan", -0x1.eae76ap125F); LIST.add(-0x1.eae76ap125F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 2A81BDFF}, L 0.46666667, A 0.46666667, B 0.44313726, alpha 1.0, hue 0.6656062, saturation 0.68891937, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.e2eeeep125F}.
     * <pre>
     * <font style='background-color: #2A81BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2A81BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2A81BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2A81BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2A81BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2A81BD'>&nbsp;@&nbsp;</font><font style='background-color: #2A81BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2A81BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2A81BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.e2eeeep125F;
    static { NAMED.put("deep cyan blue", -0x1.e2eeeep125F); LIST.add(-0x1.e2eeeep125F); }

    /**
     * This color constant "true cyan blue" has RGBA8888 code {@code 3B92D1FF}, L 0.5254902, A 0.46666667, B 0.44313726, alpha 1.0, hue 0.6656062, saturation 0.5971727, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.e2ef0cp125F}.
     * <pre>
     * <font style='background-color: #3B92D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B92D1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B92D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B92D1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B92D1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B92D1'>&nbsp;@&nbsp;</font><font style='background-color: #3B92D1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B92D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B92D1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_BLUE = -0x1.e2ef0cp125F;
    static { NAMED.put("true cyan blue", -0x1.e2ef0cp125F); LIST.add(-0x1.e2ef0cp125F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code 50A8EAFF}, L 0.6039216, A 0.47058824, B 0.44313726, alpha 1.0, hue 0.6740277, saturation 0.5633505, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.e2f134p125F}.
     * <pre>
     * <font style='background-color: #50A8EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50A8EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50A8EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #50A8EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #50A8EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #50A8EA'>&nbsp;@&nbsp;</font><font style='background-color: #50A8EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50A8EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50A8EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.e2f134p125F;
    static { NAMED.put("bright cyan blue", -0x1.e2f134p125F); LIST.add(-0x1.e2f134p125F); }

    /**
     * This color constant "deep pure blue" has RGBA8888 code {@code 002FCDFF}, L 0.3019608, A 0.4862745, B 0.3764706, alpha 1.0, hue 0.7323789, saturation 0.6520346, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f89ap125F}.
     * <pre>
     * <font style='background-color: #002FCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #002FCD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #002FCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #002FCD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #002FCD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #002FCD'>&nbsp;@&nbsp;</font><font style='background-color: #002FCD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #002FCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #002FCD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BLUE = -0x1.c0f89ap125F;
    static { NAMED.put("deep pure blue", -0x1.c0f89ap125F); LIST.add(-0x1.c0f89ap125F); }

    /**
     * This color constant "true pure blue" has RGBA8888 code {@code 0D41E1FF}, L 0.34901962, A 0.48235294, B 0.3764706, alpha 1.0, hue 0.72740346, saturation 0.65722746, and chroma 0.24859223.
     * It can be represented as a packed float with the constant {@code -0x1.c0f6b2p125F}.
     * <pre>
     * <font style='background-color: #0D41E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D41E1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D41E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0D41E1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0D41E1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0D41E1'>&nbsp;@&nbsp;</font><font style='background-color: #0D41E1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D41E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D41E1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BLUE = -0x1.c0f6b2p125F;
    static { NAMED.put("true pure blue", -0x1.c0f6b2p125F); LIST.add(-0x1.c0f6b2p125F); }

    /**
     * This color constant "bright pure blue" has RGBA8888 code {@code 1950F4FF}, L 0.39215687, A 0.48235294, B 0.3764706, alpha 1.0, hue 0.72740346, saturation 0.81484836, and chroma 0.24859223.
     * It can be represented as a packed float with the constant {@code -0x1.c0f6c8p125F}.
     * <pre>
     * <font style='background-color: #1950F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1950F4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1950F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1950F4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1950F4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1950F4'>&nbsp;@&nbsp;</font><font style='background-color: #1950F4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1950F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1950F4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BLUE = -0x1.c0f6c8p125F;
    static { NAMED.put("bright pure blue", -0x1.c0f6c8p125F); LIST.add(-0x1.c0f6c8p125F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 3834C0FF}, L 0.31764707, A 0.50980395, B 0.39215687, alpha 1.0, hue 0.764433, saturation 0.5495248, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.c904a2p125F}.
     * <pre>
     * <font style='background-color: #3834C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3834C0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3834C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3834C0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3834C0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3834C0'>&nbsp;@&nbsp;</font><font style='background-color: #3834C0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3834C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3834C0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.c904a2p125F;
    static { NAMED.put("deep violet blue", -0x1.c904a2p125F); LIST.add(-0x1.c904a2p125F); }

    /**
     * This color constant "true violet blue" has RGBA8888 code {@code 4143D2FF}, L 0.36078432, A 0.5058824, B 0.39215687, alpha 1.0, hue 0.7586634, saturation 0.5051405, and chroma 0.21516311.
     * It can be represented as a packed float with the constant {@code -0x1.c902b8p125F}.
     * <pre>
     * <font style='background-color: #4143D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4143D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4143D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4143D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4143D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4143D2'>&nbsp;@&nbsp;</font><font style='background-color: #4143D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4143D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4143D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_BLUE = -0x1.c902b8p125F;
    static { NAMED.put("true violet blue", -0x1.c902b8p125F); LIST.add(-0x1.c902b8p125F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code 4E53E6FF}, L 0.4117647, A 0.50980395, B 0.39215687, alpha 1.0, hue 0.764433, saturation 0.65000796, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.c904d2p125F}.
     * <pre>
     * <font style='background-color: #4E53E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E53E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E53E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E53E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E53E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E53E6'>&nbsp;@&nbsp;</font><font style='background-color: #4E53E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E53E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E53E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.c904d2p125F;
    static { NAMED.put("bright violet blue", -0x1.c904d2p125F); LIST.add(-0x1.c904d2p125F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 5127C6FF}, L 0.3254902, A 0.5294118, B 0.3882353, alpha 1.0, hue 0.7909493, saturation 0.6989588, and chroma 0.2302369.
     * It can be represented as a packed float with the constant {@code -0x1.c70ea6p125F}.
     * <pre>
     * <font style='background-color: #5127C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5127C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5127C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5127C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5127C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5127C6'>&nbsp;@&nbsp;</font><font style='background-color: #5127C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5127C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5127C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.c70ea6p125F;
    static { NAMED.put("deep blue violet", -0x1.c70ea6p125F); LIST.add(-0x1.c70ea6p125F); }

    /**
     * This color constant "true blue violet" has RGBA8888 code {@code 603BDDFF}, L 0.38039216, A 0.53333336, B 0.3882353, alpha 1.0, hue 0.7961206, saturation 0.5890531, and chroma 0.23234801.
     * It can be represented as a packed float with the constant {@code -0x1.c710c2p125F}.
     * <pre>
     * <font style='background-color: #603BDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #603BDD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #603BDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #603BDD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #603BDD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #603BDD'>&nbsp;@&nbsp;</font><font style='background-color: #603BDD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #603BDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #603BDD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_VIOLET = -0x1.c710c2p125F;
    static { NAMED.put("true blue violet", -0x1.c710c2p125F); LIST.add(-0x1.c710c2p125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code 704FF6FF}, L 0.44313726, A 0.5294118, B 0.3882353, alpha 1.0, hue 0.7909493, saturation 0.78557295, and chroma 0.2302369.
     * It can be represented as a packed float with the constant {@code -0x1.c70ee2p125F}.
     * <pre>
     * <font style='background-color: #704FF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #704FF6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #704FF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #704FF6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #704FF6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #704FF6'>&nbsp;@&nbsp;</font><font style='background-color: #704FF6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #704FF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #704FF6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.c70ee2p125F;
    static { NAMED.put("bright blue violet", -0x1.c70ee2p125F); LIST.add(-0x1.c70ee2p125F); }

    /**
     * This color constant "deep pure violet" has RGBA8888 code {@code 7536C2FF}, L 0.3764706, A 0.5529412, B 0.40784314, alpha 1.0, hue 0.8329952, saturation 0.55895364, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d11acp125F}.
     * <pre>
     * <font style='background-color: #7536C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7536C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7536C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7536C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7536C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7536C2'>&nbsp;@&nbsp;</font><font style='background-color: #7536C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7536C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7536C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_VIOLET = -0x1.d11acp125F;
    static { NAMED.put("deep pure violet", -0x1.d11acp125F); LIST.add(-0x1.d11acp125F); }

    /**
     * This color constant "true pure violet" has RGBA8888 code {@code 8446D6FF}, L 0.42745098, A 0.5529412, B 0.40784314, alpha 1.0, hue 0.8329952, saturation 0.47677392, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d11adap125F}.
     * <pre>
     * <font style='background-color: #8446D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8446D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8446D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8446D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8446D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8446D6'>&nbsp;@&nbsp;</font><font style='background-color: #8446D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8446D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8446D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_VIOLET = -0x1.d11adap125F;
    static { NAMED.put("true pure violet", -0x1.d11adap125F); LIST.add(-0x1.d11adap125F); }

    /**
     * This color constant "bright pure violet" has RGBA8888 code {@code 9455E9FF}, L 0.47843137, A 0.5529412, B 0.40784314, alpha 1.0, hue 0.8329952, saturation 0.6082501, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d11af4p125F}.
     * <pre>
     * <font style='background-color: #9455E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9455E9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9455E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9455E9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9455E9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9455E9'>&nbsp;@&nbsp;</font><font style='background-color: #9455E9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9455E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9455E9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_VIOLET = -0x1.d11af4p125F;
    static { NAMED.put("bright pure violet", -0x1.d11af4p125F); LIST.add(-0x1.d11af4p125F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 7E2ACDFF}, L 0.38039216, A 0.5647059, B 0.4, alpha 1.0, hue 0.84141475, saturation 0.7020214, and chroma 0.2372866.
     * It can be represented as a packed float with the constant {@code -0x1.cd20c2p125F}.
     * <pre>
     * <font style='background-color: #7E2ACD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E2ACD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E2ACD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E2ACD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E2ACD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E2ACD'>&nbsp;@&nbsp;</font><font style='background-color: #7E2ACD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E2ACD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E2ACD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.cd20c2p125F;
    static { NAMED.put("deep purple violet", -0x1.cd20c2p125F); LIST.add(-0x1.cd20c2p125F); }

    /**
     * This color constant "true purple violet" has RGBA8888 code {@code 8E3CE0FF}, L 0.43137255, A 0.56078434, B 0.4, alpha 1.0, hue 0.8369341, saturation 0.5780356, and chroma 0.23313475.
     * It can be represented as a packed float with the constant {@code -0x1.cd1edcp125F}.
     * <pre>
     * <font style='background-color: #8E3CE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E3CE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E3CE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E3CE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E3CE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E3CE0'>&nbsp;@&nbsp;</font><font style='background-color: #8E3CE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E3CE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E3CE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_VIOLET = -0x1.cd1edcp125F;
    static { NAMED.put("true purple violet", -0x1.cd1edcp125F); LIST.add(-0x1.cd1edcp125F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code A04EF7FF}, L 0.49019608, A 0.56078434, B 0.4, alpha 1.0, hue 0.8369341, saturation 0.7591241, and chroma 0.23313475.
     * It can be represented as a packed float with the constant {@code -0x1.cd1efap125F}.
     * <pre>
     * <font style='background-color: #A04EF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A04EF7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A04EF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A04EF7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A04EF7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A04EF7'>&nbsp;@&nbsp;</font><font style='background-color: #A04EF7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A04EF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A04EF7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.cd1efap125F;
    static { NAMED.put("bright purple violet", -0x1.cd1efap125F); LIST.add(-0x1.cd1efap125F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 8F39C5FF}, L 0.4117647, A 0.5686275, B 0.41568628, alpha 1.0, hue 0.85874414, saturation 0.5538489, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.d522d2p125F}.
     * <pre>
     * <font style='background-color: #8F39C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F39C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F39C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F39C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F39C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F39C5'>&nbsp;@&nbsp;</font><font style='background-color: #8F39C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F39C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F39C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.d522d2p125F;
    static { NAMED.put("deep violet purple", -0x1.d522d2p125F); LIST.add(-0x1.d522d2p125F); }

    /**
     * This color constant "true violet purple" has RGBA8888 code {@code A149DAFF}, L 0.46666667, A 0.5686275, B 0.41568628, alpha 1.0, hue 0.85874414, saturation 0.47436443, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.d522eep125F}.
     * <pre>
     * <font style='background-color: #A149DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A149DA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A149DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A149DA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A149DA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A149DA'>&nbsp;@&nbsp;</font><font style='background-color: #A149DA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A149DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A149DA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_PURPLE = -0x1.d522eep125F;
    static { NAMED.put("true violet purple", -0x1.d522eep125F); LIST.add(-0x1.d522eep125F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code B158ECFF}, L 0.5176471, A 0.5686275, B 0.41568628, alpha 1.0, hue 0.85874414, saturation 0.636406, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.d52308p125F}.
     * <pre>
     * <font style='background-color: #B158EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B158EC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B158EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B158EC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B158EC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B158EC'>&nbsp;@&nbsp;</font><font style='background-color: #B158EC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B158EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B158EC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.d52308p125F;
    static { NAMED.put("bright violet purple", -0x1.d52308p125F); LIST.add(-0x1.d52308p125F); }

    /**
     * This color constant "deep pure purple" has RGBA8888 code {@code 9E2BCCFF}, L 0.41960785, A 0.58431375, B 0.4117647, alpha 1.0, hue 0.8713863, saturation 0.7171086, and chroma 0.24313073.
     * It can be represented as a packed float with the constant {@code -0x1.d32ad6p125F}.
     * <pre>
     * <font style='background-color: #9E2BCC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E2BCC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E2BCC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E2BCC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E2BCC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E2BCC'>&nbsp;@&nbsp;</font><font style='background-color: #9E2BCC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E2BCC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E2BCC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_PURPLE = -0x1.d32ad6p125F;
    static { NAMED.put("deep pure purple", -0x1.d32ad6p125F); LIST.add(-0x1.d32ad6p125F); }

    /**
     * This color constant "true pure purple" has RGBA8888 code {@code B03DE0FF}, L 0.4745098, A 0.58431375, B 0.4117647, alpha 1.0, hue 0.8713863, saturation 0.59781647, and chroma 0.24313073.
     * It can be represented as a packed float with the constant {@code -0x1.d32af2p125F}.
     * <pre>
     * <font style='background-color: #B03DE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B03DE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B03DE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B03DE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B03DE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B03DE0'>&nbsp;@&nbsp;</font><font style='background-color: #B03DE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B03DE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B03DE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_PURPLE = -0x1.d32af2p125F;
    static { NAMED.put("true pure purple", -0x1.d32af2p125F); LIST.add(-0x1.d32af2p125F); }

    /**
     * This color constant "bright pure purple" has RGBA8888 code {@code C44FF5FF}, L 0.53333336, A 0.58431375, B 0.4117647, alpha 1.0, hue 0.8713863, saturation 0.7171086, and chroma 0.24313073.
     * It can be represented as a packed float with the constant {@code -0x1.d32b1p125F}.
     * <pre>
     * <font style='background-color: #C44FF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C44FF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C44FF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C44FF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C44FF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C44FF5'>&nbsp;@&nbsp;</font><font style='background-color: #C44FF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C44FF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C44FF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_PURPLE = -0x1.d32b1p125F;
    static { NAMED.put("bright pure purple", -0x1.d32b1p125F); LIST.add(-0x1.d32b1p125F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code A238C2FF}, L 0.43137255, A 0.5803922, B 0.42352942, alpha 1.0, hue 0.878975, saturation 0.592716, and chroma 0.2210399.
     * It can be represented as a packed float with the constant {@code -0x1.d928dcp125F}.
     * <pre>
     * <font style='background-color: #A238C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A238C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A238C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A238C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A238C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A238C2'>&nbsp;@&nbsp;</font><font style='background-color: #A238C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A238C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A238C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.d928dcp125F;
    static { NAMED.put("deep magenta purple", -0x1.d928dcp125F); LIST.add(-0x1.d928dcp125F); }

    /**
     * This color constant "true magenta purple" has RGBA8888 code {@code B448D6FF}, L 0.4862745, A 0.5803922, B 0.42352942, alpha 1.0, hue 0.878975, saturation 0.49411675, and chroma 0.2210399.
     * It can be represented as a packed float with the constant {@code -0x1.d928f8p125F}.
     * <pre>
     * <font style='background-color: #B448D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B448D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B448D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B448D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B448D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B448D6'>&nbsp;@&nbsp;</font><font style='background-color: #B448D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B448D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B448D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_PURPLE = -0x1.d928f8p125F;
    static { NAMED.put("true magenta purple", -0x1.d928f8p125F); LIST.add(-0x1.d928f8p125F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code CA5BEDFF}, L 0.5529412, A 0.5803922, B 0.42352942, alpha 1.0, hue 0.878975, saturation 0.592716, and chroma 0.2210399.
     * It can be represented as a packed float with the constant {@code -0x1.d9291ap125F}.
     * <pre>
     * <font style='background-color: #CA5BED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA5BED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA5BED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA5BED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA5BED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA5BED'>&nbsp;@&nbsp;</font><font style='background-color: #CA5BED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA5BED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA5BED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.d9291ap125F;
    static { NAMED.put("bright magenta purple", -0x1.d9291ap125F); LIST.add(-0x1.d9291ap125F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code BE2FCEFF}, L 0.46666667, A 0.6, B 0.42352942, alpha 1.0, hue 0.89608383, saturation 0.7231262, and chroma 0.2507922.
     * It can be represented as a packed float with the constant {@code -0x1.d932eep125F}.
     * <pre>
     * <font style='background-color: #BE2FCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE2FCE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE2FCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE2FCE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE2FCE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE2FCE'>&nbsp;@&nbsp;</font><font style='background-color: #BE2FCE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE2FCE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE2FCE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.d932eep125F;
    static { NAMED.put("deep purple magenta", -0x1.d932eep125F); LIST.add(-0x1.d932eep125F); }

    /**
     * This color constant "true purple magenta" has RGBA8888 code {@code D443E4FF}, L 0.5294118, A 0.6, B 0.42352942, alpha 1.0, hue 0.89608383, saturation 0.60561985, and chroma 0.2507922.
     * It can be represented as a packed float with the constant {@code -0x1.d9330ep125F}.
     * <pre>
     * <font style='background-color: #D443E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D443E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D443E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D443E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D443E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D443E4'>&nbsp;@&nbsp;</font><font style='background-color: #D443E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D443E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D443E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_MAGENTA = -0x1.d9330ep125F;
    static { NAMED.put("true purple magenta", -0x1.d9330ep125F); LIST.add(-0x1.d9330ep125F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code E653F6FF}, L 0.58431375, A 0.6, B 0.42352942, alpha 1.0, hue 0.89608383, saturation 0.7043445, and chroma 0.2507922.
     * It can be represented as a packed float with the constant {@code -0x1.d9332ap125F}.
     * <pre>
     * <font style='background-color: #E653F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E653F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E653F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E653F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E653F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E653F6'>&nbsp;@&nbsp;</font><font style='background-color: #E653F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E653F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E653F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.d9332ap125F;
    static { NAMED.put("bright purple magenta", -0x1.d9332ap125F); LIST.add(-0x1.d9332ap125F); }

    /**
     * This color constant "deep pure magenta" has RGBA8888 code {@code C139C2FF}, L 0.4745098, A 0.59607846, B 0.43529412, alpha 1.0, hue 0.90565705, saturation 0.61225384, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.df30f2p125F}.
     * <pre>
     * <font style='background-color: #C139C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C139C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C139C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C139C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C139C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C139C2'>&nbsp;@&nbsp;</font><font style='background-color: #C139C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C139C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C139C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_MAGENTA = -0x1.df30f2p125F;
    static { NAMED.put("deep pure magenta", -0x1.df30f2p125F); LIST.add(-0x1.df30f2p125F); }

    /**
     * This color constant "true pure magenta" has RGBA8888 code {@code D449D4FF}, L 0.5294118, A 0.59607846, B 0.43529412, alpha 1.0, hue 0.90565705, saturation 0.52542436, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.df310ep125F}.
     * <pre>
     * <font style='background-color: #D449D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D449D4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D449D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D449D4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D449D4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D449D4'>&nbsp;@&nbsp;</font><font style='background-color: #D449D4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D449D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D449D4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_MAGENTA = -0x1.df310ep125F;
    static { NAMED.put("true pure magenta", -0x1.df310ep125F); LIST.add(-0x1.df310ep125F); }

    /**
     * This color constant "bright pure magenta" has RGBA8888 code {@code EA5CEAFF}, L 0.59607846, A 0.59607846, B 0.43529412, alpha 1.0, hue 0.90565705, saturation 0.5385595, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.df313p125F}.
     * <pre>
     * <font style='background-color: #EA5CEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA5CEA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA5CEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EA5CEA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EA5CEA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EA5CEA'>&nbsp;@&nbsp;</font><font style='background-color: #EA5CEA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA5CEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA5CEA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_MAGENTA = -0x1.df313p125F;
    static { NAMED.put("bright pure magenta", -0x1.df313p125F); LIST.add(-0x1.df313p125F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code C72183FF}, L 0.43137255, A 0.60784316, B 0.47843137, alpha 1.0, hue 0.96857655, saturation 0.78020954, and chroma 0.2190985.
     * It can be represented as a packed float with the constant {@code -0x1.f536dcp125F}.
     * <pre>
     * <font style='background-color: #C72183;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C72183; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C72183;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C72183'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C72183'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C72183'>&nbsp;@&nbsp;</font><font style='background-color: #C72183; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C72183;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C72183; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.f536dcp125F;
    static { NAMED.put("deep red magenta", -0x1.f536dcp125F); LIST.add(-0x1.f536dcp125F); }

    /**
     * This color constant "true red magenta" has RGBA8888 code {@code DC3494FF}, L 0.4862745, A 0.60784316, B 0.47843137, alpha 1.0, hue 0.96857655, saturation 0.65131205, and chroma 0.2190985.
     * It can be represented as a packed float with the constant {@code -0x1.f536f8p125F}.
     * <pre>
     * <font style='background-color: #DC3494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC3494; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC3494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC3494'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC3494'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC3494'>&nbsp;@&nbsp;</font><font style='background-color: #DC3494; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC3494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC3494; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_MAGENTA = -0x1.f536f8p125F;
    static { NAMED.put("true red magenta", -0x1.f536f8p125F); LIST.add(-0x1.f536f8p125F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code F347A7FF}, L 0.54901963, A 0.60784316, B 0.4745098, alpha 1.0, hue 0.96305966, saturation 0.6247738, and chroma 0.22076361.
     * It can be represented as a packed float with the constant {@code -0x1.f33718p125F}.
     * <pre>
     * <font style='background-color: #F347A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F347A7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F347A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F347A7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F347A7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F347A7'>&nbsp;@&nbsp;</font><font style='background-color: #F347A7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F347A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F347A7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.f33718p125F;
    static { NAMED.put("bright red magenta", -0x1.f33718p125F); LIST.add(-0x1.f33718p125F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code C43659FF}, L 0.43137255, A 0.5882353, B 0.5137255, alpha 1.0, hue 0.024573287, saturation 0.5483509, and chroma 0.17789528.
     * It can be represented as a packed float with the constant {@code -0x1.072cdcp126F}.
     * <pre>
     * <font style='background-color: #C43659;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C43659; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C43659;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C43659'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C43659'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C43659'>&nbsp;@&nbsp;</font><font style='background-color: #C43659; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C43659;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C43659; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.072cdcp126F;
    static { NAMED.put("deep magenta red", -0x1.072cdcp126F); LIST.add(-0x1.072cdcp126F); }

    /**
     * This color constant "true magenta red" has RGBA8888 code {@code D74567FF}, L 0.48235294, A 0.5882353, B 0.5137255, alpha 1.0, hue 0.024573287, saturation 0.46899202, and chroma 0.17789528.
     * It can be represented as a packed float with the constant {@code -0x1.072cf6p126F}.
     * <pre>
     * <font style='background-color: #D74567;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D74567; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D74567;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D74567'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D74567'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D74567'>&nbsp;@&nbsp;</font><font style='background-color: #D74567; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D74567;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D74567; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_RED = -0x1.072cf6p126F;
    static { NAMED.put("true magenta red", -0x1.072cf6p126F); LIST.add(-0x1.072cf6p126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code E95374FF}, L 0.53333336, A 0.5882353, B 0.5137255, alpha 1.0, hue 0.024573287, saturation 0.530944, and chroma 0.17789528.
     * It can be represented as a packed float with the constant {@code -0x1.072d1p126F}.
     * <pre>
     * <font style='background-color: #E95374;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E95374; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E95374;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E95374'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E95374'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E95374'>&nbsp;@&nbsp;</font><font style='background-color: #E95374; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E95374;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E95374; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.072d1p126F;
    static { NAMED.put("bright magenta red", -0x1.072d1p126F); LIST.add(-0x1.072d1p126F); }

    /**
     * This color constant "bold pure red" has RGBA8888 code {@code FE000FFF}, L 0.4862745, A 0.6117647, B 0.56078434, alpha 1.0, hue 0.07928106, saturation 0.8972241, and chroma 0.25345513.
     * It can be represented as a packed float with the constant {@code -0x1.1f38f8p126F}.
     * <pre>
     * <font style='background-color: #FE000F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE000F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE000F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FE000F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FE000F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FE000F'>&nbsp;@&nbsp;</font><font style='background-color: #FE000F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE000F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE000F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_RED = -0x1.1f38f8p126F;
    static { NAMED.put("bold pure red", -0x1.1f38f8p126F); LIST.add(-0x1.1f38f8p126F); }

    /**
     * This color constant "bold brown red" has RGBA8888 code {@code E65239FF}, L 0.5137255, A 0.5764706, B 0.54901963, alpha 1.0, hue 0.090735756, saturation 0.53220904, and chroma 0.1809568.
     * It can be represented as a packed float with the constant {@code -0x1.192706p126F}.
     * <pre>
     * <font style='background-color: #E65239;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E65239; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E65239;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E65239'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E65239'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E65239'>&nbsp;@&nbsp;</font><font style='background-color: #E65239; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E65239;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E65239; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_RED = -0x1.192706p126F;
    static { NAMED.put("bold brown red", -0x1.192706p126F); LIST.add(-0x1.192706p126F); }

    /**
     * This color constant "bold red brown" has RGBA8888 code {@code DA6746FF}, L 0.53333336, A 0.5568628, B 0.54509807, alpha 1.0, hue 0.106728435, saturation 0.41436672, and chroma 0.14458403.
     * It can be represented as a packed float with the constant {@code -0x1.171d1p126F}.
     * <pre>
     * <font style='background-color: #DA6746;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA6746; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA6746;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA6746'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA6746'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA6746'>&nbsp;@&nbsp;</font><font style='background-color: #DA6746; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA6746;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA6746; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_BROWN = -0x1.171d1p126F;
    static { NAMED.put("bold red brown", -0x1.171d1p126F); LIST.add(-0x1.171d1p126F); }

    /**
     * This color constant "bold pure brown" has RGBA8888 code {@code CF7859FF}, L 0.5529412, A 0.5411765, B 0.53333336, alpha 1.0, hue 0.10831932, saturation 0.22079395, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.11151ap126F}.
     * <pre>
     * <font style='background-color: #CF7859;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF7859; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF7859;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CF7859'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CF7859'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CF7859'>&nbsp;@&nbsp;</font><font style='background-color: #CF7859; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF7859;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF7859; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BROWN = -0x1.11151ap126F;
    static { NAMED.put("bold pure brown", -0x1.11151ap126F); LIST.add(-0x1.11151ap126F); }

    /**
     * This color constant "bold orange brown" has RGBA8888 code {@code E17C4BFF}, L 0.5803922, A 0.54509807, B 0.54901963, alpha 1.0, hue 0.13162255, saturation 0.4186848, and chroma 0.13269757.
     * It can be represented as a packed float with the constant {@code -0x1.191728p126F}.
     * <pre>
     * <font style='background-color: #E17C4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E17C4B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E17C4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E17C4B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E17C4B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E17C4B'>&nbsp;@&nbsp;</font><font style='background-color: #E17C4B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E17C4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E17C4B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_BROWN = -0x1.191728p126F;
    static { NAMED.put("bold orange brown", -0x1.191728p126F); LIST.add(-0x1.191728p126F); }

    /**
     * This color constant "bold brown orange" has RGBA8888 code {@code E58349FF}, L 0.6, A 0.5411765, B 0.5529412, alpha 1.0, hue 0.14477962, saturation 0.45877856, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.1b1532p126F}.
     * <pre>
     * <font style='background-color: #E58349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E58349; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E58349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E58349'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E58349'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E58349'>&nbsp;@&nbsp;</font><font style='background-color: #E58349; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E58349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E58349; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_ORANGE = -0x1.1b1532p126F;
    static { NAMED.put("bold brown orange", -0x1.1b1532p126F); LIST.add(-0x1.1b1532p126F); }

    /**
     * This color constant "bold pure orange" has RGBA8888 code {@code F87C13FF}, L 0.60784316, A 0.54901963, B 0.57254905, alpha 1.0, hue 0.15541562, saturation 0.847699, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.251936p126F}.
     * <pre>
     * <font style='background-color: #F87C13;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F87C13; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F87C13;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F87C13'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F87C13'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F87C13'>&nbsp;@&nbsp;</font><font style='background-color: #F87C13; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F87C13;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F87C13; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_ORANGE = -0x1.251936p126F;
    static { NAMED.put("bold pure orange", -0x1.251936p126F); LIST.add(-0x1.251936p126F); }

    /**
     * This color constant "bold saffron orange" has RGBA8888 code {@code F48A1EFF}, L 0.6313726, A 0.5372549, B 0.57254905, alpha 1.0, hue 0.1744969, saturation 0.8000925, and chroma 0.16247371.
     * It can be represented as a packed float with the constant {@code -0x1.251342p126F}.
     * <pre>
     * <font style='background-color: #F48A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F48A1E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F48A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F48A1E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F48A1E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F48A1E'>&nbsp;@&nbsp;</font><font style='background-color: #F48A1E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F48A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F48A1E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_ORANGE = -0x1.251342p126F;
    static { NAMED.put("bold saffron orange", -0x1.251342p126F); LIST.add(-0x1.251342p126F); }

    /**
     * This color constant "bold orange saffron" has RGBA8888 code {@code FC9500FF}, L 0.6627451, A 0.5294118, B 0.5764706, alpha 1.0, hue 0.19157475, saturation 0.8433764, and chroma 0.1632233.
     * It can be represented as a packed float with the constant {@code -0x1.270f52p126F}.
     * <pre>
     * <font style='background-color: #FC9500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC9500; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC9500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC9500'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC9500'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC9500'>&nbsp;@&nbsp;</font><font style='background-color: #FC9500; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC9500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC9500; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_SAFFRON = -0x1.270f52p126F;
    static { NAMED.put("bold orange saffron", -0x1.270f52p126F); LIST.add(-0x1.270f52p126F); }

    /**
     * This color constant "bold pure saffron" has RGBA8888 code {@code F6A727FF}, L 0.69803923, A 0.5176471, B 0.5764706, alpha 1.0, hue 0.21390288, saturation 0.77381957, and chroma 0.15634763.
     * It can be represented as a packed float with the constant {@code -0x1.270964p126F}.
     * <pre>
     * <font style='background-color: #F6A727;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6A727; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6A727;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6A727'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6A727'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6A727'>&nbsp;@&nbsp;</font><font style='background-color: #F6A727; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6A727;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6A727; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_SAFFRON = -0x1.270964p126F;
    static { NAMED.put("bold pure saffron", -0x1.270964p126F); LIST.add(-0x1.270964p126F); }

    /**
     * This color constant "bold yellow saffron" has RGBA8888 code {@code FFC400FF}, L 0.78039217, A 0.49803922, B 0.5882353, alpha 1.0, hue 0.2535256, saturation 0.8979501, and chroma 0.17582464.
     * It can be represented as a packed float with the constant {@code -0x1.2cff8ep126F}.
     * <pre>
     * <font style='background-color: #FFC400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC400; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFC400'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFC400'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFC400'>&nbsp;@&nbsp;</font><font style='background-color: #FFC400; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC400; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_SAFFRON = -0x1.2cff8ep126F;
    static { NAMED.put("bold yellow saffron", -0x1.2cff8ep126F); LIST.add(-0x1.2cff8ep126F); }

    /**
     * This color constant "bold saffron yellow" has RGBA8888 code {@code FFE12EFF}, L 0.85882354, A 0.48235294, B 0.5882353, alpha 1.0, hue 0.28142345, saturation 0.76408166, and chroma 0.1792624.
     * It can be represented as a packed float with the constant {@code -0x1.2cf7b6p126F}.
     * <pre>
     * <font style='background-color: #FFE12E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE12E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE12E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFE12E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFE12E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFE12E'>&nbsp;@&nbsp;</font><font style='background-color: #FFE12E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFE12E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFE12E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_SAFFRON_YELLOW = -0x1.2cf7b6p126F;
    static { NAMED.put("bold saffron yellow", -0x1.2cf7b6p126F); LIST.add(-0x1.2cf7b6p126F); }

    /**
     * This color constant "bold pure yellow" has RGBA8888 code {@code F3F900FF}, L 0.9137255, A 0.45882353, B 0.59607846, alpha 1.0, hue 0.3144313, saturation 0.8595841, and chroma 0.2082438.
     * It can be represented as a packed float with the constant {@code -0x1.30ebd2p126F}.
     * <pre>
     * <font style='background-color: #F3F900;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3F900; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3F900;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3F900'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3F900'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3F900'>&nbsp;@&nbsp;</font><font style='background-color: #F3F900; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3F900;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3F900; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_YELLOW = -0x1.30ebd2p126F;
    static { NAMED.put("bold pure yellow", -0x1.30ebd2p126F); LIST.add(-0x1.30ebd2p126F); }

    /**
     * This color constant "bold lime yellow" has RGBA8888 code {@code E0F826FF}, L 0.8901961, A 0.4509804, B 0.5921569, alpha 1.0, hue 0.32780534, saturation 0.82811016, and chroma 0.20795049.
     * It can be represented as a packed float with the constant {@code -0x1.2ee7c6p126F}.
     * <pre>
     * <font style='background-color: #E0F826;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0F826; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0F826;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0F826'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0F826'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0F826'>&nbsp;@&nbsp;</font><font style='background-color: #E0F826; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0F826;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0F826; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_YELLOW = -0x1.2ee7c6p126F;
    static { NAMED.put("bold lime yellow", -0x1.2ee7c6p126F); LIST.add(-0x1.2ee7c6p126F); }

    /**
     * This color constant "bold yellow lime" has RGBA8888 code {@code C4FD00FF}, L 0.8745098, A 0.43529412, B 0.5921569, alpha 1.0, hue 0.34743994, saturation 0.81790566, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.2edfbep126F}.
     * <pre>
     * <font style='background-color: #C4FD00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4FD00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4FD00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4FD00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4FD00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4FD00'>&nbsp;@&nbsp;</font><font style='background-color: #C4FD00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4FD00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4FD00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_YELLOW_LIME = -0x1.2edfbep126F;
    static { NAMED.put("bold yellow lime", -0x1.2edfbep126F); LIST.add(-0x1.2edfbep126F); }

    /**
     * This color constant "bold pure lime" has RGBA8888 code {@code A3F827FF}, L 0.83137256, A 0.42352942, B 0.5882353, alpha 1.0, hue 0.3636593, saturation 0.7782716, and chroma 0.23261054.
     * It can be represented as a packed float with the constant {@code -0x1.2cd9a8p126F}.
     * <pre>
     * <font style='background-color: #A3F827;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3F827; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3F827;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A3F827'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A3F827'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A3F827'>&nbsp;@&nbsp;</font><font style='background-color: #A3F827; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3F827;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3F827; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_LIME = -0x1.2cd9a8p126F;
    static { NAMED.put("bold pure lime", -0x1.2cd9a8p126F); LIST.add(-0x1.2cd9a8p126F); }

    /**
     * This color constant "bold green lime" has RGBA8888 code {@code 75FC00FF}, L 0.8117647, A 0.40392157, B 0.5882353, alpha 1.0, hue 0.38176328, saturation 0.81928825, and chroma 0.25987574.
     * It can be represented as a packed float with the constant {@code -0x1.2ccf9ep126F}.
     * <pre>
     * <font style='background-color: #75FC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #75FC00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #75FC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #75FC00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #75FC00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #75FC00'>&nbsp;@&nbsp;</font><font style='background-color: #75FC00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #75FC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #75FC00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_LIME = -0x1.2ccf9ep126F;
    static { NAMED.put("bold green lime", -0x1.2ccf9ep126F); LIST.add(-0x1.2ccf9ep126F); }

    /**
     * This color constant "bold lime green" has RGBA8888 code {@code 49FA2AFF}, L 0.78431374, A 0.39215687, B 0.58431375, alpha 1.0, hue 0.39437985, saturation 0.83284205, and chroma 0.2727111.
     * It can be represented as a packed float with the constant {@code -0x1.2ac99p126F}.
     * <pre>
     * <font style='background-color: #49FA2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49FA2A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49FA2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #49FA2A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #49FA2A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #49FA2A'>&nbsp;@&nbsp;</font><font style='background-color: #49FA2A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49FA2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49FA2A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_LIME_GREEN = -0x1.2ac99p126F;
    static { NAMED.put("bold lime green", -0x1.2ac99p126F); LIST.add(-0x1.2ac99p126F); }

    /**
     * This color constant "bold pure green" has RGBA8888 code {@code 00FF3BFF}, L 0.7882353, A 0.38431373, B 0.5803922, alpha 1.0, hue 0.40333164, saturation 0.98206896, and chroma 0.28065258.
     * It can be represented as a packed float with the constant {@code -0x1.28c592p126F}.
     * <pre>
     * <font style='background-color: #00FF3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF3B'>&nbsp;@&nbsp;</font><font style='background-color: #00FF3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_GREEN = -0x1.28c592p126F;
    static { NAMED.put("bold pure green", -0x1.28c592p126F); LIST.add(-0x1.28c592p126F); }

    /**
     * This color constant "bold cyan green" has RGBA8888 code {@code 31F7ABFF}, L 0.7921569, A 0.4117647, B 0.5254902, alpha 1.0, hue 0.45524934, saturation 0.8603078, and chroma 0.18296935.
     * It can be represented as a packed float with the constant {@code -0x1.0cd394p126F}.
     * <pre>
     * <font style='background-color: #31F7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31F7AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31F7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #31F7AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #31F7AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #31F7AB'>&nbsp;@&nbsp;</font><font style='background-color: #31F7AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31F7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31F7AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_GREEN = -0x1.0cd394p126F;
    static { NAMED.put("bold cyan green", -0x1.0cd394p126F); LIST.add(-0x1.0cd394p126F); }

    /**
     * This color constant "bold green cyan" has RGBA8888 code {@code 00FFDAFF}, L 0.827451, A 0.41568628, B 0.49803922, alpha 1.0, hue 0.50368965, saturation 0.9342255, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.fed5a6p125F}.
     * <pre>
     * <font style='background-color: #00FFDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFDA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFDA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFDA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFDA'>&nbsp;@&nbsp;</font><font style='background-color: #00FFDA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFDA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_GREEN_CYAN = -0x1.fed5a6p125F;
    static { NAMED.put("bold green cyan", -0x1.fed5a6p125F); LIST.add(-0x1.fed5a6p125F); }

    /**
     * This color constant "bold pure cyan" has RGBA8888 code {@code 39F9F8FF}, L 0.83137256, A 0.42745098, B 0.47843137, alpha 1.0, hue 0.54598206, saturation 0.86514735, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.f4dba8p125F}.
     * <pre>
     * <font style='background-color: #39F9F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #39F9F8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #39F9F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #39F9F8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #39F9F8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #39F9F8'>&nbsp;@&nbsp;</font><font style='background-color: #39F9F8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #39F9F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #39F9F8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_CYAN = -0x1.f4dba8p125F;
    static { NAMED.put("bold pure cyan", -0x1.f4dba8p125F); LIST.add(-0x1.f4dba8p125F); }

    /**
     * This color constant "bold blue cyan" has RGBA8888 code {@code 00CCFEFF}, L 0.6901961, A 0.44313726, B 0.4509804, alpha 1.0, hue 0.6132407, saturation 0.89376616, and chroma 0.14956398.
     * It can be represented as a packed float with the constant {@code -0x1.e6e36p125F}.
     * <pre>
     * <font style='background-color: #00CCFE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00CCFE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00CCFE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00CCFE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00CCFE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00CCFE'>&nbsp;@&nbsp;</font><font style='background-color: #00CCFE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00CCFE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00CCFE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_CYAN = -0x1.e6e36p125F;
    static { NAMED.put("bold blue cyan", -0x1.e6e36p125F); LIST.add(-0x1.e6e36p125F); }

    /**
     * This color constant "bold cyan blue" has RGBA8888 code {@code 179BEFFF}, L 0.5529412, A 0.45882353, B 0.42745098, alpha 1.0, hue 0.6678337, saturation 0.914026, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.daeb1ap125F}.
     * <pre>
     * <font style='background-color: #179BEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #179BEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #179BEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #179BEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #179BEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #179BEF'>&nbsp;@&nbsp;</font><font style='background-color: #179BEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #179BEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #179BEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_CYAN_BLUE = -0x1.daeb1ap125F;
    static { NAMED.put("bold cyan blue", -0x1.daeb1ap125F); LIST.add(-0x1.daeb1ap125F); }

    /**
     * This color constant "bold pure blue" has RGBA8888 code {@code 0000FFFF}, L 0.31764707, A 0.48235294, B 0.34117648, alpha 1.0, hue 0.7323789, saturation 0.97586775, and chroma 0.31835338.
     * It can be represented as a packed float with the constant {@code -0x1.aef6a2p125F}.
     * <pre>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #0000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BLUE = -0x1.aef6a2p125F;
    static { NAMED.put("bold pure blue", -0x1.aef6a2p125F); LIST.add(-0x1.aef6a2p125F); }

    /**
     * This color constant "bold violet blue" has RGBA8888 code {@code 3F25F1FF}, L 0.34509805, A 0.50980395, B 0.36078432, alpha 1.0, hue 0.7611862, saturation 0.80155057, and chroma 0.27803063.
     * It can be represented as a packed float with the constant {@code -0x1.b904bp125F}.
     * <pre>
     * <font style='background-color: #3F25F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F25F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F25F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F25F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F25F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F25F1'>&nbsp;@&nbsp;</font><font style='background-color: #3F25F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F25F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F25F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_BLUE = -0x1.b904bp125F;
    static { NAMED.put("bold violet blue", -0x1.b904bp125F); LIST.add(-0x1.b904bp125F); }

    /**
     * This color constant "bold blue violet" has RGBA8888 code {@code 6400FEFF}, L 0.37254903, A 0.54509807, B 0.35686275, alpha 1.0, hue 0.7985663, saturation 0.97531736, and chroma 0.2989749.
     * It can be represented as a packed float with the constant {@code -0x1.b716bep125F}.
     * <pre>
     * <font style='background-color: #6400FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6400FE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6400FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6400FE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6400FE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6400FE'>&nbsp;@&nbsp;</font><font style='background-color: #6400FE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6400FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6400FE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BLUE_VIOLET = -0x1.b716bep125F;
    static { NAMED.put("bold blue violet", -0x1.b716bep125F); LIST.add(-0x1.b716bep125F); }

    /**
     * This color constant "bold pure violet" has RGBA8888 code {@code 8824F1FF}, L 0.4117647, A 0.5686275, B 0.38039216, alpha 1.0, hue 0.8329116, saturation 0.82347554, and chroma 0.274718.
     * It can be represented as a packed float with the constant {@code -0x1.c322d2p125F}.
     * <pre>
     * <font style='background-color: #8824F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8824F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8824F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8824F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8824F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8824F1'>&nbsp;@&nbsp;</font><font style='background-color: #8824F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8824F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8824F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_VIOLET = -0x1.c322d2p125F;
    static { NAMED.put("bold pure violet", -0x1.c322d2p125F); LIST.add(-0x1.c322d2p125F); }

    /**
     * This color constant "bold purple violet" has RGBA8888 code {@code 9D00FFFF}, L 0.43529412, A 0.58431375, B 0.3764706, alpha 1.0, hue 0.84533215, saturation 0.9205332, and chroma 0.2979524.
     * It can be represented as a packed float with the constant {@code -0x1.c12adep125F}.
     * <pre>
     * <font style='background-color: #9D00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D00FF'>&nbsp;@&nbsp;</font><font style='background-color: #9D00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_VIOLET = -0x1.c12adep125F;
    static { NAMED.put("bold purple violet", -0x1.c12adep125F); LIST.add(-0x1.c12adep125F); }

    /**
     * This color constant "bold violet purple" has RGBA8888 code {@code AA28FAFF}, L 0.4627451, A 0.5882353, B 0.3882353, alpha 1.0, hue 0.8563733, saturation 0.8344607, and chroma 0.2836809.
     * It can be represented as a packed float with the constant {@code -0x1.c72cecp125F}.
     * <pre>
     * <font style='background-color: #AA28FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA28FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA28FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA28FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA28FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA28FA'>&nbsp;@&nbsp;</font><font style='background-color: #AA28FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA28FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA28FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_VIOLET_PURPLE = -0x1.c72cecp125F;
    static { NAMED.put("bold violet purple", -0x1.c72cecp125F); LIST.add(-0x1.c72cecp125F); }

    /**
     * This color constant "bold pure purple" has RGBA8888 code {@code BF00FFFF}, L 0.47843137, A 0.6039216, B 0.3882353, alpha 1.0, hue 0.86921954, saturation 0.9348405, and chroma 0.30403575.
     * It can be represented as a packed float with the constant {@code -0x1.c734f4p125F}.
     * <pre>
     * <font style='background-color: #BF00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF00FF'>&nbsp;@&nbsp;</font><font style='background-color: #BF00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_PURPLE = -0x1.c734f4p125F;
    static { NAMED.put("bold pure purple", -0x1.c734f4p125F); LIST.add(-0x1.c734f4p125F); }

    /**
     * This color constant "bold magenta purple" has RGBA8888 code {@code CB25F8FF}, L 0.5019608, A 0.60784316, B 0.4, alpha 1.0, hue 0.8809984, saturation 0.84700215, and chroma 0.2929948.
     * It can be represented as a packed float with the constant {@code -0x1.cd37p125F}.
     * <pre>
     * <font style='background-color: #CB25F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB25F8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB25F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB25F8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB25F8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB25F8'>&nbsp;@&nbsp;</font><font style='background-color: #CB25F8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB25F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB25F8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_PURPLE = -0x1.cd37p125F;
    static { NAMED.put("bold magenta purple", -0x1.cd37p125F); LIST.add(-0x1.cd37p125F); }

    /**
     * This color constant "bold purple magenta" has RGBA8888 code {@code EC0FFFFF}, L 0.54509807, A 0.627451, B 0.40784314, alpha 1.0, hue 0.90034866, saturation 0.9228011, and chroma 0.31332898.
     * It can be represented as a packed float with the constant {@code -0x1.d14116p125F}.
     * <pre>
     * <font style='background-color: #EC0FFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC0FFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC0FFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EC0FFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EC0FFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EC0FFF'>&nbsp;@&nbsp;</font><font style='background-color: #EC0FFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC0FFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC0FFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURPLE_MAGENTA = -0x1.d14116p125F;
    static { NAMED.put("bold purple magenta", -0x1.d14116p125F); LIST.add(-0x1.d14116p125F); }

    /**
     * This color constant "bold pure magenta" has RGBA8888 code {@code F42FFAFF}, L 0.5686275, A 0.62352943, B 0.41568628, alpha 1.0, hue 0.90466785, saturation 0.8148174, and chroma 0.2979524.
     * It can be represented as a packed float with the constant {@code -0x1.d53f22p125F}.
     * <pre>
     * <font style='background-color: #F42FFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F42FFA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F42FFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F42FFA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F42FFA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F42FFA'>&nbsp;@&nbsp;</font><font style='background-color: #F42FFA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F42FFA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F42FFA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_MAGENTA = -0x1.d53f22p125F;
    static { NAMED.put("bold pure magenta", -0x1.d53f22p125F); LIST.add(-0x1.d53f22p125F); }

    /**
     * This color constant "bold red magenta" has RGBA8888 code {@code FF00A9FF}, L 0.5254902, A 0.63529414, B 0.46666667, alpha 1.0, hue 0.96155566, saturation 0.9347957, and chroma 0.27759123.
     * It can be represented as a packed float with the constant {@code -0x1.ef450cp125F}.
     * <pre>
     * <font style='background-color: #FF00A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00A9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF00A9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF00A9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF00A9'>&nbsp;@&nbsp;</font><font style='background-color: #FF00A9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF00A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF00A9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_MAGENTA = -0x1.ef450cp125F;
    static { NAMED.put("bold red magenta", -0x1.ef450cp125F); LIST.add(-0x1.ef450cp125F); }

    /**
     * This color constant "bold magenta red" has RGBA8888 code {@code F52664FF}, L 0.49803922, A 0.6156863, B 0.5176471, alpha 1.0, hue 0.024104964, saturation 0.7817833, and chroma 0.23313475.
     * It can be represented as a packed float with the constant {@code -0x1.093afep126F}.
     * <pre>
     * <font style='background-color: #F52664;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F52664; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F52664;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F52664'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F52664'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F52664'>&nbsp;@&nbsp;</font><font style='background-color: #F52664; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F52664;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F52664; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_MAGENTA_RED = -0x1.093afep126F;
    static { NAMED.put("bold magenta red", -0x1.093afep126F); LIST.add(-0x1.093afep126F); }

    /**
     * All names for colors in this palette, in alphabetical order. You can fetch the corresponding packed float color
     * by looking up a name in {@link #NAMED}.
     */
    public static final Array<String> NAMES = NAMED.keys().toArray();
    static { NAMES.sort(); }
    /**
     * All names for colors in this palette, sorted by hue from red to yellow to green to blue. You can fetch the
     * corresponding packed float color by looking up a name in {@link #NAMED}.
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
                final float c1 = NAMED.get(o1, YAM_TRANSPARENT), c2 = NAMED.get(o2, YAM_TRANSPARENT);
                if(ColorTools.alphaInt(c1) < 128) return -10000;
                else if(ColorTools.alphaInt(c2) < 128) return 10000;
                final float s1 = ColorTools.oklabSaturation(c1), s2 = ColorTools.oklabSaturation(c2);
                if(s1 <= 0.05f && s2 > 0.05f)
                    return -1000;
                else if(s1 > 0.05f && s2 <= 0.05f)
                    return 1000;
                else if(s1 <= 0.05f && s2 <= 0.05f)
                    return (int)Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
                else
                    return 2 * (int)Math.signum(ColorTools.oklabHue(c1) - ColorTools.oklabHue(c2))
                            + (int)Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
            }
        });
        NAMES_BY_LIGHTNESS.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Float.compare(ColorTools.channelL(NAMED.get(o1, YAM_TRANSPARENT)), ColorTools.channelL(NAMED.get(o2, YAM_TRANSPARENT)));
            }
        });
    }
}
