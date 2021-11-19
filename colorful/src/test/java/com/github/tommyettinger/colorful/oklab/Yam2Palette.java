package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;

import java.util.Comparator;

/**
 * A palette of predefined colors as packed Oklab floats, the kind {@link ColorTools} works with. This uses a geometric
 * palette, Yam2, that is designed to be as consistent as possible in its support for hues while keeping lots of
 * grayscale, desaturated, and mid-saturation colors, and to have a coherent naming system. This is the successor to
 * Yam (1), and may still be adjusted as needed (this may or may not be in another version like Yam3).
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
     * This color constant "darker gray red" has RGBA8888 code {@code 3F1613FF}, L 0.1764706, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.3728899, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.09105ap126F}.
     * <pre>
     * <font style='background-color: #3F1613;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F1613; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F1613;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F1613'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F1613'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F1613'>&nbsp;@&nbsp;</font><font style='background-color: #3F1613; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F1613;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F1613; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_RED = -0x1.09105ap126F;
    static { NAMED.put("darker gray red", -0x1.09105ap126F); LIST.add(-0x1.09105ap126F); }

    /**
     * This color constant "dark gray red" has RGBA8888 code {@code 804A43FF}, L 0.36078432, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.12456864, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.0910b8p126F}.
     * <pre>
     * <font style='background-color: #804A43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #804A43; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #804A43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #804A43'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #804A43'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #804A43'>&nbsp;@&nbsp;</font><font style='background-color: #804A43; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #804A43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #804A43; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_RED = -0x1.0910b8p126F;
    static { NAMED.put("dark gray red", -0x1.0910b8p126F); LIST.add(-0x1.0910b8p126F); }

    /**
     * This color constant "light gray red" has RGBA8888 code {@code BD7E75FF}, L 0.54901963, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, saturation 0.11190926, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.091118p126F}.
     * <pre>
     * <font style='background-color: #BD7E75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD7E75; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD7E75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD7E75'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD7E75'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD7E75'>&nbsp;@&nbsp;</font><font style='background-color: #BD7E75; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD7E75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD7E75; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_RED = -0x1.091118p126F;
    static { NAMED.put("light gray red", -0x1.091118p126F); LIST.add(-0x1.091118p126F); }

    /**
     * This color constant "lighter gray red" has RGBA8888 code {@code F3ADA4FF}, L 0.73333335, A 0.53333336, B 0.5137255, alpha 1.0, hue 0.062156405, saturation 0.41612804, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.071176p126F}.
     * <pre>
     * <font style='background-color: #F3ADA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3ADA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3ADA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3ADA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3ADA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3ADA4'>&nbsp;@&nbsp;</font><font style='background-color: #F3ADA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3ADA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3ADA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_RED = -0x1.071176p126F;
    static { NAMED.put("lighter gray red", -0x1.071176p126F); LIST.add(-0x1.071176p126F); }

    /**
     * This color constant "darker gray brown" has RGBA8888 code {@code 38241CFF}, L 0.19215687, A 0.5137255, B 0.5137255, alpha 1.0, hue 0.125, saturation 0.17745586, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.070662p126F}.
     * <pre>
     * <font style='background-color: #38241C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38241C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38241C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #38241C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #38241C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #38241C'>&nbsp;@&nbsp;</font><font style='background-color: #38241C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38241C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38241C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_BROWN = -0x1.070662p126F;
    static { NAMED.put("darker gray brown", -0x1.070662p126F); LIST.add(-0x1.070662p126F); }

    /**
     * This color constant "dark gray brown" has RGBA8888 code {@code 745A4FFF}, L 0.38431373, A 0.5137255, B 0.5137255, alpha 1.0, hue 0.125, saturation 0.059746988, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.0706c4p126F}.
     * <pre>
     * <font style='background-color: #745A4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #745A4F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #745A4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #745A4F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #745A4F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #745A4F'>&nbsp;@&nbsp;</font><font style='background-color: #745A4F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #745A4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #745A4F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_BROWN = -0x1.0706c4p126F;
    static { NAMED.put("dark gray brown", -0x1.0706c4p126F); LIST.add(-0x1.0706c4p126F); }

    /**
     * This color constant "light gray brown" has RGBA8888 code {@code AE9083FF}, L 0.5764706, A 0.50980395, B 0.5137255, alpha 1.0, hue 0.15127131, saturation 0.032797784, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.070526p126F}.
     * <pre>
     * <font style='background-color: #AE9083;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE9083; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE9083;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE9083'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE9083'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE9083'>&nbsp;@&nbsp;</font><font style='background-color: #AE9083; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE9083;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE9083; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_BROWN = -0x1.070526p126F;
    static { NAMED.put("light gray brown", -0x1.070526p126F); LIST.add(-0x1.070526p126F); }

    /**
     * This color constant "lighter gray brown" has RGBA8888 code {@code E3C2B3FF}, L 0.76862746, A 0.50980395, B 0.5137255, alpha 1.0, hue 0.15127131, saturation 0.09785124, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.070588p126F}.
     * <pre>
     * <font style='background-color: #E3C2B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C2B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C2B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3C2B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3C2B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3C2B3'>&nbsp;@&nbsp;</font><font style='background-color: #E3C2B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C2B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C2B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_BROWN = -0x1.070588p126F;
    static { NAMED.put("lighter gray brown", -0x1.070588p126F); LIST.add(-0x1.070588p126F); }

    /**
     * This color constant "darker gray orange" has RGBA8888 code {@code 3F220FFF}, L 0.19607843, A 0.5176471, B 0.52156866, alpha 1.0, hue 0.14085212, saturation 0.39901236, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.0b0864p126F}.
     * <pre>
     * <font style='background-color: #3F220F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F220F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F220F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F220F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F220F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F220F'>&nbsp;@&nbsp;</font><font style='background-color: #3F220F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F220F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F220F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_ORANGE = -0x1.0b0864p126F;
    static { NAMED.put("darker gray orange", -0x1.0b0864p126F); LIST.add(-0x1.0b0864p126F); }

    /**
     * This color constant "dark gray orange" has RGBA8888 code {@code 815B43FF}, L 0.39607844, A 0.5176471, B 0.5254902, alpha 1.0, hue 0.15361187, saturation 0.18765247, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.0d08cap126F}.
     * <pre>
     * <font style='background-color: #815B43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #815B43; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #815B43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #815B43'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #815B43'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #815B43'>&nbsp;@&nbsp;</font><font style='background-color: #815B43; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #815B43;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #815B43; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_ORANGE = -0x1.0d08cap126F;
    static { NAMED.put("dark gray orange", -0x1.0d08cap126F); LIST.add(-0x1.0d08cap126F); }

    /**
     * This color constant "light gray orange" has RGBA8888 code {@code BE9377FF}, L 0.59607846, A 0.5137255, B 0.5254902, alpha 1.0, hue 0.17138404, saturation 0.10530129, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.0d073p126F}.
     * <pre>
     * <font style='background-color: #BE9377;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE9377; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE9377;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE9377'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE9377'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE9377'>&nbsp;@&nbsp;</font><font style='background-color: #BE9377; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE9377;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE9377; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_ORANGE = -0x1.0d073p126F;
    static { NAMED.put("light gray orange", -0x1.0d073p126F); LIST.add(-0x1.0d073p126F); }

    /**
     * This color constant "lighter gray orange" has RGBA8888 code {@code F5C5A7FF}, L 0.79607844, A 0.5176471, B 0.52156866, alpha 1.0, hue 0.14085212, saturation 0.36577636, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.0b0996p126F}.
     * <pre>
     * <font style='background-color: #F5C5A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5C5A7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5C5A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5C5A7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5C5A7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5C5A7'>&nbsp;@&nbsp;</font><font style='background-color: #F5C5A7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5C5A7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5C5A7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_ORANGE = -0x1.0b0996p126F;
    static { NAMED.put("lighter gray orange", -0x1.0b0996p126F); LIST.add(-0x1.0b0996p126F); }

    /**
     * This color constant "darker gray saffron" has RGBA8888 code {@code 3D2D14FF}, L 0.21568628, A 0.5019608, B 0.5254902, alpha 1.0, hue 0.23778233, saturation 0.4470743, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0d006ep126F}.
     * <pre>
     * <font style='background-color: #3D2D14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D2D14; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D2D14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3D2D14'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3D2D14'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3D2D14'>&nbsp;@&nbsp;</font><font style='background-color: #3D2D14; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D2D14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D2D14; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_SAFFRON = -0x1.0d006ep126F;
    static { NAMED.put("darker gray saffron", -0x1.0d006ep126F); LIST.add(-0x1.0d006ep126F); }

    /**
     * This color constant "dark gray saffron" has RGBA8888 code {@code 7D684AFF}, L 0.41960785, A 0.5019608, B 0.5254902, alpha 1.0, hue 0.23778233, saturation 0.16094674, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0d00d6p126F}.
     * <pre>
     * <font style='background-color: #7D684A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D684A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D684A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7D684A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7D684A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7D684A'>&nbsp;@&nbsp;</font><font style='background-color: #7D684A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D684A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D684A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_SAFFRON = -0x1.0d00d6p126F;
    static { NAMED.put("dark gray saffron", -0x1.0d00d6p126F); LIST.add(-0x1.0d00d6p126F); }

    /**
     * This color constant "light gray saffron" has RGBA8888 code {@code B79F7DFF}, L 0.61960787, A 0.5019608, B 0.5254902, alpha 1.0, hue 0.23778233, saturation 0.098708086, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0d013cp126F}.
     * <pre>
     * <font style='background-color: #B79F7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B79F7D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B79F7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B79F7D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B79F7D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B79F7D'>&nbsp;@&nbsp;</font><font style='background-color: #B79F7D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B79F7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B79F7D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_SAFFRON = -0x1.0d013cp126F;
    static { NAMED.put("light gray saffron", -0x1.0d013cp126F); LIST.add(-0x1.0d013cp126F); }

    /**
     * This color constant "lighter gray saffron" has RGBA8888 code {@code EDD3ADFF}, L 0.8235294, A 0.5019608, B 0.5254902, alpha 1.0, hue 0.23778233, saturation 0.12760368, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0d01a4p126F}.
     * <pre>
     * <font style='background-color: #EDD3AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDD3AD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDD3AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDD3AD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDD3AD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDD3AD'>&nbsp;@&nbsp;</font><font style='background-color: #EDD3AD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDD3AD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDD3AD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_SAFFRON = -0x1.0d01a4p126F;
    static { NAMED.put("lighter gray saffron", -0x1.0d01a4p126F); LIST.add(-0x1.0d01a4p126F); }

    /**
     * This color constant "darker gray yellow" has RGBA8888 code {@code 36390DFF}, L 0.23529412, A 0.48235294, B 0.53333336, alpha 1.0, hue 0.3274942, saturation 0.61640984, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.10f678p126F}.
     * <pre>
     * <font style='background-color: #36390D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #36390D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #36390D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #36390D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #36390D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #36390D'>&nbsp;@&nbsp;</font><font style='background-color: #36390D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #36390D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #36390D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_YELLOW = -0x1.10f678p126F;
    static { NAMED.put("darker gray yellow", -0x1.10f678p126F); LIST.add(-0x1.10f678p126F); }

    /**
     * This color constant "dark gray yellow" has RGBA8888 code {@code 757947FF}, L 0.4509804, A 0.48235294, B 0.53333336, alpha 1.0, hue 0.3274942, saturation 0.24962051, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.10f6e6p126F}.
     * <pre>
     * <font style='background-color: #757947;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #757947; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #757947;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #757947'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #757947'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #757947'>&nbsp;@&nbsp;</font><font style='background-color: #757947; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #757947;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #757947; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_YELLOW = -0x1.10f6e6p126F;
    static { NAMED.put("dark gray yellow", -0x1.10f6e6p126F); LIST.add(-0x1.10f6e6p126F); }

    /**
     * This color constant "light gray yellow" has RGBA8888 code {@code AFB47CFF}, L 0.6627451, A 0.4862745, B 0.53333336, alpha 1.0, hue 0.31215638, saturation 0.1498061, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.10f952p126F}.
     * <pre>
     * <font style='background-color: #AFB47C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFB47C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFB47C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFB47C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFB47C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFB47C'>&nbsp;@&nbsp;</font><font style='background-color: #AFB47C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFB47C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFB47C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_YELLOW = -0x1.10f952p126F;
    static { NAMED.put("light gray yellow", -0x1.10f952p126F); LIST.add(-0x1.10f952p126F); }

    /**
     * This color constant "lighter gray yellow" has RGBA8888 code {@code E4EAADFF}, L 0.8784314, A 0.48235294, B 0.53333336, alpha 1.0, hue 0.3274942, saturation 0.10811601, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.10f7cp126F}.
     * <pre>
     * <font style='background-color: #E4EAAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4EAAD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4EAAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E4EAAD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E4EAAD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E4EAAD'>&nbsp;@&nbsp;</font><font style='background-color: #E4EAAD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4EAAD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4EAAD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_YELLOW = -0x1.10f7cp126F;
    static { NAMED.put("lighter gray yellow", -0x1.10f7cp126F); LIST.add(-0x1.10f7cp126F); }

    /**
     * This color constant "darker gray lime" has RGBA8888 code {@code 253A18FF}, L 0.22745098, A 0.4745098, B 0.5254902, alpha 1.0, hue 0.375, saturation 0.36334318, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0cf274p126F}.
     * <pre>
     * <font style='background-color: #253A18;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #253A18; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #253A18;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #253A18'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #253A18'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #253A18'>&nbsp;@&nbsp;</font><font style='background-color: #253A18; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #253A18;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #253A18; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_LIME = -0x1.0cf274p126F;
    static { NAMED.put("darker gray lime", -0x1.0cf274p126F); LIST.add(-0x1.0cf274p126F); }

    /**
     * This color constant "dark gray lime" has RGBA8888 code {@code 5F7B50FF}, L 0.4392157, A 0.47058824, B 0.5254902, alpha 1.0, hue 0.3863407, saturation 0.14855312, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.0cf0ep126F}.
     * <pre>
     * <font style='background-color: #5F7B50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F7B50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F7B50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5F7B50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5F7B50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5F7B50'>&nbsp;@&nbsp;</font><font style='background-color: #5F7B50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F7B50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F7B50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_LIME = -0x1.0cf0ep126F;
    static { NAMED.put("dark gray lime", -0x1.0cf0ep126F); LIST.add(-0x1.0cf0ep126F); }

    /**
     * This color constant "light gray lime" has RGBA8888 code {@code 97B786FF}, L 0.6509804, A 0.47058824, B 0.5254902, alpha 1.0, hue 0.3863407, saturation 0.089094914, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.0cf14cp126F}.
     * <pre>
     * <font style='background-color: #97B786;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #97B786; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #97B786;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #97B786'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #97B786'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #97B786'>&nbsp;@&nbsp;</font><font style='background-color: #97B786; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #97B786;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #97B786; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_LIME = -0x1.0cf14cp126F;
    static { NAMED.put("light gray lime", -0x1.0cf14cp126F); LIST.add(-0x1.0cf14cp126F); }

    /**
     * This color constant "lighter gray lime" has RGBA8888 code {@code C9ECB7FF}, L 0.85882354, A 0.47058824, B 0.5254902, alpha 1.0, hue 0.3863407, saturation 0.13264877, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.0cf1b6p126F}.
     * <pre>
     * <font style='background-color: #C9ECB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9ECB7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9ECB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9ECB7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9ECB7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9ECB7'>&nbsp;@&nbsp;</font><font style='background-color: #C9ECB7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9ECB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9ECB7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_LIME = -0x1.0cf1b6p126F;
    static { NAMED.put("lighter gray lime", -0x1.0cf1b6p126F); LIST.add(-0x1.0cf1b6p126F); }

    /**
     * This color constant "darker gray green" has RGBA8888 code {@code 0A3A1AFF}, L 0.21568628, A 0.45882353, B 0.52156866, alpha 1.0, hue 0.42320445, saturation 0.93627656, and chroma 0.09260367.
     * It can be represented as a packed float with the constant {@code -0x1.0aea6ep126F}.
     * <pre>
     * <font style='background-color: #0A3A1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A3A1A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A3A1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0A3A1A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0A3A1A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0A3A1A'>&nbsp;@&nbsp;</font><font style='background-color: #0A3A1A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A3A1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A3A1A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_GREEN = -0x1.0aea6ep126F;
    static { NAMED.put("darker gray green", -0x1.0aea6ep126F); LIST.add(-0x1.0aea6ep126F); }

    /**
     * This color constant "dark gray green" has RGBA8888 code {@code 3E7149FF}, L 0.39215687, A 0.45882353, B 0.52156866, alpha 1.0, hue 0.42320445, saturation 0.39964443, and chroma 0.09260367.
     * It can be represented as a packed float with the constant {@code -0x1.0aeac8p126F}.
     * <pre>
     * <font style='background-color: #3E7149;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E7149; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E7149;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3E7149'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3E7149'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3E7149'>&nbsp;@&nbsp;</font><font style='background-color: #3E7149; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E7149;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E7149; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_GREEN = -0x1.0aeac8p126F;
    static { NAMED.put("dark gray green", -0x1.0aeac8p126F); LIST.add(-0x1.0aeac8p126F); }

    /**
     * This color constant "light gray green" has RGBA8888 code {@code 6DA578FF}, L 0.5686275, A 0.45882353, B 0.5176471, alpha 1.0, hue 0.4355687, saturation 0.25214347, and chroma 0.08924734.
     * It can be represented as a packed float with the constant {@code -0x1.08eb22p126F}.
     * <pre>
     * <font style='background-color: #6DA578;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6DA578; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6DA578;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6DA578'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6DA578'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6DA578'>&nbsp;@&nbsp;</font><font style='background-color: #6DA578; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6DA578;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6DA578; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_GREEN = -0x1.08eb22p126F;
    static { NAMED.put("light gray green", -0x1.08eb22p126F); LIST.add(-0x1.08eb22p126F); }

    /**
     * This color constant "lighter gray green" has RGBA8888 code {@code 99D6A4FF}, L 0.74509805, A 0.45882353, B 0.52156866, alpha 1.0, hue 0.42320445, saturation 0.15874586, and chroma 0.09260367.
     * It can be represented as a packed float with the constant {@code -0x1.0aeb7cp126F}.
     * <pre>
     * <font style='background-color: #99D6A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99D6A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99D6A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #99D6A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #99D6A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #99D6A4'>&nbsp;@&nbsp;</font><font style='background-color: #99D6A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99D6A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99D6A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_GREEN = -0x1.0aeb7cp126F;
    static { NAMED.put("lighter gray green", -0x1.0aeb7cp126F); LIST.add(-0x1.0aeb7cp126F); }

    /**
     * This color constant "darker gray cyan" has RGBA8888 code {@code 0E3C3FFF}, L 0.23137255, A 0.47058824, B 0.4862745, alpha 1.0, hue 0.569486, saturation 0.8005844, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.f8f076p125F}.
     * <pre>
     * <font style='background-color: #0E3C3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0E3C3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0E3C3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0E3C3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0E3C3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0E3C3F'>&nbsp;@&nbsp;</font><font style='background-color: #0E3C3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0E3C3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0E3C3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_CYAN = -0x1.f8f076p125F;
    static { NAMED.put("darker gray cyan", -0x1.f8f076p125F); LIST.add(-0x1.f8f076p125F); }

    /**
     * This color constant "dark gray cyan" has RGBA8888 code {@code 4A7B7EFF}, L 0.4392157, A 0.47058824, B 0.49019608, alpha 1.0, hue 0.5511957, saturation 0.307787, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.faf0ep125F}.
     * <pre>
     * <font style='background-color: #4A7B7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A7B7E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A7B7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A7B7E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A7B7E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A7B7E'>&nbsp;@&nbsp;</font><font style='background-color: #4A7B7E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A7B7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A7B7E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_CYAN = -0x1.faf0ep125F;
    static { NAMED.put("dark gray cyan", -0x1.faf0ep125F); LIST.add(-0x1.faf0ep125F); }

    /**
     * This color constant "light gray cyan" has RGBA8888 code {@code 80B7BBFF}, L 0.6509804, A 0.47058824, B 0.4862745, alpha 1.0, hue 0.569486, saturation 0.20566711, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.f8f14cp125F}.
     * <pre>
     * <font style='background-color: #80B7BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80B7BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80B7BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #80B7BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #80B7BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #80B7BB'>&nbsp;@&nbsp;</font><font style='background-color: #80B7BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80B7BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80B7BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_CYAN = -0x1.f8f14cp125F;
    static { NAMED.put("light gray cyan", -0x1.f8f14cp125F); LIST.add(-0x1.f8f14cp125F); }

    /**
     * This color constant "lighter gray cyan" has RGBA8888 code {@code B1ECF0FF}, L 0.85882354, A 0.47058824, B 0.4862745, alpha 1.0, hue 0.569486, saturation 0.33733457, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.f8f1b6p125F}.
     * <pre>
     * <font style='background-color: #B1ECF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1ECF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1ECF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1ECF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1ECF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1ECF0'>&nbsp;@&nbsp;</font><font style='background-color: #B1ECF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1ECF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1ECF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_CYAN = -0x1.f8f1b6p125F;
    static { NAMED.put("lighter gray cyan", -0x1.f8f1b6p125F); LIST.add(-0x1.f8f1b6p125F); }

    /**
     * This color constant "darker gray blue" has RGBA8888 code {@code 091945FF}, L 0.14901961, A 0.49411765, B 0.44705883, alpha 1.0, hue 0.7323789, saturation 0.37268022, and chroma 0.10611779.
     * It can be represented as a packed float with the constant {@code -0x1.e4fc4cp125F}.
     * <pre>
     * <font style='background-color: #091945;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #091945; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #091945;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #091945'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #091945'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #091945'>&nbsp;@&nbsp;</font><font style='background-color: #091945; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #091945;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #091945; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_BLUE = -0x1.e4fc4cp125F;
    static { NAMED.put("darker gray blue", -0x1.e4fc4cp125F); LIST.add(-0x1.e4fc4cp125F); }

    /**
     * This color constant "dark gray blue" has RGBA8888 code {@code 354E86FF}, L 0.3254902, A 0.49411765, B 0.44705883, alpha 1.0, hue 0.7323789, saturation 0.10842975, and chroma 0.10611779.
     * It can be represented as a packed float with the constant {@code -0x1.e4fca6p125F}.
     * <pre>
     * <font style='background-color: #354E86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #354E86; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #354E86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #354E86'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #354E86'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #354E86'>&nbsp;@&nbsp;</font><font style='background-color: #354E86; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #354E86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #354E86; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_BLUE = -0x1.e4fca6p125F;
    static { NAMED.put("dark gray blue", -0x1.e4fca6p125F); LIST.add(-0x1.e4fca6p125F); }

    /**
     * This color constant "light gray blue" has RGBA8888 code {@code 6382C2FF}, L 0.5019608, A 0.49019608, B 0.44705883, alpha 1.0, hue 0.72084755, saturation 0.26342914, and chroma 0.107261956.
     * It can be represented as a packed float with the constant {@code -0x1.e4fbp125F}.
     * <pre>
     * <font style='background-color: #6382C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6382C2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6382C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6382C2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6382C2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6382C2'>&nbsp;@&nbsp;</font><font style='background-color: #6382C2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6382C2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6382C2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_BLUE = -0x1.e4fbp125F;
    static { NAMED.put("light gray blue", -0x1.e4fbp125F); LIST.add(-0x1.e4fbp125F); }

    /**
     * This color constant "lighter gray blue" has RGBA8888 code {@code 90B3F9FF}, L 0.6784314, A 0.49019608, B 0.4509804, alpha 1.0, hue 0.71857655, saturation 0.65507686, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e6fb5ap125F}.
     * <pre>
     * <font style='background-color: #90B3F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90B3F9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90B3F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #90B3F9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #90B3F9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #90B3F9'>&nbsp;@&nbsp;</font><font style='background-color: #90B3F9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90B3F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90B3F9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_BLUE = -0x1.e6fb5ap125F;
    static { NAMED.put("lighter gray blue", -0x1.e6fb5ap125F); LIST.add(-0x1.e6fb5ap125F); }

    /**
     * This color constant "darker gray violet" has RGBA8888 code {@code 261A41FF}, L 0.16862746, A 0.5137255, B 0.45882353, alpha 1.0, hue 0.8011957, saturation 0.29873496, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.eb0656p125F}.
     * <pre>
     * <font style='background-color: #261A41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #261A41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #261A41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #261A41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #261A41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #261A41'>&nbsp;@&nbsp;</font><font style='background-color: #261A41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #261A41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #261A41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_VIOLET = -0x1.eb0656p125F;
    static { NAMED.put("darker gray violet", -0x1.eb0656p125F); LIST.add(-0x1.eb0656p125F); }

    /**
     * This color constant "dark gray violet" has RGBA8888 code {@code 5B4E81FF}, L 0.3529412, A 0.5176471, B 0.45882353, alpha 1.0, hue 0.8144313, saturation 0.10210768, and chroma 0.08924734.
     * It can be represented as a packed float with the constant {@code -0x1.eb08b4p125F}.
     * <pre>
     * <font style='background-color: #5B4E81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B4E81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B4E81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B4E81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B4E81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B4E81'>&nbsp;@&nbsp;</font><font style='background-color: #5B4E81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B4E81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B4E81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_VIOLET = -0x1.eb08b4p125F;
    static { NAMED.put("dark gray violet", -0x1.eb08b4p125F); LIST.add(-0x1.eb08b4p125F); }

    /**
     * This color constant "light gray violet" has RGBA8888 code {@code 8F81BCFF}, L 0.53333336, A 0.5176471, B 0.45882353, alpha 1.0, hue 0.8144313, saturation 0.16946676, and chroma 0.08924734.
     * It can be represented as a packed float with the constant {@code -0x1.eb091p125F}.
     * <pre>
     * <font style='background-color: #8F81BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F81BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F81BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F81BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F81BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F81BC'>&nbsp;@&nbsp;</font><font style='background-color: #8F81BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F81BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F81BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_VIOLET = -0x1.eb091p125F;
    static { NAMED.put("light gray violet", -0x1.eb091p125F); LIST.add(-0x1.eb091p125F); }

    /**
     * This color constant "lighter gray violet" has RGBA8888 code {@code C1B2F2FF}, L 0.7176471, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.47514108, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed096ep125F}.
     * <pre>
     * <font style='background-color: #C1B2F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1B2F2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1B2F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1B2F2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1B2F2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1B2F2'>&nbsp;@&nbsp;</font><font style='background-color: #C1B2F2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1B2F2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1B2F2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_VIOLET = -0x1.ed096ep125F;
    static { NAMED.put("lighter gray violet", -0x1.ed096ep125F); LIST.add(-0x1.ed096ep125F); }

    /**
     * This color constant "darker gray purple" has RGBA8888 code {@code 341645FF}, L 0.18039216, A 0.53333336, B 0.45882353, alpha 1.0, hue 0.85831934, saturation 0.49249452, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.eb105cp125F}.
     * <pre>
     * <font style='background-color: #341645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #341645; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #341645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #341645'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #341645'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #341645'>&nbsp;@&nbsp;</font><font style='background-color: #341645; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #341645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #341645; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_PURPLE = -0x1.eb105cp125F;
    static { NAMED.put("darker gray purple", -0x1.eb105cp125F); LIST.add(-0x1.eb105cp125F); }

    /**
     * This color constant "dark gray purple" has RGBA8888 code {@code 6F4B86FF}, L 0.36862746, A 0.53333336, B 0.45882353, alpha 1.0, hue 0.85831934, saturation 0.15557568, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.eb10bcp125F}.
     * <pre>
     * <font style='background-color: #6F4B86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F4B86; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F4B86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F4B86'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F4B86'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F4B86'>&nbsp;@&nbsp;</font><font style='background-color: #6F4B86; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F4B86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F4B86; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_PURPLE = -0x1.eb10bcp125F;
    static { NAMED.put("dark gray purple", -0x1.eb10bcp125F); LIST.add(-0x1.eb10bcp125F); }

    /**
     * This color constant "light gray purple" has RGBA8888 code {@code A980C3FF}, L 0.56078434, A 0.53333336, B 0.45882353, alpha 1.0, hue 0.85831934, saturation 0.19300681, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.eb111ep125F}.
     * <pre>
     * <font style='background-color: #A980C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A980C3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A980C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A980C3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A980C3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A980C3'>&nbsp;@&nbsp;</font><font style='background-color: #A980C3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A980C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A980C3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_PURPLE = -0x1.eb111ep125F;
    static { NAMED.put("light gray purple", -0x1.eb111ep125F); LIST.add(-0x1.eb111ep125F); }

    /**
     * This color constant "lighter gray purple" has RGBA8888 code {@code DDB1FAFF}, L 0.7490196, A 0.53333336, B 0.45882353, alpha 1.0, hue 0.85831934, saturation 0.7357017, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.eb117ep125F}.
     * <pre>
     * <font style='background-color: #DDB1FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDB1FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDB1FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDB1FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDB1FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDB1FA'>&nbsp;@&nbsp;</font><font style='background-color: #DDB1FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDB1FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDB1FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_PURPLE = -0x1.eb117ep125F;
    static { NAMED.put("lighter gray purple", -0x1.eb117ep125F); LIST.add(-0x1.eb117ep125F); }

    /**
     * This color constant "darker gray magenta" has RGBA8888 code {@code 3C193EFF}, L 0.19215687, A 0.5372549, B 0.47058824, alpha 1.0, hue 0.8936267, saturation 0.3953449, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.f11262p125F}.
     * <pre>
     * <font style='background-color: #3C193E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C193E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C193E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C193E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C193E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C193E'>&nbsp;@&nbsp;</font><font style='background-color: #3C193E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C193E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C193E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_MAGENTA = -0x1.f11262p125F;
    static { NAMED.put("darker gray magenta", -0x1.f11262p125F); LIST.add(-0x1.f11262p125F); }

    /**
     * This color constant "dark gray magenta" has RGBA8888 code {@code 7B4F7DFF}, L 0.38431373, A 0.53333336, B 0.47058824, alpha 1.0, hue 0.8849269, saturation 0.11281207, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.f110c4p125F}.
     * <pre>
     * <font style='background-color: #7B4F7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B4F7D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B4F7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B4F7D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B4F7D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B4F7D'>&nbsp;@&nbsp;</font><font style='background-color: #7B4F7D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B4F7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B4F7D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_MAGENTA = -0x1.f110c4p125F;
    static { NAMED.put("dark gray magenta", -0x1.f110c4p125F); LIST.add(-0x1.f110c4p125F); }

    /**
     * This color constant "light gray magenta" has RGBA8888 code {@code B884B9FF}, L 0.5803922, A 0.5372549, B 0.47058824, alpha 1.0, hue 0.8936267, saturation 0.10280251, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.f11328p125F}.
     * <pre>
     * <font style='background-color: #B884B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B884B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B884B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B884B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B884B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B884B9'>&nbsp;@&nbsp;</font><font style='background-color: #B884B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B884B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B884B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_MAGENTA = -0x1.f11328p125F;
    static { NAMED.put("light gray magenta", -0x1.f11328p125F); LIST.add(-0x1.f11328p125F); }

    /**
     * This color constant "lighter gray magenta" has RGBA8888 code {@code EDB5EFFF}, L 0.77254903, A 0.53333336, B 0.47058824, alpha 1.0, hue 0.8849269, saturation 0.43184206, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.f1118ap125F}.
     * <pre>
     * <font style='background-color: #EDB5EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDB5EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDB5EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDB5EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDB5EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDB5EF'>&nbsp;@&nbsp;</font><font style='background-color: #EDB5EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDB5EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDB5EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_MAGENTA = -0x1.f1118ap125F;
    static { NAMED.put("lighter gray magenta", -0x1.f1118ap125F); LIST.add(-0x1.f1118ap125F); }

    /**
     * This color constant "drab brown red" has RGBA8888 code {@code 7B231AFF}, L 0.28627452, A 0.5568628, B 0.5294118, alpha 1.0, hue 0.075972304, saturation 0.5149137, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.0f1c92p126F}.
     * <pre>
     * <font style='background-color: #7B231A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B231A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B231A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B231A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B231A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B231A'>&nbsp;@&nbsp;</font><font style='background-color: #7B231A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B231A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B231A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN_RED = -0x1.0f1c92p126F;
    static { NAMED.put("drab brown red", -0x1.0f1c92p126F); LIST.add(-0x1.0f1c92p126F); }

    /**
     * This color constant "dull brown red" has RGBA8888 code {@code B95344FF}, L 0.4509804, A 0.5568628, B 0.53333336, alpha 1.0, hue 0.084393784, saturation 0.28928, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.111ce6p126F}.
     * <pre>
     * <font style='background-color: #B95344;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B95344; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B95344;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B95344'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B95344'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B95344'>&nbsp;@&nbsp;</font><font style='background-color: #B95344; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B95344;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B95344; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BROWN_RED = -0x1.111ce6p126F;
    static { NAMED.put("dull brown red", -0x1.111ce6p126F); LIST.add(-0x1.111ce6p126F); }

    /**
     * This color constant "pale brown red" has RGBA8888 code {@code EF7E6DFF}, L 0.6117647, A 0.5568628, B 0.5294118, alpha 1.0, hue 0.075972304, saturation 0.5149137, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.0f1d38p126F}.
     * <pre>
     * <font style='background-color: #EF7E6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF7E6D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF7E6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF7E6D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF7E6D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF7E6D'>&nbsp;@&nbsp;</font><font style='background-color: #EF7E6D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF7E6D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF7E6D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN_RED = -0x1.0f1d38p126F;
    static { NAMED.put("pale brown red", -0x1.0f1d38p126F); LIST.add(-0x1.0f1d38p126F); }

    /**
     * This color constant "drab red brown" has RGBA8888 code {@code 77331EFF}, L 0.3019608, A 0.5411765, B 0.53333336, alpha 1.0, hue 0.10831932, saturation 0.5191111, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.11149ap126F}.
     * <pre>
     * <font style='background-color: #77331E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #77331E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #77331E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #77331E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #77331E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #77331E'>&nbsp;@&nbsp;</font><font style='background-color: #77331E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #77331E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #77331E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED_BROWN = -0x1.11149ap126F;
    static { NAMED.put("drab red brown", -0x1.11149ap126F); LIST.add(-0x1.11149ap126F); }

    /**
     * This color constant "dull red brown" has RGBA8888 code {@code B2624AFF}, L 0.47058824, A 0.5411765, B 0.53333336, alpha 1.0, hue 0.10831932, saturation 0.2648526, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.1114fp126F}.
     * <pre>
     * <font style='background-color: #B2624A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2624A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2624A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2624A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2624A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2624A'>&nbsp;@&nbsp;</font><font style='background-color: #B2624A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2624A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2624A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_RED_BROWN = -0x1.1114fp126F;
    static { NAMED.put("dull red brown", -0x1.1114fp126F); LIST.add(-0x1.1114fp126F); }

    /**
     * This color constant "pale red brown" has RGBA8888 code {@code E88F74FF}, L 0.6392157, A 0.5411765, B 0.53333336, alpha 1.0, hue 0.10831932, saturation 0.40415224, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.111546p126F}.
     * <pre>
     * <font style='background-color: #E88F74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E88F74; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E88F74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E88F74'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E88F74'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E88F74'>&nbsp;@&nbsp;</font><font style='background-color: #E88F74; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E88F74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E88F74; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED_BROWN = -0x1.111546p126F;
    static { NAMED.put("pale red brown", -0x1.111546p126F); LIST.add(-0x1.111546p126F); }

    /**
     * This color constant "drab orange brown" has RGBA8888 code {@code 6F422FFF}, L 0.32156864, A 0.5254902, B 0.5254902, alpha 1.0, hue 0.125, saturation 0.26820076, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0d0ca4p126F}.
     * <pre>
     * <font style='background-color: #6F422F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F422F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F422F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F422F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F422F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F422F'>&nbsp;@&nbsp;</font><font style='background-color: #6F422F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F422F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F422F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE_BROWN = -0x1.0d0ca4p126F;
    static { NAMED.put("drab orange brown", -0x1.0d0ca4p126F); LIST.add(-0x1.0d0ca4p126F); }

    /**
     * This color constant "dull orange brown" has RGBA8888 code {@code A6725BFF}, L 0.49019608, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.138223, saturation 0.1400797, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.0d0afap126F}.
     * <pre>
     * <font style='background-color: #A6725B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6725B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6725B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6725B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6725B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6725B'>&nbsp;@&nbsp;</font><font style='background-color: #A6725B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6725B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6725B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_ORANGE_BROWN = -0x1.0d0afap126F;
    static { NAMED.put("dull orange brown", -0x1.0d0afap126F); LIST.add(-0x1.0d0afap126F); }

    /**
     * This color constant "pale orange brown" has RGBA8888 code {@code DAA087FF}, L 0.6627451, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.138223, saturation 0.16055363, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.0d0b52p126F}.
     * <pre>
     * <font style='background-color: #DAA087;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAA087; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAA087;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAA087'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAA087'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAA087'>&nbsp;@&nbsp;</font><font style='background-color: #DAA087; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAA087;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAA087; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_BROWN = -0x1.0d0b52p126F;
    static { NAMED.put("pale orange brown", -0x1.0d0b52p126F); LIST.add(-0x1.0d0b52p126F); }

    /**
     * This color constant "drab brown orange" has RGBA8888 code {@code 654A3CFF}, L 0.32941177, A 0.5137255, B 0.5137255, alpha 1.0, hue 0.125, saturation 0.07776235, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.0706a8p126F}.
     * <pre>
     * <font style='background-color: #654A3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #654A3C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #654A3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #654A3C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #654A3C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #654A3C'>&nbsp;@&nbsp;</font><font style='background-color: #654A3C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #654A3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #654A3C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN_ORANGE = -0x1.0706a8p126F;
    static { NAMED.put("drab brown orange", -0x1.0706a8p126F); LIST.add(-0x1.0706a8p126F); }

    /**
     * This color constant "dull brown orange" has RGBA8888 code {@code 9D7E6CFF}, L 0.50980395, A 0.5137255, B 0.5176471, alpha 1.0, hue 0.14477962, saturation 0.06564828, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.090704p126F}.
     * <pre>
     * <font style='background-color: #9D7E6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D7E6C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D7E6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D7E6C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D7E6C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D7E6C'>&nbsp;@&nbsp;</font><font style='background-color: #9D7E6C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D7E6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D7E6C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BROWN_ORANGE = -0x1.090704p126F;
    static { NAMED.put("dull brown orange", -0x1.090704p126F); LIST.add(-0x1.090704p126F); }

    /**
     * This color constant "pale brown orange" has RGBA8888 code {@code CFAD99FF}, L 0.6862745, A 0.50980395, B 0.5176471, alpha 1.0, hue 0.16928826, saturation 0.05601797, and chroma 0.040217306.
     * It can be represented as a packed float with the constant {@code -0x1.09055ep126F}.
     * <pre>
     * <font style='background-color: #CFAD99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFAD99; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFAD99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFAD99'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFAD99'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFAD99'>&nbsp;@&nbsp;</font><font style='background-color: #CFAD99; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFAD99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFAD99; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN_ORANGE = -0x1.09055ep126F;
    static { NAMED.put("pale brown orange", -0x1.09055ep126F); LIST.add(-0x1.09055ep126F); }

    /**
     * This color constant "drab saffron orange" has RGBA8888 code {@code 7B4814FF}, L 0.34509805, A 0.52156866, B 0.54509807, alpha 1.0, hue 0.1790041, saturation 0.6987369, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.170abp126F}.
     * <pre>
     * <font style='background-color: #7B4814;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B4814; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B4814;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B4814'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B4814'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B4814'>&nbsp;@&nbsp;</font><font style='background-color: #7B4814; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B4814;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B4814; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON_ORANGE = -0x1.170abp126F;
    static { NAMED.put("drab saffron orange", -0x1.170abp126F); LIST.add(-0x1.170abp126F); }

    /**
     * This color constant "dull saffron orange" has RGBA8888 code {@code B77D46FF}, L 0.5294118, A 0.5176471, B 0.54509807, alpha 1.0, hue 0.19064914, saturation 0.390963, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.17090ep126F}.
     * <pre>
     * <font style='background-color: #B77D46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B77D46; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B77D46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B77D46'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B77D46'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B77D46'>&nbsp;@&nbsp;</font><font style='background-color: #B77D46; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B77D46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B77D46; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_SAFFRON_ORANGE = -0x1.17090ep126F;
    static { NAMED.put("dull saffron orange", -0x1.17090ep126F); LIST.add(-0x1.17090ep126F); }

    /**
     * This color constant "pale saffron orange" has RGBA8888 code {@code EDAC72FF}, L 0.70980394, A 0.52156866, B 0.54509807, alpha 1.0, hue 0.1790041, saturation 0.39628106, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.170b6ap126F}.
     * <pre>
     * <font style='background-color: #EDAC72;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDAC72; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDAC72;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDAC72'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDAC72'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDAC72'>&nbsp;@&nbsp;</font><font style='background-color: #EDAC72; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDAC72;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDAC72; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON_ORANGE = -0x1.170b6ap126F;
    static { NAMED.put("pale saffron orange", -0x1.170b6ap126F); LIST.add(-0x1.170b6ap126F); }

    /**
     * This color constant "drab orange saffron" has RGBA8888 code {@code 775021FF}, L 0.35686275, A 0.5137255, B 0.5372549, alpha 1.0, hue 0.19383265, saturation 0.44074175, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.1306b6p126F}.
     * <pre>
     * <font style='background-color: #775021;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #775021; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #775021;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #775021'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #775021'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #775021'>&nbsp;@&nbsp;</font><font style='background-color: #775021; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #775021;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #775021; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE_SAFFRON = -0x1.1306b6p126F;
    static { NAMED.put("drab orange saffron", -0x1.1306b6p126F); LIST.add(-0x1.1306b6p126F); }

    /**
     * This color constant "dull orange saffron" has RGBA8888 code {@code B58853FF}, L 0.5529412, A 0.5137255, B 0.5411765, alpha 1.0, hue 0.19880433, saturation 0.29873496, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.15071ap126F}.
     * <pre>
     * <font style='background-color: #B58853;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B58853; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B58853;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B58853'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B58853'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B58853'>&nbsp;@&nbsp;</font><font style='background-color: #B58853; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B58853;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B58853; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_ORANGE_SAFFRON = -0x1.15071ap126F;
    static { NAMED.put("dull orange saffron", -0x1.15071ap126F); LIST.add(-0x1.15071ap126F); }

    /**
     * This color constant "pale orange saffron" has RGBA8888 code {@code EBBA81FF}, L 0.74509805, A 0.50980395, B 0.5411765, alpha 1.0, hue 0.21279909, saturation 0.24626768, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.15057cp126F}.
     * <pre>
     * <font style='background-color: #EBBA81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBBA81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBBA81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBBA81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBBA81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBBA81'>&nbsp;@&nbsp;</font><font style='background-color: #EBBA81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBBA81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBBA81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_SAFFRON = -0x1.15057cp126F;
    static { NAMED.put("pale orange saffron", -0x1.15057cp126F); LIST.add(-0x1.15057cp126F); }

    /**
     * This color constant "drab yellow saffron" has RGBA8888 code {@code 775C16FF}, L 0.38039216, A 0.49803922, B 0.54901963, alpha 1.0, hue 0.25635016, saturation 0.6729374, and chroma 0.09773435.
     * It can be represented as a packed float with the constant {@code -0x1.18fec2p126F}.
     * <pre>
     * <font style='background-color: #775C16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #775C16; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #775C16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #775C16'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #775C16'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #775C16'>&nbsp;@&nbsp;</font><font style='background-color: #775C16; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #775C16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #775C16; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW_SAFFRON = -0x1.18fec2p126F;
    static { NAMED.put("drab yellow saffron", -0x1.18fec2p126F); LIST.add(-0x1.18fec2p126F); }

    /**
     * This color constant "dull yellow saffron" has RGBA8888 code {@code B4954DFF}, L 0.5803922, A 0.49803922, B 0.54901963, alpha 1.0, hue 0.25635016, saturation 0.40121776, and chroma 0.09773435.
     * It can be represented as a packed float with the constant {@code -0x1.18ff28p126F}.
     * <pre>
     * <font style='background-color: #B4954D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4954D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4954D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4954D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4954D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4954D'>&nbsp;@&nbsp;</font><font style='background-color: #B4954D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4954D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4954D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_YELLOW_SAFFRON = -0x1.18ff28p126F;
    static { NAMED.put("dull yellow saffron", -0x1.18ff28p126F); LIST.add(-0x1.18ff28p126F); }

    /**
     * This color constant "pale yellow saffron" has RGBA8888 code {@code ECCA7CFF}, L 0.78431374, A 0.49803922, B 0.54901963, alpha 1.0, hue 0.25635016, saturation 0.26612818, and chroma 0.09773435.
     * It can be represented as a packed float with the constant {@code -0x1.18ff9p126F}.
     * <pre>
     * <font style='background-color: #ECCA7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECCA7C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECCA7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECCA7C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECCA7C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECCA7C'>&nbsp;@&nbsp;</font><font style='background-color: #ECCA7C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECCA7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECCA7C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_SAFFRON = -0x1.18ff9p126F;
    static { NAMED.put("pale yellow saffron", -0x1.18ff9p126F); LIST.add(-0x1.18ff9p126F); }

    /**
     * This color constant "drab saffron yellow" has RGBA8888 code {@code 726D22FF}, L 0.41568628, A 0.48235294, B 0.54901963, alpha 1.0, hue 0.3049839, saturation 0.5931527, and chroma 0.10379164.
     * It can be represented as a packed float with the constant {@code -0x1.18f6d4p126F}.
     * <pre>
     * <font style='background-color: #726D22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #726D22; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #726D22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #726D22'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #726D22'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #726D22'>&nbsp;@&nbsp;</font><font style='background-color: #726D22; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #726D22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #726D22; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON_YELLOW = -0x1.18f6d4p126F;
    static { NAMED.put("drab saffron yellow", -0x1.18f6d4p126F); LIST.add(-0x1.18f6d4p126F); }

    /**
     * This color constant "dull saffron yellow" has RGBA8888 code {@code B1AC5BFF}, L 0.63529414, A 0.48235294, B 0.54901963, alpha 1.0, hue 0.3049839, saturation 0.34102163, and chroma 0.10379164.
     * It can be represented as a packed float with the constant {@code -0x1.18f744p126F}.
     * <pre>
     * <font style='background-color: #B1AC5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1AC5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1AC5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1AC5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1AC5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1AC5B'>&nbsp;@&nbsp;</font><font style='background-color: #B1AC5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1AC5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1AC5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_SAFFRON_YELLOW = -0x1.18f744p126F;
    static { NAMED.put("dull saffron yellow", -0x1.18f744p126F); LIST.add(-0x1.18f744p126F); }

    /**
     * This color constant "pale saffron yellow" has RGBA8888 code {@code E8E38CFF}, L 0.8509804, A 0.4862745, B 0.54901963, alpha 1.0, hue 0.29344302, saturation 0.24453515, and chroma 0.10141215.
     * It can be represented as a packed float with the constant {@code -0x1.18f9b2p126F}.
     * <pre>
     * <font style='background-color: #E8E38C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8E38C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8E38C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E8E38C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E8E38C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E8E38C'>&nbsp;@&nbsp;</font><font style='background-color: #E8E38C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8E38C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8E38C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON_YELLOW = -0x1.18f9b2p126F;
    static { NAMED.put("pale saffron yellow", -0x1.18f9b2p126F); LIST.add(-0x1.18f9b2p126F); }

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
     * This color constant "drab yellow lime" has RGBA8888 code {@code 567522FF}, L 0.4117647, A 0.4627451, B 0.54901963, alpha 1.0, hue 0.3534426, saturation 0.60112786, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.18ecd2p126F}.
     * <pre>
     * <font style='background-color: #567522;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #567522; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #567522;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #567522'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #567522'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #567522'>&nbsp;@&nbsp;</font><font style='background-color: #567522; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #567522;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #567522; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW_LIME = -0x1.18ecd2p126F;
    static { NAMED.put("drab yellow lime", -0x1.18ecd2p126F); LIST.add(-0x1.18ecd2p126F); }

    /**
     * This color constant "dull yellow lime" has RGBA8888 code {@code 8DB258FF}, L 0.61960787, A 0.4627451, B 0.54901963, alpha 1.0, hue 0.3534426, saturation 0.34448424, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.18ed3cp126F}.
     * <pre>
     * <font style='background-color: #8DB258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DB258; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DB258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8DB258'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8DB258'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8DB258'>&nbsp;@&nbsp;</font><font style='background-color: #8DB258; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DB258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DB258; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_YELLOW_LIME = -0x1.18ed3cp126F;
    static { NAMED.put("dull yellow lime", -0x1.18ed3cp126F); LIST.add(-0x1.18ed3cp126F); }

    /**
     * This color constant "pale yellow lime" has RGBA8888 code {@code C0EA88FF}, L 0.83137256, A 0.45882353, B 0.54901963, alpha 1.0, hue 0.3612048, saturation 0.24105376, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.18eba8p126F}.
     * <pre>
     * <font style='background-color: #C0EA88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0EA88; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0EA88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0EA88'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0EA88'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0EA88'>&nbsp;@&nbsp;</font><font style='background-color: #C0EA88; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0EA88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0EA88; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_LIME = -0x1.18eba8p126F;
    static { NAMED.put("pale yellow lime", -0x1.18eba8p126F); LIST.add(-0x1.18eba8p126F); }

    /**
     * This color constant "drab green lime" has RGBA8888 code {@code 3C7510FF}, L 0.39215687, A 0.44313726, B 0.5529412, alpha 1.0, hue 0.3806773, saturation 0.7260955, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.1ae2c8p126F}.
     * <pre>
     * <font style='background-color: #3C7510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C7510; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C7510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C7510'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C7510'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C7510'>&nbsp;@&nbsp;</font><font style='background-color: #3C7510; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C7510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C7510; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN_LIME = -0x1.1ae2c8p126F;
    static { NAMED.put("drab green lime", -0x1.1ae2c8p126F); LIST.add(-0x1.1ae2c8p126F); }

    /**
     * This color constant "dull green lime" has RGBA8888 code {@code 72B34CFF}, L 0.6, A 0.44313726, B 0.5529412, alpha 1.0, hue 0.3806773, saturation 0.41509682, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.1ae332p126F}.
     * <pre>
     * <font style='background-color: #72B34C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #72B34C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #72B34C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #72B34C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #72B34C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #72B34C'>&nbsp;@&nbsp;</font><font style='background-color: #72B34C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #72B34C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #72B34C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN_LIME = -0x1.1ae332p126F;
    static { NAMED.put("dull green lime", -0x1.1ae332p126F); LIST.add(-0x1.1ae332p126F); }

    /**
     * This color constant "pale green lime" has RGBA8888 code {@code A4EC7DFF}, L 0.80784315, A 0.44313726, B 0.5529412, alpha 1.0, hue 0.3806773, saturation 0.29061964, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.1ae39cp126F}.
     * <pre>
     * <font style='background-color: #A4EC7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4EC7D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4EC7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4EC7D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4EC7D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4EC7D'>&nbsp;@&nbsp;</font><font style='background-color: #A4EC7D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4EC7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4EC7D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN_LIME = -0x1.1ae39cp126F;
    static { NAMED.put("pale green lime", -0x1.1ae39cp126F); LIST.add(-0x1.1ae39cp126F); }

    /**
     * This color constant "drab lime green" has RGBA8888 code {@code 1D772CFF}, L 0.3882353, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.40979147, saturation 0.84705883, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14dec6p126F}.
     * <pre>
     * <font style='background-color: #1D772C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D772C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D772C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D772C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D772C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D772C'>&nbsp;@&nbsp;</font><font style='background-color: #1D772C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D772C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D772C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME_GREEN = -0x1.14dec6p126F;
    static { NAMED.put("drab lime green", -0x1.14dec6p126F); LIST.add(-0x1.14dec6p126F); }

    /**
     * This color constant "dull lime green" has RGBA8888 code {@code 57B45FFF}, L 0.5882353, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.40979147, saturation 0.4627599, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df2cp126F}.
     * <pre>
     * <font style='background-color: #57B45F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57B45F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57B45F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57B45F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57B45F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57B45F'>&nbsp;@&nbsp;</font><font style='background-color: #57B45F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57B45F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57B45F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_LIME_GREEN = -0x1.14df2cp126F;
    static { NAMED.put("dull lime green", -0x1.14df2cp126F); LIST.add(-0x1.14df2cp126F); }

    /**
     * This color constant "pale lime green" has RGBA8888 code {@code 87EB8EFF}, L 0.7882353, A 0.43529412, B 0.5411765, alpha 1.0, hue 0.40979147, saturation 0.3078316, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.14df92p126F}.
     * <pre>
     * <font style='background-color: #87EB8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87EB8E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87EB8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87EB8E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87EB8E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87EB8E'>&nbsp;@&nbsp;</font><font style='background-color: #87EB8E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87EB8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87EB8E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME_GREEN = -0x1.14df92p126F;
    static { NAMED.put("pale lime green", -0x1.14df92p126F); LIST.add(-0x1.14df92p126F); }

    /**
     * This color constant "drab cyan green" has RGBA8888 code {@code 007649FF}, L 0.3882353, A 0.44313726, B 0.52156866, alpha 1.0, hue 0.4423118, saturation 0.8572065, and chroma 0.12115674.
     * It can be represented as a packed float with the constant {@code -0x1.0ae2c6p126F}.
     * <pre>
     * <font style='background-color: #007649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007649; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007649'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007649'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007649'>&nbsp;@&nbsp;</font><font style='background-color: #007649; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007649; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN_GREEN = -0x1.0ae2c6p126F;
    static { NAMED.put("drab cyan green", -0x1.0ae2c6p126F); LIST.add(-0x1.0ae2c6p126F); }

    /**
     * This color constant "dull cyan green" has RGBA8888 code {@code 42A874FF}, L 0.54901963, A 0.4392157, B 0.52156866, alpha 1.0, hue 0.44574448, saturation 0.6282479, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.0ae118p126F}.
     * <pre>
     * <font style='background-color: #42A874;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42A874; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42A874;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #42A874'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #42A874'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #42A874'>&nbsp;@&nbsp;</font><font style='background-color: #42A874; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42A874;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42A874; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_CYAN_GREEN = -0x1.0ae118p126F;
    static { NAMED.put("dull cyan green", -0x1.0ae118p126F); LIST.add(-0x1.0ae118p126F); }

    /**
     * This color constant "pale cyan green" has RGBA8888 code {@code 6BD59CFF}, L 0.70980394, A 0.4392157, B 0.52156866, alpha 1.0, hue 0.44574448, saturation 0.4415876, and chroma 0.12849128.
     * It can be represented as a packed float with the constant {@code -0x1.0ae16ap126F}.
     * <pre>
     * <font style='background-color: #6BD59C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6BD59C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6BD59C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6BD59C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6BD59C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6BD59C'>&nbsp;@&nbsp;</font><font style='background-color: #6BD59C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6BD59C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6BD59C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN_GREEN = -0x1.0ae16ap126F;
    static { NAMED.put("pale cyan green", -0x1.0ae16ap126F); LIST.add(-0x1.0ae16ap126F); }

    /**
     * This color constant "drab green cyan" has RGBA8888 code {@code 006C65FF}, L 0.36862746, A 0.45490196, B 0.49411765, alpha 1.0, hue 0.5206551, saturation 0.8273741, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.fce8bcp125F}.
     * <pre>
     * <font style='background-color: #006C65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #006C65; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #006C65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #006C65'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #006C65'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #006C65'>&nbsp;@&nbsp;</font><font style='background-color: #006C65; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #006C65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #006C65; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN_CYAN = -0x1.fce8bcp125F;
    static { NAMED.put("drab green cyan", -0x1.fce8bcp125F); LIST.add(-0x1.fce8bcp125F); }

    /**
     * This color constant "dull green cyan" has RGBA8888 code {@code 3C9B92FF}, L 0.52156866, A 0.4509804, B 0.49411765, alpha 1.0, hue 0.51901877, saturation 0.6002367, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.fce70ap125F}.
     * <pre>
     * <font style='background-color: #3C9B92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C9B92; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C9B92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C9B92'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C9B92'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C9B92'>&nbsp;@&nbsp;</font><font style='background-color: #3C9B92; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C9B92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C9B92; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN_CYAN = -0x1.fce70ap125F;
    static { NAMED.put("dull green cyan", -0x1.fce70ap125F); LIST.add(-0x1.fce70ap125F); }

    /**
     * This color constant "pale green cyan" has RGBA8888 code {@code 63C5BBFF}, L 0.67058825, A 0.4509804, B 0.49411765, alpha 1.0, hue 0.51901877, saturation 0.45084444, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.fce756p125F}.
     * <pre>
     * <font style='background-color: #63C5BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63C5BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63C5BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #63C5BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #63C5BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #63C5BB'>&nbsp;@&nbsp;</font><font style='background-color: #63C5BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63C5BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63C5BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN_CYAN = -0x1.fce756p125F;
    static { NAMED.put("pale green cyan", -0x1.fce756p125F); LIST.add(-0x1.fce756p125F); }

    /**
     * This color constant "drab blue cyan" has RGBA8888 code {@code 006A7CFF}, L 0.37254903, A 0.45882353, B 0.4745098, alpha 1.0, hue 0.5882305, saturation 0.93810076, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.f2eabep125F}.
     * <pre>
     * <font style='background-color: #006A7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #006A7C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #006A7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #006A7C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #006A7C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #006A7C'>&nbsp;@&nbsp;</font><font style='background-color: #006A7C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #006A7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #006A7C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE_CYAN = -0x1.f2eabep125F;
    static { NAMED.put("drab blue cyan", -0x1.f2eabep125F); LIST.add(-0x1.f2eabep125F); }

    /**
     * This color constant "dull blue cyan" has RGBA8888 code {@code 41A6BAFF}, L 0.5686275, A 0.45490196, B 0.47058824, alpha 1.0, hue 0.5919875, saturation 0.63348037, and chroma 0.107261956.
     * It can be represented as a packed float with the constant {@code -0x1.f0e922p125F}.
     * <pre>
     * <font style='background-color: #41A6BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #41A6BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #41A6BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #41A6BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #41A6BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #41A6BA'>&nbsp;@&nbsp;</font><font style='background-color: #41A6BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #41A6BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #41A6BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BLUE_CYAN = -0x1.f0e922p125F;
    static { NAMED.put("dull blue cyan", -0x1.f0e922p125F); LIST.add(-0x1.f0e922p125F); }

    /**
     * This color constant "pale blue cyan" has RGBA8888 code {@code 73DDF2FF}, L 0.76862746, A 0.45490196, B 0.4745098, alpha 1.0, hue 0.5818829, saturation 0.4255449, and chroma 0.10320191.
     * It can be represented as a packed float with the constant {@code -0x1.f2e988p125F}.
     * <pre>
     * <font style='background-color: #73DDF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73DDF2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73DDF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73DDF2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73DDF2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73DDF2'>&nbsp;@&nbsp;</font><font style='background-color: #73DDF2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73DDF2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73DDF2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE_CYAN = -0x1.f2e988p125F;
    static { NAMED.put("pale blue cyan", -0x1.f2e988p125F); LIST.add(-0x1.f2e988p125F); }

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
     * This color constant "drab violet blue" has RGBA8888 code {@code 1C1F81FF}, L 0.21960784, A 0.49803922, B 0.4117647, alpha 1.0, hue 0.7464744, saturation 0.61277884, and chroma 0.17582464.
     * It can be represented as a packed float with the constant {@code -0x1.d2fe7p125F}.
     * <pre>
     * <font style='background-color: #1C1F81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C1F81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C1F81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C1F81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C1F81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C1F81'>&nbsp;@&nbsp;</font><font style='background-color: #1C1F81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C1F81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C1F81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET_BLUE = -0x1.d2fe7p125F;
    static { NAMED.put("drab violet blue", -0x1.d2fe7p125F); LIST.add(-0x1.d2fe7p125F); }

    /**
     * This color constant "dull violet blue" has RGBA8888 code {@code 3F51BFFF}, L 0.37254903, A 0.5019608, B 0.4117647, alpha 1.0, hue 0.7535256, saturation 0.3650286, and chroma 0.17582464.
     * It can be represented as a packed float with the constant {@code -0x1.d300bep125F}.
     * <pre>
     * <font style='background-color: #3F51BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F51BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F51BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F51BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F51BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F51BF'>&nbsp;@&nbsp;</font><font style='background-color: #3F51BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F51BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F51BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET_BLUE = -0x1.d300bep125F;
    static { NAMED.put("dull violet blue", -0x1.d300bep125F); LIST.add(-0x1.d300bep125F); }

    /**
     * This color constant "pale violet blue" has RGBA8888 code {@code 657FF8FF}, L 0.5254902, A 0.49803922, B 0.4117647, alpha 1.0, hue 0.7464744, saturation 0.8268544, and chroma 0.17582464.
     * It can be represented as a packed float with the constant {@code -0x1.d2ff0cp125F}.
     * <pre>
     * <font style='background-color: #657FF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #657FF8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #657FF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #657FF8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #657FF8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #657FF8'>&nbsp;@&nbsp;</font><font style='background-color: #657FF8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #657FF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #657FF8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET_BLUE = -0x1.d2ff0cp125F;
    static { NAMED.put("pale violet blue", -0x1.d2ff0cp125F); LIST.add(-0x1.d2ff0cp125F); }

    /**
     * This color constant "drab blue violet" has RGBA8888 code {@code 3A2278FF}, L 0.23921569, A 0.5254902, B 0.42745098, alpha 1.0, hue 0.803762, saturation 0.5178015, and chroma 0.15319274.
     * It can be represented as a packed float with the constant {@code -0x1.db0c7ap125F}.
     * <pre>
     * <font style='background-color: #3A2278;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A2278; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A2278;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A2278'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A2278'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A2278'>&nbsp;@&nbsp;</font><font style='background-color: #3A2278; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A2278;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A2278; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE_VIOLET = -0x1.db0c7ap125F;
    static { NAMED.put("drab blue violet", -0x1.db0c7ap125F); LIST.add(-0x1.db0c7ap125F); }

    /**
     * This color constant "dull blue violet" has RGBA8888 code {@code 6652B4FF}, L 0.39607844, A 0.5254902, B 0.42745098, alpha 1.0, hue 0.803762, saturation 0.2628049, and chroma 0.15319274.
     * It can be represented as a packed float with the constant {@code -0x1.db0ccap125F}.
     * <pre>
     * <font style='background-color: #6652B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6652B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6652B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6652B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6652B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6652B4'>&nbsp;@&nbsp;</font><font style='background-color: #6652B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6652B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6652B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BLUE_VIOLET = -0x1.db0ccap125F;
    static { NAMED.put("dull blue violet", -0x1.db0ccap125F); LIST.add(-0x1.db0ccap125F); }

    /**
     * This color constant "pale blue violet" has RGBA8888 code {@code 927FECFF}, L 0.5568628, A 0.5254902, B 0.42745098, alpha 1.0, hue 0.803762, saturation 0.62769103, and chroma 0.15319274.
     * It can be represented as a packed float with the constant {@code -0x1.db0d1cp125F}.
     * <pre>
     * <font style='background-color: #927FEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #927FEC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #927FEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #927FEC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #927FEC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #927FEC'>&nbsp;@&nbsp;</font><font style='background-color: #927FEC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #927FEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #927FEC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE_VIOLET = -0x1.db0d1cp125F;
    static { NAMED.put("pale blue violet", -0x1.db0d1cp125F); LIST.add(-0x1.db0d1cp125F); }

    /**
     * This color constant "drab purple violet" has RGBA8888 code {@code 4E1D7FFF}, L 0.25882354, A 0.54509807, B 0.42745098, alpha 1.0, hue 0.8385265, saturation 0.66311467, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.db1684p125F}.
     * <pre>
     * <font style='background-color: #4E1D7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E1D7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E1D7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E1D7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E1D7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E1D7F'>&nbsp;@&nbsp;</font><font style='background-color: #4E1D7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E1D7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E1D7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE_VIOLET = -0x1.db1684p125F;
    static { NAMED.put("drab purple violet", -0x1.db1684p125F); LIST.add(-0x1.db1684p125F); }

    /**
     * This color constant "dull purple violet" has RGBA8888 code {@code 804EBCFF}, L 0.41960785, A 0.54509807, B 0.42745098, alpha 1.0, hue 0.8385265, saturation 0.30800438, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.db16d6p125F}.
     * <pre>
     * <font style='background-color: #804EBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #804EBC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #804EBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #804EBC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #804EBC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #804EBC'>&nbsp;@&nbsp;</font><font style='background-color: #804EBC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #804EBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #804EBC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_PURPLE_VIOLET = -0x1.db16d6p125F;
    static { NAMED.put("dull purple violet", -0x1.db16d6p125F); LIST.add(-0x1.db16d6p125F); }

    /**
     * This color constant "pale purple violet" has RGBA8888 code {@code B07CF4FF}, L 0.58431375, A 0.5411765, B 0.42745098, alpha 1.0, hue 0.8321663, saturation 0.7097343, and chroma 0.16618787.
     * It can be represented as a packed float with the constant {@code -0x1.db152ap125F}.
     * <pre>
     * <font style='background-color: #B07CF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B07CF4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B07CF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B07CF4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B07CF4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B07CF4'>&nbsp;@&nbsp;</font><font style='background-color: #B07CF4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B07CF4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B07CF4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE_VIOLET = -0x1.db152ap125F;
    static { NAMED.put("pale purple violet", -0x1.db152ap125F); LIST.add(-0x1.db152ap125F); }

    /**
     * This color constant "drab violet purple" has RGBA8888 code {@code 55257DFF}, L 0.27450982, A 0.54509807, B 0.43529412, alpha 1.0, hue 0.84688866, saturation 0.525282, and chroma 0.15712644.
     * It can be represented as a packed float with the constant {@code -0x1.df168cp125F}.
     * <pre>
     * <font style='background-color: #55257D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55257D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55257D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #55257D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #55257D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #55257D'>&nbsp;@&nbsp;</font><font style='background-color: #55257D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55257D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55257D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET_PURPLE = -0x1.df168cp125F;
    static { NAMED.put("drab violet purple", -0x1.df168cp125F); LIST.add(-0x1.df168cp125F); }

    /**
     * This color constant "dull violet purple" has RGBA8888 code {@code 8754B8FF}, L 0.43529412, A 0.54509807, B 0.43529412, alpha 1.0, hue 0.84688866, saturation 0.25600255, and chroma 0.15712644.
     * It can be represented as a packed float with the constant {@code -0x1.df16dep125F}.
     * <pre>
     * <font style='background-color: #8754B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8754B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8754B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8754B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8754B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8754B8'>&nbsp;@&nbsp;</font><font style='background-color: #8754B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8754B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8754B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET_PURPLE = -0x1.df16dep125F;
    static { NAMED.put("dull violet purple", -0x1.df16dep125F); LIST.add(-0x1.df16dep125F); }

    /**
     * This color constant "pale violet purple" has RGBA8888 code {@code B881EFFF}, L 0.6, A 0.54509807, B 0.43529412, alpha 1.0, hue 0.84688866, saturation 0.61004806, and chroma 0.15712644.
     * It can be represented as a packed float with the constant {@code -0x1.df1732p125F}.
     * <pre>
     * <font style='background-color: #B881EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B881EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B881EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B881EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B881EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B881EF'>&nbsp;@&nbsp;</font><font style='background-color: #B881EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B881EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B881EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET_PURPLE = -0x1.df1732p125F;
    static { NAMED.put("pale violet purple", -0x1.df1732p125F); LIST.add(-0x1.df1732p125F); }

    /**
     * This color constant "drab magenta purple" has RGBA8888 code {@code 651F82FF}, L 0.2901961, A 0.56078434, B 0.43529412, alpha 1.0, hue 0.8700319, saturation 0.6655304, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.df1e94p125F}.
     * <pre>
     * <font style='background-color: #651F82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #651F82; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #651F82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #651F82'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #651F82'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #651F82'>&nbsp;@&nbsp;</font><font style='background-color: #651F82; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #651F82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #651F82; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA_PURPLE = -0x1.df1e94p125F;
    static { NAMED.put("drab magenta purple", -0x1.df1e94p125F); LIST.add(-0x1.df1e94p125F); }

    /**
     * This color constant "dull magenta purple" has RGBA8888 code {@code 9D51BFFF}, L 0.45882353, A 0.56078434, B 0.43529412, alpha 1.0, hue 0.8700319, saturation 0.3326707, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.df1eeap125F}.
     * <pre>
     * <font style='background-color: #9D51BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D51BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D51BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D51BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D51BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D51BF'>&nbsp;@&nbsp;</font><font style='background-color: #9D51BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D51BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D51BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_MAGENTA_PURPLE = -0x1.df1eeap125F;
    static { NAMED.put("dull magenta purple", -0x1.df1eeap125F); LIST.add(-0x1.df1eeap125F); }

    /**
     * This color constant "pale magenta purple" has RGBA8888 code {@code D17FF6FF}, L 0.627451, A 0.56078434, B 0.43529412, alpha 1.0, hue 0.8700319, saturation 0.7162198, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.df1f4p125F}.
     * <pre>
     * <font style='background-color: #D17FF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D17FF6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D17FF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D17FF6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D17FF6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D17FF6'>&nbsp;@&nbsp;</font><font style='background-color: #D17FF6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D17FF6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D17FF6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA_PURPLE = -0x1.df1f4p125F;
    static { NAMED.put("pale magenta purple", -0x1.df1f4p125F); LIST.add(-0x1.df1f4p125F); }

    /**
     * This color constant "drab purple magenta" has RGBA8888 code {@code 6F297DFF}, L 0.30980393, A 0.56078434, B 0.44705883, alpha 1.0, hue 0.88595086, saturation 0.52940714, and chroma 0.1605844.
     * It can be represented as a packed float with the constant {@code -0x1.e51e9ep125F}.
     * <pre>
     * <font style='background-color: #6F297D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F297D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F297D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F297D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F297D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F297D'>&nbsp;@&nbsp;</font><font style='background-color: #6F297D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F297D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F297D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE_MAGENTA = -0x1.e51e9ep125F;
    static { NAMED.put("drab purple magenta", -0x1.e51e9ep125F); LIST.add(-0x1.e51e9ep125F); }

    /**
     * This color constant "dull purple magenta" has RGBA8888 code {@code A85AB8FF}, L 0.48235294, A 0.5568628, B 0.44705883, alpha 1.0, hue 0.8806773, saturation 0.2484079, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.e51cf6p125F}.
     * <pre>
     * <font style='background-color: #A85AB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A85AB8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A85AB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A85AB8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A85AB8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A85AB8'>&nbsp;@&nbsp;</font><font style='background-color: #A85AB8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A85AB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A85AB8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_PURPLE_MAGENTA = -0x1.e51cf6p125F;
    static { NAMED.put("dull purple magenta", -0x1.e51cf6p125F); LIST.add(-0x1.e51cf6p125F); }

    /**
     * This color constant "pale purple magenta" has RGBA8888 code {@code DF88EFFF}, L 0.65882355, A 0.56078434, B 0.44705883, alpha 1.0, hue 0.88595086, saturation 0.59044456, and chroma 0.1605844.
     * It can be represented as a packed float with the constant {@code -0x1.e51f5p125F}.
     * <pre>
     * <font style='background-color: #DF88EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF88EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF88EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF88EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF88EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF88EF'>&nbsp;@&nbsp;</font><font style='background-color: #DF88EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF88EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF88EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE_MAGENTA = -0x1.e51f5p125F;
    static { NAMED.put("pale purple magenta", -0x1.e51f5p125F); LIST.add(-0x1.e51f5p125F); }

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
     * This color constant "drab magenta red" has RGBA8888 code {@code 752131FF}, L 0.2784314, A 0.56078434, B 0.5137255, alpha 1.0, hue 0.035347383, saturation 0.5100366, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.071e8ep126F}.
     * <pre>
     * <font style='background-color: #752131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #752131; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #752131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #752131'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #752131'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #752131'>&nbsp;@&nbsp;</font><font style='background-color: #752131; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #752131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #752131; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA_RED = -0x1.071e8ep126F;
    static { NAMED.put("drab magenta red", -0x1.071e8ep126F); LIST.add(-0x1.071e8ep126F); }

    /**
     * This color constant "dull magenta red" has RGBA8888 code {@code B2505DFF}, L 0.44313726, A 0.56078434, B 0.5137255, alpha 1.0, hue 0.035347383, saturation 0.25856, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.071ee2p126F}.
     * <pre>
     * <font style='background-color: #B2505D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2505D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2505D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2505D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2505D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2505D'>&nbsp;@&nbsp;</font><font style='background-color: #B2505D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2505D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2505D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_MAGENTA_RED = -0x1.071ee2p126F;
    static { NAMED.put("dull magenta red", -0x1.071ee2p126F); LIST.add(-0x1.071ee2p126F); }

    /**
     * This color constant "pale magenta red" has RGBA8888 code {@code E87B88FF}, L 0.6039216, A 0.56078434, B 0.5137255, alpha 1.0, hue 0.035347383, saturation 0.44764543, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.071f34p126F}.
     * <pre>
     * <font style='background-color: #E87B88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E87B88; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E87B88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E87B88'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E87B88'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E87B88'>&nbsp;@&nbsp;</font><font style='background-color: #E87B88; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E87B88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E87B88; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA_RED = -0x1.071f34p126F;
    static { NAMED.put("pale magenta red", -0x1.071f34p126F); LIST.add(-0x1.071f34p126F); }

    /**
     * This color constant "deep red" has RGBA8888 code {@code BF1F20FF}, L 0.39215687, A 0.5882353, B 0.54509807, alpha 1.0, hue 0.07519996, saturation 0.74629265, and chroma 0.1974106.
     * It can be represented as a packed float with the constant {@code -0x1.172cc8p126F}.
     * <pre>
     * <font style='background-color: #BF1F20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF1F20; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF1F20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF1F20'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF1F20'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF1F20'>&nbsp;@&nbsp;</font><font style='background-color: #BF1F20; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF1F20;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF1F20; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED = -0x1.172cc8p126F;
    static { NAMED.put("deep red", -0x1.172cc8p126F); LIST.add(-0x1.172cc8p126F); }

    /**
     * This color constant "bright red" has RGBA8888 code {@code EF4840FF}, L 0.5137255, A 0.5882353, B 0.54509807, alpha 1.0, hue 0.07519996, saturation 0.61390543, and chroma 0.1974106.
     * It can be represented as a packed float with the constant {@code -0x1.172d06p126F}.
     * <pre>
     * <font style='background-color: #EF4840;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF4840; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF4840;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF4840'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF4840'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF4840'>&nbsp;@&nbsp;</font><font style='background-color: #EF4840; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF4840;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF4840; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED = -0x1.172d06p126F;
    static { NAMED.put("bright red", -0x1.172d06p126F); LIST.add(-0x1.172d06p126F); }

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
     * This color constant "deep red brown" has RGBA8888 code {@code C0431AFF}, L 0.43137255, A 0.5647059, B 0.5529412, alpha 1.0, hue 0.109147884, saturation 0.7419651, and chroma 0.16655473.
     * It can be represented as a packed float with the constant {@code -0x1.1b20dcp126F}.
     * <pre>
     * <font style='background-color: #C0431A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0431A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0431A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0431A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0431A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0431A'>&nbsp;@&nbsp;</font><font style='background-color: #C0431A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0431A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0431A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.1b20dcp126F;
    static { NAMED.put("deep red brown", -0x1.1b20dcp126F); LIST.add(-0x1.1b20dcp126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code EE683EFF}, L 0.56078434, A 0.5647059, B 0.5529412, alpha 1.0, hue 0.109147884, saturation 0.56950426, and chroma 0.16655473.
     * It can be represented as a packed float with the constant {@code -0x1.1b211ep126F}.
     * <pre>
     * <font style='background-color: #EE683E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE683E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE683E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EE683E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EE683E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EE683E'>&nbsp;@&nbsp;</font><font style='background-color: #EE683E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE683E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE683E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.1b211ep126F;
    static { NAMED.put("bright red brown", -0x1.1b211ep126F); LIST.add(-0x1.1b211ep126F); }

    /**
     * This color constant "deep brown" has RGBA8888 code {@code A65E44FF}, L 0.44705883, A 0.5372549, B 0.53333336, alpha 1.0, hue 0.116173744, saturation 0.2763312, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.1112e4p126F}.
     * <pre>
     * <font style='background-color: #A65E44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A65E44; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A65E44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A65E44'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A65E44'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A65E44'>&nbsp;@&nbsp;</font><font style='background-color: #A65E44; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A65E44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A65E44; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN = -0x1.1112e4p126F;
    static { NAMED.put("deep brown", -0x1.1112e4p126F); LIST.add(-0x1.1112e4p126F); }

    /**
     * This color constant "bright brown" has RGBA8888 code {@code D18165FF}, L 0.5764706, A 0.5372549, B 0.53333336, alpha 1.0, hue 0.116173744, saturation 0.2188368, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.111326p126F}.
     * <pre>
     * <font style='background-color: #D18165;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D18165; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D18165;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D18165'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D18165'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D18165'>&nbsp;@&nbsp;</font><font style='background-color: #D18165; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D18165;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D18165; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN = -0x1.111326p126F;
    static { NAMED.put("bright brown", -0x1.111326p126F); LIST.add(-0x1.111326p126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code AC5F3AFF}, L 0.45490196, A 0.5372549, B 0.5372549, alpha 1.0, hue 0.125, saturation 0.34875014, and chroma 0.104961164.
     * It can be represented as a packed float with the constant {@code -0x1.1312e8p126F}.
     * <pre>
     * <font style='background-color: #AC5F3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC5F3A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC5F3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC5F3A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC5F3A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC5F3A'>&nbsp;@&nbsp;</font><font style='background-color: #AC5F3A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC5F3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC5F3A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.1312e8p126F;
    static { NAMED.put("deep orange brown", -0x1.1312e8p126F); LIST.add(-0x1.1312e8p126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code DA865DFF}, L 0.59607846, A 0.53333336, B 0.5411765, alpha 1.0, hue 0.14168067, saturation 0.28624645, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.15113p126F}.
     * <pre>
     * <font style='background-color: #DA865D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA865D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA865D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA865D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA865D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA865D'>&nbsp;@&nbsp;</font><font style='background-color: #DA865D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA865D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA865D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.15113p126F;
    static { NAMED.put("bright orange brown", -0x1.15113p126F); LIST.add(-0x1.15113p126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code BB6128FF}, L 0.4745098, A 0.5372549, B 0.5529412, alpha 1.0, hue 0.15239218, saturation 0.632893, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.1b12f2p126F}.
     * <pre>
     * <font style='background-color: #BB6128;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB6128; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB6128;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB6128'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB6128'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB6128'>&nbsp;@&nbsp;</font><font style='background-color: #BB6128; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB6128;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB6128; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.1b12f2p126F;
    static { NAMED.put("deep brown orange", -0x1.1b12f2p126F); LIST.add(-0x1.1b12f2p126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code E9864BFF}, L 0.6117647, A 0.5411765, B 0.5529412, alpha 1.0, hue 0.14477962, saturation 0.45877856, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.1b1538p126F}.
     * <pre>
     * <font style='background-color: #E9864B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9864B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9864B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9864B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9864B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9864B'>&nbsp;@&nbsp;</font><font style='background-color: #E9864B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9864B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9864B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.1b1538p126F;
    static { NAMED.put("bright brown orange", -0x1.1b1538p126F); LIST.add(-0x1.1b1538p126F); }

    /**
     * This color constant "deep orange" has RGBA8888 code {@code BD6312FF}, L 0.47843137, A 0.5372549, B 0.56078434, alpha 1.0, hue 0.1624788, saturation 0.8059747, and chroma 0.14202859.
     * It can be represented as a packed float with the constant {@code -0x1.1f12f4p126F}.
     * <pre>
     * <font style='background-color: #BD6312;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD6312; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD6312;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD6312'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD6312'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD6312'>&nbsp;@&nbsp;</font><font style='background-color: #BD6312; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD6312;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD6312; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE = -0x1.1f12f4p126F;
    static { NAMED.put("deep orange", -0x1.1f12f4p126F); LIST.add(-0x1.1f12f4p126F); }

    /**
     * This color constant "bright orange" has RGBA8888 code {@code ED8B3FFF}, L 0.627451, A 0.5372549, B 0.56078434, alpha 1.0, hue 0.1624788, saturation 0.5620151, and chroma 0.14202859.
     * It can be represented as a packed float with the constant {@code -0x1.1f134p126F}.
     * <pre>
     * <font style='background-color: #ED8B3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED8B3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED8B3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ED8B3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ED8B3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ED8B3F'>&nbsp;@&nbsp;</font><font style='background-color: #ED8B3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED8B3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED8B3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE = -0x1.1f134p126F;
    static { NAMED.put("bright orange", -0x1.1f134p126F); LIST.add(-0x1.1f134p126F); }

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
     * This color constant "deep orange saffron" has RGBA8888 code {@code BD7713FF}, L 0.5176471, A 0.52156866, B 0.5647059, alpha 1.0, hue 0.19880433, saturation 0.81632656, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.210b08p126F}.
     * <pre>
     * <font style='background-color: #BD7713;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD7713; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD7713;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD7713'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD7713'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD7713'>&nbsp;@&nbsp;</font><font style='background-color: #BD7713; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD7713;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD7713; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.210b08p126F;
    static { NAMED.put("deep orange saffron", -0x1.210b08p126F); LIST.add(-0x1.210b08p126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code EEA243FF}, L 0.6784314, A 0.5176471, B 0.5647059, alpha 1.0, hue 0.20763123, saturation 0.5651491, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.21095ap126F}.
     * <pre>
     * <font style='background-color: #EEA243;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEA243; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEA243;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EEA243'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EEA243'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EEA243'>&nbsp;@&nbsp;</font><font style='background-color: #EEA243; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEA243;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEA243; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.21095ap126F;
    static { NAMED.put("bright orange saffron", -0x1.21095ap126F); LIST.add(-0x1.21095ap126F); }

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
     * This color constant "deep saffron yellow" has RGBA8888 code {@code BFAD33FF}, L 0.64705884, A 0.48235294, B 0.5686275, alpha 1.0, hue 0.29005373, saturation 0.6595127, and chroma 0.1411665.
     * It can be represented as a packed float with the constant {@code -0x1.22f74ap126F}.
     * <pre>
     * <font style='background-color: #BFAD33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFAD33; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFAD33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFAD33'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFAD33'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFAD33'>&nbsp;@&nbsp;</font><font style='background-color: #BFAD33; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFAD33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFAD33; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.22f74ap126F;
    static { NAMED.put("deep saffron yellow", -0x1.22f74ap126F); LIST.add(-0x1.22f74ap126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code ECD95DFF}, L 0.81960785, A 0.48235294, B 0.5686275, alpha 1.0, hue 0.29005373, saturation 0.4924121, and chroma 0.1411665.
     * It can be represented as a packed float with the constant {@code -0x1.22f7a2p126F}.
     * <pre>
     * <font style='background-color: #ECD95D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECD95D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECD95D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECD95D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECD95D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECD95D'>&nbsp;@&nbsp;</font><font style='background-color: #ECD95D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECD95D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECD95D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.22f7a2p126F;
    static { NAMED.put("bright saffron yellow", -0x1.22f7a2p126F); LIST.add(-0x1.22f7a2p126F); }

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
     * This color constant "deep yellow lime" has RGBA8888 code {@code 8DBC13FF}, L 0.6392157, A 0.44313726, B 0.5764706, alpha 1.0, hue 0.35177422, saturation 0.79521924, and chroma 0.18984535.
     * It can be represented as a packed float with the constant {@code -0x1.26e346p126F}.
     * <pre>
     * <font style='background-color: #8DBC13;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DBC13; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DBC13;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8DBC13'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8DBC13'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8DBC13'>&nbsp;@&nbsp;</font><font style='background-color: #8DBC13; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8DBC13;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8DBC13; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.26e346p126F;
    static { NAMED.put("deep yellow lime", -0x1.26e346p126F); LIST.add(-0x1.26e346p126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code B9EE4CFF}, L 0.8235294, A 0.44313726, B 0.5764706, alpha 1.0, hue 0.35177422, saturation 0.58577716, and chroma 0.18984535.
     * It can be represented as a packed float with the constant {@code -0x1.26e3a4p126F}.
     * <pre>
     * <font style='background-color: #B9EE4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9EE4C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9EE4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B9EE4C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B9EE4C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B9EE4C'>&nbsp;@&nbsp;</font><font style='background-color: #B9EE4C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9EE4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9EE4C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.26e3a4p126F;
    static { NAMED.put("bright yellow lime", -0x1.26e3a4p126F); LIST.add(-0x1.26e3a4p126F); }

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
     * This color constant "deep green lime" has RGBA8888 code {@code 56BB18FF}, L 0.6, A 0.41960785, B 0.57254905, alpha 1.0, hue 0.3831485, saturation 0.75640154, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.24d732p126F}.
     * <pre>
     * <font style='background-color: #56BB18;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56BB18; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56BB18;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #56BB18'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #56BB18'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #56BB18'>&nbsp;@&nbsp;</font><font style='background-color: #56BB18; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56BB18;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56BB18; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.24d732p126F;
    static { NAMED.put("deep green lime", -0x1.24d732p126F); LIST.add(-0x1.24d732p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code 82EE4DFF}, L 0.78039217, A 0.41960785, B 0.57254905, alpha 1.0, hue 0.3831485, saturation 0.5495248, and chroma 0.21572971.
     * It can be represented as a packed float with the constant {@code -0x1.24d78ep126F}.
     * <pre>
     * <font style='background-color: #82EE4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82EE4D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82EE4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #82EE4D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #82EE4D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #82EE4D'>&nbsp;@&nbsp;</font><font style='background-color: #82EE4D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82EE4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82EE4D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.24d78ep126F;
    static { NAMED.put("bright green lime", -0x1.24d78ep126F); LIST.add(-0x1.24d78ep126F); }

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
     * This color constant "deep blue cyan" has RGBA8888 code {@code 009DC5FF}, L 0.5372549, A 0.4509804, B 0.45882353, alpha 1.0, hue 0.6112048, saturation 0.89561015, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.eae712p125F}.
     * <pre>
     * <font style='background-color: #009DC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #009DC5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #009DC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #009DC5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #009DC5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #009DC5'>&nbsp;@&nbsp;</font><font style='background-color: #009DC5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #009DC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #009DC5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.eae712p125F;
    static { NAMED.put("deep blue cyan", -0x1.eae712p125F); LIST.add(-0x1.eae712p125F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 3CCBF5FF}, L 0.69411767, A 0.44705883, B 0.45490196, alpha 1.0, hue 0.61230373, saturation 0.76695627, and chroma 0.13854803.
     * It can be represented as a packed float with the constant {@code -0x1.e8e562p125F}.
     * <pre>
     * <font style='background-color: #3CCBF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3CCBF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3CCBF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3CCBF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3CCBF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3CCBF5'>&nbsp;@&nbsp;</font><font style='background-color: #3CCBF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3CCBF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3CCBF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.e8e562p125F;
    static { NAMED.put("bright blue cyan", -0x1.e8e562p125F); LIST.add(-0x1.e8e562p125F); }

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
     * This color constant "deep blue" has RGBA8888 code {@code 0020C5FF}, L 0.2784314, A 0.4862745, B 0.37254903, alpha 1.0, hue 0.7329173, saturation 0.7911518, and chroma 0.25537437.
     * It can be represented as a packed float with the constant {@code -0x1.bef88ep125F}.
     * <pre>
     * <font style='background-color: #0020C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0020C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0020C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0020C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0020C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0020C5'>&nbsp;@&nbsp;</font><font style='background-color: #0020C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0020C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0020C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE = -0x1.bef88ep125F;
    static { NAMED.put("deep blue", -0x1.bef88ep125F); LIST.add(-0x1.bef88ep125F); }

    /**
     * This color constant "bright blue" has RGBA8888 code {@code 144AF3FF}, L 0.38039216, A 0.48235294, B 0.37254903, alpha 1.0, hue 0.7280896, saturation 0.81921524, and chroma 0.25632858.
     * It can be represented as a packed float with the constant {@code -0x1.bef6c2p125F}.
     * <pre>
     * <font style='background-color: #144AF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #144AF3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #144AF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #144AF3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #144AF3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #144AF3'>&nbsp;@&nbsp;</font><font style='background-color: #144AF3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #144AF3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #144AF3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE = -0x1.bef6c2p125F;
    static { NAMED.put("bright blue", -0x1.bef6c2p125F); LIST.add(-0x1.bef6c2p125F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 332BBCFF}, L 0.29803923, A 0.5058824, B 0.3882353, alpha 1.0, hue 0.7583591, saturation 0.6372928, and chroma 0.22296442.
     * It can be represented as a packed float with the constant {@code -0x1.c70298p125F}.
     * <pre>
     * <font style='background-color: #332BBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #332BBC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #332BBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #332BBC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #332BBC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #332BBC'>&nbsp;@&nbsp;</font><font style='background-color: #332BBC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #332BBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #332BBC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.c70298p125F;
    static { NAMED.put("deep violet blue", -0x1.c70298p125F); LIST.add(-0x1.c70298p125F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code 4B4FE8FF}, L 0.40392157, A 0.50980395, B 0.3882353, alpha 1.0, hue 0.7639283, saturation 0.6778117, and chroma 0.22351125.
     * It can be represented as a packed float with the constant {@code -0x1.c704cep125F}.
     * <pre>
     * <font style='background-color: #4B4FE8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B4FE8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B4FE8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B4FE8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B4FE8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B4FE8'>&nbsp;@&nbsp;</font><font style='background-color: #4B4FE8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B4FE8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B4FE8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.c704cep125F;
    static { NAMED.put("bright violet blue", -0x1.c704cep125F); LIST.add(-0x1.c704cep125F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 4E1FC6FF}, L 0.3137255, A 0.53333336, B 0.38431373, alpha 1.0, hue 0.79464006, saturation 0.8034525, and chroma 0.23984502.
     * It can be represented as a packed float with the constant {@code -0x1.c510ap125F}.
     * <pre>
     * <font style='background-color: #4E1FC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E1FC6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E1FC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E1FC6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E1FC6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E1FC6'>&nbsp;@&nbsp;</font><font style='background-color: #4E1FC6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E1FC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E1FC6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.c510ap125F;
    static { NAMED.put("deep blue violet", -0x1.c510ap125F); LIST.add(-0x1.c510ap125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code 6D4BF8FF}, L 0.43529412, A 0.5294118, B 0.38431373, alpha 1.0, hue 0.78962016, saturation 0.8133882, and chroma 0.2378005.
     * It can be represented as a packed float with the constant {@code -0x1.c50edep125F}.
     * <pre>
     * <font style='background-color: #6D4BF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D4BF8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D4BF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D4BF8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D4BF8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D4BF8'>&nbsp;@&nbsp;</font><font style='background-color: #6D4BF8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D4BF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D4BF8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.c50edep125F;
    static { NAMED.put("bright blue violet", -0x1.c50edep125F); LIST.add(-0x1.c50edep125F); }

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
     * This color constant "deep purple violet" has RGBA8888 code {@code 7720C9FF}, L 0.36078432, A 0.5647059, B 0.39607844, alpha 1.0, hue 0.8386436, saturation 0.80699754, and chroma 0.24388267.
     * It can be represented as a packed float with the constant {@code -0x1.cb20b8p125F}.
     * <pre>
     * <font style='background-color: #7720C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7720C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7720C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7720C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7720C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7720C9'>&nbsp;@&nbsp;</font><font style='background-color: #7720C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7720C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7720C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.cb20b8p125F;
    static { NAMED.put("deep purple violet", -0x1.cb20b8p125F); LIST.add(-0x1.cb20b8p125F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code 9D4AF9FF}, L 0.48235294, A 0.5647059, B 0.39607844, alpha 1.0, hue 0.8386436, saturation 0.80699754, and chroma 0.24388267.
     * It can be represented as a packed float with the constant {@code -0x1.cb20f6p125F}.
     * <pre>
     * <font style='background-color: #9D4AF9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D4AF9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D4AF9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D4AF9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D4AF9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D4AF9'>&nbsp;@&nbsp;</font><font style='background-color: #9D4AF9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D4AF9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D4AF9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.cb20f6p125F;
    static { NAMED.put("bright purple violet", -0x1.cb20f6p125F); LIST.add(-0x1.cb20f6p125F); }

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
     * This color constant "deep magenta purple" has RGBA8888 code {@code 9A30C0FF}, L 0.4117647, A 0.5803922, B 0.41960785, alpha 1.0, hue 0.875, saturation 0.65763605, and chroma 0.22649515.
     * It can be represented as a packed float with the constant {@code -0x1.d728d2p125F}.
     * <pre>
     * <font style='background-color: #9A30C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A30C0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A30C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A30C0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A30C0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A30C0'>&nbsp;@&nbsp;</font><font style='background-color: #9A30C0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A30C0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A30C0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.d728d2p125F;
    static { NAMED.put("deep magenta purple", -0x1.d728d2p125F); LIST.add(-0x1.d728d2p125F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code C658EFFF}, L 0.54509807, A 0.5803922, B 0.41960785, alpha 1.0, hue 0.875, saturation 0.6223333, and chroma 0.22649515.
     * It can be represented as a packed float with the constant {@code -0x1.d72916p125F}.
     * <pre>
     * <font style='background-color: #C658EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C658EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C658EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C658EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C658EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C658EF'>&nbsp;@&nbsp;</font><font style='background-color: #C658EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C658EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C658EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.d72916p125F;
    static { NAMED.put("bright magenta purple", -0x1.d72916p125F); LIST.add(-0x1.d72916p125F); }

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
     * This color constant "deep magenta" has RGBA8888 code {@code B334BEFF}, L 0.44705883, A 0.5921569, B 0.43137255, alpha 1.0, hue 0.898114, saturation 0.63566107, and chroma 0.22890759.
     * It can be represented as a packed float with the constant {@code -0x1.dd2ee4p125F}.
     * <pre>
     * <font style='background-color: #B334BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B334BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B334BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B334BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B334BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B334BE'>&nbsp;@&nbsp;</font><font style='background-color: #B334BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B334BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B334BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA = -0x1.dd2ee4p125F;
    static { NAMED.put("deep magenta", -0x1.dd2ee4p125F); LIST.add(-0x1.dd2ee4p125F); }

    /**
     * This color constant "bright magenta" has RGBA8888 code {@code E25BECFF}, L 0.58431375, A 0.5921569, B 0.43137255, alpha 1.0, hue 0.898114, saturation 0.5867829, and chroma 0.22890759.
     * It can be represented as a packed float with the constant {@code -0x1.dd2f2ap125F}.
     * <pre>
     * <font style='background-color: #E25BEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E25BEC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E25BEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E25BEC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E25BEC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E25BEC'>&nbsp;@&nbsp;</font><font style='background-color: #E25BEC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E25BEC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E25BEC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA = -0x1.dd2f2ap125F;
    static { NAMED.put("bright magenta", -0x1.dd2f2ap125F); LIST.add(-0x1.dd2f2ap125F); }

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
     * This color constant "some red brown" has RGBA8888 code {@code D77251FF}, L 0.54901963, A 0.54901963, B 0.5411765, alpha 1.0, hue 0.111204796, saturation 0.32241967, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.151918p126F}.
     * <pre>
     * <font style='background-color: #D77251;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D77251; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D77251;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D77251'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D77251'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D77251'>&nbsp;@&nbsp;</font><font style='background-color: #D77251; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D77251;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D77251; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_RED_BROWN = -0x1.151918p126F;
    static { NAMED.put("some red brown", -0x1.151918p126F); LIST.add(-0x1.151918p126F); }

    /**
     * This color constant "some orange brown" has RGBA8888 code {@code DD7345FF}, L 0.5568628, A 0.54901963, B 0.54901963, alpha 1.0, hue 0.125, saturation 0.45351472, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.19191cp126F}.
     * <pre>
     * <font style='background-color: #DD7345;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD7345; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD7345;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD7345'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD7345'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD7345'>&nbsp;@&nbsp;</font><font style='background-color: #DD7345; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD7345;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD7345; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_ORANGE_BROWN = -0x1.19191cp126F;
    static { NAMED.put("some orange brown", -0x1.19191cp126F); LIST.add(-0x1.19191cp126F); }

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
     * This color constant "more brown orange" has RGBA8888 code {@code D28C65FF}, L 0.6, A 0.5294118, B 0.5372549, alpha 1.0, hue 0.14362669, saturation 0.22978139, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130f32p126F}.
     * <pre>
     * <font style='background-color: #D28C65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28C65; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28C65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D28C65'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D28C65'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D28C65'>&nbsp;@&nbsp;</font><font style='background-color: #D28C65; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28C65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28C65; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BROWN_ORANGE = -0x1.130f32p126F;
    static { NAMED.put("more brown orange", -0x1.130f32p126F); LIST.add(-0x1.130f32p126F); }

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
