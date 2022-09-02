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
public class BrighterMunsellPalette {
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<String>(261);
    public static final FloatArray LIST = new FloatArray(261);


    /**
     * This color constant "Munsell Transparent" has RGBA8888 code {@code 00000000}, L 0.0, A 0.49803922, B 0.49803922, alpha 0.0, hue 0.0, saturation 0.0, and chroma 0.0055242716.
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
     * This color constant "Black" has RGBA8888 code {@code 2D2725FF}, L 0.14901961, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.014486193, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.01004cp126F}.
     * <pre>
     * <font style='background-color: #2D2725;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D2725; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D2725;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D2725'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D2725'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D2725'>&nbsp;@&nbsp;</font><font style='background-color: #2D2725; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D2725;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D2725; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK = -0x1.01004cp126F;
    static { NAMED.put("Black", -0x1.01004cp126F); LIST.add(-0x1.01004cp126F); }

    /**
     * This color constant "Dark Gray" has RGBA8888 code {@code 595455FF}, L 0.34117648, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.0014512471, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff00aep125F}.
     * <pre>
     * <font style='background-color: #595455;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #595455; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #595455;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #595455'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #595455'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #595455'>&nbsp;@&nbsp;</font><font style='background-color: #595455; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #595455;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #595455; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY = -0x1.ff00aep125F;
    static { NAMED.put("Dark Gray", -0x1.ff00aep125F); LIST.add(-0x1.ff00aep125F); }

    /**
     * This color constant "Medium Gray" has RGBA8888 code {@code 8B8486FF}, L 0.5372549, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 8.281145E-4, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff0112p125F}.
     * <pre>
     * <font style='background-color: #8B8486;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B8486; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B8486;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B8486'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B8486'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B8486'>&nbsp;@&nbsp;</font><font style='background-color: #8B8486; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B8486;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B8486; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUM_GRAY = -0x1.ff0112p125F;
    static { NAMED.put("Medium Gray", -0x1.ff0112p125F); LIST.add(-0x1.ff0112p125F); }

    /**
     * This color constant "Light Gray" has RGBA8888 code {@code BFB8BAFF}, L 0.73333335, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.0037869823, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff0176p125F}.
     * <pre>
     * <font style='background-color: #BFB8BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFB8BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFB8BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFB8BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFB8BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFB8BA'>&nbsp;@&nbsp;</font><font style='background-color: #BFB8BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFB8BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFB8BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY = -0x1.ff0176p125F;
    static { NAMED.put("Light Gray", -0x1.ff0176p125F); LIST.add(-0x1.ff0176p125F); }

    /**
     * This color constant "White" has RGBA8888 code {@code EAE2E4FF}, L 0.8784314, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.021947874, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff01cp125F}.
     * <pre>
     * <font style='background-color: #EAE2E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAE2E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAE2E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAE2E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAE2E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAE2E4'>&nbsp;@&nbsp;</font><font style='background-color: #EAE2E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAE2E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAE2E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE = -0x1.ff01cp125F;
    static { NAMED.put("White", -0x1.ff01cp125F); LIST.add(-0x1.ff01cp125F); }

    /**
     * This color constant "Vivid Pink" has RGBA8888 code {@code FE7891FF}, L 0.65882355, A 0.57254905, B 0.50980395, alpha 1.0, hue 0.02493652, saturation 0.7821377, and chroma 0.14584495.
     * It can be represented as a packed float with the constant {@code -0x1.05255p126F}.
     * <pre>
     * <font style='background-color: #FE7891;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE7891; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE7891;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FE7891'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FE7891'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FE7891'>&nbsp;@&nbsp;</font><font style='background-color: #FE7891; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FE7891;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FE7891; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PINK = -0x1.05255p126F;
    static { NAMED.put("Vivid Pink", -0x1.05255p126F); LIST.add(-0x1.05255p126F); }

    /**
     * This color constant "Strong Pink" has RGBA8888 code {@code F093A2FF}, L 0.69803923, A 0.54901963, B 0.5058824, alpha 1.0, hue 0.024307659, saturation 0.51942205, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.031964p126F}.
     * <pre>
     * <font style='background-color: #F093A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F093A2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F093A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F093A2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F093A2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F093A2'>&nbsp;@&nbsp;</font><font style='background-color: #F093A2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F093A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F093A2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PINK = -0x1.031964p126F;
    static { NAMED.put("Strong Pink", -0x1.031964p126F); LIST.add(-0x1.031964p126F); }

    /**
     * This color constant "Deep Pink" has RGBA8888 code {@code E16D81FF}, L 0.5921569, A 0.5647059, B 0.50980395, alpha 1.0, hue 0.02781067, saturation 0.401313, and chroma 0.1303775.
     * It can be represented as a packed float with the constant {@code -0x1.05212ep126F}.
     * <pre>
     * <font style='background-color: #E16D81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E16D81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E16D81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E16D81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E16D81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E16D81'>&nbsp;@&nbsp;</font><font style='background-color: #E16D81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E16D81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E16D81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PINK = -0x1.05212ep126F;
    static { NAMED.put("Deep Pink", -0x1.05212ep126F); LIST.add(-0x1.05212ep126F); }

    /**
     * This color constant "Light Pink" has RGBA8888 code {@code F5C6CCFF}, L 0.81960785, A 0.52156866, B 0.5019608, alpha 1.0, hue 0.02629608, saturation 0.38921762, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.010ba2p126F}.
     * <pre>
     * <font style='background-color: #F5C6CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5C6CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5C6CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5C6CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5C6CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5C6CC'>&nbsp;@&nbsp;</font><font style='background-color: #F5C6CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5C6CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5C6CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PINK = -0x1.010ba2p126F;
    static { NAMED.put("Light Pink", -0x1.010ba2p126F); LIST.add(-0x1.010ba2p126F); }

    /**
     * This color constant "Moderate Pink" has RGBA8888 code {@code DBA7AFFF}, L 0.7176471, A 0.5254902, B 0.5019608, alpha 1.0, hue 0.022596559, saturation 0.16803193, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.010d6ep126F}.
     * <pre>
     * <font style='background-color: #DBA7AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBA7AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBA7AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBA7AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBA7AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBA7AF'>&nbsp;@&nbsp;</font><font style='background-color: #DBA7AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBA7AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBA7AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PINK = -0x1.010d6ep126F;
    static { NAMED.put("Moderate Pink", -0x1.010d6ep126F); LIST.add(-0x1.010d6ep126F); }

    /**
     * This color constant "Dark Pink" has RGBA8888 code {@code B98789FF}, L 0.59607846, A 0.5254902, B 0.5058824, alpha 1.0, hue 0.044284336, saturation 0.0769161, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.030d3p126F}.
     * <pre>
     * <font style='background-color: #B98789;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B98789; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B98789;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B98789'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B98789'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B98789'>&nbsp;@&nbsp;</font><font style='background-color: #B98789; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B98789;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B98789; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PINK = -0x1.030d3p126F;
    static { NAMED.put("Dark Pink", -0x1.030d3p126F); LIST.add(-0x1.030d3p126F); }

    /**
     * This color constant "Pale Pink" has RGBA8888 code {@code EDD4D4FF}, L 0.84705883, A 0.50980395, B 0.5019608, alpha 1.0, hue 0.05119568, saturation 0.14692378, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.0105bp126F}.
     * <pre>
     * <font style='background-color: #EDD4D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDD4D4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDD4D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDD4D4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDD4D4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDD4D4'>&nbsp;@&nbsp;</font><font style='background-color: #EDD4D4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDD4D4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDD4D4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PINK = -0x1.0105bp126F;
    static { NAMED.put("Pale Pink", -0x1.0105bp126F); LIST.add(-0x1.0105bp126F); }

    /**
     * This color constant "Grayish Pink" has RGBA8888 code {@code C3B2B0FF}, L 0.7176471, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.073790275, saturation 0.018934911, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.01036ep126F}.
     * <pre>
     * <font style='background-color: #C3B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3B2B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #C3B2B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3B2B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PINK = -0x1.01036ep126F;
    static { NAMED.put("Grayish Pink", -0x1.01036ep126F); LIST.add(-0x1.01036ep126F); }

    /**
     * This color constant "Pinkish White" has RGBA8888 code {@code E8E1E3FF}, L 0.8745098, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.021947874, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff01bep125F}.
     * <pre>
     * <font style='background-color: #E8E1E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8E1E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8E1E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E8E1E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E8E1E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E8E1E3'>&nbsp;@&nbsp;</font><font style='background-color: #E8E1E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8E1E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8E1E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINKISH_WHITE = -0x1.ff01bep125F;
    static { NAMED.put("Pinkish White", -0x1.ff01bep125F); LIST.add(-0x1.ff01bep125F); }

    /**
     * This color constant "Pinkish Gray" has RGBA8888 code {@code C3BAB6FF}, L 0.7372549, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.0075739645, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.010178p126F}.
     * <pre>
     * <font style='background-color: #C3BAB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3BAB6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3BAB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3BAB6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3BAB6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3BAB6'>&nbsp;@&nbsp;</font><font style='background-color: #C3BAB6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3BAB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3BAB6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINKISH_GRAY = -0x1.010178p126F;
    static { NAMED.put("Pinkish Gray", -0x1.010178p126F); LIST.add(-0x1.010178p126F); }

    /**
     * This color constant "Vivid Red" has RGBA8888 code {@code D61B3AFF}, L 0.44313726, A 0.6, B 0.53333336, alpha 1.0, hue 0.05302459, saturation 0.8005817, and chroma 0.209995.
     * It can be represented as a packed float with the constant {@code -0x1.1132e2p126F}.
     * <pre>
     * <font style='background-color: #D61B3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D61B3A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D61B3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D61B3A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D61B3A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D61B3A'>&nbsp;@&nbsp;</font><font style='background-color: #D61B3A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D61B3A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D61B3A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_RED = -0x1.1132e2p126F;
    static { NAMED.put("Vivid Red", -0x1.1132e2p126F); LIST.add(-0x1.1132e2p126F); }

    /**
     * This color constant "Strong Red" has RGBA8888 code {@code BA3949FF}, L 0.42745098, A 0.5764706, B 0.52156866, alpha 1.0, hue 0.04637667, saturation 0.47647017, and chroma 0.1582875.
     * It can be represented as a packed float with the constant {@code -0x1.0b26dap126F}.
     * <pre>
     * <font style='background-color: #BA3949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA3949; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA3949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BA3949'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BA3949'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BA3949'>&nbsp;@&nbsp;</font><font style='background-color: #BA3949; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BA3949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BA3949; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_RED = -0x1.0b26dap126F;
    static { NAMED.put("Strong Red", -0x1.0b26dap126F); LIST.add(-0x1.0b26dap126F); }

    /**
     * This color constant "Deep Red" has RGBA8888 code {@code 880F2DFF}, L 0.2784314, A 0.5764706, B 0.5176471, alpha 1.0, hue 0.038986836, saturation 0.78621805, and chroma 0.15634763.
     * It can be represented as a packed float with the constant {@code -0x1.09268ep126F}.
     * <pre>
     * <font style='background-color: #880F2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #880F2D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #880F2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #880F2D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #880F2D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #880F2D'>&nbsp;@&nbsp;</font><font style='background-color: #880F2D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #880F2D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #880F2D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED = -0x1.09268ep126F;
    static { NAMED.put("Deep Red", -0x1.09268ep126F); LIST.add(-0x1.09268ep126F); }

    /**
     * This color constant "Very Deep Red" has RGBA8888 code {@code 5B0825FF}, L 0.1764706, A 0.56078434, B 0.5058824, alpha 1.0, hue 0.019803474, saturation 0.8252331, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.031e5ap126F}.
     * <pre>
     * <font style='background-color: #5B0825;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B0825; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B0825;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B0825'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B0825'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B0825'>&nbsp;@&nbsp;</font><font style='background-color: #5B0825; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B0825;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B0825; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_RED = -0x1.031e5ap126F;
    static { NAMED.put("Very Deep Red", -0x1.031e5ap126F); LIST.add(-0x1.031e5ap126F); }

    /**
     * This color constant "Moderate Red" has RGBA8888 code {@code A84F57FF}, L 0.4392157, A 0.5529412, B 0.5137255, alpha 1.0, hue 0.044284336, saturation 0.22420517, and chroma 0.10895567.
     * It can be represented as a packed float with the constant {@code -0x1.071aep126F}.
     * <pre>
     * <font style='background-color: #A84F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A84F57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A84F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A84F57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A84F57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A84F57'>&nbsp;@&nbsp;</font><font style='background-color: #A84F57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A84F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A84F57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_RED = -0x1.071aep126F;
    static { NAMED.put("Moderate Red", -0x1.071aep126F); LIST.add(-0x1.071aep126F); }

    /**
     * This color constant "Dark Red" has RGBA8888 code {@code 6F2934FF}, L 0.2627451, A 0.54901963, B 0.50980395, alpha 1.0, hue 0.03609712, saturation 0.35955057, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.051886p126F}.
     * <pre>
     * <font style='background-color: #6F2934;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F2934; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F2934;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6F2934'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6F2934'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6F2934'>&nbsp;@&nbsp;</font><font style='background-color: #6F2934; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6F2934;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6F2934; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_RED = -0x1.051886p126F;
    static { NAMED.put("Dark Red", -0x1.051886p126F); LIST.add(-0x1.051886p126F); }

    /**
     * This color constant "Very Dark Red" has RGBA8888 code {@code 471223FF}, L 0.14509805, A 0.54509807, B 0.5019608, alpha 1.0, hue 0.013233744, saturation 0.54911244, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.01164ap126F}.
     * <pre>
     * <font style='background-color: #471223;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #471223; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #471223;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #471223'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #471223'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #471223'>&nbsp;@&nbsp;</font><font style='background-color: #471223; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #471223;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #471223; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_RED = -0x1.01164ap126F;
    static { NAMED.put("Very Dark Red", -0x1.01164ap126F); LIST.add(-0x1.01164ap126F); }

    /**
     * This color constant "Light Grayish Red" has RGBA8888 code {@code A6908FFF}, L 0.6, A 0.50980395, B 0.5019608, alpha 1.0, hue 0.05119568, saturation 0.015081534, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.010532p126F}.
     * <pre>
     * <font style='background-color: #A6908F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6908F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6908F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6908F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6908F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6908F'>&nbsp;@&nbsp;</font><font style='background-color: #A6908F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6908F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6908F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_RED = -0x1.010532p126F;
    static { NAMED.put("Light Grayish Red", -0x1.010532p126F); LIST.add(-0x1.010532p126F); }

    /**
     * This color constant "Grayish Red" has RGBA8888 code {@code 8B6464FF}, L 0.44705883, A 0.52156866, B 0.5058824, alpha 1.0, hue 0.05119568, saturation 0.04096, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.030ae4p126F}.
     * <pre>
     * <font style='background-color: #8B6464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B6464; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B6464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8B6464'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8B6464'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8B6464'>&nbsp;@&nbsp;</font><font style='background-color: #8B6464; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8B6464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8B6464; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_RED = -0x1.030ae4p126F;
    static { NAMED.put("Grayish Red", -0x1.030ae4p126F); LIST.add(-0x1.030ae4p126F); }

    /**
     * This color constant "Dark Grayish Red" has RGBA8888 code {@code 4C3B3BFF}, L 0.2509804, A 0.50980395, B 0.5019608, alpha 1.0, hue 0.05119568, saturation 0.021138856, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.01048p126F}.
     * <pre>
     * <font style='background-color: #4C3B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C3B3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C3B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4C3B3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4C3B3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4C3B3B'>&nbsp;@&nbsp;</font><font style='background-color: #4C3B3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4C3B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4C3B3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_RED = -0x1.01048p126F;
    static { NAMED.put("Dark Grayish Red", -0x1.01048p126F); LIST.add(-0x1.01048p126F); }

    /**
     * This color constant "Blackish Red" has RGBA8888 code {@code 2D2322FF}, L 0.13725491, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.073790275, saturation 0.018934911, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.010246p126F}.
     * <pre>
     * <font style='background-color: #2D2322;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D2322; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D2322;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2D2322'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2D2322'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2D2322'>&nbsp;@&nbsp;</font><font style='background-color: #2D2322; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2D2322;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2D2322; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_RED = -0x1.010246p126F;
    static { NAMED.put("Blackish Red", -0x1.010246p126F); LIST.add(-0x1.010246p126F); }

    /**
     * This color constant "Reddish Gray" has RGBA8888 code {@code 8D8480FF}, L 0.5372549, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.0031369473, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.010112p126F}.
     * <pre>
     * <font style='background-color: #8D8480;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D8480; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D8480;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8D8480'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8D8480'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8D8480'>&nbsp;@&nbsp;</font><font style='background-color: #8D8480; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D8480;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D8480; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REDDISH_GRAY = -0x1.010112p126F;
    static { NAMED.put("Reddish Gray", -0x1.010112p126F); LIST.add(-0x1.010112p126F); }

    /**
     * This color constant "Dark Reddish Gray" has RGBA8888 code {@code 5C4F4DFF}, L 0.32941177, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.073790275, saturation 0.007256236, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.0102a8p126F}.
     * <pre>
     * <font style='background-color: #5C4F4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C4F4D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C4F4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C4F4D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C4F4D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C4F4D'>&nbsp;@&nbsp;</font><font style='background-color: #5C4F4D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C4F4D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C4F4D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_GRAY = -0x1.0102a8p126F;
    static { NAMED.put("Dark Reddish Gray", -0x1.0102a8p126F); LIST.add(-0x1.0102a8p126F); }

    /**
     * This color constant "Reddish Black" has RGBA8888 code {@code 2F2628FF}, L 0.14901961, A 0.5058824, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.0142570725, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.ff024cp125F}.
     * <pre>
     * <font style='background-color: #2F2628;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F2628; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F2628;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F2628'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F2628'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F2628'>&nbsp;@&nbsp;</font><font style='background-color: #2F2628; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F2628;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F2628; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REDDISH_BLACK = -0x1.ff024cp125F;
    static { NAMED.put("Reddish Black", -0x1.ff024cp125F); LIST.add(-0x1.ff024cp125F); }

    /**
     * This color constant "Vivid Yellowish Pink" has RGBA8888 code {@code FD7F59FF}, L 0.65882355, A 0.5568628, B 0.54509807, alpha 1.0, hue 0.107399724, saturation 0.78002375, and chroma 0.14458403.
     * It can be represented as a packed float with the constant {@code -0x1.171d5p126F}.
     * <pre>
     * <font style='background-color: #FD7F59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD7F59; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD7F59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD7F59'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD7F59'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD7F59'>&nbsp;@&nbsp;</font><font style='background-color: #FD7F59; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD7F59;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD7F59; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOWISH_PINK = -0x1.171d5p126F;
    static { NAMED.put("Vivid Yellowish Pink", -0x1.171d5p126F); LIST.add(-0x1.171d5p126F); }

    /**
     * This color constant "Strong Yellowish Pink" has RGBA8888 code {@code F29282FF}, L 0.6901961, A 0.54509807, B 0.5254902, alpha 1.0, hue 0.084052734, saturation 0.5489778, and chroma 0.10320191.
     * It can be represented as a packed float with the constant {@code -0x1.0d176p126F}.
     * <pre>
     * <font style='background-color: #F29282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F29282; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F29282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F29282'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F29282'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F29282'>&nbsp;@&nbsp;</font><font style='background-color: #F29282; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F29282;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F29282; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_PINK = -0x1.0d176p126F;
    static { NAMED.put("Strong Yellowish Pink", -0x1.0d176p126F); LIST.add(-0x1.0d176p126F); }

    /**
     * This color constant "Deep Yellowish Pink" has RGBA8888 code {@code EA6869FF}, L 0.5921569, A 0.5686275, B 0.5254902, alpha 1.0, hue 0.05901709, saturation 0.52126825, and chroma 0.14584495.
     * It can be represented as a packed float with the constant {@code -0x1.0d232ep126F}.
     * <pre>
     * <font style='background-color: #EA6869;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA6869; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA6869;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EA6869'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EA6869'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EA6869'>&nbsp;@&nbsp;</font><font style='background-color: #EA6869; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EA6869;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EA6869; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_PINK = -0x1.0d232ep126F;
    static { NAMED.put("Deep Yellowish Pink", -0x1.0d232ep126F); LIST.add(-0x1.0d232ep126F); }

    /**
     * This color constant "Light Yellowish Pink" has RGBA8888 code {@code F4C6B7FF}, L 0.8156863, A 0.5176471, B 0.5137255, alpha 1.0, hue 0.107399724, saturation 0.3902439, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0709ap126F}.
     * <pre>
     * <font style='background-color: #F4C6B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4C6B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4C6B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F4C6B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F4C6B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F4C6B7'>&nbsp;@&nbsp;</font><font style='background-color: #F4C6B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4C6B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4C6B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_PINK = -0x1.0709ap126F;
    static { NAMED.put("Light Yellowish Pink", -0x1.0709ap126F); LIST.add(-0x1.0709ap126F); }

    /**
     * This color constant "Moderate Yellowish Pink" has RGBA8888 code {@code DCA99EFF}, L 0.7176471, A 0.52156866, B 0.5137255, alpha 1.0, hue 0.09359558, saturation 0.18534195, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.070b6ep126F}.
     * <pre>
     * <font style='background-color: #DCA99E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCA99E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCA99E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DCA99E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DCA99E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DCA99E'>&nbsp;@&nbsp;</font><font style='background-color: #DCA99E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DCA99E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DCA99E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_PINK = -0x1.070b6ep126F;
    static { NAMED.put("Moderate Yellowish Pink", -0x1.070b6ep126F); LIST.add(-0x1.070b6ep126F); }

    /**
     * This color constant "Dark Yellowish Pink" has RGBA8888 code {@code BB8783FF}, L 0.59607846, A 0.5254902, B 0.50980395, alpha 1.0, hue 0.06443131, saturation 0.08417234, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.050d3p126F}.
     * <pre>
     * <font style='background-color: #BB8783;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB8783; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB8783;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB8783'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB8783'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB8783'>&nbsp;@&nbsp;</font><font style='background-color: #BB8783; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB8783;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB8783; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_PINK = -0x1.050d3p126F;
    static { NAMED.put("Dark Yellowish Pink", -0x1.050d3p126F); LIST.add(-0x1.050d3p126F); }

    /**
     * This color constant "Pale Yellowish Pink" has RGBA8888 code {@code EED5D5FF}, L 0.84705883, A 0.50980395, B 0.5019608, alpha 1.0, hue 0.05119568, saturation 0.14692378, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.0105bp126F}.
     * <pre>
     * <font style='background-color: #EED5D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EED5D5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EED5D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EED5D5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EED5D5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EED5D5'>&nbsp;@&nbsp;</font><font style='background-color: #EED5D5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EED5D5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EED5D5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOWISH_PINK = -0x1.0105bp126F;
    static { NAMED.put("Pale Yellowish Pink", -0x1.0105bp126F); LIST.add(-0x1.0105bp126F); }

    /**
     * This color constant "Grayish Yellowish Pink" has RGBA8888 code {@code C1B0AEFF}, L 0.70980394, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.073790275, saturation 0.017821342, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.01036ap126F}.
     * <pre>
     * <font style='background-color: #C1B0AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1B0AE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1B0AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1B0AE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1B0AE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1B0AE'>&nbsp;@&nbsp;</font><font style='background-color: #C1B0AE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1B0AE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1B0AE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOWISH_PINK = -0x1.01036ap126F;
    static { NAMED.put("Grayish Yellowish Pink", -0x1.01036ap126F); LIST.add(-0x1.01036ap126F); }

    /**
     * This color constant "Brownish Pink" has RGBA8888 code {@code C3B2B0FF}, L 0.7176471, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.073790275, saturation 0.018934911, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.01036ep126F}.
     * <pre>
     * <font style='background-color: #C3B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3B2B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3B2B0'>&nbsp;@&nbsp;</font><font style='background-color: #C3B2B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3B2B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3B2B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_PINK = -0x1.01036ep126F;
    static { NAMED.put("Brownish Pink", -0x1.01036ep126F); LIST.add(-0x1.01036ep126F); }

    /**
     * This color constant "Vivid Reddish Orange" has RGBA8888 code {@code E83D19FF}, L 0.5058824, A 0.58431375, B 0.5568628, alpha 1.0, hue 0.0952538, saturation 0.8010734, and chroma 0.20259848.
     * It can be represented as a packed float with the constant {@code -0x1.1d2b02p126F}.
     * <pre>
     * <font style='background-color: #E83D19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E83D19; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E83D19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E83D19'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E83D19'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E83D19'>&nbsp;@&nbsp;</font><font style='background-color: #E83D19; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E83D19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E83D19; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_REDDISH_ORANGE = -0x1.1d2b02p126F;
    static { NAMED.put("Vivid Reddish Orange", -0x1.1d2b02p126F); LIST.add(-0x1.1d2b02p126F); }

    /**
     * This color constant "Strong Reddish Orange" has RGBA8888 code {@code D16444FF}, L 0.5372549, A 0.5529412, B 0.5411765, alpha 1.0, hue 0.10600415, saturation 0.397212, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.151b12p126F}.
     * <pre>
     * <font style='background-color: #D16444;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D16444; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D16444;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D16444'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D16444'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D16444'>&nbsp;@&nbsp;</font><font style='background-color: #D16444; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D16444;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D16444; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_ORANGE = -0x1.151b12p126F;
    static { NAMED.put("Strong Reddish Orange", -0x1.151b12p126F); LIST.add(-0x1.151b12p126F); }

    /**
     * This color constant "Deep Reddish Orange" has RGBA8888 code {@code AD351AFF}, L 0.3882353, A 0.5647059, B 0.54509807, alpha 1.0, hue 0.09783951, saturation 0.73631626, and chroma 0.15712644.
     * It can be represented as a packed float with the constant {@code -0x1.1720c6p126F}.
     * <pre>
     * <font style='background-color: #AD351A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD351A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD351A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD351A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD351A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD351A'>&nbsp;@&nbsp;</font><font style='background-color: #AD351A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD351A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD351A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_ORANGE = -0x1.1720c6p126F;
    static { NAMED.put("Deep Reddish Orange", -0x1.1720c6p126F); LIST.add(-0x1.1720c6p126F); }

    /**
     * This color constant "Moderate Reddish Orange" has RGBA8888 code {@code BF7260FF}, L 0.54509807, A 0.5372549, B 0.5254902, alpha 1.0, hue 0.09721285, saturation 0.152576, and chroma 0.08992863.
     * It can be represented as a packed float with the constant {@code -0x1.0d1316p126F}.
     * <pre>
     * <font style='background-color: #BF7260;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF7260; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF7260;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF7260'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF7260'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF7260'>&nbsp;@&nbsp;</font><font style='background-color: #BF7260; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF7260;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF7260; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_ORANGE = -0x1.0d1316p126F;
    static { NAMED.put("Moderate Reddish Orange", -0x1.0d1316p126F); LIST.add(-0x1.0d1316p126F); }

    /**
     * This color constant "Dark Reddish Orange" has RGBA8888 code {@code 9A4630FF}, L 0.3882353, A 0.54509807, B 0.53333336, alpha 1.0, hue 0.102429084, saturation 0.39889196, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.1116c6p126F}.
     * <pre>
     * <font style='background-color: #9A4630;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A4630; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A4630;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A4630'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A4630'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A4630'>&nbsp;@&nbsp;</font><font style='background-color: #9A4630; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A4630;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A4630; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_ORANGE = -0x1.1116c6p126F;
    static { NAMED.put("Dark Reddish Orange", -0x1.1116c6p126F); LIST.add(-0x1.1116c6p126F); }

    /**
     * This color constant "Grayish Reddish Orange" has RGBA8888 code {@code AA7C71FF}, L 0.54509807, A 0.52156866, B 0.5137255, alpha 1.0, hue 0.09359558, saturation 0.053248, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.070b16p126F}.
     * <pre>
     * <font style='background-color: #AA7C71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA7C71; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA7C71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA7C71'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA7C71'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA7C71'>&nbsp;@&nbsp;</font><font style='background-color: #AA7C71; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA7C71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA7C71; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_ORANGE = -0x1.070b16p126F;
    static { NAMED.put("Grayish Reddish Orange", -0x1.070b16p126F); LIST.add(-0x1.070b16p126F); }

    /**
     * This color constant "Strong Reddish Brown" has RGBA8888 code {@code 8C1B05FF}, L 0.2901961, A 0.5647059, B 0.5411765, alpha 1.0, hue 0.091414735, saturation 0.8666931, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.152094p126F}.
     * <pre>
     * <font style='background-color: #8C1B05;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C1B05; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C1B05;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C1B05'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C1B05'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C1B05'>&nbsp;@&nbsp;</font><font style='background-color: #8C1B05; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C1B05;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C1B05; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_BROWN = -0x1.152094p126F;
    static { NAMED.put("Strong Reddish Brown", -0x1.152094p126F); LIST.add(-0x1.152094p126F); }

    /**
     * This color constant "Deep Reddish Brown" has RGBA8888 code {@code 620C12FF}, L 0.19215687, A 0.5568628, B 0.5254902, alpha 1.0, hue 0.06948605, saturation 0.7394164, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.0d1c62p126F}.
     * <pre>
     * <font style='background-color: #620C12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #620C12; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #620C12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #620C12'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #620C12'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #620C12'>&nbsp;@&nbsp;</font><font style='background-color: #620C12; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #620C12;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #620C12; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_BROWN = -0x1.0d1c62p126F;
    static { NAMED.put("Deep Reddish Brown", -0x1.0d1c62p126F); LIST.add(-0x1.0d1c62p126F); }

    /**
     * This color constant "Light Reddish Brown" has RGBA8888 code {@code A08077FF}, L 0.54509807, A 0.5137255, B 0.50980395, alpha 1.0, hue 0.102429084, saturation 0.028246593, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.050716p126F}.
     * <pre>
     * <font style='background-color: #A08077;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A08077; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A08077;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A08077'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A08077'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A08077'>&nbsp;@&nbsp;</font><font style='background-color: #A08077; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A08077;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A08077; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_REDDISH_BROWN = -0x1.050716p126F;
    static { NAMED.put("Light Reddish Brown", -0x1.050716p126F); LIST.add(-0x1.050716p126F); }

    /**
     * This color constant "Moderate Reddish Brown" has RGBA8888 code {@code 744741FF}, L 0.3372549, A 0.5254902, B 0.5137255, alpha 1.0, hue 0.0826307, saturation 0.09802997, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.070cacp126F}.
     * <pre>
     * <font style='background-color: #744741;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #744741; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #744741;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #744741'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #744741'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #744741'>&nbsp;@&nbsp;</font><font style='background-color: #744741; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #744741;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #744741; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_BROWN = -0x1.070cacp126F;
    static { NAMED.put("Moderate Reddish Brown", -0x1.070cacp126F); LIST.add(-0x1.070cacp126F); }

    /**
     * This color constant "Dark Reddish Brown" has RGBA8888 code {@code 411F1EFF}, L 0.15686275, A 0.5254902, B 0.50980395, alpha 1.0, hue 0.06443131, saturation 0.19491704, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.050c5p126F}.
     * <pre>
     * <font style='background-color: #411F1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #411F1E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #411F1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #411F1E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #411F1E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #411F1E'>&nbsp;@&nbsp;</font><font style='background-color: #411F1E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #411F1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #411F1E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_BROWN = -0x1.050c5p126F;
    static { NAMED.put("Dark Reddish Brown", -0x1.050c5p126F); LIST.add(-0x1.050c5p126F); }

    /**
     * This color constant "Light Grayish Reddish Brown" has RGBA8888 code {@code 928381FF}, L 0.5411765, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.073790275, saturation 0.00512, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.010314p126F}.
     * <pre>
     * <font style='background-color: #928381;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #928381; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #928381;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #928381'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #928381'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #928381'>&nbsp;@&nbsp;</font><font style='background-color: #928381; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #928381;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #928381; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_REDDISH_BROWN = -0x1.010314p126F;
    static { NAMED.put("Light Grayish Reddish Brown", -0x1.010314p126F); LIST.add(-0x1.010314p126F); }

    /**
     * This color constant "Grayish Reddish Brown" has RGBA8888 code {@code 625050FF}, L 0.3372549, A 0.50980395, B 0.5019608, alpha 1.0, hue 0.05119568, saturation 0.014512472, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.0104acp126F}.
     * <pre>
     * <font style='background-color: #625050;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #625050; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #625050;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #625050'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #625050'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #625050'>&nbsp;@&nbsp;</font><font style='background-color: #625050; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #625050;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #625050; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_BROWN = -0x1.0104acp126F;
    static { NAMED.put("Grayish Reddish Brown", -0x1.0104acp126F); LIST.add(-0x1.0104acp126F); }

    /**
     * This color constant "Dark Grayish Reddish Brown" has RGBA8888 code {@code 3C2C28FF}, L 0.18431373, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.09359558, saturation 0.04633549, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.03045ep126F}.
     * <pre>
     * <font style='background-color: #3C2C28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C2C28; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C2C28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C2C28'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C2C28'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C2C28'>&nbsp;@&nbsp;</font><font style='background-color: #3C2C28; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C2C28;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C2C28; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_REDDISH_BROWN = -0x1.03045ep126F;
    static { NAMED.put("Dark Grayish Reddish Brown", -0x1.03045ep126F); LIST.add(-0x1.03045ep126F); }

    /**
     * This color constant "Vivid Orange" has RGBA8888 code {@code F87700FF}, L 0.627451, A 0.54901963, B 0.57254905, alpha 1.0, hue 0.1544865, saturation 0.9012647, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.25194p126F}.
     * <pre>
     * <font style='background-color: #F87700;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F87700; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F87700;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F87700'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F87700'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F87700'>&nbsp;@&nbsp;</font><font style='background-color: #F87700; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F87700;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F87700; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_ORANGE = -0x1.25194p126F;
    static { NAMED.put("Vivid Orange", -0x1.25194p126F); LIST.add(-0x1.25194p126F); }

    /**
     * This color constant "Strong Orange" has RGBA8888 code {@code E88233FF}, L 0.627451, A 0.5372549, B 0.56078434, alpha 1.0, hue 0.16108605, saturation 0.63113576, and chroma 0.14202859.
     * It can be represented as a packed float with the constant {@code -0x1.1f134p126F}.
     * <pre>
     * <font style='background-color: #E88233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E88233; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E88233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E88233'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E88233'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E88233'>&nbsp;@&nbsp;</font><font style='background-color: #E88233; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E88233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E88233; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_ORANGE = -0x1.1f134p126F;
    static { NAMED.put("Strong Orange", -0x1.1f134p126F); LIST.add(-0x1.1f134p126F); }

    /**
     * This color constant "Deep Orange" has RGBA8888 code {@code C45F02FF}, L 0.5019608, A 0.5411765, B 0.56078434, alpha 1.0, hue 0.15413038, saturation 0.8348789, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.1f15p126F}.
     * <pre>
     * <font style='background-color: #C45F02;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C45F02; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C45F02;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C45F02'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C45F02'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C45F02'>&nbsp;@&nbsp;</font><font style='background-color: #C45F02; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C45F02;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C45F02; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE = -0x1.1f15p126F;
    static { NAMED.put("Deep Orange", -0x1.1f15p126F); LIST.add(-0x1.1f15p126F); }

    /**
     * This color constant "Light Orange" has RGBA8888 code {@code FEAF82FF}, L 0.7607843, A 0.5294118, B 0.5372549, alpha 1.0, hue 0.14260027, saturation 0.7051868, and chroma 0.0945603.
     * It can be represented as a packed float with the constant {@code -0x1.130f84p126F}.
     * <pre>
     * <font style='background-color: #FEAF82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEAF82; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEAF82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FEAF82'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FEAF82'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FEAF82'>&nbsp;@&nbsp;</font><font style='background-color: #FEAF82; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FEAF82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FEAF82; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_ORANGE = -0x1.130f84p126F;
    static { NAMED.put("Light Orange", -0x1.130f84p126F); LIST.add(-0x1.130f84p126F); }

    /**
     * This color constant "Moderate Orange" has RGBA8888 code {@code D4926CFF}, L 0.64705884, A 0.5254902, B 0.53333336, alpha 1.0, hue 0.14477962, saturation 0.21222325, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.110d4ap126F}.
     * <pre>
     * <font style='background-color: #D4926C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4926C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4926C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4926C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4926C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4926C'>&nbsp;@&nbsp;</font><font style='background-color: #D4926C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4926C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4926C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_ORANGE = -0x1.110d4ap126F;
    static { NAMED.put("Moderate Orange", -0x1.110d4ap126F); LIST.add(-0x1.110d4ap126F); }

    /**
     * This color constant "Brownish Orange" has RGBA8888 code {@code AF683BFF}, L 0.49411765, A 0.5294118, B 0.5411765, alpha 1.0, hue 0.14991105, saturation 0.4096886, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.150efcp126F}.
     * <pre>
     * <font style='background-color: #AF683B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF683B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF683B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF683B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF683B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF683B'>&nbsp;@&nbsp;</font><font style='background-color: #AF683B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF683B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF683B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_ORANGE = -0x1.150efcp126F;
    static { NAMED.put("Brownish Orange", -0x1.150efcp126F); LIST.add(-0x1.150efcp126F); }

    /**
     * This color constant "Strong Brown" has RGBA8888 code {@code 8A4311FF}, L 0.3529412, A 0.53333336, B 0.54509807, alpha 1.0, hue 0.14757092, saturation 0.7561437, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.1710b4p126F}.
     * <pre>
     * <font style='background-color: #8A4311;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A4311; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A4311;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A4311'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A4311'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A4311'>&nbsp;@&nbsp;</font><font style='background-color: #8A4311; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A4311;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A4311; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BROWN = -0x1.1710b4p126F;
    static { NAMED.put("Strong Brown", -0x1.1710b4p126F); LIST.add(-0x1.1710b4p126F); }

    /**
     * This color constant "Deep Brown" has RGBA8888 code {@code 571909FF}, L 0.18039216, A 0.5411765, B 0.5294118, alpha 1.0, hue 0.10008895, saturation 0.7954851, and chroma 0.1008085.
     * It can be represented as a packed float with the constant {@code -0x1.0f145cp126F}.
     * <pre>
     * <font style='background-color: #571909;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #571909; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #571909;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #571909'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #571909'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #571909'>&nbsp;@&nbsp;</font><font style='background-color: #571909; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #571909;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #571909; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN = -0x1.0f145cp126F;
    static { NAMED.put("Deep Brown", -0x1.0f145cp126F); LIST.add(-0x1.0f145cp126F); }

    /**
     * This color constant "Light Brown" has RGBA8888 code {@code A3806CFF}, L 0.54509807, A 0.5137255, B 0.5176471, alpha 1.0, hue 0.14260027, saturation 0.07584692, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.090716p126F}.
     * <pre>
     * <font style='background-color: #A3806C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3806C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3806C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A3806C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A3806C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A3806C'>&nbsp;@&nbsp;</font><font style='background-color: #A3806C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A3806C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A3806C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BROWN = -0x1.090716p126F;
    static { NAMED.put("Light Brown", -0x1.090716p126F); LIST.add(-0x1.090716p126F); }

    /**
     * This color constant "Moderate Brown" has RGBA8888 code {@code 6E4B3CFF}, L 0.3372549, A 0.5176471, B 0.5176471, alpha 1.0, hue 0.125, saturation 0.14222223, and chroma 0.049718447.
     * It can be represented as a packed float with the constant {@code -0x1.0908acp126F}.
     * <pre>
     * <font style='background-color: #6E4B3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E4B3C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E4B3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E4B3C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E4B3C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E4B3C'>&nbsp;@&nbsp;</font><font style='background-color: #6E4B3C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E4B3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E4B3C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BROWN = -0x1.0908acp126F;
    static { NAMED.put("Moderate Brown", -0x1.0908acp126F); LIST.add(-0x1.0908acp126F); }

    /**
     * This color constant "Dark Brown" has RGBA8888 code {@code 402317FF}, L 0.16078432, A 0.5176471, B 0.5176471, alpha 1.0, hue 0.125, saturation 0.3331945, and chroma 0.049718447.
     * It can be represented as a packed float with the constant {@code -0x1.090852p126F}.
     * <pre>
     * <font style='background-color: #402317;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #402317; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #402317;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #402317'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #402317'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #402317'>&nbsp;@&nbsp;</font><font style='background-color: #402317; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #402317;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #402317; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BROWN = -0x1.090852p126F;
    static { NAMED.put("Dark Brown", -0x1.090852p126F); LIST.add(-0x1.090852p126F); }

    /**
     * This color constant "Light Grayish Brown" has RGBA8888 code {@code 92817AFF}, L 0.53333336, A 0.5058824, B 0.5058824, alpha 1.0, hue 0.125, saturation 0.013059892, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.03031p126F}.
     * <pre>
     * <font style='background-color: #92817A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92817A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92817A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #92817A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #92817A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #92817A'>&nbsp;@&nbsp;</font><font style='background-color: #92817A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92817A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92817A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_BROWN = -0x1.03031p126F;
    static { NAMED.put("Light Grayish Brown", -0x1.03031p126F); LIST.add(-0x1.03031p126F); }

    /**
     * This color constant "Grayish Brown" has RGBA8888 code {@code 60524CFF}, L 0.34117648, A 0.5058824, B 0.5058824, alpha 1.0, hue 0.125, saturation 0.022755556, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.0302aep126F}.
     * <pre>
     * <font style='background-color: #60524C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #60524C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #60524C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #60524C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #60524C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #60524C'>&nbsp;@&nbsp;</font><font style='background-color: #60524C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #60524C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #60524C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_BROWN = -0x1.0302aep126F;
    static { NAMED.put("Grayish Brown", -0x1.0302aep126F); LIST.add(-0x1.0302aep126F); }

    /**
     * This color constant "Dark Grayish Brown" has RGBA8888 code {@code 382D2CFF}, L 0.18039216, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.073790275, saturation 0.014222222, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.01025cp126F}.
     * <pre>
     * <font style='background-color: #382D2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #382D2C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #382D2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #382D2C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #382D2C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #382D2C'>&nbsp;@&nbsp;</font><font style='background-color: #382D2C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #382D2C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #382D2C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_BROWN = -0x1.01025cp126F;
    static { NAMED.put("Dark Grayish Brown", -0x1.01025cp126F); LIST.add(-0x1.01025cp126F); }

    /**
     * This color constant "Light Brownish Gray" has RGBA8888 code {@code 8D8480FF}, L 0.5372549, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.0031369473, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.010112p126F}.
     * <pre>
     * <font style='background-color: #8D8480;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D8480; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D8480;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8D8480'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8D8480'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8D8480'>&nbsp;@&nbsp;</font><font style='background-color: #8D8480; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D8480;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D8480; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BROWNISH_GRAY = -0x1.010112p126F;
    static { NAMED.put("Light Brownish Gray", -0x1.010112p126F); LIST.add(-0x1.010112p126F); }

    /**
     * This color constant "Brownish Gray" has RGBA8888 code {@code 5B5450FF}, L 0.34117648, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.005688889, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.0100aep126F}.
     * <pre>
     * <font style='background-color: #5B5450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5450; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B5450'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B5450'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B5450'>&nbsp;@&nbsp;</font><font style='background-color: #5B5450; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5450; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_GRAY = -0x1.0100aep126F;
    static { NAMED.put("Brownish Gray", -0x1.0100aep126F); LIST.add(-0x1.0100aep126F); }

    /**
     * This color constant "Brownish Black" has RGBA8888 code {@code 27221FFF}, L 0.1254902, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.017306652, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.01004p126F}.
     * <pre>
     * <font style='background-color: #27221F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #27221F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #27221F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #27221F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #27221F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #27221F'>&nbsp;@&nbsp;</font><font style='background-color: #27221F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #27221F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #27221F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_BLACK = -0x1.01004p126F;
    static { NAMED.put("Brownish Black", -0x1.01004p126F); LIST.add(-0x1.01004p126F); }

    /**
     * This color constant "Brilliant Orange Yellow" has RGBA8888 code {@code FFBF4CFF}, L 0.7882353, A 0.50980395, B 0.5686275, alpha 1.0, hue 0.22370392, saturation 0.77340686, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.230592p126F}.
     * <pre>
     * <font style='background-color: #FFBF4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF4C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBF4C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBF4C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBF4C'>&nbsp;@&nbsp;</font><font style='background-color: #FFBF4C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF4C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF4C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_ORANGE_YELLOW = -0x1.230592p126F;
    static { NAMED.put("Brilliant Orange Yellow", -0x1.230592p126F); LIST.add(-0x1.230592p126F); }

    /**
     * This color constant "Strong Orange Yellow" has RGBA8888 code {@code EFA233FF}, L 0.7058824, A 0.5176471, B 0.5686275, alpha 1.0, hue 0.20688479, saturation 0.6743147, and chroma 0.1411665.
     * It can be represented as a packed float with the constant {@code -0x1.230968p126F}.
     * <pre>
     * <font style='background-color: #EFA233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFA233; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFA233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFA233'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFA233'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFA233'>&nbsp;@&nbsp;</font><font style='background-color: #EFA233; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFA233;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFA233; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_ORANGE_YELLOW = -0x1.230968p126F;
    static { NAMED.put("Strong Orange Yellow", -0x1.230968p126F); LIST.add(-0x1.230968p126F); }

    /**
     * This color constant "Deep Orange Yellow" has RGBA8888 code {@code D0860CFF}, L 0.6, A 0.5176471, B 0.5686275, alpha 1.0, hue 0.20688479, saturation 0.81056756, and chroma 0.1411665.
     * It can be represented as a packed float with the constant {@code -0x1.230932p126F}.
     * <pre>
     * <font style='background-color: #D0860C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0860C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0860C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0860C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0860C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0860C'>&nbsp;@&nbsp;</font><font style='background-color: #D0860C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0860C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0860C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_YELLOW = -0x1.230932p126F;
    static { NAMED.put("Deep Orange Yellow", -0x1.230932p126F); LIST.add(-0x1.230932p126F); }

    /**
     * This color constant "Light Orange Yellow" has RGBA8888 code {@code FAC387FF}, L 0.8, A 0.5137255, B 0.5411765, alpha 1.0, hue 0.1945043, saturation 0.589089, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.150798p126F}.
     * <pre>
     * <font style='background-color: #FAC387;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAC387; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAC387;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FAC387'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FAC387'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FAC387'>&nbsp;@&nbsp;</font><font style='background-color: #FAC387; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAC387;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAC387; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_ORANGE_YELLOW = -0x1.150798p126F;
    static { NAMED.put("Light Orange Yellow", -0x1.150798p126F); LIST.add(-0x1.150798p126F); }

    /**
     * This color constant "Moderate Orange Yellow" has RGBA8888 code {@code E5A768FF}, L 0.70980394, A 0.5176471, B 0.54509807, alpha 1.0, hue 0.1871773, saturation 0.3126373, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.17096ap126F}.
     * <pre>
     * <font style='background-color: #E5A768;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5A768; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5A768;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E5A768'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E5A768'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E5A768'>&nbsp;@&nbsp;</font><font style='background-color: #E5A768; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E5A768;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E5A768; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_ORANGE_YELLOW = -0x1.17096ap126F;
    static { NAMED.put("Moderate Orange Yellow", -0x1.17096ap126F); LIST.add(-0x1.17096ap126F); }

    /**
     * This color constant "Dark Orange Yellow" has RGBA8888 code {@code C1864BFF}, L 0.5882353, A 0.5176471, B 0.54509807, alpha 1.0, hue 0.1871773, saturation 0.37425604, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.17092cp126F}.
     * <pre>
     * <font style='background-color: #C1864B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1864B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1864B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1864B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1864B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1864B'>&nbsp;@&nbsp;</font><font style='background-color: #C1864B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1864B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1864B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_ORANGE_YELLOW = -0x1.17092cp126F;
    static { NAMED.put("Dark Orange Yellow", -0x1.17092cp126F); LIST.add(-0x1.17092cp126F); }

    /**
     * This color constant "Pale Orange Yellow" has RGBA8888 code {@code EAC8AFFF}, L 0.8039216, A 0.50980395, B 0.5176471, alpha 1.0, hue 0.16398115, saturation 0.20915033, and chroma 0.040217306.
     * It can be represented as a packed float with the constant {@code -0x1.09059ap126F}.
     * <pre>
     * <font style='background-color: #EAC8AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAC8AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAC8AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EAC8AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EAC8AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EAC8AF'>&nbsp;@&nbsp;</font><font style='background-color: #EAC8AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EAC8AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EAC8AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_YELLOW = -0x1.09059ap126F;
    static { NAMED.put("Pale Orange Yellow", -0x1.09059ap126F); LIST.add(-0x1.09059ap126F); }

    /**
     * This color constant "Strong Yellowish Brown" has RGBA8888 code {@code 9B6825FF}, L 0.4627451, A 0.5137255, B 0.54901963, alpha 1.0, hue 0.20250328, saturation 0.5871851, and chroma 0.10141215.
     * It can be represented as a packed float with the constant {@code -0x1.1906ecp126F}.
     * <pre>
     * <font style='background-color: #9B6825;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B6825; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B6825;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B6825'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B6825'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B6825'>&nbsp;@&nbsp;</font><font style='background-color: #9B6825; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B6825;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B6825; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_BROWN = -0x1.1906ecp126F;
    static { NAMED.put("Strong Yellowish Brown", -0x1.1906ecp126F); LIST.add(-0x1.1906ecp126F); }

    /**
     * This color constant "Deep Yellowish Brown" has RGBA8888 code {@code 663E07FF}, L 0.28235295, A 0.5137255, B 0.5411765, alpha 1.0, hue 0.1945043, saturation 0.7803489, and chroma 0.08646853.
     * It can be represented as a packed float with the constant {@code -0x1.15069p126F}.
     * <pre>
     * <font style='background-color: #663E07;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #663E07; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #663E07;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #663E07'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #663E07'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #663E07'>&nbsp;@&nbsp;</font><font style='background-color: #663E07; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #663E07;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #663E07; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_BROWN = -0x1.15069p126F;
    static { NAMED.put("Deep Yellowish Brown", -0x1.15069p126F); LIST.add(-0x1.15069p126F); }

    /**
     * This color constant "Light Yellowish Brown" has RGBA8888 code {@code BE9C80FF}, L 0.64705884, A 0.50980395, B 0.52156866, alpha 1.0, hue 0.17620972, saturation 0.08694602, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0b054ap126F}.
     * <pre>
     * <font style='background-color: #BE9C80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE9C80; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE9C80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE9C80'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE9C80'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE9C80'>&nbsp;@&nbsp;</font><font style='background-color: #BE9C80; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE9C80;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE9C80; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_BROWN = -0x1.0b054ap126F;
    static { NAMED.put("Light Yellowish Brown", -0x1.0b054ap126F); LIST.add(-0x1.0b054ap126F); }

    /**
     * This color constant "Moderate Yellowish Brown" has RGBA8888 code {@code 7E6953FF}, L 0.4392157, A 0.5058824, B 0.5176471, alpha 1.0, hue 0.189452, saturation 0.09745852, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.0902ep126F}.
     * <pre>
     * <font style='background-color: #7E6953;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E6953; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E6953;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E6953'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E6953'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E6953'>&nbsp;@&nbsp;</font><font style='background-color: #7E6953; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E6953;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E6953; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_BROWN = -0x1.0902ep126F;
    static { NAMED.put("Moderate Yellowish Brown", -0x1.0902ep126F); LIST.add(-0x1.0902ep126F); }

    /**
     * This color constant "Dark Yellowish Brown" has RGBA8888 code {@code 4F3320FF}, L 0.22352941, A 0.5137255, B 0.52156866, alpha 1.0, hue 0.15640444, saturation 0.31987697, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0b0672p126F}.
     * <pre>
     * <font style='background-color: #4F3320;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F3320; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F3320;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F3320'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F3320'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F3320'>&nbsp;@&nbsp;</font><font style='background-color: #4F3320; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F3320;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F3320; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_BROWN = -0x1.0b0672p126F;
    static { NAMED.put("Dark Yellowish Brown", -0x1.0b0672p126F); LIST.add(-0x1.0b0672p126F); }

    /**
     * This color constant "Light Grayish Yellowish Brown" has RGBA8888 code {@code AF9D95FF}, L 0.6392157, A 0.5058824, B 0.5058824, alpha 1.0, hue 0.125, saturation 0.0136039965, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.030346p126F}.
     * <pre>
     * <font style='background-color: #AF9D95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF9D95; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF9D95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AF9D95'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AF9D95'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AF9D95'>&nbsp;@&nbsp;</font><font style='background-color: #AF9D95; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AF9D95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AF9D95; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_YELLOWISH_BROWN = -0x1.030346p126F;
    static { NAMED.put("Light Grayish Yellowish Brown", -0x1.030346p126F); LIST.add(-0x1.030346p126F); }

    /**
     * This color constant "Grayish Yellowish Brown" has RGBA8888 code {@code 796A63FF}, L 0.4392157, A 0.5058824, B 0.5058824, alpha 1.0, hue 0.125, saturation 0.016911084, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.0302ep126F}.
     * <pre>
     * <font style='background-color: #796A63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #796A63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #796A63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #796A63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #796A63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #796A63'>&nbsp;@&nbsp;</font><font style='background-color: #796A63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #796A63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #796A63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOWISH_BROWN = -0x1.0302ep126F;
    static { NAMED.put("Grayish Yellowish Brown", -0x1.0302ep126F); LIST.add(-0x1.0302ep126F); }

    /**
     * This color constant "Dark Grayish Yellowish Brown" has RGBA8888 code {@code 4A3D37FF}, L 0.2509804, A 0.5058824, B 0.5058824, alpha 1.0, hue 0.125, saturation 0.03224994, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.03028p126F}.
     * <pre>
     * <font style='background-color: #4A3D37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A3D37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A3D37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A3D37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A3D37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A3D37'>&nbsp;@&nbsp;</font><font style='background-color: #4A3D37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A3D37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A3D37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_YELLOWISH_BROWN = -0x1.03028p126F;
    static { NAMED.put("Dark Grayish Yellowish Brown", -0x1.03028p126F); LIST.add(-0x1.03028p126F); }

    /**
     * This color constant "Vivid Yellow" has RGBA8888 code {@code F6BE00FF}, L 0.77254903, A 0.49803922, B 0.58431375, alpha 1.0, hue 0.25, saturation 0.85806096, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.2aff8ap126F}.
     * <pre>
     * <font style='background-color: #F6BE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6BE00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6BE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6BE00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6BE00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6BE00'>&nbsp;@&nbsp;</font><font style='background-color: #F6BE00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6BE00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6BE00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOW = -0x1.2aff8ap126F;
    static { NAMED.put("Vivid Yellow", -0x1.2aff8ap126F); LIST.add(-0x1.2aff8ap126F); }

    /**
     * This color constant "Brilliant Yellow" has RGBA8888 code {@code F6CE65FF}, L 0.8156863, A 0.49803922, B 0.56078434, alpha 1.0, hue 0.25, saturation 0.4353279, and chroma 0.12115674.
     * It can be represented as a packed float with the constant {@code -0x1.1effap126F}.
     * <pre>
     * <font style='background-color: #F6CE65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6CE65; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6CE65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6CE65'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6CE65'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6CE65'>&nbsp;@&nbsp;</font><font style='background-color: #F6CE65; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6CE65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6CE65; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOW = -0x1.1effap126F;
    static { NAMED.put("Brilliant Yellow", -0x1.1effap126F); LIST.add(-0x1.1effap126F); }

    /**
     * This color constant "Strong Yellow" has RGBA8888 code {@code DFAC35FF}, L 0.70980394, A 0.5019608, B 0.5686275, alpha 1.0, hue 0.24117598, saturation 0.65648276, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.23016ap126F}.
     * <pre>
     * <font style='background-color: #DFAC35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFAC35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFAC35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DFAC35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DFAC35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DFAC35'>&nbsp;@&nbsp;</font><font style='background-color: #DFAC35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFAC35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFAC35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOW = -0x1.23016ap126F;
    static { NAMED.put("Strong Yellow", -0x1.23016ap126F); LIST.add(-0x1.23016ap126F); }

    /**
     * This color constant "Deep Yellow" has RGBA8888 code {@code BD8D07FF}, L 0.5921569, A 0.5019608, B 0.5686275, alpha 1.0, hue 0.24117598, saturation 0.8331998, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.23012ep126F}.
     * <pre>
     * <font style='background-color: #BD8D07;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD8D07; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD8D07;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD8D07'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD8D07'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD8D07'>&nbsp;@&nbsp;</font><font style='background-color: #BD8D07; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD8D07;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD8D07; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW = -0x1.23012ep126F;
    static { NAMED.put("Deep Yellow", -0x1.23012ep126F); LIST.add(-0x1.23012ep126F); }

    /**
     * This color constant "Light Yellow" has RGBA8888 code {@code F0D39CFF}, L 0.83137256, A 0.5019608, B 0.53333336, alpha 1.0, hue 0.23237891, saturation 0.27557236, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.1101a8p126F}.
     * <pre>
     * <font style='background-color: #F0D39C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0D39C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0D39C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0D39C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0D39C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0D39C'>&nbsp;@&nbsp;</font><font style='background-color: #F0D39C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0D39C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0D39C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOW = -0x1.1101a8p126F;
    static { NAMED.put("Light Yellow", -0x1.1101a8p126F); LIST.add(-0x1.1101a8p126F); }

    /**
     * This color constant "Moderate Yellow" has RGBA8888 code {@code D0AF6FFF}, L 0.7058824, A 0.5019608, B 0.5411765, alpha 1.0, hue 0.235567, saturation 0.24643353, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.150168p126F}.
     * <pre>
     * <font style='background-color: #D0AF6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0AF6F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0AF6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0AF6F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0AF6F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0AF6F'>&nbsp;@&nbsp;</font><font style='background-color: #D0AF6F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0AF6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0AF6F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOW = -0x1.150168p126F;
    static { NAMED.put("Moderate Yellow", -0x1.150168p126F); LIST.add(-0x1.150168p126F); }

    /**
     * This color constant "Dark Yellow" has RGBA8888 code {@code AE8F52FF}, L 0.5882353, A 0.5019608, B 0.5411765, alpha 1.0, hue 0.235567, saturation 0.3127704, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.15012cp126F}.
     * <pre>
     * <font style='background-color: #AE8F52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE8F52; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE8F52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE8F52'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE8F52'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE8F52'>&nbsp;@&nbsp;</font><font style='background-color: #AE8F52; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE8F52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE8F52; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOW = -0x1.15012cp126F;
    static { NAMED.put("Dark Yellow", -0x1.15012cp126F); LIST.add(-0x1.15012cp126F); }

    /**
     * This color constant "Pale Yellow" has RGBA8888 code {@code E9D8C1FF}, L 0.84313726, A 0.5019608, B 0.5137255, alpha 1.0, hue 0.21101315, saturation 0.09683161, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.0701aep126F}.
     * <pre>
     * <font style='background-color: #E9D8C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9D8C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9D8C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9D8C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9D8C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9D8C1'>&nbsp;@&nbsp;</font><font style='background-color: #E9D8C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9D8C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9D8C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.0701aep126F;
    static { NAMED.put("Pale Yellow", -0x1.0701aep126F); LIST.add(-0x1.0701aep126F); }

    /**
     * This color constant "Grayish Yellow" has RGBA8888 code {@code C2B29CFF}, L 0.70980394, A 0.5019608, B 0.5137255, alpha 1.0, hue 0.21101315, saturation 0.032846276, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.07016ap126F}.
     * <pre>
     * <font style='background-color: #C2B29C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2B29C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2B29C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C2B29C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C2B29C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C2B29C'>&nbsp;@&nbsp;</font><font style='background-color: #C2B29C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C2B29C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C2B29C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOW = -0x1.07016ap126F;
    static { NAMED.put("Grayish Yellow", -0x1.07016ap126F); LIST.add(-0x1.07016ap126F); }

    /**
     * This color constant "Dark Grayish Yellow" has RGBA8888 code {@code A49171FF}, L 0.5882353, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.22370392, saturation 0.09485659, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b012cp126F}.
     * <pre>
     * <font style='background-color: #A49171;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A49171; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A49171;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A49171'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A49171'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A49171'>&nbsp;@&nbsp;</font><font style='background-color: #A49171; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A49171;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A49171; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_YELLOW = -0x1.0b012cp126F;
    static { NAMED.put("Dark Grayish Yellow", -0x1.0b012cp126F); LIST.add(-0x1.0b012cp126F); }

    /**
     * This color constant "Yellowish White" has RGBA8888 code {@code F0DEDCFF}, L 0.87058824, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.073790275, saturation 0.10973937, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.0103bcp126F}.
     * <pre>
     * <font style='background-color: #F0DEDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0DEDC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0DEDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0DEDC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0DEDC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0DEDC'>&nbsp;@&nbsp;</font><font style='background-color: #F0DEDC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0DEDC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0DEDC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOWISH_WHITE = -0x1.0103bcp126F;
    static { NAMED.put("Yellowish White", -0x1.0103bcp126F); LIST.add(-0x1.0103bcp126F); }

    /**
     * This color constant "Yellowish Gray" has RGBA8888 code {@code C3BAB6FF}, L 0.7372549, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.0075739645, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.010178p126F}.
     * <pre>
     * <font style='background-color: #C3BAB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3BAB6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3BAB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C3BAB6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C3BAB6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C3BAB6'>&nbsp;@&nbsp;</font><font style='background-color: #C3BAB6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C3BAB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C3BAB6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOWISH_GRAY = -0x1.010178p126F;
    static { NAMED.put("Yellowish Gray", -0x1.010178p126F); LIST.add(-0x1.010178p126F); }

    /**
     * This color constant "Light Olive Brown" has RGBA8888 code {@code 937844FF}, L 0.49803922, A 0.5019608, B 0.5372549, alpha 1.0, hue 0.23413046, saturation 0.3205713, and chroma 0.07432148.
     * It can be represented as a packed float with the constant {@code -0x1.1300fep126F}.
     * <pre>
     * <font style='background-color: #937844;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #937844; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #937844;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #937844'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #937844'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #937844'>&nbsp;@&nbsp;</font><font style='background-color: #937844; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #937844;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #937844; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE_BROWN = -0x1.1300fep126F;
    static { NAMED.put("Light Olive Brown", -0x1.1300fep126F); LIST.add(-0x1.1300fep126F); }

    /**
     * This color constant "Moderate Olive Brown" has RGBA8888 code {@code 6D5423FF}, L 0.3529412, A 0.5019608, B 0.5372549, alpha 1.0, hue 0.23413046, saturation 0.4973838, and chroma 0.07432148.
     * It can be represented as a packed float with the constant {@code -0x1.1300b4p126F}.
     * <pre>
     * <font style='background-color: #6D5423;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D5423; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D5423;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6D5423'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6D5423'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6D5423'>&nbsp;@&nbsp;</font><font style='background-color: #6D5423; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6D5423;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6D5423; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE_BROWN = -0x1.1300b4p126F;
    static { NAMED.put("Moderate Olive Brown", -0x1.1300b4p126F); LIST.add(-0x1.1300b4p126F); }

    /**
     * This color constant "Dark Olive Brown" has RGBA8888 code {@code 3D2B16FF}, L 0.1764706, A 0.5058824, B 0.52156866, alpha 1.0, hue 0.19880433, saturation 0.38072577, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0b025ap126F}.
     * <pre>
     * <font style='background-color: #3D2B16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D2B16; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D2B16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3D2B16'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3D2B16'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3D2B16'>&nbsp;@&nbsp;</font><font style='background-color: #3D2B16; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D2B16;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D2B16; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE_BROWN = -0x1.0b025ap126F;
    static { NAMED.put("Dark Olive Brown", -0x1.0b025ap126F); LIST.add(-0x1.0b025ap126F); }

    /**
     * This color constant "Vivid Greenish Yellow" has RGBA8888 code {@code F3DB10FF}, L 0.8352941, A 0.47843137, B 0.5882353, alpha 1.0, hue 0.28407243, saturation 0.835517, and chroma 0.1809568.
     * It can be represented as a packed float with the constant {@code -0x1.2cf5aap126F}.
     * <pre>
     * <font style='background-color: #F3DB10;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3DB10; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3DB10;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3DB10'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3DB10'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3DB10'>&nbsp;@&nbsp;</font><font style='background-color: #F3DB10; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3DB10;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3DB10; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_GREENISH_YELLOW = -0x1.2cf5aap126F;
    static { NAMED.put("Vivid Greenish Yellow", -0x1.2cf5aap126F); LIST.add(-0x1.2cf5aap126F); }

    /**
     * This color constant "Brilliant Greenish Yellow" has RGBA8888 code {@code EADC62FF}, L 0.8352941, A 0.48235294, B 0.5647059, alpha 1.0, hue 0.2867793, saturation 0.4599868, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.20f7aap126F}.
     * <pre>
     * <font style='background-color: #EADC62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EADC62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EADC62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EADC62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EADC62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EADC62'>&nbsp;@&nbsp;</font><font style='background-color: #EADC62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EADC62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EADC62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREENISH_YELLOW = -0x1.20f7aap126F;
    static { NAMED.put("Brilliant Greenish Yellow", -0x1.20f7aap126F); LIST.add(-0x1.20f7aap126F); }

    /**
     * This color constant "Strong Greenish Yellow" has RGBA8888 code {@code C7B736FF}, L 0.70980394, A 0.48235294, B 0.5686275, alpha 1.0, hue 0.28480488, saturation 0.6569255, and chroma 0.1411665.
     * It can be represented as a packed float with the constant {@code -0x1.22f76ap126F}.
     * <pre>
     * <font style='background-color: #C7B736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7B736; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7B736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7B736'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7B736'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7B736'>&nbsp;@&nbsp;</font><font style='background-color: #C7B736; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7B736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7B736; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREENISH_YELLOW = -0x1.22f76ap126F;
    static { NAMED.put("Strong Greenish Yellow", -0x1.22f76ap126F); LIST.add(-0x1.22f76ap126F); }

    /**
     * This color constant "Deep Greenish Yellow" has RGBA8888 code {@code A69605FF}, L 0.5882353, A 0.48235294, B 0.5686275, alpha 1.0, hue 0.28480488, saturation 0.8291419, and chroma 0.1411665.
     * It can be represented as a packed float with the constant {@code -0x1.22f72cp126F}.
     * <pre>
     * <font style='background-color: #A69605;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A69605; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A69605;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A69605'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A69605'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A69605'>&nbsp;@&nbsp;</font><font style='background-color: #A69605; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A69605;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A69605; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREENISH_YELLOW = -0x1.22f72cp126F;
    static { NAMED.put("Deep Greenish Yellow", -0x1.22f72cp126F); LIST.add(-0x1.22f72cp126F); }

    /**
     * This color constant "Light Greenish Yellow" has RGBA8888 code {@code EADCA1FF}, L 0.84705883, A 0.49411765, B 0.53333336, alpha 1.0, hue 0.2676211, saturation 0.1338639, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.10fdbp126F}.
     * <pre>
     * <font style='background-color: #EADCA1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EADCA1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EADCA1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EADCA1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EADCA1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EADCA1'>&nbsp;@&nbsp;</font><font style='background-color: #EADCA1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EADCA1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EADCA1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_YELLOW = -0x1.10fdbp126F;
    static { NAMED.put("Light Greenish Yellow", -0x1.10fdbp126F); LIST.add(-0x1.10fdbp126F); }

    /**
     * This color constant "Moderate Greenish Yellow" has RGBA8888 code {@code C0B46FFF}, L 0.7019608, A 0.49019608, B 0.5411765, alpha 1.0, hue 0.27863455, saturation 0.24151672, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.14fb66p126F}.
     * <pre>
     * <font style='background-color: #C0B46F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0B46F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0B46F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0B46F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0B46F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0B46F'>&nbsp;@&nbsp;</font><font style='background-color: #C0B46F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0B46F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0B46F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREENISH_YELLOW = -0x1.14fb66p126F;
    static { NAMED.put("Moderate Greenish Yellow", -0x1.14fb66p126F); LIST.add(-0x1.14fb66p126F); }

    /**
     * This color constant "Dark Greenish Yellow" has RGBA8888 code {@code A0934AFF}, L 0.5803922, A 0.49019608, B 0.54509807, alpha 1.0, hue 0.27629608, saturation 0.37942636, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.16fb28p126F}.
     * <pre>
     * <font style='background-color: #A0934A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0934A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0934A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A0934A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A0934A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A0934A'>&nbsp;@&nbsp;</font><font style='background-color: #A0934A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0934A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0934A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_YELLOW = -0x1.16fb28p126F;
    static { NAMED.put("Dark Greenish Yellow", -0x1.16fb28p126F); LIST.add(-0x1.16fb28p126F); }

    /**
     * This color constant "Pale Greenish Yellow" has RGBA8888 code {@code E7DBBCFF}, L 0.84705883, A 0.49803922, B 0.5176471, alpha 1.0, hue 0.25, saturation 0.067465, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.08ffbp126F}.
     * <pre>
     * <font style='background-color: #E7DBBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7DBBC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7DBBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7DBBC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7DBBC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7DBBC'>&nbsp;@&nbsp;</font><font style='background-color: #E7DBBC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7DBBC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7DBBC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREENISH_YELLOW = -0x1.08ffbp126F;
    static { NAMED.put("Pale Greenish Yellow", -0x1.08ffbp126F); LIST.add(-0x1.08ffbp126F); }

    /**
     * This color constant "Grayish Greenish Yellow" has RGBA8888 code {@code BFB497FF}, L 0.70980394, A 0.49803922, B 0.5176471, alpha 1.0, hue 0.25, saturation 0.050498676, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.08ff6ap126F}.
     * <pre>
     * <font style='background-color: #BFB497;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFB497; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFB497;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFB497'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFB497'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFB497'>&nbsp;@&nbsp;</font><font style='background-color: #BFB497; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFB497;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFB497; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_GREENISH_YELLOW = -0x1.08ff6ap126F;
    static { NAMED.put("Grayish Greenish Yellow", -0x1.08ff6ap126F); LIST.add(-0x1.08ff6ap126F); }

    /**
     * This color constant "Light Olive" has RGBA8888 code {@code 8F7B37FF}, L 0.49803922, A 0.49411765, B 0.54509807, alpha 1.0, hue 0.26323372, saturation 0.46022615, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.16fcfep126F}.
     * <pre>
     * <font style='background-color: #8F7B37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F7B37; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F7B37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F7B37'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F7B37'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F7B37'>&nbsp;@&nbsp;</font><font style='background-color: #8F7B37; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F7B37;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F7B37; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE = -0x1.16fcfep126F;
    static { NAMED.put("Light Olive", -0x1.16fcfep126F); LIST.add(-0x1.16fcfep126F); }

    /**
     * This color constant "Moderate Olive" has RGBA8888 code {@code 655824FF}, L 0.3529412, A 0.49411765, B 0.5372549, alpha 1.0, hue 0.26586956, saturation 0.4642344, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.12fcb4p126F}.
     * <pre>
     * <font style='background-color: #655824;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #655824; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #655824;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #655824'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #655824'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #655824'>&nbsp;@&nbsp;</font><font style='background-color: #655824; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #655824;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #655824; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE = -0x1.12fcb4p126F;
    static { NAMED.put("Moderate Olive", -0x1.12fcb4p126F); LIST.add(-0x1.12fcb4p126F); }

    /**
     * This color constant "Dark Olive" has RGBA8888 code {@code 382C06FF}, L 0.17254902, A 0.49803922, B 0.5294118, alpha 1.0, hue 0.25, saturation 0.6732413, and chroma 0.058723815.
     * It can be represented as a packed float with the constant {@code -0x1.0efe58p126F}.
     * <pre>
     * <font style='background-color: #382C06;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #382C06; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #382C06;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #382C06'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #382C06'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #382C06'>&nbsp;@&nbsp;</font><font style='background-color: #382C06; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #382C06;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #382C06; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE = -0x1.0efe58p126F;
    static { NAMED.put("Dark Olive", -0x1.0efe58p126F); LIST.add(-0x1.0efe58p126F); }

    /**
     * This color constant "Light Grayish Olive" has RGBA8888 code {@code 8F847BFF}, L 0.5372549, A 0.5019608, B 0.5058824, alpha 1.0, hue 0.17620972, saturation 0.0121932635, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.030112p126F}.
     * <pre>
     * <font style='background-color: #8F847B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F847B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F847B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F847B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F847B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F847B'>&nbsp;@&nbsp;</font><font style='background-color: #8F847B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F847B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F847B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_OLIVE = -0x1.030112p126F;
    static { NAMED.put("Light Grayish Olive", -0x1.030112p126F); LIST.add(-0x1.030112p126F); }

    /**
     * This color constant "Grayish Olive" has RGBA8888 code {@code 5E5347FF}, L 0.3372549, A 0.5019608, B 0.50980395, alpha 1.0, hue 0.19880433, saturation 0.045963805, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.0500acp126F}.
     * <pre>
     * <font style='background-color: #5E5347;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E5347; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E5347;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5E5347'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5E5347'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5E5347'>&nbsp;@&nbsp;</font><font style='background-color: #5E5347; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5E5347;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5E5347; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_OLIVE = -0x1.0500acp126F;
    static { NAMED.put("Grayish Olive", -0x1.0500acp126F); LIST.add(-0x1.0500acp126F); }

    /**
     * This color constant "Dark Grayish Olive" has RGBA8888 code {@code 352F1FFF}, L 0.18039216, A 0.49803922, B 0.5137255, alpha 1.0, hue 0.25, saturation 0.1522903, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.06fe5cp126F}.
     * <pre>
     * <font style='background-color: #352F1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #352F1F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #352F1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #352F1F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #352F1F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #352F1F'>&nbsp;@&nbsp;</font><font style='background-color: #352F1F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #352F1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #352F1F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_OLIVE = -0x1.06fe5cp126F;
    static { NAMED.put("Dark Grayish Olive", -0x1.06fe5cp126F); LIST.add(-0x1.06fe5cp126F); }

    /**
     * This color constant "Light Olive Gray" has RGBA8888 code {@code 8F8682FF}, L 0.54509807, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.0031369473, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.010116p126F}.
     * <pre>
     * <font style='background-color: #8F8682;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8682; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8682;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8682'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8682'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8682'>&nbsp;@&nbsp;</font><font style='background-color: #8F8682; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8682;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8682; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE_GRAY = -0x1.010116p126F;
    static { NAMED.put("Light Olive Gray", -0x1.010116p126F); LIST.add(-0x1.010116p126F); }

    /**
     * This color constant "Olive Gray" has RGBA8888 code {@code 58514EFF}, L 0.32941177, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.006004879, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.0100a8p126F}.
     * <pre>
     * <font style='background-color: #58514E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #58514E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #58514E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #58514E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #58514E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #58514E'>&nbsp;@&nbsp;</font><font style='background-color: #58514E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #58514E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #58514E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_GRAY = -0x1.0100a8p126F;
    static { NAMED.put("Olive Gray", -0x1.0100a8p126F); LIST.add(-0x1.0100a8p126F); }

    /**
     * This color constant "Olive Black" has RGBA8888 code {@code 241F1DFF}, L 0.11372549, A 0.5019608, B 0.5019608, alpha 1.0, hue 0.125, saturation 0.019036287, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.01003ap126F}.
     * <pre>
     * <font style='background-color: #241F1D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #241F1D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #241F1D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #241F1D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #241F1D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #241F1D'>&nbsp;@&nbsp;</font><font style='background-color: #241F1D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #241F1D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #241F1D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_BLACK = -0x1.01003ap126F;
    static { NAMED.put("Olive Black", -0x1.01003ap126F); LIST.add(-0x1.01003ap126F); }

    /**
     * This color constant "Vivid Yellow Green" has RGBA8888 code {@code B1DB25FF}, L 0.7764706, A 0.44705883, B 0.5803922, alpha 1.0, hue 0.33823052, saturation 0.7643512, and chroma 0.19176465.
     * It can be represented as a packed float with the constant {@code -0x1.28e58cp126F}.
     * <pre>
     * <font style='background-color: #B1DB25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1DB25; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1DB25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1DB25'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1DB25'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1DB25'>&nbsp;@&nbsp;</font><font style='background-color: #B1DB25; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1DB25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1DB25; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOW_GREEN = -0x1.28e58cp126F;
    static { NAMED.put("Vivid Yellow Green", -0x1.28e58cp126F); LIST.add(-0x1.28e58cp126F); }

    /**
     * This color constant "Brilliant Yellow Green" has RGBA8888 code {@code CBDC75FF}, L 0.80784315, A 0.47058824, B 0.5529412, alpha 1.0, hue 0.32379028, saturation 0.32993856, and chroma 0.120651916.
     * It can be represented as a packed float with the constant {@code -0x1.1af19cp126F}.
     * <pre>
     * <font style='background-color: #CBDC75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBDC75; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBDC75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBDC75'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBDC75'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBDC75'>&nbsp;@&nbsp;</font><font style='background-color: #CBDC75; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBDC75;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBDC75; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOW_GREEN = -0x1.1af19cp126F;
    static { NAMED.put("Brilliant Yellow Green", -0x1.1af19cp126F); LIST.add(-0x1.1af19cp126F); }

    /**
     * This color constant "Strong Yellow Green" has RGBA8888 code {@code 869F34FF}, L 0.58431375, A 0.4627451, B 0.5568628, alpha 1.0, hue 0.33601886, saturation 0.566077, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.1ced2ap126F}.
     * <pre>
     * <font style='background-color: #869F34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #869F34; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #869F34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #869F34'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #869F34'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #869F34'>&nbsp;@&nbsp;</font><font style='background-color: #869F34; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #869F34;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #869F34; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOW_GREEN = -0x1.1ced2ap126F;
    static { NAMED.put("Strong Yellow Green", -0x1.1ced2ap126F); LIST.add(-0x1.1ced2ap126F); }

    /**
     * This color constant "Deep Yellow Green" has RGBA8888 code {@code 4B6B10FF}, L 0.38039216, A 0.45882353, B 0.54901963, alpha 1.0, hue 0.35436952, saturation 0.7259234, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.18eac2p126F}.
     * <pre>
     * <font style='background-color: #4B6B10;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B6B10; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B6B10;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B6B10'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B6B10'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B6B10'>&nbsp;@&nbsp;</font><font style='background-color: #4B6B10; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B6B10;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B6B10; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_GREEN = -0x1.18eac2p126F;
    static { NAMED.put("Deep Yellow Green", -0x1.18eac2p126F); LIST.add(-0x1.18eac2p126F); }

    /**
     * This color constant "Light Yellow Green" has RGBA8888 code {@code D4D8B5FF}, L 0.8235294, A 0.49019608, B 0.5176471, alpha 1.0, hue 0.310548, saturation 0.040527556, and chroma 0.040217306.
     * It can be represented as a packed float with the constant {@code -0x1.08fba4p126F}.
     * <pre>
     * <font style='background-color: #D4D8B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4D8B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4D8B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4D8B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4D8B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4D8B5'>&nbsp;@&nbsp;</font><font style='background-color: #D4D8B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4D8B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4D8B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOW_GREEN = -0x1.08fba4p126F;
    static { NAMED.put("Light Yellow Green", -0x1.08fba4p126F); LIST.add(-0x1.08fba4p126F); }

    /**
     * This color constant "Moderate Yellow Green" has RGBA8888 code {@code 92976BFF}, L 0.58431375, A 0.4862745, B 0.5254902, alpha 1.0, hue 0.3144313, saturation 0.12260536, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.0cf92ap126F}.
     * <pre>
     * <font style='background-color: #92976B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92976B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92976B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #92976B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #92976B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #92976B'>&nbsp;@&nbsp;</font><font style='background-color: #92976B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #92976B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #92976B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOW_GREEN = -0x1.0cf92ap126F;
    static { NAMED.put("Moderate Yellow Green", -0x1.0cf92ap126F); LIST.add(-0x1.0cf92ap126F); }

    /**
     * This color constant "Pale Yellow Green" has RGBA8888 code {@code DBD6CAFF}, L 0.83137256, A 0.49803922, B 0.5058824, alpha 1.0, hue 0.25, saturation 0.008455542, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.02ffa8p126F}.
     * <pre>
     * <font style='background-color: #DBD6CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBD6CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBD6CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBD6CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBD6CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBD6CA'>&nbsp;@&nbsp;</font><font style='background-color: #DBD6CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBD6CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBD6CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_GREEN = -0x1.02ffa8p126F;
    static { NAMED.put("Pale Yellow Green", -0x1.02ffa8p126F); LIST.add(-0x1.02ffa8p126F); }

    /**
     * This color constant "Grayish Yellow Green" has RGBA8888 code {@code 9C978CFF}, L 0.6039216, A 0.49803922, B 0.5058824, alpha 1.0, hue 0.25, saturation 0.010254767, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.02ff34p126F}.
     * <pre>
     * <font style='background-color: #9C978C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C978C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C978C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C978C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C978C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C978C'>&nbsp;@&nbsp;</font><font style='background-color: #9C978C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C978C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C978C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOW_GREEN = -0x1.02ff34p126F;
    static { NAMED.put("Grayish Yellow Green", -0x1.02ff34p126F); LIST.add(-0x1.02ff34p126F); }

    /**
     * This color constant "Strong Olive Green" has RGBA8888 code {@code 31540AFF}, L 0.28627452, A 0.45882353, B 0.5411765, alpha 1.0, hue 0.36743265, saturation 0.70144814, and chroma 0.116009705.
     * It can be represented as a packed float with the constant {@code -0x1.14ea92p126F}.
     * <pre>
     * <font style='background-color: #31540A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31540A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31540A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #31540A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #31540A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #31540A'>&nbsp;@&nbsp;</font><font style='background-color: #31540A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31540A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31540A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_OLIVE_GREEN = -0x1.14ea92p126F;
    static { NAMED.put("Strong Olive Green", -0x1.14ea92p126F); LIST.add(-0x1.14ea92p126F); }

    /**
     * This color constant "Moderate Olive Green" has RGBA8888 code {@code 4E5826FF}, L 0.3254902, A 0.47843137, B 0.53333336, alpha 1.0, hue 0.33071172, saturation 0.40142012, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.10f4a6p126F}.
     * <pre>
     * <font style='background-color: #4E5826;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E5826; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E5826;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E5826'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E5826'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E5826'>&nbsp;@&nbsp;</font><font style='background-color: #4E5826; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E5826;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E5826; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE_GREEN = -0x1.10f4a6p126F;
    static { NAMED.put("Moderate Olive Green", -0x1.10f4a6p126F); LIST.add(-0x1.10f4a6p126F); }

    /**
     * This color constant "Dark Olive Green" has RGBA8888 code {@code 233205FF}, L 0.16470589, A 0.4745098, B 0.5294118, alpha 1.0, hue 0.3524291, saturation 0.666389, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.0ef254p126F}.
     * <pre>
     * <font style='background-color: #233205;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #233205; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #233205;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #233205'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #233205'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #233205'>&nbsp;@&nbsp;</font><font style='background-color: #233205; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #233205;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #233205; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE_GREEN = -0x1.0ef254p126F;
    static { NAMED.put("Dark Olive Green", -0x1.0ef254p126F); LIST.add(-0x1.0ef254p126F); }

    /**
     * This color constant "Grayish Olive Green" has RGBA8888 code {@code 59564DFF}, L 0.34509805, A 0.49803922, B 0.5058824, alpha 1.0, hue 0.25, saturation 0.019698368, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.02febp126F}.
     * <pre>
     * <font style='background-color: #59564D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #59564D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #59564D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #59564D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #59564D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #59564D'>&nbsp;@&nbsp;</font><font style='background-color: #59564D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #59564D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #59564D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_OLIVE_GREEN = -0x1.02febp126F;
    static { NAMED.put("Grayish Olive Green", -0x1.02febp126F); LIST.add(-0x1.02febp126F); }

    /**
     * This color constant "Dark Grayish Olive Green" has RGBA8888 code {@code 2F3128FF}, L 0.18039216, A 0.49411765, B 0.5058824, alpha 1.0, hue 0.32379028, saturation 0.039506175, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.02fc5cp126F}.
     * <pre>
     * <font style='background-color: #2F3128;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F3128; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F3128;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F3128'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F3128'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F3128'>&nbsp;@&nbsp;</font><font style='background-color: #2F3128; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F3128;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F3128; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_OLIVE_GREEN = -0x1.02fc5cp126F;
    static { NAMED.put("Dark Grayish Olive Green", -0x1.02fc5cp126F); LIST.add(-0x1.02fc5cp126F); }

    /**
     * This color constant "Vivid Yellowish Green" has RGBA8888 code {@code 50D54CFF}, L 0.7019608, A 0.41568628, B 0.56078434, alpha 1.0, hue 0.39636543, saturation 0.5609376, and chroma 0.2070681.
     * It can be represented as a packed float with the constant {@code -0x1.1ed566p126F}.
     * <pre>
     * <font style='background-color: #50D54C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50D54C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50D54C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #50D54C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #50D54C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #50D54C'>&nbsp;@&nbsp;</font><font style='background-color: #50D54C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #50D54C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #50D54C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOWISH_GREEN = -0x1.1ed566p126F;
    static { NAMED.put("Vivid Yellowish Green", -0x1.1ed566p126F); LIST.add(-0x1.1ed566p126F); }

    /**
     * This color constant "Brilliant Yellowish Green" has RGBA8888 code {@code A5D198FF}, L 0.7607843, A 0.46666667, B 0.5254902, alpha 1.0, hue 0.3855869, saturation 0.085992865, and chroma 0.0835974.
     * It can be represented as a packed float with the constant {@code -0x1.0cef84p126F}.
     * <pre>
     * <font style='background-color: #A5D198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5D198; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5D198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A5D198'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A5D198'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A5D198'>&nbsp;@&nbsp;</font><font style='background-color: #A5D198; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5D198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5D198; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOWISH_GREEN = -0x1.0cef84p126F;
    static { NAMED.put("Brilliant Yellowish Green", -0x1.0cef84p126F); LIST.add(-0x1.0cef84p126F); }

    /**
     * This color constant "Strong Yellowish Green" has RGBA8888 code {@code 42944EFF}, L 0.5058824, A 0.44313726, B 0.53333336, alpha 1.0, hue 0.40905774, saturation 0.4344672, and chroma 0.13131043.
     * It can be represented as a packed float with the constant {@code -0x1.10e302p126F}.
     * <pre>
     * <font style='background-color: #42944E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42944E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42944E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #42944E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #42944E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #42944E'>&nbsp;@&nbsp;</font><font style='background-color: #42944E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42944E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42944E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_GREEN = -0x1.10e302p126F;
    static { NAMED.put("Strong Yellowish Green", -0x1.10e302p126F); LIST.add(-0x1.10e302p126F); }

    /**
     * This color constant "Deep Yellowish Green" has RGBA8888 code {@code 22681CFF}, L 0.34509805, A 0.44313726, B 0.5411765, alpha 1.0, hue 0.39399585, saturation 0.64032316, and chroma 0.13986339.
     * It can be represented as a packed float with the constant {@code -0x1.14e2bp126F}.
     * <pre>
     * <font style='background-color: #22681C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #22681C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #22681C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #22681C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #22681C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #22681C'>&nbsp;@&nbsp;</font><font style='background-color: #22681C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #22681C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #22681C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_GREEN = -0x1.14e2bp126F;
    static { NAMED.put("Deep Yellowish Green", -0x1.14e2bp126F); LIST.add(-0x1.14e2bp126F); }

    /**
     * This color constant "Very Deep Yellowish Green" has RGBA8888 code {@code 114006FF}, L 0.2, A 0.45490196, B 0.53333336, alpha 1.0, hue 0.39085212, saturation 0.7649704, and chroma 0.11172148.
     * It can be represented as a packed float with the constant {@code -0x1.10e866p126F}.
     * <pre>
     * <font style='background-color: #114006;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #114006; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #114006;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #114006'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #114006'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #114006'>&nbsp;@&nbsp;</font><font style='background-color: #114006; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #114006;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #114006; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_YELLOWISH_GREEN = -0x1.10e866p126F;
    static { NAMED.put("Very Deep Yellowish Green", -0x1.10e866p126F); LIST.add(-0x1.10e866p126F); }

    /**
     * This color constant "Very Light Yellowish Green" has RGBA8888 code {@code D6E7CEFF}, L 0.8666667, A 0.4862745, B 0.50980395, alpha 1.0, hue 0.375, saturation 0.027146762, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.04f9bap126F}.
     * <pre>
     * <font style='background-color: #D6E7CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6E7CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6E7CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6E7CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6E7CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6E7CE'>&nbsp;@&nbsp;</font><font style='background-color: #D6E7CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6E7CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6E7CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_YELLOWISH_GREEN = -0x1.04f9bap126F;
    static { NAMED.put("Very Light Yellowish Green", -0x1.04f9bap126F); LIST.add(-0x1.04f9bap126F); }

    /**
     * This color constant "Light Yellowish Green" has RGBA8888 code {@code AFBFA7FF}, L 0.7294118, A 0.4862745, B 0.50980395, alpha 1.0, hue 0.375, saturation 0.016281305, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.04f974p126F}.
     * <pre>
     * <font style='background-color: #AFBFA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFBFA7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFBFA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFBFA7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFBFA7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFBFA7'>&nbsp;@&nbsp;</font><font style='background-color: #AFBFA7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFBFA7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFBFA7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_GREEN = -0x1.04f974p126F;
    static { NAMED.put("Light Yellowish Green", -0x1.04f974p126F); LIST.add(-0x1.04f974p126F); }

    /**
     * This color constant "Moderate Yellowish Green" has RGBA8888 code {@code 788A6EFF}, L 0.5254902, A 0.48235294, B 0.5137255, alpha 1.0, hue 0.375, saturation 0.043094017, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.06f70cp126F}.
     * <pre>
     * <font style='background-color: #788A6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #788A6E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #788A6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #788A6E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #788A6E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #788A6E'>&nbsp;@&nbsp;</font><font style='background-color: #788A6E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #788A6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #788A6E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_GREEN = -0x1.06f70cp126F;
    static { NAMED.put("Moderate Yellowish Green", -0x1.06f70cp126F); LIST.add(-0x1.06f70cp126F); }

    /**
     * This color constant "Dark Yellowish Green" has RGBA8888 code {@code 3D593FFF}, L 0.32156864, A 0.4745098, B 0.5137255, alpha 1.0, hue 0.40640444, saturation 0.14791112, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.06f2a4p126F}.
     * <pre>
     * <font style='background-color: #3D593F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D593F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D593F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3D593F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3D593F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3D593F'>&nbsp;@&nbsp;</font><font style='background-color: #3D593F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3D593F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3D593F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_GREEN = -0x1.06f2a4p126F;
    static { NAMED.put("Dark Yellowish Green", -0x1.06f2a4p126F); LIST.add(-0x1.06f2a4p126F); }

    /**
     * This color constant "Very Dark Yellowish Green" has RGBA8888 code {@code 1D3319FF}, L 0.16470589, A 0.4745098, B 0.5176471, alpha 1.0, hue 0.38941902, saturation 0.2803792, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.08f254p126F}.
     * <pre>
     * <font style='background-color: #1D3319;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D3319; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D3319;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1D3319'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1D3319'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1D3319'>&nbsp;@&nbsp;</font><font style='background-color: #1D3319; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1D3319;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1D3319; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_YELLOWISH_GREEN = -0x1.08f254p126F;
    static { NAMED.put("Very Dark Yellowish Green", -0x1.08f254p126F); LIST.add(-0x1.08f254p126F); }

    /**
     * This color constant "Vivid Green" has RGBA8888 code {@code 3AEAA4FF}, L 0.77254903, A 0.41960785, B 0.52156866, alpha 1.0, hue 0.45362332, saturation 0.7117641, and chroma 0.1658202.
     * It can be represented as a packed float with the constant {@code -0x1.0ad78ap126F}.
     * <pre>
     * <font style='background-color: #3AEAA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3AEAA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3AEAA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3AEAA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3AEAA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3AEAA4'>&nbsp;@&nbsp;</font><font style='background-color: #3AEAA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3AEAA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3AEAA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_GREEN = -0x1.0ad78ap126F;
    static { NAMED.put("Vivid Green", -0x1.0ad78ap126F); LIST.add(-0x1.0ad78ap126F); }

    /**
     * This color constant "Brilliant Green" has RGBA8888 code {@code 68CBA4FF}, L 0.7058824, A 0.44705883, B 0.50980395, alpha 1.0, hue 0.4639029, saturation 0.35955057, and chroma 0.107261956.
     * It can be represented as a packed float with the constant {@code -0x1.04e568p126F}.
     * <pre>
     * <font style='background-color: #68CBA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68CBA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68CBA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #68CBA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #68CBA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #68CBA4'>&nbsp;@&nbsp;</font><font style='background-color: #68CBA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #68CBA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #68CBA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREEN = -0x1.04e568p126F;
    static { NAMED.put("Brilliant Green", -0x1.04e568p126F); LIST.add(-0x1.04e568p126F); }

    /**
     * This color constant "Strong Green" has RGBA8888 code {@code 338767FF}, L 0.46666667, A 0.4509804, B 0.50980395, alpha 1.0, hue 0.46101317, saturation 0.5141777, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.04e6eep126F}.
     * <pre>
     * <font style='background-color: #338767;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #338767; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #338767;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #338767'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #338767'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #338767'>&nbsp;@&nbsp;</font><font style='background-color: #338767; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #338767;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #338767; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREEN = -0x1.04e6eep126F;
    static { NAMED.put("Strong Green", -0x1.04e6eep126F); LIST.add(-0x1.04e6eep126F); }

    /**
     * This color constant "Very Light Green" has RGBA8888 code {@code C0DBC7FF}, L 0.81960785, A 0.48235294, B 0.5058824, alpha 1.0, hue 0.42620972, saturation 0.023376433, and chroma 0.03705794.
     * It can be represented as a packed float with the constant {@code -0x1.02f7a2p126F}.
     * <pre>
     * <font style='background-color: #C0DBC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0DBC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0DBC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0DBC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0DBC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0DBC7'>&nbsp;@&nbsp;</font><font style='background-color: #C0DBC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0DBC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0DBC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_GREEN = -0x1.02f7a2p126F;
    static { NAMED.put("Very Light Green", -0x1.02f7a2p126F); LIST.add(-0x1.02f7a2p126F); }

    /**
     * This color constant "Light Green" has RGBA8888 code {@code 81A893FF}, L 0.62352943, A 0.4745098, B 0.5058824, alpha 1.0, hue 0.44880432, saturation 0.08079788, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.02f33ep126F}.
     * <pre>
     * <font style='background-color: #81A893;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81A893; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81A893;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #81A893'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #81A893'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #81A893'>&nbsp;@&nbsp;</font><font style='background-color: #81A893; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #81A893;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #81A893; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREEN = -0x1.02f33ep126F;
    static { NAMED.put("Light Green", -0x1.02f33ep126F); LIST.add(-0x1.02f33ep126F); }

    /**
     * This color constant "Moderate Green" has RGBA8888 code {@code 42745FFF}, L 0.41568628, A 0.46666667, B 0.5058824, alpha 1.0, hue 0.46101317, saturation 0.27412447, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.02eed4p126F}.
     * <pre>
     * <font style='background-color: #42745F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42745F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42745F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #42745F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #42745F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #42745F'>&nbsp;@&nbsp;</font><font style='background-color: #42745F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42745F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42745F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREEN = -0x1.02eed4p126F;
    static { NAMED.put("Moderate Green", -0x1.02eed4p126F); LIST.add(-0x1.02eed4p126F); }

    /**
     * This color constant "Dark Green" has RGBA8888 code {@code 264C3BFF}, L 0.2627451, A 0.47058824, B 0.5058824, alpha 1.0, hue 0.45571566, saturation 0.32602844, and chroma 0.059754133.
     * It can be represented as a packed float with the constant {@code -0x1.02f086p126F}.
     * <pre>
     * <font style='background-color: #264C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #264C3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #264C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #264C3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #264C3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #264C3B'>&nbsp;@&nbsp;</font><font style='background-color: #264C3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #264C3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #264C3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREEN = -0x1.02f086p126F;
    static { NAMED.put("Dark Green", -0x1.02f086p126F); LIST.add(-0x1.02f086p126F); }

    /**
     * This color constant "Very Dark Green" has RGBA8888 code {@code 1A2B24FF}, L 0.14117648, A 0.48235294, B 0.5019608, alpha 1.0, hue 0.46101317, saturation 0.22204082, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.00f648p126F}.
     * <pre>
     * <font style='background-color: #1A2B24;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A2B24; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A2B24;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1A2B24'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1A2B24'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1A2B24'>&nbsp;@&nbsp;</font><font style='background-color: #1A2B24; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A2B24;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A2B24; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_GREEN = -0x1.00f648p126F;
    static { NAMED.put("Very Dark Green", -0x1.00f648p126F); LIST.add(-0x1.00f648p126F); }

    /**
     * This color constant "Very Pale Green" has RGBA8888 code {@code D7D5CFFF}, L 0.827451, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.25, saturation 0.002019947, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.00ffa6p126F}.
     * <pre>
     * <font style='background-color: #D7D5CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7D5CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7D5CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7D5CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7D5CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7D5CF'>&nbsp;@&nbsp;</font><font style='background-color: #D7D5CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7D5CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7D5CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_GREEN = -0x1.00ffa6p126F;
    static { NAMED.put("Very Pale Green", -0x1.00ffa6p126F); LIST.add(-0x1.00ffa6p126F); }

    /**
     * This color constant "Pale Green" has RGBA8888 code {@code A4A29CFF}, L 0.6431373, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.25, saturation 0.0023225432, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.00ff48p126F}.
     * <pre>
     * <font style='background-color: #A4A29C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A29C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A29C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4A29C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4A29C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4A29C'>&nbsp;@&nbsp;</font><font style='background-color: #A4A29C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A29C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A29C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.00ff48p126F;
    static { NAMED.put("Pale Green", -0x1.00ff48p126F); LIST.add(-0x1.00ff48p126F); }

    /**
     * This color constant "Grayish Green" has RGBA8888 code {@code 6B6F68FF}, L 0.4392157, A 0.49411765, B 0.5019608, alpha 1.0, hue 0.375, saturation 0.0034009991, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.00fcep126F}.
     * <pre>
     * <font style='background-color: #6B6F68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B6F68; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B6F68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6B6F68'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6B6F68'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6B6F68'>&nbsp;@&nbsp;</font><font style='background-color: #6B6F68; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B6F68;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B6F68; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_GREEN = -0x1.00fcep126F;
    static { NAMED.put("Grayish Green", -0x1.00fcep126F); LIST.add(-0x1.00fcep126F); }

    /**
     * This color constant "Dark Grayish Green" has RGBA8888 code {@code 45433FFF}, L 0.2627451, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.25, saturation 0.00666389, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.00fe86p126F}.
     * <pre>
     * <font style='background-color: #45433F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #45433F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #45433F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #45433F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #45433F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #45433F'>&nbsp;@&nbsp;</font><font style='background-color: #45433F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #45433F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #45433F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_GREEN = -0x1.00fe86p126F;
    static { NAMED.put("Dark Grayish Green", -0x1.00fe86p126F); LIST.add(-0x1.00fe86p126F); }

    /**
     * This color constant "Blackish Green" has RGBA8888 code {@code 242827FF}, L 0.14117648, A 0.49411765, B 0.49803922, alpha 1.0, hue 0.49998704, saturation 0.014692378, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fefc48p125F}.
     * <pre>
     * <font style='background-color: #242827;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #242827; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #242827;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #242827'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #242827'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #242827'>&nbsp;@&nbsp;</font><font style='background-color: #242827; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #242827;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #242827; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_GREEN = -0x1.fefc48p125F;
    static { NAMED.put("Blackish Green", -0x1.fefc48p125F); LIST.add(-0x1.fefc48p125F); }

    /**
     * This color constant "Greenish White" has RGBA8888 code {@code E8E1E3FF}, L 0.8745098, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.021947874, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff01bep125F}.
     * <pre>
     * <font style='background-color: #E8E1E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8E1E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8E1E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E8E1E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E8E1E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E8E1E3'>&nbsp;@&nbsp;</font><font style='background-color: #E8E1E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E8E1E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E8E1E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_WHITE = -0x1.ff01bep125F;
    static { NAMED.put("Greenish White", -0x1.ff01bep125F); LIST.add(-0x1.ff01bep125F); }

    /**
     * This color constant "Light Greenish Gray" has RGBA8888 code {@code C4BCBEFF}, L 0.74509805, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.0040312423, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff017cp125F}.
     * <pre>
     * <font style='background-color: #C4BCBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4BCBE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4BCBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4BCBE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4BCBE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4BCBE'>&nbsp;@&nbsp;</font><font style='background-color: #C4BCBE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4BCBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4BCBE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_GRAY = -0x1.ff017cp125F;
    static { NAMED.put("Light Greenish Gray", -0x1.ff017cp125F); LIST.add(-0x1.ff017cp125F); }

    /**
     * This color constant "Greenish Gray" has RGBA8888 code {@code 898782FF}, L 0.5411765, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.25, saturation 0.0028444445, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.00ff14p126F}.
     * <pre>
     * <font style='background-color: #898782;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #898782; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #898782;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #898782'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #898782'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #898782'>&nbsp;@&nbsp;</font><font style='background-color: #898782; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #898782;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #898782; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_GRAY = -0x1.00ff14p126F;
    static { NAMED.put("Greenish Gray", -0x1.00ff14p126F); LIST.add(-0x1.00ff14p126F); }

    /**
     * This color constant "Dark Greenish Gray" has RGBA8888 code {@code 595752FF}, L 0.34901962, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.25, saturation 0.004924592, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.00feb2p126F}.
     * <pre>
     * <font style='background-color: #595752;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #595752; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #595752;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #595752'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #595752'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #595752'>&nbsp;@&nbsp;</font><font style='background-color: #595752; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #595752;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #595752; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_GRAY = -0x1.00feb2p126F;
    static { NAMED.put("Dark Greenish Gray", -0x1.00feb2p126F); LIST.add(-0x1.00feb2p126F); }

    /**
     * This color constant "Greenish Black" has RGBA8888 code {@code 252421FF}, L 0.12941177, A 0.49803922, B 0.5019608, alpha 1.0, hue 0.25, saturation 0.0130612245, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.00fe42p126F}.
     * <pre>
     * <font style='background-color: #252421;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #252421; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #252421;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #252421'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #252421'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #252421'>&nbsp;@&nbsp;</font><font style='background-color: #252421; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #252421;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #252421; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_BLACK = -0x1.00fe42p126F;
    static { NAMED.put("Greenish Black", -0x1.00fe42p126F); LIST.add(-0x1.00fe42p126F); }

    /**
     * This color constant "Vivid Bluish Green" has RGBA8888 code {@code 2BFCD4FF}, L 0.83137256, A 0.41960785, B 0.5019608, alpha 1.0, hue 0.4920594, saturation 0.77478564, and chroma 0.16020387.
     * It can be represented as a packed float with the constant {@code -0x1.00d7a8p126F}.
     * <pre>
     * <font style='background-color: #2BFCD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2BFCD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2BFCD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2BFCD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2BFCD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2BFCD4'>&nbsp;@&nbsp;</font><font style='background-color: #2BFCD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2BFCD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2BFCD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_BLUISH_GREEN = -0x1.00d7a8p126F;
    static { NAMED.put("Vivid Bluish Green", -0x1.00d7a8p126F); LIST.add(-0x1.00d7a8p126F); }

    /**
     * This color constant "Brilliant Bluish Green" has RGBA8888 code {@code 54D5CAFF}, L 0.7372549, A 0.44313726, B 0.49019608, alpha 1.0, hue 0.52259654, saturation 0.51273835, and chroma 0.114952646.
     * It can be represented as a packed float with the constant {@code -0x1.fae378p125F}.
     * <pre>
     * <font style='background-color: #54D5CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54D5CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54D5CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #54D5CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #54D5CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #54D5CA'>&nbsp;@&nbsp;</font><font style='background-color: #54D5CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #54D5CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #54D5CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_BLUISH_GREEN = -0x1.fae378p125F;
    static { NAMED.put("Brilliant Bluish Green", -0x1.fae378p125F); LIST.add(-0x1.fae378p125F); }

    /**
     * This color constant "Strong Bluish Green" has RGBA8888 code {@code 218E7CFF}, L 0.49019608, A 0.44705883, B 0.49803922, alpha 1.0, hue 0.49998704, saturation 0.6812799, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.fee4fap125F}.
     * <pre>
     * <font style='background-color: #218E7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #218E7C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #218E7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #218E7C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #218E7C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #218E7C'>&nbsp;@&nbsp;</font><font style='background-color: #218E7C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #218E7C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #218E7C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BLUISH_GREEN = -0x1.fee4fap125F;
    static { NAMED.put("Strong Bluish Green", -0x1.fee4fap125F); LIST.add(-0x1.fee4fap125F); }

    /**
     * This color constant "Very Light Bluish Green" has RGBA8888 code {@code B2DCD9FF}, L 0.8156863, A 0.47843137, B 0.49411765, alpha 1.0, hue 0.53142345, saturation 0.06038612, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf5ap125F}.
     * <pre>
     * <font style='background-color: #B2DCD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2DCD9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2DCD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B2DCD9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B2DCD9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B2DCD9'>&nbsp;@&nbsp;</font><font style='background-color: #B2DCD9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B2DCD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B2DCD9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_BLUISH_GREEN = -0x1.fcf5ap125F;
    static { NAMED.put("Very Light Bluish Green", -0x1.fcf5ap125F); LIST.add(-0x1.fcf5ap125F); }

    /**
     * This color constant "Light Bluish Green" has RGBA8888 code {@code 76A6A8FF}, L 0.61960787, A 0.4745098, B 0.49019608, alpha 1.0, hue 0.5511957, saturation 0.13442554, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.faf33cp125F}.
     * <pre>
     * <font style='background-color: #76A6A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76A6A8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76A6A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #76A6A8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #76A6A8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #76A6A8'>&nbsp;@&nbsp;</font><font style='background-color: #76A6A8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #76A6A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #76A6A8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUISH_GREEN = -0x1.faf33cp125F;
    static { NAMED.put("Light Bluish Green", -0x1.faf33cp125F); LIST.add(-0x1.faf33cp125F); }

    /**
     * This color constant "Moderate Bluish Green" has RGBA8888 code {@code 3B7776FF}, L 0.42745098, A 0.46666667, B 0.49019608, alpha 1.0, hue 0.53898686, saturation 0.35966942, and chroma 0.069218926.
     * It can be represented as a packed float with the constant {@code -0x1.faeedap125F}.
     * <pre>
     * <font style='background-color: #3B7776;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7776; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7776;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B7776'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B7776'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B7776'>&nbsp;@&nbsp;</font><font style='background-color: #3B7776; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7776;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7776; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BLUISH_GREEN = -0x1.faeedap125F;
    static { NAMED.put("Moderate Bluish Green", -0x1.faeedap125F); LIST.add(-0x1.faeedap125F); }

    /**
     * This color constant "Dark Bluish Green" has RGBA8888 code {@code 2A4846FF}, L 0.25490198, A 0.47843137, B 0.49411765, alpha 1.0, hue 0.53142345, saturation 0.24747175, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf482p125F}.
     * <pre>
     * <font style='background-color: #2A4846;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2A4846; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2A4846;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2A4846'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2A4846'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2A4846'>&nbsp;@&nbsp;</font><font style='background-color: #2A4846; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2A4846;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2A4846; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUISH_GREEN = -0x1.fcf482p125F;
    static { NAMED.put("Dark Bluish Green", -0x1.fcf482p125F); LIST.add(-0x1.fcf482p125F); }

    /**
     * This color constant "Very Dark Bluish Green" has RGBA8888 code {@code 102C2BFF}, L 0.14117648, A 0.47843137, B 0.49411765, alpha 1.0, hue 0.53142345, saturation 0.43288243, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.fcf448p125F}.
     * <pre>
     * <font style='background-color: #102C2B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #102C2B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #102C2B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #102C2B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #102C2B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #102C2B'>&nbsp;@&nbsp;</font><font style='background-color: #102C2B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #102C2B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #102C2B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_BLUISH_GREEN = -0x1.fcf448p125F;
    static { NAMED.put("Very Dark Bluish Green", -0x1.fcf448p125F); LIST.add(-0x1.fcf448p125F); }

    /**
     * This color constant "Brilliant Greenish Blue" has RGBA8888 code {@code 39BCDEFF}, L 0.6666667, A 0.4509804, B 0.4627451, alpha 1.0, hue 0.6024291, saturation 0.607185, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.ece754p125F}.
     * <pre>
     * <font style='background-color: #39BCDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #39BCDE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #39BCDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #39BCDE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #39BCDE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #39BCDE'>&nbsp;@&nbsp;</font><font style='background-color: #39BCDE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #39BCDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #39BCDE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREENISH_BLUE = -0x1.ece754p125F;
    static { NAMED.put("Brilliant Greenish Blue", -0x1.ece754p125F); LIST.add(-0x1.ece754p125F); }

    /**
     * This color constant "Strong Greenish Blue" has RGBA8888 code {@code 2484ACFF}, L 0.48235294, A 0.4627451, B 0.45882353, alpha 1.0, hue 0.63336253, saturation 0.6082756, and chroma 0.11062346.
     * It can be represented as a packed float with the constant {@code -0x1.eaecf6p125F}.
     * <pre>
     * <font style='background-color: #2484AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2484AC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2484AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2484AC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2484AC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2484AC'>&nbsp;@&nbsp;</font><font style='background-color: #2484AC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2484AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2484AC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREENISH_BLUE = -0x1.eaecf6p125F;
    static { NAMED.put("Strong Greenish Blue", -0x1.eaecf6p125F); LIST.add(-0x1.eaecf6p125F); }

    /**
     * This color constant "Very Light Greenish Blue" has RGBA8888 code {@code A4D4E4FF}, L 0.7882353, A 0.47843137, B 0.48235294, alpha 1.0, hue 0.6073997, saturation 0.16528092, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.f6f592p125F}.
     * <pre>
     * <font style='background-color: #A4D4E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4D4E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4D4E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4D4E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4D4E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4D4E4'>&nbsp;@&nbsp;</font><font style='background-color: #A4D4E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4D4E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4D4E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_GREENISH_BLUE = -0x1.f6f592p125F;
    static { NAMED.put("Very Light Greenish Blue", -0x1.f6f592p125F); LIST.add(-0x1.f6f592p125F); }

    /**
     * This color constant "Light Greenish Blue" has RGBA8888 code {@code 7FA4B4FF}, L 0.627451, A 0.48235294, B 0.48235294, alpha 1.0, hue 0.625, saturation 0.082038134, and chroma 0.049718447.
     * It can be represented as a packed float with the constant {@code -0x1.f6f74p125F}.
     * <pre>
     * <font style='background-color: #7FA4B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FA4B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FA4B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FA4B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FA4B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FA4B4'>&nbsp;@&nbsp;</font><font style='background-color: #7FA4B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FA4B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FA4B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_BLUE = -0x1.f6f74p125F;
    static { NAMED.put("Light Greenish Blue", -0x1.f6f74p125F); LIST.add(-0x1.f6f74p125F); }

    /**
     * This color constant "Moderate Greenish Blue" has RGBA8888 code {@code 3F7489FF}, L 0.43137255, A 0.4745098, B 0.4745098, alpha 1.0, hue 0.625, saturation 0.29024944, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.f2f2dcp125F}.
     * <pre>
     * <font style='background-color: #3F7489;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F7489; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F7489;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F7489'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F7489'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F7489'>&nbsp;@&nbsp;</font><font style='background-color: #3F7489; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F7489;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F7489; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREENISH_BLUE = -0x1.f2f2dcp125F;
    static { NAMED.put("Moderate Greenish Blue", -0x1.f2f2dcp125F); LIST.add(-0x1.f2f2dcp125F); }

    /**
     * This color constant "Dark Greenish Blue" has RGBA8888 code {@code 224858FF}, L 0.25882354, A 0.47843137, B 0.47843137, alpha 1.0, hue 0.625, saturation 0.3621548, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.f4f484p125F}.
     * <pre>
     * <font style='background-color: #224858;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #224858; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #224858;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #224858'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #224858'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #224858'>&nbsp;@&nbsp;</font><font style='background-color: #224858; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #224858;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #224858; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_BLUE = -0x1.f4f484p125F;
    static { NAMED.put("Dark Greenish Blue", -0x1.f4f484p125F); LIST.add(-0x1.f4f484p125F); }

    /**
     * This color constant "Very Dark Greenish Blue" has RGBA8888 code {@code 162B36FF}, L 0.14901961, A 0.4862745, B 0.48235294, alpha 1.0, hue 0.6475709, saturation 0.26298487, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.f6f84cp125F}.
     * <pre>
     * <font style='background-color: #162B36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #162B36; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #162B36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #162B36'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #162B36'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #162B36'>&nbsp;@&nbsp;</font><font style='background-color: #162B36; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #162B36;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #162B36; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_GREENISH_BLUE = -0x1.f6f84cp125F;
    static { NAMED.put("Very Dark Greenish Blue", -0x1.f6f84cp125F); LIST.add(-0x1.f6f84cp125F); }

    /**
     * This color constant "Vivid Blue" has RGBA8888 code {@code 2F5FC2FF}, L 0.4, A 0.4862745, B 0.41960785, alpha 1.0, hue 0.7262905, saturation 0.33869883, and chroma 0.16247371.
     * It can be represented as a packed float with the constant {@code -0x1.d6f8ccp125F}.
     * <pre>
     * <font style='background-color: #2F5FC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F5FC2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F5FC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F5FC2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F5FC2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F5FC2'>&nbsp;@&nbsp;</font><font style='background-color: #2F5FC2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F5FC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F5FC2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_BLUE = -0x1.d6f8ccp125F;
    static { NAMED.put("Vivid Blue", -0x1.d6f8ccp125F); LIST.add(-0x1.d6f8ccp125F); }

    /**
     * This color constant "Brilliant Blue" has RGBA8888 code {@code 529CE2FF}, L 0.59607846, A 0.4745098, B 0.44313726, alpha 1.0, hue 0.6855687, saturation 0.42918256, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.e2f33p125F}.
     * <pre>
     * <font style='background-color: #529CE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #529CE2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #529CE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #529CE2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #529CE2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #529CE2'>&nbsp;@&nbsp;</font><font style='background-color: #529CE2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #529CE2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #529CE2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_BLUE = -0x1.e2f33p125F;
    static { NAMED.put("Brilliant Blue", -0x1.e2f33p125F); LIST.add(-0x1.e2f33p125F); }

    /**
     * This color constant "Strong Blue" has RGBA8888 code {@code 3A6CAEFF}, L 0.42745098, A 0.48235294, B 0.44313726, alpha 1.0, hue 0.70571566, saturation 0.29627043, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.e2f6dap125F}.
     * <pre>
     * <font style='background-color: #3A6CAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A6CAE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A6CAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A6CAE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A6CAE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A6CAE'>&nbsp;@&nbsp;</font><font style='background-color: #3A6CAE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A6CAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A6CAE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BLUE = -0x1.e2f6dap125F;
    static { NAMED.put("Strong Blue", -0x1.e2f6dap125F); LIST.add(-0x1.e2f6dap125F); }

    /**
     * This color constant "Deep Blue" has RGBA8888 code {@code 203262FF}, L 0.20392157, A 0.49411765, B 0.4509804, alpha 1.0, hue 0.7367663, saturation 0.14384028, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.e6fc68p125F}.
     * <pre>
     * <font style='background-color: #203262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #203262; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #203262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #203262'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #203262'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #203262'>&nbsp;@&nbsp;</font><font style='background-color: #203262; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #203262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #203262; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE = -0x1.e6fc68p125F;
    static { NAMED.put("Deep Blue", -0x1.e6fc68p125F); LIST.add(-0x1.e6fc68p125F); }

    /**
     * This color constant "Very Light Blue" has RGBA8888 code {@code A5C4F1FF}, L 0.75686276, A 0.49019608, B 0.46666667, alpha 1.0, hue 0.71101314, saturation 0.41830066, and chroma 0.069218926.
     * It can be represented as a packed float with the constant {@code -0x1.eefb82p125F}.
     * <pre>
     * <font style='background-color: #A5C4F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5C4F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5C4F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A5C4F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A5C4F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A5C4F1'>&nbsp;@&nbsp;</font><font style='background-color: #A5C4F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5C4F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5C4F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_BLUE = -0x1.eefb82p125F;
    static { NAMED.put("Very Light Blue", -0x1.eefb82p125F); LIST.add(-0x1.eefb82p125F); }

    /**
     * This color constant "Light Blue" has RGBA8888 code {@code 7FA4CDFF}, L 0.63529414, A 0.4862745, B 0.46666667, alpha 1.0, hue 0.6929125, saturation 0.17802164, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.eef944p125F}.
     * <pre>
     * <font style='background-color: #7FA4CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FA4CD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FA4CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FA4CD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FA4CD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FA4CD'>&nbsp;@&nbsp;</font><font style='background-color: #7FA4CD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FA4CD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FA4CD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUE = -0x1.eef944p125F;
    static { NAMED.put("Light Blue", -0x1.eef944p125F); LIST.add(-0x1.eef944p125F); }

    /**
     * This color constant "Moderate Blue" has RGBA8888 code {@code 45678FFF}, L 0.4, A 0.4862745, B 0.4627451, alpha 1.0, hue 0.6988043, saturation 0.16649324, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.ecf8ccp125F}.
     * <pre>
     * <font style='background-color: #45678F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #45678F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #45678F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #45678F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #45678F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #45678F'>&nbsp;@&nbsp;</font><font style='background-color: #45678F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #45678F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #45678F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BLUE = -0x1.ecf8ccp125F;
    static { NAMED.put("Moderate Blue", -0x1.ecf8ccp125F); LIST.add(-0x1.ecf8ccp125F); }

    /**
     * This color constant "Dark Blue" has RGBA8888 code {@code 20344DFF}, L 0.19215687, A 0.49019608, B 0.47058824, alpha 1.0, hue 0.70571566, saturation 0.17811385, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.f0fa62p125F}.
     * <pre>
     * <font style='background-color: #20344D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20344D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20344D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #20344D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #20344D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #20344D'>&nbsp;@&nbsp;</font><font style='background-color: #20344D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20344D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20344D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUE = -0x1.f0fa62p125F;
    static { NAMED.put("Dark Blue", -0x1.f0fa62p125F); LIST.add(-0x1.f0fa62p125F); }

    /**
     * This color constant "Very Pale Blue" has RGBA8888 code {@code CBD1E4FF}, L 0.8156863, A 0.49803922, B 0.4862745, alpha 1.0, hue 0.75, saturation 0.10518627, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.f8ffap125F}.
     * <pre>
     * <font style='background-color: #CBD1E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBD1E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBD1E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBD1E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBD1E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBD1E4'>&nbsp;@&nbsp;</font><font style='background-color: #CBD1E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBD1E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBD1E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_BLUE = -0x1.f8ffap125F;
    static { NAMED.put("Very Pale Blue", -0x1.f8ffap125F); LIST.add(-0x1.f8ffap125F); }

    /**
     * This color constant "Pale Blue" has RGBA8888 code {@code 9DA1ACFF}, L 0.6392157, A 0.49803922, B 0.49019608, alpha 1.0, hue 0.75, saturation 0.0107944, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.faff46p125F}.
     * <pre>
     * <font style='background-color: #9DA1AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9DA1AC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9DA1AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9DA1AC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9DA1AC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9DA1AC'>&nbsp;@&nbsp;</font><font style='background-color: #9DA1AC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9DA1AC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9DA1AC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.faff46p125F;
    static { NAMED.put("Pale Blue", -0x1.faff46p125F); LIST.add(-0x1.faff46p125F); }

    /**
     * This color constant "Grayish Blue" has RGBA8888 code {@code 5D676FFF}, L 0.40784314, A 0.49411765, B 0.49019608, alpha 1.0, hue 0.6762097, saturation 0.0128184585, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fafcdp125F}.
     * <pre>
     * <font style='background-color: #5D676F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D676F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D676F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5D676F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5D676F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5D676F'>&nbsp;@&nbsp;</font><font style='background-color: #5D676F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5D676F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5D676F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_BLUE = -0x1.fafcdp125F;
    static { NAMED.put("Grayish Blue", -0x1.fafcdp125F); LIST.add(-0x1.fafcdp125F); }

    /**
     * This color constant "Dark Grayish Blue" has RGBA8888 code {@code 3B3D41FF}, L 0.23529412, A 0.49803922, B 0.49411765, alpha 1.0, hue 0.75, saturation 9.0451696E-4, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fcfe78p125F}.
     * <pre>
     * <font style='background-color: #3B3D41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B3D41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B3D41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B3D41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B3D41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B3D41'>&nbsp;@&nbsp;</font><font style='background-color: #3B3D41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B3D41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B3D41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_BLUE = -0x1.fcfe78p125F;
    static { NAMED.put("Dark Grayish Blue", -0x1.fcfe78p125F); LIST.add(-0x1.fcfe78p125F); }

    /**
     * This color constant "Blackish Blue" has RGBA8888 code {@code 21242BFF}, L 0.12941177, A 0.49803922, B 0.49019608, alpha 1.0, hue 0.75, saturation 0.007091413, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.fafe42p125F}.
     * <pre>
     * <font style='background-color: #21242B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #21242B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #21242B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #21242B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #21242B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #21242B'>&nbsp;@&nbsp;</font><font style='background-color: #21242B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #21242B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #21242B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_BLUE = -0x1.fafe42p125F;
    static { NAMED.put("Blackish Blue", -0x1.fafe42p125F); LIST.add(-0x1.fafe42p125F); }

    /**
     * This color constant "Bluish White" has RGBA8888 code {@code E6E1E9FF}, L 0.8745098, A 0.5019608, B 0.49411765, alpha 1.0, hue 0.875, saturation 0.026122449, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fd01bep125F}.
     * <pre>
     * <font style='background-color: #E6E1E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6E1E9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6E1E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6E1E9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6E1E9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6E1E9'>&nbsp;@&nbsp;</font><font style='background-color: #E6E1E9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6E1E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6E1E9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_WHITE = -0x1.fd01bep125F;
    static { NAMED.put("Bluish White", -0x1.fd01bep125F); LIST.add(-0x1.fd01bep125F); }

    /**
     * This color constant "Light Bluish Gray" has RGBA8888 code {@code BFB8BAFF}, L 0.73333335, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.0037869823, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff0176p125F}.
     * <pre>
     * <font style='background-color: #BFB8BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFB8BA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFB8BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFB8BA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFB8BA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFB8BA'>&nbsp;@&nbsp;</font><font style='background-color: #BFB8BA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFB8BA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFB8BA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUISH_GRAY = -0x1.ff0176p125F;
    static { NAMED.put("Light Bluish Gray", -0x1.ff0176p125F); LIST.add(-0x1.ff0176p125F); }

    /**
     * This color constant "Bluish Gray" has RGBA8888 code {@code 8C8587FF}, L 0.5411765, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 8.524695E-4, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff0114p125F}.
     * <pre>
     * <font style='background-color: #8C8587;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C8587; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C8587;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C8587'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C8587'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C8587'>&nbsp;@&nbsp;</font><font style='background-color: #8C8587; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C8587;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C8587; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_GRAY = -0x1.ff0114p125F;
    static { NAMED.put("Bluish Gray", -0x1.ff0114p125F); LIST.add(-0x1.ff0114p125F); }

    /**
     * This color constant "Dark Bluish Gray" has RGBA8888 code {@code 585354FF}, L 0.3372549, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.0014512471, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff00acp125F}.
     * <pre>
     * <font style='background-color: #585354;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #585354; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #585354;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #585354'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #585354'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #585354'>&nbsp;@&nbsp;</font><font style='background-color: #585354; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #585354;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #585354; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUISH_GRAY = -0x1.ff00acp125F;
    static { NAMED.put("Dark Bluish Gray", -0x1.ff00acp125F); LIST.add(-0x1.ff00acp125F); }

    /**
     * This color constant "Bluish Black" has RGBA8888 code {@code 2A2627FF}, L 0.14509805, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.0037869823, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff004ap125F}.
     * <pre>
     * <font style='background-color: #2A2627;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2A2627; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2A2627;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2A2627'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2A2627'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2A2627'>&nbsp;@&nbsp;</font><font style='background-color: #2A2627; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2A2627;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2A2627; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_BLACK = -0x1.ff004ap125F;
    static { NAMED.put("Bluish Black", -0x1.ff004ap125F); LIST.add(-0x1.ff004ap125F); }

    /**
     * This color constant "Vivid Purplish Blue" has RGBA8888 code {@code 463EBEFF}, L 0.3372549, A 0.5137255, B 0.40392157, alpha 1.0, hue 0.7762961, saturation 0.394256, and chroma 0.19334951.
     * It can be represented as a packed float with the constant {@code -0x1.cf06acp125F}.
     * <pre>
     * <font style='background-color: #463EBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #463EBE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #463EBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #463EBE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #463EBE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #463EBE'>&nbsp;@&nbsp;</font><font style='background-color: #463EBE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #463EBE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #463EBE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLISH_BLUE = -0x1.cf06acp125F;
    static { NAMED.put("Vivid Purplish Blue", -0x1.cf06acp125F); LIST.add(-0x1.cf06acp125F); }

    /**
     * This color constant "Brilliant Purplish Blue" has RGBA8888 code {@code 838AD6FF}, L 0.5803922, A 0.5058824, B 0.44705883, alpha 1.0, hue 0.77430767, saturation 0.320037, and chroma 0.10611779.
     * It can be represented as a packed float with the constant {@code -0x1.e50328p125F}.
     * <pre>
     * <font style='background-color: #838AD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #838AD6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #838AD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #838AD6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #838AD6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #838AD6'>&nbsp;@&nbsp;</font><font style='background-color: #838AD6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #838AD6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #838AD6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLISH_BLUE = -0x1.e50328p125F;
    static { NAMED.put("Brilliant Purplish Blue", -0x1.e50328p125F); LIST.add(-0x1.e50328p125F); }

    /**
     * This color constant "Strong Purplish Blue" has RGBA8888 code {@code 575CA0FF}, L 0.39607844, A 0.5058824, B 0.44705883, alpha 1.0, hue 0.77430767, saturation 0.13165279, and chroma 0.10611779.
     * It can be represented as a packed float with the constant {@code -0x1.e502cap125F}.
     * <pre>
     * <font style='background-color: #575CA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #575CA0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #575CA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #575CA0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #575CA0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #575CA0'>&nbsp;@&nbsp;</font><font style='background-color: #575CA0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #575CA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #575CA0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_BLUE = -0x1.e502cap125F;
    static { NAMED.put("Strong Purplish Blue", -0x1.e502cap125F); LIST.add(-0x1.e502cap125F); }

    /**
     * This color constant "Deep Purplish Blue" has RGBA8888 code {@code 2B2C5FFF}, L 0.19215687, A 0.5058824, B 0.4509804, alpha 1.0, hue 0.7762961, saturation 0.1921922, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.e70262p125F}.
     * <pre>
     * <font style='background-color: #2B2C5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B2C5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B2C5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2B2C5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2B2C5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2B2C5F'>&nbsp;@&nbsp;</font><font style='background-color: #2B2C5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B2C5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B2C5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_BLUE = -0x1.e70262p125F;
    static { NAMED.put("Deep Purplish Blue", -0x1.e70262p125F); LIST.add(-0x1.e70262p125F); }

    /**
     * This color constant "Very Light Purplish Blue" has RGBA8888 code {@code BAC1EDFF}, L 0.7647059, A 0.5019608, B 0.47058824, alpha 1.0, hue 0.77259654, saturation 0.3331945, and chroma 0.058723815.
     * It can be represented as a packed float with the constant {@code -0x1.f10186p125F}.
     * <pre>
     * <font style='background-color: #BAC1ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BAC1ED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BAC1ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BAC1ED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BAC1ED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BAC1ED'>&nbsp;@&nbsp;</font><font style='background-color: #BAC1ED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BAC1ED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BAC1ED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_PURPLISH_BLUE = -0x1.f10186p125F;
    static { NAMED.put("Very Light Purplish Blue", -0x1.f10186p125F); LIST.add(-0x1.f10186p125F); }

    /**
     * This color constant "Light Purplish Blue" has RGBA8888 code {@code 8F93B5FF}, L 0.59607846, A 0.5019608, B 0.4745098, alpha 1.0, hue 0.7762961, saturation 0.07473804, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.f3013p125F}.
     * <pre>
     * <font style='background-color: #8F93B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F93B5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F93B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F93B5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F93B5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F93B5'>&nbsp;@&nbsp;</font><font style='background-color: #8F93B5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F93B5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F93B5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_BLUE = -0x1.f3013p125F;
    static { NAMED.put("Light Purplish Blue", -0x1.f3013p125F); LIST.add(-0x1.f3013p125F); }

    /**
     * This color constant "Moderate Purplish Blue" has RGBA8888 code {@code 53506EFF}, L 0.33333334, A 0.5058824, B 0.4745098, alpha 1.0, hue 0.8011957, saturation 0.031297375, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.f302aap125F}.
     * <pre>
     * <font style='background-color: #53506E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53506E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53506E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #53506E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #53506E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #53506E'>&nbsp;@&nbsp;</font><font style='background-color: #53506E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53506E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53506E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_BLUE = -0x1.f302aap125F;
    static { NAMED.put("Moderate Purplish Blue", -0x1.f302aap125F); LIST.add(-0x1.f302aap125F); }

    /**
     * This color constant "Dark Purplish Blue" has RGBA8888 code {@code 26233BFF}, L 0.14117648, A 0.5058824, B 0.4745098, alpha 1.0, hue 0.8011957, saturation 0.084555425, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.f30248p125F}.
     * <pre>
     * <font style='background-color: #26233B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #26233B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #26233B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #26233B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #26233B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #26233B'>&nbsp;@&nbsp;</font><font style='background-color: #26233B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #26233B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #26233B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_BLUE = -0x1.f30248p125F;
    static { NAMED.put("Dark Purplish Blue", -0x1.f30248p125F); LIST.add(-0x1.f30248p125F); }

    /**
     * This color constant "Very Pale Purplish Blue" has RGBA8888 code {@code CACBE5FF}, L 0.8, A 0.5019608, B 0.48235294, alpha 1.0, hue 0.78898686, saturation 0.16180845, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.f70198p125F}.
     * <pre>
     * <font style='background-color: #CACBE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CACBE5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CACBE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CACBE5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CACBE5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CACBE5'>&nbsp;@&nbsp;</font><font style='background-color: #CACBE5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CACBE5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CACBE5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_PURPLISH_BLUE = -0x1.f70198p125F;
    static { NAMED.put("Very Pale Purplish Blue", -0x1.f70198p125F); LIST.add(-0x1.f70198p125F); }

    /**
     * This color constant "Pale Purplish Blue" has RGBA8888 code {@code 9493A5FF}, L 0.59607846, A 0.5019608, B 0.4862745, alpha 1.0, hue 0.8011957, saturation 0.017728532, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.f9013p125F}.
     * <pre>
     * <font style='background-color: #9493A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9493A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9493A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9493A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9493A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9493A5'>&nbsp;@&nbsp;</font><font style='background-color: #9493A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9493A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9493A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLISH_BLUE = -0x1.f9013p125F;
    static { NAMED.put("Pale Purplish Blue", -0x1.f9013p125F); LIST.add(-0x1.f9013p125F); }

    /**
     * This color constant "Grayish Purplish Blue" has RGBA8888 code {@code 4F4E5CFF}, L 0.31764707, A 0.5019608, B 0.4862745, alpha 1.0, hue 0.8011957, saturation 0.008281145, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.f900a2p125F}.
     * <pre>
     * <font style='background-color: #4F4E5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F4E5C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F4E5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F4E5C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F4E5C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F4E5C'>&nbsp;@&nbsp;</font><font style='background-color: #4F4E5C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F4E5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F4E5C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_BLUE = -0x1.f900a2p125F;
    static { NAMED.put("Grayish Purplish Blue", -0x1.f900a2p125F); LIST.add(-0x1.f900a2p125F); }

    /**
     * This color constant "Vivid Violet" has RGBA8888 code {@code 7A39C4FF}, L 0.39215687, A 0.5529412, B 0.4117647, alpha 1.0, hue 0.84020853, saturation 0.5034939, and chroma 0.2049944.
     * It can be represented as a packed float with the constant {@code -0x1.d31ac8p125F}.
     * <pre>
     * <font style='background-color: #7A39C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A39C4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A39C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A39C4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A39C4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A39C4'>&nbsp;@&nbsp;</font><font style='background-color: #7A39C4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A39C4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A39C4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_VIOLET = -0x1.d31ac8p125F;
    static { NAMED.put("Vivid Violet", -0x1.d31ac8p125F); LIST.add(-0x1.d31ac8p125F); }

    /**
     * This color constant "Brilliant Violet" has RGBA8888 code {@code 9883CFFF}, L 0.5803922, A 0.52156866, B 0.4509804, alpha 1.0, hue 0.8237903, saturation 0.26122448, and chroma 0.106691405.
     * It can be represented as a packed float with the constant {@code -0x1.e70b28p125F}.
     * <pre>
     * <font style='background-color: #9883CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9883CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9883CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9883CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9883CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9883CF'>&nbsp;@&nbsp;</font><font style='background-color: #9883CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9883CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9883CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_VIOLET = -0x1.e70b28p125F;
    static { NAMED.put("Brilliant Violet", -0x1.e70b28p125F); LIST.add(-0x1.e70b28p125F); }

    /**
     * This color constant "Strong Violet" has RGBA8888 code {@code 614886FF}, L 0.34509805, A 0.5254902, B 0.45490196, alpha 1.0, hue 0.84020853, saturation 0.14924555, and chroma 0.10320191.
     * It can be represented as a packed float with the constant {@code -0x1.e90cbp125F}.
     * <pre>
     * <font style='background-color: #614886;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #614886; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #614886;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #614886'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #614886'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #614886'>&nbsp;@&nbsp;</font><font style='background-color: #614886; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #614886;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #614886; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_VIOLET = -0x1.e90cbp125F;
    static { NAMED.put("Strong Violet", -0x1.e90cbp125F); LIST.add(-0x1.e90cbp125F); }

    /**
     * This color constant "Deep Violet" has RGBA8888 code {@code 3E1860FF}, L 0.1764706, A 0.5372549, B 0.44313726, alpha 1.0, hue 0.84872866, saturation 0.57191163, and chroma 0.13542919.
     * It can be represented as a packed float with the constant {@code -0x1.e3125ap125F}.
     * <pre>
     * <font style='background-color: #3E1860;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E1860; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E1860;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3E1860'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3E1860'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3E1860'>&nbsp;@&nbsp;</font><font style='background-color: #3E1860; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E1860;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E1860; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET = -0x1.e3125ap125F;
    static { NAMED.put("Deep Violet", -0x1.e3125ap125F); LIST.add(-0x1.e3125ap125F); }

    /**
     * This color constant "Very Light Violet" has RGBA8888 code {@code CCBCEDFF}, L 0.77254903, A 0.5137255, B 0.47058824, alpha 1.0, hue 0.8326307, saturation 0.34380165, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.f1078ap125F}.
     * <pre>
     * <font style='background-color: #CCBCED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCBCED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCBCED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CCBCED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CCBCED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CCBCED'>&nbsp;@&nbsp;</font><font style='background-color: #CCBCED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCBCED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCBCED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_VIOLET = -0x1.f1078ap125F;
    static { NAMED.put("Very Light Violet", -0x1.f1078ap125F); LIST.add(-0x1.f1078ap125F); }

    /**
     * This color constant "Light Violet" has RGBA8888 code {@code 9E8EB6FF}, L 0.6, A 0.5137255, B 0.4745098, alpha 1.0, hue 0.84359556, saturation 0.0726701, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.f30732p125F}.
     * <pre>
     * <font style='background-color: #9E8EB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E8EB6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E8EB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E8EB6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E8EB6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E8EB6'>&nbsp;@&nbsp;</font><font style='background-color: #9E8EB6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E8EB6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E8EB6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_VIOLET = -0x1.f30732p125F;
    static { NAMED.put("Light Violet", -0x1.f30732p125F); LIST.add(-0x1.f30732p125F); }

    /**
     * This color constant "Moderate Violet" has RGBA8888 code {@code 5C4E6FFF}, L 0.34117648, A 0.5137255, B 0.4745098, alpha 1.0, hue 0.84359556, saturation 0.045651577, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.f306aep125F}.
     * <pre>
     * <font style='background-color: #5C4E6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C4E6F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C4E6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5C4E6F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5C4E6F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5C4E6F'>&nbsp;@&nbsp;</font><font style='background-color: #5C4E6F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5C4E6F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5C4E6F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_VIOLET = -0x1.f306aep125F;
    static { NAMED.put("Moderate Violet", -0x1.f306aep125F); LIST.add(-0x1.f306aep125F); }

    /**
     * This color constant "Dark Violet" has RGBA8888 code {@code 332742FF}, L 0.17254902, A 0.5137255, B 0.4745098, alpha 1.0, hue 0.84359556, saturation 0.10047096, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.f30658p125F}.
     * <pre>
     * <font style='background-color: #332742;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #332742; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #332742;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #332742'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #332742'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #332742'>&nbsp;@&nbsp;</font><font style='background-color: #332742; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #332742;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #332742; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_VIOLET = -0x1.f30658p125F;
    static { NAMED.put("Dark Violet", -0x1.f30658p125F); LIST.add(-0x1.f30658p125F); }

    /**
     * This color constant "Very Pale Violet" has RGBA8888 code {@code D4C6E4FF}, L 0.79607844, A 0.50980395, B 0.48235294, alpha 1.0, hue 0.8524291, saturation 0.14239943, and chroma 0.040217306.
     * It can be represented as a packed float with the constant {@code -0x1.f70596p125F}.
     * <pre>
     * <font style='background-color: #D4C6E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4C6E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4C6E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D4C6E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D4C6E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D4C6E4'>&nbsp;@&nbsp;</font><font style='background-color: #D4C6E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D4C6E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D4C6E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_VIOLET = -0x1.f70596p125F;
    static { NAMED.put("Very Pale Violet", -0x1.f70596p125F); LIST.add(-0x1.f70596p125F); }

    /**
     * This color constant "Pale Violet" has RGBA8888 code {@code 9A92A6FF}, L 0.6, A 0.5058824, B 0.4862745, alpha 1.0, hue 0.84359556, saturation 0.018167526, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.f90332p125F}.
     * <pre>
     * <font style='background-color: #9A92A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A92A6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A92A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9A92A6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9A92A6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9A92A6'>&nbsp;@&nbsp;</font><font style='background-color: #9A92A6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9A92A6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9A92A6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.f90332p125F;
    static { NAMED.put("Pale Violet", -0x1.f90332p125F); LIST.add(-0x1.f90332p125F); }

    /**
     * This color constant "Grayish Violet" has RGBA8888 code {@code 5B4F61FF}, L 0.33333334, A 0.50980395, B 0.4862745, alpha 1.0, hue 0.875, saturation 0.018432, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.f904aap125F}.
     * <pre>
     * <font style='background-color: #5B4F61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B4F61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B4F61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B4F61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B4F61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B4F61'>&nbsp;@&nbsp;</font><font style='background-color: #5B4F61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B4F61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B4F61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_VIOLET = -0x1.f904aap125F;
    static { NAMED.put("Grayish Violet", -0x1.f904aap125F); LIST.add(-0x1.f904aap125F); }

    /**
     * This color constant "Vivid Purple" has RGBA8888 code {@code B73ACDFF}, L 0.4862745, A 0.5882353, B 0.42745098, alpha 1.0, hue 0.89430183, saturation 0.56807494, and chroma 0.22757049.
     * It can be represented as a packed float with the constant {@code -0x1.db2cf8p125F}.
     * <pre>
     * <font style='background-color: #B73ACD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B73ACD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B73ACD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B73ACD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B73ACD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B73ACD'>&nbsp;@&nbsp;</font><font style='background-color: #B73ACD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B73ACD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B73ACD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLE = -0x1.db2cf8p125F;
    static { NAMED.put("Vivid Purple", -0x1.db2cf8p125F); LIST.add(-0x1.db2cf8p125F); }

    /**
     * This color constant "Brilliant Purple" has RGBA8888 code {@code C992D3FF}, L 0.6666667, A 0.5372549, B 0.46666667, alpha 1.0, hue 0.8926003, saturation 0.17922273, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.ef1354p125F}.
     * <pre>
     * <font style='background-color: #C992D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C992D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C992D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C992D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C992D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C992D3'>&nbsp;@&nbsp;</font><font style='background-color: #C992D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C992D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C992D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLE = -0x1.ef1354p125F;
    static { NAMED.put("Brilliant Purple", -0x1.ef1354p125F); LIST.add(-0x1.ef1354p125F); }

    /**
     * This color constant "Strong Purple" has RGBA8888 code {@code 905A95FF}, L 0.4509804, A 0.5411765, B 0.46666667, alpha 1.0, hue 0.89991105, saturation 0.14078479, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.ef14e6p125F}.
     * <pre>
     * <font style='background-color: #905A95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #905A95; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #905A95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #905A95'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #905A95'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #905A95'>&nbsp;@&nbsp;</font><font style='background-color: #905A95; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #905A95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #905A95; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLE = -0x1.ef14e6p125F;
    static { NAMED.put("Strong Purple", -0x1.ef14e6p125F); LIST.add(-0x1.ef14e6p125F); }

    /**
     * This color constant "Deep Purple" has RGBA8888 code {@code 64276FFF}, L 0.27058825, A 0.5529412, B 0.45490196, alpha 1.0, hue 0.8939959, saturation 0.44300812, and chroma 0.13854803.
     * It can be represented as a packed float with the constant {@code -0x1.e91a8ap125F}.
     * <pre>
     * <font style='background-color: #64276F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64276F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64276F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #64276F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #64276F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #64276F'>&nbsp;@&nbsp;</font><font style='background-color: #64276F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64276F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64276F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE = -0x1.e91a8ap125F;
    static { NAMED.put("Deep Purple", -0x1.e91a8ap125F); LIST.add(-0x1.e91a8ap125F); }

    /**
     * This color constant "Very Deep Purple" has RGBA8888 code {@code 470B51FF}, L 0.16470589, A 0.5529412, B 0.45490196, alpha 1.0, hue 0.8939959, saturation 0.7730529, and chroma 0.13854803.
     * It can be represented as a packed float with the constant {@code -0x1.e91a54p125F}.
     * <pre>
     * <font style='background-color: #470B51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #470B51; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #470B51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #470B51'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #470B51'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #470B51'>&nbsp;@&nbsp;</font><font style='background-color: #470B51; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #470B51;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #470B51; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_PURPLE = -0x1.e91a54p125F;
    static { NAMED.put("Very Deep Purple", -0x1.e91a54p125F); LIST.add(-0x1.e91a54p125F); }

    /**
     * This color constant "Very Light Purple" has RGBA8888 code {@code E6BCE8FF}, L 0.79607844, A 0.5254902, B 0.47843137, alpha 1.0, hue 0.90127134, saturation 0.22218052, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.f50d96p125F}.
     * <pre>
     * <font style='background-color: #E6BCE8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6BCE8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6BCE8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6BCE8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6BCE8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6BCE8'>&nbsp;@&nbsp;</font><font style='background-color: #E6BCE8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6BCE8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6BCE8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_PURPLE = -0x1.f50d96p125F;
    static { NAMED.put("Very Light Purple", -0x1.f50d96p125F); LIST.add(-0x1.f50d96p125F); }

    /**
     * This color constant "Light Purple" has RGBA8888 code {@code B898BFFF}, L 0.654902, A 0.52156866, B 0.47843137, alpha 1.0, hue 0.889419, saturation 0.064511865, and chroma 0.060766988.
     * It can be represented as a packed float with the constant {@code -0x1.f50b4ep125F}.
     * <pre>
     * <font style='background-color: #B898BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B898BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B898BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B898BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B898BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B898BF'>&nbsp;@&nbsp;</font><font style='background-color: #B898BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B898BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B898BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLE = -0x1.f50b4ep125F;
    static { NAMED.put("Light Purple", -0x1.f50b4ep125F); LIST.add(-0x1.f50b4ep125F); }

    /**
     * This color constant "Moderate Purple" has RGBA8888 code {@code 836582FF}, L 0.4509804, A 0.52156866, B 0.48235294, alpha 1.0, hue 0.90640444, saturation 0.040686585, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.f70ae6p125F}.
     * <pre>
     * <font style='background-color: #836582;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #836582; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #836582;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #836582'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #836582'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #836582'>&nbsp;@&nbsp;</font><font style='background-color: #836582; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #836582;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #836582; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLE = -0x1.f70ae6p125F;
    static { NAMED.put("Moderate Purple", -0x1.f70ae6p125F); LIST.add(-0x1.f70ae6p125F); }

    /**
     * This color constant "Dark Purple" has RGBA8888 code {@code 543A54FF}, L 0.26666668, A 0.52156866, B 0.48235294, alpha 1.0, hue 0.90640444, saturation 0.07842398, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.f70a88p125F}.
     * <pre>
     * <font style='background-color: #543A54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #543A54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #543A54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #543A54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #543A54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #543A54'>&nbsp;@&nbsp;</font><font style='background-color: #543A54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #543A54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #543A54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLE = -0x1.f70a88p125F;
    static { NAMED.put("Dark Purple", -0x1.f70a88p125F); LIST.add(-0x1.f70a88p125F); }

    /**
     * This color constant "Very Dark Purple" has RGBA8888 code {@code 351D3BFF}, L 0.14509805, A 0.5254902, B 0.4745098, alpha 1.0, hue 0.8872099, saturation 0.24177778, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.f30c4ap125F}.
     * <pre>
     * <font style='background-color: #351D3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #351D3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #351D3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #351D3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #351D3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #351D3B'>&nbsp;@&nbsp;</font><font style='background-color: #351D3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #351D3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #351D3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_PURPLE = -0x1.f30c4ap125F;
    static { NAMED.put("Very Dark Purple", -0x1.f30c4ap125F); LIST.add(-0x1.f30c4ap125F); }

    /**
     * This color constant "Very Pale Purple" has RGBA8888 code {@code E1CEE0FF}, L 0.8235294, A 0.50980395, B 0.49019608, alpha 1.0, hue 0.90640444, saturation 0.055898953, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.fb05a4p125F}.
     * <pre>
     * <font style='background-color: #E1CEE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1CEE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1CEE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1CEE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1CEE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1CEE0'>&nbsp;@&nbsp;</font><font style='background-color: #E1CEE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1CEE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1CEE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_PURPLE = -0x1.fb05a4p125F;
    static { NAMED.put("Very Pale Purple", -0x1.fb05a4p125F); LIST.add(-0x1.fb05a4p125F); }

    /**
     * This color constant "Pale Purple" has RGBA8888 code {@code A89CA5FF}, L 0.63529414, A 0.5058824, B 0.49411765, alpha 1.0, hue 0.92620975, saturation 0.0042623477, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.fd0344p125F}.
     * <pre>
     * <font style='background-color: #A89CA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A89CA5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A89CA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A89CA5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A89CA5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A89CA5'>&nbsp;@&nbsp;</font><font style='background-color: #A89CA5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A89CA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A89CA5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.fd0344p125F;
    static { NAMED.put("Pale Purple", -0x1.fd0344p125F); LIST.add(-0x1.fd0344p125F); }

    /**
     * This color constant "Grayish Purple" has RGBA8888 code {@code 756B73FF}, L 0.44313726, A 0.5058824, B 0.49411765, alpha 1.0, hue 0.92620975, saturation 0.0042623477, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.fd02e2p125F}.
     * <pre>
     * <font style='background-color: #756B73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #756B73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #756B73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #756B73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #756B73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #756B73'>&nbsp;@&nbsp;</font><font style='background-color: #756B73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #756B73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #756B73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLE = -0x1.fd02e2p125F;
    static { NAMED.put("Grayish Purple", -0x1.fd02e2p125F); LIST.add(-0x1.fd02e2p125F); }

    /**
     * This color constant "Dark Grayish Purple" has RGBA8888 code {@code 4B4249FF}, L 0.27058825, A 0.5058824, B 0.49411765, alpha 1.0, hue 0.92620975, saturation 0.007842368, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.fd028ap125F}.
     * <pre>
     * <font style='background-color: #4B4249;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B4249; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B4249;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B4249'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B4249'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B4249'>&nbsp;@&nbsp;</font><font style='background-color: #4B4249; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B4249;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B4249; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_PURPLE = -0x1.fd028ap125F;
    static { NAMED.put("Dark Grayish Purple", -0x1.fd028ap125F); LIST.add(-0x1.fd028ap125F); }

    /**
     * This color constant "Blackish Purple" has RGBA8888 code {@code 2E232AFF}, L 0.14117648, A 0.50980395, B 0.49411765, alpha 1.0, hue 0.9488043, saturation 0.033606384, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fd0448p125F}.
     * <pre>
     * <font style='background-color: #2E232A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2E232A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2E232A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2E232A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2E232A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2E232A'>&nbsp;@&nbsp;</font><font style='background-color: #2E232A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2E232A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2E232A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_PURPLE = -0x1.fd0448p125F;
    static { NAMED.put("Blackish Purple", -0x1.fd0448p125F); LIST.add(-0x1.fd0448p125F); }

    /**
     * This color constant "Purplish White" has RGBA8888 code {@code EDE0EAFF}, L 0.8784314, A 0.5058824, B 0.49411765, alpha 1.0, hue 0.92620975, saturation 0.06530612, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.fd03cp125F}.
     * <pre>
     * <font style='background-color: #EDE0EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDE0EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDE0EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDE0EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDE0EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDE0EA'>&nbsp;@&nbsp;</font><font style='background-color: #EDE0EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDE0EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDE0EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_WHITE = -0x1.fd03cp125F;
    static { NAMED.put("Purplish White", -0x1.fd03cp125F); LIST.add(-0x1.fd03cp125F); }

    /**
     * This color constant "Light Purplish Gray" has RGBA8888 code {@code C1BABCFF}, L 0.7372549, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.0037869823, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff0178p125F}.
     * <pre>
     * <font style='background-color: #C1BABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1BABC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1BABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1BABC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1BABC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1BABC'>&nbsp;@&nbsp;</font><font style='background-color: #C1BABC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1BABC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1BABC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_GRAY = -0x1.ff0178p125F;
    static { NAMED.put("Light Purplish Gray", -0x1.ff0178p125F); LIST.add(-0x1.ff0178p125F); }

    /**
     * This color constant "Purplish Gray" has RGBA8888 code {@code 8D8688FF}, L 0.54509807, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 8.7791495E-4, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff0116p125F}.
     * <pre>
     * <font style='background-color: #8D8688;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D8688; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D8688;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8D8688'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8D8688'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8D8688'>&nbsp;@&nbsp;</font><font style='background-color: #8D8688; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D8688;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D8688; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_GRAY = -0x1.ff0116p125F;
    static { NAMED.put("Purplish Gray", -0x1.ff0116p125F); LIST.add(-0x1.ff0116p125F); }

    /**
     * This color constant "Dark Purplish Gray" has RGBA8888 code {@code 595455FF}, L 0.34117648, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.0014512471, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff00aep125F}.
     * <pre>
     * <font style='background-color: #595455;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #595455; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #595455;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #595455'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #595455'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #595455'>&nbsp;@&nbsp;</font><font style='background-color: #595455; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #595455;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #595455; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_GRAY = -0x1.ff00aep125F;
    static { NAMED.put("Dark Purplish Gray", -0x1.ff00aep125F); LIST.add(-0x1.ff00aep125F); }

    /**
     * This color constant "Purplish Black" has RGBA8888 code {@code 2B2628FF}, L 0.14509805, A 0.5019608, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.0037869823, and chroma 0.0055242716.
     * It can be represented as a packed float with the constant {@code -0x1.ff004ap125F}.
     * <pre>
     * <font style='background-color: #2B2628;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B2628; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B2628;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2B2628'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2B2628'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2B2628'>&nbsp;@&nbsp;</font><font style='background-color: #2B2628; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2B2628;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2B2628; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_BLACK = -0x1.ff004ap125F;
    static { NAMED.put("Purplish Black", -0x1.ff004ap125F); LIST.add(-0x1.ff004ap125F); }

    /**
     * This color constant "Vivid Reddish Purple" has RGBA8888 code {@code D42EB3FF}, L 0.5019608, A 0.60784316, B 0.4509804, alpha 1.0, hue 0.9355687, saturation 0.7062069, and chroma 0.23599699.
     * It can be represented as a packed float with the constant {@code -0x1.e737p125F}.
     * <pre>
     * <font style='background-color: #D42EB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D42EB3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D42EB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D42EB3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D42EB3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D42EB3'>&nbsp;@&nbsp;</font><font style='background-color: #D42EB3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D42EB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D42EB3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_REDDISH_PURPLE = -0x1.e737p125F;
    static { NAMED.put("Vivid Reddish Purple", -0x1.e737p125F); LIST.add(-0x1.e737p125F); }

    /**
     * This color constant "Strong Reddish Purple" has RGBA8888 code {@code 9D538AFF}, L 0.4509804, A 0.5529412, B 0.4745098, alpha 1.0, hue 0.9355687, saturation 0.19777292, and chroma 0.11705722.
     * It can be represented as a packed float with the constant {@code -0x1.f31ae6p125F}.
     * <pre>
     * <font style='background-color: #9D538A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D538A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D538A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9D538A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9D538A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9D538A'>&nbsp;@&nbsp;</font><font style='background-color: #9D538A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9D538A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9D538A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_PURPLE = -0x1.f31ae6p125F;
    static { NAMED.put("Strong Reddish Purple", -0x1.f31ae6p125F); LIST.add(-0x1.f31ae6p125F); }

    /**
     * This color constant "Deep Reddish Purple" has RGBA8888 code {@code 741E64FF}, L 0.27450982, A 0.5686275, B 0.46666667, alpha 1.0, hue 0.9334452, saturation 0.6085678, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.ef228cp125F}.
     * <pre>
     * <font style='background-color: #741E64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #741E64; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #741E64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #741E64'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #741E64'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #741E64'>&nbsp;@&nbsp;</font><font style='background-color: #741E64; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #741E64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #741E64; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_PURPLE = -0x1.ef228cp125F;
    static { NAMED.put("Deep Reddish Purple", -0x1.ef228cp125F); LIST.add(-0x1.ef228cp125F); }

    /**
     * This color constant "Very Deep Reddish Purple" has RGBA8888 code {@code 4F0447FF}, L 0.16862746, A 0.56078434, B 0.46666667, alpha 1.0, hue 0.92620975, saturation 0.86355203, and chroma 0.1381068.
     * It can be represented as a packed float with the constant {@code -0x1.ef1e56p125F}.
     * <pre>
     * <font style='background-color: #4F0447;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F0447; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F0447;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F0447'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F0447'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F0447'>&nbsp;@&nbsp;</font><font style='background-color: #4F0447; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F0447;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F0447; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_REDDISH_PURPLE = -0x1.ef1e56p125F;
    static { NAMED.put("Very Deep Reddish Purple", -0x1.ef1e56p125F); LIST.add(-0x1.ef1e56p125F); }

    /**
     * This color constant "Light Reddish Purple" has RGBA8888 code {@code B388A0FF}, L 0.6, A 0.5254902, B 0.49019608, alpha 1.0, hue 0.95571566, saturation 0.047939397, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.fb0d32p125F}.
     * <pre>
     * <font style='background-color: #B388A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B388A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B388A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B388A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B388A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B388A0'>&nbsp;@&nbsp;</font><font style='background-color: #B388A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B388A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B388A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_REDDISH_PURPLE = -0x1.fb0d32p125F;
    static { NAMED.put("Light Reddish Purple", -0x1.fb0d32p125F); LIST.add(-0x1.fb0d32p125F); }

    /**
     * This color constant "Moderate Reddish Purple" has RGBA8888 code {@code 8C617DFF}, L 0.4509804, A 0.5294118, B 0.4862745, alpha 1.0, hue 0.9429125, saturation 0.06408779, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.f90ee6p125F}.
     * <pre>
     * <font style='background-color: #8C617D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C617D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C617D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C617D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C617D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C617D'>&nbsp;@&nbsp;</font><font style='background-color: #8C617D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C617D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C617D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_PURPLE = -0x1.f90ee6p125F;
    static { NAMED.put("Moderate Reddish Purple", -0x1.f90ee6p125F); LIST.add(-0x1.f90ee6p125F); }

    /**
     * This color constant "Dark Reddish Purple" has RGBA8888 code {@code 583950FF}, L 0.27058825, A 0.5254902, B 0.4862745, alpha 1.0, hue 0.9355687, saturation 0.09468421, and chroma 0.05767509.
     * It can be represented as a packed float with the constant {@code -0x1.f90c8ap125F}.
     * <pre>
     * <font style='background-color: #583950;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #583950; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #583950;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #583950'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #583950'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #583950'>&nbsp;@&nbsp;</font><font style='background-color: #583950; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #583950;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #583950; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_PURPLE = -0x1.f90c8ap125F;
    static { NAMED.put("Dark Reddish Purple", -0x1.f90c8ap125F); LIST.add(-0x1.f90c8ap125F); }

    /**
     * This color constant "Very Dark Reddish Purple" has RGBA8888 code {@code 3A1C35FF}, L 0.14901961, A 0.5294118, B 0.48235294, alpha 1.0, hue 0.92620975, saturation 0.24019516, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.f70e4cp125F}.
     * <pre>
     * <font style='background-color: #3A1C35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A1C35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A1C35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3A1C35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3A1C35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3A1C35'>&nbsp;@&nbsp;</font><font style='background-color: #3A1C35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3A1C35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3A1C35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_REDDISH_PURPLE = -0x1.f70e4cp125F;
    static { NAMED.put("Very Dark Reddish Purple", -0x1.f70e4cp125F); LIST.add(-0x1.f70e4cp125F); }

    /**
     * This color constant "Pale Reddish Purple" has RGBA8888 code {@code A2909AFF}, L 0.59607846, A 0.50980395, B 0.49411765, alpha 1.0, hue 0.9488043, saturation 0.008047885, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fd053p125F}.
     * <pre>
     * <font style='background-color: #A2909A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2909A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2909A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2909A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2909A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2909A'>&nbsp;@&nbsp;</font><font style='background-color: #A2909A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2909A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2909A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_REDDISH_PURPLE = -0x1.fd053p125F;
    static { NAMED.put("Pale Reddish Purple", -0x1.fd053p125F); LIST.add(-0x1.fd053p125F); }

    /**
     * This color constant "Grayish Reddish Purple" has RGBA8888 code {@code 7E6873FF}, L 0.44705883, A 0.5137255, B 0.49411765, alpha 1.0, hue 0.96101314, saturation 0.015849892, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.fd06e4p125F}.
     * <pre>
     * <font style='background-color: #7E6873;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E6873; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E6873;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E6873'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E6873'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E6873'>&nbsp;@&nbsp;</font><font style='background-color: #7E6873; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E6873;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E6873; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_PURPLE = -0x1.fd06e4p125F;
    static { NAMED.put("Grayish Reddish Purple", -0x1.fd06e4p125F); LIST.add(-0x1.fd06e4p125F); }

    /**
     * This color constant "Brilliant Purplish Pink" has RGBA8888 code {@code FFA1E4FF}, L 0.7647059, A 0.5568628, B 0.4745098, alpha 1.0, hue 0.939452, saturation 0.82840705, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.f31d86p125F}.
     * <pre>
     * <font style='background-color: #FFA1E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA1E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA1E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA1E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA1E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA1E4'>&nbsp;@&nbsp;</font><font style='background-color: #FFA1E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA1E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA1E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLISH_PINK = -0x1.f31d86p125F;
    static { NAMED.put("Brilliant Purplish Pink", -0x1.f31d86p125F); LIST.add(-0x1.f31d86p125F); }

    /**
     * This color constant "Strong Purplish Pink" has RGBA8888 code {@code F286C6FF}, L 0.6862745, A 0.5647059, B 0.47843137, alpha 1.0, hue 0.954483, saturation 0.5566759, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.f5215ep125F}.
     * <pre>
     * <font style='background-color: #F286C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F286C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F286C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F286C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F286C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F286C6'>&nbsp;@&nbsp;</font><font style='background-color: #F286C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F286C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F286C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_PINK = -0x1.f5215ep125F;
    static { NAMED.put("Strong Purplish Pink", -0x1.f5215ep125F); LIST.add(-0x1.f5215ep125F); }

    /**
     * This color constant "Deep Purplish Pink" has RGBA8888 code {@code D673A3FF}, L 0.6, A 0.56078434, B 0.4862745, alpha 1.0, hue 0.9704918, saturation 0.27136, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.f91f32p125F}.
     * <pre>
     * <font style='background-color: #D673A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D673A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D673A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D673A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D673A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D673A3'>&nbsp;@&nbsp;</font><font style='background-color: #D673A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D673A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D673A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_PINK = -0x1.f91f32p125F;
    static { NAMED.put("Deep Purplish Pink", -0x1.f91f32p125F); LIST.add(-0x1.f91f32p125F); }

    /**
     * This color constant "Light Purplish Pink" has RGBA8888 code {@code F3B5D3FF}, L 0.78431374, A 0.53333336, B 0.49019608, alpha 1.0, hue 0.96519506, saturation 0.41859034, and chroma 0.069218926.
     * It can be represented as a packed float with the constant {@code -0x1.fb119p125F}.
     * <pre>
     * <font style='background-color: #F3B5D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3B5D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3B5D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F3B5D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F3B5D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F3B5D3'>&nbsp;@&nbsp;</font><font style='background-color: #F3B5D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F3B5D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F3B5D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_PINK = -0x1.fb119p125F;
    static { NAMED.put("Light Purplish Pink", -0x1.fb119p125F); LIST.add(-0x1.fb119p125F); }

    /**
     * This color constant "Moderate Purplish Pink" has RGBA8888 code {@code D39FBAFF}, L 0.69803923, A 0.5294118, B 0.49019608, alpha 1.0, hue 0.96101314, saturation 0.14374422, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.fb0f64p125F}.
     * <pre>
     * <font style='background-color: #D39FBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D39FBA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D39FBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D39FBA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D39FBA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D39FBA'>&nbsp;@&nbsp;</font><font style='background-color: #D39FBA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D39FBA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D39FBA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_PINK = -0x1.fb0f64p125F;
    static { NAMED.put("Moderate Purplish Pink", -0x1.fb0f64p125F); LIST.add(-0x1.fb0f64p125F); }

    /**
     * This color constant "Dark Purplish Pink" has RGBA8888 code {@code B9869AFF}, L 0.6, A 0.5294118, B 0.49411765, alpha 1.0, hue 0.98019654, saturation 0.071033396, and chroma 0.059754133.
     * It can be represented as a packed float with the constant {@code -0x1.fd0f32p125F}.
     * <pre>
     * <font style='background-color: #B9869A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9869A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9869A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B9869A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B9869A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B9869A'>&nbsp;@&nbsp;</font><font style='background-color: #B9869A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B9869A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B9869A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_PINK = -0x1.fd0f32p125F;
    static { NAMED.put("Dark Purplish Pink", -0x1.fd0f32p125F); LIST.add(-0x1.fd0f32p125F); }

    /**
     * This color constant "Pale Purplish Pink" has RGBA8888 code {@code E7CBD9FF}, L 0.8235294, A 0.5137255, B 0.49411765, alpha 1.0, hue 0.96101314, saturation 0.13432099, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.fd07a4p125F}.
     * <pre>
     * <font style='background-color: #E7CBD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7CBD9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7CBD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E7CBD9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E7CBD9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E7CBD9'>&nbsp;@&nbsp;</font><font style='background-color: #E7CBD9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E7CBD9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E7CBD9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLISH_PINK = -0x1.fd07a4p125F;
    static { NAMED.put("Pale Purplish Pink", -0x1.fd07a4p125F); LIST.add(-0x1.fd07a4p125F); }

    /**
     * This color constant "Grayish Purplish Pink" has RGBA8888 code {@code C1A7B4FF}, L 0.69411767, A 0.5137255, B 0.49411765, alpha 1.0, hue 0.96101314, saturation 0.0343391, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.fd0762p125F}.
     * <pre>
     * <font style='background-color: #C1A7B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1A7B4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1A7B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1A7B4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1A7B4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1A7B4'>&nbsp;@&nbsp;</font><font style='background-color: #C1A7B4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1A7B4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1A7B4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_PINK = -0x1.fd0762p125F;
    static { NAMED.put("Grayish Purplish Pink", -0x1.fd0762p125F); LIST.add(-0x1.fd0762p125F); }

    /**
     * This color constant "Vivid Purplish Red" has RGBA8888 code {@code DA2A82FF}, L 0.4862745, A 0.60784316, B 0.4862745, alpha 1.0, hue 0.9830039, saturation 0.69618654, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.f936f8p125F}.
     * <pre>
     * <font style='background-color: #DA2A82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA2A82; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA2A82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA2A82'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA2A82'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA2A82'>&nbsp;@&nbsp;</font><font style='background-color: #DA2A82; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA2A82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA2A82; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLISH_RED = -0x1.f936f8p125F;
    static { NAMED.put("Vivid Purplish Red", -0x1.f936f8p125F); LIST.add(-0x1.f936f8p125F); }

    /**
     * This color constant "Strong Purplish Red" has RGBA8888 code {@code B0426EFF}, L 0.43529412, A 0.57254905, B 0.49411765, alpha 1.0, hue 0.9916409, saturation 0.3828409, and chroma 0.14500555.
     * It can be represented as a packed float with the constant {@code -0x1.fd24dep125F}.
     * <pre>
     * <font style='background-color: #B0426E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0426E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0426E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0426E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0426E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0426E'>&nbsp;@&nbsp;</font><font style='background-color: #B0426E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0426E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0426E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_RED = -0x1.fd24dep125F;
    static { NAMED.put("Strong Purplish Red", -0x1.fd24dep125F); LIST.add(-0x1.fd24dep125F); }

    /**
     * This color constant "Deep Purplish Red" has RGBA8888 code {@code 890F53FF}, L 0.29411766, A 0.58431375, B 0.4862745, alpha 1.0, hue 0.97841734, saturation 0.8048158, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.f92a96p125F}.
     * <pre>
     * <font style='background-color: #890F53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #890F53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #890F53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #890F53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #890F53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #890F53'>&nbsp;@&nbsp;</font><font style='background-color: #890F53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #890F53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #890F53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_RED = -0x1.f92a96p125F;
    static { NAMED.put("Deep Purplish Red", -0x1.f92a96p125F); LIST.add(-0x1.f92a96p125F); }

    /**
     * This color constant "Very Deep Purplish Red" has RGBA8888 code {@code 530839FF}, L 0.17254902, A 0.56078434, B 0.48235294, alpha 1.0, hue 0.96101314, saturation 0.7736889, and chroma 0.12609385.
     * It can be represented as a packed float with the constant {@code -0x1.f71e58p125F}.
     * <pre>
     * <font style='background-color: #530839;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #530839; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #530839;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #530839'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #530839'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #530839'>&nbsp;@&nbsp;</font><font style='background-color: #530839; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #530839;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #530839; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_PURPLISH_RED = -0x1.f71e58p125F;
    static { NAMED.put("Very Deep Purplish Red", -0x1.f71e58p125F); LIST.add(-0x1.f71e58p125F); }

    /**
     * This color constant "Moderate Purplish Red" has RGBA8888 code {@code A05572FF}, L 0.44705883, A 0.54901963, B 0.49411765, alpha 1.0, hue 0.98778236, saturation 0.16864033, and chroma 0.098356865.
     * It can be represented as a packed float with the constant {@code -0x1.fd18e4p125F}.
     * <pre>
     * <font style='background-color: #A05572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A05572; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A05572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A05572'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A05572'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A05572'>&nbsp;@&nbsp;</font><font style='background-color: #A05572; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A05572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A05572; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_RED = -0x1.fd18e4p125F;
    static { NAMED.put("Moderate Purplish Red", -0x1.fd18e4p125F); LIST.add(-0x1.fd18e4p125F); }

    /**
     * This color constant "Dark Purplish Red" has RGBA8888 code {@code 692F47FF}, L 0.27058825, A 0.54509807, B 0.49411765, alpha 1.0, hue 0.9867663, saturation 0.2682391, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.fd168ap125F}.
     * <pre>
     * <font style='background-color: #692F47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #692F47; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #692F47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #692F47'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #692F47'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #692F47'>&nbsp;@&nbsp;</font><font style='background-color: #692F47; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #692F47;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #692F47; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_RED = -0x1.fd168ap125F;
    static { NAMED.put("Dark Purplish Red", -0x1.fd168ap125F); LIST.add(-0x1.fd168ap125F); }

    /**
     * This color constant "Very Dark Purplish Red" has RGBA8888 code {@code 42152DFF}, L 0.14509805, A 0.5411765, B 0.49019608, alpha 1.0, hue 0.97136545, saturation 0.4455335, and chroma 0.084324345.
     * It can be represented as a packed float with the constant {@code -0x1.fb144ap125F}.
     * <pre>
     * <font style='background-color: #42152D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42152D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42152D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #42152D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #42152D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #42152D'>&nbsp;@&nbsp;</font><font style='background-color: #42152D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #42152D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #42152D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_PURPLISH_RED = -0x1.fb144ap125F;
    static { NAMED.put("Very Dark Purplish Red", -0x1.fb144ap125F); LIST.add(-0x1.fb144ap125F); }

    /**
     * This color constant "Light Grayish Purplish Red" has RGBA8888 code {@code A88E95FF}, L 0.59607846, A 0.5137255, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.020048555, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.ff073p125F}.
     * <pre>
     * <font style='background-color: #A88E95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A88E95; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A88E95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A88E95'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A88E95'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A88E95'>&nbsp;@&nbsp;</font><font style='background-color: #A88E95; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A88E95;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A88E95; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_PURPLISH_RED = -0x1.ff073p125F;
    static { NAMED.put("Light Grayish Purplish Red", -0x1.ff073p125F); LIST.add(-0x1.ff073p125F); }

    /**
     * This color constant "Grayish Purplish Red" has RGBA8888 code {@code 88646EFF}, L 0.44705883, A 0.52156866, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.036864, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.ff0ae4p125F}.
     * <pre>
     * <font style='background-color: #88646E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #88646E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #88646E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #88646E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #88646E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #88646E'>&nbsp;@&nbsp;</font><font style='background-color: #88646E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #88646E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #88646E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_RED = -0x1.ff0ae4p125F;
    static { NAMED.put("Grayish Purplish Red", -0x1.ff0ae4p125F); LIST.add(-0x1.ff0ae4p125F); }

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
