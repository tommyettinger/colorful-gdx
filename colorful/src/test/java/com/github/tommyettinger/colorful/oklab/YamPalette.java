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
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922, alpha 0.0, hue 0.625, and saturation 0.017527897.
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
     * This color constant "pure black" has RGBA8888 code {@code 000000FF}, L 0.0, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.017527897.
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
     * This color constant "almost black" has RGBA8888 code {@code 050403FF}, L 0.03529412, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.375, and saturation 0.022535868.
     * It can be represented as a packed float with the constant {@code -0x1.00fe12p126F}.
     * <pre>
     * <font style='background-color: #050403;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #050403; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #050403;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #050403'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #050403'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #050403'>&nbsp;@&nbsp;</font><font style='background-color: #050403; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #050403;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #050403; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_BLACK = -0x1.00fe12p126F;
    static { NAMED.put("almost black", -0x1.00fe12p126F); LIST.add(-0x1.00fe12p126F); }

    /**
     * This color constant "lead black" has RGBA8888 code {@code 0F0D0CFF}, L 0.08235294, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, and saturation 0.037362095.
     * It can be represented as a packed float with the constant {@code -0x1.01002ap126F}.
     * <pre>
     * <font style='background-color: #0F0D0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F0D0C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F0D0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F0D0C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F0D0C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F0D0C'>&nbsp;@&nbsp;</font><font style='background-color: #0F0D0C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F0D0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F0D0C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_BLACK = -0x1.01002ap126F;
    static { NAMED.put("lead black", -0x1.01002ap126F); LIST.add(-0x1.01002ap126F); }

    /**
     * This color constant "black lead" has RGBA8888 code {@code 1A1817FF}, L 0.12941177, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.067607604.
     * It can be represented as a packed float with the constant {@code -0x1.fefe42p125F}.
     * <pre>
     * <font style='background-color: #1A1817;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A1817; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A1817;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1A1817'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1A1817'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1A1817'>&nbsp;@&nbsp;</font><font style='background-color: #1A1817; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A1817;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A1817; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK_LEAD = -0x1.fefe42p125F;
    static { NAMED.put("black lead", -0x1.fefe42p125F); LIST.add(-0x1.fefe42p125F); }

    /**
     * This color constant "pure lead" has RGBA8888 code {@code 292625FF}, L 0.18431373, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, and saturation 0.032267265.
     * It can be represented as a packed float with the constant {@code -0x1.ff005ep125F}.
     * <pre>
     * <font style='background-color: #292625;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #292625; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #292625;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #292625'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #292625'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #292625'>&nbsp;@&nbsp;</font><font style='background-color: #292625; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #292625;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #292625; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_LEAD = -0x1.ff005ep125F;
    static { NAMED.put("pure lead", -0x1.ff005ep125F); LIST.add(-0x1.ff005ep125F); }

    /**
     * This color constant "gray lead" has RGBA8888 code {@code 393534FF}, L 0.23921569, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, and saturation 0.028395193.
     * It can be represented as a packed float with the constant {@code -0x1.ff007ap125F}.
     * <pre>
     * <font style='background-color: #393534;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #393534; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #393534;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #393534'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #393534'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #393534'>&nbsp;@&nbsp;</font><font style='background-color: #393534; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #393534;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #393534; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_LEAD = -0x1.ff007ap125F;
    static { NAMED.put("gray lead", -0x1.ff007ap125F); LIST.add(-0x1.ff007ap125F); }

    /**
     * This color constant "lead gray" has RGBA8888 code {@code 4A4645FF}, L 0.29803923, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, and saturation 0.024478614.
     * It can be represented as a packed float with the constant {@code -0x1.ff0098p125F}.
     * <pre>
     * <font style='background-color: #4A4645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A4645; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A4645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A4645'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A4645'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A4645'>&nbsp;@&nbsp;</font><font style='background-color: #4A4645; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A4645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A4645; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD_GRAY = -0x1.ff0098p125F;
    static { NAMED.put("lead gray", -0x1.ff0098p125F); LIST.add(-0x1.ff0098p125F); }

    /**
     * This color constant "pure gray" has RGBA8888 code {@code 5C5957FF}, L 0.36078432, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.375, and saturation 0.035493992.
     * It can be represented as a packed float with the constant {@code -0x1.00feb8p126F}.
     * <pre>
     * <font style='background-color: #5C5957;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C5957; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C5957;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C5957'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C5957'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C5957'>&nbsp;@&nbsp;</font><font style='background-color: #5C5957; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C5957;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C5957; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_GRAY = -0x1.00feb8p126F;
    static { NAMED.put("pure gray", -0x1.00feb8p126F); LIST.add(-0x1.00feb8p126F); }

    /**
     * This color constant "silver gray" has RGBA8888 code {@code 726E6CFF}, L 0.43529412, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.375, and saturation 0.031550214.
     * It can be represented as a packed float with the constant {@code -0x1.00fedep126F}.
     * <pre>
     * <font style='background-color: #726E6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #726E6C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #726E6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #726E6C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #726E6C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #726E6C'>&nbsp;@&nbsp;</font><font style='background-color: #726E6C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #726E6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #726E6C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GRAY = -0x1.00fedep126F;
    static { NAMED.put("silver gray", -0x1.00fedep126F); LIST.add(-0x1.00fedep126F); }

    /**
     * This color constant "gray silver" has RGBA8888 code {@code 878381FF}, L 0.50980395, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.375, and saturation 0.028395193.
     * It can be represented as a packed float with the constant {@code -0x1.00ff04p126F}.
     * <pre>
     * <font style='background-color: #878381;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #878381; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #878381;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #878381'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #878381'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #878381'>&nbsp;@&nbsp;</font><font style='background-color: #878381; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #878381;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #878381; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_SILVER = -0x1.00ff04p126F;
    static { NAMED.put("gray silver", -0x1.00ff04p126F); LIST.add(-0x1.00ff04p126F); }

    /**
     * This color constant "pure silver" has RGBA8888 code {@code 9D9997FF}, L 0.5882353, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.037362095.
     * It can be represented as a packed float with the constant {@code -0x1.feff2cp125F}.
     * <pre>
     * <font style='background-color: #9D9997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D9997; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D9997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D9997'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D9997'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D9997'>&nbsp;@&nbsp;</font><font style='background-color: #9D9997; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D9997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D9997; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_SILVER = -0x1.feff2cp125F;
    static { NAMED.put("pure silver", -0x1.feff2cp125F); LIST.add(-0x1.feff2cp125F); }

    /**
     * This color constant "white silver" has RGBA8888 code {@code B6B2B0FF}, L 0.68235296, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.035493992.
     * It can be represented as a packed float with the constant {@code -0x1.feff5cp125F}.
     * <pre>
     * <font style='background-color: #B6B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B2B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #B6B2B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B2B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE_SILVER = -0x1.feff5cp125F;
    static { NAMED.put("white silver", -0x1.feff5cp125F); LIST.add(-0x1.feff5cp125F); }

    /**
     * This color constant "silver white" has RGBA8888 code {@code CFCAC8FF}, L 0.7764706, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, and saturation 0.04732532.
     * It can be represented as a packed float with the constant {@code -0x1.ff018cp125F}.
     * <pre>
     * <font style='background-color: #CFCAC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFCAC8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFCAC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFCAC8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFCAC8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFCAC8'>&nbsp;@&nbsp;</font><font style='background-color: #CFCAC8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFCAC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFCAC8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_WHITE = -0x1.ff018cp125F;
    static { NAMED.put("silver white", -0x1.ff018cp125F); LIST.add(-0x1.ff018cp125F); }

    /**
     * This color constant "almost white" has RGBA8888 code {@code E9E4E2FF}, L 0.8862745, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, and saturation 0.08873498.
     * It can be represented as a packed float with the constant {@code -0x1.ff01c4p125F}.
     * <pre>
     * <font style='background-color: #E9E4E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9E4E2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9E4E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9E4E2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9E4E2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9E4E2'>&nbsp;@&nbsp;</font><font style='background-color: #E9E4E2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9E4E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9E4E2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ALMOST_WHITE = -0x1.ff01c4p125F;
    static { NAMED.put("almost white", -0x1.ff01c4p125F); LIST.add(-0x1.ff01c4p125F); }

    /**
     * This color constant "pure white" has RGBA8888 code {@code FBFFFFFF}, L 0.99607843, A 0.49803922, B 0.49803922, alpha 1.0, hue 0.625, and saturation 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.fefffcp125F}.
     * <pre>
     * <font style='background-color: #FBFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FBFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FBFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FBFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FBFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURE_WHITE = -0x1.fefffcp125F;
    static { NAMED.put("pure white", -0x1.fefffcp125F); LIST.add(-0x1.fefffcp125F); }

    /**
     * This color constant "darker gray red" has RGBA8888 code {@code 2B1C1BFF}, L 0.16078432, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.08601887, and saturation 0.17217113.
     * It can be represented as a packed float with the constant {@code -0x1.030452p126F}.
     * <pre>
     * <font style='background-color: #2B1C1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B1C1B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B1C1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2B1C1B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2B1C1B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2B1C1B'>&nbsp;@&nbsp;</font><font style='background-color: #2B1C1B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B1C1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B1C1B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_RED = -0x1.030452p126F;
    static { NAMED.put("darker gray red", -0x1.030452p126F); LIST.add(-0x1.030452p126F); }

    /**
     * This color constant "dark gray red" has RGBA8888 code {@code 574241FF}, L 0.3019608, A 0.5137255, B 0.5019608, alpha 1.0, hue 0.022596559, and saturation 0.15103824.
     * It can be represented as a packed float with the constant {@code -0x1.01069ap126F}.
     * <pre>
     * <font style='background-color: #574241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #574241; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #574241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #574241'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #574241'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #574241'>&nbsp;@&nbsp;</font><font style='background-color: #574241; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #574241;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #574241; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_RED = -0x1.01069ap126F;
    static { NAMED.put("dark gray red", -0x1.01069ap126F); LIST.add(-0x1.01069ap126F); }

    /**
     * This color constant "light gray red" has RGBA8888 code {@code 8C7472FF}, L 0.4745098, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.08601887, and saturation 0.092917755.
     * It can be represented as a packed float with the constant {@code -0x1.0304f2p126F}.
     * <pre>
     * <font style='background-color: #8C7472;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C7472; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C7472;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C7472'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C7472'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C7472'>&nbsp;@&nbsp;</font><font style='background-color: #8C7472; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C7472;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C7472; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_RED = -0x1.0304f2p126F;
    static { NAMED.put("light gray red", -0x1.0304f2p126F); LIST.add(-0x1.0304f2p126F); }

    /**
     * This color constant "lighter gray red" has RGBA8888 code {@code C9ADAAFF}, L 0.6862745, A 0.5137255, B 0.5058824, alpha 1.0, hue 0.06443131, and saturation 0.24663351.
     * It can be represented as a packed float with the constant {@code -0x1.03075ep126F}.
     * <pre>
     * <font style='background-color: #C9ADAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9ADAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9ADAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9ADAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9ADAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9ADAA'>&nbsp;@&nbsp;</font><font style='background-color: #C9ADAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9ADAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9ADAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_RED = -0x1.03075ep126F;
    static { NAMED.put("lighter gray red", -0x1.03075ep126F); LIST.add(-0x1.03075ep126F); }

    /**
     * This color constant "darker gray brown" has RGBA8888 code {@code 2D231CFF}, L 0.18039216, A 0.5058824, B 0.50980395, alpha 1.0, hue 0.16398115, and saturation 0.22514686.
     * It can be represented as a packed float with the constant {@code -0x1.05025cp126F}.
     * <pre>
     * <font style='background-color: #2D231C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D231C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D231C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D231C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D231C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D231C'>&nbsp;@&nbsp;</font><font style='background-color: #2D231C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D231C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D231C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_BROWN = -0x1.05025cp126F;
    static { NAMED.put("darker gray brown", -0x1.05025cp126F); LIST.add(-0x1.05025cp126F); }

    /**
     * This color constant "dark gray brown" has RGBA8888 code {@code 594D44FF}, L 0.3254902, A 0.5019608, B 0.50980395, alpha 1.0, hue 0.21857657, and saturation 0.18959318.
     * It can be represented as a packed float with the constant {@code -0x1.0500a6p126F}.
     * <pre>
     * <font style='background-color: #594D44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #594D44; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #594D44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #594D44'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #594D44'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #594D44'>&nbsp;@&nbsp;</font><font style='background-color: #594D44; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #594D44;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #594D44; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_BROWN = -0x1.0500a6p126F;
    static { NAMED.put("dark gray brown", -0x1.0500a6p126F); LIST.add(-0x1.0500a6p126F); }

    /**
     * This color constant "light gray brown" has RGBA8888 code {@code 8F8176FF}, L 0.50980395, A 0.5019608, B 0.50980395, alpha 1.0, hue 0.21857657, and saturation 0.14219488.
     * It can be represented as a packed float with the constant {@code -0x1.050104p126F}.
     * <pre>
     * <font style='background-color: #8F8176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8176; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8176'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8176'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8176'>&nbsp;@&nbsp;</font><font style='background-color: #8F8176; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8176;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8176; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_BROWN = -0x1.050104p126F;
    static { NAMED.put("light gray brown", -0x1.050104p126F); LIST.add(-0x1.050104p126F); }

    /**
     * This color constant "lighter gray brown" has RGBA8888 code {@code CCBCB0FF}, L 0.7294118, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.19880433, and saturation 0.08818552.
     * It can be represented as a packed float with the constant {@code -0x1.030174p126F}.
     * <pre>
     * <font style='background-color: #CCBCB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCBCB0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCBCB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CCBCB0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CCBCB0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CCBCB0'>&nbsp;@&nbsp;</font><font style='background-color: #CCBCB0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCBCB0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCBCB0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_BROWN = -0x1.030174p126F;
    static { NAMED.put("lighter gray brown", -0x1.030174p126F); LIST.add(-0x1.030174p126F); }

    /**
     * This color constant "darker gray orange" has RGBA8888 code {@code 2D241DFF}, L 0.18039216, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.19880433, and saturation 0.13802952.
     * It can be represented as a packed float with the constant {@code -0x1.03005cp126F}.
     * <pre>
     * <font style='background-color: #2D241D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D241D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D241D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D241D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D241D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D241D'>&nbsp;@&nbsp;</font><font style='background-color: #2D241D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D241D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D241D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_ORANGE = -0x1.03005cp126F;
    static { NAMED.put("darker gray orange", -0x1.03005cp126F); LIST.add(-0x1.03005cp126F); }

    /**
     * This color constant "dark gray orange" has RGBA8888 code {@code 5B4F46FF}, L 0.33333334, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.19880433, and saturation 0.113381386.
     * It can be represented as a packed float with the constant {@code -0x1.0300aap126F}.
     * <pre>
     * <font style='background-color: #5B4F46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B4F46; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B4F46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B4F46'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B4F46'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B4F46'>&nbsp;@&nbsp;</font><font style='background-color: #5B4F46; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B4F46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B4F46; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_ORANGE = -0x1.0300aap126F;
    static { NAMED.put("dark gray orange", -0x1.0300aap126F); LIST.add(-0x1.0300aap126F); }

    /**
     * This color constant "light gray orange" has RGBA8888 code {@code 908277FF}, L 0.50980395, A 0.5019608, B 0.50980395, alpha 1.0, hue 0.21857657, and saturation 0.14219488.
     * It can be represented as a packed float with the constant {@code -0x1.050104p126F}.
     * <pre>
     * <font style='background-color: #908277;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #908277; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #908277;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #908277'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #908277'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #908277'>&nbsp;@&nbsp;</font><font style='background-color: #908277; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #908277;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #908277; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_ORANGE = -0x1.050104p126F;
    static { NAMED.put("light gray orange", -0x1.050104p126F); LIST.add(-0x1.050104p126F); }

    /**
     * This color constant "lighter gray orange" has RGBA8888 code {@code CFBEB2FF}, L 0.7411765, A 0.5058824, B 0.50980395, alpha 1.0, hue 0.16398115, and saturation 0.20185581.
     * It can be represented as a packed float with the constant {@code -0x1.05037ap126F}.
     * <pre>
     * <font style='background-color: #CFBEB2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFBEB2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFBEB2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFBEB2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFBEB2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFBEB2'>&nbsp;@&nbsp;</font><font style='background-color: #CFBEB2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFBEB2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFBEB2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_ORANGE = -0x1.05037ap126F;
    static { NAMED.put("lighter gray orange", -0x1.05037ap126F); LIST.add(-0x1.05037ap126F); }

    /**
     * This color constant "darker gray saffron" has RGBA8888 code {@code 2E2820FF}, L 0.19215687, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.19880433, and saturation 0.13802952.
     * It can be represented as a packed float with the constant {@code -0x1.030062p126F}.
     * <pre>
     * <font style='background-color: #2E2820;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2E2820; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2E2820;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2E2820'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2E2820'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2E2820'>&nbsp;@&nbsp;</font><font style='background-color: #2E2820; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2E2820;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2E2820; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_SAFFRON = -0x1.030062p126F;
    static { NAMED.put("darker gray saffron", -0x1.030062p126F); LIST.add(-0x1.030062p126F); }

    /**
     * This color constant "dark gray saffron" has RGBA8888 code {@code 5C554BFF}, L 0.34901962, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.19880433, and saturation 0.109471686.
     * It can be represented as a packed float with the constant {@code -0x1.0300b2p126F}.
     * <pre>
     * <font style='background-color: #5C554B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C554B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C554B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C554B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C554B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C554B'>&nbsp;@&nbsp;</font><font style='background-color: #5C554B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C554B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C554B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_SAFFRON = -0x1.0300b2p126F;
    static { NAMED.put("dark gray saffron", -0x1.0300b2p126F); LIST.add(-0x1.0300b2p126F); }

    /**
     * This color constant "light gray saffron" has RGBA8888 code {@code 938B7EFF}, L 0.5372549, A 0.49803922, B 0.50980395, alpha 1.0, hue 0.28142345, and saturation 0.13835177.
     * It can be represented as a packed float with the constant {@code -0x1.04ff12p126F}.
     * <pre>
     * <font style='background-color: #938B7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #938B7E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #938B7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #938B7E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #938B7E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #938B7E'>&nbsp;@&nbsp;</font><font style='background-color: #938B7E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #938B7E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #938B7E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_SAFFRON = -0x1.04ff12p126F;
    static { NAMED.put("light gray saffron", -0x1.04ff12p126F); LIST.add(-0x1.04ff12p126F); }

    /**
     * This color constant "lighter gray saffron" has RGBA8888 code {@code D3CABCFF}, L 0.7764706, A 0.49803922, B 0.5058824, alpha 1.0, hue 0.30119568, and saturation 0.06613914.
     * It can be represented as a packed float with the constant {@code -0x1.02ff8cp126F}.
     * <pre>
     * <font style='background-color: #D3CABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3CABC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3CABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3CABC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3CABC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3CABC'>&nbsp;@&nbsp;</font><font style='background-color: #D3CABC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3CABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3CABC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_SAFFRON = -0x1.02ff8cp126F;
    static { NAMED.put("lighter gray saffron", -0x1.02ff8cp126F); LIST.add(-0x1.02ff8cp126F); }

    /**
     * This color constant "darker gray yellow" has RGBA8888 code {@code 323425FF}, L 0.22352941, A 0.49019608, B 0.50980395, alpha 1.0, hue 0.375, and saturation 0.22899348.
     * It can be represented as a packed float with the constant {@code -0x1.04fa72p126F}.
     * <pre>
     * <font style='background-color: #323425;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #323425; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #323425;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #323425'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #323425'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #323425'>&nbsp;@&nbsp;</font><font style='background-color: #323425; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #323425;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #323425; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_YELLOW = -0x1.04fa72p126F;
    static { NAMED.put("darker gray yellow", -0x1.04fa72p126F); LIST.add(-0x1.04fa72p126F); }

    /**
     * This color constant "dark gray yellow" has RGBA8888 code {@code 626451FF}, L 0.3882353, A 0.49411765, B 0.5137255, alpha 1.0, hue 0.3144313, and saturation 0.23892622.
     * It can be represented as a packed float with the constant {@code -0x1.06fcc6p126F}.
     * <pre>
     * <font style='background-color: #626451;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626451; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626451;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #626451'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #626451'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #626451'>&nbsp;@&nbsp;</font><font style='background-color: #626451; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626451;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626451; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_YELLOW = -0x1.06fcc6p126F;
    static { NAMED.put("dark gray yellow", -0x1.06fcc6p126F); LIST.add(-0x1.06fcc6p126F); }

    /**
     * This color constant "light gray yellow" has RGBA8888 code {@code 9A9D87FF}, L 0.5882353, A 0.49019608, B 0.5137255, alpha 1.0, hue 0.3487287, and saturation 0.18374595.
     * It can be represented as a packed float with the constant {@code -0x1.06fb2cp126F}.
     * <pre>
     * <font style='background-color: #9A9D87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A9D87; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A9D87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A9D87'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A9D87'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A9D87'>&nbsp;@&nbsp;</font><font style='background-color: #9A9D87; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A9D87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A9D87; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_YELLOW = -0x1.06fb2cp126F;
    static { NAMED.put("light gray yellow", -0x1.06fb2cp126F); LIST.add(-0x1.06fb2cp126F); }

    /**
     * This color constant "lighter gray yellow" has RGBA8888 code {@code D9DCC4FF}, L 0.8352941, A 0.49411765, B 0.50980395, alpha 1.0, hue 0.33601886, and saturation 0.10453247.
     * It can be represented as a packed float with the constant {@code -0x1.04fdaap126F}.
     * <pre>
     * <font style='background-color: #D9DCC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9DCC4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9DCC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9DCC4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9DCC4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9DCC4'>&nbsp;@&nbsp;</font><font style='background-color: #D9DCC4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9DCC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9DCC4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_YELLOW = -0x1.04fdaap126F;
    static { NAMED.put("lighter gray yellow", -0x1.04fdaap126F); LIST.add(-0x1.04fdaap126F); }

    /**
     * This color constant "darker gray lime" has RGBA8888 code {@code 293523FF}, L 0.21960784, A 0.48235294, B 0.5137255, alpha 1.0, hue 0.39477962, and saturation 0.32704192.
     * It can be represented as a packed float with the constant {@code -0x1.06f67p126F}.
     * <pre>
     * <font style='background-color: #293523;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #293523; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #293523;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #293523'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #293523'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #293523'>&nbsp;@&nbsp;</font><font style='background-color: #293523; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #293523;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #293523; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_LIME = -0x1.06f67p126F;
    static { NAMED.put("darker gray lime", -0x1.06f67p126F); LIST.add(-0x1.06f67p126F); }

    /**
     * This color constant "dark gray lime" has RGBA8888 code {@code 56644FFF}, L 0.38039216, A 0.4862745, B 0.50980395, alpha 1.0, hue 0.4012713, and saturation 0.17991792.
     * It can be represented as a packed float with the constant {@code -0x1.04f8c2p126F}.
     * <pre>
     * <font style='background-color: #56644F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56644F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56644F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #56644F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #56644F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #56644F'>&nbsp;@&nbsp;</font><font style='background-color: #56644F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #56644F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #56644F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_LIME = -0x1.04f8c2p126F;
    static { NAMED.put("dark gray lime", -0x1.04f8c2p126F); LIST.add(-0x1.04f8c2p126F); }

    /**
     * This color constant "light gray lime" has RGBA8888 code {@code 8C9C83FF}, L 0.57254905, A 0.4862745, B 0.5137255, alpha 1.0, hue 0.375, and saturation 0.1840429.
     * It can be represented as a packed float with the constant {@code -0x1.06f924p126F}.
     * <pre>
     * <font style='background-color: #8C9C83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C9C83; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C9C83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C9C83'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C9C83'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C9C83'>&nbsp;@&nbsp;</font><font style='background-color: #8C9C83; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C9C83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C9C83; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_LIME = -0x1.06f924p126F;
    static { NAMED.put("light gray lime", -0x1.06f924p126F); LIST.add(-0x1.06f924p126F); }

    /**
     * This color constant "lighter gray lime" has RGBA8888 code {@code CADCC0FF}, L 0.81960785, A 0.4862745, B 0.5137255, alpha 1.0, hue 0.375, and saturation 0.14833307.
     * It can be represented as a packed float with the constant {@code -0x1.06f9a2p126F}.
     * <pre>
     * <font style='background-color: #CADCC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CADCC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CADCC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CADCC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CADCC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CADCC0'>&nbsp;@&nbsp;</font><font style='background-color: #CADCC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CADCC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CADCC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_LIME = -0x1.06f9a2p126F;
    static { NAMED.put("lighter gray lime", -0x1.06f9a2p126F); LIST.add(-0x1.06f9a2p126F); }

    /**
     * This color constant "darker gray green" has RGBA8888 code {@code 1D2F21FF}, L 0.19607843, A 0.47843137, B 0.50980395, alpha 1.0, hue 0.43210676, and saturation 0.44927523.
     * It can be represented as a packed float with the constant {@code -0x1.04f464p126F}.
     * <pre>
     * <font style='background-color: #1D2F21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D2F21; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D2F21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D2F21'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D2F21'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D2F21'>&nbsp;@&nbsp;</font><font style='background-color: #1D2F21; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D2F21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D2F21; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_GREEN = -0x1.04f464p126F;
    static { NAMED.put("darker gray green", -0x1.04f464p126F); LIST.add(-0x1.04f464p126F); }

    /**
     * This color constant "dark gray green" has RGBA8888 code {@code 405645FF}, L 0.3254902, A 0.47843137, B 0.5058824, alpha 1.0, hue 0.45763126, and saturation 0.3815489.
     * It can be represented as a packed float with the constant {@code -0x1.02f4a6p126F}.
     * <pre>
     * <font style='background-color: #405645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #405645; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #405645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #405645'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #405645'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #405645'>&nbsp;@&nbsp;</font><font style='background-color: #405645; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #405645;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #405645; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_GREEN = -0x1.02f4a6p126F;
    static { NAMED.put("dark gray green", -0x1.02f4a6p126F); LIST.add(-0x1.02f4a6p126F); }

    /**
     * This color constant "light gray green" has RGBA8888 code {@code 6A836FFF}, L 0.47843137, A 0.47843137, B 0.50980395, alpha 1.0, hue 0.43210676, and saturation 0.27569163.
     * It can be represented as a packed float with the constant {@code -0x1.04f4f4p126F}.
     * <pre>
     * <font style='background-color: #6A836F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A836F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A836F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A836F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A836F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A836F'>&nbsp;@&nbsp;</font><font style='background-color: #6A836F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A836F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A836F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_GREEN = -0x1.04f4f4p126F;
    static { NAMED.put("light gray green", -0x1.04f4f4p126F); LIST.add(-0x1.04f4f4p126F); }

    /**
     * This color constant "lighter gray green" has RGBA8888 code {@code 9BB6A0FF}, L 0.6627451, A 0.48235294, B 0.50980395, alpha 1.0, hue 0.41928825, and saturation 0.17820701.
     * It can be represented as a packed float with the constant {@code -0x1.04f752p126F}.
     * <pre>
     * <font style='background-color: #9BB6A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BB6A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BB6A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9BB6A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9BB6A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9BB6A0'>&nbsp;@&nbsp;</font><font style='background-color: #9BB6A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BB6A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BB6A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_GREEN = -0x1.04f752p126F;
    static { NAMED.put("lighter gray green", -0x1.04f752p126F); LIST.add(-0x1.04f752p126F); }

    /**
     * This color constant "darker gray cyan" has RGBA8888 code {@code 1F3335FF}, L 0.21176471, A 0.48235294, B 0.49019608, alpha 1.0, hue 0.5807117, and saturation 0.5168003.
     * It can be represented as a packed float with the constant {@code -0x1.faf66cp125F}.
     * <pre>
     * <font style='background-color: #1F3335;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F3335; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F3335;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1F3335'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1F3335'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1F3335'>&nbsp;@&nbsp;</font><font style='background-color: #1F3335; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F3335;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F3335; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_CYAN = -0x1.faf66cp125F;
    static { NAMED.put("darker gray cyan", -0x1.faf66cp125F); LIST.add(-0x1.faf66cp125F); }

    /**
     * This color constant "dark gray cyan" has RGBA8888 code {@code 4A6264FF}, L 0.37254903, A 0.48235294, B 0.49411765, alpha 1.0, hue 0.5511957, and saturation 0.3663091.
     * It can be represented as a packed float with the constant {@code -0x1.fcf6bep125F}.
     * <pre>
     * <font style='background-color: #4A6264;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A6264; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A6264;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A6264'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A6264'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A6264'>&nbsp;@&nbsp;</font><font style='background-color: #4A6264; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A6264;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A6264; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_CYAN = -0x1.fcf6bep125F;
    static { NAMED.put("dark gray cyan", -0x1.fcf6bep125F); LIST.add(-0x1.fcf6bep125F); }

    /**
     * This color constant "light gray cyan" has RGBA8888 code {@code 809B9EFF}, L 0.5686275, A 0.4862745, B 0.49019608, alpha 1.0, hue 0.59872866, and saturation 0.24674456.
     * It can be represented as a packed float with the constant {@code -0x1.faf922p125F}.
     * <pre>
     * <font style='background-color: #809B9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #809B9E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #809B9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #809B9E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #809B9E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #809B9E'>&nbsp;@&nbsp;</font><font style='background-color: #809B9E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #809B9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #809B9E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_CYAN = -0x1.faf922p125F;
    static { NAMED.put("light gray cyan", -0x1.faf922p125F); LIST.add(-0x1.faf922p125F); }

    /**
     * This color constant "lighter gray cyan" has RGBA8888 code {@code BDDCDFFF}, L 0.81960785, A 0.48235294, B 0.49019608, alpha 1.0, hue 0.5807117, and saturation 0.3230002.
     * It can be represented as a packed float with the constant {@code -0x1.faf7a2p125F}.
     * <pre>
     * <font style='background-color: #BDDCDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDDCDF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDDCDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BDDCDF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BDDCDF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BDDCDF'>&nbsp;@&nbsp;</font><font style='background-color: #BDDCDF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDDCDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDDCDF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_CYAN = -0x1.faf7a2p125F;
    static { NAMED.put("lighter gray cyan", -0x1.faf7a2p125F); LIST.add(-0x1.faf7a2p125F); }

    /**
     * This color constant "darker gray blue" has RGBA8888 code {@code 0F1B2BFF}, L 0.13725491, A 0.49019608, B 0.47843137, alpha 1.0, hue 0.68210673, and saturation 0.41829073.
     * It can be represented as a packed float with the constant {@code -0x1.f4fa46p125F}.
     * <pre>
     * <font style='background-color: #0F1B2B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F1B2B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F1B2B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F1B2B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F1B2B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F1B2B'>&nbsp;@&nbsp;</font><font style='background-color: #0F1B2B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F1B2B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F1B2B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_BLUE = -0x1.f4fa46p125F;
    static { NAMED.put("darker gray blue", -0x1.f4fa46p125F); LIST.add(-0x1.f4fa46p125F); }

    /**
     * This color constant "dark gray blue" has RGBA8888 code {@code 334459FF}, L 0.28235295, A 0.49019608, B 0.4745098, alpha 1.0, hue 0.69157475, and saturation 0.38841695.
     * It can be represented as a packed float with the constant {@code -0x1.f2fa9p125F}.
     * <pre>
     * <font style='background-color: #334459;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #334459; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #334459;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #334459'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #334459'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #334459'>&nbsp;@&nbsp;</font><font style='background-color: #334459; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #334459;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #334459; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_BLUE = -0x1.f2fa9p125F;
    static { NAMED.put("dark gray blue", -0x1.f2fa9p125F); LIST.add(-0x1.f2fa9p125F); }

    /**
     * This color constant "light gray blue" has RGBA8888 code {@code 637690FF}, L 0.45490196, A 0.49411765, B 0.4745098, alpha 1.0, hue 0.7139029, and saturation 0.23093078.
     * It can be represented as a packed float with the constant {@code -0x1.f2fce8p125F}.
     * <pre>
     * <font style='background-color: #637690;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #637690; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #637690;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #637690'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #637690'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #637690'>&nbsp;@&nbsp;</font><font style='background-color: #637690; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #637690;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #637690; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_BLUE = -0x1.f2fce8p125F;
    static { NAMED.put("light gray blue", -0x1.f2fce8p125F); LIST.add(-0x1.f2fce8p125F); }

    /**
     * This color constant "lighter gray blue" has RGBA8888 code {@code 9BB1CEFF}, L 0.6666667, A 0.49411765, B 0.4745098, alpha 1.0, hue 0.7139029, and saturation 0.41856202.
     * It can be represented as a packed float with the constant {@code -0x1.f2fd54p125F}.
     * <pre>
     * <font style='background-color: #9BB1CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BB1CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BB1CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9BB1CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9BB1CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9BB1CE'>&nbsp;@&nbsp;</font><font style='background-color: #9BB1CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BB1CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BB1CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_BLUE = -0x1.f2fd54p125F;
    static { NAMED.put("lighter gray blue", -0x1.f2fd54p125F); LIST.add(-0x1.f2fd54p125F); }

    /**
     * This color constant "darker gray violet" has RGBA8888 code {@code 1D182CFF}, L 0.14117648, A 0.5058824, B 0.4745098, alpha 1.0, hue 0.7860971, and saturation 0.30440876.
     * It can be represented as a packed float with the constant {@code -0x1.f30248p125F}.
     * <pre>
     * <font style='background-color: #1D182C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D182C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D182C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D182C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D182C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D182C'>&nbsp;@&nbsp;</font><font style='background-color: #1D182C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D182C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D182C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_VIOLET = -0x1.f30248p125F;
    static { NAMED.put("darker gray violet", -0x1.f30248p125F); LIST.add(-0x1.f30248p125F); }

    /**
     * This color constant "dark gray violet" has RGBA8888 code {@code 45405AFF}, L 0.28627452, A 0.5058824, B 0.47843137, alpha 1.0, hue 0.79236877, and saturation 0.1708428.
     * It can be represented as a packed float with the constant {@code -0x1.f50292p125F}.
     * <pre>
     * <font style='background-color: #45405A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #45405A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #45405A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #45405A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #45405A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #45405A'>&nbsp;@&nbsp;</font><font style='background-color: #45405A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #45405A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #45405A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_VIOLET = -0x1.f50292p125F;
    static { NAMED.put("dark gray violet", -0x1.f50292p125F); LIST.add(-0x1.f50292p125F); }

    /**
     * This color constant "light gray violet" has RGBA8888 code {@code 777090FF}, L 0.45490196, A 0.50980395, B 0.4745098, alpha 1.0, hue 0.80842525, and saturation 0.21848454.
     * It can be represented as a packed float with the constant {@code -0x1.f304e8p125F}.
     * <pre>
     * <font style='background-color: #777090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #777090; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #777090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #777090'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #777090'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #777090'>&nbsp;@&nbsp;</font><font style='background-color: #777090; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #777090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #777090; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_VIOLET = -0x1.f304e8p125F;
    static { NAMED.put("light gray violet", -0x1.f304e8p125F); LIST.add(-0x1.f304e8p125F); }

    /**
     * This color constant "lighter gray violet" has RGBA8888 code {@code B2AACFFF}, L 0.67058825, A 0.50980395, B 0.4745098, alpha 1.0, hue 0.80842525, and saturation 0.41126502.
     * It can be represented as a packed float with the constant {@code -0x1.f30556p125F}.
     * <pre>
     * <font style='background-color: #B2AACF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2AACF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2AACF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2AACF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2AACF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2AACF'>&nbsp;@&nbsp;</font><font style='background-color: #B2AACF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2AACF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2AACF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_VIOLET = -0x1.f30556p125F;
    static { NAMED.put("lighter gray violet", -0x1.f30556p125F); LIST.add(-0x1.f30556p125F); }

    /**
     * This color constant "darker gray purple" has RGBA8888 code {@code 251B2EFF}, L 0.16078432, A 0.5137255, B 0.47843137, alpha 1.0, hue 0.84020853, and saturation 0.29748943.
     * It can be represented as a packed float with the constant {@code -0x1.f50652p125F}.
     * <pre>
     * <font style='background-color: #251B2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #251B2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #251B2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #251B2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #251B2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #251B2E'>&nbsp;@&nbsp;</font><font style='background-color: #251B2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #251B2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #251B2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_PURPLE = -0x1.f50652p125F;
    static { NAMED.put("darker gray purple", -0x1.f50652p125F); LIST.add(-0x1.f50652p125F); }

    /**
     * This color constant "dark gray purple" has RGBA8888 code {@code 4F435BFF}, L 0.3019608, A 0.50980395, B 0.47843137, alpha 1.0, hue 0.81789327, and saturation 0.18662202.
     * It can be represented as a packed float with the constant {@code -0x1.f5049ap125F}.
     * <pre>
     * <font style='background-color: #4F435B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F435B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F435B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F435B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F435B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F435B'>&nbsp;@&nbsp;</font><font style='background-color: #4F435B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F435B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F435B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_PURPLE = -0x1.f5049ap125F;
    static { NAMED.put("dark gray purple", -0x1.f5049ap125F); LIST.add(-0x1.f5049ap125F); }

    /**
     * This color constant "light gray purple" has RGBA8888 code {@code 837491FF}, L 0.47843137, A 0.5137255, B 0.47843137, alpha 1.0, hue 0.84020853, and saturation 0.19249317.
     * It can be represented as a packed float with the constant {@code -0x1.f506f4p125F}.
     * <pre>
     * <font style='background-color: #837491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #837491; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #837491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #837491'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #837491'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #837491'>&nbsp;@&nbsp;</font><font style='background-color: #837491; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #837491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #837491; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_PURPLE = -0x1.f506f4p125F;
    static { NAMED.put("light gray purple", -0x1.f506f4p125F); LIST.add(-0x1.f506f4p125F); }

    /**
     * This color constant "lighter gray purple" has RGBA8888 code {@code BFAFD0FF}, L 0.69803923, A 0.5137255, B 0.47843137, alpha 1.0, hue 0.84020853, and saturation 0.38498634.
     * It can be represented as a packed float with the constant {@code -0x1.f50764p125F}.
     * <pre>
     * <font style='background-color: #BFAFD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFAFD0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFAFD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFAFD0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFAFD0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFAFD0'>&nbsp;@&nbsp;</font><font style='background-color: #BFAFD0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFAFD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFAFD0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_PURPLE = -0x1.f50764p125F;
    static { NAMED.put("lighter gray purple", -0x1.f50764p125F); LIST.add(-0x1.f50764p125F); }

    /**
     * This color constant "darker gray magenta" has RGBA8888 code {@code 2C1D2EFF}, L 0.17254902, A 0.5176471, B 0.48235294, alpha 1.0, hue 0.875, and saturation 0.297159.
     * It can be represented as a packed float with the constant {@code -0x1.f70858p125F}.
     * <pre>
     * <font style='background-color: #2C1D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2C1D2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2C1D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2C1D2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2C1D2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2C1D2E'>&nbsp;@&nbsp;</font><font style='background-color: #2C1D2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2C1D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2C1D2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARKER_GRAY_MAGENTA = -0x1.f70858p125F;
    static { NAMED.put("darker gray magenta", -0x1.f70858p125F); LIST.add(-0x1.f70858p125F); }

    /**
     * This color constant "dark gray magenta" has RGBA8888 code {@code 5A475CFF}, L 0.32156864, A 0.5137255, B 0.4862745, alpha 1.0, hue 0.875, and saturation 0.16292322.
     * It can be represented as a packed float with the constant {@code -0x1.f906a4p125F}.
     * <pre>
     * <font style='background-color: #5A475C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A475C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A475C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A475C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A475C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A475C'>&nbsp;@&nbsp;</font><font style='background-color: #5A475C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A475C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A475C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY_MAGENTA = -0x1.f906a4p125F;
    static { NAMED.put("dark gray magenta", -0x1.f906a4p125F); LIST.add(-0x1.f906a4p125F); }

    /**
     * This color constant "light gray magenta" has RGBA8888 code {@code 907A93FF}, L 0.5019608, A 0.5137255, B 0.4862745, alpha 1.0, hue 0.875, and saturation 0.12741432.
     * It can be represented as a packed float with the constant {@code -0x1.f907p125F}.
     * <pre>
     * <font style='background-color: #907A93;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #907A93; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #907A93;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #907A93'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #907A93'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #907A93'>&nbsp;@&nbsp;</font><font style='background-color: #907A93; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #907A93;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #907A93; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY_MAGENTA = -0x1.f907p125F;
    static { NAMED.put("light gray magenta", -0x1.f907p125F); LIST.add(-0x1.f907p125F); }

    /**
     * This color constant "lighter gray magenta" has RGBA8888 code {@code CFB5D2FF}, L 0.7294118, A 0.5176471, B 0.48235294, alpha 1.0, hue 0.875, and saturation 0.34534693.
     * It can be represented as a packed float with the constant {@code -0x1.f70974p125F}.
     * <pre>
     * <font style='background-color: #CFB5D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB5D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB5D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CFB5D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CFB5D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CFB5D2'>&nbsp;@&nbsp;</font><font style='background-color: #CFB5D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CFB5D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CFB5D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHTER_GRAY_MAGENTA = -0x1.f70974p125F;
    static { NAMED.put("lighter gray magenta", -0x1.f70974p125F); LIST.add(-0x1.f70974p125F); }

    /**
     * This color constant "drab brown red" has RGBA8888 code {@code 662F28FF}, L 0.27450982, A 0.5372549, B 0.5176471, alpha 1.0, hue 0.070401505, and saturation 0.46902764.
     * It can be represented as a packed float with the constant {@code -0x1.09128cp126F}.
     * <pre>
     * <font style='background-color: #662F28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #662F28; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #662F28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #662F28'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #662F28'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #662F28'>&nbsp;@&nbsp;</font><font style='background-color: #662F28; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #662F28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #662F28; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN_RED = -0x1.09128cp126F;
    static { NAMED.put("drab brown red", -0x1.09128cp126F); LIST.add(-0x1.09128cp126F); }

    /**
     * This color constant "dull brown red" has RGBA8888 code {@code 98584EFF}, L 0.41960785, A 0.53333336, B 0.5176471, alpha 1.0, hue 0.07749419, and saturation 0.32184696.
     * It can be represented as a packed float with the constant {@code -0x1.0910d6p126F}.
     * <pre>
     * <font style='background-color: #98584E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98584E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98584E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98584E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98584E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98584E'>&nbsp;@&nbsp;</font><font style='background-color: #98584E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98584E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98584E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BROWN_RED = -0x1.0910d6p126F;
    static { NAMED.put("dull brown red", -0x1.0910d6p126F); LIST.add(-0x1.0910d6p126F); }

    /**
     * This color constant "pale brown red" has RGBA8888 code {@code CE8579FF}, L 0.5882353, A 0.5372549, B 0.52156866, alpha 1.0, hue 0.08353054, and saturation 0.46894884.
     * It can be represented as a packed float with the constant {@code -0x1.0b132cp126F}.
     * <pre>
     * <font style='background-color: #CE8579;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8579; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8579;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE8579'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE8579'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE8579'>&nbsp;@&nbsp;</font><font style='background-color: #CE8579; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8579;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8579; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN_RED = -0x1.0b132cp126F;
    static { NAMED.put("pale brown red", -0x1.0b132cp126F); LIST.add(-0x1.0b132cp126F); }

    /**
     * This color constant "drab red brown" has RGBA8888 code {@code 663B28FF}, L 0.29803923, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.138223, and saturation 0.5180657.
     * It can be represented as a packed float with the constant {@code -0x1.0d0a98p126F}.
     * <pre>
     * <font style='background-color: #663B28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #663B28; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #663B28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #663B28'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #663B28'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #663B28'>&nbsp;@&nbsp;</font><font style='background-color: #663B28; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #663B28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #663B28; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED_BROWN = -0x1.0d0a98p126F;
    static { NAMED.put("drab red brown", -0x1.0d0a98p126F); LIST.add(-0x1.0d0a98p126F); }

    /**
     * This color constant "dull red brown" has RGBA8888 code {@code 986650FF}, L 0.4509804, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.138223, and saturation 0.40705162.
     * It can be represented as a packed float with the constant {@code -0x1.0d0ae6p126F}.
     * <pre>
     * <font style='background-color: #986650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #986650; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #986650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #986650'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #986650'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #986650'>&nbsp;@&nbsp;</font><font style='background-color: #986650; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #986650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #986650; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_RED_BROWN = -0x1.0d0ae6p126F;
    static { NAMED.put("dull red brown", -0x1.0d0ae6p126F); LIST.add(-0x1.0d0ae6p126F); }

    /**
     * This color constant "pale red brown" has RGBA8888 code {@code D0977EFF}, L 0.6313726, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.138223, and saturation 0.3975853.
     * It can be represented as a packed float with the constant {@code -0x1.0d0b42p126F}.
     * <pre>
     * <font style='background-color: #D0977E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0977E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0977E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0977E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0977E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0977E'>&nbsp;@&nbsp;</font><font style='background-color: #D0977E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0977E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0977E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED_BROWN = -0x1.0d0b42p126F;
    static { NAMED.put("pale red brown", -0x1.0d0b42p126F); LIST.add(-0x1.0d0b42p126F); }

    /**
     * This color constant "drab orange brown" has RGBA8888 code {@code 68412CFF}, L 0.3137255, A 0.5176471, B 0.5254902, alpha 1.0, hue 0.15361187, and saturation 0.51204497.
     * It can be represented as a packed float with the constant {@code -0x1.0d08ap126F}.
     * <pre>
     * <font style='background-color: #68412C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68412C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68412C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68412C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68412C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68412C'>&nbsp;@&nbsp;</font><font style='background-color: #68412C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68412C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68412C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE_BROWN = -0x1.0d08ap126F;
    static { NAMED.put("drab orange brown", -0x1.0d08ap126F); LIST.add(-0x1.0d08ap126F); }

    /**
     * This color constant "dull orange brown" has RGBA8888 code {@code 9A6C54FF}, L 0.46666667, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.138223, and saturation 0.3975853.
     * It can be represented as a packed float with the constant {@code -0x1.0d0aeep126F}.
     * <pre>
     * <font style='background-color: #9A6C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A6C54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A6C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A6C54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A6C54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A6C54'>&nbsp;@&nbsp;</font><font style='background-color: #9A6C54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A6C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A6C54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_ORANGE_BROWN = -0x1.0d0aeep126F;
    static { NAMED.put("dull orange brown", -0x1.0d0aeep126F); LIST.add(-0x1.0d0aeep126F); }

    /**
     * This color constant "pale orange brown" has RGBA8888 code {@code D3A085FF}, L 0.65882355, A 0.5176471, B 0.5254902, alpha 1.0, hue 0.15361187, and saturation 0.39683485.
     * It can be represented as a packed float with the constant {@code -0x1.0d095p126F}.
     * <pre>
     * <font style='background-color: #D3A085;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3A085; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3A085;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3A085'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3A085'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3A085'>&nbsp;@&nbsp;</font><font style='background-color: #D3A085; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3A085;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3A085; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_BROWN = -0x1.0d095p126F;
    static { NAMED.put("pale orange brown", -0x1.0d095p126F); LIST.add(-0x1.0d095p126F); }

    /**
     * This color constant "drab brown orange" has RGBA8888 code {@code 68452FFF}, L 0.32156864, A 0.5176471, B 0.5254902, alpha 1.0, hue 0.15361187, and saturation 0.49604356.
     * It can be represented as a packed float with the constant {@code -0x1.0d08a4p126F}.
     * <pre>
     * <font style='background-color: #68452F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68452F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68452F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68452F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68452F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68452F'>&nbsp;@&nbsp;</font><font style='background-color: #68452F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68452F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68452F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BROWN_ORANGE = -0x1.0d08a4p126F;
    static { NAMED.put("drab brown orange", -0x1.0d08a4p126F); LIST.add(-0x1.0d08a4p126F); }

    /**
     * This color constant "dull brown orange" has RGBA8888 code {@code 9A7258FF}, L 0.47843137, A 0.5176471, B 0.5254902, alpha 1.0, hue 0.15361187, and saturation 0.38715595.
     * It can be represented as a packed float with the constant {@code -0x1.0d08f4p126F}.
     * <pre>
     * <font style='background-color: #9A7258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A7258; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A7258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A7258'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A7258'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A7258'>&nbsp;@&nbsp;</font><font style='background-color: #9A7258; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A7258;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A7258; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BROWN_ORANGE = -0x1.0d08f4p126F;
    static { NAMED.put("dull brown orange", -0x1.0d08f4p126F); LIST.add(-0x1.0d08f4p126F); }

    /**
     * This color constant "pale brown orange" has RGBA8888 code {@code D0A386FF}, L 0.65882355, A 0.5176471, B 0.5254902, alpha 1.0, hue 0.15361187, and saturation 0.39683485.
     * It can be represented as a packed float with the constant {@code -0x1.0d095p126F}.
     * <pre>
     * <font style='background-color: #D0A386;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0A386; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0A386;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0A386'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0A386'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0A386'>&nbsp;@&nbsp;</font><font style='background-color: #D0A386; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0A386;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0A386; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BROWN_ORANGE = -0x1.0d095p126F;
    static { NAMED.put("pale brown orange", -0x1.0d095p126F); LIST.add(-0x1.0d095p126F); }

    /**
     * This color constant "drab saffron orange" has RGBA8888 code {@code 6A4A2DFF}, L 0.33333334, A 0.5137255, B 0.5254902, alpha 1.0, hue 0.17138404, and saturation 0.4781524.
     * It can be represented as a packed float with the constant {@code -0x1.0d06aap126F}.
     * <pre>
     * <font style='background-color: #6A4A2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A4A2D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A4A2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A4A2D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A4A2D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A4A2D'>&nbsp;@&nbsp;</font><font style='background-color: #6A4A2D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A4A2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A4A2D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON_ORANGE = -0x1.0d06aap126F;
    static { NAMED.put("drab saffron orange", -0x1.0d06aap126F); LIST.add(-0x1.0d06aap126F); }

    /**
     * This color constant "dull saffron orange" has RGBA8888 code {@code 9C7756FF}, L 0.49019608, A 0.5137255, B 0.5294118, alpha 1.0, hue 0.18051395, and saturation 0.43731207.
     * It can be represented as a packed float with the constant {@code -0x1.0f06fap126F}.
     * <pre>
     * <font style='background-color: #9C7756;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C7756; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C7756;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C7756'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C7756'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C7756'>&nbsp;@&nbsp;</font><font style='background-color: #9C7756; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C7756;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C7756; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_SAFFRON_ORANGE = -0x1.0f06fap126F;
    static { NAMED.put("dull saffron orange", -0x1.0f06fap126F); LIST.add(-0x1.0f06fap126F); }

    /**
     * This color constant "pale saffron orange" has RGBA8888 code {@code D3AA85FF}, L 0.68235296, A 0.5137255, B 0.5294118, alpha 1.0, hue 0.18051395, and saturation 0.40531364.
     * It can be represented as a packed float with the constant {@code -0x1.0f075cp126F}.
     * <pre>
     * <font style='background-color: #D3AA85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3AA85; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3AA85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3AA85'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3AA85'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3AA85'>&nbsp;@&nbsp;</font><font style='background-color: #D3AA85; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3AA85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3AA85; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON_ORANGE = -0x1.0f075cp126F;
    static { NAMED.put("pale saffron orange", -0x1.0f075cp126F); LIST.add(-0x1.0f075cp126F); }

    /**
     * This color constant "drab orange saffron" has RGBA8888 code {@code 6C5131FF}, L 0.34901962, A 0.5058824, B 0.5294118, alpha 1.0, hue 0.21857657, and saturation 0.54846597.
     * It can be represented as a packed float with the constant {@code -0x1.0f02b2p126F}.
     * <pre>
     * <font style='background-color: #6C5131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C5131; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C5131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C5131'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C5131'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C5131'>&nbsp;@&nbsp;</font><font style='background-color: #6C5131; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C5131;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C5131; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_ORANGE_SAFFRON = -0x1.0f02b2p126F;
    static { NAMED.put("drab orange saffron", -0x1.0f02b2p126F); LIST.add(-0x1.0f02b2p126F); }

    /**
     * This color constant "dull orange saffron" has RGBA8888 code {@code 9E7F5BFF}, L 0.5137255, A 0.50980395, B 0.5294118, alpha 1.0, hue 0.19880433, and saturation 0.42901066.
     * It can be represented as a packed float with the constant {@code -0x1.0f0506p126F}.
     * <pre>
     * <font style='background-color: #9E7F5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E7F5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E7F5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E7F5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E7F5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E7F5B'>&nbsp;@&nbsp;</font><font style='background-color: #9E7F5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E7F5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E7F5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_ORANGE_SAFFRON = -0x1.0f0506p126F;
    static { NAMED.put("dull orange saffron", -0x1.0f0506p126F); LIST.add(-0x1.0f0506p126F); }

    /**
     * This color constant "pale orange saffron" has RGBA8888 code {@code D7B58DFF}, L 0.7137255, A 0.5058824, B 0.5294118, alpha 1.0, hue 0.21857657, and saturation 0.3490238.
     * It can be represented as a packed float with the constant {@code -0x1.0f036cp126F}.
     * <pre>
     * <font style='background-color: #D7B58D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7B58D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7B58D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7B58D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7B58D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7B58D'>&nbsp;@&nbsp;</font><font style='background-color: #D7B58D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7B58D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7B58D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_SAFFRON = -0x1.0f036cp126F;
    static { NAMED.put("pale orange saffron", -0x1.0f036cp126F); LIST.add(-0x1.0f036cp126F); }

    /**
     * This color constant "drab yellow saffron" has RGBA8888 code {@code 6A5A36FF}, L 0.36862746, A 0.49803922, B 0.5294118, alpha 1.0, hue 0.26058978, and saturation 0.52042246.
     * It can be represented as a packed float with the constant {@code -0x1.0efebcp126F}.
     * <pre>
     * <font style='background-color: #6A5A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A5A36; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A5A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6A5A36'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6A5A36'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6A5A36'>&nbsp;@&nbsp;</font><font style='background-color: #6A5A36; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6A5A36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6A5A36; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW_SAFFRON = -0x1.0efebcp126F;
    static { NAMED.put("drab yellow saffron", -0x1.0efebcp126F); LIST.add(-0x1.0efebcp126F); }

    /**
     * This color constant "dull yellow saffron" has RGBA8888 code {@code 9E8B63FF}, L 0.5411765, A 0.5019608, B 0.5294118, alpha 1.0, hue 0.23941022, and saturation 0.40789866.
     * It can be represented as a packed float with the constant {@code -0x1.0f0114p126F}.
     * <pre>
     * <font style='background-color: #9E8B63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E8B63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E8B63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E8B63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E8B63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E8B63'>&nbsp;@&nbsp;</font><font style='background-color: #9E8B63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E8B63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E8B63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_YELLOW_SAFFRON = -0x1.0f0114p126F;
    static { NAMED.put("dull yellow saffron", -0x1.0f0114p126F); LIST.add(-0x1.0f0114p126F); }

    /**
     * This color constant "pale yellow saffron" has RGBA8888 code {@code D7C296FF}, L 0.7490196, A 0.5019608, B 0.5294118, alpha 1.0, hue 0.23941022, and saturation 0.33538336.
     * It can be represented as a packed float with the constant {@code -0x1.0f017ep126F}.
     * <pre>
     * <font style='background-color: #D7C296;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7C296; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7C296;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7C296'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7C296'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7C296'>&nbsp;@&nbsp;</font><font style='background-color: #D7C296; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7C296;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7C296; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_SAFFRON = -0x1.0f017ep126F;
    static { NAMED.put("pale yellow saffron", -0x1.0f017ep126F); LIST.add(-0x1.0f017ep126F); }

    /**
     * This color constant "drab saffron yellow" has RGBA8888 code {@code 6E6C3DFF}, L 0.41568628, A 0.4862745, B 0.53333336, alpha 1.0, hue 0.31215638, and saturation 0.55929923.
     * It can be represented as a packed float with the constant {@code -0x1.10f8d4p126F}.
     * <pre>
     * <font style='background-color: #6E6C3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E6C3D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E6C3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E6C3D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E6C3D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E6C3D'>&nbsp;@&nbsp;</font><font style='background-color: #6E6C3D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E6C3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E6C3D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_SAFFRON_YELLOW = -0x1.10f8d4p126F;
    static { NAMED.put("drab saffron yellow", -0x1.10f8d4p126F); LIST.add(-0x1.10f8d4p126F); }

    /**
     * This color constant "dull saffron yellow" has RGBA8888 code {@code A19F6AFF}, L 0.5921569, A 0.4862745, B 0.53333336, alpha 1.0, hue 0.31215638, and saturation 0.4394494.
     * It can be represented as a packed float with the constant {@code -0x1.10f92ep126F}.
     * <pre>
     * <font style='background-color: #A19F6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A19F6A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A19F6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A19F6A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A19F6A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A19F6A'>&nbsp;@&nbsp;</font><font style='background-color: #A19F6A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A19F6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A19F6A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_SAFFRON_YELLOW = -0x1.10f92ep126F;
    static { NAMED.put("dull saffron yellow", -0x1.10f92ep126F); LIST.add(-0x1.10f92ep126F); }

    /**
     * This color constant "pale saffron yellow" has RGBA8888 code {@code DAD89EFF}, L 0.8156863, A 0.49019608, B 0.53333336, alpha 1.0, hue 0.29551703, and saturation 0.36305177.
     * It can be represented as a packed float with the constant {@code -0x1.10fbap126F}.
     * <pre>
     * <font style='background-color: #DAD89E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAD89E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAD89E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAD89E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAD89E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAD89E'>&nbsp;@&nbsp;</font><font style='background-color: #DAD89E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAD89E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAD89E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_SAFFRON_YELLOW = -0x1.10fbap126F;
    static { NAMED.put("pale saffron yellow", -0x1.10fbap126F); LIST.add(-0x1.10fbap126F); }

    /**
     * This color constant "drab lime yellow" has RGBA8888 code {@code 67753BFF}, L 0.42745098, A 0.4745098, B 0.5372549, alpha 1.0, hue 0.3455135, and saturation 0.6082108.
     * It can be represented as a packed float with the constant {@code -0x1.12f2dap126F}.
     * <pre>
     * <font style='background-color: #67753B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #67753B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #67753B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #67753B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #67753B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #67753B'>&nbsp;@&nbsp;</font><font style='background-color: #67753B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #67753B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #67753B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME_YELLOW = -0x1.12f2dap126F;
    static { NAMED.put("drab lime yellow", -0x1.12f2dap126F); LIST.add(-0x1.12f2dap126F); }

    /**
     * This color constant "dull lime yellow" has RGBA8888 code {@code 98A868FF}, L 0.60784316, A 0.47843137, B 0.5372549, alpha 1.0, hue 0.33353055, and saturation 0.48979104.
     * It can be represented as a packed float with the constant {@code -0x1.12f536p126F}.
     * <pre>
     * <font style='background-color: #98A868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98A868; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98A868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98A868'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98A868'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98A868'>&nbsp;@&nbsp;</font><font style='background-color: #98A868; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98A868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98A868; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_LIME_YELLOW = -0x1.12f536p126F;
    static { NAMED.put("dull lime yellow", -0x1.12f536p126F); LIST.add(-0x1.12f536p126F); }

    /**
     * This color constant "pale lime yellow" has RGBA8888 code {@code D0E29CFF}, L 0.83137256, A 0.47843137, B 0.5372549, alpha 1.0, hue 0.33353055, and saturation 0.40073812.
     * It can be represented as a packed float with the constant {@code -0x1.12f5a8p126F}.
     * <pre>
     * <font style='background-color: #D0E29C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0E29C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0E29C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0E29C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0E29C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0E29C'>&nbsp;@&nbsp;</font><font style='background-color: #D0E29C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0E29C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0E29C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME_YELLOW = -0x1.12f5a8p126F;
    static { NAMED.put("pale lime yellow", -0x1.12f5a8p126F); LIST.add(-0x1.12f5a8p126F); }

    /**
     * This color constant "drab yellow lime" has RGBA8888 code {@code 59703BFF}, L 0.40784314, A 0.47058824, B 0.53333336, alpha 1.0, hue 0.36507308, and saturation 0.5690119.
     * It can be represented as a packed float with the constant {@code -0x1.10f0dp126F}.
     * <pre>
     * <font style='background-color: #59703B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #59703B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #59703B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #59703B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #59703B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #59703B'>&nbsp;@&nbsp;</font><font style='background-color: #59703B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #59703B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #59703B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_YELLOW_LIME = -0x1.10f0dp126F;
    static { NAMED.put("drab yellow lime", -0x1.10f0dp126F); LIST.add(-0x1.10f0dp126F); }

    /**
     * This color constant "dull yellow lime" has RGBA8888 code {@code 8BA56AFF}, L 0.5882353, A 0.47058824, B 0.53333336, alpha 1.0, hue 0.36507308, and saturation 0.44628388.
     * It can be represented as a packed float with the constant {@code -0x1.10f12cp126F}.
     * <pre>
     * <font style='background-color: #8BA56A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BA56A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BA56A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8BA56A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8BA56A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8BA56A'>&nbsp;@&nbsp;</font><font style='background-color: #8BA56A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BA56A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BA56A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_YELLOW_LIME = -0x1.10f12cp126F;
    static { NAMED.put("dull yellow lime", -0x1.10f12cp126F); LIST.add(-0x1.10f12cp126F); }

    /**
     * This color constant "pale yellow lime" has RGBA8888 code {@code C2DF9EFF}, L 0.80784315, A 0.47058824, B 0.53333336, alpha 1.0, hue 0.36507308, and saturation 0.36710447.
     * It can be represented as a packed float with the constant {@code -0x1.10f19cp126F}.
     * <pre>
     * <font style='background-color: #C2DF9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2DF9E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2DF9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C2DF9E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C2DF9E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C2DF9E'>&nbsp;@&nbsp;</font><font style='background-color: #C2DF9E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2DF9E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2DF9E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_LIME = -0x1.10f19cp126F;
    static { NAMED.put("pale yellow lime", -0x1.10f19cp126F); LIST.add(-0x1.10f19cp126F); }

    /**
     * This color constant "drab green lime" has RGBA8888 code {@code 4C6F39FF}, L 0.39215687, A 0.4627451, B 0.5294118, alpha 1.0, hue 0.3936267, and saturation 0.5283123.
     * It can be represented as a packed float with the constant {@code -0x1.0eecc8p126F}.
     * <pre>
     * <font style='background-color: #4C6F39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C6F39; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C6F39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4C6F39'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4C6F39'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4C6F39'>&nbsp;@&nbsp;</font><font style='background-color: #4C6F39; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C6F39;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C6F39; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN_LIME = -0x1.0eecc8p126F;
    static { NAMED.put("drab green lime", -0x1.0eecc8p126F); LIST.add(-0x1.0eecc8p126F); }

    /**
     * This color constant "dull green lime" has RGBA8888 code {@code 7BA366FF}, L 0.5686275, A 0.4627451, B 0.53333336, alpha 1.0, hue 0.38382626, and saturation 0.457055.
     * It can be represented as a packed float with the constant {@code -0x1.10ed22p126F}.
     * <pre>
     * <font style='background-color: #7BA366;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7BA366; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7BA366;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7BA366'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7BA366'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7BA366'>&nbsp;@&nbsp;</font><font style='background-color: #7BA366; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7BA366;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7BA366; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN_LIME = -0x1.10ed22p126F;
    static { NAMED.put("dull green lime", -0x1.10ed22p126F); LIST.add(-0x1.10ed22p126F); }

    /**
     * This color constant "pale green lime" has RGBA8888 code {@code B0DD99FF}, L 0.78431374, A 0.4627451, B 0.53333336, alpha 1.0, hue 0.38382626, and saturation 0.3709432.
     * It can be represented as a packed float with the constant {@code -0x1.10ed9p126F}.
     * <pre>
     * <font style='background-color: #B0DD99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0DD99; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0DD99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0DD99'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0DD99'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0DD99'>&nbsp;@&nbsp;</font><font style='background-color: #B0DD99; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0DD99;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0DD99; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN_LIME = -0x1.10ed9p126F;
    static { NAMED.put("pale green lime", -0x1.10ed9p126F); LIST.add(-0x1.10ed9p126F); }

    /**
     * This color constant "drab lime green" has RGBA8888 code {@code 366F35FF}, L 0.38039216, A 0.4509804, B 0.53333336, alpha 1.0, hue 0.40494394, and saturation 0.63231236.
     * It can be represented as a packed float with the constant {@code -0x1.10e6c2p126F}.
     * <pre>
     * <font style='background-color: #366F35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #366F35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #366F35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #366F35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #366F35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #366F35'>&nbsp;@&nbsp;</font><font style='background-color: #366F35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #366F35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #366F35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_LIME_GREEN = -0x1.10e6c2p126F;
    static { NAMED.put("drab lime green", -0x1.10e6c2p126F); LIST.add(-0x1.10e6c2p126F); }

    /**
     * This color constant "dull lime green" has RGBA8888 code {@code 64A362FF}, L 0.5529412, A 0.4509804, B 0.53333336, alpha 1.0, hue 0.40494394, and saturation 0.5058499.
     * It can be represented as a packed float with the constant {@code -0x1.10e71ap126F}.
     * <pre>
     * <font style='background-color: #64A362;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64A362; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64A362;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #64A362'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #64A362'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #64A362'>&nbsp;@&nbsp;</font><font style='background-color: #64A362; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64A362;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64A362; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_LIME_GREEN = -0x1.10e71ap126F;
    static { NAMED.put("dull lime green", -0x1.10e71ap126F); LIST.add(-0x1.10e71ap126F); }

    /**
     * This color constant "pale lime green" has RGBA8888 code {@code 97DD94FF}, L 0.7607843, A 0.4509804, B 0.53333336, alpha 1.0, hue 0.40494394, and saturation 0.415767.
     * It can be represented as a packed float with the constant {@code -0x1.10e784p126F}.
     * <pre>
     * <font style='background-color: #97DD94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #97DD94; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #97DD94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #97DD94'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #97DD94'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #97DD94'>&nbsp;@&nbsp;</font><font style='background-color: #97DD94; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #97DD94;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #97DD94; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_LIME_GREEN = -0x1.10e784p126F;
    static { NAMED.put("pale lime green", -0x1.10e784p126F); LIST.add(-0x1.10e784p126F); }

    /**
     * This color constant "drab cyan green" has RGBA8888 code {@code 346C4FFF}, L 0.3764706, A 0.45882353, B 0.5137255, alpha 1.0, hue 0.44880432, and saturation 0.65361035.
     * It can be represented as a packed float with the constant {@code -0x1.06eacp126F}.
     * <pre>
     * <font style='background-color: #346C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #346C4F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #346C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #346C4F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #346C4F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #346C4F'>&nbsp;@&nbsp;</font><font style='background-color: #346C4F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #346C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #346C4F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN_GREEN = -0x1.06eacp126F;
    static { NAMED.put("drab cyan green", -0x1.06eacp126F); LIST.add(-0x1.06eacp126F); }

    /**
     * This color constant "dull cyan green" has RGBA8888 code {@code 569271FF}, L 0.5019608, A 0.45882353, B 0.5137255, alpha 1.0, hue 0.44880432, and saturation 0.54201835.
     * It can be represented as a packed float with the constant {@code -0x1.06ebp126F}.
     * <pre>
     * <font style='background-color: #569271;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #569271; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #569271;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #569271'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #569271'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #569271'>&nbsp;@&nbsp;</font><font style='background-color: #569271; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #569271;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #569271; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_CYAN_GREEN = -0x1.06ebp126F;
    static { NAMED.put("dull cyan green", -0x1.06ebp126F); LIST.add(-0x1.06ebp126F); }

    /**
     * This color constant "pale cyan green" has RGBA8888 code {@code 7CBC98FF}, L 0.6509804, A 0.45882353, B 0.5137255, alpha 1.0, hue 0.44880432, and saturation 0.46297398.
     * It can be represented as a packed float with the constant {@code -0x1.06eb4cp126F}.
     * <pre>
     * <font style='background-color: #7CBC98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CBC98; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CBC98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7CBC98'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7CBC98'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7CBC98'>&nbsp;@&nbsp;</font><font style='background-color: #7CBC98; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7CBC98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7CBC98; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN_GREEN = -0x1.06eb4cp126F;
    static { NAMED.put("pale cyan green", -0x1.06eb4cp126F); LIST.add(-0x1.06eb4cp126F); }

    /**
     * This color constant "drab green cyan" has RGBA8888 code {@code 2B665DFF}, L 0.36078432, A 0.4627451, B 0.49803922, alpha 1.0, hue 0.5083591, and saturation 0.7074412.
     * It can be represented as a packed float with the constant {@code -0x1.feecb8p125F}.
     * <pre>
     * <font style='background-color: #2B665D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B665D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B665D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2B665D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2B665D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2B665D'>&nbsp;@&nbsp;</font><font style='background-color: #2B665D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B665D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B665D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN_CYAN = -0x1.feecb8p125F;
    static { NAMED.put("drab green cyan", -0x1.feecb8p125F); LIST.add(-0x1.feecb8p125F); }

    /**
     * This color constant "dull green cyan" has RGBA8888 code {@code 49877DFF}, L 0.47058824, A 0.4627451, B 0.49803922, alpha 1.0, hue 0.5083591, and saturation 0.6161584.
     * It can be represented as a packed float with the constant {@code -0x1.feecfp125F}.
     * <pre>
     * <font style='background-color: #49877D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49877D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49877D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #49877D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #49877D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #49877D'>&nbsp;@&nbsp;</font><font style='background-color: #49877D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49877D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49877D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN_CYAN = -0x1.feecfp125F;
    static { NAMED.put("dull green cyan", -0x1.feecfp125F); LIST.add(-0x1.feecfp125F); }

    /**
     * This color constant "pale green cyan" has RGBA8888 code {@code 69AAA0FF}, L 0.5921569, A 0.4627451, B 0.49411765, alpha 1.0, hue 0.5249365, and saturation 0.55173767.
     * It can be represented as a packed float with the constant {@code -0x1.fced2ep125F}.
     * <pre>
     * <font style='background-color: #69AAA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #69AAA0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #69AAA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #69AAA0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #69AAA0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #69AAA0'>&nbsp;@&nbsp;</font><font style='background-color: #69AAA0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #69AAA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #69AAA0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN_CYAN = -0x1.fced2ep125F;
    static { NAMED.put("pale green cyan", -0x1.fced2ep125F); LIST.add(-0x1.fced2ep125F); }

    /**
     * This color constant "drab blue cyan" has RGBA8888 code {@code 326672FF}, L 0.37254903, A 0.47058824, B 0.48235294, alpha 1.0, hue 0.58601886, and saturation 0.67544055.
     * It can be represented as a packed float with the constant {@code -0x1.f6f0bep125F}.
     * <pre>
     * <font style='background-color: #326672;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #326672; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #326672;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #326672'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #326672'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #326672'>&nbsp;@&nbsp;</font><font style='background-color: #326672; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #326672;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #326672; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE_CYAN = -0x1.f6f0bep125F;
    static { NAMED.put("drab blue cyan", -0x1.f6f0bep125F); LIST.add(-0x1.f6f0bep125F); }

    /**
     * This color constant "dull blue cyan" has RGBA8888 code {@code 5E97A5FF}, L 0.5372549, A 0.47058824, B 0.47843137, alpha 1.0, hue 0.600718, and saturation 0.5492359.
     * It can be represented as a packed float with the constant {@code -0x1.f4f112p125F}.
     * <pre>
     * <font style='background-color: #5E97A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E97A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E97A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5E97A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5E97A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5E97A5'>&nbsp;@&nbsp;</font><font style='background-color: #5E97A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E97A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E97A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BLUE_CYAN = -0x1.f4f112p125F;
    static { NAMED.put("dull blue cyan", -0x1.f4f112p125F); LIST.add(-0x1.f4f112p125F); }

    /**
     * This color constant "pale blue cyan" has RGBA8888 code {@code 92CFDFFF}, L 0.74509805, A 0.4745098, B 0.47843137, alpha 1.0, hue 0.611777, and saturation 0.48846197.
     * It can be represented as a packed float with the constant {@code -0x1.f4f37cp125F}.
     * <pre>
     * <font style='background-color: #92CFDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92CFDF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92CFDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #92CFDF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #92CFDF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #92CFDF'>&nbsp;@&nbsp;</font><font style='background-color: #92CFDF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92CFDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92CFDF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE_CYAN = -0x1.f4f37cp125F;
    static { NAMED.put("pale blue cyan", -0x1.f4f37cp125F); LIST.add(-0x1.f4f37cp125F); }

    /**
     * This color constant "drab cyan blue" has RGBA8888 code {@code 294B6CFF}, L 0.3019608, A 0.48235294, B 0.4627451, alpha 1.0, hue 0.6795985, and saturation 0.6595701.
     * It can be represented as a packed float with the constant {@code -0x1.ecf69ap125F}.
     * <pre>
     * <font style='background-color: #294B6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #294B6C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #294B6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #294B6C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #294B6C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #294B6C'>&nbsp;@&nbsp;</font><font style='background-color: #294B6C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #294B6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #294B6C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_CYAN_BLUE = -0x1.ecf69ap125F;
    static { NAMED.put("drab cyan blue", -0x1.ecf69ap125F); LIST.add(-0x1.ecf69ap125F); }

    /**
     * This color constant "dull cyan blue" has RGBA8888 code {@code 52789FFF}, L 0.4509804, A 0.4862745, B 0.4627451, alpha 1.0, hue 0.69383264, and saturation 0.43250772.
     * It can be represented as a packed float with the constant {@code -0x1.ecf8e6p125F}.
     * <pre>
     * <font style='background-color: #52789F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #52789F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #52789F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #52789F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #52789F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #52789F'>&nbsp;@&nbsp;</font><font style='background-color: #52789F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #52789F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #52789F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_CYAN_BLUE = -0x1.ecf8e6p125F;
    static { NAMED.put("dull cyan blue", -0x1.ecf8e6p125F); LIST.add(-0x1.ecf8e6p125F); }

    /**
     * This color constant "pale cyan blue" has RGBA8888 code {@code 81ACD7FF}, L 0.63529414, A 0.48235294, B 0.4627451, alpha 1.0, hue 0.6795985, and saturation 0.55542743.
     * It can be represented as a packed float with the constant {@code -0x1.ecf744p125F}.
     * <pre>
     * <font style='background-color: #81ACD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81ACD7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81ACD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #81ACD7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #81ACD7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #81ACD7'>&nbsp;@&nbsp;</font><font style='background-color: #81ACD7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81ACD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81ACD7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_CYAN_BLUE = -0x1.ecf744p125F;
    static { NAMED.put("pale cyan blue", -0x1.ecf744p125F); LIST.add(-0x1.ecf744p125F); }

    /**
     * This color constant "drab violet blue" has RGBA8888 code {@code 1D2565FF}, L 0.20784314, A 0.49803922, B 0.43529412, alpha 1.0, hue 0.74519134, and saturation 0.55241036.
     * It can be represented as a packed float with the constant {@code -0x1.defe6ap125F}.
     * <pre>
     * <font style='background-color: #1D2565;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D2565; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D2565;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D2565'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D2565'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D2565'>&nbsp;@&nbsp;</font><font style='background-color: #1D2565; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D2565;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D2565; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET_BLUE = -0x1.defe6ap125F;
    static { NAMED.put("drab violet blue", -0x1.defe6ap125F); LIST.add(-0x1.defe6ap125F); }

    /**
     * This color constant "dull violet blue" has RGBA8888 code {@code 3E4D97FF}, L 0.34117648, A 0.49803922, B 0.43529412, alpha 1.0, hue 0.74519134, and saturation 0.41955218.
     * It can be represented as a packed float with the constant {@code -0x1.defeaep125F}.
     * <pre>
     * <font style='background-color: #3E4D97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E4D97; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E4D97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3E4D97'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3E4D97'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3E4D97'>&nbsp;@&nbsp;</font><font style='background-color: #3E4D97; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E4D97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E4D97; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET_BLUE = -0x1.defeaep125F;
    static { NAMED.put("dull violet blue", -0x1.defeaep125F); LIST.add(-0x1.defeaep125F); }

    /**
     * This color constant "pale violet blue" has RGBA8888 code {@code 667ACEFF}, L 0.49411765, A 0.49803922, B 0.43529412, alpha 1.0, hue 0.74519134, and saturation 0.62537026.
     * It can be represented as a packed float with the constant {@code -0x1.defefcp125F}.
     * <pre>
     * <font style='background-color: #667ACE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #667ACE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #667ACE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #667ACE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #667ACE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #667ACE'>&nbsp;@&nbsp;</font><font style='background-color: #667ACE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #667ACE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #667ACE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET_BLUE = -0x1.defefcp125F;
    static { NAMED.put("pale violet blue", -0x1.defefcp125F); LIST.add(-0x1.defefcp125F); }

    /**
     * This color constant "drab blue violet" has RGBA8888 code {@code 312663FF}, L 0.22352941, A 0.5137255, B 0.44313726, alpha 1.0, hue 0.7876946, and saturation 0.52543616.
     * It can be represented as a packed float with the constant {@code -0x1.e30672p125F}.
     * <pre>
     * <font style='background-color: #312663;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #312663; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #312663;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #312663'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #312663'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #312663'>&nbsp;@&nbsp;</font><font style='background-color: #312663; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #312663;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #312663; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_BLUE_VIOLET = -0x1.e30672p125F;
    static { NAMED.put("drab blue violet", -0x1.e30672p125F); LIST.add(-0x1.e30672p125F); }

    /**
     * This color constant "dull blue violet" has RGBA8888 code {@code 584E95FF}, L 0.36078432, A 0.5176471, B 0.44313726, alpha 1.0, hue 0.79788184, and saturation 0.39588997.
     * It can be represented as a packed float with the constant {@code -0x1.e308b8p125F}.
     * <pre>
     * <font style='background-color: #584E95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #584E95; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #584E95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #584E95'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #584E95'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #584E95'>&nbsp;@&nbsp;</font><font style='background-color: #584E95; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #584E95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #584E95; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_BLUE_VIOLET = -0x1.e308b8p125F;
    static { NAMED.put("dull blue violet", -0x1.e308b8p125F); LIST.add(-0x1.e308b8p125F); }

    /**
     * This color constant "pale blue violet" has RGBA8888 code {@code 877ECEFF}, L 0.5294118, A 0.5137255, B 0.44313726, alpha 1.0, hue 0.7876946, and saturation 0.5989972.
     * It can be represented as a packed float with the constant {@code -0x1.e3070ep125F}.
     * <pre>
     * <font style='background-color: #877ECE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #877ECE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #877ECE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #877ECE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #877ECE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #877ECE'>&nbsp;@&nbsp;</font><font style='background-color: #877ECE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #877ECE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #877ECE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE_VIOLET = -0x1.e3070ep125F;
    static { NAMED.put("pale blue violet", -0x1.e3070ep125F); LIST.add(-0x1.e3070ep125F); }

    /**
     * This color constant "drab purple violet" has RGBA8888 code {@code 422866FF}, L 0.24705882, A 0.5294118, B 0.4509804, alpha 1.0, hue 0.83601886, and saturation 0.53216535.
     * It can be represented as a packed float with the constant {@code -0x1.e70e7ep125F}.
     * <pre>
     * <font style='background-color: #422866;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #422866; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #422866;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #422866'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #422866'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #422866'>&nbsp;@&nbsp;</font><font style='background-color: #422866; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #422866;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #422866; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE_VIOLET = -0x1.e70e7ep125F;
    static { NAMED.put("drab purple violet", -0x1.e70e7ep125F); LIST.add(-0x1.e70e7ep125F); }

    /**
     * This color constant "dull purple violet" has RGBA8888 code {@code 6C5199FF}, L 0.3882353, A 0.5254902, B 0.44705883, alpha 1.0, hue 0.8214129, and saturation 0.39070344.
     * It can be represented as a packed float with the constant {@code -0x1.e50cc6p125F}.
     * <pre>
     * <font style='background-color: #6C5199;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C5199; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C5199;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C5199'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C5199'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C5199'>&nbsp;@&nbsp;</font><font style='background-color: #6C5199; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C5199;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C5199; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_PURPLE_VIOLET = -0x1.e50cc6p125F;
    static { NAMED.put("dull purple violet", -0x1.e50cc6p125F); LIST.add(-0x1.e50cc6p125F); }

    /**
     * This color constant "pale purple violet" has RGBA8888 code {@code 9D7ED0FF}, L 0.5529412, A 0.5294118, B 0.44705883, alpha 1.0, hue 0.8307117, and saturation 0.5850569.
     * It can be represented as a packed float with the constant {@code -0x1.e50f1ap125F}.
     * <pre>
     * <font style='background-color: #9D7ED0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D7ED0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D7ED0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D7ED0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D7ED0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D7ED0'>&nbsp;@&nbsp;</font><font style='background-color: #9D7ED0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D7ED0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D7ED0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE_VIOLET = -0x1.e50f1ap125F;
    static { NAMED.put("pale purple violet", -0x1.e50f1ap125F); LIST.add(-0x1.e50f1ap125F); }

    /**
     * This color constant "drab violet purple" has RGBA8888 code {@code 492A66FF}, L 0.25490198, A 0.53333336, B 0.4509804, alpha 1.0, hue 0.84505606, and saturation 0.5518362.
     * It can be represented as a packed float with the constant {@code -0x1.e71082p125F}.
     * <pre>
     * <font style='background-color: #492A66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #492A66; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #492A66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #492A66'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #492A66'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #492A66'>&nbsp;@&nbsp;</font><font style='background-color: #492A66; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #492A66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #492A66; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_VIOLET_PURPLE = -0x1.e71082p125F;
    static { NAMED.put("drab violet purple", -0x1.e71082p125F); LIST.add(-0x1.e71082p125F); }

    /**
     * This color constant "dull violet purple" has RGBA8888 code {@code 765399FF}, L 0.4, A 0.53333336, B 0.4509804, alpha 1.0, hue 0.84505606, and saturation 0.4046799.
     * It can be represented as a packed float with the constant {@code -0x1.e710ccp125F}.
     * <pre>
     * <font style='background-color: #765399;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #765399; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #765399;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #765399'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #765399'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #765399'>&nbsp;@&nbsp;</font><font style='background-color: #765399; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #765399;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #765399; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET_PURPLE = -0x1.e710ccp125F;
    static { NAMED.put("dull violet purple", -0x1.e710ccp125F); LIST.add(-0x1.e710ccp125F); }

    /**
     * This color constant "pale violet purple" has RGBA8888 code {@code A983D1FF}, L 0.5764706, A 0.53333336, B 0.45490196, alpha 1.0, hue 0.8513163, and saturation 0.5417521.
     * It can be represented as a packed float with the constant {@code -0x1.e91126p125F}.
     * <pre>
     * <font style='background-color: #A983D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A983D1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A983D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A983D1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A983D1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A983D1'>&nbsp;@&nbsp;</font><font style='background-color: #A983D1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A983D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A983D1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET_PURPLE = -0x1.e91126p125F;
    static { NAMED.put("pale violet purple", -0x1.e91126p125F); LIST.add(-0x1.e91126p125F); }

    /**
     * This color constant "drab magenta purple" has RGBA8888 code {@code 552B67FF}, L 0.27058825, A 0.5411765, B 0.45882353, alpha 1.0, hue 0.875, and saturation 0.55212873.
     * It can be represented as a packed float with the constant {@code -0x1.eb148ap125F}.
     * <pre>
     * <font style='background-color: #552B67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #552B67; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #552B67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #552B67'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #552B67'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #552B67'>&nbsp;@&nbsp;</font><font style='background-color: #552B67; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #552B67;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #552B67; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA_PURPLE = -0x1.eb148ap125F;
    static { NAMED.put("drab magenta purple", -0x1.eb148ap125F); LIST.add(-0x1.eb148ap125F); }

    /**
     * This color constant "dull magenta purple" has RGBA8888 code {@code 845499FF}, L 0.41568628, A 0.5411765, B 0.45882353, alpha 1.0, hue 0.875, and saturation 0.408424.
     * It can be represented as a packed float with the constant {@code -0x1.eb14d4p125F}.
     * <pre>
     * <font style='background-color: #845499;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #845499; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #845499;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #845499'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #845499'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #845499'>&nbsp;@&nbsp;</font><font style='background-color: #845499; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #845499;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #845499; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_MAGENTA_PURPLE = -0x1.eb14d4p125F;
    static { NAMED.put("dull magenta purple", -0x1.eb14d4p125F); LIST.add(-0x1.eb14d4p125F); }

    /**
     * This color constant "pale magenta purple" has RGBA8888 code {@code BA85D3FF}, L 0.6, A 0.5411765, B 0.45490196, alpha 1.0, hue 0.86777616, and saturation 0.5684902.
     * It can be represented as a packed float with the constant {@code -0x1.e91532p125F}.
     * <pre>
     * <font style='background-color: #BA85D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA85D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA85D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA85D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA85D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA85D3'>&nbsp;@&nbsp;</font><font style='background-color: #BA85D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA85D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA85D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA_PURPLE = -0x1.e91532p125F;
    static { NAMED.put("pale magenta purple", -0x1.e91532p125F); LIST.add(-0x1.e91532p125F); }

    /**
     * This color constant "drab purple magenta" has RGBA8888 code {@code 5E2E69FF}, L 0.28627452, A 0.54509807, B 0.45882353, alpha 1.0, hue 0.88222384, and saturation 0.5583386.
     * It can be represented as a packed float with the constant {@code -0x1.eb1692p125F}.
     * <pre>
     * <font style='background-color: #5E2E69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E2E69; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E2E69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5E2E69'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5E2E69'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5E2E69'>&nbsp;@&nbsp;</font><font style='background-color: #5E2E69; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E2E69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E2E69; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_PURPLE_MAGENTA = -0x1.eb1692p125F;
    static { NAMED.put("drab purple magenta", -0x1.eb1692p125F); LIST.add(-0x1.eb1692p125F); }

    /**
     * This color constant "dull purple magenta" has RGBA8888 code {@code 90599CFF}, L 0.44313726, A 0.54509807, B 0.45882353, alpha 1.0, hue 0.88222384, and saturation 0.4168928.
     * It can be represented as a packed float with the constant {@code -0x1.eb16e2p125F}.
     * <pre>
     * <font style='background-color: #90599C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90599C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90599C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #90599C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #90599C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #90599C'>&nbsp;@&nbsp;</font><font style='background-color: #90599C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90599C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90599C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_PURPLE_MAGENTA = -0x1.eb16e2p125F;
    static { NAMED.put("dull purple magenta", -0x1.eb16e2p125F); LIST.add(-0x1.eb16e2p125F); }

    /**
     * This color constant "pale purple magenta" has RGBA8888 code {@code C789D4FF}, L 0.61960787, A 0.54509807, B 0.45882353, alpha 1.0, hue 0.88222384, and saturation 0.5583386.
     * It can be represented as a packed float with the constant {@code -0x1.eb173cp125F}.
     * <pre>
     * <font style='background-color: #C789D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C789D4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C789D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C789D4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C789D4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C789D4'>&nbsp;@&nbsp;</font><font style='background-color: #C789D4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C789D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C789D4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE_MAGENTA = -0x1.eb173cp125F;
    static { NAMED.put("pale purple magenta", -0x1.eb173cp125F); LIST.add(-0x1.eb173cp125F); }

    /**
     * This color constant "drab red magenta" has RGBA8888 code {@code 692B54FF}, L 0.28627452, A 0.5529412, B 0.48235294, alpha 1.0, hue 0.9488043, and saturation 0.5714422.
     * It can be represented as a packed float with the constant {@code -0x1.f71a92p125F}.
     * <pre>
     * <font style='background-color: #692B54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #692B54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #692B54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #692B54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #692B54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #692B54'>&nbsp;@&nbsp;</font><font style='background-color: #692B54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #692B54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #692B54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_RED_MAGENTA = -0x1.f71a92p125F;
    static { NAMED.put("drab red magenta", -0x1.f71a92p125F); LIST.add(-0x1.f71a92p125F); }

    /**
     * This color constant "dull red magenta" has RGBA8888 code {@code 9B5382FF}, L 0.43137255, A 0.5529412, B 0.47843137, alpha 1.0, hue 0.93843776, and saturation 0.4434711.
     * It can be represented as a packed float with the constant {@code -0x1.f51adcp125F}.
     * <pre>
     * <font style='background-color: #9B5382;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B5382; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B5382;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B5382'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B5382'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B5382'>&nbsp;@&nbsp;</font><font style='background-color: #9B5382; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B5382;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B5382; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_RED_MAGENTA = -0x1.f51adcp125F;
    static { NAMED.put("dull red magenta", -0x1.f51adcp125F); LIST.add(-0x1.f51adcp125F); }

    /**
     * This color constant "pale red magenta" has RGBA8888 code {@code D382B5FF}, L 0.60784316, A 0.5529412, B 0.47843137, alpha 1.0, hue 0.93843776, and saturation 0.47982118.
     * It can be represented as a packed float with the constant {@code -0x1.f51b36p125F}.
     * <pre>
     * <font style='background-color: #D382B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D382B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D382B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D382B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D382B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D382B5'>&nbsp;@&nbsp;</font><font style='background-color: #D382B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D382B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D382B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_RED_MAGENTA = -0x1.f51b36p125F;
    static { NAMED.put("pale red magenta", -0x1.f51b36p125F); LIST.add(-0x1.f51b36p125F); }

    /**
     * This color constant "drab magenta red" has RGBA8888 code {@code 672B34FF}, L 0.27450982, A 0.54509807, B 0.50980395, alpha 1.0, hue 0.034072436, and saturation 0.5370343.
     * It can be represented as a packed float with the constant {@code -0x1.05168cp126F}.
     * <pre>
     * <font style='background-color: #672B34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #672B34; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #672B34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #672B34'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #672B34'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #672B34'>&nbsp;@&nbsp;</font><font style='background-color: #672B34; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #672B34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #672B34; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_MAGENTA_RED = -0x1.05168cp126F;
    static { NAMED.put("drab magenta red", -0x1.05168cp126F); LIST.add(-0x1.05168cp126F); }

    /**
     * This color constant "dull magenta red" has RGBA8888 code {@code 99535CFF}, L 0.41568628, A 0.54509807, B 0.5058824, alpha 1.0, hue 0.02065512, and saturation 0.39467436.
     * It can be represented as a packed float with the constant {@code -0x1.0316d4p126F}.
     * <pre>
     * <font style='background-color: #99535C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99535C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99535C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #99535C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #99535C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #99535C'>&nbsp;@&nbsp;</font><font style='background-color: #99535C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99535C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99535C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_MAGENTA_RED = -0x1.0316d4p126F;
    static { NAMED.put("dull magenta red", -0x1.0316d4p126F); LIST.add(-0x1.0316d4p126F); }

    /**
     * This color constant "pale magenta red" has RGBA8888 code {@code CF8088FF}, L 0.58431375, A 0.5411765, B 0.50980395, alpha 1.0, hue 0.037200917, and saturation 0.45149353.
     * It can be represented as a packed float with the constant {@code -0x1.05152ap126F}.
     * <pre>
     * <font style='background-color: #CF8088;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF8088; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF8088;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CF8088'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CF8088'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CF8088'>&nbsp;@&nbsp;</font><font style='background-color: #CF8088; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CF8088;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CF8088; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_MAGENTA_RED = -0x1.05152ap126F;
    static { NAMED.put("pale magenta red", -0x1.05152ap126F); LIST.add(-0x1.05152ap126F); }

    /**
     * This color constant "deep red" has RGBA8888 code {@code B0342EFF}, L 0.39215687, A 0.57254905, B 0.5372549, alpha 1.0, hue 0.07550309, and saturation 0.7325681.
     * It can be represented as a packed float with the constant {@code -0x1.1324c8p126F}.
     * <pre>
     * <font style='background-color: #B0342E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0342E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0342E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0342E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0342E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0342E'>&nbsp;@&nbsp;</font><font style='background-color: #B0342E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0342E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0342E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED = -0x1.1324c8p126F;
    static { NAMED.put("deep red", -0x1.1324c8p126F); LIST.add(-0x1.1324c8p126F); }

    /**
     * This color constant "bright red" has RGBA8888 code {@code DB554BFF}, L 0.5058824, A 0.57254905, B 0.5372549, alpha 1.0, hue 0.07550309, and saturation 0.6627997.
     * It can be represented as a packed float with the constant {@code -0x1.132502p126F}.
     * <pre>
     * <font style='background-color: #DB554B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB554B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB554B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB554B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB554B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB554B'>&nbsp;@&nbsp;</font><font style='background-color: #DB554B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB554B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB554B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED = -0x1.132502p126F;
    static { NAMED.put("bright red", -0x1.132502p126F); LIST.add(-0x1.132502p126F); }

    /**
     * This color constant "deep brown red" has RGBA8888 code {@code B1432EFF}, L 0.4117647, A 0.56078434, B 0.5411765, alpha 1.0, hue 0.0947748, and saturation 0.70924765.
     * It can be represented as a packed float with the constant {@code -0x1.151ed2p126F}.
     * <pre>
     * <font style='background-color: #B1432E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1432E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1432E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1432E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1432E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1432E'>&nbsp;@&nbsp;</font><font style='background-color: #B1432E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1432E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1432E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_RED = -0x1.151ed2p126F;
    static { NAMED.put("deep brown red", -0x1.151ed2p126F); LIST.add(-0x1.151ed2p126F); }

    /**
     * This color constant "bright brown red" has RGBA8888 code {@code DC654CFF}, L 0.53333336, A 0.56078434, B 0.5411765, alpha 1.0, hue 0.0947748, and saturation 0.6481056.
     * It can be represented as a packed float with the constant {@code -0x1.151f1p126F}.
     * <pre>
     * <font style='background-color: #DC654C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC654C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC654C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC654C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC654C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC654C'>&nbsp;@&nbsp;</font><font style='background-color: #DC654C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC654C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC654C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_RED = -0x1.151f1p126F;
    static { NAMED.put("bright brown red", -0x1.151f1p126F); LIST.add(-0x1.151f1p126F); }

    /**
     * This color constant "deep red brown" has RGBA8888 code {@code B0512CFF}, L 0.43529412, A 0.54901963, B 0.54509807, alpha 1.0, hue 0.11837745, and saturation 0.74138683.
     * It can be represented as a packed float with the constant {@code -0x1.1718dep126F}.
     * <pre>
     * <font style='background-color: #B0512C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0512C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0512C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0512C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0512C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0512C'>&nbsp;@&nbsp;</font><font style='background-color: #B0512C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0512C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0512C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_BROWN = -0x1.1718dep126F;
    static { NAMED.put("deep red brown", -0x1.1718dep126F); LIST.add(-0x1.1718dep126F); }

    /**
     * This color constant "bright red brown" has RGBA8888 code {@code DD754EFF}, L 0.5647059, A 0.54901963, B 0.54509807, alpha 1.0, hue 0.11837745, and saturation 0.6434678.
     * It can be represented as a packed float with the constant {@code -0x1.17192p126F}.
     * <pre>
     * <font style='background-color: #DD754E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD754E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD754E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD754E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD754E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD754E'>&nbsp;@&nbsp;</font><font style='background-color: #DD754E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD754E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD754E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_BROWN = -0x1.17192p126F;
    static { NAMED.put("bright red brown", -0x1.17192p126F); LIST.add(-0x1.17192p126F); }

    /**
     * This color constant "deep brown" has RGBA8888 code {@code B25D33FF}, L 0.45882353, A 0.5411765, B 0.54509807, alpha 1.0, hue 0.13222384, and saturation 0.7106127.
     * It can be represented as a packed float with the constant {@code -0x1.1714eap126F}.
     * <pre>
     * <font style='background-color: #B25D33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B25D33; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B25D33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B25D33'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B25D33'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B25D33'>&nbsp;@&nbsp;</font><font style='background-color: #B25D33; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B25D33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B25D33; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN = -0x1.1714eap126F;
    static { NAMED.put("deep brown", -0x1.1714eap126F); LIST.add(-0x1.1714eap126F); }

    /**
     * This color constant "bright brown" has RGBA8888 code {@code DE8155FF}, L 0.5921569, A 0.5411765, B 0.54509807, alpha 1.0, hue 0.13222384, and saturation 0.6381012.
     * It can be represented as a packed float with the constant {@code -0x1.17152ep126F}.
     * <pre>
     * <font style='background-color: #DE8155;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE8155; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE8155;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DE8155'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DE8155'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DE8155'>&nbsp;@&nbsp;</font><font style='background-color: #DE8155; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE8155;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE8155; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN = -0x1.17152ep126F;
    static { NAMED.put("bright brown", -0x1.17152ep126F); LIST.add(-0x1.17152ep126F); }

    /**
     * This color constant "deep orange brown" has RGBA8888 code {@code B3612EFF}, L 0.46666667, A 0.5372549, B 0.54901963, alpha 1.0, hue 0.14655739, and saturation 0.7688726.
     * It can be represented as a packed float with the constant {@code -0x1.1912eep126F}.
     * <pre>
     * <font style='background-color: #B3612E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3612E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3612E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B3612E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B3612E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B3612E'>&nbsp;@&nbsp;</font><font style='background-color: #B3612E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B3612E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B3612E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_BROWN = -0x1.1912eep126F;
    static { NAMED.put("deep orange brown", -0x1.1912eep126F); LIST.add(-0x1.1912eep126F); }

    /**
     * This color constant "bright orange brown" has RGBA8888 code {@code DF8650FF}, L 0.6039216, A 0.5372549, B 0.54901963, alpha 1.0, hue 0.14655739, and saturation 0.6433424.
     * It can be represented as a packed float with the constant {@code -0x1.191334p126F}.
     * <pre>
     * <font style='background-color: #DF8650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF8650; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF8650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF8650'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF8650'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF8650'>&nbsp;@&nbsp;</font><font style='background-color: #DF8650; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF8650;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF8650; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_BROWN = -0x1.191334p126F;
    static { NAMED.put("bright orange brown", -0x1.191334p126F); LIST.add(-0x1.191334p126F); }

    /**
     * This color constant "deep brown orange" has RGBA8888 code {@code B56832FF}, L 0.48235294, A 0.53333336, B 0.54901963, alpha 1.0, hue 0.15494394, and saturation 0.7402681.
     * It can be represented as a packed float with the constant {@code -0x1.1910f6p126F}.
     * <pre>
     * <font style='background-color: #B56832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B56832; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B56832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B56832'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B56832'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B56832'>&nbsp;@&nbsp;</font><font style='background-color: #B56832; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B56832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B56832; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN_ORANGE = -0x1.1910f6p126F;
    static { NAMED.put("deep brown orange", -0x1.1910f6p126F); LIST.add(-0x1.1910f6p126F); }

    /**
     * This color constant "bright brown orange" has RGBA8888 code {@code DE8B53FF}, L 0.6117647, A 0.53333336, B 0.54901963, alpha 1.0, hue 0.15494394, and saturation 0.63231236.
     * It can be represented as a packed float with the constant {@code -0x1.191138p126F}.
     * <pre>
     * <font style='background-color: #DE8B53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE8B53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE8B53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DE8B53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DE8B53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DE8B53'>&nbsp;@&nbsp;</font><font style='background-color: #DE8B53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE8B53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE8B53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BROWN_ORANGE = -0x1.191138p126F;
    static { NAMED.put("bright brown orange", -0x1.191138p126F); LIST.add(-0x1.191138p126F); }

    /**
     * This color constant "deep orange" has RGBA8888 code {@code B36731FF}, L 0.47843137, A 0.5294118, B 0.54901963, alpha 1.0, hue 0.16398115, and saturation 0.75048953.
     * It can be represented as a packed float with the constant {@code -0x1.190ef4p126F}.
     * <pre>
     * <font style='background-color: #B36731;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B36731; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B36731;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B36731'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B36731'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B36731'>&nbsp;@&nbsp;</font><font style='background-color: #B36731; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B36731;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B36731; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE = -0x1.190ef4p126F;
    static { NAMED.put("deep orange", -0x1.190ef4p126F); LIST.add(-0x1.190ef4p126F); }

    /**
     * This color constant "bright orange" has RGBA8888 code {@code E08C54FF}, L 0.6156863, A 0.53333336, B 0.54901963, alpha 1.0, hue 0.15494394, and saturation 0.63231236.
     * It can be represented as a packed float with the constant {@code -0x1.19113ap126F}.
     * <pre>
     * <font style='background-color: #E08C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E08C54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E08C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E08C54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E08C54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E08C54'>&nbsp;@&nbsp;</font><font style='background-color: #E08C54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E08C54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E08C54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE = -0x1.19113ap126F;
    static { NAMED.put("bright orange", -0x1.19113ap126F); LIST.add(-0x1.19113ap126F); }

    /**
     * This color constant "deep saffron orange" has RGBA8888 code {@code B06F35FF}, L 0.49019608, A 0.52156866, B 0.54901963, alpha 1.0, hue 0.18403731, and saturation 0.7410841.
     * It can be represented as a packed float with the constant {@code -0x1.190afap126F}.
     * <pre>
     * <font style='background-color: #B06F35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B06F35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B06F35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B06F35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B06F35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B06F35'>&nbsp;@&nbsp;</font><font style='background-color: #B06F35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B06F35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B06F35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_ORANGE = -0x1.190afap126F;
    static { NAMED.put("deep saffron orange", -0x1.190afap126F); LIST.add(-0x1.190afap126F); }

    /**
     * This color constant "bright saffron orange" has RGBA8888 code {@code DF975AFF}, L 0.6392157, A 0.5254902, B 0.54901963, alpha 1.0, hue 0.1736814, and saturation 0.6149676.
     * It can be represented as a packed float with the constant {@code -0x1.190d46p126F}.
     * <pre>
     * <font style='background-color: #DF975A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF975A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF975A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF975A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF975A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF975A'>&nbsp;@&nbsp;</font><font style='background-color: #DF975A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF975A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF975A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_ORANGE = -0x1.190d46p126F;
    static { NAMED.put("bright saffron orange", -0x1.190d46p126F); LIST.add(-0x1.190d46p126F); }

    /**
     * This color constant "deep orange saffron" has RGBA8888 code {@code B47B35FF}, L 0.52156866, A 0.5176471, B 0.5529412, alpha 1.0, hue 0.19880433, and saturation 0.75189763.
     * It can be represented as a packed float with the constant {@code -0x1.1b090ap126F}.
     * <pre>
     * <font style='background-color: #B47B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B47B35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B47B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B47B35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B47B35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B47B35'>&nbsp;@&nbsp;</font><font style='background-color: #B47B35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B47B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B47B35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_SAFFRON = -0x1.1b090ap126F;
    static { NAMED.put("deep orange saffron", -0x1.1b090ap126F); LIST.add(-0x1.1b090ap126F); }

    /**
     * This color constant "bright orange saffron" has RGBA8888 code {@code E0A259FF}, L 0.6666667, A 0.5176471, B 0.5529412, alpha 1.0, hue 0.19880433, and saturation 0.64936614.
     * It can be represented as a packed float with the constant {@code -0x1.1b0954p126F}.
     * <pre>
     * <font style='background-color: #E0A259;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0A259; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0A259;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0A259'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0A259'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0A259'>&nbsp;@&nbsp;</font><font style='background-color: #E0A259; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0A259;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0A259; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_ORANGE_SAFFRON = -0x1.1b0954p126F;
    static { NAMED.put("bright orange saffron", -0x1.1b0954p126F); LIST.add(-0x1.1b0954p126F); }

    /**
     * This color constant "deep saffron" has RGBA8888 code {@code B4863CFF}, L 0.54509807, A 0.50980395, B 0.5529412, alpha 1.0, hue 0.22084753, and saturation 0.74504715.
     * It can be represented as a packed float with the constant {@code -0x1.1b0516p126F}.
     * <pre>
     * <font style='background-color: #B4863C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4863C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4863C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4863C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4863C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4863C'>&nbsp;@&nbsp;</font><font style='background-color: #B4863C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4863C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4863C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON = -0x1.1b0516p126F;
    static { NAMED.put("deep saffron", -0x1.1b0516p126F); LIST.add(-0x1.1b0516p126F); }

    /**
     * This color constant "bright saffron" has RGBA8888 code {@code DEAD5FFF}, L 0.6901961, A 0.50980395, B 0.5529412, alpha 1.0, hue 0.22084753, and saturation 0.64108706.
     * It can be represented as a packed float with the constant {@code -0x1.1b056p126F}.
     * <pre>
     * <font style='background-color: #DEAD5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEAD5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEAD5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEAD5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEAD5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEAD5F'>&nbsp;@&nbsp;</font><font style='background-color: #DEAD5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEAD5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEAD5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON = -0x1.1b056p126F;
    static { NAMED.put("bright saffron", -0x1.1b056p126F); LIST.add(-0x1.1b056p126F); }

    /**
     * This color constant "deep yellow saffron" has RGBA8888 code {@code B0923AFF}, L 0.5686275, A 0.49803922, B 0.5568628, alpha 1.0, hue 0.25547296, and saturation 0.76660603.
     * It can be represented as a packed float with the constant {@code -0x1.1cff22p126F}.
     * <pre>
     * <font style='background-color: #B0923A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0923A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0923A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0923A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0923A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0923A'>&nbsp;@&nbsp;</font><font style='background-color: #B0923A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0923A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0923A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_SAFFRON = -0x1.1cff22p126F;
    static { NAMED.put("deep yellow saffron", -0x1.1cff22p126F); LIST.add(-0x1.1cff22p126F); }

    /**
     * This color constant "bright yellow saffron" has RGBA8888 code {@code DEBE62FF}, L 0.73333335, A 0.49803922, B 0.5568628, alpha 1.0, hue 0.25547296, and saturation 0.66206884.
     * It can be represented as a packed float with the constant {@code -0x1.1cff76p126F}.
     * <pre>
     * <font style='background-color: #DEBE62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEBE62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEBE62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEBE62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEBE62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEBE62'>&nbsp;@&nbsp;</font><font style='background-color: #DEBE62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEBE62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEBE62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_SAFFRON = -0x1.1cff76p126F;
    static { NAMED.put("bright yellow saffron", -0x1.1cff76p126F); LIST.add(-0x1.1cff76p126F); }

    /**
     * This color constant "deep saffron yellow" has RGBA8888 code {@code B4A740FF}, L 0.62352943, A 0.48235294, B 0.56078434, alpha 1.0, hue 0.29496104, and saturation 0.771586.
     * It can be represented as a packed float with the constant {@code -0x1.1ef73ep126F}.
     * <pre>
     * <font style='background-color: #B4A740;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4A740; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4A740;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4A740'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4A740'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4A740'>&nbsp;@&nbsp;</font><font style='background-color: #B4A740; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4A740;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4A740; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_SAFFRON_YELLOW = -0x1.1ef73ep126F;
    static { NAMED.put("deep saffron yellow", -0x1.1ef73ep126F); LIST.add(-0x1.1ef73ep126F); }

    /**
     * This color constant "bright saffron yellow" has RGBA8888 code {@code E1D468FF}, L 0.79607844, A 0.48235294, B 0.56078434, alpha 1.0, hue 0.29496104, and saturation 0.67513776.
     * It can be represented as a packed float with the constant {@code -0x1.1ef796p126F}.
     * <pre>
     * <font style='background-color: #E1D468;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1D468; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1D468;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1D468'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1D468'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1D468'>&nbsp;@&nbsp;</font><font style='background-color: #E1D468; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1D468;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1D468; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_SAFFRON_YELLOW = -0x1.1ef796p126F;
    static { NAMED.put("bright saffron yellow", -0x1.1ef796p126F); LIST.add(-0x1.1ef796p126F); }

    /**
     * This color constant "deep yellow" has RGBA8888 code {@code B4C048FF}, L 0.6901961, A 0.47058824, B 0.5647059, alpha 1.0, hue 0.31789324, and saturation 0.7742828.
     * It can be represented as a packed float with the constant {@code -0x1.20f16p126F}.
     * <pre>
     * <font style='background-color: #B4C048;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4C048; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4C048;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4C048'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4C048'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4C048'>&nbsp;@&nbsp;</font><font style='background-color: #B4C048; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4C048;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4C048; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW = -0x1.20f16p126F;
    static { NAMED.put("deep yellow", -0x1.20f16p126F); LIST.add(-0x1.20f16p126F); }

    /**
     * This color constant "bright yellow" has RGBA8888 code {@code DDEC6FFF}, L 0.8627451, A 0.46666667, B 0.5647059, alpha 1.0, hue 0.32570946, and saturation 0.6775818.
     * It can be represented as a packed float with the constant {@code -0x1.20efb8p126F}.
     * <pre>
     * <font style='background-color: #DDEC6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDEC6F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDEC6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDEC6F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDEC6F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDEC6F'>&nbsp;@&nbsp;</font><font style='background-color: #DDEC6F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDEC6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDEC6F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW = -0x1.20efb8p126F;
    static { NAMED.put("bright yellow", -0x1.20efb8p126F); LIST.add(-0x1.20efb8p126F); }

    /**
     * This color constant "deep lime yellow" has RGBA8888 code {@code A7BD4CFF}, L 0.67058825, A 0.46666667, B 0.56078434, alpha 1.0, hue 0.32983655, and saturation 0.7394581.
     * It can be represented as a packed float with the constant {@code -0x1.1eef56p126F}.
     * <pre>
     * <font style='background-color: #A7BD4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7BD4C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7BD4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A7BD4C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A7BD4C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A7BD4C'>&nbsp;@&nbsp;</font><font style='background-color: #A7BD4C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7BD4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7BD4C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_YELLOW = -0x1.1eef56p126F;
    static { NAMED.put("deep lime yellow", -0x1.1eef56p126F); LIST.add(-0x1.1eef56p126F); }

    /**
     * This color constant "bright lime yellow" has RGBA8888 code {@code D0EA73FF}, L 0.84313726, A 0.4627451, B 0.56078434, alpha 1.0, hue 0.3375212, and saturation 0.6518197.
     * It can be represented as a packed float with the constant {@code -0x1.1eedaep126F}.
     * <pre>
     * <font style='background-color: #D0EA73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0EA73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0EA73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0EA73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0EA73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0EA73'>&nbsp;@&nbsp;</font><font style='background-color: #D0EA73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0EA73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0EA73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_YELLOW = -0x1.1eedaep126F;
    static { NAMED.put("bright lime yellow", -0x1.1eedaep126F); LIST.add(-0x1.1eedaep126F); }

    /**
     * This color constant "deep yellow lime" has RGBA8888 code {@code 95BA47FF}, L 0.64705884, A 0.45490196, B 0.56078434, alpha 1.0, hue 0.35160458, and saturation 0.759841.
     * It can be represented as a packed float with the constant {@code -0x1.1ee94ap126F}.
     * <pre>
     * <font style='background-color: #95BA47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #95BA47; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #95BA47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #95BA47'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #95BA47'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #95BA47'>&nbsp;@&nbsp;</font><font style='background-color: #95BA47; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #95BA47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #95BA47; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_LIME = -0x1.1ee94ap126F;
    static { NAMED.put("deep yellow lime", -0x1.1ee94ap126F); LIST.add(-0x1.1ee94ap126F); }

    /**
     * This color constant "bright yellow lime" has RGBA8888 code {@code BFE86FFF}, L 0.81960785, A 0.45490196, B 0.56078434, alpha 1.0, hue 0.35160458, and saturation 0.6568117.
     * It can be represented as a packed float with the constant {@code -0x1.1ee9a2p126F}.
     * <pre>
     * <font style='background-color: #BFE86F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFE86F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFE86F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFE86F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFE86F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFE86F'>&nbsp;@&nbsp;</font><font style='background-color: #BFE86F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFE86F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFE86F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_YELLOW_LIME = -0x1.1ee9a2p126F;
    static { NAMED.put("bright yellow lime", -0x1.1ee9a2p126F); LIST.add(-0x1.1ee9a2p126F); }

    /**
     * This color constant "deep lime" has RGBA8888 code {@code 82BE46FF}, L 0.6431373, A 0.44313726, B 0.56078434, alpha 1.0, hue 0.3697009, and saturation 0.7748444.
     * It can be represented as a packed float with the constant {@code -0x1.1ee348p126F}.
     * <pre>
     * <font style='background-color: #82BE46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82BE46; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82BE46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #82BE46'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #82BE46'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #82BE46'>&nbsp;@&nbsp;</font><font style='background-color: #82BE46; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82BE46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82BE46; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME = -0x1.1ee348p126F;
    static { NAMED.put("deep lime", -0x1.1ee348p126F); LIST.add(-0x1.1ee348p126F); }

    /**
     * This color constant "bright lime" has RGBA8888 code {@code A8E96BFF}, L 0.8039216, A 0.44313726, B 0.56078434, alpha 1.0, hue 0.3697009, and saturation 0.6764515.
     * It can be represented as a packed float with the constant {@code -0x1.1ee39ap126F}.
     * <pre>
     * <font style='background-color: #A8E96B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8E96B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8E96B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8E96B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8E96B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8E96B'>&nbsp;@&nbsp;</font><font style='background-color: #A8E96B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8E96B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8E96B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME = -0x1.1ee39ap126F;
    static { NAMED.put("bright lime", -0x1.1ee39ap126F); LIST.add(-0x1.1ee39ap126F); }

    /**
     * This color constant "deep green lime" has RGBA8888 code {@code 6EBA40FF}, L 0.6156863, A 0.43529412, B 0.56078434, alpha 1.0, hue 0.37996814, and saturation 0.78369796.
     * It can be represented as a packed float with the constant {@code -0x1.1edf3ap126F}.
     * <pre>
     * <font style='background-color: #6EBA40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6EBA40; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6EBA40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6EBA40'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6EBA40'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6EBA40'>&nbsp;@&nbsp;</font><font style='background-color: #6EBA40; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6EBA40;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6EBA40; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_LIME = -0x1.1edf3ap126F;
    static { NAMED.put("deep green lime", -0x1.1edf3ap126F); LIST.add(-0x1.1edf3ap126F); }

    /**
     * This color constant "bright green lime" has RGBA8888 code {@code 96E868FF}, L 0.78431374, A 0.43529412, B 0.56078434, alpha 1.0, hue 0.37996814, and saturation 0.67842513.
     * It can be represented as a packed float with the constant {@code -0x1.1edf9p126F}.
     * <pre>
     * <font style='background-color: #96E868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96E868; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96E868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #96E868'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #96E868'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #96E868'>&nbsp;@&nbsp;</font><font style='background-color: #96E868; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96E868;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96E868; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_LIME = -0x1.1edf9p126F;
    static { NAMED.put("bright green lime", -0x1.1edf9p126F); LIST.add(-0x1.1edf9p126F); }

    /**
     * This color constant "deep lime green" has RGBA8888 code {@code 50BD47FF}, L 0.60784316, A 0.42352942, B 0.5568628, alpha 1.0, hue 0.39822578, and saturation 0.76235944.
     * It can be represented as a packed float with the constant {@code -0x1.1cd936p126F}.
     * <pre>
     * <font style='background-color: #50BD47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50BD47; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50BD47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #50BD47'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #50BD47'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #50BD47'>&nbsp;@&nbsp;</font><font style='background-color: #50BD47; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50BD47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50BD47; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_LIME_GREEN = -0x1.1cd936p126F;
    static { NAMED.put("deep lime green", -0x1.1cd936p126F); LIST.add(-0x1.1cd936p126F); }

    /**
     * This color constant "bright lime green" has RGBA8888 code {@code 76E86BFF}, L 0.7607843, A 0.42352942, B 0.5568628, alpha 1.0, hue 0.39822578, and saturation 0.6593379.
     * It can be represented as a packed float with the constant {@code -0x1.1cd984p126F}.
     * <pre>
     * <font style='background-color: #76E86B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76E86B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76E86B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #76E86B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #76E86B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #76E86B'>&nbsp;@&nbsp;</font><font style='background-color: #76E86B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76E86B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76E86B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_LIME_GREEN = -0x1.1cd984p126F;
    static { NAMED.put("bright lime green", -0x1.1cd984p126F); LIST.add(-0x1.1cd984p126F); }

    /**
     * This color constant "deep green" has RGBA8888 code {@code 30B24CFF}, L 0.5647059, A 0.41960785, B 0.54901963, alpha 1.0, hue 0.41284364, and saturation 0.84577453.
     * It can be represented as a packed float with the constant {@code -0x1.18d72p126F}.
     * <pre>
     * <font style='background-color: #30B24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30B24C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30B24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #30B24C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #30B24C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #30B24C'>&nbsp;@&nbsp;</font><font style='background-color: #30B24C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30B24C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30B24C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN = -0x1.18d72p126F;
    static { NAMED.put("deep green", -0x1.18d72p126F); LIST.add(-0x1.18d72p126F); }

    /**
     * This color constant "bright green" has RGBA8888 code {@code 47CA5FFF}, L 0.64705884, A 0.41960785, B 0.54901963, alpha 1.0, hue 0.41284364, and saturation 0.7775669.
     * It can be represented as a packed float with the constant {@code -0x1.18d74ap126F}.
     * <pre>
     * <font style='background-color: #47CA5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #47CA5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #47CA5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #47CA5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #47CA5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #47CA5F'>&nbsp;@&nbsp;</font><font style='background-color: #47CA5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #47CA5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #47CA5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN = -0x1.18d74ap126F;
    static { NAMED.put("bright green", -0x1.18d74ap126F); LIST.add(-0x1.18d74ap126F); }

    /**
     * This color constant "deep cyan green" has RGBA8888 code {@code 23A979FF}, L 0.54509807, A 0.43137255, B 0.5137255, alpha 1.0, hue 0.46857655, and saturation 0.91879773.
     * It can be represented as a packed float with the constant {@code -0x1.06dd16p126F}.
     * <pre>
     * <font style='background-color: #23A979;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #23A979; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #23A979;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #23A979'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #23A979'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #23A979'>&nbsp;@&nbsp;</font><font style='background-color: #23A979; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #23A979;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #23A979; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_GREEN = -0x1.06dd16p126F;
    static { NAMED.put("deep cyan green", -0x1.06dd16p126F); LIST.add(-0x1.06dd16p126F); }

    /**
     * This color constant "bright cyan green" has RGBA8888 code {@code 30B482FF}, L 0.58431375, A 0.43137255, B 0.5176471, alpha 1.0, hue 0.4599463, and saturation 0.86381775.
     * It can be represented as a packed float with the constant {@code -0x1.08dd2ap126F}.
     * <pre>
     * <font style='background-color: #30B482;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30B482; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30B482;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #30B482'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #30B482'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #30B482'>&nbsp;@&nbsp;</font><font style='background-color: #30B482; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30B482;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30B482; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_GREEN = -0x1.08dd2ap126F;
    static { NAMED.put("bright cyan green", -0x1.08dd2ap126F); LIST.add(-0x1.08dd2ap126F); }

    /**
     * This color constant "deep green cyan" has RGBA8888 code {@code 2BAD9AFF}, L 0.5686275, A 0.4392157, B 0.49803922, alpha 1.0, hue 0.5051194, and saturation 0.8649377.
     * It can be represented as a packed float with the constant {@code -0x1.fee122p125F}.
     * <pre>
     * <font style='background-color: #2BAD9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2BAD9A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2BAD9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2BAD9A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2BAD9A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2BAD9A'>&nbsp;@&nbsp;</font><font style='background-color: #2BAD9A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2BAD9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2BAD9A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREEN_CYAN = -0x1.fee122p125F;
    static { NAMED.put("deep green cyan", -0x1.fee122p125F); LIST.add(-0x1.fee122p125F); }

    /**
     * This color constant "bright green cyan" has RGBA8888 code {@code 44C4B1FF}, L 0.6509804, A 0.4392157, B 0.49411765, alpha 1.0, hue 0.5153601, and saturation 0.82281476.
     * It can be represented as a packed float with the constant {@code -0x1.fce14cp125F}.
     * <pre>
     * <font style='background-color: #44C4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #44C4B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #44C4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #44C4B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #44C4B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #44C4B1'>&nbsp;@&nbsp;</font><font style='background-color: #44C4B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #44C4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #44C4B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_GREEN_CYAN = -0x1.fce14cp125F;
    static { NAMED.put("bright green cyan", -0x1.fce14cp125F); LIST.add(-0x1.fce14cp125F); }

    /**
     * This color constant "deep cyan" has RGBA8888 code {@code 38BDC1FF}, L 0.6313726, A 0.44313726, B 0.48235294, alpha 1.0, hue 0.54788184, and saturation 0.8467647.
     * It can be represented as a packed float with the constant {@code -0x1.f6e342p125F}.
     * <pre>
     * <font style='background-color: #38BDC1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38BDC1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38BDC1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #38BDC1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #38BDC1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #38BDC1'>&nbsp;@&nbsp;</font><font style='background-color: #38BDC1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #38BDC1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #38BDC1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN = -0x1.f6e342p125F;
    static { NAMED.put("deep cyan", -0x1.f6e342p125F); LIST.add(-0x1.f6e342p125F); }

    /**
     * This color constant "bright cyan" has RGBA8888 code {@code 63E7ECFF}, L 0.7882353, A 0.44313726, B 0.47843137, alpha 1.0, hue 0.5576882, and saturation 0.75945747.
     * It can be represented as a packed float with the constant {@code -0x1.f4e392p125F}.
     * <pre>
     * <font style='background-color: #63E7EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63E7EC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63E7EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #63E7EC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #63E7EC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #63E7EC'>&nbsp;@&nbsp;</font><font style='background-color: #63E7EC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63E7EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63E7EC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN = -0x1.f4e392p125F;
    static { NAMED.put("bright cyan", -0x1.f4e392p125F); LIST.add(-0x1.f4e392p125F); }

    /**
     * This color constant "deep blue cyan" has RGBA8888 code {@code 319DBCFF}, L 0.5411765, A 0.45490196, B 0.4627451, alpha 1.0, hue 0.60989827, and saturation 0.85571027.
     * It can be represented as a packed float with the constant {@code -0x1.ece914p125F}.
     * <pre>
     * <font style='background-color: #319DBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #319DBC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #319DBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #319DBC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #319DBC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #319DBC'>&nbsp;@&nbsp;</font><font style='background-color: #319DBC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #319DBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #319DBC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_CYAN = -0x1.ece914p125F;
    static { NAMED.put("deep blue cyan", -0x1.ece914p125F); LIST.add(-0x1.ece914p125F); }

    /**
     * This color constant "bright blue cyan" has RGBA8888 code {@code 5AC7E8FF}, L 0.6901961, A 0.45490196, B 0.4627451, alpha 1.0, hue 0.60989827, and saturation 0.7487465.
     * It can be represented as a packed float with the constant {@code -0x1.ece96p125F}.
     * <pre>
     * <font style='background-color: #5AC7E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AC7E8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AC7E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5AC7E8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5AC7E8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5AC7E8'>&nbsp;@&nbsp;</font><font style='background-color: #5AC7E8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AC7E8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AC7E8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_CYAN = -0x1.ece96p125F;
    static { NAMED.put("bright blue cyan", -0x1.ece96p125F); LIST.add(-0x1.ece96p125F); }

    /**
     * This color constant "deep cyan blue" has RGBA8888 code {@code 2D80B7FF}, L 0.46666667, A 0.47058824, B 0.44705883, alpha 1.0, hue 0.6692883, and saturation 0.7752004.
     * It can be represented as a packed float with the constant {@code -0x1.e4f0eep125F}.
     * <pre>
     * <font style='background-color: #2D80B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D80B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D80B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D80B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D80B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D80B7'>&nbsp;@&nbsp;</font><font style='background-color: #2D80B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D80B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D80B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_CYAN_BLUE = -0x1.e4f0eep125F;
    static { NAMED.put("deep cyan blue", -0x1.e4f0eep125F); LIST.add(-0x1.e4f0eep125F); }

    /**
     * This color constant "bright cyan blue" has RGBA8888 code {@code 51A7E2FF}, L 0.6, A 0.46666667, B 0.44705883, alpha 1.0, hue 0.66055703, and saturation 0.7118052.
     * It can be represented as a packed float with the constant {@code -0x1.e4ef32p125F}.
     * <pre>
     * <font style='background-color: #51A7E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51A7E2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51A7E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #51A7E2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #51A7E2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #51A7E2'>&nbsp;@&nbsp;</font><font style='background-color: #51A7E2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #51A7E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #51A7E2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_CYAN_BLUE = -0x1.e4ef32p125F;
    static { NAMED.put("bright cyan blue", -0x1.e4ef32p125F); LIST.add(-0x1.e4ef32p125F); }

    /**
     * This color constant "deep blue" has RGBA8888 code {@code 1D44B0FF}, L 0.32156864, A 0.4862745, B 0.40784314, alpha 1.0, hue 0.7264561, and saturation 0.80855536.
     * It can be represented as a packed float with the constant {@code -0x1.d0f8a4p125F}.
     * <pre>
     * <font style='background-color: #1D44B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D44B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D44B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D44B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D44B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D44B0'>&nbsp;@&nbsp;</font><font style='background-color: #1D44B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D44B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D44B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE = -0x1.d0f8a4p125F;
    static { NAMED.put("deep blue", -0x1.d0f8a4p125F); LIST.add(-0x1.d0f8a4p125F); }

    /**
     * This color constant "bright blue" has RGBA8888 code {@code 3967DCFF}, L 0.43529412, A 0.49019608, B 0.40784314, alpha 1.0, hue 0.73312366, and saturation 0.7531836.
     * It can be represented as a packed float with the constant {@code -0x1.d0fadep125F}.
     * <pre>
     * <font style='background-color: #3967DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3967DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3967DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3967DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3967DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3967DC'>&nbsp;@&nbsp;</font><font style='background-color: #3967DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3967DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3967DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE = -0x1.d0fadep125F;
    static { NAMED.put("bright blue", -0x1.d0fadep125F); LIST.add(-0x1.d0fadep125F); }

    /**
     * This color constant "deep violet blue" has RGBA8888 code {@code 322CAFFF}, L 0.2901961, A 0.5058824, B 0.39607844, alpha 1.0, hue 0.75899065, and saturation 0.73004127.
     * It can be represented as a packed float with the constant {@code -0x1.cb0294p125F}.
     * <pre>
     * <font style='background-color: #322CAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #322CAF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #322CAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #322CAF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #322CAF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #322CAF'>&nbsp;@&nbsp;</font><font style='background-color: #322CAF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #322CAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #322CAF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_BLUE = -0x1.cb0294p125F;
    static { NAMED.put("deep violet blue", -0x1.cb0294p125F); LIST.add(-0x1.cb0294p125F); }

    /**
     * This color constant "bright violet blue" has RGBA8888 code {@code 4B50DBFF}, L 0.4, A 0.50980395, B 0.39607844, alpha 1.0, hue 0.7649754, and saturation 0.7745521.
     * It can be represented as a packed float with the constant {@code -0x1.cb04ccp125F}.
     * <pre>
     * <font style='background-color: #4B50DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B50DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B50DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B50DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B50DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B50DB'>&nbsp;@&nbsp;</font><font style='background-color: #4B50DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B50DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B50DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_BLUE = -0x1.cb04ccp125F;
    static { NAMED.put("bright violet blue", -0x1.cb04ccp125F); LIST.add(-0x1.cb04ccp125F); }

    /**
     * This color constant "deep blue violet" has RGBA8888 code {@code 4B2BB2FF}, L 0.30980393, A 0.5254902, B 0.4, alpha 1.0, hue 0.7897194, and saturation 0.754817.
     * It can be represented as a packed float with the constant {@code -0x1.cd0c9ep125F}.
     * <pre>
     * <font style='background-color: #4B2BB2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2BB2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2BB2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B2BB2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B2BB2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B2BB2'>&nbsp;@&nbsp;</font><font style='background-color: #4B2BB2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2BB2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2BB2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE_VIOLET = -0x1.cd0c9ep125F;
    static { NAMED.put("deep blue violet", -0x1.cd0c9ep125F); LIST.add(-0x1.cd0c9ep125F); }

    /**
     * This color constant "bright blue violet" has RGBA8888 code {@code 684DDDFF}, L 0.41568628, A 0.5294118, B 0.4, alpha 1.0, hue 0.795517, and saturation 0.77345806.
     * It can be represented as a packed float with the constant {@code -0x1.cd0ed4p125F}.
     * <pre>
     * <font style='background-color: #684DDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #684DDD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #684DDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #684DDD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #684DDD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #684DDD'>&nbsp;@&nbsp;</font><font style='background-color: #684DDD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #684DDD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #684DDD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_BLUE_VIOLET = -0x1.cd0ed4p125F;
    static { NAMED.put("bright blue violet", -0x1.cd0ed4p125F); LIST.add(-0x1.cd0ed4p125F); }

    /**
     * This color constant "deep violet" has RGBA8888 code {@code 662CB3FF}, L 0.34117648, A 0.54901963, B 0.40784314, alpha 1.0, hue 0.82780534, and saturation 0.7634871.
     * It can be represented as a packed float with the constant {@code -0x1.d118aep125F}.
     * <pre>
     * <font style='background-color: #662CB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #662CB3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #662CB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #662CB3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #662CB3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #662CB3'>&nbsp;@&nbsp;</font><font style='background-color: #662CB3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #662CB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #662CB3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET = -0x1.d118aep125F;
    static { NAMED.put("deep violet", -0x1.d118aep125F); LIST.add(-0x1.d118aep125F); }

    /**
     * This color constant "bright violet" has RGBA8888 code {@code 8850DFFF}, L 0.45490196, A 0.54901963, B 0.40784314, alpha 1.0, hue 0.82780534, and saturation 0.78594255.
     * It can be represented as a packed float with the constant {@code -0x1.d118e8p125F}.
     * <pre>
     * <font style='background-color: #8850DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8850DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8850DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8850DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8850DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8850DF'>&nbsp;@&nbsp;</font><font style='background-color: #8850DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8850DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8850DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET = -0x1.d118e8p125F;
    static { NAMED.put("bright violet", -0x1.d118e8p125F); LIST.add(-0x1.d118e8p125F); }

    /**
     * This color constant "deep purple violet" has RGBA8888 code {@code 6D2FB3FF}, L 0.34901962, A 0.5529412, B 0.4117647, alpha 1.0, hue 0.83601886, and saturation 0.7635416.
     * It can be represented as a packed float with the constant {@code -0x1.d31ab2p125F}.
     * <pre>
     * <font style='background-color: #6D2FB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D2FB3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D2FB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D2FB3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D2FB3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D2FB3'>&nbsp;@&nbsp;</font><font style='background-color: #6D2FB3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D2FB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D2FB3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_VIOLET = -0x1.d31ab2p125F;
    static { NAMED.put("deep purple violet", -0x1.d31ab2p125F); LIST.add(-0x1.d31ab2p125F); }

    /**
     * This color constant "bright purple violet" has RGBA8888 code {@code 9052DFFF}, L 0.46666667, A 0.5529412, B 0.4117647, alpha 1.0, hue 0.83601886, and saturation 0.7635416.
     * It can be represented as a packed float with the constant {@code -0x1.d31aeep125F}.
     * <pre>
     * <font style='background-color: #9052DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9052DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9052DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9052DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9052DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9052DF'>&nbsp;@&nbsp;</font><font style='background-color: #9052DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9052DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9052DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_VIOLET = -0x1.d31aeep125F;
    static { NAMED.put("bright purple violet", -0x1.d31aeep125F); LIST.add(-0x1.d31aeep125F); }

    /**
     * This color constant "deep violet purple" has RGBA8888 code {@code 7A31B5FF}, L 0.36862746, A 0.56078434, B 0.41568628, alpha 1.0, hue 0.84942675, and saturation 0.7495397.
     * It can be represented as a packed float with the constant {@code -0x1.d51ebcp125F}.
     * <pre>
     * <font style='background-color: #7A31B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A31B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A31B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A31B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A31B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A31B5'>&nbsp;@&nbsp;</font><font style='background-color: #7A31B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A31B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A31B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET_PURPLE = -0x1.d51ebcp125F;
    static { NAMED.put("deep violet purple", -0x1.d51ebcp125F); LIST.add(-0x1.d51ebcp125F); }

    /**
     * This color constant "bright violet purple" has RGBA8888 code {@code A155E2FF}, L 0.49019608, A 0.56078434, B 0.41568628, alpha 1.0, hue 0.84942675, and saturation 0.78260756.
     * It can be represented as a packed float with the constant {@code -0x1.d51efap125F}.
     * <pre>
     * <font style='background-color: #A155E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A155E2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A155E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A155E2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A155E2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A155E2'>&nbsp;@&nbsp;</font><font style='background-color: #A155E2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A155E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A155E2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_VIOLET_PURPLE = -0x1.d51efap125F;
    static { NAMED.put("bright violet purple", -0x1.d51efap125F); LIST.add(-0x1.d51efap125F); }

    /**
     * This color constant "deep purple" has RGBA8888 code {@code 8631B5FF}, L 0.38431373, A 0.5686275, B 0.41960785, alpha 1.0, hue 0.8624701, and saturation 0.76223564.
     * It can be represented as a packed float with the constant {@code -0x1.d722c4p125F}.
     * <pre>
     * <font style='background-color: #8631B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8631B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8631B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8631B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8631B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8631B5'>&nbsp;@&nbsp;</font><font style='background-color: #8631B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8631B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8631B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE = -0x1.d722c4p125F;
    static { NAMED.put("deep purple", -0x1.d722c4p125F); LIST.add(-0x1.d722c4p125F); }

    /**
     * This color constant "bright purple" has RGBA8888 code {@code AE56E2FF}, L 0.5058824, A 0.5647059, B 0.41960785, alpha 1.0, hue 0.8578718, and saturation 0.7770175.
     * It can be represented as a packed float with the constant {@code -0x1.d72102p125F}.
     * <pre>
     * <font style='background-color: #AE56E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE56E2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE56E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE56E2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE56E2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE56E2'>&nbsp;@&nbsp;</font><font style='background-color: #AE56E2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE56E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE56E2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE = -0x1.d72102p125F;
    static { NAMED.put("bright purple", -0x1.d72102p125F); LIST.add(-0x1.d72102p125F); }

    /**
     * This color constant "deep magenta purple" has RGBA8888 code {@code 9431B1FF}, L 0.39607844, A 0.5764706, B 0.42745098, alpha 1.0, hue 0.87918407, and saturation 0.7709935.
     * It can be represented as a packed float with the constant {@code -0x1.db26cap125F}.
     * <pre>
     * <font style='background-color: #9431B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9431B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9431B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9431B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9431B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9431B1'>&nbsp;@&nbsp;</font><font style='background-color: #9431B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9431B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9431B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_PURPLE = -0x1.db26cap125F;
    static { NAMED.put("deep magenta purple", -0x1.db26cap125F); LIST.add(-0x1.db26cap125F); }

    /**
     * This color constant "bright magenta purple" has RGBA8888 code {@code BE56DEFF}, L 0.5254902, A 0.5764706, B 0.42745098, alpha 1.0, hue 0.87918407, and saturation 0.71959394.
     * It can be represented as a packed float with the constant {@code -0x1.db270cp125F}.
     * <pre>
     * <font style='background-color: #BE56DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE56DE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE56DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE56DE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE56DE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE56DE'>&nbsp;@&nbsp;</font><font style='background-color: #BE56DE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE56DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE56DE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_PURPLE = -0x1.db270cp125F;
    static { NAMED.put("bright magenta purple", -0x1.db270cp125F); LIST.add(-0x1.db270cp125F); }

    /**
     * This color constant "deep purple magenta" has RGBA8888 code {@code 9F35B3FF}, L 0.41960785, A 0.5803922, B 0.43137255, alpha 1.0, hue 0.8875299, and saturation 0.7516491.
     * It can be represented as a packed float with the constant {@code -0x1.dd28d6p125F}.
     * <pre>
     * <font style='background-color: #9F35B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F35B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F35B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9F35B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9F35B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9F35B3'>&nbsp;@&nbsp;</font><font style='background-color: #9F35B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9F35B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9F35B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE_MAGENTA = -0x1.dd28d6p125F;
    static { NAMED.put("deep purple magenta", -0x1.dd28d6p125F); LIST.add(-0x1.dd28d6p125F); }

    /**
     * This color constant "bright purple magenta" has RGBA8888 code {@code CA5AE0FF}, L 0.54901963, A 0.5803922, B 0.43137255, alpha 1.0, hue 0.8875299, and saturation 0.7313342.
     * It can be represented as a packed float with the constant {@code -0x1.dd2918p125F}.
     * <pre>
     * <font style='background-color: #CA5AE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA5AE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA5AE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA5AE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA5AE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA5AE0'>&nbsp;@&nbsp;</font><font style='background-color: #CA5AE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA5AE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA5AE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_PURPLE_MAGENTA = -0x1.dd2918p125F;
    static { NAMED.put("bright purple magenta", -0x1.dd2918p125F); LIST.add(-0x1.dd2918p125F); }

    /**
     * This color constant "deep magenta" has RGBA8888 code {@code AE36B6FF}, L 0.4392157, A 0.5882353, B 0.43529412, alpha 1.0, hue 0.899282, and saturation 0.7780842.
     * It can be represented as a packed float with the constant {@code -0x1.df2cep125F}.
     * <pre>
     * <font style='background-color: #AE36B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE36B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE36B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE36B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE36B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE36B6'>&nbsp;@&nbsp;</font><font style='background-color: #AE36B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE36B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE36B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA = -0x1.df2cep125F;
    static { NAMED.put("deep magenta", -0x1.df2cep125F); LIST.add(-0x1.df2cep125F); }

    /**
     * This color constant "bright magenta" has RGBA8888 code {@code DB5CE3FF}, L 0.57254905, A 0.5882353, B 0.43529412, alpha 1.0, hue 0.899282, and saturation 0.73713243.
     * It can be represented as a packed float with the constant {@code -0x1.df2d24p125F}.
     * <pre>
     * <font style='background-color: #DB5CE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB5CE3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB5CE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB5CE3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB5CE3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB5CE3'>&nbsp;@&nbsp;</font><font style='background-color: #DB5CE3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB5CE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB5CE3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA = -0x1.df2d24p125F;
    static { NAMED.put("bright magenta", -0x1.df2d24p125F); LIST.add(-0x1.df2d24p125F); }

    /**
     * This color constant "deep red magenta" has RGBA8888 code {@code B33581FF}, L 0.41960785, A 0.5882353, B 0.4745098, alpha 1.0, hue 0.9552493, and saturation 0.7347475.
     * It can be represented as a packed float with the constant {@code -0x1.f32cd6p125F}.
     * <pre>
     * <font style='background-color: #B33581;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B33581; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B33581;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B33581'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B33581'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B33581'>&nbsp;@&nbsp;</font><font style='background-color: #B33581; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B33581;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B33581; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED_MAGENTA = -0x1.f32cd6p125F;
    static { NAMED.put("deep red magenta", -0x1.f32cd6p125F); LIST.add(-0x1.f32cd6p125F); }

    /**
     * This color constant "bright red magenta" has RGBA8888 code {@code DD56A4FF}, L 0.5372549, A 0.5882353, B 0.4745098, alpha 1.0, hue 0.9552493, and saturation 0.63545734.
     * It can be represented as a packed float with the constant {@code -0x1.f32d12p125F}.
     * <pre>
     * <font style='background-color: #DD56A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD56A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD56A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD56A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD56A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD56A4'>&nbsp;@&nbsp;</font><font style='background-color: #DD56A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD56A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD56A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_RED_MAGENTA = -0x1.f32d12p125F;
    static { NAMED.put("bright red magenta", -0x1.f32d12p125F); LIST.add(-0x1.f32d12p125F); }

    /**
     * This color constant "deep magenta red" has RGBA8888 code {@code B33156FF}, L 0.4, A 0.58431375, B 0.5058824, alpha 1.0, hue 0.0110821845, and saturation 0.7460959.
     * It can be represented as a packed float with the constant {@code -0x1.032accp126F}.
     * <pre>
     * <font style='background-color: #B33156;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B33156; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B33156;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B33156'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B33156'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B33156'>&nbsp;@&nbsp;</font><font style='background-color: #B33156; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B33156;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B33156; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_MAGENTA_RED = -0x1.032accp126F;
    static { NAMED.put("deep magenta red", -0x1.032accp126F); LIST.add(-0x1.032accp126F); }

    /**
     * This color constant "bright magenta red" has RGBA8888 code {@code DF5376FF}, L 0.52156866, A 0.58431375, B 0.5058824, alpha 1.0, hue 0.0110821845, and saturation 0.6761494.
     * It can be represented as a packed float with the constant {@code -0x1.032b0ap126F}.
     * <pre>
     * <font style='background-color: #DF5376;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF5376; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF5376;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF5376'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF5376'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF5376'>&nbsp;@&nbsp;</font><font style='background-color: #DF5376; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF5376;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF5376; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRIGHT_MAGENTA_RED = -0x1.032b0ap126F;
    static { NAMED.put("bright magenta red", -0x1.032b0ap126F); LIST.add(-0x1.032b0ap126F); }

    /**
     * This color constant "some brown red" has RGBA8888 code {@code FD2918FF}, L 0.5019608, A 0.6039216, B 0.56078434, alpha 1.0, hue 0.08423945, and saturation 0.9631424.
     * It can be represented as a packed float with the constant {@code -0x1.1f35p126F}.
     * <pre>
     * <font style='background-color: #FD2918;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD2918; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD2918;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD2918'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD2918'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD2918'>&nbsp;@&nbsp;</font><font style='background-color: #FD2918; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD2918;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD2918; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BROWN_RED = -0x1.1f35p126F;
    static { NAMED.put("some brown red", -0x1.1f35p126F); LIST.add(-0x1.1f35p126F); }

    /**
     * This color constant "more brown red" has RGBA8888 code {@code FA4411FF}, L 0.5254902, A 0.5882353, B 0.5647059, alpha 1.0, hue 0.10071799, and saturation 0.9337011.
     * It can be represented as a packed float with the constant {@code -0x1.212d0cp126F}.
     * <pre>
     * <font style='background-color: #FA4411;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA4411; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA4411;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA4411'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA4411'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA4411'>&nbsp;@&nbsp;</font><font style='background-color: #FA4411; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA4411;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA4411; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BROWN_RED = -0x1.212d0cp126F;
    static { NAMED.put("more brown red", -0x1.212d0cp126F); LIST.add(-0x1.212d0cp126F); }

    /**
     * This color constant "more red brown" has RGBA8888 code {@code FD580BFF}, L 0.5568628, A 0.5764706, B 0.5686275, alpha 1.0, hue 0.11641185, and saturation 0.97421837.
     * It can be represented as a packed float with the constant {@code -0x1.23271cp126F}.
     * <pre>
     * <font style='background-color: #FD580B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD580B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD580B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD580B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD580B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD580B'>&nbsp;@&nbsp;</font><font style='background-color: #FD580B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD580B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD580B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_RED_BROWN = -0x1.23271cp126F;
    static { NAMED.put("more red brown", -0x1.23271cp126F); LIST.add(-0x1.23271cp126F); }

    /**
     * This color constant "some red brown" has RGBA8888 code {@code FC6A1AFF}, L 0.58431375, A 0.5647059, B 0.5686275, alpha 1.0, hue 0.12967606, and saturation 0.96585405.
     * It can be represented as a packed float with the constant {@code -0x1.23212ap126F}.
     * <pre>
     * <font style='background-color: #FC6A1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC6A1A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC6A1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC6A1A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC6A1A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC6A1A'>&nbsp;@&nbsp;</font><font style='background-color: #FC6A1A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC6A1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC6A1A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_RED_BROWN = -0x1.23212ap126F;
    static { NAMED.put("some red brown", -0x1.23212ap126F); LIST.add(-0x1.23212ap126F); }

    /**
     * This color constant "some orange brown" has RGBA8888 code {@code F8721EFF}, L 0.5921569, A 0.5568628, B 0.5686275, alpha 1.0, hue 0.1398671, and saturation 0.91263044.
     * It can be represented as a packed float with the constant {@code -0x1.231d2ep126F}.
     * <pre>
     * <font style='background-color: #F8721E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8721E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8721E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8721E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8721E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8721E'>&nbsp;@&nbsp;</font><font style='background-color: #F8721E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8721E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8721E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_ORANGE_BROWN = -0x1.231d2ep126F;
    static { NAMED.put("some orange brown", -0x1.231d2ep126F); LIST.add(-0x1.231d2ep126F); }

    /**
     * This color constant "more orange brown" has RGBA8888 code {@code FD740EFF}, L 0.6039216, A 0.5568628, B 0.57254905, alpha 1.0, hue 0.14418595, and saturation 0.983229.
     * It can be represented as a packed float with the constant {@code -0x1.251d34p126F}.
     * <pre>
     * <font style='background-color: #FD740E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD740E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD740E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD740E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD740E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD740E'>&nbsp;@&nbsp;</font><font style='background-color: #FD740E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD740E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD740E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_ORANGE_BROWN = -0x1.251d34p126F;
    static { NAMED.put("more orange brown", -0x1.251d34p126F); LIST.add(-0x1.251d34p126F); }

    /**
     * This color constant "more brown orange" has RGBA8888 code {@code F9770FFF}, L 0.6039216, A 0.5529412, B 0.57254905, alpha 1.0, hue 0.1496556, and saturation 0.9579907.
     * It can be represented as a packed float with the constant {@code -0x1.251b34p126F}.
     * <pre>
     * <font style='background-color: #F9770F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9770F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9770F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F9770F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F9770F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F9770F'>&nbsp;@&nbsp;</font><font style='background-color: #F9770F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9770F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9770F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BROWN_ORANGE = -0x1.251b34p126F;
    static { NAMED.put("more brown orange", -0x1.251b34p126F); LIST.add(-0x1.251b34p126F); }

    /**
     * This color constant "some brown orange" has RGBA8888 code {@code FA7E17FF}, L 0.6156863, A 0.54509807, B 0.57254905, alpha 1.0, hue 0.1614735, and saturation 0.9508019.
     * It can be represented as a packed float with the constant {@code -0x1.25173ap126F}.
     * <pre>
     * <font style='background-color: #FA7E17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA7E17; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA7E17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA7E17'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA7E17'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA7E17'>&nbsp;@&nbsp;</font><font style='background-color: #FA7E17; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA7E17;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA7E17; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BROWN_ORANGE = -0x1.25173ap126F;
    static { NAMED.put("some brown orange", -0x1.25173ap126F); LIST.add(-0x1.25173ap126F); }

    /**
     * This color constant "some saffron orange" has RGBA8888 code {@code FA831BFF}, L 0.627451, A 0.54509807, B 0.57254905, alpha 1.0, hue 0.1614735, and saturation 0.9305721.
     * It can be represented as a packed float with the constant {@code -0x1.25174p126F}.
     * <pre>
     * <font style='background-color: #FA831B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA831B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA831B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA831B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA831B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA831B'>&nbsp;@&nbsp;</font><font style='background-color: #FA831B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA831B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA831B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_SAFFRON_ORANGE = -0x1.25174p126F;
    static { NAMED.put("some saffron orange", -0x1.25174p126F); LIST.add(-0x1.25174p126F); }

    /**
     * This color constant "more saffron orange" has RGBA8888 code {@code FD8F14FF}, L 0.654902, A 0.5372549, B 0.5764706, alpha 1.0, hue 0.17785192, and saturation 0.9678256.
     * It can be represented as a packed float with the constant {@code -0x1.27134ep126F}.
     * <pre>
     * <font style='background-color: #FD8F14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD8F14; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD8F14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD8F14'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD8F14'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD8F14'>&nbsp;@&nbsp;</font><font style='background-color: #FD8F14; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD8F14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD8F14; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_SAFFRON_ORANGE = -0x1.27134ep126F;
    static { NAMED.put("more saffron orange", -0x1.27134ep126F); LIST.add(-0x1.27134ep126F); }

    /**
     * This color constant "more orange saffron" has RGBA8888 code {@code FB9A1EFF}, L 0.6784314, A 0.5254902, B 0.5764706, alpha 1.0, hue 0.19880433, and saturation 0.91712946.
     * It can be represented as a packed float with the constant {@code -0x1.270d5ap126F}.
     * <pre>
     * <font style='background-color: #FB9A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FB9A1E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FB9A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FB9A1E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FB9A1E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FB9A1E'>&nbsp;@&nbsp;</font><font style='background-color: #FB9A1E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FB9A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FB9A1E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_ORANGE_SAFFRON = -0x1.270d5ap126F;
    static { NAMED.put("more orange saffron", -0x1.270d5ap126F); LIST.add(-0x1.270d5ap126F); }

    /**
     * This color constant "some orange saffron" has RGBA8888 code {@code FDA514FF}, L 0.7019608, A 0.52156866, B 0.5803922, alpha 1.0, hue 0.20828822, and saturation 0.9685555.
     * It can be represented as a packed float with the constant {@code -0x1.290b66p126F}.
     * <pre>
     * <font style='background-color: #FDA514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDA514; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDA514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDA514'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDA514'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDA514'>&nbsp;@&nbsp;</font><font style='background-color: #FDA514; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDA514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDA514; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_ORANGE_SAFFRON = -0x1.290b66p126F;
    static { NAMED.put("some orange saffron", -0x1.290b66p126F); LIST.add(-0x1.290b66p126F); }

    /**
     * This color constant "some yellow saffron" has RGBA8888 code {@code F7B321FF}, L 0.7294118, A 0.5058824, B 0.5803922, alpha 1.0, hue 0.2383776, and saturation 0.93797326.
     * It can be represented as a packed float with the constant {@code -0x1.290374p126F}.
     * <pre>
     * <font style='background-color: #F7B321;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7B321; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7B321;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7B321'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7B321'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7B321'>&nbsp;@&nbsp;</font><font style='background-color: #F7B321; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7B321;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7B321; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_YELLOW_SAFFRON = -0x1.290374p126F;
    static { NAMED.put("some yellow saffron", -0x1.290374p126F); LIST.add(-0x1.290374p126F); }

    /**
     * This color constant "more yellow saffron" has RGBA8888 code {@code F8C41FFF}, L 0.77254903, A 0.49803922, B 0.58431375, alpha 1.0, hue 0.25368965, and saturation 0.9387022.
     * It can be represented as a packed float with the constant {@code -0x1.2aff8ap126F}.
     * <pre>
     * <font style='background-color: #F8C41F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C41F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C41F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8C41F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8C41F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8C41F'>&nbsp;@&nbsp;</font><font style='background-color: #F8C41F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C41F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C41F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_YELLOW_SAFFRON = -0x1.2aff8ap126F;
    static { NAMED.put("more yellow saffron", -0x1.2aff8ap126F); LIST.add(-0x1.2aff8ap126F); }

    /**
     * This color constant "more saffron yellow" has RGBA8888 code {@code FDDC27FF}, L 0.84313726, A 0.48235294, B 0.5882353, alpha 1.0, hue 0.28142345, and saturation 0.9402273.
     * It can be represented as a packed float with the constant {@code -0x1.2cf7aep126F}.
     * <pre>
     * <font style='background-color: #FDDC27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDDC27; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDDC27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDDC27'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDDC27'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDDC27'>&nbsp;@&nbsp;</font><font style='background-color: #FDDC27; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDDC27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDDC27; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_SAFFRON_YELLOW = -0x1.2cf7aep126F;
    static { NAMED.put("more saffron yellow", -0x1.2cf7aep126F); LIST.add(-0x1.2cf7aep126F); }

    /**
     * This color constant "some saffron yellow" has RGBA8888 code {@code FBF42CFF}, L 0.9098039, A 0.46666667, B 0.5921569, alpha 1.0, hue 0.30522364, and saturation 0.92918515.
     * It can be represented as a packed float with the constant {@code -0x1.2eefdp126F}.
     * <pre>
     * <font style='background-color: #FBF42C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBF42C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBF42C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FBF42C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FBF42C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FBF42C'>&nbsp;@&nbsp;</font><font style='background-color: #FBF42C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBF42C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBF42C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_SAFFRON_YELLOW = -0x1.2eefdp126F;
    static { NAMED.put("some saffron yellow", -0x1.2eefdp126F); LIST.add(-0x1.2eefdp126F); }

    /**
     * This color constant "some lime yellow" has RGBA8888 code {@code EDFF21FF}, L 0.9254902, A 0.45490196, B 0.5921569, alpha 1.0, hue 0.3224288, and saturation 0.93805546.
     * It can be represented as a packed float with the constant {@code -0x1.2ee9d8p126F}.
     * <pre>
     * <font style='background-color: #EDFF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDFF21; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDFF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDFF21'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDFF21'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDFF21'>&nbsp;@&nbsp;</font><font style='background-color: #EDFF21; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDFF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDFF21; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_LIME_YELLOW = -0x1.2ee9d8p126F;
    static { NAMED.put("some lime yellow", -0x1.2ee9d8p126F); LIST.add(-0x1.2ee9d8p126F); }

    /**
     * This color constant "more lime yellow" has RGBA8888 code {@code DAFF2FFF}, L 0.90588236, A 0.44705883, B 0.5882353, alpha 1.0, hue 0.33601886, and saturation 0.9083512.
     * It can be represented as a packed float with the constant {@code -0x1.2ce5cep126F}.
     * <pre>
     * <font style='background-color: #DAFF2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAFF2F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAFF2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DAFF2F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DAFF2F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DAFF2F'>&nbsp;@&nbsp;</font><font style='background-color: #DAFF2F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DAFF2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DAFF2F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_LIME_YELLOW = -0x1.2ce5cep126F;
    static { NAMED.put("more lime yellow", -0x1.2ce5cep126F); LIST.add(-0x1.2ce5cep126F); }

    /**
     * This color constant "more yellow lime" has RGBA8888 code {@code C9FF29FF}, L 0.8901961, A 0.4392157, B 0.5921569, alpha 1.0, hue 0.34281132, and saturation 0.95802623.
     * It can be represented as a packed float with the constant {@code -0x1.2ee1c6p126F}.
     * <pre>
     * <font style='background-color: #C9FF29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9FF29; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9FF29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9FF29'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9FF29'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9FF29'>&nbsp;@&nbsp;</font><font style='background-color: #C9FF29; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9FF29;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9FF29; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_YELLOW_LIME = -0x1.2ee1c6p126F;
    static { NAMED.put("more yellow lime", -0x1.2ee1c6p126F); LIST.add(-0x1.2ee1c6p126F); }

    /**
     * This color constant "some yellow lime" has RGBA8888 code {@code AFFF21FF}, L 0.8666667, A 0.42745098, B 0.5882353, alpha 1.0, hue 0.35953215, and saturation 0.9433308.
     * It can be represented as a packed float with the constant {@code -0x1.2cdbbap126F}.
     * <pre>
     * <font style='background-color: #AFFF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFFF21; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFFF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFFF21'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFFF21'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFFF21'>&nbsp;@&nbsp;</font><font style='background-color: #AFFF21; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFFF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFFF21; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_YELLOW_LIME = -0x1.2cdbbap126F;
    static { NAMED.put("some yellow lime", -0x1.2cdbbap126F); LIST.add(-0x1.2cdbbap126F); }

    /**
     * This color constant "some green lime" has RGBA8888 code {@code 94FF19FF}, L 0.84313726, A 0.41568628, B 0.5882353, alpha 1.0, hue 0.3713863, and saturation 0.93262017.
     * It can be represented as a packed float with the constant {@code -0x1.2cd5aep126F}.
     * <pre>
     * <font style='background-color: #94FF19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #94FF19; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #94FF19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #94FF19'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #94FF19'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #94FF19'>&nbsp;@&nbsp;</font><font style='background-color: #94FF19; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #94FF19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #94FF19; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_GREEN_LIME = -0x1.2cd5aep126F;
    static { NAMED.put("some green lime", -0x1.2cd5aep126F); LIST.add(-0x1.2cd5aep126F); }

    /**
     * This color constant "more green lime" has RGBA8888 code {@code 7AFF27FF}, L 0.827451, A 0.40392157, B 0.5882353, alpha 1.0, hue 0.38176328, and saturation 0.96795774.
     * It can be represented as a packed float with the constant {@code -0x1.2ccfa6p126F}.
     * <pre>
     * <font style='background-color: #7AFF27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AFF27; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AFF27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7AFF27'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7AFF27'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7AFF27'>&nbsp;@&nbsp;</font><font style='background-color: #7AFF27; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7AFF27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7AFF27; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_GREEN_LIME = -0x1.2ccfa6p126F;
    static { NAMED.put("more green lime", -0x1.2ccfa6p126F); LIST.add(-0x1.2ccfa6p126F); }

    /**
     * This color constant "more lime green" has RGBA8888 code {@code 49FF21FF}, L 0.8039216, A 0.39215687, B 0.58431375, alpha 1.0, hue 0.39437985, and saturation 0.9471327.
     * It can be represented as a packed float with the constant {@code -0x1.2ac99ap126F}.
     * <pre>
     * <font style='background-color: #49FF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49FF21; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49FF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #49FF21'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #49FF21'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #49FF21'>&nbsp;@&nbsp;</font><font style='background-color: #49FF21; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49FF21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49FF21; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_LIME_GREEN = -0x1.2ac99ap126F;
    static { NAMED.put("more lime green", -0x1.2ac99ap126F); LIST.add(-0x1.2ac99ap126F); }

    /**
     * This color constant "some lime green" has RGBA8888 code {@code 00FF3CFF}, L 0.7921569, A 0.38431373, B 0.5803922, alpha 1.0, hue 0.40333164, and saturation 0.96171755.
     * It can be represented as a packed float with the constant {@code -0x1.28c594p126F}.
     * <pre>
     * <font style='background-color: #00FF3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF3C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF3C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF3C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF3C'>&nbsp;@&nbsp;</font><font style='background-color: #00FF3C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF3C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_LIME_GREEN = -0x1.28c594p126F;
    static { NAMED.put("some lime green", -0x1.28c594p126F); LIST.add(-0x1.28c594p126F); }

    /**
     * This color constant "some cyan green" has RGBA8888 code {@code 00EE6EFF}, L 0.74509805, A 0.4, B 0.5529412, alpha 1.0, hue 0.4225058, and saturation 0.94971234.
     * It can be represented as a packed float with the constant {@code -0x1.1acd7cp126F}.
     * <pre>
     * <font style='background-color: #00EE6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00EE6E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00EE6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00EE6E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00EE6E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00EE6E'>&nbsp;@&nbsp;</font><font style='background-color: #00EE6E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00EE6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00EE6E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_CYAN_GREEN = -0x1.1acd7cp126F;
    static { NAMED.put("some cyan green", -0x1.1acd7cp126F); LIST.add(-0x1.1acd7cp126F); }

    /**
     * This color constant "more cyan green" has RGBA8888 code {@code 00DF9BFF}, L 0.70980394, A 0.41568628, B 0.52156866, alpha 1.0, hue 0.46014452, and saturation 0.94805837.
     * It can be represented as a packed float with the constant {@code -0x1.0ad56ap126F}.
     * <pre>
     * <font style='background-color: #00DF9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DF9B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DF9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00DF9B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00DF9B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00DF9B'>&nbsp;@&nbsp;</font><font style='background-color: #00DF9B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DF9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DF9B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_CYAN_GREEN = -0x1.0ad56ap126F;
    static { NAMED.put("more cyan green", -0x1.0ad56ap126F); LIST.add(-0x1.0ad56ap126F); }

    /**
     * This color constant "more green cyan" has RGBA8888 code {@code 00DFBBFF}, L 0.72156864, A 0.42352942, B 0.5019608, alpha 1.0, hue 0.49593177, and saturation 0.93251926.
     * It can be represented as a packed float with the constant {@code -0x1.00d97p126F}.
     * <pre>
     * <font style='background-color: #00DFBB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DFBB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DFBB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00DFBB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00DFBB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00DFBB'>&nbsp;@&nbsp;</font><font style='background-color: #00DFBB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DFBB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DFBB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_GREEN_CYAN = -0x1.00d97p126F;
    static { NAMED.put("more green cyan", -0x1.00d97p126F); LIST.add(-0x1.00d97p126F); }

    /**
     * This color constant "some green cyan" has RGBA8888 code {@code 00EAE0FF}, L 0.76862746, A 0.42352942, B 0.4862745, alpha 1.0, hue 0.5282756, and saturation 0.97021.
     * It can be represented as a packed float with the constant {@code -0x1.f8d988p125F}.
     * <pre>
     * <font style='background-color: #00EAE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00EAE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00EAE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00EAE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00EAE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00EAE0'>&nbsp;@&nbsp;</font><font style='background-color: #00EAE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00EAE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00EAE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_GREEN_CYAN = -0x1.f8d988p125F;
    static { NAMED.put("some green cyan", -0x1.f8d988p125F); LIST.add(-0x1.f8d988p125F); }

    /**
     * This color constant "some blue cyan" has RGBA8888 code {@code 00EBFFFF}, L 0.78431374, A 0.43137255, B 0.46666667, alpha 1.0, hue 0.5719594, and saturation 0.95274985.
     * It can be represented as a packed float with the constant {@code -0x1.eedd9p125F}.
     * <pre>
     * <font style='background-color: #00EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00EBFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #00EBFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00EBFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BLUE_CYAN = -0x1.eedd9p125F;
    static { NAMED.put("some blue cyan", -0x1.eedd9p125F); LIST.add(-0x1.eedd9p125F); }

    /**
     * This color constant "more blue cyan" has RGBA8888 code {@code 00CEFFFF}, L 0.69803923, A 0.44313726, B 0.4509804, alpha 1.0, hue 0.6132407, and saturation 0.96096325.
     * It can be represented as a packed float with the constant {@code -0x1.e6e364p125F}.
     * <pre>
     * <font style='background-color: #00CEFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00CEFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00CEFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00CEFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00CEFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00CEFF'>&nbsp;@&nbsp;</font><font style='background-color: #00CEFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00CEFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00CEFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_BLUE_CYAN = -0x1.e6e364p125F;
    static { NAMED.put("more blue cyan", -0x1.e6e364p125F); LIST.add(-0x1.e6e364p125F); }

    /**
     * This color constant "more cyan blue" has RGBA8888 code {@code 00ADFFFF}, L 0.60784316, A 0.45490196, B 0.43137255, alpha 1.0, hue 0.6574588, and saturation 0.9555687.
     * It can be represented as a packed float with the constant {@code -0x1.dce936p125F}.
     * <pre>
     * <font style='background-color: #00ADFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00ADFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00ADFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00ADFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00ADFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00ADFF'>&nbsp;@&nbsp;</font><font style='background-color: #00ADFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00ADFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00ADFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_CYAN_BLUE = -0x1.dce936p125F;
    static { NAMED.put("more cyan blue", -0x1.dce936p125F); LIST.add(-0x1.dce936p125F); }

    /**
     * This color constant "some cyan blue" has RGBA8888 code {@code 007EFFFF}, L 0.49411765, A 0.47058824, B 0.4, alpha 1.0, hue 0.704483, and saturation 0.9703383.
     * It can be represented as a packed float with the constant {@code -0x1.ccf0fcp125F}.
     * <pre>
     * <font style='background-color: #007EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007EFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007EFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007EFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007EFF'>&nbsp;@&nbsp;</font><font style='background-color: #007EFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007EFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007EFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_CYAN_BLUE = -0x1.ccf0fcp125F;
    static { NAMED.put("some cyan blue", -0x1.ccf0fcp125F); LIST.add(-0x1.ccf0fcp125F); }

    /**
     * This color constant "some violet blue" has RGBA8888 code {@code 2200FFFF}, L 0.3254902, A 0.49019608, B 0.34509805, alpha 1.0, hue 0.7399465, and saturation 0.969128.
     * It can be represented as a packed float with the constant {@code -0x1.b0faa6p125F}.
     * <pre>
     * <font style='background-color: #2200FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2200FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2200FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2200FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2200FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2200FF'>&nbsp;@&nbsp;</font><font style='background-color: #2200FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2200FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2200FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_VIOLET_BLUE = -0x1.b0faa6p125F;
    static { NAMED.put("some violet blue", -0x1.b0faa6p125F); LIST.add(-0x1.b0faa6p125F); }

    /**
     * This color constant "more violet blue" has RGBA8888 code {@code 4300FFFF}, L 0.34509805, A 0.5137255, B 0.34901962, alpha 1.0, hue 0.764433, and saturation 0.98254097.
     * It can be represented as a packed float with the constant {@code -0x1.b306bp125F}.
     * <pre>
     * <font style='background-color: #4300FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4300FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4300FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4300FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4300FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4300FF'>&nbsp;@&nbsp;</font><font style='background-color: #4300FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4300FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4300FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_VIOLET_BLUE = -0x1.b306bp125F;
    static { NAMED.put("more violet blue", -0x1.b306bp125F); LIST.add(-0x1.b306bp125F); }

    /**
     * This color constant "more blue violet" has RGBA8888 code {@code 5F00FFFF}, L 0.36862746, A 0.5372549, B 0.35686275, alpha 1.0, hue 0.7905202, and saturation 0.98347926.
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
     * This color constant "some blue violet" has RGBA8888 code {@code 7900FFFF}, L 0.39607844, A 0.56078434, B 0.3647059, alpha 1.0, hue 0.8171962, and saturation 0.9862409.
     * It can be represented as a packed float with the constant {@code -0x1.bb1ecap125F}.
     * <pre>
     * <font style='background-color: #7900FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7900FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7900FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7900FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7900FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7900FF'>&nbsp;@&nbsp;</font><font style='background-color: #7900FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7900FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7900FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_BLUE_VIOLET = -0x1.bb1ecap125F;
    static { NAMED.put("some blue violet", -0x1.bb1ecap125F); LIST.add(-0x1.bb1ecap125F); }

    /**
     * This color constant "some purple violet" has RGBA8888 code {@code 8F05FFFF}, L 0.41960785, A 0.5764706, B 0.37254903, alpha 1.0, hue 0.83601886, and saturation 0.9756364.
     * It can be represented as a packed float with the constant {@code -0x1.bf26d6p125F}.
     * <pre>
     * <font style='background-color: #8F05FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F05FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F05FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F05FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F05FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F05FF'>&nbsp;@&nbsp;</font><font style='background-color: #8F05FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F05FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F05FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_PURPLE_VIOLET = -0x1.bf26d6p125F;
    static { NAMED.put("some purple violet", -0x1.bf26d6p125F); LIST.add(-0x1.bf26d6p125F); }

    /**
     * This color constant "more purple violet" has RGBA8888 code {@code 9B13FEFF}, L 0.43529412, A 0.58431375, B 0.3764706, alpha 1.0, hue 0.84533215, and saturation 0.9817299.
     * It can be represented as a packed float with the constant {@code -0x1.c12adep125F}.
     * <pre>
     * <font style='background-color: #9B13FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B13FE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B13FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B13FE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B13FE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B13FE'>&nbsp;@&nbsp;</font><font style='background-color: #9B13FE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B13FE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B13FE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_PURPLE_VIOLET = -0x1.c12adep125F;
    static { NAMED.put("more purple violet", -0x1.c12adep125F); LIST.add(-0x1.c12adep125F); }

    /**
     * This color constant "more violet purple" has RGBA8888 code {@code A600FFFF}, L 0.44705883, A 0.5921569, B 0.38039216, alpha 1.0, hue 0.8544955, and saturation 0.99113435.
     * It can be represented as a packed float with the constant {@code -0x1.c32ee4p125F}.
     * <pre>
     * <font style='background-color: #A600FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A600FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A600FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A600FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A600FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A600FF'>&nbsp;@&nbsp;</font><font style='background-color: #A600FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A600FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A600FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_VIOLET_PURPLE = -0x1.c32ee4p125F;
    static { NAMED.put("more violet purple", -0x1.c32ee4p125F); LIST.add(-0x1.c32ee4p125F); }

    /**
     * This color constant "some violet purple" has RGBA8888 code {@code B510FFFF}, L 0.47058824, A 0.6, B 0.38431373, alpha 1.0, hue 0.8634538, and saturation 1.0037566.
     * It can be represented as a packed float with the constant {@code -0x1.c532fp125F}.
     * <pre>
     * <font style='background-color: #B510FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B510FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B510FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B510FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B510FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B510FF'>&nbsp;@&nbsp;</font><font style='background-color: #B510FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B510FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B510FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_VIOLET_PURPLE = -0x1.c532fp125F;
    static { NAMED.put("some violet purple", -0x1.c532fp125F); LIST.add(-0x1.c532fp125F); }

    /**
     * This color constant "some magenta purple" has RGBA8888 code {@code BD15FFFF}, L 0.48235294, A 0.6039216, B 0.3882353, alpha 1.0, hue 0.86921954, and saturation 1.0017742.
     * It can be represented as a packed float with the constant {@code -0x1.c734f6p125F}.
     * <pre>
     * <font style='background-color: #BD15FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD15FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD15FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD15FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD15FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD15FF'>&nbsp;@&nbsp;</font><font style='background-color: #BD15FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD15FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD15FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_MAGENTA_PURPLE = -0x1.c734f6p125F;
    static { NAMED.put("some magenta purple", -0x1.c734f6p125F); LIST.add(-0x1.c734f6p125F); }

    /**
     * This color constant "more magenta purple" has RGBA8888 code {@code CA11FFFF}, L 0.49803922, A 0.6117647, B 0.39215687, alpha 1.0, hue 0.8778395, and saturation 1.019477.
     * It can be represented as a packed float with the constant {@code -0x1.c938fep125F}.
     * <pre>
     * <font style='background-color: #CA11FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA11FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA11FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA11FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA11FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA11FF'>&nbsp;@&nbsp;</font><font style='background-color: #CA11FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA11FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA11FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_MAGENTA_PURPLE = -0x1.c938fep125F;
    static { NAMED.put("more magenta purple", -0x1.c938fep125F); LIST.add(-0x1.c938fep125F); }

    /**
     * This color constant "more purple magenta" has RGBA8888 code {@code E10AFFFF}, L 0.5294118, A 0.62352943, B 0.40392157, alpha 1.0, hue 0.8947796, and saturation 0.96536463.
     * It can be represented as a packed float with the constant {@code -0x1.cf3f0ep125F}.
     * <pre>
     * <font style='background-color: #E10AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E10AFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E10AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E10AFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E10AFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E10AFF'>&nbsp;@&nbsp;</font><font style='background-color: #E10AFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E10AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E10AFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_PURPLE_MAGENTA = -0x1.cf3f0ep125F;
    static { NAMED.put("more purple magenta", -0x1.cf3f0ep125F); LIST.add(-0x1.cf3f0ep125F); }

    /**
     * This color constant "some purple magenta" has RGBA8888 code {@code ED16FCFF}, L 0.54901963, A 0.627451, B 0.40784314, alpha 1.0, hue 0.90034866, and saturation 0.9820339.
     * It can be represented as a packed float with the constant {@code -0x1.d14118p125F}.
     * <pre>
     * <font style='background-color: #ED16FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED16FC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED16FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ED16FC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ED16FC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ED16FC'>&nbsp;@&nbsp;</font><font style='background-color: #ED16FC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ED16FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ED16FC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_PURPLE_MAGENTA = -0x1.d14118p125F;
    static { NAMED.put("some purple magenta", -0x1.d14118p125F); LIST.add(-0x1.d14118p125F); }

    /**
     * This color constant "some red magenta" has RGBA8888 code {@code FD15DFFF}, L 0.5568628, A 0.63529414, B 0.43137255, alpha 1.0, hue 0.9252889, and saturation 0.95892173.
     * It can be represented as a packed float with the constant {@code -0x1.dd451cp125F}.
     * <pre>
     * <font style='background-color: #FD15DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD15DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD15DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD15DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD15DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD15DF'>&nbsp;@&nbsp;</font><font style='background-color: #FD15DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD15DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD15DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_RED_MAGENTA = -0x1.dd451cp125F;
    static { NAMED.put("some red magenta", -0x1.dd451cp125F); LIST.add(-0x1.dd451cp125F); }

    /**
     * This color constant "more red magenta" has RGBA8888 code {@code FD1BA4FF}, L 0.5294118, A 0.6313726, B 0.4745098, alpha 1.0, hue 0.9694902, and saturation 0.96503085.
     * It can be represented as a packed float with the constant {@code -0x1.f3430ep125F}.
     * <pre>
     * <font style='background-color: #FD1BA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD1BA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD1BA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD1BA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD1BA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD1BA4'>&nbsp;@&nbsp;</font><font style='background-color: #FD1BA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD1BA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD1BA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_RED_MAGENTA = -0x1.f3430ep125F;
    static { NAMED.put("more red magenta", -0x1.f3430ep125F); LIST.add(-0x1.f3430ep125F); }

    /**
     * This color constant "more magenta red" has RGBA8888 code {@code FF1271FF}, L 0.50980395, A 0.627451, B 0.50980395, alpha 1.0, hue 0.01221767, and saturation 0.976831.
     * It can be represented as a packed float with the constant {@code -0x1.054104p126F}.
     * <pre>
     * <font style='background-color: #FF1271;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF1271; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF1271;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF1271'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF1271'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF1271'>&nbsp;@&nbsp;</font><font style='background-color: #FF1271; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF1271;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF1271; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MORE_MAGENTA_RED = -0x1.054104p126F;
    static { NAMED.put("more magenta red", -0x1.054104p126F); LIST.add(-0x1.054104p126F); }

    /**
     * This color constant "some magenta red" has RGBA8888 code {@code FE173BFF}, L 0.49803922, A 0.6156863, B 0.54509807, alpha 1.0, hue 0.059147265, and saturation 0.9780445.
     * It can be represented as a packed float with the constant {@code -0x1.173afep126F}.
     * <pre>
     * <font style='background-color: #FE173B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE173B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE173B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FE173B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FE173B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FE173B'>&nbsp;@&nbsp;</font><font style='background-color: #FE173B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE173B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE173B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOME_MAGENTA_RED = -0x1.173afep126F;
    static { NAMED.put("some magenta red", -0x1.173afep126F); LIST.add(-0x1.173afep126F); }

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
