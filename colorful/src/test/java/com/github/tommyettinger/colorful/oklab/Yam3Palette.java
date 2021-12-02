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
     * This color constant "black red" has RGBA8888 code {@code 4E221EFF}, L 0.21960784, A 0.53333336, B 0.5137255, alpha 1.0, hue 0.062156405, saturation 0.24035555, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.07107p126F}.
     * <pre>
     * <font style='background-color: #4E221E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E221E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E221E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E221E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E221E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E221E'>&nbsp;@&nbsp;</font><font style='background-color: #4E221E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E221E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E221E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_RED = -0x1.07107p126F;
    static { NAMED.put("black red", -0x1.07107p126F); LIST.add(-0x1.07107p126F); }

    /**
     * This color constant "lead red" has RGBA8888 code {@code 6F3D37FF}, L 0.3137255, A 0.5294118, B 0.5137255, alpha 1.0, hue 0.06948605, saturation 0.111825325, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.070eap126F}.
     * <pre>
     * <font style='background-color: #6F3D37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F3D37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F3D37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F3D37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F3D37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F3D37'>&nbsp;@&nbsp;</font><font style='background-color: #6F3D37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F3D37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F3D37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_RED = -0x1.070eap126F;
    static { NAMED.put("lead red", -0x1.070eap126F); LIST.add(-0x1.070eap126F); }

    /**
     * This color constant "gray red" has RGBA8888 code {@code 925952FF}, L 0.41568628, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.10108599, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.0910d4p126F}.
     * <pre>
     * <font style='background-color: #925952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #925952; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #925952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #925952'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #925952'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #925952'>&nbsp;@&nbsp;</font><font style='background-color: #925952; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #925952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #925952; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_RED = -0x1.0910d4p126F;
    static { NAMED.put("gray red", -0x1.0910d4p126F); LIST.add(-0x1.0910d4p126F); }

    /**
     * This color constant "silver red" has RGBA8888 code {@code B77870FF}, L 0.5294118, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.09782537, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.09110ep126F}.
     * <pre>
     * <font style='background-color: #B77870;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B77870; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B77870;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B77870'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B77870'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B77870'>&nbsp;@&nbsp;</font><font style='background-color: #B77870; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B77870;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B77870; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_RED = -0x1.09110ep126F;
    static { NAMED.put("silver red", -0x1.09110ep126F); LIST.add(-0x1.09110ep126F); }

    /**
     * This color constant "white red" has RGBA8888 code {@code DE9B91FF}, L 0.65882355, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.08601887, saturation 0.20644291, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090f5p126F}.
     * <pre>
     * <font style='background-color: #DE9B91;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE9B91; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE9B91;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DE9B91'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DE9B91'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DE9B91'>&nbsp;@&nbsp;</font><font style='background-color: #DE9B91; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE9B91;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE9B91; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_RED = -0x1.090f5p126F;
    static { NAMED.put("white red", -0x1.090f5p126F); LIST.add(-0x1.090f5p126F); }

    /**
     * This color constant "black brown" has RGBA8888 code {@code 4D3832FF}, L 0.2627451, A 0.5137255, B 0.50980395, alpha 1.0, hue 0.09872868, saturation 0.05554513, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.050686p126F}.
     * <pre>
     * <font style='background-color: #4D3832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4D3832; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4D3832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4D3832'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4D3832'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4D3832'>&nbsp;@&nbsp;</font><font style='background-color: #4D3832; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4D3832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4D3832; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BROWN = -0x1.050686p126F;
    static { NAMED.put("black brown", -0x1.050686p126F); LIST.add(-0x1.050686p126F); }

    /**
     * This color constant "lead brown" has RGBA8888 code {@code 745C55FF}, L 0.3882353, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.030483158, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.0504c6p126F}.
     * <pre>
     * <font style='background-color: #745C55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #745C55; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #745C55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #745C55'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #745C55'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #745C55'>&nbsp;@&nbsp;</font><font style='background-color: #745C55; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #745C55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #745C55; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BROWN = -0x1.0504c6p126F;
    static { NAMED.put("lead brown", -0x1.0504c6p126F); LIST.add(-0x1.0504c6p126F); }

    /**
     * This color constant "gray brown" has RGBA8888 code {@code 947A73FF}, L 0.49411765, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.08601887, saturation 0.008172586, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.0304fcp126F}.
     * <pre>
     * <font style='background-color: #947A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #947A73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #947A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #947A73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #947A73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #947A73'>&nbsp;@&nbsp;</font><font style='background-color: #947A73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #947A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #947A73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BROWN = -0x1.0304fcp126F;
    static { NAMED.put("gray brown", -0x1.0304fcp126F); LIST.add(-0x1.0304fcp126F); }

    /**
     * This color constant "silver brown" has RGBA8888 code {@code B0938CFF}, L 0.5882353, A 0.5137255, B 0.5058824, alpha 1.0, hue 0.06443131, saturation 0.023671053, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.03072cp126F}.
     * <pre>
     * <font style='background-color: #B0938C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0938C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0938C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0938C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0938C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0938C'>&nbsp;@&nbsp;</font><font style='background-color: #B0938C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0938C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0938C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BROWN = -0x1.03072cp126F;
    static { NAMED.put("silver brown", -0x1.03072cp126F); LIST.add(-0x1.03072cp126F); }

    /**
     * This color constant "white brown" has RGBA8888 code {@code CFB1A9FF}, L 0.7019608, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.08601887, saturation 0.03218935, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.030566p126F}.
     * <pre>
     * <font style='background-color: #CFB1A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB1A9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB1A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFB1A9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFB1A9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFB1A9'>&nbsp;@&nbsp;</font><font style='background-color: #CFB1A9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB1A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB1A9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BROWN = -0x1.030566p126F;
    static { NAMED.put("white brown", -0x1.030566p126F); LIST.add(-0x1.030566p126F); }

    /**
     * This color constant "black orange" has RGBA8888 code {@code 4F3624FF}, L 0.25882354, A 0.50980395, B 0.52156866, alpha 1.0, hue 0.18210676, saturation 0.24323198, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0b0484p126F}.
     * <pre>
     * <font style='background-color: #4F3624;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F3624; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F3624;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F3624'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F3624'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F3624'>&nbsp;@&nbsp;</font><font style='background-color: #4F3624; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F3624;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F3624; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_ORANGE = -0x1.0b0484p126F;
    static { NAMED.put("black orange", -0x1.0b0484p126F); LIST.add(-0x1.0b0484p126F); }

    /**
     * This color constant "lead orange" has RGBA8888 code {@code 735641FF}, L 0.36862746, A 0.50980395, B 0.52156866, alpha 1.0, hue 0.18210676, saturation 0.14714034, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0b04bcp126F}.
     * <pre>
     * <font style='background-color: #735641;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #735641; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #735641;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #735641'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #735641'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #735641'>&nbsp;@&nbsp;</font><font style='background-color: #735641; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #735641;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #735641; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_ORANGE = -0x1.0b04bcp126F;
    static { NAMED.put("lead orange", -0x1.0b04bcp126F); LIST.add(-0x1.0b04bcp126F); }

    /**
     * This color constant "gray orange" has RGBA8888 code {@code 93735CFF}, L 0.47058824, A 0.50980395, B 0.52156866, alpha 1.0, hue 0.18210676, saturation 0.103822224, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0b04fp126F}.
     * <pre>
     * <font style='background-color: #93735C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93735C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93735C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93735C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93735C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93735C'>&nbsp;@&nbsp;</font><font style='background-color: #93735C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93735C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93735C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_ORANGE = -0x1.0b04fp126F;
    static { NAMED.put("gray orange", -0x1.0b04fp126F); LIST.add(-0x1.0b04fp126F); }

    /**
     * This color constant "silver orange" has RGBA8888 code {@code B6937BFF}, L 0.5882353, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15979148, saturation 0.07534626, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b072cp126F}.
     * <pre>
     * <font style='background-color: #B6937B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6937B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6937B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6937B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6937B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6937B'>&nbsp;@&nbsp;</font><font style='background-color: #B6937B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6937B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6937B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_ORANGE = -0x1.0b072cp126F;
    static { NAMED.put("silver orange", -0x1.0b072cp126F); LIST.add(-0x1.0b072cp126F); }

    /**
     * This color constant "white orange" has RGBA8888 code {@code DBB69CFF}, L 0.72156864, A 0.50980395, B 0.52156866, alpha 1.0, hue 0.18210676, saturation 0.09357475, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0b057p126F}.
     * <pre>
     * <font style='background-color: #DBB69C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBB69C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBB69C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBB69C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBB69C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBB69C'>&nbsp;@&nbsp;</font><font style='background-color: #DBB69C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBB69C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBB69C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_ORANGE = -0x1.0b057p126F;
    static { NAMED.put("white orange", -0x1.0b057p126F); LIST.add(-0x1.0b057p126F); }

    /**
     * This color constant "black saffron" has RGBA8888 code {@code 54432EFF}, L 0.29411766, A 0.5058824, B 0.5176471, alpha 1.0, hue 0.19880433, saturation 0.1281595, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.090296p126F}.
     * <pre>
     * <font style='background-color: #54432E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54432E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54432E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #54432E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #54432E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #54432E'>&nbsp;@&nbsp;</font><font style='background-color: #54432E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54432E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54432E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_SAFFRON = -0x1.090296p126F;
    static { NAMED.put("black saffron", -0x1.090296p126F); LIST.add(-0x1.090296p126F); }

    /**
     * This color constant "lead saffron" has RGBA8888 code {@code 7F6B52FF}, L 0.43137255, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.20763123, saturation 0.115838714, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b02dcp126F}.
     * <pre>
     * <font style='background-color: #7F6B52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F6B52; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F6B52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F6B52'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F6B52'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F6B52'>&nbsp;@&nbsp;</font><font style='background-color: #7F6B52; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F6B52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F6B52; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_SAFFRON = -0x1.0b02dcp126F;
    static { NAMED.put("lead saffron", -0x1.0b02dcp126F); LIST.add(-0x1.0b02dcp126F); }

    /**
     * This color constant "gray saffron" has RGBA8888 code {@code A38D72FF}, L 0.5529412, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.20763123, saturation 0.083319984, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b031ap126F}.
     * <pre>
     * <font style='background-color: #A38D72;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A38D72; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A38D72;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A38D72'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A38D72'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A38D72'>&nbsp;@&nbsp;</font><font style='background-color: #A38D72; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A38D72;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A38D72; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SAFFRON = -0x1.0b031ap126F;
    static { NAMED.put("gray saffron", -0x1.0b031ap126F); LIST.add(-0x1.0b031ap126F); }

    /**
     * This color constant "silver saffron" has RGBA8888 code {@code C0AA8DFF}, L 0.65882355, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.235567, saturation 0.06447351, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b015p126F}.
     * <pre>
     * <font style='background-color: #C0AA8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0AA8D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0AA8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0AA8D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0AA8D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0AA8D'>&nbsp;@&nbsp;</font><font style='background-color: #C0AA8D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0AA8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0AA8D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_SAFFRON = -0x1.0b015p126F;
    static { NAMED.put("silver saffron", -0x1.0b015p126F); LIST.add(-0x1.0b015p126F); }

    /**
     * This color constant "white saffron" has RGBA8888 code {@code DCC5A6FF}, L 0.7647059, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.235567, saturation 0.054072022, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b0186p126F}.
     * <pre>
     * <font style='background-color: #DCC5A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCC5A6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCC5A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DCC5A6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DCC5A6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DCC5A6'>&nbsp;@&nbsp;</font><font style='background-color: #DCC5A6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCC5A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCC5A6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SAFFRON = -0x1.0b0186p126F;
    static { NAMED.put("white saffron", -0x1.0b0186p126F); LIST.add(-0x1.0b0186p126F); }

    /**
     * This color constant "black yellow" has RGBA8888 code {@code 4E4E28FF}, L 0.30980393, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.31485206, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef89ep126F}.
     * <pre>
     * <font style='background-color: #4E4E28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E4E28; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E4E28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E4E28'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E4E28'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E4E28'>&nbsp;@&nbsp;</font><font style='background-color: #4E4E28; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E4E28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E4E28; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_YELLOW = -0x1.0ef89ep126F;
    static { NAMED.put("black yellow", -0x1.0ef89ep126F); LIST.add(-0x1.0ef89ep126F); }

    /**
     * This color constant "lead yellow" has RGBA8888 code {@code 75754BFF}, L 0.44313726, A 0.49019608, B 0.5294118, alpha 1.0, hue 0.30119568, saturation 0.19837333, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.0efae2p126F}.
     * <pre>
     * <font style='background-color: #75754B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #75754B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #75754B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #75754B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #75754B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #75754B'>&nbsp;@&nbsp;</font><font style='background-color: #75754B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #75754B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #75754B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_YELLOW = -0x1.0efae2p126F;
    static { NAMED.put("lead yellow", -0x1.0efae2p126F); LIST.add(-0x1.0efae2p126F); }

    /**
     * This color constant "gray yellow" has RGBA8888 code {@code 949567FF}, L 0.5529412, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.14480117, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef91ap126F}.
     * <pre>
     * <font style='background-color: #949567;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #949567; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #949567;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #949567'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #949567'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #949567'>&nbsp;@&nbsp;</font><font style='background-color: #949567; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #949567;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #949567; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_YELLOW = -0x1.0ef91ap126F;
    static { NAMED.put("gray yellow", -0x1.0ef91ap126F); LIST.add(-0x1.0ef91ap126F); }

    /**
     * This color constant "silver yellow" has RGBA8888 code {@code B3B383FF}, L 0.6666667, A 0.49019608, B 0.5294118, alpha 1.0, hue 0.30119568, saturation 0.1156203, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.0efb54p126F}.
     * <pre>
     * <font style='background-color: #B3B383;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3B383; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3B383;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B3B383'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B3B383'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B3B383'>&nbsp;@&nbsp;</font><font style='background-color: #B3B383; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3B383;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3B383; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_YELLOW = -0x1.0efb54p126F;
    static { NAMED.put("silver yellow", -0x1.0efb54p126F); LIST.add(-0x1.0efb54p126F); }

    /**
     * This color constant "white yellow" has RGBA8888 code {@code D5D6A2FF}, L 0.8, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.09224813, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef998p126F}.
     * <pre>
     * <font style='background-color: #D5D6A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5D6A2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5D6A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5D6A2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5D6A2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5D6A2'>&nbsp;@&nbsp;</font><font style='background-color: #D5D6A2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5D6A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5D6A2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_YELLOW = -0x1.0ef998p126F;
    static { NAMED.put("white yellow", -0x1.0ef998p126F); LIST.add(-0x1.0ef998p126F); }

    /**
     * This color constant "black lime" has RGBA8888 code {@code 394B27FF}, L 0.28627452, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.25370613, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf292p126F}.
     * <pre>
     * <font style='background-color: #394B27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #394B27; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #394B27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #394B27'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #394B27'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #394B27'>&nbsp;@&nbsp;</font><font style='background-color: #394B27; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #394B27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #394B27; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LIME = -0x1.0cf292p126F;
    static { NAMED.put("black lime", -0x1.0cf292p126F); LIST.add(-0x1.0cf292p126F); }

    /**
     * This color constant "lead lime" has RGBA8888 code {@code 576B43FF}, L 0.39215687, A 0.47843137, B 0.5254902, alpha 1.0, hue 0.361777, saturation 0.16838439, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.0cf4c8p126F}.
     * <pre>
     * <font style='background-color: #576B43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #576B43; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #576B43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #576B43'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #576B43'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #576B43'>&nbsp;@&nbsp;</font><font style='background-color: #576B43; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #576B43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #576B43; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_LIME = -0x1.0cf4c8p126F;
    static { NAMED.put("lead lime", -0x1.0cf4c8p126F); LIST.add(-0x1.0cf4c8p126F); }

    /**
     * This color constant "gray lime" has RGBA8888 code {@code 768D61FF}, L 0.5058824, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.118088916, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf302p126F}.
     * <pre>
     * <font style='background-color: #768D61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #768D61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #768D61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #768D61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #768D61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #768D61'>&nbsp;@&nbsp;</font><font style='background-color: #768D61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #768D61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #768D61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LIME = -0x1.0cf302p126F;
    static { NAMED.put("gray lime", -0x1.0cf302p126F); LIST.add(-0x1.0cf302p126F); }

    /**
     * This color constant "silver lime" has RGBA8888 code {@code 98B182FF}, L 0.63529414, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.086528, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf344p126F}.
     * <pre>
     * <font style='background-color: #98B182;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98B182; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98B182;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98B182'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98B182'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98B182'>&nbsp;@&nbsp;</font><font style='background-color: #98B182; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98B182;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98B182; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_LIME = -0x1.0cf344p126F;
    static { NAMED.put("silver lime", -0x1.0cf344p126F); LIST.add(-0x1.0cf344p126F); }

    /**
     * This color constant "white lime" has RGBA8888 code {@code BDD7A5FF}, L 0.78039217, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.06800463, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf38ep126F}.
     * <pre>
     * <font style='background-color: #BDD7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDD7A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDD7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BDD7A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BDD7A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BDD7A5'>&nbsp;@&nbsp;</font><font style='background-color: #BDD7A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDD7A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDD7A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_LIME = -0x1.0cf38ep126F;
    static { NAMED.put("white lime", -0x1.0cf38ep126F); LIST.add(-0x1.0cf38ep126F); }

    /**
     * This color constant "black green" has RGBA8888 code {@code 2D5532FF}, L 0.30588236, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.42949432, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aec9cp126F}.
     * <pre>
     * <font style='background-color: #2D5532;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D5532; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D5532;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D5532'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D5532'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D5532'>&nbsp;@&nbsp;</font><font style='background-color: #2D5532; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D5532;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D5532; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_GREEN = -0x1.0aec9cp126F;
    static { NAMED.put("black green", -0x1.0aec9cp126F); LIST.add(-0x1.0aec9cp126F); }

    /**
     * This color constant "lead green" has RGBA8888 code {@code 517E56FF}, L 0.4392157, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.25472322, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aecep126F}.
     * <pre>
     * <font style='background-color: #517E56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #517E56; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #517E56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #517E56'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #517E56'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #517E56'>&nbsp;@&nbsp;</font><font style='background-color: #517E56; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #517E56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #517E56; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GREEN = -0x1.0aecep126F;
    static { NAMED.put("lead green", -0x1.0aecep126F); LIST.add(-0x1.0aecep126F); }

    /**
     * This color constant "gray green" has RGBA8888 code {@code 70A075FF}, L 0.5568628, A 0.4627451, B 0.5176471, alpha 1.0, hue 0.4295985, saturation 0.19590028, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.08ed1cp126F}.
     * <pre>
     * <font style='background-color: #70A075;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #70A075; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #70A075;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #70A075'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #70A075'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #70A075'>&nbsp;@&nbsp;</font><font style='background-color: #70A075; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #70A075;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #70A075; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.08ed1cp126F;
    static { NAMED.put("gray green", -0x1.08ed1cp126F); LIST.add(-0x1.08ed1cp126F); }

    /**
     * This color constant "silver green" has RGBA8888 code {@code 8ABD8FFF}, L 0.65882355, A 0.4627451, B 0.5176471, alpha 1.0, hue 0.4295985, saturation 0.15442397, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.08ed5p126F}.
     * <pre>
     * <font style='background-color: #8ABD8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8ABD8F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8ABD8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8ABD8F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8ABD8F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8ABD8F'>&nbsp;@&nbsp;</font><font style='background-color: #8ABD8F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8ABD8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8ABD8F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.08ed5p126F;
    static { NAMED.put("silver green", -0x1.08ed5p126F); LIST.add(-0x1.08ed5p126F); }

    /**
     * This color constant "white green" has RGBA8888 code {@code A6DBAAFF}, L 0.77254903, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.11585842, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed8ap126F}.
     * <pre>
     * <font style='background-color: #A6DBAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6DBAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6DBAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6DBAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6DBAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6DBAA'>&nbsp;@&nbsp;</font><font style='background-color: #A6DBAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6DBAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6DBAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_GREEN = -0x1.0aed8ap126F;
    static { NAMED.put("white green", -0x1.0aed8ap126F); LIST.add(-0x1.0aed8ap126F); }

    /**
     * This color constant "black cyan" has RGBA8888 code {@code 2D5050FF}, L 0.3019608, A 0.47843137, B 0.49019608, alpha 1.0, hue 0.56789327, saturation 0.3158464, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.faf49ap125F}.
     * <pre>
     * <font style='background-color: #2D5050;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D5050; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D5050;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D5050'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D5050'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D5050'>&nbsp;@&nbsp;</font><font style='background-color: #2D5050; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D5050;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D5050; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_CYAN = -0x1.faf49ap125F;
    static { NAMED.put("black cyan", -0x1.faf49ap125F); LIST.add(-0x1.faf49ap125F); }

    /**
     * This color constant "lead cyan" has RGBA8888 code {@code 4E7574FF}, L 0.42352942, A 0.4745098, B 0.49411765, alpha 1.0, hue 0.5360971, saturation 0.2353719, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.fcf2d8p125F}.
     * <pre>
     * <font style='background-color: #4E7574;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7574; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7574;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E7574'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E7574'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E7574'>&nbsp;@&nbsp;</font><font style='background-color: #4E7574; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7574;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7574; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_CYAN = -0x1.fcf2d8p125F;
    static { NAMED.put("lead cyan", -0x1.fcf2d8p125F); LIST.add(-0x1.fcf2d8p125F); }

    /**
     * This color constant "gray cyan" has RGBA8888 code {@code 699392FF}, L 0.5254902, A 0.4745098, B 0.49411765, alpha 1.0, hue 0.5360971, saturation 0.17939028, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.fcf30cp125F}.
     * <pre>
     * <font style='background-color: #699392;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #699392; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #699392;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #699392'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #699392'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #699392'>&nbsp;@&nbsp;</font><font style='background-color: #699392; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #699392;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #699392; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_CYAN = -0x1.fcf30cp125F;
    static { NAMED.put("gray cyan", -0x1.fcf30cp125F); LIST.add(-0x1.fcf30cp125F); }

    /**
     * This color constant "silver cyan" has RGBA8888 code {@code 87B3B3FF}, L 0.6431373, A 0.47843137, B 0.49019608, alpha 1.0, hue 0.56789327, saturation 0.10958904, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.faf548p125F}.
     * <pre>
     * <font style='background-color: #87B3B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87B3B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87B3B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87B3B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87B3B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87B3B3'>&nbsp;@&nbsp;</font><font style='background-color: #87B3B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87B3B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87B3B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_CYAN = -0x1.faf548p125F;
    static { NAMED.put("silver cyan", -0x1.faf548p125F); LIST.add(-0x1.faf548p125F); }

    /**
     * This color constant "white cyan" has RGBA8888 code {@code A9D7D6FF}, L 0.78039217, A 0.47843137, B 0.49411765, alpha 1.0, hue 0.54236877, saturation 0.079256214, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf58ep125F}.
     * <pre>
     * <font style='background-color: #A9D7D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9D7D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9D7D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A9D7D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A9D7D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A9D7D6'>&nbsp;@&nbsp;</font><font style='background-color: #A9D7D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A9D7D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A9D7D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_CYAN = -0x1.fcf58ep125F;
    static { NAMED.put("white cyan", -0x1.fcf58ep125F); LIST.add(-0x1.fcf58ep125F); }

    /**
     * This color constant "black blue" has RGBA8888 code {@code 1B2F5BFF}, L 0.21960784, A 0.49019608, B 0.45490196, alpha 1.0, hue 0.7159276, saturation 0.3377534, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.e8fa7p125F}.
     * <pre>
     * <font style='background-color: #1B2F5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1B2F5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1B2F5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1B2F5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1B2F5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1B2F5B'>&nbsp;@&nbsp;</font><font style='background-color: #1B2F5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1B2F5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1B2F5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BLUE = -0x1.e8fa7p125F;
    static { NAMED.put("black blue", -0x1.e8fa7p125F); LIST.add(-0x1.e8fa7p125F); }

    /**
     * This color constant "lead blue" has RGBA8888 code {@code 3A5387FF}, L 0.34117648, A 0.49019608, B 0.4509804, alpha 1.0, hue 0.71857655, saturation 0.20361814, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e6faaep125F}.
     * <pre>
     * <font style='background-color: #3A5387;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A5387; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A5387;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A5387'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A5387'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A5387'>&nbsp;@&nbsp;</font><font style='background-color: #3A5387; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A5387;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A5387; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLUE = -0x1.e6faaep125F;
    static { NAMED.put("lead blue", -0x1.e6faaep125F); LIST.add(-0x1.e6faaep125F); }

    /**
     * This color constant "gray blue" has RGBA8888 code {@code 5673ABFF}, L 0.44705883, A 0.49019608, B 0.45490196, alpha 1.0, hue 0.7159276, saturation 0.14647366, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.e8fae4p125F}.
     * <pre>
     * <font style='background-color: #5673AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5673AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5673AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5673AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5673AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5673AB'>&nbsp;@&nbsp;</font><font style='background-color: #5673AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5673AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5673AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BLUE = -0x1.e8fae4p125F;
    static { NAMED.put("gray blue", -0x1.e8fae4p125F); LIST.add(-0x1.e8fae4p125F); }

    /**
     * This color constant "silver blue" has RGBA8888 code {@code 6F8ECAFF}, L 0.5411765, A 0.49019608, B 0.4509804, alpha 1.0, hue 0.71857655, saturation 0.2763312, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e6fb14p125F}.
     * <pre>
     * <font style='background-color: #6F8ECA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F8ECA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F8ECA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F8ECA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F8ECA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F8ECA'>&nbsp;@&nbsp;</font><font style='background-color: #6F8ECA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F8ECA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F8ECA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BLUE = -0x1.e6fb14p125F;
    static { NAMED.put("silver blue", -0x1.e6fb14p125F); LIST.add(-0x1.e6fb14p125F); }

    /**
     * This color constant "white blue" has RGBA8888 code {@code 86A6E6FF}, L 0.6313726, A 0.49411765, B 0.4509804, alpha 1.0, hue 0.73098123, saturation 0.47588667, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.e6fd42p125F}.
     * <pre>
     * <font style='background-color: #86A6E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86A6E6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86A6E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #86A6E6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #86A6E6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #86A6E6'>&nbsp;@&nbsp;</font><font style='background-color: #86A6E6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86A6E6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86A6E6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BLUE = -0x1.e6fd42p125F;
    static { NAMED.put("white blue", -0x1.e6fd42p125F); LIST.add(-0x1.e6fd42p125F); }

    /**
     * This color constant "black violet" has RGBA8888 code {@code 3A2D55FF}, L 0.23529412, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.16665095, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed0878p125F}.
     * <pre>
     * <font style='background-color: #3A2D55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A2D55; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A2D55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A2D55'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A2D55'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A2D55'>&nbsp;@&nbsp;</font><font style='background-color: #3A2D55; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A2D55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A2D55; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_VIOLET = -0x1.ed0878p125F;
    static { NAMED.put("black violet", -0x1.ed0878p125F); LIST.add(-0x1.ed0878p125F); }

    /**
     * This color constant "lead violet" has RGBA8888 code {@code 5B4E7BFF}, L 0.34901962, A 0.5137255, B 0.46666667, alpha 1.0, hue 0.8121564, saturation 0.0661157, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.ef06b2p125F}.
     * <pre>
     * <font style='background-color: #5B4E7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B4E7B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B4E7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B4E7B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B4E7B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B4E7B'>&nbsp;@&nbsp;</font><font style='background-color: #5B4E7B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B4E7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B4E7B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_VIOLET = -0x1.ef06b2p125F;
    static { NAMED.put("lead violet", -0x1.ef06b2p125F); LIST.add(-0x1.ef06b2p125F); }

    /**
     * This color constant "gray violet" has RGBA8888 code {@code 796B9DFF}, L 0.4509804, A 0.5137255, B 0.4627451, alpha 1.0, hue 0.80616736, saturation 0.08998628, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.ed06e6p125F}.
     * <pre>
     * <font style='background-color: #796B9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #796B9D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #796B9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #796B9D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #796B9D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #796B9D'>&nbsp;@&nbsp;</font><font style='background-color: #796B9D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #796B9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #796B9D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_VIOLET = -0x1.ed06e6p125F;
    static { NAMED.put("gray violet", -0x1.ed06e6p125F); LIST.add(-0x1.ed06e6p125F); }

    /**
     * This color constant "silver violet" has RGBA8888 code {@code 9384B9FF}, L 0.5411765, A 0.5137255, B 0.4627451, alpha 1.0, hue 0.80616736, saturation 0.14875284, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.ed0714p125F}.
     * <pre>
     * <font style='background-color: #9384B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9384B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9384B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9384B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9384B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9384B9'>&nbsp;@&nbsp;</font><font style='background-color: #9384B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9384B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9384B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_VIOLET = -0x1.ed0714p125F;
    static { NAMED.put("silver violet", -0x1.ed0714p125F); LIST.add(-0x1.ed0714p125F); }

    /**
     * This color constant "white violet" has RGBA8888 code {@code B2A2DBFF}, L 0.654902, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.2981953, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed094ep125F}.
     * <pre>
     * <font style='background-color: #B2A2DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2A2DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2A2DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2A2DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2A2DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2A2DB'>&nbsp;@&nbsp;</font><font style='background-color: #B2A2DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2A2DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2A2DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_VIOLET = -0x1.ed094ep125F;
    static { NAMED.put("white violet", -0x1.ed094ep125F); LIST.add(-0x1.ed094ep125F); }

    /**
     * This color constant "black purple" has RGBA8888 code {@code 442956FF}, L 0.23921569, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.24912319, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0e7ap125F}.
     * <pre>
     * <font style='background-color: #442956;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #442956; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #442956;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #442956'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #442956'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #442956'>&nbsp;@&nbsp;</font><font style='background-color: #442956; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #442956;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #442956; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_PURPLE = -0x1.ed0e7ap125F;
    static { NAMED.put("black purple", -0x1.ed0e7ap125F); LIST.add(-0x1.ed0e7ap125F); }

    /**
     * This color constant "lead purple" has RGBA8888 code {@code 634577FF}, L 0.3372549, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.14532828, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0eacp125F}.
     * <pre>
     * <font style='background-color: #634577;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #634577; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #634577;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #634577'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #634577'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #634577'>&nbsp;@&nbsp;</font><font style='background-color: #634577; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #634577;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #634577; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_PURPLE = -0x1.ed0eacp125F;
    static { NAMED.put("lead purple", -0x1.ed0eacp125F); LIST.add(-0x1.ed0eacp125F); }

    /**
     * This color constant "gray purple" has RGBA8888 code {@code 816097FF}, L 0.43529412, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.09756504, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0edep125F}.
     * <pre>
     * <font style='background-color: #816097;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #816097; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #816097;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #816097'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #816097'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #816097'>&nbsp;@&nbsp;</font><font style='background-color: #816097; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #816097;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #816097; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_PURPLE = -0x1.ed0edep125F;
    static { NAMED.put("gray purple", -0x1.ed0edep125F); LIST.add(-0x1.ed0edep125F); }

    /**
     * This color constant "silver purple" has RGBA8888 code {@code A380BBFF}, L 0.54901963, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.14532828, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0f18p125F}.
     * <pre>
     * <font style='background-color: #A380BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A380BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A380BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A380BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A380BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A380BB'>&nbsp;@&nbsp;</font><font style='background-color: #A380BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A380BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A380BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PURPLE = -0x1.ed0f18p125F;
    static { NAMED.put("silver purple", -0x1.ed0f18p125F); LIST.add(-0x1.ed0f18p125F); }

    /**
     * This color constant "white purple" has RGBA8888 code {@code C7A2E1FF}, L 0.6784314, A 0.5254902, B 0.4627451, alpha 1.0, hue 0.84551346, saturation 0.33968917, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.ed0d5ap125F}.
     * <pre>
     * <font style='background-color: #C7A2E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7A2E1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7A2E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7A2E1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7A2E1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7A2E1'>&nbsp;@&nbsp;</font><font style='background-color: #C7A2E1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7A2E1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7A2E1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_PURPLE = -0x1.ed0d5ap125F;
    static { NAMED.put("white purple", -0x1.ed0d5ap125F); LIST.add(-0x1.ed0d5ap125F); }

    /**
     * This color constant "black magenta" has RGBA8888 code {@code 533053FF}, L 0.26666668, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.1869197, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f31088p125F}.
     * <pre>
     * <font style='background-color: #533053;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #533053; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #533053;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #533053'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #533053'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #533053'>&nbsp;@&nbsp;</font><font style='background-color: #533053; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #533053;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #533053; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_MAGENTA = -0x1.f31088p125F;
    static { NAMED.put("black magenta", -0x1.f31088p125F); LIST.add(-0x1.f31088p125F); }

    /**
     * This color constant "lead magenta" has RGBA8888 code {@code 7D547CFF}, L 0.39607844, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.10052126, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f310cap125F}.
     * <pre>
     * <font style='background-color: #7D547C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D547C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D547C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7D547C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7D547C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7D547C'>&nbsp;@&nbsp;</font><font style='background-color: #7D547C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D547C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D547C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_MAGENTA = -0x1.f310cap125F;
    static { NAMED.put("lead magenta", -0x1.f310cap125F); LIST.add(-0x1.f310cap125F); }

    /**
     * This color constant "gray magenta" has RGBA8888 code {@code A0749FFF}, L 0.50980395, A 0.5294118, B 0.47843137, alpha 1.0, hue 0.899282, saturation 0.053393003, and chroma 0.07266045.
     * It can be represented as a packed float with the constant {@code -0x1.f50f04p125F}.
     * <pre>
     * <font style='background-color: #A0749F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0749F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0749F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A0749F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A0749F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A0749F'>&nbsp;@&nbsp;</font><font style='background-color: #A0749F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0749F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0749F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_MAGENTA = -0x1.f50f04p125F;
    static { NAMED.put("gray magenta", -0x1.f50f04p125F); LIST.add(-0x1.f50f04p125F); }

    /**
     * This color constant "silver magenta" has RGBA8888 code {@code BD8EBCFF}, L 0.60784316, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.08958873, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f31136p125F}.
     * <pre>
     * <font style='background-color: #BD8EBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD8EBC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD8EBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD8EBC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD8EBC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD8EBC'>&nbsp;@&nbsp;</font><font style='background-color: #BD8EBC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD8EBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD8EBC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_MAGENTA = -0x1.f31136p125F;
    static { NAMED.put("silver magenta", -0x1.f31136p125F); LIST.add(-0x1.f31136p125F); }

    /**
     * This color constant "white magenta" has RGBA8888 code {@code DBA9D9FF}, L 0.7137255, A 0.53333336, B 0.47843137, alpha 1.0, hue 0.90858525, saturation 0.16076855, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f5116cp125F}.
     * <pre>
     * <font style='background-color: #DBA9D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBA9D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBA9D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBA9D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBA9D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBA9D9'>&nbsp;@&nbsp;</font><font style='background-color: #DBA9D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBA9D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBA9D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_MAGENTA = -0x1.f5116cp125F;
    static { NAMED.put("white magenta", -0x1.f5116cp125F); LIST.add(-0x1.f5116cp125F); }

    /**
     * This color constant "drab red" has RGBA8888 code {@code 872720FF}, L 0.30980393, A 0.56078434, B 0.53333336, alpha 1.0, hue 0.079836555, saturation 0.5314061, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.111e9ep126F}.
     * <pre>
     * <font style='background-color: #872720;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #872720; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #872720;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #872720'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #872720'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #872720'>&nbsp;@&nbsp;</font><font style='background-color: #872720; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #872720;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #872720; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED = -0x1.111e9ep126F;
    static { NAMED.put("drab red", -0x1.111e9ep126F); LIST.add(-0x1.111e9ep126F); }

    /**
     * This color constant "faded red" has RGBA8888 code {@code AF463BFF}, L 0.41568628, A 0.56078434, B 0.53333336, alpha 1.0, hue 0.079836555, saturation 0.34150672, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.111ed4p126F}.
     * <pre>
     * <font style='background-color: #AF463B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF463B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF463B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF463B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF463B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF463B'>&nbsp;@&nbsp;</font><font style='background-color: #AF463B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF463B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF463B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_RED = -0x1.111ed4p126F;
    static { NAMED.put("faded red", -0x1.111ed4p126F); LIST.add(-0x1.111ed4p126F); }

    /**
     * This color constant "pale red" has RGBA8888 code {@code DD6A5CFF}, L 0.54509807, A 0.56078434, B 0.53333336, alpha 1.0, hue 0.079836555, saturation 0.3652568, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.111f16p126F}.
     * <pre>
     * <font style='background-color: #DD6A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD6A5C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD6A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD6A5C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD6A5C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD6A5C'>&nbsp;@&nbsp;</font><font style='background-color: #DD6A5C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD6A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD6A5C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED = -0x1.111f16p126F;
    static { NAMED.put("pale red", -0x1.111f16p126F); LIST.add(-0x1.111f16p126F); }

    /**
     * This color constant "drab brown" has RGBA8888 code {@code 785140FF}, L 0.3647059, A 0.5176471, B 0.52156866, alpha 1.0, hue 0.14085212, saturation 0.16028565, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.0b08bap126F}.
     * <pre>
     * <font style='background-color: #785140;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #785140; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #785140;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #785140'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #785140'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #785140'>&nbsp;@&nbsp;</font><font style='background-color: #785140; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #785140;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #785140; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN = -0x1.0b08bap126F;
    static { NAMED.put("drab brown", -0x1.0b08bap126F); LIST.add(-0x1.0b08bap126F); }

    /**
     * This color constant "faded brown" has RGBA8888 code {@code A27562FF}, L 0.49411765, A 0.52156866, B 0.52156866, alpha 1.0, hue 0.125, saturation 0.102880225, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0b0afcp126F}.
     * <pre>
     * <font style='background-color: #A27562;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A27562; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A27562;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A27562'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A27562'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A27562'>&nbsp;@&nbsp;</font><font style='background-color: #A27562; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A27562;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A27562; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BROWN = -0x1.0b0afcp126F;
    static { NAMED.put("faded brown", -0x1.0b0afcp126F); LIST.add(-0x1.0b0afcp126F); }

    /**
     * This color constant "pale brown" has RGBA8888 code {@code C4947FFF}, L 0.60784316, A 0.5176471, B 0.52156866, alpha 1.0, hue 0.14085212, saturation 0.07920792, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.0b0936p126F}.
     * <pre>
     * <font style='background-color: #C4947F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4947F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4947F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4947F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4947F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4947F'>&nbsp;@&nbsp;</font><font style='background-color: #C4947F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4947F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4947F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN = -0x1.0b0936p126F;
    static { NAMED.put("pale brown", -0x1.0b0936p126F); LIST.add(-0x1.0b0936p126F); }

    /**
     * This color constant "drab orange" has RGBA8888 code {@code 86502CFF}, L 0.3764706, A 0.5254902, B 0.5372549, alpha 1.0, hue 0.1544865, saturation 0.42055148, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.130ccp126F}.
     * <pre>
     * <font style='background-color: #86502C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86502C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86502C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #86502C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #86502C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #86502C'>&nbsp;@&nbsp;</font><font style='background-color: #86502C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86502C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86502C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE = -0x1.130ccp126F;
    static { NAMED.put("drab orange", -0x1.130ccp126F); LIST.add(-0x1.130ccp126F); }

    /**
     * This color constant "faded orange" has RGBA8888 code {@code AD724BFF}, L 0.49411765, A 0.5254902, B 0.5372549, alpha 1.0, hue 0.1544865, saturation 0.29342562, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.130cfcp126F}.
     * <pre>
     * <font style='background-color: #AD724B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD724B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD724B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD724B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD724B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD724B'>&nbsp;@&nbsp;</font><font style='background-color: #AD724B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD724B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD724B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_ORANGE = -0x1.130cfcp126F;
    static { NAMED.put("faded orange", -0x1.130cfcp126F); LIST.add(-0x1.130cfcp126F); }

    /**
     * This color constant "pale orange" has RGBA8888 code {@code D8986DFF}, L 0.63529414, A 0.52156866, B 0.5372549, alpha 1.0, hue 0.16646945, saturation 0.20491019, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.130b44p126F}.
     * <pre>
     * <font style='background-color: #D8986D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8986D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8986D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D8986D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D8986D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D8986D'>&nbsp;@&nbsp;</font><font style='background-color: #D8986D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8986D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8986D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE = -0x1.130b44p126F;
    static { NAMED.put("pale orange", -0x1.130b44p126F); LIST.add(-0x1.130b44p126F); }

    /**
     * This color constant "drab saffron" has RGBA8888 code {@code 8B6835FF}, L 0.43137255, A 0.5058824, B 0.5411765, alpha 1.0, hue 0.22740345, saturation 0.4260355, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.1502dcp126F}.
     * <pre>
     * <font style='background-color: #8B6835;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B6835; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B6835;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B6835'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B6835'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B6835'>&nbsp;@&nbsp;</font><font style='background-color: #8B6835; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B6835;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B6835; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON = -0x1.1502dcp126F;
    static { NAMED.put("drab saffron", -0x1.1502dcp126F); LIST.add(-0x1.1502dcp126F); }

    /**
     * This color constant "faded saffron" has RGBA8888 code {@code BA925BFF}, L 0.58431375, A 0.50980395, B 0.5411765, alpha 1.0, hue 0.21279909, saturation 0.28410304, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.15052ap126F}.
     * <pre>
     * <font style='background-color: #BA925B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA925B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA925B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA925B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA925B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA925B'>&nbsp;@&nbsp;</font><font style='background-color: #BA925B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA925B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA925B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_SAFFRON = -0x1.15052ap126F;
    static { NAMED.put("faded saffron", -0x1.15052ap126F); LIST.add(-0x1.15052ap126F); }

    /**
     * This color constant "pale saffron" has RGBA8888 code {@code DDB379FF}, L 0.70980394, A 0.50980395, B 0.5411765, alpha 1.0, hue 0.21279909, saturation 0.21551624, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.15056ap126F}.
     * <pre>
     * <font style='background-color: #DDB379;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDB379; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDB379;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDB379'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDB379'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDB379'>&nbsp;@&nbsp;</font><font style='background-color: #DDB379; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDB379;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDB379; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON = -0x1.15056ap126F;
    static { NAMED.put("pale saffron", -0x1.15056ap126F); LIST.add(-0x1.15056ap126F); }

    /**
     * This color constant "drab yellow" has RGBA8888 code {@code 81862FFF}, L 0.49019608, A 0.4745098, B 0.5529412, alpha 1.0, hue 0.3214129, saturation 0.54747754, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1af2fap126F}.
     * <pre>
     * <font style='background-color: #81862F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81862F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81862F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #81862F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #81862F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #81862F'>&nbsp;@&nbsp;</font><font style='background-color: #81862F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81862F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81862F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW = -0x1.1af2fap126F;
    static { NAMED.put("drab yellow", -0x1.1af2fap126F); LIST.add(-0x1.1af2fap126F); }

    /**
     * This color constant "faded yellow" has RGBA8888 code {@code AAB055FF}, L 0.6392157, A 0.47843137, B 0.5529412, alpha 1.0, hue 0.3115622, saturation 0.39310902, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1af546p126F}.
     * <pre>
     * <font style='background-color: #AAB055;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAB055; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAB055;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAB055'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAB055'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAB055'>&nbsp;@&nbsp;</font><font style='background-color: #AAB055; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAB055;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAB055; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_YELLOW = -0x1.1af546p126F;
    static { NAMED.put("faded yellow", -0x1.1af546p126F); LIST.add(-0x1.1af546p126F); }

    /**
     * This color constant "pale yellow" has RGBA8888 code {@code D1D878FF}, L 0.7921569, A 0.47843137, B 0.5529412, alpha 1.0, hue 0.3115622, saturation 0.30839002, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1af594p126F}.
     * <pre>
     * <font style='background-color: #D1D878;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1D878; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1D878;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1D878'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1D878'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1D878'>&nbsp;@&nbsp;</font><font style='background-color: #D1D878; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1D878;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1D878; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.1af594p126F;
    static { NAMED.put("pale yellow", -0x1.1af594p126F); LIST.add(-0x1.1af594p126F); }

    /**
     * This color constant "drab lime" has RGBA8888 code {@code 5C7F32FF}, L 0.44313726, A 0.4627451, B 0.54509807, alpha 1.0, hue 0.35989827, saturation 0.4494382, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.16ece2p126F}.
     * <pre>
     * <font style='background-color: #5C7F32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C7F32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C7F32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C7F32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C7F32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C7F32'>&nbsp;@&nbsp;</font><font style='background-color: #5C7F32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C7F32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C7F32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME = -0x1.16ece2p126F;
    static { NAMED.put("drab lime", -0x1.16ece2p126F); LIST.add(-0x1.16ece2p126F); }

    /**
     * This color constant "faded lime" has RGBA8888 code {@code 80A855FF}, L 0.5803922, A 0.45882353, B 0.54509807, alpha 1.0, hue 0.36777616, saturation 0.3038609, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.16eb28p126F}.
     * <pre>
     * <font style='background-color: #80A855;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80A855; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80A855;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #80A855'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #80A855'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #80A855'>&nbsp;@&nbsp;</font><font style='background-color: #80A855; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80A855;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80A855; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_LIME = -0x1.16eb28p126F;
    static { NAMED.put("faded lime", -0x1.16eb28p126F); LIST.add(-0x1.16eb28p126F); }

    /**
     * This color constant "pale lime" has RGBA8888 code {@code AAD67DFF}, L 0.7490196, A 0.45882353, B 0.54509807, alpha 1.0, hue 0.36777616, saturation 0.22609405, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.16eb7ep126F}.
     * <pre>
     * <font style='background-color: #AAD67D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAD67D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAD67D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAD67D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAD67D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAD67D'>&nbsp;@&nbsp;</font><font style='background-color: #AAD67D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAD67D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAD67D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME = -0x1.16eb7ep126F;
    static { NAMED.put("pale lime", -0x1.16eb7ep126F); LIST.add(-0x1.16eb7ep126F); }

    /**
     * This color constant "drab green" has RGBA8888 code {@code 369040FF}, L 0.46666667, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.40979147, saturation 0.65044105, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14deeep126F}.
     * <pre>
     * <font style='background-color: #369040;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #369040; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #369040;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #369040'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #369040'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #369040'>&nbsp;@&nbsp;</font><font style='background-color: #369040; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #369040;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #369040; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.14deeep126F;
    static { NAMED.put("drab green", -0x1.14deeep126F); LIST.add(-0x1.14deeep126F); }

    /**
     * This color constant "faded green" has RGBA8888 code {@code 5CBA64FF}, L 0.60784316, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.40979147, saturation 0.4470743, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df36p126F}.
     * <pre>
     * <font style='background-color: #5CBA64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CBA64; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CBA64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5CBA64'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5CBA64'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5CBA64'>&nbsp;@&nbsp;</font><font style='background-color: #5CBA64; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CBA64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CBA64; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_GREEN = -0x1.14df36p126F;
    static { NAMED.put("faded green", -0x1.14df36p126F); LIST.add(-0x1.14df36p126F); }

    /**
     * This color constant "pale green" has RGBA8888 code {@code 7ADD82FF}, L 0.73333335, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.40979147, saturation 0.34597772, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df76p126F}.
     * <pre>
     * <font style='background-color: #7ADD82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7ADD82; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7ADD82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7ADD82'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7ADD82'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7ADD82'>&nbsp;@&nbsp;</font><font style='background-color: #7ADD82; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7ADD82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7ADD82; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.14df76p126F;
    static { NAMED.put("pale green", -0x1.14df76p126F); LIST.add(-0x1.14df76p126F); }

    /**
     * This color constant "drab cyan" has RGBA8888 code {@code 3B8787FF}, L 0.46666667, A 0.45882353, B 0.4862745, alpha 1.0, hue 0.5511957, saturation 0.5630566, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.f8eaeep125F}.
     * <pre>
     * <font style='background-color: #3B8787;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B8787; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B8787;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B8787'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B8787'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B8787'>&nbsp;@&nbsp;</font><font style='background-color: #3B8787; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B8787;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B8787; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN = -0x1.f8eaeep125F;
    static { NAMED.put("drab cyan", -0x1.f8eaeep125F); LIST.add(-0x1.f8eaeep125F); }

    /**
     * This color constant "faded cyan" has RGBA8888 code {@code 5FAEADFF}, L 0.6, A 0.45882353, B 0.49019608, alpha 1.0, hue 0.5372009, saturation 0.3915144, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.faeb32p125F}.
     * <pre>
     * <font style='background-color: #5FAEAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5FAEAD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5FAEAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5FAEAD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5FAEAD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5FAEAD'>&nbsp;@&nbsp;</font><font style='background-color: #5FAEAD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5FAEAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5FAEAD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_CYAN = -0x1.faeb32p125F;
    static { NAMED.put("faded cyan", -0x1.faeb32p125F); LIST.add(-0x1.faeb32p125F); }

    /**
     * This color constant "pale cyan" has RGBA8888 code {@code 83D7D5FF}, L 0.7490196, A 0.45882353, B 0.49019608, alpha 1.0, hue 0.5372009, saturation 0.29867008, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.faeb7ep125F}.
     * <pre>
     * <font style='background-color: #83D7D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83D7D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83D7D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #83D7D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #83D7D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #83D7D5'>&nbsp;@&nbsp;</font><font style='background-color: #83D7D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #83D7D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #83D7D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN = -0x1.faeb7ep125F;
    static { NAMED.put("pale cyan", -0x1.faeb7ep125F); LIST.add(-0x1.faeb7ep125F); }

    /**
     * This color constant "drab blue" has RGBA8888 code {@code 123490FF}, L 0.2627451, A 0.4862745, B 0.41568628, alpha 1.0, hue 0.72430414, saturation 0.38187215, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.d4f886p125F}.
     * <pre>
     * <font style='background-color: #123490;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #123490; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #123490;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #123490'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #123490'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #123490'>&nbsp;@&nbsp;</font><font style='background-color: #123490; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #123490;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #123490; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE = -0x1.d4f886p125F;
    static { NAMED.put("drab blue", -0x1.d4f886p125F); LIST.add(-0x1.d4f886p125F); }

    /**
     * This color constant "faded blue" has RGBA8888 code {@code 2F59BFFF}, L 0.38039216, A 0.49019608, B 0.41568628, alpha 1.0, hue 0.7315659, saturation 0.35652795, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.d4fac2p125F}.
     * <pre>
     * <font style='background-color: #2F59BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F59BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F59BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F59BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F59BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F59BF'>&nbsp;@&nbsp;</font><font style='background-color: #2F59BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F59BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F59BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.d4fac2p125F;
    static { NAMED.put("faded blue", -0x1.d4fac2p125F); LIST.add(-0x1.d4fac2p125F); }

    /**
     * This color constant "pale blue" has RGBA8888 code {@code 4877E3FF}, L 0.47843137, A 0.49019608, B 0.41568628, alpha 1.0, hue 0.7315659, saturation 0.58704674, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.d4faf4p125F}.
     * <pre>
     * <font style='background-color: #4877E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4877E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4877E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4877E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4877E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4877E3'>&nbsp;@&nbsp;</font><font style='background-color: #4877E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4877E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4877E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.d4faf4p125F;
    static { NAMED.put("pale blue", -0x1.d4faf4p125F); LIST.add(-0x1.d4faf4p125F); }

    /**
     * This color constant "drab violet" has RGBA8888 code {@code 583389FF}, L 0.30588236, A 0.5372549, B 0.43529412, alpha 1.0, hue 0.8331495, saturation 0.38336968, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.df129cp125F}.
     * <pre>
     * <font style='background-color: #583389;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #583389; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #583389;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #583389'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #583389'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #583389'>&nbsp;@&nbsp;</font><font style='background-color: #583389; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #583389;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #583389; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET = -0x1.df129cp125F;
    static { NAMED.put("drab violet", -0x1.df129cp125F); LIST.add(-0x1.df129cp125F); }

    /**
     * This color constant "faded violet" has RGBA8888 code {@code 7B54B3FF}, L 0.41960785, A 0.5372549, B 0.43529412, alpha 1.0, hue 0.8331495, saturation 0.23530367, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.df12d6p125F}.
     * <pre>
     * <font style='background-color: #7B54B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B54B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B54B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B54B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B54B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B54B3'>&nbsp;@&nbsp;</font><font style='background-color: #7B54B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B54B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B54B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_VIOLET = -0x1.df12d6p125F;
    static { NAMED.put("faded violet", -0x1.df12d6p125F); LIST.add(-0x1.df12d6p125F); }

    /**
     * This color constant "pale violet" has RGBA8888 code {@code 9B73D8FF}, L 0.5294118, A 0.5372549, B 0.43529412, alpha 1.0, hue 0.8331495, saturation 0.40957558, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.df130ep125F}.
     * <pre>
     * <font style='background-color: #9B73D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B73D8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B73D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B73D8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B73D8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B73D8'>&nbsp;@&nbsp;</font><font style='background-color: #9B73D8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B73D8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B73D8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.df130ep125F;
    static { NAMED.put("pale violet", -0x1.df130ep125F); LIST.add(-0x1.df130ep125F); }

    /**
     * This color constant "drab purple" has RGBA8888 code {@code 6E2D8AFF}, L 0.32156864, A 0.5568628, B 0.4392157, alpha 1.0, hue 0.8697009, saturation 0.4923161, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.e11ca4p125F}.
     * <pre>
     * <font style='background-color: #6E2D8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E2D8A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E2D8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E2D8A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E2D8A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E2D8A'>&nbsp;@&nbsp;</font><font style='background-color: #6E2D8A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E2D8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E2D8A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE = -0x1.e11ca4p125F;
    static { NAMED.put("drab purple", -0x1.e11ca4p125F); LIST.add(-0x1.e11ca4p125F); }

    /**
     * This color constant "faded purple" has RGBA8888 code {@code 924DB1FF}, L 0.43137255, A 0.5568628, B 0.4392157, alpha 1.0, hue 0.8697009, saturation 0.3161265, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.e11cdcp125F}.
     * <pre>
     * <font style='background-color: #924DB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #924DB1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #924DB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #924DB1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #924DB1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #924DB1'>&nbsp;@&nbsp;</font><font style='background-color: #924DB1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #924DB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #924DB1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_PURPLE = -0x1.e11cdcp125F;
    static { NAMED.put("faded purple", -0x1.e11cdcp125F); LIST.add(-0x1.e11cdcp125F); }

    /**
     * This color constant "pale purple" has RGBA8888 code {@code BA70DCFF}, L 0.56078434, A 0.5568628, B 0.4392157, alpha 1.0, hue 0.8697009, saturation 0.4074849, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.e11d1ep125F}.
     * <pre>
     * <font style='background-color: #BA70DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA70DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA70DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA70DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA70DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA70DC'>&nbsp;@&nbsp;</font><font style='background-color: #BA70DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA70DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA70DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.e11d1ep125F;
    static { NAMED.put("pale purple", -0x1.e11d1ep125F); LIST.add(-0x1.e11d1ep125F); }

    /**
     * This color constant "drab magenta" has RGBA8888 code {@code 8A398AFF}, L 0.37254903, A 0.5647059, B 0.45490196, alpha 1.0, hue 0.90311134, saturation 0.40126482, and chroma 0.15712644.
     * It can be represented as a packed float with the constant {@code -0x1.e920bep125F}.
     * <pre>
     * <font style='background-color: #8A398A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A398A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A398A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A398A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A398A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A398A'>&nbsp;@&nbsp;</font><font style='background-color: #8A398A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A398A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A398A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA = -0x1.e920bep125F;
    static { NAMED.put("drab magenta", -0x1.e920bep125F); LIST.add(-0x1.e920bep125F); }

    /**
     * This color constant "faded magenta" has RGBA8888 code {@code B65EB5FF}, L 0.5058824, A 0.5647059, B 0.45490196, alpha 1.0, hue 0.90311134, saturation 0.25600255, and chroma 0.15712644.
     * It can be represented as a packed float with the constant {@code -0x1.e92102p125F}.
     * <pre>
     * <font style='background-color: #B65EB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B65EB5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B65EB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B65EB5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B65EB5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B65EB5'>&nbsp;@&nbsp;</font><font style='background-color: #B65EB5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B65EB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B65EB5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_MAGENTA = -0x1.e92102p125F;
    static { NAMED.put("faded magenta", -0x1.e92102p125F); LIST.add(-0x1.e92102p125F); }

    /**
     * This color constant "pale magenta" has RGBA8888 code {@code D97BD7FF}, L 0.6156863, A 0.5647059, B 0.45490196, alpha 1.0, hue 0.90311134, saturation 0.2915184, and chroma 0.15712644.
     * It can be represented as a packed float with the constant {@code -0x1.e9213ap125F}.
     * <pre>
     * <font style='background-color: #D97BD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D97BD7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D97BD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D97BD7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D97BD7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D97BD7'>&nbsp;@&nbsp;</font><font style='background-color: #D97BD7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D97BD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D97BD7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA = -0x1.e9213ap125F;
    static { NAMED.put("pale magenta", -0x1.e9213ap125F); LIST.add(-0x1.e9213ap125F); }

    /**
     * This color constant "deep pure red" has RGBA8888 code {@code BF1C16FF}, L 0.3882353, A 0.5882353, B 0.54901963, alpha 1.0, hue 0.080711745, saturation 0.8015123, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.192cc6p126F}.
     * <pre>
     * <font style='background-color: #BF1C16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF1C16; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF1C16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF1C16'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF1C16'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF1C16'>&nbsp;@&nbsp;</font><font style='background-color: #BF1C16; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF1C16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF1C16; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_RED = -0x1.192cc6p126F;
    static { NAMED.put("deep pure red", -0x1.192cc6p126F); LIST.add(-0x1.192cc6p126F); }

    /**
     * This color constant "true pure red" has RGBA8888 code {@code D42F25FF}, L 0.4392157, A 0.5882353, B 0.54901963, alpha 1.0, hue 0.080711745, saturation 0.6572013, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.192cep126F}.
     * <pre>
     * <font style='background-color: #D42F25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D42F25; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D42F25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D42F25'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D42F25'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D42F25'>&nbsp;@&nbsp;</font><font style='background-color: #D42F25; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D42F25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D42F25; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_RED = -0x1.192cep126F;
    static { NAMED.put("true pure red", -0x1.192cep126F); LIST.add(-0x1.192cep126F); }

    /**
     * This color constant "bright pure red" has RGBA8888 code {@code ED4437FF}, L 0.5058824, A 0.5882353, B 0.54901963, alpha 1.0, hue 0.080711745, saturation 0.59924245, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.192d02p126F}.
     * <pre>
     * <font style='background-color: #ED4437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED4437; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED4437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ED4437'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ED4437'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ED4437'>&nbsp;@&nbsp;</font><font style='background-color: #ED4437; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED4437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED4437; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_RED = -0x1.192d02p126F;
    static { NAMED.put("bright pure red", -0x1.192d02p126F); LIST.add(-0x1.192d02p126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code AF4936FF}, L 0.41960785, A 0.5568628, B 0.5372549, alpha 1.0, hue 0.09232193, saturation 0.39022806, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131cd6p126F}.
     * <pre>
     * <font style='background-color: #AF4936;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF4936; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF4936;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF4936'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF4936'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF4936'>&nbsp;@&nbsp;</font><font style='background-color: #AF4936; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF4936;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF4936; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.131cd6p126F;
    static { NAMED.put("deep brown red", -0x1.131cd6p126F); LIST.add(-0x1.131cd6p126F); }

    /**
     * This color constant "true brown red" has RGBA8888 code {@code C45A45FF}, L 0.47843137, A 0.5568628, B 0.5372549, alpha 1.0, hue 0.09232193, saturation 0.32839286, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131cf4p126F}.
     * <pre>
     * <font style='background-color: #C45A45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C45A45; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C45A45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C45A45'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C45A45'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C45A45'>&nbsp;@&nbsp;</font><font style='background-color: #C45A45; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C45A45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C45A45; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_RED = -0x1.131cf4p126F;
    static { NAMED.put("true brown red", -0x1.131cf4p126F); LIST.add(-0x1.131cf4p126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code D66852FF}, L 0.5294118, A 0.5568628, B 0.5372549, alpha 1.0, hue 0.09232193, saturation 0.31780025, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.131d0ep126F}.
     * <pre>
     * <font style='background-color: #D66852;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D66852; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D66852;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D66852'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D66852'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D66852'>&nbsp;@&nbsp;</font><font style='background-color: #D66852; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D66852;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D66852; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.131d0ep126F;
    static { NAMED.put("bright brown red", -0x1.131d0ep126F); LIST.add(-0x1.131d0ep126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code A85540FF}, L 0.43137255, A 0.54509807, B 0.53333336, alpha 1.0, hue 0.101316266, saturation 0.29678005, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.1116dcp126F}.
     * <pre>
     * <font style='background-color: #A85540;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A85540; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A85540;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A85540'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A85540'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A85540'>&nbsp;@&nbsp;</font><font style='background-color: #A85540; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A85540;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A85540; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.1116dcp126F;
    static { NAMED.put("deep red brown", -0x1.1116dcp126F); LIST.add(-0x1.1116dcp126F); }

    /**
     * This color constant "true red brown" has RGBA8888 code {@code BB644EFF}, L 0.4862745, A 0.54509807, B 0.53333336, alpha 1.0, hue 0.101316266, saturation 0.24741021, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.1116f8p126F}.
     * <pre>
     * <font style='background-color: #BB644E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB644E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB644E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB644E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB644E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB644E'>&nbsp;@&nbsp;</font><font style='background-color: #BB644E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB644E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB644E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_BROWN = -0x1.1116f8p126F;
    static { NAMED.put("true red brown", -0x1.1116f8p126F); LIST.add(-0x1.1116f8p126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code D1765FFF}, L 0.5529412, A 0.54509807, B 0.5294118, alpha 1.0, hue 0.09198746, saturation 0.22805293, and chroma 0.107261956.
     * It can be represented as a packed float with the constant {@code -0x1.0f171ap126F}.
     * <pre>
     * <font style='background-color: #D1765F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1765F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1765F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1765F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1765F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1765F'>&nbsp;@&nbsp;</font><font style='background-color: #D1765F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1765F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1765F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.0f171ap126F;
    static { NAMED.put("bright red brown", -0x1.0f171ap126F); LIST.add(-0x1.0f171ap126F); }

    /**
     * This color constant "deep pure brown" has RGBA8888 code {@code A4634CFF}, L 0.45490196, A 0.53333336, B 0.5294118, alpha 1.0, hue 0.115073085, saturation 0.21851419, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.0f10e8p126F}.
     * <pre>
     * <font style='background-color: #A4634C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4634C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4634C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4634C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4634C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4634C'>&nbsp;@&nbsp;</font><font style='background-color: #A4634C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4634C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4634C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BROWN = -0x1.0f10e8p126F;
    static { NAMED.put("deep pure brown", -0x1.0f10e8p126F); LIST.add(-0x1.0f10e8p126F); }

    /**
     * This color constant "true pure brown" has RGBA8888 code {@code BA755DFF}, L 0.52156866, A 0.53333336, B 0.5294118, alpha 1.0, hue 0.115073085, saturation 0.179579, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.0f110ap126F}.
     * <pre>
     * <font style='background-color: #BA755D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA755D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA755D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA755D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA755D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA755D'>&nbsp;@&nbsp;</font><font style='background-color: #BA755D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA755D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA755D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BROWN = -0x1.0f110ap126F;
    static { NAMED.put("true pure brown", -0x1.0f110ap126F); LIST.add(-0x1.0f110ap126F); }

    /**
     * This color constant "bright pure brown" has RGBA8888 code {@code CC856CFF}, L 0.5803922, A 0.53333336, B 0.5294118, alpha 1.0, hue 0.115073085, saturation 0.179579, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.0f1128p126F}.
     * <pre>
     * <font style='background-color: #CC856C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC856C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC856C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CC856C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CC856C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CC856C'>&nbsp;@&nbsp;</font><font style='background-color: #CC856C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CC856C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CC856C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BROWN = -0x1.0f1128p126F;
    static { NAMED.put("bright pure brown", -0x1.0f1128p126F); LIST.add(-0x1.0f1128p126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code AA6442FF}, L 0.4627451, A 0.53333336, B 0.5372549, alpha 1.0, hue 0.13382626, saturation 0.32824138, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.1310ecp126F}.
     * <pre>
     * <font style='background-color: #AA6442;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA6442; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA6442;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA6442'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA6442'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA6442'>&nbsp;@&nbsp;</font><font style='background-color: #AA6442; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA6442;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA6442; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.1310ecp126F;
    static { NAMED.put("deep orange brown", -0x1.1310ecp126F); LIST.add(-0x1.1310ecp126F); }

    /**
     * This color constant "true orange brown" has RGBA8888 code {@code BE7652FF}, L 0.5254902, A 0.5294118, B 0.5372549, alpha 1.0, hue 0.14362669, saturation 0.271014, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130f0cp126F}.
     * <pre>
     * <font style='background-color: #BE7652;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE7652; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE7652;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE7652'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE7652'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE7652'>&nbsp;@&nbsp;</font><font style='background-color: #BE7652; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE7652;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE7652; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_BROWN = -0x1.130f0cp126F;
    static { NAMED.put("true orange brown", -0x1.130f0cp126F); LIST.add(-0x1.130f0cp126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code D28762FF}, L 0.5882353, A 0.5294118, B 0.5372549, alpha 1.0, hue 0.14362669, saturation 0.22978139, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130f2cp126F}.
     * <pre>
     * <font style='background-color: #D28762;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28762; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28762;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D28762'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D28762'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D28762'>&nbsp;@&nbsp;</font><font style='background-color: #D28762; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28762;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28762; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.130f2cp126F;
    static { NAMED.put("bright orange brown", -0x1.130f2cp126F); LIST.add(-0x1.130f2cp126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code AE663EFF}, L 0.47058824, A 0.53333336, B 0.5411765, alpha 1.0, hue 0.14168067, saturation 0.38578412, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.1510fp126F}.
     * <pre>
     * <font style='background-color: #AE663E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE663E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE663E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE663E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE663E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE663E'>&nbsp;@&nbsp;</font><font style='background-color: #AE663E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE663E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE663E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.1510fp126F;
    static { NAMED.put("deep brown orange", -0x1.1510fp126F); LIST.add(-0x1.1510fp126F); }

    /**
     * This color constant "true brown orange" has RGBA8888 code {@code C1764DFF}, L 0.5294118, A 0.53333336, B 0.5372549, alpha 1.0, hue 0.13382626, saturation 0.2763312, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.13110ep126F}.
     * <pre>
     * <font style='background-color: #C1764D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1764D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1764D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1764D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1764D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1764D'>&nbsp;@&nbsp;</font><font style='background-color: #C1764D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1764D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1764D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_ORANGE = -0x1.13110ep126F;
    static { NAMED.put("true brown orange", -0x1.13110ep126F); LIST.add(-0x1.13110ep126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code D98A5FFF}, L 0.6039216, A 0.53333336, B 0.5411765, alpha 1.0, hue 0.14168067, saturation 0.275238, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.151134p126F}.
     * <pre>
     * <font style='background-color: #D98A5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D98A5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D98A5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D98A5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D98A5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D98A5F'>&nbsp;@&nbsp;</font><font style='background-color: #D98A5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D98A5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D98A5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.151134p126F;
    static { NAMED.put("bright brown orange", -0x1.151134p126F); LIST.add(-0x1.151134p126F); }

    /**
     * This color constant "deep pure orange" has RGBA8888 code {@code BE6622FF}, L 0.4862745, A 0.5372549, B 0.5568628, alpha 1.0, hue 0.15767807, saturation 0.69792426, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.1d12f8p126F}.
     * <pre>
     * <font style='background-color: #BE6622;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE6622; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE6622;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE6622'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE6622'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE6622'>&nbsp;@&nbsp;</font><font style='background-color: #BE6622; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE6622;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE6622; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_ORANGE = -0x1.1d12f8p126F;
    static { NAMED.put("deep pure orange", -0x1.1d12f8p126F); LIST.add(-0x1.1d12f8p126F); }

    /**
     * This color constant "true pure orange" has RGBA8888 code {@code D67A37FF}, L 0.56078434, A 0.5372549, B 0.5568628, alpha 1.0, hue 0.15767807, saturation 0.5806062, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.1d131ep126F}.
     * <pre>
     * <font style='background-color: #D67A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D67A37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D67A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D67A37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D67A37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D67A37'>&nbsp;@&nbsp;</font><font style='background-color: #D67A37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D67A37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D67A37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_ORANGE = -0x1.1d131ep126F;
    static { NAMED.put("true pure orange", -0x1.1d131ep126F); LIST.add(-0x1.1d131ep126F); }

    /**
     * This color constant "bright pure orange" has RGBA8888 code {@code EB8C48FF}, L 0.627451, A 0.5372549, B 0.5568628, alpha 1.0, hue 0.15767807, saturation 0.4905622, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.1d134p126F}.
     * <pre>
     * <font style='background-color: #EB8C48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB8C48; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB8C48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EB8C48'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EB8C48'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EB8C48'>&nbsp;@&nbsp;</font><font style='background-color: #EB8C48; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB8C48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB8C48; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_ORANGE = -0x1.1d134p126F;
    static { NAMED.put("bright pure orange", -0x1.1d134p126F); LIST.add(-0x1.1d134p126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code BD7028FF}, L 0.5058824, A 0.5294118, B 0.5568628, alpha 1.0, hue 0.17402768, saturation 0.6499009, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.1d0f02p126F}.
     * <pre>
     * <font style='background-color: #BD7028;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD7028; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD7028;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD7028'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD7028'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD7028'>&nbsp;@&nbsp;</font><font style='background-color: #BD7028; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD7028;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD7028; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.1d0f02p126F;
    static { NAMED.put("deep saffron orange", -0x1.1d0f02p126F); LIST.add(-0x1.1d0f02p126F); }

    /**
     * This color constant "true saffron orange" has RGBA8888 code {@code D2823AFF}, L 0.57254905, A 0.5294118, B 0.5568628, alpha 1.0, hue 0.17402768, saturation 0.5633505, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.1d0f24p126F}.
     * <pre>
     * <font style='background-color: #D2823A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2823A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2823A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2823A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2823A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2823A'>&nbsp;@&nbsp;</font><font style='background-color: #D2823A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2823A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2823A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_ORANGE = -0x1.1d0f24p126F;
    static { NAMED.put("true saffron orange", -0x1.1d0f24p126F); LIST.add(-0x1.1d0f24p126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code E9964DFF}, L 0.64705884, A 0.5294118, B 0.5568628, alpha 1.0, hue 0.17402768, saturation 0.47246537, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.1d0f4ap126F}.
     * <pre>
     * <font style='background-color: #E9964D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9964D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9964D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9964D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9964D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9964D'>&nbsp;@&nbsp;</font><font style='background-color: #E9964D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9964D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9964D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.1d0f4ap126F;
    static { NAMED.put("bright saffron orange", -0x1.1d0f4ap126F); LIST.add(-0x1.1d0f4ap126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code C17A18FF}, L 0.5294118, A 0.52156866, B 0.5647059, alpha 1.0, hue 0.19880433, saturation 0.77551675, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.210b0ep126F}.
     * <pre>
     * <font style='background-color: #C17A18;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C17A18; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C17A18;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C17A18'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C17A18'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C17A18'>&nbsp;@&nbsp;</font><font style='background-color: #C17A18; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C17A18;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C17A18; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.210b0ep126F;
    static { NAMED.put("deep orange saffron", -0x1.210b0ep126F); LIST.add(-0x1.210b0ep126F); }

    /**
     * This color constant "true orange saffron" has RGBA8888 code {@code DA9032FF}, L 0.6117647, A 0.52156866, B 0.5647059, alpha 1.0, hue 0.19880433, saturation 0.6394504, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.210b38p126F}.
     * <pre>
     * <font style='background-color: #DA9032;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA9032; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA9032;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA9032'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA9032'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA9032'>&nbsp;@&nbsp;</font><font style='background-color: #DA9032; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA9032;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA9032; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_SAFFRON = -0x1.210b38p126F;
    static { NAMED.put("true orange saffron", -0x1.210b38p126F); LIST.add(-0x1.210b38p126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code EFA344FF}, L 0.68235296, A 0.5176471, B 0.5647059, alpha 1.0, hue 0.20763123, saturation 0.5651491, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.21095cp126F}.
     * <pre>
     * <font style='background-color: #EFA344;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFA344; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFA344;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFA344'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFA344'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFA344'>&nbsp;@&nbsp;</font><font style='background-color: #EFA344; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFA344;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFA344; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.21095cp126F;
    static { NAMED.put("bright orange saffron", -0x1.21095cp126F); LIST.add(-0x1.21095cp126F); }

    /**
     * This color constant "deep pure saffron" has RGBA8888 code {@code BD842BFF}, L 0.54901963, A 0.5137255, B 0.56078434, alpha 1.0, hue 0.21465261, saturation 0.64733213, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.1f0718p126F}.
     * <pre>
     * <font style='background-color: #BD842B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD842B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD842B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD842B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD842B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD842B'>&nbsp;@&nbsp;</font><font style='background-color: #BD842B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD842B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD842B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_SAFFRON = -0x1.1f0718p126F;
    static { NAMED.put("deep pure saffron", -0x1.1f0718p126F); LIST.add(-0x1.1f0718p126F); }

    /**
     * This color constant "true pure saffron" has RGBA8888 code {@code D3993FFF}, L 0.62352943, A 0.50980395, B 0.56078434, alpha 1.0, hue 0.22453669, saturation 0.54588234, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.1f053ep126F}.
     * <pre>
     * <font style='background-color: #D3993F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3993F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3993F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3993F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3993F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3993F'>&nbsp;@&nbsp;</font><font style='background-color: #D3993F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3993F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3993F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_SAFFRON = -0x1.1f053ep126F;
    static { NAMED.put("true pure saffron", -0x1.1f053ep126F); LIST.add(-0x1.1f053ep126F); }

    /**
     * This color constant "bright pure saffron" has RGBA8888 code {@code E9AC51FF}, L 0.69803923, A 0.5137255, B 0.56078434, alpha 1.0, hue 0.21465261, saturation 0.48786378, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.1f0764p126F}.
     * <pre>
     * <font style='background-color: #E9AC51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9AC51; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9AC51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9AC51'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9AC51'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9AC51'>&nbsp;@&nbsp;</font><font style='background-color: #E9AC51; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9AC51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9AC51; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_SAFFRON = -0x1.1f0764p126F;
    static { NAMED.put("bright pure saffron", -0x1.1f0764p126F); LIST.add(-0x1.1f0764p126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code C39723FF}, L 0.59607846, A 0.49803922, B 0.5686275, alpha 1.0, hue 0.2545336, saturation 0.747447, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.22ff3p126F}.
     * <pre>
     * <font style='background-color: #C39723;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C39723; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C39723;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C39723'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C39723'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C39723'>&nbsp;@&nbsp;</font><font style='background-color: #C39723; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C39723;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C39723; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.22ff3p126F;
    static { NAMED.put("deep yellow saffron", -0x1.22ff3p126F); LIST.add(-0x1.22ff3p126F); }

    /**
     * This color constant "true yellow saffron" has RGBA8888 code {@code D6A836FF}, L 0.6627451, A 0.5019608, B 0.5686275, alpha 1.0, hue 0.24546641, saturation 0.64790595, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.230152p126F}.
     * <pre>
     * <font style='background-color: #D6A836;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6A836; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6A836;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6A836'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6A836'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6A836'>&nbsp;@&nbsp;</font><font style='background-color: #D6A836; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6A836;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6A836; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_SAFFRON = -0x1.230152p126F;
    static { NAMED.put("true yellow saffron", -0x1.230152p126F); LIST.add(-0x1.230152p126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code ECBE4BFF}, L 0.74509805, A 0.49803922, B 0.5686275, alpha 1.0, hue 0.2545336, saturation 0.56700194, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.22ff7cp126F}.
     * <pre>
     * <font style='background-color: #ECBE4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECBE4B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECBE4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECBE4B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECBE4B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECBE4B'>&nbsp;@&nbsp;</font><font style='background-color: #ECBE4B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECBE4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECBE4B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.22ff7cp126F;
    static { NAMED.put("bright yellow saffron", -0x1.22ff7cp126F); LIST.add(-0x1.22ff7cp126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code C7AE36FF}, L 0.65882355, A 0.4862745, B 0.5686275, alpha 1.0, hue 0.28142345, saturation 0.6433531, and chroma 0.13942632.
     * It can be represented as a packed float with the constant {@code -0x1.22f95p126F}.
     * <pre>
     * <font style='background-color: #C7AE36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7AE36; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7AE36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7AE36'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7AE36'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7AE36'>&nbsp;@&nbsp;</font><font style='background-color: #C7AE36; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7AE36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7AE36; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.22f95p126F;
    static { NAMED.put("deep saffron yellow", -0x1.22f95p126F); LIST.add(-0x1.22f95p126F); }

    /**
     * This color constant "true saffron yellow" has RGBA8888 code {@code DEC44CFF}, L 0.74509805, A 0.49019608, B 0.5686275, alpha 1.0, hue 0.27259654, saturation 0.55401665, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.22fb7cp126F}.
     * <pre>
     * <font style='background-color: #DEC44C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEC44C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEC44C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEC44C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEC44C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEC44C'>&nbsp;@&nbsp;</font><font style='background-color: #DEC44C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEC44C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEC44C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_YELLOW = -0x1.22fb7cp126F;
    static { NAMED.put("true saffron yellow", -0x1.22fb7cp126F); LIST.add(-0x1.22fb7cp126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code F1D75DFF}, L 0.81960785, A 0.4862745, B 0.5686275, alpha 1.0, hue 0.28142345, saturation 0.49955887, and chroma 0.13942632.
     * It can be represented as a packed float with the constant {@code -0x1.22f9a2p126F}.
     * <pre>
     * <font style='background-color: #F1D75D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1D75D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1D75D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F1D75D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F1D75D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F1D75D'>&nbsp;@&nbsp;</font><font style='background-color: #F1D75D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1D75D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1D75D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.22f9a2p126F;
    static { NAMED.put("bright saffron yellow", -0x1.22f9a2p126F); LIST.add(-0x1.22f9a2p126F); }

    /**
     * This color constant "deep pure yellow" has RGBA8888 code {@code B5B91EFF}, L 0.6666667, A 0.46666667, B 0.5764706, alpha 1.0, hue 0.31541443, saturation 0.76947606, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.26ef54p126F}.
     * <pre>
     * <font style='background-color: #B5B91E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5B91E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5B91E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B5B91E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B5B91E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B5B91E'>&nbsp;@&nbsp;</font><font style='background-color: #B5B91E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5B91E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5B91E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_YELLOW = -0x1.26ef54p126F;
    static { NAMED.put("deep pure yellow", -0x1.26ef54p126F); LIST.add(-0x1.26ef54p126F); }

    /**
     * This color constant "true pure yellow" has RGBA8888 code {@code CCD13BFF}, L 0.75686276, A 0.46666667, B 0.5764706, alpha 1.0, hue 0.31541443, saturation 0.68243945, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.26ef82p126F}.
     * <pre>
     * <font style='background-color: #CCD13B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCD13B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCD13B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CCD13B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CCD13B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CCD13B'>&nbsp;@&nbsp;</font><font style='background-color: #CCD13B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCD13B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCD13B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_YELLOW = -0x1.26ef82p126F;
    static { NAMED.put("true pure yellow", -0x1.26ef82p126F); LIST.add(-0x1.26ef82p126F); }

    /**
     * This color constant "bright pure yellow" has RGBA8888 code {@code E2E751FF}, L 0.84705883, A 0.47058824, B 0.5764706, alpha 1.0, hue 0.30842525, saturation 0.5878293, and chroma 0.1632233.
     * It can be represented as a packed float with the constant {@code -0x1.26f1bp126F}.
     * <pre>
     * <font style='background-color: #E2E751;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2E751; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2E751;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2E751'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2E751'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2E751'>&nbsp;@&nbsp;</font><font style='background-color: #E2E751; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2E751;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2E751; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_YELLOW = -0x1.26f1bp126F;
    static { NAMED.put("bright pure yellow", -0x1.26f1bp126F); LIST.add(-0x1.26f1bp126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code A2B935FF}, L 0.6509804, A 0.4627451, B 0.5686275, alpha 1.0, hue 0.32915777, saturation 0.6472809, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.22ed4cp126F}.
     * <pre>
     * <font style='background-color: #A2B935;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2B935; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2B935;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2B935'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2B935'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2B935'>&nbsp;@&nbsp;</font><font style='background-color: #A2B935; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2B935;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2B935; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.22ed4cp126F;
    static { NAMED.put("deep lime yellow", -0x1.22ed4cp126F); LIST.add(-0x1.22ed4cp126F); }

    /**
     * This color constant "true lime yellow" has RGBA8888 code {@code B6CF4AFF}, L 0.73333335, A 0.45882353, B 0.5686275, alpha 1.0, hue 0.33601886, saturation 0.5408652, and chroma 0.15944009.
     * It can be represented as a packed float with the constant {@code -0x1.22eb76p126F}.
     * <pre>
     * <font style='background-color: #B6CF4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6CF4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6CF4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6CF4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6CF4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6CF4A'>&nbsp;@&nbsp;</font><font style='background-color: #B6CF4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6CF4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6CF4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_YELLOW = -0x1.22eb76p126F;
    static { NAMED.put("true lime yellow", -0x1.22eb76p126F); LIST.add(-0x1.22eb76p126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code CEE961FF}, L 0.83137256, A 0.45882353, B 0.5686275, alpha 1.0, hue 0.33601886, saturation 0.47058824, and chroma 0.15944009.
     * It can be represented as a packed float with the constant {@code -0x1.22eba8p126F}.
     * <pre>
     * <font style='background-color: #CEE961;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEE961; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEE961;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEE961'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEE961'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEE961'>&nbsp;@&nbsp;</font><font style='background-color: #CEE961; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEE961;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEE961; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.22eba8p126F;
    static { NAMED.put("bright lime yellow", -0x1.22eba8p126F); LIST.add(-0x1.22eba8p126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code 96BE19FF}, L 0.6509804, A 0.44705883, B 0.5764706, alpha 1.0, hue 0.34638813, saturation 0.78609484, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.26e54cp126F}.
     * <pre>
     * <font style='background-color: #96BE19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96BE19; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96BE19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #96BE19'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #96BE19'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #96BE19'>&nbsp;@&nbsp;</font><font style='background-color: #96BE19; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96BE19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96BE19; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.26e54cp126F;
    static { NAMED.put("deep yellow lime", -0x1.26e54cp126F); LIST.add(-0x1.26e54cp126F); }

    /**
     * This color constant "true yellow lime" has RGBA8888 code {@code ACD637FF}, L 0.7411765, A 0.44705883, B 0.5764706, alpha 1.0, hue 0.34638813, saturation 0.6805293, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.26e57ap126F}.
     * <pre>
     * <font style='background-color: #ACD637;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACD637; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACD637;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ACD637'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ACD637'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ACD637'>&nbsp;@&nbsp;</font><font style='background-color: #ACD637; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACD637;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACD637; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_LIME = -0x1.26e57ap126F;
    static { NAMED.put("true yellow lime", -0x1.26e57ap126F); LIST.add(-0x1.26e57ap126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code BFEB4BFF}, L 0.81960785, A 0.44705883, B 0.5764706, alpha 1.0, hue 0.34638813, saturation 0.6147121, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.26e5a2p126F}.
     * <pre>
     * <font style='background-color: #BFEB4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFEB4B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFEB4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFEB4B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFEB4B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFEB4B'>&nbsp;@&nbsp;</font><font style='background-color: #BFEB4B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFEB4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFEB4B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.26e5a2p126F;
    static { NAMED.put("bright yellow lime", -0x1.26e5a2p126F); LIST.add(-0x1.26e5a2p126F); }

    /**
     * This color constant "deep pure lime" has RGBA8888 code {@code 82B72BFF}, L 0.6156863, A 0.44313726, B 0.5686275, alpha 1.0, hue 0.3601329, saturation 0.67072475, and chroma 0.17755185.
     * It can be represented as a packed float with the constant {@code -0x1.22e33ap126F}.
     * <pre>
     * <font style='background-color: #82B72B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82B72B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82B72B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #82B72B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #82B72B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #82B72B'>&nbsp;@&nbsp;</font><font style='background-color: #82B72B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82B72B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82B72B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_LIME = -0x1.22e33ap126F;
    static { NAMED.put("deep pure lime", -0x1.22e33ap126F); LIST.add(-0x1.22e33ap126F); }

    /**
     * This color constant "true pure lime" has RGBA8888 code {@code 96CE42FF}, L 0.69803923, A 0.44313726, B 0.5686275, alpha 1.0, hue 0.3601329, saturation 0.5835746, and chroma 0.17755185.
     * It can be represented as a packed float with the constant {@code -0x1.22e364p126F}.
     * <pre>
     * <font style='background-color: #96CE42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96CE42; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96CE42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #96CE42'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #96CE42'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #96CE42'>&nbsp;@&nbsp;</font><font style='background-color: #96CE42; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96CE42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96CE42; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_LIME = -0x1.22e364p126F;
    static { NAMED.put("true pure lime", -0x1.22e364p126F); LIST.add(-0x1.22e364p126F); }

    /**
     * This color constant "bright pure lime" has RGBA8888 code {@code ABE558FF}, L 0.7882353, A 0.44313726, B 0.5686275, alpha 1.0, hue 0.3601329, saturation 0.49660477, and chroma 0.17755185.
     * It can be represented as a packed float with the constant {@code -0x1.22e392p126F}.
     * <pre>
     * <font style='background-color: #ABE558;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE558; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE558;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE558'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE558'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE558'>&nbsp;@&nbsp;</font><font style='background-color: #ABE558; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE558;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE558; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_LIME = -0x1.22e392p126F;
    static { NAMED.put("bright pure lime", -0x1.22e392p126F); LIST.add(-0x1.22e392p126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 61BD1DFF}, L 0.6117647, A 0.42352942, B 0.57254905, alpha 1.0, hue 0.37918407, saturation 0.73984, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.24d938p126F}.
     * <pre>
     * <font style='background-color: #61BD1D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61BD1D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61BD1D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61BD1D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61BD1D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61BD1D'>&nbsp;@&nbsp;</font><font style='background-color: #61BD1D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61BD1D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61BD1D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.24d938p126F;
    static { NAMED.put("deep green lime", -0x1.24d938p126F); LIST.add(-0x1.24d938p126F); }

    /**
     * This color constant "true green lime" has RGBA8888 code {@code 78D83AFF}, L 0.7058824, A 0.42352942, B 0.57254905, alpha 1.0, hue 0.37918407, saturation 0.6159092, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.24d968p126F}.
     * <pre>
     * <font style='background-color: #78D83A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78D83A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78D83A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #78D83A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #78D83A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #78D83A'>&nbsp;@&nbsp;</font><font style='background-color: #78D83A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78D83A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78D83A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_LIME = -0x1.24d968p126F;
    static { NAMED.put("true green lime", -0x1.24d968p126F); LIST.add(-0x1.24d968p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code 8AEC4DFF}, L 0.78039217, A 0.42352942, B 0.57254905, alpha 1.0, hue 0.37918407, saturation 0.5498216, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.24d98ep126F}.
     * <pre>
     * <font style='background-color: #8AEC4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8AEC4D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8AEC4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8AEC4D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8AEC4D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8AEC4D'>&nbsp;@&nbsp;</font><font style='background-color: #8AEC4D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8AEC4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8AEC4D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.24d98ep126F;
    static { NAMED.put("bright green lime", -0x1.24d98ep126F); LIST.add(-0x1.24d98ep126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 4CBA31FF}, L 0.5921569, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.64565, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.20d72ep126F}.
     * <pre>
     * <font style='background-color: #4CBA31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4CBA31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4CBA31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4CBA31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4CBA31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4CBA31'>&nbsp;@&nbsp;</font><font style='background-color: #4CBA31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4CBA31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4CBA31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.20d72ep126F;
    static { NAMED.put("deep lime green", -0x1.20d72ep126F); LIST.add(-0x1.20d72ep126F); }

    /**
     * This color constant "true lime green" has RGBA8888 code {@code 60D046FF}, L 0.67058825, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.557316, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.20d756p126F}.
     * <pre>
     * <font style='background-color: #60D046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #60D046; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #60D046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #60D046'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #60D046'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #60D046'>&nbsp;@&nbsp;</font><font style='background-color: #60D046; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #60D046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #60D046; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_GREEN = -0x1.20d756p126F;
    static { NAMED.put("true lime green", -0x1.20d756p126F); LIST.add(-0x1.20d756p126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code 73E659FF}, L 0.7490196, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.4990766, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.20d77ep126F}.
     * <pre>
     * <font style='background-color: #73E659;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73E659; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73E659;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73E659'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73E659'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73E659'>&nbsp;@&nbsp;</font><font style='background-color: #73E659; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73E659;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73E659; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.20d77ep126F;
    static { NAMED.put("bright lime green", -0x1.20d77ep126F); LIST.add(-0x1.20d77ep126F); }

    /**
     * This color constant "deep pure green" has RGBA8888 code {@code 20C03CFF}, L 0.59607846, A 0.40784314, B 0.56078434, alpha 1.0, hue 0.40718868, saturation 0.92629117, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.1ed13p126F}.
     * <pre>
     * <font style='background-color: #20C03C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20C03C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20C03C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #20C03C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #20C03C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #20C03C'>&nbsp;@&nbsp;</font><font style='background-color: #20C03C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20C03C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20C03C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_GREEN = -0x1.1ed13p126F;
    static { NAMED.put("deep pure green", -0x1.1ed13p126F); LIST.add(-0x1.1ed13p126F); }

    /**
     * This color constant "true pure green" has RGBA8888 code {@code 39D44EFF}, L 0.6666667, A 0.40784314, B 0.56078434, alpha 1.0, hue 0.40718868, saturation 0.81152, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.1ed154p126F}.
     * <pre>
     * <font style='background-color: #39D44E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #39D44E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #39D44E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #39D44E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #39D44E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #39D44E'>&nbsp;@&nbsp;</font><font style='background-color: #39D44E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #39D44E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #39D44E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_GREEN = -0x1.1ed154p126F;
    static { NAMED.put("true pure green", -0x1.1ed154p126F); LIST.add(-0x1.1ed154p126F); }

    /**
     * This color constant "bright pure green" has RGBA8888 code {@code 50EB62FF}, L 0.7490196, A 0.40784314, B 0.56078434, alpha 1.0, hue 0.40718868, saturation 0.6957476, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.1ed17ep126F}.
     * <pre>
     * <font style='background-color: #50EB62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50EB62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50EB62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #50EB62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #50EB62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #50EB62'>&nbsp;@&nbsp;</font><font style='background-color: #50EB62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50EB62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50EB62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_GREEN = -0x1.1ed17ep126F;
    static { NAMED.put("bright pure green", -0x1.1ed17ep126F); LIST.add(-0x1.1ed17ep126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 41BF86FF}, L 0.61960787, A 0.43137255, B 0.52156866, alpha 1.0, hue 0.451547, saturation 0.6797122, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add3cp126F}.
     * <pre>
     * <font style='background-color: #41BF86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #41BF86; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #41BF86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #41BF86'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #41BF86'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #41BF86'>&nbsp;@&nbsp;</font><font style='background-color: #41BF86; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #41BF86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #41BF86; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.0add3cp126F;
    static { NAMED.put("deep cyan green", -0x1.0add3cp126F); LIST.add(-0x1.0add3cp126F); }

    /**
     * This color constant "true cyan green" has RGBA8888 code {@code 57D69BFF}, L 0.7019608, A 0.43137255, B 0.5176471, alpha 1.0, hue 0.4599463, saturation 0.60400045, and chroma 0.1411665.
     * It can be represented as a packed float with the constant {@code -0x1.08dd66p126F}.
     * <pre>
     * <font style='background-color: #57D69B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57D69B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57D69B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57D69B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57D69B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57D69B'>&nbsp;@&nbsp;</font><font style='background-color: #57D69B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57D69B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57D69B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_GREEN = -0x1.08dd66p126F;
    static { NAMED.put("true cyan green", -0x1.08dd66p126F); LIST.add(-0x1.08dd66p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code 68E9ACFF}, L 0.77254903, A 0.43137255, B 0.5176471, alpha 1.0, hue 0.4599463, saturation 0.53300685, and chroma 0.1411665.
     * It can be represented as a packed float with the constant {@code -0x1.08dd8ap126F}.
     * <pre>
     * <font style='background-color: #68E9AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68E9AC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68E9AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68E9AC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68E9AC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68E9AC'>&nbsp;@&nbsp;</font><font style='background-color: #68E9AC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68E9AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68E9AC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.08dd8ap126F;
    static { NAMED.put("bright cyan green", -0x1.08dd8ap126F); LIST.add(-0x1.08dd8ap126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 24C0A4FF}, L 0.62352943, A 0.43137255, B 0.5019608, alpha 1.0, hue 0.4954664, saturation 0.87182224, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.00dd3ep126F}.
     * <pre>
     * <font style='background-color: #24C0A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #24C0A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #24C0A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #24C0A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #24C0A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #24C0A4'>&nbsp;@&nbsp;</font><font style='background-color: #24C0A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #24C0A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #24C0A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.00dd3ep126F;
    static { NAMED.put("deep green cyan", -0x1.00dd3ep126F); LIST.add(-0x1.00dd3ep126F); }

    /**
     * This color constant "true green cyan" has RGBA8888 code {@code 3ED5B8FF}, L 0.69803923, A 0.43137255, B 0.49803922, alpha 1.0, hue 0.5045336, saturation 0.7857715, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.fedd64p125F}.
     * <pre>
     * <font style='background-color: #3ED5B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3ED5B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3ED5B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3ED5B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3ED5B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3ED5B8'>&nbsp;@&nbsp;</font><font style='background-color: #3ED5B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3ED5B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3ED5B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_CYAN = -0x1.fedd64p125F;
    static { NAMED.put("true green cyan", -0x1.fedd64p125F); LIST.add(-0x1.fedd64p125F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code 55EBCDFF}, L 0.78039217, A 0.43137255, B 0.49803922, alpha 1.0, hue 0.5045336, saturation 0.6787543, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.fedd8ep125F}.
     * <pre>
     * <font style='background-color: #55EBCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55EBCD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55EBCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #55EBCD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #55EBCD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #55EBCD'>&nbsp;@&nbsp;</font><font style='background-color: #55EBCD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55EBCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55EBCD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.fedd8ep125F;
    static { NAMED.put("bright green cyan", -0x1.fedd8ep125F); LIST.add(-0x1.fedd8ep125F); }

    /**
     * This color constant "deep pure cyan" has RGBA8888 code {@code 3EBDBCFF}, L 0.6313726, A 0.44313726, B 0.4862745, alpha 1.0, hue 0.5376946, saturation 0.70620906, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.f8e342p125F}.
     * <pre>
     * <font style='background-color: #3EBDBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3EBDBC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3EBDBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3EBDBC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3EBDBC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3EBDBC'>&nbsp;@&nbsp;</font><font style='background-color: #3EBDBC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3EBDBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3EBDBC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_CYAN = -0x1.f8e342p125F;
    static { NAMED.put("deep pure cyan", -0x1.f8e342p125F); LIST.add(-0x1.f8e342p125F); }

    /**
     * This color constant "true pure cyan" has RGBA8888 code {@code 57D7D6FF}, L 0.7254902, A 0.44313726, B 0.48235294, alpha 1.0, hue 0.54788184, saturation 0.62202734, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e372p125F}.
     * <pre>
     * <font style='background-color: #57D7D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57D7D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57D7D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57D7D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57D7D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57D7D6'>&nbsp;@&nbsp;</font><font style='background-color: #57D7D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57D7D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57D7D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_CYAN = -0x1.f6e372p125F;
    static { NAMED.put("true pure cyan", -0x1.f6e372p125F); LIST.add(-0x1.f6e372p125F); }

    /**
     * This color constant "bright pure cyan" has RGBA8888 code {@code 6AECEAFF}, L 0.8039216, A 0.44313726, B 0.4862745, alpha 1.0, hue 0.5376946, saturation 0.51676583, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.f8e39ap125F}.
     * <pre>
     * <font style='background-color: #6AECEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AECEA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AECEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6AECEA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6AECEA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6AECEA'>&nbsp;@&nbsp;</font><font style='background-color: #6AECEA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AECEA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AECEA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_CYAN = -0x1.f8e39ap125F;
    static { NAMED.put("bright pure cyan", -0x1.f8e39ap125F); LIST.add(-0x1.f8e39ap125F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 1C9FC2FF}, L 0.5411765, A 0.4509804, B 0.45882353, alpha 1.0, hue 0.6112048, saturation 0.89561015, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.eae714p125F}.
     * <pre>
     * <font style='background-color: #1C9FC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C9FC2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C9FC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C9FC2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C9FC2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C9FC2'>&nbsp;@&nbsp;</font><font style='background-color: #1C9FC2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C9FC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C9FC2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.eae714p125F;
    static { NAMED.put("deep blue cyan", -0x1.eae714p125F); LIST.add(-0x1.eae714p125F); }

    /**
     * This color constant "true blue cyan" has RGBA8888 code {@code 38B5DAFF}, L 0.61960787, A 0.4509804, B 0.45882353, alpha 1.0, hue 0.6112048, saturation 0.7580444, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.eae73cp125F}.
     * <pre>
     * <font style='background-color: #38B5DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38B5DA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38B5DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #38B5DA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #38B5DA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #38B5DA'>&nbsp;@&nbsp;</font><font style='background-color: #38B5DA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38B5DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38B5DA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_CYAN = -0x1.eae73cp125F;
    static { NAMED.put("true blue cyan", -0x1.eae73cp125F); LIST.add(-0x1.eae73cp125F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 4CC9EEFF}, L 0.6901961, A 0.4509804, B 0.4627451, alpha 1.0, hue 0.6034426, saturation 0.63195, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.ece76p125F}.
     * <pre>
     * <font style='background-color: #4CC9EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4CC9EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4CC9EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4CC9EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4CC9EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4CC9EE'>&nbsp;@&nbsp;</font><font style='background-color: #4CC9EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4CC9EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4CC9EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.ece76p125F;
    static { NAMED.put("bright blue cyan", -0x1.ece76p125F); LIST.add(-0x1.ece76p125F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 237AB5FF}, L 0.44313726, A 0.46666667, B 0.44313726, alpha 1.0, hue 0.6656062, saturation 0.76235455, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.e2eee2p125F}.
     * <pre>
     * <font style='background-color: #237AB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #237AB5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #237AB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #237AB5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #237AB5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #237AB5'>&nbsp;@&nbsp;</font><font style='background-color: #237AB5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #237AB5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #237AB5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.e2eee2p125F;
    static { NAMED.put("deep cyan blue", -0x1.e2eee2p125F); LIST.add(-0x1.e2eee2p125F); }

    /**
     * This color constant "true cyan blue" has RGBA8888 code {@code 368CCAFF}, L 0.5058824, A 0.46666667, B 0.44313726, alpha 1.0, hue 0.6656062, saturation 0.6256055, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.e2ef02p125F}.
     * <pre>
     * <font style='background-color: #368CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #368CCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #368CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #368CCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #368CCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #368CCA'>&nbsp;@&nbsp;</font><font style='background-color: #368CCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #368CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #368CCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_BLUE = -0x1.e2ef02p125F;
    static { NAMED.put("true cyan blue", -0x1.e2ef02p125F); LIST.add(-0x1.e2ef02p125F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code 4BA3E4FF}, L 0.58431375, A 0.46666667, B 0.44313726, alpha 1.0, hue 0.6656062, saturation 0.5458278, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.e2ef2ap125F}.
     * <pre>
     * <font style='background-color: #4BA3E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4BA3E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4BA3E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4BA3E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4BA3E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4BA3E4'>&nbsp;@&nbsp;</font><font style='background-color: #4BA3E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4BA3E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4BA3E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.e2ef2ap125F;
    static { NAMED.put("bright cyan blue", -0x1.e2ef2ap125F); LIST.add(-0x1.e2ef2ap125F); }

    /**
     * This color constant "deep pure blue" has RGBA8888 code {@code 0027C4FF}, L 0.28235295, A 0.4862745, B 0.3764706, alpha 1.0, hue 0.7323789, saturation 0.7239314, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f89p125F}.
     * <pre>
     * <font style='background-color: #0027C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0027C4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0027C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0027C4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0027C4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0027C4'>&nbsp;@&nbsp;</font><font style='background-color: #0027C4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0027C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0027C4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BLUE = -0x1.c0f89p125F;
    static { NAMED.put("deep pure blue", -0x1.c0f89p125F); LIST.add(-0x1.c0f89p125F); }

    /**
     * This color constant "true pure blue" has RGBA8888 code {@code 0A3DDCFF}, L 0.3372549, A 0.48235294, B 0.3764706, alpha 1.0, hue 0.72740346, saturation 0.62497586, and chroma 0.24859223.
     * It can be represented as a packed float with the constant {@code -0x1.c0f6acp125F}.
     * <pre>
     * <font style='background-color: #0A3DDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A3DDC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A3DDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0A3DDC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0A3DDC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0A3DDC'>&nbsp;@&nbsp;</font><font style='background-color: #0A3DDC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A3DDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A3DDC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BLUE = -0x1.c0f6acp125F;
    static { NAMED.put("true pure blue", -0x1.c0f6acp125F); LIST.add(-0x1.c0f6acp125F); }

    /**
     * This color constant "bright pure blue" has RGBA8888 code {@code 174DF0FF}, L 0.38431373, A 0.4862745, B 0.3764706, alpha 1.0, hue 0.7323789, saturation 0.7859553, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f8c4p125F}.
     * <pre>
     * <font style='background-color: #174DF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #174DF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #174DF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #174DF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #174DF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #174DF0'>&nbsp;@&nbsp;</font><font style='background-color: #174DF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #174DF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #174DF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BLUE = -0x1.c0f8c4p125F;
    static { NAMED.put("bright pure blue", -0x1.c0f8c4p125F); LIST.add(-0x1.c0f8c4p125F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 332DB7FF}, L 0.29803923, A 0.5058824, B 0.39215687, alpha 1.0, hue 0.7586634, saturation 0.5934765, and chroma 0.21516311.
     * It can be represented as a packed float with the constant {@code -0x1.c90298p125F}.
     * <pre>
     * <font style='background-color: #332DB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #332DB7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #332DB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #332DB7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #332DB7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #332DB7'>&nbsp;@&nbsp;</font><font style='background-color: #332DB7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #332DB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #332DB7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.c90298p125F;
    static { NAMED.put("deep violet blue", -0x1.c90298p125F); LIST.add(-0x1.c90298p125F); }

    /**
     * This color constant "true violet blue" has RGBA8888 code {@code 3F3FCDFF}, L 0.34901962, A 0.50980395, B 0.39215687, alpha 1.0, hue 0.764433, saturation 0.48257583, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.c904b2p125F}.
     * <pre>
     * <font style='background-color: #3F3FCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F3FCD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F3FCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F3FCD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F3FCD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F3FCD'>&nbsp;@&nbsp;</font><font style='background-color: #3F3FCD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F3FCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F3FCD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_BLUE = -0x1.c904b2p125F;
    static { NAMED.put("true violet blue", -0x1.c904b2p125F); LIST.add(-0x1.c904b2p125F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code 4B4FE2FF}, L 0.4, A 0.50980395, B 0.39215687, alpha 1.0, hue 0.764433, saturation 0.6136512, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.c904ccp125F}.
     * <pre>
     * <font style='background-color: #4B4FE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B4FE2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B4FE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B4FE2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B4FE2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B4FE2'>&nbsp;@&nbsp;</font><font style='background-color: #4B4FE2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B4FE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B4FE2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.c904ccp125F;
    static { NAMED.put("bright violet blue", -0x1.c904ccp125F); LIST.add(-0x1.c904ccp125F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 4D20BFFF}, L 0.30980393, A 0.53333336, B 0.3882353, alpha 1.0, hue 0.7961206, saturation 0.7765158, and chroma 0.23234801.
     * It can be represented as a packed float with the constant {@code -0x1.c7109ep125F}.
     * <pre>
     * <font style='background-color: #4D20BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4D20BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4D20BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4D20BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4D20BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4D20BF'>&nbsp;@&nbsp;</font><font style='background-color: #4D20BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4D20BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4D20BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.c7109ep125F;
    static { NAMED.put("deep blue violet", -0x1.c7109ep125F); LIST.add(-0x1.c7109ep125F); }

    /**
     * This color constant "true blue violet" has RGBA8888 code {@code 5C35D6FF}, L 0.3647059, A 0.53333336, B 0.3882353, alpha 1.0, hue 0.7961206, saturation 0.6045538, and chroma 0.23234801.
     * It can be represented as a packed float with the constant {@code -0x1.c710bap125F}.
     * <pre>
     * <font style='background-color: #5C35D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C35D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C35D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C35D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C35D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C35D6'>&nbsp;@&nbsp;</font><font style='background-color: #5C35D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C35D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C35D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_VIOLET = -0x1.c710bap125F;
    static { NAMED.put("true blue violet", -0x1.c710bap125F); LIST.add(-0x1.c710bap125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code 6C4AF0FF}, L 0.42745098, A 0.53333336, B 0.3882353, alpha 1.0, hue 0.7961206, saturation 0.73246723, and chroma 0.23234801.
     * It can be represented as a packed float with the constant {@code -0x1.c710dap125F}.
     * <pre>
     * <font style='background-color: #6C4AF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C4AF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C4AF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C4AF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C4AF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C4AF0'>&nbsp;@&nbsp;</font><font style='background-color: #6C4AF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C4AF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C4AF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.c710dap125F;
    static { NAMED.put("bright blue violet", -0x1.c710dap125F); LIST.add(-0x1.c710dap125F); }

    /**
     * This color constant "deep pure violet" has RGBA8888 code {@code 6E2EB9FF}, L 0.3529412, A 0.5529412, B 0.40784314, alpha 1.0, hue 0.8329952, saturation 0.6082501, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d11ab4p125F}.
     * <pre>
     * <font style='background-color: #6E2EB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E2EB9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E2EB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E2EB9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E2EB9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E2EB9'>&nbsp;@&nbsp;</font><font style='background-color: #6E2EB9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E2EB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E2EB9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_VIOLET = -0x1.d11ab4p125F;
    static { NAMED.put("deep pure violet", -0x1.d11ab4p125F); LIST.add(-0x1.d11ab4p125F); }

    /**
     * This color constant "true pure violet" has RGBA8888 code {@code 8041D0FF}, L 0.4117647, A 0.5529412, B 0.40784314, alpha 1.0, hue 0.8329952, saturation 0.48915714, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d11ad2p125F}.
     * <pre>
     * <font style='background-color: #8041D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8041D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8041D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8041D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8041D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8041D0'>&nbsp;@&nbsp;</font><font style='background-color: #8041D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8041D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8041D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_VIOLET = -0x1.d11ad2p125F;
    static { NAMED.put("true pure violet", -0x1.d11ad2p125F); LIST.add(-0x1.d11ad2p125F); }

    /**
     * This color constant "bright pure violet" has RGBA8888 code {@code 9052E5FF}, L 0.46666667, A 0.5529412, B 0.40784314, alpha 1.0, hue 0.8329952, saturation 0.57469803, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d11aeep125F}.
     * <pre>
     * <font style='background-color: #9052E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9052E5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9052E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9052E5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9052E5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9052E5'>&nbsp;@&nbsp;</font><font style='background-color: #9052E5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9052E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9052E5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_VIOLET = -0x1.d11aeep125F;
    static { NAMED.put("bright pure violet", -0x1.d11aeep125F); LIST.add(-0x1.d11aeep125F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 7924C6FF}, L 0.3647059, A 0.5647059, B 0.4, alpha 1.0, hue 0.84141475, saturation 0.7639356, and chroma 0.2372866.
     * It can be represented as a packed float with the constant {@code -0x1.cd20bap125F}.
     * <pre>
     * <font style='background-color: #7924C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7924C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7924C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7924C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7924C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7924C6'>&nbsp;@&nbsp;</font><font style='background-color: #7924C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7924C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7924C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.cd20bap125F;
    static { NAMED.put("deep purple violet", -0x1.cd20bap125F); LIST.add(-0x1.cd20bap125F); }

    /**
     * This color constant "true purple violet" has RGBA8888 code {@code 8936DAFF}, L 0.41568628, A 0.5647059, B 0.4, alpha 1.0, hue 0.84141475, saturation 0.61436003, and chroma 0.2372866.
     * It can be represented as a packed float with the constant {@code -0x1.cd20d4p125F}.
     * <pre>
     * <font style='background-color: #8936DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8936DA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8936DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8936DA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8936DA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8936DA'>&nbsp;@&nbsp;</font><font style='background-color: #8936DA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8936DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8936DA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_VIOLET = -0x1.cd20d4p125F;
    static { NAMED.put("true purple violet", -0x1.cd20d4p125F); LIST.add(-0x1.cd20d4p125F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code 9C49F1FF}, L 0.4745098, A 0.5647059, B 0.4, alpha 1.0, hue 0.84141475, saturation 0.7020214, and chroma 0.2372866.
     * It can be represented as a packed float with the constant {@code -0x1.cd20f2p125F}.
     * <pre>
     * <font style='background-color: #9C49F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C49F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C49F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C49F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C49F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C49F1'>&nbsp;@&nbsp;</font><font style='background-color: #9C49F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C49F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C49F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.cd20f2p125F;
    static { NAMED.put("bright purple violet", -0x1.cd20f2p125F); LIST.add(-0x1.cd20f2p125F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 8831BDFF}, L 0.3882353, A 0.5686275, B 0.41568628, alpha 1.0, hue 0.85874414, saturation 0.6013008, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.d522c6p125F}.
     * <pre>
     * <font style='background-color: #8831BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8831BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8831BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8831BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8831BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8831BD'>&nbsp;@&nbsp;</font><font style='background-color: #8831BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8831BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8831BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.d522c6p125F;
    static { NAMED.put("deep violet purple", -0x1.d522c6p125F); LIST.add(-0x1.d522c6p125F); }

    /**
     * This color constant "true violet purple" has RGBA8888 code {@code 9C45D4FF}, L 0.4509804, A 0.5686275, B 0.41568628, alpha 1.0, hue 0.85874414, saturation 0.48637316, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.d522e6p125F}.
     * <pre>
     * <font style='background-color: #9C45D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C45D4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C45D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C45D4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C45D4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C45D4'>&nbsp;@&nbsp;</font><font style='background-color: #9C45D4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C45D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C45D4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_PURPLE = -0x1.d522e6p125F;
    static { NAMED.put("true violet purple", -0x1.d522e6p125F); LIST.add(-0x1.d522e6p125F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code AD55E8FF}, L 0.5058824, A 0.5647059, B 0.41568628, alpha 1.0, hue 0.8541905, saturation 0.59111714, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d52102p125F}.
     * <pre>
     * <font style='background-color: #AD55E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD55E8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD55E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD55E8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD55E8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD55E8'>&nbsp;@&nbsp;</font><font style='background-color: #AD55E8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD55E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD55E8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.d52102p125F;
    static { NAMED.put("bright violet purple", -0x1.d52102p125F); LIST.add(-0x1.d52102p125F); }

    /**
     * This color constant "deep pure purple" has RGBA8888 code {@code 9622C3FF}, L 0.39607844, A 0.58431375, B 0.4117647, alpha 1.0, hue 0.8713863, saturation 0.77943766, and chroma 0.24313073.
     * It can be represented as a packed float with the constant {@code -0x1.d32acap125F}.
     * <pre>
     * <font style='background-color: #9622C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9622C3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9622C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9622C3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9622C3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9622C3'>&nbsp;@&nbsp;</font><font style='background-color: #9622C3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9622C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9622C3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_PURPLE = -0x1.d32acap125F;
    static { NAMED.put("deep pure purple", -0x1.d32acap125F); LIST.add(-0x1.d32acap125F); }

    /**
     * This color constant "true pure purple" has RGBA8888 code {@code AB38DBFF}, L 0.45882353, A 0.58431375, B 0.4117647, alpha 1.0, hue 0.8713863, saturation 0.62866646, and chroma 0.24313073.
     * It can be represented as a packed float with the constant {@code -0x1.d32aeap125F}.
     * <pre>
     * <font style='background-color: #AB38DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB38DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB38DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB38DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB38DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB38DB'>&nbsp;@&nbsp;</font><font style='background-color: #AB38DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB38DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB38DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_PURPLE = -0x1.d32aeap125F;
    static { NAMED.put("true pure purple", -0x1.d32aeap125F); LIST.add(-0x1.d32aeap125F); }

    /**
     * This color constant "bright pure purple" has RGBA8888 code {@code BF4AF0FF}, L 0.5176471, A 0.58431375, B 0.4117647, alpha 1.0, hue 0.8713863, saturation 0.6449948, and chroma 0.24313073.
     * It can be represented as a packed float with the constant {@code -0x1.d32b08p125F}.
     * <pre>
     * <font style='background-color: #BF4AF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF4AF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF4AF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF4AF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF4AF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF4AF0'>&nbsp;@&nbsp;</font><font style='background-color: #BF4AF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF4AF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF4AF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_PURPLE = -0x1.d32b08p125F;
    static { NAMED.put("bright pure purple", -0x1.d32b08p125F); LIST.add(-0x1.d32b08p125F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code 9B31BBFF}, L 0.4117647, A 0.5803922, B 0.42352942, alpha 1.0, hue 0.878975, saturation 0.6263387, and chroma 0.2210399.
     * It can be represented as a packed float with the constant {@code -0x1.d928d2p125F}.
     * <pre>
     * <font style='background-color: #9B31BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B31BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B31BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B31BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B31BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B31BB'>&nbsp;@&nbsp;</font><font style='background-color: #9B31BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B31BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B31BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.d928d2p125F;
    static { NAMED.put("deep magenta purple", -0x1.d928d2p125F); LIST.add(-0x1.d928d2p125F); }

    /**
     * This color constant "true magenta purple" has RGBA8888 code {@code AE42CFFF}, L 0.46666667, A 0.5803922, B 0.42352942, alpha 1.0, hue 0.878975, saturation 0.53311133, and chroma 0.2210399.
     * It can be represented as a packed float with the constant {@code -0x1.d928eep125F}.
     * <pre>
     * <font style='background-color: #AE42CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE42CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE42CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE42CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE42CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE42CF'>&nbsp;@&nbsp;</font><font style='background-color: #AE42CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE42CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE42CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_PURPLE = -0x1.d928eep125F;
    static { NAMED.put("true magenta purple", -0x1.d928eep125F); LIST.add(-0x1.d928eep125F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code C557E7FF}, L 0.5372549, A 0.5803922, B 0.42745098, alpha 1.0, hue 0.8831485, saturation 0.47066087, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.db2912p125F}.
     * <pre>
     * <font style='background-color: #C557E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C557E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C557E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C557E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C557E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C557E7'>&nbsp;@&nbsp;</font><font style='background-color: #C557E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C557E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C557E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.db2912p125F;
    static { NAMED.put("bright magenta purple", -0x1.db2912p125F); LIST.add(-0x1.db2912p125F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code B626C5FF}, L 0.44313726, A 0.6, B 0.42352942, alpha 1.0, hue 0.89608383, saturation 0.7630154, and chroma 0.2507922.
     * It can be represented as a packed float with the constant {@code -0x1.d932e2p125F}.
     * <pre>
     * <font style='background-color: #B626C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B626C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B626C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B626C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B626C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B626C5'>&nbsp;@&nbsp;</font><font style='background-color: #B626C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B626C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B626C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.d932e2p125F;
    static { NAMED.put("deep purple magenta", -0x1.d932e2p125F); LIST.add(-0x1.d932e2p125F); }

    /**
     * This color constant "true purple magenta" has RGBA8888 code {@code CD3DDDFF}, L 0.50980395, A 0.6, B 0.42352942, alpha 1.0, hue 0.89608383, saturation 0.6360866, and chroma 0.2507922.
     * It can be represented as a packed float with the constant {@code -0x1.d93304p125F}.
     * <pre>
     * <font style='background-color: #CD3DDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD3DDD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD3DDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD3DDD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD3DDD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD3DDD'>&nbsp;@&nbsp;</font><font style='background-color: #CD3DDD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD3DDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD3DDD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_MAGENTA = -0x1.d93304p125F;
    static { NAMED.put("true purple magenta", -0x1.d93304p125F); LIST.add(-0x1.d93304p125F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code E14EF1FF}, L 0.5686275, A 0.6, B 0.42352942, alpha 1.0, hue 0.89608383, saturation 0.6360866, and chroma 0.2507922.
     * It can be represented as a packed float with the constant {@code -0x1.d93322p125F}.
     * <pre>
     * <font style='background-color: #E14EF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E14EF1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E14EF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E14EF1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E14EF1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E14EF1'>&nbsp;@&nbsp;</font><font style='background-color: #E14EF1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E14EF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E14EF1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.d93322p125F;
    static { NAMED.put("bright purple magenta", -0x1.d93322p125F); LIST.add(-0x1.d93322p125F); }

    /**
     * This color constant "deep pure magenta" has RGBA8888 code {@code B932BAFF}, L 0.4509804, A 0.59607846, B 0.43529412, alpha 1.0, hue 0.90565705, saturation 0.6639715, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.df30e6p125F}.
     * <pre>
     * <font style='background-color: #B932BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B932BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B932BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B932BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B932BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B932BA'>&nbsp;@&nbsp;</font><font style='background-color: #B932BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B932BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B932BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_MAGENTA = -0x1.df30e6p125F;
    static { NAMED.put("deep pure magenta", -0x1.df30e6p125F); LIST.add(-0x1.df30e6p125F); }

    /**
     * This color constant "true pure magenta" has RGBA8888 code {@code CF45CFFF}, L 0.5137255, A 0.59607846, B 0.43529412, alpha 1.0, hue 0.90565705, saturation 0.5385595, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.df3106p125F}.
     * <pre>
     * <font style='background-color: #CF45CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF45CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF45CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CF45CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CF45CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CF45CF'>&nbsp;@&nbsp;</font><font style='background-color: #CF45CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF45CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF45CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_MAGENTA = -0x1.df3106p125F;
    static { NAMED.put("true pure magenta", -0x1.df3106p125F); LIST.add(-0x1.df3106p125F); }

    /**
     * This color constant "bright pure magenta" has RGBA8888 code {@code E457E4FF}, L 0.5764706, A 0.59607846, B 0.43529412, alpha 1.0, hue 0.90565705, saturation 0.47741187, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.df3126p125F}.
     * <pre>
     * <font style='background-color: #E457E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E457E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E457E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E457E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E457E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E457E4'>&nbsp;@&nbsp;</font><font style='background-color: #E457E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E457E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E457E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_MAGENTA = -0x1.df3126p125F;
    static { NAMED.put("bright pure magenta", -0x1.df3126p125F); LIST.add(-0x1.df3126p125F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code BF187DFF}, L 0.4117647, A 0.60784316, B 0.47843137, alpha 1.0, hue 0.96857655, saturation 0.83178, and chroma 0.2190985.
     * It can be represented as a packed float with the constant {@code -0x1.f536d2p125F}.
     * <pre>
     * <font style='background-color: #BF187D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF187D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF187D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF187D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF187D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF187D'>&nbsp;@&nbsp;</font><font style='background-color: #BF187D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF187D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF187D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.f536d2p125F;
    static { NAMED.put("deep red magenta", -0x1.f536d2p125F); LIST.add(-0x1.f536d2p125F); }

    /**
     * This color constant "true red magenta" has RGBA8888 code {@code D42E8EFF}, L 0.46666667, A 0.60784316, B 0.47843137, alpha 1.0, hue 0.96857655, saturation 0.6904801, and chroma 0.2190985.
     * It can be represented as a packed float with the constant {@code -0x1.f536eep125F}.
     * <pre>
     * <font style='background-color: #D42E8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D42E8E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D42E8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D42E8E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D42E8E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D42E8E'>&nbsp;@&nbsp;</font><font style='background-color: #D42E8E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D42E8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D42E8E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_MAGENTA = -0x1.f536eep125F;
    static { NAMED.put("true red magenta", -0x1.f536eep125F); LIST.add(-0x1.f536eep125F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code ED42A2FF}, L 0.53333336, A 0.60784316, B 0.4745098, alpha 1.0, hue 0.96305966, saturation 0.57546955, and chroma 0.22076361.
     * It can be represented as a packed float with the constant {@code -0x1.f3371p125F}.
     * <pre>
     * <font style='background-color: #ED42A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED42A2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED42A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ED42A2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ED42A2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ED42A2'>&nbsp;@&nbsp;</font><font style='background-color: #ED42A2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED42A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED42A2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.f3371p125F;
    static { NAMED.put("bright red magenta", -0x1.f3371p125F); LIST.add(-0x1.f3371p125F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code BB2F53FF}, L 0.40784314, A 0.5882353, B 0.5137255, alpha 1.0, hue 0.024573287, saturation 0.60603404, and chroma 0.17789528.
     * It can be represented as a packed float with the constant {@code -0x1.072cdp126F}.
     * <pre>
     * <font style='background-color: #BB2F53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB2F53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB2F53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB2F53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB2F53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB2F53'>&nbsp;@&nbsp;</font><font style='background-color: #BB2F53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB2F53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB2F53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.072cdp126F;
    static { NAMED.put("deep magenta red", -0x1.072cdp126F); LIST.add(-0x1.072cdp126F); }

    /**
     * This color constant "true magenta red" has RGBA8888 code {@code D14063FF}, L 0.46666667, A 0.5882353, B 0.50980395, alpha 1.0, hue 0.017621128, saturation 0.49275884, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.052ceep126F}.
     * <pre>
     * <font style='background-color: #D14063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D14063; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D14063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D14063'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D14063'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D14063'>&nbsp;@&nbsp;</font><font style='background-color: #D14063; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D14063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D14063; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_RED = -0x1.052ceep126F;
    static { NAMED.put("true magenta red", -0x1.052ceep126F); LIST.add(-0x1.052ceep126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code E44F70FF}, L 0.5176471, A 0.5882353, B 0.5137255, alpha 1.0, hue 0.024573287, saturation 0.46899202, and chroma 0.17789528.
     * It can be represented as a packed float with the constant {@code -0x1.072d08p126F}.
     * <pre>
     * <font style='background-color: #E44F70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E44F70; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E44F70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E44F70'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E44F70'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E44F70'>&nbsp;@&nbsp;</font><font style='background-color: #E44F70; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E44F70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E44F70; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.072d08p126F;
    static { NAMED.put("bright magenta red", -0x1.072d08p126F); LIST.add(-0x1.072d08p126F); }

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
