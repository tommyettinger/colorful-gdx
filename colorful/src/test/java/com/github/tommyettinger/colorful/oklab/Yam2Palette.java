package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;

import java.util.Comparator;

/**
 * A palette of predefined colors as packed Oklab floats, the kind {@link ColorTools} works with. This uses a geometric
 * palette, Yam2, that is designed to be as consistent as possible in its support for hues while keeping lots of
 * grayscale, desaturated, and mid-saturation colors, and to have a coherent naming system. This is the successor to
 * Yam (1), and may still be adjusted as needed (this may or may not be in another version like Yam3). The original Yam
 * had too many colors that were indistinguishable from grayscale, despite being named like they had a non-gray color
 * included in their mix. Yam and Yam2 share the same shape and distribution of colors, though, and both have 255 opaque
 * colors, plus pure transparent.
 * <br>
 * You can access colors by their constant name, such as {@code PALE_GREEN_CYAN}, by the {@link #NAMED} map using
 * {@code NAMED.get("pale green cyan", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access
 * a float color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default
 * because it will not occur in a valid Oklab color. You can access the names in a specific order with {@link #NAMES}
 * (which is alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow
 * to blue to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * The names here put the most relevant color last, so "pale green cyan" is more cyan than it is green. There are 12
 * words used for hue; in order, they are: red, brown, orange, saffron, yellow, lime, green, cyan, blue, violet, purple,
 * and magenta. There are four very-desaturated colors per hue, called "darker gray HUE", "dark gray HUE", "light gray
 * HUE", and "lighter gray HUE" (for each of the twelve hues). Going towards more saturated colors, there are drab,
 * dull, and pale variants of "HUE2 HUE1" and "HUE1 HUE2", where the last hue is more important, and hues are only
 * paired with their neighbors in the order. Getting even more saturated, there are "bright" and "deep" variants of
 * "HUE1", "HUE2 HUE1", and "HUE1 HUE2". At the most saturated edge, there are "some HUE2 HUE1", "more HUE2 HUE1", "more
 * HUE1 HUE2", and "some HUE1 HUE2", forming a rainbow-colored band with medium lightness consistently across it. In
 * this last one, "some violet blue" is primarily blue and only has a little violet tinge, while "more violet blue" has
 * more violet, and "more blue violet" becomes primarily violet.
 */
