package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;

import java.util.Comparator;

/**
 * A palette of predefined colors as packed Oklab floats, the kind {@link ColorTools} works with. This uses a geometric
 * palette, Yam, that is designed to be as consistent as possible in its support for hues while keeping lots of
 * grayscale, desaturated, and mid-saturation colors, and to have a coherent naming system.
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
public class YamPalette {
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<>(256);
    public static final FloatArray LIST = new FloatArray(256);

    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922, alpha 0.0, hue 0.6666667, and saturation 0.0.
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
     * This color constant "pure black" has RGBA8888 code {@code 000000FF}, L 0.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.6666667, and saturation 0.0.
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
     * This color constant "almost black" has RGBA8888 code {@code 050403FF}, L 0.0627451, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.27689993, and saturation 0.0041905893.
     * It can be represented as a packed float with the constant {@code -0x1.00fe2p126F}.
     * <pre>
     * <font style='background-color: #050403;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #050403; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #050403;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #050403'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #050403'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #050403'>&nbsp;@&nbsp;</font><font style='background-color: #050403; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #050403;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #050403; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_BLACK = -0x1.00fe2p126F;
    static { NAMED.put("almost black", -0x1.00fe2p126F); LIST.add(-0x1.00fe2p126F); }

    /**
     * This color constant "lead black" has RGBA8888 code {@code 0F0D0CFF}, L 0.14117648, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.050162267, and saturation 0.010861374.
     * It can be represented as a packed float with the constant {@code -0x1.010048p126F}.
     * <pre>
     * <font style='background-color: #0F0D0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F0D0C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F0D0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F0D0C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F0D0C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F0D0C'>&nbsp;@&nbsp;</font><font style='background-color: #0F0D0C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F0D0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F0D0C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLACK = -0x1.010048p126F;
    static { NAMED.put("lead black", -0x1.010048p126F); LIST.add(-0x1.010048p126F); }

    /**
     * This color constant "black lead" has RGBA8888 code {@code 1A1817FF}, L 0.20784314, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.5496696, and saturation 0.013484627.
     * It can be represented as a packed float with the constant {@code -0x1.fefe6ap125F}.
     * <pre>
     * <font style='background-color: #1A1817;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A1817; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A1817;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1A1817'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1A1817'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1A1817'>&nbsp;@&nbsp;</font><font style='background-color: #1A1817; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A1817;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A1817; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LEAD = -0x1.fefe6ap125F;
    static { NAMED.put("black lead", -0x1.fefe6ap125F); LIST.add(-0x1.fefe6ap125F); }

    /**
     * This color constant "pure lead" has RGBA8888 code {@code 292625FF}, L 0.28235295, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.77983654, and saturation 0.008793369.
     * It can be represented as a packed float with the constant {@code -0x1.ff009p125F}.
     * <pre>
     * <font style='background-color: #292625;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #292625; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #292625;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #292625'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #292625'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #292625'>&nbsp;@&nbsp;</font><font style='background-color: #292625; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #292625;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #292625; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_LEAD = -0x1.ff009p125F;
    static { NAMED.put("pure lead", -0x1.ff009p125F); LIST.add(-0x1.ff009p125F); }

    /**
     * This color constant "gray lead" has RGBA8888 code {@code 393534FF}, L 0.3529412, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.7800549, and saturation 0.009812221.
     * It can be represented as a packed float with the constant {@code -0x1.ff00b4p125F}.
     * <pre>
     * <font style='background-color: #393534;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #393534; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #393534;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #393534'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #393534'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #393534'>&nbsp;@&nbsp;</font><font style='background-color: #393534; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #393534;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #393534; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LEAD = -0x1.ff00b4p125F;
    static { NAMED.put("gray lead", -0x1.ff00b4p125F); LIST.add(-0x1.ff00b4p125F); }

    /**
     * This color constant "lead gray" has RGBA8888 code {@code 4A4645FF}, L 0.42745098, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.7803023, and saturation 0.010777533.
     * It can be represented as a packed float with the constant {@code -0x1.ff00dap125F}.
     * <pre>
     * <font style='background-color: #4A4645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A4645; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A4645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A4645'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A4645'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A4645'>&nbsp;@&nbsp;</font><font style='background-color: #4A4645; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A4645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A4645; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GRAY = -0x1.ff00dap125F;
    static { NAMED.put("lead gray", -0x1.ff00dap125F); LIST.add(-0x1.ff00dap125F); }

    /**
     * This color constant "pure gray" has RGBA8888 code {@code 5C5957FF}, L 0.49803922, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.27659082, and saturation 0.011876017.
     * It can be represented as a packed float with the constant {@code -0x1.00fefep126F}.
     * <pre>
     * <font style='background-color: #5C5957;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C5957; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C5957;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C5957'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C5957'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C5957'>&nbsp;@&nbsp;</font><font style='background-color: #5C5957; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C5957;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C5957; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_GRAY = -0x1.00fefep126F;
    static { NAMED.put("pure gray", -0x1.00fefep126F); LIST.add(-0x1.00fefep126F); }

    /**
     * This color constant "silver gray" has RGBA8888 code {@code 726E6CFF}, L 0.57254905, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.2763408, and saturation 0.01275593.
     * It can be represented as a packed float with the constant {@code -0x1.00ff24p126F}.
     * <pre>
     * <font style='background-color: #726E6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #726E6C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #726E6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #726E6C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #726E6C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #726E6C'>&nbsp;@&nbsp;</font><font style='background-color: #726E6C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #726E6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #726E6C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GRAY = -0x1.00ff24p126F;
    static { NAMED.put("silver gray", -0x1.00ff24p126F); LIST.add(-0x1.00ff24p126F); }

    /**
     * This color constant "gray silver" has RGBA8888 code {@code 878381FF}, L 0.6431373, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.27610058, and saturation 0.013541877.
     * It can be represented as a packed float with the constant {@code -0x1.00ff48p126F}.
     * <pre>
     * <font style='background-color: #878381;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #878381; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #878381;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #878381'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #878381'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #878381'>&nbsp;@&nbsp;</font><font style='background-color: #878381; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #878381;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #878381; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SILVER = -0x1.00ff48p126F;
    static { NAMED.put("gray silver", -0x1.00ff48p126F); LIST.add(-0x1.00ff48p126F); }

    /**
     * This color constant "pure silver" has RGBA8888 code {@code 9D9997FF}, L 0.7137255, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.5490258, and saturation 0.024529815.
     * It can be represented as a packed float with the constant {@code -0x1.feff6cp125F}.
     * <pre>
     * <font style='background-color: #9D9997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D9997; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D9997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D9997'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D9997'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D9997'>&nbsp;@&nbsp;</font><font style='background-color: #9D9997; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D9997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D9997; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_SILVER = -0x1.feff6cp125F;
    static { NAMED.put("pure silver", -0x1.feff6cp125F); LIST.add(-0x1.feff6cp125F); }

    /**
     * This color constant "white silver" has RGBA8888 code {@code B6B2B0FF}, L 0.7921569, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.5489175, and saturation 0.025798142.
     * It can be represented as a packed float with the constant {@code -0x1.feff94p125F}.
     * <pre>
     * <font style='background-color: #B6B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B2B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #B6B2B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B2B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SILVER = -0x1.feff94p125F;
    static { NAMED.put("white silver", -0x1.feff94p125F); LIST.add(-0x1.feff94p125F); }

    /**
     * This color constant "silver white" has RGBA8888 code {@code CFCAC8FF}, L 0.85882354, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.781859, and saturation 0.015115082.
     * It can be represented as a packed float with the constant {@code -0x1.ff01b6p125F}.
     * <pre>
     * <font style='background-color: #CFCAC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFCAC8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFCAC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFCAC8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFCAC8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFCAC8'>&nbsp;@&nbsp;</font><font style='background-color: #CFCAC8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFCAC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFCAC8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_WHITE = -0x1.ff01b6p125F;
    static { NAMED.put("silver white", -0x1.ff01b6p125F); LIST.add(-0x1.ff01b6p125F); }

    /**
     * This color constant "almost white" has RGBA8888 code {@code E9E4E2FF}, L 0.93333334, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.7821396, and saturation 0.015728652.
     * It can be represented as a packed float with the constant {@code -0x1.ff01dcp125F}.
     * <pre>
     * <font style='background-color: #E9E4E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9E4E2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9E4E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9E4E2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9E4E2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9E4E2'>&nbsp;@&nbsp;</font><font style='background-color: #E9E4E2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9E4E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9E4E2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_WHITE = -0x1.ff01dcp125F;
    static { NAMED.put("almost white", -0x1.ff01dcp125F); LIST.add(-0x1.ff01dcp125F); }

    /**
     * This color constant "pure white" has RGBA8888 code {@code FBFFFFFF}, L 1.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.5, and saturation 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.fefffep125F}.
     * <pre>
     * <font style='background-color: #FBFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FBFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FBFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FBFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FBFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_WHITE = -0x1.fefffep125F;
    static { NAMED.put("pure white", -0x1.fefffep125F); LIST.add(-0x1.fefffep125F); }

    /**
     * This color constant "darker gray red" has RGBA8888 code {@code 2B1C1BFF}, L 0.2509804, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.026073912, and saturation 0.053416535.
     * It can be represented as a packed float with the constant {@code -0x1.03048p126F}.
     * <pre>
     * <font style='background-color: #2B1C1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B1C1B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B1C1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2B1C1B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2B1C1B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2B1C1B'>&nbsp;@&nbsp;</font><font style='background-color: #2B1C1B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B1C1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B1C1B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_RED = -0x1.03048p126F;
    static { NAMED.put("darker gray red", -0x1.03048p126F); LIST.add(-0x1.03048p126F); }

    /**
     * This color constant "dark gray red" has RGBA8888 code {@code 574241FF}, L 0.42745098, A 0.5137255, B 0.5019608, alpha 1.0, hue 0.97550875, and saturation 0.07318434.
     * It can be represented as a packed float with the constant {@code -0x1.0106dap126F}.
     * <pre>
     * <font style='background-color: #574241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #574241; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #574241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #574241'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #574241'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #574241'>&nbsp;@&nbsp;</font><font style='background-color: #574241; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #574241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #574241; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_RED = -0x1.0106dap126F;
    static { NAMED.put("dark gray red", -0x1.0106dap126F); LIST.add(-0x1.0106dap126F); }

    /**
     * This color constant "light gray red" has RGBA8888 code {@code 8C7472FF}, L 0.6117647, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.027372777, and saturation 0.085623175.
     * It can be represented as a packed float with the constant {@code -0x1.030538p126F}.
     * <pre>
     * <font style='background-color: #8C7472;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C7472; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C7472;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C7472'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C7472'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C7472'>&nbsp;@&nbsp;</font><font style='background-color: #8C7472; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C7472;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C7472; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_RED = -0x1.030538p126F;
    static { NAMED.put("light gray red", -0x1.030538p126F); LIST.add(-0x1.030538p126F); }

    /**
     * This color constant "lighter gray red" has RGBA8888 code {@code C9ADAAFF}, L 0.79607844, A 0.5137255, B 0.5058824, alpha 1.0, hue 0.0124387955, and saturation 0.11803645.
     * It can be represented as a packed float with the constant {@code -0x1.030796p126F}.
     * <pre>
     * <font style='background-color: #C9ADAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9ADAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9ADAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9ADAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9ADAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9ADAA'>&nbsp;@&nbsp;</font><font style='background-color: #C9ADAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9ADAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9ADAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_RED = -0x1.030796p126F;
    static { NAMED.put("lighter gray red", -0x1.030796p126F); LIST.add(-0x1.030796p126F); }

    /**
     * This color constant "darker gray brown" has RGBA8888 code {@code 2D231CFF}, L 0.27450982, A 0.5058824, B 0.50980395, alpha 1.0, hue 0.070724174, and saturation 0.06376135.
     * It can be represented as a packed float with the constant {@code -0x1.05028cp126F}.
     * <pre>
     * <font style='background-color: #2D231C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D231C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D231C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D231C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D231C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D231C'>&nbsp;@&nbsp;</font><font style='background-color: #2D231C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D231C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D231C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_BROWN = -0x1.05028cp126F;
    static { NAMED.put("darker gray brown", -0x1.05028cp126F); LIST.add(-0x1.05028cp126F); }

    /**
     * This color constant "dark gray brown" has RGBA8888 code {@code 594D44FF}, L 0.45882353, A 0.5019608, B 0.50980395, alpha 1.0, hue 0.10030061, and saturation 0.067493975.
     * It can be represented as a packed float with the constant {@code -0x1.0500eap126F}.
     * <pre>
     * <font style='background-color: #594D44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #594D44; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #594D44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #594D44'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #594D44'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #594D44'>&nbsp;@&nbsp;</font><font style='background-color: #594D44; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #594D44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #594D44; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_BROWN = -0x1.0500eap126F;
    static { NAMED.put("dark gray brown", -0x1.0500eap126F); LIST.add(-0x1.0500eap126F); }

    /**
     * This color constant "light gray brown" has RGBA8888 code {@code 8F8176FF}, L 0.6431373, A 0.5019608, B 0.50980395, alpha 1.0, hue 0.10041337, and saturation 0.079796225.
     * It can be represented as a packed float with the constant {@code -0x1.050148p126F}.
     * <pre>
     * <font style='background-color: #8F8176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8176; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8176'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8176'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8176'>&nbsp;@&nbsp;</font><font style='background-color: #8F8176; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8176; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_BROWN = -0x1.050148p126F;
    static { NAMED.put("light gray brown", -0x1.050148p126F); LIST.add(-0x1.050148p126F); }

    /**
     * This color constant "lighter gray brown" has RGBA8888 code {@code CCBCB0FF}, L 0.827451, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.089260906, and saturation 0.05862099.
     * It can be represented as a packed float with the constant {@code -0x1.0301a6p126F}.
     * <pre>
     * <font style='background-color: #CCBCB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCBCB0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCBCB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CCBCB0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CCBCB0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CCBCB0'>&nbsp;@&nbsp;</font><font style='background-color: #CCBCB0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCBCB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCBCB0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_BROWN = -0x1.0301a6p126F;
    static { NAMED.put("lighter gray brown", -0x1.0301a6p126F); LIST.add(-0x1.0301a6p126F); }

    /**
     * This color constant "darker gray orange" has RGBA8888 code {@code 2D241DFF}, L 0.2784314, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.0890199, and saturation 0.033918202.
     * It can be represented as a packed float with the constant {@code -0x1.03008ep126F}.
     * <pre>
     * <font style='background-color: #2D241D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D241D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D241D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D241D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D241D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D241D'>&nbsp;@&nbsp;</font><font style='background-color: #2D241D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D241D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D241D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_ORANGE = -0x1.03008ep126F;
    static { NAMED.put("darker gray orange", -0x1.03008ep126F); LIST.add(-0x1.03008ep126F); }

    /**
     * This color constant "dark gray orange" has RGBA8888 code {@code 5B4F46FF}, L 0.46666667, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.089107215, and saturation 0.043917686.
     * It can be represented as a packed float with the constant {@code -0x1.0300eep126F}.
     * <pre>
     * <font style='background-color: #5B4F46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B4F46; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B4F46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B4F46'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B4F46'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B4F46'>&nbsp;@&nbsp;</font><font style='background-color: #5B4F46; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B4F46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B4F46; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_ORANGE = -0x1.0300eep126F;
    static { NAMED.put("dark gray orange", -0x1.0300eep126F); LIST.add(-0x1.0300eep126F); }

    /**
     * This color constant "light gray orange" has RGBA8888 code {@code 908277FF}, L 0.64705884, A 0.5019608, B 0.50980395, alpha 1.0, hue 0.100415945, and saturation 0.08003804.
     * It can be represented as a packed float with the constant {@code -0x1.05014ap126F}.
     * <pre>
     * <font style='background-color: #908277;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #908277; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #908277;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #908277'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #908277'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #908277'>&nbsp;@&nbsp;</font><font style='background-color: #908277; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #908277;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #908277; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_ORANGE = -0x1.05014ap126F;
    static { NAMED.put("light gray orange", -0x1.05014ap126F); LIST.add(-0x1.05014ap126F); }

    /**
     * This color constant "lighter gray orange" has RGBA8888 code {@code CFBEB2FF}, L 0.83137256, A 0.5058824, B 0.50980395, alpha 1.0, hue 0.070663355, and saturation 0.11159426.
     * It can be represented as a packed float with the constant {@code -0x1.0503a8p126F}.
     * <pre>
     * <font style='background-color: #CFBEB2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFBEB2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFBEB2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFBEB2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFBEB2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFBEB2'>&nbsp;@&nbsp;</font><font style='background-color: #CFBEB2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFBEB2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFBEB2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_ORANGE = -0x1.0503a8p126F;
    static { NAMED.put("lighter gray orange", -0x1.0503a8p126F); LIST.add(-0x1.0503a8p126F); }

    /**
     * This color constant "darker gray saffron" has RGBA8888 code {@code 2E2820FF}, L 0.29411766, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.089025915, and saturation 0.03485678.
     * It can be represented as a packed float with the constant {@code -0x1.030096p126F}.
     * <pre>
     * <font style='background-color: #2E2820;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2E2820; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2E2820;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2E2820'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2E2820'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2E2820'>&nbsp;@&nbsp;</font><font style='background-color: #2E2820; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2E2820;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2E2820; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_SAFFRON = -0x1.030096p126F;
    static { NAMED.put("darker gray saffron", -0x1.030096p126F); LIST.add(-0x1.030096p126F); }

    /**
     * This color constant "dark gray saffron" has RGBA8888 code {@code 5C554BFF}, L 0.4862745, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.08911615, and saturation 0.04483545.
     * It can be represented as a packed float with the constant {@code -0x1.0300f8p126F}.
     * <pre>
     * <font style='background-color: #5C554B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C554B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C554B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C554B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C554B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C554B'>&nbsp;@&nbsp;</font><font style='background-color: #5C554B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C554B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C554B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_SAFFRON = -0x1.0300f8p126F;
    static { NAMED.put("dark gray saffron", -0x1.0300f8p126F); LIST.add(-0x1.0300f8p126F); }

    /**
     * This color constant "light gray saffron" has RGBA8888 code {@code 938B7EFF}, L 0.67058825, A 0.49803922, B 0.50980395, alpha 1.0, hue 0.14849351, and saturation 0.062447757.
     * It can be represented as a packed float with the constant {@code -0x1.04ff56p126F}.
     * <pre>
     * <font style='background-color: #938B7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #938B7E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #938B7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #938B7E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #938B7E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #938B7E'>&nbsp;@&nbsp;</font><font style='background-color: #938B7E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #938B7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #938B7E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_SAFFRON = -0x1.04ff56p126F;
    static { NAMED.put("light gray saffron", -0x1.04ff56p126F); LIST.add(-0x1.04ff56p126F); }

    /**
     * This color constant "lighter gray saffron" has RGBA8888 code {@code D3CABCFF}, L 0.85882354, A 0.49803922, B 0.5058824, alpha 1.0, hue 0.17264348, and saturation 0.03933221.
     * It can be represented as a packed float with the constant {@code -0x1.02ffb6p126F}.
     * <pre>
     * <font style='background-color: #D3CABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3CABC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3CABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3CABC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3CABC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3CABC'>&nbsp;@&nbsp;</font><font style='background-color: #D3CABC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3CABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3CABC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_SAFFRON = -0x1.02ffb6p126F;
    static { NAMED.put("lighter gray saffron", -0x1.02ffb6p126F); LIST.add(-0x1.02ffb6p126F); }

    /**
     * This color constant "darker gray yellow" has RGBA8888 code {@code 323425FF}, L 0.3372549, A 0.49019608, B 0.50980395, alpha 1.0, hue 0.2770083, and saturation 0.0485415.
     * It can be represented as a packed float with the constant {@code -0x1.04faacp126F}.
     * <pre>
     * <font style='background-color: #323425;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #323425; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #323425;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #323425'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #323425'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #323425'>&nbsp;@&nbsp;</font><font style='background-color: #323425; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #323425;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #323425; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_YELLOW = -0x1.04faacp126F;
    static { NAMED.put("darker gray yellow", -0x1.04faacp126F); LIST.add(-0x1.04faacp126F); }

    /**
     * This color constant "dark gray yellow" has RGBA8888 code {@code 626451FF}, L 0.5294118, A 0.49411765, B 0.5137255, alpha 1.0, hue 0.18760537, and saturation 0.073999286.
     * It can be represented as a packed float with the constant {@code -0x1.06fd0ep126F}.
     * <pre>
     * <font style='background-color: #626451;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626451; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626451;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #626451'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #626451'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #626451'>&nbsp;@&nbsp;</font><font style='background-color: #626451; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626451;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626451; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_YELLOW = -0x1.06fd0ep126F;
    static { NAMED.put("dark gray yellow", -0x1.06fd0ep126F); LIST.add(-0x1.06fd0ep126F); }

    /**
     * This color constant "light gray yellow" has RGBA8888 code {@code 9A9D87FF}, L 0.7176471, A 0.49019608, B 0.5137255, alpha 1.0, hue 0.23562619, and saturation 0.09251851.
     * It can be represented as a packed float with the constant {@code -0x1.06fb6ep126F}.
     * <pre>
     * <font style='background-color: #9A9D87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A9D87; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A9D87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A9D87'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A9D87'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A9D87'>&nbsp;@&nbsp;</font><font style='background-color: #9A9D87; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A9D87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A9D87; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_YELLOW = -0x1.06fb6ep126F;
    static { NAMED.put("light gray yellow", -0x1.06fb6ep126F); LIST.add(-0x1.06fb6ep126F); }

    /**
     * This color constant "lighter gray yellow" has RGBA8888 code {@code D9DCC4FF}, L 0.8980392, A 0.49411765, B 0.50980395, alpha 1.0, hue 0.21835345, and saturation 0.07182968.
     * It can be represented as a packed float with the constant {@code -0x1.04fdcap126F}.
     * <pre>
     * <font style='background-color: #D9DCC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9DCC4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9DCC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9DCC4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9DCC4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9DCC4'>&nbsp;@&nbsp;</font><font style='background-color: #D9DCC4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9DCC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9DCC4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_YELLOW = -0x1.04fdcap126F;
    static { NAMED.put("lighter gray yellow", -0x1.04fdcap126F); LIST.add(-0x1.04fdcap126F); }

    /**
     * This color constant "darker gray lime" has RGBA8888 code {@code 293523FF}, L 0.32941177, A 0.48235294, B 0.5137255, alpha 1.0, hue 0.31731647, and saturation 0.07162973.
     * It can be represented as a packed float with the constant {@code -0x1.06f6a8p126F}.
     * <pre>
     * <font style='background-color: #293523;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #293523; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #293523;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #293523'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #293523'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #293523'>&nbsp;@&nbsp;</font><font style='background-color: #293523; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #293523;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #293523; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_LIME = -0x1.06f6a8p126F;
    static { NAMED.put("darker gray lime", -0x1.06f6a8p126F); LIST.add(-0x1.06f6a8p126F); }

    /**
     * This color constant "dark gray lime" has RGBA8888 code {@code 56644FFF}, L 0.5176471, A 0.4862745, B 0.50980395, alpha 1.0, hue 0.32905647, and saturation 0.065377295.
     * It can be represented as a packed float with the constant {@code -0x1.04f908p126F}.
     * <pre>
     * <font style='background-color: #56644F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56644F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56644F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #56644F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #56644F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #56644F'>&nbsp;@&nbsp;</font><font style='background-color: #56644F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56644F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56644F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_LIME = -0x1.04f908p126F;
    static { NAMED.put("dark gray lime", -0x1.04f908p126F); LIST.add(-0x1.04f908p126F); }

    /**
     * This color constant "light gray lime" has RGBA8888 code {@code 8C9C83FF}, L 0.7019608, A 0.4862745, B 0.5137255, alpha 1.0, hue 0.27737972, and saturation 0.09786707.
     * It can be represented as a packed float with the constant {@code -0x1.06f966p126F}.
     * <pre>
     * <font style='background-color: #8C9C83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C9C83; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C9C83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C9C83'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C9C83'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C9C83'>&nbsp;@&nbsp;</font><font style='background-color: #8C9C83; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C9C83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C9C83; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_LIME = -0x1.06f966p126F;
    static { NAMED.put("light gray lime", -0x1.06f966p126F); LIST.add(-0x1.06f966p126F); }

    /**
     * This color constant "lighter gray lime" has RGBA8888 code {@code CADCC0FF}, L 0.8901961, A 0.4862745, B 0.5137255, alpha 1.0, hue 0.2774698, and saturation 0.110213935.
     * It can be represented as a packed float with the constant {@code -0x1.06f9c6p126F}.
     * <pre>
     * <font style='background-color: #CADCC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CADCC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CADCC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CADCC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CADCC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CADCC0'>&nbsp;@&nbsp;</font><font style='background-color: #CADCC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CADCC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CADCC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_LIME = -0x1.06f9c6p126F;
    static { NAMED.put("lighter gray lime", -0x1.06f9c6p126F); LIST.add(-0x1.06f9c6p126F); }

    /**
     * This color constant "darker gray green" has RGBA8888 code {@code 1D2F21FF}, L 0.29803923, A 0.47843137, B 0.50980395, alpha 1.0, hue 0.39672932, and saturation 0.0919783.
     * It can be represented as a packed float with the constant {@code -0x1.04f498p126F}.
     * <pre>
     * <font style='background-color: #1D2F21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D2F21; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D2F21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D2F21'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D2F21'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D2F21'>&nbsp;@&nbsp;</font><font style='background-color: #1D2F21; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D2F21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D2F21; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_GREEN = -0x1.04f498p126F;
    static { NAMED.put("darker gray green", -0x1.04f498p126F); LIST.add(-0x1.04f498p126F); }

    /**
     * This color constant "dark gray green" has RGBA8888 code {@code 405645FF}, L 0.45882353, A 0.47843137, B 0.5058824, alpha 1.0, hue 0.42338073, and saturation 0.11630453.
     * It can be represented as a packed float with the constant {@code -0x1.02f4eap126F}.
     * <pre>
     * <font style='background-color: #405645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #405645; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #405645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #405645'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #405645'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #405645'>&nbsp;@&nbsp;</font><font style='background-color: #405645; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #405645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #405645; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_GREEN = -0x1.02f4eap126F;
    static { NAMED.put("dark gray green", -0x1.02f4eap126F); LIST.add(-0x1.02f4eap126F); }

    /**
     * This color constant "light gray green" has RGBA8888 code {@code 6A836FFF}, L 0.6156863, A 0.47843137, B 0.50980395, alpha 1.0, hue 0.3876081, and saturation 0.12271017.
     * It can be represented as a packed float with the constant {@code -0x1.04f53ap126F}.
     * <pre>
     * <font style='background-color: #6A836F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A836F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A836F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A836F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A836F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A836F'>&nbsp;@&nbsp;</font><font style='background-color: #6A836F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A836F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A836F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_GREEN = -0x1.04f53ap126F;
    static { NAMED.put("light gray green", -0x1.04f53ap126F); LIST.add(-0x1.04f53ap126F); }

    /**
     * This color constant "lighter gray green" has RGBA8888 code {@code 9BB6A0FF}, L 0.7764706, A 0.48235294, B 0.50980395, alpha 1.0, hue 0.36454338, and saturation 0.10676628.
     * It can be represented as a packed float with the constant {@code -0x1.04f78cp126F}.
     * <pre>
     * <font style='background-color: #9BB6A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BB6A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BB6A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9BB6A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9BB6A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9BB6A0'>&nbsp;@&nbsp;</font><font style='background-color: #9BB6A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BB6A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BB6A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_GREEN = -0x1.04f78cp126F;
    static { NAMED.put("lighter gray green", -0x1.04f78cp126F); LIST.add(-0x1.04f78cp126F); }

    /**
     * This color constant "darker gray cyan" has RGBA8888 code {@code 1F3335FF}, L 0.32156864, A 0.48235294, B 0.49019608, alpha 1.0, hue 0.5239544, and saturation 0.12805912.
     * It can be represented as a packed float with the constant {@code -0x1.faf6a4p125F}.
     * <pre>
     * <font style='background-color: #1F3335;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F3335; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F3335;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1F3335'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1F3335'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1F3335'>&nbsp;@&nbsp;</font><font style='background-color: #1F3335; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F3335;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F3335; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_CYAN = -0x1.faf6a4p125F;
    static { NAMED.put("darker gray cyan", -0x1.faf6a4p125F); LIST.add(-0x1.faf6a4p125F); }

    /**
     * This color constant "dark gray cyan" has RGBA8888 code {@code 4A6264FF}, L 0.50980395, A 0.48235294, B 0.49411765, alpha 1.0, hue 0.5048996, and saturation 0.12303221.
     * It can be represented as a packed float with the constant {@code -0x1.fcf704p125F}.
     * <pre>
     * <font style='background-color: #4A6264;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A6264; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A6264;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A6264'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A6264'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A6264'>&nbsp;@&nbsp;</font><font style='background-color: #4A6264; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A6264;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A6264; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_CYAN = -0x1.fcf704p125F;
    static { NAMED.put("dark gray cyan", -0x1.fcf704p125F); LIST.add(-0x1.fcf704p125F); }

    /**
     * This color constant "light gray cyan" has RGBA8888 code {@code 809B9EFF}, L 0.69803923, A 0.4862745, B 0.49019608, alpha 1.0, hue 0.53523105, and saturation 0.14698184.
     * It can be represented as a packed float with the constant {@code -0x1.faf964p125F}.
     * <pre>
     * <font style='background-color: #809B9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #809B9E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #809B9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #809B9E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #809B9E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #809B9E'>&nbsp;@&nbsp;</font><font style='background-color: #809B9E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #809B9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #809B9E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_CYAN = -0x1.faf964p125F;
    static { NAMED.put("light gray cyan", -0x1.faf964p125F); LIST.add(-0x1.faf964p125F); }

    /**
     * This color constant "lighter gray cyan" has RGBA8888 code {@code BDDCDFFF}, L 0.8862745, A 0.48235294, B 0.49019608, alpha 1.0, hue 0.524482, and saturation 0.18924212.
     * It can be represented as a packed float with the constant {@code -0x1.faf7c4p125F}.
     * <pre>
     * <font style='background-color: #BDDCDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDDCDF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDDCDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BDDCDF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BDDCDF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BDDCDF'>&nbsp;@&nbsp;</font><font style='background-color: #BDDCDF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDDCDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDDCDF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_CYAN = -0x1.faf7c4p125F;
    static { NAMED.put("lighter gray cyan", -0x1.faf7c4p125F); LIST.add(-0x1.faf7c4p125F); }

    /**
     * This color constant "darker gray blue" has RGBA8888 code {@code 0F1B2BFF}, L 0.21960784, A 0.49019608, B 0.47843137, alpha 1.0, hue 0.5764271, and saturation 0.13103506.
     * It can be represented as a packed float with the constant {@code -0x1.f4fa7p125F}.
     * <pre>
     * <font style='background-color: #0F1B2B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F1B2B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F1B2B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F1B2B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F1B2B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F1B2B'>&nbsp;@&nbsp;</font><font style='background-color: #0F1B2B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F1B2B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F1B2B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_BLUE = -0x1.f4fa7p125F;
    static { NAMED.put("darker gray blue", -0x1.f4fa7p125F); LIST.add(-0x1.f4fa7p125F); }

    /**
     * This color constant "dark gray blue" has RGBA8888 code {@code 334459FF}, L 0.40784314, A 0.49019608, B 0.4745098, alpha 1.0, hue 0.5851689, and saturation 0.18760586.
     * It can be represented as a packed float with the constant {@code -0x1.f2fadp125F}.
     * <pre>
     * <font style='background-color: #334459;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #334459; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #334459;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #334459'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #334459'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #334459'>&nbsp;@&nbsp;</font><font style='background-color: #334459; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #334459;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #334459; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_BLUE = -0x1.f2fadp125F;
    static { NAMED.put("dark gray blue", -0x1.f2fadp125F); LIST.add(-0x1.f2fadp125F); }

    /**
     * This color constant "light gray blue" has RGBA8888 code {@code 637690FF}, L 0.5921569, A 0.49411765, B 0.4745098, alpha 1.0, hue 0.59892124, and saturation 0.20137686.
     * It can be represented as a packed float with the constant {@code -0x1.f2fd2ep125F}.
     * <pre>
     * <font style='background-color: #637690;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #637690; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #637690;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #637690'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #637690'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #637690'>&nbsp;@&nbsp;</font><font style='background-color: #637690; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #637690;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #637690; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_BLUE = -0x1.f2fd2ep125F;
    static { NAMED.put("light gray blue", -0x1.f2fd2ep125F); LIST.add(-0x1.f2fd2ep125F); }

    /**
     * This color constant "lighter gray blue" has RGBA8888 code {@code 9BB1CEFF}, L 0.7764706, A 0.49411765, B 0.4745098, alpha 1.0, hue 0.59860307, and saturation 0.23016638.
     * It can be represented as a packed float with the constant {@code -0x1.f2fd8cp125F}.
     * <pre>
     * <font style='background-color: #9BB1CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BB1CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BB1CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9BB1CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9BB1CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9BB1CE'>&nbsp;@&nbsp;</font><font style='background-color: #9BB1CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BB1CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BB1CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_BLUE = -0x1.f2fd8cp125F;
    static { NAMED.put("lighter gray blue", -0x1.f2fd8cp125F); LIST.add(-0x1.f2fd8cp125F); }

    /**
     * This color constant "darker gray violet" has RGBA8888 code {@code 1D182CFF}, L 0.22745098, A 0.5058824, B 0.4745098, alpha 1.0, hue 0.67215544, and saturation 0.08808917.
     * It can be represented as a packed float with the constant {@code -0x1.f30274p125F}.
     * <pre>
     * <font style='background-color: #1D182C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D182C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D182C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D182C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D182C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D182C'>&nbsp;@&nbsp;</font><font style='background-color: #1D182C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D182C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D182C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_VIOLET = -0x1.f30274p125F;
    static { NAMED.put("darker gray violet", -0x1.f30274p125F); LIST.add(-0x1.f30274p125F); }

    /**
     * This color constant "dark gray violet" has RGBA8888 code {@code 45405AFF}, L 0.41568628, A 0.5058824, B 0.47843137, alpha 1.0, hue 0.6701341, and saturation 0.098555624.
     * It can be represented as a packed float with the constant {@code -0x1.f502d4p125F}.
     * <pre>
     * <font style='background-color: #45405A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #45405A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #45405A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #45405A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #45405A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #45405A'>&nbsp;@&nbsp;</font><font style='background-color: #45405A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #45405A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #45405A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_VIOLET = -0x1.f502d4p125F;
    static { NAMED.put("dark gray violet", -0x1.f502d4p125F); LIST.add(-0x1.f502d4p125F); }

    /**
     * This color constant "light gray violet" has RGBA8888 code {@code 777090FF}, L 0.59607846, A 0.50980395, B 0.4745098, alpha 1.0, hue 0.689485, and saturation 0.14344445.
     * It can be represented as a packed float with the constant {@code -0x1.f3053p125F}.
     * <pre>
     * <font style='background-color: #777090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #777090; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #777090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #777090'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #777090'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #777090'>&nbsp;@&nbsp;</font><font style='background-color: #777090; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #777090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #777090; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_VIOLET = -0x1.f3053p125F;
    static { NAMED.put("light gray violet", -0x1.f3053p125F); LIST.add(-0x1.f3053p125F); }

    /**
     * This color constant "lighter gray violet" has RGBA8888 code {@code B2AACFFF}, L 0.78039217, A 0.50980395, B 0.4745098, alpha 1.0, hue 0.6880621, and saturation 0.16358823.
     * It can be represented as a packed float with the constant {@code -0x1.f3058ep125F}.
     * <pre>
     * <font style='background-color: #B2AACF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2AACF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2AACF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2AACF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2AACF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2AACF'>&nbsp;@&nbsp;</font><font style='background-color: #B2AACF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2AACF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2AACF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_VIOLET = -0x1.f3058ep125F;
    static { NAMED.put("lighter gray violet", -0x1.f3058ep125F); LIST.add(-0x1.f3058ep125F); }

    /**
     * This color constant "darker gray purple" has RGBA8888 code {@code 251B2EFF}, L 0.2509804, A 0.5137255, B 0.47843137, alpha 1.0, hue 0.7355196, and saturation 0.086499214.
     * It can be represented as a packed float with the constant {@code -0x1.f5068p125F}.
     * <pre>
     * <font style='background-color: #251B2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #251B2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #251B2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #251B2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #251B2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #251B2E'>&nbsp;@&nbsp;</font><font style='background-color: #251B2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #251B2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #251B2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_PURPLE = -0x1.f5068p125F;
    static { NAMED.put("darker gray purple", -0x1.f5068p125F); LIST.add(-0x1.f5068p125F); }

    /**
     * This color constant "dark gray purple" has RGBA8888 code {@code 4F435BFF}, L 0.43137255, A 0.50980395, B 0.47843137, alpha 1.0, hue 0.7026629, and saturation 0.1055831.
     * It can be represented as a packed float with the constant {@code -0x1.f504dcp125F}.
     * <pre>
     * <font style='background-color: #4F435B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F435B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F435B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F435B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F435B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F435B'>&nbsp;@&nbsp;</font><font style='background-color: #4F435B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F435B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F435B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_PURPLE = -0x1.f504dcp125F;
    static { NAMED.put("dark gray purple", -0x1.f504dcp125F); LIST.add(-0x1.f504dcp125F); }

    /**
     * This color constant "light gray purple" has RGBA8888 code {@code 837491FF}, L 0.6156863, A 0.5137255, B 0.47843137, alpha 1.0, hue 0.73009306, and saturation 0.13183218.
     * It can be represented as a packed float with the constant {@code -0x1.f5073ap125F}.
     * <pre>
     * <font style='background-color: #837491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #837491; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #837491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #837491'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #837491'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #837491'>&nbsp;@&nbsp;</font><font style='background-color: #837491; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #837491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #837491; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_PURPLE = -0x1.f5073ap125F;
    static { NAMED.put("light gray purple", -0x1.f5073ap125F); LIST.add(-0x1.f5073ap125F); }

    /**
     * This color constant "lighter gray purple" has RGBA8888 code {@code BFAFD0FF}, L 0.8, A 0.5137255, B 0.47843137, alpha 1.0, hue 0.72924864, and saturation 0.14986074.
     * It can be represented as a packed float with the constant {@code -0x1.f50798p125F}.
     * <pre>
     * <font style='background-color: #BFAFD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFAFD0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFAFD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFAFD0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFAFD0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFAFD0'>&nbsp;@&nbsp;</font><font style='background-color: #BFAFD0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFAFD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFAFD0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_PURPLE = -0x1.f50798p125F;
    static { NAMED.put("lighter gray purple", -0x1.f50798p125F); LIST.add(-0x1.f50798p125F); }

    /**
     * This color constant "darker gray magenta" has RGBA8888 code {@code 2C1D2EFF}, L 0.26666668, A 0.5176471, B 0.48235294, alpha 1.0, hue 0.7807247, and saturation 0.07982489.
     * It can be represented as a packed float with the constant {@code -0x1.f70888p125F}.
     * <pre>
     * <font style='background-color: #2C1D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2C1D2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2C1D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2C1D2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2C1D2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2C1D2E'>&nbsp;@&nbsp;</font><font style='background-color: #2C1D2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2C1D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2C1D2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_MAGENTA = -0x1.f70888p125F;
    static { NAMED.put("darker gray magenta", -0x1.f70888p125F); LIST.add(-0x1.f70888p125F); }

    /**
     * This color constant "dark gray magenta" has RGBA8888 code {@code 5A475CFF}, L 0.45490196, A 0.5137255, B 0.4862745, alpha 1.0, hue 0.7797514, and saturation 0.07915273.
     * It can be represented as a packed float with the constant {@code -0x1.f906e8p125F}.
     * <pre>
     * <font style='background-color: #5A475C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A475C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A475C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A475C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A475C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A475C'>&nbsp;@&nbsp;</font><font style='background-color: #5A475C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A475C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A475C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_MAGENTA = -0x1.f906e8p125F;
    static { NAMED.put("dark gray magenta", -0x1.f906e8p125F); LIST.add(-0x1.f906e8p125F); }

    /**
     * This color constant "light gray magenta" has RGBA8888 code {@code 907A93FF}, L 0.6392157, A 0.5137255, B 0.4862745, alpha 1.0, hue 0.77958703, and saturation 0.0934273.
     * It can be represented as a packed float with the constant {@code -0x1.f90746p125F}.
     * <pre>
     * <font style='background-color: #907A93;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #907A93; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #907A93;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #907A93'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #907A93'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #907A93'>&nbsp;@&nbsp;</font><font style='background-color: #907A93; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #907A93;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #907A93; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_MAGENTA = -0x1.f90746p125F;
    static { NAMED.put("light gray magenta", -0x1.f90746p125F); LIST.add(-0x1.f90746p125F); }

    /**
     * This color constant "lighter gray magenta" has RGBA8888 code {@code CFB5D2FF}, L 0.8235294, A 0.5176471, B 0.48235294, alpha 1.0, hue 0.7795868, and saturation 0.13634074.
     * It can be represented as a packed float with the constant {@code -0x1.f709a4p125F}.
     * <pre>
     * <font style='background-color: #CFB5D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB5D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB5D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFB5D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFB5D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFB5D2'>&nbsp;@&nbsp;</font><font style='background-color: #CFB5D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB5D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB5D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_MAGENTA = -0x1.f709a4p125F;
    static { NAMED.put("lighter gray magenta", -0x1.f709a4p125F); LIST.add(-0x1.f709a4p125F); }

    /**
     * This color constant "drab brown red" has RGBA8888 code {@code 662F28FF}, L 0.4, A 0.5372549, B 0.5176471, alpha 1.0, hue 0.009751135, and saturation 0.22029808.
     * It can be represented as a packed float with the constant {@code -0x1.0912ccp126F}.
     * <pre>
     * <font style='background-color: #662F28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #662F28; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #662F28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #662F28'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #662F28'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #662F28'>&nbsp;@&nbsp;</font><font style='background-color: #662F28; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #662F28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #662F28; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN_RED = -0x1.0912ccp126F;
    static { NAMED.put("drab brown red", -0x1.0912ccp126F); LIST.add(-0x1.0912ccp126F); }

    /**
     * This color constant "dull brown red" has RGBA8888 code {@code 98584EFF}, L 0.56078434, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.018694628, and saturation 0.2512784.
     * It can be represented as a packed float with the constant {@code -0x1.09111ep126F}.
     * <pre>
     * <font style='background-color: #98584E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98584E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98584E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98584E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98584E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98584E'>&nbsp;@&nbsp;</font><font style='background-color: #98584E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98584E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98584E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BROWN_RED = -0x1.09111ep126F;
    static { NAMED.put("dull brown red", -0x1.09111ep126F); LIST.add(-0x1.09111ep126F); }

    /**
     * This color constant "pale brown red" has RGBA8888 code {@code CE8579FF}, L 0.7137255, A 0.5372549, B 0.52156866, alpha 1.0, hue 0.02353279, and saturation 0.33258557.
     * It can be represented as a packed float with the constant {@code -0x1.0b136cp126F}.
     * <pre>
     * <font style='background-color: #CE8579;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8579; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8579;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE8579'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE8579'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE8579'>&nbsp;@&nbsp;</font><font style='background-color: #CE8579; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8579;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8579; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN_RED = -0x1.0b136cp126F;
    static { NAMED.put("pale brown red", -0x1.0b136cp126F); LIST.add(-0x1.0b136cp126F); }

    /**
     * This color constant "drab red brown" has RGBA8888 code {@code 663B28FF}, L 0.42745098, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.057452876, and saturation 0.22877114.
     * It can be represented as a packed float with the constant {@code -0x1.0d0adap126F}.
     * <pre>
     * <font style='background-color: #663B28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #663B28; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #663B28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #663B28'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #663B28'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #663B28'>&nbsp;@&nbsp;</font><font style='background-color: #663B28; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #663B28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #663B28; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED_BROWN = -0x1.0d0adap126F;
    static { NAMED.put("drab red brown", -0x1.0d0adap126F); LIST.add(-0x1.0d0adap126F); }

    /**
     * This color constant "dull red brown" has RGBA8888 code {@code 986650FF}, L 0.5882353, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.05714932, and saturation 0.2676781.
     * It can be represented as a packed float with the constant {@code -0x1.0d0b2cp126F}.
     * <pre>
     * <font style='background-color: #986650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #986650; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #986650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #986650'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #986650'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #986650'>&nbsp;@&nbsp;</font><font style='background-color: #986650; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #986650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #986650; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_RED_BROWN = -0x1.0d0b2cp126F;
    static { NAMED.put("dull red brown", -0x1.0d0b2cp126F); LIST.add(-0x1.0d0b2cp126F); }

    /**
     * This color constant "pale red brown" has RGBA8888 code {@code D0977EFF}, L 0.7490196, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.05712344, and saturation 0.30260265.
     * It can be represented as a packed float with the constant {@code -0x1.0d0b7ep126F}.
     * <pre>
     * <font style='background-color: #D0977E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0977E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0977E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0977E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0977E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0977E'>&nbsp;@&nbsp;</font><font style='background-color: #D0977E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0977E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0977E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED_BROWN = -0x1.0d0b7ep126F;
    static { NAMED.put("pale red brown", -0x1.0d0b7ep126F); LIST.add(-0x1.0d0b7ep126F); }

    /**
     * This color constant "drab orange brown" has RGBA8888 code {@code 68412CFF}, L 0.44313726, A 0.5176471, B 0.5254902, alpha 1.0, hue 0.06583361, and saturation 0.21991037.
     * It can be represented as a packed float with the constant {@code -0x1.0d08e2p126F}.
     * <pre>
     * <font style='background-color: #68412C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68412C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68412C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68412C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68412C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68412C'>&nbsp;@&nbsp;</font><font style='background-color: #68412C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68412C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68412C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE_BROWN = -0x1.0d08e2p126F;
    static { NAMED.put("drab orange brown", -0x1.0d08e2p126F); LIST.add(-0x1.0d08e2p126F); }

    /**
     * This color constant "dull orange brown" has RGBA8888 code {@code 9A6C54FF}, L 0.6039216, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.057142265, and saturation 0.2712521.
     * It can be represented as a packed float with the constant {@code -0x1.0d0b34p126F}.
     * <pre>
     * <font style='background-color: #9A6C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A6C54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A6C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A6C54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A6C54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A6C54'>&nbsp;@&nbsp;</font><font style='background-color: #9A6C54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A6C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A6C54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_ORANGE_BROWN = -0x1.0d0b34p126F;
    static { NAMED.put("dull orange brown", -0x1.0d0b34p126F); LIST.add(-0x1.0d0b34p126F); }

    /**
     * This color constant "pale orange brown" has RGBA8888 code {@code D3A085FF}, L 0.77254903, A 0.5176471, B 0.5254902, alpha 1.0, hue 0.0652901, and saturation 0.2891755.
     * It can be represented as a packed float with the constant {@code -0x1.0d098ap126F}.
     * <pre>
     * <font style='background-color: #D3A085;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3A085; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3A085;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3A085'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3A085'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3A085'>&nbsp;@&nbsp;</font><font style='background-color: #D3A085; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3A085;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3A085; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_BROWN = -0x1.0d098ap126F;
    static { NAMED.put("pale orange brown", -0x1.0d098ap126F); LIST.add(-0x1.0d098ap126F); }

    /**
     * This color constant "drab brown orange" has RGBA8888 code {@code 68452FFF}, L 0.4509804, A 0.5176471, B 0.5254902, alpha 1.0, hue 0.06579162, and saturation 0.22173499.
     * It can be represented as a packed float with the constant {@code -0x1.0d08e6p126F}.
     * <pre>
     * <font style='background-color: #68452F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68452F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68452F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68452F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68452F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68452F'>&nbsp;@&nbsp;</font><font style='background-color: #68452F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68452F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68452F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN_ORANGE = -0x1.0d08e6p126F;
    static { NAMED.put("drab brown orange", -0x1.0d08e6p126F); LIST.add(-0x1.0d08e6p126F); }

    /**
     * This color constant "dull brown orange" has RGBA8888 code {@code 9A7258FF}, L 0.6156863, A 0.5176471, B 0.5254902, alpha 1.0, hue 0.06538664, and saturation 0.25806162.
     * It can be represented as a packed float with the constant {@code -0x1.0d093ap126F}.
     * <pre>
     * <font style='background-color: #9A7258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A7258; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A7258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A7258'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A7258'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A7258'>&nbsp;@&nbsp;</font><font style='background-color: #9A7258; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A7258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A7258; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BROWN_ORANGE = -0x1.0d093ap126F;
    static { NAMED.put("dull brown orange", -0x1.0d093ap126F); LIST.add(-0x1.0d093ap126F); }

    /**
     * This color constant "pale brown orange" has RGBA8888 code {@code D0A386FF}, L 0.77254903, A 0.5176471, B 0.5254902, alpha 1.0, hue 0.0652901, and saturation 0.2891755.
     * It can be represented as a packed float with the constant {@code -0x1.0d098ap126F}.
     * <pre>
     * <font style='background-color: #D0A386;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0A386; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0A386;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0A386'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0A386'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0A386'>&nbsp;@&nbsp;</font><font style='background-color: #D0A386; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0A386;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0A386; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN_ORANGE = -0x1.0d098ap126F;
    static { NAMED.put("pale brown orange", -0x1.0d098ap126F); LIST.add(-0x1.0d098ap126F); }

    /**
     * This color constant "drab saffron orange" has RGBA8888 code {@code 6A4A2DFF}, L 0.46666667, A 0.5137255, B 0.5254902, alpha 1.0, hue 0.07508457, and saturation 0.21182999.
     * It can be represented as a packed float with the constant {@code -0x1.0d06eep126F}.
     * <pre>
     * <font style='background-color: #6A4A2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A4A2D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A4A2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A4A2D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A4A2D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A4A2D'>&nbsp;@&nbsp;</font><font style='background-color: #6A4A2D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A4A2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A4A2D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON_ORANGE = -0x1.0d06eep126F;
    static { NAMED.put("drab saffron orange", -0x1.0d06eep126F); LIST.add(-0x1.0d06eep126F); }

    /**
     * This color constant "dull saffron orange" has RGBA8888 code {@code 9C7756FF}, L 0.627451, A 0.5137255, B 0.5294118, alpha 1.0, hue 0.07956978, and saturation 0.27356818.
     * It can be represented as a packed float with the constant {@code -0x1.0f074p126F}.
     * <pre>
     * <font style='background-color: #9C7756;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C7756; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C7756;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C7756'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C7756'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C7756'>&nbsp;@&nbsp;</font><font style='background-color: #9C7756; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C7756;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C7756; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_SAFFRON_ORANGE = -0x1.0f074p126F;
    static { NAMED.put("dull saffron orange", -0x1.0f074p126F); LIST.add(-0x1.0f074p126F); }

    /**
     * This color constant "pale saffron orange" has RGBA8888 code {@code D3AA85FF}, L 0.7882353, A 0.5137255, B 0.5294118, alpha 1.0, hue 0.07935389, and saturation 0.30554438.
     * It can be represented as a packed float with the constant {@code -0x1.0f0792p126F}.
     * <pre>
     * <font style='background-color: #D3AA85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3AA85; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3AA85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3AA85'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3AA85'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3AA85'>&nbsp;@&nbsp;</font><font style='background-color: #D3AA85; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3AA85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3AA85; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON_ORANGE = -0x1.0f0792p126F;
    static { NAMED.put("pale saffron orange", -0x1.0f0792p126F); LIST.add(-0x1.0f0792p126F); }

    /**
     * This color constant "drab orange saffron" has RGBA8888 code {@code 6C5131FF}, L 0.4862745, A 0.5058824, B 0.5294118, alpha 1.0, hue 0.100711875, and saturation 0.2147117.
     * It can be represented as a packed float with the constant {@code -0x1.0f02f8p126F}.
     * <pre>
     * <font style='background-color: #6C5131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C5131; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C5131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C5131'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C5131'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C5131'>&nbsp;@&nbsp;</font><font style='background-color: #6C5131; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C5131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C5131; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE_SAFFRON = -0x1.0f02f8p126F;
    static { NAMED.put("drab orange saffron", -0x1.0f02f8p126F); LIST.add(-0x1.0f02f8p126F); }

    /**
     * This color constant "dull orange saffron" has RGBA8888 code {@code 9E7F5BFF}, L 0.64705884, A 0.50980395, B 0.5294118, alpha 1.0, hue 0.08922794, and saturation 0.2608438.
     * It can be represented as a packed float with the constant {@code -0x1.0f054ap126F}.
     * <pre>
     * <font style='background-color: #9E7F5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E7F5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E7F5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E7F5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E7F5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E7F5B'>&nbsp;@&nbsp;</font><font style='background-color: #9E7F5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E7F5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E7F5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_ORANGE_SAFFRON = -0x1.0f054ap126F;
    static { NAMED.put("dull orange saffron", -0x1.0f054ap126F); LIST.add(-0x1.0f054ap126F); }

    /**
     * This color constant "pale orange saffron" has RGBA8888 code {@code D7B58DFF}, L 0.8156863, A 0.5058824, B 0.5294118, alpha 1.0, hue 0.10020096, and saturation 0.27183086.
     * It can be represented as a packed float with the constant {@code -0x1.0f03ap126F}.
     * <pre>
     * <font style='background-color: #D7B58D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7B58D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7B58D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7B58D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7B58D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7B58D'>&nbsp;@&nbsp;</font><font style='background-color: #D7B58D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7B58D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7B58D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_SAFFRON = -0x1.0f03ap126F;
    static { NAMED.put("pale orange saffron", -0x1.0f03ap126F); LIST.add(-0x1.0f03ap126F); }

    /**
     * This color constant "drab yellow saffron" has RGBA8888 code {@code 6A5A36FF}, L 0.5058824, A 0.49803922, B 0.5294118, alpha 1.0, hue 0.12780502, and saturation 0.18785982.
     * It can be represented as a packed float with the constant {@code -0x1.0eff02p126F}.
     * <pre>
     * <font style='background-color: #6A5A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A5A36; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A5A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A5A36'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A5A36'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A5A36'>&nbsp;@&nbsp;</font><font style='background-color: #6A5A36; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A5A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A5A36; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW_SAFFRON = -0x1.0eff02p126F;
    static { NAMED.put("drab yellow saffron", -0x1.0eff02p126F); LIST.add(-0x1.0eff02p126F); }

    /**
     * This color constant "dull yellow saffron" has RGBA8888 code {@code 9E8B63FF}, L 0.6745098, A 0.5019608, B 0.5294118, alpha 1.0, hue 0.11304743, and saturation 0.2306461.
     * It can be represented as a packed float with the constant {@code -0x1.0f0158p126F}.
     * <pre>
     * <font style='background-color: #9E8B63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E8B63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E8B63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E8B63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E8B63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E8B63'>&nbsp;@&nbsp;</font><font style='background-color: #9E8B63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E8B63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E8B63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_YELLOW_SAFFRON = -0x1.0f0158p126F;
    static { NAMED.put("dull yellow saffron", -0x1.0f0158p126F); LIST.add(-0x1.0f0158p126F); }

    /**
     * This color constant "pale yellow saffron" has RGBA8888 code {@code D7C296FF}, L 0.8392157, A 0.5019608, B 0.5294118, alpha 1.0, hue 0.11313975, and saturation 0.25530845.
     * It can be represented as a packed float with the constant {@code -0x1.0f01acp126F}.
     * <pre>
     * <font style='background-color: #D7C296;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7C296; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7C296;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7C296'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7C296'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7C296'>&nbsp;@&nbsp;</font><font style='background-color: #D7C296; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7C296;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7C296; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_SAFFRON = -0x1.0f01acp126F;
    static { NAMED.put("pale yellow saffron", -0x1.0f01acp126F); LIST.add(-0x1.0f01acp126F); }

    /**
     * This color constant "drab saffron yellow" has RGBA8888 code {@code 6E6C3DFF}, L 0.5529412, A 0.4862745, B 0.53333336, alpha 1.0, hue 0.1801388, and saturation 0.18898413.
     * It can be represented as a packed float with the constant {@code -0x1.10f91ap126F}.
     * <pre>
     * <font style='background-color: #6E6C3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E6C3D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E6C3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E6C3D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E6C3D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E6C3D'>&nbsp;@&nbsp;</font><font style='background-color: #6E6C3D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E6C3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E6C3D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON_YELLOW = -0x1.10f91ap126F;
    static { NAMED.put("drab saffron yellow", -0x1.10f91ap126F); LIST.add(-0x1.10f91ap126F); }

    /**
     * This color constant "dull saffron yellow" has RGBA8888 code {@code A19F6AFF}, L 0.7176471, A 0.4862745, B 0.53333336, alpha 1.0, hue 0.18195865, and saturation 0.21137118.
     * It can be represented as a packed float with the constant {@code -0x1.10f96ep126F}.
     * <pre>
     * <font style='background-color: #A19F6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A19F6A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A19F6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A19F6A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A19F6A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A19F6A'>&nbsp;@&nbsp;</font><font style='background-color: #A19F6A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A19F6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A19F6A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_SAFFRON_YELLOW = -0x1.10f96ep126F;
    static { NAMED.put("dull saffron yellow", -0x1.10f96ep126F); LIST.add(-0x1.10f96ep126F); }

    /**
     * This color constant "pale saffron yellow" has RGBA8888 code {@code DAD89EFF}, L 0.8862745, A 0.49019608, B 0.53333336, alpha 1.0, hue 0.16191328, and saturation 0.23227566.
     * It can be represented as a packed float with the constant {@code -0x1.10fbc4p126F}.
     * <pre>
     * <font style='background-color: #DAD89E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAD89E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAD89E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAD89E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAD89E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAD89E'>&nbsp;@&nbsp;</font><font style='background-color: #DAD89E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAD89E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAD89E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON_YELLOW = -0x1.10fbc4p126F;
    static { NAMED.put("pale saffron yellow", -0x1.10fbc4p126F); LIST.add(-0x1.10fbc4p126F); }

    /**
     * This color constant "drab lime yellow" has RGBA8888 code {@code 67753BFF}, L 0.5686275, A 0.4745098, B 0.5372549, alpha 1.0, hue 0.22425237, and saturation 0.2310923.
     * It can be represented as a packed float with the constant {@code -0x1.12f322p126F}.
     * <pre>
     * <font style='background-color: #67753B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #67753B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #67753B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #67753B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #67753B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #67753B'>&nbsp;@&nbsp;</font><font style='background-color: #67753B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #67753B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #67753B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME_YELLOW = -0x1.12f322p126F;
    static { NAMED.put("drab lime yellow", -0x1.12f322p126F); LIST.add(-0x1.12f322p126F); }

    /**
     * This color constant "dull lime yellow" has RGBA8888 code {@code 98A868FF}, L 0.7294118, A 0.47843137, B 0.5372549, alpha 1.0, hue 0.20967491, and saturation 0.24986196.
     * It can be represented as a packed float with the constant {@code -0x1.12f574p126F}.
     * <pre>
     * <font style='background-color: #98A868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98A868; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98A868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98A868'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98A868'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98A868'>&nbsp;@&nbsp;</font><font style='background-color: #98A868; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98A868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98A868; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_LIME_YELLOW = -0x1.12f574p126F;
    static { NAMED.put("dull lime yellow", -0x1.12f574p126F); LIST.add(-0x1.12f574p126F); }

    /**
     * This color constant "pale lime yellow" has RGBA8888 code {@code D0E29CFF}, L 0.8980392, A 0.47843137, B 0.5372549, alpha 1.0, hue 0.21109581, and saturation 0.27464014.
     * It can be represented as a packed float with the constant {@code -0x1.12f5cap126F}.
     * <pre>
     * <font style='background-color: #D0E29C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0E29C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0E29C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0E29C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0E29C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0E29C'>&nbsp;@&nbsp;</font><font style='background-color: #D0E29C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0E29C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0E29C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME_YELLOW = -0x1.12f5cap126F;
    static { NAMED.put("pale lime yellow", -0x1.12f5cap126F); LIST.add(-0x1.12f5cap126F); }

    /**
     * This color constant "drab yellow lime" has RGBA8888 code {@code 59703BFF}, L 0.54509807, A 0.47058824, B 0.53333336, alpha 1.0, hue 0.25618285, and saturation 0.20997573.
     * It can be represented as a packed float with the constant {@code -0x1.10f116p126F}.
     * <pre>
     * <font style='background-color: #59703B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #59703B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #59703B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #59703B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #59703B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #59703B'>&nbsp;@&nbsp;</font><font style='background-color: #59703B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #59703B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #59703B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW_LIME = -0x1.10f116p126F;
    static { NAMED.put("drab yellow lime", -0x1.10f116p126F); LIST.add(-0x1.10f116p126F); }

    /**
     * This color constant "dull yellow lime" has RGBA8888 code {@code 8BA56AFF}, L 0.7137255, A 0.47058824, B 0.53333336, alpha 1.0, hue 0.25820112, and saturation 0.2363173.
     * It can be represented as a packed float with the constant {@code -0x1.10f16cp126F}.
     * <pre>
     * <font style='background-color: #8BA56A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BA56A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BA56A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8BA56A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8BA56A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8BA56A'>&nbsp;@&nbsp;</font><font style='background-color: #8BA56A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BA56A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BA56A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_YELLOW_LIME = -0x1.10f16cp126F;
    static { NAMED.put("dull yellow lime", -0x1.10f16cp126F); LIST.add(-0x1.10f16cp126F); }

    /**
     * This color constant "pale yellow lime" has RGBA8888 code {@code C2DF9EFF}, L 0.88235295, A 0.47058824, B 0.53333336, alpha 1.0, hue 0.25918686, and saturation 0.26106352.
     * It can be represented as a packed float with the constant {@code -0x1.10f1c2p126F}.
     * <pre>
     * <font style='background-color: #C2DF9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2DF9E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2DF9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C2DF9E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C2DF9E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C2DF9E'>&nbsp;@&nbsp;</font><font style='background-color: #C2DF9E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2DF9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2DF9E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_LIME = -0x1.10f1c2p126F;
    static { NAMED.put("pale yellow lime", -0x1.10f1c2p126F); LIST.add(-0x1.10f1c2p126F); }

    /**
     * This color constant "drab green lime" has RGBA8888 code {@code 4C6F39FF}, L 0.53333336, A 0.4627451, B 0.5294118, alpha 1.0, hue 0.31584084, and saturation 0.19647881.
     * It can be represented as a packed float with the constant {@code -0x1.0eed1p126F}.
     * <pre>
     * <font style='background-color: #4C6F39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C6F39; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C6F39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4C6F39'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4C6F39'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4C6F39'>&nbsp;@&nbsp;</font><font style='background-color: #4C6F39; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C6F39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C6F39; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN_LIME = -0x1.0eed1p126F;
    static { NAMED.put("drab green lime", -0x1.0eed1p126F); LIST.add(-0x1.0eed1p126F); }

    /**
     * This color constant "dull green lime" has RGBA8888 code {@code 7BA366FF}, L 0.69803923, A 0.4627451, B 0.53333336, alpha 1.0, hue 0.2932449, and saturation 0.24585322.
     * It can be represented as a packed float with the constant {@code -0x1.10ed64p126F}.
     * <pre>
     * <font style='background-color: #7BA366;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7BA366; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7BA366;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7BA366'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7BA366'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7BA366'>&nbsp;@&nbsp;</font><font style='background-color: #7BA366; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7BA366;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7BA366; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN_LIME = -0x1.10ed64p126F;
    static { NAMED.put("dull green lime", -0x1.10ed64p126F); LIST.add(-0x1.10ed64p126F); }

    /**
     * This color constant "pale green lime" has RGBA8888 code {@code B0DD99FF}, L 0.8666667, A 0.4627451, B 0.53333336, alpha 1.0, hue 0.29339743, and saturation 0.27234524.
     * It can be represented as a packed float with the constant {@code -0x1.10edbap126F}.
     * <pre>
     * <font style='background-color: #B0DD99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0DD99; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0DD99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0DD99'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0DD99'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0DD99'>&nbsp;@&nbsp;</font><font style='background-color: #B0DD99; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0DD99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0DD99; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN_LIME = -0x1.10edbap126F;
    static { NAMED.put("pale green lime", -0x1.10edbap126F); LIST.add(-0x1.10edbap126F); }

    /**
     * This color constant "drab lime green" has RGBA8888 code {@code 366F35FF}, L 0.5176471, A 0.4509804, B 0.53333336, alpha 1.0, hue 0.35077494, and saturation 0.25675768.
     * It can be represented as a packed float with the constant {@code -0x1.10e708p126F}.
     * <pre>
     * <font style='background-color: #366F35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #366F35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #366F35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #366F35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #366F35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #366F35'>&nbsp;@&nbsp;</font><font style='background-color: #366F35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #366F35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #366F35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME_GREEN = -0x1.10e708p126F;
    static { NAMED.put("drab lime green", -0x1.10e708p126F); LIST.add(-0x1.10e708p126F); }

    /**
     * This color constant "dull lime green" has RGBA8888 code {@code 64A362FF}, L 0.68235296, A 0.4509804, B 0.53333336, alpha 1.0, hue 0.34490848, and saturation 0.27962467.
     * It can be represented as a packed float with the constant {@code -0x1.10e75cp126F}.
     * <pre>
     * <font style='background-color: #64A362;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64A362; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64A362;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #64A362'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #64A362'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #64A362'>&nbsp;@&nbsp;</font><font style='background-color: #64A362; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64A362;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64A362; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_LIME_GREEN = -0x1.10e75cp126F;
    static { NAMED.put("dull lime green", -0x1.10e75cp126F); LIST.add(-0x1.10e75cp126F); }

    /**
     * This color constant "pale lime green" has RGBA8888 code {@code 97DD94FF}, L 0.8509804, A 0.4509804, B 0.53333336, alpha 1.0, hue 0.34215418, and saturation 0.3054825.
     * It can be represented as a packed float with the constant {@code -0x1.10e7b2p126F}.
     * <pre>
     * <font style='background-color: #97DD94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #97DD94; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #97DD94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #97DD94'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #97DD94'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #97DD94'>&nbsp;@&nbsp;</font><font style='background-color: #97DD94; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #97DD94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #97DD94; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME_GREEN = -0x1.10e7b2p126F;
    static { NAMED.put("pale lime green", -0x1.10e7b2p126F); LIST.add(-0x1.10e7b2p126F); }

    /**
     * This color constant "drab cyan green" has RGBA8888 code {@code 346C4FFF}, L 0.5137255, A 0.45882353, B 0.5137255, alpha 1.0, hue 0.42214683, and saturation 0.2528034.
     * It can be represented as a packed float with the constant {@code -0x1.06eb06p126F}.
     * <pre>
     * <font style='background-color: #346C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #346C4F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #346C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #346C4F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #346C4F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #346C4F'>&nbsp;@&nbsp;</font><font style='background-color: #346C4F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #346C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #346C4F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN_GREEN = -0x1.06eb06p126F;
    static { NAMED.put("drab cyan green", -0x1.06eb06p126F); LIST.add(-0x1.06eb06p126F); }

    /**
     * This color constant "dull cyan green" has RGBA8888 code {@code 569271FF}, L 0.6392157, A 0.45882353, B 0.5137255, alpha 1.0, hue 0.4171554, and saturation 0.26674154.
     * It can be represented as a packed float with the constant {@code -0x1.06eb46p126F}.
     * <pre>
     * <font style='background-color: #569271;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #569271; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #569271;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #569271'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #569271'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #569271'>&nbsp;@&nbsp;</font><font style='background-color: #569271; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #569271;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #569271; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_CYAN_GREEN = -0x1.06eb46p126F;
    static { NAMED.put("dull cyan green", -0x1.06eb46p126F); LIST.add(-0x1.06eb46p126F); }

    /**
     * This color constant "pale cyan green" has RGBA8888 code {@code 7CBC98FF}, L 0.7647059, A 0.45882353, B 0.5137255, alpha 1.0, hue 0.41444883, and saturation 0.28392714.
     * It can be represented as a packed float with the constant {@code -0x1.06eb86p126F}.
     * <pre>
     * <font style='background-color: #7CBC98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CBC98; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CBC98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7CBC98'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7CBC98'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7CBC98'>&nbsp;@&nbsp;</font><font style='background-color: #7CBC98; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CBC98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CBC98; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN_GREEN = -0x1.06eb86p126F;
    static { NAMED.put("pale cyan green", -0x1.06eb86p126F); LIST.add(-0x1.06eb86p126F); }

    /**
     * This color constant "drab green cyan" has RGBA8888 code {@code 2B665DFF}, L 0.49803922, A 0.4627451, B 0.49803922, alpha 1.0, hue 0.47658125, and saturation 0.2676156.
     * It can be represented as a packed float with the constant {@code -0x1.feecfep125F}.
     * <pre>
     * <font style='background-color: #2B665D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B665D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B665D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2B665D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2B665D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2B665D'>&nbsp;@&nbsp;</font><font style='background-color: #2B665D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B665D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B665D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN_CYAN = -0x1.feecfep125F;
    static { NAMED.put("drab green cyan", -0x1.feecfep125F); LIST.add(-0x1.feecfep125F); }

    /**
     * This color constant "dull green cyan" has RGBA8888 code {@code 49877DFF}, L 0.60784316, A 0.4627451, B 0.49803922, alpha 1.0, hue 0.47408706, and saturation 0.2744463.
     * It can be represented as a packed float with the constant {@code -0x1.feed36p125F}.
     * <pre>
     * <font style='background-color: #49877D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49877D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49877D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #49877D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #49877D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #49877D'>&nbsp;@&nbsp;</font><font style='background-color: #49877D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49877D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49877D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN_CYAN = -0x1.feed36p125F;
    static { NAMED.put("dull green cyan", -0x1.feed36p125F); LIST.add(-0x1.feed36p125F); }

    /**
     * This color constant "pale green cyan" has RGBA8888 code {@code 69AAA0FF}, L 0.7176471, A 0.4627451, B 0.49411765, alpha 1.0, hue 0.48585266, and saturation 0.29877767.
     * It can be represented as a packed float with the constant {@code -0x1.fced6ep125F}.
     * <pre>
     * <font style='background-color: #69AAA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #69AAA0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #69AAA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #69AAA0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #69AAA0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #69AAA0'>&nbsp;@&nbsp;</font><font style='background-color: #69AAA0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #69AAA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #69AAA0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN_CYAN = -0x1.fced6ep125F;
    static { NAMED.put("pale green cyan", -0x1.fced6ep125F); LIST.add(-0x1.fced6ep125F); }

    /**
     * This color constant "drab blue cyan" has RGBA8888 code {@code 326672FF}, L 0.50980395, A 0.47058824, B 0.48235294, alpha 1.0, hue 0.526598, and saturation 0.2826812.
     * It can be represented as a packed float with the constant {@code -0x1.f6f104p125F}.
     * <pre>
     * <font style='background-color: #326672;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #326672; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #326672;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #326672'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #326672'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #326672'>&nbsp;@&nbsp;</font><font style='background-color: #326672; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #326672;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #326672; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE_CYAN = -0x1.f6f104p125F;
    static { NAMED.put("drab blue cyan", -0x1.f6f104p125F); LIST.add(-0x1.f6f104p125F); }

    /**
     * This color constant "dull blue cyan" has RGBA8888 code {@code 5E97A5FF}, L 0.67058825, A 0.47058824, B 0.47843137, alpha 1.0, hue 0.5355814, and saturation 0.3349513.
     * It can be represented as a packed float with the constant {@code -0x1.f4f156p125F}.
     * <pre>
     * <font style='background-color: #5E97A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E97A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E97A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5E97A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5E97A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5E97A5'>&nbsp;@&nbsp;</font><font style='background-color: #5E97A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E97A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E97A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BLUE_CYAN = -0x1.f4f156p125F;
    static { NAMED.put("dull blue cyan", -0x1.f4f156p125F); LIST.add(-0x1.f4f156p125F); }

    /**
     * This color constant "pale blue cyan" has RGBA8888 code {@code 92CFDFFF}, L 0.8352941, A 0.4745098, B 0.47843137, alpha 1.0, hue 0.5422168, and saturation 0.33480644.
     * It can be represented as a packed float with the constant {@code -0x1.f4f3aap125F}.
     * <pre>
     * <font style='background-color: #92CFDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92CFDF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92CFDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #92CFDF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #92CFDF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #92CFDF'>&nbsp;@&nbsp;</font><font style='background-color: #92CFDF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92CFDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92CFDF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE_CYAN = -0x1.f4f3aap125F;
    static { NAMED.put("pale blue cyan", -0x1.f4f3aap125F); LIST.add(-0x1.f4f3aap125F); }

    /**
     * This color constant "drab cyan blue" has RGBA8888 code {@code 294B6CFF}, L 0.43137255, A 0.48235294, B 0.4627451, alpha 1.0, hue 0.5762196, and saturation 0.3131079.
     * It can be represented as a packed float with the constant {@code -0x1.ecf6dcp125F}.
     * <pre>
     * <font style='background-color: #294B6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #294B6C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #294B6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #294B6C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #294B6C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #294B6C'>&nbsp;@&nbsp;</font><font style='background-color: #294B6C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #294B6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #294B6C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN_BLUE = -0x1.ecf6dcp125F;
    static { NAMED.put("drab cyan blue", -0x1.ecf6dcp125F); LIST.add(-0x1.ecf6dcp125F); }

    /**
     * This color constant "dull cyan blue" has RGBA8888 code {@code 52789FFF}, L 0.5921569, A 0.4862745, B 0.4627451, alpha 1.0, hue 0.5865661, and saturation 0.32671395.
     * It can be represented as a packed float with the constant {@code -0x1.ecf92ep125F}.
     * <pre>
     * <font style='background-color: #52789F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #52789F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #52789F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #52789F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #52789F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #52789F'>&nbsp;@&nbsp;</font><font style='background-color: #52789F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #52789F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #52789F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_CYAN_BLUE = -0x1.ecf92ep125F;
    static { NAMED.put("dull cyan blue", -0x1.ecf92ep125F); LIST.add(-0x1.ecf92ep125F); }

    /**
     * This color constant "pale cyan blue" has RGBA8888 code {@code 81ACD7FF}, L 0.7529412, A 0.48235294, B 0.4627451, alpha 1.0, hue 0.5782524, and saturation 0.39094284.
     * It can be represented as a packed float with the constant {@code -0x1.ecf78p125F}.
     * <pre>
     * <font style='background-color: #81ACD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81ACD7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81ACD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #81ACD7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #81ACD7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #81ACD7'>&nbsp;@&nbsp;</font><font style='background-color: #81ACD7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81ACD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81ACD7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN_BLUE = -0x1.ecf78p125F;
    static { NAMED.put("pale cyan blue", -0x1.ecf78p125F); LIST.add(-0x1.ecf78p125F); }

    /**
     * This color constant "drab violet blue" has RGBA8888 code {@code 1D2565FF}, L 0.3137255, A 0.49803922, B 0.43529412, alpha 1.0, hue 0.64013815, and saturation 0.31584805.
     * It can be represented as a packed float with the constant {@code -0x1.defeap125F}.
     * <pre>
     * <font style='background-color: #1D2565;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D2565; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D2565;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D2565'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D2565'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D2565'>&nbsp;@&nbsp;</font><font style='background-color: #1D2565; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D2565;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D2565; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET_BLUE = -0x1.defeap125F;
    static { NAMED.put("drab violet blue", -0x1.defeap125F); LIST.add(-0x1.defeap125F); }

    /**
     * This color constant "dull violet blue" has RGBA8888 code {@code 3E4D97FF}, L 0.4745098, A 0.49803922, B 0.43529412, alpha 1.0, hue 0.6299733, and saturation 0.39239606.
     * It can be represented as a packed float with the constant {@code -0x1.defef2p125F}.
     * <pre>
     * <font style='background-color: #3E4D97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E4D97; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E4D97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3E4D97'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3E4D97'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3E4D97'>&nbsp;@&nbsp;</font><font style='background-color: #3E4D97; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E4D97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E4D97; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET_BLUE = -0x1.defef2p125F;
    static { NAMED.put("dull violet blue", -0x1.defef2p125F); LIST.add(-0x1.defef2p125F); }

    /**
     * This color constant "pale violet blue" has RGBA8888 code {@code 667ACEFF}, L 0.6313726, A 0.49803922, B 0.43529412, alpha 1.0, hue 0.62625694, and saturation 0.45418105.
     * It can be represented as a packed float with the constant {@code -0x1.deff42p125F}.
     * <pre>
     * <font style='background-color: #667ACE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #667ACE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #667ACE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #667ACE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #667ACE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #667ACE'>&nbsp;@&nbsp;</font><font style='background-color: #667ACE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #667ACE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #667ACE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET_BLUE = -0x1.deff42p125F;
    static { NAMED.put("pale violet blue", -0x1.deff42p125F); LIST.add(-0x1.deff42p125F); }

    /**
     * This color constant "drab blue violet" has RGBA8888 code {@code 312663FF}, L 0.3372549, A 0.5137255, B 0.44313726, alpha 1.0, hue 0.6849629, and saturation 0.25173938.
     * It can be represented as a packed float with the constant {@code -0x1.e306acp125F}.
     * <pre>
     * <font style='background-color: #312663;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #312663; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #312663;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #312663'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #312663'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #312663'>&nbsp;@&nbsp;</font><font style='background-color: #312663; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #312663;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #312663; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE_VIOLET = -0x1.e306acp125F;
    static { NAMED.put("drab blue violet", -0x1.e306acp125F); LIST.add(-0x1.e306acp125F); }

    /**
     * This color constant "dull blue violet" has RGBA8888 code {@code 584E95FF}, L 0.49803922, A 0.5176471, B 0.44313726, alpha 1.0, hue 0.687301, and saturation 0.2979556.
     * It can be represented as a packed float with the constant {@code -0x1.e308fep125F}.
     * <pre>
     * <font style='background-color: #584E95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #584E95; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #584E95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #584E95'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #584E95'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #584E95'>&nbsp;@&nbsp;</font><font style='background-color: #584E95; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #584E95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #584E95; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BLUE_VIOLET = -0x1.e308fep125F;
    static { NAMED.put("dull blue violet", -0x1.e308fep125F); LIST.add(-0x1.e308fep125F); }

    /**
     * This color constant "pale blue violet" has RGBA8888 code {@code 877ECEFF}, L 0.6627451, A 0.5137255, B 0.44313726, alpha 1.0, hue 0.6695958, and saturation 0.33074299.
     * It can be represented as a packed float with the constant {@code -0x1.e30752p125F}.
     * <pre>
     * <font style='background-color: #877ECE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #877ECE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #877ECE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #877ECE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #877ECE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #877ECE'>&nbsp;@&nbsp;</font><font style='background-color: #877ECE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #877ECE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #877ECE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE_VIOLET = -0x1.e30752p125F;
    static { NAMED.put("pale blue violet", -0x1.e30752p125F); LIST.add(-0x1.e30752p125F); }

    /**
     * This color constant "drab purple violet" has RGBA8888 code {@code 422866FF}, L 0.3647059, A 0.5294118, B 0.4509804, alpha 1.0, hue 0.73606426, and saturation 0.24445646.
     * It can be represented as a packed float with the constant {@code -0x1.e70ebap125F}.
     * <pre>
     * <font style='background-color: #422866;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #422866; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #422866;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #422866'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #422866'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #422866'>&nbsp;@&nbsp;</font><font style='background-color: #422866; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #422866;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #422866; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE_VIOLET = -0x1.e70ebap125F;
    static { NAMED.put("drab purple violet", -0x1.e70ebap125F); LIST.add(-0x1.e70ebap125F); }

    /**
     * This color constant "dull purple violet" has RGBA8888 code {@code 6C5199FF}, L 0.5254902, A 0.5254902, B 0.44705883, alpha 1.0, hue 0.7140238, and saturation 0.29666826.
     * It can be represented as a packed float with the constant {@code -0x1.e50d0cp125F}.
     * <pre>
     * <font style='background-color: #6C5199;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C5199; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C5199;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C5199'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C5199'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C5199'>&nbsp;@&nbsp;</font><font style='background-color: #6C5199; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C5199;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C5199; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_PURPLE_VIOLET = -0x1.e50d0cp125F;
    static { NAMED.put("dull purple violet", -0x1.e50d0cp125F); LIST.add(-0x1.e50d0cp125F); }

    /**
     * This color constant "pale purple violet" has RGBA8888 code {@code 9D7ED0FF}, L 0.68235296, A 0.5294118, B 0.44705883, alpha 1.0, hue 0.72255486, and saturation 0.3401101.
     * It can be represented as a packed float with the constant {@code -0x1.e50f5cp125F}.
     * <pre>
     * <font style='background-color: #9D7ED0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D7ED0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D7ED0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D7ED0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D7ED0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D7ED0'>&nbsp;@&nbsp;</font><font style='background-color: #9D7ED0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D7ED0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D7ED0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE_VIOLET = -0x1.e50f5cp125F;
    static { NAMED.put("pale purple violet", -0x1.e50f5cp125F); LIST.add(-0x1.e50f5cp125F); }

    /**
     * This color constant "drab violet purple" has RGBA8888 code {@code 492A66FF}, L 0.3764706, A 0.53333336, B 0.4509804, alpha 1.0, hue 0.7463628, and saturation 0.25394785.
     * It can be represented as a packed float with the constant {@code -0x1.e710cp125F}.
     * <pre>
     * <font style='background-color: #492A66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #492A66; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #492A66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #492A66'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #492A66'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #492A66'>&nbsp;@&nbsp;</font><font style='background-color: #492A66; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #492A66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #492A66; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET_PURPLE = -0x1.e710cp125F;
    static { NAMED.put("drab violet purple", -0x1.e710cp125F); LIST.add(-0x1.e710cp125F); }

    /**
     * This color constant "dull violet purple" has RGBA8888 code {@code 765399FF}, L 0.5411765, A 0.53333336, B 0.4509804, alpha 1.0, hue 0.74214834, and saturation 0.29341996.
     * It can be represented as a packed float with the constant {@code -0x1.e71114p125F}.
     * <pre>
     * <font style='background-color: #765399;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #765399; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #765399;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #765399'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #765399'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #765399'>&nbsp;@&nbsp;</font><font style='background-color: #765399; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #765399;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #765399; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET_PURPLE = -0x1.e71114p125F;
    static { NAMED.put("dull violet purple", -0x1.e71114p125F); LIST.add(-0x1.e71114p125F); }

    /**
     * This color constant "pale violet purple" has RGBA8888 code {@code A983D1FF}, L 0.7019608, A 0.53333336, B 0.45490196, alpha 1.0, hue 0.74782085, and saturation 0.307069.
     * It can be represented as a packed float with the constant {@code -0x1.e91166p125F}.
     * <pre>
     * <font style='background-color: #A983D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A983D1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A983D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A983D1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A983D1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A983D1'>&nbsp;@&nbsp;</font><font style='background-color: #A983D1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A983D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A983D1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET_PURPLE = -0x1.e91166p125F;
    static { NAMED.put("pale violet purple", -0x1.e91166p125F); LIST.add(-0x1.e91166p125F); }

    /**
     * This color constant "drab magenta purple" has RGBA8888 code {@code 552B67FF}, L 0.39607844, A 0.5411765, B 0.45882353, alpha 1.0, hue 0.7821886, and saturation 0.2362864.
     * It can be represented as a packed float with the constant {@code -0x1.eb14cap125F}.
     * <pre>
     * <font style='background-color: #552B67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #552B67; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #552B67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #552B67'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #552B67'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #552B67'>&nbsp;@&nbsp;</font><font style='background-color: #552B67; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #552B67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #552B67; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA_PURPLE = -0x1.eb14cap125F;
    static { NAMED.put("drab magenta purple", -0x1.eb14cap125F); LIST.add(-0x1.eb14cap125F); }

    /**
     * This color constant "dull magenta purple" has RGBA8888 code {@code 845499FF}, L 0.5568628, A 0.5411765, B 0.45882353, alpha 1.0, hue 0.78097916, and saturation 0.2710097.
     * It can be represented as a packed float with the constant {@code -0x1.eb151cp125F}.
     * <pre>
     * <font style='background-color: #845499;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #845499; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #845499;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #845499'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #845499'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #845499'>&nbsp;@&nbsp;</font><font style='background-color: #845499; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #845499;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #845499; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_MAGENTA_PURPLE = -0x1.eb151cp125F;
    static { NAMED.put("dull magenta purple", -0x1.eb151cp125F); LIST.add(-0x1.eb151cp125F); }

    /**
     * This color constant "pale magenta purple" has RGBA8888 code {@code BA85D3FF}, L 0.72156864, A 0.5411765, B 0.45490196, alpha 1.0, hue 0.7702677, and saturation 0.32650506.
     * It can be represented as a packed float with the constant {@code -0x1.e9157p125F}.
     * <pre>
     * <font style='background-color: #BA85D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA85D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA85D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA85D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA85D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA85D3'>&nbsp;@&nbsp;</font><font style='background-color: #BA85D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA85D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA85D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA_PURPLE = -0x1.e9157p125F;
    static { NAMED.put("pale magenta purple", -0x1.e9157p125F); LIST.add(-0x1.e9157p125F); }

    /**
     * This color constant "drab purple magenta" has RGBA8888 code {@code 5E2E69FF}, L 0.41568628, A 0.54509807, B 0.45882353, alpha 1.0, hue 0.79188794, and saturation 0.248293.
     * It can be represented as a packed float with the constant {@code -0x1.eb16d4p125F}.
     * <pre>
     * <font style='background-color: #5E2E69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E2E69; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E2E69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5E2E69'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5E2E69'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5E2E69'>&nbsp;@&nbsp;</font><font style='background-color: #5E2E69; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E2E69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E2E69; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE_MAGENTA = -0x1.eb16d4p125F;
    static { NAMED.put("drab purple magenta", -0x1.eb16d4p125F); LIST.add(-0x1.eb16d4p125F); }

    /**
     * This color constant "dull purple magenta" has RGBA8888 code {@code 90599CFF}, L 0.5803922, A 0.54509807, B 0.45882353, alpha 1.0, hue 0.79135036, and saturation 0.2836423.
     * It can be represented as a packed float with the constant {@code -0x1.eb1728p125F}.
     * <pre>
     * <font style='background-color: #90599C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90599C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90599C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #90599C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #90599C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #90599C'>&nbsp;@&nbsp;</font><font style='background-color: #90599C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90599C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90599C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_PURPLE_MAGENTA = -0x1.eb1728p125F;
    static { NAMED.put("dull purple magenta", -0x1.eb1728p125F); LIST.add(-0x1.eb1728p125F); }

    /**
     * This color constant "pale purple magenta" has RGBA8888 code {@code C789D4FF}, L 0.7411765, A 0.54509807, B 0.45882353, alpha 1.0, hue 0.7912155, and saturation 0.31605107.
     * It can be represented as a packed float with the constant {@code -0x1.eb177ap125F}.
     * <pre>
     * <font style='background-color: #C789D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C789D4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C789D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C789D4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C789D4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C789D4'>&nbsp;@&nbsp;</font><font style='background-color: #C789D4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C789D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C789D4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE_MAGENTA = -0x1.eb177ap125F;
    static { NAMED.put("pale purple magenta", -0x1.eb177ap125F); LIST.add(-0x1.eb177ap125F); }

    /**
     * This color constant "drab red magenta" has RGBA8888 code {@code 692B54FF}, L 0.41568628, A 0.5529412, B 0.48235294, alpha 1.0, hue 0.8890594, and saturation 0.24363217.
     * It can be represented as a packed float with the constant {@code -0x1.f71ad4p125F}.
     * <pre>
     * <font style='background-color: #692B54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #692B54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #692B54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #692B54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #692B54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #692B54'>&nbsp;@&nbsp;</font><font style='background-color: #692B54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #692B54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #692B54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED_MAGENTA = -0x1.f71ad4p125F;
    static { NAMED.put("drab red magenta", -0x1.f71ad4p125F); LIST.add(-0x1.f71ad4p125F); }

    /**
     * This color constant "dull red magenta" has RGBA8888 code {@code 9B5382FF}, L 0.57254905, A 0.5529412, B 0.47843137, alpha 1.0, hue 0.8797889, and saturation 0.27748543.
     * It can be represented as a packed float with the constant {@code -0x1.f51b24p125F}.
     * <pre>
     * <font style='background-color: #9B5382;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B5382; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B5382;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B5382'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B5382'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B5382'>&nbsp;@&nbsp;</font><font style='background-color: #9B5382; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B5382;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B5382; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_RED_MAGENTA = -0x1.f51b24p125F;
    static { NAMED.put("dull red magenta", -0x1.f51b24p125F); LIST.add(-0x1.f51b24p125F); }

    /**
     * This color constant "pale red magenta" has RGBA8888 code {@code D382B5FF}, L 0.73333335, A 0.5529412, B 0.47843137, alpha 1.0, hue 0.88205415, and saturation 0.3133847.
     * It can be represented as a packed float with the constant {@code -0x1.f51b76p125F}.
     * <pre>
     * <font style='background-color: #D382B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D382B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D382B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D382B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D382B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D382B5'>&nbsp;@&nbsp;</font><font style='background-color: #D382B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D382B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D382B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED_MAGENTA = -0x1.f51b76p125F;
    static { NAMED.put("pale red magenta", -0x1.f51b76p125F); LIST.add(-0x1.f51b76p125F); }

    /**
     * This color constant "drab magenta red" has RGBA8888 code {@code 672B34FF}, L 0.4, A 0.54509807, B 0.50980395, alpha 1.0, hue 0.9749559, and saturation 0.23401962.
     * It can be represented as a packed float with the constant {@code -0x1.0516ccp126F}.
     * <pre>
     * <font style='background-color: #672B34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #672B34; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #672B34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #672B34'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #672B34'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #672B34'>&nbsp;@&nbsp;</font><font style='background-color: #672B34; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #672B34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #672B34; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA_RED = -0x1.0516ccp126F;
    static { NAMED.put("drab magenta red", -0x1.0516ccp126F); LIST.add(-0x1.0516ccp126F); }

    /**
     * This color constant "dull magenta red" has RGBA8888 code {@code 99535CFF}, L 0.5568628, A 0.54509807, B 0.5058824, alpha 1.0, hue 0.9677618, and saturation 0.26827356.
     * It can be represented as a packed float with the constant {@code -0x1.03171cp126F}.
     * <pre>
     * <font style='background-color: #99535C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99535C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99535C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #99535C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #99535C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #99535C'>&nbsp;@&nbsp;</font><font style='background-color: #99535C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99535C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99535C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_MAGENTA_RED = -0x1.03171cp126F;
    static { NAMED.put("dull magenta red", -0x1.03171cp126F); LIST.add(-0x1.03171cp126F); }

    /**
     * This color constant "pale magenta red" has RGBA8888 code {@code CF8088FF}, L 0.70980394, A 0.5411765, B 0.50980395, alpha 1.0, hue 0.98532486, and saturation 0.2863652.
     * It can be represented as a packed float with the constant {@code -0x1.05156ap126F}.
     * <pre>
     * <font style='background-color: #CF8088;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF8088; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF8088;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CF8088'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CF8088'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CF8088'>&nbsp;@&nbsp;</font><font style='background-color: #CF8088; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF8088;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF8088; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA_RED = -0x1.05156ap126F;
    static { NAMED.put("pale magenta red", -0x1.05156ap126F); LIST.add(-0x1.05156ap126F); }

    /**
     * This color constant "deep red" has RGBA8888 code {@code B0342EFF}, L 0.5294118, A 0.57254905, B 0.5372549, alpha 1.0, hue 0.0073815915, and saturation 0.5073909.
     * It can be represented as a packed float with the constant {@code -0x1.13250ep126F}.
     * <pre>
     * <font style='background-color: #B0342E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0342E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0342E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0342E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0342E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0342E'>&nbsp;@&nbsp;</font><font style='background-color: #B0342E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0342E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0342E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED = -0x1.13250ep126F;
    static { NAMED.put("deep red", -0x1.13250ep126F); LIST.add(-0x1.13250ep126F); }

    /**
     * This color constant "bright red" has RGBA8888 code {@code DB554BFF}, L 0.6431373, A 0.57254905, B 0.5372549, alpha 1.0, hue 0.011365181, and saturation 0.56111693.
     * It can be represented as a packed float with the constant {@code -0x1.132548p126F}.
     * <pre>
     * <font style='background-color: #DB554B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB554B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB554B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB554B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB554B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB554B'>&nbsp;@&nbsp;</font><font style='background-color: #DB554B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB554B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB554B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED = -0x1.132548p126F;
    static { NAMED.put("bright red", -0x1.132548p126F); LIST.add(-0x1.132548p126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code B1432EFF}, L 0.5529412, A 0.56078434, B 0.5411765, alpha 1.0, hue 0.027617203, and saturation 0.51197284.
     * It can be represented as a packed float with the constant {@code -0x1.151f1ap126F}.
     * <pre>
     * <font style='background-color: #B1432E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1432E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1432E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1432E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1432E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1432E'>&nbsp;@&nbsp;</font><font style='background-color: #B1432E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1432E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1432E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.151f1ap126F;
    static { NAMED.put("deep brown red", -0x1.151f1ap126F); LIST.add(-0x1.151f1ap126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code DC654CFF}, L 0.6666667, A 0.56078434, B 0.5411765, alpha 1.0, hue 0.028705204, and saturation 0.5604184.
     * It can be represented as a packed float with the constant {@code -0x1.151f54p126F}.
     * <pre>
     * <font style='background-color: #DC654C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC654C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC654C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC654C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC654C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC654C'>&nbsp;@&nbsp;</font><font style='background-color: #DC654C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC654C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC654C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.151f54p126F;
    static { NAMED.put("bright brown red", -0x1.151f54p126F); LIST.add(-0x1.151f54p126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code B0512CFF}, L 0.57254905, A 0.54901963, B 0.54509807, alpha 1.0, hue 0.046204347, and saturation 0.51413673.
     * It can be represented as a packed float with the constant {@code -0x1.171924p126F}.
     * <pre>
     * <font style='background-color: #B0512C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0512C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0512C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0512C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0512C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0512C'>&nbsp;@&nbsp;</font><font style='background-color: #B0512C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0512C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0512C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.171924p126F;
    static { NAMED.put("deep red brown", -0x1.171924p126F); LIST.add(-0x1.171924p126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code DD754EFF}, L 0.69411767, A 0.54901963, B 0.54509807, alpha 1.0, hue 0.045623418, and saturation 0.5598223.
     * It can be represented as a packed float with the constant {@code -0x1.171962p126F}.
     * <pre>
     * <font style='background-color: #DD754E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD754E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD754E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD754E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD754E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD754E'>&nbsp;@&nbsp;</font><font style='background-color: #DD754E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD754E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD754E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.171962p126F;
    static { NAMED.put("bright red brown", -0x1.171962p126F); LIST.add(-0x1.171962p126F); }

    /**
     * This color constant "deep brown" has RGBA8888 code {@code B25D33FF}, L 0.59607846, A 0.5411765, B 0.54509807, alpha 1.0, hue 0.05487497, and saturation 0.49504033.
     * It can be represented as a packed float with the constant {@code -0x1.17153p126F}.
     * <pre>
     * <font style='background-color: #B25D33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B25D33; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B25D33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B25D33'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B25D33'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B25D33'>&nbsp;@&nbsp;</font><font style='background-color: #B25D33; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B25D33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B25D33; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN = -0x1.17153p126F;
    static { NAMED.put("deep brown", -0x1.17153p126F); LIST.add(-0x1.17153p126F); }

    /**
     * This color constant "bright brown" has RGBA8888 code {@code DE8155FF}, L 0.7176471, A 0.5411765, B 0.54509807, alpha 1.0, hue 0.054102365, and saturation 0.5374869.
     * It can be represented as a packed float with the constant {@code -0x1.17156ep126F}.
     * <pre>
     * <font style='background-color: #DE8155;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE8155; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE8155;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DE8155'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DE8155'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DE8155'>&nbsp;@&nbsp;</font><font style='background-color: #DE8155; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE8155;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE8155; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN = -0x1.17156ep126F;
    static { NAMED.put("bright brown", -0x1.17156ep126F); LIST.add(-0x1.17156ep126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code B3612EFF}, L 0.6039216, A 0.5372549, B 0.54901963, alpha 1.0, hue 0.06401043, and saturation 0.5192054.
     * It can be represented as a packed float with the constant {@code -0x1.191334p126F}.
     * <pre>
     * <font style='background-color: #B3612E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3612E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3612E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B3612E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B3612E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B3612E'>&nbsp;@&nbsp;</font><font style='background-color: #B3612E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3612E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3612E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.191334p126F;
    static { NAMED.put("deep orange brown", -0x1.191334p126F); LIST.add(-0x1.191334p126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code DF8650FF}, L 0.7254902, A 0.5372549, B 0.54901963, alpha 1.0, hue 0.06253882, and saturation 0.55872524.
     * It can be represented as a packed float with the constant {@code -0x1.191372p126F}.
     * <pre>
     * <font style='background-color: #DF8650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF8650; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF8650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF8650'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF8650'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF8650'>&nbsp;@&nbsp;</font><font style='background-color: #DF8650; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF8650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF8650; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.191372p126F;
    static { NAMED.put("bright orange brown", -0x1.191372p126F); LIST.add(-0x1.191372p126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code B56832FF}, L 0.61960787, A 0.53333336, B 0.54901963, alpha 1.0, hue 0.06837665, and saturation 0.5094125.
     * It can be represented as a packed float with the constant {@code -0x1.19113cp126F}.
     * <pre>
     * <font style='background-color: #B56832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B56832; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B56832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B56832'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B56832'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B56832'>&nbsp;@&nbsp;</font><font style='background-color: #B56832; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B56832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B56832; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.19113cp126F;
    static { NAMED.put("deep brown orange", -0x1.19113cp126F); LIST.add(-0x1.19113cp126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code DE8B53FF}, L 0.73333335, A 0.53333336, B 0.54901963, alpha 1.0, hue 0.06708324, and saturation 0.54516506.
     * It can be represented as a packed float with the constant {@code -0x1.191176p126F}.
     * <pre>
     * <font style='background-color: #DE8B53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE8B53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE8B53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DE8B53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DE8B53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DE8B53'>&nbsp;@&nbsp;</font><font style='background-color: #DE8B53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE8B53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE8B53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.191176p126F;
    static { NAMED.put("bright brown orange", -0x1.191176p126F); LIST.add(-0x1.191176p126F); }

    /**
     * This color constant "deep orange" has RGBA8888 code {@code B36731FF}, L 0.6156863, A 0.5294118, B 0.54901963, alpha 1.0, hue 0.07334139, and saturation 0.49365833.
     * It can be represented as a packed float with the constant {@code -0x1.190f3ap126F}.
     * <pre>
     * <font style='background-color: #B36731;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B36731; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B36731;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B36731'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B36731'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B36731'>&nbsp;@&nbsp;</font><font style='background-color: #B36731; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B36731;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B36731; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE = -0x1.190f3ap126F;
    static { NAMED.put("deep orange", -0x1.190f3ap126F); LIST.add(-0x1.190f3ap126F); }

    /**
     * This color constant "bright orange" has RGBA8888 code {@code E08C54FF}, L 0.7372549, A 0.53333336, B 0.54901963, alpha 1.0, hue 0.067057416, and saturation 0.5464393.
     * It can be represented as a packed float with the constant {@code -0x1.191178p126F}.
     * <pre>
     * <font style='background-color: #E08C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E08C54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E08C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E08C54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E08C54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E08C54'>&nbsp;@&nbsp;</font><font style='background-color: #E08C54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E08C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E08C54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE = -0x1.191178p126F;
    static { NAMED.put("bright orange", -0x1.191178p126F); LIST.add(-0x1.191178p126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code B06F35FF}, L 0.627451, A 0.52156866, B 0.54901963, alpha 1.0, hue 0.08366412, and saturation 0.46675718.
     * It can be represented as a packed float with the constant {@code -0x1.190b4p126F}.
     * <pre>
     * <font style='background-color: #B06F35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B06F35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B06F35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B06F35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B06F35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B06F35'>&nbsp;@&nbsp;</font><font style='background-color: #B06F35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B06F35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B06F35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.190b4p126F;
    static { NAMED.put("deep saffron orange", -0x1.190b4p126F); LIST.add(-0x1.190b4p126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code DF975AFF}, L 0.75686276, A 0.5254902, B 0.54901963, alpha 1.0, hue 0.07686434, and saturation 0.519133.
     * It can be represented as a packed float with the constant {@code -0x1.190d82p126F}.
     * <pre>
     * <font style='background-color: #DF975A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF975A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF975A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF975A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF975A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF975A'>&nbsp;@&nbsp;</font><font style='background-color: #DF975A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF975A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF975A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.190d82p126F;
    static { NAMED.put("bright saffron orange", -0x1.190d82p126F); LIST.add(-0x1.190d82p126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code B47B35FF}, L 0.654902, A 0.5176471, B 0.5529412, alpha 1.0, hue 0.09180119, and saturation 0.4955681.
     * It can be represented as a packed float with the constant {@code -0x1.1b094ep126F}.
     * <pre>
     * <font style='background-color: #B47B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B47B35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B47B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B47B35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B47B35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B47B35'>&nbsp;@&nbsp;</font><font style='background-color: #B47B35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B47B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B47B35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.1b094ep126F;
    static { NAMED.put("deep orange saffron", -0x1.1b094ep126F); LIST.add(-0x1.1b094ep126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code E0A259FF}, L 0.78039217, A 0.5176471, B 0.5529412, alpha 1.0, hue 0.0903098, and saturation 0.5273291.
     * It can be represented as a packed float with the constant {@code -0x1.1b098ep126F}.
     * <pre>
     * <font style='background-color: #E0A259;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0A259; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0A259;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0A259'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0A259'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0A259'>&nbsp;@&nbsp;</font><font style='background-color: #E0A259; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0A259;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0A259; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.1b098ep126F;
    static { NAMED.put("bright orange saffron", -0x1.1b098ep126F); LIST.add(-0x1.1b098ep126F); }

    /**
     * This color constant "deep saffron" has RGBA8888 code {@code B4863CFF}, L 0.6784314, A 0.50980395, B 0.5529412, alpha 1.0, hue 0.10334286, and saturation 0.46811497.
     * It can be represented as a packed float with the constant {@code -0x1.1b055ap126F}.
     * <pre>
     * <font style='background-color: #B4863C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4863C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4863C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4863C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4863C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4863C'>&nbsp;@&nbsp;</font><font style='background-color: #B4863C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4863C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4863C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON = -0x1.1b055ap126F;
    static { NAMED.put("deep saffron", -0x1.1b055ap126F); LIST.add(-0x1.1b055ap126F); }

    /**
     * This color constant "bright saffron" has RGBA8888 code {@code DEAD5FFF}, L 0.79607844, A 0.50980395, B 0.5529412, alpha 1.0, hue 0.102334596, and saturation 0.49522382.
     * It can be represented as a packed float with the constant {@code -0x1.1b0596p126F}.
     * <pre>
     * <font style='background-color: #DEAD5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEAD5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEAD5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEAD5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEAD5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEAD5F'>&nbsp;@&nbsp;</font><font style='background-color: #DEAD5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEAD5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEAD5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON = -0x1.1b0596p126F;
    static { NAMED.put("bright saffron", -0x1.1b0596p126F); LIST.add(-0x1.1b0596p126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code B0923AFF}, L 0.69803923, A 0.49803922, B 0.5568628, alpha 1.0, hue 0.12484391, and saturation 0.45992342.
     * It can be represented as a packed float with the constant {@code -0x1.1cff64p126F}.
     * <pre>
     * <font style='background-color: #B0923A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0923A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0923A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0923A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0923A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0923A'>&nbsp;@&nbsp;</font><font style='background-color: #B0923A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0923A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0923A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.1cff64p126F;
    static { NAMED.put("deep yellow saffron", -0x1.1cff64p126F); LIST.add(-0x1.1cff64p126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code DEBE62FF}, L 0.83137256, A 0.49803922, B 0.5568628, alpha 1.0, hue 0.12417182, and saturation 0.4842339.
     * It can be represented as a packed float with the constant {@code -0x1.1cffa8p126F}.
     * <pre>
     * <font style='background-color: #DEBE62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEBE62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEBE62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEBE62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEBE62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEBE62'>&nbsp;@&nbsp;</font><font style='background-color: #DEBE62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEBE62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEBE62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.1cffa8p126F;
    static { NAMED.put("bright yellow saffron", -0x1.1cffa8p126F); LIST.add(-0x1.1cffa8p126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code B4A740FF}, L 0.74509805, A 0.48235294, B 0.56078434, alpha 1.0, hue 0.15752204, and saturation 0.4331127.
     * It can be represented as a packed float with the constant {@code -0x1.1ef77cp126F}.
     * <pre>
     * <font style='background-color: #B4A740;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4A740; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4A740;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4A740'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4A740'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4A740'>&nbsp;@&nbsp;</font><font style='background-color: #B4A740; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4A740;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4A740; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.1ef77cp126F;
    static { NAMED.put("deep saffron yellow", -0x1.1ef77cp126F); LIST.add(-0x1.1ef77cp126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code E1D468FF}, L 0.8745098, A 0.48235294, B 0.56078434, alpha 1.0, hue 0.1583011, and saturation 0.44996536.
     * It can be represented as a packed float with the constant {@code -0x1.1ef7bep126F}.
     * <pre>
     * <font style='background-color: #E1D468;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1D468; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1D468;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1D468'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1D468'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1D468'>&nbsp;@&nbsp;</font><font style='background-color: #E1D468; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1D468;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1D468; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.1ef7bep126F;
    static { NAMED.put("bright saffron yellow", -0x1.1ef7bep126F); LIST.add(-0x1.1ef7bep126F); }

    /**
     * This color constant "deep yellow" has RGBA8888 code {@code B4C048FF}, L 0.79607844, A 0.47058824, B 0.5647059, alpha 1.0, hue 0.18429576, and saturation 0.46900737.
     * It can be represented as a packed float with the constant {@code -0x1.20f196p126F}.
     * <pre>
     * <font style='background-color: #B4C048;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4C048; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4C048;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4C048'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4C048'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4C048'>&nbsp;@&nbsp;</font><font style='background-color: #B4C048; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4C048;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4C048; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW = -0x1.20f196p126F;
    static { NAMED.put("deep yellow", -0x1.20f196p126F); LIST.add(-0x1.20f196p126F); }

    /**
     * This color constant "bright yellow" has RGBA8888 code {@code DDEC6FFF}, L 0.91764706, A 0.46666667, B 0.5647059, alpha 1.0, hue 0.19600742, and saturation 0.4950528.
     * It can be represented as a packed float with the constant {@code -0x1.20efd4p126F}.
     * <pre>
     * <font style='background-color: #DDEC6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDEC6F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDEC6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDEC6F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDEC6F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDEC6F'>&nbsp;@&nbsp;</font><font style='background-color: #DDEC6F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDEC6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDEC6F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW = -0x1.20efd4p126F;
    static { NAMED.put("bright yellow", -0x1.20efd4p126F); LIST.add(-0x1.20efd4p126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code A7BD4CFF}, L 0.78039217, A 0.46666667, B 0.56078434, alpha 1.0, hue 0.20005059, and saturation 0.44098935.
     * It can be represented as a packed float with the constant {@code -0x1.1eef8ep126F}.
     * <pre>
     * <font style='background-color: #A7BD4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7BD4C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7BD4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A7BD4C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A7BD4C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A7BD4C'>&nbsp;@&nbsp;</font><font style='background-color: #A7BD4C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7BD4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7BD4C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.1eef8ep126F;
    static { NAMED.put("deep lime yellow", -0x1.1eef8ep126F); LIST.add(-0x1.1eef8ep126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code D0EA73FF}, L 0.90588236, A 0.4627451, B 0.56078434, alpha 1.0, hue 0.21252508, and saturation 0.46951878.
     * It can be represented as a packed float with the constant {@code -0x1.1eedcep126F}.
     * <pre>
     * <font style='background-color: #D0EA73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0EA73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0EA73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0EA73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0EA73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0EA73'>&nbsp;@&nbsp;</font><font style='background-color: #D0EA73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0EA73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0EA73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.1eedcep126F;
    static { NAMED.put("bright lime yellow", -0x1.1eedcep126F); LIST.add(-0x1.1eedcep126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code 95BA47FF}, L 0.7607843, A 0.45490196, B 0.56078434, alpha 1.0, hue 0.2301492, and saturation 0.45757133.
     * It can be represented as a packed float with the constant {@code -0x1.1ee984p126F}.
     * <pre>
     * <font style='background-color: #95BA47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #95BA47; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #95BA47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #95BA47'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #95BA47'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #95BA47'>&nbsp;@&nbsp;</font><font style='background-color: #95BA47; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #95BA47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #95BA47; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.1ee984p126F;
    static { NAMED.put("deep yellow lime", -0x1.1ee984p126F); LIST.add(-0x1.1ee984p126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code BFE86FFF}, L 0.8901961, A 0.45490196, B 0.56078434, alpha 1.0, hue 0.23285747, and saturation 0.48050272.
     * It can be represented as a packed float with the constant {@code -0x1.1ee9c6p126F}.
     * <pre>
     * <font style='background-color: #BFE86F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFE86F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFE86F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFE86F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFE86F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFE86F'>&nbsp;@&nbsp;</font><font style='background-color: #BFE86F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFE86F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFE86F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.1ee9c6p126F;
    static { NAMED.put("bright yellow lime", -0x1.1ee9c6p126F); LIST.add(-0x1.1ee9c6p126F); }

    /**
     * This color constant "deep lime" has RGBA8888 code {@code 82BE46FF}, L 0.75686276, A 0.44313726, B 0.56078434, alpha 1.0, hue 0.26053327, and saturation 0.4753754.
     * It can be represented as a packed float with the constant {@code -0x1.1ee382p126F}.
     * <pre>
     * <font style='background-color: #82BE46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82BE46; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82BE46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #82BE46'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #82BE46'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #82BE46'>&nbsp;@&nbsp;</font><font style='background-color: #82BE46; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82BE46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82BE46; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME = -0x1.1ee382p126F;
    static { NAMED.put("deep lime", -0x1.1ee382p126F); LIST.add(-0x1.1ee382p126F); }

    /**
     * This color constant "bright lime" has RGBA8888 code {@code A8E96BFF}, L 0.8784314, A 0.44313726, B 0.56078434, alpha 1.0, hue 0.2630751, and saturation 0.49827242.
     * It can be represented as a packed float with the constant {@code -0x1.1ee3cp126F}.
     * <pre>
     * <font style='background-color: #A8E96B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8E96B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8E96B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8E96B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8E96B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8E96B'>&nbsp;@&nbsp;</font><font style='background-color: #A8E96B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8E96B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8E96B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME = -0x1.1ee3cp126F;
    static { NAMED.put("bright lime", -0x1.1ee3cp126F); LIST.add(-0x1.1ee3cp126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 6EBA40FF}, L 0.7372549, A 0.43529412, B 0.56078434, alpha 1.0, hue 0.2810936, and saturation 0.48405564.
     * It can be represented as a packed float with the constant {@code -0x1.1edf78p126F}.
     * <pre>
     * <font style='background-color: #6EBA40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6EBA40; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6EBA40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6EBA40'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6EBA40'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6EBA40'>&nbsp;@&nbsp;</font><font style='background-color: #6EBA40; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6EBA40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6EBA40; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.1edf78p126F;
    static { NAMED.put("deep green lime", -0x1.1edf78p126F); LIST.add(-0x1.1edf78p126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code 96E868FF}, L 0.8627451, A 0.43529412, B 0.56078434, alpha 1.0, hue 0.2833667, and saturation 0.50784814.
     * It can be represented as a packed float with the constant {@code -0x1.1edfb8p126F}.
     * <pre>
     * <font style='background-color: #96E868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96E868; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96E868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #96E868'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #96E868'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #96E868'>&nbsp;@&nbsp;</font><font style='background-color: #96E868; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96E868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96E868; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.1edfb8p126F;
    static { NAMED.put("bright green lime", -0x1.1edfb8p126F); LIST.add(-0x1.1edfb8p126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 50BD47FF}, L 0.73333335, A 0.42352942, B 0.5568628, alpha 1.0, hue 0.33353743, and saturation 0.4683851.
     * It can be represented as a packed float with the constant {@code -0x1.1cd976p126F}.
     * <pre>
     * <font style='background-color: #50BD47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50BD47; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50BD47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #50BD47'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #50BD47'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #50BD47'>&nbsp;@&nbsp;</font><font style='background-color: #50BD47; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50BD47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50BD47; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.1cd976p126F;
    static { NAMED.put("deep lime green", -0x1.1cd976p126F); LIST.add(-0x1.1cd976p126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code 76E86BFF}, L 0.8509804, A 0.42352942, B 0.5568628, alpha 1.0, hue 0.33053362, and saturation 0.4936835.
     * It can be represented as a packed float with the constant {@code -0x1.1cd9b2p126F}.
     * <pre>
     * <font style='background-color: #76E86B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76E86B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76E86B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #76E86B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #76E86B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #76E86B'>&nbsp;@&nbsp;</font><font style='background-color: #76E86B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76E86B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76E86B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.1cd9b2p126F;
    static { NAMED.put("bright lime green", -0x1.1cd9b2p126F); LIST.add(-0x1.1cd9b2p126F); }

    /**
     * This color constant "deep green" has RGBA8888 code {@code 30B24CFF}, L 0.69411767, A 0.41960785, B 0.54901963, alpha 1.0, hue 0.3836139, and saturation 0.58139145.
     * It can be represented as a packed float with the constant {@code -0x1.18d762p126F}.
     * <pre>
     * <font style='background-color: #30B24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30B24C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30B24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #30B24C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #30B24C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #30B24C'>&nbsp;@&nbsp;</font><font style='background-color: #30B24C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30B24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30B24C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN = -0x1.18d762p126F;
    static { NAMED.put("deep green", -0x1.18d762p126F); LIST.add(-0x1.18d762p126F); }

    /**
     * This color constant "bright green" has RGBA8888 code {@code 47CA5FFF}, L 0.7607843, A 0.41960785, B 0.54901963, alpha 1.0, hue 0.3753056, and saturation 0.56334835.
     * It can be represented as a packed float with the constant {@code -0x1.18d784p126F}.
     * <pre>
     * <font style='background-color: #47CA5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #47CA5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #47CA5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #47CA5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #47CA5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #47CA5F'>&nbsp;@&nbsp;</font><font style='background-color: #47CA5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #47CA5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #47CA5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN = -0x1.18d784p126F;
    static { NAMED.put("bright green", -0x1.18d784p126F); LIST.add(-0x1.18d784p126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 23A979FF}, L 0.6784314, A 0.43137255, B 0.5137255, alpha 1.0, hue 0.45699245, and saturation 0.6697317.
     * It can be represented as a packed float with the constant {@code -0x1.06dd5ap126F}.
     * <pre>
     * <font style='background-color: #23A979;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #23A979; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #23A979;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #23A979'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #23A979'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #23A979'>&nbsp;@&nbsp;</font><font style='background-color: #23A979; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #23A979;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #23A979; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.06dd5ap126F;
    static { NAMED.put("deep cyan green", -0x1.06dd5ap126F); LIST.add(-0x1.06dd5ap126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code 30B482FF}, L 0.70980394, A 0.43137255, B 0.5176471, alpha 1.0, hue 0.44364303, and saturation 0.5906826.
     * It can be represented as a packed float with the constant {@code -0x1.08dd6ap126F}.
     * <pre>
     * <font style='background-color: #30B482;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30B482; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30B482;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #30B482'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #30B482'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #30B482'>&nbsp;@&nbsp;</font><font style='background-color: #30B482; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30B482;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30B482; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.08dd6ap126F;
    static { NAMED.put("bright cyan green", -0x1.08dd6ap126F); LIST.add(-0x1.08dd6ap126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 2BAD9AFF}, L 0.69803923, A 0.4392157, B 0.49803922, alpha 1.0, hue 0.47794583, and saturation 0.5881294.
     * It can be represented as a packed float with the constant {@code -0x1.fee164p125F}.
     * <pre>
     * <font style='background-color: #2BAD9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2BAD9A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2BAD9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2BAD9A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2BAD9A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2BAD9A'>&nbsp;@&nbsp;</font><font style='background-color: #2BAD9A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2BAD9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2BAD9A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.fee164p125F;
    static { NAMED.put("deep green cyan", -0x1.fee164p125F); LIST.add(-0x1.fee164p125F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code 44C4B1FF}, L 0.7647059, A 0.4392157, B 0.49411765, alpha 1.0, hue 0.48275995, and saturation 0.57761157.
     * It can be represented as a packed float with the constant {@code -0x1.fce186p125F}.
     * <pre>
     * <font style='background-color: #44C4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #44C4B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #44C4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #44C4B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #44C4B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #44C4B1'>&nbsp;@&nbsp;</font><font style='background-color: #44C4B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #44C4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #44C4B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.fce186p125F;
    static { NAMED.put("bright green cyan", -0x1.fce186p125F); LIST.add(-0x1.fce186p125F); }

    /**
     * This color constant "deep cyan" has RGBA8888 code {@code 38BDC1FF}, L 0.7529412, A 0.44313726, B 0.48235294, alpha 1.0, hue 0.50392354, and saturation 0.5953772.
     * It can be represented as a packed float with the constant {@code -0x1.f6e38p125F}.
     * <pre>
     * <font style='background-color: #38BDC1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38BDC1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38BDC1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #38BDC1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #38BDC1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #38BDC1'>&nbsp;@&nbsp;</font><font style='background-color: #38BDC1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38BDC1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38BDC1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN = -0x1.f6e38p125F;
    static { NAMED.put("deep cyan", -0x1.f6e38p125F); LIST.add(-0x1.f6e38p125F); }

    /**
     * This color constant "bright cyan" has RGBA8888 code {@code 63E7ECFF}, L 0.8666667, A 0.44313726, B 0.47843137, alpha 1.0, hue 0.51006734, and saturation 0.62127507.
     * It can be represented as a packed float with the constant {@code -0x1.f4e3bap125F}.
     * <pre>
     * <font style='background-color: #63E7EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63E7EC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63E7EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #63E7EC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #63E7EC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #63E7EC'>&nbsp;@&nbsp;</font><font style='background-color: #63E7EC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63E7EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63E7EC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN = -0x1.f4e3bap125F;
    static { NAMED.put("bright cyan", -0x1.f4e3bap125F); LIST.add(-0x1.f4e3bap125F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 319DBCFF}, L 0.6745098, A 0.45490196, B 0.4627451, alpha 1.0, hue 0.53551364, and saturation 0.6466276.
     * It can be represented as a packed float with the constant {@code -0x1.ece958p125F}.
     * <pre>
     * <font style='background-color: #319DBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #319DBC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #319DBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #319DBC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #319DBC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #319DBC'>&nbsp;@&nbsp;</font><font style='background-color: #319DBC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #319DBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #319DBC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.ece958p125F;
    static { NAMED.put("deep blue cyan", -0x1.ece958p125F); LIST.add(-0x1.ece958p125F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 5AC7E8FF}, L 0.79607844, A 0.45490196, B 0.4627451, alpha 1.0, hue 0.538719, and saturation 0.63408726.
     * It can be represented as a packed float with the constant {@code -0x1.ece996p125F}.
     * <pre>
     * <font style='background-color: #5AC7E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AC7E8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AC7E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5AC7E8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5AC7E8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5AC7E8'>&nbsp;@&nbsp;</font><font style='background-color: #5AC7E8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AC7E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AC7E8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.ece996p125F;
    static { NAMED.put("bright blue cyan", -0x1.ece996p125F); LIST.add(-0x1.ece996p125F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 2D80B7FF}, L 0.6039216, A 0.47058824, B 0.44705883, alpha 1.0, hue 0.5685337, and saturation 0.5730659.
     * It can be represented as a packed float with the constant {@code -0x1.e4f134p125F}.
     * <pre>
     * <font style='background-color: #2D80B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D80B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D80B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D80B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D80B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D80B7'>&nbsp;@&nbsp;</font><font style='background-color: #2D80B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D80B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D80B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.e4f134p125F;
    static { NAMED.put("deep cyan blue", -0x1.e4f134p125F); LIST.add(-0x1.e4f134p125F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code 51A7E2FF}, L 0.7254902, A 0.46666667, B 0.44705883, alpha 1.0, hue 0.56519043, and saturation 0.6374672.
     * It can be represented as a packed float with the constant {@code -0x1.e4ef72p125F}.
     * <pre>
     * <font style='background-color: #51A7E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51A7E2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51A7E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #51A7E2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #51A7E2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #51A7E2'>&nbsp;@&nbsp;</font><font style='background-color: #51A7E2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51A7E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51A7E2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.e4ef72p125F;
    static { NAMED.put("bright cyan blue", -0x1.e4ef72p125F); LIST.add(-0x1.e4ef72p125F); }

    /**
     * This color constant "deep blue" has RGBA8888 code {@code 1D44B0FF}, L 0.45490196, A 0.4862745, B 0.40784314, alpha 1.0, hue 0.6144512, and saturation 0.64074236.
     * It can be represented as a packed float with the constant {@code -0x1.d0f8e8p125F}.
     * <pre>
     * <font style='background-color: #1D44B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D44B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D44B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D44B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D44B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D44B0'>&nbsp;@&nbsp;</font><font style='background-color: #1D44B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D44B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D44B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE = -0x1.d0f8e8p125F;
    static { NAMED.put("deep blue", -0x1.d0f8e8p125F); LIST.add(-0x1.d0f8e8p125F); }

    /**
     * This color constant "bright blue" has RGBA8888 code {@code 3967DCFF}, L 0.57254905, A 0.49019608, B 0.40784314, alpha 1.0, hue 0.6205455, and saturation 0.6626407.
     * It can be represented as a packed float with the constant {@code -0x1.d0fb24p125F}.
     * <pre>
     * <font style='background-color: #3967DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3967DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3967DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3967DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3967DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3967DC'>&nbsp;@&nbsp;</font><font style='background-color: #3967DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3967DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3967DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE = -0x1.d0fb24p125F;
    static { NAMED.put("bright blue", -0x1.d0fb24p125F); LIST.add(-0x1.d0fb24p125F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 322CAFFF}, L 0.41960785, A 0.5058824, B 0.39607844, alpha 1.0, hue 0.6680541, and saturation 0.53157085.
     * It can be represented as a packed float with the constant {@code -0x1.cb02d6p125F}.
     * <pre>
     * <font style='background-color: #322CAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #322CAF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #322CAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #322CAF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #322CAF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #322CAF'>&nbsp;@&nbsp;</font><font style='background-color: #322CAF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #322CAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #322CAF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.cb02d6p125F;
    static { NAMED.put("deep violet blue", -0x1.cb02d6p125F); LIST.add(-0x1.cb02d6p125F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code 4B50DBFF}, L 0.5372549, A 0.50980395, B 0.39607844, alpha 1.0, hue 0.6618446, and saturation 0.5871402.
     * It can be represented as a packed float with the constant {@code -0x1.cb0512p125F}.
     * <pre>
     * <font style='background-color: #4B50DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B50DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B50DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B50DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B50DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B50DB'>&nbsp;@&nbsp;</font><font style='background-color: #4B50DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B50DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B50DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.cb0512p125F;
    static { NAMED.put("bright violet blue", -0x1.cb0512p125F); LIST.add(-0x1.cb0512p125F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 4B2BB2FF}, L 0.44313726, A 0.5254902, B 0.4, alpha 1.0, hue 0.69992834, and saturation 0.5456964.
     * It can be represented as a packed float with the constant {@code -0x1.cd0ce2p125F}.
     * <pre>
     * <font style='background-color: #4B2BB2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2BB2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2BB2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B2BB2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B2BB2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B2BB2'>&nbsp;@&nbsp;</font><font style='background-color: #4B2BB2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2BB2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2BB2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.cd0ce2p125F;
    static { NAMED.put("deep blue violet", -0x1.cd0ce2p125F); LIST.add(-0x1.cd0ce2p125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code 684DDDFF}, L 0.5568628, A 0.5294118, B 0.4, alpha 1.0, hue 0.69646055, and saturation 0.5848775.
     * It can be represented as a packed float with the constant {@code -0x1.cd0f1cp125F}.
     * <pre>
     * <font style='background-color: #684DDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #684DDD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #684DDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #684DDD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #684DDD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #684DDD'>&nbsp;@&nbsp;</font><font style='background-color: #684DDD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #684DDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #684DDD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.cd0f1cp125F;
    static { NAMED.put("bright blue violet", -0x1.cd0f1cp125F); LIST.add(-0x1.cd0f1cp125F); }

    /**
     * This color constant "deep violet" has RGBA8888 code {@code 662CB3FF}, L 0.4745098, A 0.54901963, B 0.40784314, alpha 1.0, hue 0.7358191, and saturation 0.5538484.
     * It can be represented as a packed float with the constant {@code -0x1.d118f2p125F}.
     * <pre>
     * <font style='background-color: #662CB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #662CB3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #662CB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #662CB3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #662CB3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #662CB3'>&nbsp;@&nbsp;</font><font style='background-color: #662CB3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #662CB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #662CB3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET = -0x1.d118f2p125F;
    static { NAMED.put("deep violet", -0x1.d118f2p125F); LIST.add(-0x1.d118f2p125F); }

    /**
     * This color constant "bright violet" has RGBA8888 code {@code 8850DFFF}, L 0.5921569, A 0.54901963, B 0.40784314, alpha 1.0, hue 0.729403, and saturation 0.58585227.
     * It can be represented as a packed float with the constant {@code -0x1.d1192ep125F}.
     * <pre>
     * <font style='background-color: #8850DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8850DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8850DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8850DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8850DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8850DF'>&nbsp;@&nbsp;</font><font style='background-color: #8850DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8850DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8850DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET = -0x1.d1192ep125F;
    static { NAMED.put("bright violet", -0x1.d1192ep125F); LIST.add(-0x1.d1192ep125F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 6D2FB3FF}, L 0.4862745, A 0.5529412, B 0.4117647, alpha 1.0, hue 0.7428555, and saturation 0.540856.
     * It can be represented as a packed float with the constant {@code -0x1.d31af8p125F}.
     * <pre>
     * <font style='background-color: #6D2FB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D2FB3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D2FB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D2FB3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D2FB3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D2FB3'>&nbsp;@&nbsp;</font><font style='background-color: #6D2FB3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D2FB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D2FB3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.d31af8p125F;
    static { NAMED.put("deep purple violet", -0x1.d31af8p125F); LIST.add(-0x1.d31af8p125F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code 9052DFFF}, L 0.6039216, A 0.5529412, B 0.4117647, alpha 1.0, hue 0.7375949, and saturation 0.5735987.
     * It can be represented as a packed float with the constant {@code -0x1.d31b34p125F}.
     * <pre>
     * <font style='background-color: #9052DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9052DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9052DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9052DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9052DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9052DF'>&nbsp;@&nbsp;</font><font style='background-color: #9052DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9052DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9052DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.d31b34p125F;
    static { NAMED.put("bright purple violet", -0x1.d31b34p125F); LIST.add(-0x1.d31b34p125F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 7A31B5FF}, L 0.5058824, A 0.56078434, B 0.41568628, alpha 1.0, hue 0.756131, and saturation 0.54134536.
     * It can be represented as a packed float with the constant {@code -0x1.d51f02p125F}.
     * <pre>
     * <font style='background-color: #7A31B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A31B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A31B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A31B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A31B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A31B5'>&nbsp;@&nbsp;</font><font style='background-color: #7A31B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A31B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A31B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.d51f02p125F;
    static { NAMED.put("deep violet purple", -0x1.d51f02p125F); LIST.add(-0x1.d51f02p125F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code A155E2FF}, L 0.627451, A 0.56078434, B 0.41568628, alpha 1.0, hue 0.7521104, and saturation 0.5746888.
     * It can be represented as a packed float with the constant {@code -0x1.d51f4p125F}.
     * <pre>
     * <font style='background-color: #A155E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A155E2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A155E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A155E2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A155E2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A155E2'>&nbsp;@&nbsp;</font><font style='background-color: #A155E2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A155E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A155E2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.d51f4p125F;
    static { NAMED.put("bright violet purple", -0x1.d51f4p125F); LIST.add(-0x1.d51f4p125F); }

    /**
     * This color constant "deep purple" has RGBA8888 code {@code 8631B5FF}, L 0.52156866, A 0.5686275, B 0.41960785, alpha 1.0, hue 0.7702163, and saturation 0.54291654.
     * It can be represented as a packed float with the constant {@code -0x1.d7230ap125F}.
     * <pre>
     * <font style='background-color: #8631B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8631B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8631B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8631B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8631B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8631B5'>&nbsp;@&nbsp;</font><font style='background-color: #8631B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8631B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8631B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE = -0x1.d7230ap125F;
    static { NAMED.put("deep purple", -0x1.d7230ap125F); LIST.add(-0x1.d7230ap125F); }

    /**
     * This color constant "bright purple" has RGBA8888 code {@code AE56E2FF}, L 0.6431373, A 0.5647059, B 0.41960785, alpha 1.0, hue 0.7615294, and saturation 0.56474006.
     * It can be represented as a packed float with the constant {@code -0x1.d72148p125F}.
     * <pre>
     * <font style='background-color: #AE56E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE56E2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE56E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE56E2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE56E2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE56E2'>&nbsp;@&nbsp;</font><font style='background-color: #AE56E2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE56E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE56E2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE = -0x1.d72148p125F;
    static { NAMED.put("bright purple", -0x1.d72148p125F); LIST.add(-0x1.d72148p125F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code 9431B1FF}, L 0.5372549, A 0.5764706, B 0.42745098, alpha 1.0, hue 0.78954935, and saturation 0.5225743.
     * It can be represented as a packed float with the constant {@code -0x1.db2712p125F}.
     * <pre>
     * <font style='background-color: #9431B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9431B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9431B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9431B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9431B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9431B1'>&nbsp;@&nbsp;</font><font style='background-color: #9431B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9431B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9431B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.db2712p125F;
    static { NAMED.put("deep magenta purple", -0x1.db2712p125F); LIST.add(-0x1.db2712p125F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code BE56DEFF}, L 0.65882355, A 0.5764706, B 0.42745098, alpha 1.0, hue 0.78811514, and saturation 0.5519341.
     * It can be represented as a packed float with the constant {@code -0x1.db275p125F}.
     * <pre>
     * <font style='background-color: #BE56DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE56DE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE56DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE56DE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE56DE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE56DE'>&nbsp;@&nbsp;</font><font style='background-color: #BE56DE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE56DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE56DE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.db275p125F;
    static { NAMED.put("bright magenta purple", -0x1.db275p125F); LIST.add(-0x1.db275p125F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code 9F35B3FF}, L 0.5568628, A 0.5803922, B 0.43137255, alpha 1.0, hue 0.79996896, and saturation 0.5156574.
     * It can be represented as a packed float with the constant {@code -0x1.dd291cp125F}.
     * <pre>
     * <font style='background-color: #9F35B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F35B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F35B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F35B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F35B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F35B3'>&nbsp;@&nbsp;</font><font style='background-color: #9F35B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F35B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F35B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.dd291cp125F;
    static { NAMED.put("deep purple magenta", -0x1.dd291cp125F); LIST.add(-0x1.dd291cp125F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code CA5AE0FF}, L 0.6784314, A 0.5803922, B 0.43137255, alpha 1.0, hue 0.7993644, and saturation 0.5444921.
     * It can be represented as a packed float with the constant {@code -0x1.dd295ap125F}.
     * <pre>
     * <font style='background-color: #CA5AE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA5AE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA5AE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA5AE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA5AE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA5AE0'>&nbsp;@&nbsp;</font><font style='background-color: #CA5AE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA5AE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA5AE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.dd295ap125F;
    static { NAMED.put("bright purple magenta", -0x1.dd295ap125F); LIST.add(-0x1.dd295ap125F); }

    /**
     * This color constant "deep magenta" has RGBA8888 code {@code AE36B6FF}, L 0.5803922, A 0.5882353, B 0.43529412, alpha 1.0, hue 0.8160552, and saturation 0.52364445.
     * It can be represented as a packed float with the constant {@code -0x1.df2d28p125F}.
     * <pre>
     * <font style='background-color: #AE36B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE36B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE36B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE36B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE36B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE36B6'>&nbsp;@&nbsp;</font><font style='background-color: #AE36B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE36B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE36B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA = -0x1.df2d28p125F;
    static { NAMED.put("deep magenta", -0x1.df2d28p125F); LIST.add(-0x1.df2d28p125F); }

    /**
     * This color constant "bright magenta" has RGBA8888 code {@code DB5CE3FF}, L 0.7019608, A 0.5882353, B 0.43529412, alpha 1.0, hue 0.81647754, and saturation 0.54974484.
     * It can be represented as a packed float with the constant {@code -0x1.df2d66p125F}.
     * <pre>
     * <font style='background-color: #DB5CE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB5CE3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB5CE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB5CE3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB5CE3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB5CE3'>&nbsp;@&nbsp;</font><font style='background-color: #DB5CE3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB5CE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB5CE3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA = -0x1.df2d66p125F;
    static { NAMED.put("bright magenta", -0x1.df2d66p125F); LIST.add(-0x1.df2d66p125F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code B33581FF}, L 0.56078434, A 0.5882353, B 0.4745098, alpha 1.0, hue 0.8927443, and saturation 0.48814684.
     * It can be represented as a packed float with the constant {@code -0x1.f32d1ep125F}.
     * <pre>
     * <font style='background-color: #B33581;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B33581; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B33581;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B33581'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B33581'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B33581'>&nbsp;@&nbsp;</font><font style='background-color: #B33581; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B33581;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B33581; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.f32d1ep125F;
    static { NAMED.put("deep red magenta", -0x1.f32d1ep125F); LIST.add(-0x1.f32d1ep125F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code DD56A4FF}, L 0.67058825, A 0.5882353, B 0.4745098, alpha 1.0, hue 0.8961635, and saturation 0.52206373.
     * It can be represented as a packed float with the constant {@code -0x1.f32d56p125F}.
     * <pre>
     * <font style='background-color: #DD56A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD56A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD56A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD56A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD56A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD56A4'>&nbsp;@&nbsp;</font><font style='background-color: #DD56A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD56A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD56A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.f32d56p125F;
    static { NAMED.put("bright red magenta", -0x1.f32d56p125F); LIST.add(-0x1.f32d56p125F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code B33156FF}, L 0.5411765, A 0.58431375, B 0.5058824, alpha 1.0, hue 0.9470227, and saturation 0.5016347.
     * It can be represented as a packed float with the constant {@code -0x1.032b14p126F}.
     * <pre>
     * <font style='background-color: #B33156;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B33156; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B33156;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B33156'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B33156'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B33156'>&nbsp;@&nbsp;</font><font style='background-color: #B33156; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B33156;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B33156; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.032b14p126F;
    static { NAMED.put("deep magenta red", -0x1.032b14p126F); LIST.add(-0x1.032b14p126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code DF5376FF}, L 0.654902, A 0.58431375, B 0.5058824, alpha 1.0, hue 0.95214164, and saturation 0.5399199.
     * It can be represented as a packed float with the constant {@code -0x1.032b4ep126F}.
     * <pre>
     * <font style='background-color: #DF5376;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF5376; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF5376;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF5376'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF5376'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF5376'>&nbsp;@&nbsp;</font><font style='background-color: #DF5376; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF5376;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF5376; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.032b4ep126F;
    static { NAMED.put("bright magenta red", -0x1.032b4ep126F); LIST.add(-0x1.032b4ep126F); }

    /**
     * This color constant "some brown red" has RGBA8888 code {@code FD2918FF}, L 0.6392157, A 0.6039216, B 0.56078434, alpha 1.0, hue 0.012586858, and saturation 0.89423895.
     * It can be represented as a packed float with the constant {@code -0x1.1f3546p126F}.
     * <pre>
     * <font style='background-color: #FD2918;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD2918; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD2918;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD2918'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD2918'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD2918'>&nbsp;@&nbsp;</font><font style='background-color: #FD2918; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD2918;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD2918; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BROWN_RED = -0x1.1f3546p126F;
    static { NAMED.put("some brown red", -0x1.1f3546p126F); LIST.add(-0x1.1f3546p126F); }

    /**
     * This color constant "more brown red" has RGBA8888 code {@code FA4411FF}, L 0.65882355, A 0.5882353, B 0.5647059, alpha 1.0, hue 0.03673658, and saturation 0.90978897.
     * It can be represented as a packed float with the constant {@code -0x1.212d5p126F}.
     * <pre>
     * <font style='background-color: #FA4411;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA4411; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA4411;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA4411'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA4411'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA4411'>&nbsp;@&nbsp;</font><font style='background-color: #FA4411; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA4411;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA4411; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BROWN_RED = -0x1.212d5p126F;
    static { NAMED.put("more brown red", -0x1.212d5p126F); LIST.add(-0x1.212d5p126F); }

    /**
     * This color constant "more red brown" has RGBA8888 code {@code FD580BFF}, L 0.6862745, A 0.5764706, B 0.5686275, alpha 1.0, hue 0.053095475, and saturation 0.9452705.
     * It can be represented as a packed float with the constant {@code -0x1.23275ep126F}.
     * <pre>
     * <font style='background-color: #FD580B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD580B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD580B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD580B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD580B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD580B'>&nbsp;@&nbsp;</font><font style='background-color: #FD580B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD580B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD580B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_RED_BROWN = -0x1.23275ep126F;
    static { NAMED.put("more red brown", -0x1.23275ep126F); LIST.add(-0x1.23275ep126F); }

    /**
     * This color constant "some red brown" has RGBA8888 code {@code FC6A1AFF}, L 0.70980394, A 0.5647059, B 0.5686275, alpha 1.0, hue 0.058716558, and saturation 0.88285327.
     * It can be represented as a packed float with the constant {@code -0x1.23216ap126F}.
     * <pre>
     * <font style='background-color: #FC6A1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC6A1A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC6A1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC6A1A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC6A1A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC6A1A'>&nbsp;@&nbsp;</font><font style='background-color: #FC6A1A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC6A1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC6A1A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_RED_BROWN = -0x1.23216ap126F;
    static { NAMED.put("some red brown", -0x1.23216ap126F); LIST.add(-0x1.23216ap126F); }

    /**
     * This color constant "some orange brown" has RGBA8888 code {@code F8721EFF}, L 0.7176471, A 0.5568628, B 0.5686275, alpha 1.0, hue 0.06444143, and saturation 0.8515679.
     * It can be represented as a packed float with the constant {@code -0x1.231d6ep126F}.
     * <pre>
     * <font style='background-color: #F8721E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8721E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8721E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8721E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8721E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8721E'>&nbsp;@&nbsp;</font><font style='background-color: #F8721E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8721E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8721E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_ORANGE_BROWN = -0x1.231d6ep126F;
    static { NAMED.put("some orange brown", -0x1.231d6ep126F); LIST.add(-0x1.231d6ep126F); }

    /**
     * This color constant "more orange brown" has RGBA8888 code {@code FD740EFF}, L 0.7254902, A 0.5568628, B 0.57254905, alpha 1.0, hue 0.07102957, and saturation 0.9327041.
     * It can be represented as a packed float with the constant {@code -0x1.251d72p126F}.
     * <pre>
     * <font style='background-color: #FD740E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD740E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD740E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD740E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD740E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD740E'>&nbsp;@&nbsp;</font><font style='background-color: #FD740E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD740E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD740E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_ORANGE_BROWN = -0x1.251d72p126F;
    static { NAMED.put("more orange brown", -0x1.251d72p126F); LIST.add(-0x1.251d72p126F); }

    /**
     * This color constant "more brown orange" has RGBA8888 code {@code F9770FFF}, L 0.7254902, A 0.5529412, B 0.57254905, alpha 1.0, hue 0.07408976, and saturation 0.9169473.
     * It can be represented as a packed float with the constant {@code -0x1.251b72p126F}.
     * <pre>
     * <font style='background-color: #F9770F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9770F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9770F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F9770F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F9770F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F9770F'>&nbsp;@&nbsp;</font><font style='background-color: #F9770F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9770F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9770F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BROWN_ORANGE = -0x1.251b72p126F;
    static { NAMED.put("more brown orange", -0x1.251b72p126F); LIST.add(-0x1.251b72p126F); }

    /**
     * This color constant "some brown orange" has RGBA8888 code {@code FA7E17FF}, L 0.7372549, A 0.54509807, B 0.57254905, alpha 1.0, hue 0.07859983, and saturation 0.87335414.
     * It can be represented as a packed float with the constant {@code -0x1.251778p126F}.
     * <pre>
     * <font style='background-color: #FA7E17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA7E17; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA7E17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA7E17'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA7E17'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA7E17'>&nbsp;@&nbsp;</font><font style='background-color: #FA7E17; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA7E17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA7E17; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BROWN_ORANGE = -0x1.251778p126F;
    static { NAMED.put("some brown orange", -0x1.251778p126F); LIST.add(-0x1.251778p126F); }

    /**
     * This color constant "some saffron orange" has RGBA8888 code {@code FA831BFF}, L 0.74509805, A 0.54509807, B 0.57254905, alpha 1.0, hue 0.07775962, and saturation 0.86922586.
     * It can be represented as a packed float with the constant {@code -0x1.25177cp126F}.
     * <pre>
     * <font style='background-color: #FA831B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA831B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA831B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA831B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA831B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA831B'>&nbsp;@&nbsp;</font><font style='background-color: #FA831B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA831B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA831B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_SAFFRON_ORANGE = -0x1.25177cp126F;
    static { NAMED.put("some saffron orange", -0x1.25177cp126F); LIST.add(-0x1.25177cp126F); }

    /**
     * This color constant "more saffron orange" has RGBA8888 code {@code FD8F14FF}, L 0.76862746, A 0.5372549, B 0.5764706, alpha 1.0, hue 0.08807056, and saturation 0.90940726.
     * It can be represented as a packed float with the constant {@code -0x1.271388p126F}.
     * <pre>
     * <font style='background-color: #FD8F14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD8F14; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD8F14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD8F14'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD8F14'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD8F14'>&nbsp;@&nbsp;</font><font style='background-color: #FD8F14; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD8F14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD8F14; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_SAFFRON_ORANGE = -0x1.271388p126F;
    static { NAMED.put("more saffron orange", -0x1.271388p126F); LIST.add(-0x1.271388p126F); }

    /**
     * This color constant "more orange saffron" has RGBA8888 code {@code FB9A1EFF}, L 0.78431374, A 0.5254902, B 0.5764706, alpha 1.0, hue 0.09687954, and saturation 0.8474528.
     * It can be represented as a packed float with the constant {@code -0x1.270d9p126F}.
     * <pre>
     * <font style='background-color: #FB9A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FB9A1E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FB9A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FB9A1E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FB9A1E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FB9A1E'>&nbsp;@&nbsp;</font><font style='background-color: #FB9A1E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FB9A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FB9A1E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_ORANGE_SAFFRON = -0x1.270d9p126F;
    static { NAMED.put("more orange saffron", -0x1.270d9p126F); LIST.add(-0x1.270d9p126F); }

    /**
     * This color constant "some orange saffron" has RGBA8888 code {@code FDA514FF}, L 0.80784315, A 0.52156866, B 0.5803922, alpha 1.0, hue 0.10313909, and saturation 0.9023769.
     * It can be represented as a packed float with the constant {@code -0x1.290b9cp126F}.
     * <pre>
     * <font style='background-color: #FDA514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDA514; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDA514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDA514'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDA514'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDA514'>&nbsp;@&nbsp;</font><font style='background-color: #FDA514; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDA514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDA514; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_ORANGE_SAFFRON = -0x1.290b9cp126F;
    static { NAMED.put("some orange saffron", -0x1.290b9cp126F); LIST.add(-0x1.290b9cp126F); }

    /**
     * This color constant "some yellow saffron" has RGBA8888 code {@code F7B321FF}, L 0.827451, A 0.5058824, B 0.5803922, alpha 1.0, hue 0.11744157, and saturation 0.81595457.
     * It can be represented as a packed float with the constant {@code -0x1.2903a6p126F}.
     * <pre>
     * <font style='background-color: #F7B321;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7B321; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7B321;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7B321'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7B321'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7B321'>&nbsp;@&nbsp;</font><font style='background-color: #F7B321; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7B321;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7B321; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_YELLOW_SAFFRON = -0x1.2903a6p126F;
    static { NAMED.put("some yellow saffron", -0x1.2903a6p126F); LIST.add(-0x1.2903a6p126F); }

    /**
     * This color constant "more yellow saffron" has RGBA8888 code {@code F8C41FFF}, L 0.85882354, A 0.49803922, B 0.58431375, alpha 1.0, hue 0.12684672, and saturation 0.8416454.
     * It can be represented as a packed float with the constant {@code -0x1.2affb6p126F}.
     * <pre>
     * <font style='background-color: #F8C41F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C41F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C41F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8C41F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8C41F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8C41F'>&nbsp;@&nbsp;</font><font style='background-color: #F8C41F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C41F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C41F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_YELLOW_SAFFRON = -0x1.2affb6p126F;
    static { NAMED.put("more yellow saffron", -0x1.2affb6p126F); LIST.add(-0x1.2affb6p126F); }

    /**
     * This color constant "more saffron yellow" has RGBA8888 code {@code FDDC27FF}, L 0.90588236, A 0.48235294, B 0.5882353, alpha 1.0, hue 0.14565593, and saturation 0.8153669.
     * It can be represented as a packed float with the constant {@code -0x1.2cf7cep126F}.
     * <pre>
     * <font style='background-color: #FDDC27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDDC27; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDDC27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDDC27'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDDC27'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDDC27'>&nbsp;@&nbsp;</font><font style='background-color: #FDDC27; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDDC27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDDC27; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_SAFFRON_YELLOW = -0x1.2cf7cep126F;
    static { NAMED.put("more saffron yellow", -0x1.2cf7cep126F); LIST.add(-0x1.2cf7cep126F); }

    /**
     * This color constant "some saffron yellow" has RGBA8888 code {@code FBF42CFF}, L 0.9490196, A 0.46666667, B 0.5921569, alpha 1.0, hue 0.16743594, and saturation 0.78836024.
     * It can be represented as a packed float with the constant {@code -0x1.2eefe4p126F}.
     * <pre>
     * <font style='background-color: #FBF42C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBF42C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBF42C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FBF42C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FBF42C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FBF42C'>&nbsp;@&nbsp;</font><font style='background-color: #FBF42C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBF42C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBF42C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_SAFFRON_YELLOW = -0x1.2eefe4p126F;
    static { NAMED.put("some saffron yellow", -0x1.2eefe4p126F); LIST.add(-0x1.2eefe4p126F); }

    /**
     * This color constant "some lime yellow" has RGBA8888 code {@code EDFF21FF}, L 0.95686275, A 0.45490196, B 0.5921569, alpha 1.0, hue 0.18537322, and saturation 0.8001526.
     * It can be represented as a packed float with the constant {@code -0x1.2ee9e8p126F}.
     * <pre>
     * <font style='background-color: #EDFF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDFF21; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDFF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDFF21'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDFF21'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDFF21'>&nbsp;@&nbsp;</font><font style='background-color: #EDFF21; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDFF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDFF21; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_LIME_YELLOW = -0x1.2ee9e8p126F;
    static { NAMED.put("some lime yellow", -0x1.2ee9e8p126F); LIST.add(-0x1.2ee9e8p126F); }

    /**
     * This color constant "more lime yellow" has RGBA8888 code {@code DAFF2FFF}, L 0.94509804, A 0.44705883, B 0.5882353, alpha 1.0, hue 0.20294033, and saturation 0.75953174.
     * It can be represented as a packed float with the constant {@code -0x1.2ce5e2p126F}.
     * <pre>
     * <font style='background-color: #DAFF2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAFF2F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAFF2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAFF2F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAFF2F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAFF2F'>&nbsp;@&nbsp;</font><font style='background-color: #DAFF2F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAFF2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAFF2F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_LIME_YELLOW = -0x1.2ce5e2p126F;
    static { NAMED.put("more lime yellow", -0x1.2ce5e2p126F); LIST.add(-0x1.2ce5e2p126F); }

    /**
     * This color constant "more yellow lime" has RGBA8888 code {@code C9FF29FF}, L 0.93333334, A 0.4392157, B 0.5921569, alpha 1.0, hue 0.20997319, and saturation 0.8453514.
     * It can be represented as a packed float with the constant {@code -0x1.2ee1dcp126F}.
     * <pre>
     * <font style='background-color: #C9FF29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9FF29; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9FF29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9FF29'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9FF29'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9FF29'>&nbsp;@&nbsp;</font><font style='background-color: #C9FF29; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9FF29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9FF29; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_YELLOW_LIME = -0x1.2ee1dcp126F;
    static { NAMED.put("more yellow lime", -0x1.2ee1dcp126F); LIST.add(-0x1.2ee1dcp126F); }

    /**
     * This color constant "some yellow lime" has RGBA8888 code {@code AFFF21FF}, L 0.92156863, A 0.42745098, B 0.5882353, alpha 1.0, hue 0.23524442, and saturation 0.79658175.
     * It can be represented as a packed float with the constant {@code -0x1.2cdbd6p126F}.
     * <pre>
     * <font style='background-color: #AFFF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFFF21; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFFF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFFF21'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFFF21'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFFF21'>&nbsp;@&nbsp;</font><font style='background-color: #AFFF21; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFFF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFFF21; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_YELLOW_LIME = -0x1.2cdbd6p126F;
    static { NAMED.put("some yellow lime", -0x1.2cdbd6p126F); LIST.add(-0x1.2cdbd6p126F); }

    /**
     * This color constant "some green lime" has RGBA8888 code {@code 94FF19FF}, L 0.90588236, A 0.41568628, B 0.5882353, alpha 1.0, hue 0.25530198, and saturation 0.82366455.
     * It can be represented as a packed float with the constant {@code -0x1.2cd5cep126F}.
     * <pre>
     * <font style='background-color: #94FF19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #94FF19; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #94FF19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #94FF19'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #94FF19'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #94FF19'>&nbsp;@&nbsp;</font><font style='background-color: #94FF19; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #94FF19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #94FF19; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_GREEN_LIME = -0x1.2cd5cep126F;
    static { NAMED.put("some green lime", -0x1.2cd5cep126F); LIST.add(-0x1.2cd5cep126F); }

    /**
     * This color constant "more green lime" has RGBA8888 code {@code 7AFF27FF}, L 0.89411765, A 0.40392157, B 0.5882353, alpha 1.0, hue 0.2768409, and saturation 0.84507436.
     * It can be represented as a packed float with the constant {@code -0x1.2ccfc8p126F}.
     * <pre>
     * <font style='background-color: #7AFF27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AFF27; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AFF27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7AFF27'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7AFF27'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7AFF27'>&nbsp;@&nbsp;</font><font style='background-color: #7AFF27; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AFF27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AFF27; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_GREEN_LIME = -0x1.2ccfc8p126F;
    static { NAMED.put("more green lime", -0x1.2ccfc8p126F); LIST.add(-0x1.2ccfc8p126F); }

    /**
     * This color constant "more lime green" has RGBA8888 code {@code 49FF21FF}, L 0.8784314, A 0.39215687, B 0.58431375, alpha 1.0, hue 0.32147935, and saturation 0.80614054.
     * It can be represented as a packed float with the constant {@code -0x1.2ac9cp126F}.
     * <pre>
     * <font style='background-color: #49FF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49FF21; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49FF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #49FF21'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #49FF21'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #49FF21'>&nbsp;@&nbsp;</font><font style='background-color: #49FF21; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49FF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49FF21; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_LIME_GREEN = -0x1.2ac9cp126F;
    static { NAMED.put("more lime green", -0x1.2ac9cp126F); LIST.add(-0x1.2ac9cp126F); }

    /**
     * This color constant "some lime green" has RGBA8888 code {@code 00FF3CFF}, L 0.87058824, A 0.38431373, B 0.5803922, alpha 1.0, hue 0.3727562, and saturation 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.28c5bcp126F}.
     * <pre>
     * <font style='background-color: #00FF3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF3C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF3C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF3C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF3C'>&nbsp;@&nbsp;</font><font style='background-color: #00FF3C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF3C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_LIME_GREEN = -0x1.28c5bcp126F;
    static { NAMED.put("some lime green", -0x1.28c5bcp126F); LIST.add(-0x1.28c5bcp126F); }

    /**
     * This color constant "some cyan green" has RGBA8888 code {@code 00EE6EFF}, L 0.8352941, A 0.4, B 0.5529412, alpha 1.0, hue 0.4118296, and saturation 0.9358403.
     * It can be represented as a packed float with the constant {@code -0x1.1acdaap126F}.
     * <pre>
     * <font style='background-color: #00EE6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00EE6E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00EE6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00EE6E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00EE6E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00EE6E'>&nbsp;@&nbsp;</font><font style='background-color: #00EE6E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00EE6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00EE6E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_CYAN_GREEN = -0x1.1acdaap126F;
    static { NAMED.put("some cyan green", -0x1.1acdaap126F); LIST.add(-0x1.1acdaap126F); }

    /**
     * This color constant "more cyan green" has RGBA8888 code {@code 00DF9BFF}, L 0.8117647, A 0.41568628, B 0.52156866, alpha 1.0, hue 0.45060232, and saturation 0.8799386.
     * It can be represented as a packed float with the constant {@code -0x1.0ad59ep126F}.
     * <pre>
     * <font style='background-color: #00DF9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DF9B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DF9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00DF9B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00DF9B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00DF9B'>&nbsp;@&nbsp;</font><font style='background-color: #00DF9B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DF9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DF9B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_CYAN_GREEN = -0x1.0ad59ep126F;
    static { NAMED.put("more cyan green", -0x1.0ad59ep126F); LIST.add(-0x1.0ad59ep126F); }

    /**
     * This color constant "more green cyan" has RGBA8888 code {@code 00DFBBFF}, L 0.81960785, A 0.42352942, B 0.5019608, alpha 1.0, hue 0.47500896, and saturation 0.8784716.
     * It can be represented as a packed float with the constant {@code -0x1.00d9a2p126F}.
     * <pre>
     * <font style='background-color: #00DFBB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DFBB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DFBB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00DFBB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00DFBB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00DFBB'>&nbsp;@&nbsp;</font><font style='background-color: #00DFBB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DFBB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DFBB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_GREEN_CYAN = -0x1.00d9a2p126F;
    static { NAMED.put("more green cyan", -0x1.00d9a2p126F); LIST.add(-0x1.00d9a2p126F); }

    /**
     * This color constant "some green cyan" has RGBA8888 code {@code 00EAE0FF}, L 0.85490197, A 0.42352942, B 0.4862745, alpha 1.0, hue 0.49307656, and saturation 0.92815965.
     * It can be represented as a packed float with the constant {@code -0x1.f8d9b4p125F}.
     * <pre>
     * <font style='background-color: #00EAE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00EAE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00EAE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00EAE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00EAE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00EAE0'>&nbsp;@&nbsp;</font><font style='background-color: #00EAE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00EAE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00EAE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_GREEN_CYAN = -0x1.f8d9b4p125F;
    static { NAMED.put("some green cyan", -0x1.f8d9b4p125F); LIST.add(-0x1.f8d9b4p125F); }

    /**
     * This color constant "some blue cyan" has RGBA8888 code {@code 00EBFFFF}, L 0.8666667, A 0.43137255, B 0.46666667, alpha 1.0, hue 0.51206535, and saturation 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.eeddbap125F}.
     * <pre>
     * <font style='background-color: #00EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00EBFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #00EBFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00EBFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BLUE_CYAN = -0x1.eeddbap125F;
    static { NAMED.put("some blue cyan", -0x1.eeddbap125F); LIST.add(-0x1.eeddbap125F); }

    /**
     * This color constant "more blue cyan" has RGBA8888 code {@code 00CEFFFF}, L 0.8039216, A 0.44313726, B 0.4509804, alpha 1.0, hue 0.53155667, and saturation 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.e6e39ap125F}.
     * <pre>
     * <font style='background-color: #00CEFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00CEFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00CEFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00CEFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00CEFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00CEFF'>&nbsp;@&nbsp;</font><font style='background-color: #00CEFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00CEFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00CEFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BLUE_CYAN = -0x1.e6e39ap125F;
    static { NAMED.put("more blue cyan", -0x1.e6e39ap125F); LIST.add(-0x1.e6e39ap125F); }

    /**
     * This color constant "more cyan blue" has RGBA8888 code {@code 00ADFFFF}, L 0.73333335, A 0.45490196, B 0.43137255, alpha 1.0, hue 0.552951, and saturation 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.dce976p125F}.
     * <pre>
     * <font style='background-color: #00ADFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00ADFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00ADFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00ADFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00ADFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00ADFF'>&nbsp;@&nbsp;</font><font style='background-color: #00ADFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00ADFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00ADFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_CYAN_BLUE = -0x1.dce976p125F;
    static { NAMED.put("more cyan blue", -0x1.dce976p125F); LIST.add(-0x1.dce976p125F); }

    /**
     * This color constant "some cyan blue" has RGBA8888 code {@code 007EFFFF}, L 0.6313726, A 0.47058824, B 0.4, alpha 1.0, hue 0.58423084, and saturation 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.ccf142p125F}.
     * <pre>
     * <font style='background-color: #007EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007EFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007EFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007EFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007EFF'>&nbsp;@&nbsp;</font><font style='background-color: #007EFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007EFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_CYAN_BLUE = -0x1.ccf142p125F;
    static { NAMED.put("some cyan blue", -0x1.ccf142p125F); LIST.add(-0x1.ccf142p125F); }

    /**
     * This color constant "some violet blue" has RGBA8888 code {@code 2200FFFF}, L 0.45882353, A 0.49019608, B 0.34509805, alpha 1.0, hue 0.68121165, and saturation 0.98061234.
     * It can be represented as a packed float with the constant {@code -0x1.b0faeap125F}.
     * <pre>
     * <font style='background-color: #2200FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2200FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2200FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2200FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2200FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2200FF'>&nbsp;@&nbsp;</font><font style='background-color: #2200FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2200FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2200FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_VIOLET_BLUE = -0x1.b0faeap125F;
    static { NAMED.put("some violet blue", -0x1.b0faeap125F); LIST.add(-0x1.b0faeap125F); }

    /**
     * This color constant "more violet blue" has RGBA8888 code {@code 4300FFFF}, L 0.48235294, A 0.5137255, B 0.34901962, alpha 1.0, hue 0.7079629, and saturation 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.b306f6p125F}.
     * <pre>
     * <font style='background-color: #4300FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4300FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4300FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4300FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4300FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4300FF'>&nbsp;@&nbsp;</font><font style='background-color: #4300FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4300FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4300FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_VIOLET_BLUE = -0x1.b306f6p125F;
    static { NAMED.put("more violet blue", -0x1.b306f6p125F); LIST.add(-0x1.b306f6p125F); }

    /**
     * This color constant "more blue violet" has RGBA8888 code {@code 5F00FFFF}, L 0.5058824, A 0.5372549, B 0.35686275, alpha 1.0, hue 0.72404116, and saturation 0.9764148.
     * It can be represented as a packed float with the constant {@code -0x1.b71302p125F}.
     * <pre>
     * <font style='background-color: #5F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #5F00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BLUE_VIOLET = -0x1.b71302p125F;
    static { NAMED.put("more blue violet", -0x1.b71302p125F); LIST.add(-0x1.b71302p125F); }

    /**
     * This color constant "some blue violet" has RGBA8888 code {@code 7900FFFF}, L 0.53333336, A 0.56078434, B 0.3647059, alpha 1.0, hue 0.7452302, and saturation 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.bb1f1p125F}.
     * <pre>
     * <font style='background-color: #7900FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7900FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7900FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7900FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7900FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7900FF'>&nbsp;@&nbsp;</font><font style='background-color: #7900FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7900FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7900FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BLUE_VIOLET = -0x1.bb1f1p125F;
    static { NAMED.put("some blue violet", -0x1.bb1f1p125F); LIST.add(-0x1.bb1f1p125F); }

    /**
     * This color constant "some purple violet" has RGBA8888 code {@code 8F05FFFF}, L 0.56078434, A 0.5764706, B 0.37254903, alpha 1.0, hue 0.75810045, and saturation 0.97720647.
     * It can be represented as a packed float with the constant {@code -0x1.bf271ep125F}.
     * <pre>
     * <font style='background-color: #8F05FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F05FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F05FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F05FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F05FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F05FF'>&nbsp;@&nbsp;</font><font style='background-color: #8F05FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F05FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F05FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_PURPLE_VIOLET = -0x1.bf271ep125F;
    static { NAMED.put("some purple violet", -0x1.bf271ep125F); LIST.add(-0x1.bf271ep125F); }

    /**
     * This color constant "more purple violet" has RGBA8888 code {@code 9B13FEFF}, L 0.5764706, A 0.58431375, B 0.3764706, alpha 1.0, hue 0.76523525, and saturation 0.9631477.
     * It can be represented as a packed float with the constant {@code -0x1.c12b26p125F}.
     * <pre>
     * <font style='background-color: #9B13FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B13FE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B13FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B13FE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B13FE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B13FE'>&nbsp;@&nbsp;</font><font style='background-color: #9B13FE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B13FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B13FE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_PURPLE_VIOLET = -0x1.c12b26p125F;
    static { NAMED.put("more purple violet", -0x1.c12b26p125F); LIST.add(-0x1.c12b26p125F); }

    /**
     * This color constant "more violet purple" has RGBA8888 code {@code A600FFFF}, L 0.5882353, A 0.5921569, B 0.38039216, alpha 1.0, hue 0.775003, and saturation 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.c32f2cp125F}.
     * <pre>
     * <font style='background-color: #A600FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A600FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A600FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A600FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A600FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A600FF'>&nbsp;@&nbsp;</font><font style='background-color: #A600FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A600FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A600FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_VIOLET_PURPLE = -0x1.c32f2cp125F;
    static { NAMED.put("more violet purple", -0x1.c32f2cp125F); LIST.add(-0x1.c32f2cp125F); }

    /**
     * This color constant "some violet purple" has RGBA8888 code {@code B510FFFF}, L 0.60784316, A 0.6, B 0.38431373, alpha 1.0, hue 0.78325164, and saturation 0.98208517.
     * It can be represented as a packed float with the constant {@code -0x1.c53336p125F}.
     * <pre>
     * <font style='background-color: #B510FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B510FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B510FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B510FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B510FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B510FF'>&nbsp;@&nbsp;</font><font style='background-color: #B510FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B510FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B510FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_VIOLET_PURPLE = -0x1.c53336p125F;
    static { NAMED.put("some violet purple", -0x1.c53336p125F); LIST.add(-0x1.c53336p125F); }

    /**
     * This color constant "some magenta purple" has RGBA8888 code {@code BD15FFFF}, L 0.61960787, A 0.6039216, B 0.3882353, alpha 1.0, hue 0.7868559, and saturation 0.941861.
     * It can be represented as a packed float with the constant {@code -0x1.c7353cp125F}.
     * <pre>
     * <font style='background-color: #BD15FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD15FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD15FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD15FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD15FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD15FF'>&nbsp;@&nbsp;</font><font style='background-color: #BD15FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD15FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD15FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_MAGENTA_PURPLE = -0x1.c7353cp125F;
    static { NAMED.put("some magenta purple", -0x1.c7353cp125F); LIST.add(-0x1.c7353cp125F); }

    /**
     * This color constant "more magenta purple" has RGBA8888 code {@code CA11FFFF}, L 0.63529414, A 0.6117647, B 0.39215687, alpha 1.0, hue 0.797148, and saturation 0.9721383.
     * It can be represented as a packed float with the constant {@code -0x1.c93944p125F}.
     * <pre>
     * <font style='background-color: #CA11FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA11FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA11FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA11FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA11FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA11FF'>&nbsp;@&nbsp;</font><font style='background-color: #CA11FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA11FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA11FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_MAGENTA_PURPLE = -0x1.c93944p125F;
    static { NAMED.put("more magenta purple", -0x1.c93944p125F); LIST.add(-0x1.c93944p125F); }

    /**
     * This color constant "more purple magenta" has RGBA8888 code {@code E10AFFFF}, L 0.6627451, A 0.62352943, B 0.40392157, alpha 1.0, hue 0.81248313, and saturation 0.95753956.
     * It can be represented as a packed float with the constant {@code -0x1.cf3f52p125F}.
     * <pre>
     * <font style='background-color: #E10AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E10AFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E10AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E10AFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E10AFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E10AFF'>&nbsp;@&nbsp;</font><font style='background-color: #E10AFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E10AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E10AFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_PURPLE_MAGENTA = -0x1.cf3f52p125F;
    static { NAMED.put("more purple magenta", -0x1.cf3f52p125F); LIST.add(-0x1.cf3f52p125F); }

    /**
     * This color constant "some purple magenta" has RGBA8888 code {@code ED16FCFF}, L 0.68235296, A 0.627451, B 0.40784314, alpha 1.0, hue 0.8202048, and saturation 0.91677696.
     * It can be represented as a packed float with the constant {@code -0x1.d1415cp125F}.
     * <pre>
     * <font style='background-color: #ED16FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED16FC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED16FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ED16FC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ED16FC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ED16FC'>&nbsp;@&nbsp;</font><font style='background-color: #ED16FC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED16FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED16FC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_PURPLE_MAGENTA = -0x1.d1415cp125F;
    static { NAMED.put("some purple magenta", -0x1.d1415cp125F); LIST.add(-0x1.d1415cp125F); }

    /**
     * This color constant "some red magenta" has RGBA8888 code {@code FD15DFFF}, L 0.6862745, A 0.63529414, B 0.43137255, alpha 1.0, hue 0.85035896, and saturation 0.9132806.
     * It can be represented as a packed float with the constant {@code -0x1.dd455ep125F}.
     * <pre>
     * <font style='background-color: #FD15DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD15DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD15DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD15DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD15DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD15DF'>&nbsp;@&nbsp;</font><font style='background-color: #FD15DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD15DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD15DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_RED_MAGENTA = -0x1.dd455ep125F;
    static { NAMED.put("some red magenta", -0x1.dd455ep125F); LIST.add(-0x1.dd455ep125F); }

    /**
     * This color constant "more red magenta" has RGBA8888 code {@code FD1BA4FF}, L 0.6627451, A 0.6313726, B 0.4745098, alpha 1.0, hue 0.89881617, and saturation 0.8848165.
     * It can be represented as a packed float with the constant {@code -0x1.f34352p125F}.
     * <pre>
     * <font style='background-color: #FD1BA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD1BA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD1BA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD1BA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD1BA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD1BA4'>&nbsp;@&nbsp;</font><font style='background-color: #FD1BA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD1BA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD1BA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_RED_MAGENTA = -0x1.f34352p125F;
    static { NAMED.put("more red magenta", -0x1.f34352p125F); LIST.add(-0x1.f34352p125F); }

    /**
     * This color constant "more magenta red" has RGBA8888 code {@code FF1271FF}, L 0.64705884, A 0.627451, B 0.50980395, alpha 1.0, hue 0.93306816, and saturation 0.9271525.
     * It can be represented as a packed float with the constant {@code -0x1.05414ap126F}.
     * <pre>
     * <font style='background-color: #FF1271;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF1271; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF1271;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF1271'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF1271'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF1271'>&nbsp;@&nbsp;</font><font style='background-color: #FF1271; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF1271;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF1271; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_MAGENTA_RED = -0x1.05414ap126F;
    static { NAMED.put("more magenta red", -0x1.05414ap126F); LIST.add(-0x1.05414ap126F); }

    /**
     * This color constant "some magenta red" has RGBA8888 code {@code FE173BFF}, L 0.63529414, A 0.6156863, B 0.54509807, alpha 1.0, hue 0.9744144, and saturation 0.9033586.
     * It can be represented as a packed float with the constant {@code -0x1.173b44p126F}.
     * <pre>
     * <font style='background-color: #FE173B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE173B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE173B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FE173B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FE173B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FE173B'>&nbsp;@&nbsp;</font><font style='background-color: #FE173B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE173B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE173B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_MAGENTA_RED = -0x1.173b44p126F;
    static { NAMED.put("some magenta red", -0x1.173b44p126F); LIST.add(-0x1.173b44p126F); }

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
                final float s1 = ColorTools.saturation(c1), s2 = ColorTools.saturation(c2);
                if(s1 <= 0.05f && s2 > 0.05f)
                    return -1000;
                else if(s1 > 0.05f && s2 <= 0.05f)
                    return 1000;
                else if(s1 <= 0.05f && s2 <= 0.05f)
                    return (int)Math.signum(ColorTools.channelL(c1) - ColorTools.channelL(c2));
                else
                    return 2 * (int)Math.signum(ColorTools.hue(c1) - ColorTools.hue(c2))
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
