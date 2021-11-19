package com.github.tommyettinger.colorful.oklab;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;

import java.util.Comparator;

/**
 * A palette of predefined colors as packed Oklab floats, the kind {@link ColorTools} works with.
 * You can access colors by their constant name, such as {@code DEEP_YELLOWISH_PINK}, by the {@link #NAMED} map using
 * {@code NAMED.get("Deep Yellowish Pink", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access
 * a float color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default
 * because it will not occur in a valid Oklab color. You can access the names in a specific order with {@link #NAMES}
 * (which is alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow
 * to blue to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * This uses data provided by Paul Centore for the ISCC-NBS color names and their similar sRGB colors. These colors are
 * related closely to the Munsell color system, but I'm not sure exactly how ISCC-NBS ties in. The 260 colors (plus
 * transparent) are a good size to incorporate here, though.
 * <a href="https://www.munsellcolourscienceforpainters.com/MunsellAndKubelkaMunkToolbox/MunsellAndKubelkaMunkToolbox.html">That data, and other Munsell-related color code, is available here.</a>
 */
public class NamedMunsellPalette {
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(261);
    public static final FloatArray LIST = new FloatArray(261);

    /**
     * This color constant "Munsell Transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922, alpha 0.0, hue 0.625, saturation 0.0, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code 0x0.fefep-126F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MUNSELL_TRANSPARENT = 0x0.fefep-126F;
    static { NAMED.put("Munsell Transparent", 0x0.fefep-126F); LIST.add(0x0.fefep-126F); }

    /**
     * This color constant "Black" has RGBA8888 code {@code 2B292BFF}, L 0.19607843, A 0.5019608, B 0.49803922, alpha 1.0, hue 0.875, saturation 0.0012193263, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff0064p125F}.
     * <pre>
     * <font style='background-color: #2B292B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B292B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B292B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2B292B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2B292B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2B292B'>&nbsp;@&nbsp;</font><font style='background-color: #2B292B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B292B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B292B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK = -0x1.ff0064p125F;
    static { NAMED.put("Black", -0x1.ff0064p125F); LIST.add(-0x1.ff0064p125F); }

    /**
     * This color constant "Dark Gray" has RGBA8888 code {@code 585458FF}, L 0.34509805, A 0.5019608, B 0.49411765, alpha 1.0, hue 0.8011957, saturation 0.001902497, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fd00bp125F}.
     * <pre>
     * <font style='background-color: #585458;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #585458; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #585458;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #585458'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #585458'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #585458'>&nbsp;@&nbsp;</font><font style='background-color: #585458; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #585458;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #585458; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY = -0x1.fd00bp125F;
    static { NAMED.put("Dark Gray", -0x1.fd00bp125F); LIST.add(-0x1.fd00bp125F); }

    /**
     * This color constant "Medium Gray" has RGBA8888 code {@code 8A8489FF}, L 0.5137255, A 0.5019608, B 0.49411765, alpha 1.0, hue 0.8011957, saturation 0.0031325868, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fd0106p125F}.
     * <pre>
     * <font style='background-color: #8A8489;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A8489; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A8489;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A8489'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A8489'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A8489'>&nbsp;@&nbsp;</font><font style='background-color: #8A8489; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A8489;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A8489; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUM_GRAY = -0x1.fd0106p125F;
    static { NAMED.put("Medium Gray", -0x1.fd0106p125F); LIST.add(-0x1.fd0106p125F); }

    /**
     * This color constant "Light Gray" has RGBA8888 code {@code BDB7BFFF}, L 0.7058824, A 0.5019608, B 0.49411765, alpha 1.0, hue 0.8011957, saturation 0.010749798, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fd0168p125F}.
     * <pre>
     * <font style='background-color: #BDB7BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDB7BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDB7BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BDB7BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BDB7BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BDB7BF'>&nbsp;@&nbsp;</font><font style='background-color: #BDB7BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BDB7BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BDB7BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY = -0x1.fd0168p125F;
    static { NAMED.put("Light Gray", -0x1.fd0168p125F); LIST.add(-0x1.fd0168p125F); }

    /**
     * This color constant "White" has RGBA8888 code {@code E7E1E9FF}, L 0.8745098, A 0.5019608, B 0.49411765, alpha 1.0, hue 0.8011957, saturation 0.054869685, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fd01bep125F}.
     * <pre>
     * <font style='background-color: #E7E1E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7E1E9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7E1E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7E1E9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7E1E9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7E1E9'>&nbsp;@&nbsp;</font><font style='background-color: #E7E1E9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7E1E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7E1E9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE = -0x1.fd01bep125F;
    static { NAMED.put("White", -0x1.fd01bep125F); LIST.add(-0x1.fd01bep125F); }

    /**
     * This color constant "Vivid Pink" has RGBA8888 code {@code FD7992FF}, L 0.6313726, A 0.57254905, B 0.50980395, alpha 1.0, hue 0.021390583, saturation 0.70395154, and chroma 0.14584495.
     * It can be represented as a packed float with the constant {@code -0x1.052542p126F}.
     * <pre>
     * <font style='background-color: #FD7992;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD7992; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD7992;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD7992'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD7992'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD7992'>&nbsp;@&nbsp;</font><font style='background-color: #FD7992; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD7992;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD7992; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PINK = -0x1.052542p126F;
    static { NAMED.put("Vivid Pink", -0x1.052542p126F); LIST.add(-0x1.052542p126F); }

    /**
     * This color constant "Strong Pink" has RGBA8888 code {@code F48FA0FF}, L 0.6666667, A 0.5529412, B 0.5058824, alpha 1.0, hue 0.017621128, saturation 0.49789172, and chroma 0.10611779.
     * It can be represented as a packed float with the constant {@code -0x1.031b54p126F}.
     * <pre>
     * <font style='background-color: #F48FA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F48FA0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F48FA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F48FA0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F48FA0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F48FA0'>&nbsp;@&nbsp;</font><font style='background-color: #F48FA0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F48FA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F48FA0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PINK = -0x1.031b54p126F;
    static { NAMED.put("Strong Pink", -0x1.031b54p126F); LIST.add(-0x1.031b54p126F); }

    /**
     * This color constant "Deep Pink" has RGBA8888 code {@code E66980FF}, L 0.5686275, A 0.57254905, B 0.50980395, alpha 1.0, hue 0.021390583, saturation 0.45256066, and chroma 0.14584495.
     * It can be represented as a packed float with the constant {@code -0x1.052522p126F}.
     * <pre>
     * <font style='background-color: #E66980;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E66980; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E66980;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E66980'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E66980'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E66980'>&nbsp;@&nbsp;</font><font style='background-color: #E66980; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E66980;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E66980; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PINK = -0x1.052522p126F;
    static { NAMED.put("Deep Pink", -0x1.052522p126F); LIST.add(-0x1.052522p126F); }

    /**
     * This color constant "Light Pink" has RGBA8888 code {@code F8C3CEFF}, L 0.80784315, A 0.5254902, B 0.49803922, alpha 1.0, hue 0.98778236, saturation 0.33580247, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.ff0d9cp125F}.
     * <pre>
     * <font style='background-color: #F8C3CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C3CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C3CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8C3CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8C3CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8C3CE'>&nbsp;@&nbsp;</font><font style='background-color: #F8C3CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C3CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C3CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PINK = -0x1.ff0d9cp125F;
    static { NAMED.put("Light Pink", -0x1.ff0d9cp125F); LIST.add(-0x1.ff0d9cp125F); }

    /**
     * This color constant "Moderate Pink" has RGBA8888 code {@code E2A3AEFF}, L 0.69411767, A 0.53333336, B 0.5019608, alpha 1.0, hue 0.009343538, saturation 0.23011307, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.011162p126F}.
     * <pre>
     * <font style='background-color: #E2A3AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2A3AE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2A3AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2A3AE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2A3AE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2A3AE'>&nbsp;@&nbsp;</font><font style='background-color: #E2A3AE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2A3AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2A3AE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PINK = -0x1.011162p126F;
    static { NAMED.put("Moderate Pink", -0x1.011162p126F); LIST.add(-0x1.011162p126F); }

    /**
     * This color constant "Dark Pink" has RGBA8888 code {@code C5808AFF}, L 0.5686275, A 0.5372549, B 0.5058824, alpha 1.0, hue 0.02493652, saturation 0.12012012, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.031322p126F}.
     * <pre>
     * <font style='background-color: #C5808A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5808A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5808A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C5808A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C5808A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C5808A'>&nbsp;@&nbsp;</font><font style='background-color: #C5808A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5808A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5808A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PINK = -0x1.031322p126F;
    static { NAMED.put("Dark Pink", -0x1.031322p126F); LIST.add(-0x1.031322p126F); }

    /**
     * This color constant "Pale Pink" has RGBA8888 code {@code EFD1DCFF}, L 0.8392157, A 0.5137255, B 0.49803922, alpha 1.0, hue 0.97740346, saturation 0.13149244, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.ff07acp125F}.
     * <pre>
     * <font style='background-color: #EFD1DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFD1DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFD1DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFD1DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFD1DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFD1DC'>&nbsp;@&nbsp;</font><font style='background-color: #EFD1DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFD1DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFD1DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PINK = -0x1.ff07acp125F;
    static { NAMED.put("Pale Pink", -0x1.ff07acp125F); LIST.add(-0x1.ff07acp125F); }

    /**
     * This color constant "Grayish Pink" has RGBA8888 code {@code CBADB7FF}, L 0.69411767, A 0.5137255, B 0.49803922, alpha 1.0, hue 0.97740346, saturation 0.0337325, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.ff0762p125F}.
     * <pre>
     * <font style='background-color: #CBADB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBADB7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBADB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBADB7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBADB7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBADB7'>&nbsp;@&nbsp;</font><font style='background-color: #CBADB7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBADB7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBADB7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PINK = -0x1.ff0762p125F;
    static { NAMED.put("Grayish Pink", -0x1.ff0762p125F); LIST.add(-0x1.ff0762p125F); }

    /**
     * This color constant "Pinkish White" has RGBA8888 code {@code EFDDE5FF}, L 0.8745098, A 0.50980395, B 0.49411765, alpha 1.0, hue 0.91398114, saturation 0.07355327, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fd05bep125F}.
     * <pre>
     * <font style='background-color: #EFDDE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFDDE5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFDDE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFDDE5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFDDE5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFDDE5'>&nbsp;@&nbsp;</font><font style='background-color: #EFDDE5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFDDE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFDDE5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINKISH_WHITE = -0x1.fd05bep125F;
    static { NAMED.put("Pinkish White", -0x1.fd05bep125F); LIST.add(-0x1.fd05bep125F); }

    /**
     * This color constant "Pinkish Gray" has RGBA8888 code {@code C7B6BDFF}, L 0.7137255, A 0.5058824, B 0.49803922, alpha 1.0, hue 0.9488043, saturation 0.0060966318, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.ff036cp125F}.
     * <pre>
     * <font style='background-color: #C7B6BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7B6BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7B6BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7B6BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7B6BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7B6BD'>&nbsp;@&nbsp;</font><font style='background-color: #C7B6BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7B6BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7B6BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINKISH_GRAY = -0x1.ff036cp125F;
    static { NAMED.put("Pinkish Gray", -0x1.ff036cp125F); LIST.add(-0x1.ff036cp125F); }

    /**
     * This color constant "Vivid Red" has RGBA8888 code {@code D51C3CFF}, L 0.43137255, A 0.6, B 0.53333336, alpha 1.0, hue 0.05119568, saturation 0.7640954, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.1132dcp126F}.
     * <pre>
     * <font style='background-color: #D51C3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D51C3C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D51C3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D51C3C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D51C3C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D51C3C'>&nbsp;@&nbsp;</font><font style='background-color: #D51C3C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D51C3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D51C3C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_RED = -0x1.1132dcp126F;
    static { NAMED.put("Vivid Red", -0x1.1132dcp126F); LIST.add(-0x1.1132dcp126F); }

    /**
     * This color constant "Strong Red" has RGBA8888 code {@code BF344BFF}, L 0.41960785, A 0.58431375, B 0.52156866, alpha 1.0, hue 0.03985548, saturation 0.5564579, and chroma 0.17337766.
     * It can be represented as a packed float with the constant {@code -0x1.0b2ad6p126F}.
     * <pre>
     * <font style='background-color: #BF344B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF344B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF344B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF344B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF344B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF344B'>&nbsp;@&nbsp;</font><font style='background-color: #BF344B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF344B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF344B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_RED = -0x1.0b2ad6p126F;
    static { NAMED.put("Strong Red", -0x1.0b2ad6p126F); LIST.add(-0x1.0b2ad6p126F); }

    /**
     * This color constant "Deep Red" has RGBA8888 code {@code 87122DFF}, L 0.29411766, A 0.5764706, B 0.5176471, alpha 1.0, hue 0.03609712, saturation 0.77381957, and chroma 0.15634763.
     * It can be represented as a packed float with the constant {@code -0x1.092696p126F}.
     * <pre>
     * <font style='background-color: #87122D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87122D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87122D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87122D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87122D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87122D'>&nbsp;@&nbsp;</font><font style='background-color: #87122D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87122D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87122D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED = -0x1.092696p126F;
    static { NAMED.put("Deep Red", -0x1.092696p126F); LIST.add(-0x1.092696p126F); }

    /**
     * This color constant "Very Deep Red" has RGBA8888 code {@code 5C0625FF}, L 0.21568628, A 0.56078434, B 0.5058824, alpha 1.0, hue 0.015360129, saturation 0.7280916, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.031e6ep126F}.
     * <pre>
     * <font style='background-color: #5C0625;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C0625; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C0625;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C0625'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C0625'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C0625'>&nbsp;@&nbsp;</font><font style='background-color: #5C0625; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C0625;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C0625; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_RED = -0x1.031e6ep126F;
    static { NAMED.put("Very Deep Red", -0x1.031e6ep126F); LIST.add(-0x1.031e6ep126F); }

    /**
     * This color constant "Moderate Red" has RGBA8888 code {@code B14955FF}, L 0.42745098, A 0.5647059, B 0.5137255, alpha 1.0, hue 0.033271696, saturation 0.31090772, and chroma 0.13177444.
     * It can be represented as a packed float with the constant {@code -0x1.0720dap126F}.
     * <pre>
     * <font style='background-color: #B14955;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B14955; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B14955;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B14955'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B14955'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B14955'>&nbsp;@&nbsp;</font><font style='background-color: #B14955; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B14955;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B14955; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_RED = -0x1.0720dap126F;
    static { NAMED.put("Moderate Red", -0x1.0720dap126F); LIST.add(-0x1.0720dap126F); }

    /**
     * This color constant "Dark Red" has RGBA8888 code {@code 742434FF}, L 0.28235295, A 0.5568628, B 0.50980395, alpha 1.0, hue 0.027184589, saturation 0.43731853, and chroma 0.114952646.
     * It can be represented as a packed float with the constant {@code -0x1.051c9p126F}.
     * <pre>
     * <font style='background-color: #742434;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #742434; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #742434;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #742434'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #742434'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #742434'>&nbsp;@&nbsp;</font><font style='background-color: #742434; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #742434;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #742434; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_RED = -0x1.051c9p126F;
    static { NAMED.put("Dark Red", -0x1.051c9p126F); LIST.add(-0x1.051c9p126F); }

    /**
     * This color constant "Very Dark Red" has RGBA8888 code {@code 481127FF}, L 0.1882353, A 0.54901963, B 0.49803922, alpha 1.0, hue 0.99364984, saturation 0.59266275, and chroma 0.09773435.
     * It can be represented as a packed float with the constant {@code -0x1.ff186p125F}.
     * <pre>
     * <font style='background-color: #481127;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #481127; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #481127;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #481127'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #481127'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #481127'>&nbsp;@&nbsp;</font><font style='background-color: #481127; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #481127;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #481127; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_RED = -0x1.ff186p125F;
    static { NAMED.put("Very Dark Red", -0x1.ff186p125F); LIST.add(-0x1.ff186p125F); }

    /**
     * This color constant "Light Grayish Red" has RGBA8888 code {@code B4888DFF}, L 0.5686275, A 0.52156866, B 0.5019608, alpha 1.0, hue 0.014432981, saturation 0.03821756, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.010b22p126F}.
     * <pre>
     * <font style='background-color: #B4888D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4888D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4888D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4888D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4888D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4888D'>&nbsp;@&nbsp;</font><font style='background-color: #B4888D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4888D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4888D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_RED = -0x1.010b22p126F;
    static { NAMED.put("Light Grayish Red", -0x1.010b22p126F); LIST.add(-0x1.010b22p126F); }

    /**
     * This color constant "Grayish Red" has RGBA8888 code {@code 985D62FF}, L 0.43529412, A 0.53333336, B 0.5058824, alpha 1.0, hue 0.02781067, saturation 0.07878908, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.0310dep126F}.
     * <pre>
     * <font style='background-color: #985D62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #985D62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #985D62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #985D62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #985D62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #985D62'>&nbsp;@&nbsp;</font><font style='background-color: #985D62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #985D62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #985D62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_RED = -0x1.0310dep126F;
    static { NAMED.put("Grayish Red", -0x1.0310dep126F); LIST.add(-0x1.0310dep126F); }

    /**
     * This color constant "Dark Grayish Red" has RGBA8888 code {@code 53383EFF}, L 0.27450982, A 0.5176471, B 0.49803922, alpha 1.0, hue 0.9823789, saturation 0.04140891, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.ff088cp125F}.
     * <pre>
     * <font style='background-color: #53383E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53383E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53383E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #53383E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #53383E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #53383E'>&nbsp;@&nbsp;</font><font style='background-color: #53383E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53383E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53383E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_RED = -0x1.ff088cp125F;
    static { NAMED.put("Dark Grayish Red", -0x1.ff088cp125F); LIST.add(-0x1.ff088cp125F); }

    /**
     * This color constant "Blackish Red" has RGBA8888 code {@code 332127FF}, L 0.18431373, A 0.5137255, B 0.49803922, alpha 1.0, hue 0.97740346, saturation 0.04733728, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.ff065ep125F}.
     * <pre>
     * <font style='background-color: #332127;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #332127; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #332127;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #332127'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #332127'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #332127'>&nbsp;@&nbsp;</font><font style='background-color: #332127; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #332127;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #332127; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_RED = -0x1.ff065ep125F;
    static { NAMED.put("Blackish Red", -0x1.ff065ep125F); LIST.add(-0x1.ff065ep125F); }

    /**
     * This color constant "Reddish Gray" has RGBA8888 code {@code 928186FF}, L 0.5137255, A 0.50980395, B 0.49803922, alpha 1.0, hue 0.96857655, saturation 0.004946492, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.ff0506p125F}.
     * <pre>
     * <font style='background-color: #928186;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #928186; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #928186;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #928186'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #928186'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #928186'>&nbsp;@&nbsp;</font><font style='background-color: #928186; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #928186;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #928186; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REDDISH_GRAY = -0x1.ff0506p125F;
    static { NAMED.put("Reddish Gray", -0x1.ff0506p125F); LIST.add(-0x1.ff0506p125F); }

    /**
     * This color constant "Dark Reddish Gray" has RGBA8888 code {@code 5D4E53FF}, L 0.3372549, A 0.50980395, B 0.49803922, alpha 1.0, hue 0.96857655, saturation 0.009083763, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.ff04acp125F}.
     * <pre>
     * <font style='background-color: #5D4E53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D4E53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D4E53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5D4E53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5D4E53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5D4E53'>&nbsp;@&nbsp;</font><font style='background-color: #5D4E53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D4E53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D4E53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_GRAY = -0x1.ff04acp125F;
    static { NAMED.put("Dark Reddish Gray", -0x1.ff04acp125F); LIST.add(-0x1.ff04acp125F); }

    /**
     * This color constant "Reddish Black" has RGBA8888 code {@code 30262BFF}, L 0.19215687, A 0.50980395, B 0.49411765, alpha 1.0, hue 0.91398114, saturation 0.024177779, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fd0462p125F}.
     * <pre>
     * <font style='background-color: #30262B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30262B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30262B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #30262B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #30262B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #30262B'>&nbsp;@&nbsp;</font><font style='background-color: #30262B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #30262B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #30262B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REDDISH_BLACK = -0x1.fd0462p125F;
    static { NAMED.put("Reddish Black", -0x1.fd0462p125F); LIST.add(-0x1.fd0462p125F); }

    /**
     * This color constant "Vivid Yellowish Pink" has RGBA8888 code {@code FD7E5DFF}, L 0.627451, A 0.56078434, B 0.54509807, alpha 1.0, hue 0.10160455, saturation 0.75243026, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.171f4p126F}.
     * <pre>
     * <font style='background-color: #FD7E5D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD7E5D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD7E5D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD7E5D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD7E5D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD7E5D'>&nbsp;@&nbsp;</font><font style='background-color: #FD7E5D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD7E5D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD7E5D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOWISH_PINK = -0x1.171f4p126F;
    static { NAMED.put("Vivid Yellowish Pink", -0x1.171f4p126F); LIST.add(-0x1.171f4p126F); }

    /**
     * This color constant "Strong Yellowish Pink" has RGBA8888 code {@code F59080FF}, L 0.6627451, A 0.54901963, B 0.5254902, alpha 1.0, hue 0.0763186, saturation 0.5646222, and chroma 0.11007033.
     * It can be represented as a packed float with the constant {@code -0x1.0d1952p126F}.
     * <pre>
     * <font style='background-color: #F59080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F59080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F59080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F59080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F59080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F59080'>&nbsp;@&nbsp;</font><font style='background-color: #F59080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F59080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F59080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_PINK = -0x1.0d1952p126F;
    static { NAMED.put("Strong Yellowish Pink", -0x1.0d1952p126F); LIST.add(-0x1.0d1952p126F); }

    /**
     * This color constant "Deep Yellowish Pink" has RGBA8888 code {@code EF6366FF}, L 0.56078434, A 0.5764706, B 0.5254902, alpha 1.0, hue 0.05119568, saturation 0.54865676, and chroma 0.1605844.
     * It can be represented as a packed float with the constant {@code -0x1.0d271ep126F}.
     * <pre>
     * <font style='background-color: #EF6366;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF6366; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF6366;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EF6366'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EF6366'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EF6366'>&nbsp;@&nbsp;</font><font style='background-color: #EF6366; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EF6366;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EF6366; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_PINK = -0x1.0d271ep126F;
    static { NAMED.put("Deep Yellowish Pink", -0x1.0d271ep126F); LIST.add(-0x1.0d271ep126F); }

    /**
     * This color constant "Light Yellowish Pink" has RGBA8888 code {@code F8C4B6FF}, L 0.8, A 0.52156866, B 0.5137255, alpha 1.0, hue 0.090208516, saturation 0.36776635, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.070b98p126F}.
     * <pre>
     * <font style='background-color: #F8C4B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C4B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C4B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8C4B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8C4B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8C4B6'>&nbsp;@&nbsp;</font><font style='background-color: #F8C4B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C4B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C4B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_PINK = -0x1.070b98p126F;
    static { NAMED.put("Light Yellowish Pink", -0x1.070b98p126F); LIST.add(-0x1.070b98p126F); }

    /**
     * This color constant "Moderate Yellowish Pink" has RGBA8888 code {@code E2A698FF}, L 0.6901961, A 0.5254902, B 0.5176471, alpha 1.0, hue 0.09638812, saturation 0.21003991, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.090d6p126F}.
     * <pre>
     * <font style='background-color: #E2A698;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2A698; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2A698;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E2A698'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E2A698'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E2A698'>&nbsp;@&nbsp;</font><font style='background-color: #E2A698; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E2A698;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E2A698; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_PINK = -0x1.090d6p126F;
    static { NAMED.put("Moderate Yellowish Pink", -0x1.090d6p126F); LIST.add(-0x1.090d6p126F); }

    /**
     * This color constant "Dark Yellowish Pink" has RGBA8888 code {@code C9807EFF}, L 0.57254905, A 0.5372549, B 0.5137255, alpha 1.0, hue 0.056167345, saturation 0.14875284, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.071324p126F}.
     * <pre>
     * <font style='background-color: #C9807E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9807E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9807E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9807E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9807E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9807E'>&nbsp;@&nbsp;</font><font style='background-color: #C9807E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9807E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9807E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_PINK = -0x1.071324p126F;
    static { NAMED.put("Dark Yellowish Pink", -0x1.071324p126F); LIST.add(-0x1.071324p126F); }

    /**
     * This color constant "Pale Yellowish Pink" has RGBA8888 code {@code F1D3D1FF}, L 0.8392157, A 0.5137255, B 0.5019608, alpha 1.0, hue 0.022596559, saturation 0.1632653, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.0107acp126F}.
     * <pre>
     * <font style='background-color: #F1D3D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1D3D1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1D3D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F1D3D1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F1D3D1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F1D3D1'>&nbsp;@&nbsp;</font><font style='background-color: #F1D3D1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1D3D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1D3D1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOWISH_PINK = -0x1.0107acp126F;
    static { NAMED.put("Pale Yellowish Pink", -0x1.0107acp126F); LIST.add(-0x1.0107acp126F); }

    /**
     * This color constant "Grayish Yellowish Pink" has RGBA8888 code {@code CBACACFF}, L 0.6862745, A 0.5137255, B 0.5019608, alpha 1.0, hue 0.022596559, saturation 0.03967467, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.01075ep126F}.
     * <pre>
     * <font style='background-color: #CBACAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBACAC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBACAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBACAC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBACAC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBACAC'>&nbsp;@&nbsp;</font><font style='background-color: #CBACAC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBACAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBACAC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOWISH_PINK = -0x1.01075ep126F;
    static { NAMED.put("Grayish Yellowish Pink", -0x1.01075ep126F); LIST.add(-0x1.01075ep126F); }

    /**
     * This color constant "Brownish Pink" has RGBA8888 code {@code CBAFA7FF}, L 0.6901961, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.08601887, saturation 0.028565427, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.03056p126F}.
     * <pre>
     * <font style='background-color: #CBAFA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBAFA7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBAFA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBAFA7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBAFA7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBAFA7'>&nbsp;@&nbsp;</font><font style='background-color: #CBAFA7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBAFA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBAFA7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_PINK = -0x1.03056p126F;
    static { NAMED.put("Brownish Pink", -0x1.03056p126F); LIST.add(-0x1.03056p126F); }

    /**
     * This color constant "Vivid Reddish Orange" has RGBA8888 code {@code E83B1BFF}, L 0.4862745, A 0.5882353, B 0.5568628, alpha 1.0, hue 0.091120966, saturation 0.75775003, and chroma 0.20912123.
     * It can be represented as a packed float with the constant {@code -0x1.1d2cf8p126F}.
     * <pre>
     * <font style='background-color: #E83B1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E83B1B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E83B1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E83B1B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E83B1B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E83B1B'>&nbsp;@&nbsp;</font><font style='background-color: #E83B1B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E83B1B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E83B1B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_REDDISH_ORANGE = -0x1.1d2cf8p126F;
    static { NAMED.put("Vivid Reddish Orange", -0x1.1d2cf8p126F); LIST.add(-0x1.1d2cf8p126F); }

    /**
     * This color constant "Strong Reddish Orange" has RGBA8888 code {@code DB5D3BFF}, L 0.5137255, A 0.56078434, B 0.54509807, alpha 1.0, hue 0.10160455, saturation 0.45066163, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.171f06p126F}.
     * <pre>
     * <font style='background-color: #DB5D3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB5D3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB5D3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB5D3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB5D3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB5D3B'>&nbsp;@&nbsp;</font><font style='background-color: #DB5D3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB5D3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB5D3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_ORANGE = -0x1.171f06p126F;
    static { NAMED.put("Strong Reddish Orange", -0x1.171f06p126F); LIST.add(-0x1.171f06p126F); }

    /**
     * This color constant "Deep Reddish Orange" has RGBA8888 code {@code AF3318FF}, L 0.38431373, A 0.5686275, B 0.54509807, alpha 1.0, hue 0.09254123, saturation 0.6613253, and chroma 0.16359681.
     * It can be represented as a packed float with the constant {@code -0x1.1722c4p126F}.
     * <pre>
     * <font style='background-color: #AF3318;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF3318; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF3318;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF3318'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF3318'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF3318'>&nbsp;@&nbsp;</font><font style='background-color: #AF3318; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF3318;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF3318; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_ORANGE = -0x1.1722c4p126F;
    static { NAMED.put("Deep Reddish Orange", -0x1.1722c4p126F); LIST.add(-0x1.1722c4p126F); }

    /**
     * This color constant "Moderate Reddish Orange" has RGBA8888 code {@code CD6952FF}, L 0.52156866, A 0.54901963, B 0.53333336, alpha 1.0, hue 0.095056064, saturation 0.233984, and chroma 0.11809544.
     * It can be represented as a packed float with the constant {@code -0x1.11190ap126F}.
     * <pre>
     * <font style='background-color: #CD6952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD6952; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD6952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD6952'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD6952'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD6952'>&nbsp;@&nbsp;</font><font style='background-color: #CD6952; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD6952;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD6952; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_ORANGE = -0x1.11190ap126F;
    static { NAMED.put("Moderate Reddish Orange", -0x1.11190ap126F); LIST.add(-0x1.11190ap126F); }

    /**
     * This color constant "Dark Reddish Orange" has RGBA8888 code {@code A2402BFF}, L 0.38431373, A 0.5529412, B 0.5372549, alpha 1.0, hue 0.09760782, saturation 0.44485256, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.131ac4p126F}.
     * <pre>
     * <font style='background-color: #A2402B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2402B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2402B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2402B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2402B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2402B'>&nbsp;@&nbsp;</font><font style='background-color: #A2402B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2402B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2402B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_ORANGE = -0x1.131ac4p126F;
    static { NAMED.put("Dark Reddish Orange", -0x1.131ac4p126F); LIST.add(-0x1.131ac4p126F); }

    /**
     * This color constant "Grayish Reddish Orange" has RGBA8888 code {@code B97565FF}, L 0.52156866, A 0.53333336, B 0.52156866, alpha 1.0, hue 0.091414735, saturation 0.101680204, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.0b110ap126F}.
     * <pre>
     * <font style='background-color: #B97565;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B97565; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B97565;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B97565'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B97565'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B97565'>&nbsp;@&nbsp;</font><font style='background-color: #B97565; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B97565;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B97565; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_ORANGE = -0x1.0b110ap126F;
    static { NAMED.put("Grayish Reddish Orange", -0x1.0b110ap126F); LIST.add(-0x1.0b110ap126F); }

    /**
     * This color constant "Strong Reddish Brown" has RGBA8888 code {@code 8B1C0EFF}, L 0.3019608, A 0.5647059, B 0.5411765, alpha 1.0, hue 0.090208516, saturation 0.84705883, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.15209ap126F}.
     * <pre>
     * <font style='background-color: #8B1C0E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B1C0E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B1C0E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B1C0E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B1C0E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B1C0E'>&nbsp;@&nbsp;</font><font style='background-color: #8B1C0E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B1C0E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B1C0E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_BROWN = -0x1.15209ap126F;
    static { NAMED.put("Strong Reddish Brown", -0x1.15209ap126F); LIST.add(-0x1.15209ap126F); }

    /**
     * This color constant "Deep Reddish Brown" has RGBA8888 code {@code 610F12FF}, L 0.22352941, A 0.5568628, B 0.5254902, alpha 1.0, hue 0.06706365, saturation 0.7182222, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.0d1c72p126F}.
     * <pre>
     * <font style='background-color: #610F12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #610F12; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #610F12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #610F12'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #610F12'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #610F12'>&nbsp;@&nbsp;</font><font style='background-color: #610F12; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #610F12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #610F12; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_BROWN = -0x1.0d1c72p126F;
    static { NAMED.put("Deep Reddish Brown", -0x1.0d1c72p126F); LIST.add(-0x1.0d1c72p126F); }

    /**
     * This color constant "Light Reddish Brown" has RGBA8888 code {@code AC7A73FF}, L 0.52156866, A 0.5254902, B 0.5137255, alpha 1.0, hue 0.078615956, saturation 0.05406411, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.070d0ap126F}.
     * <pre>
     * <font style='background-color: #AC7A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC7A73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC7A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC7A73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC7A73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC7A73'>&nbsp;@&nbsp;</font><font style='background-color: #AC7A73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC7A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC7A73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_REDDISH_BROWN = -0x1.070d0ap126F;
    static { NAMED.put("Light Reddish Brown", -0x1.070d0ap126F); LIST.add(-0x1.070d0ap126F); }

    /**
     * This color constant "Moderate Reddish Brown" has RGBA8888 code {@code 7D423BFF}, L 0.34117648, A 0.5372549, B 0.5176471, alpha 1.0, hue 0.070401505, saturation 0.16036281, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.0912aep126F}.
     * <pre>
     * <font style='background-color: #7D423B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D423B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D423B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7D423B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7D423B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7D423B'>&nbsp;@&nbsp;</font><font style='background-color: #7D423B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D423B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D423B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_BROWN = -0x1.0912aep126F;
    static { NAMED.put("Moderate Reddish Brown", -0x1.0912aep126F); LIST.add(-0x1.0912aep126F); }

    /**
     * This color constant "Dark Reddish Brown" has RGBA8888 code {@code 461D1EFF}, L 0.2, A 0.53333336, B 0.50980395, alpha 1.0, hue 0.045517046, saturation 0.26381013, and chroma 0.069218926.
     * It can be represented as a packed float with the constant {@code -0x1.051066p126F}.
     * <pre>
     * <font style='background-color: #461D1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #461D1E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #461D1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #461D1E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #461D1E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #461D1E'>&nbsp;@&nbsp;</font><font style='background-color: #461D1E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #461D1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #461D1E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_BROWN = -0x1.051066p126F;
    static { NAMED.put("Dark Reddish Brown", -0x1.051066p126F); LIST.add(-0x1.051066p126F); }

    /**
     * This color constant "Light Grayish Reddish Brown" has RGBA8888 code {@code 9E7F7AFF}, L 0.5176471, A 0.5137255, B 0.5058824, alpha 1.0, hue 0.06443131, saturation 0.0143840285, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.030708p126F}.
     * <pre>
     * <font style='background-color: #9E7F7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E7F7A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E7F7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E7F7A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E7F7A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E7F7A'>&nbsp;@&nbsp;</font><font style='background-color: #9E7F7A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E7F7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E7F7A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_REDDISH_BROWN = -0x1.030708p126F;
    static { NAMED.put("Light Grayish Reddish Brown", -0x1.030708p126F); LIST.add(-0x1.030708p126F); }

    /**
     * This color constant "Grayish Reddish Brown" has RGBA8888 code {@code 6C4D4BFF}, L 0.34509805, A 0.5176471, B 0.5058824, alpha 1.0, hue 0.05119568, saturation 0.03265306, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.0308bp126F}.
     * <pre>
     * <font style='background-color: #6C4D4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C4D4B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C4D4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C4D4B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C4D4B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C4D4B'>&nbsp;@&nbsp;</font><font style='background-color: #6C4D4B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C4D4B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C4D4B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_BROWN = -0x1.0308bp126F;
    static { NAMED.put("Grayish Reddish Brown", -0x1.0308bp126F); LIST.add(-0x1.0308bp126F); }

    /**
     * This color constant "Dark Grayish Reddish Brown" has RGBA8888 code {@code 43292AFF}, L 0.21960784, A 0.5176471, B 0.5058824, alpha 1.0, hue 0.05119568, saturation 0.06755489, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.03087p126F}.
     * <pre>
     * <font style='background-color: #43292A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43292A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43292A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #43292A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #43292A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #43292A'>&nbsp;@&nbsp;</font><font style='background-color: #43292A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #43292A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #43292A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_REDDISH_BROWN = -0x1.03087p126F;
    static { NAMED.put("Dark Grayish Reddish Brown", -0x1.03087p126F); LIST.add(-0x1.03087p126F); }

    /**
     * This color constant "Vivid Orange" has RGBA8888 code {@code F7760BFF}, L 0.59607846, A 0.54901963, B 0.57254905, alpha 1.0, hue 0.15541562, saturation 0.847699, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.25193p126F}.
     * <pre>
     * <font style='background-color: #F7760B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7760B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7760B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7760B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7760B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7760B'>&nbsp;@&nbsp;</font><font style='background-color: #F7760B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7760B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7760B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_ORANGE = -0x1.25193p126F;
    static { NAMED.put("Vivid Orange", -0x1.25193p126F); LIST.add(-0x1.25193p126F); }

    /**
     * This color constant "Strong Orange" has RGBA8888 code {@code EA8127FF}, L 0.6, A 0.5372549, B 0.5647059, alpha 1.0, hue 0.16685049, saturation 0.67059773, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.211332p126F}.
     * <pre>
     * <font style='background-color: #EA8127;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA8127; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA8127;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EA8127'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EA8127'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EA8127'>&nbsp;@&nbsp;</font><font style='background-color: #EA8127; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA8127;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA8127; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_ORANGE = -0x1.211332p126F;
    static { NAMED.put("Strong Orange", -0x1.211332p126F); LIST.add(-0x1.211332p126F); }

    /**
     * This color constant "Deep Orange" has RGBA8888 code {@code C26012FF}, L 0.48235294, A 0.5411765, B 0.56078434, alpha 1.0, hue 0.15522522, saturation 0.8140514, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.1f14f6p126F}.
     * <pre>
     * <font style='background-color: #C26012;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C26012; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C26012;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C26012'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C26012'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C26012'>&nbsp;@&nbsp;</font><font style='background-color: #C26012; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C26012;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C26012; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE = -0x1.1f14f6p126F;
    static { NAMED.put("Deep Orange", -0x1.1f14f6p126F); LIST.add(-0x1.1f14f6p126F); }

    /**
     * This color constant "Light Orange" has RGBA8888 code {@code FBAF82FF}, L 0.7372549, A 0.5294118, B 0.5372549, alpha 1.0, hue 0.14362669, saturation 0.59057695, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130f78p126F}.
     * <pre>
     * <font style='background-color: #FBAF82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBAF82; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBAF82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FBAF82'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FBAF82'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FBAF82'>&nbsp;@&nbsp;</font><font style='background-color: #FBAF82; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FBAF82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FBAF82; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_ORANGE = -0x1.130f78p126F;
    static { NAMED.put("Light Orange", -0x1.130f78p126F); LIST.add(-0x1.130f78p126F); }

    /**
     * This color constant "Moderate Orange" has RGBA8888 code {@code DE8D5CFF}, L 0.6156863, A 0.53333336, B 0.5411765, alpha 1.0, hue 0.14168067, saturation 0.28624645, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.15113ap126F}.
     * <pre>
     * <font style='background-color: #DE8D5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE8D5C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE8D5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DE8D5C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DE8D5C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DE8D5C'>&nbsp;@&nbsp;</font><font style='background-color: #DE8D5C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE8D5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE8D5C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_ORANGE = -0x1.15113ap126F;
    static { NAMED.put("Moderate Orange", -0x1.15113ap126F); LIST.add(-0x1.15113ap126F); }

    /**
     * This color constant "Brownish Orange" has RGBA8888 code {@code B26633FF}, L 0.4745098, A 0.53333336, B 0.54509807, alpha 1.0, hue 0.14868373, saturation 0.4749601, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.1710f2p126F}.
     * <pre>
     * <font style='background-color: #B26633;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B26633; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B26633;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B26633'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B26633'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B26633'>&nbsp;@&nbsp;</font><font style='background-color: #B26633; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B26633;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B26633; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_ORANGE = -0x1.1710f2p126F;
    static { NAMED.put("Brownish Orange", -0x1.1710f2p126F); LIST.add(-0x1.1710f2p126F); }

    /**
     * This color constant "Strong Brown" has RGBA8888 code {@code 8A4416FF}, L 0.35686275, A 0.53333336, B 0.54509807, alpha 1.0, hue 0.14868373, saturation 0.68725055, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.1710b6p126F}.
     * <pre>
     * <font style='background-color: #8A4416;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A4416; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A4416;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A4416'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A4416'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A4416'>&nbsp;@&nbsp;</font><font style='background-color: #8A4416; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A4416;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A4416; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BROWN = -0x1.1710b6p126F;
    static { NAMED.put("Strong Brown", -0x1.1710b6p126F); LIST.add(-0x1.1710b6p126F); }

    /**
     * This color constant "Deep Brown" has RGBA8888 code {@code 571A07FF}, L 0.21960784, A 0.5411765, B 0.5294118, alpha 1.0, hue 0.09872868, saturation 0.6712018, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.0f147p126F}.
     * <pre>
     * <font style='background-color: #571A07;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #571A07; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #571A07;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #571A07'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #571A07'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #571A07'>&nbsp;@&nbsp;</font><font style='background-color: #571A07; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #571A07;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #571A07; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN = -0x1.0f147p126F;
    static { NAMED.put("Deep Brown", -0x1.0f147p126F); LIST.add(-0x1.0f147p126F); }

    /**
     * This color constant "Light Brown" has RGBA8888 code {@code AD7C63FF}, L 0.52156866, A 0.52156866, B 0.5254902, alpha 1.0, hue 0.138223, saturation 0.12853186, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.0d0b0ap126F}.
     * <pre>
     * <font style='background-color: #AD7C63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD7C63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD7C63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD7C63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD7C63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD7C63'>&nbsp;@&nbsp;</font><font style='background-color: #AD7C63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD7C63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD7C63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BROWN = -0x1.0d0b0ap126F;
    static { NAMED.put("Light Brown", -0x1.0d0b0ap126F); LIST.add(-0x1.0d0b0ap126F); }

    /**
     * This color constant "Moderate Brown" has RGBA8888 code {@code 724A38FF}, L 0.34117648, A 0.52156866, B 0.52156866, alpha 1.0, hue 0.125, saturation 0.18164758, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0b0aaep126F}.
     * <pre>
     * <font style='background-color: #724A38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #724A38; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #724A38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #724A38'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #724A38'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #724A38'>&nbsp;@&nbsp;</font><font style='background-color: #724A38; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #724A38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #724A38; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BROWN = -0x1.0b0aaep126F;
    static { NAMED.put("Moderate Brown", -0x1.0b0aaep126F); LIST.add(-0x1.0b0aaep126F); }

    /**
     * This color constant "Dark Brown" has RGBA8888 code {@code 442112FF}, L 0.2, A 0.52156866, B 0.52156866, alpha 1.0, hue 0.125, saturation 0.40316534, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.0b0a66p126F}.
     * <pre>
     * <font style='background-color: #442112;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #442112; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #442112;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #442112'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #442112'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #442112'>&nbsp;@&nbsp;</font><font style='background-color: #442112; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #442112;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #442112; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BROWN = -0x1.0b0a66p126F;
    static { NAMED.put("Dark Brown", -0x1.0b0a66p126F); LIST.add(-0x1.0b0a66p126F); }

    /**
     * This color constant "Light Grayish Brown" has RGBA8888 code {@code 997F75FF}, L 0.5137255, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.02040608, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.050506p126F}.
     * <pre>
     * <font style='background-color: #997F75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #997F75; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #997F75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #997F75'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #997F75'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #997F75'>&nbsp;@&nbsp;</font><font style='background-color: #997F75; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #997F75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #997F75; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_BROWN = -0x1.050506p126F;
    static { NAMED.put("Light Grayish Brown", -0x1.050506p126F); LIST.add(-0x1.050506p126F); }

    /**
     * This color constant "Grayish Brown" has RGBA8888 code {@code 674F48FF}, L 0.34509805, A 0.5137255, B 0.50980395, alpha 1.0, hue 0.09872868, saturation 0.03736902, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.0506bp126F}.
     * <pre>
     * <font style='background-color: #674F48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #674F48; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #674F48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #674F48'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #674F48'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #674F48'>&nbsp;@&nbsp;</font><font style='background-color: #674F48; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #674F48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #674F48; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_BROWN = -0x1.0506bp126F;
    static { NAMED.put("Grayish Brown", -0x1.0506bp126F); LIST.add(-0x1.0506bp126F); }

    /**
     * This color constant "Dark Grayish Brown" has RGBA8888 code {@code 3E2C28FF}, L 0.21960784, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.08601887, saturation 0.028565427, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.03047p126F}.
     * <pre>
     * <font style='background-color: #3E2C28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E2C28; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E2C28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3E2C28'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3E2C28'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3E2C28'>&nbsp;@&nbsp;</font><font style='background-color: #3E2C28; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E2C28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E2C28; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_BROWN = -0x1.03047p126F;
    static { NAMED.put("Dark Grayish Brown", -0x1.03047p126F); LIST.add(-0x1.03047p126F); }

    /**
     * This color constant "Light Brownish Gray" has RGBA8888 code {@code 928281FF}, L 0.5137255, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.05119568, saturation 0.0024037017, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.010306p126F}.
     * <pre>
     * <font style='background-color: #928281;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #928281; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #928281;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #928281'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #928281'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #928281'>&nbsp;@&nbsp;</font><font style='background-color: #928281; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #928281;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #928281; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BROWNISH_GRAY = -0x1.010306p126F;
    static { NAMED.put("Light Brownish Gray", -0x1.010306p126F); LIST.add(-0x1.010306p126F); }

    /**
     * This color constant "Brownish Gray" has RGBA8888 code {@code 605251FF}, L 0.34509805, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.05119568, saturation 0.003628118, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.0102bp126F}.
     * <pre>
     * <font style='background-color: #605251;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #605251; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #605251;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #605251'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #605251'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #605251'>&nbsp;@&nbsp;</font><font style='background-color: #605251; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #605251;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #605251; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_GRAY = -0x1.0102bp126F;
    static { NAMED.put("Brownish Gray", -0x1.0102bp126F); LIST.add(-0x1.0102bp126F); }

    /**
     * This color constant "Brownish Black" has RGBA8888 code {@code 2B211EFF}, L 0.17254902, A 0.5058824, B 0.5058824, alpha 1.0, hue 0.125, saturation 0.038939968, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.030258p126F}.
     * <pre>
     * <font style='background-color: #2B211E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B211E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B211E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2B211E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2B211E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2B211E'>&nbsp;@&nbsp;</font><font style='background-color: #2B211E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B211E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B211E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_BLACK = -0x1.030258p126F;
    static { NAMED.put("Brownish Black", -0x1.030258p126F); LIST.add(-0x1.030258p126F); }

    /**
     * This color constant "Brilliant Orange Yellow" has RGBA8888 code {@code FFBE50FF}, L 0.76862746, A 0.50980395, B 0.5686275, alpha 1.0, hue 0.22740345, saturation 0.63123345, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.230588p126F}.
     * <pre>
     * <font style='background-color: #FFBE50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBE50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBE50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBE50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBE50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBE50'>&nbsp;@&nbsp;</font><font style='background-color: #FFBE50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBE50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBE50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_ORANGE_YELLOW = -0x1.230588p126F;
    static { NAMED.put("Brilliant Orange Yellow", -0x1.230588p126F); LIST.add(-0x1.230588p126F); }

    /**
     * This color constant "Strong Orange Yellow" has RGBA8888 code {@code F0A121FF}, L 0.6745098, A 0.5176471, B 0.5764706, alpha 1.0, hue 0.21390288, saturation 0.80898875, and chroma 0.15634763.
     * It can be represented as a packed float with the constant {@code -0x1.270958p126F}.
     * <pre>
     * <font style='background-color: #F0A121;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0A121; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0A121;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0A121'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0A121'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0A121'>&nbsp;@&nbsp;</font><font style='background-color: #F0A121; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0A121;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0A121; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_ORANGE_YELLOW = -0x1.270958p126F;
    static { NAMED.put("Strong Orange Yellow", -0x1.270958p126F); LIST.add(-0x1.270958p126F); }

    /**
     * This color constant "Deep Orange Yellow" has RGBA8888 code {@code D08511FF}, L 0.57254905, A 0.5176471, B 0.5686275, alpha 1.0, hue 0.20994629, saturation 0.79622006, and chroma 0.1411665.
     * It can be represented as a packed float with the constant {@code -0x1.230924p126F}.
     * <pre>
     * <font style='background-color: #D08511;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D08511; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D08511;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D08511'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D08511'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D08511'>&nbsp;@&nbsp;</font><font style='background-color: #D08511; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D08511;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D08511; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_YELLOW = -0x1.230924p126F;
    static { NAMED.put("Deep Orange Yellow", -0x1.230924p126F); LIST.add(-0x1.230924p126F); }

    /**
     * This color constant "Light Orange Yellow" has RGBA8888 code {@code FCC27CFF}, L 0.78431374, A 0.5137255, B 0.54901963, alpha 1.0, hue 0.20655698, saturation 0.5662676, and chroma 0.10141215.
     * It can be represented as a packed float with the constant {@code -0x1.19079p126F}.
     * <pre>
     * <font style='background-color: #FCC27C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FCC27C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FCC27C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FCC27C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FCC27C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FCC27C'>&nbsp;@&nbsp;</font><font style='background-color: #FCC27C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FCC27C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FCC27C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_ORANGE_YELLOW = -0x1.19079p126F;
    static { NAMED.put("Light Orange Yellow", -0x1.19079p126F); LIST.add(-0x1.19079p126F); }

    /**
     * This color constant "Moderate Orange Yellow" has RGBA8888 code {@code E7A75DFF}, L 0.6862745, A 0.5176471, B 0.5529412, alpha 1.0, hue 0.19880433, saturation 0.37460977, and chroma 0.11117382.
     * It can be represented as a packed float with the constant {@code -0x1.1b095ep126F}.
     * <pre>
     * <font style='background-color: #E7A75D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7A75D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7A75D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7A75D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7A75D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7A75D'>&nbsp;@&nbsp;</font><font style='background-color: #E7A75D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7A75D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7A75D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_ORANGE_YELLOW = -0x1.1b095ep126F;
    static { NAMED.put("Moderate Orange Yellow", -0x1.1b095ep126F); LIST.add(-0x1.1b095ep126F); }

    /**
     * This color constant "Dark Orange Yellow" has RGBA8888 code {@code C38639FF}, L 0.56078434, A 0.5176471, B 0.5529412, alpha 1.0, hue 0.19880433, saturation 0.49382716, and chroma 0.11117382.
     * It can be represented as a packed float with the constant {@code -0x1.1b091ep126F}.
     * <pre>
     * <font style='background-color: #C38639;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C38639; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C38639;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C38639'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C38639'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C38639'>&nbsp;@&nbsp;</font><font style='background-color: #C38639; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C38639;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C38639; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_ORANGE_YELLOW = -0x1.1b091ep126F;
    static { NAMED.put("Dark Orange Yellow", -0x1.1b091ep126F); LIST.add(-0x1.1b091ep126F); }

    /**
     * This color constant "Pale Orange Yellow" has RGBA8888 code {@code EEC6A6FF}, L 0.7882353, A 0.50980395, B 0.52156866, alpha 1.0, hue 0.18210676, saturation 0.16776788, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0b0592p126F}.
     * <pre>
     * <font style='background-color: #EEC6A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEC6A6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEC6A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EEC6A6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EEC6A6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EEC6A6'>&nbsp;@&nbsp;</font><font style='background-color: #EEC6A6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEC6A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEC6A6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_YELLOW = -0x1.0b0592p126F;
    static { NAMED.put("Pale Orange Yellow", -0x1.0b0592p126F); LIST.add(-0x1.0b0592p126F); }

    /**
     * This color constant "Strong Yellowish Brown" has RGBA8888 code {@code 9E671DFF}, L 0.4509804, A 0.5137255, B 0.5529412, alpha 1.0, hue 0.20963092, saturation 0.6536442, and chroma 0.10895567.
     * It can be represented as a packed float with the constant {@code -0x1.1b06e6p126F}.
     * <pre>
     * <font style='background-color: #9E671D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E671D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E671D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E671D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E671D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E671D'>&nbsp;@&nbsp;</font><font style='background-color: #9E671D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E671D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E671D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_BROWN = -0x1.1b06e6p126F;
    static { NAMED.put("Strong Yellowish Brown", -0x1.1b06e6p126F); LIST.add(-0x1.1b06e6p126F); }

    /**
     * This color constant "Deep Yellowish Brown" has RGBA8888 code {@code 673F0BFF}, L 0.3019608, A 0.5137255, B 0.5411765, alpha 1.0, hue 0.19880433, saturation 0.6977572, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.15069ap126F}.
     * <pre>
     * <font style='background-color: #673F0B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #673F0B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #673F0B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #673F0B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #673F0B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #673F0B'>&nbsp;@&nbsp;</font><font style='background-color: #673F0B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #673F0B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #673F0B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_BROWN = -0x1.15069ap126F;
    static { NAMED.put("Deep Yellowish Brown", -0x1.15069ap126F); LIST.add(-0x1.15069ap126F); }

    /**
     * This color constant "Light Yellowish Brown" has RGBA8888 code {@code C49A74FF}, L 0.61960787, A 0.5137255, B 0.5294118, alpha 1.0, hue 0.18051395, saturation 0.13235116, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.0f073cp126F}.
     * <pre>
     * <font style='background-color: #C49A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C49A74; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C49A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C49A74'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C49A74'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C49A74'>&nbsp;@&nbsp;</font><font style='background-color: #C49A74; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C49A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C49A74; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_BROWN = -0x1.0f073cp126F;
    static { NAMED.put("Light Yellowish Brown", -0x1.0f073cp126F); LIST.add(-0x1.0f073cp126F); }

    /**
     * This color constant "Moderate Yellowish Brown" has RGBA8888 code {@code 886648FF}, L 0.42745098, A 0.5137255, B 0.5254902, alpha 1.0, hue 0.17138404, saturation 0.16363296, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.0d06dap126F}.
     * <pre>
     * <font style='background-color: #886648;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #886648; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #886648;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #886648'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #886648'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #886648'>&nbsp;@&nbsp;</font><font style='background-color: #886648; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #886648;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #886648; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_BROWN = -0x1.0d06dap126F;
    static { NAMED.put("Moderate Yellowish Brown", -0x1.0d06dap126F); LIST.add(-0x1.0d06dap126F); }

    /**
     * This color constant "Dark Yellowish Brown" has RGBA8888 code {@code 50341AFF}, L 0.25490198, A 0.5137255, B 0.5254902, alpha 1.0, hue 0.17138404, saturation 0.363182, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.0d0682p126F}.
     * <pre>
     * <font style='background-color: #50341A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50341A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50341A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #50341A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #50341A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #50341A'>&nbsp;@&nbsp;</font><font style='background-color: #50341A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50341A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50341A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_BROWN = -0x1.0d0682p126F;
    static { NAMED.put("Dark Yellowish Brown", -0x1.0d0682p126F); LIST.add(-0x1.0d0682p126F); }

    /**
     * This color constant "Light Grayish Yellowish Brown" has RGBA8888 code {@code B49B8DFF}, L 0.6117647, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.02040608, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.050538p126F}.
     * <pre>
     * <font style='background-color: #B49B8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B49B8D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B49B8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B49B8D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B49B8D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B49B8D'>&nbsp;@&nbsp;</font><font style='background-color: #B49B8D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B49B8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B49B8D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_YELLOWISH_BROWN = -0x1.050538p126F;
    static { NAMED.put("Light Grayish Yellowish Brown", -0x1.050538p126F); LIST.add(-0x1.050538p126F); }

    /**
     * This color constant "Grayish Yellowish Brown" has RGBA8888 code {@code 7E695DFF}, L 0.42745098, A 0.50980395, B 0.50980395, alpha 1.0, hue 0.125, saturation 0.02642357, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.0504dap126F}.
     * <pre>
     * <font style='background-color: #7E695D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E695D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E695D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E695D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E695D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E695D'>&nbsp;@&nbsp;</font><font style='background-color: #7E695D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E695D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E695D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOWISH_BROWN = -0x1.0504dap126F;
    static { NAMED.put("Grayish Yellowish Brown", -0x1.0504dap126F); LIST.add(-0x1.0504dap126F); }

    /**
     * This color constant "Dark Grayish Yellowish Brown" has RGBA8888 code {@code 4D3D33FF}, L 0.27450982, A 0.5058824, B 0.50980395, alpha 1.0, hue 0.16398115, saturation 0.048415806, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.05028cp126F}.
     * <pre>
     * <font style='background-color: #4D3D33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4D3D33; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4D3D33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4D3D33'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4D3D33'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4D3D33'>&nbsp;@&nbsp;</font><font style='background-color: #4D3D33; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4D3D33;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4D3D33; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_YELLOWISH_BROWN = -0x1.05028cp126F;
    static { NAMED.put("Dark Grayish Yellowish Brown", -0x1.05028cp126F); LIST.add(-0x1.05028cp126F); }

    /**
     * This color constant "Vivid Yellow" has RGBA8888 code {@code F1BF15FF}, L 0.7490196, A 0.49411765, B 0.58431375, alpha 1.0, hue 0.26108217, saturation 0.85929006, and chroma 0.16837704.
     * It can be represented as a packed float with the constant {@code -0x1.2afd7ep126F}.
     * <pre>
     * <font style='background-color: #F1BF15;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1BF15; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1BF15;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F1BF15'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F1BF15'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F1BF15'>&nbsp;@&nbsp;</font><font style='background-color: #F1BF15; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F1BF15;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F1BF15; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOW = -0x1.2afd7ep126F;
    static { NAMED.put("Vivid Yellow", -0x1.2afd7ep126F); LIST.add(-0x1.2afd7ep126F); }

    /**
     * This color constant "Brilliant Yellow" has RGBA8888 code {@code F7CE50FF}, L 0.8, A 0.49411765, B 0.57254905, alpha 1.0, hue 0.26287684, saturation 0.5858221, and chroma 0.14500555.
     * It can be represented as a packed float with the constant {@code -0x1.24fd98p126F}.
     * <pre>
     * <font style='background-color: #F7CE50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7CE50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7CE50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7CE50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7CE50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7CE50'>&nbsp;@&nbsp;</font><font style='background-color: #F7CE50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7CE50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7CE50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOW = -0x1.24fd98p126F;
    static { NAMED.put("Brilliant Yellow", -0x1.24fd98p126F); LIST.add(-0x1.24fd98p126F); }

    /**
     * This color constant "Strong Yellow" has RGBA8888 code {@code D9AE2FFF}, L 0.6784314, A 0.49803922, B 0.57254905, alpha 1.0, hue 0.25428832, saturation 0.6918318, and chroma 0.14458403.
     * It can be represented as a packed float with the constant {@code -0x1.24ff5ap126F}.
     * <pre>
     * <font style='background-color: #D9AE2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9AE2F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9AE2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9AE2F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9AE2F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9AE2F'>&nbsp;@&nbsp;</font><font style='background-color: #D9AE2F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9AE2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9AE2F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOW = -0x1.24ff5ap126F;
    static { NAMED.put("Strong Yellow", -0x1.24ff5ap126F); LIST.add(-0x1.24ff5ap126F); }

    /**
     * This color constant "Deep Yellow" has RGBA8888 code {@code B88F16FF}, L 0.5647059, A 0.49803922, B 0.5686275, alpha 1.0, hue 0.2545336, saturation 0.7857715, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.22ff2p126F}.
     * <pre>
     * <font style='background-color: #B88F16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B88F16; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B88F16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B88F16'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B88F16'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B88F16'>&nbsp;@&nbsp;</font><font style='background-color: #B88F16; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B88F16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B88F16; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW = -0x1.22ff2p126F;
    static { NAMED.put("Deep Yellow", -0x1.22ff2p126F); LIST.add(-0x1.22ff2p126F); }

    /**
     * This color constant "Light Yellow" has RGBA8888 code {@code F4D284FF}, L 0.81960785, A 0.49803922, B 0.54901963, alpha 1.0, hue 0.25635016, saturation 0.26612818, and chroma 0.09773435.
     * It can be represented as a packed float with the constant {@code -0x1.18ffa2p126F}.
     * <pre>
     * <font style='background-color: #F4D284;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4D284; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4D284;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F4D284'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F4D284'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F4D284'>&nbsp;@&nbsp;</font><font style='background-color: #F4D284; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4D284;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4D284; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOW = -0x1.18ffa2p126F;
    static { NAMED.put("Light Yellow", -0x1.18ffa2p126F); LIST.add(-0x1.18ffa2p126F); }

    /**
     * This color constant "Moderate Yellow" has RGBA8888 code {@code D2AF63FF}, L 0.68235296, A 0.5019608, B 0.54901963, alpha 1.0, hue 0.24364984, saturation 0.3161217, and chroma 0.09773435.
     * It can be represented as a packed float with the constant {@code -0x1.19015cp126F}.
     * <pre>
     * <font style='background-color: #D2AF63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2AF63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2AF63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2AF63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2AF63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2AF63'>&nbsp;@&nbsp;</font><font style='background-color: #D2AF63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2AF63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2AF63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOW = -0x1.19015cp126F;
    static { NAMED.put("Moderate Yellow", -0x1.19015cp126F); LIST.add(-0x1.19015cp126F); }

    /**
     * This color constant "Dark Yellow" has RGBA8888 code {@code B08F42FF}, L 0.56078434, A 0.5019608, B 0.54901963, alpha 1.0, hue 0.24364984, saturation 0.42233092, and chroma 0.09773435.
     * It can be represented as a packed float with the constant {@code -0x1.19011ep126F}.
     * <pre>
     * <font style='background-color: #B08F42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B08F42; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B08F42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B08F42'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B08F42'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B08F42'>&nbsp;@&nbsp;</font><font style='background-color: #B08F42; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B08F42;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B08F42; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOW = -0x1.19011ep126F;
    static { NAMED.put("Dark Yellow", -0x1.19011ep126F); LIST.add(-0x1.19011ep126F); }

    /**
     * This color constant "Pale Yellow" has RGBA8888 code {@code EFD7B2FF}, L 0.8392157, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.235567, saturation 0.10871018, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b01acp126F}.
     * <pre>
     * <font style='background-color: #EFD7B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFD7B2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFD7B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFD7B2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFD7B2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFD7B2'>&nbsp;@&nbsp;</font><font style='background-color: #EFD7B2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFD7B2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFD7B2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.0b01acp126F;
    static { NAMED.put("Pale Yellow", -0x1.0b01acp126F); LIST.add(-0x1.0b01acp126F); }

    /**
     * This color constant "Grayish Yellow" has RGBA8888 code {@code C8B18BFF}, L 0.6862745, A 0.5019608, B 0.5254902, alpha 1.0, hue 0.23778233, saturation 0.08584774, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0d015ep126F}.
     * <pre>
     * <font style='background-color: #C8B18B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8B18B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8B18B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8B18B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8B18B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8B18B'>&nbsp;@&nbsp;</font><font style='background-color: #C8B18B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8B18B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8B18B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOW = -0x1.0d015ep126F;
    static { NAMED.put("Grayish Yellow", -0x1.0d015ep126F); LIST.add(-0x1.0d015ep126F); }

    /**
     * This color constant "Dark Grayish Yellow" has RGBA8888 code {@code A99066FF}, L 0.5647059, A 0.5019608, B 0.5294118, alpha 1.0, hue 0.23941022, saturation 0.14484859, and chroma 0.058723815.
     * It can be represented as a packed float with the constant {@code -0x1.0f012p126F}.
     * <pre>
     * <font style='background-color: #A99066;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A99066; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A99066;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A99066'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A99066'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A99066'>&nbsp;@&nbsp;</font><font style='background-color: #A99066; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A99066;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A99066; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_YELLOW = -0x1.0f012p126F;
    static { NAMED.put("Dark Grayish Yellow", -0x1.0f012p126F); LIST.add(-0x1.0f012p126F); }

    /**
     * This color constant "Yellowish White" has RGBA8888 code {@code EEDFDAFF}, L 0.8745098, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.05119568, saturation 0.054869685, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.0103bep126F}.
     * <pre>
     * <font style='background-color: #EEDFDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEDFDA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEDFDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EEDFDA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EEDFDA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EEDFDA'>&nbsp;@&nbsp;</font><font style='background-color: #EEDFDA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EEDFDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EEDFDA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOWISH_WHITE = -0x1.0103bep126F;
    static { NAMED.put("Yellowish White", -0x1.0103bep126F); LIST.add(-0x1.0103bep126F); }

    /**
     * This color constant "Yellowish Gray" has RGBA8888 code {@code C6B9B1FF}, L 0.7137255, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.19880433, saturation 0.0050498676, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.03016cp126F}.
     * <pre>
     * <font style='background-color: #C6B9B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6B9B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6B9B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6B9B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6B9B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6B9B1'>&nbsp;@&nbsp;</font><font style='background-color: #C6B9B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6B9B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6B9B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOWISH_GRAY = -0x1.03016cp126F;
    static { NAMED.put("Yellowish Gray", -0x1.03016cp126F); LIST.add(-0x1.03016cp126F); }

    /**
     * This color constant "Light Olive Brown" has RGBA8888 code {@code 997736FF}, L 0.47843137, A 0.5019608, B 0.54509807, alpha 1.0, hue 0.24309666, saturation 0.42055148, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.1700f4p126F}.
     * <pre>
     * <font style='background-color: #997736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #997736; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #997736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #997736'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #997736'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #997736'>&nbsp;@&nbsp;</font><font style='background-color: #997736; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #997736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #997736; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE_BROWN = -0x1.1700f4p126F;
    static { NAMED.put("Light Olive Brown", -0x1.1700f4p126F); LIST.add(-0x1.1700f4p126F); }

    /**
     * This color constant "Moderate Olive Brown" has RGBA8888 code {@code 705420FF}, L 0.35686275, A 0.5019608, B 0.5411765, alpha 1.0, hue 0.2424381, saturation 0.54416746, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.1500b6p126F}.
     * <pre>
     * <font style='background-color: #705420;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #705420; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #705420;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #705420'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #705420'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #705420'>&nbsp;@&nbsp;</font><font style='background-color: #705420; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #705420;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #705420; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE_BROWN = -0x1.1500b6p126F;
    static { NAMED.put("Moderate Olive Brown", -0x1.1500b6p126F); LIST.add(-0x1.1500b6p126F); }

    /**
     * This color constant "Dark Olive Brown" has RGBA8888 code {@code 3F2C10FF}, L 0.21568628, A 0.5058824, B 0.5254902, alpha 1.0, hue 0.21390288, saturation 0.4235574, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.0d026ep126F}.
     * <pre>
     * <font style='background-color: #3F2C10;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F2C10; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F2C10;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F2C10'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F2C10'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F2C10'>&nbsp;@&nbsp;</font><font style='background-color: #3F2C10; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F2C10;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F2C10; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE_BROWN = -0x1.0d026ep126F;
    static { NAMED.put("Dark Olive Brown", -0x1.0d026ep126F); LIST.add(-0x1.0d026ep126F); }

    /**
     * This color constant "Vivid Greenish Yellow" has RGBA8888 code {@code EBDD21FF}, L 0.8235294, A 0.4745098, B 0.5882353, alpha 1.0, hue 0.29475066, saturation 0.82722217, and chroma 0.18296935.
     * It can be represented as a packed float with the constant {@code -0x1.2cf3a4p126F}.
     * <pre>
     * <font style='background-color: #EBDD21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBDD21; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBDD21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBDD21'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBDD21'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBDD21'>&nbsp;@&nbsp;</font><font style='background-color: #EBDD21; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBDD21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBDD21; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_GREENISH_YELLOW = -0x1.2cf3a4p126F;
    static { NAMED.put("Vivid Greenish Yellow", -0x1.2cf3a4p126F); LIST.add(-0x1.2cf3a4p126F); }

    /**
     * This color constant "Brilliant Greenish Yellow" has RGBA8888 code {@code E9DC55FF}, L 0.8235294, A 0.47843137, B 0.57254905, alpha 1.0, hue 0.29598206, saturation 0.5617872, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.24f5a4p126F}.
     * <pre>
     * <font style='background-color: #E9DC55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9DC55; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9DC55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9DC55'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9DC55'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9DC55'>&nbsp;@&nbsp;</font><font style='background-color: #E9DC55; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9DC55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9DC55; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREENISH_YELLOW = -0x1.24f5a4p126F;
    static { NAMED.put("Brilliant Greenish Yellow", -0x1.24f5a4p126F); LIST.add(-0x1.24f5a4p126F); }

    /**
     * This color constant "Strong Greenish Yellow" has RGBA8888 code {@code C4B827FF}, L 0.68235296, A 0.47843137, B 0.57254905, alpha 1.0, hue 0.29598206, saturation 0.689097, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.24f55cp126F}.
     * <pre>
     * <font style='background-color: #C4B827;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4B827; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4B827;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4B827'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4B827'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4B827'>&nbsp;@&nbsp;</font><font style='background-color: #C4B827; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4B827;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4B827; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREENISH_YELLOW = -0x1.24f55cp126F;
    static { NAMED.put("Strong Greenish Yellow", -0x1.24f55cp126F); LIST.add(-0x1.24f55cp126F); }

    /**
     * This color constant "Deep Greenish Yellow" has RGBA8888 code {@code A29812FF}, L 0.5647059, A 0.47843137, B 0.5686275, alpha 1.0, hue 0.298453, saturation 0.7815358, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.22f52p126F}.
     * <pre>
     * <font style='background-color: #A29812;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A29812; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A29812;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A29812'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A29812'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A29812'>&nbsp;@&nbsp;</font><font style='background-color: #A29812; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A29812;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A29812; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREENISH_YELLOW = -0x1.22f52p126F;
    static { NAMED.put("Deep Greenish Yellow", -0x1.22f52p126F); LIST.add(-0x1.22f52p126F); }

    /**
     * This color constant "Light Greenish Yellow" has RGBA8888 code {@code E9DD8AFF}, L 0.8352941, A 0.49019608, B 0.54509807, alpha 1.0, hue 0.28407243, saturation 0.20887925, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.16fbaap126F}.
     * <pre>
     * <font style='background-color: #E9DD8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9DD8A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9DD8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9DD8A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9DD8A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9DD8A'>&nbsp;@&nbsp;</font><font style='background-color: #E9DD8A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9DD8A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9DD8A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_YELLOW = -0x1.16fbaap126F;
    static { NAMED.put("Light Greenish Yellow", -0x1.16fbaap126F); LIST.add(-0x1.16fbaap126F); }

    /**
     * This color constant "Moderate Greenish Yellow" has RGBA8888 code {@code C0B55EFF}, L 0.6745098, A 0.4862745, B 0.54901963, alpha 1.0, hue 0.29344302, saturation 0.31171232, and chroma 0.10141215.
     * It can be represented as a packed float with the constant {@code -0x1.18f958p126F}.
     * <pre>
     * <font style='background-color: #C0B55E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0B55E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0B55E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0B55E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0B55E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0B55E'>&nbsp;@&nbsp;</font><font style='background-color: #C0B55E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0B55E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0B55E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREENISH_YELLOW = -0x1.18f958p126F;
    static { NAMED.put("Moderate Greenish Yellow", -0x1.18f958p126F); LIST.add(-0x1.18f958p126F); }

    /**
     * This color constant "Dark Greenish Yellow" has RGBA8888 code {@code 9E953CFF}, L 0.5568628, A 0.4862745, B 0.5529412, alpha 1.0, hue 0.2903691, saturation 0.47431794, and chroma 0.10895567.
     * It can be represented as a packed float with the constant {@code -0x1.1af91cp126F}.
     * <pre>
     * <font style='background-color: #9E953C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E953C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E953C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E953C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E953C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E953C'>&nbsp;@&nbsp;</font><font style='background-color: #9E953C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E953C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E953C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_YELLOW = -0x1.1af91cp126F;
    static { NAMED.put("Dark Greenish Yellow", -0x1.1af91cp126F); LIST.add(-0x1.1af91cp126F); }

    /**
     * This color constant "Pale Greenish Yellow" has RGBA8888 code {@code E6DCABFF}, L 0.8392157, A 0.49411765, B 0.5294118, alpha 1.0, hue 0.28142345, saturation 0.088226974, and chroma 0.059754133.
     * It can be represented as a packed float with the constant {@code -0x1.0efdacp126F}.
     * <pre>
     * <font style='background-color: #E6DCAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6DCAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6DCAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6DCAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6DCAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6DCAB'>&nbsp;@&nbsp;</font><font style='background-color: #E6DCAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6DCAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6DCAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREENISH_YELLOW = -0x1.0efdacp126F;
    static { NAMED.put("Pale Greenish Yellow", -0x1.0efdacp126F); LIST.add(-0x1.0efdacp126F); }

    /**
     * This color constant "Grayish Greenish Yellow" has RGBA8888 code {@code BEB584FF}, L 0.68235296, A 0.49411765, B 0.5294118, alpha 1.0, hue 0.28142345, saturation 0.11302983, and chroma 0.059754133.
     * It can be represented as a packed float with the constant {@code -0x1.0efd5cp126F}.
     * <pre>
     * <font style='background-color: #BEB584;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEB584; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEB584;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BEB584'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BEB584'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BEB584'>&nbsp;@&nbsp;</font><font style='background-color: #BEB584; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEB584;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEB584; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_GREENISH_YELLOW = -0x1.0efd5cp126F;
    static { NAMED.put("Grayish Greenish Yellow", -0x1.0efd5cp126F); LIST.add(-0x1.0efd5cp126F); }

    /**
     * This color constant "Light Olive" has RGBA8888 code {@code 8B7D2EFF}, L 0.47843137, A 0.49019608, B 0.54901963, alpha 1.0, hue 0.28142345, saturation 0.4878964, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.18faf4p126F}.
     * <pre>
     * <font style='background-color: #8B7D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B7D2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B7D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B7D2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B7D2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B7D2E'>&nbsp;@&nbsp;</font><font style='background-color: #8B7D2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B7D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B7D2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE = -0x1.18faf4p126F;
    static { NAMED.put("Light Olive", -0x1.18faf4p126F); LIST.add(-0x1.18faf4p126F); }

    /**
     * This color constant "Moderate Olive" has RGBA8888 code {@code 64591AFF}, L 0.35686275, A 0.49019608, B 0.5411765, alpha 1.0, hue 0.28720093, saturation 0.5354783, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.14fab6p126F}.
     * <pre>
     * <font style='background-color: #64591A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64591A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64591A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #64591A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #64591A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #64591A'>&nbsp;@&nbsp;</font><font style='background-color: #64591A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64591A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64591A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE = -0x1.14fab6p126F;
    static { NAMED.put("Moderate Olive", -0x1.14fab6p126F); LIST.add(-0x1.14fab6p126F); }

    /**
     * This color constant "Dark Olive" has RGBA8888 code {@code 352E0AFF}, L 0.20784314, A 0.49411765, B 0.5294118, alpha 1.0, hue 0.28142345, saturation 0.5568114, and chroma 0.059754133.
     * It can be represented as a packed float with the constant {@code -0x1.0efc6ap126F}.
     * <pre>
     * <font style='background-color: #352E0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #352E0A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #352E0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #352E0A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #352E0A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #352E0A'>&nbsp;@&nbsp;</font><font style='background-color: #352E0A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #352E0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #352E0A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE = -0x1.0efc6ap126F;
    static { NAMED.put("Dark Olive", -0x1.0efc6ap126F); LIST.add(-0x1.0efc6ap126F); }

    /**
     * This color constant "Light Grayish Olive" has RGBA8888 code {@code 8E856FFF}, L 0.5137255, A 0.49803922, B 0.5137255, alpha 1.0, hue 0.27259654, saturation 0.035555556, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.06ff06p126F}.
     * <pre>
     * <font style='background-color: #8E856F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E856F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E856F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E856F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E856F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E856F'>&nbsp;@&nbsp;</font><font style='background-color: #8E856F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E856F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E856F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_OLIVE = -0x1.06ff06p126F;
    static { NAMED.put("Light Grayish Olive", -0x1.06ff06p126F); LIST.add(-0x1.06ff06p126F); }

    /**
     * This color constant "Grayish Olive" has RGBA8888 code {@code 5D553FFF}, L 0.34509805, A 0.49803922, B 0.5176471, alpha 1.0, hue 0.2676211, saturation 0.10095414, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.08febp126F}.
     * <pre>
     * <font style='background-color: #5D553F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D553F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D553F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5D553F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5D553F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5D553F'>&nbsp;@&nbsp;</font><font style='background-color: #5D553F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D553F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D553F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_OLIVE = -0x1.08febp126F;
    static { NAMED.put("Grayish Olive", -0x1.08febp126F); LIST.add(-0x1.08febp126F); }

    /**
     * This color constant "Dark Grayish Olive" has RGBA8888 code {@code 35301CFF}, L 0.21568628, A 0.49411765, B 0.5176471, alpha 1.0, hue 0.30119568, saturation 0.19469984, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.08fc6ep126F}.
     * <pre>
     * <font style='background-color: #35301C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #35301C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #35301C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #35301C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #35301C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #35301C'>&nbsp;@&nbsp;</font><font style='background-color: #35301C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #35301C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #35301C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_OLIVE = -0x1.08fc6ep126F;
    static { NAMED.put("Dark Grayish Olive", -0x1.08fc6ep126F); LIST.add(-0x1.08fc6ep126F); }

    /**
     * This color constant "Light Olive Gray" has RGBA8888 code {@code 8F877FFF}, L 0.52156866, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.19880433, saturation 0.0067465003, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.03010ap126F}.
     * <pre>
     * <font style='background-color: #8F877F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F877F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F877F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F877F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F877F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F877F'>&nbsp;@&nbsp;</font><font style='background-color: #8F877F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F877F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F877F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE_GRAY = -0x1.03010ap126F;
    static { NAMED.put("Light Olive Gray", -0x1.03010ap126F); LIST.add(-0x1.03010ap126F); }

    /**
     * This color constant "Olive Gray" has RGBA8888 code {@code 58514AFF}, L 0.33333334, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.19880433, saturation 0.01231148, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.0300aap126F}.
     * <pre>
     * <font style='background-color: #58514A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #58514A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #58514A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #58514A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #58514A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #58514A'>&nbsp;@&nbsp;</font><font style='background-color: #58514A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #58514A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #58514A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_GRAY = -0x1.0300aap126F;
    static { NAMED.put("Olive Gray", -0x1.0300aap126F); LIST.add(-0x1.0300aap126F); }

    /**
     * This color constant "Olive Black" has RGBA8888 code {@code 23211CFF}, L 0.16078432, A 0.49803922, B 0.5058824, alpha 1.0, hue 0.30119568, saturation 0.03265306, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.02fe52p126F}.
     * <pre>
     * <font style='background-color: #23211C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #23211C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #23211C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #23211C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #23211C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #23211C'>&nbsp;@&nbsp;</font><font style='background-color: #23211C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #23211C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #23211C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_BLACK = -0x1.02fe52p126F;
    static { NAMED.put("Olive Black", -0x1.02fe52p126F); LIST.add(-0x1.02fe52p126F); }

    /**
     * This color constant "Vivid Yellow Green" has RGBA8888 code {@code A7DC26FF}, L 0.7529412, A 0.44313726, B 0.5803922, alpha 1.0, hue 0.34799182, saturation 0.7123791, and chroma 0.19616999.
     * It can be represented as a packed float with the constant {@code -0x1.28e38p126F}.
     * <pre>
     * <font style='background-color: #A7DC26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7DC26; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7DC26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A7DC26'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A7DC26'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A7DC26'>&nbsp;@&nbsp;</font><font style='background-color: #A7DC26; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A7DC26;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A7DC26; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOW_GREEN = -0x1.28e38p126F;
    static { NAMED.put("Vivid Yellow Green", -0x1.28e38p126F); LIST.add(-0x1.28e38p126F); }

    /**
     * This color constant "Brilliant Yellow Green" has RGBA8888 code {@code C3DF69FF}, L 0.79607844, A 0.4627451, B 0.56078434, alpha 1.0, hue 0.3375212, saturation 0.39984876, and chroma 0.14202859.
     * It can be represented as a packed float with the constant {@code -0x1.1eed96p126F}.
     * <pre>
     * <font style='background-color: #C3DF69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3DF69; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3DF69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3DF69'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3DF69'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3DF69'>&nbsp;@&nbsp;</font><font style='background-color: #C3DF69; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3DF69;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3DF69; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOW_GREEN = -0x1.1eed96p126F;
    static { NAMED.put("Brilliant Yellow Green", -0x1.1eed96p126F); LIST.add(-0x1.1eed96p126F); }

    /**
     * This color constant "Strong Yellow Green" has RGBA8888 code {@code 82A12BFF}, L 0.5568628, A 0.45882353, B 0.56078434, alpha 1.0, hue 0.34477478, saturation 0.5960251, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.1eeb1cp126F}.
     * <pre>
     * <font style='background-color: #82A12B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82A12B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82A12B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #82A12B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #82A12B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #82A12B'>&nbsp;@&nbsp;</font><font style='background-color: #82A12B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #82A12B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #82A12B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOW_GREEN = -0x1.1eeb1cp126F;
    static { NAMED.put("Strong Yellow Green", -0x1.1eeb1cp126F); LIST.add(-0x1.1eeb1cp126F); }

    /**
     * This color constant "Deep Yellow Green" has RGBA8888 code {@code 486C0EFF}, L 0.3764706, A 0.45490196, B 0.54901963, alpha 1.0, hue 0.36837745, saturation 0.6700537, and chroma 0.13269757.
     * It can be represented as a packed float with the constant {@code -0x1.18e8cp126F}.
     * <pre>
     * <font style='background-color: #486C0E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #486C0E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #486C0E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #486C0E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #486C0E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #486C0E'>&nbsp;@&nbsp;</font><font style='background-color: #486C0E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #486C0E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #486C0E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_GREEN = -0x1.18e8cp126F;
    static { NAMED.put("Deep Yellow Green", -0x1.18e8cp126F); LIST.add(-0x1.18e8cp126F); }

    /**
     * This color constant "Light Yellow Green" has RGBA8888 code {@code CEDB9FFF}, L 0.80784315, A 0.48235294, B 0.5294118, alpha 1.0, hue 0.33601886, saturation 0.08941486, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.0ef79cp126F}.
     * <pre>
     * <font style='background-color: #CEDB9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEDB9F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEDB9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEDB9F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEDB9F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEDB9F'>&nbsp;@&nbsp;</font><font style='background-color: #CEDB9F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEDB9F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEDB9F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOW_GREEN = -0x1.0ef79cp126F;
    static { NAMED.put("Light Yellow Green", -0x1.0ef79cp126F); LIST.add(-0x1.0ef79cp126F); }

    /**
     * This color constant "Moderate Yellow Green" has RGBA8888 code {@code 8B9A5FFF}, L 0.5568628, A 0.47843137, B 0.53333336, alpha 1.0, hue 0.34141475, saturation 0.18171746, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.10f51cp126F}.
     * <pre>
     * <font style='background-color: #8B9A5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B9A5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B9A5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B9A5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B9A5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B9A5F'>&nbsp;@&nbsp;</font><font style='background-color: #8B9A5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B9A5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B9A5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOW_GREEN = -0x1.10f51cp126F;
    static { NAMED.put("Moderate Yellow Green", -0x1.10f51cp126F); LIST.add(-0x1.10f51cp126F); }

    /**
     * This color constant "Pale Yellow Green" has RGBA8888 code {@code D7D7C1FF}, L 0.8156863, A 0.49411765, B 0.50980395, alpha 1.0, hue 0.33601886, saturation 0.009934984, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.04fdap126F}.
     * <pre>
     * <font style='background-color: #D7D7C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7D7C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7D7C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7D7C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7D7C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7D7C1'>&nbsp;@&nbsp;</font><font style='background-color: #D7D7C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7D7C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7D7C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_GREEN = -0x1.04fdap126F;
    static { NAMED.put("Pale Yellow Green", -0x1.04fdap126F); LIST.add(-0x1.04fdap126F); }

    /**
     * This color constant "Grayish Yellow Green" has RGBA8888 code {@code 979A85FF}, L 0.5764706, A 0.49411765, B 0.50980395, alpha 1.0, hue 0.33601886, saturation 0.015069252, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.04fd26p126F}.
     * <pre>
     * <font style='background-color: #979A85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #979A85; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #979A85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #979A85'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #979A85'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #979A85'>&nbsp;@&nbsp;</font><font style='background-color: #979A85; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #979A85;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #979A85; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOW_GREEN = -0x1.04fd26p126F;
    static { NAMED.put("Grayish Yellow Green", -0x1.04fd26p126F); LIST.add(-0x1.04fd26p126F); }

    /**
     * This color constant "Strong Olive Green" has RGBA8888 code {@code 2C5506FF}, L 0.3019608, A 0.45490196, B 0.5411765, alpha 1.0, hue 0.38222384, saturation 0.65441054, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.14e89ap126F}.
     * <pre>
     * <font style='background-color: #2C5506;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2C5506; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2C5506;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2C5506'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2C5506'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2C5506'>&nbsp;@&nbsp;</font><font style='background-color: #2C5506; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2C5506;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2C5506; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_OLIVE_GREEN = -0x1.14e89ap126F;
    static { NAMED.put("Strong Olive Green", -0x1.14e89ap126F); LIST.add(-0x1.14e89ap126F); }

    /**
     * This color constant "Moderate Olive Green" has RGBA8888 code {@code 495B22FF}, L 0.3372549, A 0.47058824, B 0.5372549, alpha 1.0, hue 0.3563733, saturation 0.4398574, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.12f0acp126F}.
     * <pre>
     * <font style='background-color: #495B22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #495B22; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #495B22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #495B22'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #495B22'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #495B22'>&nbsp;@&nbsp;</font><font style='background-color: #495B22; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #495B22;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #495B22; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE_GREEN = -0x1.12f0acp126F;
    static { NAMED.put("Moderate Olive Green", -0x1.12f0acp126F); LIST.add(-0x1.12f0acp126F); }

    /**
     * This color constant "Dark Olive Green" has RGBA8888 code {@code 20340BFF}, L 0.20784314, A 0.47058824, B 0.5294118, alpha 1.0, hue 0.375, saturation 0.55401665, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.0ef06ap126F}.
     * <pre>
     * <font style='background-color: #20340B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20340B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20340B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #20340B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #20340B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #20340B'>&nbsp;@&nbsp;</font><font style='background-color: #20340B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20340B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20340B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE_GREEN = -0x1.0ef06ap126F;
    static { NAMED.put("Dark Olive Green", -0x1.0ef06ap126F); LIST.add(-0x1.0ef06ap126F); }

    /**
     * This color constant "Grayish Olive Green" has RGBA8888 code {@code 545947FF}, L 0.34901962, A 0.49019608, B 0.50980395, alpha 1.0, hue 0.375, saturation 0.029031789, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.04fab2p126F}.
     * <pre>
     * <font style='background-color: #545947;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #545947; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #545947;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #545947'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #545947'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #545947'>&nbsp;@&nbsp;</font><font style='background-color: #545947; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #545947;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #545947; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_OLIVE_GREEN = -0x1.04fab2p126F;
    static { NAMED.put("Grayish Olive Green", -0x1.04fab2p126F); LIST.add(-0x1.04fab2p126F); }

    /**
     * This color constant "Dark Grayish Olive Green" has RGBA8888 code {@code 2F3326FF}, L 0.21960784, A 0.49019608, B 0.50980395, alpha 1.0, hue 0.375, saturation 0.057454754, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.04fa7p126F}.
     * <pre>
     * <font style='background-color: #2F3326;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F3326; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F3326;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F3326'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F3326'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F3326'>&nbsp;@&nbsp;</font><font style='background-color: #2F3326; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F3326;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F3326; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_OLIVE_GREEN = -0x1.04fa7p126F;
    static { NAMED.put("Dark Grayish Olive Green", -0x1.04fa7p126F); LIST.add(-0x1.04fa7p126F); }

    /**
     * This color constant "Vivid Yellowish Green" has RGBA8888 code {@code 3FD740FF}, L 0.6745098, A 0.40784314, B 0.5647059, alpha 1.0, hue 0.40256009, saturation 0.7687198, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.20d158p126F}.
     * <pre>
     * <font style='background-color: #3FD740;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FD740; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FD740;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FD740'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FD740'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FD740'>&nbsp;@&nbsp;</font><font style='background-color: #3FD740; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FD740;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FD740; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOWISH_GREEN = -0x1.20d158p126F;
    static { NAMED.put("Vivid Yellowish Green", -0x1.20d158p126F); LIST.add(-0x1.20d158p126F); }

    /**
     * This color constant "Brilliant Yellowish Green" has RGBA8888 code {@code 87D989FF}, L 0.73333335, A 0.44705883, B 0.53333336, alpha 1.0, hue 0.41055703, saturation 0.24469683, and chroma 0.12463325.
     * It can be represented as a packed float with the constant {@code -0x1.10e576p126F}.
     * <pre>
     * <font style='background-color: #87D989;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87D989; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87D989;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87D989'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87D989'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87D989'>&nbsp;@&nbsp;</font><font style='background-color: #87D989; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87D989;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87D989; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOWISH_GREEN = -0x1.10e576p126F;
    static { NAMED.put("Brilliant Yellowish Green", -0x1.10e576p126F); LIST.add(-0x1.10e576p126F); }

    /**
     * This color constant "Strong Yellowish Green" has RGBA8888 code {@code 39964AFF}, L 0.4862745, A 0.43529412, B 0.5372549, alpha 1.0, hue 0.4168505, saturation 0.67059773, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.12def8p126F}.
     * <pre>
     * <font style='background-color: #39964A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #39964A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #39964A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #39964A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #39964A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #39964A'>&nbsp;@&nbsp;</font><font style='background-color: #39964A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #39964A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #39964A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_GREEN = -0x1.12def8p126F;
    static { NAMED.put("Strong Yellowish Green", -0x1.12def8p126F); LIST.add(-0x1.12def8p126F); }

    /**
     * This color constant "Deep Yellowish Green" has RGBA8888 code {@code 176A1EFF}, L 0.34901962, A 0.4392157, B 0.5411765, alpha 1.0, hue 0.40522522, saturation 0.8547478, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.14e0b2p126F}.
     * <pre>
     * <font style='background-color: #176A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #176A1E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #176A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #176A1E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #176A1E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #176A1E'>&nbsp;@&nbsp;</font><font style='background-color: #176A1E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #176A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #176A1E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_GREEN = -0x1.14e0b2p126F;
    static { NAMED.put("Deep Yellowish Green", -0x1.14e0b2p126F); LIST.add(-0x1.14e0b2p126F); }

    /**
     * This color constant "Very Deep Yellowish Green" has RGBA8888 code {@code 054208FF}, L 0.23529412, A 0.4509804, B 0.53333336, alpha 1.0, hue 0.40494394, saturation 1.050273, and chroma 0.11809544.
     * It can be represented as a packed float with the constant {@code -0x1.10e678p126F}.
     * <pre>
     * <font style='background-color: #054208;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #054208; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #054208;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #054208'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #054208'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #054208'>&nbsp;@&nbsp;</font><font style='background-color: #054208; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #054208;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #054208; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_YELLOWISH_GREEN = -0x1.10e678p126F;
    static { NAMED.put("Very Deep Yellowish Green", -0x1.10e678p126F); LIST.add(-0x1.10e678p126F); }

    /**
     * This color constant "Very Light Yellowish Green" has RGBA8888 code {@code C5EDC4FF}, L 0.8627451, A 0.4745098, B 0.5176471, alpha 1.0, hue 0.4036119, saturation 0.1156203, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.08f3b8p126F}.
     * <pre>
     * <font style='background-color: #C5EDC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5EDC4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5EDC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C5EDC4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C5EDC4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C5EDC4'>&nbsp;@&nbsp;</font><font style='background-color: #C5EDC4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5EDC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5EDC4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_YELLOWISH_GREEN = -0x1.08f3b8p126F;
    static { NAMED.put("Very Light Yellowish Green", -0x1.08f3b8p126F); LIST.add(-0x1.08f3b8p126F); }

    /**
     * This color constant "Light Yellowish Green" has RGBA8888 code {@code 9CC69CFF}, L 0.7019608, A 0.47058824, B 0.5176471, alpha 1.0, hue 0.41398114, saturation 0.078336, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.08f166p126F}.
     * <pre>
     * <font style='background-color: #9CC69C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CC69C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CC69C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9CC69C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9CC69C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9CC69C'>&nbsp;@&nbsp;</font><font style='background-color: #9CC69C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CC69C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CC69C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_GREEN = -0x1.08f166p126F;
    static { NAMED.put("Light Yellowish Green", -0x1.08f166p126F); LIST.add(-0x1.08f166p126F); }

    /**
     * This color constant "Moderate Yellowish Green" has RGBA8888 code {@code 669069FF}, L 0.5058824, A 0.46666667, B 0.5176471, alpha 1.0, hue 0.4225058, saturation 0.17872238, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.08ef02p126F}.
     * <pre>
     * <font style='background-color: #669069;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #669069; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #669069;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #669069'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #669069'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #669069'>&nbsp;@&nbsp;</font><font style='background-color: #669069; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #669069;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #669069; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_GREEN = -0x1.08ef02p126F;
    static { NAMED.put("Moderate Yellowish Green", -0x1.08ef02p126F); LIST.add(-0x1.08ef02p126F); }

    /**
     * This color constant "Dark Yellowish Green" has RGBA8888 code {@code 2F5D3AFF}, L 0.32941177, A 0.4627451, B 0.5176471, alpha 1.0, hue 0.4295985, saturation 0.41846153, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.08eca8p126F}.
     * <pre>
     * <font style='background-color: #2F5D3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F5D3A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F5D3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F5D3A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F5D3A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F5D3A'>&nbsp;@&nbsp;</font><font style='background-color: #2F5D3A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F5D3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F5D3A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_GREEN = -0x1.08eca8p126F;
    static { NAMED.put("Dark Yellowish Green", -0x1.08eca8p126F); LIST.add(-0x1.08eca8p126F); }

    /**
     * This color constant "Very Dark Yellowish Green" has RGBA8888 code {@code 10361AFF}, L 0.20784314, A 0.46666667, B 0.5176471, alpha 1.0, hue 0.4225058, saturation 0.6699864, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.08ee6ap126F}.
     * <pre>
     * <font style='background-color: #10361A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10361A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10361A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #10361A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #10361A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #10361A'>&nbsp;@&nbsp;</font><font style='background-color: #10361A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #10361A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #10361A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_YELLOWISH_GREEN = -0x1.08ee6ap126F;
    static { NAMED.put("Very Dark Yellowish Green", -0x1.08ee6ap126F); LIST.add(-0x1.08ee6ap126F); }

    /**
     * This color constant "Vivid Green" has RGBA8888 code {@code 23EAA5FF}, L 0.7490196, A 0.41568628, B 0.52156866, alpha 1.0, hue 0.46014452, saturation 0.83749604, and chroma 0.17337766.
     * It can be represented as a packed float with the constant {@code -0x1.0ad57ep126F}.
     * <pre>
     * <font style='background-color: #23EAA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #23EAA5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #23EAA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #23EAA5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #23EAA5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #23EAA5'>&nbsp;@&nbsp;</font><font style='background-color: #23EAA5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #23EAA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #23EAA5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_GREEN = -0x1.0ad57ep126F;
    static { NAMED.put("Vivid Green", -0x1.0ad57ep126F); LIST.add(-0x1.0ad57ep126F); }

    /**
     * This color constant "Brilliant Green" has RGBA8888 code {@code 49D0A3FF}, L 0.6784314, A 0.43529412, B 0.50980395, alpha 1.0, hue 0.4760548, saturation 0.6167474, and chroma 0.1303775.
     * It can be represented as a packed float with the constant {@code -0x1.04df5ap126F}.
     * <pre>
     * <font style='background-color: #49D0A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49D0A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49D0A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #49D0A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #49D0A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #49D0A3'>&nbsp;@&nbsp;</font><font style='background-color: #49D0A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #49D0A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #49D0A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREEN = -0x1.04df5ap126F;
    static { NAMED.put("Brilliant Green", -0x1.04df5ap126F); LIST.add(-0x1.04df5ap126F); }

    /**
     * This color constant "Strong Green" has RGBA8888 code {@code 158A66FF}, L 0.4509804, A 0.4392157, B 0.50980395, alpha 1.0, hue 0.47453672, saturation 0.9334911, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.04e0e6p126F}.
     * <pre>
     * <font style='background-color: #158A66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #158A66; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #158A66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #158A66'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #158A66'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #158A66'>&nbsp;@&nbsp;</font><font style='background-color: #158A66; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #158A66;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #158A66; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREEN = -0x1.04e0e6p126F;
    static { NAMED.put("Strong Green", -0x1.04e0e6p126F); LIST.add(-0x1.04e0e6p126F); }

    /**
     * This color constant "Very Light Green" has RGBA8888 code {@code A6E2CAFF}, L 0.8039216, A 0.46666667, B 0.5058824, alpha 1.0, hue 0.47218934, saturation 0.13207756, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.02ef9ap126F}.
     * <pre>
     * <font style='background-color: #A6E2CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6E2CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6E2CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6E2CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6E2CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6E2CA'>&nbsp;@&nbsp;</font><font style='background-color: #A6E2CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6E2CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6E2CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_GREEN = -0x1.02ef9ap126F;
    static { NAMED.put("Very Light Green", -0x1.02ef9ap126F); LIST.add(-0x1.02ef9ap126F); }

    /**
     * This color constant "Light Green" has RGBA8888 code {@code 6FAC95FF}, L 0.59607846, A 0.4627451, B 0.5058824, alpha 1.0, hue 0.47506347, saturation 0.23714149, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.02ed3p126F}.
     * <pre>
     * <font style='background-color: #6FAC95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6FAC95; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6FAC95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6FAC95'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6FAC95'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6FAC95'>&nbsp;@&nbsp;</font><font style='background-color: #6FAC95; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6FAC95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6FAC95; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREEN = -0x1.02ed3p126F;
    static { NAMED.put("Light Green", -0x1.02ed3p126F); LIST.add(-0x1.02ed3p126F); }

    /**
     * This color constant "Moderate Green" has RGBA8888 code {@code 337762FF}, L 0.40784314, A 0.45882353, B 0.5058824, alpha 1.0, hue 0.47740343, saturation 0.51709276, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.02eadp126F}.
     * <pre>
     * <font style='background-color: #337762;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #337762; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #337762;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #337762'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #337762'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #337762'>&nbsp;@&nbsp;</font><font style='background-color: #337762; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #337762;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #337762; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREEN = -0x1.02eadp126F;
    static { NAMED.put("Moderate Green", -0x1.02eadp126F); LIST.add(-0x1.02eadp126F); }

    /**
     * This color constant "Dark Green" has RGBA8888 code {@code 164E3DFF}, L 0.28235295, A 0.4627451, B 0.5058824, alpha 1.0, hue 0.47506347, saturation 0.6699864, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.02ec9p126F}.
     * <pre>
     * <font style='background-color: #164E3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #164E3D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #164E3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #164E3D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #164E3D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #164E3D'>&nbsp;@&nbsp;</font><font style='background-color: #164E3D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #164E3D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #164E3D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREEN = -0x1.02ec9p126F;
    static { NAMED.put("Dark Green", -0x1.02ec9p126F); LIST.add(-0x1.02ec9p126F); }

    /**
     * This color constant "Very Dark Green" has RGBA8888 code {@code 0C2E24FF}, L 0.18431373, A 0.4745098, B 0.5019608, alpha 1.0, hue 0.48778233, saturation 0.62442607, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.00f25ep126F}.
     * <pre>
     * <font style='background-color: #0C2E24;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C2E24; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C2E24;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0C2E24'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0C2E24'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0C2E24'>&nbsp;@&nbsp;</font><font style='background-color: #0C2E24; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C2E24;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C2E24; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_GREEN = -0x1.00f25ep126F;
    static { NAMED.put("Very Dark Green", -0x1.00f25ep126F); LIST.add(-0x1.00f25ep126F); }

    /**
     * This color constant "Very Pale Green" has RGBA8888 code {@code C7D9D6FF}, L 0.8117647, A 0.49019608, B 0.49803922, alpha 1.0, hue 0.53142345, saturation 0.01509653, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.fefb9ep125F}.
     * <pre>
     * <font style='background-color: #C7D9D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7D9D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7D9D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7D9D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7D9D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7D9D6'>&nbsp;@&nbsp;</font><font style='background-color: #C7D9D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7D9D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7D9D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_GREEN = -0x1.fefb9ep125F;
    static { NAMED.put("Very Pale Green", -0x1.fefb9ep125F); LIST.add(-0x1.fefb9ep125F); }

    /**
     * This color constant "Pale Green" has RGBA8888 code {@code 94A6A3FF}, L 0.6156863, A 0.49019608, B 0.49803922, alpha 1.0, hue 0.53142345, saturation 0.020630827, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.fefb3ap125F}.
     * <pre>
     * <font style='background-color: #94A6A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #94A6A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #94A6A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #94A6A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #94A6A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #94A6A3'>&nbsp;@&nbsp;</font><font style='background-color: #94A6A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #94A6A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #94A6A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.fefb3ap125F;
    static { NAMED.put("Pale Green", -0x1.fefb3ap125F); LIST.add(-0x1.fefb3ap125F); }

    /**
     * This color constant "Grayish Green" has RGBA8888 code {@code 61716EFF}, L 0.42745098, A 0.4862745, B 0.49803922, alpha 1.0, hue 0.52259654, saturation 0.0615574, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.fef8dap125F}.
     * <pre>
     * <font style='background-color: #61716E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61716E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61716E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61716E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61716E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61716E'>&nbsp;@&nbsp;</font><font style='background-color: #61716E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61716E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61716E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_GREEN = -0x1.fef8dap125F;
    static { NAMED.put("Grayish Green", -0x1.fef8dap125F); LIST.add(-0x1.fef8dap125F); }

    /**
     * This color constant "Dark Grayish Green" has RGBA8888 code {@code 394746FF}, L 0.28627452, A 0.49019608, B 0.49803922, alpha 1.0, hue 0.53142345, saturation 0.061867937, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.fefa92p125F}.
     * <pre>
     * <font style='background-color: #394746;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #394746; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #394746;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #394746'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #394746'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #394746'>&nbsp;@&nbsp;</font><font style='background-color: #394746; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #394746;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #394746; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_GREEN = -0x1.fefa92p125F;
    static { NAMED.put("Dark Grayish Green", -0x1.fefa92p125F); LIST.add(-0x1.fefa92p125F); }

    /**
     * This color constant "Blackish Green" has RGBA8888 code {@code 1F2A2AFF}, L 0.18431373, A 0.49019608, B 0.49411765, alpha 1.0, hue 0.58601886, saturation 0.14151925, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fcfa5ep125F}.
     * <pre>
     * <font style='background-color: #1F2A2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F2A2A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F2A2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1F2A2A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1F2A2A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1F2A2A'>&nbsp;@&nbsp;</font><font style='background-color: #1F2A2A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F2A2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F2A2A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_GREEN = -0x1.fcfa5ep125F;
    static { NAMED.put("Blackish Green", -0x1.fcfa5ep125F); LIST.add(-0x1.fcfa5ep125F); }

    /**
     * This color constant "Greenish White" has RGBA8888 code {@code E0E2E5FF}, L 0.87058824, A 0.49803922, B 0.49411765, alpha 1.0, hue 0.6988043, saturation 0.054869685, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fcffbcp125F}.
     * <pre>
     * <font style='background-color: #E0E2E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0E2E5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0E2E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0E2E5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0E2E5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0E2E5'>&nbsp;@&nbsp;</font><font style='background-color: #E0E2E5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0E2E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0E2E5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_WHITE = -0x1.fcffbcp125F;
    static { NAMED.put("Greenish White", -0x1.fcffbcp125F); LIST.add(-0x1.fcffbcp125F); }

    /**
     * This color constant "Light Greenish Gray" has RGBA8888 code {@code BABEC1FF}, L 0.72156864, A 0.49803922, B 0.49411765, alpha 1.0, hue 0.6988043, saturation 0.0132231405, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fcff7p125F}.
     * <pre>
     * <font style='background-color: #BABEC1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BABEC1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BABEC1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BABEC1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BABEC1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BABEC1'>&nbsp;@&nbsp;</font><font style='background-color: #BABEC1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BABEC1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BABEC1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_GRAY = -0x1.fcff7p125F;
    static { NAMED.put("Light Greenish Gray", -0x1.fcff7p125F); LIST.add(-0x1.fcff7p125F); }

    /**
     * This color constant "Greenish Gray" has RGBA8888 code {@code 848888FF}, L 0.5176471, A 0.49411765, B 0.49803922, alpha 1.0, hue 0.5511957, saturation 0.010078105, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fefd08p125F}.
     * <pre>
     * <font style='background-color: #848888;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #848888; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #848888;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #848888'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #848888'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #848888'>&nbsp;@&nbsp;</font><font style='background-color: #848888; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #848888;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #848888; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_GRAY = -0x1.fefd08p125F;
    static { NAMED.put("Greenish Gray", -0x1.fefd08p125F); LIST.add(-0x1.fefd08p125F); }

    /**
     * This color constant "Dark Greenish Gray" has RGBA8888 code {@code 545858FF}, L 0.3529412, A 0.49411765, B 0.49803922, alpha 1.0, hue 0.5511957, saturation 0.016659725, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fefcb4p125F}.
     * <pre>
     * <font style='background-color: #545858;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #545858; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #545858;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #545858'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #545858'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #545858'>&nbsp;@&nbsp;</font><font style='background-color: #545858; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #545858;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #545858; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_GRAY = -0x1.fefcb4p125F;
    static { NAMED.put("Dark Greenish Gray", -0x1.fefcb4p125F); LIST.add(-0x1.fefcb4p125F); }

    /**
     * This color constant "Greenish Black" has RGBA8888 code {@code 212626FF}, L 0.1764706, A 0.49411765, B 0.49803922, alpha 1.0, hue 0.5511957, saturation 0.047562424, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fefc5ap125F}.
     * <pre>
     * <font style='background-color: #212626;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #212626; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #212626;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #212626'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #212626'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #212626'>&nbsp;@&nbsp;</font><font style='background-color: #212626; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #212626;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #212626; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_BLACK = -0x1.fefc5ap125F;
    static { NAMED.put("Greenish Black", -0x1.fefc5ap125F); LIST.add(-0x1.fefc5ap125F); }

    /**
     * This color constant "Vivid Bluish Green" has RGBA8888 code {@code 13FCD5FF}, L 0.81960785, A 0.41568628, B 0.5019608, alpha 1.0, hue 0.49631038, saturation 0.9342255, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.00d5a2p126F}.
     * <pre>
     * <font style='background-color: #13FCD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #13FCD5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #13FCD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #13FCD5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #13FCD5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #13FCD5'>&nbsp;@&nbsp;</font><font style='background-color: #13FCD5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #13FCD5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #13FCD5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_BLUISH_GREEN = -0x1.00d5a2p126F;
    static { NAMED.put("Vivid Bluish Green", -0x1.00d5a2p126F); LIST.add(-0x1.00d5a2p126F); }

    /**
     * This color constant "Brilliant Bluish Green" has RGBA8888 code {@code 35D7CEFF}, L 0.70980394, A 0.43529412, B 0.4862745, alpha 1.0, hue 0.5332717, saturation 0.76775175, and chroma 0.13177444.
     * It can be represented as a packed float with the constant {@code -0x1.f8df6ap125F}.
     * <pre>
     * <font style='background-color: #35D7CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #35D7CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #35D7CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #35D7CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #35D7CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #35D7CE'>&nbsp;@&nbsp;</font><font style='background-color: #35D7CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #35D7CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #35D7CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_BLUISH_GREEN = -0x1.f8df6ap125F;
    static { NAMED.put("Brilliant Bluish Green", -0x1.f8df6ap125F); LIST.add(-0x1.f8df6ap125F); }

    /**
     * This color constant "Strong Bluish Green" has RGBA8888 code {@code 0D8F82FF}, L 0.47058824, A 0.44313726, B 0.49411765, alpha 1.0, hue 0.5164136, saturation 0.9137329, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.fce2fp125F}.
     * <pre>
     * <font style='background-color: #0D8F82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D8F82; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D8F82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0D8F82'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0D8F82'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0D8F82'>&nbsp;@&nbsp;</font><font style='background-color: #0D8F82; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D8F82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D8F82; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BLUISH_GREEN = -0x1.fce2fp125F;
    static { NAMED.put("Strong Bluish Green", -0x1.fce2fp125F); LIST.add(-0x1.fce2fp125F); }

    /**
     * This color constant "Very Light Bluish Green" has RGBA8888 code {@code 98E1E0FF}, L 0.8, A 0.46666667, B 0.49019608, alpha 1.0, hue 0.545517, saturation 0.18231964, and chroma 0.069218926.
     * It can be represented as a packed float with the constant {@code -0x1.faef98p125F}.
     * <pre>
     * <font style='background-color: #98E1E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98E1E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98E1E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98E1E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98E1E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98E1E0'>&nbsp;@&nbsp;</font><font style='background-color: #98E1E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98E1E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98E1E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_BLUISH_GREEN = -0x1.faef98p125F;
    static { NAMED.put("Very Light Bluish Green", -0x1.faef98p125F); LIST.add(-0x1.faef98p125F); }

    /**
     * This color constant "Light Bluish Green" has RGBA8888 code {@code 5FABABFF}, L 0.5921569, A 0.4627451, B 0.4862745, alpha 1.0, hue 0.55616736, saturation 0.34446543, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f8ed2ep125F}.
     * <pre>
     * <font style='background-color: #5FABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5FABAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5FABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5FABAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5FABAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5FABAB'>&nbsp;@&nbsp;</font><font style='background-color: #5FABAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5FABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5FABAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUISH_GREEN = -0x1.f8ed2ep125F;
    static { NAMED.put("Light Bluish Green", -0x1.f8ed2ep125F); LIST.add(-0x1.f8ed2ep125F); }

    /**
     * This color constant "Moderate Bluish Green" has RGBA8888 code {@code 297A7BFF}, L 0.41960785, A 0.45882353, B 0.4862745, alpha 1.0, hue 0.5511957, saturation 0.6479339, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.f8ead6p125F}.
     * <pre>
     * <font style='background-color: #297A7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #297A7B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #297A7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #297A7B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #297A7B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #297A7B'>&nbsp;@&nbsp;</font><font style='background-color: #297A7B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #297A7B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #297A7B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BLUISH_GREEN = -0x1.f8ead6p125F;
    static { NAMED.put("Moderate Bluish Green", -0x1.f8ead6p125F); LIST.add(-0x1.f8ead6p125F); }

    /**
     * This color constant "Dark Bluish Green" has RGBA8888 code {@code 154B4DFF}, L 0.2784314, A 0.47058824, B 0.49019608, alpha 1.0, hue 0.5511957, saturation 0.594884, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.faf08ep125F}.
     * <pre>
     * <font style='background-color: #154B4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #154B4D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #154B4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #154B4D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #154B4D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #154B4D'>&nbsp;@&nbsp;</font><font style='background-color: #154B4D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #154B4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #154B4D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUISH_GREEN = -0x1.faf08ep125F;
    static { NAMED.put("Dark Bluish Green", -0x1.faf08ep125F); LIST.add(-0x1.faf08ep125F); }

    /**
     * This color constant "Very Dark Bluish Green" has RGBA8888 code {@code 0A2D2EFF}, L 0.18431373, A 0.4745098, B 0.49019608, alpha 1.0, hue 0.55842525, saturation 0.8074922, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.faf25ep125F}.
     * <pre>
     * <font style='background-color: #0A2D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A2D2E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A2D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0A2D2E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0A2D2E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0A2D2E'>&nbsp;@&nbsp;</font><font style='background-color: #0A2D2E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0A2D2E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0A2D2E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_BLUISH_GREEN = -0x1.faf25ep125F;
    static { NAMED.put("Very Dark Bluish Green", -0x1.faf25ep125F); LIST.add(-0x1.faf25ep125F); }

    /**
     * This color constant "Brilliant Greenish Blue" has RGBA8888 code {@code 2DBCE2FF}, L 0.6392157, A 0.44705883, B 0.45882353, alpha 1.0, hue 0.6052204, saturation 0.832, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.eae546p125F}.
     * <pre>
     * <font style='background-color: #2DBCE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2DBCE2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2DBCE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2DBCE2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2DBCE2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2DBCE2'>&nbsp;@&nbsp;</font><font style='background-color: #2DBCE2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2DBCE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2DBCE2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREENISH_BLUE = -0x1.eae546p125F;
    static { NAMED.put("Brilliant Greenish Blue", -0x1.eae546p125F); LIST.add(-0x1.eae546p125F); }

    /**
     * This color constant "Strong Greenish Blue" has RGBA8888 code {@code 1385AFFF}, L 0.46666667, A 0.45882353, B 0.45490196, alpha 1.0, hue 0.63222384, saturation 0.86433506, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.e8eaeep125F}.
     * <pre>
     * <font style='background-color: #1385AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1385AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1385AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1385AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1385AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1385AF'>&nbsp;@&nbsp;</font><font style='background-color: #1385AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1385AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1385AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREENISH_BLUE = -0x1.e8eaeep125F;
    static { NAMED.put("Strong Greenish Blue", -0x1.e8eaeep125F); LIST.add(-0x1.e8eaeep125F); }

    /**
     * This color constant "Very Light Greenish Blue" has RGBA8888 code {@code 94D6EFFF}, L 0.77254903, A 0.47058824, B 0.4745098, alpha 1.0, hue 0.61365926, saturation 0.42354205, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.f2f18ap125F}.
     * <pre>
     * <font style='background-color: #94D6EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #94D6EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #94D6EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #94D6EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #94D6EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #94D6EF'>&nbsp;@&nbsp;</font><font style='background-color: #94D6EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #94D6EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #94D6EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_GREENISH_BLUE = -0x1.f2f18ap125F;
    static { NAMED.put("Very Light Greenish Blue", -0x1.f2f18ap125F); LIST.add(-0x1.f2f18ap125F); }

    /**
     * This color constant "Light Greenish Blue" has RGBA8888 code {@code 65A8C3FF}, L 0.59607846, A 0.47058824, B 0.47058824, alpha 1.0, hue 0.625, saturation 0.3035925, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.f0f13p125F}.
     * <pre>
     * <font style='background-color: #65A8C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65A8C3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65A8C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65A8C3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65A8C3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65A8C3'>&nbsp;@&nbsp;</font><font style='background-color: #65A8C3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65A8C3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65A8C3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_BLUE = -0x1.f0f13p125F;
    static { NAMED.put("Light Greenish Blue", -0x1.f0f13p125F); LIST.add(-0x1.f0f13p125F); }

    /**
     * This color constant "Moderate Greenish Blue" has RGBA8888 code {@code 2A7691FF}, L 0.41960785, A 0.46666667, B 0.46666667, alpha 1.0, hue 0.625, saturation 0.62133837, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.eeeed6p125F}.
     * <pre>
     * <font style='background-color: #2A7691;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2A7691; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2A7691;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2A7691'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2A7691'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2A7691'>&nbsp;@&nbsp;</font><font style='background-color: #2A7691; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2A7691;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2A7691; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREENISH_BLUE = -0x1.eeeed6p125F;
    static { NAMED.put("Moderate Greenish Blue", -0x1.eeeed6p125F); LIST.add(-0x1.eeeed6p125F); }

    /**
     * This color constant "Dark Greenish Blue" has RGBA8888 code {@code 134A60FF}, L 0.28235295, A 0.4745098, B 0.47058824, alpha 1.0, hue 0.63634074, saturation 0.6563932, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.f0f29p125F}.
     * <pre>
     * <font style='background-color: #134A60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #134A60; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #134A60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #134A60'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #134A60'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #134A60'>&nbsp;@&nbsp;</font><font style='background-color: #134A60; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #134A60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #134A60; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_BLUE = -0x1.f0f29p125F;
    static { NAMED.put("Dark Greenish Blue", -0x1.f0f29p125F); LIST.add(-0x1.f0f29p125F); }

    /**
     * This color constant "Very Dark Greenish Blue" has RGBA8888 code {@code 0B2C3BFF}, L 0.1882353, A 0.48235294, B 0.47843137, alpha 1.0, hue 0.6408521, saturation 0.5902118, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.f4f66p125F}.
     * <pre>
     * <font style='background-color: #0B2C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0B2C3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0B2C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0B2C3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0B2C3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0B2C3B'>&nbsp;@&nbsp;</font><font style='background-color: #0B2C3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0B2C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0B2C3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_GREENISH_BLUE = -0x1.f4f66p125F;
    static { NAMED.put("Very Dark Greenish Blue", -0x1.f4f66p125F); LIST.add(-0x1.f4f66p125F); }

    /**
     * This color constant "Vivid Blue" has RGBA8888 code {@code 1B5CD7FF}, L 0.39607844, A 0.48235294, B 0.4, alpha 1.0, hue 0.7221893, saturation 0.5715808, and chroma 0.20229699.
     * It can be represented as a packed float with the constant {@code -0x1.ccf6cap125F}.
     * <pre>
     * <font style='background-color: #1B5CD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1B5CD7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1B5CD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1B5CD7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1B5CD7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1B5CD7'>&nbsp;@&nbsp;</font><font style='background-color: #1B5CD7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1B5CD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1B5CD7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_BLUE = -0x1.ccf6cap125F;
    static { NAMED.put("Vivid Blue", -0x1.ccf6cap125F); LIST.add(-0x1.ccf6cap125F); }

    /**
     * This color constant "Brilliant Blue" has RGBA8888 code {@code 419DEDFF}, L 0.5686275, A 0.47058824, B 0.43529412, alpha 1.0, hue 0.68210673, saturation 0.58238226, and chroma 0.1415982.
     * It can be represented as a packed float with the constant {@code -0x1.def122p125F}.
     * <pre>
     * <font style='background-color: #419DED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #419DED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #419DED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #419DED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #419DED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #419DED'>&nbsp;@&nbsp;</font><font style='background-color: #419DED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #419DED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #419DED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_BLUE = -0x1.def122p125F;
    static { NAMED.put("Brilliant Blue", -0x1.def122p125F); LIST.add(-0x1.def122p125F); }

    /**
     * This color constant "Strong Blue" has RGBA8888 code {@code 276CBDFF}, L 0.41568628, A 0.47843137, B 0.43137255, alpha 1.0, hue 0.701547, saturation 0.5277914, and chroma 0.143312.
     * It can be represented as a packed float with the constant {@code -0x1.dcf4d4p125F}.
     * <pre>
     * <font style='background-color: #276CBD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #276CBD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #276CBD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #276CBD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #276CBD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #276CBD'>&nbsp;@&nbsp;</font><font style='background-color: #276CBD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #276CBD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #276CBD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BLUE = -0x1.dcf4d4p125F;
    static { NAMED.put("Strong Blue", -0x1.dcf4d4p125F); LIST.add(-0x1.dcf4d4p125F); }

    /**
     * This color constant "Deep Blue" has RGBA8888 code {@code 113074FF}, L 0.23529412, A 0.49019608, B 0.43137255, alpha 1.0, hue 0.72740346, saturation 0.30046272, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.dcfa78p125F}.
     * <pre>
     * <font style='background-color: #113074;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #113074; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #113074;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #113074'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #113074'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #113074'>&nbsp;@&nbsp;</font><font style='background-color: #113074; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #113074;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #113074; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE = -0x1.dcfa78p125F;
    static { NAMED.put("Deep Blue", -0x1.dcfa78p125F); LIST.add(-0x1.dcfa78p125F); }

    /**
     * This color constant "Very Light Blue" has RGBA8888 code {@code 99C6F9FF}, L 0.7372549, A 0.4862745, B 0.45882353, alpha 1.0, hue 0.6988043, saturation 0.6977572, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.eaf978p125F}.
     * <pre>
     * <font style='background-color: #99C6F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99C6F9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99C6F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #99C6F9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #99C6F9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #99C6F9'>&nbsp;@&nbsp;</font><font style='background-color: #99C6F9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99C6F9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99C6F9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_BLUE = -0x1.eaf978p125F;
    static { NAMED.put("Very Light Blue", -0x1.eaf978p125F); LIST.add(-0x1.eaf978p125F); }

    /**
     * This color constant "Light Blue" has RGBA8888 code {@code 73A4DCFF}, L 0.60784316, A 0.48235294, B 0.45490196, alpha 1.0, hue 0.69064915, saturation 0.35418785, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.e8f736p125F}.
     * <pre>
     * <font style='background-color: #73A4DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73A4DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73A4DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73A4DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73A4DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73A4DC'>&nbsp;@&nbsp;</font><font style='background-color: #73A4DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73A4DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73A4DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUE = -0x1.e8f736p125F;
    static { NAMED.put("Light Blue", -0x1.e8f736p125F); LIST.add(-0x1.e8f736p125F); }

    /**
     * This color constant "Moderate Blue" has RGBA8888 code {@code 34689EFF}, L 0.39607844, A 0.47843137, B 0.4509804, alpha 1.0, hue 0.6840373, saturation 0.4331543, and chroma 0.106691405.
     * It can be represented as a packed float with the constant {@code -0x1.e6f4cap125F}.
     * <pre>
     * <font style='background-color: #34689E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #34689E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #34689E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #34689E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #34689E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #34689E'>&nbsp;@&nbsp;</font><font style='background-color: #34689E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #34689E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #34689E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BLUE = -0x1.e6f4cap125F;
    static { NAMED.put("Moderate Blue", -0x1.e6f4cap125F); LIST.add(-0x1.e6f4cap125F); }

    /**
     * This color constant "Dark Blue" has RGBA8888 code {@code 173459FF}, L 0.22745098, A 0.4862745, B 0.45882353, alpha 1.0, hue 0.6988043, saturation 0.49382716, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.eaf874p125F}.
     * <pre>
     * <font style='background-color: #173459;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #173459; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #173459;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #173459'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #173459'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #173459'>&nbsp;@&nbsp;</font><font style='background-color: #173459; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #173459;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #173459; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUE = -0x1.eaf874p125F;
    static { NAMED.put("Dark Blue", -0x1.eaf874p125F); LIST.add(-0x1.eaf874p125F); }

    /**
     * This color constant "Very Pale Blue" has RGBA8888 code {@code C2D2ECFF}, L 0.8, A 0.49411765, B 0.47843137, alpha 1.0, hue 0.70763123, saturation 0.34188035, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.f4fd98p125F}.
     * <pre>
     * <font style='background-color: #C2D2EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2D2EC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2D2EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C2D2EC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C2D2EC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C2D2EC'>&nbsp;@&nbsp;</font><font style='background-color: #C2D2EC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2D2EC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2D2EC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_BLUE = -0x1.f4fd98p125F;
    static { NAMED.put("Very Pale Blue", -0x1.f4fd98p125F); LIST.add(-0x1.f4fd98p125F); }

    /**
     * This color constant "Pale Blue" has RGBA8888 code {@code 91A2BBFF}, L 0.6117647, A 0.49411765, B 0.47843137, alpha 1.0, hue 0.70763123, saturation 0.083319984, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.f4fd38p125F}.
     * <pre>
     * <font style='background-color: #91A2BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91A2BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91A2BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #91A2BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #91A2BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #91A2BB'>&nbsp;@&nbsp;</font><font style='background-color: #91A2BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91A2BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91A2BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.f4fd38p125F;
    static { NAMED.put("Pale Blue", -0x1.f4fd38p125F); LIST.add(-0x1.f4fd38p125F); }

    /**
     * This color constant "Grayish Blue" has RGBA8888 code {@code 54687FFF}, L 0.4, A 0.49019608, B 0.47843137, alpha 1.0, hue 0.68210673, saturation 0.08901082, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.f4faccp125F}.
     * <pre>
     * <font style='background-color: #54687F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54687F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54687F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #54687F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #54687F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #54687F'>&nbsp;@&nbsp;</font><font style='background-color: #54687F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54687F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54687F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_BLUE = -0x1.f4faccp125F;
    static { NAMED.put("Grayish Blue", -0x1.f4faccp125F); LIST.add(-0x1.f4faccp125F); }

    /**
     * This color constant "Dark Grayish Blue" has RGBA8888 code {@code 323F4EFF}, L 0.2627451, A 0.49411765, B 0.48235294, alpha 1.0, hue 0.6988043, saturation 0.07561437, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.f6fc86p125F}.
     * <pre>
     * <font style='background-color: #323F4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #323F4E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #323F4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #323F4E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #323F4E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #323F4E'>&nbsp;@&nbsp;</font><font style='background-color: #323F4E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #323F4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #323F4E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_BLUE = -0x1.f6fc86p125F;
    static { NAMED.put("Dark Grayish Blue", -0x1.f6fc86p125F); LIST.add(-0x1.f6fc86p125F); }

    /**
     * This color constant "Blackish Blue" has RGBA8888 code {@code 1E2531FF}, L 0.1764706, A 0.49411765, B 0.48235294, alpha 1.0, hue 0.6988043, saturation 0.1384083, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.f6fc5ap125F}.
     * <pre>
     * <font style='background-color: #1E2531;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E2531; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E2531;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1E2531'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1E2531'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1E2531'>&nbsp;@&nbsp;</font><font style='background-color: #1E2531; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E2531;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E2531; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_BLUE = -0x1.f6fc5ap125F;
    static { NAMED.put("Blackish Blue", -0x1.f6fc5ap125F); LIST.add(-0x1.f6fc5ap125F); }

    /**
     * This color constant "Bluish White" has RGBA8888 code {@code E1E1F1FF}, L 0.8745098, A 0.5019608, B 0.49019608, alpha 1.0, hue 0.78142345, saturation 0.14266118, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.fb01bep125F}.
     * <pre>
     * <font style='background-color: #E1E1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1E1F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1E1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1E1F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1E1F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1E1F1'>&nbsp;@&nbsp;</font><font style='background-color: #E1E1F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1E1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1E1F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_WHITE = -0x1.fb01bep125F;
    static { NAMED.put("Bluish White", -0x1.fb01bep125F); LIST.add(-0x1.fb01bep125F); }

    /**
     * This color constant "Light Bluish Gray" has RGBA8888 code {@code B7B8C6FF}, L 0.7058824, A 0.5019608, B 0.49019608, alpha 1.0, hue 0.78142345, saturation 0.029876472, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.fb0168p125F}.
     * <pre>
     * <font style='background-color: #B7B8C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7B8C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7B8C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B7B8C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B7B8C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B7B8C6'>&nbsp;@&nbsp;</font><font style='background-color: #B7B8C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7B8C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7B8C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUISH_GRAY = -0x1.fb0168p125F;
    static { NAMED.put("Light Bluish Gray", -0x1.fb0168p125F); LIST.add(-0x1.fb0168p125F); }

    /**
     * This color constant "Bluish Gray" has RGBA8888 code {@code 838793FF}, L 0.5176471, A 0.49803922, B 0.49019608, alpha 1.0, hue 0.71857655, saturation 0.009802997, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.faff08p125F}.
     * <pre>
     * <font style='background-color: #838793;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #838793; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #838793;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #838793'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #838793'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #838793'>&nbsp;@&nbsp;</font><font style='background-color: #838793; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #838793;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #838793; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_GRAY = -0x1.faff08p125F;
    static { NAMED.put("Bluish Gray", -0x1.faff08p125F); LIST.add(-0x1.faff08p125F); }

    /**
     * This color constant "Dark Bluish Gray" has RGBA8888 code {@code 50545FFF}, L 0.34117648, A 0.49803922, B 0.49019608, alpha 1.0, hue 0.71857655, saturation 0.008144725, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.fafeaep125F}.
     * <pre>
     * <font style='background-color: #50545F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50545F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50545F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #50545F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #50545F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #50545F'>&nbsp;@&nbsp;</font><font style='background-color: #50545F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50545F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50545F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUISH_GRAY = -0x1.fafeaep125F;
    static { NAMED.put("Dark Bluish Gray", -0x1.fafeaep125F); LIST.add(-0x1.fafeaep125F); }

    /**
     * This color constant "Bluish Black" has RGBA8888 code {@code 24272EFF}, L 0.18431373, A 0.49803922, B 0.49019608, alpha 1.0, hue 0.71857655, saturation 0.020630827, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.fafe5ep125F}.
     * <pre>
     * <font style='background-color: #24272E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #24272E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #24272E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #24272E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #24272E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #24272E'>&nbsp;@&nbsp;</font><font style='background-color: #24272E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #24272E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #24272E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_BLACK = -0x1.fafe5ep125F;
    static { NAMED.put("Bluish Black", -0x1.fafe5ep125F); LIST.add(-0x1.fafe5ep125F); }

    /**
     * This color constant "Vivid Purplish Blue" has RGBA8888 code {@code 4436D1FF}, L 0.34509805, A 0.5137255, B 0.3882353, alpha 1.0, hue 0.7694595, saturation 0.5351941, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.c706bp125F}.
     * <pre>
     * <font style='background-color: #4436D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4436D1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4436D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4436D1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4436D1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4436D1'>&nbsp;@&nbsp;</font><font style='background-color: #4436D1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4436D1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4436D1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLISH_BLUE = -0x1.c706bp125F;
    static { NAMED.put("Vivid Purplish Blue", -0x1.c706bp125F); LIST.add(-0x1.c706bp125F); }

    /**
     * This color constant "Brilliant Purplish Blue" has RGBA8888 code {@code 8088E2FF}, L 0.5529412, A 0.5058824, B 0.43529412, alpha 1.0, hue 0.764433, saturation 0.5078044, and chroma 0.12943782.
     * It can be represented as a packed float with the constant {@code -0x1.df031ap125F}.
     * <pre>
     * <font style='background-color: #8088E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8088E2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8088E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8088E2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8088E2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8088E2'>&nbsp;@&nbsp;</font><font style='background-color: #8088E2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8088E2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8088E2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLISH_BLUE = -0x1.df031ap125F;
    static { NAMED.put("Brilliant Purplish Blue", -0x1.df031ap125F); LIST.add(-0x1.df031ap125F); }

    /**
     * This color constant "Strong Purplish Blue" has RGBA8888 code {@code 5359B5FF}, L 0.39607844, A 0.5058824, B 0.42745098, alpha 1.0, hue 0.76287687, saturation 0.27724963, and chroma 0.14500555.
     * It can be represented as a packed float with the constant {@code -0x1.db02cap125F}.
     * <pre>
     * <font style='background-color: #5359B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5359B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5359B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5359B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5359B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5359B5'>&nbsp;@&nbsp;</font><font style='background-color: #5359B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5359B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5359B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_BLUE = -0x1.db02cap125F;
    static { NAMED.put("Strong Purplish Blue", -0x1.db02cap125F); LIST.add(-0x1.db02cap125F); }

    /**
     * This color constant "Deep Purplish Blue" has RGBA8888 code {@code 2A286FFF}, L 0.22745098, A 0.5058824, B 0.43529412, alpha 1.0, hue 0.764433, saturation 0.3320983, and chroma 0.12943782.
     * It can be represented as a packed float with the constant {@code -0x1.df0274p125F}.
     * <pre>
     * <font style='background-color: #2A286F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2A286F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2A286F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2A286F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2A286F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2A286F'>&nbsp;@&nbsp;</font><font style='background-color: #2A286F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2A286F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2A286F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_BLUE = -0x1.df0274p125F;
    static { NAMED.put("Deep Purplish Blue", -0x1.df0274p125F); LIST.add(-0x1.df0274p125F); }

    /**
     * This color constant "Very Light Purplish Blue" has RGBA8888 code {@code B7C0F8FF}, L 0.74509805, A 0.5019608, B 0.4627451, alpha 1.0, hue 0.7583591, saturation 0.60308206, and chroma 0.07432148.
     * It can be represented as a packed float with the constant {@code -0x1.ed017cp125F}.
     * <pre>
     * <font style='background-color: #B7C0F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7C0F8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7C0F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B7C0F8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B7C0F8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B7C0F8'>&nbsp;@&nbsp;</font><font style='background-color: #B7C0F8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B7C0F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B7C0F8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_PURPLISH_BLUE = -0x1.ed017cp125F;
    static { NAMED.put("Very Light Purplish Blue", -0x1.ed017cp125F); LIST.add(-0x1.ed017cp125F); }

    /**
     * This color constant "Light Purplish Blue" has RGBA8888 code {@code 8991CBFF}, L 0.5686275, A 0.5019608, B 0.45882353, alpha 1.0, hue 0.7575619, saturation 0.22320414, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.eb0122p125F}.
     * <pre>
     * <font style='background-color: #8991CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8991CB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8991CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8991CB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8991CB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8991CB'>&nbsp;@&nbsp;</font><font style='background-color: #8991CB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8991CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8991CB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_BLUE = -0x1.eb0122p125F;
    static { NAMED.put("Light Purplish Blue", -0x1.eb0122p125F); LIST.add(-0x1.eb0122p125F); }

    /**
     * This color constant "Moderate Purplish Blue" has RGBA8888 code {@code 4D4E87FF}, L 0.34509805, A 0.5058824, B 0.4509804, alpha 1.0, hue 0.76901877, saturation 0.1028845, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.e702bp125F}.
     * <pre>
     * <font style='background-color: #4D4E87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4D4E87; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4D4E87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4D4E87'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4D4E87'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4D4E87'>&nbsp;@&nbsp;</font><font style='background-color: #4D4E87; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4D4E87;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4D4E87; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_BLUE = -0x1.e702bp125F;
    static { NAMED.put("Moderate Purplish Blue", -0x1.e702bp125F); LIST.add(-0x1.e702bp125F); }

    /**
     * This color constant "Dark Purplish Blue" has RGBA8888 code {@code 222248FF}, L 0.18431373, A 0.5058824, B 0.45882353, alpha 1.0, hue 0.77259654, saturation 0.19944598, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.eb025ep125F}.
     * <pre>
     * <font style='background-color: #222248;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #222248; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #222248;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #222248'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #222248'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #222248'>&nbsp;@&nbsp;</font><font style='background-color: #222248; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #222248;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #222248; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_BLUE = -0x1.eb025ep125F;
    static { NAMED.put("Dark Purplish Blue", -0x1.eb025ep125F); LIST.add(-0x1.eb025ep125F); }

    /**
     * This color constant "Very Pale Purplish Blue" has RGBA8888 code {@code C5C9F0FF}, L 0.78039217, A 0.5019608, B 0.4745098, alpha 1.0, hue 0.76221764, saturation 0.36776635, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.f3018ep125F}.
     * <pre>
     * <font style='background-color: #C5C9F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5C9F0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5C9F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C5C9F0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C5C9F0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C5C9F0'>&nbsp;@&nbsp;</font><font style='background-color: #C5C9F0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C5C9F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C5C9F0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_PURPLISH_BLUE = -0x1.f3018ep125F;
    static { NAMED.put("Very Pale Purplish Blue", -0x1.f3018ep125F); LIST.add(-0x1.f3018ep125F); }

    /**
     * This color constant "Pale Purplish Blue" has RGBA8888 code {@code 8E92B7FF}, L 0.5686275, A 0.5019608, B 0.4745098, alpha 1.0, hue 0.76221764, saturation 0.08584774, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.f30122p125F}.
     * <pre>
     * <font style='background-color: #8E92B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E92B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E92B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E92B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E92B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E92B7'>&nbsp;@&nbsp;</font><font style='background-color: #8E92B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E92B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E92B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLISH_BLUE = -0x1.f30122p125F;
    static { NAMED.put("Pale Purplish Blue", -0x1.f30122p125F); LIST.add(-0x1.f30122p125F); }

    /**
     * This color constant "Grayish Purplish Blue" has RGBA8888 code {@code 494D71FF}, L 0.32941177, A 0.5019608, B 0.46666667, alpha 1.0, hue 0.75934356, saturation 0.048283037, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.ef00a8p125F}.
     * <pre>
     * <font style='background-color: #494D71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494D71; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494D71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #494D71'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #494D71'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #494D71'>&nbsp;@&nbsp;</font><font style='background-color: #494D71; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494D71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494D71; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_BLUE = -0x1.ef00a8p125F;
    static { NAMED.put("Grayish Purplish Blue", -0x1.ef00a8p125F); LIST.add(-0x1.ef00a8p125F); }

    /**
     * This color constant "Vivid Violet" has RGBA8888 code {@code 7931D3FF}, L 0.3882353, A 0.5568628, B 0.4, alpha 1.0, hue 0.832294, saturation 0.62015224, and chroma 0.22917406.
     * It can be represented as a packed float with the constant {@code -0x1.cd1cc6p125F}.
     * <pre>
     * <font style='background-color: #7931D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7931D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7931D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7931D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7931D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7931D3'>&nbsp;@&nbsp;</font><font style='background-color: #7931D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7931D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7931D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_VIOLET = -0x1.cd1cc6p125F;
    static { NAMED.put("Vivid Violet", -0x1.cd1cc6p125F); LIST.add(-0x1.cd1cc6p125F); }

    /**
     * This color constant "Brilliant Violet" has RGBA8888 code {@code 987FDCFF}, L 0.5529412, A 0.5254902, B 0.4392157, alpha 1.0, hue 0.8131871, saturation 0.40997732, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.e10d1ap125F}.
     * <pre>
     * <font style='background-color: #987FDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #987FDC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #987FDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #987FDC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #987FDC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #987FDC'>&nbsp;@&nbsp;</font><font style='background-color: #987FDC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #987FDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #987FDC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_VIOLET = -0x1.e10d1ap125F;
    static { NAMED.put("Brilliant Violet", -0x1.e10d1ap125F); LIST.add(-0x1.e10d1ap125F); }

    /**
     * This color constant "Strong Violet" has RGBA8888 code {@code 61419CFF}, L 0.34901962, A 0.53333336, B 0.43529412, alpha 1.0, hue 0.82570946, saturation 0.28528544, and chroma 0.14500555.
     * It can be represented as a packed float with the constant {@code -0x1.df10b2p125F}.
     * <pre>
     * <font style='background-color: #61419C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61419C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61419C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61419C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61419C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61419C'>&nbsp;@&nbsp;</font><font style='background-color: #61419C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61419C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61419C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_VIOLET = -0x1.df10b2p125F;
    static { NAMED.put("Strong Violet", -0x1.df10b2p125F); LIST.add(-0x1.df10b2p125F); }

    /**
     * This color constant "Deep Violet" has RGBA8888 code {@code 3C1668FF}, L 0.21568628, A 0.5372549, B 0.43529412, alpha 1.0, hue 0.8331495, saturation 0.67059773, and chroma 0.14874557.
     * It can be represented as a packed float with the constant {@code -0x1.df126ep125F}.
     * <pre>
     * <font style='background-color: #3C1668;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C1668; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C1668;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C1668'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C1668'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C1668'>&nbsp;@&nbsp;</font><font style='background-color: #3C1668; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C1668;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C1668; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET = -0x1.df126ep125F;
    static { NAMED.put("Deep Violet", -0x1.df126ep125F); LIST.add(-0x1.df126ep125F); }

    /**
     * This color constant "Very Light Violet" has RGBA8888 code {@code C9BAF8FF}, L 0.7490196, A 0.5137255, B 0.4627451, alpha 1.0, hue 0.80616736, saturation 0.5838377, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.ed077ep125F}.
     * <pre>
     * <font style='background-color: #C9BAF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9BAF8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9BAF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9BAF8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9BAF8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9BAF8'>&nbsp;@&nbsp;</font><font style='background-color: #C9BAF8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9BAF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9BAF8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_VIOLET = -0x1.ed077ep125F;
    static { NAMED.put("Very Light Violet", -0x1.ed077ep125F); LIST.add(-0x1.ed077ep125F); }

    /**
     * This color constant "Light Violet" has RGBA8888 code {@code 9B8CCAFF}, L 0.5764706, A 0.5176471, B 0.45882353, alpha 1.0, hue 0.8144313, saturation 0.22191519, and chroma 0.08924734.
     * It can be represented as a packed float with the constant {@code -0x1.eb0926p125F}.
     * <pre>
     * <font style='background-color: #9B8CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B8CCA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B8CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B8CCA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B8CCA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B8CCA'>&nbsp;@&nbsp;</font><font style='background-color: #9B8CCA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B8CCA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B8CCA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_VIOLET = -0x1.eb0926p125F;
    static { NAMED.put("Light Violet", -0x1.eb0926p125F); LIST.add(-0x1.eb0926p125F); }

    /**
     * This color constant "Moderate Violet" has RGBA8888 code {@code 5C4985FF}, L 0.34509805, A 0.52156866, B 0.45490196, alpha 1.0, hue 0.8209959, saturation 0.1345686, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.e90abp125F}.
     * <pre>
     * <font style='background-color: #5C4985;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C4985; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C4985;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C4985'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C4985'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C4985'>&nbsp;@&nbsp;</font><font style='background-color: #5C4985; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C4985;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C4985; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_VIOLET = -0x1.e90abp125F;
    static { NAMED.put("Moderate Violet", -0x1.e90abp125F); LIST.add(-0x1.e90abp125F); }

    /**
     * This color constant "Dark Violet" has RGBA8888 code {@code 34254DFF}, L 0.21176471, A 0.5176471, B 0.4627451, alpha 1.0, hue 0.8204015, saturation 0.19590028, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.ed086cp125F}.
     * <pre>
     * <font style='background-color: #34254D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #34254D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #34254D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #34254D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #34254D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #34254D'>&nbsp;@&nbsp;</font><font style='background-color: #34254D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #34254D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #34254D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_VIOLET = -0x1.ed086cp125F;
    static { NAMED.put("Dark Violet", -0x1.ed086cp125F); LIST.add(-0x1.ed086cp125F); }

    /**
     * This color constant "Very Pale Violet" has RGBA8888 code {@code D0C6EFFF}, L 0.78431374, A 0.50980395, B 0.4745098, alpha 1.0, hue 0.80842525, saturation 0.38320988, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.f3059p125F}.
     * <pre>
     * <font style='background-color: #D0C6EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0C6EF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0C6EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0C6EF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0C6EF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0C6EF'>&nbsp;@&nbsp;</font><font style='background-color: #D0C6EF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0C6EF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0C6EF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_VIOLET = -0x1.f3059p125F;
    static { NAMED.put("Very Pale Violet", -0x1.f3059p125F); LIST.add(-0x1.f3059p125F); }

    /**
     * This color constant "Pale Violet" has RGBA8888 code {@code 9A90B5FF}, L 0.57254905, A 0.50980395, B 0.4745098, alpha 1.0, hue 0.80842525, saturation 0.08598338, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.f30524p125F}.
     * <pre>
     * <font style='background-color: #9A90B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A90B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A90B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A90B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A90B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A90B5'>&nbsp;@&nbsp;</font><font style='background-color: #9A90B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A90B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A90B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.f30524p125F;
    static { NAMED.put("Pale Violet", -0x1.f30524p125F); LIST.add(-0x1.f30524p125F); }

    /**
     * This color constant "Grayish Violet" has RGBA8888 code {@code 584E72FF}, L 0.34117648, A 0.5137255, B 0.47058824, alpha 1.0, hue 0.819486, saturation 0.05672584, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.f106aep125F}.
     * <pre>
     * <font style='background-color: #584E72;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #584E72; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #584E72;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #584E72'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #584E72'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #584E72'>&nbsp;@&nbsp;</font><font style='background-color: #584E72; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #584E72;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #584E72; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_VIOLET = -0x1.f106aep125F;
    static { NAMED.put("Grayish Violet", -0x1.f106aep125F); LIST.add(-0x1.f106aep125F); }

    /**
     * This color constant "Vivid Purple" has RGBA8888 code {@code B935D5FF}, L 0.47058824, A 0.5921569, B 0.41960785, alpha 1.0, hue 0.88582677, saturation 0.6476587, and chroma 0.24363229.
     * It can be represented as a packed float with the constant {@code -0x1.d72efp125F}.
     * <pre>
     * <font style='background-color: #B935D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B935D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B935D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B935D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B935D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B935D5'>&nbsp;@&nbsp;</font><font style='background-color: #B935D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B935D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B935D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLE = -0x1.d72efp125F;
    static { NAMED.put("Vivid Purple", -0x1.d72efp125F); LIST.add(-0x1.d72efp125F); }

    /**
     * This color constant "Brilliant Purple" has RGBA8888 code {@code CE8CE3FF}, L 0.6392157, A 0.54509807, B 0.45490196, alpha 1.0, hue 0.875, saturation 0.35619897, and chroma 0.12705825.
     * It can be represented as a packed float with the constant {@code -0x1.e91746p125F}.
     * <pre>
     * <font style='background-color: #CE8CE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8CE3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8CE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE8CE3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE8CE3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE8CE3'>&nbsp;@&nbsp;</font><font style='background-color: #CE8CE3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8CE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8CE3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLE = -0x1.e91746p125F;
    static { NAMED.put("Brilliant Purple", -0x1.e91746p125F); LIST.add(-0x1.e91746p125F); }

    /**
     * This color constant "Strong Purple" has RGBA8888 code {@code 9352A8FF}, L 0.43529412, A 0.5529412, B 0.4509804, alpha 1.0, hue 0.88111365, saturation 0.24395297, and chroma 0.14373726.
     * It can be represented as a packed float with the constant {@code -0x1.e71adep125F}.
     * <pre>
     * <font style='background-color: #9352A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9352A8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9352A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9352A8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9352A8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9352A8'>&nbsp;@&nbsp;</font><font style='background-color: #9352A8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9352A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9352A8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLE = -0x1.e71adep125F;
    static { NAMED.put("Strong Purple", -0x1.e71adep125F); LIST.add(-0x1.e71adep125F); }

    /**
     * This color constant "Deep Purple" has RGBA8888 code {@code 652277FF}, L 0.28627452, A 0.5568628, B 0.44705883, alpha 1.0, hue 0.8806773, saturation 0.5485195, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.e51c92p125F}.
     * <pre>
     * <font style='background-color: #652277;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #652277; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #652277;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #652277'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #652277'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #652277'>&nbsp;@&nbsp;</font><font style='background-color: #652277; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #652277;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #652277; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE = -0x1.e51c92p125F;
    static { NAMED.put("Deep Purple", -0x1.e51c92p125F); LIST.add(-0x1.e51c92p125F); }

    /**
     * This color constant "Very Deep Purple" has RGBA8888 code {@code 460A55FF}, L 0.20392157, A 0.5529412, B 0.4509804, alpha 1.0, hue 0.88111365, saturation 0.78618085, and chroma 0.14373726.
     * It can be represented as a packed float with the constant {@code -0x1.e71a68p125F}.
     * <pre>
     * <font style='background-color: #460A55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #460A55; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #460A55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #460A55'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #460A55'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #460A55'>&nbsp;@&nbsp;</font><font style='background-color: #460A55; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #460A55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #460A55; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_PURPLE = -0x1.e71a68p125F;
    static { NAMED.put("Very Deep Purple", -0x1.e71a68p125F); LIST.add(-0x1.e71a68p125F); }

    /**
     * This color constant "Very Light Purple" has RGBA8888 code {@code E4B9F3FF}, L 0.77254903, A 0.5294118, B 0.47058824, alpha 1.0, hue 0.875, saturation 0.4260355, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.f10f8ap125F}.
     * <pre>
     * <font style='background-color: #E4B9F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4B9F3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4B9F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E4B9F3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E4B9F3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E4B9F3'>&nbsp;@&nbsp;</font><font style='background-color: #E4B9F3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4B9F3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4B9F3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_PURPLE = -0x1.f10f8ap125F;
    static { NAMED.put("Very Light Purple", -0x1.f10f8ap125F); LIST.add(-0x1.f10f8ap125F); }

    /**
     * This color constant "Light Purple" has RGBA8888 code {@code BC93CCFF}, L 0.62352943, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.8650731, saturation 0.18648526, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0f3ep125F}.
     * <pre>
     * <font style='background-color: #BC93CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC93CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC93CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BC93CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BC93CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BC93CC'>&nbsp;@&nbsp;</font><font style='background-color: #BC93CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC93CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC93CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLE = -0x1.ef0f3ep125F;
    static { NAMED.put("Light Purple", -0x1.ef0f3ep125F); LIST.add(-0x1.ef0f3ep125F); }

    /**
     * This color constant "Moderate Purple" has RGBA8888 code {@code 875E96FF}, L 0.43529412, A 0.53333336, B 0.46666667, alpha 1.0, hue 0.875, saturation 0.104139455, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.ef10dep125F}.
     * <pre>
     * <font style='background-color: #875E96;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #875E96; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #875E96;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #875E96'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #875E96'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #875E96'>&nbsp;@&nbsp;</font><font style='background-color: #875E96; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #875E96;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #875E96; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLE = -0x1.ef10dep125F;
    static { NAMED.put("Moderate Purple", -0x1.ef10dep125F); LIST.add(-0x1.ef10dep125F); }

    /**
     * This color constant "Dark Purple" has RGBA8888 code {@code 563762FF}, L 0.2901961, A 0.5294118, B 0.47058824, alpha 1.0, hue 0.875, saturation 0.1515024, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.f10e94p125F}.
     * <pre>
     * <font style='background-color: #563762;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #563762; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #563762;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #563762'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #563762'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #563762'>&nbsp;@&nbsp;</font><font style='background-color: #563762; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #563762;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #563762; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLE = -0x1.f10e94p125F;
    static { NAMED.put("Dark Purple", -0x1.f10e94p125F); LIST.add(-0x1.f10e94p125F); }

    /**
     * This color constant "Very Dark Purple" has RGBA8888 code {@code 371B41FF}, L 0.1882353, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.8650731, saturation 0.3294344, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0e6p125F}.
     * <pre>
     * <font style='background-color: #371B41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #371B41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #371B41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #371B41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #371B41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #371B41'>&nbsp;@&nbsp;</font><font style='background-color: #371B41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #371B41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #371B41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_PURPLE = -0x1.ef0e6p125F;
    static { NAMED.put("Very Dark Purple", -0x1.ef0e6p125F); LIST.add(-0x1.ef0e6p125F); }

    /**
     * This color constant "Very Pale Purple" has RGBA8888 code {@code E0CBEBFF}, L 0.8117647, A 0.5137255, B 0.48235294, alpha 1.0, hue 0.8552204, saturation 0.23540063, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.f7079ep125F}.
     * <pre>
     * <font style='background-color: #E0CBEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0CBEB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0CBEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0CBEB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0CBEB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0CBEB'>&nbsp;@&nbsp;</font><font style='background-color: #E0CBEB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0CBEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0CBEB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_PURPLE = -0x1.f7079ep125F;
    static { NAMED.put("Very Pale Purple", -0x1.f7079ep125F); LIST.add(-0x1.f7079ep125F); }

    /**
     * This color constant "Pale Purple" has RGBA8888 code {@code AD97B3FF}, L 0.60784316, A 0.5137255, B 0.48235294, alpha 1.0, hue 0.8552204, saturation 0.04716553, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.f70736p125F}.
     * <pre>
     * <font style='background-color: #AD97B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD97B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD97B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD97B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD97B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD97B3'>&nbsp;@&nbsp;</font><font style='background-color: #AD97B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD97B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD97B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.f70736p125F;
    static { NAMED.put("Pale Purple", -0x1.f70736p125F); LIST.add(-0x1.f70736p125F); }

    /**
     * This color constant "Grayish Purple" has RGBA8888 code {@code 7B667EFF}, L 0.43137255, A 0.5137255, B 0.4862745, alpha 1.0, hue 0.875, saturation 0.017656863, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.f906dcp125F}.
     * <pre>
     * <font style='background-color: #7B667E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B667E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B667E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7B667E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7B667E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7B667E'>&nbsp;@&nbsp;</font><font style='background-color: #7B667E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7B667E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7B667E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLE = -0x1.f906dcp125F;
    static { NAMED.put("Grayish Purple", -0x1.f906dcp125F); LIST.add(-0x1.f906dcp125F); }

    /**
     * This color constant "Dark Grayish Purple" has RGBA8888 code {@code 513F51FF}, L 0.29411766, A 0.5137255, B 0.4862745, alpha 1.0, hue 0.875, saturation 0.0318156, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.f90696p125F}.
     * <pre>
     * <font style='background-color: #513F51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #513F51; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #513F51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #513F51'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #513F51'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #513F51'>&nbsp;@&nbsp;</font><font style='background-color: #513F51; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #513F51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #513F51; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_PURPLE = -0x1.f90696p125F;
    static { NAMED.put("Dark Grayish Purple", -0x1.f90696p125F); LIST.add(-0x1.f90696p125F); }

    /**
     * This color constant "Blackish Purple" has RGBA8888 code {@code 2F2231FF}, L 0.18431373, A 0.5137255, B 0.4862745, alpha 1.0, hue 0.875, saturation 0.0661157, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.f9065ep125F}.
     * <pre>
     * <font style='background-color: #2F2231;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F2231; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F2231;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F2231'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F2231'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F2231'>&nbsp;@&nbsp;</font><font style='background-color: #2F2231; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F2231;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F2231; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_PURPLE = -0x1.f9065ep125F;
    static { NAMED.put("Blackish Purple", -0x1.f9065ep125F); LIST.add(-0x1.f9065ep125F); }

    /**
     * This color constant "Purplish White" has RGBA8888 code {@code EBDFEFFF}, L 0.8784314, A 0.5058824, B 0.49019608, alpha 1.0, hue 0.83601886, saturation 0.16171224, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fb03cp125F}.
     * <pre>
     * <font style='background-color: #EBDFEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBDFEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBDFEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBDFEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBDFEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBDFEF'>&nbsp;@&nbsp;</font><font style='background-color: #EBDFEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBDFEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBDFEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_WHITE = -0x1.fb03cp125F;
    static { NAMED.put("Purplish White", -0x1.fb03cp125F); LIST.add(-0x1.fb03cp125F); }

    /**
     * This color constant "Light Purplish Gray" has RGBA8888 code {@code C3B7C6FF}, L 0.7137255, A 0.5058824, B 0.49019608, alpha 1.0, hue 0.83601886, saturation 0.03029628, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fb036cp125F}.
     * <pre>
     * <font style='background-color: #C3B7C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3B7C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3B7C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3B7C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3B7C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3B7C6'>&nbsp;@&nbsp;</font><font style='background-color: #C3B7C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3B7C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3B7C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_GRAY = -0x1.fb036cp125F;
    static { NAMED.put("Light Purplish Gray", -0x1.fb036cp125F); LIST.add(-0x1.fb036cp125F); }

    /**
     * This color constant "Purplish Gray" has RGBA8888 code {@code 8F8490FF}, L 0.52156866, A 0.5058824, B 0.49019608, alpha 1.0, hue 0.83601886, saturation 0.008704, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fb030ap125F}.
     * <pre>
     * <font style='background-color: #8F8490;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8490; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8490;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8490'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8490'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8490'>&nbsp;@&nbsp;</font><font style='background-color: #8F8490; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8490;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8490; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_GRAY = -0x1.fb030ap125F;
    static { NAMED.put("Purplish Gray", -0x1.fb030ap125F); LIST.add(-0x1.fb030ap125F); }

    /**
     * This color constant "Dark Purplish Gray" has RGBA8888 code {@code 5C525EFF}, L 0.34901962, A 0.5058824, B 0.49019608, alpha 1.0, hue 0.83601886, saturation 0.007245991, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fb02b2p125F}.
     * <pre>
     * <font style='background-color: #5C525E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C525E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C525E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C525E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C525E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C525E'>&nbsp;@&nbsp;</font><font style='background-color: #5C525E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C525E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C525E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_GRAY = -0x1.fb02b2p125F;
    static { NAMED.put("Dark Purplish Gray", -0x1.fb02b2p125F); LIST.add(-0x1.fb02b2p125F); }

    /**
     * This color constant "Purplish Black" has RGBA8888 code {@code 2B2630FF}, L 0.1882353, A 0.5058824, B 0.49019608, alpha 1.0, hue 0.83601886, saturation 0.019741617, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fb026p125F}.
     * <pre>
     * <font style='background-color: #2B2630;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B2630; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B2630;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2B2630'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2B2630'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2B2630'>&nbsp;@&nbsp;</font><font style='background-color: #2B2630; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B2630;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B2630; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_BLACK = -0x1.fb026p125F;
    static { NAMED.put("Purplish Black", -0x1.fb026p125F); LIST.add(-0x1.fb026p125F); }

    /**
     * This color constant "Vivid Reddish Purple" has RGBA8888 code {@code D429B9FF}, L 0.48235294, A 0.6117647, B 0.44705883, alpha 1.0, hue 0.9295985, saturation 0.73635983, and chroma 0.24637261.
     * It can be represented as a packed float with the constant {@code -0x1.e538f6p125F}.
     * <pre>
     * <font style='background-color: #D429B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D429B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D429B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D429B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D429B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D429B9'>&nbsp;@&nbsp;</font><font style='background-color: #D429B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D429B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D429B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_REDDISH_PURPLE = -0x1.e538f6p125F;
    static { NAMED.put("Vivid Reddish Purple", -0x1.e538f6p125F); LIST.add(-0x1.e538f6p125F); }

    /**
     * This color constant "Strong Reddish Purple" has RGBA8888 code {@code A74994FF}, L 0.4392157, A 0.5686275, B 0.46666667, alpha 1.0, hue 0.9280406, saturation 0.3226597, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.ef22ep125F}.
     * <pre>
     * <font style='background-color: #A74994;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A74994; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A74994;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A74994'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A74994'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A74994'>&nbsp;@&nbsp;</font><font style='background-color: #A74994; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A74994;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A74994; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_PURPLE = -0x1.ef22ep125F;
    static { NAMED.put("Strong Reddish Purple", -0x1.ef22ep125F); LIST.add(-0x1.ef22ep125F); }

    /**
     * This color constant "Deep Reddish Purple" has RGBA8888 code {@code 761A6AFF}, L 0.29411766, A 0.57254905, B 0.4627451, alpha 1.0, hue 0.9244969, saturation 0.6522764, and chroma 0.16247371.
     * It can be represented as a packed float with the constant {@code -0x1.ed2496p125F}.
     * <pre>
     * <font style='background-color: #761A6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #761A6A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #761A6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #761A6A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #761A6A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #761A6A'>&nbsp;@&nbsp;</font><font style='background-color: #761A6A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #761A6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #761A6A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_PURPLE = -0x1.ed2496p125F;
    static { NAMED.put("Deep Reddish Purple", -0x1.ed2496p125F); LIST.add(-0x1.ed2496p125F); }

    /**
     * This color constant "Very Deep Reddish Purple" has RGBA8888 code {@code 4F094AFF}, L 0.21176471, A 0.56078434, B 0.4627451, alpha 1.0, hue 0.9124788, saturation 0.8059747, and chroma 0.14202859.
     * It can be represented as a packed float with the constant {@code -0x1.ed1e6cp125F}.
     * <pre>
     * <font style='background-color: #4F094A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F094A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F094A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F094A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F094A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F094A'>&nbsp;@&nbsp;</font><font style='background-color: #4F094A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F094A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F094A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_REDDISH_PURPLE = -0x1.ed1e6cp125F;
    static { NAMED.put("Very Deep Reddish Purple", -0x1.ed1e6cp125F); LIST.add(-0x1.ed1e6cp125F); }

    /**
     * This color constant "Light Reddish Purple" has RGBA8888 code {@code BD80AEFF}, L 0.57254905, A 0.5411765, B 0.47843137, alpha 1.0, hue 0.9232045, saturation 0.08672505, and chroma 0.09260367.
     * It can be represented as a packed float with the constant {@code -0x1.f51524p125F}.
     * <pre>
     * <font style='background-color: #BD80AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD80AE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD80AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD80AE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD80AE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD80AE'>&nbsp;@&nbsp;</font><font style='background-color: #BD80AE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD80AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD80AE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_REDDISH_PURPLE = -0x1.f51524p125F;
    static { NAMED.put("Light Reddish Purple", -0x1.f51524p125F); LIST.add(-0x1.f51524p125F); }

    /**
     * This color constant "Moderate Reddish Purple" has RGBA8888 code {@code 965888FF}, L 0.43529412, A 0.54509807, B 0.4745098, alpha 1.0, hue 0.9181171, saturation 0.14450598, and chroma 0.10320191.
     * It can be represented as a packed float with the constant {@code -0x1.f316dep125F}.
     * <pre>
     * <font style='background-color: #965888;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #965888; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #965888;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #965888'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #965888'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #965888'>&nbsp;@&nbsp;</font><font style='background-color: #965888; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #965888;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #965888; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_PURPLE = -0x1.f316dep125F;
    static { NAMED.put("Moderate Reddish Purple", -0x1.f316dep125F); LIST.add(-0x1.f316dep125F); }

    /**
     * This color constant "Dark Reddish Purple" has RGBA8888 code {@code 5F3458FF}, L 0.2901961, A 0.5372549, B 0.47843137, alpha 1.0, hue 0.91646945, saturation 0.18173249, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.f51294p125F}.
     * <pre>
     * <font style='background-color: #5F3458;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F3458; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F3458;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5F3458'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5F3458'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5F3458'>&nbsp;@&nbsp;</font><font style='background-color: #5F3458; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F3458;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F3458; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_PURPLE = -0x1.f51294p125F;
    static { NAMED.put("Dark Reddish Purple", -0x1.f51294p125F); LIST.add(-0x1.f51294p125F); }

    /**
     * This color constant "Very Dark Reddish Purple" has RGBA8888 code {@code 3F183CFF}, L 0.19215687, A 0.5372549, B 0.4745098, alpha 1.0, hue 0.90448654, saturation 0.3768889, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.f31262p125F}.
     * <pre>
     * <font style='background-color: #3F183C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F183C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F183C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F183C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F183C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F183C'>&nbsp;@&nbsp;</font><font style='background-color: #3F183C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F183C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F183C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_REDDISH_PURPLE = -0x1.f31262p125F;
    static { NAMED.put("Very Dark Reddish Purple", -0x1.f31262p125F); LIST.add(-0x1.f31262p125F); }

    /**
     * This color constant "Pale Reddish Purple" has RGBA8888 code {@code AD89A5FF}, L 0.57254905, A 0.52156866, B 0.4862745, alpha 1.0, hue 0.90979147, saturation 0.023255019, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.f90b24p125F}.
     * <pre>
     * <font style='background-color: #AD89A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD89A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD89A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD89A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD89A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD89A5'>&nbsp;@&nbsp;</font><font style='background-color: #AD89A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD89A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD89A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_REDDISH_PURPLE = -0x1.f90b24p125F;
    static { NAMED.put("Pale Reddish Purple", -0x1.f90b24p125F); LIST.add(-0x1.f90b24p125F); }

    /**
     * This color constant "Grayish Reddish Purple" has RGBA8888 code {@code 86627EFF}, L 0.43529412, A 0.5254902, B 0.4862745, alpha 1.0, hue 0.92138404, saturation 0.04513224, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.f90cdep125F}.
     * <pre>
     * <font style='background-color: #86627E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86627E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86627E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #86627E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #86627E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #86627E'>&nbsp;@&nbsp;</font><font style='background-color: #86627E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86627E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86627E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_PURPLE = -0x1.f90cdep125F;
    static { NAMED.put("Grayish Reddish Purple", -0x1.f90cdep125F); LIST.add(-0x1.f90cdep125F); }

    /**
     * This color constant "Brilliant Purplish Pink" has RGBA8888 code {@code FCA1E7FF}, L 0.74509805, A 0.5568628, B 0.47058824, alpha 1.0, hue 0.9240277, saturation 0.6499009, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.f11d7cp125F}.
     * <pre>
     * <font style='background-color: #FCA1E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FCA1E7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FCA1E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FCA1E7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FCA1E7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FCA1E7'>&nbsp;@&nbsp;</font><font style='background-color: #FCA1E7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FCA1E7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FCA1E7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLISH_PINK = -0x1.f11d7cp125F;
    static { NAMED.put("Brilliant Purplish Pink", -0x1.f11d7cp125F); LIST.add(-0x1.f11d7cp125F); }

    /**
     * This color constant "Strong Purplish Pink" has RGBA8888 code {@code F483CDFF}, L 0.6627451, A 0.5686275, B 0.4745098, alpha 1.0, hue 0.9434115, saturation 0.5466131, and chroma 0.14584495.
     * It can be represented as a packed float with the constant {@code -0x1.f32352p125F}.
     * <pre>
     * <font style='background-color: #F483CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F483CD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F483CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F483CD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F483CD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F483CD'>&nbsp;@&nbsp;</font><font style='background-color: #F483CD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F483CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F483CD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_PINK = -0x1.f32352p125F;
    static { NAMED.put("Strong Purplish Pink", -0x1.f32352p125F); LIST.add(-0x1.f32352p125F); }

    /**
     * This color constant "Deep Purplish Pink" has RGBA8888 code {@code DF6AACFF}, L 0.57254905, A 0.57254905, B 0.47843137, alpha 1.0, hue 0.95401794, saturation 0.32702333, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.f52524p125F}.
     * <pre>
     * <font style='background-color: #DF6AAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF6AAC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF6AAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DF6AAC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DF6AAC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DF6AAC'>&nbsp;@&nbsp;</font><font style='background-color: #DF6AAC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DF6AAC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DF6AAC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_PINK = -0x1.f52524p125F;
    static { NAMED.put("Deep Purplish Pink", -0x1.f52524p125F); LIST.add(-0x1.f52524p125F); }

    /**
     * This color constant "Light Purplish Pink" has RGBA8888 code {@code F5B2DBFF}, L 0.76862746, A 0.5372549, B 0.4862745, alpha 1.0, hue 0.94383264, saturation 0.38816568, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f91388p125F}.
     * <pre>
     * <font style='background-color: #F5B2DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5B2DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5B2DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5B2DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5B2DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5B2DB'>&nbsp;@&nbsp;</font><font style='background-color: #F5B2DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5B2DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5B2DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_PINK = -0x1.f91388p125F;
    static { NAMED.put("Light Purplish Pink", -0x1.f91388p125F); LIST.add(-0x1.f91388p125F); }

    /**
     * This color constant "Moderate Purplish Pink" has RGBA8888 code {@code DE98BFFF}, L 0.67058825, A 0.5411765, B 0.4862745, alpha 1.0, hue 0.9488043, saturation 0.20831119, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.f91556p125F}.
     * <pre>
     * <font style='background-color: #DE98BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE98BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE98BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DE98BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DE98BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DE98BF'>&nbsp;@&nbsp;</font><font style='background-color: #DE98BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DE98BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DE98BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_PINK = -0x1.f91556p125F;
    static { NAMED.put("Moderate Purplish Pink", -0x1.f91556p125F); LIST.add(-0x1.f91556p125F); }

    /**
     * This color constant "Dark Purplish Pink" has RGBA8888 code {@code C67D9DFF}, L 0.57254905, A 0.54509807, B 0.49019608, alpha 1.0, hue 0.96592754, saturation 0.13316508, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.fb1724p125F}.
     * <pre>
     * <font style='background-color: #C67D9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C67D9D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C67D9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C67D9D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C67D9D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C67D9D'>&nbsp;@&nbsp;</font><font style='background-color: #C67D9D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C67D9D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C67D9D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_PINK = -0x1.fb1724p125F;
    static { NAMED.put("Dark Purplish Pink", -0x1.fb1724p125F); LIST.add(-0x1.fb1724p125F); }

    /**
     * This color constant "Pale Purplish Pink" has RGBA8888 code {@code EBC8DFFF}, L 0.8117647, A 0.5176471, B 0.49019608, alpha 1.0, hue 0.9192883, saturation 0.12180408, and chroma 0.040217306.
     * It can be represented as a packed float with the constant {@code -0x1.fb099ep125F}.
     * <pre>
     * <font style='background-color: #EBC8DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBC8DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBC8DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBC8DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBC8DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBC8DF'>&nbsp;@&nbsp;</font><font style='background-color: #EBC8DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBC8DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBC8DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLISH_PINK = -0x1.fb099ep125F;
    static { NAMED.put("Pale Purplish Pink", -0x1.fb099ep125F); LIST.add(-0x1.fb099ep125F); }

    /**
     * This color constant "Grayish Purplish Pink" has RGBA8888 code {@code C7A3B9FF}, L 0.6666667, A 0.52156866, B 0.49019608, alpha 1.0, hue 0.93210673, saturation 0.05100882, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.fb0b54p125F}.
     * <pre>
     * <font style='background-color: #C7A3B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7A3B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7A3B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7A3B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7A3B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7A3B9'>&nbsp;@&nbsp;</font><font style='background-color: #C7A3B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7A3B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7A3B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_PINK = -0x1.fb0b54p125F;
    static { NAMED.put("Grayish Purplish Pink", -0x1.fb0b54p125F); LIST.add(-0x1.fb0b54p125F); }

    /**
     * This color constant "Vivid Purplish Red" has RGBA8888 code {@code DD2388FF}, L 0.47058824, A 0.6156863, B 0.48235294, alpha 1.0, hue 0.97589505, saturation 0.7817833, and chroma 0.23313475.
     * It can be represented as a packed float with the constant {@code -0x1.f73afp125F}.
     * <pre>
     * <font style='background-color: #DD2388;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD2388; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD2388;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD2388'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD2388'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD2388'>&nbsp;@&nbsp;</font><font style='background-color: #DD2388; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD2388;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD2388; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLISH_RED = -0x1.f73afp125F;
    static { NAMED.put("Vivid Purplish Red", -0x1.f73afp125F); LIST.add(-0x1.f73afp125F); }

    /**
     * This color constant "Strong Purplish Red" has RGBA8888 code {@code B83773FF}, L 0.42352942, A 0.58431375, B 0.49019608, alpha 1.0, hue 0.9815659, saturation 0.49547228, and chroma 0.16910048.
     * It can be represented as a packed float with the constant {@code -0x1.fb2ad8p125F}.
     * <pre>
     * <font style='background-color: #B83773;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B83773; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B83773;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B83773'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B83773'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B83773'>&nbsp;@&nbsp;</font><font style='background-color: #B83773; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B83773;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B83773; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_RED = -0x1.fb2ad8p125F;
    static { NAMED.put("Strong Purplish Red", -0x1.fb2ad8p125F); LIST.add(-0x1.fb2ad8p125F); }

    /**
     * This color constant "Deep Purplish Red" has RGBA8888 code {@code 881055FF}, L 0.30588236, A 0.58431375, B 0.48235294, alpha 1.0, hue 0.96715736, saturation 0.7876747, and chroma 0.17160846.
     * It can be represented as a packed float with the constant {@code -0x1.f72a9cp125F}.
     * <pre>
     * <font style='background-color: #881055;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #881055; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #881055;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #881055'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #881055'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #881055'>&nbsp;@&nbsp;</font><font style='background-color: #881055; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #881055;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #881055; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_RED = -0x1.f72a9cp125F;
    static { NAMED.put("Deep Purplish Red", -0x1.f72a9cp125F); LIST.add(-0x1.f72a9cp125F); }

    /**
     * This color constant "Very Deep Purplish Red" has RGBA8888 code {@code 54063CFF}, L 0.21176471, A 0.5647059, B 0.47843137, alpha 1.0, hue 0.9488043, saturation 0.81632656, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.f5206cp125F}.
     * <pre>
     * <font style='background-color: #54063C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54063C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54063C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #54063C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #54063C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #54063C'>&nbsp;@&nbsp;</font><font style='background-color: #54063C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54063C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54063C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_PURPLISH_RED = -0x1.f5206cp125F;
    static { NAMED.put("Very Deep Purplish Red", -0x1.f5206cp125F); LIST.add(-0x1.f5206cp125F); }

    /**
     * This color constant "Moderate Purplish Red" has RGBA8888 code {@code AB4B74FF}, L 0.43529412, A 0.5647059, B 0.49019608, alpha 1.0, hue 0.9760548, saturation 0.27627257, and chroma 0.1303775.
     * It can be represented as a packed float with the constant {@code -0x1.fb20dep125F}.
     * <pre>
     * <font style='background-color: #AB4B74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB4B74; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB4B74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB4B74'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB4B74'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB4B74'>&nbsp;@&nbsp;</font><font style='background-color: #AB4B74; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB4B74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB4B74; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_RED = -0x1.fb20dep125F;
    static { NAMED.put("Moderate Purplish Red", -0x1.fb20dep125F); LIST.add(-0x1.fb20dep125F); }

    /**
     * This color constant "Dark Purplish Red" has RGBA8888 code {@code 6E294CFF}, L 0.28627452, A 0.5529412, B 0.49019608, alpha 1.0, hue 0.97084755, saturation 0.34871083, and chroma 0.107261956.
     * It can be represented as a packed float with the constant {@code -0x1.fb1a92p125F}.
     * <pre>
     * <font style='background-color: #6E294C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E294C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E294C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E294C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E294C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E294C'>&nbsp;@&nbsp;</font><font style='background-color: #6E294C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E294C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E294C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_RED = -0x1.fb1a92p125F;
    static { NAMED.put("Dark Purplish Red", -0x1.fb1a92p125F); LIST.add(-0x1.fb1a92p125F); }

    /**
     * This color constant "Very Dark Purplish Red" has RGBA8888 code {@code 431432FF}, L 0.1882353, A 0.54509807, B 0.4862745, alpha 1.0, hue 0.9529897, saturation 0.48561227, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.f9166p125F}.
     * <pre>
     * <font style='background-color: #431432;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #431432; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #431432;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #431432'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #431432'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #431432'>&nbsp;@&nbsp;</font><font style='background-color: #431432; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #431432;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #431432; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_PURPLISH_RED = -0x1.f9166p125F;
    static { NAMED.put("Very Dark Purplish Red", -0x1.f9166p125F); LIST.add(-0x1.f9166p125F); }

    /**
     * This color constant "Light Grayish Purplish Red" has RGBA8888 code {@code B2879BFF}, L 0.5686275, A 0.5254902, B 0.49411765, alpha 1.0, hue 0.9639029, saturation 0.040251, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.fd0d22p125F}.
     * <pre>
     * <font style='background-color: #B2879B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2879B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2879B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2879B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2879B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2879B'>&nbsp;@&nbsp;</font><font style='background-color: #B2879B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2879B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2879B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_PURPLISH_RED = -0x1.fd0d22p125F;
    static { NAMED.put("Light Grayish Purplish Red", -0x1.fd0d22p125F); LIST.add(-0x1.fd0d22p125F); }

    /**
     * This color constant "Grayish Purplish Red" has RGBA8888 code {@code 945C73FF}, L 0.43529412, A 0.5372549, B 0.49411765, alpha 1.0, hue 0.9750635, saturation 0.09176018, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.fd12dep125F}.
     * <pre>
     * <font style='background-color: #945C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #945C73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #945C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #945C73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #945C73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #945C73'>&nbsp;@&nbsp;</font><font style='background-color: #945C73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #945C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #945C73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_RED = -0x1.fd12dep125F;
    static { NAMED.put("Grayish Purplish Red", -0x1.fd12dep125F); LIST.add(-0x1.fd12dep125F); }

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
                final float c1 = NAMED.get(o1, MUNSELL_TRANSPARENT), c2 = NAMED.get(o2, MUNSELL_TRANSPARENT);
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
                return Float.compare(ColorTools.channelL(NAMED.get(o1, MUNSELL_TRANSPARENT)), ColorTools.channelL(NAMED.get(o2, MUNSELL_TRANSPARENT)));
            }
        });
    }
}