public class Yam2Palette {
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
     * This color constant "darker gray red" has RGBA8888 code {@code 3D1913FF}, L 0.1764706, A 0.5254902, B 0.5176471, alpha 1.0, hue 0.09638812, saturation 0.3305785, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.090c5ap126F}.
     * <pre>
     * <font style='background-color: #3D1913;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D1913; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D1913;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3D1913'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3D1913'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3D1913'>&nbsp;@&nbsp;</font><font style='background-color: #3D1913; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D1913;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D1913; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_RED = -0x1.090c5ap126F;
    static { NAMED.put("darker gray red", -0x1.090c5ap126F); LIST.add(-0x1.090c5ap126F); }

    /**
     * This color constant "dark gray red" has RGBA8888 code {@code 7C4C43FF}, L 0.36078432, A 0.5254902, B 0.5176471, alpha 1.0, hue 0.09638812, saturation 0.11080332, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.090cb8p126F}.
     * <pre>
     * <font style='background-color: #7C4C43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C4C43; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C4C43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C4C43'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C4C43'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C4C43'>&nbsp;@&nbsp;</font><font style='background-color: #7C4C43; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C4C43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C4C43; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_RED = -0x1.090cb8p126F;
    static { NAMED.put("dark gray red", -0x1.090cb8p126F); LIST.add(-0x1.090cb8p126F); }

    /**
     * This color constant "light gray red" has RGBA8888 code {@code B98076FF}, L 0.54901963, A 0.5294118, B 0.5137255, alpha 1.0, hue 0.06948605, saturation 0.082873344, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.070f18p126F}.
     * <pre>
     * <font style='background-color: #B98076;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B98076; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B98076;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B98076'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B98076'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B98076'>&nbsp;@&nbsp;</font><font style='background-color: #B98076; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B98076;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B98076; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_RED = -0x1.070f18p126F;
    static { NAMED.put("light gray red", -0x1.070f18p126F); LIST.add(-0x1.070f18p126F); }

    /**
     * This color constant "lighter gray red" has RGBA8888 code {@code EFB0A4FF}, L 0.73333335, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.08601887, saturation 0.3767313, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090f76p126F}.
     * <pre>
     * <font style='background-color: #EFB0A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFB0A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFB0A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFB0A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFB0A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFB0A4'>&nbsp;@&nbsp;</font><font style='background-color: #EFB0A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFB0A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFB0A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_RED = -0x1.090f76p126F;
    static { NAMED.put("lighter gray red", -0x1.090f76p126F); LIST.add(-0x1.090f76p126F); }

    /**
     * This color constant "darker gray brown" has RGBA8888 code {@code 342620FF}, L 0.19215687, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.0905387, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.050462p126F}.
     * <pre>
     * <font style='background-color: #342620;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #342620; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #342620;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #342620'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #342620'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #342620'>&nbsp;@&nbsp;</font><font style='background-color: #342620; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #342620;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #342620; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_BROWN = -0x1.050462p126F;
    static { NAMED.put("darker gray brown", -0x1.050462p126F); LIST.add(-0x1.050462p126F); }

    /**
     * This color constant "dark gray brown" has RGBA8888 code {@code 6F5C54FF}, L 0.38431373, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.030483158, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.0504c4p126F}.
     * <pre>
     * <font style='background-color: #6F5C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F5C54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F5C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F5C54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F5C54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F5C54'>&nbsp;@&nbsp;</font><font style='background-color: #6F5C54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F5C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F5C54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_BROWN = -0x1.0504c4p126F;
    static { NAMED.put("dark gray brown", -0x1.0504c4p126F); LIST.add(-0x1.0504c4p126F); }

    /**
     * This color constant "light gray brown" has RGBA8888 code {@code A89289FF}, L 0.5764706, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.08601887, saturation 0.012335601, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.030526p126F}.
     * <pre>
     * <font style='background-color: #A89289;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A89289; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A89289;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A89289'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A89289'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A89289'>&nbsp;@&nbsp;</font><font style='background-color: #A89289; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A89289;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A89289; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_BROWN = -0x1.030526p126F;
    static { NAMED.put("light gray brown", -0x1.030526p126F); LIST.add(-0x1.030526p126F); }

    /**
     * This color constant "lighter gray brown" has RGBA8888 code {@code DCC4BAFF}, L 0.76862746, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.08601887, saturation 0.056643065, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.030588p126F}.
     * <pre>
     * <font style='background-color: #DCC4BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCC4BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCC4BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DCC4BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DCC4BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DCC4BA'>&nbsp;@&nbsp;</font><font style='background-color: #DCC4BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCC4BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCC4BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_BROWN = -0x1.030588p126F;
    static { NAMED.put("lighter gray brown", -0x1.030588p126F); LIST.add(-0x1.030588p126F); }

    /**
     * This color constant "darker gray orange" has RGBA8888 code {@code 3B2414FF}, L 0.19607843, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15979148, saturation 0.36776635, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b0664p126F}.
     * <pre>
     * <font style='background-color: #3B2414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B2414; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B2414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B2414'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B2414'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B2414'>&nbsp;@&nbsp;</font><font style='background-color: #3B2414; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B2414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B2414; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_ORANGE = -0x1.0b0664p126F;
    static { NAMED.put("darker gray orange", -0x1.0b0664p126F); LIST.add(-0x1.0b0664p126F); }

    /**
     * This color constant "dark gray orange" has RGBA8888 code {@code 7B5D48FF}, L 0.39607844, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15979148, saturation 0.13489386, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b06cap126F}.
     * <pre>
     * <font style='background-color: #7B5D48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B5D48; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B5D48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B5D48'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B5D48'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B5D48'>&nbsp;@&nbsp;</font><font style='background-color: #7B5D48; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B5D48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B5D48; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_ORANGE = -0x1.0b06cap126F;
    static { NAMED.put("dark gray orange", -0x1.0b06cap126F); LIST.add(-0x1.0b06cap126F); }

    /**
     * This color constant "light gray orange" has RGBA8888 code {@code B8957DFF}, L 0.59607846, A 0.5137255, B 0.5176471, alpha 1.0, hue 0.14477962, saturation 0.05305581, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.09073p126F}.
     * <pre>
     * <font style='background-color: #B8957D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8957D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8957D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B8957D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B8957D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B8957D'>&nbsp;@&nbsp;</font><font style='background-color: #B8957D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8957D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8957D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_ORANGE = -0x1.09073p126F;
    static { NAMED.put("light gray orange", -0x1.09073p126F); LIST.add(-0x1.09073p126F); }

    /**
     * This color constant "lighter gray orange" has RGBA8888 code {@code EEC8ADFF}, L 0.79607844, A 0.50980395, B 0.52156866, alpha 1.0, hue 0.18210676, saturation 0.17974761, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0b0596p126F}.
     * <pre>
     * <font style='background-color: #EEC8AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEC8AD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEC8AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EEC8AD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EEC8AD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EEC8AD'>&nbsp;@&nbsp;</font><font style='background-color: #EEC8AD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEC8AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEC8AD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_ORANGE = -0x1.0b0596p126F;
    static { NAMED.put("lighter gray orange", -0x1.0b0596p126F); LIST.add(-0x1.0b0596p126F); }

    /**
     * This color constant "darker gray saffron" has RGBA8888 code {@code 392E19FF}, L 0.21568628, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.235567, saturation 0.32084155, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b006ep126F}.
     * <pre>
     * <font style='background-color: #392E19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #392E19; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #392E19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #392E19'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #392E19'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #392E19'>&nbsp;@&nbsp;</font><font style='background-color: #392E19; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #392E19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #392E19; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_SAFFRON = -0x1.0b006ep126F;
    static { NAMED.put("darker gray saffron", -0x1.0b006ep126F); LIST.add(-0x1.0b006ep126F); }

    /**
     * This color constant "dark gray saffron" has RGBA8888 code {@code 776A4FFF}, L 0.41960785, A 0.49803922, B 0.52156866, alpha 1.0, hue 0.264433, saturation 0.11550296, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0afed6p126F}.
     * <pre>
     * <font style='background-color: #776A4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #776A4F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #776A4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #776A4F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #776A4F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #776A4F'>&nbsp;@&nbsp;</font><font style='background-color: #776A4F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #776A4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #776A4F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_SAFFRON = -0x1.0afed6p126F;
    static { NAMED.put("dark gray saffron", -0x1.0afed6p126F); LIST.add(-0x1.0afed6p126F); }

    /**
     * This color constant "light gray saffron" has RGBA8888 code {@code B1A183FF}, L 0.61960787, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.235567, saturation 0.070837565, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b013cp126F}.
     * <pre>
     * <font style='background-color: #B1A183;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1A183; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1A183;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1A183'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1A183'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1A183'>&nbsp;@&nbsp;</font><font style='background-color: #B1A183; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1A183;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1A183; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_SAFFRON = -0x1.0b013cp126F;
    static { NAMED.put("light gray saffron", -0x1.0b013cp126F); LIST.add(-0x1.0b013cp126F); }

    /**
     * This color constant "lighter gray saffron" has RGBA8888 code {@code E6D5B4FF}, L 0.8235294, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.235567, saturation 0.0915744, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b01a4p126F}.
     * <pre>
     * <font style='background-color: #E6D5B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6D5B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6D5B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6D5B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6D5B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6D5B4'>&nbsp;@&nbsp;</font><font style='background-color: #E6D5B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6D5B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6D5B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_SAFFRON = -0x1.0b01a4p126F;
    static { NAMED.put("lighter gray saffron", -0x1.0b01a4p126F); LIST.add(-0x1.0b01a4p126F); }

    /**
     * This color constant "darker gray yellow" has RGBA8888 code {@code 353914FF}, L 0.23529412, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.4961521, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef878p126F}.
     * <pre>
     * <font style='background-color: #353914;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #353914; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #353914;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #353914'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #353914'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #353914'>&nbsp;@&nbsp;</font><font style='background-color: #353914; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #353914;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #353914; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_YELLOW = -0x1.0ef878p126F;
    static { NAMED.put("darker gray yellow", -0x1.0ef878p126F); LIST.add(-0x1.0ef878p126F); }

    /**
     * This color constant "dark gray yellow" has RGBA8888 code {@code 73794DFF}, L 0.4509804, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.19484444, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef8e6p126F}.
     * <pre>
     * <font style='background-color: #73794D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73794D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73794D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73794D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73794D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73794D'>&nbsp;@&nbsp;</font><font style='background-color: #73794D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73794D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73794D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_YELLOW = -0x1.0ef8e6p126F;
    static { NAMED.put("dark gray yellow", -0x1.0ef8e6p126F); LIST.add(-0x1.0ef8e6p126F); }

    /**
     * This color constant "light gray yellow" has RGBA8888 code {@code ADB482FF}, L 0.6627451, A 0.4862745, B 0.5294118, alpha 1.0, hue 0.31948605, saturation 0.11648422, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0ef952p126F}.
     * <pre>
     * <font style='background-color: #ADB482;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ADB482; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ADB482;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ADB482'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ADB482'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ADB482'>&nbsp;@&nbsp;</font><font style='background-color: #ADB482; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ADB482;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ADB482; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_YELLOW = -0x1.0ef952p126F;
    static { NAMED.put("light gray yellow", -0x1.0ef952p126F); LIST.add(-0x1.0ef952p126F); }

    /**
     * This color constant "lighter gray yellow" has RGBA8888 code {@code E2EBB4FF}, L 0.8784314, A 0.48235294, B 0.5294118, alpha 1.0, hue 0.33601886, saturation 0.08090422, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.0ef7cp126F}.
     * <pre>
     * <font style='background-color: #E2EBB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2EBB4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2EBB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2EBB4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2EBB4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2EBB4'>&nbsp;@&nbsp;</font><font style='background-color: #E2EBB4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2EBB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2EBB4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_YELLOW = -0x1.0ef7cp126F;
    static { NAMED.put("lighter gray yellow", -0x1.0ef7cp126F); LIST.add(-0x1.0ef7cp126F); }

    /**
     * This color constant "darker gray lime" has RGBA8888 code {@code 27391DFF}, L 0.22745098, A 0.47843137, B 0.5176471, alpha 1.0, hue 0.39085212, saturation 0.1912426, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.08f474p126F}.
     * <pre>
     * <font style='background-color: #27391D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #27391D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #27391D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #27391D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #27391D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #27391D'>&nbsp;@&nbsp;</font><font style='background-color: #27391D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #27391D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #27391D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_LIME = -0x1.08f474p126F;
    static { NAMED.put("darker gray lime", -0x1.08f474p126F); LIST.add(-0x1.08f474p126F); }

    /**
     * This color constant "dark gray lime" has RGBA8888 code {@code 627955FF}, L 0.4392157, A 0.47843137, B 0.52156866, alpha 1.0, hue 0.375, saturation 0.09876543, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af4ep126F}.
     * <pre>
     * <font style='background-color: #627955;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #627955; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #627955;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #627955'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #627955'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #627955'>&nbsp;@&nbsp;</font><font style='background-color: #627955; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #627955;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #627955; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_LIME = -0x1.0af4ep126F;
    static { NAMED.put("dark gray lime", -0x1.0af4ep126F); LIST.add(-0x1.0af4ep126F); }

    /**
     * This color constant "light gray lime" has RGBA8888 code {@code 9AB58CFF}, L 0.6509804, A 0.47843137, B 0.52156866, alpha 1.0, hue 0.375, saturation 0.06001612, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af54cp126F}.
     * <pre>
     * <font style='background-color: #9AB58C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9AB58C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9AB58C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9AB58C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9AB58C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9AB58C'>&nbsp;@&nbsp;</font><font style='background-color: #9AB58C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9AB58C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9AB58C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_LIME = -0x1.0af54cp126F;
    static { NAMED.put("light gray lime", -0x1.0af54cp126F); LIST.add(-0x1.0af54cp126F); }

    /**
     * This color constant "lighter gray lime" has RGBA8888 code {@code CDEABDFF}, L 0.85882354, A 0.47843137, B 0.52156866, alpha 1.0, hue 0.375, saturation 0.073194705, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0af5b6p126F}.
     * <pre>
     * <font style='background-color: #CDEABD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDEABD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDEABD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CDEABD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CDEABD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CDEABD'>&nbsp;@&nbsp;</font><font style='background-color: #CDEABD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDEABD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDEABD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_LIME = -0x1.0af5b6p126F;
    static { NAMED.put("lighter gray lime", -0x1.0af5b6p126F); LIST.add(-0x1.0af5b6p126F); }

    /**
     * This color constant "darker gray green" has RGBA8888 code {@code 14391AFF}, L 0.21568628, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.7412534, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aec6ep126F}.
     * <pre>
     * <font style='background-color: #14391A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14391A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14391A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #14391A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #14391A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #14391A'>&nbsp;@&nbsp;</font><font style='background-color: #14391A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14391A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14391A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_GREEN = -0x1.0aec6ep126F;
    static { NAMED.put("darker gray green", -0x1.0aec6ep126F); LIST.add(-0x1.0aec6ep126F); }

    /**
     * This color constant "dark gray green" has RGBA8888 code {@code 457049FF}, L 0.39215687, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.29385763, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aecc8p126F}.
     * <pre>
     * <font style='background-color: #457049;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #457049; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #457049;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #457049'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #457049'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #457049'>&nbsp;@&nbsp;</font><font style='background-color: #457049; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #457049;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #457049; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_GREEN = -0x1.0aecc8p126F;
    static { NAMED.put("dark gray green", -0x1.0aecc8p126F); LIST.add(-0x1.0aecc8p126F); }

    /**
     * This color constant "light gray green" has RGBA8888 code {@code 73A478FF}, L 0.5686275, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.17487529, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed22p126F}.
     * <pre>
     * <font style='background-color: #73A478;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73A478; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73A478;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73A478'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73A478'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73A478'>&nbsp;@&nbsp;</font><font style='background-color: #73A478; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73A478;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73A478; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_GREEN = -0x1.0aed22p126F;
    static { NAMED.put("light gray green", -0x1.0aed22p126F); LIST.add(-0x1.0aed22p126F); }

    /**
     * This color constant "lighter gray green" has RGBA8888 code {@code 9FD4A4FF}, L 0.74509805, A 0.4627451, B 0.52156866, alpha 1.0, hue 0.41646945, saturation 0.123392, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0aed7cp126F}.
     * <pre>
     * <font style='background-color: #9FD4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FD4A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FD4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9FD4A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9FD4A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9FD4A4'>&nbsp;@&nbsp;</font><font style='background-color: #9FD4A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FD4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FD4A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_GREEN = -0x1.0aed7cp126F;
    static { NAMED.put("lighter gray green", -0x1.0aed7cp126F); LIST.add(-0x1.0aed7cp126F); }

    /**
     * This color constant "darker gray cyan" has RGBA8888 code {@code 173B3FFF}, L 0.23137255, A 0.4745098, B 0.4862745, alpha 1.0, hue 0.57861596, saturation 0.6369613, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.f8f276p125F}.
     * <pre>
     * <font style='background-color: #173B3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #173B3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #173B3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #173B3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #173B3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #173B3F'>&nbsp;@&nbsp;</font><font style='background-color: #173B3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #173B3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #173B3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_CYAN = -0x1.f8f276p125F;
    static { NAMED.put("darker gray cyan", -0x1.f8f276p125F); LIST.add(-0x1.f8f276p125F); }

    /**
     * This color constant "dark gray cyan" has RGBA8888 code {@code 50797EFF}, L 0.4392157, A 0.47843137, B 0.4862745, alpha 1.0, hue 0.59020853, saturation 0.19534616, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.f8f4ep125F}.
     * <pre>
     * <font style='background-color: #50797E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50797E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50797E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #50797E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #50797E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #50797E'>&nbsp;@&nbsp;</font><font style='background-color: #50797E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50797E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50797E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_CYAN = -0x1.f8f4ep125F;
    static { NAMED.put("dark gray cyan", -0x1.f8f4ep125F); LIST.add(-0x1.f8f4ep125F); }

    /**
     * This color constant "light gray cyan" has RGBA8888 code {@code 87B5BBFF}, L 0.6509804, A 0.47843137, B 0.4862745, alpha 1.0, hue 0.59020853, saturation 0.12088889, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.f8f54cp125F}.
     * <pre>
     * <font style='background-color: #87B5BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87B5BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87B5BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87B5BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87B5BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87B5BB'>&nbsp;@&nbsp;</font><font style='background-color: #87B5BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87B5BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87B5BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_CYAN = -0x1.f8f54cp125F;
    static { NAMED.put("light gray cyan", -0x1.f8f54cp125F); LIST.add(-0x1.f8f54cp125F); }

    /**
     * This color constant "lighter gray cyan" has RGBA8888 code {@code B8EAF0FF}, L 0.85882354, A 0.47843137, B 0.4862745, alpha 1.0, hue 0.59020853, saturation 0.33580247, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.f8f5b6p125F}.
     * <pre>
     * <font style='background-color: #B8EAF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8EAF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8EAF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B8EAF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B8EAF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B8EAF0'>&nbsp;@&nbsp;</font><font style='background-color: #B8EAF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B8EAF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B8EAF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_CYAN = -0x1.f8f5b6p125F;
    static { NAMED.put("lighter gray cyan", -0x1.f8f5b6p125F); LIST.add(-0x1.f8f5b6p125F); }

    /**
     * This color constant "darker gray blue" has RGBA8888 code {@code 0A1A42FF}, L 0.14901961, A 0.49019608, B 0.4509804, alpha 1.0, hue 0.71857655, saturation 0.6987369, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e6fa4cp125F}.
     * <pre>
     * <font style='background-color: #0A1A42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A1A42; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A1A42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0A1A42'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0A1A42'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0A1A42'>&nbsp;@&nbsp;</font><font style='background-color: #0A1A42; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A1A42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A1A42; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_BLUE = -0x1.e6fa4cp125F;
    static { NAMED.put("darker gray blue", -0x1.e6fa4cp125F); LIST.add(-0x1.e6fa4cp125F); }

    /**
     * This color constant "dark gray blue" has RGBA8888 code {@code 364F82FF}, L 0.3254902, A 0.49019608, B 0.4509804, alpha 1.0, hue 0.71857655, saturation 0.2188368, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e6faa6p125F}.
     * <pre>
     * <font style='background-color: #364F82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #364F82; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #364F82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #364F82'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #364F82'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #364F82'>&nbsp;@&nbsp;</font><font style='background-color: #364F82; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #364F82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #364F82; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_BLUE = -0x1.e6faa6p125F;
    static { NAMED.put("dark gray blue", -0x1.e6faa6p125F); LIST.add(-0x1.e6faa6p125F); }

    /**
     * This color constant "light gray blue" has RGBA8888 code {@code 6583BDFF}, L 0.5019608, A 0.49019608, B 0.45490196, alpha 1.0, hue 0.7159276, saturation 0.19355403, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.e8fbp125F}.
     * <pre>
     * <font style='background-color: #6583BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6583BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6583BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6583BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6583BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6583BD'>&nbsp;@&nbsp;</font><font style='background-color: #6583BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6583BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6583BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_BLUE = -0x1.e8fbp125F;
    static { NAMED.put("light gray blue", -0x1.e8fbp125F); LIST.add(-0x1.e8fbp125F); }

    /**
     * This color constant "lighter gray blue" has RGBA8888 code {@code 92B3F4FF}, L 0.6784314, A 0.49411765, B 0.4509804, alpha 1.0, hue 0.73098123, saturation 0.6389519, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.e6fd5ap125F}.
     * <pre>
     * <font style='background-color: #92B3F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92B3F4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92B3F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #92B3F4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #92B3F4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #92B3F4'>&nbsp;@&nbsp;</font><font style='background-color: #92B3F4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92B3F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92B3F4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_BLUE = -0x1.e6fd5ap125F;
    static { NAMED.put("lighter gray blue", -0x1.e6fd5ap125F); LIST.add(-0x1.e6fd5ap125F); }

    /**
     * This color constant "darker gray violet" has RGBA8888 code {@code 271B3EFF}, L 0.16862746, A 0.5137255, B 0.4627451, alpha 1.0, hue 0.80616736, saturation 0.2499619, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.ed0656p125F}.
     * <pre>
     * <font style='background-color: #271B3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #271B3E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #271B3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #271B3E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #271B3E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #271B3E'>&nbsp;@&nbsp;</font><font style='background-color: #271B3E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #271B3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #271B3E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_VIOLET = -0x1.ed0656p125F;
    static { NAMED.put("darker gray violet", -0x1.ed0656p125F); LIST.add(-0x1.ed0656p125F); }

    /**
     * This color constant "dark gray violet" has RGBA8888 code {@code 5D4F7DFF}, L 0.3529412, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.08892913, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed08b4p125F}.
     * <pre>
     * <font style='background-color: #5D4F7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D4F7D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D4F7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5D4F7D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5D4F7D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5D4F7D'>&nbsp;@&nbsp;</font><font style='background-color: #5D4F7D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D4F7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D4F7D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_VIOLET = -0x1.ed08b4p125F;
    static { NAMED.put("dark gray violet", -0x1.ed08b4p125F); LIST.add(-0x1.ed08b4p125F); }

    /**
     * This color constant "light gray violet" has RGBA8888 code {@code 9182B7FF}, L 0.53333336, A 0.5137255, B 0.4627451, alpha 1.0, hue 0.80616736, saturation 0.14324395, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.ed071p125F}.
     * <pre>
     * <font style='background-color: #9182B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9182B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9182B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9182B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9182B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9182B7'>&nbsp;@&nbsp;</font><font style='background-color: #9182B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9182B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9182B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_VIOLET = -0x1.ed071p125F;
    static { NAMED.put("light gray violet", -0x1.ed071p125F); LIST.add(-0x1.ed071p125F); }

    /**
     * This color constant "lighter gray violet" has RGBA8888 code {@code C3B2EDFF}, L 0.7176471, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.47514108, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed096ep125F}.
     * <pre>
     * <font style='background-color: #C3B2ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3B2ED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3B2ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3B2ED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3B2ED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3B2ED'>&nbsp;@&nbsp;</font><font style='background-color: #C3B2ED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3B2ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3B2ED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_VIOLET = -0x1.ed096ep125F;
    static { NAMED.put("lighter gray violet", -0x1.ed096ep125F); LIST.add(-0x1.ed096ep125F); }

    /**
     * This color constant "darker gray purple" has RGBA8888 code {@code 321941FF}, L 0.18039216, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.8650731, saturation 0.3467701, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0e5cp125F}.
     * <pre>
     * <font style='background-color: #321941;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #321941; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #321941;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #321941'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #321941'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #321941'>&nbsp;@&nbsp;</font><font style='background-color: #321941; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #321941;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #321941; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_PURPLE = -0x1.ef0e5cp125F;
    static { NAMED.put("darker gray purple", -0x1.ef0e5cp125F); LIST.add(-0x1.ef0e5cp125F); }

    /**
     * This color constant "dark gray purple" has RGBA8888 code {@code 6D4E81FF}, L 0.36862746, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.8650731, saturation 0.11281207, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0ebcp125F}.
     * <pre>
     * <font style='background-color: #6D4E81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D4E81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D4E81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D4E81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D4E81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D4E81'>&nbsp;@&nbsp;</font><font style='background-color: #6D4E81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D4E81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D4E81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_PURPLE = -0x1.ef0ebcp125F;
    static { NAMED.put("dark gray purple", -0x1.ef0ebcp125F); LIST.add(-0x1.ef0ebcp125F); }

    /**
     * This color constant "light gray purple" has RGBA8888 code {@code A683BEFF}, L 0.56078434, A 0.5254902, B 0.4627451, alpha 1.0, hue 0.84551346, saturation 0.16030246, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.ed0d1ep125F}.
     * <pre>
     * <font style='background-color: #A683BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A683BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A683BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A683BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A683BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A683BE'>&nbsp;@&nbsp;</font><font style='background-color: #A683BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A683BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A683BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_PURPLE = -0x1.ed0d1ep125F;
    static { NAMED.put("light gray purple", -0x1.ed0d1ep125F); LIST.add(-0x1.ed0d1ep125F); }

    /**
     * This color constant "lighter gray purple" has RGBA8888 code {@code DAB3F4FF}, L 0.7490196, A 0.5294118, B 0.4627451, alpha 1.0, hue 0.8563733, saturation 0.59057695, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.ed0f7ep125F}.
     * <pre>
     * <font style='background-color: #DAB3F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAB3F4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAB3F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAB3F4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAB3F4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAB3F4'>&nbsp;@&nbsp;</font><font style='background-color: #DAB3F4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAB3F4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAB3F4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_PURPLE = -0x1.ed0f7ep125F;
    static { NAMED.put("lighter gray purple", -0x1.ed0f7ep125F); LIST.add(-0x1.ed0f7ep125F); }

    /**
     * This color constant "darker gray magenta" has RGBA8888 code {@code 3A1B3EFF}, L 0.19215687, A 0.53333336, B 0.47058824, alpha 1.0, hue 0.8849269, saturation 0.3294344, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.f11062p125F}.
     * <pre>
     * <font style='background-color: #3A1B3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A1B3E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A1B3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A1B3E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A1B3E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A1B3E'>&nbsp;@&nbsp;</font><font style='background-color: #3A1B3E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A1B3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A1B3E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_MAGENTA = -0x1.f11062p125F;
    static { NAMED.put("darker gray magenta", -0x1.f11062p125F); LIST.add(-0x1.f11062p125F); }

    /**
     * This color constant "dark gray magenta" has RGBA8888 code {@code 78517DFF}, L 0.38431373, A 0.53333336, B 0.4745098, alpha 1.0, hue 0.89608383, saturation 0.106753685, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.f310c4p125F}.
     * <pre>
     * <font style='background-color: #78517D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78517D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78517D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #78517D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #78517D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #78517D'>&nbsp;@&nbsp;</font><font style='background-color: #78517D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78517D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78517D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_MAGENTA = -0x1.f310c4p125F;
    static { NAMED.put("dark gray magenta", -0x1.f310c4p125F); LIST.add(-0x1.f310c4p125F); }

    /**
     * This color constant "light gray magenta" has RGBA8888 code {@code B486B9FF}, L 0.5803922, A 0.53333336, B 0.47058824, alpha 1.0, hue 0.8849269, saturation 0.10341532, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.f11128p125F}.
     * <pre>
     * <font style='background-color: #B486B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B486B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B486B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B486B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B486B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B486B9'>&nbsp;@&nbsp;</font><font style='background-color: #B486B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B486B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B486B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_MAGENTA = -0x1.f11128p125F;
    static { NAMED.put("light gray magenta", -0x1.f11128p125F); LIST.add(-0x1.f11128p125F); }

    /**
     * This color constant "lighter gray magenta" has RGBA8888 code {@code E9B7EFFF}, L 0.77254903, A 0.53333336, B 0.47058824, alpha 1.0, hue 0.8849269, saturation 0.43184206, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.f1118ap125F}.
     * <pre>
     * <font style='background-color: #E9B7EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9B7EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9B7EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9B7EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9B7EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9B7EF'>&nbsp;@&nbsp;</font><font style='background-color: #E9B7EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9B7EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9B7EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_MAGENTA = -0x1.f1118ap125F;
    static { NAMED.put("lighter gray magenta", -0x1.f1118ap125F); LIST.add(-0x1.f1118ap125F); }

    /**
     * This color constant "drab brown red" has RGBA8888 code {@code 79261AFF}, L 0.28627452, A 0.5529412, B 0.53333336, alpha 1.0, hue 0.089442976, saturation 0.56359863, and chroma 0.12463325.
     * It can be represented as a packed float with the constant {@code -0x1.111a92p126F}.
     * <pre>
     * <font style='background-color: #79261A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #79261A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #79261A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #79261A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #79261A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #79261A'>&nbsp;@&nbsp;</font><font style='background-color: #79261A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #79261A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #79261A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN_RED = -0x1.111a92p126F;
    static { NAMED.put("drab brown red", -0x1.111a92p126F); LIST.add(-0x1.111a92p126F); }

    /**
     * This color constant "dull brown red" has RGBA8888 code {@code B55545FF}, L 0.4509804, A 0.5529412, B 0.5294118, alpha 1.0, hue 0.080711745, saturation 0.22931314, and chroma 0.120651916.
     * It can be represented as a packed float with the constant {@code -0x1.0f1ae6p126F}.
     * <pre>
     * <font style='background-color: #B55545;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B55545; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B55545;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B55545'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B55545'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B55545'>&nbsp;@&nbsp;</font><font style='background-color: #B55545; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B55545;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B55545; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BROWN_RED = -0x1.0f1ae6p126F;
    static { NAMED.put("dull brown red", -0x1.0f1ae6p126F); LIST.add(-0x1.0f1ae6p126F); }

    /**
     * This color constant "pale brown red" has RGBA8888 code {@code EB816DFF}, L 0.6117647, A 0.5529412, B 0.53333336, alpha 1.0, hue 0.089442976, saturation 0.47080588, and chroma 0.12463325.
     * It can be represented as a packed float with the constant {@code -0x1.111b38p126F}.
     * <pre>
     * <font style='background-color: #EB816D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB816D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB816D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EB816D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EB816D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EB816D'>&nbsp;@&nbsp;</font><font style='background-color: #EB816D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB816D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB816D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN_RED = -0x1.111b38p126F;
    static { NAMED.put("pale brown red", -0x1.111b38p126F); LIST.add(-0x1.111b38p126F); }

    /**
     * This color constant "drab red brown" has RGBA8888 code {@code 74351EFF}, L 0.3019608, A 0.5372549, B 0.53333336, alpha 1.0, hue 0.116173744, saturation 0.4878964, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.11129ap126F}.
     * <pre>
     * <font style='background-color: #74351E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #74351E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #74351E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #74351E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #74351E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #74351E'>&nbsp;@&nbsp;</font><font style='background-color: #74351E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #74351E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #74351E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED_BROWN = -0x1.11129ap126F;
    static { NAMED.put("drab red brown", -0x1.11129ap126F); LIST.add(-0x1.11129ap126F); }

    /**
     * This color constant "dull red brown" has RGBA8888 code {@code AE654AFF}, L 0.47058824, A 0.53333336, B 0.53333336, alpha 1.0, hue 0.125, saturation 0.26731414, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.1110fp126F}.
     * <pre>
     * <font style='background-color: #AE654A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE654A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE654A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE654A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE654A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE654A'>&nbsp;@&nbsp;</font><font style='background-color: #AE654A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE654A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE654A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_RED_BROWN = -0x1.1110fp126F;
    static { NAMED.put("dull red brown", -0x1.1110fp126F); LIST.add(-0x1.1110fp126F); }

    /**
     * This color constant "pale red brown" has RGBA8888 code {@code E49274FF}, L 0.6392157, A 0.5372549, B 0.53333336, alpha 1.0, hue 0.116173744, saturation 0.3435064, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.111346p126F}.
     * <pre>
     * <font style='background-color: #E49274;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E49274; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E49274;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E49274'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E49274'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E49274'>&nbsp;@&nbsp;</font><font style='background-color: #E49274; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E49274;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E49274; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED_BROWN = -0x1.111346p126F;
    static { NAMED.put("pale red brown", -0x1.111346p126F); LIST.add(-0x1.111346p126F); }

    /**
     * This color constant "drab orange brown" has RGBA8888 code {@code 6A4434FF}, L 0.32156864, A 0.52156866, B 0.52156866, alpha 1.0, hue 0.125, saturation 0.1920254, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0b0aa4p126F}.
     * <pre>
     * <font style='background-color: #6A4434;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A4434; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A4434;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A4434'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A4434'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A4434'>&nbsp;@&nbsp;</font><font style='background-color: #6A4434; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A4434;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A4434; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE_BROWN = -0x1.0b0aa4p126F;
    static { NAMED.put("drab orange brown", -0x1.0b0aa4p126F); LIST.add(-0x1.0b0aa4p126F); }

    /**
     * This color constant "dull orange brown" has RGBA8888 code {@code A17461FF}, L 0.49019608, A 0.52156866, B 0.52156866, alpha 1.0, hue 0.125, saturation 0.102880225, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0b0afap126F}.
     * <pre>
     * <font style='background-color: #A17461;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A17461; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A17461;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A17461'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A17461'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A17461'>&nbsp;@&nbsp;</font><font style='background-color: #A17461; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A17461;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A17461; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_ORANGE_BROWN = -0x1.0b0afap126F;
    static { NAMED.put("dull orange brown", -0x1.0b0afap126F); LIST.add(-0x1.0b0afap126F); }

    /**
     * This color constant "pale orange brown" has RGBA8888 code {@code D4A28DFF}, L 0.6627451, A 0.52156866, B 0.52156866, alpha 1.0, hue 0.125, saturation 0.14753848, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0b0b52p126F}.
     * <pre>
     * <font style='background-color: #D4A28D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4A28D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4A28D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4A28D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4A28D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4A28D'>&nbsp;@&nbsp;</font><font style='background-color: #D4A28D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4A28D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4A28D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_BROWN = -0x1.0b0b52p126F;
    static { NAMED.put("pale orange brown", -0x1.0b0b52p126F); LIST.add(-0x1.0b0b52p126F); }

    /**
     * This color constant "drab brown orange" has RGBA8888 code {@code 604C40FF}, L 0.32941177, A 0.50980395, B 0.5137255, alpha 1.0, hue 0.15127131, saturation 0.07005917, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.0704a8p126F}.
     * <pre>
     * <font style='background-color: #604C40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #604C40; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #604C40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #604C40'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #604C40'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #604C40'>&nbsp;@&nbsp;</font><font style='background-color: #604C40; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #604C40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #604C40; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN_ORANGE = -0x1.0704a8p126F;
    static { NAMED.put("drab brown orange", -0x1.0704a8p126F); LIST.add(-0x1.0704a8p126F); }

    /**
     * This color constant "dull brown orange" has RGBA8888 code {@code 978072FF}, L 0.50980395, A 0.50980395, B 0.5137255, alpha 1.0, hue 0.15127131, saturation 0.03736902, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.070504p126F}.
     * <pre>
     * <font style='background-color: #978072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #978072; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #978072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #978072'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #978072'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #978072'>&nbsp;@&nbsp;</font><font style='background-color: #978072; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #978072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #978072; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BROWN_ORANGE = -0x1.070504p126F;
    static { NAMED.put("dull brown orange", -0x1.070504p126F); LIST.add(-0x1.070504p126F); }

    /**
     * This color constant "pale brown orange" has RGBA8888 code {@code C8AF9FFF}, L 0.6862745, A 0.5058824, B 0.5137255, alpha 1.0, hue 0.18556869, saturation 0.02682391, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.07035ep126F}.
     * <pre>
     * <font style='background-color: #C8AF9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8AF9F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8AF9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8AF9F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8AF9F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8AF9F'>&nbsp;@&nbsp;</font><font style='background-color: #C8AF9F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8AF9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8AF9F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN_ORANGE = -0x1.07035ep126F;
    static { NAMED.put("pale brown orange", -0x1.07035ep126F); LIST.add(-0x1.07035ep126F); }

    /**
     * This color constant "drab saffron orange" has RGBA8888 code {@code 7A491DFF}, L 0.34509805, A 0.52156866, B 0.5411765, alpha 1.0, hue 0.17320445, saturation 0.6041387, and chroma 0.09260367.
     * It can be represented as a packed float with the constant {@code -0x1.150abp126F}.
     * <pre>
     * <font style='background-color: #7A491D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A491D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A491D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A491D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A491D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A491D'>&nbsp;@&nbsp;</font><font style='background-color: #7A491D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A491D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A491D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON_ORANGE = -0x1.150abp126F;
    static { NAMED.put("drab saffron orange", -0x1.150abp126F); LIST.add(-0x1.150abp126F); }

    /**
     * This color constant "dull saffron orange" has RGBA8888 code {@code B67D4DFF}, L 0.5294118, A 0.52156866, B 0.5411765, alpha 1.0, hue 0.17320445, saturation 0.3263173, and chroma 0.09260367.
     * It can be represented as a packed float with the constant {@code -0x1.150b0ep126F}.
     * <pre>
     * <font style='background-color: #B67D4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B67D4D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B67D4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B67D4D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B67D4D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B67D4D'>&nbsp;@&nbsp;</font><font style='background-color: #B67D4D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B67D4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B67D4D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_SAFFRON_ORANGE = -0x1.150b0ep126F;
    static { NAMED.put("dull saffron orange", -0x1.150b0ep126F); LIST.add(-0x1.150b0ep126F); }

    /**
     * This color constant "pale saffron orange" has RGBA8888 code {@code EBAD78FF}, L 0.70980394, A 0.5176471, B 0.5411765, alpha 1.0, hue 0.18556869, saturation 0.28899655, and chroma 0.08924734.
     * It can be represented as a packed float with the constant {@code -0x1.15096ap126F}.
     * <pre>
     * <font style='background-color: #EBAD78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBAD78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBAD78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBAD78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBAD78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBAD78'>&nbsp;@&nbsp;</font><font style='background-color: #EBAD78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBAD78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBAD78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON_ORANGE = -0x1.15096ap126F;
    static { NAMED.put("pale saffron orange", -0x1.15096ap126F); LIST.add(-0x1.15096ap126F); }

    /**
     * This color constant "drab orange saffron" has RGBA8888 code {@code 725328FF}, L 0.35686275, A 0.5058824, B 0.5372549, alpha 1.0, hue 0.2250635, saturation 0.45552477, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.1302b6p126F}.
     * <pre>
     * <font style='background-color: #725328;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #725328; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #725328;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #725328'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #725328'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #725328'>&nbsp;@&nbsp;</font><font style='background-color: #725328; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #725328;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #725328; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE_SAFFRON = -0x1.1302b6p126F;
    static { NAMED.put("drab orange saffron", -0x1.1302b6p126F); LIST.add(-0x1.1302b6p126F); }

    /**
     * This color constant "dull orange saffron" has RGBA8888 code {@code AF8A5AFF}, L 0.5529412, A 0.50980395, B 0.5372549, alpha 1.0, hue 0.20905071, saturation 0.24739625, and chroma 0.07674564.
     * It can be represented as a packed float with the constant {@code -0x1.13051ap126F}.
     * <pre>
     * <font style='background-color: #AF8A5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF8A5A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF8A5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF8A5A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF8A5A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF8A5A'>&nbsp;@&nbsp;</font><font style='background-color: #AF8A5A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF8A5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF8A5A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_ORANGE_SAFFRON = -0x1.13051ap126F;
    static { NAMED.put("dull orange saffron", -0x1.13051ap126F); LIST.add(-0x1.13051ap126F); }

    /**
     * This color constant "pale orange saffron" has RGBA8888 code {@code E5BD88FF}, L 0.74509805, A 0.50980395, B 0.5372549, alpha 1.0, hue 0.20905071, saturation 0.21370243, and chroma 0.07674564.
     * It can be represented as a packed float with the constant {@code -0x1.13057cp126F}.
     * <pre>
     * <font style='background-color: #E5BD88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5BD88; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5BD88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5BD88'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5BD88'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5BD88'>&nbsp;@&nbsp;</font><font style='background-color: #E5BD88; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5BD88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5BD88; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_SAFFRON = -0x1.13057cp126F;
    static { NAMED.put("pale orange saffron", -0x1.13057cp126F); LIST.add(-0x1.13057cp126F); }

    /**
     * This color constant "drab yellow saffron" has RGBA8888 code {@code 765C20FF}, L 0.38039216, A 0.5019608, B 0.54509807, alpha 1.0, hue 0.24309666, saturation 0.6090204, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.1700c2p126F}.
     * <pre>
     * <font style='background-color: #765C20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #765C20; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #765C20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #765C20'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #765C20'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #765C20'>&nbsp;@&nbsp;</font><font style='background-color: #765C20; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #765C20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #765C20; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW_SAFFRON = -0x1.1700c2p126F;
    static { NAMED.put("drab yellow saffron", -0x1.1700c2p126F); LIST.add(-0x1.1700c2p126F); }

    /**
     * This color constant "dull yellow saffron" has RGBA8888 code {@code B29554FF}, L 0.5803922, A 0.5019608, B 0.54509807, alpha 1.0, hue 0.24309666, saturation 0.33968917, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.170128p126F}.
     * <pre>
     * <font style='background-color: #B29554;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B29554; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B29554;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B29554'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B29554'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B29554'>&nbsp;@&nbsp;</font><font style='background-color: #B29554; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B29554;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B29554; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_YELLOW_SAFFRON = -0x1.170128p126F;
    static { NAMED.put("dull yellow saffron", -0x1.170128p126F); LIST.add(-0x1.170128p126F); }

    /**
     * This color constant "pale yellow saffron" has RGBA8888 code {@code E9CA83FF}, L 0.78431374, A 0.49803922, B 0.54509807, alpha 1.0, hue 0.25690335, saturation 0.22531618, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.16ff9p126F}.
     * <pre>
     * <font style='background-color: #E9CA83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9CA83; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9CA83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9CA83'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9CA83'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9CA83'>&nbsp;@&nbsp;</font><font style='background-color: #E9CA83; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9CA83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9CA83; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_SAFFRON = -0x1.16ff9p126F;
    static { NAMED.put("pale yellow saffron", -0x1.16ff9p126F); LIST.add(-0x1.16ff9p126F); }

    /**
     * This color constant "drab saffron yellow" has RGBA8888 code {@code 706E2AFF}, L 0.41568628, A 0.48235294, B 0.54509807, alpha 1.0, hue 0.30935085, saturation 0.51249737, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.16f6d4p126F}.
     * <pre>
     * <font style='background-color: #706E2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #706E2A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #706E2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #706E2A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #706E2A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #706E2A'>&nbsp;@&nbsp;</font><font style='background-color: #706E2A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #706E2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #706E2A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON_YELLOW = -0x1.16f6d4p126F;
    static { NAMED.put("drab saffron yellow", -0x1.16f6d4p126F); LIST.add(-0x1.16f6d4p126F); }

    /**
     * This color constant "dull saffron yellow" has RGBA8888 code {@code AEAC62FF}, L 0.63529414, A 0.48235294, B 0.54509807, alpha 1.0, hue 0.30935085, saturation 0.28211355, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.16f744p126F}.
     * <pre>
     * <font style='background-color: #AEAC62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEAC62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEAC62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AEAC62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AEAC62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AEAC62'>&nbsp;@&nbsp;</font><font style='background-color: #AEAC62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AEAC62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AEAC62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_SAFFRON_YELLOW = -0x1.16f744p126F;
    static { NAMED.put("dull saffron yellow", -0x1.16f744p126F); LIST.add(-0x1.16f744p126F); }

    /**
     * This color constant "pale saffron yellow" has RGBA8888 code {@code E5E393FF}, L 0.8509804, A 0.48235294, B 0.54509807, alpha 1.0, hue 0.30935085, saturation 0.20536992, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.16f7b2p126F}.
     * <pre>
     * <font style='background-color: #E5E393;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5E393; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5E393;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5E393'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5E393'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5E393'>&nbsp;@&nbsp;</font><font style='background-color: #E5E393; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5E393;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5E393; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON_YELLOW = -0x1.16f7b2p126F;
    static { NAMED.put("pale saffron yellow", -0x1.16f7b2p126F); LIST.add(-0x1.16f7b2p126F); }

    /**
     * This color constant "drab lime yellow" has RGBA8888 code {@code 65761BFF}, L 0.42352942, A 0.46666667, B 0.5529412, alpha 1.0, hue 0.33944297, saturation 0.68679374, and chroma 0.12463325.
     * It can be represented as a packed float with the constant {@code -0x1.1aeed8p126F}.
     * <pre>
     * <font style='background-color: #65761B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65761B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65761B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65761B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65761B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65761B'>&nbsp;@&nbsp;</font><font style='background-color: #65761B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65761B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65761B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME_YELLOW = -0x1.1aeed8p126F;
    static { NAMED.put("drab lime yellow", -0x1.1aeed8p126F); LIST.add(-0x1.1aeed8p126F); }

    /**
     * This color constant "dull lime yellow" has RGBA8888 code {@code 9FB455FF}, L 0.6392157, A 0.46666667, B 0.5529412, alpha 1.0, hue 0.33944297, saturation 0.39917654, and chroma 0.12463325.
     * It can be represented as a packed float with the constant {@code -0x1.1aef46p126F}.
     * <pre>
     * <font style='background-color: #9FB455;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FB455; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FB455;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9FB455'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9FB455'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9FB455'>&nbsp;@&nbsp;</font><font style='background-color: #9FB455; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FB455;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FB455; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_LIME_YELLOW = -0x1.1aef46p126F;
    static { NAMED.put("dull lime yellow", -0x1.1aef46p126F); LIST.add(-0x1.1aef46p126F); }

    /**
     * This color constant "pale lime yellow" has RGBA8888 code {@code D4EB85FF}, L 0.8509804, A 0.46666667, B 0.5529412, alpha 1.0, hue 0.33944297, saturation 0.27812308, and chroma 0.12463325.
     * It can be represented as a packed float with the constant {@code -0x1.1aefb2p126F}.
     * <pre>
     * <font style='background-color: #D4EB85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4EB85; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4EB85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4EB85'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4EB85'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4EB85'>&nbsp;@&nbsp;</font><font style='background-color: #D4EB85; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4EB85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4EB85; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME_YELLOW = -0x1.1aefb2p126F;
    static { NAMED.put("pale lime yellow", -0x1.1aefb2p126F); LIST.add(-0x1.1aefb2p126F); }

    /**
     * This color constant "drab yellow lime" has RGBA8888 code {@code 58742AFF}, L 0.4117647, A 0.4627451, B 0.54509807, alpha 1.0, hue 0.35989827, saturation 0.49273357, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.16ecd2p126F}.
     * <pre>
     * <font style='background-color: #58742A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #58742A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #58742A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #58742A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #58742A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #58742A'>&nbsp;@&nbsp;</font><font style='background-color: #58742A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #58742A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #58742A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW_LIME = -0x1.16ecd2p126F;
    static { NAMED.put("drab yellow lime", -0x1.16ecd2p126F); LIST.add(-0x1.16ecd2p126F); }

    /**
     * This color constant "dull yellow lime" has RGBA8888 code {@code 90B15FFF}, L 0.61960787, A 0.4627451, B 0.54509807, alpha 1.0, hue 0.35989827, saturation 0.2889376, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.16ed3cp126F}.
     * <pre>
     * <font style='background-color: #90B15F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90B15F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90B15F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #90B15F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #90B15F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #90B15F'>&nbsp;@&nbsp;</font><font style='background-color: #90B15F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90B15F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90B15F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_YELLOW_LIME = -0x1.16ed3cp126F;
    static { NAMED.put("dull yellow lime", -0x1.16ed3cp126F); LIST.add(-0x1.16ed3cp126F); }

    /**
     * This color constant "pale yellow lime" has RGBA8888 code {@code C4E98FFF}, L 0.83137256, A 0.4627451, B 0.54509807, alpha 1.0, hue 0.35989827, saturation 0.20125502, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.16eda8p126F}.
     * <pre>
     * <font style='background-color: #C4E98F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4E98F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4E98F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4E98F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4E98F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4E98F'>&nbsp;@&nbsp;</font><font style='background-color: #C4E98F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4E98F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4E98F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_LIME = -0x1.16eda8p126F;
    static { NAMED.put("pale yellow lime", -0x1.16eda8p126F); LIST.add(-0x1.16eda8p126F); }

    /**
     * This color constant "drab green lime" has RGBA8888 code {@code 3F741CFF}, L 0.39215687, A 0.44705883, B 0.54901963, alpha 1.0, hue 0.38111365, saturation 0.62619954, and chroma 0.14373726.
     * It can be represented as a packed float with the constant {@code -0x1.18e4c8p126F}.
     * <pre>
     * <font style='background-color: #3F741C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F741C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F741C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F741C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F741C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F741C'>&nbsp;@&nbsp;</font><font style='background-color: #3F741C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F741C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F741C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN_LIME = -0x1.18e4c8p126F;
    static { NAMED.put("drab green lime", -0x1.18e4c8p126F); LIST.add(-0x1.18e4c8p126F); }

    /**
     * This color constant "dull green lime" has RGBA8888 code {@code 76B254FF}, L 0.6, A 0.44705883, B 0.54901963, alpha 1.0, hue 0.38111365, saturation 0.35798797, and chroma 0.14373726.
     * It can be represented as a packed float with the constant {@code -0x1.18e532p126F}.
     * <pre>
     * <font style='background-color: #76B254;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76B254; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76B254;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #76B254'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #76B254'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #76B254'>&nbsp;@&nbsp;</font><font style='background-color: #76B254; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76B254;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76B254; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN_LIME = -0x1.18e532p126F;
    static { NAMED.put("dull green lime", -0x1.18e532p126F); LIST.add(-0x1.18e532p126F); }

    /**
     * This color constant "pale green lime" has RGBA8888 code {@code A8EA84FF}, L 0.80784315, A 0.44705883, B 0.54901963, alpha 1.0, hue 0.38111365, saturation 0.2506363, and chroma 0.14373726.
     * It can be represented as a packed float with the constant {@code -0x1.18e59cp126F}.
     * <pre>
     * <font style='background-color: #A8EA84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8EA84; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8EA84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8EA84'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8EA84'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8EA84'>&nbsp;@&nbsp;</font><font style='background-color: #A8EA84; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8EA84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8EA84; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN_LIME = -0x1.18e59cp126F;
    static { NAMED.put("pale green lime", -0x1.18e59cp126F); LIST.add(-0x1.18e59cp126F); }

    /**
     * This color constant "drab lime green" has RGBA8888 code {@code 28762CFF}, L 0.3882353, A 0.4392157, B 0.5411765, alpha 1.0, hue 0.40522522, saturation 0.7079914, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.14e0c6p126F}.
     * <pre>
     * <font style='background-color: #28762C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #28762C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #28762C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #28762C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #28762C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #28762C'>&nbsp;@&nbsp;</font><font style='background-color: #28762C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #28762C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #28762C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME_GREEN = -0x1.14e0c6p126F;
    static { NAMED.put("drab lime green", -0x1.14e0c6p126F); LIST.add(-0x1.14e0c6p126F); }

    /**
     * This color constant "dull lime green" has RGBA8888 code {@code 5EB25FFF}, L 0.5882353, A 0.4392157, B 0.5411765, alpha 1.0, hue 0.40522522, saturation 0.39601722, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.14e12cp126F}.
     * <pre>
     * <font style='background-color: #5EB25F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5EB25F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5EB25F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5EB25F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5EB25F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5EB25F'>&nbsp;@&nbsp;</font><font style='background-color: #5EB25F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5EB25F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5EB25F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_LIME_GREEN = -0x1.14e12cp126F;
    static { NAMED.put("dull lime green", -0x1.14e12cp126F); LIST.add(-0x1.14e12cp126F); }

    /**
     * This color constant "pale lime green" has RGBA8888 code {@code 8FE98EFF}, L 0.7882353, A 0.4392157, B 0.5411765, alpha 1.0, hue 0.40522522, saturation 0.26673007, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.14e192p126F}.
     * <pre>
     * <font style='background-color: #8FE98E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FE98E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FE98E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FE98E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FE98E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FE98E'>&nbsp;@&nbsp;</font><font style='background-color: #8FE98E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FE98E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FE98E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME_GREEN = -0x1.14e192p126F;
    static { NAMED.put("pale lime green", -0x1.14e192p126F); LIST.add(-0x1.14e192p126F); }

    /**
     * This color constant "drab cyan green" has RGBA8888 code {@code 14754EFF}, L 0.3882353, A 0.44313726, B 0.5176471, alpha 1.0, hue 0.45211816, saturation 0.8728994, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.08e2c6p126F}.
     * <pre>
     * <font style='background-color: #14754E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14754E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14754E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #14754E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #14754E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #14754E'>&nbsp;@&nbsp;</font><font style='background-color: #14754E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14754E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14754E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN_GREEN = -0x1.08e2c6p126F;
    static { NAMED.put("drab cyan green", -0x1.08e2c6p126F); LIST.add(-0x1.08e2c6p126F); }

    /**
     * This color constant "dull cyan green" has RGBA8888 code {@code 47A679FF}, L 0.54901963, A 0.44313726, B 0.5176471, alpha 1.0, hue 0.45211816, saturation 0.5621094, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.08e318p126F}.
     * <pre>
     * <font style='background-color: #47A679;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #47A679; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #47A679;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #47A679'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #47A679'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #47A679'>&nbsp;@&nbsp;</font><font style='background-color: #47A679; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #47A679;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #47A679; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_CYAN_GREEN = -0x1.08e318p126F;
    static { NAMED.put("dull cyan green", -0x1.08e318p126F); LIST.add(-0x1.08e318p126F); }

    /**
     * This color constant "pale cyan green" has RGBA8888 code {@code 70D3A2FF}, L 0.70980394, A 0.44313726, B 0.5176471, alpha 1.0, hue 0.45211816, saturation 0.39196515, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.08e36ap126F}.
     * <pre>
     * <font style='background-color: #70D3A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #70D3A2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #70D3A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #70D3A2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #70D3A2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #70D3A2'>&nbsp;@&nbsp;</font><font style='background-color: #70D3A2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #70D3A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #70D3A2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN_GREEN = -0x1.08e36ap126F;
    static { NAMED.put("pale cyan green", -0x1.08e36ap126F); LIST.add(-0x1.08e36ap126F); }

    /**
     * This color constant "drab green cyan" has RGBA8888 code {@code 156B65FF}, L 0.36862746, A 0.45490196, B 0.49019608, alpha 1.0, hue 0.53407246, saturation 0.85198003, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.fae8bcp125F}.
     * <pre>
     * <font style='background-color: #156B65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #156B65; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #156B65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #156B65'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #156B65'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #156B65'>&nbsp;@&nbsp;</font><font style='background-color: #156B65; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #156B65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #156B65; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN_CYAN = -0x1.fae8bcp125F;
    static { NAMED.put("drab green cyan", -0x1.fae8bcp125F); LIST.add(-0x1.fae8bcp125F); }

    /**
     * This color constant "dull green cyan" has RGBA8888 code {@code 459992FF}, L 0.52156866, A 0.45490196, B 0.49019608, alpha 1.0, hue 0.53407246, saturation 0.558327, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.fae90ap125F}.
     * <pre>
     * <font style='background-color: #459992;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #459992; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #459992;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #459992'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #459992'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #459992'>&nbsp;@&nbsp;</font><font style='background-color: #459992; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #459992;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #459992; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN_CYAN = -0x1.fae90ap125F;
    static { NAMED.put("dull green cyan", -0x1.fae90ap125F); LIST.add(-0x1.fae90ap125F); }

    /**
     * This color constant "pale green cyan" has RGBA8888 code {@code 6CC4BBFF}, L 0.67058825, A 0.45490196, B 0.49411765, alpha 1.0, hue 0.5206551, saturation 0.38257778, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.fce956p125F}.
     * <pre>
     * <font style='background-color: #6CC4BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6CC4BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6CC4BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6CC4BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6CC4BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6CC4BB'>&nbsp;@&nbsp;</font><font style='background-color: #6CC4BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6CC4BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6CC4BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN_CYAN = -0x1.fce956p125F;
    static { NAMED.put("pale green cyan", -0x1.fce956p125F); LIST.add(-0x1.fce956p125F); }

    /**
     * This color constant "drab blue cyan" has RGBA8888 code {@code 00697CFF}, L 0.36862746, A 0.4627451, B 0.47058824, alpha 1.0, hue 0.6063733, saturation 0.8344607, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.f0ecbcp125F}.
     * <pre>
     * <font style='background-color: #00697C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00697C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00697C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00697C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00697C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00697C'>&nbsp;@&nbsp;</font><font style='background-color: #00697C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00697C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00697C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE_CYAN = -0x1.f0ecbcp125F;
    static { NAMED.put("drab blue cyan", -0x1.f0ecbcp125F); LIST.add(-0x1.f0ecbcp125F); }

    /**
     * This color constant "dull blue cyan" has RGBA8888 code {@code 4AA5BAFF}, L 0.5686275, A 0.45882353, B 0.4745098, alpha 1.0, hue 0.5882305, saturation 0.51249737, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.f2eb22p125F}.
     * <pre>
     * <font style='background-color: #4AA5BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4AA5BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4AA5BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4AA5BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4AA5BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4AA5BA'>&nbsp;@&nbsp;</font><font style='background-color: #4AA5BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4AA5BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4AA5BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BLUE_CYAN = -0x1.f2eb22p125F;
    static { NAMED.put("dull blue cyan", -0x1.f2eb22p125F); LIST.add(-0x1.f2eb22p125F); }

    /**
     * This color constant "pale blue cyan" has RGBA8888 code {@code 7CDBF2FF}, L 0.76862746, A 0.45882353, B 0.47058824, alpha 1.0, hue 0.59872866, saturation 0.5595463, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.f0eb88p125F}.
     * <pre>
     * <font style='background-color: #7CDBF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CDBF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CDBF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7CDBF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7CDBF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7CDBF2'>&nbsp;@&nbsp;</font><font style='background-color: #7CDBF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CDBF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CDBF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE_CYAN = -0x1.f0eb88p125F;
    static { NAMED.put("pale blue cyan", -0x1.f0eb88p125F); LIST.add(-0x1.f0eb88p125F); }

    /**
     * This color constant "drab cyan blue" has RGBA8888 code {@code 0F4A77FF}, L 0.29411766, A 0.47843137, B 0.4509804, alpha 1.0, hue 0.6840373, saturation 0.66473603, and chroma 0.106691405.
     * It can be represented as a packed float with the constant {@code -0x1.e6f496p125F}.
     * <pre>
     * <font style='background-color: #0F4A77;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F4A77; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F4A77;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F4A77'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F4A77'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F4A77'>&nbsp;@&nbsp;</font><font style='background-color: #0F4A77; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F4A77;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F4A77; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN_BLUE = -0x1.e6f496p125F;
    static { NAMED.put("drab cyan blue", -0x1.e6f496p125F); LIST.add(-0x1.e6f496p125F); }

    /**
     * This color constant "dull cyan blue" has RGBA8888 code {@code 4380B4FF}, L 0.47058824, A 0.4745098, B 0.4509804, alpha 1.0, hue 0.6736814, saturation 0.43958476, and chroma 0.11007033.
     * It can be represented as a packed float with the constant {@code -0x1.e6f2fp125F}.
     * <pre>
     * <font style='background-color: #4380B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4380B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4380B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4380B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4380B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4380B4'>&nbsp;@&nbsp;</font><font style='background-color: #4380B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4380B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4380B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_CYAN_BLUE = -0x1.e6f2fp125F;
    static { NAMED.put("dull cyan blue", -0x1.e6f2fp125F); LIST.add(-0x1.e6f2fp125F); }

    /**
     * This color constant "pale cyan blue" has RGBA8888 code {@code 71B3EDFF}, L 0.6509804, A 0.4745098, B 0.4509804, alpha 1.0, hue 0.6736814, saturation 0.5646222, and chroma 0.11007033.
     * It can be represented as a packed float with the constant {@code -0x1.e6f34cp125F}.
     * <pre>
     * <font style='background-color: #71B3ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #71B3ED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #71B3ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #71B3ED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #71B3ED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #71B3ED'>&nbsp;@&nbsp;</font><font style='background-color: #71B3ED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #71B3ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #71B3ED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN_BLUE = -0x1.e6f34cp125F;
    static { NAMED.put("pale cyan blue", -0x1.e6f34cp125F); LIST.add(-0x1.e6f34cp125F); }

    /**
     * This color constant "drab violet blue" has RGBA8888 code {@code 1D217DFF}, L 0.21960784, A 0.5019608, B 0.41568628, alpha 1.0, hue 0.75368965, saturation 0.5595463, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.d5007p125F}.
     * <pre>
     * <font style='background-color: #1D217D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D217D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D217D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D217D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D217D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D217D'>&nbsp;@&nbsp;</font><font style='background-color: #1D217D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D217D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D217D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET_BLUE = -0x1.d5007p125F;
    static { NAMED.put("drab violet blue", -0x1.d5007p125F); LIST.add(-0x1.d5007p125F); }

    /**
     * This color constant "dull violet blue" has RGBA8888 code {@code 4052BBFF}, L 0.37254903, A 0.5019608, B 0.41568628, alpha 1.0, hue 0.75368965, saturation 0.33331832, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.d500bep125F}.
     * <pre>
     * <font style='background-color: #4052BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4052BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4052BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4052BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4052BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4052BB'>&nbsp;@&nbsp;</font><font style='background-color: #4052BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4052BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4052BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET_BLUE = -0x1.d500bep125F;
    static { NAMED.put("dull violet blue", -0x1.d500bep125F); LIST.add(-0x1.d500bep125F); }

    /**
     * This color constant "pale violet blue" has RGBA8888 code {@code 6680F3FF}, L 0.5254902, A 0.49803922, B 0.41568628, alpha 1.0, hue 0.74631035, saturation 0.75502497, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.d4ff0cp125F}.
     * <pre>
     * <font style='background-color: #6680F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6680F3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6680F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6680F3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6680F3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6680F3'>&nbsp;@&nbsp;</font><font style='background-color: #6680F3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6680F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6680F3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET_BLUE = -0x1.d4ff0cp125F;
    static { NAMED.put("pale violet blue", -0x1.d4ff0cp125F); LIST.add(-0x1.d4ff0cp125F); }

    /**
     * This color constant "drab blue violet" has RGBA8888 code {@code 362479FF}, L 0.23921569, A 0.5176471, B 0.42745098, alpha 1.0, hue 0.78797436, saturation 0.45422506, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.db087ap125F}.
     * <pre>
     * <font style='background-color: #362479;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #362479; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #362479;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #362479'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #362479'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #362479'>&nbsp;@&nbsp;</font><font style='background-color: #362479; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #362479;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #362479; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE_VIOLET = -0x1.db087ap125F;
    static { NAMED.put("drab blue violet", -0x1.db087ap125F); LIST.add(-0x1.db087ap125F); }

    /**
     * This color constant "dull blue violet" has RGBA8888 code {@code 6154B4FF}, L 0.39607844, A 0.5176471, B 0.42745098, alpha 1.0, hue 0.78797436, saturation 0.2684067, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.db08cap125F}.
     * <pre>
     * <font style='background-color: #6154B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6154B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6154B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6154B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6154B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6154B4'>&nbsp;@&nbsp;</font><font style='background-color: #6154B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6154B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6154B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BLUE_VIOLET = -0x1.db08cap125F;
    static { NAMED.put("dull blue violet", -0x1.db08cap125F); LIST.add(-0x1.db08cap125F); }

    /**
     * This color constant "pale blue violet" has RGBA8888 code {@code 8E81EDFF}, L 0.5568628, A 0.52156866, B 0.42745098, alpha 1.0, hue 0.79598206, saturation 0.6334361, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.db0b1cp125F}.
     * <pre>
     * <font style='background-color: #8E81ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E81ED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E81ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E81ED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E81ED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E81ED'>&nbsp;@&nbsp;</font><font style='background-color: #8E81ED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E81ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E81ED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE_VIOLET = -0x1.db0b1cp125F;
    static { NAMED.put("pale blue violet", -0x1.db0b1cp125F); LIST.add(-0x1.db0b1cp125F); }

    /**
     * This color constant "drab purple violet" has RGBA8888 code {@code 4B207FFF}, L 0.25882354, A 0.5411765, B 0.42745098, alpha 1.0, hue 0.8321663, saturation 0.6093763, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.db1484p125F}.
     * <pre>
     * <font style='background-color: #4B207F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B207F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B207F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B207F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B207F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B207F'>&nbsp;@&nbsp;</font><font style='background-color: #4B207F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B207F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B207F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE_VIOLET = -0x1.db1484p125F;
    static { NAMED.put("drab purple violet", -0x1.db1484p125F); LIST.add(-0x1.db1484p125F); }

    /**
     * This color constant "dull purple violet" has RGBA8888 code {@code 7C50BCFF}, L 0.41960785, A 0.5411765, B 0.42745098, alpha 1.0, hue 0.8321663, saturation 0.29372388, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.db14d6p125F}.
     * <pre>
     * <font style='background-color: #7C50BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C50BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C50BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C50BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C50BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C50BC'>&nbsp;@&nbsp;</font><font style='background-color: #7C50BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C50BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C50BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_PURPLE_VIOLET = -0x1.db14d6p125F;
    static { NAMED.put("dull purple violet", -0x1.db14d6p125F); LIST.add(-0x1.db14d6p125F); }

    /**
     * This color constant "pale purple violet" has RGBA8888 code {@code AC7EF4FF}, L 0.58431375, A 0.5411765, B 0.42745098, alpha 1.0, hue 0.8321663, saturation 0.7097343, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.db152ap125F}.
     * <pre>
     * <font style='background-color: #AC7EF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC7EF4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC7EF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC7EF4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC7EF4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC7EF4'>&nbsp;@&nbsp;</font><font style='background-color: #AC7EF4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC7EF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC7EF4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE_VIOLET = -0x1.db152ap125F;
    static { NAMED.put("pale purple violet", -0x1.db152ap125F); LIST.add(-0x1.db152ap125F); }

    /**
     * This color constant "drab violet purple" has RGBA8888 code {@code 552779FF}, L 0.27450982, A 0.5411765, B 0.4392157, alpha 1.0, hue 0.8447748, saturation 0.45515785, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.e1148cp125F}.
     * <pre>
     * <font style='background-color: #552779;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #552779; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #552779;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #552779'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #552779'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #552779'>&nbsp;@&nbsp;</font><font style='background-color: #552779; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #552779;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #552779; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET_PURPLE = -0x1.e1148cp125F;
    static { NAMED.put("drab violet purple", -0x1.e1148cp125F); LIST.add(-0x1.e1148cp125F); }

    /**
     * This color constant "dull violet purple" has RGBA8888 code {@code 8855B3FF}, L 0.43529412, A 0.54509807, B 0.4392157, alpha 1.0, hue 0.8516046, saturation 0.24807492, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.e116dep125F}.
     * <pre>
     * <font style='background-color: #8855B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8855B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8855B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8855B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8855B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8855B3'>&nbsp;@&nbsp;</font><font style='background-color: #8855B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8855B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8855B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET_PURPLE = -0x1.e116dep125F;
    static { NAMED.put("dull violet purple", -0x1.e116dep125F); LIST.add(-0x1.e116dep125F); }

    /**
     * This color constant "pale violet purple" has RGBA8888 code {@code B982E9FF}, L 0.6, A 0.54509807, B 0.4392157, alpha 1.0, hue 0.8516046, saturation 0.5205695, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.e11732p125F}.
     * <pre>
     * <font style='background-color: #B982E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B982E9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B982E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B982E9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B982E9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B982E9'>&nbsp;@&nbsp;</font><font style='background-color: #B982E9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B982E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B982E9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET_PURPLE = -0x1.e11732p125F;
    static { NAMED.put("pale violet purple", -0x1.e11732p125F); LIST.add(-0x1.e11732p125F); }

    /**
     * This color constant "drab magenta purple" has RGBA8888 code {@code 63237EFF}, L 0.2901961, A 0.5568628, B 0.4392157, alpha 1.0, hue 0.8697009, saturation 0.58501744, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.e11c94p125F}.
     * <pre>
     * <font style='background-color: #63237E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63237E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63237E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #63237E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #63237E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #63237E'>&nbsp;@&nbsp;</font><font style='background-color: #63237E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63237E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63237E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA_PURPLE = -0x1.e11c94p125F;
    static { NAMED.put("drab magenta purple", -0x1.e11c94p125F); LIST.add(-0x1.e11c94p125F); }

    /**
     * This color constant "dull magenta purple" has RGBA8888 code {@code 9B55BAFF}, L 0.45882353, A 0.5568628, B 0.4392157, alpha 1.0, hue 0.8697009, saturation 0.29242566, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.e11ceap125F}.
     * <pre>
     * <font style='background-color: #9B55BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B55BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B55BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B55BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B55BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B55BA'>&nbsp;@&nbsp;</font><font style='background-color: #9B55BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B55BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B55BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_MAGENTA_PURPLE = -0x1.e11ceap125F;
    static { NAMED.put("dull magenta purple", -0x1.e11ceap125F); LIST.add(-0x1.e11ceap125F); }

    /**
     * This color constant "pale magenta purple" has RGBA8888 code {@code CE82F1FF}, L 0.627451, A 0.5568628, B 0.4392157, alpha 1.0, hue 0.8697009, saturation 0.62957466, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.e11d4p125F}.
     * <pre>
     * <font style='background-color: #CE82F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE82F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE82F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE82F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE82F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE82F1'>&nbsp;@&nbsp;</font><font style='background-color: #CE82F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE82F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE82F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA_PURPLE = -0x1.e11d4p125F;
    static { NAMED.put("pale magenta purple", -0x1.e11d4p125F); LIST.add(-0x1.e11d4p125F); }

    /**
     * This color constant "drab purple magenta" has RGBA8888 code {@code 702978FF}, L 0.30980393, A 0.56078434, B 0.4509804, alpha 1.0, hue 0.89197636, saturation 0.49682826, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.e71e9ep125F}.
     * <pre>
     * <font style='background-color: #702978;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #702978; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #702978;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #702978'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #702978'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #702978'>&nbsp;@&nbsp;</font><font style='background-color: #702978; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #702978;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #702978; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE_MAGENTA = -0x1.e71e9ep125F;
    static { NAMED.put("drab purple magenta", -0x1.e71e9ep125F); LIST.add(-0x1.e71e9ep125F); }

    /**
     * This color constant "dull purple magenta" has RGBA8888 code {@code AA5AB3FF}, L 0.48235294, A 0.56078434, B 0.4509804, alpha 1.0, hue 0.89197636, saturation 0.2573735, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.e71ef6p125F}.
     * <pre>
     * <font style='background-color: #AA5AB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA5AB3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA5AB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA5AB3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA5AB3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA5AB3'>&nbsp;@&nbsp;</font><font style='background-color: #AA5AB3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA5AB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA5AB3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_PURPLE_MAGENTA = -0x1.e71ef6p125F;
    static { NAMED.put("dull purple magenta", -0x1.e71ef6p125F); LIST.add(-0x1.e71ef6p125F); }

    /**
     * This color constant "pale purple magenta" has RGBA8888 code {@code E089EAFF}, L 0.65882355, A 0.56078434, B 0.4509804, alpha 1.0, hue 0.89197636, saturation 0.47969756, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.e71f5p125F}.
     * <pre>
     * <font style='background-color: #E089EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E089EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E089EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E089EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E089EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E089EA'>&nbsp;@&nbsp;</font><font style='background-color: #E089EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E089EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E089EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE_MAGENTA = -0x1.e71f5p125F;
    static { NAMED.put("pale purple magenta", -0x1.e71f5p125F); LIST.add(-0x1.e71f5p125F); }

    /**
     * This color constant "drab red magenta" has RGBA8888 code {@code 7A2061FF}, L 0.3019608, A 0.57254905, B 0.47058824, alpha 1.0, hue 0.9387114, saturation 0.6250368, and chroma 0.15595676.
     * It can be represented as a packed float with the constant {@code -0x1.f1249ap125F}.
     * <pre>
     * <font style='background-color: #7A2061;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A2061; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A2061;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A2061'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A2061'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A2061'>&nbsp;@&nbsp;</font><font style='background-color: #7A2061; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A2061;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A2061; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED_MAGENTA = -0x1.f1249ap125F;
    static { NAMED.put("drab red magenta", -0x1.f1249ap125F); LIST.add(-0x1.f1249ap125F); }

    /**
     * This color constant "dull red magenta" has RGBA8888 code {@code B64F95FF}, L 0.46666667, A 0.57254905, B 0.47058824, alpha 1.0, hue 0.9387114, saturation 0.32070822, and chroma 0.15595676.
     * It can be represented as a packed float with the constant {@code -0x1.f124eep125F}.
     * <pre>
     * <font style='background-color: #B64F95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B64F95; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B64F95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B64F95'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B64F95'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B64F95'>&nbsp;@&nbsp;</font><font style='background-color: #B64F95; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B64F95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B64F95; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_RED_MAGENTA = -0x1.f124eep125F;
    static { NAMED.put("dull red magenta", -0x1.f124eep125F); LIST.add(-0x1.f124eep125F); }

    /**
     * This color constant "pale red magenta" has RGBA8888 code {@code EC7BC6FF}, L 0.6313726, A 0.57254905, B 0.47058824, alpha 1.0, hue 0.9387114, saturation 0.46577543, and chroma 0.15595676.
     * It can be represented as a packed float with the constant {@code -0x1.f12542p125F}.
     * <pre>
     * <font style='background-color: #EC7BC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC7BC6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC7BC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EC7BC6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EC7BC6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EC7BC6'>&nbsp;@&nbsp;</font><font style='background-color: #EC7BC6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC7BC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC7BC6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED_MAGENTA = -0x1.f12542p125F;
    static { NAMED.put("pale red magenta", -0x1.f12542p125F); LIST.add(-0x1.f12542p125F); }

    /**
     * This color constant "drab magenta red" has RGBA8888 code {@code 722431FF}, L 0.2784314, A 0.5568628, B 0.50980395, alpha 1.0, hue 0.027184589, saturation 0.43731853, and chroma 0.114952646.
     * It can be represented as a packed float with the constant {@code -0x1.051c8ep126F}.
     * <pre>
     * <font style='background-color: #722431;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #722431; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #722431;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #722431'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #722431'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #722431'>&nbsp;@&nbsp;</font><font style='background-color: #722431; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #722431;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #722431; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA_RED = -0x1.051c8ep126F;
    static { NAMED.put("drab magenta red", -0x1.051c8ep126F); LIST.add(-0x1.051c8ep126F); }

    /**
     * This color constant "dull magenta red" has RGBA8888 code {@code AE535EFF}, L 0.44313726, A 0.5568628, B 0.50980395, alpha 1.0, hue 0.027184589, saturation 0.221696, and chroma 0.114952646.
     * It can be represented as a packed float with the constant {@code -0x1.051ce2p126F}.
     * <pre>
     * <font style='background-color: #AE535E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE535E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE535E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE535E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE535E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE535E'>&nbsp;@&nbsp;</font><font style='background-color: #AE535E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE535E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE535E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_MAGENTA_RED = -0x1.051ce2p126F;
    static { NAMED.put("dull magenta red", -0x1.051ce2p126F); LIST.add(-0x1.051ce2p126F); }

    /**
     * This color constant "pale magenta red" has RGBA8888 code {@code E47E88FF}, L 0.6039216, A 0.5568628, B 0.5137255, alpha 1.0, hue 0.03769455, saturation 0.39445984, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.071d34p126F}.
     * <pre>
     * <font style='background-color: #E47E88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E47E88; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E47E88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E47E88'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E47E88'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E47E88'>&nbsp;@&nbsp;</font><font style='background-color: #E47E88; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E47E88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E47E88; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA_RED = -0x1.071d34p126F;
    static { NAMED.put("pale magenta red", -0x1.071d34p126F); LIST.add(-0x1.071d34p126F); }

    /**
     * This color constant "deep red" has RGBA8888 code {@code BD2520FF}, L 0.39215687, A 0.58431375, B 0.54509807, alpha 1.0, hue 0.07817349, saturation 0.6948645, and chroma 0.19048727.
     * It can be represented as a packed float with the constant {@code -0x1.172ac8p126F}.
     * <pre>
     * <font style='background-color: #BD2520;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD2520; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD2520;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD2520'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD2520'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD2520'>&nbsp;@&nbsp;</font><font style='background-color: #BD2520; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD2520;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD2520; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED = -0x1.172ac8p126F;
    static { NAMED.put("deep red", -0x1.172ac8p126F); LIST.add(-0x1.172ac8p126F); }

    /**
     * This color constant "bright red" has RGBA8888 code {@code EB4C40FF}, L 0.5137255, A 0.58431375, B 0.54509807, alpha 1.0, hue 0.07817349, saturation 0.57160026, and chroma 0.19048727.
     * It can be represented as a packed float with the constant {@code -0x1.172b06p126F}.
     * <pre>
     * <font style='background-color: #EB4C40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB4C40; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB4C40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EB4C40'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EB4C40'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EB4C40'>&nbsp;@&nbsp;</font><font style='background-color: #EB4C40; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB4C40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB4C40; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED = -0x1.172b06p126F;
    static { NAMED.put("bright red", -0x1.172b06p126F); LIST.add(-0x1.172b06p126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code BA3D27FF}, L 0.41568628, A 0.5686275, B 0.54509807, alpha 1.0, hue 0.09254123, saturation 0.5905227, and chroma 0.16359681.
     * It can be represented as a packed float with the constant {@code -0x1.1722d4p126F}.
     * <pre>
     * <font style='background-color: #BA3D27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA3D27; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA3D27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA3D27'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA3D27'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA3D27'>&nbsp;@&nbsp;</font><font style='background-color: #BA3D27; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA3D27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA3D27; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.1722d4p126F;
    static { NAMED.put("deep brown red", -0x1.1722d4p126F); LIST.add(-0x1.1722d4p126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code E55F46FF}, L 0.53333336, A 0.5686275, B 0.54509807, alpha 1.0, hue 0.09254123, saturation 0.46374512, and chroma 0.16359681.
     * It can be represented as a packed float with the constant {@code -0x1.17231p126F}.
     * <pre>
     * <font style='background-color: #E55F46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E55F46; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E55F46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E55F46'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E55F46'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E55F46'>&nbsp;@&nbsp;</font><font style='background-color: #E55F46; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E55F46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E55F46; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.17231p126F;
    static { NAMED.put("bright brown red", -0x1.17231p126F); LIST.add(-0x1.17231p126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code BE4424FF}, L 0.43137255, A 0.5647059, B 0.54901963, alpha 1.0, hue 0.10319781, saturation 0.64624375, and chroma 0.16172063.
     * It can be represented as a packed float with the constant {@code -0x1.1920dcp126F}.
     * <pre>
     * <font style='background-color: #BE4424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE4424; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE4424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE4424'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE4424'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE4424'>&nbsp;@&nbsp;</font><font style='background-color: #BE4424; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE4424;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE4424; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.1920dcp126F;
    static { NAMED.put("deep red brown", -0x1.1920dcp126F); LIST.add(-0x1.1920dcp126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code EC6845FF}, L 0.56078434, A 0.5647059, B 0.54901963, alpha 1.0, hue 0.10319781, saturation 0.5369254, and chroma 0.16172063.
     * It can be represented as a packed float with the constant {@code -0x1.19211ep126F}.
     * <pre>
     * <font style='background-color: #EC6845;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC6845; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC6845;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EC6845'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EC6845'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EC6845'>&nbsp;@&nbsp;</font><font style='background-color: #EC6845; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC6845;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC6845; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.19211ep126F;
    static { NAMED.put("bright red brown", -0x1.19211ep126F); LIST.add(-0x1.19211ep126F); }

    /**
     * This color constant "deep brown" has RGBA8888 code {@code A1614AFF}, L 0.44705883, A 0.53333336, B 0.5294118, alpha 1.0, hue 0.115073085, saturation 0.21851419, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.0f10e4p126F}.
     * <pre>
     * <font style='background-color: #A1614A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1614A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1614A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A1614A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A1614A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A1614A'>&nbsp;@&nbsp;</font><font style='background-color: #A1614A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A1614A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A1614A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN = -0x1.0f10e4p126F;
    static { NAMED.put("deep brown", -0x1.0f10e4p126F); LIST.add(-0x1.0f10e4p126F); }

    /**
     * This color constant "bright brown" has RGBA8888 code {@code CB846BFF}, L 0.5764706, A 0.53333336, B 0.5294118, alpha 1.0, hue 0.115073085, saturation 0.1730494, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.0f1126p126F}.
     * <pre>
     * <font style='background-color: #CB846B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB846B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB846B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB846B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB846B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB846B'>&nbsp;@&nbsp;</font><font style='background-color: #CB846B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB846B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB846B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN = -0x1.0f1126p126F;
    static { NAMED.put("bright brown", -0x1.0f1126p126F); LIST.add(-0x1.0f1126p126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code A76240FF}, L 0.45490196, A 0.53333336, B 0.5372549, alpha 1.0, hue 0.13382626, saturation 0.3435064, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.1310e8p126F}.
     * <pre>
     * <font style='background-color: #A76240;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A76240; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A76240;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A76240'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A76240'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A76240'>&nbsp;@&nbsp;</font><font style='background-color: #A76240; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A76240;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A76240; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.1310e8p126F;
    static { NAMED.put("deep orange brown", -0x1.1310e8p126F); LIST.add(-0x1.1310e8p126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code D48963FF}, L 0.59607846, A 0.5294118, B 0.5372549, alpha 1.0, hue 0.14362669, saturation 0.22978139, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130f3p126F}.
     * <pre>
     * <font style='background-color: #D48963;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D48963; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D48963;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D48963'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D48963'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D48963'>&nbsp;@&nbsp;</font><font style='background-color: #D48963; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D48963;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D48963; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.130f3p126F;
    static { NAMED.put("bright orange brown", -0x1.130f3p126F); LIST.add(-0x1.130f3p126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code BA6130FF}, L 0.4745098, A 0.5411765, B 0.54901963, alpha 1.0, hue 0.1387952, saturation 0.5383159, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.1914f2p126F}.
     * <pre>
     * <font style='background-color: #BA6130;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA6130; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA6130;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA6130'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA6130'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA6130'>&nbsp;@&nbsp;</font><font style='background-color: #BA6130; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA6130;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA6130; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.1914f2p126F;
    static { NAMED.put("deep brown orange", -0x1.1914f2p126F); LIST.add(-0x1.1914f2p126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code E78653FF}, L 0.6117647, A 0.5411765, B 0.54901963, alpha 1.0, hue 0.1387952, saturation 0.41799822, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.191538p126F}.
     * <pre>
     * <font style='background-color: #E78653;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E78653; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E78653;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E78653'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E78653'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E78653'>&nbsp;@&nbsp;</font><font style='background-color: #E78653; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E78653;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E78653; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.191538p126F;
    static { NAMED.put("bright brown orange", -0x1.191538p126F); LIST.add(-0x1.191538p126F); }

    /**
     * This color constant "deep orange" has RGBA8888 code {@code BB6420FF}, L 0.47843137, A 0.5372549, B 0.5568628, alpha 1.0, hue 0.15767807, saturation 0.73281515, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.1d12f4p126F}.
     * <pre>
     * <font style='background-color: #BB6420;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB6420; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB6420;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB6420'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB6420'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB6420'>&nbsp;@&nbsp;</font><font style='background-color: #BB6420; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB6420;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB6420; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE = -0x1.1d12f4p126F;
    static { NAMED.put("deep orange", -0x1.1d12f4p126F); LIST.add(-0x1.1d12f4p126F); }

    /**
     * This color constant "bright orange" has RGBA8888 code {@code EB8C48FF}, L 0.627451, A 0.5372549, B 0.5568628, alpha 1.0, hue 0.15767807, saturation 0.4905622, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.1d134p126F}.
     * <pre>
     * <font style='background-color: #EB8C48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB8C48; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB8C48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EB8C48'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EB8C48'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EB8C48'>&nbsp;@&nbsp;</font><font style='background-color: #EB8C48; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB8C48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB8C48; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE = -0x1.1d134p126F;
    static { NAMED.put("bright orange", -0x1.1d134p126F); LIST.add(-0x1.1d134p126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code B67230FF}, L 0.5019608, A 0.52156866, B 0.5529412, alpha 1.0, hue 0.18843779, saturation 0.57345253, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1b0bp126F}.
     * <pre>
     * <font style='background-color: #B67230;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B67230; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B67230;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B67230'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B67230'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B67230'>&nbsp;@&nbsp;</font><font style='background-color: #B67230; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B67230;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B67230; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.1b0bp126F;
    static { NAMED.put("deep saffron orange", -0x1.1b0bp126F); LIST.add(-0x1.1b0bp126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code E59B56FF}, L 0.654902, A 0.52156866, B 0.5529412, alpha 1.0, hue 0.18843779, saturation 0.41057843, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1b0b4ep126F}.
     * <pre>
     * <font style='background-color: #E59B56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E59B56; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E59B56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E59B56'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E59B56'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E59B56'>&nbsp;@&nbsp;</font><font style='background-color: #E59B56; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E59B56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E59B56; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.1b0b4ep126F;
    static { NAMED.put("bright saffron orange", -0x1.1b0b4ep126F); LIST.add(-0x1.1b0b4ep126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code BB7721FF}, L 0.5176471, A 0.52156866, B 0.56078434, alpha 1.0, hue 0.19574447, saturation 0.72997135, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.1f0b08p126F}.
     * <pre>
     * <font style='background-color: #BB7721;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB7721; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB7721;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB7721'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB7721'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB7721'>&nbsp;@&nbsp;</font><font style='background-color: #BB7721; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB7721;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB7721; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.1f0b08p126F;
    static { NAMED.put("deep orange saffron", -0x1.1f0b08p126F); LIST.add(-0x1.1f0b08p126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code ECA24CFF}, L 0.6784314, A 0.52156866, B 0.56078434, alpha 1.0, hue 0.19574447, saturation 0.50040466, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.1f0b5ap126F}.
     * <pre>
     * <font style='background-color: #ECA24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECA24C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECA24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECA24C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECA24C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECA24C'>&nbsp;@&nbsp;</font><font style='background-color: #ECA24C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECA24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECA24C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.1f0b5ap126F;
    static { NAMED.put("bright orange saffron", -0x1.1f0b5ap126F); LIST.add(-0x1.1f0b5ap126F); }

    /**
     * This color constant "deep saffron" has RGBA8888 code {@code B58533FF}, L 0.5411765, A 0.50980395, B 0.5568628, alpha 1.0, hue 0.22281541, saturation 0.58424693, and chroma 0.114952646.
     * It can be represented as a packed float with the constant {@code -0x1.1d0514p126F}.
     * <pre>
     * <font style='background-color: #B58533;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B58533; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B58533;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B58533'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B58533'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B58533'>&nbsp;@&nbsp;</font><font style='background-color: #B58533; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B58533;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B58533; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON = -0x1.1d0514p126F;
    static { NAMED.put("deep saffron", -0x1.1d0514p126F); LIST.add(-0x1.1d0514p126F); }

    /**
     * This color constant "bright saffron" has RGBA8888 code {@code E3B05AFF}, L 0.7019608, A 0.5058824, B 0.5568628, alpha 1.0, hue 0.23358636, saturation 0.41057843, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1d0366p126F}.
     * <pre>
     * <font style='background-color: #E3B05A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3B05A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3B05A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3B05A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3B05A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3B05A'>&nbsp;@&nbsp;</font><font style='background-color: #E3B05A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3B05A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3B05A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON = -0x1.1d0366p126F;
    static { NAMED.put("bright saffron", -0x1.1d0366p126F); LIST.add(-0x1.1d0366p126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code BE9923FF}, L 0.59607846, A 0.49411765, B 0.5686275, alpha 1.0, hue 0.26361084, saturation 0.75232434, and chroma 0.13722007.
     * It can be represented as a packed float with the constant {@code -0x1.22fd3p126F}.
     * <pre>
     * <font style='background-color: #BE9923;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE9923; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE9923;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE9923'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE9923'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE9923'>&nbsp;@&nbsp;</font><font style='background-color: #BE9923; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE9923;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE9923; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.22fd3p126F;
    static { NAMED.put("deep yellow saffron", -0x1.22fd3p126F); LIST.add(-0x1.22fd3p126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code EAC24DFF}, L 0.7529412, A 0.49803922, B 0.5686275, alpha 1.0, hue 0.2545336, saturation 0.56700194, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.22ff8p126F}.
     * <pre>
     * <font style='background-color: #EAC24D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAC24D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAC24D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAC24D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAC24D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAC24D'>&nbsp;@&nbsp;</font><font style='background-color: #EAC24D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAC24D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAC24D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.22ff8p126F;
    static { NAMED.put("bright yellow saffron", -0x1.22ff8p126F); LIST.add(-0x1.22ff8p126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code BDAD3DFF}, L 0.64705884, A 0.4862745, B 0.5647059, alpha 1.0, hue 0.28327167, saturation 0.5746749, and chroma 0.13177444.
     * It can be represented as a packed float with the constant {@code -0x1.20f94ap126F}.
     * <pre>
     * <font style='background-color: #BDAD3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDAD3D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDAD3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BDAD3D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BDAD3D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BDAD3D'>&nbsp;@&nbsp;</font><font style='background-color: #BDAD3D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDAD3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDAD3D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.20f94ap126F;
    static { NAMED.put("deep saffron yellow", -0x1.20f94ap126F); LIST.add(-0x1.20f94ap126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code EADA66FF}, L 0.81960785, A 0.48235294, B 0.5647059, alpha 1.0, hue 0.29236877, saturation 0.4411349, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.20f7a2p126F}.
     * <pre>
     * <font style='background-color: #EADA66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EADA66; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EADA66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EADA66'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EADA66'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EADA66'>&nbsp;@&nbsp;</font><font style='background-color: #EADA66; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EADA66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EADA66; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.20f7a2p126F;
    static { NAMED.put("bright saffron yellow", -0x1.20f7a2p126F); LIST.add(-0x1.20f7a2p126F); }

    /**
     * This color constant "deep yellow" has RGBA8888 code {@code AFBA1DFF}, L 0.6627451, A 0.4627451, B 0.5764706, alpha 1.0, hue 0.32214808, saturation 0.7680849, and chroma 0.16946103.
     * It can be represented as a packed float with the constant {@code -0x1.26ed52p126F}.
     * <pre>
     * <font style='background-color: #AFBA1D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFBA1D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFBA1D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFBA1D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFBA1D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFBA1D'>&nbsp;@&nbsp;</font><font style='background-color: #AFBA1D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFBA1D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFBA1D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW = -0x1.26ed52p126F;
    static { NAMED.put("deep yellow", -0x1.26ed52p126F); LIST.add(-0x1.26ed52p126F); }

    /**
     * This color constant "bright yellow" has RGBA8888 code {@code DFEC54FF}, L 0.85882354, A 0.4627451, B 0.5764706, alpha 1.0, hue 0.32214808, saturation 0.56922495, and chroma 0.16946103.
     * It can be represented as a packed float with the constant {@code -0x1.26edb6p126F}.
     * <pre>
     * <font style='background-color: #DFEC54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFEC54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFEC54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DFEC54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DFEC54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DFEC54'>&nbsp;@&nbsp;</font><font style='background-color: #DFEC54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFEC54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFEC54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW = -0x1.26edb6p126F;
    static { NAMED.put("bright yellow", -0x1.26edb6p126F); LIST.add(-0x1.26edb6p126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code A4BB37FF}, L 0.65882355, A 0.4627451, B 0.5686275, alpha 1.0, hue 0.32915777, saturation 0.62189984, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.22ed5p126F}.
     * <pre>
     * <font style='background-color: #A4BB37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4BB37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4BB37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4BB37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4BB37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4BB37'>&nbsp;@&nbsp;</font><font style='background-color: #A4BB37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4BB37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4BB37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.22ed5p126F;
    static { NAMED.put("deep lime yellow", -0x1.22ed5p126F); LIST.add(-0x1.22ed5p126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code D1EB63FF}, L 0.84313726, A 0.4627451, B 0.5686275, alpha 1.0, hue 0.32915777, saturation 0.4634378, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.22edaep126F}.
     * <pre>
     * <font style='background-color: #D1EB63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1EB63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1EB63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1EB63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1EB63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1EB63'>&nbsp;@&nbsp;</font><font style='background-color: #D1EB63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1EB63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1EB63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.22edaep126F;
    static { NAMED.put("bright lime yellow", -0x1.22edaep126F); LIST.add(-0x1.22edaep126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code 8ABD26FF}, L 0.6392157, A 0.44313726, B 0.57254905, alpha 1.0, hue 0.35581404, saturation 0.7174742, and chroma 0.1836353.
     * It can be represented as a packed float with the constant {@code -0x1.24e346p126F}.
     * <pre>
     * <font style='background-color: #8ABD26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8ABD26; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8ABD26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8ABD26'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8ABD26'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8ABD26'>&nbsp;@&nbsp;</font><font style='background-color: #8ABD26; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8ABD26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8ABD26; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.24e346p126F;
    static { NAMED.put("deep yellow lime", -0x1.24e346p126F); LIST.add(-0x1.24e346p126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code B6EE56FF}, L 0.8235294, A 0.44313726, B 0.57254905, alpha 1.0, hue 0.35581404, saturation 0.53121805, and chroma 0.1836353.
     * It can be represented as a packed float with the constant {@code -0x1.24e3a4p126F}.
     * <pre>
     * <font style='background-color: #B6EE56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6EE56; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6EE56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6EE56'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6EE56'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6EE56'>&nbsp;@&nbsp;</font><font style='background-color: #B6EE56; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6EE56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6EE56; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.24e3a4p126F;
    static { NAMED.put("bright yellow lime", -0x1.24e3a4p126F); LIST.add(-0x1.24e3a4p126F); }

    /**
     * This color constant "deep lime" has RGBA8888 code {@code 78B937FF}, L 0.6156863, A 0.4392157, B 0.5647059, alpha 1.0, hue 0.3700319, saturation 0.59902114, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.20e13ap126F}.
     * <pre>
     * <font style='background-color: #78B937;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78B937; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78B937;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #78B937'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #78B937'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #78B937'>&nbsp;@&nbsp;</font><font style='background-color: #78B937; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #78B937;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #78B937; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME = -0x1.20e13ap126F;
    static { NAMED.put("deep lime", -0x1.20e13ap126F); LIST.add(-0x1.20e13ap126F); }

    /**
     * This color constant "bright lime" has RGBA8888 code {@code A4EB63FF}, L 0.8, A 0.4392157, B 0.5647059, alpha 1.0, hue 0.3700319, saturation 0.4499314, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.20e198p126F}.
     * <pre>
     * <font style='background-color: #A4EB63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4EB63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4EB63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4EB63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4EB63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4EB63'>&nbsp;@&nbsp;</font><font style='background-color: #A4EB63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4EB63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4EB63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME = -0x1.20e198p126F;
    static { NAMED.put("bright lime", -0x1.20e198p126F); LIST.add(-0x1.20e198p126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 5FBA17FF}, L 0.6, A 0.42352942, B 0.57254905, alpha 1.0, hue 0.37918407, saturation 0.7640954, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.24d932p126F}.
     * <pre>
     * <font style='background-color: #5FBA17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5FBA17; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5FBA17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5FBA17'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5FBA17'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5FBA17'>&nbsp;@&nbsp;</font><font style='background-color: #5FBA17; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5FBA17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5FBA17; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.24d932p126F;
    static { NAMED.put("deep green lime", -0x1.24d932p126F); LIST.add(-0x1.24d932p126F); }

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
     * This color constant "deep lime green" has RGBA8888 code {@code 41BA30FF}, L 0.5882353, A 0.41568628, B 0.5647059, alpha 1.0, hue 0.39580947, saturation 0.7062076, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.20d52cp126F}.
     * <pre>
     * <font style='background-color: #41BA30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #41BA30; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #41BA30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #41BA30'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #41BA30'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #41BA30'>&nbsp;@&nbsp;</font><font style='background-color: #41BA30; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #41BA30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #41BA30; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.20d52cp126F;
    static { NAMED.put("deep lime green", -0x1.20d52cp126F); LIST.add(-0x1.20d52cp126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code 6CEA5BFF}, L 0.75686276, A 0.41568628, B 0.5647059, alpha 1.0, hue 0.39580947, saturation 0.51541597, and chroma 0.21173172.
     * It can be represented as a packed float with the constant {@code -0x1.20d582p126F}.
     * <pre>
     * <font style='background-color: #6CEA5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6CEA5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6CEA5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6CEA5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6CEA5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6CEA5B'>&nbsp;@&nbsp;</font><font style='background-color: #6CEA5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6CEA5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6CEA5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.20d582p126F;
    static { NAMED.put("bright lime green", -0x1.20d582p126F); LIST.add(-0x1.20d582p126F); }

    /**
     * This color constant "deep green" has RGBA8888 code {@code 00BC40FF}, L 0.58431375, A 0.40784314, B 0.5568628, alpha 1.0, hue 0.41200298, saturation 0.99017936, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.1cd12ap126F}.
     * <pre>
     * <font style='background-color: #00BC40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00BC40; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00BC40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00BC40'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00BC40'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00BC40'>&nbsp;@&nbsp;</font><font style='background-color: #00BC40; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00BC40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00BC40; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN = -0x1.1cd12ap126F;
    static { NAMED.put("deep green", -0x1.1cd12ap126F); LIST.add(-0x1.1cd12ap126F); }

    /**
     * This color constant "bright green" has RGBA8888 code {@code 25DB5AFF}, L 0.68235296, A 0.40392157, B 0.5568628, alpha 1.0, hue 0.41494054, saturation 0.91575456, and chroma 0.22241627.
     * It can be represented as a packed float with the constant {@code -0x1.1ccf5cp126F}.
     * <pre>
     * <font style='background-color: #25DB5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25DB5A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25DB5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #25DB5A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #25DB5A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #25DB5A'>&nbsp;@&nbsp;</font><font style='background-color: #25DB5A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25DB5A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25DB5A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN = -0x1.1ccf5cp126F;
    static { NAMED.put("bright green", -0x1.1ccf5cp126F); LIST.add(-0x1.1ccf5cp126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 18B27FFF}, L 0.5686275, A 0.42745098, B 0.5176471, alpha 1.0, hue 0.4620256, saturation 0.9293383, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.08db22p126F}.
     * <pre>
     * <font style='background-color: #18B27F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #18B27F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #18B27F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #18B27F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #18B27F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #18B27F'>&nbsp;@&nbsp;</font><font style='background-color: #18B27F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #18B27F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #18B27F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.08db22p126F;
    static { NAMED.put("deep cyan green", -0x1.08db22p126F); LIST.add(-0x1.08db22p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code 37C993FF}, L 0.64705884, A 0.42745098, B 0.5176471, alpha 1.0, hue 0.4620256, saturation 0.7662835, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.08db4ap126F}.
     * <pre>
     * <font style='background-color: #37C993;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #37C993; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #37C993;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #37C993'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #37C993'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #37C993'>&nbsp;@&nbsp;</font><font style='background-color: #37C993; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #37C993;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #37C993; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.08db4ap126F;
    static { NAMED.put("bright cyan green", -0x1.08db4ap126F); LIST.add(-0x1.08db4ap126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 00B8A1FF}, L 0.59607846, A 0.43137255, B 0.49803922, alpha 1.0, hue 0.5045336, saturation 0.97282284, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.fedd3p125F}.
     * <pre>
     * <font style='background-color: #00B8A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00B8A1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00B8A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00B8A1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00B8A1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00B8A1'>&nbsp;@&nbsp;</font><font style='background-color: #00B8A1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00B8A1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00B8A1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.fedd3p125F;
    static { NAMED.put("deep green cyan", -0x1.fedd3p125F); LIST.add(-0x1.fedd3p125F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code 25D6BDFF}, L 0.69411767, A 0.42745098, B 0.49803922, alpha 1.0, hue 0.5042883, saturation 0.8780644, and chroma 0.14458403.
     * It can be represented as a packed float with the constant {@code -0x1.fedb62p125F}.
     * <pre>
     * <font style='background-color: #25D6BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25D6BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25D6BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #25D6BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #25D6BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #25D6BD'>&nbsp;@&nbsp;</font><font style='background-color: #25D6BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25D6BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25D6BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.fedb62p125F;
    static { NAMED.put("bright green cyan", -0x1.fedb62p125F); LIST.add(-0x1.fedb62p125F); }

    /**
     * This color constant "deep cyan" has RGBA8888 code {@code 25BBBFFF}, L 0.61960787, A 0.4392157, B 0.47843137, alpha 1.0, hue 0.55425555, saturation 0.8585598, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.f4e13cp125F}.
     * <pre>
     * <font style='background-color: #25BBBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25BBBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25BBBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #25BBBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #25BBBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #25BBBF'>&nbsp;@&nbsp;</font><font style='background-color: #25BBBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #25BBBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #25BBBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN = -0x1.f4e13cp125F;
    static { NAMED.put("deep cyan", -0x1.f4e13cp125F); LIST.add(-0x1.f4e13cp125F); }

    /**
     * This color constant "bright cyan" has RGBA8888 code {@code 5AEBEFFF}, L 0.79607844, A 0.4392157, B 0.47843137, alpha 1.0, hue 0.55425555, saturation 0.6596555, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.f4e196p125F}.
     * <pre>
     * <font style='background-color: #5AEBEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AEBEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AEBEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5AEBEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5AEBEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5AEBEF'>&nbsp;@&nbsp;</font><font style='background-color: #5AEBEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AEBEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AEBEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN = -0x1.f4e196p125F;
    static { NAMED.put("bright cyan", -0x1.f4e196p125F); LIST.add(-0x1.f4e196p125F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 009EC0FF}, L 0.5372549, A 0.4509804, B 0.4627451, alpha 1.0, hue 0.6034426, saturation 0.8283974, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.ece712p125F}.
     * <pre>
     * <font style='background-color: #009EC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #009EC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #009EC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #009EC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #009EC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #009EC0'>&nbsp;@&nbsp;</font><font style='background-color: #009EC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #009EC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #009EC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.ece712p125F;
    static { NAMED.put("deep blue cyan", -0x1.ece712p125F); LIST.add(-0x1.ece712p125F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 41CBF0FF}, L 0.69411767, A 0.44705883, B 0.45882353, alpha 1.0, hue 0.6052204, saturation 0.74987984, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.eae562p125F}.
     * <pre>
     * <font style='background-color: #41CBF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #41CBF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #41CBF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #41CBF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #41CBF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #41CBF0'>&nbsp;@&nbsp;</font><font style='background-color: #41CBF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #41CBF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #41CBF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.eae562p125F;
    static { NAMED.put("bright blue cyan", -0x1.eae562p125F); LIST.add(-0x1.eae562p125F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 097ABAFF}, L 0.44313726, A 0.4627451, B 0.4392157, alpha 1.0, hue 0.6624788, saturation 0.94008887, and chroma 0.14202859.
     * It can be represented as a packed float with the constant {@code -0x1.e0ece2p125F}.
     * <pre>
     * <font style='background-color: #097ABA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #097ABA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #097ABA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #097ABA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #097ABA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #097ABA'>&nbsp;@&nbsp;</font><font style='background-color: #097ABA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #097ABA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #097ABA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.e0ece2p125F;
    static { NAMED.put("deep cyan blue", -0x1.e0ece2p125F); LIST.add(-0x1.e0ece2p125F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code 40A6ECFF}, L 0.5921569, A 0.4627451, B 0.4392157, alpha 1.0, hue 0.6624788, saturation 0.63857025, and chroma 0.14202859.
     * It can be represented as a packed float with the constant {@code -0x1.e0ed2ep125F}.
     * <pre>
     * <font style='background-color: #40A6EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40A6EC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40A6EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #40A6EC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #40A6EC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #40A6EC'>&nbsp;@&nbsp;</font><font style='background-color: #40A6EC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #40A6EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #40A6EC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.e0ed2ep125F;
    static { NAMED.put("bright cyan blue", -0x1.e0ed2ep125F); LIST.add(-0x1.e0ed2ep125F); }

    /**
     * This color constant "deep blue" has RGBA8888 code {@code 0024C0FF}, L 0.2784314, A 0.4862745, B 0.3764706, alpha 1.0, hue 0.7323789, saturation 0.74376416, and chroma 0.24760818.
     * It can be represented as a packed float with the constant {@code -0x1.c0f88ep125F}.
     * <pre>
     * <font style='background-color: #0024C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0024C0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0024C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0024C0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0024C0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0024C0'>&nbsp;@&nbsp;</font><font style='background-color: #0024C0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0024C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0024C0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE = -0x1.c0f88ep125F;
    static { NAMED.put("deep blue", -0x1.c0f88ep125F); LIST.add(-0x1.c0f88ep125F); }

    /**
     * This color constant "bright blue" has RGBA8888 code {@code 164CEFFF}, L 0.38039216, A 0.48235294, B 0.3764706, alpha 1.0, hue 0.72740346, saturation 0.77051127, and chroma 0.24859223.
     * It can be represented as a packed float with the constant {@code -0x1.c0f6c2p125F}.
     * <pre>
     * <font style='background-color: #164CEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #164CEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #164CEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #164CEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #164CEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #164CEF'>&nbsp;@&nbsp;</font><font style='background-color: #164CEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #164CEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #164CEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE = -0x1.c0f6c2p125F;
    static { NAMED.put("bright blue", -0x1.c0f6c2p125F); LIST.add(-0x1.c0f6c2p125F); }

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
     * This color constant "bright violet blue" has RGBA8888 code {@code 4C51E3FF}, L 0.40392157, A 0.50980395, B 0.39215687, alpha 1.0, hue 0.764433, saturation 0.6314373, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.c904cep125F}.
     * <pre>
     * <font style='background-color: #4C51E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C51E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C51E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4C51E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4C51E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4C51E3'>&nbsp;@&nbsp;</font><font style='background-color: #4C51E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C51E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C51E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.c904cep125F;
    static { NAMED.put("bright violet blue", -0x1.c904cep125F); LIST.add(-0x1.c904cep125F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 4E22C1FF}, L 0.3137255, A 0.5294118, B 0.3882353, alpha 1.0, hue 0.7909493, saturation 0.74036974, and chroma 0.2302369.
     * It can be represented as a packed float with the constant {@code -0x1.c70eap125F}.
     * <pre>
     * <font style='background-color: #4E22C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E22C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E22C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E22C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E22C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E22C1'>&nbsp;@&nbsp;</font><font style='background-color: #4E22C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E22C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E22C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.c70eap125F;
    static { NAMED.put("deep blue violet", -0x1.c70eap125F); LIST.add(-0x1.c70eap125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code 6E4CF3FF}, L 0.43529412, A 0.53333336, B 0.3882353, alpha 1.0, hue 0.7961206, saturation 0.7765158, and chroma 0.23234801.
     * It can be represented as a packed float with the constant {@code -0x1.c710dep125F}.
     * <pre>
     * <font style='background-color: #6E4CF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E4CF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E4CF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E4CF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E4CF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E4CF3'>&nbsp;@&nbsp;</font><font style='background-color: #6E4CF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E4CF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E4CF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.c710dep125F;
    static { NAMED.put("bright blue violet", -0x1.c710dep125F); LIST.add(-0x1.c710dep125F); }

    /**
     * This color constant "deep violet" has RGBA8888 code {@code 672DBBFF}, L 0.34509805, A 0.54509807, B 0.40392157, alpha 1.0, hue 0.8198416, saturation 0.5895076, and chroma 0.21144326.
     * It can be represented as a packed float with the constant {@code -0x1.cf16bp125F}.
     * <pre>
     * <font style='background-color: #672DBB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #672DBB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #672DBB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #672DBB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #672DBB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #672DBB'>&nbsp;@&nbsp;</font><font style='background-color: #672DBB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #672DBB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #672DBB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET = -0x1.cf16bp125F;
    static { NAMED.put("deep violet", -0x1.cf16bp125F); LIST.add(-0x1.cf16bp125F); }

    /**
     * This color constant "bright violet" has RGBA8888 code {@code 8B53EAFF}, L 0.46666667, A 0.54901963, B 0.40392157, alpha 1.0, hue 0.82508534, saturation 0.6264686, and chroma 0.21487926.
     * It can be represented as a packed float with the constant {@code -0x1.cf18eep125F}.
     * <pre>
     * <font style='background-color: #8B53EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B53EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B53EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B53EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B53EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B53EA'>&nbsp;@&nbsp;</font><font style='background-color: #8B53EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B53EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B53EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET = -0x1.cf18eep125F;
    static { NAMED.put("bright violet", -0x1.cf18eep125F); LIST.add(-0x1.cf18eep125F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 7823C5FF}, L 0.36078432, A 0.5647059, B 0.4, alpha 1.0, hue 0.84141475, saturation 0.7639356, and chroma 0.2372866.
     * It can be represented as a packed float with the constant {@code -0x1.cd20b8p125F}.
     * <pre>
     * <font style='background-color: #7823C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7823C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7823C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7823C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7823C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7823C5'>&nbsp;@&nbsp;</font><font style='background-color: #7823C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7823C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7823C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.cd20b8p125F;
    static { NAMED.put("deep purple violet", -0x1.cd20b8p125F); LIST.add(-0x1.cd20b8p125F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code 9E4BF4FF}, L 0.48235294, A 0.5647059, B 0.4, alpha 1.0, hue 0.84141475, saturation 0.7424174, and chroma 0.2372866.
     * It can be represented as a packed float with the constant {@code -0x1.cd20f6p125F}.
     * <pre>
     * <font style='background-color: #9E4BF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E4BF4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E4BF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E4BF4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E4BF4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E4BF4'>&nbsp;@&nbsp;</font><font style='background-color: #9E4BF4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E4BF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E4BF4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.cd20f6p125F;
    static { NAMED.put("bright purple violet", -0x1.cd20f6p125F); LIST.add(-0x1.cd20f6p125F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 8130BFFF}, L 0.38039216, A 0.5647059, B 0.4117647, alpha 1.0, hue 0.85071796, saturation 0.60912514, and chroma 0.21798135.
     * It can be represented as a packed float with the constant {@code -0x1.d320c2p125F}.
     * <pre>
     * <font style='background-color: #8130BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8130BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8130BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8130BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8130BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8130BF'>&nbsp;@&nbsp;</font><font style='background-color: #8130BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8130BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8130BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.d320c2p125F;
    static { NAMED.put("deep violet purple", -0x1.d320c2p125F); LIST.add(-0x1.d320c2p125F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code A755ECFF}, L 0.5019608, A 0.5647059, B 0.4117647, alpha 1.0, hue 0.85071796, saturation 0.6446871, and chroma 0.21798135.
     * It can be represented as a packed float with the constant {@code -0x1.d321p125F}.
     * <pre>
     * <font style='background-color: #A755EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A755EC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A755EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A755EC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A755EC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A755EC'>&nbsp;@&nbsp;</font><font style='background-color: #A755EC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A755EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A755EC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.d321p125F;
    static { NAMED.put("bright violet purple", -0x1.d321p125F); LIST.add(-0x1.d321p125F); }

    /**
     * This color constant "deep purple" has RGBA8888 code {@code 9123C7FF}, L 0.39215687, A 0.5803922, B 0.40784314, alpha 1.0, hue 0.86417323, saturation 0.7826568, and chroma 0.24363229.
     * It can be represented as a packed float with the constant {@code -0x1.d128c8p125F}.
     * <pre>
     * <font style='background-color: #9123C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9123C7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9123C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9123C7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9123C7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9123C7'>&nbsp;@&nbsp;</font><font style='background-color: #9123C7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9123C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9123C7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE = -0x1.d128c8p125F;
    static { NAMED.put("deep purple", -0x1.d128c8p125F); LIST.add(-0x1.d128c8p125F); }

    /**
     * This color constant "bright purple" has RGBA8888 code {@code BB4EF7FF}, L 0.52156866, A 0.5803922, B 0.40784314, alpha 1.0, hue 0.86417323, saturation 0.74007136, and chroma 0.24363229.
     * It can be represented as a packed float with the constant {@code -0x1.d1290ap125F}.
     * <pre>
     * <font style='background-color: #BB4EF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB4EF7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB4EF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB4EF7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB4EF7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB4EF7'>&nbsp;@&nbsp;</font><font style='background-color: #BB4EF7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB4EF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB4EF7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE = -0x1.d1290ap125F;
    static { NAMED.put("bright purple", -0x1.d1290ap125F); LIST.add(-0x1.d1290ap125F); }

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
     * This color constant "bright magenta purple" has RGBA8888 code {@code C759EAFF}, L 0.54509807, A 0.5803922, B 0.42352942, alpha 1.0, hue 0.878975, saturation 0.5617297, and chroma 0.2210399.
     * It can be represented as a packed float with the constant {@code -0x1.d92916p125F}.
     * <pre>
     * <font style='background-color: #C759EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C759EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C759EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C759EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C759EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C759EA'>&nbsp;@&nbsp;</font><font style='background-color: #C759EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C759EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C759EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.d92916p125F;
    static { NAMED.put("bright magenta purple", -0x1.d92916p125F); LIST.add(-0x1.d92916p125F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code AF27C8FF}, L 0.43529412, A 0.59607846, B 0.41960785, alpha 1.0, hue 0.8891002, saturation 0.75561106, and chroma 0.24957238.
     * It can be represented as a packed float with the constant {@code -0x1.d730dep125F}.
     * <pre>
     * <font style='background-color: #AF27C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF27C8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF27C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF27C8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF27C8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF27C8'>&nbsp;@&nbsp;</font><font style='background-color: #AF27C8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF27C8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF27C8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.d730dep125F;
    static { NAMED.put("deep purple magenta", -0x1.d730dep125F); LIST.add(-0x1.d730dep125F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code DC51F6FF}, L 0.5686275, A 0.59607846, B 0.41960785, alpha 1.0, hue 0.8891002, saturation 0.7161089, and chroma 0.24957238.
     * It can be represented as a packed float with the constant {@code -0x1.d73122p125F}.
     * <pre>
     * <font style='background-color: #DC51F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC51F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC51F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC51F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC51F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC51F6'>&nbsp;@&nbsp;</font><font style='background-color: #DC51F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC51F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC51F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.d73122p125F;
    static { NAMED.put("bright purple magenta", -0x1.d73122p125F); LIST.add(-0x1.d73122p125F); }

    /**
     * This color constant "deep magenta" has RGBA8888 code {@code B435B9FF}, L 0.44705883, A 0.5921569, B 0.43529412, alpha 1.0, hue 0.9025601, saturation 0.6274435, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.df2ee4p125F}.
     * <pre>
     * <font style='background-color: #B435B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B435B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B435B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B435B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B435B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B435B9'>&nbsp;@&nbsp;</font><font style='background-color: #B435B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B435B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B435B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA = -0x1.df2ee4p125F;
    static { NAMED.put("deep magenta", -0x1.df2ee4p125F); LIST.add(-0x1.df2ee4p125F); }

    /**
     * This color constant "bright magenta" has RGBA8888 code {@code E35CE7FF}, L 0.58431375, A 0.5921569, B 0.43529412, alpha 1.0, hue 0.9025601, saturation 0.47301805, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.df2f2ap125F}.
     * <pre>
     * <font style='background-color: #E35CE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E35CE7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E35CE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E35CE7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E35CE7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E35CE7'>&nbsp;@&nbsp;</font><font style='background-color: #E35CE7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E35CE7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E35CE7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA = -0x1.df2f2ap125F;
    static { NAMED.put("bright magenta", -0x1.df2f2ap125F); LIST.add(-0x1.df2f2ap125F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code BE2285FF}, L 0.41960785, A 0.6039216, B 0.47058824, alpha 1.0, hue 0.9561122, saturation 0.7524335, and chroma 0.21516311.
     * It can be represented as a packed float with the constant {@code -0x1.f134d6p125F}.
     * <pre>
     * <font style='background-color: #BE2285;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE2285; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE2285;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE2285'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE2285'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE2285'>&nbsp;@&nbsp;</font><font style='background-color: #BE2285; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE2285;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE2285; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.f134d6p125F;
    static { NAMED.put("deep red magenta", -0x1.f134d6p125F); LIST.add(-0x1.f134d6p125F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code EB49AAFF}, L 0.5411765, A 0.6039216, B 0.47058824, alpha 1.0, hue 0.9561122, saturation 0.5322574, and chroma 0.21516311.
     * It can be represented as a packed float with the constant {@code -0x1.f13514p125F}.
     * <pre>
     * <font style='background-color: #EB49AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB49AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB49AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EB49AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EB49AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EB49AA'>&nbsp;@&nbsp;</font><font style='background-color: #EB49AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB49AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB49AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.f13514p125F;
    static { NAMED.put("bright red magenta", -0x1.f13514p125F); LIST.add(-0x1.f13514p125F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code B82E56FF}, L 0.40392157, A 0.5882353, B 0.50980395, alpha 1.0, hue 0.017621128, saturation 0.59902114, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.052ccep126F}.
     * <pre>
     * <font style='background-color: #B82E56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B82E56; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B82E56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B82E56'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B82E56'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B82E56'>&nbsp;@&nbsp;</font><font style='background-color: #B82E56; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B82E56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B82E56; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.052ccep126F;
    static { NAMED.put("deep magenta red", -0x1.052ccep126F); LIST.add(-0x1.052ccep126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code E45076FF}, L 0.52156866, A 0.5882353, B 0.50980395, alpha 1.0, hue 0.017621128, saturation 0.46356493, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.052d0ap126F}.
     * <pre>
     * <font style='background-color: #E45076;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E45076; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E45076;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E45076'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E45076'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E45076'>&nbsp;@&nbsp;</font><font style='background-color: #E45076; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E45076;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E45076; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.052d0ap126F;
    static { NAMED.put("bright magenta red", -0x1.052d0ap126F); LIST.add(-0x1.052d0ap126F); }

    /**
     * This color constant "some brown red" has RGBA8888 code {@code FF1B00FF}, L 0.49803922, A 0.60784316, B 0.56078434, alpha 1.0, hue 0.08169193, saturation 0.8494859, and chroma 0.24662022.
     * It can be represented as a packed float with the constant {@code -0x1.1f36fep126F}.
     * <pre>
     * <font style='background-color: #FF1B00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF1B00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF1B00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF1B00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF1B00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF1B00'>&nbsp;@&nbsp;</font><font style='background-color: #FF1B00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF1B00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF1B00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BROWN_RED = -0x1.1f36fep126F;
    static { NAMED.put("some brown red", -0x1.1f36fep126F); LIST.add(-0x1.1f36fep126F); }

    /**
     * This color constant "more brown red" has RGBA8888 code {@code F34428FF}, L 0.5137255, A 0.5882353, B 0.5568628, alpha 1.0, hue 0.091120966, saturation 0.7107694, and chroma 0.20912123.
     * It can be represented as a packed float with the constant {@code -0x1.1d2d06p126F}.
     * <pre>
     * <font style='background-color: #F34428;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F34428; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F34428;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F34428'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F34428'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F34428'>&nbsp;@&nbsp;</font><font style='background-color: #F34428; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F34428;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F34428; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BROWN_RED = -0x1.1d2d06p126F;
    static { NAMED.put("more brown red", -0x1.1d2d06p126F); LIST.add(-0x1.1d2d06p126F); }

    /**
     * This color constant "more red brown" has RGBA8888 code {@code FC4400FF}, L 0.5254902, A 0.5882353, B 0.5647059, alpha 1.0, hue 0.10071799, saturation 0.85076153, and chroma 0.21798135.
     * It can be represented as a packed float with the constant {@code -0x1.212d0cp126F}.
     * <pre>
     * <font style='background-color: #FC4400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC4400; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC4400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC4400'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC4400'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC4400'>&nbsp;@&nbsp;</font><font style='background-color: #FC4400; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC4400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC4400; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_RED_BROWN = -0x1.212d0cp126F;
    static { NAMED.put("more red brown", -0x1.212d0cp126F); LIST.add(-0x1.212d0cp126F); }

    /**
     * This color constant "some red brown" has RGBA8888 code {@code CD7758FF}, L 0.54901963, A 0.5411765, B 0.53333336, alpha 1.0, hue 0.10831932, saturation 0.21330996, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.111518p126F}.
     * <pre>
     * <font style='background-color: #CD7758;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD7758; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD7758;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD7758'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD7758'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD7758'>&nbsp;@&nbsp;</font><font style='background-color: #CD7758; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD7758;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD7758; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_RED_BROWN = -0x1.111518p126F;
    static { NAMED.put("some red brown", -0x1.111518p126F); LIST.add(-0x1.111518p126F); }

    /**
     * This color constant "some orange brown" has RGBA8888 code {@code D7764CFF}, L 0.5568628, A 0.54509807, B 0.54509807, alpha 1.0, hue 0.125, saturation 0.38385487, and chroma 0.12705825.
     * It can be represented as a packed float with the constant {@code -0x1.17171cp126F}.
     * <pre>
     * <font style='background-color: #D7764C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7764C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7764C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7764C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7764C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7764C'>&nbsp;@&nbsp;</font><font style='background-color: #D7764C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7764C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7764C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_ORANGE_BROWN = -0x1.17171cp126F;
    static { NAMED.put("some orange brown", -0x1.17171cp126F); LIST.add(-0x1.17171cp126F); }

    /**
     * This color constant "more orange brown" has RGBA8888 code {@code ED7432FF}, L 0.5803922, A 0.5529412, B 0.56078434, alpha 1.0, hue 0.13595085, saturation 0.6371948, and chroma 0.1605844.
     * It can be represented as a packed float with the constant {@code -0x1.1f1b28p126F}.
     * <pre>
     * <font style='background-color: #ED7432;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED7432; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED7432;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ED7432'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ED7432'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ED7432'>&nbsp;@&nbsp;</font><font style='background-color: #ED7432; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED7432;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED7432; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_ORANGE_BROWN = -0x1.1f1b28p126F;
    static { NAMED.put("more orange brown", -0x1.1f1b28p126F); LIST.add(-0x1.1f1b28p126F); }

    /**
     * This color constant "more brown orange" has RGBA8888 code {@code C59171FF}, L 0.6, A 0.5176471, B 0.5294118, alpha 1.0, hue 0.16398115, saturation 0.13562326, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.0f0932p126F}.
     * <pre>
     * <font style='background-color: #C59171;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C59171; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C59171;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C59171'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C59171'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C59171'>&nbsp;@&nbsp;</font><font style='background-color: #C59171; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C59171;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C59171; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BROWN_ORANGE = -0x1.0f0932p126F;
    static { NAMED.put("more brown orange", -0x1.0f0932p126F); LIST.add(-0x1.0f0932p126F); }

    /**
     * This color constant "some brown orange" has RGBA8888 code {@code F87E26FF}, L 0.6117647, A 0.54901963, B 0.5686275, alpha 1.0, hue 0.15127131, saturation 0.75502497, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.231938p126F}.
     * <pre>
     * <font style='background-color: #F87E26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F87E26; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F87E26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F87E26'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F87E26'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F87E26'>&nbsp;@&nbsp;</font><font style='background-color: #F87E26; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F87E26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F87E26; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BROWN_ORANGE = -0x1.231938p126F;
    static { NAMED.put("some brown orange", -0x1.231938p126F); LIST.add(-0x1.231938p126F); }

    /**
     * This color constant "some saffron orange" has RGBA8888 code {@code F98000FF}, L 0.6156863, A 0.54509807, B 0.57254905, alpha 1.0, hue 0.1614735, saturation 0.8412188, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.25173ap126F}.
     * <pre>
     * <font style='background-color: #F98000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F98000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F98000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F98000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F98000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F98000'>&nbsp;@&nbsp;</font><font style='background-color: #F98000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F98000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F98000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_SAFFRON_ORANGE = -0x1.25173ap126F;
    static { NAMED.put("some saffron orange", -0x1.25173ap126F); LIST.add(-0x1.25173ap126F); }

    /**
     * This color constant "more saffron orange" has RGBA8888 code {@code F49023FF}, L 0.6431373, A 0.53333336, B 0.57254905, alpha 1.0, hue 0.18145926, saturation 0.76679385, and chroma 0.15905683.
     * It can be represented as a packed float with the constant {@code -0x1.251148p126F}.
     * <pre>
     * <font style='background-color: #F49023;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F49023; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F49023;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F49023'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F49023'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F49023'>&nbsp;@&nbsp;</font><font style='background-color: #F49023; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F49023;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F49023; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_SAFFRON_ORANGE = -0x1.251148p126F;
    static { NAMED.put("more saffron orange", -0x1.251148p126F); LIST.add(-0x1.251148p126F); }

    /**
     * This color constant "more orange saffron" has RGBA8888 code {@code FA9600FF}, L 0.6627451, A 0.5294118, B 0.5764706, alpha 1.0, hue 0.19157475, saturation 0.8433764, and chroma 0.1632233.
     * It can be represented as a packed float with the constant {@code -0x1.270f52p126F}.
     * <pre>
     * <font style='background-color: #FA9600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA9600; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA9600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA9600'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA9600'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA9600'>&nbsp;@&nbsp;</font><font style='background-color: #FA9600; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA9600;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA9600; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_ORANGE_SAFFRON = -0x1.270f52p126F;
    static { NAMED.put("more orange saffron", -0x1.270f52p126F); LIST.add(-0x1.270f52p126F); }

    /**
     * This color constant "some orange saffron" has RGBA8888 code {@code F2A733FF}, L 0.69411767, A 0.5137255, B 0.57254905, alpha 1.0, hue 0.22023249, saturation 0.6849414, and chroma 0.14709508.
     * It can be represented as a packed float with the constant {@code -0x1.250762p126F}.
     * <pre>
     * <font style='background-color: #F2A733;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2A733; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2A733;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2A733'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2A733'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2A733'>&nbsp;@&nbsp;</font><font style='background-color: #F2A733; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2A733;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2A733; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_ORANGE_SAFFRON = -0x1.250762p126F;
    static { NAMED.put("some orange saffron", -0x1.250762p126F); LIST.add(-0x1.250762p126F); }

    /**
     * This color constant "some yellow saffron" has RGBA8888 code {@code F6AF00FF}, L 0.7137255, A 0.50980395, B 0.5803922, alpha 1.0, hue 0.23067485, saturation 0.8240551, and chroma 0.16134278.
     * It can be represented as a packed float with the constant {@code -0x1.29056cp126F}.
     * <pre>
     * <font style='background-color: #F6AF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6AF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6AF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6AF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6AF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6AF00'>&nbsp;@&nbsp;</font><font style='background-color: #F6AF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6AF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6AF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_YELLOW_SAFFRON = -0x1.29056cp126F;
    static { NAMED.put("some yellow saffron", -0x1.29056cp126F); LIST.add(-0x1.29056cp126F); }

    /**
     * This color constant "more yellow saffron" has RGBA8888 code {@code F9C834FF}, L 0.78431374, A 0.49803922, B 0.5803922, alpha 1.0, hue 0.2538697, saturation 0.71506006, and chroma 0.16020387.
     * It can be represented as a packed float with the constant {@code -0x1.28ff9p126F}.
     * <pre>
     * <font style='background-color: #F9C834;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9C834; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9C834;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F9C834'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F9C834'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F9C834'>&nbsp;@&nbsp;</font><font style='background-color: #F9C834; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9C834;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9C834; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_YELLOW_SAFFRON = -0x1.28ff9p126F;
    static { NAMED.put("more yellow saffron", -0x1.28ff9p126F); LIST.add(-0x1.28ff9p126F); }

    /**
     * This color constant "more saffron yellow" has RGBA8888 code {@code FEDD00FF}, L 0.84313726, A 0.48235294, B 0.5921569, alpha 1.0, hue 0.2801204, saturation 0.86341786, and chroma 0.18692946.
     * It can be represented as a packed float with the constant {@code -0x1.2ef7aep126F}.
     * <pre>
     * <font style='background-color: #FEDD00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDD00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEDD00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEDD00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEDD00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEDD00'>&nbsp;@&nbsp;</font><font style='background-color: #FEDD00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEDD00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEDD00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_SAFFRON_YELLOW = -0x1.2ef7aep126F;
    static { NAMED.put("more saffron yellow", -0x1.2ef7aep126F); LIST.add(-0x1.2ef7aep126F); }

    /**
     * This color constant "some saffron yellow" has RGBA8888 code {@code FFF932FF}, L 0.92941177, A 0.46666667, B 0.5921569, alpha 1.0, hue 0.30522364, saturation 0.75553876, and chroma 0.19523436.
     * It can be represented as a packed float with the constant {@code -0x1.2eefdap126F}.
     * <pre>
     * <font style='background-color: #FFF932;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF932; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF932;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFF932'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFF932'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFF932'>&nbsp;@&nbsp;</font><font style='background-color: #FFF932; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFF932;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFF932; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_SAFFRON_YELLOW = -0x1.2eefdap126F;
    static { NAMED.put("some saffron yellow", -0x1.2eefdap126F); LIST.add(-0x1.2eefdap126F); }

    /**
     * This color constant "some lime yellow" has RGBA8888 code {@code EDFF00FF}, L 0.9254902, A 0.45490196, B 0.59607846, alpha 1.0, hue 0.31984162, saturation 0.8561619, and chroma 0.21144326.
     * It can be represented as a packed float with the constant {@code -0x1.30e9d8p126F}.
     * <pre>
     * <font style='background-color: #EDFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDFF00'>&nbsp;@&nbsp;</font><font style='background-color: #EDFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_LIME_YELLOW = -0x1.30e9d8p126F;
    static { NAMED.put("some lime yellow", -0x1.30e9d8p126F); LIST.add(-0x1.30e9d8p126F); }

    /**
     * This color constant "more lime yellow" has RGBA8888 code {@code D7FB38FF}, L 0.8901961, A 0.44705883, B 0.5882353, alpha 1.0, hue 0.33601886, saturation 0.728138, and chroma 0.2049944.
     * It can be represented as a packed float with the constant {@code -0x1.2ce5c6p126F}.
     * <pre>
     * <font style='background-color: #D7FB38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7FB38; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7FB38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7FB38'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7FB38'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7FB38'>&nbsp;@&nbsp;</font><font style='background-color: #D7FB38; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7FB38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7FB38; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_LIME_YELLOW = -0x1.2ce5c6p126F;
    static { NAMED.put("more lime yellow", -0x1.2ce5c6p126F); LIST.add(-0x1.2ce5c6p126F); }

    /**
     * This color constant "more yellow lime" has RGBA8888 code {@code C1FE00FF}, L 0.8745098, A 0.43529412, B 0.5921569, alpha 1.0, hue 0.34743994, saturation 0.81790566, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.2edfbep126F}.
     * <pre>
     * <font style='background-color: #C1FE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1FE00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1FE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1FE00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1FE00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1FE00'>&nbsp;@&nbsp;</font><font style='background-color: #C1FE00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1FE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1FE00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_YELLOW_LIME = -0x1.2edfbep126F;
    static { NAMED.put("more yellow lime", -0x1.2edfbep126F); LIST.add(-0x1.2edfbep126F); }

    /**
     * This color constant "some yellow lime" has RGBA8888 code {@code ACF829FF}, L 0.8392157, A 0.42745098, B 0.5882353, alpha 1.0, hue 0.35953215, saturation 0.76748264, and chroma 0.22757049.
     * It can be represented as a packed float with the constant {@code -0x1.2cdbacp126F}.
     * <pre>
     * <font style='background-color: #ACF829;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACF829; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACF829;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ACF829'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ACF829'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ACF829'>&nbsp;@&nbsp;</font><font style='background-color: #ACF829; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ACF829;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ACF829; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_YELLOW_LIME = -0x1.2cdbacp126F;
    static { NAMED.put("some yellow lime", -0x1.2cdbacp126F); LIST.add(-0x1.2cdbacp126F); }

    /**
     * This color constant "some green lime" has RGBA8888 code {@code 8CFF00FF}, L 0.8352941, A 0.4117647, B 0.5921569, alpha 1.0, hue 0.37154335, saturation 0.8765592, and chroma 0.25417653.
     * It can be represented as a packed float with the constant {@code -0x1.2ed3aap126F}.
     * <pre>
     * <font style='background-color: #8CFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8CFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8CFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8CFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8CFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8CFF00'>&nbsp;@&nbsp;</font><font style='background-color: #8CFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8CFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8CFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_GREEN_LIME = -0x1.2ed3aap126F;
    static { NAMED.put("some green lime", -0x1.2ed3aap126F); LIST.add(-0x1.2ed3aap126F); }

    /**
     * This color constant "more green lime" has RGBA8888 code {@code 72FC32FF}, L 0.8117647, A 0.40392157, B 0.58431375, alpha 1.0, hue 0.38535738, saturation 0.78670925, and chroma 0.25465634.
     * It can be represented as a packed float with the constant {@code -0x1.2acf9ep126F}.
     * <pre>
     * <font style='background-color: #72FC32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #72FC32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #72FC32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #72FC32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #72FC32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #72FC32'>&nbsp;@&nbsp;</font><font style='background-color: #72FC32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #72FC32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #72FC32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_GREEN_LIME = -0x1.2acf9ep126F;
    static { NAMED.put("more green lime", -0x1.2acf9ep126F); LIST.add(-0x1.2acf9ep126F); }

    /**
     * This color constant "more lime green" has RGBA8888 code {@code 2EFE00FF}, L 0.7882353, A 0.38431373, B 0.5882353, alpha 1.0, hue 0.39628422, saturation 0.91671175, and chroma 0.28985322.
     * It can be represented as a packed float with the constant {@code -0x1.2cc592p126F}.
     * <pre>
     * <font style='background-color: #2EFE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2EFE00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2EFE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2EFE00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2EFE00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2EFE00'>&nbsp;@&nbsp;</font><font style='background-color: #2EFE00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2EFE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2EFE00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_LIME_GREEN = -0x1.2cc592p126F;
    static { NAMED.put("more lime green", -0x1.2cc592p126F); LIST.add(-0x1.2cc592p126F); }

    /**
     * This color constant "some lime green" has RGBA8888 code {@code 26FC45FF}, L 0.78431374, A 0.3882353, B 0.5764706, alpha 1.0, hue 0.4044865, saturation 0.9074911, and chroma 0.26978588.
     * It can be represented as a packed float with the constant {@code -0x1.26c79p126F}.
     * <pre>
     * <font style='background-color: #26FC45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #26FC45; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #26FC45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #26FC45'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #26FC45'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #26FC45'>&nbsp;@&nbsp;</font><font style='background-color: #26FC45; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #26FC45;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #26FC45; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_LIME_GREEN = -0x1.26c79p126F;
    static { NAMED.put("some lime green", -0x1.26c79p126F); LIST.add(-0x1.26c79p126F); }

    /**
     * This color constant "some cyan green" has RGBA8888 code {@code 00FF7EFF}, L 0.8, A 0.39607844, B 0.54901963, alpha 1.0, hue 0.42985708, saturation 1.0034335, and chroma 0.22890759.
     * It can be represented as a packed float with the constant {@code -0x1.18cb98p126F}.
     * <pre>
     * <font style='background-color: #00FF7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF7E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF7E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF7E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF7E'>&nbsp;@&nbsp;</font><font style='background-color: #00FF7E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF7E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_CYAN_GREEN = -0x1.18cb98p126F;
    static { NAMED.put("some cyan green", -0x1.18cb98p126F); LIST.add(-0x1.18cb98p126F); }

    /**
     * This color constant "more cyan green" has RGBA8888 code {@code 28F7B1FF}, L 0.7921569, A 0.4117647, B 0.52156866, alpha 1.0, hue 0.4618454, saturation 0.875829, and chroma 0.1809568.
     * It can be represented as a packed float with the constant {@code -0x1.0ad394p126F}.
     * <pre>
     * <font style='background-color: #28F7B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #28F7B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #28F7B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #28F7B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #28F7B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #28F7B1'>&nbsp;@&nbsp;</font><font style='background-color: #28F7B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #28F7B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #28F7B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_CYAN_GREEN = -0x1.0ad394p126F;
    static { NAMED.put("more cyan green", -0x1.0ad394p126F); LIST.add(-0x1.0ad394p126F); }

    /**
     * This color constant "more green cyan" has RGBA8888 code {@code 00FBD4FF}, L 0.8117647, A 0.41568628, B 0.5019608, alpha 1.0, hue 0.49631038, saturation 0.9342255, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.00d59ep126F}.
     * <pre>
     * <font style='background-color: #00FBD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FBD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FBD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FBD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FBD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FBD4'>&nbsp;@&nbsp;</font><font style='background-color: #00FBD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FBD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FBD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_GREEN_CYAN = -0x1.00d59ep126F;
    static { NAMED.put("more green cyan", -0x1.00d59ep126F); LIST.add(-0x1.00d59ep126F); }

    /**
     * This color constant "some green cyan" has RGBA8888 code {@code 07FFF5FF}, L 0.8392157, A 0.41960785, B 0.48235294, alpha 1.0, hue 0.5343942, saturation 0.9755017, and chroma 0.16396947.
     * It can be represented as a packed float with the constant {@code -0x1.f6d7acp125F}.
     * <pre>
     * <font style='background-color: #07FFF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #07FFF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #07FFF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #07FFF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #07FFF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #07FFF5'>&nbsp;@&nbsp;</font><font style='background-color: #07FFF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #07FFF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #07FFF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_GREEN_CYAN = -0x1.f6d7acp125F;
    static { NAMED.put("some green cyan", -0x1.f6d7acp125F); LIST.add(-0x1.f6d7acp125F); }

    /**
     * This color constant "some blue cyan" has RGBA8888 code {@code 00E7FEFF}, L 0.76862746, A 0.43137255, B 0.46666667, alpha 1.0, hue 0.5719594, saturation 0.92303, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.eedd88p125F}.
     * <pre>
     * <font style='background-color: #00E7FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00E7FE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00E7FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00E7FE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00E7FE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00E7FE'>&nbsp;@&nbsp;</font><font style='background-color: #00E7FE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00E7FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00E7FE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BLUE_CYAN = -0x1.eedd88p125F;
    static { NAMED.put("some blue cyan", -0x1.eedd88p125F); LIST.add(-0x1.eedd88p125F); }

    /**
     * This color constant "more blue cyan" has RGBA8888 code {@code 00C3F8FF}, L 0.6627451, A 0.44313726, B 0.44705883, alpha 1.0, hue 0.6193227, saturation 0.95717114, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.e4e352p125F}.
     * <pre>
     * <font style='background-color: #00C3F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00C3F8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00C3F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00C3F8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00C3F8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00C3F8'>&nbsp;@&nbsp;</font><font style='background-color: #00C3F8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00C3F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00C3F8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BLUE_CYAN = -0x1.e4e352p125F;
    static { NAMED.put("more blue cyan", -0x1.e4e352p125F); LIST.add(-0x1.e4e352p125F); }

    /**
     * This color constant "more cyan blue" has RGBA8888 code {@code 00A6FFFF}, L 0.5882353, A 0.45882353, B 0.42745098, alpha 1.0, hue 0.6678337, saturation 0.8742905, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.daeb2cp125F}.
     * <pre>
     * <font style='background-color: #00A6FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00A6FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00A6FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00A6FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00A6FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00A6FF'>&nbsp;@&nbsp;</font><font style='background-color: #00A6FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00A6FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00A6FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_CYAN_BLUE = -0x1.daeb2cp125F;
    static { NAMED.put("more cyan blue", -0x1.daeb2cp125F); LIST.add(-0x1.daeb2cp125F); }

    /**
     * This color constant "some cyan blue" has RGBA8888 code {@code 0070F2FF}, L 0.4509804, A 0.4745098, B 0.4, alpha 1.0, hue 0.7102806, saturation 0.78243065, and chroma 0.20558903.
     * It can be represented as a packed float with the constant {@code -0x1.ccf2e6p125F}.
     * <pre>
     * <font style='background-color: #0070F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0070F2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0070F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0070F2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0070F2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0070F2'>&nbsp;@&nbsp;</font><font style='background-color: #0070F2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0070F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0070F2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_CYAN_BLUE = -0x1.ccf2e6p125F;
    static { NAMED.put("some cyan blue", -0x1.ccf2e6p125F); LIST.add(-0x1.ccf2e6p125F); }

    /**
     * This color constant "some violet blue" has RGBA8888 code {@code 2300FFFF}, L 0.3254902, A 0.49411765, B 0.34509805, alpha 1.0, hue 0.7439718, saturation 0.96446896, and chroma 0.30881616.
     * It can be represented as a packed float with the constant {@code -0x1.b0fca6p125F}.
     * <pre>
     * <font style='background-color: #2300FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2300FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2300FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2300FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2300FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2300FF'>&nbsp;@&nbsp;</font><font style='background-color: #2300FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2300FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2300FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_VIOLET_BLUE = -0x1.b0fca6p125F;
    static { NAMED.put("some violet blue", -0x1.b0fca6p125F); LIST.add(-0x1.b0fca6p125F); }

    /**
     * This color constant "more violet blue" has RGBA8888 code {@code 3F25F1FF}, L 0.34509805, A 0.50980395, B 0.36078432, alpha 1.0, hue 0.7611862, saturation 0.80155057, and chroma 0.27803063.
     * It can be represented as a packed float with the constant {@code -0x1.b904bp125F}.
     * <pre>
     * <font style='background-color: #3F25F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F25F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F25F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F25F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F25F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F25F1'>&nbsp;@&nbsp;</font><font style='background-color: #3F25F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F25F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F25F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_VIOLET_BLUE = -0x1.b904bp125F;
    static { NAMED.put("more violet blue", -0x1.b904bp125F); LIST.add(-0x1.b904bp125F); }

    /**
     * This color constant "more blue violet" has RGBA8888 code {@code 5F00FFFF}, L 0.36862746, A 0.5372549, B 0.35686275, alpha 1.0, hue 0.7905202, saturation 0.92336404, and chroma 0.2946566.
     * It can be represented as a packed float with the constant {@code -0x1.b712bcp125F}.
     * <pre>
     * <font style='background-color: #5F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #5F00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BLUE_VIOLET = -0x1.b712bcp125F;
    static { NAMED.put("more blue violet", -0x1.b712bcp125F); LIST.add(-0x1.b712bcp125F); }

    /**
     * This color constant "some blue violet" has RGBA8888 code {@code 7824F7FF}, L 0.4, A 0.5529412, B 0.37254903, alpha 1.0, hue 0.81264865, saturation 0.82480747, and chroma 0.27494007.
     * It can be represented as a packed float with the constant {@code -0x1.bf1accp125F}.
     * <pre>
     * <font style='background-color: #7824F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7824F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7824F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7824F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7824F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7824F7'>&nbsp;@&nbsp;</font><font style='background-color: #7824F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7824F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7824F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BLUE_VIOLET = -0x1.bf1accp125F;
    static { NAMED.put("some blue violet", -0x1.bf1accp125F); LIST.add(-0x1.bf1accp125F); }

    /**
     * This color constant "some purple violet" has RGBA8888 code {@code 9500FFFF}, L 0.42352942, A 0.5803922, B 0.37254903, alpha 1.0, hue 0.83957285, saturation 0.95841616, and chroma 0.30019727.
     * It can be represented as a packed float with the constant {@code -0x1.bf28d8p125F}.
     * <pre>
     * <font style='background-color: #9500FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9500FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9500FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9500FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9500FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9500FF'>&nbsp;@&nbsp;</font><font style='background-color: #9500FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9500FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9500FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_PURPLE_VIOLET = -0x1.bf28d8p125F;
    static { NAMED.put("some purple violet", -0x1.bf28d8p125F); LIST.add(-0x1.bf28d8p125F); }

    /**
     * This color constant "more purple violet" has RGBA8888 code {@code 9625FAFF}, L 0.43529412, A 0.5764706, B 0.38039216, alpha 1.0, hue 0.84054583, saturation 0.82939756, and chroma 0.28281897.
     * It can be represented as a packed float with the constant {@code -0x1.c326dep125F}.
     * <pre>
     * <font style='background-color: #9625FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9625FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9625FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9625FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9625FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9625FA'>&nbsp;@&nbsp;</font><font style='background-color: #9625FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9625FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9625FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_PURPLE_VIOLET = -0x1.c326dep125F;
    static { NAMED.put("more purple violet", -0x1.c326dep125F); LIST.add(-0x1.c326dep125F); }

    /**
     * This color constant "more violet purple" has RGBA8888 code {@code AD00FFFF}, L 0.45490196, A 0.59607846, B 0.38431373, alpha 1.0, hue 0.86031544, saturation 0.9306594, and chroma 0.2995867.
     * It can be represented as a packed float with the constant {@code -0x1.c530e8p125F}.
     * <pre>
     * <font style='background-color: #AD00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD00FF'>&nbsp;@&nbsp;</font><font style='background-color: #AD00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_VIOLET_PURPLE = -0x1.c530e8p125F;
    static { NAMED.put("more violet purple", -0x1.c530e8p125F); LIST.add(-0x1.c530e8p125F); }

    /**
     * This color constant "some violet purple" has RGBA8888 code {@code B32AFFFF}, L 0.47843137, A 0.5921569, B 0.3882353, alpha 1.0, hue 0.8597539, saturation 0.86357343, and chroma 0.28858703.
     * It can be represented as a packed float with the constant {@code -0x1.c72ef4p125F}.
     * <pre>
     * <font style='background-color: #B32AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B32AFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B32AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B32AFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B32AFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B32AFF'>&nbsp;@&nbsp;</font><font style='background-color: #B32AFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B32AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B32AFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_VIOLET_PURPLE = -0x1.c72ef4p125F;
    static { NAMED.put("some violet purple", -0x1.c72ef4p125F); LIST.add(-0x1.c72ef4p125F); }

    /**
     * This color constant "some magenta purple" has RGBA8888 code {@code CA00FFFF}, L 0.49411765, A 0.6117647, B 0.39215687, alpha 1.0, hue 0.8778395, saturation 0.94455945, and chroma 0.30940855.
     * It can be represented as a packed float with the constant {@code -0x1.c938fcp125F}.
     * <pre>
     * <font style='background-color: #CA00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA00FF'>&nbsp;@&nbsp;</font><font style='background-color: #CA00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_MAGENTA_PURPLE = -0x1.c938fcp125F;
    static { NAMED.put("some magenta purple", -0x1.c938fcp125F); LIST.add(-0x1.c938fcp125F); }

    /**
     * This color constant "more magenta purple" has RGBA8888 code {@code C72BF8FF}, L 0.5019608, A 0.6039216, B 0.4, alpha 1.0, hue 0.8780579, saturation 0.81448305, and chroma 0.28731525.
     * It can be represented as a packed float with the constant {@code -0x1.cd35p125F}.
     * <pre>
     * <font style='background-color: #C72BF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C72BF8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C72BF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C72BF8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C72BF8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C72BF8'>&nbsp;@&nbsp;</font><font style='background-color: #C72BF8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C72BF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C72BF8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_MAGENTA_PURPLE = -0x1.cd35p125F;
    static { NAMED.put("more magenta purple", -0x1.cd35p125F); LIST.add(-0x1.cd35p125F); }

    /**
     * This color constant "more purple magenta" has RGBA8888 code {@code EB03FFFF}, L 0.5411765, A 0.627451, B 0.40392157, alpha 1.0, hue 0.89717996, saturation 0.95033884, and chroma 0.3179697.
     * It can be represented as a packed float with the constant {@code -0x1.cf4114p125F}.
     * <pre>
     * <font style='background-color: #EB03FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB03FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB03FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EB03FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EB03FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EB03FF'>&nbsp;@&nbsp;</font><font style='background-color: #EB03FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB03FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB03FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_PURPLE_MAGENTA = -0x1.cf4114p125F;
    static { NAMED.put("more purple magenta", -0x1.cf4114p125F); LIST.add(-0x1.cf4114p125F); }

    /**
     * This color constant "some purple magenta" has RGBA8888 code {@code EF33FFFF}, L 0.5686275, A 0.61960787, B 0.4117647, alpha 1.0, hue 0.89883053, saturation 0.80473375, and chroma 0.29610303.
     * It can be represented as a packed float with the constant {@code -0x1.d33d22p125F}.
     * <pre>
     * <font style='background-color: #EF33FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF33FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF33FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF33FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF33FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF33FF'>&nbsp;@&nbsp;</font><font style='background-color: #EF33FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF33FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF33FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_PURPLE_MAGENTA = -0x1.d33d22p125F;
    static { NAMED.put("some purple magenta", -0x1.d33d22p125F); LIST.add(-0x1.d33d22p125F); }

    /**
     * This color constant "some red magenta" has RGBA8888 code {@code FE00E2FF}, L 0.5529412, A 0.63529414, B 0.43137255, alpha 1.0, hue 0.9252889, saturation 0.90120065, and chroma 0.30222362.
     * It can be represented as a packed float with the constant {@code -0x1.dd451ap125F}.
     * <pre>
     * <font style='background-color: #FE00E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE00E2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE00E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FE00E2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FE00E2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FE00E2'>&nbsp;@&nbsp;</font><font style='background-color: #FE00E2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE00E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE00E2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_RED_MAGENTA = -0x1.dd451ap125F;
    static { NAMED.put("some red magenta", -0x1.dd451ap125F); LIST.add(-0x1.dd451ap125F); }

    /**
     * This color constant "more red magenta" has RGBA8888 code {@code F02D9CFF}, L 0.5137255, A 0.61960787, B 0.4745098, alpha 1.0, hue 0.96657723, saturation 0.74007136, and chroma 0.24363229.
     * It can be represented as a packed float with the constant {@code -0x1.f33d06p125F}.
     * <pre>
     * <font style='background-color: #F02D9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F02D9C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F02D9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F02D9C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F02D9C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F02D9C'>&nbsp;@&nbsp;</font><font style='background-color: #F02D9C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F02D9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F02D9C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_RED_MAGENTA = -0x1.f33d06p125F;
    static { NAMED.put("more red magenta", -0x1.f33d06p125F); LIST.add(-0x1.f33d06p125F); }

    /**
     * This color constant "more magenta red" has RGBA8888 code {@code FF0070FF}, L 0.5058824, A 0.627451, B 0.50980395, alpha 1.0, hue 0.01221767, saturation 0.90574884, and chroma 0.25465634.
     * It can be represented as a packed float with the constant {@code -0x1.054102p126F}.
     * <pre>
     * <font style='background-color: #FF0070;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0070; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0070;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0070'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0070'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0070'>&nbsp;@&nbsp;</font><font style='background-color: #FF0070; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0070;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0070; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_MAGENTA_RED = -0x1.054102p126F;
    static { NAMED.put("more magenta red", -0x1.054102p126F); LIST.add(-0x1.054102p126F); }

    /**
     * This color constant "some magenta red" has RGBA8888 code {@code F62941FF}, L 0.49411765, A 0.60784316, B 0.5411765, alpha 1.0, hue 0.05803694, saturation 0.7607133, and chroma 0.22997166.
     * It can be represented as a packed float with the constant {@code -0x1.1536fcp126F}.
     * <pre>
     * <font style='background-color: #F62941;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F62941; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F62941;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F62941'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F62941'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F62941'>&nbsp;@&nbsp;</font><font style='background-color: #F62941; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F62941;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F62941; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_MAGENTA_RED = -0x1.1536fcp126F;
    static { NAMED.put("some magenta red", -0x1.1536fcp126F); LIST.add(-0x1.1536fcp126F); }

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
