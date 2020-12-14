package com.github.tommyettinger.colorful.ipt_hq.internal;

import com.github.tommyettinger.colorful.ipt_hq.ColorTools;
import com.github.tommyettinger.ds.FloatList;
import com.github.tommyettinger.ds.ObjectFloatOrderedMap;
import com.github.tommyettinger.ds.ObjectList;

import java.util.Comparator;

import static com.github.tommyettinger.colorful.ipt_hq.ColorTools.*;

/**
 * A palette of predefined colors as packed IPT floats, the kind {@link ColorTools} works with.
 * You can access colors by their constant name, such as {@code OCEAN_BLUE}, by the {@link #NAMED} map using
 * {@code NAMED.get("Ocean Blue", 0f)}, or by index in the FloatArray called {@link #LIST}. Note that to access a float
 * color from NAMED, you need to give a default value if the name is not found; {@code 0f} is a good default because it
 * will not occur in a valid IPT color. You can access the names in a specific order with {@link #NAMES} (which is
 * alphabetical), {@link #NAMES_BY_HUE} (which is sorted by the hue of the matching color, from red to yellow to blue
 * (with gray around here) to purple to red again), or {@link #NAMES_BY_LIGHTNESS} (which is sorted by the intensity of
 * the matching color, from darkest to lightest). Having a name lets you look up the matching color in {@link #NAMED}.
 * <br>
 * Created by Tommy Ettinger on 10/13/2020.
 */
public class Palette {
    public static final ObjectFloatOrderedMap<String> NAMED = new ObjectFloatOrderedMap<String>(50);
    public static final FloatList LIST = new FloatList(50);

