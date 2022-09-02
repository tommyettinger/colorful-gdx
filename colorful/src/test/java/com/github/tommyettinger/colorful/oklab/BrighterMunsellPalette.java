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
     * This color constant "Black" has RGBA8888 code {@code 423438FF}, L 0.21568628, A 0.50980395, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.15384616, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.ff046ep125F}.
     * <pre>
     * <font style='background-color: #423438;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #423438; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #423438;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #423438'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #423438'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #423438'>&nbsp;@&nbsp;</font><font style='background-color: #423438; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #423438;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #423438; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK = -0x1.ff046ep125F;
    static { NAMED.put("Black", -0x1.ff046ep125F); LIST.add(-0x1.ff046ep125F); }

    /**
     * This color constant "Dark Gray" has RGBA8888 code {@code 716477FF}, L 0.42352942, A 0.50980395, B 0.4862745, alpha 1.0, hue 0.875, saturation 0.119511, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.f904d8p125F}.
     * <pre>
     * <font style='background-color: #716477;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #716477; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #716477;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #716477'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #716477'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #716477'>&nbsp;@&nbsp;</font><font style='background-color: #716477; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #716477;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #716477; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY = -0x1.f904d8p125F;
    static { NAMED.put("Dark Gray", -0x1.f904d8p125F); LIST.add(-0x1.f904d8p125F); }

    /**
     * This color constant "Medium Gray" has RGBA8888 code {@code 9E95A3FF}, L 0.60784316, A 0.5058824, B 0.49019608, alpha 1.0, hue 0.875, saturation 0.089791335, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fb0336p125F}.
     * <pre>
     * <font style='background-color: #9E95A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E95A3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E95A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9E95A3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9E95A3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9E95A3'>&nbsp;@&nbsp;</font><font style='background-color: #9E95A3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9E95A3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9E95A3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUM_GRAY = -0x1.fb0336p125F;
    static { NAMED.put("Medium Gray", -0x1.fb0336p125F); LIST.add(-0x1.fb0336p125F); }

    /**
     * This color constant "Light Gray" has RGBA8888 code {@code CEC3D3FF}, L 0.78039217, A 0.5058824, B 0.49019608, alpha 1.0, hue 0.875, saturation 0.18247917, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fb038ep125F}.
     * <pre>
     * <font style='background-color: #CEC3D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEC3D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEC3D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEC3D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEC3D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEC3D3'>&nbsp;@&nbsp;</font><font style='background-color: #CEC3D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEC3D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEC3D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY = -0x1.fb038ep125F;
    static { NAMED.put("Light Gray", -0x1.fb038ep125F); LIST.add(-0x1.fb038ep125F); }

    /**
     * This color constant "White" has RGBA8888 code {@code EFE9F1FF}, L 0.9019608, A 0.5019608, B 0.49411765, alpha 1.0, hue 0.875, saturation 0.25712973, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fd01ccp125F}.
     * <pre>
     * <font style='background-color: #EFE9F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFE9F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFE9F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFE9F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFE9F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFE9F1'>&nbsp;@&nbsp;</font><font style='background-color: #EFE9F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFE9F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFE9F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE = -0x1.fd01ccp125F;
    static { NAMED.put("White", -0x1.fd01ccp125F); LIST.add(-0x1.fd01ccp125F); }

    /**
     * This color constant "Vivid Pink" has RGBA8888 code {@code FF94A8FF}, L 0.72156864, A 0.5568628, B 0.5058824, alpha 1.0, hue 0.021108776, saturation 0.9457966, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.031d7p126F}.
     * <pre>
     * <font style='background-color: #FF94A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF94A8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF94A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF94A8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF94A8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF94A8'>&nbsp;@&nbsp;</font><font style='background-color: #FF94A8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF94A8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF94A8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PINK = -0x1.031d7p126F;
    static { NAMED.put("Vivid Pink", -0x1.031d7p126F); LIST.add(-0x1.031d7p126F); }

    /**
     * This color constant "Strong Pink" has RGBA8888 code {@code FDA4B1FF}, L 0.7529412, A 0.54509807, B 0.5058824, alpha 1.0, hue 0.02629608, saturation 0.90115005, and chroma 0.09060479.
     * It can be represented as a packed float with the constant {@code -0x1.03178p126F}.
     * <pre>
     * <font style='background-color: #FDA4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDA4B1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDA4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDA4B1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDA4B1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDA4B1'>&nbsp;@&nbsp;</font><font style='background-color: #FDA4B1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDA4B1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDA4B1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PINK = -0x1.03178p126F;
    static { NAMED.put("Strong Pink", -0x1.03178p126F); LIST.add(-0x1.03178p126F); }

    /**
     * This color constant "Deep Pink" has RGBA8888 code {@code F67E92FF}, L 0.65882355, A 0.5647059, B 0.50980395, alpha 1.0, hue 0.02781067, saturation 0.8420818, and chroma 0.1303775.
     * It can be represented as a packed float with the constant {@code -0x1.05215p126F}.
     * <pre>
     * <font style='background-color: #F67E92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F67E92; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F67E92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F67E92'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F67E92'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F67E92'>&nbsp;@&nbsp;</font><font style='background-color: #F67E92; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F67E92;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F67E92; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PINK = -0x1.05215p126F;
    static { NAMED.put("Deep Pink", -0x1.05215p126F); LIST.add(-0x1.05215p126F); }

    /**
     * This color constant "Light Pink" has RGBA8888 code {@code FED1DDFF}, L 0.85882354, A 0.52156866, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.85714287, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.ff0bb6p125F}.
     * <pre>
     * <font style='background-color: #FED1DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FED1DD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FED1DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FED1DD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FED1DD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FED1DD'>&nbsp;@&nbsp;</font><font style='background-color: #FED1DD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FED1DD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FED1DD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PINK = -0x1.ff0bb6p125F;
    static { NAMED.put("Light Pink", -0x1.ff0bb6p125F); LIST.add(-0x1.ff0bb6p125F); }

    /**
     * This color constant "Moderate Pink" has RGBA8888 code {@code EFB3BDFF}, L 0.76862746, A 0.5294118, B 0.5019608, alpha 1.0, hue 0.019803474, saturation 0.6449806, and chroma 0.058723815.
     * It can be represented as a packed float with the constant {@code -0x1.010f88p126F}.
     * <pre>
     * <font style='background-color: #EFB3BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFB3BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFB3BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EFB3BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EFB3BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EFB3BD'>&nbsp;@&nbsp;</font><font style='background-color: #EFB3BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EFB3BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EFB3BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PINK = -0x1.010f88p126F;
    static { NAMED.put("Moderate Pink", -0x1.010f88p126F); LIST.add(-0x1.010f88p126F); }

    /**
     * This color constant "Dark Pink" has RGBA8888 code {@code D9929AFF}, L 0.6627451, A 0.5372549, B 0.5058824, alpha 1.0, hue 0.03142344, saturation 0.50990194, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.031352p126F}.
     * <pre>
     * <font style='background-color: #D9929A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9929A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9929A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9929A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9929A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9929A'>&nbsp;@&nbsp;</font><font style='background-color: #D9929A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9929A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9929A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PINK = -0x1.031352p126F;
    static { NAMED.put("Dark Pink", -0x1.031352p126F); LIST.add(-0x1.031352p126F); }

    /**
     * This color constant "Pale Pink" has RGBA8888 code {@code FADBE3FF}, L 0.8784314, A 0.5137255, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.6666667, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.ff07cp125F}.
     * <pre>
     * <font style='background-color: #FADBE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FADBE3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FADBE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FADBE3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FADBE3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FADBE3'>&nbsp;@&nbsp;</font><font style='background-color: #FADBE3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FADBE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FADBE3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PINK = -0x1.ff07cp125F;
    static { NAMED.put("Pale Pink", -0x1.ff07cp125F); LIST.add(-0x1.ff07cp125F); }

    /**
     * This color constant "Grayish Pink" has RGBA8888 code {@code DFBAC4FF}, L 0.77254903, A 0.5176471, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.3846154, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.ff098ap125F}.
     * <pre>
     * <font style='background-color: #DFBAC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFBAC4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFBAC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DFBAC4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DFBAC4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DFBAC4'>&nbsp;@&nbsp;</font><font style='background-color: #DFBAC4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DFBAC4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DFBAC4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PINK = -0x1.ff098ap125F;
    static { NAMED.put("Grayish Pink", -0x1.ff098ap125F); LIST.add(-0x1.ff098ap125F); }

    /**
     * This color constant "Pinkish White" has RGBA8888 code {@code F4E7F1FF}, L 0.9019608, A 0.5058824, B 0.49411765, alpha 1.0, hue 0.92620975, saturation 0.372678, and chroma 0.016572814.
     * It can be represented as a packed float with the constant {@code -0x1.fd03ccp125F}.
     * <pre>
     * <font style='background-color: #F4E7F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4E7F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4E7F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F4E7F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F4E7F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F4E7F1'>&nbsp;@&nbsp;</font><font style='background-color: #F4E7F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F4E7F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F4E7F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINKISH_WHITE = -0x1.fd03ccp125F;
    static { NAMED.put("Pinkish White", -0x1.fd03ccp125F); LIST.add(-0x1.fd03ccp125F); }

    /**
     * This color constant "Pinkish Gray" has RGBA8888 code {@code D9C3C9FF}, L 0.78431374, A 0.50980395, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.25, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.ff059p125F}.
     * <pre>
     * <font style='background-color: #D9C3C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9C3C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9C3C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D9C3C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D9C3C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D9C3C9'>&nbsp;@&nbsp;</font><font style='background-color: #D9C3C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D9C3C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D9C3C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINKISH_GRAY = -0x1.ff059p125F;
    static { NAMED.put("Pinkish Gray", -0x1.ff059p125F); LIST.add(-0x1.ff059p125F); }

    /**
     * This color constant "Vivid Red" has RGBA8888 code {@code FF003EFF}, L 0.5137255, A 0.6156863, B 0.5411765, alpha 1.0, hue 0.055921316, saturation 0.9538236, and chroma 0.24463232.
     * It can be represented as a packed float with the constant {@code -0x1.153b06p126F}.
     * <pre>
     * <font style='background-color: #FF003E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF003E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF003E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF003E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF003E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF003E'>&nbsp;@&nbsp;</font><font style='background-color: #FF003E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF003E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF003E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_RED = -0x1.153b06p126F;
    static { NAMED.put("Vivid Red", -0x1.153b06p126F); LIST.add(-0x1.153b06p126F); }

    /**
     * This color constant "Strong Red" has RGBA8888 code {@code EC2E54FF}, L 0.5058824, A 0.6039216, B 0.5254902, alpha 1.0, hue 0.0403691, saturation 0.8582354, and chroma 0.21316819.
     * It can be represented as a packed float with the constant {@code -0x1.0d3502p126F}.
     * <pre>
     * <font style='background-color: #EC2E54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC2E54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC2E54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EC2E54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EC2E54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EC2E54'>&nbsp;@&nbsp;</font><font style='background-color: #EC2E54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EC2E54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EC2E54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_RED = -0x1.0d3502p126F;
    static { NAMED.put("Strong Red", -0x1.0d3502p126F); LIST.add(-0x1.0d3502p126F); }

    /**
     * This color constant "Deep Red" has RGBA8888 code {@code B30039FF}, L 0.3647059, A 0.59607846, B 0.52156866, alpha 1.0, hue 0.03748731, saturation 0.9701857, and chroma 0.19616999.
     * It can be represented as a packed float with the constant {@code -0x1.0b30bap126F}.
     * <pre>
     * <font style='background-color: #B30039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B30039; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B30039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B30039'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B30039'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B30039'>&nbsp;@&nbsp;</font><font style='background-color: #B30039; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B30039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B30039; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_RED = -0x1.0b30bap126F;
    static { NAMED.put("Deep Red", -0x1.0b30bap126F); LIST.add(-0x1.0b30bap126F); }

    /**
     * This color constant "Very Deep Red" has RGBA8888 code {@code 810031FF}, L 0.25882354, A 0.5764706, B 0.50980395, alpha 1.0, hue 0.02370946, saturation 0.9406395, and chroma 0.15359065.
     * It can be represented as a packed float with the constant {@code -0x1.052684p126F}.
     * <pre>
     * <font style='background-color: #810031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #810031; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #810031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #810031'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #810031'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #810031'>&nbsp;@&nbsp;</font><font style='background-color: #810031; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #810031;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #810031; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_RED = -0x1.052684p126F;
    static { NAMED.put("Very Deep Red", -0x1.052684p126F); LIST.add(-0x1.052684p126F); }

    /**
     * This color constant "Moderate Red" has RGBA8888 code {@code DD4A62FF}, L 0.5176471, A 0.58431375, B 0.5176471, alpha 1.0, hue 0.035569068, saturation 0.6836675, and chroma 0.17160846.
     * It can be represented as a packed float with the constant {@code -0x1.092b08p126F}.
     * <pre>
     * <font style='background-color: #DD4A62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD4A62; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD4A62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD4A62'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD4A62'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD4A62'>&nbsp;@&nbsp;</font><font style='background-color: #DD4A62; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD4A62;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD4A62; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_RED = -0x1.092b08p126F;
    static { NAMED.put("Moderate Red", -0x1.092b08p126F); LIST.add(-0x1.092b08p126F); }

    /**
     * This color constant "Dark Red" has RGBA8888 code {@code 9C243FFF}, L 0.34117648, A 0.5764706, B 0.5137255, alpha 1.0, hue 0.03142344, saturation 0.7998462, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.0726aep126F}.
     * <pre>
     * <font style='background-color: #9C243F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C243F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C243F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C243F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C243F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C243F'>&nbsp;@&nbsp;</font><font style='background-color: #9C243F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C243F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C243F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_RED = -0x1.0726aep126F;
    static { NAMED.put("Dark Red", -0x1.0726aep126F); LIST.add(-0x1.0726aep126F); }

    /**
     * This color constant "Very Dark Red" has RGBA8888 code {@code 671135FF}, L 0.21568628, A 0.5647059, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.8717949, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.ff206ep125F}.
     * <pre>
     * <font style='background-color: #671135;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #671135; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #671135;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #671135'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #671135'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #671135'>&nbsp;@&nbsp;</font><font style='background-color: #671135; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #671135;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #671135; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_RED = -0x1.ff206ep125F;
    static { NAMED.put("Very Dark Red", -0x1.ff206ep125F); LIST.add(-0x1.ff206ep125F); }

    /**
     * This color constant "Light Grayish Red" has RGBA8888 code {@code CA99A0FF}, L 0.6627451, A 0.5254902, B 0.5019608, alpha 1.0, hue 0.022596559, saturation 0.34493014, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.010d52p126F}.
     * <pre>
     * <font style='background-color: #CA99A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA99A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA99A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CA99A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CA99A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CA99A0'>&nbsp;@&nbsp;</font><font style='background-color: #CA99A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CA99A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CA99A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_RED = -0x1.010d52p126F;
    static { NAMED.put("Light Grayish Red", -0x1.010d52p126F); LIST.add(-0x1.010d52p126F); }

    /**
     * This color constant "Grayish Red" has RGBA8888 code {@code C16570FF}, L 0.5254902, A 0.5529412, B 0.50980395, alpha 1.0, hue 0.03360078, saturation 0.4338734, and chroma 0.107261956.
     * It can be represented as a packed float with the constant {@code -0x1.051b0cp126F}.
     * <pre>
     * <font style='background-color: #C16570;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C16570; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C16570;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C16570'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C16570'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C16570'>&nbsp;@&nbsp;</font><font style='background-color: #C16570; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C16570;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C16570; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_RED = -0x1.051b0cp126F;
    static { NAMED.put("Grayish Red", -0x1.051b0cp126F); LIST.add(-0x1.051b0cp126F); }

    /**
     * This color constant "Dark Grayish Red" has RGBA8888 code {@code 724251FF}, L 0.3254902, A 0.53333336, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.36, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.ff10a6p125F}.
     * <pre>
     * <font style='background-color: #724251;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #724251; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #724251;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #724251'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #724251'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #724251'>&nbsp;@&nbsp;</font><font style='background-color: #724251; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #724251;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #724251; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_RED = -0x1.ff10a6p125F;
    static { NAMED.put("Dark Grayish Red", -0x1.ff10a6p125F); LIST.add(-0x1.ff10a6p125F); }

    /**
     * This color constant "Blackish Red" has RGBA8888 code {@code 4B2B35FF}, L 0.20784314, A 0.5254902, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.36842105, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.ff0c6ap125F}.
     * <pre>
     * <font style='background-color: #4B2B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2B35; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B2B35'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B2B35'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B2B35'>&nbsp;@&nbsp;</font><font style='background-color: #4B2B35; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2B35;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2B35; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_RED = -0x1.ff0c6ap125F;
    static { NAMED.put("Blackish Red", -0x1.ff0c6ap125F); LIST.add(-0x1.ff0c6ap125F); }

    /**
     * This color constant "Reddish Gray" has RGBA8888 code {@code AB9198FF}, L 0.60784316, A 0.5137255, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.1509434, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.ff0736p125F}.
     * <pre>
     * <font style='background-color: #AB9198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB9198; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB9198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB9198'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB9198'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB9198'>&nbsp;@&nbsp;</font><font style='background-color: #AB9198; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB9198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB9198; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REDDISH_GRAY = -0x1.ff0736p125F;
    static { NAMED.put("Reddish Gray", -0x1.ff0736p125F); LIST.add(-0x1.ff0736p125F); }

    /**
     * This color constant "Dark Reddish Gray" has RGBA8888 code {@code 7E5C65FF}, L 0.4117647, A 0.52156866, B 0.49803922, alpha 1.0, hue 1.2950972E-5, saturation 0.20689656, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.ff0ad2p125F}.
     * <pre>
     * <font style='background-color: #7E5C65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E5C65; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E5C65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E5C65'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E5C65'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E5C65'>&nbsp;@&nbsp;</font><font style='background-color: #7E5C65; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E5C65;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E5C65; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_GRAY = -0x1.ff0ad2p125F;
    static { NAMED.put("Dark Reddish Gray", -0x1.ff0ad2p125F); LIST.add(-0x1.ff0ad2p125F); }

    /**
     * This color constant "Reddish Black" has RGBA8888 code {@code 492F3FFF}, L 0.21568628, A 0.52156866, B 0.49019608, alpha 1.0, hue 0.9488043, saturation 0.3085149, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.fb0a6ep125F}.
     * <pre>
     * <font style='background-color: #492F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #492F3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #492F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #492F3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #492F3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #492F3F'>&nbsp;@&nbsp;</font><font style='background-color: #492F3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #492F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #492F3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REDDISH_BLACK = -0x1.fb0a6ep125F;
    static { NAMED.put("Reddish Black", -0x1.fb0a6ep125F); LIST.add(-0x1.fb0a6ep125F); }

    /**
     * This color constant "Vivid Yellowish Pink" has RGBA8888 code {@code FF9576FF}, L 0.70980394, A 0.54509807, B 0.5372549, alpha 1.0, hue 0.11058099, saturation 0.9188529, and chroma 0.11653464.
     * It can be represented as a packed float with the constant {@code -0x1.13176ap126F}.
     * <pre>
     * <font style='background-color: #FF9576;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF9576; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF9576;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF9576'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF9576'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF9576'>&nbsp;@&nbsp;</font><font style='background-color: #FF9576; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF9576;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF9576; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOWISH_PINK = -0x1.13176ap126F;
    static { NAMED.put("Vivid Yellowish Pink", -0x1.13176ap126F); LIST.add(-0x1.13176ap126F); }

    /**
     * This color constant "Strong Yellowish Pink" has RGBA8888 code {@code FDA397FF}, L 0.7411765, A 0.5411765, B 0.52156866, alpha 1.0, hue 0.07947698, saturation 0.8949975, and chroma 0.09260367.
     * It can be represented as a packed float with the constant {@code -0x1.0b157ap126F}.
     * <pre>
     * <font style='background-color: #FDA397;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDA397; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDA397;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDA397'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDA397'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDA397'>&nbsp;@&nbsp;</font><font style='background-color: #FDA397; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDA397;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDA397; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_PINK = -0x1.0b157ap126F;
    static { NAMED.put("Strong Yellowish Pink", -0x1.0b157ap126F); LIST.add(-0x1.0b157ap126F); }

    /**
     * This color constant "Deep Yellowish Pink" has RGBA8888 code {@code FA7C7FFF}, L 0.654902, A 0.5647059, B 0.52156866, alpha 1.0, hue 0.053987134, saturation 0.8794027, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.0b214ep126F}.
     * <pre>
     * <font style='background-color: #FA7C7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA7C7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA7C7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FA7C7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FA7C7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FA7C7F'>&nbsp;@&nbsp;</font><font style='background-color: #FA7C7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FA7C7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FA7C7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_PINK = -0x1.0b214ep126F;
    static { NAMED.put("Deep Yellowish Pink", -0x1.0b214ep126F); LIST.add(-0x1.0b214ep126F); }

    /**
     * This color constant "Light Yellowish Pink" has RGBA8888 code {@code FFD0C1FF}, L 0.84705883, A 0.5176471, B 0.5137255, alpha 1.0, hue 0.107399724, saturation 0.85374993, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.0709bp126F}.
     * <pre>
     * <font style='background-color: #FFD0C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD0C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD0C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD0C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD0C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD0C1'>&nbsp;@&nbsp;</font><font style='background-color: #FFD0C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD0C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD0C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_PINK = -0x1.0709bp126F;
    static { NAMED.put("Light Yellowish Pink", -0x1.0709bp126F); LIST.add(-0x1.0709bp126F); }

    /**
     * This color constant "Moderate Yellowish Pink" has RGBA8888 code {@code F2B5A5FF}, L 0.76862746, A 0.5254902, B 0.5176471, alpha 1.0, hue 0.09872868, saturation 0.68818605, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.090d88p126F}.
     * <pre>
     * <font style='background-color: #F2B5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2B5A5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2B5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2B5A5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2B5A5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2B5A5'>&nbsp;@&nbsp;</font><font style='background-color: #F2B5A5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2B5A5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2B5A5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_PINK = -0x1.090d88p126F;
    static { NAMED.put("Moderate Yellowish Pink", -0x1.090d88p126F); LIST.add(-0x1.090d88p126F); }

    /**
     * This color constant "Dark Yellowish Pink" has RGBA8888 code {@code DD918EFF}, L 0.6627451, A 0.5372549, B 0.5137255, alpha 1.0, hue 0.060548004, saturation 0.53851646, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.071352p126F}.
     * <pre>
     * <font style='background-color: #DD918E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD918E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD918E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DD918E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DD918E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DD918E'>&nbsp;@&nbsp;</font><font style='background-color: #DD918E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DD918E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DD918E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_PINK = -0x1.071352p126F;
    static { NAMED.put("Dark Yellowish Pink", -0x1.071352p126F); LIST.add(-0x1.071352p126F); }

    /**
     * This color constant "Pale Yellowish Pink" has RGBA8888 code {@code FDDCDEFF}, L 0.88235295, A 0.5137255, B 0.5019608, alpha 1.0, hue 0.038986836, saturation 0.74965554, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.0107c2p126F}.
     * <pre>
     * <font style='background-color: #FDDCDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDDCDE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDDCDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDDCDE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDDCDE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDDCDE'>&nbsp;@&nbsp;</font><font style='background-color: #FDDCDE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDDCDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDDCDE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOWISH_PINK = -0x1.0107c2p126F;
    static { NAMED.put("Pale Yellowish Pink", -0x1.0107c2p126F); LIST.add(-0x1.0107c2p126F); }

    /**
     * This color constant "Grayish Yellowish Pink" has RGBA8888 code {@code DEB8BCFF}, L 0.7607843, A 0.5176471, B 0.5019608, alpha 1.0, hue 0.03142344, saturation 0.39223227, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.010984p126F}.
     * <pre>
     * <font style='background-color: #DEB8BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEB8BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEB8BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEB8BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEB8BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEB8BC'>&nbsp;@&nbsp;</font><font style='background-color: #DEB8BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEB8BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEB8BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOWISH_PINK = -0x1.010984p126F;
    static { NAMED.put("Grayish Yellowish Pink", -0x1.010984p126F); LIST.add(-0x1.010984p126F); }

    /**
     * This color constant "Brownish Pink" has RGBA8888 code {@code DEBCB8FF}, L 0.76862746, A 0.5137255, B 0.5058824, alpha 1.0, hue 0.073790275, saturation 0.372678, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.030788p126F}.
     * <pre>
     * <font style='background-color: #DEBCB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEBCB8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEBCB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEBCB8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEBCB8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEBCB8'>&nbsp;@&nbsp;</font><font style='background-color: #DEBCB8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEBCB8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEBCB8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_PINK = -0x1.030788p126F;
    static { NAMED.put("Brownish Pink", -0x1.030788p126F); LIST.add(-0x1.030788p126F); }

    /**
     * This color constant "Vivid Reddish Orange" has RGBA8888 code {@code FF512EFF}, L 0.5764706, A 0.58431375, B 0.5568628, alpha 1.0, hue 0.0952538, saturation 0.96825653, and chroma 0.20259848.
     * It can be represented as a packed float with the constant {@code -0x1.1d2b26p126F}.
     * <pre>
     * <font style='background-color: #FF512E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF512E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF512E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF512E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF512E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF512E'>&nbsp;@&nbsp;</font><font style='background-color: #FF512E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF512E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF512E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_REDDISH_ORANGE = -0x1.1d2b26p126F;
    static { NAMED.put("Vivid Reddish Orange", -0x1.1d2b26p126F); LIST.add(-0x1.1d2b26p126F); }

    /**
     * This color constant "Strong Reddish Orange" has RGBA8888 code {@code F16F4EFF}, L 0.60784316, A 0.56078434, B 0.54509807, alpha 1.0, hue 0.102429084, saturation 0.8, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.171f36p126F}.
     * <pre>
     * <font style='background-color: #F16F4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F16F4E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F16F4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F16F4E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F16F4E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F16F4E'>&nbsp;@&nbsp;</font><font style='background-color: #F16F4E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F16F4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F16F4E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_ORANGE = -0x1.171f36p126F;
    static { NAMED.put("Strong Reddish Orange", -0x1.171f36p126F); LIST.add(-0x1.171f36p126F); }

    /**
     * This color constant "Deep Reddish Orange" has RGBA8888 code {@code DC320AFF}, L 0.47058824, A 0.58431375, B 0.5568628, alpha 1.0, hue 0.0952538, saturation 0.96825653, and chroma 0.20259848.
     * It can be represented as a packed float with the constant {@code -0x1.1d2afp126F}.
     * <pre>
     * <font style='background-color: #DC320A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC320A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC320A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC320A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC320A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC320A'>&nbsp;@&nbsp;</font><font style='background-color: #DC320A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC320A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC320A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_ORANGE = -0x1.1d2afp126F;
    static { NAMED.put("Deep Reddish Orange", -0x1.1d2afp126F); LIST.add(-0x1.1d2afp126F); }

    /**
     * This color constant "Moderate Reddish Orange" has RGBA8888 code {@code E37C64FF}, L 0.6156863, A 0.54901963, B 0.53333336, alpha 1.0, hue 0.09638812, saturation 0.6588078, and chroma 0.11809544.
     * It can be represented as a packed float with the constant {@code -0x1.11193ap126F}.
     * <pre>
     * <font style='background-color: #E37C64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E37C64; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E37C64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E37C64'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E37C64'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E37C64'>&nbsp;@&nbsp;</font><font style='background-color: #E37C64; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E37C64;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E37C64; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_ORANGE = -0x1.11193ap126F;
    static { NAMED.put("Moderate Reddish Orange", -0x1.11193ap126F); LIST.add(-0x1.11193ap126F); }

    /**
     * This color constant "Dark Reddish Orange" has RGBA8888 code {@code CD4525FF}, L 0.47058824, A 0.5686275, B 0.54901963, alpha 1.0, hue 0.09956196, saturation 0.8223557, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.1922fp126F}.
     * <pre>
     * <font style='background-color: #CD4525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD4525; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD4525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CD4525'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CD4525'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CD4525'>&nbsp;@&nbsp;</font><font style='background-color: #CD4525; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CD4525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CD4525; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_ORANGE = -0x1.1922fp126F;
    static { NAMED.put("Dark Reddish Orange", -0x1.1922fp126F); LIST.add(-0x1.1922fp126F); }

    /**
     * This color constant "Grayish Reddish Orange" has RGBA8888 code {@code D28477FF}, L 0.6156863, A 0.5372549, B 0.52156866, alpha 1.0, hue 0.08601887, saturation 0.48591265, and chroma 0.08575976.
     * It can be represented as a packed float with the constant {@code -0x1.0b133ap126F}.
     * <pre>
     * <font style='background-color: #D28477;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28477; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28477;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D28477'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D28477'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D28477'>&nbsp;@&nbsp;</font><font style='background-color: #D28477; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D28477;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D28477; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_ORANGE = -0x1.0b133ap126F;
    static { NAMED.put("Grayish Reddish Orange", -0x1.0b133ap126F); LIST.add(-0x1.0b133ap126F); }

    /**
     * This color constant "Strong Reddish Brown" has RGBA8888 code {@code B51C01FF}, L 0.37254903, A 0.5803922, B 0.54901963, alpha 1.0, hue 0.08823052, saturation 0.968556, and chroma 0.18758136.
     * It can be represented as a packed float with the constant {@code -0x1.1928bep126F}.
     * <pre>
     * <font style='background-color: #B51C01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B51C01; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B51C01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B51C01'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B51C01'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B51C01'>&nbsp;@&nbsp;</font><font style='background-color: #B51C01; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B51C01;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B51C01; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_BROWN = -0x1.1928bep126F;
    static { NAMED.put("Strong Reddish Brown", -0x1.1928bep126F); LIST.add(-0x1.1928bep126F); }

    /**
     * This color constant "Deep Reddish Brown" has RGBA8888 code {@code 890014FF}, L 0.27058825, A 0.57254905, B 0.53333336, alpha 1.0, hue 0.070401505, saturation 0.93439096, and chroma 0.15905683.
     * It can be represented as a packed float with the constant {@code -0x1.11248ap126F}.
     * <pre>
     * <font style='background-color: #890014;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #890014; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #890014;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #890014'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #890014'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #890014'>&nbsp;@&nbsp;</font><font style='background-color: #890014; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #890014;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #890014; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_BROWN = -0x1.11248ap126F;
    static { NAMED.put("Deep Reddish Brown", -0x1.11248ap126F); LIST.add(-0x1.11248ap126F); }

    /**
     * This color constant "Light Reddish Brown" has RGBA8888 code {@code C8897DFF}, L 0.6156863, A 0.5294118, B 0.5176471, alpha 1.0, hue 0.088913955, saturation 0.39308253, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.090f3ap126F}.
     * <pre>
     * <font style='background-color: #C8897D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8897D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8897D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8897D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8897D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8897D'>&nbsp;@&nbsp;</font><font style='background-color: #C8897D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8897D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8897D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_REDDISH_BROWN = -0x1.090f3ap126F;
    static { NAMED.put("Light Reddish Brown", -0x1.090f3ap126F); LIST.add(-0x1.090f3ap126F); }

    /**
     * This color constant "Moderate Reddish Brown" has RGBA8888 code {@code AA4742FF}, L 0.41960785, A 0.5568628, B 0.5254902, alpha 1.0, hue 0.06948605, saturation 0.5611168, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.0d1cd6p126F}.
     * <pre>
     * <font style='background-color: #AA4742;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA4742; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA4742;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AA4742'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AA4742'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AA4742'>&nbsp;@&nbsp;</font><font style='background-color: #AA4742; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AA4742;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AA4742; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_BROWN = -0x1.0d1cd6p126F;
    static { NAMED.put("Moderate Reddish Brown", -0x1.0d1cd6p126F); LIST.add(-0x1.0d1cd6p126F); }

    /**
     * This color constant "Dark Reddish Brown" has RGBA8888 code {@code 662129FF}, L 0.22745098, A 0.54901963, B 0.5137255, alpha 1.0, hue 0.04749672, saturation 0.68007356, and chroma 0.10141215.
     * It can be represented as a packed float with the constant {@code -0x1.071874p126F}.
     * <pre>
     * <font style='background-color: #662129;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #662129; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #662129;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #662129'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #662129'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #662129'>&nbsp;@&nbsp;</font><font style='background-color: #662129; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #662129;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #662129; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_BROWN = -0x1.071874p126F;
    static { NAMED.put("Dark Reddish Brown", -0x1.071874p126F); LIST.add(-0x1.071874p126F); }

    /**
     * This color constant "Light Grayish Reddish Brown" has RGBA8888 code {@code B78F88FF}, L 0.6117647, A 0.5176471, B 0.50980395, alpha 1.0, hue 0.08601887, saturation 0.23799802, and chroma 0.040217306.
     * It can be represented as a packed float with the constant {@code -0x1.050938p126F}.
     * <pre>
     * <font style='background-color: #B78F88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B78F88; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B78F88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B78F88'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B78F88'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B78F88'>&nbsp;@&nbsp;</font><font style='background-color: #B78F88; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B78F88;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B78F88; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_REDDISH_BROWN = -0x1.050938p126F;
    static { NAMED.put("Light Grayish Reddish Brown", -0x1.050938p126F); LIST.add(-0x1.050938p126F); }

    /**
     * This color constant "Grayish Reddish Brown" has RGBA8888 code {@code 925854FF}, L 0.42352942, A 0.53333336, B 0.5137255, alpha 1.0, hue 0.06655477, saturation 0.3338596, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.0710d8p126F}.
     * <pre>
     * <font style='background-color: #925854;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #925854; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #925854;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #925854'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #925854'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #925854'>&nbsp;@&nbsp;</font><font style='background-color: #925854; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #925854;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #925854; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_BROWN = -0x1.0710d8p126F;
    static { NAMED.put("Grayish Reddish Brown", -0x1.0710d8p126F); LIST.add(-0x1.0710d8p126F); }

    /**
     * This color constant "Dark Grayish Reddish Brown" has RGBA8888 code {@code 603434FF}, L 0.25882354, A 0.5294118, B 0.50980395, alpha 1.0, hue 0.057087466, saturation 0.39739552, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.050e84p126F}.
     * <pre>
     * <font style='background-color: #603434;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #603434; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #603434;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #603434'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #603434'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #603434'>&nbsp;@&nbsp;</font><font style='background-color: #603434; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #603434;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #603434; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_REDDISH_BROWN = -0x1.050e84p126F;
    static { NAMED.put("Dark Grayish Reddish Brown", -0x1.050e84p126F); LIST.add(-0x1.050e84p126F); }

    /**
     * This color constant "Vivid Orange" has RGBA8888 code {@code FF8C38FF}, L 0.6784314, A 0.5411765, B 0.5647059, alpha 1.0, hue 0.15858527, saturation 0.89993143, and chroma 0.15279381.
     * It can be represented as a packed float with the constant {@code -0x1.21155ap126F}.
     * <pre>
     * <font style='background-color: #FF8C38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8C38; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8C38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF8C38'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF8C38'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF8C38'>&nbsp;@&nbsp;</font><font style='background-color: #FF8C38; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8C38;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8C38; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_ORANGE = -0x1.21155ap126F;
    static { NAMED.put("Vivid Orange", -0x1.21155ap126F); LIST.add(-0x1.21155ap126F); }

    /**
     * This color constant "Strong Orange" has RGBA8888 code {@code FF8F2FFF}, L 0.6862745, A 0.5372549, B 0.5686275, alpha 1.0, hue 0.16928826, saturation 0.91516715, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.23135ep126F}.
     * <pre>
     * <font style='background-color: #FF8F2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8F2F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8F2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF8F2F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF8F2F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF8F2F'>&nbsp;@&nbsp;</font><font style='background-color: #FF8F2F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8F2F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8F2F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_ORANGE = -0x1.23135ep126F;
    static { NAMED.put("Strong Orange", -0x1.23135ep126F); LIST.add(-0x1.23135ep126F); }

    /**
     * This color constant "Deep Orange" has RGBA8888 code {@code E86A00FF}, L 0.5764706, A 0.5529412, B 0.5686275, alpha 1.0, hue 0.14477962, saturation 0.9914569, and chroma 0.17267215.
     * It can be represented as a packed float with the constant {@code -0x1.231b26p126F}.
     * <pre>
     * <font style='background-color: #E86A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E86A00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E86A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E86A00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E86A00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E86A00'>&nbsp;@&nbsp;</font><font style='background-color: #E86A00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E86A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E86A00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE = -0x1.231b26p126F;
    static { NAMED.put("Deep Orange", -0x1.231b26p126F); LIST.add(-0x1.231b26p126F); }

    /**
     * This color constant "Light Orange" has RGBA8888 code {@code FFBE9BFF}, L 0.8, A 0.5254902, B 0.5294118, alpha 1.0, hue 0.13558689, saturation 0.9663769, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.0f0d98p126F}.
     * <pre>
     * <font style='background-color: #FFBE9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBE9B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBE9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBE9B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBE9B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBE9B'>&nbsp;@&nbsp;</font><font style='background-color: #FFBE9B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBE9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBE9B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_ORANGE = -0x1.0f0d98p126F;
    static { NAMED.put("Light Orange", -0x1.0f0d98p126F); LIST.add(-0x1.0f0d98p126F); }

    /**
     * This color constant "Moderate Orange" has RGBA8888 code {@code F09C73FF}, L 0.7019608, A 0.53333336, B 0.5372549, alpha 1.0, hue 0.13336256, saturation 0.7272229, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.131166p126F}.
     * <pre>
     * <font style='background-color: #F09C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F09C73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F09C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F09C73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F09C73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F09C73'>&nbsp;@&nbsp;</font><font style='background-color: #F09C73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F09C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F09C73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_ORANGE = -0x1.131166p126F;
    static { NAMED.put("Moderate Orange", -0x1.131166p126F); LIST.add(-0x1.131166p126F); }

    /**
     * This color constant "Brownish Orange" has RGBA8888 code {@code D7712DFF}, L 0.5686275, A 0.5411765, B 0.5568628, alpha 1.0, hue 0.14928202, saturation 0.8267145, and chroma 0.13986339.
     * It can be represented as a packed float with the constant {@code -0x1.1d1522p126F}.
     * <pre>
     * <font style='background-color: #D7712D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7712D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7712D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7712D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7712D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7712D'>&nbsp;@&nbsp;</font><font style='background-color: #D7712D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7712D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7712D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_ORANGE = -0x1.1d1522p126F;
    static { NAMED.put("Brownish Orange", -0x1.1d1522p126F); LIST.add(-0x1.1d1522p126F); }

    /**
     * This color constant "Strong Brown" has RGBA8888 code {@code AE5000FF}, L 0.43529412, A 0.5411765, B 0.5568628, alpha 1.0, hue 0.14928202, saturation 1.0054636, and chroma 0.13986339.
     * It can be represented as a packed float with the constant {@code -0x1.1d14dep126F}.
     * <pre>
     * <font style='background-color: #AE5000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE5000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE5000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE5000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE5000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE5000'>&nbsp;@&nbsp;</font><font style='background-color: #AE5000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE5000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE5000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BROWN = -0x1.1d14dep126F;
    static { NAMED.put("Strong Brown", -0x1.1d14dep126F); LIST.add(-0x1.1d14dep126F); }

    /**
     * This color constant "Deep Brown" has RGBA8888 code {@code 79200AFF}, L 0.25882354, A 0.5529412, B 0.5372549, alpha 1.0, hue 0.09872868, saturation 0.95581394, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.131a84p126F}.
     * <pre>
     * <font style='background-color: #79200A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #79200A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #79200A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #79200A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #79200A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #79200A'>&nbsp;@&nbsp;</font><font style='background-color: #79200A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #79200A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #79200A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BROWN = -0x1.131a84p126F;
    static { NAMED.put("Deep Brown", -0x1.131a84p126F); LIST.add(-0x1.131a84p126F); }

    /**
     * This color constant "Light Brown" has RGBA8888 code {@code D5855EFF}, L 0.6156863, A 0.53333336, B 0.5372549, alpha 1.0, hue 0.13336256, saturation 0.5275931, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.13113ap126F}.
     * <pre>
     * <font style='background-color: #D5855E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5855E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5855E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5855E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5855E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5855E'>&nbsp;@&nbsp;</font><font style='background-color: #D5855E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5855E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5855E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BROWN = -0x1.13113ap126F;
    static { NAMED.put("Light Brown", -0x1.13113ap126F); LIST.add(-0x1.13113ap126F); }

    /**
     * This color constant "Moderate Brown" has RGBA8888 code {@code 995538FF}, L 0.41960785, A 0.53333336, B 0.53333336, alpha 1.0, hue 0.125, saturation 0.6208743, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.1110d6p126F}.
     * <pre>
     * <font style='background-color: #995538;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #995538; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #995538;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #995538'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #995538'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #995538'>&nbsp;@&nbsp;</font><font style='background-color: #995538; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #995538;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #995538; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BROWN = -0x1.1110d6p126F;
    static { NAMED.put("Moderate Brown", -0x1.1110d6p126F); LIST.add(-0x1.1110d6p126F); }

    /**
     * This color constant "Dark Brown" has RGBA8888 code {@code 63290EFF}, L 0.23137255, A 0.53333336, B 0.53333336, alpha 1.0, hue 0.125, saturation 0.9091373, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.111076p126F}.
     * <pre>
     * <font style='background-color: #63290E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63290E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63290E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #63290E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #63290E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #63290E'>&nbsp;@&nbsp;</font><font style='background-color: #63290E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63290E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63290E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BROWN = -0x1.111076p126F;
    static { NAMED.put("Dark Brown", -0x1.111076p126F); LIST.add(-0x1.111076p126F); }

    /**
     * This color constant "Light Grayish Brown" has RGBA8888 code {@code B88D7AFF}, L 0.6039216, A 0.5176471, B 0.5176471, alpha 1.0, hue 0.125, saturation 0.27196413, and chroma 0.049718447.
     * It can be represented as a packed float with the constant {@code -0x1.090934p126F}.
     * <pre>
     * <font style='background-color: #B88D7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B88D7A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B88D7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B88D7A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B88D7A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B88D7A'>&nbsp;@&nbsp;</font><font style='background-color: #B88D7A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B88D7A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B88D7A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_BROWN = -0x1.090934p126F;
    static { NAMED.put("Light Grayish Brown", -0x1.090934p126F); LIST.add(-0x1.090934p126F); }

    /**
     * This color constant "Grayish Brown" has RGBA8888 code {@code 8D5C4FFF}, L 0.42352942, A 0.5254902, B 0.5176471, alpha 1.0, hue 0.09872868, saturation 0.34409302, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.090cd8p126F}.
     * <pre>
     * <font style='background-color: #8D5C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D5C4F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D5C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8D5C4F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8D5C4F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8D5C4F'>&nbsp;@&nbsp;</font><font style='background-color: #8D5C4F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8D5C4F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8D5C4F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_BROWN = -0x1.090cd8p126F;
    static { NAMED.put("Grayish Brown", -0x1.090cd8p126F); LIST.add(-0x1.090cd8p126F); }

    /**
     * This color constant "Dark Grayish Brown" has RGBA8888 code {@code 5B372FFF}, L 0.25490198, A 0.52156866, B 0.5137255, alpha 1.0, hue 0.09359558, saturation 0.36980012, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.070a82p126F}.
     * <pre>
     * <font style='background-color: #5B372F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B372F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B372F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B372F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B372F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B372F'>&nbsp;@&nbsp;</font><font style='background-color: #5B372F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B372F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B372F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_BROWN = -0x1.070a82p126F;
    static { NAMED.put("Dark Grayish Brown", -0x1.070a82p126F); LIST.add(-0x1.070a82p126F); }

    /**
     * This color constant "Light Brownish Gray" has RGBA8888 code {@code AB938DFF}, L 0.6117647, A 0.50980395, B 0.5058824, alpha 1.0, hue 0.09359558, saturation 0.14716536, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.030538p126F}.
     * <pre>
     * <font style='background-color: #AB938D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB938D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB938D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB938D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB938D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB938D'>&nbsp;@&nbsp;</font><font style='background-color: #AB938D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB938D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB938D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BROWNISH_GRAY = -0x1.030538p126F;
    static { NAMED.put("Light Brownish Gray", -0x1.030538p126F); LIST.add(-0x1.030538p126F); }

    /**
     * This color constant "Brownish Gray" has RGBA8888 code {@code 7D625FFF}, L 0.42352942, A 0.5137255, B 0.5058824, alpha 1.0, hue 0.073790275, saturation 0.1490712, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.0306d8p126F}.
     * <pre>
     * <font style='background-color: #7D625F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D625F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D625F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7D625F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7D625F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7D625F'>&nbsp;@&nbsp;</font><font style='background-color: #7D625F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7D625F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7D625F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_GRAY = -0x1.0306d8p126F;
    static { NAMED.put("Brownish Gray", -0x1.0306d8p126F); LIST.add(-0x1.0306d8p126F); }

    /**
     * This color constant "Brownish Black" has RGBA8888 code {@code 432C21FF}, L 0.19215687, A 0.5137255, B 0.5137255, alpha 1.0, hue 0.125, saturation 0.43514264, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.070662p126F}.
     * <pre>
     * <font style='background-color: #432C21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #432C21; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #432C21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #432C21'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #432C21'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #432C21'>&nbsp;@&nbsp;</font><font style='background-color: #432C21; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #432C21;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #432C21; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWNISH_BLACK = -0x1.070662p126F;
    static { NAMED.put("Brownish Black", -0x1.070662p126F); LIST.add(-0x1.070662p126F); }

    /**
     * This color constant "Brilliant Orange Yellow" has RGBA8888 code {@code FFCE71FF}, L 0.827451, A 0.5058824, B 0.5568628, alpha 1.0, hue 0.22889122, saturation 0.9457966, and chroma 0.11388578.
     * It can be represented as a packed float with the constant {@code -0x1.1d03a6p126F}.
     * <pre>
     * <font style='background-color: #FFCE71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFCE71; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFCE71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFCE71'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFCE71'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFCE71'>&nbsp;@&nbsp;</font><font style='background-color: #FFCE71; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFCE71;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFCE71; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_ORANGE_YELLOW = -0x1.1d03a6p126F;
    static { NAMED.put("Brilliant Orange Yellow", -0x1.1d03a6p126F); LIST.add(-0x1.1d03a6p126F); }

    /**
     * This color constant "Strong Orange Yellow" has RGBA8888 code {@code FFAF2AFF}, L 0.7490196, A 0.5137255, B 0.5764706, alpha 1.0, hue 0.21857657, saturation 0.90649235, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.27077ep126F}.
     * <pre>
     * <font style='background-color: #FFAF2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFAF2A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFAF2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFAF2A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFAF2A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFAF2A'>&nbsp;@&nbsp;</font><font style='background-color: #FFAF2A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFAF2A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFAF2A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_ORANGE_YELLOW = -0x1.27077ep126F;
    static { NAMED.put("Strong Orange Yellow", -0x1.27077ep126F); LIST.add(-0x1.27077ep126F); }

    /**
     * This color constant "Deep Orange Yellow" has RGBA8888 code {@code EE9300FF}, L 0.6666667, A 0.5254902, B 0.5764706, alpha 1.0, hue 0.19642948, saturation 0.98556376, and chroma 0.1605844.
     * It can be represented as a packed float with the constant {@code -0x1.270d54p126F}.
     * <pre>
     * <font style='background-color: #EE9300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE9300; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE9300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EE9300'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EE9300'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EE9300'>&nbsp;@&nbsp;</font><font style='background-color: #EE9300; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EE9300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EE9300; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_ORANGE_YELLOW = -0x1.270d54p126F;
    static { NAMED.put("Deep Orange Yellow", -0x1.270d54p126F); LIST.add(-0x1.270d54p126F); }

    /**
     * This color constant "Light Orange Yellow" has RGBA8888 code {@code FFD198FF}, L 0.8392157, A 0.50980395, B 0.5372549, alpha 1.0, hue 0.20362332, saturation 0.8700256, and chroma 0.07674564.
     * It can be represented as a packed float with the constant {@code -0x1.1305acp126F}.
     * <pre>
     * <font style='background-color: #FFD198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD198; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD198'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD198'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD198'>&nbsp;@&nbsp;</font><font style='background-color: #FFD198; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD198;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD198; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_ORANGE_YELLOW = -0x1.1305acp126F;
    static { NAMED.put("Light Orange Yellow", -0x1.1305acp126F); LIST.add(-0x1.1305acp126F); }

    /**
     * This color constant "Moderate Orange Yellow" has RGBA8888 code {@code F7B56EFF}, L 0.7647059, A 0.5176471, B 0.54901963, alpha 1.0, hue 0.19157475, saturation 0.77379936, and chroma 0.10379164.
     * It can be represented as a packed float with the constant {@code -0x1.190986p126F}.
     * <pre>
     * <font style='background-color: #F7B56E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7B56E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7B56E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7B56E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7B56E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7B56E'>&nbsp;@&nbsp;</font><font style='background-color: #F7B56E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7B56E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7B56E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_ORANGE_YELLOW = -0x1.190986p126F;
    static { NAMED.put("Moderate Orange Yellow", -0x1.190986p126F); LIST.add(-0x1.190986p126F); }

    /**
     * This color constant "Dark Orange Yellow" has RGBA8888 code {@code E39331FF}, L 0.654902, A 0.52156866, B 0.5647059, alpha 1.0, hue 0.19601284, saturation 0.83850026, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.210b4ep126F}.
     * <pre>
     * <font style='background-color: #E39331;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E39331; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E39331;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E39331'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E39331'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E39331'>&nbsp;@&nbsp;</font><font style='background-color: #E39331; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E39331;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E39331; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_ORANGE_YELLOW = -0x1.210b4ep126F;
    static { NAMED.put("Dark Orange Yellow", -0x1.210b4ep126F); LIST.add(-0x1.210b4ep126F); }

    /**
     * This color constant "Pale Orange Yellow" has RGBA8888 code {@code F7D2B3FF}, L 0.84313726, A 0.50980395, B 0.52156866, alpha 1.0, hue 0.17620972, saturation 0.67082036, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0b05aep126F}.
     * <pre>
     * <font style='background-color: #F7D2B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7D2B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7D2B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7D2B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7D2B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7D2B3'>&nbsp;@&nbsp;</font><font style='background-color: #F7D2B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7D2B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7D2B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_ORANGE_YELLOW = -0x1.0b05aep126F;
    static { NAMED.put("Pale Orange Yellow", -0x1.0b05aep126F); LIST.add(-0x1.0b05aep126F); }

    /**
     * This color constant "Strong Yellowish Brown" has RGBA8888 code {@code BC7700FF}, L 0.5411765, A 0.5176471, B 0.5647059, alpha 1.0, hue 0.20448297, saturation 0.95784026, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.210914p126F}.
     * <pre>
     * <font style='background-color: #BC7700;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC7700; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC7700;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BC7700'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BC7700'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BC7700'>&nbsp;@&nbsp;</font><font style='background-color: #BC7700; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BC7700;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BC7700; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_BROWN = -0x1.210914p126F;
    static { NAMED.put("Strong Yellowish Brown", -0x1.210914p126F); LIST.add(-0x1.210914p126F); }

    /**
     * This color constant "Deep Yellowish Brown" has RGBA8888 code {@code 854E00FF}, L 0.36862746, A 0.5176471, B 0.54901963, alpha 1.0, hue 0.19157475, saturation 0.92855924, and chroma 0.10379164.
     * It can be represented as a packed float with the constant {@code -0x1.1908bcp126F}.
     * <pre>
     * <font style='background-color: #854E00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #854E00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #854E00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #854E00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #854E00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #854E00'>&nbsp;@&nbsp;</font><font style='background-color: #854E00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #854E00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #854E00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_BROWN = -0x1.1908bcp126F;
    static { NAMED.put("Deep Yellowish Brown", -0x1.1908bcp126F); LIST.add(-0x1.1908bcp126F); }

    /**
     * This color constant "Light Yellowish Brown" has RGBA8888 code {@code E0A775FF}, L 0.70980394, A 0.5176471, B 0.5372549, alpha 1.0, hue 0.17620972, saturation 0.5200158, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.13096ap126F}.
     * <pre>
     * <font style='background-color: #E0A775;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0A775; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0A775;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E0A775'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E0A775'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E0A775'>&nbsp;@&nbsp;</font><font style='background-color: #E0A775; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E0A775;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E0A775; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_BROWN = -0x1.13096ap126F;
    static { NAMED.put("Light Yellowish Brown", -0x1.13096ap126F); LIST.add(-0x1.13096ap126F); }

    /**
     * This color constant "Moderate Yellowish Brown" has RGBA8888 code {@code A57748FF}, L 0.5176471, A 0.5137255, B 0.5372549, alpha 1.0, hue 0.189452, saturation 0.58217996, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.130708p126F}.
     * <pre>
     * <font style='background-color: #A57748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A57748; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A57748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A57748'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A57748'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A57748'>&nbsp;@&nbsp;</font><font style='background-color: #A57748; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A57748;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A57748; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_BROWN = -0x1.130708p126F;
    static { NAMED.put("Moderate Yellowish Brown", -0x1.130708p126F); LIST.add(-0x1.130708p126F); }

    /**
     * This color constant "Dark Yellowish Brown" has RGBA8888 code {@code 6C421FFF}, L 0.30588236, A 0.5176471, B 0.53333336, alpha 1.0, hue 0.16928826, saturation 0.73540217, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.11089cp126F}.
     * <pre>
     * <font style='background-color: #6C421F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C421F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C421F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C421F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C421F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C421F'>&nbsp;@&nbsp;</font><font style='background-color: #6C421F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C421F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C421F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_BROWN = -0x1.11089cp126F;
    static { NAMED.put("Dark Yellowish Brown", -0x1.11089cp126F); LIST.add(-0x1.11089cp126F); }

    /**
     * This color constant "Light Grayish Yellowish Brown" has RGBA8888 code {@code CDA899FF}, L 0.69803923, A 0.5137255, B 0.5137255, alpha 1.0, hue 0.125, saturation 0.3057759, and chroma 0.038669903.
     * It can be represented as a packed float with the constant {@code -0x1.070764p126F}.
     * <pre>
     * <font style='background-color: #CDA899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDA899; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDA899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CDA899'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CDA899'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CDA899'>&nbsp;@&nbsp;</font><font style='background-color: #CDA899; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CDA899;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CDA899; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_YELLOWISH_BROWN = -0x1.070764p126F;
    static { NAMED.put("Light Grayish Yellowish Brown", -0x1.070764p126F); LIST.add(-0x1.070764p126F); }

    /**
     * This color constant "Grayish Yellowish Brown" has RGBA8888 code {@code A07765FF}, L 0.5176471, A 0.5176471, B 0.5176471, alpha 1.0, hue 0.125, saturation 0.3008965, and chroma 0.049718447.
     * It can be represented as a packed float with the constant {@code -0x1.090908p126F}.
     * <pre>
     * <font style='background-color: #A07765;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A07765; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A07765;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A07765'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A07765'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A07765'>&nbsp;@&nbsp;</font><font style='background-color: #A07765; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A07765;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A07765; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOWISH_BROWN = -0x1.090908p126F;
    static { NAMED.put("Grayish Yellowish Brown", -0x1.090908p126F); LIST.add(-0x1.090908p126F); }

    /**
     * This color constant "Dark Grayish Yellowish Brown" has RGBA8888 code {@code 664D3BFF}, L 0.32941177, A 0.50980395, B 0.5176471, alpha 1.0, hue 0.16398115, saturation 0.3887301, and chroma 0.040217306.
     * It can be represented as a packed float with the constant {@code -0x1.0904a8p126F}.
     * <pre>
     * <font style='background-color: #664D3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #664D3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #664D3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #664D3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #664D3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #664D3B'>&nbsp;@&nbsp;</font><font style='background-color: #664D3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #664D3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #664D3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_YELLOWISH_BROWN = -0x1.0904a8p126F;
    static { NAMED.put("Dark Grayish Yellowish Brown", -0x1.0904a8p126F); LIST.add(-0x1.0904a8p126F); }

    /**
     * This color constant "Vivid Yellow" has RGBA8888 code {@code FFCC00FF}, L 0.8117647, A 0.49411765, B 0.5882353, alpha 1.0, hue 0.25690335, saturation 0.979648, and chroma 0.17617144.
     * It can be represented as a packed float with the constant {@code -0x1.2cfd9ep126F}.
     * <pre>
     * <font style='background-color: #FFCC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFCC00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFCC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFCC00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFCC00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFCC00'>&nbsp;@&nbsp;</font><font style='background-color: #FFCC00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFCC00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFCC00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOW = -0x1.2cfd9ep126F;
    static { NAMED.put("Vivid Yellow", -0x1.2cfd9ep126F); LIST.add(-0x1.2cfd9ep126F); }

    /**
     * This color constant "Brilliant Yellow" has RGBA8888 code {@code FFD95DFF}, L 0.84705883, A 0.49411765, B 0.5686275, alpha 1.0, hue 0.25882402, saturation 0.85846454, and chroma 0.13722007.
     * It can be represented as a packed float with the constant {@code -0x1.22fdbp126F}.
     * <pre>
     * <font style='background-color: #FFD95D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD95D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD95D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD95D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD95D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD95D'>&nbsp;@&nbsp;</font><font style='background-color: #FFD95D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD95D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD95D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOW = -0x1.22fdbp126F;
    static { NAMED.put("Brilliant Yellow", -0x1.22fdbp126F); LIST.add(-0x1.22fdbp126F); }

    /**
     * This color constant "Strong Yellow" has RGBA8888 code {@code F0BB1AFF}, L 0.7607843, A 0.49803922, B 0.5803922, alpha 1.0, hue 0.25, saturation 0.93333334, and chroma 0.16020387.
     * It can be represented as a packed float with the constant {@code -0x1.28ff84p126F}.
     * <pre>
     * <font style='background-color: #F0BB1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0BB1A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0BB1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0BB1A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0BB1A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0BB1A'>&nbsp;@&nbsp;</font><font style='background-color: #F0BB1A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0BB1A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0BB1A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOW = -0x1.28ff84p126F;
    static { NAMED.put("Strong Yellow", -0x1.28ff84p126F); LIST.add(-0x1.28ff84p126F); }

    /**
     * This color constant "Deep Yellow" has RGBA8888 code {@code D29F00FF}, L 0.65882355, A 0.5019608, B 0.5764706, alpha 1.0, hue 0.24205942, saturation 0.9768285, and chroma 0.15239382.
     * It can be represented as a packed float with the constant {@code -0x1.27015p126F}.
     * <pre>
     * <font style='background-color: #D29F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D29F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D29F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D29F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D29F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D29F00'>&nbsp;@&nbsp;</font><font style='background-color: #D29F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D29F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D29F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW = -0x1.27015p126F;
    static { NAMED.put("Deep Yellow", -0x1.27015p126F); LIST.add(-0x1.27015p126F); }

    /**
     * This color constant "Light Yellow" has RGBA8888 code {@code FADF98FF}, L 0.8666667, A 0.49803922, B 0.5411765, alpha 1.0, hue 0.25, saturation 0.7096774, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.14ffbap126F}.
     * <pre>
     * <font style='background-color: #FADF98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FADF98; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FADF98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FADF98'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FADF98'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FADF98'>&nbsp;@&nbsp;</font><font style='background-color: #FADF98; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FADF98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FADF98; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOW = -0x1.14ffbap126F;
    static { NAMED.put("Light Yellow", -0x1.14ffbap126F); LIST.add(-0x1.14ffbap126F); }

    /**
     * This color constant "Moderate Yellow" has RGBA8888 code {@code EABB56FF}, L 0.75686276, A 0.5019608, B 0.56078434, alpha 1.0, hue 0.24007216, saturation 0.71249866, and chroma 0.12115674.
     * It can be represented as a packed float with the constant {@code -0x1.1f0182p126F}.
     * <pre>
     * <font style='background-color: #EABB56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EABB56; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EABB56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EABB56'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EABB56'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EABB56'>&nbsp;@&nbsp;</font><font style='background-color: #EABB56; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EABB56;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EABB56; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOW = -0x1.1f0182p126F;
    static { NAMED.put("Moderate Yellow", -0x1.1f0182p126F); LIST.add(-0x1.1f0182p126F); }

    /**
     * This color constant "Dark Yellow" has RGBA8888 code {@code CB9F3CFF}, L 0.654902, A 0.5019608, B 0.56078434, alpha 1.0, hue 0.24007216, saturation 0.78201073, and chroma 0.12115674.
     * It can be represented as a packed float with the constant {@code -0x1.1f014ep126F}.
     * <pre>
     * <font style='background-color: #CB9F3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB9F3C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB9F3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB9F3C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB9F3C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB9F3C'>&nbsp;@&nbsp;</font><font style='background-color: #CB9F3C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB9F3C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB9F3C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOW = -0x1.1f014ep126F;
    static { NAMED.put("Dark Yellow", -0x1.1f014ep126F); LIST.add(-0x1.1f014ep126F); }

    /**
     * This color constant "Pale Yellow" has RGBA8888 code {@code F7E1BDFF}, L 0.8745098, A 0.5019608, B 0.52156866, alpha 1.0, hue 0.22370392, saturation 0.5793107, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.0b01bep126F}.
     * <pre>
     * <font style='background-color: #F7E1BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7E1BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7E1BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7E1BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7E1BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7E1BD'>&nbsp;@&nbsp;</font><font style='background-color: #F7E1BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7E1BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7E1BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW = -0x1.0b01bep126F;
    static { NAMED.put("Pale Yellow", -0x1.0b01bep126F); LIST.add(-0x1.0b01bep126F); }

    /**
     * This color constant "Grayish Yellow" has RGBA8888 code {@code DEBF84FF}, L 0.7647059, A 0.5019608, B 0.5372549, alpha 1.0, hue 0.23413046, saturation 0.4466611, and chroma 0.07432148.
     * It can be represented as a packed float with the constant {@code -0x1.130186p126F}.
     * <pre>
     * <font style='background-color: #DEBF84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEBF84; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEBF84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DEBF84'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DEBF84'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DEBF84'>&nbsp;@&nbsp;</font><font style='background-color: #DEBF84; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DEBF84;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DEBF84; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOW = -0x1.130186p126F;
    static { NAMED.put("Grayish Yellow", -0x1.130186p126F); LIST.add(-0x1.130186p126F); }

    /**
     * This color constant "Dark Grayish Yellow" has RGBA8888 code {@code C1A162FF}, L 0.654902, A 0.5019608, B 0.5411765, alpha 1.0, hue 0.235567, saturation 0.5387981, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.15014ep126F}.
     * <pre>
     * <font style='background-color: #C1A162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1A162; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1A162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C1A162'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C1A162'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C1A162'>&nbsp;@&nbsp;</font><font style='background-color: #C1A162; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C1A162;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C1A162; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_YELLOW = -0x1.15014ep126F;
    static { NAMED.put("Dark Grayish Yellow", -0x1.15014ep126F); LIST.add(-0x1.15014ep126F); }

    /**
     * This color constant "Yellowish White" has RGBA8888 code {@code F9E7E4FF}, L 0.9019608, A 0.5058824, B 0.5019608, alpha 1.0, hue 0.073790275, saturation 0.559017, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.0103ccp126F}.
     * <pre>
     * <font style='background-color: #F9E7E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9E7E4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9E7E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F9E7E4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F9E7E4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F9E7E4'>&nbsp;@&nbsp;</font><font style='background-color: #F9E7E4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F9E7E4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F9E7E4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOWISH_WHITE = -0x1.0103ccp126F;
    static { NAMED.put("Yellowish White", -0x1.0103ccp126F); LIST.add(-0x1.0103ccp126F); }

    /**
     * This color constant "Yellowish Gray" has RGBA8888 code {@code D5C7B7FF}, L 0.78431374, A 0.5019608, B 0.50980395, alpha 1.0, hue 0.19880433, saturation 0.19165319, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.05019p126F}.
     * <pre>
     * <font style='background-color: #D5C7B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5C7B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5C7B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5C7B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5C7B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5C7B7'>&nbsp;@&nbsp;</font><font style='background-color: #D5C7B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5C7B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5C7B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOWISH_GRAY = -0x1.05019p126F;
    static { NAMED.put("Yellowish Gray", -0x1.05019p126F); LIST.add(-0x1.05019p126F); }

    /**
     * This color constant "Light Olive Brown" has RGBA8888 code {@code B1892FFF}, L 0.57254905, A 0.5019608, B 0.5568628, alpha 1.0, hue 0.23941022, saturation 0.8126106, and chroma 0.11334858.
     * It can be represented as a packed float with the constant {@code -0x1.1d0124p126F}.
     * <pre>
     * <font style='background-color: #B1892F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1892F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1892F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1892F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1892F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1892F'>&nbsp;@&nbsp;</font><font style='background-color: #B1892F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1892F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1892F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE_BROWN = -0x1.1d0124p126F;
    static { NAMED.put("Light Olive Brown", -0x1.1d0124p126F); LIST.add(-0x1.1d0124p126F); }

    /**
     * This color constant "Moderate Olive Brown" has RGBA8888 code {@code 8A6713FF}, L 0.43529412, A 0.5019608, B 0.5529412, alpha 1.0, hue 0.23865414, saturation 0.905527, and chroma 0.105541065.
     * It can be represented as a packed float with the constant {@code -0x1.1b00dep126F}.
     * <pre>
     * <font style='background-color: #8A6713;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A6713; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A6713;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8A6713'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8A6713'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8A6713'>&nbsp;@&nbsp;</font><font style='background-color: #8A6713; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8A6713;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8A6713; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE_BROWN = -0x1.1b00dep126F;
    static { NAMED.put("Moderate Olive Brown", -0x1.1b00dep126F); LIST.add(-0x1.1b00dep126F); }

    /**
     * This color constant "Dark Olive Brown" has RGBA8888 code {@code 583A14FF}, L 0.25490198, A 0.50980395, B 0.53333336, alpha 1.0, hue 0.19880433, saturation 0.824942, and chroma 0.069218926.
     * It can be represented as a packed float with the constant {@code -0x1.110482p126F}.
     * <pre>
     * <font style='background-color: #583A14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #583A14; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #583A14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #583A14'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #583A14'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #583A14'>&nbsp;@&nbsp;</font><font style='background-color: #583A14; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #583A14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #583A14; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE_BROWN = -0x1.110482p126F;
    static { NAMED.put("Dark Olive Brown", -0x1.110482p126F); LIST.add(-0x1.110482p126F); }

    /**
     * This color constant "Vivid Greenish Yellow" has RGBA8888 code {@code F7E800FF}, L 0.8666667, A 0.47058824, B 0.5921569, alpha 1.0, hue 0.29515803, saturation 0.96153843, and chroma 0.19271713.
     * It can be represented as a packed float with the constant {@code -0x1.2ef1bap126F}.
     * <pre>
     * <font style='background-color: #F7E800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7E800; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7E800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F7E800'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F7E800'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F7E800'>&nbsp;@&nbsp;</font><font style='background-color: #F7E800; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F7E800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F7E800; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_GREENISH_YELLOW = -0x1.2ef1bap126F;
    static { NAMED.put("Vivid Greenish Yellow", -0x1.2ef1bap126F); LIST.add(-0x1.2ef1bap126F); }

    /**
     * This color constant "Brilliant Greenish Yellow" has RGBA8888 code {@code F5E736FF}, L 0.8666667, A 0.4745098, B 0.58431375, alpha 1.0, hue 0.29236877, saturation 0.877058, and chroma 0.17547716.
     * It can be represented as a packed float with the constant {@code -0x1.2af3bap126F}.
     * <pre>
     * <font style='background-color: #F5E736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E736; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5E736'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5E736'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5E736'>&nbsp;@&nbsp;</font><font style='background-color: #F5E736; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E736;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E736; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREENISH_YELLOW = -0x1.2af3bap126F;
    static { NAMED.put("Brilliant Greenish Yellow", -0x1.2af3bap126F); LIST.add(-0x1.2af3bap126F); }

    /**
     * This color constant "Strong Greenish Yellow" has RGBA8888 code {@code D3C81CFF}, L 0.7607843, A 0.4745098, B 0.5803922, alpha 1.0, hue 0.29428434, saturation 0.92937577, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.28f384p126F}.
     * <pre>
     * <font style='background-color: #D3C81C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3C81C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3C81C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3C81C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3C81C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3C81C'>&nbsp;@&nbsp;</font><font style='background-color: #D3C81C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3C81C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3C81C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREENISH_YELLOW = -0x1.28f384p126F;
    static { NAMED.put("Strong Greenish Yellow", -0x1.28f384p126F); LIST.add(-0x1.28f384p126F); }

    /**
     * This color constant "Deep Greenish Yellow" has RGBA8888 code {@code B6AB00FF}, L 0.65882355, A 0.47843137, B 0.5764706, alpha 1.0, hue 0.28898686, saturation 0.98169184, and chroma 0.1582875.
     * It can be represented as a packed float with the constant {@code -0x1.26f55p126F}.
     * <pre>
     * <font style='background-color: #B6AB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6AB00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6AB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6AB00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6AB00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6AB00'>&nbsp;@&nbsp;</font><font style='background-color: #B6AB00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6AB00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6AB00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_GREENISH_YELLOW = -0x1.26f55p126F;
    static { NAMED.put("Deep Greenish Yellow", -0x1.26f55p126F); LIST.add(-0x1.26f55p126F); }

    /**
     * This color constant "Light Greenish Yellow" has RGBA8888 code {@code FAE686FF}, L 0.8784314, A 0.49019608, B 0.5529412, alpha 1.0, hue 0.27259654, saturation 0.7252377, and chroma 0.107261956.
     * It can be represented as a packed float with the constant {@code -0x1.1afbcp126F}.
     * <pre>
     * <font style='background-color: #FAE686;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAE686; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAE686;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FAE686'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FAE686'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FAE686'>&nbsp;@&nbsp;</font><font style='background-color: #FAE686; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAE686;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAE686; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_YELLOW = -0x1.1afbcp126F;
    static { NAMED.put("Light Greenish Yellow", -0x1.1afbcp126F); LIST.add(-0x1.1afbcp126F); }

    /**
     * This color constant "Moderate Greenish Yellow" has RGBA8888 code {@code D0C556FF}, L 0.7529412, A 0.48235294, B 0.56078434, alpha 1.0, hue 0.28898686, saturation 0.7170618, and chroma 0.12609385.
     * It can be represented as a packed float with the constant {@code -0x1.1ef78p126F}.
     * <pre>
     * <font style='background-color: #D0C556;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0C556; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0C556;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0C556'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0C556'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0C556'>&nbsp;@&nbsp;</font><font style='background-color: #D0C556; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0C556;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0C556; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREENISH_YELLOW = -0x1.1ef78p126F;
    static { NAMED.put("Moderate Greenish Yellow", -0x1.1ef78p126F); LIST.add(-0x1.1ef78p126F); }

    /**
     * This color constant "Dark Greenish Yellow" has RGBA8888 code {@code B4A730FF}, L 0.64705884, A 0.48235294, B 0.5647059, alpha 1.0, hue 0.2867793, saturation 0.8316309, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.20f74ap126F}.
     * <pre>
     * <font style='background-color: #B4A730;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4A730; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4A730;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4A730'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4A730'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4A730'>&nbsp;@&nbsp;</font><font style='background-color: #B4A730; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4A730;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4A730; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_YELLOW = -0x1.20f74ap126F;
    static { NAMED.put("Dark Greenish Yellow", -0x1.20f74ap126F); LIST.add(-0x1.20f74ap126F); }

    /**
     * This color constant "Pale Greenish Yellow" has RGBA8888 code {@code F5E6ABFF}, L 0.88235295, A 0.49411765, B 0.53333336, alpha 1.0, hue 0.2676211, saturation 0.51745063, and chroma 0.06743233.
     * It can be represented as a packed float with the constant {@code -0x1.10fdc2p126F}.
     * <pre>
     * <font style='background-color: #F5E6AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E6AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E6AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5E6AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5E6AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5E6AB'>&nbsp;@&nbsp;</font><font style='background-color: #F5E6AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E6AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E6AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREENISH_YELLOW = -0x1.10fdc2p126F;
    static { NAMED.put("Pale Greenish Yellow", -0x1.10fdc2p126F); LIST.add(-0x1.10fdc2p126F); }

    /**
     * This color constant "Grayish Greenish Yellow" has RGBA8888 code {@code D6C37DFF}, L 0.7607843, A 0.49411765, B 0.5411765, alpha 1.0, hue 0.264433, saturation 0.49090493, and chroma 0.082864076.
     * It can be represented as a packed float with the constant {@code -0x1.14fd84p126F}.
     * <pre>
     * <font style='background-color: #D6C37D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6C37D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6C37D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6C37D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6C37D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6C37D'>&nbsp;@&nbsp;</font><font style='background-color: #D6C37D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6C37D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6C37D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_GREENISH_YELLOW = -0x1.14fd84p126F;
    static { NAMED.put("Grayish Greenish Yellow", -0x1.14fd84p126F); LIST.add(-0x1.14fd84p126F); }

    /**
     * This color constant "Light Olive" has RGBA8888 code {@code A78F25FF}, L 0.57254905, A 0.49019608, B 0.56078434, alpha 1.0, hue 0.26980346, saturation 0.84865874, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.1efb24p126F}.
     * <pre>
     * <font style='background-color: #A78F25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A78F25; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A78F25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A78F25'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A78F25'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A78F25'>&nbsp;@&nbsp;</font><font style='background-color: #A78F25; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A78F25;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A78F25; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE = -0x1.1efb24p126F;
    static { NAMED.put("Light Olive", -0x1.1efb24p126F); LIST.add(-0x1.1efb24p126F); }

    /**
     * This color constant "Moderate Olive" has RGBA8888 code {@code 7A6E14FF}, L 0.43529412, A 0.4862745, B 0.5529412, alpha 1.0, hue 0.28360078, saturation 0.89486384, and chroma 0.10895567.
     * It can be represented as a packed float with the constant {@code -0x1.1af8dep126F}.
     * <pre>
     * <font style='background-color: #7A6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A6E14; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7A6E14'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7A6E14'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7A6E14'>&nbsp;@&nbsp;</font><font style='background-color: #7A6E14; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7A6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7A6E14; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE = -0x1.1af8dep126F;
    static { NAMED.put("Moderate Olive", -0x1.1af8dep126F); LIST.add(-0x1.1af8dep126F); }

    /**
     * This color constant "Dark Olive" has RGBA8888 code {@code 4B3D00FF}, L 0.24313726, A 0.49411765, B 0.5372549, alpha 1.0, hue 0.26586956, saturation 0.913625, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.12fc7cp126F}.
     * <pre>
     * <font style='background-color: #4B3D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B3D00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B3D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B3D00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B3D00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B3D00'>&nbsp;@&nbsp;</font><font style='background-color: #4B3D00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B3D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B3D00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE = -0x1.12fc7cp126F;
    static { NAMED.put("Dark Olive", -0x1.12fc7cp126F); LIST.add(-0x1.12fc7cp126F); }

    /**
     * This color constant "Light Grayish Olive" has RGBA8888 code {@code A79871FF}, L 0.60784316, A 0.49803922, B 0.5254902, alpha 1.0, hue 0.25, saturation 0.35897437, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.0cff36p126F}.
     * <pre>
     * <font style='background-color: #A79871;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A79871; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A79871;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A79871'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A79871'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A79871'>&nbsp;@&nbsp;</font><font style='background-color: #A79871; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A79871;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A79871; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_OLIVE = -0x1.0cff36p126F;
    static { NAMED.put("Light Grayish Olive", -0x1.0cff36p126F); LIST.add(-0x1.0cff36p126F); }

    /**
     * This color constant "Grayish Olive" has RGBA8888 code {@code 786840FF}, L 0.42352942, A 0.49803922, B 0.5294118, alpha 1.0, hue 0.25, saturation 0.516129, and chroma 0.058723815.
     * It can be represented as a packed float with the constant {@code -0x1.0efed8p126F}.
     * <pre>
     * <font style='background-color: #786840;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #786840; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #786840;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #786840'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #786840'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #786840'>&nbsp;@&nbsp;</font><font style='background-color: #786840; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #786840;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #786840; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_OLIVE = -0x1.0efed8p126F;
    static { NAMED.put("Grayish Olive", -0x1.0efed8p126F); LIST.add(-0x1.0efed8p126F); }

    /**
     * This color constant "Dark Grayish Olive" has RGBA8888 code {@code 484121FF}, L 0.2509804, A 0.49411765, B 0.5254902, alpha 1.0, hue 0.27259654, saturation 0.61487544, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.0cfc8p126F}.
     * <pre>
     * <font style='background-color: #484121;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #484121; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #484121;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #484121'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #484121'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #484121'>&nbsp;@&nbsp;</font><font style='background-color: #484121; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #484121;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #484121; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_OLIVE = -0x1.0cfc8p126F;
    static { NAMED.put("Dark Grayish Olive", -0x1.0cfc8p126F); LIST.add(-0x1.0cfc8p126F); }

    /**
     * This color constant "Light Olive Gray" has RGBA8888 code {@code AC9684FF}, L 0.6156863, A 0.5058824, B 0.5137255, alpha 1.0, hue 0.17620972, saturation 0.20800632, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.07033ap126F}.
     * <pre>
     * <font style='background-color: #AC9684;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC9684; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC9684;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC9684'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC9684'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC9684'>&nbsp;@&nbsp;</font><font style='background-color: #AC9684; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC9684;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC9684; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_OLIVE_GRAY = -0x1.07033ap126F;
    static { NAMED.put("Light Olive Gray", -0x1.07033ap126F); LIST.add(-0x1.07033ap126F); }

    /**
     * This color constant "Olive Gray" has RGBA8888 code {@code 766352FF}, L 0.4117647, A 0.5058824, B 0.5137255, alpha 1.0, hue 0.17620972, saturation 0.27103853, and chroma 0.029749114.
     * It can be represented as a packed float with the constant {@code -0x1.0702d2p126F}.
     * <pre>
     * <font style='background-color: #766352;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #766352; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #766352;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #766352'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #766352'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #766352'>&nbsp;@&nbsp;</font><font style='background-color: #766352; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #766352;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #766352; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_GRAY = -0x1.0702d2p126F;
    static { NAMED.put("Olive Gray", -0x1.0702d2p126F); LIST.add(-0x1.0702d2p126F); }

    /**
     * This color constant "Olive Black" has RGBA8888 code {@code 352F1FFF}, L 0.18039216, A 0.49803922, B 0.5137255, alpha 1.0, hue 0.25, saturation 0.42105263, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.06fe5cp126F}.
     * <pre>
     * <font style='background-color: #352F1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #352F1F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #352F1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #352F1F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #352F1F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #352F1F'>&nbsp;@&nbsp;</font><font style='background-color: #352F1F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #352F1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #352F1F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_BLACK = -0x1.06fe5cp126F;
    static { NAMED.put("Olive Black", -0x1.06fe5cp126F); LIST.add(-0x1.06fe5cp126F); }

    /**
     * This color constant "Vivid Yellow Green" has RGBA8888 code {@code B0ED00FF}, L 0.81960785, A 0.43529412, B 0.5882353, alpha 1.0, hue 0.34674743, saturation 0.94975764, and chroma 0.21798135.
     * It can be represented as a packed float with the constant {@code -0x1.2cdfa2p126F}.
     * <pre>
     * <font style='background-color: #B0ED00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0ED00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0ED00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B0ED00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B0ED00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B0ED00'>&nbsp;@&nbsp;</font><font style='background-color: #B0ED00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B0ED00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B0ED00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOW_GREEN = -0x1.2cdfa2p126F;
    static { NAMED.put("Vivid Yellow Green", -0x1.2cdfa2p126F); LIST.add(-0x1.2cdfa2p126F); }

    /**
     * This color constant "Brilliant Yellow Green" has RGBA8888 code {@code CBED54FF}, L 0.84705883, A 0.45490196, B 0.57254905, alpha 1.0, hue 0.33353055, saturation 0.75705165, and chroma 0.17017984.
     * It can be represented as a packed float with the constant {@code -0x1.24e9bp126F}.
     * <pre>
     * <font style='background-color: #CBED54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBED54; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBED54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBED54'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBED54'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBED54'>&nbsp;@&nbsp;</font><font style='background-color: #CBED54; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBED54;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBED54; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOW_GREEN = -0x1.24e9bp126F;
    static { NAMED.put("Brilliant Yellow Green", -0x1.24e9bp126F); LIST.add(-0x1.24e9bp126F); }

    /**
     * This color constant "Strong Yellow Green" has RGBA8888 code {@code 91B615FF}, L 0.6509804, A 0.4509804, B 0.57254905, alpha 1.0, hue 0.33966506, saturation 0.9172328, and chroma 0.17443058.
     * It can be represented as a packed float with the constant {@code -0x1.24e74cp126F}.
     * <pre>
     * <font style='background-color: #91B615;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91B615; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91B615;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #91B615'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #91B615'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #91B615'>&nbsp;@&nbsp;</font><font style='background-color: #91B615; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91B615;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91B615; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOW_GREEN = -0x1.24e74cp126F;
    static { NAMED.put("Strong Yellow Green", -0x1.24e74cp126F); LIST.add(-0x1.24e74cp126F); }

    /**
     * This color constant "Deep Yellow Green" has RGBA8888 code {@code 558400FF}, L 0.4627451, A 0.44705883, B 0.5568628, alpha 1.0, hue 0.3636593, saturation 0.8821971, and chroma 0.15477823.
     * It can be represented as a packed float with the constant {@code -0x1.1ce4ecp126F}.
     * <pre>
     * <font style='background-color: #558400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #558400; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #558400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #558400'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #558400'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #558400'>&nbsp;@&nbsp;</font><font style='background-color: #558400; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #558400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #558400; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOW_GREEN = -0x1.1ce4ecp126F;
    static { NAMED.put("Deep Yellow Green", -0x1.1ce4ecp126F); LIST.add(-0x1.1ce4ecp126F); }

    /**
     * This color constant "Light Yellow Green" has RGBA8888 code {@code D7E997FF}, L 0.85882354, A 0.4745098, B 0.5411765, alpha 1.0, hue 0.32947695, saturation 0.43964788, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.14f3b6p126F}.
     * <pre>
     * <font style='background-color: #D7E997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7E997; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7E997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7E997'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7E997'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7E997'>&nbsp;@&nbsp;</font><font style='background-color: #D7E997; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7E997;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7E997; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOW_GREEN = -0x1.14f3b6p126F;
    static { NAMED.put("Light Yellow Green", -0x1.14f3b6p126F); LIST.add(-0x1.14f3b6p126F); }

    /**
     * This color constant "Moderate Yellow Green" has RGBA8888 code {@code 9BAF5CFF}, L 0.6509804, A 0.47058824, B 0.54509807, alpha 1.0, hue 0.33405274, saturation 0.5788518, and chroma 0.107261956.
     * It can be represented as a packed float with the constant {@code -0x1.16f14cp126F}.
     * <pre>
     * <font style='background-color: #9BAF5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BAF5C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BAF5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9BAF5C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9BAF5C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9BAF5C'>&nbsp;@&nbsp;</font><font style='background-color: #9BAF5C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BAF5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BAF5C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOW_GREEN = -0x1.16f14cp126F;
    static { NAMED.put("Moderate Yellow Green", -0x1.16f14cp126F); LIST.add(-0x1.16f14cp126F); }

    /**
     * This color constant "Pale Yellow Green" has RGBA8888 code {@code E3E4BBFF}, L 0.8627451, A 0.49019608, B 0.52156866, alpha 1.0, hue 0.30119568, saturation 0.23866247, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.0afbb8p126F}.
     * <pre>
     * <font style='background-color: #E3E4BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3E4BB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3E4BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3E4BB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3E4BB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3E4BB'>&nbsp;@&nbsp;</font><font style='background-color: #E3E4BB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3E4BB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3E4BB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_YELLOW_GREEN = -0x1.0afbb8p126F;
    static { NAMED.put("Pale Yellow Green", -0x1.0afbb8p126F); LIST.add(-0x1.0afbb8p126F); }

    /**
     * This color constant "Grayish Yellow Green" has RGBA8888 code {@code AAAD8DFF}, L 0.67058825, A 0.49019608, B 0.5176471, alpha 1.0, hue 0.310548, saturation 0.23934065, and chroma 0.040217306.
     * It can be represented as a packed float with the constant {@code -0x1.08fb56p126F}.
     * <pre>
     * <font style='background-color: #AAAD8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAAD8D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAAD8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAAD8D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAAD8D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAAD8D'>&nbsp;@&nbsp;</font><font style='background-color: #AAAD8D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAAD8D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAAD8D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_YELLOW_GREEN = -0x1.08fb56p126F;
    static { NAMED.put("Grayish Yellow Green", -0x1.08fb56p126F); LIST.add(-0x1.08fb56p126F); }

    /**
     * This color constant "Strong Olive Green" has RGBA8888 code {@code 336D00FF}, L 0.36862746, A 0.44313726, B 0.54901963, alpha 1.0, hue 0.3808874, saturation 0.8886034, and chroma 0.14956398.
     * It can be represented as a packed float with the constant {@code -0x1.18e2bcp126F}.
     * <pre>
     * <font style='background-color: #336D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #336D00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #336D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #336D00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #336D00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #336D00'>&nbsp;@&nbsp;</font><font style='background-color: #336D00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #336D00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #336D00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_OLIVE_GREEN = -0x1.18e2bcp126F;
    static { NAMED.put("Strong Olive Green", -0x1.18e2bcp126F); LIST.add(-0x1.18e2bcp126F); }

    /**
     * This color constant "Moderate Olive Green" has RGBA8888 code {@code 57721AFF}, L 0.4117647, A 0.4627451, B 0.54901963, alpha 1.0, hue 0.34638813, saturation 0.8321783, and chroma 0.12265874.
     * It can be represented as a packed float with the constant {@code -0x1.18ecd2p126F}.
     * <pre>
     * <font style='background-color: #57721A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57721A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57721A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57721A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57721A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57721A'>&nbsp;@&nbsp;</font><font style='background-color: #57721A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57721A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57721A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_OLIVE_GREEN = -0x1.18ecd2p126F;
    static { NAMED.put("Moderate Olive Green", -0x1.18ecd2p126F); LIST.add(-0x1.18ecd2p126F); }

    /**
     * This color constant "Dark Olive Green" has RGBA8888 code {@code 294705FF}, L 0.23921569, A 0.4627451, B 0.5372549, alpha 1.0, hue 0.36663744, saturation 0.8969082, and chroma 0.104961164.
     * It can be represented as a packed float with the constant {@code -0x1.12ec7ap126F}.
     * <pre>
     * <font style='background-color: #294705;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #294705; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #294705;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #294705'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #294705'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #294705'>&nbsp;@&nbsp;</font><font style='background-color: #294705; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #294705;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #294705; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_OLIVE_GREEN = -0x1.12ec7ap126F;
    static { NAMED.put("Dark Olive Green", -0x1.12ec7ap126F); LIST.add(-0x1.12ec7ap126F); }

    /**
     * This color constant "Grayish Olive Green" has RGBA8888 code {@code 627052FF}, L 0.42745098, A 0.48235294, B 0.5176471, alpha 1.0, hue 0.35739973, saturation 0.31234753, and chroma 0.049718447.
     * It can be represented as a packed float with the constant {@code -0x1.08f6dap126F}.
     * <pre>
     * <font style='background-color: #627052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #627052; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #627052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #627052'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #627052'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #627052'>&nbsp;@&nbsp;</font><font style='background-color: #627052; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #627052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #627052; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_OLIVE_GREEN = -0x1.08f6dap126F;
    static { NAMED.put("Grayish Olive Green", -0x1.08f6dap126F); LIST.add(-0x1.08f6dap126F); }

    /**
     * This color constant "Dark Grayish Olive Green" has RGBA8888 code {@code 3E452CFF}, L 0.25490198, A 0.4862745, B 0.5176471, alpha 1.0, hue 0.33601886, saturation 0.43192235, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.08f882p126F}.
     * <pre>
     * <font style='background-color: #3E452C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E452C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E452C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3E452C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3E452C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3E452C'>&nbsp;@&nbsp;</font><font style='background-color: #3E452C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3E452C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3E452C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_OLIVE_GREEN = -0x1.08f882p126F;
    static { NAMED.put("Dark Grayish Olive Green", -0x1.08f882p126F); LIST.add(-0x1.08f882p126F); }

    /**
     * This color constant "Vivid Yellowish Green" has RGBA8888 code {@code 20ED30FF}, L 0.7529412, A 0.39215687, B 0.5764706, alpha 1.0, hue 0.39851815, saturation 0.9464956, and chroma 0.26337513.
     * It can be represented as a packed float with the constant {@code -0x1.26c98p126F}.
     * <pre>
     * <font style='background-color: #20ED30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20ED30; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20ED30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #20ED30'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #20ED30'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #20ED30'>&nbsp;@&nbsp;</font><font style='background-color: #20ED30; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20ED30;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20ED30; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_YELLOWISH_GREEN = -0x1.26c98p126F;
    static { NAMED.put("Vivid Yellowish Green", -0x1.26c98p126F); LIST.add(-0x1.26c98p126F); }

    /**
     * This color constant "Brilliant Yellowish Green" has RGBA8888 code {@code 80ED83FF}, L 0.8, A 0.43137255, B 0.54509807, alpha 1.0, hue 0.4021605, saturation 0.5548974, and chroma 0.16359681.
     * It can be represented as a packed float with the constant {@code -0x1.16dd98p126F}.
     * <pre>
     * <font style='background-color: #80ED83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80ED83; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80ED83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #80ED83'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #80ED83'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #80ED83'>&nbsp;@&nbsp;</font><font style='background-color: #80ED83; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #80ED83;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #80ED83; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_YELLOWISH_GREEN = -0x1.16dd98p126F;
    static { NAMED.put("Brilliant Yellowish Green", -0x1.16dd98p126F); LIST.add(-0x1.16dd98p126F); }

    /**
     * This color constant "Strong Yellowish Green" has RGBA8888 code {@code 20B14DFF}, L 0.5803922, A 0.41960785, B 0.54509807, alpha 1.0, hue 0.41398114, saturation 0.8970695, and chroma 0.1836353.
     * It can be represented as a packed float with the constant {@code -0x1.16d728p126F}.
     * <pre>
     * <font style='background-color: #20B14D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20B14D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20B14D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #20B14D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #20B14D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #20B14D'>&nbsp;@&nbsp;</font><font style='background-color: #20B14D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #20B14D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #20B14D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_YELLOWISH_GREEN = -0x1.16d728p126F;
    static { NAMED.put("Strong Yellowish Green", -0x1.16d728p126F); LIST.add(-0x1.16d728p126F); }

    /**
     * This color constant "Deep Yellowish Green" has RGBA8888 code {@code 1A8313FF}, L 0.42745098, A 0.42745098, B 0.5529412, alpha 1.0, hue 0.39477962, saturation 0.93075544, and chroma 0.1789216.
     * It can be represented as a packed float with the constant {@code -0x1.1adadap126F}.
     * <pre>
     * <font style='background-color: #1A8313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A8313; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A8313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1A8313'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1A8313'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1A8313'>&nbsp;@&nbsp;</font><font style='background-color: #1A8313; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1A8313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1A8313; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_YELLOWISH_GREEN = -0x1.1adadap126F;
    static { NAMED.put("Deep Yellowish Green", -0x1.1adadap126F); LIST.add(-0x1.1adadap126F); }

    /**
     * This color constant "Very Deep Yellowish Green" has RGBA8888 code {@code 005807FF}, L 0.2784314, A 0.4392157, B 0.5411765, alpha 1.0, hue 0.399282, saturation 1.0333931, and chroma 0.14626285.
     * It can be represented as a packed float with the constant {@code -0x1.14e08ep126F}.
     * <pre>
     * <font style='background-color: #005807;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #005807; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #005807;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #005807'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #005807'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #005807'>&nbsp;@&nbsp;</font><font style='background-color: #005807; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #005807;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #005807; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_YELLOWISH_GREEN = -0x1.14e08ep126F;
    static { NAMED.put("Very Deep Yellowish Green", -0x1.14e08ep126F); LIST.add(-0x1.14e08ep126F); }

    /**
     * This color constant "Very Light Yellowish Green" has RGBA8888 code {@code D1F5CAFF}, L 0.8901961, A 0.4745098, B 0.5176471, alpha 1.0, hue 0.38941902, saturation 0.45942646, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.08f3c6p126F}.
     * <pre>
     * <font style='background-color: #D1F5CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1F5CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1F5CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1F5CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1F5CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1F5CA'>&nbsp;@&nbsp;</font><font style='background-color: #D1F5CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1F5CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1F5CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_YELLOWISH_GREEN = -0x1.08f3c6p126F;
    static { NAMED.put("Very Light Yellowish Green", -0x1.08f3c6p126F); LIST.add(-0x1.08f3c6p126F); }

    /**
     * This color constant "Light Yellowish Green" has RGBA8888 code {@code 99DC98FF}, L 0.7764706, A 0.45490196, B 0.5294118, alpha 1.0, hue 0.39991105, saturation 0.37264305, and chroma 0.107261956.
     * It can be represented as a packed float with the constant {@code -0x1.0ee98cp126F}.
     * <pre>
     * <font style='background-color: #99DC98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99DC98; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99DC98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #99DC98'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #99DC98'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #99DC98'>&nbsp;@&nbsp;</font><font style='background-color: #99DC98; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #99DC98;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #99DC98; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_YELLOWISH_GREEN = -0x1.0ee98cp126F;
    static { NAMED.put("Light Yellowish Green", -0x1.0ee98cp126F); LIST.add(-0x1.0ee98cp126F); }

    /**
     * This color constant "Moderate Yellowish Green" has RGBA8888 code {@code 61AA70FF}, L 0.59607846, A 0.4509804, B 0.5254902, alpha 1.0, hue 0.41594726, saturation 0.5448017, and chroma 0.11007033.
     * It can be represented as a packed float with the constant {@code -0x1.0ce73p126F}.
     * <pre>
     * <font style='background-color: #61AA70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61AA70; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61AA70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61AA70'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61AA70'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61AA70'>&nbsp;@&nbsp;</font><font style='background-color: #61AA70; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61AA70;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61AA70; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_YELLOWISH_GREEN = -0x1.0ce73p126F;
    static { NAMED.put("Moderate Yellowish Green", -0x1.0ce73p126F); LIST.add(-0x1.0ce73p126F); }

    /**
     * This color constant "Dark Yellowish Green" has RGBA8888 code {@code 327643FF}, L 0.40392157, A 0.4509804, B 0.5254902, alpha 1.0, hue 0.41594726, saturation 0.712433, and chroma 0.11007033.
     * It can be represented as a packed float with the constant {@code -0x1.0ce6cep126F}.
     * <pre>
     * <font style='background-color: #327643;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #327643; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #327643;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #327643'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #327643'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #327643'>&nbsp;@&nbsp;</font><font style='background-color: #327643; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #327643;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #327643; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_YELLOWISH_GREEN = -0x1.0ce6cep126F;
    static { NAMED.put("Dark Yellowish Green", -0x1.0ce6cep126F); LIST.add(-0x1.0ce6cep126F); }

    /**
     * This color constant "Very Dark Yellowish Green" has RGBA8888 code {@code 124A1FFF}, L 0.23921569, A 0.45490196, B 0.5254902, alpha 1.0, hue 0.40979147, saturation 0.869227, and chroma 0.10320191.
     * It can be represented as a packed float with the constant {@code -0x1.0ce87ap126F}.
     * <pre>
     * <font style='background-color: #124A1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #124A1F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #124A1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #124A1F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #124A1F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #124A1F'>&nbsp;@&nbsp;</font><font style='background-color: #124A1F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #124A1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #124A1F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_YELLOWISH_GREEN = -0x1.0ce87ap126F;
    static { NAMED.put("Very Dark Yellowish Green", -0x1.0ce87ap126F); LIST.add(-0x1.0ce87ap126F); }

    /**
     * This color constant "Vivid Green" has RGBA8888 code {@code 00FCAAFF}, L 0.8156863, A 0.40784314, B 0.5254902, alpha 1.0, hue 0.4529897, saturation 0.942809, and chroma 0.19048727.
     * It can be represented as a packed float with the constant {@code -0x1.0cd1ap126F}.
     * <pre>
     * <font style='background-color: #00FCAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FCAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FCAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FCAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FCAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FCAA'>&nbsp;@&nbsp;</font><font style='background-color: #00FCAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FCAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FCAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_GREEN = -0x1.0cd1ap126F;
    static { NAMED.put("Vivid Green", -0x1.0cd1ap126F); LIST.add(-0x1.0cd1ap126F); }

    /**
     * This color constant "Brilliant Green" has RGBA8888 code {@code 32E4B3FF}, L 0.75686276, A 0.42352942, B 0.50980395, alpha 1.0, hue 0.47506347, saturation 0.894669, and chroma 0.15359065.
     * It can be represented as a packed float with the constant {@code -0x1.04d982p126F}.
     * <pre>
     * <font style='background-color: #32E4B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32E4B3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32E4B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #32E4B3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #32E4B3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #32E4B3'>&nbsp;@&nbsp;</font><font style='background-color: #32E4B3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #32E4B3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #32E4B3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREEN = -0x1.04d982p126F;
    static { NAMED.put("Brilliant Green", -0x1.04d982p126F); LIST.add(-0x1.04d982p126F); }

    /**
     * This color constant "Strong Green" has RGBA8888 code {@code 00A375FF}, L 0.54509807, A 0.43137255, B 0.5137255, alpha 1.0, hue 0.46322072, saturation 0.97023606, and chroma 0.13942632.
     * It can be represented as a packed float with the constant {@code -0x1.06dd16p126F}.
     * <pre>
     * <font style='background-color: #00A375;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00A375; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00A375;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00A375'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00A375'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00A375'>&nbsp;@&nbsp;</font><font style='background-color: #00A375; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00A375;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00A375; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREEN = -0x1.06dd16p126F;
    static { NAMED.put("Strong Green", -0x1.06dd16p126F); LIST.add(-0x1.06dd16p126F); }

    /**
     * This color constant "Very Light Green" has RGBA8888 code {@code AAEFD2FF}, L 0.8509804, A 0.4627451, B 0.5058824, alpha 1.0, hue 0.4651951, saturation 0.46097723, and chroma 0.07513822.
     * It can be represented as a packed float with the constant {@code -0x1.02edb2p126F}.
     * <pre>
     * <font style='background-color: #AAEFD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAEFD2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAEFD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AAEFD2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AAEFD2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AAEFD2'>&nbsp;@&nbsp;</font><font style='background-color: #AAEFD2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AAEFD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AAEFD2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_GREEN = -0x1.02edb2p126F;
    static { NAMED.put("Very Light Green", -0x1.02edb2p126F); LIST.add(-0x1.02edb2p126F); }

    /**
     * This color constant "Light Green" has RGBA8888 code {@code 6BC49FFF}, L 0.6901961, A 0.4509804, B 0.50980395, alpha 1.0, hue 0.46101317, saturation 0.5753171, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.04e76p126F}.
     * <pre>
     * <font style='background-color: #6BC49F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6BC49F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6BC49F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6BC49F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6BC49F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6BC49F'>&nbsp;@&nbsp;</font><font style='background-color: #6BC49F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6BC49F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6BC49F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREEN = -0x1.04e76p126F;
    static { NAMED.put("Light Green", -0x1.04e76p126F); LIST.add(-0x1.04e76p126F); }

    /**
     * This color constant "Moderate Green" has RGBA8888 code {@code 31906EFF}, L 0.49411765, A 0.44705883, B 0.50980395, alpha 1.0, hue 0.4639029, saturation 0.7848038, and chroma 0.107261956.
     * It can be represented as a packed float with the constant {@code -0x1.04e4fcp126F}.
     * <pre>
     * <font style='background-color: #31906E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31906E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31906E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #31906E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #31906E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #31906E'>&nbsp;@&nbsp;</font><font style='background-color: #31906E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #31906E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #31906E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREEN = -0x1.04e4fcp126F;
    static { NAMED.put("Moderate Green", -0x1.04e4fcp126F); LIST.add(-0x1.04e4fcp126F); }

    /**
     * This color constant "Dark Green" has RGBA8888 code {@code 0D6649FF}, L 0.34117648, A 0.4509804, B 0.50980395, alpha 1.0, hue 0.46101317, saturation 0.9162457, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.04e6aep126F}.
     * <pre>
     * <font style='background-color: #0D6649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D6649; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D6649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0D6649'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0D6649'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0D6649'>&nbsp;@&nbsp;</font><font style='background-color: #0D6649; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D6649;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D6649; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREEN = -0x1.04e6aep126F;
    static { NAMED.put("Dark Green", -0x1.04e6aep126F); LIST.add(-0x1.04e6aep126F); }

    /**
     * This color constant "Very Dark Green" has RGBA8888 code {@code 0F4034FF}, L 0.20784314, A 0.46666667, B 0.5019608, alpha 1.0, hue 0.48019654, saturation 0.84865874, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.00ee6ap126F}.
     * <pre>
     * <font style='background-color: #0F4034;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F4034; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F4034;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F4034'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F4034'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F4034'>&nbsp;@&nbsp;</font><font style='background-color: #0F4034; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F4034;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F4034; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_GREEN = -0x1.00ee6ap126F;
    static { NAMED.put("Very Dark Green", -0x1.00ee6ap126F); LIST.add(-0x1.00ee6ap126F); }

    /**
     * This color constant "Very Pale Green" has RGBA8888 code {@code CEE6E0FF}, L 0.8627451, A 0.4862745, B 0.49803922, alpha 1.0, hue 0.49998704, saturation 0.17142858, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.fef9b8p125F}.
     * <pre>
     * <font style='background-color: #CEE6E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEE6E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEE6E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CEE6E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CEE6E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CEE6E0'>&nbsp;@&nbsp;</font><font style='background-color: #CEE6E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CEE6E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CEE6E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_GREEN = -0x1.fef9b8p125F;
    static { NAMED.put("Very Pale Green", -0x1.fef9b8p125F); LIST.add(-0x1.fef9b8p125F); }

    /**
     * This color constant "Pale Green" has RGBA8888 code {@code 9CBAB3FF}, L 0.7019608, A 0.48235294, B 0.49803922, alpha 1.0, hue 0.49998704, saturation 0.21052632, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.fef766p125F}.
     * <pre>
     * <font style='background-color: #9CBAB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CBAB3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CBAB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9CBAB3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9CBAB3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9CBAB3'>&nbsp;@&nbsp;</font><font style='background-color: #9CBAB3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CBAB3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CBAB3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_GREEN = -0x1.fef766p125F;
    static { NAMED.put("Pale Green", -0x1.fef766p125F); LIST.add(-0x1.fef766p125F); }

    /**
     * This color constant "Grayish Green" has RGBA8888 code {@code 688A82FF}, L 0.5176471, A 0.47843137, B 0.49803922, alpha 1.0, hue 0.49998704, saturation 0.32258064, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.fef508p125F}.
     * <pre>
     * <font style='background-color: #688A82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #688A82; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #688A82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #688A82'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #688A82'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #688A82'>&nbsp;@&nbsp;</font><font style='background-color: #688A82; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #688A82;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #688A82; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_GREEN = -0x1.fef508p125F;
    static { NAMED.put("Grayish Green", -0x1.fef508p125F); LIST.add(-0x1.fef508p125F); }

    /**
     * This color constant "Dark Grayish Green" has RGBA8888 code {@code 445C57FF}, L 0.34509805, A 0.48235294, B 0.49803922, alpha 1.0, hue 0.49998704, saturation 0.33333334, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.fef6bp125F}.
     * <pre>
     * <font style='background-color: #445C57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #445C57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #445C57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #445C57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #445C57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #445C57'>&nbsp;@&nbsp;</font><font style='background-color: #445C57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #445C57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #445C57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_GREEN = -0x1.fef6bp125F;
    static { NAMED.put("Dark Grayish Green", -0x1.fef6bp125F); LIST.add(-0x1.fef6bp125F); }

    /**
     * This color constant "Blackish Green" has RGBA8888 code {@code 273A3FFF}, L 0.20784314, A 0.4862745, B 0.49019608, alpha 1.0, hue 0.59359556, saturation 0.4006168, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.faf86ap125F}.
     * <pre>
     * <font style='background-color: #273A3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #273A3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #273A3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #273A3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #273A3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #273A3F'>&nbsp;@&nbsp;</font><font style='background-color: #273A3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #273A3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #273A3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_GREEN = -0x1.faf86ap125F;
    static { NAMED.put("Blackish Green", -0x1.faf86ap125F); LIST.add(-0x1.faf86ap125F); }

    /**
     * This color constant "Greenish White" has RGBA8888 code {@code E9EBF1FF}, L 0.9019608, A 0.49803922, B 0.49411765, alpha 1.0, hue 0.75, saturation 0.25, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fcffccp125F}.
     * <pre>
     * <font style='background-color: #E9EBF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9EBF1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9EBF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E9EBF1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E9EBF1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E9EBF1'>&nbsp;@&nbsp;</font><font style='background-color: #E9EBF1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E9EBF1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E9EBF1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_WHITE = -0x1.fcffccp125F;
    static { NAMED.put("Greenish White", -0x1.fcffccp125F); LIST.add(-0x1.fcffccp125F); }

    /**
     * This color constant "Light Greenish Gray" has RGBA8888 code {@code CACCD2FF}, L 0.79607844, A 0.49803922, B 0.49411765, alpha 1.0, hue 0.75, saturation 0.10526316, and chroma 0.012352647.
     * It can be represented as a packed float with the constant {@code -0x1.fcff96p125F}.
     * <pre>
     * <font style='background-color: #CACCD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CACCD2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CACCD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CACCD2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CACCD2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CACCD2'>&nbsp;@&nbsp;</font><font style='background-color: #CACCD2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CACCD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CACCD2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_GRAY = -0x1.fcff96p125F;
    static { NAMED.put("Light Greenish Gray", -0x1.fcff96p125F); LIST.add(-0x1.fcff96p125F); }

    /**
     * This color constant "Greenish Gray" has RGBA8888 code {@code 8F9D9AFF}, L 0.6117647, A 0.49019608, B 0.49803922, alpha 1.0, hue 0.49998704, saturation 0.114285715, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.fefb38p125F}.
     * <pre>
     * <font style='background-color: #8F9D9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F9D9A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F9D9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F9D9A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F9D9A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F9D9A'>&nbsp;@&nbsp;</font><font style='background-color: #8F9D9A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F9D9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F9D9A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_GRAY = -0x1.fefb38p125F;
    static { NAMED.put("Greenish Gray", -0x1.fefb38p125F); LIST.add(-0x1.fefb38p125F); }

    /**
     * This color constant "Dark Greenish Gray" has RGBA8888 code {@code 626F6CFF}, L 0.43137255, A 0.49019608, B 0.49803922, alpha 1.0, hue 0.49998704, saturation 0.14285715, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.fefadcp125F}.
     * <pre>
     * <font style='background-color: #626F6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626F6C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626F6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #626F6C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #626F6C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #626F6C'>&nbsp;@&nbsp;</font><font style='background-color: #626F6C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626F6C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626F6C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_GRAY = -0x1.fefadcp125F;
    static { NAMED.put("Dark Greenish Gray", -0x1.fefadcp125F); LIST.add(-0x1.fefadcp125F); }

    /**
     * This color constant "Greenish Black" has RGBA8888 code {@code 2C3634FF}, L 0.19607843, A 0.49019608, B 0.49803922, alpha 1.0, hue 0.49998704, saturation 0.23529412, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.fefa64p125F}.
     * <pre>
     * <font style='background-color: #2C3634;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2C3634; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2C3634;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2C3634'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2C3634'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2C3634'>&nbsp;@&nbsp;</font><font style='background-color: #2C3634; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2C3634;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2C3634; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREENISH_BLACK = -0x1.fefa64p125F;
    static { NAMED.put("Greenish Black", -0x1.fefa64p125F); LIST.add(-0x1.fefa64p125F); }

    /**
     * This color constant "Vivid Bluish Green" has RGBA8888 code {@code 65FFDEFF}, L 0.85882354, A 0.43137255, B 0.5019608, alpha 1.0, hue 0.49065647, saturation 0.94607705, and chroma 0.13677454.
     * It can be represented as a packed float with the constant {@code -0x1.00ddb6p126F}.
     * <pre>
     * <font style='background-color: #65FFDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65FFDE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65FFDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #65FFDE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #65FFDE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #65FFDE'>&nbsp;@&nbsp;</font><font style='background-color: #65FFDE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #65FFDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #65FFDE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_BLUISH_GREEN = -0x1.00ddb6p126F;
    static { NAMED.put("Vivid Bluish Green", -0x1.00ddb6p126F); LIST.add(-0x1.00ddb6p126F); }

    /**
     * This color constant "Brilliant Bluish Green" has RGBA8888 code {@code 1EE9DEFF}, L 0.78039217, A 0.42745098, B 0.4862745, alpha 1.0, hue 0.5262961, saturation 0.9358096, and chroma 0.14709508.
     * It can be represented as a packed float with the constant {@code -0x1.f8db8ep125F}.
     * <pre>
     * <font style='background-color: #1EE9DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1EE9DE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1EE9DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1EE9DE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1EE9DE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1EE9DE'>&nbsp;@&nbsp;</font><font style='background-color: #1EE9DE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1EE9DE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1EE9DE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_BLUISH_GREEN = -0x1.f8db8ep125F;
    static { NAMED.put("Brilliant Bluish Green", -0x1.f8db8ep125F); LIST.add(-0x1.f8db8ep125F); }

    /**
     * This color constant "Strong Bluish Green" has RGBA8888 code {@code 00A796FF}, L 0.5686275, A 0.4392157, B 0.49411765, alpha 1.0, hue 0.5105898, saturation 0.93958104, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.fce122p125F}.
     * <pre>
     * <font style='background-color: #00A796;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00A796; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00A796;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00A796'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00A796'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00A796'>&nbsp;@&nbsp;</font><font style='background-color: #00A796; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00A796;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00A796; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BLUISH_GREEN = -0x1.fce122p125F;
    static { NAMED.put("Strong Bluish Green", -0x1.fce122p125F); LIST.add(-0x1.fce122p125F); }

    /**
     * This color constant "Very Light Bluish Green" has RGBA8888 code {@code 8BF2F1FF}, L 0.8509804, A 0.45490196, B 0.4862745, alpha 1.0, hue 0.54236877, saturation 0.55618316, and chroma 0.09391262.
     * It can be represented as a packed float with the constant {@code -0x1.f8e9b2p125F}.
     * <pre>
     * <font style='background-color: #8BF2F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BF2F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BF2F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8BF2F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8BF2F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8BF2F1'>&nbsp;@&nbsp;</font><font style='background-color: #8BF2F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8BF2F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8BF2F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_BLUISH_GREEN = -0x1.f8e9b2p125F;
    static { NAMED.put("Very Light Bluish Green", -0x1.f8e9b2p125F); LIST.add(-0x1.f8e9b2p125F); }

    /**
     * This color constant "Light Bluish Green" has RGBA8888 code {@code 5CC0C6FF}, L 0.68235296, A 0.45490196, B 0.48235294, alpha 1.0, hue 0.55549574, saturation 0.66884, and chroma 0.096477255.
     * It can be represented as a packed float with the constant {@code -0x1.f6e95cp125F}.
     * <pre>
     * <font style='background-color: #5CC0C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CC0C6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CC0C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5CC0C6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5CC0C6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5CC0C6'>&nbsp;@&nbsp;</font><font style='background-color: #5CC0C6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5CC0C6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5CC0C6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUISH_GREEN = -0x1.f6e95cp125F;
    static { NAMED.put("Light Bluish Green", -0x1.f6e95cp125F); LIST.add(-0x1.f6e95cp125F); }

    /**
     * This color constant "Moderate Bluish Green" has RGBA8888 code {@code 209296FF}, L 0.50980395, A 0.4509804, B 0.48235294, alpha 1.0, hue 0.5511957, saturation 0.8723525, and chroma 0.10379164.
     * It can be represented as a packed float with the constant {@code -0x1.f6e704p125F}.
     * <pre>
     * <font style='background-color: #209296;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #209296; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #209296;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #209296'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #209296'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #209296'>&nbsp;@&nbsp;</font><font style='background-color: #209296; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #209296;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #209296; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BLUISH_GREEN = -0x1.f6e704p125F;
    static { NAMED.put("Moderate Bluish Green", -0x1.f6e704p125F); LIST.add(-0x1.f6e704p125F); }

    /**
     * This color constant "Dark Bluish Green" has RGBA8888 code {@code 176063FF}, L 0.33333334, A 0.4627451, B 0.4862745, alpha 1.0, hue 0.5511957, saturation 0.86243933, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f8ecaap125F}.
     * <pre>
     * <font style='background-color: #176063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #176063; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #176063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #176063'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #176063'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #176063'>&nbsp;@&nbsp;</font><font style='background-color: #176063; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #176063;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #176063; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUISH_GREEN = -0x1.f8ecaap125F;
    static { NAMED.put("Dark Bluish Green", -0x1.f8ecaap125F); LIST.add(-0x1.f8ecaap125F); }

    /**
     * This color constant "Very Dark Bluish Green" has RGBA8888 code {@code 004043FF}, L 0.21568628, A 0.47058824, B 0.4862745, alpha 1.0, hue 0.5644313, saturation 0.8959733, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.f8f06ep125F}.
     * <pre>
     * <font style='background-color: #004043;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #004043; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #004043;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #004043'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #004043'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #004043'>&nbsp;@&nbsp;</font><font style='background-color: #004043; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #004043;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #004043; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_BLUISH_GREEN = -0x1.f8f06ep125F;
    static { NAMED.put("Very Dark Bluish Green", -0x1.f8f06ep125F); LIST.add(-0x1.f8f06ep125F); }

    /**
     * This color constant "Brilliant Greenish Blue" has RGBA8888 code {@code 00D1FCFF}, L 0.7294118, A 0.4392157, B 0.45490196, alpha 1.0, hue 0.600718, saturation 0.95390135, and chroma 0.15078327.
     * It can be represented as a packed float with the constant {@code -0x1.e8e174p125F}.
     * <pre>
     * <font style='background-color: #00D1FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00D1FC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00D1FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00D1FC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00D1FC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00D1FC'>&nbsp;@&nbsp;</font><font style='background-color: #00D1FC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00D1FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00D1FC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_GREENISH_BLUE = -0x1.e8e174p125F;
    static { NAMED.put("Brilliant Greenish Blue", -0x1.e8e174p125F); LIST.add(-0x1.e8e174p125F); }

    /**
     * This color constant "Strong Greenish Blue" has RGBA8888 code {@code 009BD2FF}, L 0.56078434, A 0.45490196, B 0.44705883, alpha 1.0, hue 0.638223, saturation 0.9205074, and chroma 0.13854803.
     * It can be represented as a packed float with the constant {@code -0x1.e4e91ep125F}.
     * <pre>
     * <font style='background-color: #009BD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #009BD2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #009BD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #009BD2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #009BD2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #009BD2'>&nbsp;@&nbsp;</font><font style='background-color: #009BD2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #009BD2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #009BD2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_GREENISH_BLUE = -0x1.e4e91ep125F;
    static { NAMED.put("Strong Greenish Blue", -0x1.e4e91ep125F); LIST.add(-0x1.e4e91ep125F); }

    /**
     * This color constant "Very Light Greenish Blue" has RGBA8888 code {@code 9CE3FCFF}, L 0.827451, A 0.47058824, B 0.4745098, alpha 1.0, hue 0.6127901, saturation 0.8016995, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.f2f1a6p125F}.
     * <pre>
     * <font style='background-color: #9CE3FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CE3FC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CE3FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9CE3FC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9CE3FC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9CE3FC'>&nbsp;@&nbsp;</font><font style='background-color: #9CE3FC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9CE3FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9CE3FC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_GREENISH_BLUE = -0x1.f2f1a6p125F;
    static { NAMED.put("Very Light Greenish Blue", -0x1.f2f1a6p125F); LIST.add(-0x1.f2f1a6p125F); }

    /**
     * This color constant "Light Greenish Blue" has RGBA8888 code {@code 5EBDE3FF}, L 0.6901961, A 0.4627451, B 0.4627451, alpha 1.0, hue 0.625, saturation 0.6363961, and chroma 0.104961164.
     * It can be represented as a packed float with the constant {@code -0x1.eced6p125F}.
     * <pre>
     * <font style='background-color: #5EBDE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5EBDE3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5EBDE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5EBDE3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5EBDE3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5EBDE3'>&nbsp;@&nbsp;</font><font style='background-color: #5EBDE3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5EBDE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5EBDE3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GREENISH_BLUE = -0x1.eced6p125F;
    static { NAMED.put("Light Greenish Blue", -0x1.eced6p125F); LIST.add(-0x1.eced6p125F); }

    /**
     * This color constant "Moderate Greenish Blue" has RGBA8888 code {@code 1E8DB4FF}, L 0.50980395, A 0.45882353, B 0.45882353, alpha 1.0, hue 0.625, saturation 0.8570991, and chroma 0.116009705.
     * It can be represented as a packed float with the constant {@code -0x1.eaeb04p125F}.
     * <pre>
     * <font style='background-color: #1E8DB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E8DB4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E8DB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1E8DB4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1E8DB4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1E8DB4'>&nbsp;@&nbsp;</font><font style='background-color: #1E8DB4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E8DB4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E8DB4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_GREENISH_BLUE = -0x1.eaeb04p125F;
    static { NAMED.put("Moderate Greenish Blue", -0x1.eaeb04p125F); LIST.add(-0x1.eaeb04p125F); }

    /**
     * This color constant "Dark Greenish Blue" has RGBA8888 code {@code 005E7FFF}, L 0.34117648, A 0.46666667, B 0.4627451, alpha 1.0, hue 0.63434434, saturation 0.89197, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.eceeaep125F}.
     * <pre>
     * <font style='background-color: #005E7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #005E7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #005E7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #005E7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #005E7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #005E7F'>&nbsp;@&nbsp;</font><font style='background-color: #005E7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #005E7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #005E7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GREENISH_BLUE = -0x1.eceeaep125F;
    static { NAMED.put("Dark Greenish Blue", -0x1.eceeaep125F); LIST.add(-0x1.eceeaep125F); }

    /**
     * This color constant "Very Dark Greenish Blue" has RGBA8888 code {@code 003D53FF}, L 0.21176471, A 0.4745098, B 0.47058824, alpha 1.0, hue 0.6372099, saturation 0.8780519, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.f0f26cp125F}.
     * <pre>
     * <font style='background-color: #003D53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #003D53; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #003D53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #003D53'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #003D53'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #003D53'>&nbsp;@&nbsp;</font><font style='background-color: #003D53; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #003D53;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #003D53; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_GREENISH_BLUE = -0x1.f0f26cp125F;
    static { NAMED.put("Very Dark Greenish Blue", -0x1.f0f26cp125F); LIST.add(-0x1.f0f26cp125F); }

    /**
     * This color constant "Vivid Blue" has RGBA8888 code {@code 3272EAFF}, L 0.47843137, A 0.48235294, B 0.40784314, alpha 1.0, hue 0.72258395, saturation 0.8191311, and chroma 0.18692946.
     * It can be represented as a packed float with the constant {@code -0x1.d0f6f4p125F}.
     * <pre>
     * <font style='background-color: #3272EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3272EA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3272EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3272EA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3272EA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3272EA'>&nbsp;@&nbsp;</font><font style='background-color: #3272EA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3272EA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3272EA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_BLUE = -0x1.d0f6f4p125F;
    static { NAMED.put("Vivid Blue", -0x1.d0f6f4p125F); LIST.add(-0x1.d0f6f4p125F); }

    /**
     * This color constant "Brilliant Blue" has RGBA8888 code {@code 63AFF7FF}, L 0.6666667, A 0.4745098, B 0.44313726, alpha 1.0, hue 0.6855687, saturation 0.846197, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.e2f354p125F}.
     * <pre>
     * <font style='background-color: #63AFF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63AFF7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63AFF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #63AFF7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #63AFF7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #63AFF7'>&nbsp;@&nbsp;</font><font style='background-color: #63AFF7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #63AFF7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #63AFF7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_BLUE = -0x1.e2f354p125F;
    static { NAMED.put("Brilliant Blue", -0x1.e2f354p125F); LIST.add(-0x1.e2f354p125F); }

    /**
     * This color constant "Strong Blue" has RGBA8888 code {@code 2C7FE4FF}, L 0.5058824, A 0.4745098, B 0.41960785, alpha 1.0, hue 0.7036233, saturation 0.773356, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.d6f302p125F}.
     * <pre>
     * <font style='background-color: #2C7FE4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2C7FE4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2C7FE4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2C7FE4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2C7FE4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2C7FE4'>&nbsp;@&nbsp;</font><font style='background-color: #2C7FE4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2C7FE4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2C7FE4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_BLUE = -0x1.d6f302p125F;
    static { NAMED.put("Strong Blue", -0x1.d6f302p125F); LIST.add(-0x1.d6f302p125F); }

    /**
     * This color constant "Deep Blue" has RGBA8888 code {@code 0D38B0FF}, L 0.2784314, A 0.4862745, B 0.4, alpha 1.0, hue 0.73098123, saturation 0.66261464, and chroma 0.20108652.
     * It can be represented as a packed float with the constant {@code -0x1.ccf88ep125F}.
     * <pre>
     * <font style='background-color: #0D38B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D38B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D38B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0D38B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0D38B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0D38B0'>&nbsp;@&nbsp;</font><font style='background-color: #0D38B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0D38B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0D38B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_BLUE = -0x1.ccf88ep125F;
    static { NAMED.put("Deep Blue", -0x1.ccf88ep125F); LIST.add(-0x1.ccf88ep125F); }

    /**
     * This color constant "Very Light Blue" has RGBA8888 code {@code A8D3FFFF}, L 0.8, A 0.4862745, B 0.46666667, alpha 1.0, hue 0.6929125, saturation 0.89936876, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.eef998p125F}.
     * <pre>
     * <font style='background-color: #A8D3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8D3FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8D3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8D3FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8D3FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8D3FF'>&nbsp;@&nbsp;</font><font style='background-color: #A8D3FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8D3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8D3FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_BLUE = -0x1.eef998p125F;
    static { NAMED.put("Very Light Blue", -0x1.eef998p125F); LIST.add(-0x1.eef998p125F); }

    /**
     * This color constant "Light Blue" has RGBA8888 code {@code 84B7EBFF}, L 0.7019608, A 0.48235294, B 0.45882353, alpha 1.0, hue 0.689452, saturation 0.69486, and chroma 0.08924734.
     * It can be represented as a packed float with the constant {@code -0x1.eaf766p125F}.
     * <pre>
     * <font style='background-color: #84B7EB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #84B7EB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #84B7EB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #84B7EB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #84B7EB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #84B7EB'>&nbsp;@&nbsp;</font><font style='background-color: #84B7EB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #84B7EB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #84B7EB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUE = -0x1.eaf766p125F;
    static { NAMED.put("Light Blue", -0x1.eaf766p125F); LIST.add(-0x1.eaf766p125F); }

    /**
     * This color constant "Moderate Blue" has RGBA8888 code {@code 267EC9FF}, L 0.48235294, A 0.47058824, B 0.43529412, alpha 1.0, hue 0.68437123, saturation 0.77618885, and chroma 0.1415982.
     * It can be represented as a packed float with the constant {@code -0x1.def0f6p125F}.
     * <pre>
     * <font style='background-color: #267EC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #267EC9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #267EC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #267EC9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #267EC9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #267EC9'>&nbsp;@&nbsp;</font><font style='background-color: #267EC9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #267EC9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #267EC9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_BLUE = -0x1.def0f6p125F;
    static { NAMED.put("Moderate Blue", -0x1.def0f6p125F); LIST.add(-0x1.def0f6p125F); }

    /**
     * This color constant "Dark Blue" has RGBA8888 code {@code 16457FFF}, L 0.27058825, A 0.48235294, B 0.44313726, alpha 1.0, hue 0.70571566, saturation 0.728011, and chroma 0.11861114.
     * It can be represented as a packed float with the constant {@code -0x1.e2f68ap125F}.
     * <pre>
     * <font style='background-color: #16457F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #16457F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #16457F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #16457F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #16457F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #16457F'>&nbsp;@&nbsp;</font><font style='background-color: #16457F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #16457F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #16457F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUE = -0x1.e2f68ap125F;
    static { NAMED.put("Dark Blue", -0x1.e2f68ap125F); LIST.add(-0x1.e2f68ap125F); }

    /**
     * This color constant "Very Pale Blue" has RGBA8888 code {@code CBDEFBFF}, L 0.8509804, A 0.49411765, B 0.47843137, alpha 1.0, hue 0.71857655, saturation 0.78446454, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.f4fdb2p125F}.
     * <pre>
     * <font style='background-color: #CBDEFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBDEFB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBDEFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBDEFB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBDEFB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBDEFB'>&nbsp;@&nbsp;</font><font style='background-color: #CBDEFB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBDEFB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBDEFB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_BLUE = -0x1.f4fdb2p125F;
    static { NAMED.put("Very Pale Blue", -0x1.f4fdb2p125F); LIST.add(-0x1.f4fdb2p125F); }

    /**
     * This color constant "Pale Blue" has RGBA8888 code {@code 9FB2D3FF}, L 0.69803923, A 0.49411765, B 0.4745098, alpha 1.0, hue 0.7237039, saturation 0.40551752, and chroma 0.052115876.
     * It can be represented as a packed float with the constant {@code -0x1.f2fd64p125F}.
     * <pre>
     * <font style='background-color: #9FB2D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FB2D3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FB2D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9FB2D3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9FB2D3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9FB2D3'>&nbsp;@&nbsp;</font><font style='background-color: #9FB2D3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9FB2D3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9FB2D3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_BLUE = -0x1.f2fd64p125F;
    static { NAMED.put("Pale Blue", -0x1.f2fd64p125F); LIST.add(-0x1.f2fd64p125F); }

    /**
     * This color constant "Grayish Blue" has RGBA8888 code {@code 5A7DA2FF}, L 0.4862745, A 0.4862745, B 0.46666667, alpha 1.0, hue 0.6929125, saturation 0.34873483, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.eef8f8p125F}.
     * <pre>
     * <font style='background-color: #5A7DA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A7DA2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A7DA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A7DA2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A7DA2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A7DA2'>&nbsp;@&nbsp;</font><font style='background-color: #5A7DA2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A7DA2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A7DA2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_BLUE = -0x1.eef8f8p125F;
    static { NAMED.put("Grayish Blue", -0x1.eef8f8p125F); LIST.add(-0x1.eef8f8p125F); }

    /**
     * This color constant "Dark Grayish Blue" has RGBA8888 code {@code 3B516EFF}, L 0.3137255, A 0.49019608, B 0.47058824, alpha 1.0, hue 0.70571566, saturation 0.33860976, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.f0faap125F}.
     * <pre>
     * <font style='background-color: #3B516E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B516E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B516E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B516E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B516E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B516E'>&nbsp;@&nbsp;</font><font style='background-color: #3B516E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B516E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B516E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_BLUE = -0x1.f0faap125F;
    static { NAMED.put("Dark Grayish Blue", -0x1.f0faap125F); LIST.add(-0x1.f0faap125F); }

    /**
     * This color constant "Blackish Blue" has RGBA8888 code {@code 21354EFF}, L 0.19607843, A 0.49019608, B 0.47058824, alpha 1.0, hue 0.70571566, saturation 0.4412188, and chroma 0.061763234.
     * It can be represented as a packed float with the constant {@code -0x1.f0fa64p125F}.
     * <pre>
     * <font style='background-color: #21354E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #21354E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #21354E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #21354E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #21354E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #21354E'>&nbsp;@&nbsp;</font><font style='background-color: #21354E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #21354E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #21354E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_BLUE = -0x1.f0fa64p125F;
    static { NAMED.put("Blackish Blue", -0x1.f0fa64p125F); LIST.add(-0x1.f0fa64p125F); }

    /**
     * This color constant "Bluish White" has RGBA8888 code {@code ECE9F8FF}, L 0.9019608, A 0.5019608, B 0.49019608, alpha 1.0, hue 0.8237903, saturation 0.49690402, and chroma 0.019918045.
     * It can be represented as a packed float with the constant {@code -0x1.fb01ccp125F}.
     * <pre>
     * <font style='background-color: #ECE9F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECE9F8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECE9F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECE9F8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECE9F8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECE9F8'>&nbsp;@&nbsp;</font><font style='background-color: #ECE9F8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECE9F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECE9F8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_WHITE = -0x1.fb01ccp125F;
    static { NAMED.put("Bluish White", -0x1.fb01ccp125F); LIST.add(-0x1.fb01ccp125F); }

    /**
     * This color constant "Light Bluish Gray" has RGBA8888 code {@code C6C5D9FF}, L 0.78039217, A 0.5019608, B 0.4862745, alpha 1.0, hue 0.8011957, saturation 0.2874798, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.f9018ep125F}.
     * <pre>
     * <font style='background-color: #C6C5D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6C5D9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6C5D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C6C5D9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C6C5D9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C6C5D9'>&nbsp;@&nbsp;</font><font style='background-color: #C6C5D9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C6C5D9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C6C5D9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_BLUISH_GRAY = -0x1.f9018ep125F;
    static { NAMED.put("Light Bluish Gray", -0x1.f9018ep125F); LIST.add(-0x1.f9018ep125F); }

    /**
     * This color constant "Bluish Gray" has RGBA8888 code {@code 9499AAFF}, L 0.6117647, A 0.49803922, B 0.4862745, alpha 1.0, hue 0.75, saturation 0.15, and chroma 0.027621359.
     * It can be represented as a packed float with the constant {@code -0x1.f8ff38p125F}.
     * <pre>
     * <font style='background-color: #9499AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9499AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9499AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9499AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9499AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9499AA'>&nbsp;@&nbsp;</font><font style='background-color: #9499AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9499AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9499AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_GRAY = -0x1.f8ff38p125F;
    static { NAMED.put("Bluish Gray", -0x1.f8ff38p125F); LIST.add(-0x1.f8ff38p125F); }

    /**
     * This color constant "Dark Bluish Gray" has RGBA8888 code {@code 61687BFF}, L 0.41960785, A 0.49803922, B 0.48235294, alpha 1.0, hue 0.75, saturation 0.121212125, and chroma 0.0353726.
     * It can be represented as a packed float with the constant {@code -0x1.f6fed6p125F}.
     * <pre>
     * <font style='background-color: #61687B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61687B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61687B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #61687B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #61687B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #61687B'>&nbsp;@&nbsp;</font><font style='background-color: #61687B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #61687B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #61687B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_BLUISH_GRAY = -0x1.f6fed6p125F;
    static { NAMED.put("Dark Bluish Gray", -0x1.f6fed6p125F); LIST.add(-0x1.f6fed6p125F); }

    /**
     * This color constant "Bluish Black" has RGBA8888 code {@code 2F364AFF}, L 0.20784314, A 0.49803922, B 0.47843137, alpha 1.0, hue 0.75, saturation 0.16666667, and chroma 0.04314594.
     * It can be represented as a packed float with the constant {@code -0x1.f4fe6ap125F}.
     * <pre>
     * <font style='background-color: #2F364A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F364A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F364A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F364A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F364A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F364A'>&nbsp;@&nbsp;</font><font style='background-color: #2F364A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F364A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F364A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUISH_BLACK = -0x1.f4fe6ap125F;
    static { NAMED.put("Bluish Black", -0x1.f4fe6ap125F); LIST.add(-0x1.f4fe6ap125F); }

    /**
     * This color constant "Vivid Purplish Blue" has RGBA8888 code {@code 534DEDFF}, L 0.41960785, A 0.5137255, B 0.3882353, alpha 1.0, hue 0.77259654, saturation 0.8443066, and chroma 0.22432896.
     * It can be represented as a packed float with the constant {@code -0x1.c706d6p125F}.
     * <pre>
     * <font style='background-color: #534DED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #534DED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #534DED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #534DED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #534DED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #534DED'>&nbsp;@&nbsp;</font><font style='background-color: #534DED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #534DED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #534DED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLISH_BLUE = -0x1.c706d6p125F;
    static { NAMED.put("Vivid Purplish Blue", -0x1.c706d6p125F); LIST.add(-0x1.c706d6p125F); }

    /**
     * This color constant "Brilliant Purplish Blue" has RGBA8888 code {@code 909BF5FF}, L 0.64705884, A 0.5058824, B 0.4392157, alpha 1.0, hue 0.77110875, saturation 0.81798625, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.e1034ap125F}.
     * <pre>
     * <font style='background-color: #909BF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #909BF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #909BF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #909BF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #909BF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #909BF5'>&nbsp;@&nbsp;</font><font style='background-color: #909BF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #909BF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #909BF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLISH_BLUE = -0x1.e1034ap125F;
    static { NAMED.put("Brilliant Purplish Blue", -0x1.e1034ap125F); LIST.add(-0x1.e1034ap125F); }

    /**
     * This color constant "Strong Purplish Blue" has RGBA8888 code {@code 626DD0FF}, L 0.47843137, A 0.5058824, B 0.42745098, alpha 1.0, hue 0.7676211, saturation 0.62450933, and chroma 0.14500555.
     * It can be represented as a packed float with the constant {@code -0x1.db02f4p125F}.
     * <pre>
     * <font style='background-color: #626DD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626DD0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626DD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #626DD0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #626DD0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #626DD0'>&nbsp;@&nbsp;</font><font style='background-color: #626DD0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626DD0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626DD0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_BLUE = -0x1.db02f4p125F;
    static { NAMED.put("Strong Purplish Blue", -0x1.db02f4p125F); LIST.add(-0x1.db02f4p125F); }

    /**
     * This color constant "Deep Purplish Blue" has RGBA8888 code {@code 352FA4FF}, L 0.27058825, A 0.50980395, B 0.40784314, alpha 1.0, hue 0.77065516, saturation 0.6923829, and chroma 0.18462972.
     * It can be represented as a packed float with the constant {@code -0x1.d1048ap125F}.
     * <pre>
     * <font style='background-color: #352FA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #352FA4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #352FA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #352FA4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #352FA4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #352FA4'>&nbsp;@&nbsp;</font><font style='background-color: #352FA4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #352FA4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #352FA4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_BLUE = -0x1.d1048ap125F;
    static { NAMED.put("Deep Purplish Blue", -0x1.d1048ap125F); LIST.add(-0x1.d1048ap125F); }

    /**
     * This color constant "Very Light Purplish Blue" has RGBA8888 code {@code C4CDFFFF}, L 0.80784315, A 0.5019608, B 0.46666667, alpha 1.0, hue 0.76980346, saturation 0.89580643, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.ef019cp125F}.
     * <pre>
     * <font style='background-color: #C4CDFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4CDFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4CDFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C4CDFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C4CDFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C4CDFF'>&nbsp;@&nbsp;</font><font style='background-color: #C4CDFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C4CDFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C4CDFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_PURPLISH_BLUE = -0x1.ef019cp125F;
    static { NAMED.put("Very Light Purplish Blue", -0x1.ef019cp125F); LIST.add(-0x1.ef019cp125F); }

    /**
     * This color constant "Light Purplish Blue" has RGBA8888 code {@code 98A4DFFF}, L 0.6627451, A 0.5019608, B 0.45882353, alpha 1.0, hue 0.76586956, saturation 0.5911691, and chroma 0.0821242.
     * It can be represented as a packed float with the constant {@code -0x1.eb0152p125F}.
     * <pre>
     * <font style='background-color: #98A4DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98A4DF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98A4DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98A4DF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98A4DF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98A4DF'>&nbsp;@&nbsp;</font><font style='background-color: #98A4DF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98A4DF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98A4DF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_BLUE = -0x1.eb0152p125F;
    static { NAMED.put("Light Purplish Blue", -0x1.eb0152p125F); LIST.add(-0x1.eb0152p125F); }

    /**
     * This color constant "Moderate Purplish Blue" has RGBA8888 code {@code 5960AFFF}, L 0.41960785, A 0.5058824, B 0.4392157, alpha 1.0, hue 0.77110875, saturation 0.45172375, and chroma 0.121659465.
     * It can be represented as a packed float with the constant {@code -0x1.e102d6p125F}.
     * <pre>
     * <font style='background-color: #5960AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5960AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5960AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5960AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5960AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5960AF'>&nbsp;@&nbsp;</font><font style='background-color: #5960AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5960AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5960AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_BLUE = -0x1.e102d6p125F;
    static { NAMED.put("Moderate Purplish Blue", -0x1.e102d6p125F); LIST.add(-0x1.e102d6p125F); }

    /**
     * This color constant "Dark Purplish Blue" has RGBA8888 code {@code 2F2A73FF}, L 0.20784314, A 0.50980395, B 0.43529412, alpha 1.0, hue 0.7795081, saturation 0.58138645, and chroma 0.1303775.
     * It can be represented as a packed float with the constant {@code -0x1.df046ap125F}.
     * <pre>
     * <font style='background-color: #2F2A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F2A73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F2A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2F2A73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2F2A73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2F2A73'>&nbsp;@&nbsp;</font><font style='background-color: #2F2A73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2F2A73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2F2A73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_BLUE = -0x1.df046ap125F;
    static { NAMED.put("Dark Purplish Blue", -0x1.df046ap125F); LIST.add(-0x1.df046ap125F); }

    /**
     * This color constant "Very Pale Purplish Blue" has RGBA8888 code {@code D1D6FDFF}, L 0.8392157, A 0.5019608, B 0.4745098, alpha 1.0, hue 0.7762961, saturation 0.81103504, and chroma 0.050931267.
     * It can be represented as a packed float with the constant {@code -0x1.f301acp125F}.
     * <pre>
     * <font style='background-color: #D1D6FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1D6FD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1D6FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D1D6FD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D1D6FD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D1D6FD'>&nbsp;@&nbsp;</font><font style='background-color: #D1D6FD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D1D6FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D1D6FD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_PURPLISH_BLUE = -0x1.f301acp125F;
    static { NAMED.put("Very Pale Purplish Blue", -0x1.f301acp125F); LIST.add(-0x1.f301acp125F); }

    /**
     * This color constant "Pale Purplish Blue" has RGBA8888 code {@code 9EA5CEFF}, L 0.6627451, A 0.5019608, B 0.47058824, alpha 1.0, hue 0.77259654, saturation 0.40406102, and chroma 0.058723815.
     * It can be represented as a packed float with the constant {@code -0x1.f10152p125F}.
     * <pre>
     * <font style='background-color: #9EA5CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9EA5CE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9EA5CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9EA5CE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9EA5CE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9EA5CE'>&nbsp;@&nbsp;</font><font style='background-color: #9EA5CE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9EA5CE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9EA5CE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLISH_BLUE = -0x1.f10152p125F;
    static { NAMED.put("Pale Purplish Blue", -0x1.f10152p125F); LIST.add(-0x1.f10152p125F); }

    /**
     * This color constant "Grayish Purplish Blue" has RGBA8888 code {@code 545F9BFF}, L 0.4, A 0.5019608, B 0.4509804, alpha 1.0, hue 0.7632337, saturation 0.34903172, and chroma 0.09773435.
     * It can be represented as a packed float with the constant {@code -0x1.e700ccp125F}.
     * <pre>
     * <font style='background-color: #545F9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #545F9B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #545F9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #545F9B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #545F9B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #545F9B'>&nbsp;@&nbsp;</font><font style='background-color: #545F9B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #545F9B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #545F9B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_BLUE = -0x1.e700ccp125F;
    static { NAMED.put("Grayish Purplish Blue", -0x1.e700ccp125F); LIST.add(-0x1.e700ccp125F); }

    /**
     * This color constant "Vivid Violet" has RGBA8888 code {@code 9240F8FF}, L 0.4745098, A 0.56078434, B 0.39215687, alpha 1.0, hue 0.8351486, saturation 0.88407636, and chroma 0.24662022.
     * It can be represented as a packed float with the constant {@code -0x1.c91ef2p125F}.
     * <pre>
     * <font style='background-color: #9240F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9240F8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9240F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9240F8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9240F8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9240F8'>&nbsp;@&nbsp;</font><font style='background-color: #9240F8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9240F8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9240F8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_VIOLET = -0x1.c91ef2p125F;
    static { NAMED.put("Vivid Violet", -0x1.c91ef2p125F); LIST.add(-0x1.c91ef2p125F); }

    /**
     * This color constant "Brilliant Violet" has RGBA8888 code {@code AB91EEFF}, L 0.64705884, A 0.5254902, B 0.44313726, alpha 1.0, hue 0.8237903, saturation 0.76353544, and chroma 0.124142565.
     * It can be represented as a packed float with the constant {@code -0x1.e30d4ap125F}.
     * <pre>
     * <font style='background-color: #AB91EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB91EE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB91EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB91EE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB91EE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB91EE'>&nbsp;@&nbsp;</font><font style='background-color: #AB91EE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB91EE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB91EE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_VIOLET = -0x1.e30d4ap125F;
    static { NAMED.put("Brilliant Violet", -0x1.e30d4ap125F); LIST.add(-0x1.e30d4ap125F); }

    /**
     * This color constant "Strong Violet" has RGBA8888 code {@code 7C47D0FF}, L 0.42745098, A 0.54509807, B 0.4117647, alpha 1.0, hue 0.82947695, saturation 0.6509072, and chroma 0.1974106.
     * It can be represented as a packed float with the constant {@code -0x1.d316dap125F}.
     * <pre>
     * <font style='background-color: #7C47D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C47D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C47D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C47D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C47D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C47D0'>&nbsp;@&nbsp;</font><font style='background-color: #7C47D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C47D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C47D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_VIOLET = -0x1.d316dap125F;
    static { NAMED.put("Strong Violet", -0x1.d316dap125F); LIST.add(-0x1.d316dap125F); }

    /**
     * This color constant "Deep Violet" has RGBA8888 code {@code 570D97FF}, L 0.25490198, A 0.5529412, B 0.4117647, alpha 1.0, hue 0.84020853, saturation 0.9482476, and chroma 0.2049944.
     * It can be represented as a packed float with the constant {@code -0x1.d31a82p125F}.
     * <pre>
     * <font style='background-color: #570D97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #570D97; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #570D97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #570D97'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #570D97'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #570D97'>&nbsp;@&nbsp;</font><font style='background-color: #570D97; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #570D97;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #570D97; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_VIOLET = -0x1.d31a82p125F;
    static { NAMED.put("Deep Violet", -0x1.d31a82p125F); LIST.add(-0x1.d31a82p125F); }

    /**
     * This color constant "Very Light Violet" has RGBA8888 code {@code D6C8FFFF}, L 0.8117647, A 0.5137255, B 0.46666667, alpha 1.0, hue 0.8237903, saturation 0.8944272, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.ef079ep125F}.
     * <pre>
     * <font style='background-color: #D6C8FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6C8FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6C8FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D6C8FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D6C8FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D6C8FF'>&nbsp;@&nbsp;</font><font style='background-color: #D6C8FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D6C8FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D6C8FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_VIOLET = -0x1.ef079ep125F;
    static { NAMED.put("Very Light Violet", -0x1.ef079ep125F); LIST.add(-0x1.ef079ep125F); }

    /**
     * This color constant "Light Violet" has RGBA8888 code {@code AE9DDFFF}, L 0.67058825, A 0.5176471, B 0.45882353, alpha 1.0, hue 0.8237903, saturation 0.5884389, and chroma 0.08924734.
     * It can be represented as a packed float with the constant {@code -0x1.eb0956p125F}.
     * <pre>
     * <font style='background-color: #AE9DDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE9DDF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE9DDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AE9DDF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AE9DDF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AE9DDF'>&nbsp;@&nbsp;</font><font style='background-color: #AE9DDF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AE9DDF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AE9DDF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_VIOLET = -0x1.eb0956p125F;
    static { NAMED.put("Light Violet", -0x1.eb0956p125F); LIST.add(-0x1.eb0956p125F); }

    /**
     * This color constant "Moderate Violet" has RGBA8888 code {@code 7552B8FF}, L 0.42352942, A 0.53333336, B 0.43137255, alpha 1.0, hue 0.8274942, saturation 0.49962038, and chroma 0.15199278.
     * It can be represented as a packed float with the constant {@code -0x1.dd10d8p125F}.
     * <pre>
     * <font style='background-color: #7552B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7552B8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7552B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7552B8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7552B8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7552B8'>&nbsp;@&nbsp;</font><font style='background-color: #7552B8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7552B8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7552B8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_VIOLET = -0x1.dd10d8p125F;
    static { NAMED.put("Moderate Violet", -0x1.dd10d8p125F); LIST.add(-0x1.dd10d8p125F); }

    /**
     * This color constant "Dark Violet" has RGBA8888 code {@code 482B74FF}, L 0.23921569, A 0.5294118, B 0.44313726, alpha 1.0, hue 0.8326307, saturation 0.59720427, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.e30e7ap125F}.
     * <pre>
     * <font style='background-color: #482B74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #482B74; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #482B74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #482B74'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #482B74'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #482B74'>&nbsp;@&nbsp;</font><font style='background-color: #482B74; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #482B74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #482B74; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_VIOLET = -0x1.e30e7ap125F;
    static { NAMED.put("Dark Violet", -0x1.e30e7ap125F); LIST.add(-0x1.e30e7ap125F); }

    /**
     * This color constant "Very Pale Violet" has RGBA8888 code {@code DED1F6FF}, L 0.8352941, A 0.50980395, B 0.47843137, alpha 1.0, hue 0.83601886, saturation 0.64788353, and chroma 0.0471994.
     * It can be represented as a packed float with the constant {@code -0x1.f505aap125F}.
     * <pre>
     * <font style='background-color: #DED1F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DED1F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DED1F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DED1F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DED1F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DED1F6'>&nbsp;@&nbsp;</font><font style='background-color: #DED1F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DED1F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DED1F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_VIOLET = -0x1.f505aap125F;
    static { NAMED.put("Very Pale Violet", -0x1.f505aap125F); LIST.add(-0x1.f505aap125F); }

    /**
     * This color constant "Pale Violet" has RGBA8888 code {@code AFA0CFFF}, L 0.67058825, A 0.5137255, B 0.47058824, alpha 1.0, hue 0.8326307, saturation 0.4031129, and chroma 0.064659946.
     * It can be represented as a packed float with the constant {@code -0x1.f10756p125F}.
     * <pre>
     * <font style='background-color: #AFA0CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFA0CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFA0CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFA0CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFA0CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFA0CF'>&nbsp;@&nbsp;</font><font style='background-color: #AFA0CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFA0CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFA0CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_VIOLET = -0x1.f10756p125F;
    static { NAMED.put("Pale Violet", -0x1.f10756p125F); LIST.add(-0x1.f10756p125F); }

    /**
     * This color constant "Grayish Violet" has RGBA8888 code {@code 7359A0FF}, L 0.41960785, A 0.5254902, B 0.4509804, alpha 1.0, hue 0.83405274, saturation 0.36559063, and chroma 0.11007033.
     * It can be represented as a packed float with the constant {@code -0x1.e70cd6p125F}.
     * <pre>
     * <font style='background-color: #7359A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7359A0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7359A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7359A0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7359A0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7359A0'>&nbsp;@&nbsp;</font><font style='background-color: #7359A0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7359A0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7359A0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_VIOLET = -0x1.e70cd6p125F;
    static { NAMED.put("Grayish Violet", -0x1.e70cd6p125F); LIST.add(-0x1.e70cd6p125F); }

    /**
     * This color constant "Vivid Purple" has RGBA8888 code {@code DA32FDFF}, L 0.56078434, A 0.60784316, B 0.40784314, alpha 1.0, hue 0.8905432, saturation 0.9058835, and chroma 0.28260309.
     * It can be represented as a packed float with the constant {@code -0x1.d1371ep125F}.
     * <pre>
     * <font style='background-color: #DA32FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA32FD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA32FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA32FD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA32FD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA32FD'>&nbsp;@&nbsp;</font><font style='background-color: #DA32FD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA32FD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA32FD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLE = -0x1.d1371ep125F;
    static { NAMED.put("Vivid Purple", -0x1.d1371ep125F); LIST.add(-0x1.d1371ep125F); }

    /**
     * This color constant "Brilliant Purple" has RGBA8888 code {@code DB9FEFFF}, L 0.7254902, A 0.5411765, B 0.45882353, alpha 1.0, hue 0.88256735, saturation 0.70790803, and chroma 0.116009705.
     * It can be represented as a packed float with the constant {@code -0x1.eb1572p125F}.
     * <pre>
     * <font style='background-color: #DB9FEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB9FEF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB9FEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DB9FEF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DB9FEF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DB9FEF'>&nbsp;@&nbsp;</font><font style='background-color: #DB9FEF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DB9FEF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DB9FEF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLE = -0x1.eb1572p125F;
    static { NAMED.put("Brilliant Purple", -0x1.eb1572p125F); LIST.add(-0x1.eb1572p125F); }

    /**
     * This color constant "Strong Purple" has RGBA8888 code {@code BB51D6FF}, L 0.5294118, A 0.5764706, B 0.43137255, alpha 1.0, hue 0.88786715, saturation 0.64811873, and chroma 0.20469645.
     * It can be represented as a packed float with the constant {@code -0x1.dd270ep125F}.
     * <pre>
     * <font style='background-color: #BB51D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB51D6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB51D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BB51D6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BB51D6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BB51D6'>&nbsp;@&nbsp;</font><font style='background-color: #BB51D6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BB51D6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BB51D6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLE = -0x1.dd270ep125F;
    static { NAMED.put("Strong Purple", -0x1.dd270ep125F); LIST.add(-0x1.dd270ep125F); }

    /**
     * This color constant "Deep Purple" has RGBA8888 code {@code 86209EFF}, L 0.34901962, A 0.5764706, B 0.43137255, alpha 1.0, hue 0.88786715, saturation 0.86061674, and chroma 0.20469645.
     * It can be represented as a packed float with the constant {@code -0x1.dd26b2p125F}.
     * <pre>
     * <font style='background-color: #86209E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86209E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86209E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #86209E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #86209E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #86209E'>&nbsp;@&nbsp;</font><font style='background-color: #86209E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86209E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86209E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE = -0x1.dd26b2p125F;
    static { NAMED.put("Deep Purple", -0x1.dd26b2p125F); LIST.add(-0x1.dd26b2p125F); }

    /**
     * This color constant "Very Deep Purple" has RGBA8888 code {@code 620079FF}, L 0.23921569, A 0.5686275, B 0.43529412, alpha 1.0, hue 0.88434434, saturation 0.9829873, and chroma 0.18790646.
     * It can be represented as a packed float with the constant {@code -0x1.df227ap125F}.
     * <pre>
     * <font style='background-color: #620079;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #620079; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #620079;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #620079'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #620079'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #620079'>&nbsp;@&nbsp;</font><font style='background-color: #620079; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #620079;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #620079; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_PURPLE = -0x1.df227ap125F;
    static { NAMED.put("Very Deep Purple", -0x1.df227ap125F); LIST.add(-0x1.df227ap125F); }

    /**
     * This color constant "Very Light Purple" has RGBA8888 code {@code F0C7FAFF}, L 0.8352941, A 0.5254902, B 0.4745098, alpha 1.0, hue 0.8872099, saturation 0.76829535, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.f30daap125F}.
     * <pre>
     * <font style='background-color: #F0C7FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0C7FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0C7FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F0C7FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F0C7FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F0C7FA'>&nbsp;@&nbsp;</font><font style='background-color: #F0C7FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F0C7FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F0C7FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_LIGHT_PURPLE = -0x1.f30daap125F;
    static { NAMED.put("Very Light Purple", -0x1.f30daap125F); LIST.add(-0x1.f30daap125F); }

    /**
     * This color constant "Light Purple" has RGBA8888 code {@code CCA2E0FF}, L 0.70980394, A 0.5294118, B 0.46666667, alpha 1.0, hue 0.875, saturation 0.51425946, and chroma 0.08856081.
     * It can be represented as a packed float with the constant {@code -0x1.ef0f6ap125F}.
     * <pre>
     * <font style='background-color: #CCA2E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCA2E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCA2E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CCA2E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CCA2E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CCA2E0'>&nbsp;@&nbsp;</font><font style='background-color: #CCA2E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CCA2E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CCA2E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLE = -0x1.ef0f6ap125F;
    static { NAMED.put("Light Purple", -0x1.ef0f6ap125F); LIST.add(-0x1.ef0f6ap125F); }

    /**
     * This color constant "Moderate Purple" has RGBA8888 code {@code AC64BEFF}, L 0.5294118, A 0.5529412, B 0.4509804, alpha 1.0, hue 0.8872099, saturation 0.45528615, and chroma 0.14373726.
     * It can be represented as a packed float with the constant {@code -0x1.e71b0ep125F}.
     * <pre>
     * <font style='background-color: #AC64BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC64BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC64BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC64BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC64BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC64BE'>&nbsp;@&nbsp;</font><font style='background-color: #AC64BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC64BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC64BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLE = -0x1.e71b0ep125F;
    static { NAMED.put("Moderate Purple", -0x1.e71b0ep125F); LIST.add(-0x1.e71b0ep125F); }

    /**
     * This color constant "Dark Purple" has RGBA8888 code {@code 743E86FF}, L 0.34901962, A 0.54509807, B 0.45490196, alpha 1.0, hue 0.88191015, saturation 0.52512324, and chroma 0.12705825.
     * It can be represented as a packed float with the constant {@code -0x1.e916b2p125F}.
     * <pre>
     * <font style='background-color: #743E86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #743E86; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #743E86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #743E86'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #743E86'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #743E86'>&nbsp;@&nbsp;</font><font style='background-color: #743E86; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #743E86;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #743E86; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLE = -0x1.e916b2p125F;
    static { NAMED.put("Dark Purple", -0x1.e916b2p125F); LIST.add(-0x1.e916b2p125F); }

    /**
     * This color constant "Very Dark Purple" has RGBA8888 code {@code 4F1E63FF}, L 0.21568628, A 0.54509807, B 0.4509804, alpha 1.0, hue 0.875, saturation 0.7221516, and chroma 0.13269757.
     * It can be represented as a packed float with the constant {@code -0x1.e7166ep125F}.
     * <pre>
     * <font style='background-color: #4F1E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F1E63; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F1E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4F1E63'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4F1E63'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4F1E63'>&nbsp;@&nbsp;</font><font style='background-color: #4F1E63; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4F1E63;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4F1E63; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_PURPLE = -0x1.e7166ep125F;
    static { NAMED.put("Very Dark Purple", -0x1.e7166ep125F); LIST.add(-0x1.e7166ep125F); }

    /**
     * This color constant "Very Pale Purple" has RGBA8888 code {@code ECD6F7FF}, L 0.85882354, A 0.5137255, B 0.48235294, alpha 1.0, hue 0.875, saturation 0.6285393, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.f707b6p125F}.
     * <pre>
     * <font style='background-color: #ECD6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECD6F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECD6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ECD6F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ECD6F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ECD6F7'>&nbsp;@&nbsp;</font><font style='background-color: #ECD6F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ECD6F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ECD6F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_PALE_PURPLE = -0x1.f707b6p125F;
    static { NAMED.put("Very Pale Purple", -0x1.f707b6p125F); LIST.add(-0x1.f707b6p125F); }

    /**
     * This color constant "Pale Purple" has RGBA8888 code {@code C0A6CCFF}, L 0.69803923, A 0.5176471, B 0.47843137, alpha 1.0, hue 0.875, saturation 0.30743772, and chroma 0.055518243.
     * It can be represented as a packed float with the constant {@code -0x1.f50964p125F}.
     * <pre>
     * <font style='background-color: #C0A6CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0A6CC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0A6CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C0A6CC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C0A6CC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C0A6CC'>&nbsp;@&nbsp;</font><font style='background-color: #C0A6CC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C0A6CC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C0A6CC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLE = -0x1.f50964p125F;
    static { NAMED.put("Pale Purple", -0x1.f50964p125F); LIST.add(-0x1.f50964p125F); }

    /**
     * This color constant "Grayish Purple" has RGBA8888 code {@code 9B729FFF}, L 0.52156866, A 0.5294118, B 0.4745098, alpha 1.0, hue 0.8975709, saturation 0.25316456, and chroma 0.07753685.
     * It can be represented as a packed float with the constant {@code -0x1.f30f0ap125F}.
     * <pre>
     * <font style='background-color: #9B729F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B729F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B729F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B729F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B729F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B729F'>&nbsp;@&nbsp;</font><font style='background-color: #9B729F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B729F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B729F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLE = -0x1.f30f0ap125F;
    static { NAMED.put("Grayish Purple", -0x1.f30f0ap125F); LIST.add(-0x1.f30f0ap125F); }

    /**
     * This color constant "Dark Grayish Purple" has RGBA8888 code {@code 6B4C73FF}, L 0.35686275, A 0.5254902, B 0.4745098, alpha 1.0, hue 0.8872099, saturation 0.29740465, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.f30cb6p125F}.
     * <pre>
     * <font style='background-color: #6B4C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B4C73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B4C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6B4C73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6B4C73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6B4C73'>&nbsp;@&nbsp;</font><font style='background-color: #6B4C73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6B4C73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6B4C73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAYISH_PURPLE = -0x1.f30cb6p125F;
    static { NAMED.put("Dark Grayish Purple", -0x1.f30cb6p125F); LIST.add(-0x1.f30cb6p125F); }

    /**
     * This color constant "Blackish Purple" has RGBA8888 code {@code 462B48FF}, L 0.20784314, A 0.5254902, B 0.47843137, alpha 1.0, hue 0.90127134, saturation 0.39101478, and chroma 0.06652104.
     * It can be represented as a packed float with the constant {@code -0x1.f50c6ap125F}.
     * <pre>
     * <font style='background-color: #462B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #462B48; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #462B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #462B48'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #462B48'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #462B48'>&nbsp;@&nbsp;</font><font style='background-color: #462B48; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #462B48;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #462B48; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKISH_PURPLE = -0x1.f50c6ap125F;
    static { NAMED.put("Blackish Purple", -0x1.f50c6ap125F); LIST.add(-0x1.f50c6ap125F); }

    /**
     * This color constant "Purplish White" has RGBA8888 code {@code F2E7F7FF}, L 0.9019608, A 0.5058824, B 0.49019608, alpha 1.0, hue 0.875, saturation 0.51425946, and chroma 0.022777155.
     * It can be represented as a packed float with the constant {@code -0x1.fb03ccp125F}.
     * <pre>
     * <font style='background-color: #F2E7F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2E7F7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2E7F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F2E7F7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F2E7F7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F2E7F7'>&nbsp;@&nbsp;</font><font style='background-color: #F2E7F7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F2E7F7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F2E7F7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_WHITE = -0x1.fb03ccp125F;
    static { NAMED.put("Purplish White", -0x1.fb03ccp125F); LIST.add(-0x1.fb03ccp125F); }

    /**
     * This color constant "Light Purplish Gray" has RGBA8888 code {@code D3C3DBFF}, L 0.7882353, A 0.50980395, B 0.4862745, alpha 1.0, hue 0.875, saturation 0.2828427, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.f90592p125F}.
     * <pre>
     * <font style='background-color: #D3C3DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3C3DB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3C3DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3C3DB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3C3DB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3C3DB'>&nbsp;@&nbsp;</font><font style='background-color: #D3C3DB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3C3DB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3C3DB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_GRAY = -0x1.f90592p125F;
    static { NAMED.put("Light Purplish Gray", -0x1.f90592p125F); LIST.add(-0x1.f90592p125F); }

    /**
     * This color constant "Purplish Gray" has RGBA8888 code {@code A395AAFF}, L 0.6156863, A 0.50980395, B 0.4862745, alpha 1.0, hue 0.875, saturation 0.13685937, and chroma 0.033602834.
     * It can be represented as a packed float with the constant {@code -0x1.f9053ap125F}.
     * <pre>
     * <font style='background-color: #A395AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A395AA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A395AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A395AA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A395AA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A395AA'>&nbsp;@&nbsp;</font><font style='background-color: #A395AA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A395AA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A395AA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_GRAY = -0x1.f9053ap125F;
    static { NAMED.put("Purplish Gray", -0x1.f9053ap125F); LIST.add(-0x1.f9053ap125F); }

    /**
     * This color constant "Dark Purplish Gray" has RGBA8888 code {@code 73627CFF}, L 0.42352942, A 0.5137255, B 0.48235294, alpha 1.0, hue 0.875, saturation 0.15934801, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.f706d8p125F}.
     * <pre>
     * <font style='background-color: #73627C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73627C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73627C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73627C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73627C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73627C'>&nbsp;@&nbsp;</font><font style='background-color: #73627C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73627C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73627C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_GRAY = -0x1.f706d8p125F;
    static { NAMED.put("Dark Purplish Gray", -0x1.f706d8p125F); LIST.add(-0x1.f706d8p125F); }

    /**
     * This color constant "Purplish Black" has RGBA8888 code {@code 3F3146FF}, L 0.21176471, A 0.5137255, B 0.48235294, alpha 1.0, hue 0.875, saturation 0.24595018, and chroma 0.044538103.
     * It can be represented as a packed float with the constant {@code -0x1.f7066cp125F}.
     * <pre>
     * <font style='background-color: #3F3146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F3146; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F3146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F3146'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F3146'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F3146'>&nbsp;@&nbsp;</font><font style='background-color: #3F3146; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F3146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F3146; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLISH_BLACK = -0x1.f7066cp125F;
    static { NAMED.put("Purplish Black", -0x1.f7066cp125F); LIST.add(-0x1.f7066cp125F); }

    /**
     * This color constant "Vivid Reddish Purple" has RGBA8888 code {@code FD1BD7FF}, L 0.5764706, A 0.6313726, B 0.4392157, alpha 1.0, hue 0.9338804, saturation 0.94080526, and chroma 0.28837544.
     * It can be represented as a packed float with the constant {@code -0x1.e14326p125F}.
     * <pre>
     * <font style='background-color: #FD1BD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD1BD7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD1BD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD1BD7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD1BD7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD1BD7'>&nbsp;@&nbsp;</font><font style='background-color: #FD1BD7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD1BD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD1BD7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_REDDISH_PURPLE = -0x1.e14326p125F;
    static { NAMED.put("Vivid Reddish Purple", -0x1.e14326p125F); LIST.add(-0x1.e14326p125F); }

    /**
     * This color constant "Strong Reddish Purple" has RGBA8888 code {@code D147B7FF}, L 0.5294118, A 0.5921569, B 0.45490196, alpha 1.0, hue 0.9316074, saturation 0.7040202, and chroma 0.20439805.
     * It can be represented as a packed float with the constant {@code -0x1.e92f0ep125F}.
     * <pre>
     * <font style='background-color: #D147B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D147B7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D147B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D147B7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D147B7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D147B7'>&nbsp;@&nbsp;</font><font style='background-color: #D147B7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D147B7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D147B7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_REDDISH_PURPLE = -0x1.e92f0ep125F;
    static { NAMED.put("Strong Reddish Purple", -0x1.e92f0ep125F); LIST.add(-0x1.e92f0ep125F); }

    /**
     * This color constant "Deep Reddish Purple" has RGBA8888 code {@code 9B1786FF}, L 0.36078432, A 0.5921569, B 0.45490196, alpha 1.0, hue 0.9316074, saturation 0.91037095, and chroma 0.20439805.
     * It can be represented as a packed float with the constant {@code -0x1.e92eb8p125F}.
     * <pre>
     * <font style='background-color: #9B1786;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B1786; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B1786;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9B1786'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9B1786'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9B1786'>&nbsp;@&nbsp;</font><font style='background-color: #9B1786; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9B1786;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9B1786; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_REDDISH_PURPLE = -0x1.e92eb8p125F;
    static { NAMED.put("Deep Reddish Purple", -0x1.e92eb8p125F); LIST.add(-0x1.e92eb8p125F); }

    /**
     * This color constant "Very Deep Reddish Purple" has RGBA8888 code {@code 6C0066FF}, L 0.24313726, A 0.5764706, B 0.45490196, alpha 1.0, hue 0.91996604, saturation 0.99240977, and chroma 0.17686298.
     * It can be represented as a packed float with the constant {@code -0x1.e9267cp125F}.
     * <pre>
     * <font style='background-color: #6C0066;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C0066; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C0066;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6C0066'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6C0066'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6C0066'>&nbsp;@&nbsp;</font><font style='background-color: #6C0066; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6C0066;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6C0066; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_REDDISH_PURPLE = -0x1.e9267cp125F;
    static { NAMED.put("Very Deep Reddish Purple", -0x1.e9267cp125F); LIST.add(-0x1.e9267cp125F); }

    /**
     * This color constant "Light Reddish Purple" has RGBA8888 code {@code D78EC2FF}, L 0.6666667, A 0.54509807, B 0.47843137, alpha 1.0, hue 0.9371773, saturation 0.4642857, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.f51754p125F}.
     * <pre>
     * <font style='background-color: #D78EC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D78EC2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D78EC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D78EC2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D78EC2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D78EC2'>&nbsp;@&nbsp;</font><font style='background-color: #D78EC2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D78EC2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D78EC2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_REDDISH_PURPLE = -0x1.f51754p125F;
    static { NAMED.put("Light Reddish Purple", -0x1.f51754p125F); LIST.add(-0x1.f51754p125F); }

    /**
     * This color constant "Moderate Reddish Purple" has RGBA8888 code {@code BF5BAEFF}, L 0.5294118, A 0.5686275, B 0.4627451, alpha 1.0, hue 0.92620975, saturation 0.5366563, and chroma 0.1555649.
     * It can be represented as a packed float with the constant {@code -0x1.ed230ep125F}.
     * <pre>
     * <font style='background-color: #BF5BAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF5BAE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF5BAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF5BAE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF5BAE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF5BAE'>&nbsp;@&nbsp;</font><font style='background-color: #BF5BAE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF5BAE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF5BAE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_REDDISH_PURPLE = -0x1.ed230ep125F;
    static { NAMED.put("Moderate Reddish Purple", -0x1.ed230ep125F); LIST.add(-0x1.ed230ep125F); }

    /**
     * This color constant "Dark Reddish Purple" has RGBA8888 code {@code 823973FF}, L 0.34901962, A 0.5568628, B 0.47058824, alpha 1.0, hue 0.930514, saturation 0.5808051, and chroma 0.12753771.
     * It can be represented as a packed float with the constant {@code -0x1.f11cb2p125F}.
     * <pre>
     * <font style='background-color: #823973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #823973; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #823973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #823973'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #823973'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #823973'>&nbsp;@&nbsp;</font><font style='background-color: #823973; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #823973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #823973; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_REDDISH_PURPLE = -0x1.f11cb2p125F;
    static { NAMED.put("Dark Reddish Purple", -0x1.f11cb2p125F); LIST.add(-0x1.f11cb2p125F); }

    /**
     * This color constant "Very Dark Reddish Purple" has RGBA8888 code {@code 581C58FF}, L 0.21960784, A 0.5529412, B 0.4627451, alpha 1.0, hue 0.90905774, saturation 0.73970294, and chroma 0.12896542.
     * It can be represented as a packed float with the constant {@code -0x1.ed1a7p125F}.
     * <pre>
     * <font style='background-color: #581C58;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #581C58; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #581C58;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #581C58'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #581C58'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #581C58'>&nbsp;@&nbsp;</font><font style='background-color: #581C58; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #581C58;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #581C58; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_REDDISH_PURPLE = -0x1.ed1a7p125F;
    static { NAMED.put("Very Dark Reddish Purple", -0x1.ed1a7p125F); LIST.add(-0x1.ed1a7p125F); }

    /**
     * This color constant "Pale Reddish Purple" has RGBA8888 code {@code C696BCFF}, L 0.6627451, A 0.5294118, B 0.48235294, alpha 1.0, hue 0.92620975, saturation 0.2981424, and chroma 0.068331465.
     * It can be represented as a packed float with the constant {@code -0x1.f70f52p125F}.
     * <pre>
     * <font style='background-color: #C696BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C696BC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C696BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C696BC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C696BC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C696BC'>&nbsp;@&nbsp;</font><font style='background-color: #C696BC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C696BC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C696BC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_REDDISH_PURPLE = -0x1.f70f52p125F;
    static { NAMED.put("Pale Reddish Purple", -0x1.f70f52p125F); LIST.add(-0x1.f70f52p125F); }

    /**
     * This color constant "Grayish Reddish Purple" has RGBA8888 code {@code AD6A9AFF}, L 0.5254902, A 0.54509807, B 0.47843137, alpha 1.0, hue 0.9371773, saturation 0.35135135, and chroma 0.09959023.
     * It can be represented as a packed float with the constant {@code -0x1.f5170cp125F}.
     * <pre>
     * <font style='background-color: #AD6A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD6A9A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD6A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AD6A9A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AD6A9A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AD6A9A'>&nbsp;@&nbsp;</font><font style='background-color: #AD6A9A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AD6A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AD6A9A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_REDDISH_PURPLE = -0x1.f5170cp125F;
    static { NAMED.put("Grayish Reddish Purple", -0x1.f5170cp125F); LIST.add(-0x1.f5170cp125F); }

    /**
     * This color constant "Brilliant Purplish Pink" has RGBA8888 code {@code FFB4F1FF}, L 0.80784315, A 0.54509807, B 0.4745098, alpha 1.0, hue 0.92620975, saturation 0.9252695, and chroma 0.10320191.
     * It can be represented as a packed float with the constant {@code -0x1.f3179cp125F}.
     * <pre>
     * <font style='background-color: #FFB4F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFB4F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFB4F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFB4F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFB4F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFB4F1'>&nbsp;@&nbsp;</font><font style='background-color: #FFB4F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFB4F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFB4F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRILLIANT_PURPLISH_PINK = -0x1.f3179cp125F;
    static { NAMED.put("Brilliant Purplish Pink", -0x1.f3179cp125F); LIST.add(-0x1.f3179cp125F); }

    /**
     * This color constant "Strong Purplish Pink" has RGBA8888 code {@code FB9AD7FF}, L 0.7411765, A 0.5568628, B 0.47843137, alpha 1.0, hue 0.9488043, saturation 0.85466963, and chroma 0.12115674.
     * It can be represented as a packed float with the constant {@code -0x1.f51d7ap125F}.
     * <pre>
     * <font style='background-color: #FB9AD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FB9AD7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FB9AD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FB9AD7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FB9AD7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FB9AD7'>&nbsp;@&nbsp;</font><font style='background-color: #FB9AD7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FB9AD7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FB9AD7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_PINK = -0x1.f51d7ap125F;
    static { NAMED.put("Strong Purplish Pink", -0x1.f51d7ap125F); LIST.add(-0x1.f51d7ap125F); }

    /**
     * This color constant "Deep Purplish Pink" has RGBA8888 code {@code EB81C1FF}, L 0.6666667, A 0.5647059, B 0.47843137, alpha 1.0, hue 0.954483, saturation 0.7088018, and chroma 0.13587911.
     * It can be represented as a packed float with the constant {@code -0x1.f52154p125F}.
     * <pre>
     * <font style='background-color: #EB81C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB81C1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB81C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EB81C1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EB81C1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EB81C1'>&nbsp;@&nbsp;</font><font style='background-color: #EB81C1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EB81C1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EB81C1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_PINK = -0x1.f52154p125F;
    static { NAMED.put("Deep Purplish Pink", -0x1.f52154p125F); LIST.add(-0x1.f52154p125F); }

    /**
     * This color constant "Light Purplish Pink" has RGBA8888 code {@code FDC0E5FF}, L 0.8235294, A 0.53333336, B 0.4862745, alpha 1.0, hue 0.9488043, saturation 0.824942, and chroma 0.071815535.
     * It can be represented as a packed float with the constant {@code -0x1.f911a4p125F}.
     * <pre>
     * <font style='background-color: #FDC0E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDC0E5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDC0E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FDC0E5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FDC0E5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FDC0E5'>&nbsp;@&nbsp;</font><font style='background-color: #FDC0E5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FDC0E5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FDC0E5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_PURPLISH_PINK = -0x1.f911a4p125F;
    static { NAMED.put("Light Purplish Pink", -0x1.f911a4p125F); LIST.add(-0x1.f911a4p125F); }

    /**
     * This color constant "Moderate Purplish Pink" has RGBA8888 code {@code EBA9CFFF}, L 0.7529412, A 0.5372549, B 0.4862745, alpha 1.0, hue 0.95362335, saturation 0.6141357, and chroma 0.079095535.
     * It can be represented as a packed float with the constant {@code -0x1.f9138p125F}.
     * <pre>
     * <font style='background-color: #EBA9CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBA9CF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBA9CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBA9CF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBA9CF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBA9CF'>&nbsp;@&nbsp;</font><font style='background-color: #EBA9CF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBA9CF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBA9CF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_PINK = -0x1.f9138p125F;
    static { NAMED.put("Moderate Purplish Pink", -0x1.f9138p125F); LIST.add(-0x1.f9138p125F); }

    /**
     * This color constant "Dark Purplish Pink" has RGBA8888 code {@code DC8EB1FF}, L 0.6666667, A 0.54509807, B 0.49019608, alpha 1.0, hue 0.9737039, saturation 0.5289359, and chroma 0.091942206.
     * It can be represented as a packed float with the constant {@code -0x1.fb1754p125F}.
     * <pre>
     * <font style='background-color: #DC8EB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC8EB1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC8EB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DC8EB1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DC8EB1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DC8EB1'>&nbsp;@&nbsp;</font><font style='background-color: #DC8EB1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DC8EB1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DC8EB1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_PINK = -0x1.fb1754p125F;
    static { NAMED.put("Dark Purplish Pink", -0x1.fb1754p125F); LIST.add(-0x1.fb1754p125F); }

    /**
     * This color constant "Pale Purplish Pink" has RGBA8888 code {@code F5D3E9FF}, L 0.85882354, A 0.5176471, B 0.49019608, alpha 1.0, hue 0.939452, saturation 0.59835166, and chroma 0.040217306.
     * It can be represented as a packed float with the constant {@code -0x1.fb09b6p125F}.
     * <pre>
     * <font style='background-color: #F5D3E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5D3E9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5D3E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5D3E9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5D3E9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5D3E9'>&nbsp;@&nbsp;</font><font style='background-color: #F5D3E9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5D3E9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5D3E9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PALE_PURPLISH_PINK = -0x1.fb09b6p125F;
    static { NAMED.put("Pale Purplish Pink", -0x1.fb09b6p125F); LIST.add(-0x1.fb09b6p125F); }

    /**
     * This color constant "Grayish Purplish Pink" has RGBA8888 code {@code DDAFC8FF}, L 0.74509805, A 0.5254902, B 0.49019608, alpha 1.0, hue 0.95571566, saturation 0.41600627, and chroma 0.054407768.
     * It can be represented as a packed float with the constant {@code -0x1.fb0d7cp125F}.
     * <pre>
     * <font style='background-color: #DDAFC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDAFC8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDAFC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DDAFC8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DDAFC8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DDAFC8'>&nbsp;@&nbsp;</font><font style='background-color: #DDAFC8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DDAFC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DDAFC8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_PINK = -0x1.fb0d7cp125F;
    static { NAMED.put("Grayish Purplish Pink", -0x1.fb0d7cp125F); LIST.add(-0x1.fb0d7cp125F); }

    /**
     * This color constant "Vivid Purplish Red" has RGBA8888 code {@code FF2A9AFF}, L 0.56078434, A 0.62352943, B 0.48235294, alpha 1.0, hue 0.98019654, saturation 0.94850093, and chroma 0.24859223.
     * It can be represented as a packed float with the constant {@code -0x1.f73f1ep125F}.
     * <pre>
     * <font style='background-color: #FF2A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF2A9A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF2A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF2A9A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF2A9A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF2A9A'>&nbsp;@&nbsp;</font><font style='background-color: #FF2A9A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF2A9A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF2A9A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIVID_PURPLISH_RED = -0x1.f73f1ep125F;
    static { NAMED.put("Vivid Purplish Red", -0x1.f73f1ep125F); LIST.add(-0x1.f73f1ep125F); }

    /**
     * This color constant "Strong Purplish Red" has RGBA8888 code {@code E4338AFF}, L 0.5137255, A 0.60784316, B 0.4862745, alpha 1.0, hue 0.9830039, saturation 0.8282428, and chroma 0.21657681.
     * It can be represented as a packed float with the constant {@code -0x1.f93706p125F}.
     * <pre>
     * <font style='background-color: #E4338A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4338A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4338A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E4338A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E4338A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E4338A'>&nbsp;@&nbsp;</font><font style='background-color: #E4338A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E4338A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E4338A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_PURPLISH_RED = -0x1.f93706p125F;
    static { NAMED.put("Strong Purplish Red", -0x1.f93706p125F); LIST.add(-0x1.f93706p125F); }

    /**
     * This color constant "Deep Purplish Red" has RGBA8888 code {@code B00070FF}, L 0.3764706, A 0.6039216, B 0.47843137, alpha 1.0, hue 0.97084755, saturation 0.98068076, and chroma 0.21144326.
     * It can be represented as a packed float with the constant {@code -0x1.f534cp125F}.
     * <pre>
     * <font style='background-color: #B00070;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B00070; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B00070;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B00070'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B00070'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B00070'>&nbsp;@&nbsp;</font><font style='background-color: #B00070; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B00070;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B00070; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLISH_RED = -0x1.f534cp125F;
    static { NAMED.put("Deep Purplish Red", -0x1.f534cp125F); LIST.add(-0x1.f534cp125F); }

    /**
     * This color constant "Very Deep Purplish Red" has RGBA8888 code {@code 730052FF}, L 0.24313726, A 0.5803922, B 0.4745098, alpha 1.0, hue 0.95571566, saturation 0.9927423, and chroma 0.16801417.
     * It can be represented as a packed float with the constant {@code -0x1.f3287cp125F}.
     * <pre>
     * <font style='background-color: #730052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #730052; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #730052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #730052'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #730052'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #730052'>&nbsp;@&nbsp;</font><font style='background-color: #730052; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #730052;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #730052; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DEEP_PURPLISH_RED = -0x1.f3287cp125F;
    static { NAMED.put("Very Deep Purplish Red", -0x1.f3287cp125F); LIST.add(-0x1.f3287cp125F); }

    /**
     * This color constant "Moderate Purplish Red" has RGBA8888 code {@code D74B8EFF}, L 0.5254902, A 0.5882353, B 0.4862745, alpha 1.0, hue 0.9793449, saturation 0.6723138, and chroma 0.17789528.
     * It can be represented as a packed float with the constant {@code -0x1.f92d0cp125F}.
     * <pre>
     * <font style='background-color: #D74B8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D74B8E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D74B8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D74B8E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D74B8E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D74B8E'>&nbsp;@&nbsp;</font><font style='background-color: #D74B8E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D74B8E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D74B8E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MODERATE_PURPLISH_RED = -0x1.f92d0cp125F;
    static { NAMED.put("Moderate Purplish Red", -0x1.f92d0cp125F); LIST.add(-0x1.f92d0cp125F); }

    /**
     * This color constant "Dark Purplish Red" has RGBA8888 code {@code 922D61FF}, L 0.34509805, A 0.57254905, B 0.4862745, alpha 1.0, hue 0.9750635, saturation 0.7258636, and chroma 0.14709508.
     * It can be represented as a packed float with the constant {@code -0x1.f924bp125F}.
     * <pre>
     * <font style='background-color: #922D61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #922D61; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #922D61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #922D61'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #922D61'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #922D61'>&nbsp;@&nbsp;</font><font style='background-color: #922D61; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #922D61;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #922D61; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PURPLISH_RED = -0x1.f924bp125F;
    static { NAMED.put("Dark Purplish Red", -0x1.f924bp125F); LIST.add(-0x1.f924bp125F); }

    /**
     * This color constant "Very Dark Purplish Red" has RGBA8888 code {@code 631244FF}, L 0.21568628, A 0.5647059, B 0.48235294, alpha 1.0, hue 0.9632207, saturation 0.8519146, and chroma 0.1336143.
     * It can be represented as a packed float with the constant {@code -0x1.f7206ep125F}.
     * <pre>
     * <font style='background-color: #631244;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #631244; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #631244;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #631244'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #631244'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #631244'>&nbsp;@&nbsp;</font><font style='background-color: #631244; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #631244;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #631244; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VERY_DARK_PURPLISH_RED = -0x1.f7206ep125F;
    static { NAMED.put("Very Dark Purplish Red", -0x1.f7206ep125F); LIST.add(-0x1.f7206ep125F); }

    /**
     * This color constant "Light Grayish Purplish Red" has RGBA8888 code {@code CB97ABFF}, L 0.6627451, A 0.5294118, B 0.49411765, alpha 1.0, hue 0.98019654, saturation 0.35053295, and chroma 0.059754133.
     * It can be represented as a packed float with the constant {@code -0x1.fd0f52p125F}.
     * <pre>
     * <font style='background-color: #CB97AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB97AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB97AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CB97AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CB97AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CB97AB'>&nbsp;@&nbsp;</font><font style='background-color: #CB97AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CB97AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CB97AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAYISH_PURPLISH_RED = -0x1.fd0f52p125F;
    static { NAMED.put("Light Grayish Purplish Red", -0x1.fd0f52p125F); LIST.add(-0x1.fd0f52p125F); }

    /**
     * This color constant "Grayish Purplish Red" has RGBA8888 code {@code BD638AFF}, L 0.5254902, A 0.5568628, B 0.49019608, alpha 1.0, hue 0.97889125, saturation 0.4386303, and chroma 0.114952646.
     * It can be represented as a packed float with the constant {@code -0x1.fb1d0cp125F}.
     * <pre>
     * <font style='background-color: #BD638A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD638A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD638A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD638A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD638A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD638A'>&nbsp;@&nbsp;</font><font style='background-color: #BD638A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD638A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD638A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAYISH_PURPLISH_RED = -0x1.fb1d0cp125F;
    static { NAMED.put("Grayish Purplish Red", -0x1.fb1d0cp125F); LIST.add(-0x1.fb1d0cp125F); }

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
