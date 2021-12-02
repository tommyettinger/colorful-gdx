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
     * This color constant "black red" has RGBA8888 code {@code 340E0CFF}, L 0.14509805, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.08601887, saturation 0.50978756, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090e4ap126F}.
     * <pre>
     * <font style='background-color: #340E0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #340E0C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #340E0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #340E0C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #340E0C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #340E0C'>&nbsp;@&nbsp;</font><font style='background-color: #340E0C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #340E0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #340E0C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_RED = -0x1.090e4ap126F;
    static { NAMED.put("black red", -0x1.090e4ap126F); LIST.add(-0x1.090e4ap126F); }

    /**
     * This color constant "lead red" has RGBA8888 code {@code 78433DFF}, L 0.3372549, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.13424036, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.0910acp126F}.
     * <pre>
     * <font style='background-color: #78433D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78433D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78433D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #78433D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #78433D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #78433D'>&nbsp;@&nbsp;</font><font style='background-color: #78433D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78433D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78433D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_RED = -0x1.0910acp126F;
    static { NAMED.put("lead red", -0x1.0910acp126F); LIST.add(-0x1.0910acp126F); }

    /**
     * This color constant "gray red" has RGBA8888 code {@code B2746CFF}, L 0.5137255, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.08893696, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.091106p126F}.
     * <pre>
     * <font style='background-color: #B2746C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2746C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2746C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2746C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2746C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2746C'>&nbsp;@&nbsp;</font><font style='background-color: #B2746C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2746C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2746C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_RED = -0x1.091106p126F;
    static { NAMED.put("gray red", -0x1.091106p126F); LIST.add(-0x1.091106p126F); }

    /**
     * This color constant "silver red" has RGBA8888 code {@code DD9A90FF}, L 0.654902, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.08601887, saturation 0.19612242, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090f4ep126F}.
     * <pre>
     * <font style='background-color: #DD9A90;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD9A90; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD9A90;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD9A90'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD9A90'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD9A90'>&nbsp;@&nbsp;</font><font style='background-color: #DD9A90; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD9A90;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD9A90; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_RED = -0x1.090f4ep126F;
    static { NAMED.put("silver red", -0x1.090f4ep126F); LIST.add(-0x1.090f4ep126F); }

    /**
     * This color constant "white red" has RGBA8888 code {@code FEB7ADFF}, L 0.77254903, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.6699864, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.09118ap126F}.
     * <pre>
     * <font style='background-color: #FEB7AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEB7AD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEB7AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEB7AD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEB7AD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEB7AD'>&nbsp;@&nbsp;</font><font style='background-color: #FEB7AD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEB7AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEB7AD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_RED = -0x1.09118ap126F;
    static { NAMED.put("white red", -0x1.09118ap126F); LIST.add(-0x1.09118ap126F); }

    /**
     * This color constant "black brown" has RGBA8888 code {@code 2A1911FF}, L 0.14901961, A 0.50980395, B 0.5137255, alpha 1.0, hue 0.15127131, saturation 0.24163266, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.07044cp126F}.
     * <pre>
     * <font style='background-color: #2A1911;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2A1911; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2A1911;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2A1911'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2A1911'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2A1911'>&nbsp;@&nbsp;</font><font style='background-color: #2A1911; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2A1911;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2A1911; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BROWN = -0x1.07044cp126F;
    static { NAMED.put("black brown", -0x1.07044cp126F); LIST.add(-0x1.07044cp126F); }

    /**
     * This color constant "lead brown" has RGBA8888 code {@code 543D33FF}, L 0.28235295, A 0.5137255, B 0.5137255, alpha 1.0, hue 0.125, saturation 0.09278107, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.07069p126F}.
     * <pre>
     * <font style='background-color: #543D33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #543D33; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #543D33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #543D33'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #543D33'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #543D33'>&nbsp;@&nbsp;</font><font style='background-color: #543D33; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #543D33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #543D33; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BROWN = -0x1.07069p126F;
    static { NAMED.put("lead brown", -0x1.07069p126F); LIST.add(-0x1.07069p126F); }

    /**
     * This color constant "gray brown" has RGBA8888 code {@code 8B7064FF}, L 0.45882353, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.024151672, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.0504eap126F}.
     * <pre>
     * <font style='background-color: #8B7064;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B7064; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B7064;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B7064'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B7064'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B7064'>&nbsp;@&nbsp;</font><font style='background-color: #8B7064; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B7064;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B7064; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BROWN = -0x1.0504eap126F;
    static { NAMED.put("gray brown", -0x1.0504eap126F); LIST.add(-0x1.0504eap126F); }

    /**
     * This color constant "silver brown" has RGBA8888 code {@code C7A799FF}, L 0.6627451, A 0.5137255, B 0.5137255, alpha 1.0, hue 0.125, saturation 0.059746988, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.070752p126F}.
     * <pre>
     * <font style='background-color: #C7A799;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7A799; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7A799;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7A799'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7A799'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7A799'>&nbsp;@&nbsp;</font><font style='background-color: #C7A799; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7A799;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7A799; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BROWN = -0x1.070752p126F;
    static { NAMED.put("silver brown", -0x1.070752p126F); LIST.add(-0x1.070752p126F); }

    /**
     * This color constant "white brown" has RGBA8888 code {@code EDCBBDFF}, L 0.80784315, A 0.5137255, B 0.50980395, alpha 1.0, hue 0.09872868, saturation 0.17608567, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.05079cp126F}.
     * <pre>
     * <font style='background-color: #EDCBBD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDCBBD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDCBBD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDCBBD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDCBBD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDCBBD'>&nbsp;@&nbsp;</font><font style='background-color: #EDCBBD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDCBBD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDCBBD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BROWN = -0x1.05079cp126F;
    static { NAMED.put("white brown", -0x1.05079cp126F); LIST.add(-0x1.05079cp126F); }

    /**
     * This color constant "black orange" has RGBA8888 code {@code 31180AFF}, L 0.15686275, A 0.5176471, B 0.52156866, alpha 1.0, hue 0.14085212, saturation 0.5902118, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.0b085p126F}.
     * <pre>
     * <font style='background-color: #31180A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31180A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31180A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #31180A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #31180A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #31180A'>&nbsp;@&nbsp;</font><font style='background-color: #31180A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31180A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31180A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_ORANGE = -0x1.0b085p126F;
    static { NAMED.put("black orange", -0x1.0b085p126F); LIST.add(-0x1.0b085p126F); }

    /**
     * This color constant "lead orange" has RGBA8888 code {@code 674634FF}, L 0.32156864, A 0.5176471, B 0.52156866, alpha 1.0, hue 0.14085212, saturation 0.1912426, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.0b08a4p126F}.
     * <pre>
     * <font style='background-color: #674634;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #674634; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #674634;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #674634'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #674634'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #674634'>&nbsp;@&nbsp;</font><font style='background-color: #674634; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #674634;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #674634; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_ORANGE = -0x1.0b08a4p126F;
    static { NAMED.put("lead orange", -0x1.0b08a4p126F); LIST.add(-0x1.0b08a4p126F); }

    /**
     * This color constant "gray orange" has RGBA8888 code {@code AB836DFF}, L 0.5372549, A 0.5176471, B 0.52156866, alpha 1.0, hue 0.14085212, saturation 0.08952908, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.0b0912p126F}.
     * <pre>
     * <font style='background-color: #AB836D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB836D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB836D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB836D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB836D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB836D'>&nbsp;@&nbsp;</font><font style='background-color: #AB836D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB836D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB836D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_ORANGE = -0x1.0b0912p126F;
    static { NAMED.put("gray orange", -0x1.0b0912p126F); LIST.add(-0x1.0b0912p126F); }

    /**
     * This color constant "silver orange" has RGBA8888 code {@code DBAF98FF}, L 0.7058824, A 0.5176471, B 0.5176471, alpha 1.0, hue 0.125, saturation 0.13610587, and chroma 0.049718447.
     * It can be represented as a packed float with the constant {@code -0x1.090968p126F}.
     * <pre>
     * <font style='background-color: #DBAF98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBAF98; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBAF98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBAF98'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBAF98'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBAF98'>&nbsp;@&nbsp;</font><font style='background-color: #DBAF98; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBAF98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBAF98; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_ORANGE = -0x1.090968p126F;
    static { NAMED.put("silver orange", -0x1.090968p126F); LIST.add(-0x1.090968p126F); }

    /**
     * This color constant "white orange" has RGBA8888 code {@code FED0B7FF}, L 0.8392157, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15979148, saturation 0.4470743, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b07acp126F}.
     * <pre>
     * <font style='background-color: #FED0B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FED0B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FED0B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FED0B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FED0B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FED0B7'>&nbsp;@&nbsp;</font><font style='background-color: #FED0B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FED0B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FED0B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_ORANGE = -0x1.0b07acp126F;
    static { NAMED.put("white orange", -0x1.0b07acp126F); LIST.add(-0x1.0b07acp126F); }

    /**
     * This color constant "black saffron" has RGBA8888 code {@code 2D200DFF}, L 0.16862746, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.235567, saturation 0.44811752, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b0056p126F}.
     * <pre>
     * <font style='background-color: #2D200D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D200D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D200D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D200D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D200D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D200D'>&nbsp;@&nbsp;</font><font style='background-color: #2D200D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D200D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D200D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_SAFFRON = -0x1.0b0056p126F;
    static { NAMED.put("black saffron", -0x1.0b0056p126F); LIST.add(-0x1.0b0056p126F); }

    /**
     * This color constant "lead saffron" has RGBA8888 code {@code 574630FF}, L 0.3019608, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.20763123, saturation 0.18511926, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b029ap126F}.
     * <pre>
     * <font style='background-color: #574630;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #574630; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #574630;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #574630'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #574630'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #574630'>&nbsp;@&nbsp;</font><font style='background-color: #574630; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #574630;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #574630; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_SAFFRON = -0x1.0b029ap126F;
    static { NAMED.put("lead saffron", -0x1.0b029ap126F); LIST.add(-0x1.0b029ap126F); }

    /**
     * This color constant "gray saffron" has RGBA8888 code {@code 89755BFF}, L 0.46666667, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.20763123, saturation 0.10315414, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b02eep126F}.
     * <pre>
     * <font style='background-color: #89755B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #89755B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #89755B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #89755B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #89755B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #89755B'>&nbsp;@&nbsp;</font><font style='background-color: #89755B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #89755B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #89755B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SAFFRON = -0x1.0b02eep126F;
    static { NAMED.put("gray saffron", -0x1.0b02eep126F); LIST.add(-0x1.0b02eep126F); }

    /**
     * This color constant "silver saffron" has RGBA8888 code {@code C5AF92FF}, L 0.6784314, A 0.5019608, B 0.5176471, alpha 1.0, hue 0.23237891, saturation 0.04140891, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.09015ap126F}.
     * <pre>
     * <font style='background-color: #C5AF92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5AF92; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5AF92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C5AF92'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C5AF92'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C5AF92'>&nbsp;@&nbsp;</font><font style='background-color: #C5AF92; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5AF92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5AF92; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_SAFFRON = -0x1.09015ap126F;
    static { NAMED.put("silver saffron", -0x1.09015ap126F); LIST.add(-0x1.09015ap126F); }

    /**
     * This color constant "white saffron" has RGBA8888 code {@code F6DDBEFF}, L 0.8666667, A 0.5058824, B 0.5176471, alpha 1.0, hue 0.19880433, saturation 0.21415824, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.0903bap126F}.
     * <pre>
     * <font style='background-color: #F6DDBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6DDBE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6DDBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6DDBE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6DDBE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6DDBE'>&nbsp;@&nbsp;</font><font style='background-color: #F6DDBE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6DDBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6DDBE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SAFFRON = -0x1.0903bap126F;
    static { NAMED.put("white saffron", -0x1.0903bap126F); LIST.add(-0x1.0903bap126F); }

    /**
     * This color constant "black yellow" has RGBA8888 code {@code 2B2A04FF}, L 0.1882353, A 0.49019608, B 0.5294118, alpha 1.0, hue 0.30119568, saturation 0.6574622, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.0efa6p126F}.
     * <pre>
     * <font style='background-color: #2B2A04;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B2A04; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B2A04;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2B2A04'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2B2A04'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2B2A04'>&nbsp;@&nbsp;</font><font style='background-color: #2B2A04; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B2A04;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B2A04; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_YELLOW = -0x1.0efa6p126F;
    static { NAMED.put("black yellow", -0x1.0efa6p126F); LIST.add(-0x1.0efa6p126F); }

    /**
     * This color constant "lead yellow" has RGBA8888 code {@code 585831FF}, L 0.34509805, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.2761401, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef8bp126F}.
     * <pre>
     * <font style='background-color: #585831;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #585831; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #585831;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #585831'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #585831'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #585831'>&nbsp;@&nbsp;</font><font style='background-color: #585831; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #585831;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #585831; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_YELLOW = -0x1.0ef8bp126F;
    static { NAMED.put("lead yellow", -0x1.0ef8bp126F); LIST.add(-0x1.0ef8bp126F); }

    /**
     * This color constant "gray yellow" has RGBA8888 code {@code 97986AFF}, L 0.5647059, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.14480117, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef92p126F}.
     * <pre>
     * <font style='background-color: #97986A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #97986A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #97986A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #97986A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #97986A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #97986A'>&nbsp;@&nbsp;</font><font style='background-color: #97986A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #97986A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #97986A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_YELLOW = -0x1.0ef92p126F;
    static { NAMED.put("gray yellow", -0x1.0ef92p126F); LIST.add(-0x1.0ef92p126F); }

    /**
     * This color constant "silver yellow" has RGBA8888 code {@code CECF9CFF}, L 0.77254903, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.09572888, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef98ap126F}.
     * <pre>
     * <font style='background-color: #CECF9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CECF9C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CECF9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CECF9C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CECF9C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CECF9C'>&nbsp;@&nbsp;</font><font style='background-color: #CECF9C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CECF9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CECF9C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_YELLOW = -0x1.0ef98ap126F;
    static { NAMED.put("silver yellow", -0x1.0ef98ap126F); LIST.add(-0x1.0ef98ap126F); }

    /**
     * This color constant "white yellow" has RGBA8888 code {@code F2F3BEFF}, L 0.9254902, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.08006428, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef9d8p126F}.
     * <pre>
     * <font style='background-color: #F2F3BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2F3BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2F3BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2F3BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2F3BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2F3BE'>&nbsp;@&nbsp;</font><font style='background-color: #F2F3BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2F3BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2F3BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_YELLOW = -0x1.0ef9d8p126F;
    static { NAMED.put("white yellow", -0x1.0ef9d8p126F); LIST.add(-0x1.0ef9d8p126F); }

    /**
     * This color constant "black lime" has RGBA8888 code {@code 1E2C0CFF}, L 0.18431373, A 0.47843137, B 0.5254902, alpha 1.0, hue 0.361777, saturation 0.5251245, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.0cf45ep126F}.
     * <pre>
     * <font style='background-color: #1E2C0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E2C0C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E2C0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1E2C0C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1E2C0C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1E2C0C'>&nbsp;@&nbsp;</font><font style='background-color: #1E2C0C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E2C0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E2C0C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LIME = -0x1.0cf45ep126F;
    static { NAMED.put("black lime", -0x1.0cf45ep126F); LIST.add(-0x1.0cf45ep126F); }

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
     * This color constant "gray lime" has RGBA8888 code {@code 90A87AFF}, L 0.6039216, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.09234342, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf334p126F}.
     * <pre>
     * <font style='background-color: #90A87A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90A87A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90A87A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #90A87A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #90A87A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #90A87A'>&nbsp;@&nbsp;</font><font style='background-color: #90A87A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90A87A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90A87A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LIME = -0x1.0cf334p126F;
    static { NAMED.put("gray lime", -0x1.0cf334p126F); LIST.add(-0x1.0cf334p126F); }

    /**
     * This color constant "silver lime" has RGBA8888 code {@code BBD5A3FF}, L 0.77254903, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.069975674, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf38ap126F}.
     * <pre>
     * <font style='background-color: #BBD5A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBD5A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBD5A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BBD5A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BBD5A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BBD5A3'>&nbsp;@&nbsp;</font><font style='background-color: #BBD5A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BBD5A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BBD5A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_LIME = -0x1.0cf38ap126F;
    static { NAMED.put("silver lime", -0x1.0cf38ap126F); LIST.add(-0x1.0cf38ap126F); }

    /**
     * This color constant "white lime" has RGBA8888 code {@code DAF5C1FF}, L 0.90588236, A 0.47843137, B 0.5254902, alpha 1.0, hue 0.361777, saturation 0.16055363, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.0cf5cep126F}.
     * <pre>
     * <font style='background-color: #DAF5C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAF5C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAF5C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAF5C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAF5C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAF5C1'>&nbsp;@&nbsp;</font><font style='background-color: #DAF5C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAF5C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAF5C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_LIME = -0x1.0cf5cep126F;
    static { NAMED.put("white lime", -0x1.0cf5cep126F); LIST.add(-0x1.0cf5cep126F); }

    /**
     * This color constant "black green" has RGBA8888 code {@code 0A3012FF}, L 0.18431373, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.9520988, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aec5ep126F}.
     * <pre>
     * <font style='background-color: #0A3012;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A3012; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A3012;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0A3012'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0A3012'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0A3012'>&nbsp;@&nbsp;</font><font style='background-color: #0A3012; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A3012;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A3012; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_GREEN = -0x1.0aec5ep126F;
    static { NAMED.put("black green", -0x1.0aec5ep126F); LIST.add(-0x1.0aec5ep126F); }

    /**
     * This color constant "lead green" has RGBA8888 code {@code 315A36FF}, L 0.32156864, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.40495694, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aeca4p126F}.
     * <pre>
     * <font style='background-color: #315A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #315A36; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #315A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #315A36'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #315A36'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #315A36'>&nbsp;@&nbsp;</font><font style='background-color: #315A36; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #315A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #315A36; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GREEN = -0x1.0aeca4p126F;
    static { NAMED.put("lead green", -0x1.0aeca4p126F); LIST.add(-0x1.0aeca4p126F); }

    /**
     * This color constant "gray green" has RGBA8888 code {@code 618F65FF}, L 0.49803922, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.21362881, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aecfep126F}.
     * <pre>
     * <font style='background-color: #618F65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #618F65; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #618F65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #618F65'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #618F65'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #618F65'>&nbsp;@&nbsp;</font><font style='background-color: #618F65; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #618F65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #618F65; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.0aecfep126F;
    static { NAMED.put("gray green", -0x1.0aecfep126F); LIST.add(-0x1.0aecfep126F); }

    /**
     * This color constant "silver green" has RGBA8888 code {@code 9ACE9EFF}, L 0.72156864, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.12743737, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed7p126F}.
     * <pre>
     * <font style='background-color: #9ACE9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9ACE9E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9ACE9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9ACE9E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9ACE9E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9ACE9E'>&nbsp;@&nbsp;</font><font style='background-color: #9ACE9E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9ACE9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9ACE9E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.0aed7p126F;
    static { NAMED.put("silver green", -0x1.0aed7p126F); LIST.add(-0x1.0aed7p126F); }

    /**
     * This color constant "white green" has RGBA8888 code {@code BFF6C3FF}, L 0.88235295, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.34275556, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aedc2p126F}.
     * <pre>
     * <font style='background-color: #BFF6C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFF6C3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFF6C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFF6C3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFF6C3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFF6C3'>&nbsp;@&nbsp;</font><font style='background-color: #BFF6C3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFF6C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFF6C3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_GREEN = -0x1.0aedc2p126F;
    static { NAMED.put("white green", -0x1.0aedc2p126F); LIST.add(-0x1.0aedc2p126F); }

    /**
     * This color constant "black cyan" has RGBA8888 code {@code 0C2E2EFF}, L 0.1882353, A 0.4745098, B 0.49411765, alpha 1.0, hue 0.5360971, saturation 0.7408949, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.fcf26p125F}.
     * <pre>
     * <font style='background-color: #0C2E2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C2E2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C2E2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0C2E2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0C2E2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0C2E2E'>&nbsp;@&nbsp;</font><font style='background-color: #0C2E2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C2E2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C2E2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_CYAN = -0x1.fcf26p125F;
    static { NAMED.put("black cyan", -0x1.fcf26p125F); LIST.add(-0x1.fcf26p125F); }

    /**
     * This color constant "lead cyan" has RGBA8888 code {@code 3B605FFF}, L 0.3529412, A 0.4745098, B 0.49411765, alpha 1.0, hue 0.5360971, saturation 0.29654312, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.fcf2b4p125F}.
     * <pre>
     * <font style='background-color: #3B605F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B605F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B605F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B605F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B605F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B605F'>&nbsp;@&nbsp;</font><font style='background-color: #3B605F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B605F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B605F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_CYAN = -0x1.fcf2b4p125F;
    static { NAMED.put("lead cyan", -0x1.fcf2b4p125F); LIST.add(-0x1.fcf2b4p125F); }

    /**
     * This color constant "gray cyan" has RGBA8888 code {@code 76A1A0FF}, L 0.5764706, A 0.4745098, B 0.49411765, alpha 1.0, hue 0.5360971, saturation 0.15860994, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.fcf326p125F}.
     * <pre>
     * <font style='background-color: #76A1A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76A1A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76A1A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #76A1A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #76A1A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #76A1A0'>&nbsp;@&nbsp;</font><font style='background-color: #76A1A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76A1A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76A1A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_CYAN = -0x1.fcf326p125F;
    static { NAMED.put("gray cyan", -0x1.fcf326p125F); LIST.add(-0x1.fcf326p125F); }

    /**
     * This color constant "silver cyan" has RGBA8888 code {@code A4D2D2FF}, L 0.7607843, A 0.47843137, B 0.49019608, alpha 1.0, hue 0.56789327, saturation 0.09357475, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.faf584p125F}.
     * <pre>
     * <font style='background-color: #A4D2D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4D2D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4D2D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4D2D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4D2D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4D2D2'>&nbsp;@&nbsp;</font><font style='background-color: #A4D2D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4D2D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4D2D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_CYAN = -0x1.faf584p125F;
    static { NAMED.put("silver cyan", -0x1.faf584p125F); LIST.add(-0x1.faf584p125F); }

    /**
     * This color constant "white cyan" has RGBA8888 code {@code C4F5F4FF}, L 0.9019608, A 0.4745098, B 0.49019608, alpha 1.0, hue 0.55842525, saturation 0.32319868, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.faf3ccp125F}.
     * <pre>
     * <font style='background-color: #C4F5F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4F5F4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4F5F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4F5F4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4F5F4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4F5F4'>&nbsp;@&nbsp;</font><font style='background-color: #C4F5F4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4F5F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4F5F4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_CYAN = -0x1.faf3ccp125F;
    static { NAMED.put("white cyan", -0x1.faf3ccp125F); LIST.add(-0x1.faf3ccp125F); }

    /**
     * This color constant "black blue" has RGBA8888 code {@code 000F34FF}, L 0.10980392, A 0.49411765, B 0.4509804, alpha 1.0, hue 0.73098123, saturation 0.5326612, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.e6fc38p125F}.
     * <pre>
     * <font style='background-color: #000F34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000F34; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000F34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000F34'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000F34'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000F34'>&nbsp;@&nbsp;</font><font style='background-color: #000F34; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000F34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000F34; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_BLUE = -0x1.e6fc38p125F;
    static { NAMED.put("black blue", -0x1.e6fc38p125F); LIST.add(-0x1.e6fc38p125F); }

    /**
     * This color constant "lead blue" has RGBA8888 code {@code 1C305DFF}, L 0.22352941, A 0.49411765, B 0.4509804, alpha 1.0, hue 0.73098123, saturation 0.16762508, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.e6fc72p125F}.
     * <pre>
     * <font style='background-color: #1C305D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C305D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C305D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C305D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C305D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C305D'>&nbsp;@&nbsp;</font><font style='background-color: #1C305D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C305D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C305D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLUE = -0x1.e6fc72p125F;
    static { NAMED.put("lead blue", -0x1.e6fc72p125F); LIST.add(-0x1.e6fc72p125F); }

    /**
     * This color constant "gray blue" has RGBA8888 code {@code 3F598EFF}, L 0.36078432, A 0.49019608, B 0.4509804, alpha 1.0, hue 0.71857655, saturation 0.18993352, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e6fab8p125F}.
     * <pre>
     * <font style='background-color: #3F598E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F598E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F598E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F598E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F598E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F598E'>&nbsp;@&nbsp;</font><font style='background-color: #3F598E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F598E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F598E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_BLUE = -0x1.e6fab8p125F;
    static { NAMED.put("gray blue", -0x1.e6fab8p125F); LIST.add(-0x1.e6fab8p125F); }

    /**
     * This color constant "silver blue" has RGBA8888 code {@code 6D8BC7FF}, L 0.53333336, A 0.49411765, B 0.4509804, alpha 1.0, hue 0.73098123, saturation 0.26952916, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.e6fd1p125F}.
     * <pre>
     * <font style='background-color: #6D8BC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D8BC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D8BC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D8BC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D8BC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D8BC7'>&nbsp;@&nbsp;</font><font style='background-color: #6D8BC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D8BC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D8BC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_BLUE = -0x1.e6fd1p125F;
    static { NAMED.put("silver blue", -0x1.e6fd1p125F); LIST.add(-0x1.e6fd1p125F); }

    /**
     * This color constant "white blue" has RGBA8888 code {@code 99BBFDFF}, L 0.70980394, A 0.49019608, B 0.4509804, alpha 1.0, hue 0.71857655, saturation 0.80024624, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e6fb6ap125F}.
     * <pre>
     * <font style='background-color: #99BBFD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99BBFD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99BBFD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #99BBFD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #99BBFD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #99BBFD'>&nbsp;@&nbsp;</font><font style='background-color: #99BBFD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99BBFD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99BBFD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_BLUE = -0x1.e6fb6ap125F;
    static { NAMED.put("white blue", -0x1.e6fb6ap125F); LIST.add(-0x1.e6fb6ap125F); }

    /**
     * This color constant "black violet" has RGBA8888 code {@code 1C1031FF}, L 0.12941177, A 0.5137255, B 0.4627451, alpha 1.0, hue 0.80616736, saturation 0.38816568, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.ed0642p125F}.
     * <pre>
     * <font style='background-color: #1C1031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C1031; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C1031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C1031'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C1031'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C1031'>&nbsp;@&nbsp;</font><font style='background-color: #1C1031; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C1031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C1031; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_VIOLET = -0x1.ed0642p125F;
    static { NAMED.put("black violet", -0x1.ed0642p125F); LIST.add(-0x1.ed0642p125F); }

    /**
     * This color constant "lead violet" has RGBA8888 code {@code 40335BFF}, L 0.25490198, A 0.5176471, B 0.46666667, alpha 1.0, hue 0.8274942, saturation 0.12456864, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef0882p125F}.
     * <pre>
     * <font style='background-color: #40335B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40335B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40335B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #40335B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #40335B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #40335B'>&nbsp;@&nbsp;</font><font style='background-color: #40335B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40335B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40335B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_VIOLET = -0x1.ef0882p125F;
    static { NAMED.put("lead violet", -0x1.ef0882p125F); LIST.add(-0x1.ef0882p125F); }

    /**
     * This color constant "gray violet" has RGBA8888 code {@code 746596FF}, L 0.43137255, A 0.5176471, B 0.46666667, alpha 1.0, hue 0.8274942, saturation 0.064909436, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef08dcp125F}.
     * <pre>
     * <font style='background-color: #746596;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #746596; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #746596;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #746596'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #746596'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #746596'>&nbsp;@&nbsp;</font><font style='background-color: #746596; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #746596;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #746596; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_VIOLET = -0x1.ef08dcp125F;
    static { NAMED.put("gray violet", -0x1.ef08dcp125F); LIST.add(-0x1.ef08dcp125F); }

    /**
     * This color constant "silver violet" has RGBA8888 code {@code AA9AD2FF}, L 0.62352943, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.23358436, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed093ep125F}.
     * <pre>
     * <font style='background-color: #AA9AD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA9AD2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA9AD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA9AD2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA9AD2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA9AD2'>&nbsp;@&nbsp;</font><font style='background-color: #AA9AD2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA9AD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA9AD2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_VIOLET = -0x1.ed093ep125F;
    static { NAMED.put("silver violet", -0x1.ed093ep125F); LIST.add(-0x1.ed093ep125F); }

    /**
     * This color constant "white violet" has RGBA8888 code {@code CDBCF7FF}, L 0.75686276, A 0.5176471, B 0.46666667, alpha 1.0, hue 0.8274942, saturation 0.5268779, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.ef0982p125F}.
     * <pre>
     * <font style='background-color: #CDBCF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDBCF7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDBCF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CDBCF7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CDBCF7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CDBCF7'>&nbsp;@&nbsp;</font><font style='background-color: #CDBCF7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDBCF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDBCF7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_VIOLET = -0x1.ef0982p125F;
    static { NAMED.put("white violet", -0x1.ef0982p125F); LIST.add(-0x1.ef0982p125F); }

    /**
     * This color constant "black purple" has RGBA8888 code {@code 260E34FF}, L 0.14117648, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.59057695, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0e48p125F}.
     * <pre>
     * <font style='background-color: #260E34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #260E34; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #260E34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #260E34'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #260E34'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #260E34'>&nbsp;@&nbsp;</font><font style='background-color: #260E34; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #260E34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #260E34; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_PURPLE = -0x1.ed0e48p125F;
    static { NAMED.put("black purple", -0x1.ed0e48p125F); LIST.add(-0x1.ed0e48p125F); }

    /**
     * This color constant "lead purple" has RGBA8888 code {@code 5A3D6EFF}, L 0.30980393, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.16552503, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0e9ep125F}.
     * <pre>
     * <font style='background-color: #5A3D6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A3D6E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A3D6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A3D6E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A3D6E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A3D6E'>&nbsp;@&nbsp;</font><font style='background-color: #5A3D6E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A3D6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A3D6E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_PURPLE = -0x1.ed0e9ep125F;
    static { NAMED.put("lead purple", -0x1.ed0e9ep125F); LIST.add(-0x1.ed0e9ep125F); }

    /**
     * This color constant "gray purple" has RGBA8888 code {@code 9775AFFF}, L 0.50980395, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.11462663, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0f04p125F}.
     * <pre>
     * <font style='background-color: #9775AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9775AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9775AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9775AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9775AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9775AF'>&nbsp;@&nbsp;</font><font style='background-color: #9775AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9775AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9775AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_PURPLE = -0x1.ed0f04p125F;
    static { NAMED.put("gray purple", -0x1.ed0f04p125F); LIST.add(-0x1.ed0f04p125F); }

    /**
     * This color constant "silver purple" has RGBA8888 code {@code C49FDDFF}, L 0.6666667, A 0.5254902, B 0.46666667, alpha 1.0, hue 0.85391617, saturation 0.253564, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.ef0d54p125F}.
     * <pre>
     * <font style='background-color: #C49FDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C49FDD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C49FDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C49FDD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C49FDD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C49FDD'>&nbsp;@&nbsp;</font><font style='background-color: #C49FDD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C49FDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C49FDD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PURPLE = -0x1.ef0d54p125F;
    static { NAMED.put("silver purple", -0x1.ef0d54p125F); LIST.add(-0x1.ef0d54p125F); }

    /**
     * This color constant "white purple" has RGBA8888 code {@code E5BEFFFF}, L 0.7921569, A 0.5254902, B 0.46666667, alpha 1.0, hue 0.85391617, saturation 0.7043445, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.ef0d94p125F}.
     * <pre>
     * <font style='background-color: #E5BEFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5BEFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5BEFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5BEFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5BEFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5BEFF'>&nbsp;@&nbsp;</font><font style='background-color: #E5BEFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5BEFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5BEFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_PURPLE = -0x1.ef0d94p125F;
    static { NAMED.put("white purple", -0x1.ef0d94p125F); LIST.add(-0x1.ef0d94p125F); }

    /**
     * This color constant "black magenta" has RGBA8888 code {@code 2D102DFF}, L 0.14901961, A 0.53333336, B 0.47843137, alpha 1.0, hue 0.90858525, saturation 0.44074175, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f5104cp125F}.
     * <pre>
     * <font style='background-color: #2D102D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D102D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D102D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D102D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D102D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D102D'>&nbsp;@&nbsp;</font><font style='background-color: #2D102D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D102D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D102D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_MAGENTA = -0x1.f5104cp125F;
    static { NAMED.put("black magenta", -0x1.f5104cp125F); LIST.add(-0x1.f5104cp125F); }

    /**
     * This color constant "lead magenta" has RGBA8888 code {@code 573357FF}, L 0.2784314, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.17268357, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f3108ep125F}.
     * <pre>
     * <font style='background-color: #573357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573357; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #573357'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #573357'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #573357'>&nbsp;@&nbsp;</font><font style='background-color: #573357; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573357; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_MAGENTA = -0x1.f3108ep125F;
    static { NAMED.put("lead magenta", -0x1.f3108ep125F); LIST.add(-0x1.f3108ep125F); }

    /**
     * This color constant "gray magenta" has RGBA8888 code {@code 8B608AFF}, L 0.4392157, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.08713436, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f310ep125F}.
     * <pre>
     * <font style='background-color: #8B608A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B608A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B608A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B608A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B608A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B608A'>&nbsp;@&nbsp;</font><font style='background-color: #8B608A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B608A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B608A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_MAGENTA = -0x1.f310ep125F;
    static { NAMED.put("gray magenta", -0x1.f310ep125F); LIST.add(-0x1.f310ep125F); }

    /**
     * This color constant "silver magenta" has RGBA8888 code {@code C797C6FF}, L 0.6431373, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.11358423, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f31148p125F}.
     * <pre>
     * <font style='background-color: #C797C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C797C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C797C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C797C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C797C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C797C6'>&nbsp;@&nbsp;</font><font style='background-color: #C797C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C797C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C797C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_MAGENTA = -0x1.f31148p125F;
    static { NAMED.put("silver magenta", -0x1.f31148p125F); LIST.add(-0x1.f31148p125F); }

    /**
     * This color constant "white magenta" has RGBA8888 code {@code F5C1F3FF}, L 0.8117647, A 0.53333336, B 0.47843137, alpha 1.0, hue 0.90858525, saturation 0.41320232, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f5119ep125F}.
     * <pre>
     * <font style='background-color: #F5C1F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5C1F3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5C1F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5C1F3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5C1F3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5C1F3'>&nbsp;@&nbsp;</font><font style='background-color: #F5C1F3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5C1F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5C1F3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_MAGENTA = -0x1.f5119ep125F;
    static { NAMED.put("white magenta", -0x1.f5119ep125F); LIST.add(-0x1.f5119ep125F); }

    /**
     * This color constant "drab red" has RGBA8888 code {@code 791B16FF}, L 0.27450982, A 0.56078434, B 0.53333336, alpha 1.0, hue 0.079836555, saturation 0.63123345, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.111e8cp126F}.
     * <pre>
     * <font style='background-color: #791B16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #791B16; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #791B16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #791B16'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #791B16'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #791B16'>&nbsp;@&nbsp;</font><font style='background-color: #791B16; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #791B16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #791B16; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED = -0x1.111e8cp126F;
    static { NAMED.put("drab red", -0x1.111e8cp126F); LIST.add(-0x1.111e8cp126F); }

    /**
     * This color constant "faded red" has RGBA8888 code {@code C6584BFF}, L 0.47843137, A 0.56078434, B 0.53333336, alpha 1.0, hue 0.079836555, saturation 0.2743484, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.111ef4p126F}.
     * <pre>
     * <font style='background-color: #C6584B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6584B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6584B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6584B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6584B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6584B'>&nbsp;@&nbsp;</font><font style='background-color: #C6584B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6584B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6584B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_RED = -0x1.111ef4p126F;
    static { NAMED.put("faded red", -0x1.111ef4p126F); LIST.add(-0x1.111ef4p126F); }

    /**
     * This color constant "pale red" has RGBA8888 code {@code F77E6FFF}, L 0.62352943, A 0.56078434, B 0.53333336, alpha 1.0, hue 0.079836555, saturation 0.6605892, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.111f3ep126F}.
     * <pre>
     * <font style='background-color: #F77E6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F77E6F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F77E6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F77E6F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F77E6F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F77E6F'>&nbsp;@&nbsp;</font><font style='background-color: #F77E6F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F77E6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F77E6F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED = -0x1.111f3ep126F;
    static { NAMED.put("pale red", -0x1.111f3ep126F); LIST.add(-0x1.111f3ep126F); }

    /**
     * This color constant "drab brown" has RGBA8888 code {@code 633826FF}, L 0.28627452, A 0.5254902, B 0.5254902, alpha 1.0, hue 0.125, saturation 0.32, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0d0c92p126F}.
     * <pre>
     * <font style='background-color: #633826;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #633826; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #633826;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #633826'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #633826'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #633826'>&nbsp;@&nbsp;</font><font style='background-color: #633826; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #633826;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #633826; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN = -0x1.0d0c92p126F;
    static { NAMED.put("drab brown", -0x1.0d0c92p126F); LIST.add(-0x1.0d0c92p126F); }

    /**
     * This color constant "faded brown" has RGBA8888 code {@code A06C56FF}, L 0.47058824, A 0.5254902, B 0.5254902, alpha 1.0, hue 0.125, saturation 0.15631865, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0d0cfp126F}.
     * <pre>
     * <font style='background-color: #A06C56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A06C56; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A06C56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A06C56'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A06C56'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A06C56'>&nbsp;@&nbsp;</font><font style='background-color: #A06C56; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A06C56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A06C56; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BROWN = -0x1.0d0cfp126F;
    static { NAMED.put("faded brown", -0x1.0d0cfp126F); LIST.add(-0x1.0d0cfp126F); }

    /**
     * This color constant "pale brown" has RGBA8888 code {@code DFA48AFF}, L 0.6784314, A 0.5254902, B 0.5254902, alpha 1.0, hue 0.125, saturation 0.22803171, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0d0d5ap126F}.
     * <pre>
     * <font style='background-color: #DFA48A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFA48A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFA48A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DFA48A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DFA48A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DFA48A'>&nbsp;@&nbsp;</font><font style='background-color: #DFA48A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFA48A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFA48A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN = -0x1.0d0d5ap126F;
    static { NAMED.put("pale brown", -0x1.0d0d5ap126F); LIST.add(-0x1.0d0d5ap126F); }

    /**
     * This color constant "drab orange" has RGBA8888 code {@code 733B12FF}, L 0.30980393, A 0.5254902, B 0.5411765, alpha 1.0, hue 0.16176948, saturation 0.700948, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.150c9ep126F}.
     * <pre>
     * <font style='background-color: #733B12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #733B12; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #733B12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #733B12'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #733B12'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #733B12'>&nbsp;@&nbsp;</font><font style='background-color: #733B12; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #733B12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #733B12; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE = -0x1.150c9ep126F;
    static { NAMED.put("drab orange", -0x1.150c9ep126F); LIST.add(-0x1.150c9ep126F); }

    /**
     * This color constant "faded orange" has RGBA8888 code {@code C17C50FF}, L 0.5411765, A 0.5294118, B 0.5411765, alpha 1.0, hue 0.15127131, saturation 0.32170027, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.150f14p126F}.
     * <pre>
     * <font style='background-color: #C17C50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C17C50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C17C50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C17C50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C17C50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C17C50'>&nbsp;@&nbsp;</font><font style='background-color: #C17C50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C17C50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C17C50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_ORANGE = -0x1.150f14p126F;
    static { NAMED.put("faded orange", -0x1.150f14p126F); LIST.add(-0x1.150f14p126F); }

    /**
     * This color constant "pale orange" has RGBA8888 code {@code F7AB7BFF}, L 0.72156864, A 0.5294118, B 0.5411765, alpha 1.0, hue 0.15127131, saturation 0.5595463, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.150f7p126F}.
     * <pre>
     * <font style='background-color: #F7AB7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7AB7B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7AB7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7AB7B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7AB7B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7AB7B'>&nbsp;@&nbsp;</font><font style='background-color: #F7AB7B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7AB7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7AB7B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE = -0x1.150f7p126F;
    static { NAMED.put("pale orange", -0x1.150f7p126F); LIST.add(-0x1.150f7p126F); }

    /**
     * This color constant "drab saffron" has RGBA8888 code {@code 6E4E1CFF}, L 0.34117648, A 0.5058824, B 0.5411765, alpha 1.0, hue 0.22740345, saturation 0.59504133, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.1502aep126F}.
     * <pre>
     * <font style='background-color: #6E4E1C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E4E1C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E4E1C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E4E1C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E4E1C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E4E1C'>&nbsp;@&nbsp;</font><font style='background-color: #6E4E1C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E4E1C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E4E1C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON = -0x1.1502aep126F;
    static { NAMED.put("drab saffron", -0x1.1502aep126F); LIST.add(-0x1.1502aep126F); }

    /**
     * This color constant "faded saffron" has RGBA8888 code {@code A7814CFF}, L 0.52156866, A 0.50980395, B 0.5411765, alpha 1.0, hue 0.21279909, saturation 0.33137777, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.15050ap126F}.
     * <pre>
     * <font style='background-color: #A7814C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7814C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7814C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A7814C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A7814C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A7814C'>&nbsp;@&nbsp;</font><font style='background-color: #A7814C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7814C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7814C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_SAFFRON = -0x1.15050ap126F;
    static { NAMED.put("faded saffron", -0x1.15050ap126F); LIST.add(-0x1.15050ap126F); }

    /**
     * This color constant "pale saffron" has RGBA8888 code {@code EEC387FF}, L 0.77254903, A 0.50980395, B 0.5411765, alpha 1.0, hue 0.21279909, saturation 0.3143869, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.15058ap126F}.
     * <pre>
     * <font style='background-color: #EEC387;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEC387; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEC387;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EEC387'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EEC387'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EEC387'>&nbsp;@&nbsp;</font><font style='background-color: #EEC387; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEC387;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEC387; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON = -0x1.15058ap126F;
    static { NAMED.put("pale saffron", -0x1.15058ap126F); LIST.add(-0x1.15058ap126F); }

    /**
     * This color constant "drab yellow" has RGBA8888 code {@code 676B12FF}, L 0.4, A 0.4745098, B 0.5529412, alpha 1.0, hue 0.3214129, saturation 0.712557, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1af2ccp126F}.
     * <pre>
     * <font style='background-color: #676B12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #676B12; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #676B12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #676B12'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #676B12'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #676B12'>&nbsp;@&nbsp;</font><font style='background-color: #676B12; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #676B12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #676B12; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW = -0x1.1af2ccp126F;
    static { NAMED.put("drab yellow", -0x1.1af2ccp126F); LIST.add(-0x1.1af2ccp126F); }

    /**
     * This color constant "faded yellow" has RGBA8888 code {@code ACB257FF}, L 0.64705884, A 0.47843137, B 0.5529412, alpha 1.0, hue 0.3115622, saturation 0.39310902, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1af54ap126F}.
     * <pre>
     * <font style='background-color: #ACB257;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACB257; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACB257;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ACB257'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ACB257'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ACB257'>&nbsp;@&nbsp;</font><font style='background-color: #ACB257; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACB257;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACB257; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_YELLOW = -0x1.1af54ap126F;
    static { NAMED.put("faded yellow", -0x1.1af54ap126F); LIST.add(-0x1.1af54ap126F); }

    /**
     * This color constant "pale yellow" has RGBA8888 code {@code E7EF8CFF}, L 0.8862745, A 0.4745098, B 0.5529412, alpha 1.0, hue 0.3214129, saturation 0.26240048, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.1af3c4p126F}.
     * <pre>
     * <font style='background-color: #E7EF8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7EF8C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7EF8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7EF8C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7EF8C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7EF8C'>&nbsp;@&nbsp;</font><font style='background-color: #E7EF8C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7EF8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7EF8C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.1af3c4p126F;
    static { NAMED.put("pale yellow", -0x1.1af3c4p126F); LIST.add(-0x1.1af3c4p126F); }

    /**
     * This color constant "drab lime" has RGBA8888 code {@code 4C6D22FF}, L 0.38431373, A 0.4627451, B 0.54509807, alpha 1.0, hue 0.35989827, saturation 0.5426002, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.16ecc4p126F}.
     * <pre>
     * <font style='background-color: #4C6D22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C6D22; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C6D22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4C6D22'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4C6D22'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4C6D22'>&nbsp;@&nbsp;</font><font style='background-color: #4C6D22; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C6D22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C6D22; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME = -0x1.16ecc4p126F;
    static { NAMED.put("drab lime", -0x1.16ecc4p126F); LIST.add(-0x1.16ecc4p126F); }

    /**
     * This color constant "faded lime" has RGBA8888 code {@code 93BC67FF}, L 0.654902, A 0.45882353, B 0.54509807, alpha 1.0, hue 0.36777616, saturation 0.26500922, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.16eb4ep126F}.
     * <pre>
     * <font style='background-color: #93BC67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93BC67; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93BC67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93BC67'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93BC67'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93BC67'>&nbsp;@&nbsp;</font><font style='background-color: #93BC67; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93BC67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93BC67; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_LIME = -0x1.16eb4ep126F;
    static { NAMED.put("faded lime", -0x1.16eb4ep126F); LIST.add(-0x1.16eb4ep126F); }

    /**
     * This color constant "pale lime" has RGBA8888 code {@code C2EF94FF}, L 0.8509804, A 0.45882353, B 0.54509807, alpha 1.0, hue 0.36777616, saturation 0.21934536, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.16ebb2p126F}.
     * <pre>
     * <font style='background-color: #C2EF94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2EF94; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2EF94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C2EF94'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C2EF94'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C2EF94'>&nbsp;@&nbsp;</font><font style='background-color: #C2EF94; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2EF94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2EF94; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME = -0x1.16ebb2p126F;
    static { NAMED.put("pale lime", -0x1.16ebb2p126F); LIST.add(-0x1.16ebb2p126F); }

    /**
     * This color constant "drab green" has RGBA8888 code {@code 187328FF}, L 0.3764706, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.40979147, saturation 0.8883728, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14decp126F}.
     * <pre>
     * <font style='background-color: #187328;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #187328; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #187328;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #187328'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #187328'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #187328'>&nbsp;@&nbsp;</font><font style='background-color: #187328; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #187328;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #187328; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.14decp126F;
    static { NAMED.put("drab green", -0x1.14decp126F); LIST.add(-0x1.14decp126F); }

    /**
     * This color constant "faded green" has RGBA8888 code {@code 51AE5AFF}, L 0.5686275, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.40979147, saturation 0.47928578, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df22p126F}.
     * <pre>
     * <font style='background-color: #51AE5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51AE5A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51AE5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #51AE5A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #51AE5A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #51AE5A'>&nbsp;@&nbsp;</font><font style='background-color: #51AE5A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51AE5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51AE5A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_GREEN = -0x1.14df22p126F;
    static { NAMED.put("faded green", -0x1.14df22p126F); LIST.add(-0x1.14df22p126F); }

    /**
     * This color constant "pale green" has RGBA8888 code {@code 8CF093FF}, L 0.80784315, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.40979147, saturation 0.33580247, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df9cp126F}.
     * <pre>
     * <font style='background-color: #8CF093;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8CF093; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8CF093;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8CF093'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8CF093'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8CF093'>&nbsp;@&nbsp;</font><font style='background-color: #8CF093; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8CF093;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8CF093; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.14df9cp126F;
    static { NAMED.put("pale green", -0x1.14df9cp126F); LIST.add(-0x1.14df9cp126F); }

    /**
     * This color constant "drab cyan" has RGBA8888 code {@code 236E6EFF}, L 0.38431373, A 0.4627451, B 0.4862745, alpha 1.0, hue 0.55616736, saturation 0.6305267, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f8ecc4p125F}.
     * <pre>
     * <font style='background-color: #236E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #236E6E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #236E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #236E6E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #236E6E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #236E6E'>&nbsp;@&nbsp;</font><font style='background-color: #236E6E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #236E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #236E6E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN = -0x1.f8ecc4p125F;
    static { NAMED.put("drab cyan", -0x1.f8ecc4p125F); LIST.add(-0x1.f8ecc4p125F); }

    /**
     * This color constant "faded cyan" has RGBA8888 code {@code 68B8B7FF}, L 0.63529414, A 0.45882353, B 0.4862745, alpha 1.0, hue 0.5511957, saturation 0.38881174, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.f8eb44p125F}.
     * <pre>
     * <font style='background-color: #68B8B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68B8B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68B8B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68B8B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68B8B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68B8B7'>&nbsp;@&nbsp;</font><font style='background-color: #68B8B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68B8B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68B8B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_CYAN = -0x1.f8eb44p125F;
    static { NAMED.put("faded cyan", -0x1.f8eb44p125F); LIST.add(-0x1.f8eb44p125F); }

    /**
     * This color constant "pale cyan" has RGBA8888 code {@code 99EEEDFF}, L 0.84313726, A 0.4627451, B 0.4862745, alpha 1.0, hue 0.55616736, saturation 0.30775005, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f8edaep125F}.
     * <pre>
     * <font style='background-color: #99EEED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99EEED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99EEED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #99EEED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #99EEED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #99EEED'>&nbsp;@&nbsp;</font><font style='background-color: #99EEED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99EEED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99EEED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN = -0x1.f8edaep125F;
    static { NAMED.put("pale cyan", -0x1.f8edaep125F); LIST.add(-0x1.f8edaep125F); }

    /**
     * This color constant "drab blue" has RGBA8888 code {@code 001A71FF}, L 0.1882353, A 0.49019608, B 0.41568628, alpha 1.0, hue 0.7315659, saturation 0.65472966, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.d4fa6p125F}.
     * <pre>
     * <font style='background-color: #001A71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #001A71; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #001A71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #001A71'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #001A71'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #001A71'>&nbsp;@&nbsp;</font><font style='background-color: #001A71; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #001A71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #001A71; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE = -0x1.d4fa6p125F;
    static { NAMED.put("drab blue", -0x1.d4fa6p125F); LIST.add(-0x1.d4fa6p125F); }

    /**
     * This color constant "faded blue" has RGBA8888 code {@code 2248A9FF}, L 0.3254902, A 0.49019608, B 0.41568628, alpha 1.0, hue 0.7315659, saturation 0.27533516, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.d4faa6p125F}.
     * <pre>
     * <font style='background-color: #2248A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2248A9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2248A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2248A9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2248A9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2248A9'>&nbsp;@&nbsp;</font><font style='background-color: #2248A9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2248A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2248A9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.d4faa6p125F;
    static { NAMED.put("faded blue", -0x1.d4faa6p125F); LIST.add(-0x1.d4faa6p125F); }

    /**
     * This color constant "pale blue" has RGBA8888 code {@code 5183F1FF}, L 0.5176471, A 0.4862745, B 0.41568628, alpha 1.0, hue 0.72430414, saturation 0.7156188, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.d4f908p125F}.
     * <pre>
     * <font style='background-color: #5183F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5183F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5183F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5183F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5183F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5183F1'>&nbsp;@&nbsp;</font><font style='background-color: #5183F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5183F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5183F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.d4f908p125F;
    static { NAMED.put("pale blue", -0x1.d4f908p125F); LIST.add(-0x1.d4f908p125F); }

    /**
     * This color constant "drab violet" has RGBA8888 code {@code 431E6EFF}, L 0.23529412, A 0.5372549, B 0.43529412, alpha 1.0, hue 0.8331495, saturation 0.5685717, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.df1278p125F}.
     * <pre>
     * <font style='background-color: #431E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #431E6E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #431E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #431E6E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #431E6E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #431E6E'>&nbsp;@&nbsp;</font><font style='background-color: #431E6E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #431E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #431E6E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET = -0x1.df1278p125F;
    static { NAMED.put("drab violet", -0x1.df1278p125F); LIST.add(-0x1.df1278p125F); }

    /**
     * This color constant "faded violet" has RGBA8888 code {@code 7751AEFF}, L 0.40784314, A 0.5372549, B 0.43529412, alpha 1.0, hue 0.8331495, saturation 0.24141519, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.df12dp125F}.
     * <pre>
     * <font style='background-color: #7751AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7751AE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7751AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7751AE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7751AE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7751AE'>&nbsp;@&nbsp;</font><font style='background-color: #7751AE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7751AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7751AE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_VIOLET = -0x1.df12dp125F;
    static { NAMED.put("faded violet", -0x1.df12dp125F); LIST.add(-0x1.df12dp125F); }

    /**
     * This color constant "pale violet" has RGBA8888 code {@code AE85EEFF}, L 0.59607846, A 0.5372549, B 0.43529412, alpha 1.0, hue 0.8331495, saturation 0.5917764, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.df133p125F}.
     * <pre>
     * <font style='background-color: #AE85EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE85EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE85EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE85EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE85EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE85EE'>&nbsp;@&nbsp;</font><font style='background-color: #AE85EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE85EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE85EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.df133p125F;
    static { NAMED.put("pale violet", -0x1.df133p125F); LIST.add(-0x1.df133p125F); }

    /**
     * This color constant "drab purple" has RGBA8888 code {@code 5A1A74FF}, L 0.2627451, A 0.5568628, B 0.4392157, alpha 1.0, hue 0.8697009, saturation 0.67942315, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.e11c86p125F}.
     * <pre>
     * <font style='background-color: #5A1A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A1A74; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A1A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A1A74'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A1A74'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A1A74'>&nbsp;@&nbsp;</font><font style='background-color: #5A1A74; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A1A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A1A74; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE = -0x1.e11c86p125F;
    static { NAMED.put("drab purple", -0x1.e11c86p125F); LIST.add(-0x1.e11c86p125F); }

    /**
     * This color constant "faded purple" has RGBA8888 code {@code A15AC1FF}, L 0.47843137, A 0.5568628, B 0.4392157, alpha 1.0, hue 0.8697009, saturation 0.2780757, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.e11cf4p125F}.
     * <pre>
     * <font style='background-color: #A15AC1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A15AC1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A15AC1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A15AC1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A15AC1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A15AC1'>&nbsp;@&nbsp;</font><font style='background-color: #A15AC1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A15AC1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A15AC1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_PURPLE = -0x1.e11cf4p125F;
    static { NAMED.put("faded purple", -0x1.e11cf4p125F); LIST.add(-0x1.e11cf4p125F); }

    /**
     * This color constant "pale purple" has RGBA8888 code {@code D386F6FF}, L 0.6431373, A 0.5568628, B 0.4392157, alpha 1.0, hue 0.8697009, saturation 0.7065974, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.e11d48p125F}.
     * <pre>
     * <font style='background-color: #D386F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D386F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D386F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D386F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D386F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D386F6'>&nbsp;@&nbsp;</font><font style='background-color: #D386F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D386F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D386F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.e11d48p125F;
    static { NAMED.put("pale purple", -0x1.e11d48p125F); LIST.add(-0x1.e11d48p125F); }

    /**
     * This color constant "drab magenta" has RGBA8888 code {@code 6D216DFF}, L 0.2901961, A 0.5647059, B 0.45882353, alpha 1.0, hue 0.90979147, saturation 0.5768687, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.eb2094p125F}.
     * <pre>
     * <font style='background-color: #6D216D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D216D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D216D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D216D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D216D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D216D'>&nbsp;@&nbsp;</font><font style='background-color: #6D216D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D216D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D216D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA = -0x1.eb2094p125F;
    static { NAMED.put("drab magenta", -0x1.eb2094p125F); LIST.add(-0x1.eb2094p125F); }

    /**
     * This color constant "faded magenta" has RGBA8888 code {@code A651A5FF}, L 0.45490196, A 0.5647059, B 0.45882353, alpha 1.0, hue 0.90979147, saturation 0.29108205, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.eb20e8p125F}.
     * <pre>
     * <font style='background-color: #A651A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A651A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A651A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A651A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A651A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A651A5'>&nbsp;@&nbsp;</font><font style='background-color: #A651A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A651A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A651A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_MAGENTA = -0x1.eb20e8p125F;
    static { NAMED.put("faded magenta", -0x1.eb20e8p125F); LIST.add(-0x1.eb20e8p125F); }

    /**
     * This color constant "pale magenta" has RGBA8888 code {@code EA8AE9FF}, L 0.6745098, A 0.5647059, B 0.45490196, alpha 1.0, hue 0.90311134, saturation 0.45702988, and chroma 0.15712644.
     * It can be represented as a packed float with the constant {@code -0x1.e92158p125F}.
     * <pre>
     * <font style='background-color: #EA8AE9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA8AE9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA8AE9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EA8AE9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EA8AE9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EA8AE9'>&nbsp;@&nbsp;</font><font style='background-color: #EA8AE9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA8AE9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA8AE9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA = -0x1.e92158p125F;
    static { NAMED.put("pale magenta", -0x1.e92158p125F); LIST.add(-0x1.e92158p125F); }

    /**
     * This color constant "deep pure red" has RGBA8888 code {@code B7110EFF}, L 0.36862746, A 0.5882353, B 0.54901963, alpha 1.0, hue 0.080711745, saturation 0.8603198, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.192cbcp126F}.
     * <pre>
     * <font style='background-color: #B7110E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7110E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7110E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B7110E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B7110E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B7110E'>&nbsp;@&nbsp;</font><font style='background-color: #B7110E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7110E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7110E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_RED = -0x1.192cbcp126F;
    static { NAMED.put("deep pure red", -0x1.192cbcp126F); LIST.add(-0x1.192cbcp126F); }

    /**
     * This color constant "true pure red" has RGBA8888 code {@code E0392EFF}, L 0.47058824, A 0.5882353, B 0.54901963, alpha 1.0, hue 0.080711745, saturation 0.59924245, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.192cfp126F}.
     * <pre>
     * <font style='background-color: #E0392E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0392E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0392E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0392E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0392E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0392E'>&nbsp;@&nbsp;</font><font style='background-color: #E0392E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0392E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0392E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_RED = -0x1.192cfp126F;
    static { NAMED.put("true pure red", -0x1.192cfp126F); LIST.add(-0x1.192cfp126F); }

    /**
     * This color constant "bright pure red" has RGBA8888 code {@code FC5041FF}, L 0.54509807, A 0.5882353, B 0.54901963, alpha 1.0, hue 0.080711745, saturation 0.7743444, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.192d16p126F}.
     * <pre>
     * <font style='background-color: #FC5041;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC5041; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC5041;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC5041'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC5041'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC5041'>&nbsp;@&nbsp;</font><font style='background-color: #FC5041; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC5041;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC5041; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_RED = -0x1.192d16p126F;
    static { NAMED.put("bright pure red", -0x1.192d16p126F); LIST.add(-0x1.192d16p126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code B1301EFF}, L 0.38431373, A 0.57254905, B 0.54509807, alpha 1.0, hue 0.08852651, saturation 0.66311467, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.1724c4p126F}.
     * <pre>
     * <font style='background-color: #B1301E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1301E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1301E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1301E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1301E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1301E'>&nbsp;@&nbsp;</font><font style='background-color: #B1301E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1301E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1301E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.1724c4p126F;
    static { NAMED.put("deep brown red", -0x1.1724c4p126F); LIST.add(-0x1.1724c4p126F); }

    /**
     * This color constant "true brown red" has RGBA8888 code {@code D04935FF}, L 0.46666667, A 0.57254905, B 0.54509807, alpha 1.0, hue 0.08852651, saturation 0.5018177, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.1724eep126F}.
     * <pre>
     * <font style='background-color: #D04935;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D04935; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D04935;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D04935'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D04935'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D04935'>&nbsp;@&nbsp;</font><font style='background-color: #D04935; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D04935;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D04935; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_RED = -0x1.1724eep126F;
    static { NAMED.put("true brown red", -0x1.1724eep126F); LIST.add(-0x1.1724eep126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code F2634CFF}, L 0.56078434, A 0.57254905, B 0.54509807, alpha 1.0, hue 0.08852651, saturation 0.61618376, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.17251ep126F}.
     * <pre>
     * <font style='background-color: #F2634C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2634C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2634C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2634C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2634C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2634C'>&nbsp;@&nbsp;</font><font style='background-color: #F2634C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2634C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2634C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.17251ep126F;
    static { NAMED.put("bright brown red", -0x1.17251ep126F); LIST.add(-0x1.17251ep126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code B7360EFF}, L 0.4, A 0.5686275, B 0.5529412, alpha 1.0, hue 0.10458898, saturation 0.830694, and chroma 0.17267215.
     * It can be represented as a packed float with the constant {@code -0x1.1b22ccp126F}.
     * <pre>
     * <font style='background-color: #B7360E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7360E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7360E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B7360E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B7360E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B7360E'>&nbsp;@&nbsp;</font><font style='background-color: #B7360E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7360E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7360E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.1b22ccp126F;
    static { NAMED.put("deep red brown", -0x1.1b22ccp126F); LIST.add(-0x1.1b22ccp126F); }

    /**
     * This color constant "true red brown" has RGBA8888 code {@code E05631FF}, L 0.50980395, A 0.5686275, B 0.5529412, alpha 1.0, hue 0.10458898, saturation 0.59100187, and chroma 0.17267215.
     * It can be represented as a packed float with the constant {@code -0x1.1b2304p126F}.
     * <pre>
     * <font style='background-color: #E05631;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E05631; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E05631;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E05631'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E05631'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E05631'>&nbsp;@&nbsp;</font><font style='background-color: #E05631; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E05631;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E05631; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_BROWN = -0x1.1b2304p126F;
    static { NAMED.put("true red brown", -0x1.1b2304p126F); LIST.add(-0x1.1b2304p126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code FE6E46FF}, L 0.59607846, A 0.5686275, B 0.5529412, alpha 1.0, hue 0.10458898, saturation 0.7974697, and chroma 0.17267215.
     * It can be represented as a packed float with the constant {@code -0x1.1b233p126F}.
     * <pre>
     * <font style='background-color: #FE6E46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE6E46; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE6E46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FE6E46'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FE6E46'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FE6E46'>&nbsp;@&nbsp;</font><font style='background-color: #FE6E46; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE6E46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE6E46; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.1b233p126F;
    static { NAMED.put("bright red brown", -0x1.1b233p126F); LIST.add(-0x1.1b233p126F); }

    /**
     * This color constant "deep pure brown" has RGBA8888 code {@code 9F5135FF}, L 0.4117647, A 0.5411765, B 0.5372549, alpha 1.0, hue 0.11705489, saturation 0.38739282, and chroma 0.11062346.
     * It can be represented as a packed float with the constant {@code -0x1.1314d2p126F}.
     * <pre>
     * <font style='background-color: #9F5135;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F5135; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F5135;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F5135'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F5135'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F5135'>&nbsp;@&nbsp;</font><font style='background-color: #9F5135; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F5135;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F5135; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BROWN = -0x1.1314d2p126F;
    static { NAMED.put("deep pure brown", -0x1.1314d2p126F); LIST.add(-0x1.1314d2p126F); }

    /**
     * This color constant "true pure brown" has RGBA8888 code {@code BB684AFF}, L 0.49411765, A 0.5411765, B 0.5372549, alpha 1.0, hue 0.11705489, saturation 0.30238476, and chroma 0.11062346.
     * It can be represented as a packed float with the constant {@code -0x1.1314fcp126F}.
     * <pre>
     * <font style='background-color: #BB684A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB684A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB684A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB684A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB684A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB684A'>&nbsp;@&nbsp;</font><font style='background-color: #BB684A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB684A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB684A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BROWN = -0x1.1314fcp126F;
    static { NAMED.put("true pure brown", -0x1.1314fcp126F); LIST.add(-0x1.1314fcp126F); }

    /**
     * This color constant "bright pure brown" has RGBA8888 code {@code E08766FF}, L 0.60784316, A 0.5411765, B 0.5372549, alpha 1.0, hue 0.11705489, saturation 0.34095016, and chroma 0.11062346.
     * It can be represented as a packed float with the constant {@code -0x1.131536p126F}.
     * <pre>
     * <font style='background-color: #E08766;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E08766; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E08766;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E08766'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E08766'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E08766'>&nbsp;@&nbsp;</font><font style='background-color: #E08766; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E08766;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E08766; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BROWN = -0x1.131536p126F;
    static { NAMED.put("bright pure brown", -0x1.131536p126F); LIST.add(-0x1.131536p126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code A6542AFF}, L 0.42352942, A 0.5372549, B 0.54509807, alpha 1.0, hue 0.14010175, saturation 0.5426002, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.1712d8p126F}.
     * <pre>
     * <font style='background-color: #A6542A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6542A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6542A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6542A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6542A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6542A'>&nbsp;@&nbsp;</font><font style='background-color: #A6542A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6542A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6542A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.1712d8p126F;
    static { NAMED.put("deep orange brown", -0x1.1712d8p126F); LIST.add(-0x1.1712d8p126F); }

    /**
     * This color constant "true orange brown" has RGBA8888 code {@code CA7146FF}, L 0.5294118, A 0.5411765, B 0.54509807, alpha 1.0, hue 0.13222384, saturation 0.39587796, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.17150ep126F}.
     * <pre>
     * <font style='background-color: #CA7146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA7146; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA7146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA7146'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA7146'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA7146'>&nbsp;@&nbsp;</font><font style='background-color: #CA7146; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA7146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA7146; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_BROWN = -0x1.17150ep126F;
    static { NAMED.put("true orange brown", -0x1.17150ep126F); LIST.add(-0x1.17150ep126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code EA8B5EFF}, L 0.627451, A 0.5411765, B 0.54509807, alpha 1.0, hue 0.13222384, saturation 0.4486068, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.17154p126F}.
     * <pre>
     * <font style='background-color: #EA8B5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA8B5E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA8B5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EA8B5E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EA8B5E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EA8B5E'>&nbsp;@&nbsp;</font><font style='background-color: #EA8B5E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA8B5E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA8B5E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.17154p126F;
    static { NAMED.put("bright orange brown", -0x1.17154p126F); LIST.add(-0x1.17154p126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code B25920FF}, L 0.44705883, A 0.5411765, B 0.5529412, alpha 1.0, hue 0.14477962, saturation 0.7133059, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.1b14e4p126F}.
     * <pre>
     * <font style='background-color: #B25920;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B25920; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B25920;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B25920'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B25920'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B25920'>&nbsp;@&nbsp;</font><font style='background-color: #B25920; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B25920;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B25920; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.1b14e4p126F;
    static { NAMED.put("deep brown orange", -0x1.1b14e4p126F); LIST.add(-0x1.1b14e4p126F); }

    /**
     * This color constant "true brown orange" has RGBA8888 code {@code D8783FFF}, L 0.56078434, A 0.5411765, B 0.5529412, alpha 1.0, hue 0.14477962, saturation 0.5185596, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.1b151ep126F}.
     * <pre>
     * <font style='background-color: #D8783F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8783F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8783F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D8783F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D8783F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D8783F'>&nbsp;@&nbsp;</font><font style='background-color: #D8783F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D8783F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D8783F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BROWN_ORANGE = -0x1.1b151ep126F;
    static { NAMED.put("true brown orange", -0x1.1b151ep126F); LIST.add(-0x1.1b151ep126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code F48F54FF}, L 0.64705884, A 0.5411765, B 0.5529412, alpha 1.0, hue 0.14477962, saturation 0.5651491, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.1b154ap126F}.
     * <pre>
     * <font style='background-color: #F48F54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F48F54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F48F54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F48F54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F48F54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F48F54'>&nbsp;@&nbsp;</font><font style='background-color: #F48F54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F48F54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F48F54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.1b154ap126F;
    static { NAMED.put("bright brown orange", -0x1.1b154ap126F); LIST.add(-0x1.1b154ap126F); }

    /**
     * This color constant "deep pure orange" has RGBA8888 code {@code B25500FF}, L 0.4392157, A 0.5411765, B 0.5568628, alpha 1.0, hue 0.15023786, saturation 0.8216632, and chroma 0.13986339.
     * It can be represented as a packed float with the constant {@code -0x1.1d14ep126F}.
     * <pre>
     * <font style='background-color: #B25500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B25500; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B25500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B25500'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B25500'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B25500'>&nbsp;@&nbsp;</font><font style='background-color: #B25500; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B25500;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B25500; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_ORANGE = -0x1.1d14ep126F;
    static { NAMED.put("deep pure orange", -0x1.1d14ep126F); LIST.add(-0x1.1d14ep126F); }

    /**
     * This color constant "true pure orange" has RGBA8888 code {@code D57228FF}, L 0.5411765, A 0.5372549, B 0.56078434, alpha 1.0, hue 0.1624788, saturation 0.6986392, and chroma 0.14202859.
     * It can be represented as a packed float with the constant {@code -0x1.1f1314p126F}.
     * <pre>
     * <font style='background-color: #D57228;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D57228; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D57228;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D57228'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D57228'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D57228'>&nbsp;@&nbsp;</font><font style='background-color: #D57228; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D57228;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D57228; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_ORANGE = -0x1.1f1314p126F;
    static { NAMED.put("true pure orange", -0x1.1f1314p126F); LIST.add(-0x1.1f1314p126F); }

    /**
     * This color constant "bright pure orange" has RGBA8888 code {@code FC9248FF}, L 0.6627451, A 0.5411765, B 0.56078434, alpha 1.0, hue 0.15522522, saturation 0.7079914, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.1f1552p126F}.
     * <pre>
     * <font style='background-color: #FC9248;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC9248; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC9248;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC9248'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC9248'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC9248'>&nbsp;@&nbsp;</font><font style='background-color: #FC9248; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC9248;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC9248; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_ORANGE = -0x1.1f1552p126F;
    static { NAMED.put("bright pure orange", -0x1.1f1552p126F); LIST.add(-0x1.1f1552p126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code B0651CFF}, L 0.46666667, A 0.5294118, B 0.5568628, alpha 1.0, hue 0.17402768, saturation 0.71917695, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.1d0eeep126F}.
     * <pre>
     * <font style='background-color: #B0651C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0651C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0651C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0651C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0651C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0651C'>&nbsp;@&nbsp;</font><font style='background-color: #B0651C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0651C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0651C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.1d0eeep126F;
    static { NAMED.put("deep saffron orange", -0x1.1d0eeep126F); LIST.add(-0x1.1d0eeep126F); }

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
     * This color constant "bright saffron orange" has RGBA8888 code {@code F7A258FF}, L 0.69411767, A 0.5294118, B 0.5568628, alpha 1.0, hue 0.17402768, saturation 0.590173, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.1d0f62p126F}.
     * <pre>
     * <font style='background-color: #F7A258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7A258; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7A258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7A258'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7A258'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7A258'>&nbsp;@&nbsp;</font><font style='background-color: #F7A258; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7A258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7A258; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.1d0f62p126F;
    static { NAMED.put("bright saffron orange", -0x1.1d0f62p126F); LIST.add(-0x1.1d0f62p126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code B06C00FF}, L 0.47843137, A 0.52156866, B 0.56078434, alpha 1.0, hue 0.19574447, saturation 0.8121599, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.1f0af4p126F}.
     * <pre>
     * <font style='background-color: #B06C00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B06C00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B06C00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B06C00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B06C00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B06C00'>&nbsp;@&nbsp;</font><font style='background-color: #B06C00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B06C00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B06C00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.1f0af4p126F;
    static { NAMED.put("deep orange saffron", -0x1.1f0af4p126F); LIST.add(-0x1.1f0af4p126F); }

    /**
     * This color constant "true orange saffron" has RGBA8888 code {@code CF8728FF}, L 0.5764706, A 0.5176471, B 0.5647059, alpha 1.0, hue 0.20763123, saturation 0.7133059, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.210926p126F}.
     * <pre>
     * <font style='background-color: #CF8728;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF8728; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF8728;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CF8728'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CF8728'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CF8728'>&nbsp;@&nbsp;</font><font style='background-color: #CF8728; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF8728;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF8728; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_ORANGE_SAFFRON = -0x1.210926p126F;
    static { NAMED.put("true orange saffron", -0x1.210926p126F); LIST.add(-0x1.210926p126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code F7AA4BFF}, L 0.70980394, A 0.5176471, B 0.5647059, alpha 1.0, hue 0.20763123, saturation 0.541103, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.21096ap126F}.
     * <pre>
     * <font style='background-color: #F7AA4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7AA4B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7AA4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7AA4B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7AA4B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7AA4B'>&nbsp;@&nbsp;</font><font style='background-color: #F7AA4B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7AA4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7AA4B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.21096ap126F;
    static { NAMED.put("bright orange saffron", -0x1.21096ap126F); LIST.add(-0x1.21096ap126F); }

    /**
     * This color constant "deep pure saffron" has RGBA8888 code {@code AD761BFF}, L 0.49803922, A 0.5137255, B 0.56078434, alpha 1.0, hue 0.21465261, saturation 0.75811595, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.1f06fep126F}.
     * <pre>
     * <font style='background-color: #AD761B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD761B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD761B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD761B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD761B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD761B'>&nbsp;@&nbsp;</font><font style='background-color: #AD761B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD761B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD761B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_SAFFRON = -0x1.1f06fep126F;
    static { NAMED.put("deep pure saffron", -0x1.1f06fep126F); LIST.add(-0x1.1f06fep126F); }

    /**
     * This color constant "true pure saffron" has RGBA8888 code {@code D1963DFF}, L 0.6156863, A 0.5137255, B 0.56078434, alpha 1.0, hue 0.21465261, saturation 0.55916953, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.1f073ap126F}.
     * <pre>
     * <font style='background-color: #D1963D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1963D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1963D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1963D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1963D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1963D'>&nbsp;@&nbsp;</font><font style='background-color: #D1963D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1963D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1963D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_SAFFRON = -0x1.1f073ap126F;
    static { NAMED.put("true pure saffron", -0x1.1f073ap126F); LIST.add(-0x1.1f073ap126F); }

    /**
     * This color constant "bright pure saffron" has RGBA8888 code {@code F6B85CFF}, L 0.74509805, A 0.50980395, B 0.56078434, alpha 1.0, hue 0.22453669, saturation 0.45600647, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.1f057cp126F}.
     * <pre>
     * <font style='background-color: #F6B85C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6B85C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6B85C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6B85C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6B85C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6B85C'>&nbsp;@&nbsp;</font><font style='background-color: #F6B85C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6B85C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6B85C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_SAFFRON = -0x1.1f057cp126F;
    static { NAMED.put("bright pure saffron", -0x1.1f057cp126F); LIST.add(-0x1.1f057cp126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code B88D15FF}, L 0.56078434, A 0.49803922, B 0.5686275, alpha 1.0, hue 0.2545336, saturation 0.7857715, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.22ff1ep126F}.
     * <pre>
     * <font style='background-color: #B88D15;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B88D15; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B88D15;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B88D15'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B88D15'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B88D15'>&nbsp;@&nbsp;</font><font style='background-color: #B88D15; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B88D15;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B88D15; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.22ff1ep126F;
    static { NAMED.put("deep yellow saffron", -0x1.22ff1ep126F); LIST.add(-0x1.22ff1ep126F); }

    /**
     * This color constant "true yellow saffron" has RGBA8888 code {@code DFB13FFF}, L 0.69411767, A 0.5019608, B 0.5686275, alpha 1.0, hue 0.24546641, saturation 0.61911374, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.230162p126F}.
     * <pre>
     * <font style='background-color: #DFB13F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFB13F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFB13F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DFB13F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DFB13F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DFB13F'>&nbsp;@&nbsp;</font><font style='background-color: #DFB13F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFB13F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFB13F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_SAFFRON = -0x1.230162p126F;
    static { NAMED.put("true yellow saffron", -0x1.230162p126F); LIST.add(-0x1.230162p126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code FACA57FF}, L 0.79607844, A 0.5019608, B 0.5686275, alpha 1.0, hue 0.24546641, saturation 0.56700194, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.230196p126F}.
     * <pre>
     * <font style='background-color: #FACA57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FACA57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FACA57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FACA57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FACA57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FACA57'>&nbsp;@&nbsp;</font><font style='background-color: #FACA57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FACA57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FACA57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.230196p126F;
    static { NAMED.put("bright yellow saffron", -0x1.230196p126F); LIST.add(-0x1.230196p126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code B89F26FF}, L 0.6039216, A 0.49019608, B 0.5686275, alpha 1.0, hue 0.27259654, saturation 0.72579473, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.22fb34p126F}.
     * <pre>
     * <font style='background-color: #B89F26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B89F26; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B89F26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B89F26'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B89F26'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B89F26'>&nbsp;@&nbsp;</font><font style='background-color: #B89F26; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B89F26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B89F26; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.22fb34p126F;
    static { NAMED.put("deep saffron yellow", -0x1.22fb34p126F); LIST.add(-0x1.22fb34p126F); }

    /**
     * This color constant "true saffron yellow" has RGBA8888 code {@code D6BC44FF}, L 0.7137255, A 0.49019608, B 0.5686275, alpha 1.0, hue 0.27259654, saturation 0.5781015, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.22fb6cp126F}.
     * <pre>
     * <font style='background-color: #D6BC44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6BC44; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6BC44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6BC44'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6BC44'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6BC44'>&nbsp;@&nbsp;</font><font style='background-color: #D6BC44; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6BC44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6BC44; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_SAFFRON_YELLOW = -0x1.22fb6cp126F;
    static { NAMED.put("true saffron yellow", -0x1.22fb6cp126F); LIST.add(-0x1.22fb6cp126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code FCE166FF}, L 0.8627451, A 0.49019608, B 0.5686275, alpha 1.0, hue 0.27259654, saturation 0.5314061, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.22fbb8p126F}.
     * <pre>
     * <font style='background-color: #FCE166;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FCE166; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FCE166;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FCE166'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FCE166'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FCE166'>&nbsp;@&nbsp;</font><font style='background-color: #FCE166; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FCE166;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FCE166; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.22fbb8p126F;
    static { NAMED.put("bright saffron yellow", -0x1.22fbb8p126F); LIST.add(-0x1.22fbb8p126F); }

    /**
     * This color constant "deep pure yellow" has RGBA8888 code {@code A8AB00FF}, L 0.6156863, A 0.47058824, B 0.57254905, alpha 1.0, hue 0.3112886, saturation 0.76995534, and chroma 0.15595676.
     * It can be represented as a packed float with the constant {@code -0x1.24f13ap126F}.
     * <pre>
     * <font style='background-color: #A8AB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8AB00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8AB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8AB00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8AB00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8AB00'>&nbsp;@&nbsp;</font><font style='background-color: #A8AB00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8AB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8AB00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_YELLOW = -0x1.24f13ap126F;
    static { NAMED.put("deep pure yellow", -0x1.24f13ap126F); LIST.add(-0x1.24f13ap126F); }

    /**
     * This color constant "true pure yellow" has RGBA8888 code {@code CFD43EFF}, L 0.76862746, A 0.46666667, B 0.5764706, alpha 1.0, hue 0.31541443, saturation 0.65668935, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.26ef88p126F}.
     * <pre>
     * <font style='background-color: #CFD43E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFD43E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFD43E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFD43E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFD43E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFD43E'>&nbsp;@&nbsp;</font><font style='background-color: #CFD43E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFD43E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFD43E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_YELLOW = -0x1.26ef88p126F;
    static { NAMED.put("true pure yellow", -0x1.26ef88p126F); LIST.add(-0x1.26ef88p126F); }

    /**
     * This color constant "bright pure yellow" has RGBA8888 code {@code F0F65EFF}, L 0.90588236, A 0.46666667, B 0.5764706, alpha 1.0, hue 0.31541443, saturation 0.54744804, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.26efcep126F}.
     * <pre>
     * <font style='background-color: #F0F65E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0F65E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0F65E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0F65E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0F65E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0F65E'>&nbsp;@&nbsp;</font><font style='background-color: #F0F65E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0F65E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0F65E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_YELLOW = -0x1.26efcep126F;
    static { NAMED.put("bright pure yellow", -0x1.26efcep126F); LIST.add(-0x1.26efcep126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code 99B12CFF}, L 0.61960787, A 0.45882353, B 0.5686275, alpha 1.0, hue 0.33601886, saturation 0.6799306, and chroma 0.15944009.
     * It can be represented as a packed float with the constant {@code -0x1.22eb3cp126F}.
     * <pre>
     * <font style='background-color: #99B12C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99B12C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99B12C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #99B12C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #99B12C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #99B12C'>&nbsp;@&nbsp;</font><font style='background-color: #99B12C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99B12C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99B12C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.22eb3cp126F;
    static { NAMED.put("deep lime yellow", -0x1.22eb3cp126F); LIST.add(-0x1.22eb3cp126F); }

    /**
     * This color constant "true lime yellow" has RGBA8888 code {@code C1DB54FF}, L 0.7764706, A 0.45882353, B 0.5686275, alpha 1.0, hue 0.33601886, saturation 0.50389415, and chroma 0.15944009.
     * It can be represented as a packed float with the constant {@code -0x1.22eb8cp126F}.
     * <pre>
     * <font style='background-color: #C1DB54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1DB54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1DB54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1DB54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1DB54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1DB54'>&nbsp;@&nbsp;</font><font style='background-color: #C1DB54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1DB54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1DB54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_LIME_YELLOW = -0x1.22eb8cp126F;
    static { NAMED.put("true lime yellow", -0x1.22eb8cp126F); LIST.add(-0x1.22eb8cp126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code DBF76DFF}, L 0.8901961, A 0.45882353, B 0.5686275, alpha 1.0, hue 0.33601886, saturation 0.44047856, and chroma 0.15944009.
     * It can be represented as a packed float with the constant {@code -0x1.22ebc6p126F}.
     * <pre>
     * <font style='background-color: #DBF76D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBF76D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBF76D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBF76D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBF76D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBF76D'>&nbsp;@&nbsp;</font><font style='background-color: #DBF76D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBF76D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBF76D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.22ebc6p126F;
    static { NAMED.put("bright lime yellow", -0x1.22ebc6p126F); LIST.add(-0x1.22ebc6p126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code 88AE00FF}, L 0.59607846, A 0.4509804, B 0.57254905, alpha 1.0, hue 0.34458438, saturation 0.78188413, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.24e73p126F}.
     * <pre>
     * <font style='background-color: #88AE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #88AE00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #88AE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #88AE00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #88AE00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #88AE00'>&nbsp;@&nbsp;</font><font style='background-color: #88AE00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #88AE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #88AE00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.24e73p126F;
    static { NAMED.put("deep yellow lime", -0x1.24e73p126F); LIST.add(-0x1.24e73p126F); }

    /**
     * This color constant "true yellow lime" has RGBA8888 code {@code A6D030FF}, L 0.7176471, A 0.44705883, B 0.5764706, alpha 1.0, hue 0.34638813, saturation 0.704832, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.26e56ep126F}.
     * <pre>
     * <font style='background-color: #A6D030;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6D030; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6D030;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6D030'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6D030'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6D030'>&nbsp;@&nbsp;</font><font style='background-color: #A6D030; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6D030;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6D030; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_YELLOW_LIME = -0x1.26e56ep126F;
    static { NAMED.put("true yellow lime", -0x1.26e56ep126F); LIST.add(-0x1.26e56ep126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code C9F656FF}, L 0.8666667, A 0.44705883, B 0.5764706, alpha 1.0, hue 0.34638813, saturation 0.576, and chroma 0.18528971.
     * It can be represented as a packed float with the constant {@code -0x1.26e5bap126F}.
     * <pre>
     * <font style='background-color: #C9F656;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9F656; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9F656;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9F656'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9F656'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9F656'>&nbsp;@&nbsp;</font><font style='background-color: #C9F656; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9F656;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9F656; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.26e5bap126F;
    static { NAMED.put("bright yellow lime", -0x1.26e5bap126F); LIST.add(-0x1.26e5bap126F); }

    /**
     * This color constant "deep pure lime" has RGBA8888 code {@code 76A91BFF}, L 0.5686275, A 0.44313726, B 0.5686275, alpha 1.0, hue 0.3601329, saturation 0.7495692, and chroma 0.17755185.
     * It can be represented as a packed float with the constant {@code -0x1.22e322p126F}.
     * <pre>
     * <font style='background-color: #76A91B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76A91B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76A91B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #76A91B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #76A91B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #76A91B'>&nbsp;@&nbsp;</font><font style='background-color: #76A91B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76A91B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76A91B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_LIME = -0x1.22e322p126F;
    static { NAMED.put("deep pure lime", -0x1.22e322p126F); LIST.add(-0x1.22e322p126F); }

    /**
     * This color constant "true pure lime" has RGBA8888 code {@code 9DD549FF}, L 0.7254902, A 0.44313726, B 0.5686275, alpha 1.0, hue 0.3601329, saturation 0.5462357, and chroma 0.17755185.
     * It can be represented as a packed float with the constant {@code -0x1.22e372p126F}.
     * <pre>
     * <font style='background-color: #9DD549;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9DD549; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9DD549;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9DD549'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9DD549'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9DD549'>&nbsp;@&nbsp;</font><font style='background-color: #9DD549; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9DD549;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9DD549; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_LIME = -0x1.22e372p126F;
    static { NAMED.put("true pure lime", -0x1.22e372p126F); LIST.add(-0x1.22e372p126F); }

    /**
     * This color constant "bright pure lime" has RGBA8888 code {@code B9F465FF}, L 0.84705883, A 0.44313726, B 0.5686275, alpha 1.0, hue 0.3601329, saturation 0.467183, and chroma 0.17755185.
     * It can be represented as a packed float with the constant {@code -0x1.22e3bp126F}.
     * <pre>
     * <font style='background-color: #B9F465;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9F465; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9F465;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B9F465'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B9F465'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B9F465'>&nbsp;@&nbsp;</font><font style='background-color: #B9F465; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9F465;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9F465; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_LIME = -0x1.22e3bp126F;
    static { NAMED.put("bright pure lime", -0x1.22e3bp126F); LIST.add(-0x1.22e3bp126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 53AC00FF}, L 0.5529412, A 0.42745098, B 0.5686275, alpha 1.0, hue 0.3794164, saturation 0.7579809, and chroma 0.1989505.
     * It can be represented as a packed float with the constant {@code -0x1.22db1ap126F}.
     * <pre>
     * <font style='background-color: #53AC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53AC00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53AC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #53AC00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #53AC00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #53AC00'>&nbsp;@&nbsp;</font><font style='background-color: #53AC00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53AC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53AC00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.22db1ap126F;
    static { NAMED.put("deep green lime", -0x1.22db1ap126F); LIST.add(-0x1.22db1ap126F); }

    /**
     * This color constant "true green lime" has RGBA8888 code {@code 6ECC2EFF}, L 0.6627451, A 0.42352942, B 0.57254905, alpha 1.0, hue 0.37918407, saturation 0.6736204, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.24d952p126F}.
     * <pre>
     * <font style='background-color: #6ECC2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6ECC2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6ECC2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6ECC2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6ECC2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6ECC2E'>&nbsp;@&nbsp;</font><font style='background-color: #6ECC2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6ECC2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6ECC2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_LIME = -0x1.24d952p126F;
    static { NAMED.put("true green lime", -0x1.24d952p126F); LIST.add(-0x1.24d952p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code 93F656FF}, L 0.81960785, A 0.42352942, B 0.57254905, alpha 1.0, hue 0.37918407, saturation 0.5498216, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.24d9a2p126F}.
     * <pre>
     * <font style='background-color: #93F656;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93F656; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93F656;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93F656'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93F656'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93F656'>&nbsp;@&nbsp;</font><font style='background-color: #93F656; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93F656;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93F656; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.24d9a2p126F;
    static { NAMED.put("bright green lime", -0x1.24d9a2p126F); LIST.add(-0x1.24d9a2p126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 3FAC22FF}, L 0.54509807, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.7323683, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.20d716p126F}.
     * <pre>
     * <font style='background-color: #3FAC22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FAC22; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FAC22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FAC22'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FAC22'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FAC22'>&nbsp;@&nbsp;</font><font style='background-color: #3FAC22; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FAC22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FAC22; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.20d716p126F;
    static { NAMED.put("deep lime green", -0x1.20d716p126F); LIST.add(-0x1.20d716p126F); }

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
     * This color constant "bright lime green" has RGBA8888 code {@code 7FF464FF}, L 0.8, A 0.41960785, B 0.5647059, alpha 1.0, hue 0.39212817, saturation 0.4733222, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.20d798p126F}.
     * <pre>
     * <font style='background-color: #7FF464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FF464; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FF464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FF464'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FF464'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FF464'>&nbsp;@&nbsp;</font><font style='background-color: #7FF464; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FF464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FF464; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.20d798p126F;
    static { NAMED.put("bright lime green", -0x1.20d798p126F); LIST.add(-0x1.20d798p126F); }

    /**
     * This color constant "deep pure green" has RGBA8888 code {@code 0CB634FF}, L 0.5647059, A 0.40784314, B 0.56078434, alpha 1.0, hue 0.40718868, saturation 1.0291373, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.1ed12p126F}.
     * <pre>
     * <font style='background-color: #0CB634;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0CB634; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0CB634;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0CB634'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0CB634'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0CB634'>&nbsp;@&nbsp;</font><font style='background-color: #0CB634; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0CB634;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0CB634; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_GREEN = -0x1.1ed12p126F;
    static { NAMED.put("deep pure green", -0x1.1ed12p126F); LIST.add(-0x1.1ed12p126F); }

    /**
     * This color constant "true pure green" has RGBA8888 code {@code 43DE57FF}, L 0.7019608, A 0.40784314, B 0.56078434, alpha 1.0, hue 0.40718868, saturation 0.76197344, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.1ed166p126F}.
     * <pre>
     * <font style='background-color: #43DE57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43DE57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43DE57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #43DE57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #43DE57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #43DE57'>&nbsp;@&nbsp;</font><font style='background-color: #43DE57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43DE57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43DE57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_GREEN = -0x1.1ed166p126F;
    static { NAMED.put("true pure green", -0x1.1ed166p126F); LIST.add(-0x1.1ed166p126F); }

    /**
     * This color constant "bright pure green" has RGBA8888 code {@code 5DF96DFF}, L 0.8, A 0.40784314, B 0.56078434, alpha 1.0, hue 0.40718868, saturation 0.63779485, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.1ed198p126F}.
     * <pre>
     * <font style='background-color: #5DF96D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5DF96D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5DF96D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5DF96D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5DF96D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5DF96D'>&nbsp;@&nbsp;</font><font style='background-color: #5DF96D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5DF96D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5DF96D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_GREEN = -0x1.1ed198p126F;
    static { NAMED.put("bright pure green", -0x1.1ed198p126F); LIST.add(-0x1.1ed198p126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 31B079FF}, L 0.5686275, A 0.43137255, B 0.52156866, alpha 1.0, hue 0.451547, saturation 0.7815358, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add22p126F}.
     * <pre>
     * <font style='background-color: #31B079;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31B079; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31B079;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #31B079'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #31B079'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #31B079'>&nbsp;@&nbsp;</font><font style='background-color: #31B079; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31B079;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31B079; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.0add22p126F;
    static { NAMED.put("deep cyan green", -0x1.0add22p126F); LIST.add(-0x1.0add22p126F); }

    /**
     * This color constant "true cyan green" has RGBA8888 code {@code 50CF94FF}, L 0.6745098, A 0.43137255, B 0.52156866, alpha 1.0, hue 0.451547, saturation 0.6224997, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.0add58p126F}.
     * <pre>
     * <font style='background-color: #50CF94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50CF94; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50CF94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #50CF94'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #50CF94'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #50CF94'>&nbsp;@&nbsp;</font><font style='background-color: #50CF94; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50CF94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50CF94; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_GREEN = -0x1.0add58p126F;
    static { NAMED.put("true cyan green", -0x1.0add58p126F); LIST.add(-0x1.0add58p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code 72F3B5FF}, L 0.8117647, A 0.43137255, B 0.5176471, alpha 1.0, hue 0.4599463, saturation 0.51210666, and chroma 0.1411665.
     * It can be represented as a packed float with the constant {@code -0x1.08dd9ep126F}.
     * <pre>
     * <font style='background-color: #72F3B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #72F3B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #72F3B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #72F3B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #72F3B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #72F3B5'>&nbsp;@&nbsp;</font><font style='background-color: #72F3B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #72F3B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #72F3B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.08dd9ep126F;
    static { NAMED.put("bright cyan green", -0x1.08dd9ep126F); LIST.add(-0x1.08dd9ep126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 05B398FF}, L 0.5764706, A 0.43137255, B 0.5019608, alpha 1.0, hue 0.4954664, saturation 0.9202477, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.00dd26p126F}.
     * <pre>
     * <font style='background-color: #05B398;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #05B398; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #05B398;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #05B398'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #05B398'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #05B398'>&nbsp;@&nbsp;</font><font style='background-color: #05B398; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #05B398;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #05B398; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.00dd26p126F;
    static { NAMED.put("deep green cyan", -0x1.00dd26p126F); LIST.add(-0x1.00dd26p126F); }

    /**
     * This color constant "true green cyan" has RGBA8888 code {@code 43D9BCFF}, L 0.7137255, A 0.43137255, B 0.49803922, alpha 1.0, hue 0.5045336, saturation 0.747447, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.fedd6cp125F}.
     * <pre>
     * <font style='background-color: #43D9BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43D9BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43D9BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #43D9BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #43D9BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #43D9BC'>&nbsp;@&nbsp;</font><font style='background-color: #43D9BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43D9BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43D9BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_GREEN_CYAN = -0x1.fedd6cp125F;
    static { NAMED.put("true green cyan", -0x1.fedd6cp125F); LIST.add(-0x1.fedd6cp125F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code 62F8D9FF}, L 0.83137256, A 0.43137255, B 0.49803922, alpha 1.0, hue 0.5045336, saturation 0.61911374, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.fedda8p125F}.
     * <pre>
     * <font style='background-color: #62F8D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #62F8D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #62F8D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #62F8D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #62F8D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #62F8D9'>&nbsp;@&nbsp;</font><font style='background-color: #62F8D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #62F8D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #62F8D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.fedda8p125F;
    static { NAMED.put("bright green cyan", -0x1.fedda8p125F); LIST.add(-0x1.fedda8p125F); }

    /**
     * This color constant "deep pure cyan" has RGBA8888 code {@code 2CAEADFF}, L 0.5764706, A 0.44313726, B 0.4862745, alpha 1.0, hue 0.5376946, saturation 0.7930497, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.f8e326p125F}.
     * <pre>
     * <font style='background-color: #2CAEAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2CAEAD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2CAEAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2CAEAD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2CAEAD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2CAEAD'>&nbsp;@&nbsp;</font><font style='background-color: #2CAEAD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2CAEAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2CAEAD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_CYAN = -0x1.f8e326p125F;
    static { NAMED.put("deep pure cyan", -0x1.f8e326p125F); LIST.add(-0x1.f8e326p125F); }

    /**
     * This color constant "true pure cyan" has RGBA8888 code {@code 4CCBCAFF}, L 0.68235296, A 0.44313726, B 0.48235294, alpha 1.0, hue 0.54788184, saturation 0.6556444, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.f6e35cp125F}.
     * <pre>
     * <font style='background-color: #4CCBCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4CCBCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4CCBCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4CCBCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4CCBCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4CCBCA'>&nbsp;@&nbsp;</font><font style='background-color: #4CCBCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4CCBCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4CCBCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_CYAN = -0x1.f6e35cp125F;
    static { NAMED.put("true pure cyan", -0x1.f6e35cp125F); LIST.add(-0x1.f6e35cp125F); }

    /**
     * This color constant "bright pure cyan" has RGBA8888 code {@code 71F3F1FF}, L 0.83137256, A 0.44313726, B 0.4862745, alpha 1.0, hue 0.5376946, saturation 0.49273357, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.f8e3a8p125F}.
     * <pre>
     * <font style='background-color: #71F3F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #71F3F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #71F3F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #71F3F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #71F3F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #71F3F1'>&nbsp;@&nbsp;</font><font style='background-color: #71F3F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #71F3F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #71F3F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_CYAN = -0x1.f8e3a8p125F;
    static { NAMED.put("bright pure cyan", -0x1.f8e3a8p125F); LIST.add(-0x1.f8e3a8p125F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 0091B3FF}, L 0.49803922, A 0.45490196, B 0.4627451, alpha 1.0, hue 0.60989827, saturation 0.7930497, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.ece8fep125F}.
     * <pre>
     * <font style='background-color: #0091B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0091B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0091B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0091B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0091B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0091B3'>&nbsp;@&nbsp;</font><font style='background-color: #0091B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0091B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0091B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.ece8fep125F;
    static { NAMED.put("deep blue cyan", -0x1.ece8fep125F); LIST.add(-0x1.ece8fep125F); }

    /**
     * This color constant "true blue cyan" has RGBA8888 code {@code 35B2D6FF}, L 0.60784316, A 0.4509804, B 0.4627451, alpha 1.0, hue 0.6034426, saturation 0.74010134, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.ece736p125F}.
     * <pre>
     * <font style='background-color: #35B2D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #35B2D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #35B2D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #35B2D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #35B2D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #35B2D6'>&nbsp;@&nbsp;</font><font style='background-color: #35B2D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #35B2D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #35B2D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_CYAN = -0x1.ece736p125F;
    static { NAMED.put("true blue cyan", -0x1.ece736p125F); LIST.add(-0x1.ece736p125F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 57D4FBFF}, L 0.73333335, A 0.4509804, B 0.45882353, alpha 1.0, hue 0.6112048, saturation 0.8001501, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.eae776p125F}.
     * <pre>
     * <font style='background-color: #57D4FB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57D4FB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57D4FB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57D4FB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57D4FB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57D4FB'>&nbsp;@&nbsp;</font><font style='background-color: #57D4FB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57D4FB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57D4FB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.eae776p125F;
    static { NAMED.put("bright blue cyan", -0x1.eae776p125F); LIST.add(-0x1.eae776p125F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 166FA9FF}, L 0.40784314, A 0.46666667, B 0.44313726, alpha 1.0, hue 0.6656062, saturation 0.8481892, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.e2eedp125F}.
     * <pre>
     * <font style='background-color: #166FA9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #166FA9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #166FA9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #166FA9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #166FA9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #166FA9'>&nbsp;@&nbsp;</font><font style='background-color: #166FA9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #166FA9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #166FA9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.e2eedp125F;
    static { NAMED.put("deep cyan blue", -0x1.e2eedp125F); LIST.add(-0x1.e2eedp125F); }

    /**
     * This color constant "true cyan blue" has RGBA8888 code {@code 3D94D3FF}, L 0.53333336, A 0.46666667, B 0.44313726, alpha 1.0, hue 0.6656062, saturation 0.5971727, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.e2ef1p125F}.
     * <pre>
     * <font style='background-color: #3D94D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D94D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D94D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3D94D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3D94D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3D94D3'>&nbsp;@&nbsp;</font><font style='background-color: #3D94D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D94D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D94D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_CYAN_BLUE = -0x1.e2ef1p125F;
    static { NAMED.put("true cyan blue", -0x1.e2ef1p125F); LIST.add(-0x1.e2ef1p125F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code 57B0F2FF}, L 0.6313726, A 0.46666667, B 0.44313726, alpha 1.0, hue 0.6656062, saturation 0.68891937, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.e2ef42p125F}.
     * <pre>
     * <font style='background-color: #57B0F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57B0F2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57B0F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57B0F2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57B0F2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57B0F2'>&nbsp;@&nbsp;</font><font style='background-color: #57B0F2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57B0F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57B0F2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.e2ef42p125F;
    static { NAMED.put("bright cyan blue", -0x1.e2ef42p125F); LIST.add(-0x1.e2ef42p125F); }

    /**
     * This color constant "deep pure blue" has RGBA8888 code {@code 0015B4FF}, L 0.2509804, A 0.4862745, B 0.3764706, alpha 1.0, hue 0.7323789, saturation 0.88186556, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f88p125F}.
     * <pre>
     * <font style='background-color: #0015B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0015B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0015B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0015B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0015B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0015B4'>&nbsp;@&nbsp;</font><font style='background-color: #0015B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0015B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0015B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_BLUE = -0x1.c0f88p125F;
    static { NAMED.put("deep pure blue", -0x1.c0f88p125F); LIST.add(-0x1.c0f88p125F); }

    /**
     * This color constant "true pure blue" has RGBA8888 code {@code 0034D2FF}, L 0.3137255, A 0.4862745, B 0.3764706, alpha 1.0, hue 0.7323789, saturation 0.6049155, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f8ap125F}.
     * <pre>
     * <font style='background-color: #0034D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0034D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0034D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0034D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0034D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0034D2'>&nbsp;@&nbsp;</font><font style='background-color: #0034D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0034D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0034D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_BLUE = -0x1.c0f8ap125F;
    static { NAMED.put("true pure blue", -0x1.c0f8ap125F); LIST.add(-0x1.c0f8ap125F); }

    /**
     * This color constant "bright pure blue" has RGBA8888 code {@code 1D55FAFF}, L 0.40784314, A 0.4862745, B 0.3764706, alpha 1.0, hue 0.7323789, saturation 0.88186556, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f8dp125F}.
     * <pre>
     * <font style='background-color: #1D55FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D55FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D55FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D55FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D55FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D55FA'>&nbsp;@&nbsp;</font><font style='background-color: #1D55FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D55FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D55FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_BLUE = -0x1.c0f8dp125F;
    static { NAMED.put("bright pure blue", -0x1.c0f8dp125F); LIST.add(-0x1.c0f8dp125F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 2E23ABFF}, L 0.27058825, A 0.5058824, B 0.39215687, alpha 1.0, hue 0.7586634, saturation 0.6860761, and chroma 0.21516311.
     * It can be represented as a packed float with the constant {@code -0x1.c9028ap125F}.
     * <pre>
     * <font style='background-color: #2E23AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2E23AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2E23AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2E23AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2E23AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2E23AB'>&nbsp;@&nbsp;</font><font style='background-color: #2E23AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2E23AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2E23AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.c9028ap125F;
    static { NAMED.put("deep violet blue", -0x1.c9028ap125F); LIST.add(-0x1.c9028ap125F); }

    /**
     * This color constant "true violet blue" has RGBA8888 code {@code 4040CEFF}, L 0.3529412, A 0.50980395, B 0.39215687, alpha 1.0, hue 0.764433, saturation 0.49494907, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.c904b4p125F}.
     * <pre>
     * <font style='background-color: #4040CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4040CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4040CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4040CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4040CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4040CE'>&nbsp;@&nbsp;</font><font style='background-color: #4040CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4040CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4040CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_BLUE = -0x1.c904b4p125F;
    static { NAMED.put("true violet blue", -0x1.c904b4p125F); LIST.add(-0x1.c904b4p125F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code 5259EEFF}, L 0.43137255, A 0.5058824, B 0.39215687, alpha 1.0, hue 0.7586634, saturation 0.7292831, and chroma 0.21516311.
     * It can be represented as a packed float with the constant {@code -0x1.c902dcp125F}.
     * <pre>
     * <font style='background-color: #5259EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5259EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5259EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5259EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5259EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5259EE'>&nbsp;@&nbsp;</font><font style='background-color: #5259EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5259EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5259EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.c902dcp125F;
    static { NAMED.put("bright violet blue", -0x1.c902dcp125F); LIST.add(-0x1.c902dcp125F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 4817B7FF}, L 0.2901961, A 0.5294118, B 0.3882353, alpha 1.0, hue 0.7909493, saturation 0.835046, and chroma 0.2302369.
     * It can be represented as a packed float with the constant {@code -0x1.c70e94p125F}.
     * <pre>
     * <font style='background-color: #4817B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4817B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4817B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4817B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4817B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4817B7'>&nbsp;@&nbsp;</font><font style='background-color: #4817B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4817B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4817B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.c70e94p125F;
    static { NAMED.put("deep blue violet", -0x1.c70e94p125F); LIST.add(-0x1.c70e94p125F); }

    /**
     * This color constant "true blue violet" has RGBA8888 code {@code 633FE2FF}, L 0.39215687, A 0.53333336, B 0.3882353, alpha 1.0, hue 0.7961206, saturation 0.62067455, and chroma 0.23234801.
     * It can be represented as a packed float with the constant {@code -0x1.c710c8p125F}.
     * <pre>
     * <font style='background-color: #633FE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #633FE2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #633FE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #633FE2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #633FE2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #633FE2'>&nbsp;@&nbsp;</font><font style='background-color: #633FE2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #633FE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #633FE2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_BLUE_VIOLET = -0x1.c710c8p125F;
    static { NAMED.put("true blue violet", -0x1.c710c8p125F); LIST.add(-0x1.c710c8p125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code 7756FFFF}, L 0.46666667, A 0.53333336, B 0.3882353, alpha 1.0, hue 0.7961206, saturation 0.905728, and chroma 0.23234801.
     * It can be represented as a packed float with the constant {@code -0x1.c710eep125F}.
     * <pre>
     * <font style='background-color: #7756FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7756FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7756FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7756FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7756FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7756FF'>&nbsp;@&nbsp;</font><font style='background-color: #7756FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7756FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7756FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.c710eep125F;
    static { NAMED.put("bright blue violet", -0x1.c710eep125F); LIST.add(-0x1.c710eep125F); }

    /**
     * This color constant "deep pure violet" has RGBA8888 code {@code 6221A9FF}, L 0.3137255, A 0.5529412, B 0.4117647, alpha 1.0, hue 0.83601886, saturation 0.705024, and chroma 0.2049944.
     * It can be represented as a packed float with the constant {@code -0x1.d31aap125F}.
     * <pre>
     * <font style='background-color: #6221A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6221A9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6221A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6221A9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6221A9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6221A9'>&nbsp;@&nbsp;</font><font style='background-color: #6221A9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6221A9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6221A9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_VIOLET = -0x1.d31aap125F;
    static { NAMED.put("deep pure violet", -0x1.d31aap125F); LIST.add(-0x1.d31aap125F); }

    /**
     * This color constant "true pure violet" has RGBA8888 code {@code 7B3CCAFF}, L 0.39607844, A 0.5529412, B 0.40784314, alpha 1.0, hue 0.8329952, saturation 0.51541597, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.d11acap125F}.
     * <pre>
     * <font style='background-color: #7B3CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B3CCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B3CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B3CCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B3CCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B3CCA'>&nbsp;@&nbsp;</font><font style='background-color: #7B3CCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B3CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B3CCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_VIOLET = -0x1.d11acap125F;
    static { NAMED.put("true pure violet", -0x1.d11acap125F); LIST.add(-0x1.d11acap125F); }

    /**
     * This color constant "bright pure violet" has RGBA8888 code {@code 995BF0FF}, L 0.49803922, A 0.54901963, B 0.40784314, alpha 1.0, hue 0.82780534, saturation 0.7028334, and chroma 0.20795049.
     * It can be represented as a packed float with the constant {@code -0x1.d118fep125F}.
     * <pre>
     * <font style='background-color: #995BF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #995BF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #995BF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #995BF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #995BF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #995BF0'>&nbsp;@&nbsp;</font><font style='background-color: #995BF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #995BF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #995BF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_VIOLET = -0x1.d118fep125F;
    static { NAMED.put("bright pure violet", -0x1.d118fep125F); LIST.add(-0x1.d118fep125F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 6F16BAFF}, L 0.33333334, A 0.5647059, B 0.4, alpha 1.0, hue 0.84141475, saturation 0.8600897, and chroma 0.2372866.
     * It can be represented as a packed float with the constant {@code -0x1.cd20aap125F}.
     * <pre>
     * <font style='background-color: #6F16BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F16BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F16BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F16BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F16BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F16BA'>&nbsp;@&nbsp;</font><font style='background-color: #6F16BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F16BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F16BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.cd20aap125F;
    static { NAMED.put("deep purple violet", -0x1.cd20aap125F); LIST.add(-0x1.cd20aap125F); }

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
     * This color constant "bright purple violet" has RGBA8888 code {@code A755FFFF}, L 0.5137255, A 0.56078434, B 0.4, alpha 1.0, hue 0.8369341, saturation 0.8833778, and chroma 0.23313475.
     * It can be represented as a packed float with the constant {@code -0x1.cd1f06p125F}.
     * <pre>
     * <font style='background-color: #A755FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A755FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A755FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A755FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A755FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A755FF'>&nbsp;@&nbsp;</font><font style='background-color: #A755FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A755FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A755FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.cd1f06p125F;
    static { NAMED.put("bright purple violet", -0x1.cd1f06p125F); LIST.add(-0x1.cd1f06p125F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 7922ACFF}, L 0.34509805, A 0.5686275, B 0.41568628, alpha 1.0, hue 0.85874414, saturation 0.7165084, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.d522bp125F}.
     * <pre>
     * <font style='background-color: #7922AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7922AC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7922AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7922AC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7922AC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7922AC'>&nbsp;@&nbsp;</font><font style='background-color: #7922AC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7922AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7922AC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.d522bp125F;
    static { NAMED.put("deep violet purple", -0x1.d522bp125F); LIST.add(-0x1.d522bp125F); }

    /**
     * This color constant "true violet purple" has RGBA8888 code {@code 933CCAFF}, L 0.42352942, A 0.5686275, B 0.41568628, alpha 1.0, hue 0.85874414, saturation 0.5392746, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.d522d8p125F}.
     * <pre>
     * <font style='background-color: #933CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #933CCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #933CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #933CCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #933CCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #933CCA'>&nbsp;@&nbsp;</font><font style='background-color: #933CCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #933CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #933CCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_VIOLET_PURPLE = -0x1.d522d8p125F;
    static { NAMED.put("true violet purple", -0x1.d522d8p125F); LIST.add(-0x1.d522d8p125F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code B55BF0FF}, L 0.5294118, A 0.5686275, B 0.41568628, alpha 1.0, hue 0.85874414, saturation 0.67467767, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.d5230ep125F}.
     * <pre>
     * <font style='background-color: #B55BF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B55BF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B55BF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B55BF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B55BF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B55BF0'>&nbsp;@&nbsp;</font><font style='background-color: #B55BF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B55BF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B55BF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.d5230ep125F;
    static { NAMED.put("bright violet purple", -0x1.d5230ep125F); LIST.add(-0x1.d5230ep125F); }

    /**
     * This color constant "deep pure purple" has RGBA8888 code {@code 880FB4FF}, L 0.35686275, A 0.58431375, B 0.4117647, alpha 1.0, hue 0.8713863, saturation 0.93119407, and chroma 0.24313073.
     * It can be represented as a packed float with the constant {@code -0x1.d32ab6p125F}.
     * <pre>
     * <font style='background-color: #880FB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #880FB4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #880FB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #880FB4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #880FB4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #880FB4'>&nbsp;@&nbsp;</font><font style='background-color: #880FB4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #880FB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #880FB4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_PURPLE = -0x1.d32ab6p125F;
    static { NAMED.put("deep pure purple", -0x1.d32ab6p125F); LIST.add(-0x1.d32ab6p125F); }

    /**
     * This color constant "true pure purple" has RGBA8888 code {@code AA37D9FF}, L 0.45490196, A 0.58431375, B 0.4117647, alpha 1.0, hue 0.8713863, saturation 0.6449948, and chroma 0.24313073.
     * It can be represented as a packed float with the constant {@code -0x1.d32ae8p125F}.
     * <pre>
     * <font style='background-color: #AA37D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA37D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA37D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA37D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA37D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA37D9'>&nbsp;@&nbsp;</font><font style='background-color: #AA37D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA37D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA37D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_PURPLE = -0x1.d32ae8p125F;
    static { NAMED.put("true pure purple", -0x1.d32ae8p125F); LIST.add(-0x1.d32ae8p125F); }

    /**
     * This color constant "bright pure purple" has RGBA8888 code {@code CB56FEFF}, L 0.5568628, A 0.58431375, B 0.4117647, alpha 1.0, hue 0.8713863, saturation 0.8256167, and chroma 0.24313073.
     * It can be represented as a packed float with the constant {@code -0x1.d32b1cp125F}.
     * <pre>
     * <font style='background-color: #CB56FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB56FE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB56FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB56FE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB56FE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB56FE'>&nbsp;@&nbsp;</font><font style='background-color: #CB56FE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB56FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB56FE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_PURPLE = -0x1.d32b1cp125F;
    static { NAMED.put("bright pure purple", -0x1.d32b1cp125F); LIST.add(-0x1.d32b1cp125F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code 9027B0FF}, L 0.38039216, A 0.5803922, B 0.42352942, alpha 1.0, hue 0.878975, saturation 0.70277095, and chroma 0.2210399.
     * It can be represented as a packed float with the constant {@code -0x1.d928c2p125F}.
     * <pre>
     * <font style='background-color: #9027B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9027B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9027B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9027B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9027B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9027B0'>&nbsp;@&nbsp;</font><font style='background-color: #9027B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9027B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9027B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.d928c2p125F;
    static { NAMED.put("deep magenta purple", -0x1.d928c2p125F); LIST.add(-0x1.d928c2p125F); }

    /**
     * This color constant "true magenta purple" has RGBA8888 code {@code B74AD9FF}, L 0.49411765, A 0.5803922, B 0.42352942, alpha 1.0, hue 0.878975, saturation 0.48206556, and chroma 0.2210399.
     * It can be represented as a packed float with the constant {@code -0x1.d928fcp125F}.
     * <pre>
     * <font style='background-color: #B74AD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B74AD9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B74AD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B74AD9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B74AD9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B74AD9'>&nbsp;@&nbsp;</font><font style='background-color: #B74AD9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B74AD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B74AD9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_PURPLE = -0x1.d928fcp125F;
    static { NAMED.put("true magenta purple", -0x1.d928fcp125F); LIST.add(-0x1.d928fcp125F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code D262F6FF}, L 0.5803922, A 0.5803922, B 0.42352942, alpha 1.0, hue 0.878975, saturation 0.70277095, and chroma 0.2210399.
     * It can be represented as a packed float with the constant {@code -0x1.d92928p125F}.
     * <pre>
     * <font style='background-color: #D262F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D262F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D262F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D262F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D262F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D262F6'>&nbsp;@&nbsp;</font><font style='background-color: #D262F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D262F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D262F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.d92928p125F;
    static { NAMED.put("bright magenta purple", -0x1.d92928p125F); LIST.add(-0x1.d92928p125F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code A612B6FF}, L 0.4, A 0.6, B 0.42352942, alpha 1.0, hue 0.89608383, saturation 0.90469134, and chroma 0.2507922.
     * It can be represented as a packed float with the constant {@code -0x1.d932ccp125F}.
     * <pre>
     * <font style='background-color: #A612B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A612B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A612B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A612B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A612B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A612B6'>&nbsp;@&nbsp;</font><font style='background-color: #A612B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A612B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A612B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.d932ccp125F;
    static { NAMED.put("deep purple magenta", -0x1.d932ccp125F); LIST.add(-0x1.d932ccp125F); }

    /**
     * This color constant "true purple magenta" has RGBA8888 code {@code C535D5FF}, L 0.4862745, A 0.6, B 0.42352942, alpha 1.0, hue 0.89608383, saturation 0.6689115, and chroma 0.2507922.
     * It can be represented as a packed float with the constant {@code -0x1.d932f8p125F}.
     * <pre>
     * <font style='background-color: #C535D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C535D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C535D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C535D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C535D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C535D5'>&nbsp;@&nbsp;</font><font style='background-color: #C535D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C535D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C535D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURPLE_MAGENTA = -0x1.d932f8p125F;
    static { NAMED.put("true purple magenta", -0x1.d932f8p125F); LIST.add(-0x1.d932f8p125F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code EB57FBFF}, L 0.6, A 0.6, B 0.42352942, alpha 1.0, hue 0.89608383, saturation 0.7630154, and chroma 0.2507922.
     * It can be represented as a packed float with the constant {@code -0x1.d93332p125F}.
     * <pre>
     * <font style='background-color: #EB57FB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB57FB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB57FB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EB57FB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EB57FB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EB57FB'>&nbsp;@&nbsp;</font><font style='background-color: #EB57FB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB57FB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB57FB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.d93332p125F;
    static { NAMED.put("bright purple magenta", -0x1.d93332p125F); LIST.add(-0x1.d93332p125F); }

    /**
     * This color constant "deep pure magenta" has RGBA8888 code {@code AC26AEFF}, L 0.41568628, A 0.59607846, B 0.43529412, alpha 1.0, hue 0.90565705, saturation 0.74377966, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.df30d4p125F}.
     * <pre>
     * <font style='background-color: #AC26AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC26AE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC26AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC26AE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC26AE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC26AE'>&nbsp;@&nbsp;</font><font style='background-color: #AC26AE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC26AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC26AE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURE_MAGENTA = -0x1.df30d4p125F;
    static { NAMED.put("deep pure magenta", -0x1.df30d4p125F); LIST.add(-0x1.df30d4p125F); }

    /**
     * This color constant "true pure magenta" has RGBA8888 code {@code D247D2FF}, L 0.52156866, A 0.59607846, B 0.43529412, alpha 1.0, hue 0.90565705, saturation 0.5385595, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.df310ap125F}.
     * <pre>
     * <font style='background-color: #D247D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D247D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D247D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D247D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D247D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D247D2'>&nbsp;@&nbsp;</font><font style='background-color: #D247D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D247D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D247D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_PURE_MAGENTA = -0x1.df310ap125F;
    static { NAMED.put("true pure magenta", -0x1.df310ap125F); LIST.add(-0x1.df310ap125F); }

    /**
     * This color constant "bright pure magenta" has RGBA8888 code {@code F262F1FF}, L 0.61960787, A 0.59607846, B 0.43529412, alpha 1.0, hue 0.90565705, saturation 0.64602715, and chroma 0.23076649.
     * It can be represented as a packed float with the constant {@code -0x1.df313cp125F}.
     * <pre>
     * <font style='background-color: #F262F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F262F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F262F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F262F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F262F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F262F1'>&nbsp;@&nbsp;</font><font style='background-color: #F262F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F262F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F262F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURE_MAGENTA = -0x1.df313cp125F;
    static { NAMED.put("bright pure magenta", -0x1.df313cp125F); LIST.add(-0x1.df313cp125F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code B80C77FF}, L 0.39215687, A 0.60784316, B 0.47843137, alpha 1.0, hue 0.96857655, saturation 0.8886378, and chroma 0.2190985.
     * It can be represented as a packed float with the constant {@code -0x1.f536c8p125F}.
     * <pre>
     * <font style='background-color: #B80C77;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B80C77; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B80C77;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B80C77'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B80C77'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B80C77'>&nbsp;@&nbsp;</font><font style='background-color: #B80C77; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B80C77;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B80C77; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.f536c8p125F;
    static { NAMED.put("deep red magenta", -0x1.f536c8p125F); LIST.add(-0x1.f536c8p125F); }

    /**
     * This color constant "true red magenta" has RGBA8888 code {@code E03898FF}, L 0.49803922, A 0.60784316, B 0.4745098, alpha 1.0, hue 0.96305966, saturation 0.6247738, and chroma 0.22076361.
     * It can be represented as a packed float with the constant {@code -0x1.f336fep125F}.
     * <pre>
     * <font style='background-color: #E03898;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E03898; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E03898;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E03898'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E03898'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E03898'>&nbsp;@&nbsp;</font><font style='background-color: #E03898; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E03898;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E03898; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_RED_MAGENTA = -0x1.f336fep125F;
    static { NAMED.put("true red magenta", -0x1.f336fep125F); LIST.add(-0x1.f336fep125F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code FC4FAFFF}, L 0.5764706, A 0.60784316, B 0.4745098, alpha 1.0, hue 0.96305966, saturation 0.76774234, and chroma 0.22076361.
     * It can be represented as a packed float with the constant {@code -0x1.f33726p125F}.
     * <pre>
     * <font style='background-color: #FC4FAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC4FAF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC4FAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC4FAF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC4FAF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC4FAF'>&nbsp;@&nbsp;</font><font style='background-color: #FC4FAF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC4FAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC4FAF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.f33726p125F;
    static { NAMED.put("bright red magenta", -0x1.f33726p125F); LIST.add(-0x1.f33726p125F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code AD2349FF}, L 0.37254903, A 0.5882353, B 0.5137255, alpha 1.0, hue 0.024573287, saturation 0.69825774, and chroma 0.17789528.
     * It can be represented as a packed float with the constant {@code -0x1.072cbep126F}.
     * <pre>
     * <font style='background-color: #AD2349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD2349; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD2349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD2349'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD2349'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD2349'>&nbsp;@&nbsp;</font><font style='background-color: #AD2349; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD2349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD2349; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.072cbep126F;
    static { NAMED.put("deep magenta red", -0x1.072cbep126F); LIST.add(-0x1.072cbep126F); }

    /**
     * This color constant "true magenta red" has RGBA8888 code {@code CD3D5FFF}, L 0.45490196, A 0.5882353, B 0.5137255, alpha 1.0, hue 0.024573287, saturation 0.51435304, and chroma 0.17789528.
     * It can be represented as a packed float with the constant {@code -0x1.072ce8p126F}.
     * <pre>
     * <font style='background-color: #CD3D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD3D5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD3D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD3D5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD3D5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD3D5F'>&nbsp;@&nbsp;</font><font style='background-color: #CD3D5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD3D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD3D5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRUE_MAGENTA_RED = -0x1.072ce8p126F;
    static { NAMED.put("true magenta red", -0x1.072ce8p126F); LIST.add(-0x1.072ce8p126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code EF5778FF}, L 0.54901963, A 0.5882353, B 0.5137255, alpha 1.0, hue 0.024573287, saturation 0.5858343, and chroma 0.17789528.
     * It can be represented as a packed float with the constant {@code -0x1.072d18p126F}.
     * <pre>
     * <font style='background-color: #EF5778;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF5778; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF5778;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF5778'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF5778'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF5778'>&nbsp;@&nbsp;</font><font style='background-color: #EF5778; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF5778;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF5778; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.072d18p126F;
    static { NAMED.put("bright magenta red", -0x1.072d18p126F); LIST.add(-0x1.072d18p126F); }

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
     * This color constant "bold brown red" has RGBA8888 code {@code F83E1DFF}, L 0.5137255, A 0.5921569, B 0.56078434, alpha 1.0, hue 0.09281131, saturation 0.7861616, and chroma 0.21993263.
     * It can be represented as a packed float with the constant {@code -0x1.1f2f06p126F}.
     * <pre>
     * <font style='background-color: #F83E1D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F83E1D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F83E1D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F83E1D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F83E1D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F83E1D'>&nbsp;@&nbsp;</font><font style='background-color: #F83E1D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F83E1D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F83E1D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_RED = -0x1.1f2f06p126F;
    static { NAMED.put("bold brown red", -0x1.1f2f06p126F); LIST.add(-0x1.1f2f06p126F); }

    /**
     * This color constant "bold red brown" has RGBA8888 code {@code FF4600FF}, L 0.53333336, A 0.5882353, B 0.5647059, alpha 1.0, hue 0.10071799, saturation 0.82331944, and chroma 0.21798135.
     * It can be represented as a packed float with the constant {@code -0x1.212d1p126F}.
     * <pre>
     * <font style='background-color: #FF4600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF4600; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF4600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF4600'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF4600'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF4600'>&nbsp;@&nbsp;</font><font style='background-color: #FF4600; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF4600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF4600; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_RED_BROWN = -0x1.212d1p126F;
    static { NAMED.put("bold red brown", -0x1.212d1p126F); LIST.add(-0x1.212d1p126F); }

    /**
     * This color constant "bold pure brown" has RGBA8888 code {@code DF6F44FF}, L 0.5529412, A 0.5529412, B 0.54901963, alpha 1.0, hue 0.11888637, saturation 0.45585388, and chroma 0.14373726.
     * It can be represented as a packed float with the constant {@code -0x1.191b1ap126F}.
     * <pre>
     * <font style='background-color: #DF6F44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF6F44; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF6F44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF6F44'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF6F44'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF6F44'>&nbsp;@&nbsp;</font><font style='background-color: #DF6F44; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF6F44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF6F44; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_BROWN = -0x1.191b1ap126F;
    static { NAMED.put("bold pure brown", -0x1.191b1ap126F); LIST.add(-0x1.191b1ap126F); }

    /**
     * This color constant "bold orange brown" has RGBA8888 code {@code EC753BFF}, L 0.5803922, A 0.5529412, B 0.5568628, alpha 1.0, hue 0.13067731, saturation 0.5696145, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.1d1b28p126F}.
     * <pre>
     * <font style='background-color: #EC753B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC753B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC753B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EC753B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EC753B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EC753B'>&nbsp;@&nbsp;</font><font style='background-color: #EC753B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC753B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC753B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_ORANGE_BROWN = -0x1.1d1b28p126F;
    static { NAMED.put("bold orange brown", -0x1.1d1b28p126F); LIST.add(-0x1.1d1b28p126F); }

    /**
     * This color constant "bold brown orange" has RGBA8888 code {@code F77821FF}, L 0.6, A 0.54901963, B 0.5686275, alpha 1.0, hue 0.15127131, saturation 0.75502497, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.231932p126F}.
     * <pre>
     * <font style='background-color: #F77821;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F77821; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F77821;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F77821'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F77821'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F77821'>&nbsp;@&nbsp;</font><font style='background-color: #F77821; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F77821;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F77821; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_BROWN_ORANGE = -0x1.231932p126F;
    static { NAMED.put("bold brown orange", -0x1.231932p126F); LIST.add(-0x1.231932p126F); }

    /**
     * This color constant "bold pure orange" has RGBA8888 code {@code FE7800FF}, L 0.60784316, A 0.5529412, B 0.57254905, alpha 1.0, hue 0.1496556, saturation 0.85623914, and chroma 0.1789216.
     * It can be represented as a packed float with the constant {@code -0x1.251b36p126F}.
     * <pre>
     * <font style='background-color: #FE7800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE7800; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE7800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FE7800'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FE7800'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FE7800'>&nbsp;@&nbsp;</font><font style='background-color: #FE7800; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE7800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE7800; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLD_PURE_ORANGE = -0x1.251b36p126F;
    static { NAMED.put("bold pure orange", -0x1.251b36p126F); LIST.add(-0x1.251b36p126F); }

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