    /**
     * This color constant "transparent" has RGBA8888 code {@code 00000000}, intensity 0.0, protan 0.49803922, tritan 0.49803922, alpha 0.0, hue 0.6666667, and saturation 0.0.
     * It can be represented as a packed float with the constant {@code 0x0.fefep-126F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRANSPARENT = 0x0.fefep-126F;
    static { NAMED.put("transparent", 0x0.fefep-126F); LIST.add(0x0.fefep-126F); }

    /**
     * This color constant "black" has RGBA8888 code {@code 000000FF}, intensity 0.0, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.6666667, and saturation 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.fefep125F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK = -0x1.fefep125F;
    static { NAMED.put("black", -0x1.fefep125F); LIST.add(-0x1.fefep125F); }

    /**
     * This color constant "gray" has RGBA8888 code {@code 808080FF}, intensity 0.5529412, protan 0.5019608, tritan 0.49803922, alpha 1.0, hue 0.81858563, and saturation 0.005999118.
     * It can be represented as a packed float with the constant {@code -0x1.ff011ap125F}.
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY = -0x1.ff011ap125F;
    static { NAMED.put("gray", -0x1.ff011ap125F); LIST.add(-0x1.ff011ap125F); }

    /**
     * This color constant "silver" has RGBA8888 code {@code B6B6B6FF}, intensity 0.7490196, protan 0.5019608, tritan 0.49803922, alpha 1.0, hue 0.81859344, and saturation 0.0062664747.
     * It can be represented as a packed float with the constant {@code -0x1.ff017ep125F}.
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER = -0x1.ff017ep125F;
    static { NAMED.put("silver", -0x1.ff017ep125F); LIST.add(-0x1.ff017ep125F); }

    /**
     * This color constant "white" has RGBA8888 code {@code FFFFFFFF}, intensity 1.0, protan 0.5019608, tritan 0.49803922, alpha 1.0, hue 0.8333333, and saturation 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.ff01fep125F}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE = -0x1.ff01fep125F;
    static { NAMED.put("white", -0x1.ff01fep125F); LIST.add(-0x1.ff01fep125F); }

    /**
     * This color constant "red" has RGBA8888 code {@code FF0000FF}, intensity 0.45490196, protan 0.8117647, tritan 0.72156864, alpha 1.0, hue 0.0, and saturation 0.9997024.
     * It can be represented as a packed float with the constant {@code -0x1.719ee8p126F}.
     * <pre>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #FF0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RED = -0x1.719ee8p126F;
    static { NAMED.put("red", -0x1.719ee8p126F); LIST.add(-0x1.719ee8p126F); }

    /**
     * This color constant "orange" has RGBA8888 code {@code FF7F00FF}, intensity 0.5921569, protan 0.64705884, tritan 0.7529412, alpha 1.0, hue 0.08270054, and saturation 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.814b2ep126F}.
     * <pre>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #FF7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORANGE = -0x1.814b2ep126F;
    static { NAMED.put("orange", -0x1.814b2ep126F); LIST.add(-0x1.814b2ep126F); }

    /**
     * This color constant "yellow" has RGBA8888 code {@code FFFF00FF}, intensity 0.85882354, protan 0.44705883, tritan 0.827451, alpha 1.0, hue 0.16666667, and saturation 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.a6e5b6p126F}.
     * <pre>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOW = -0x1.a6e5b6p126F;
    static { NAMED.put("yellow", -0x1.a6e5b6p126F); LIST.add(-0x1.a6e5b6p126F); }

    /**
     * This color constant "green" has RGBA8888 code {@code 00FF00FF}, intensity 0.7607843, protan 0.27058825, tritan 0.7647059, alpha 1.0, hue 0.33734334, and saturation 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.868b84p126F}.
     * <pre>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #00FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREEN = -0x1.868b84p126F;
    static { NAMED.put("green", -0x1.868b84p126F); LIST.add(-0x1.868b84p126F); }

    /**
     * This color constant "blue" has RGBA8888 code {@code 0000FFFF}, intensity 0.44313726, protan 0.38039216, tritan 0.1254902, alpha 1.0, hue 0.6666667, and saturation 0.9989025.
     * It can be represented as a packed float with the constant {@code -0x1.40c2e2p125F}.
     * <pre>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #0000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE = -0x1.40c2e2p125F;
    static { NAMED.put("blue", -0x1.40c2e2p125F); LIST.add(-0x1.40c2e2p125F); }

    /**
     * This color constant "indigo" has RGBA8888 code {@code 520FE0FF}, intensity 0.44313726, protan 0.49803922, tritan 0.2, alpha 1.0, hue 0.72370124, and saturation 0.84834015.
     * It can be represented as a packed float with the constant {@code -0x1.66fee2p125F}.
     * <pre>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #520FE0'>&nbsp;@&nbsp;</font><font style='background-color: #520FE0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #520FE0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #520FE0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float INDIGO = -0x1.66fee2p125F;
    static { NAMED.put("indigo", -0x1.66fee2p125F); LIST.add(-0x1.66fee2p125F); }

    /**
     * This color constant "violet" has RGBA8888 code {@code 8F57C7FF}, intensity 0.5529412, protan 0.5686275, tritan 0.34117648, alpha 1.0, hue 0.74976707, and saturation 0.4390783.
     * It can be represented as a packed float with the constant {@code -0x1.af231ap125F}.
     * <pre>
     * <font style='background-color: #8F57C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F57C7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F57C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F57C7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F57C7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F57C7'>&nbsp;@&nbsp;</font><font style='background-color: #8F57C7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F57C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F57C7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIOLET = -0x1.af231ap125F;
    static { NAMED.put("violet", -0x1.af231ap125F); LIST.add(-0x1.af231ap125F); }

    /**
     * This color constant "purple" has RGBA8888 code {@code C000FFFF}, intensity 0.59607846, protan 0.68235296, tritan 0.24705882, alpha 1.0, hue 0.7911109, and saturation 0.96850824.
     * It can be represented as a packed float with the constant {@code -0x1.7f5d3p125F}.
     * <pre>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C000FF'>&nbsp;@&nbsp;</font><font style='background-color: #C000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLE = -0x1.7f5d3p125F;
    static { NAMED.put("purple", -0x1.7f5d3p125F); LIST.add(-0x1.7f5d3p125F); }

    /**
     * This color constant "magenta" has RGBA8888 code {@code F500F5FF}, intensity 0.64705884, protan 0.7764706, tritan 0.30980393, alpha 1.0, hue 0.83288956, and saturation 0.96499586.
     * It can be represented as a packed float with the constant {@code -0x1.9f8d4ap125F}.
     * <pre>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #F500F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAGENTA = -0x1.9f8d4ap125F;
    static { NAMED.put("magenta", -0x1.9f8d4ap125F); LIST.add(-0x1.9f8d4ap125F); }

    /**
     * This color constant "pink" has RGBA8888 code {@code F880BEFF}, intensity 0.7137255, protan 0.6666667, tritan 0.47058824, alpha 1.0, hue 0.91315705, and saturation 0.46917194.
     * It can be represented as a packed float with the constant {@code -0x1.f1556cp125F}.
     * <pre>
     * <font style='background-color: #F880BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F880BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F880BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F880BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F880BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F880BE'>&nbsp;@&nbsp;</font><font style='background-color: #F880BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F880BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F880BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK = -0x1.f1556cp125F;
    static { NAMED.put("pink", -0x1.f1556cp125F); LIST.add(-0x1.f1556cp125F); }

    /**
     * This color constant "shrimp" has RGBA8888 code {@code FAA0B9FF}, intensity 0.7647059, protan 0.61960787, tritan 0.50980395, alpha 1.0, hue 0.95216334, and saturation 0.35285282.
     * It can be represented as a packed float with the constant {@code -0x1.053d86p126F}.
     * <pre>
     * <font style='background-color: #FAA0B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAA0B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAA0B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FAA0B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FAA0B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FAA0B9'>&nbsp;@&nbsp;</font><font style='background-color: #FAA0B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAA0B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAA0B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHRIMP = -0x1.053d86p126F;
    static { NAMED.put("shrimp", -0x1.053d86p126F); LIST.add(-0x1.053d86p126F); }

    /**
     * This color constant "brick" has RGBA8888 code {@code D5524AFF}, intensity 0.5058824, protan 0.6862745, tritan 0.6117647, alpha 1.0, hue 0.010635166, and saturation 0.54750645.
     * It can be represented as a packed float with the constant {@code -0x1.395f02p126F}.
     * <pre>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #D5524A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRICK = -0x1.395f02p126F;
    static { NAMED.put("brick", -0x1.395f02p126F); LIST.add(-0x1.395f02p126F); }

    /**
     * This color constant "ember" has RGBA8888 code {@code F55A32FF}, intensity 0.5372549, protan 0.70980394, tritan 0.68235296, alpha 1.0, hue 0.034249328, and saturation 0.7679914.
     * It can be represented as a packed float with the constant {@code -0x1.5d6b12p126F}.
     * <pre>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #F55A32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMBER = -0x1.5d6b12p126F;
    static { NAMED.put("ember", -0x1.5d6b12p126F); LIST.add(-0x1.5d6b12p126F); }

    /**
     * This color constant "salmon" has RGBA8888 code {@code FF6262FF}, intensity 0.59607846, protan 0.72156864, tritan 0.6117647, alpha 1.0, hue 0.99928015, and saturation 0.616992.
     * It can be represented as a packed float with the constant {@code -0x1.39713p126F}.
     * <pre>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #FF6262; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SALMON = -0x1.39713p126F;
    static { NAMED.put("salmon", -0x1.39713p126F); LIST.add(-0x1.39713p126F); }

    /**
     * This color constant "chocolate" has RGBA8888 code {@code 7C3F02FF}, intensity 0.32156864, protan 0.5764706, tritan 0.63529414, alpha 1.0, hue 0.08302745, and saturation 0.47852826.
     * It can be represented as a packed float with the constant {@code -0x1.4526a4p126F}.
     * <pre>
     * <font style='background-color: #7C3F02;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C3F02; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C3F02;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7C3F02'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7C3F02'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7C3F02'>&nbsp;@&nbsp;</font><font style='background-color: #7C3F02; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7C3F02;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7C3F02; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHOCOLATE = -0x1.4526a4p126F;
    static { NAMED.put("chocolate", -0x1.4526a4p126F); LIST.add(-0x1.4526a4p126F); }

    /**
     * This color constant "tan" has RGBA8888 code {@code D2B48CFF}, intensity 0.73333335, protan 0.5294118, tritan 0.58431375, alpha 1.0, hue 0.09675702, and saturation 0.27724016.
     * It can be represented as a packed float with the constant {@code -0x1.2b0f76p126F}.
     * <pre>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2B48C'>&nbsp;@&nbsp;</font><font style='background-color: #D2B48C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2B48C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2B48C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAN = -0x1.2b0f76p126F;
    static { NAMED.put("tan", -0x1.2b0f76p126F); LIST.add(-0x1.2b0f76p126F); }

    /**
     * This color constant "bronze" has RGBA8888 code {@code CE8E31FF}, intensity 0.5882353, protan 0.56078434, tritan 0.6901961, alpha 1.0, hue 0.09874619, and saturation 0.61364305.
     * It can be represented as a packed float with the constant {@code -0x1.611f2cp126F}.
     * <pre>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CE8E31'>&nbsp;@&nbsp;</font><font style='background-color: #CE8E31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CE8E31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CE8E31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE = -0x1.611f2cp126F;
    static { NAMED.put("bronze", -0x1.611f2cp126F); LIST.add(-0x1.611f2cp126F); }

    /**
     * This color constant "cinnamon" has RGBA8888 code {@code D2691DFF}, intensity 0.50980395, protan 0.627451, tritan 0.69411767, alpha 1.0, hue 0.0698809, and saturation 0.71087015.
     * It can be represented as a packed float with the constant {@code -0x1.634104p126F}.
     * <pre>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D2691D'>&nbsp;@&nbsp;</font><font style='background-color: #D2691D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D2691D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D2691D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CINNAMON = -0x1.634104p126F;
    static { NAMED.put("cinnamon", -0x1.634104p126F); LIST.add(-0x1.634104p126F); }

    /**
     * This color constant "taupe" has RGBA8888 code {@code 583A27FF}, intensity 0.2901961, protan 0.5411765, tritan 0.5568628, alpha 1.0, hue 0.06349649, and saturation 0.19225937.
     * It can be represented as a packed float with the constant {@code -0x1.1d1494p126F}.
     * <pre>
     * <font style='background-color: #583A27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #583A27; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #583A27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #583A27'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #583A27'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #583A27'>&nbsp;@&nbsp;</font><font style='background-color: #583A27; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #583A27;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #583A27; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAUPE = -0x1.1d1494p126F;
    static { NAMED.put("taupe", -0x1.1d1494p126F); LIST.add(-0x1.1d1494p126F); }

    /**
     * This color constant "sepia" has RGBA8888 code {@code 8F573BFF}, intensity 0.42352942, protan 0.57254905, tritan 0.5882353, alpha 1.0, hue 0.056629464, and saturation 0.3289587.
     * It can be represented as a packed float with the constant {@code -0x1.2d24d8p126F}.
     * <pre>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F573B'>&nbsp;@&nbsp;</font><font style='background-color: #8F573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SEPIA = -0x1.2d24d8p126F;
    static { NAMED.put("sepia", -0x1.2d24d8p126F); LIST.add(-0x1.2d24d8p126F); }

    /**
     * This color constant "apricot" has RGBA8888 code {@code FFA53CFF}, intensity 0.6862745, protan 0.5921569, tritan 0.72156864, alpha 1.0, hue 0.08958086, and saturation 0.76140606.
     * It can be represented as a packed float with the constant {@code -0x1.712f5ep126F}.
     * <pre>
     * <font style='background-color: #FFA53C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA53C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA53C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA53C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA53C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA53C'>&nbsp;@&nbsp;</font><font style='background-color: #FFA53C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA53C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA53C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float APRICOT = -0x1.712f5ep126F;
    static { NAMED.put("apricot", -0x1.712f5ep126F); LIST.add(-0x1.712f5ep126F); }

    /**
     * This color constant "peach" has RGBA8888 code {@code FFBF81FF}, intensity 0.78039217, protan 0.5686275, tritan 0.6392157, alpha 1.0, hue 0.082601294, and saturation 0.49658114.
     * It can be represented as a packed float with the constant {@code -0x1.47238ep126F}.
     * <pre>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #FFBF81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEACH = -0x1.47238ep126F;
    static { NAMED.put("peach", -0x1.47238ep126F); LIST.add(-0x1.47238ep126F); }

    /**
     * This color constant "pear" has RGBA8888 code {@code D3E330FF}, intensity 0.7764706, protan 0.4392157, tritan 0.7647059, alpha 1.0, hue 0.18091057, and saturation 0.7110761.
     * It can be represented as a packed float with the constant {@code -0x1.86e18cp126F}.
     * <pre>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D3E330'>&nbsp;@&nbsp;</font><font style='background-color: #D3E330; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D3E330;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D3E330; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEAR = -0x1.86e18cp126F;
    static { NAMED.put("pear", -0x1.86e18cp126F); LIST.add(-0x1.86e18cp126F); }

    /**
     * This color constant "saffron" has RGBA8888 code {@code FFD510FF}, intensity 0.76862746, protan 0.5058824, tritan 0.79607844, alpha 1.0, hue 0.13782778, and saturation 0.9391338.
     * It can be represented as a packed float with the constant {@code -0x1.970388p126F}.
     * <pre>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #FFD510; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAFFRON = -0x1.970388p126F;
    static { NAMED.put("saffron", -0x1.970388p126F); LIST.add(-0x1.970388p126F); }

    /**
     * This color constant "chartreuse" has RGBA8888 code {@code C8FF41FF}, intensity 0.8392157, protan 0.39607844, tritan 0.7647059, alpha 1.0, hue 0.21423629, and saturation 0.7483187.
     * It can be represented as a packed float with the constant {@code -0x1.86cbacp126F}.
     * <pre>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #C8FF41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHARTREUSE = -0x1.86cbacp126F;
    static { NAMED.put("chartreuse", -0x1.86cbacp126F); LIST.add(-0x1.86cbacp126F); }

    /**
     * This color constant "avocado" has RGBA8888 code {@code 6AA805FF}, intensity 0.56078434, protan 0.39607844, tritan 0.7019608, alpha 1.0, hue 0.23109773, and saturation 0.6196554.
     * It can be represented as a packed float with the constant {@code -0x1.66cb1ep126F}.
     * <pre>
     * <font style='background-color: #6AA805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AA805; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AA805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6AA805'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6AA805'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6AA805'>&nbsp;@&nbsp;</font><font style='background-color: #6AA805; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AA805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AA805; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AVOCADO = -0x1.66cb1ep126F;
    static { NAMED.put("avocado", -0x1.66cb1ep126F); LIST.add(-0x1.66cb1ep126F); }

    /**
     * This color constant "lime" has RGBA8888 code {@code 93D300FF}, intensity 0.6901961, protan 0.38431373, tritan 0.7529412, alpha 1.0, hue 0.21732643, and saturation 0.83122355.
     * It can be represented as a packed float with the constant {@code -0x1.80c56p126F}.
     * <pre>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #93D300'>&nbsp;@&nbsp;</font><font style='background-color: #93D300; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #93D300;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #93D300; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIME = -0x1.80c56p126F;
    static { NAMED.put("lime", -0x1.80c56p126F); LIST.add(-0x1.80c56p126F); }

    /**
     * This color constant "olive" has RGBA8888 code {@code 818000FF}, intensity 0.4745098, protan 0.47058824, tritan 0.68235296, alpha 1.0, hue 0.16567872, and saturation 0.5062155.
     * It can be represented as a packed float with the constant {@code -0x1.5cf0f2p126F}.
     * <pre>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #818000'>&nbsp;@&nbsp;</font><font style='background-color: #818000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #818000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #818000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE = -0x1.5cf0f2p126F;
    static { NAMED.put("olive", -0x1.5cf0f2p126F); LIST.add(-0x1.5cf0f2p126F); }

    /**
     * This color constant "fern" has RGBA8888 code {@code 4E7942FF}, intensity 0.45490196, protan 0.4392157, tritan 0.5764706, alpha 1.0, hue 0.29822132, and saturation 0.21422556.
     * It can be represented as a packed float with the constant {@code -0x1.26e0e8p126F}.
     * <pre>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4E7942'>&nbsp;@&nbsp;</font><font style='background-color: #4E7942; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4E7942;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4E7942; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FERN = -0x1.26e0e8p126F;
    static { NAMED.put("fern", -0x1.26e0e8p126F); LIST.add(-0x1.26e0e8p126F); }

    /**
     * This color constant "moss" has RGBA8888 code {@code 204608FF}, intensity 0.25882354, protan 0.4392157, tritan 0.5882353, alpha 1.0, hue 0.27203476, and saturation 0.241091.
     * It can be represented as a packed float with the constant {@code -0x1.2ce084p126F}.
     * <pre>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #204608; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MOSS = -0x1.2ce084p126F;
    static { NAMED.put("moss", -0x1.2ce084p126F); LIST.add(-0x1.2ce084p126F); }

    /**
     * This color constant "celery" has RGBA8888 code {@code 7DFF73FF}, intensity 0.83137256, protan 0.34509805, tritan 0.67058825, alpha 1.0, hue 0.32334346, and saturation 0.54658806.
     * It can be represented as a packed float with the constant {@code -0x1.56b1a8p126F}.
     * <pre>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #7DFF73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CELERY = -0x1.56b1a8p126F;
    static { NAMED.put("celery", -0x1.56b1a8p126F); LIST.add(-0x1.56b1a8p126F); }

    /**
     * This color constant "sage" has RGBA8888 code {@code ABE3C5FF}, intensity 0.8509804, protan 0.43529412, tritan 0.5254902, alpha 1.0, hue 0.40719464, and saturation 0.2204172.
     * It can be represented as a packed float with the constant {@code -0x1.0cdfb2p126F}.
     * <pre>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAGE = -0x1.0cdfb2p126F;
    static { NAMED.put("sage", -0x1.0cdfb2p126F); LIST.add(-0x1.0cdfb2p126F); }

    /**
     * This color constant "jade" has RGBA8888 code {@code 3FBF3FFF}, intensity 0.62352943, protan 0.3529412, tritan 0.6627451, alpha 1.0, hue 0.33068874, and saturation 0.50730336.
     * It can be represented as a packed float with the constant {@code -0x1.52b53ep126F}.
     * <pre>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #3FBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float JADE = -0x1.52b53ep126F;
    static { NAMED.put("jade", -0x1.52b53ep126F); LIST.add(-0x1.52b53ep126F); }

    /**
     * This color constant "cyan" has RGBA8888 code {@code 00FFFFFF}, intensity 0.9137255, protan 0.32941177, tritan 0.43137255, alpha 1.0, hue 0.5, and saturation 1.0.
     * It can be represented as a packed float with the constant {@code -0x1.dca9d2p125F}.
     * <pre>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CYAN = -0x1.dca9d2p125F;
    static { NAMED.put("cyan", -0x1.dca9d2p125F); LIST.add(-0x1.dca9d2p125F); }

    /**
     * This color constant "mint" has RGBA8888 code {@code 7FFFD4FF}, intensity 0.9019608, protan 0.37254903, tritan 0.5176471, alpha 1.0, hue 0.44493762, and saturation 0.49903446.
     * It can be represented as a packed float with the constant {@code -0x1.08bfccp126F}.
     * <pre>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7FFFD4'>&nbsp;@&nbsp;</font><font style='background-color: #7FFFD4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7FFFD4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7FFFD4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MINT = -0x1.08bfccp126F;
    static { NAMED.put("mint", -0x1.08bfccp126F); LIST.add(-0x1.08bfccp126F); }

    /**
     * This color constant "teal" has RGBA8888 code {@code 007F7FFF}, intensity 0.5019608, protan 0.40784314, tritan 0.4627451, alpha 1.0, hue 0.5000993, and saturation 0.45671126.
     * It can be represented as a packed float with the constant {@code -0x1.ecd1p125F}.
     * <pre>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #007F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TEAL = -0x1.ecd1p125F;
    static { NAMED.put("teal", -0x1.ecd1p125F); LIST.add(-0x1.ecd1p125F); }

    /**
     * This color constant "turquoise" has RGBA8888 code {@code 2ED6C9FF}, intensity 0.7764706, protan 0.36078432, tritan 0.46666667, alpha 1.0, hue 0.4864811, and saturation 0.6576992.
     * It can be represented as a packed float with the constant {@code -0x1.eeb98cp125F}.
     * <pre>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2ED6C9'>&nbsp;@&nbsp;</font><font style='background-color: #2ED6C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2ED6C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2ED6C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TURQUOISE = -0x1.eeb98cp125F;
    static { NAMED.put("turquoise", -0x1.eeb98cp125F); LIST.add(-0x1.eeb98cp125F); }

    /**
     * This color constant "sky" has RGBA8888 code {@code 86CFEBFF}, intensity 0.81960785, protan 0.42745098, tritan 0.42352942, alpha 1.0, hue 0.54665047, and saturation 0.39086533.
     * It can be represented as a packed float with the constant {@code -0x1.d8dba2p125F}.
     * <pre>
     * <font style='background-color: #86CFEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86CFEB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86CFEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #86CFEB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #86CFEB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #86CFEB'>&nbsp;@&nbsp;</font><font style='background-color: #86CFEB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #86CFEB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #86CFEB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SKY = -0x1.d8dba2p125F;
    static { NAMED.put("sky", -0x1.d8dba2p125F); LIST.add(-0x1.d8dba2p125F); }

    /**
     * This color constant "cobalt" has RGBA8888 code {@code 0046ABFF}, intensity 0.4117647, protan 0.43137255, tritan 0.3019608, alpha 1.0, hue 0.5979607, and saturation 0.6710433.
     * It can be represented as a packed float with the constant {@code -0x1.9adcd2p125F}.
     * <pre>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0046AB'>&nbsp;@&nbsp;</font><font style='background-color: #0046AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0046AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0046AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COBALT = -0x1.9adcd2p125F;
    static { NAMED.put("cobalt", -0x1.9adcd2p125F); LIST.add(-0x1.9adcd2p125F); }

    /**
     * This color constant "navy" has RGBA8888 code {@code 000080FF}, intensity 0.24313726, protan 0.43529412, tritan 0.29411766, alpha 1.0, hue 0.67328644, and saturation 0.49774668.
     * It can be represented as a packed float with the constant {@code -0x1.96de7cp125F}.
     * <pre>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000080'>&nbsp;@&nbsp;</font><font style='background-color: #000080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NAVY = -0x1.96de7cp125F;
    static { NAMED.put("navy", -0x1.96de7cp125F); LIST.add(-0x1.96de7cp125F); }

    /**
     * This color constant "lavender" has RGBA8888 code {@code B991FFFF}, intensity 0.74509805, protan 0.54901963, tritan 0.34117648, alpha 1.0, hue 0.72741735, and saturation 0.42804563.
     * It can be represented as a packed float with the constant {@code -0x1.af197cp125F}.
     * <pre>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #B991FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LAVENDER = -0x1.af197cp125F;
    static { NAMED.put("lavender", -0x1.af197cp125F); LIST.add(-0x1.af197cp125F); }

    /**
     * This color constant "plum" has RGBA8888 code {@code BE0DC6FF}, intensity 0.5294118, protan 0.7137255, tritan 0.3372549, alpha 1.0, hue 0.82619894, and saturation 0.72139674.
     * It can be represented as a packed float with the constant {@code -0x1.ad6d0ep125F}.
     * <pre>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BE0DC6'>&nbsp;@&nbsp;</font><font style='background-color: #BE0DC6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BE0DC6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BE0DC6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PLUM = -0x1.ad6d0ep125F;
    static { NAMED.put("plum", -0x1.ad6d0ep125F); LIST.add(-0x1.ad6d0ep125F); }

    /**
     * This color constant "mauve" has RGBA8888 code {@code AB73ABFF}, intensity 0.6, protan 0.5764706, tritan 0.43529412, alpha 1.0, hue 0.8289416, and saturation 0.2213426.
     * It can be represented as a packed float with the constant {@code -0x1.df2732p125F}.
     * <pre>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB73AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAUVE = -0x1.df2732p125F;
    static { NAMED.put("mauve", -0x1.df2732p125F); LIST.add(-0x1.df2732p125F); }

    /**
     * This color constant "rose" has RGBA8888 code {@code E61E78FF}, intensity 0.50980395, protan 0.7882353, tritan 0.5137255, alpha 1.0, hue 0.9242363, and saturation 0.7867001.
     * It can be represented as a packed float with the constant {@code -0x1.079304p126F}.
     * <pre>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #E61E78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROSE = -0x1.079304p126F;
    static { NAMED.put("rose", -0x1.079304p126F); LIST.add(-0x1.079304p126F); }

    /**
     * This color constant "raspberry" has RGBA8888 code {@code 911437FF}, intensity 0.3254902, protan 0.69411767, tritan 0.5529412, alpha 1.0, hue 0.9541538, and saturation 0.49511868.
     * It can be represented as a packed float with the constant {@code -0x1.1b62a6p126F}.
     * <pre>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #911437; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RASPBERRY = -0x1.1b62a6p126F;
    static { NAMED.put("raspberry", -0x1.1b62a6p126F); LIST.add(-0x1.1b62a6p126F); }
    
    /**
     * All names for colors in this palette, in alphabetical order. You can fetch the corresponding packed float color
     * by looking up a name in {@link #NAMED}.
     */
    public static final ObjectList<String> NAMES = NAMED.order();
    static {
        NAMED.setDefaultValue(TRANSPARENT);
        NAMES.sort(null);
    }
    /**
     * All names for colors in this palette, with grayscale first, then sorted by hue from red to yellow to green to
     * blue. You can fetch the corresponding packed float color by looking up a name in {@link #NAMED}.
     */
    public static final ObjectList<String> NAMES_BY_HUE = new ObjectList<>(NAMES);
    /**
     * All names for colors in this palette, sorted by lightness from black to white. You can fetch the
     * corresponding packed float color by looking up a name in {@link #NAMED}.
     */
    public static final ObjectList<String> NAMES_BY_LIGHTNESS = new ObjectList<>(NAMES);
    static {
        NAMES_BY_HUE.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                final float c1 = NAMED.get(o1), c2 = NAMED.get(o2);
                final float s1 = saturation(c1), s2 = saturation(c2);
                if(s1 <= 0x1p-6f && s2 > 0x1p-6f)
                    return -1000;
                else if(s1 > 0x1p-6f && s2 <= 0x1p-6f)
                    return 1000;
                else if(s1 <= 0x1p-6f && s2 <= 0x1p-6f)
                    return (int)Math.signum(intensity(c1) - intensity(c2));
                else
                    return 2 * (int)Math.signum(hue(c1) - hue(c2))
                            + (int)Math.signum(intensity(c1) - intensity(c2));
            }
        });
        NAMES_BY_LIGHTNESS.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Float.compare(intensity(NAMED.get(o1)), intensity(NAMED.get(o2)));
            }
        });
    }
}
