package com.github.tommyettinger.colorful.ipt;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ObjectFloatMap;

import java.util.Comparator;

import static com.github.tommyettinger.colorful.ipt.ColorTools.hue;
import static com.github.tommyettinger.colorful.ipt.ColorTools.intensity;

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
    public static final ObjectFloatMap<String> NAMED = new ObjectFloatMap<>(256);
    public static final FloatArray LIST = new FloatArray(256);

    /**
     * This color constant "Transparent" has RGBA8888 code {@code 00000000}, intensity 0.0, protan 0.49803922, tritan 0.49803922, alpha 0.0, hue 0.6411699, and saturation 0.0.
     * It can be represented as a packed float with the constant {@code 0x0.fefep-126F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TRANSPARENT = 0x0.fefep-126F;
    static { NAMED.put("Transparent", 0x0.fefep-126F); LIST.add(0x0.fefep-126F); }

    /**
     * This color constant "Black" has RGBA8888 code {@code 000000FF}, intensity 0.0, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.6411699, and saturation 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.fefep125F}.
     * <pre>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;@&nbsp;</font><font style='background-color: #000000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #000000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACK = -0x1.fefep125F;
    static { NAMED.put("Black", -0x1.fefep125F); LIST.add(-0x1.fefep125F); }

    /**
     * This color constant "Coal Black" has RGBA8888 code {@code 131313FF}, intensity 0.07450981, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.5516196, and saturation 0.0059711486.
     * It can be represented as a packed float with the constant {@code -0x1.fefe26p125F}.
     * <pre>
     * <font style='background-color: #131313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #131313; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #131313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #131313'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #131313'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #131313'>&nbsp;@&nbsp;</font><font style='background-color: #131313; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #131313;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #131313; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COAL_BLACK = -0x1.fefe26p125F;
    static { NAMED.put("Coal Black", -0x1.fefe26p125F); LIST.add(-0x1.fefe26p125F); }

    /**
     * This color constant "Shadow" has RGBA8888 code {@code 252525FF}, intensity 0.14509805, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.5508787, and saturation 0.005970478.
     * It can be represented as a packed float with the constant {@code -0x1.fefe4ap125F}.
     * <pre>
     * <font style='background-color: #252525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #252525; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #252525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #252525'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #252525'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #252525'>&nbsp;@&nbsp;</font><font style='background-color: #252525; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #252525;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #252525; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHADOW = -0x1.fefe4ap125F;
    static { NAMED.put("Shadow", -0x1.fefe4ap125F); LIST.add(-0x1.fefe4ap125F); }

    /**
     * This color constant "Graphite" has RGBA8888 code {@code 373737FF}, intensity 0.21568628, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.55013776, and saturation 0.005969748.
     * It can be represented as a packed float with the constant {@code -0x1.fefe6ep125F}.
     * <pre>
     * <font style='background-color: #373737;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #373737; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #373737;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #373737'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #373737'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #373737'>&nbsp;@&nbsp;</font><font style='background-color: #373737; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #373737;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #373737; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAPHITE = -0x1.fefe6ep125F;
    static { NAMED.put("Graphite", -0x1.fefe6ep125F); LIST.add(-0x1.fefe6ep125F); }

    /**
     * This color constant "Dark Gray" has RGBA8888 code {@code 494949FF}, intensity 0.28627452, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.549397, and saturation 0.0059690773.
     * It can be represented as a packed float with the constant {@code -0x1.fefe92p125F}.
     * <pre>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494949; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #494949'>&nbsp;@&nbsp;</font><font style='background-color: #494949; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494949;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494949; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_GRAY = -0x1.fefe92p125F;
    static { NAMED.put("Dark Gray", -0x1.fefe92p125F); LIST.add(-0x1.fefe92p125F); }

    /**
     * This color constant "Lead" has RGBA8888 code {@code 5B5B5BFF}, intensity 0.35686275, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.5486563, and saturation 0.0059682727.
     * It can be represented as a packed float with the constant {@code -0x1.fefeb6p125F}.
     * <pre>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5B5B5B'>&nbsp;@&nbsp;</font><font style='background-color: #5B5B5B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5B5B5B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5B5B5B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LEAD = -0x1.fefeb6p125F;
    static { NAMED.put("Lead", -0x1.fefeb6p125F); LIST.add(-0x1.fefeb6p125F); }

    /**
     * This color constant "Iron" has RGBA8888 code {@code 6E6E6EFF}, intensity 0.43137255, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.54787433, and saturation 0.005967498.
     * It can be represented as a packed float with the constant {@code -0x1.fefedcp125F}.
     * <pre>
     * <font style='background-color: #6E6E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E6E6E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E6E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E6E6E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E6E6E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E6E6E'>&nbsp;@&nbsp;</font><font style='background-color: #6E6E6E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E6E6E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E6E6E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float IRON = -0x1.fefedcp125F;
    static { NAMED.put("Iron", -0x1.fefedcp125F); LIST.add(-0x1.fefedcp125F); }

    /**
     * This color constant "Gray" has RGBA8888 code {@code 808080FF}, intensity 0.5019608, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.5471334, and saturation 0.0059667826.
     * It can be represented as a packed float with the constant {@code -0x1.feffp125F}.
     * <pre>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #808080'>&nbsp;@&nbsp;</font><font style='background-color: #808080; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #808080;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #808080; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY = -0x1.feffp125F;
    static { NAMED.put("Gray", -0x1.feffp125F); LIST.add(-0x1.feffp125F); }

    /**
     * This color constant "Chinchilla" has RGBA8888 code {@code 929292FF}, intensity 0.57254905, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.54639214, and saturation 0.0059661865.
     * It can be represented as a packed float with the constant {@code -0x1.feff24p125F}.
     * <pre>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #929292; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #929292'>&nbsp;@&nbsp;</font><font style='background-color: #929292; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #929292;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #929292; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHINCHILLA = -0x1.feff24p125F;
    static { NAMED.put("Chinchilla", -0x1.feff24p125F); LIST.add(-0x1.feff24p125F); }

    /**
     * This color constant "Greyhound" has RGBA8888 code {@code A4A4A4FF}, intensity 0.6431373, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.54564786, and saturation 0.005965531.
     * It can be represented as a packed float with the constant {@code -0x1.feff48p125F}.
     * <pre>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A4A4; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A4A4A4'>&nbsp;@&nbsp;</font><font style='background-color: #A4A4A4; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A4A4A4;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A4A4A4; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREYHOUND = -0x1.feff48p125F;
    static { NAMED.put("Greyhound", -0x1.feff48p125F); LIST.add(-0x1.feff48p125F); }

    /**
     * This color constant "Silver" has RGBA8888 code {@code B6B6B6FF}, intensity 0.7137255, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.54490554, and saturation 0.0059648156.
     * It can be represented as a packed float with the constant {@code -0x1.feff6cp125F}.
     * <pre>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B6B6B6'>&nbsp;@&nbsp;</font><font style='background-color: #B6B6B6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B6B6B6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B6B6B6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER = -0x1.feff6cp125F;
    static { NAMED.put("Silver", -0x1.feff6cp125F); LIST.add(-0x1.feff6cp125F); }

    /**
     * This color constant "Light Gray" has RGBA8888 code {@code C9C9C9FF}, intensity 0.7882353, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.5441231, and saturation 0.0059641004.
     * It can be represented as a packed float with the constant {@code -0x1.feff92p125F}.
     * <pre>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9C9C9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C9C9C9'>&nbsp;@&nbsp;</font><font style='background-color: #C9C9C9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C9C9C9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C9C9C9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_GRAY = -0x1.feff92p125F;
    static { NAMED.put("Light Gray", -0x1.feff92p125F); LIST.add(-0x1.feff92p125F); }

    /**
     * This color constant "Platinum" has RGBA8888 code {@code DBDBDBFF}, intensity 0.85882354, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.54338175, and saturation 0.0059632063.
     * It can be represented as a packed float with the constant {@code -0x1.feffb6p125F}.
     * <pre>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDBDB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DBDBDB'>&nbsp;@&nbsp;</font><font style='background-color: #DBDBDB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DBDBDB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DBDBDB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PLATINUM = -0x1.feffb6p125F;
    static { NAMED.put("Platinum", -0x1.feffb6p125F); LIST.add(-0x1.feffb6p125F); }

    /**
     * This color constant "Cloud" has RGBA8888 code {@code EDEDEDFF}, intensity 0.92941177, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.5426351, and saturation 0.0059625506.
     * It can be represented as a packed float with the constant {@code -0x1.feffdap125F}.
     * <pre>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDED; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDEDED'>&nbsp;@&nbsp;</font><font style='background-color: #EDEDED; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDED;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDED; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CLOUD = -0x1.feffdap125F;
    static { NAMED.put("Cloud", -0x1.feffdap125F); LIST.add(-0x1.feffdap125F); }

    /**
     * This color constant "White" has RGBA8888 code {@code FFFFFFFF}, intensity 1.0, protan 0.49803922, tritan 0.49803922, alpha 1.0, hue 0.5, and saturation 0.0.
     * It can be represented as a packed float with the constant {@code -0x1.fefffep125F}.
     * <pre>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WHITE = -0x1.fefffep125F;
    static { NAMED.put("White", -0x1.fefffep125F); LIST.add(-0x1.fefffep125F); }

    /**
     * This color constant "Seawater" has RGBA8888 code {@code 007F7FFF}, intensity 0.40392157, protan 0.33333334, tritan 0.42745098, alpha 1.0, hue 0.5067261, and saturation 0.34743565.
     * It can be represented as a packed float with the constant {@code -0x1.daaacep125F}.
     * <pre>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F7F'>&nbsp;@&nbsp;</font><font style='background-color: #007F7F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F7F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F7F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SEAWATER = -0x1.daaacep125F;
    static { NAMED.put("Seawater", -0x1.daaacep125F); LIST.add(-0x1.daaacep125F); }

    /**
     * This color constant "Hospital Green" has RGBA8888 code {@code 3FBFBFFF}, intensity 0.654902, protan 0.32941177, tritan 0.42745098, alpha 1.0, hue 0.50549203, and saturation 0.35270333.
     * It can be represented as a packed float with the constant {@code -0x1.daa94ep125F}.
     * <pre>
     * <font style='background-color: #3FBFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBFBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBFBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBFBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBFBF'>&nbsp;@&nbsp;</font><font style='background-color: #3FBFBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBFBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HOSPITAL_GREEN = -0x1.daa94ep125F;
    static { NAMED.put("Hospital Green", -0x1.daa94ep125F); LIST.add(-0x1.daa94ep125F); }

    /**
     * This color constant "Cyan" has RGBA8888 code {@code 00FFFFFF}, intensity 0.8117647, protan 0.16470589, tritan 0.3529412, alpha 1.0, hue 0.507115, and saturation 0.70084304.
     * It can be represented as a packed float with the constant {@code -0x1.b4559ep125F}.
     * <pre>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00FFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CYAN = -0x1.b4559ep125F;
    static { NAMED.put("Cyan", -0x1.b4559ep125F); LIST.add(-0x1.b4559ep125F); }

    /**
     * This color constant "Bubble" has RGBA8888 code {@code BFFFFFFF}, intensity 0.9529412, protan 0.41568628, tritan 0.4627451, alpha 1.0, hue 0.5072297, and saturation 0.1766957.
     * It can be represented as a packed float with the constant {@code -0x1.ecd5e6p125F}.
     * <pre>
     * <font style='background-color: #BFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFFFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFFFFF'>&nbsp;@&nbsp;</font><font style='background-color: #BFFFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFFFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFFFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BUBBLE = -0x1.ecd5e6p125F;
    static { NAMED.put("Bubble", -0x1.ecd5e6p125F); LIST.add(-0x1.ecd5e6p125F); }

    /**
     * This color constant "Periwinkle" has RGBA8888 code {@code 8181FFFF}, intensity 0.61960787, protan 0.5137255, tritan 0.26666668, alpha 1.0, hue 0.6621431, and saturation 0.37863326.
     * It can be represented as a packed float with the constant {@code -0x1.89073cp125F}.
     * <pre>
     * <font style='background-color: #8181FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8181FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8181FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8181FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8181FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8181FF'>&nbsp;@&nbsp;</font><font style='background-color: #8181FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8181FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8181FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PERIWINKLE = -0x1.89073cp125F;
    static { NAMED.put("Periwinkle", -0x1.89073cp125F); LIST.add(-0x1.89073cp125F); }

    /**
     * This color constant "Blue" has RGBA8888 code {@code 0000FFFF}, intensity 0.23137255, protan 0.53333336, tritan 0.02745098, alpha 1.0, hue 0.66452825, and saturation 0.7593833.
     * It can be represented as a packed float with the constant {@code -0x1.0f1076p125F}.
     * <pre>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0000FF'>&nbsp;@&nbsp;</font><font style='background-color: #0000FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0000FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0000FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE = -0x1.0f1076p125F;
    static { NAMED.put("Blue", -0x1.0f1076p125F); LIST.add(-0x1.0f1076p125F); }

    /**
     * This color constant "Faded Blue" has RGBA8888 code {@code 3F3FBFFF}, intensity 0.3647059, protan 0.5137255, tritan 0.2627451, alpha 1.0, hue 0.6619961, and saturation 0.38531005.
     * It can be represented as a packed float with the constant {@code -0x1.8706bap125F}.
     * <pre>
     * <font style='background-color: #3F3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F3FBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3F3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3F3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3F3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #3F3FBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3F3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3F3FBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FADED_BLUE = -0x1.8706bap125F;
    static { NAMED.put("Faded Blue", -0x1.8706bap125F); LIST.add(-0x1.8706bap125F); }

    /**
     * This color constant "Ocean Blue" has RGBA8888 code {@code 00007FFF}, intensity 0.11764706, protan 0.5137255, tritan 0.2627451, alpha 1.0, hue 0.6620356, and saturation 0.3853125.
     * It can be represented as a packed float with the constant {@code -0x1.87063cp125F}.
     * <pre>
     * <font style='background-color: #00007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00007F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00007F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00007F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00007F'>&nbsp;@&nbsp;</font><font style='background-color: #00007F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00007F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OCEAN_BLUE = -0x1.87063cp125F;
    static { NAMED.put("Ocean Blue", -0x1.87063cp125F); LIST.add(-0x1.87063cp125F); }

    /**
     * This color constant "Stygian Blue" has RGBA8888 code {@code 0F0F50FF}, intensity 0.11764706, protan 0.5058824, tritan 0.3764706, alpha 1.0, hue 0.6600462, and saturation 0.2023153.
     * It can be represented as a packed float with the constant {@code -0x1.c1023cp125F}.
     * <pre>
     * <font style='background-color: #0F0F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F0F50; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F0F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F0F50'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F0F50'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F0F50'>&nbsp;@&nbsp;</font><font style='background-color: #0F0F50; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F0F50;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F0F50; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STYGIAN_BLUE = -0x1.c1023cp125F;
    static { NAMED.put("Stygian Blue", -0x1.c1023cp125F); LIST.add(-0x1.c1023cp125F); }

    /**
     * This color constant "Deep Purple" has RGBA8888 code {@code 7F007FFF}, intensity 0.21176471, protan 0.68235296, tritan 0.33333334, alpha 1.0, hue 0.81569934, and saturation 0.36457756.
     * It can be represented as a packed float with the constant {@code -0x1.ab5c6cp125F}.
     * <pre>
     * <font style='background-color: #7F007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F007F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F007F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F007F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F007F'>&nbsp;@&nbsp;</font><font style='background-color: #7F007F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F007F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F007F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_PURPLE = -0x1.ab5c6cp125F;
    static { NAMED.put("Deep Purple", -0x1.ab5c6cp125F); LIST.add(-0x1.ab5c6cp125F); }

    /**
     * This color constant "Tyrian Purple" has RGBA8888 code {@code BF3FBFFF}, intensity 0.45882353, protan 0.68235296, tritan 0.33333334, alpha 1.0, hue 0.815696, and saturation 0.3644838.
     * It can be represented as a packed float with the constant {@code -0x1.ab5ceap125F}.
     * <pre>
     * <font style='background-color: #BF3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF3FBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF3FBF'>&nbsp;@&nbsp;</font><font style='background-color: #BF3FBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF3FBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF3FBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TYRIAN_PURPLE = -0x1.ab5ceap125F;
    static { NAMED.put("Tyrian Purple", -0x1.ab5ceap125F); LIST.add(-0x1.ab5ceap125F); }

    /**
     * This color constant "Magenta" has RGBA8888 code {@code F500F5FF}, intensity 0.40784314, protan 0.8509804, tritan 0.18431373, alpha 1.0, hue 0.81758475, and saturation 0.6938301.
     * It can be represented as a packed float with the constant {@code -0x1.5fb2dp125F}.
     * <pre>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F500F5'>&nbsp;@&nbsp;</font><font style='background-color: #F500F5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F500F5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F500F5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAGENTA = -0x1.5fb2dp125F;
    static { NAMED.put("Magenta", -0x1.5fb2dp125F); LIST.add(-0x1.5fb2dp125F); }

    /**
     * This color constant "Bubblegum Pink" has RGBA8888 code {@code FD81FFFF}, intensity 0.7137255, protan 0.6784314, tritan 0.33333334, alpha 1.0, hue 0.81315446, and saturation 0.36208332.
     * It can be represented as a packed float with the constant {@code -0x1.ab5b6cp125F}.
     * <pre>
     * <font style='background-color: #FD81FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD81FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD81FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FD81FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FD81FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FD81FF'>&nbsp;@&nbsp;</font><font style='background-color: #FD81FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FD81FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FD81FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BUBBLEGUM_PINK = -0x1.ab5b6cp125F;
    static { NAMED.put("Bubblegum Pink", -0x1.ab5b6cp125F); LIST.add(-0x1.ab5b6cp125F); }

    /**
     * This color constant "Pork Chop" has RGBA8888 code {@code FFC0CBFF}, intensity 0.8117647, protan 0.58431375, tritan 0.5137255, alpha 1.0, hue 0.9716423, and saturation 0.16469383.
     * It can be represented as a packed float with the constant {@code -0x1.072b9ep126F}.
     * <pre>
     * <font style='background-color: #FFC0CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC0CB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC0CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFC0CB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFC0CB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFC0CB'>&nbsp;@&nbsp;</font><font style='background-color: #FFC0CB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFC0CB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFC0CB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PORK_CHOP = -0x1.072b9ep126F;
    static { NAMED.put("Pork Chop", -0x1.072b9ep126F); LIST.add(-0x1.072b9ep126F); }

    /**
     * This color constant "Raw Meat" has RGBA8888 code {@code FF8181FF}, intensity 0.6, protan 0.6627451, tritan 0.5686275, alpha 1.0, hue 0.005288946, and saturation 0.33550167.
     * It can be represented as a packed float with the constant {@code -0x1.235332p126F}.
     * <pre>
     * <font style='background-color: #FF8181;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8181; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8181;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF8181'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF8181'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF8181'>&nbsp;@&nbsp;</font><font style='background-color: #FF8181; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF8181;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF8181; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RAW_MEAT = -0x1.235332p126F;
    static { NAMED.put("Raw Meat", -0x1.235332p126F); LIST.add(-0x1.235332p126F); }

    /**
     * This color constant "Fresh Blood" has RGBA8888 code {@code FF0000FF}, intensity 0.1882353, protan 0.83137256, tritan 0.6431373, alpha 1.0, hue 0.0064214063, and saturation 0.6889092.
     * It can be represented as a packed float with the constant {@code -0x1.49a86p126F}.
     * <pre>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF0000'>&nbsp;@&nbsp;</font><font style='background-color: #FF0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FRESH_BLOOD = -0x1.49a86p126F;
    static { NAMED.put("Fresh Blood", -0x1.49a86p126F); LIST.add(-0x1.49a86p126F); }

    /**
     * This color constant "Putty" has RGBA8888 code {@code BF3F3FFF}, intensity 0.34117648, protan 0.6666667, tritan 0.5686275, alpha 1.0, hue 0.004032275, and saturation 0.34076938.
     * It can be represented as a packed float with the constant {@code -0x1.2354aep126F}.
     * <pre>
     * <font style='background-color: #BF3F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF3F3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF3F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF3F3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF3F3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF3F3F'>&nbsp;@&nbsp;</font><font style='background-color: #BF3F3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF3F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF3F3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PUTTY = -0x1.2354aep126F;
    static { NAMED.put("Putty", -0x1.2354aep126F); LIST.add(-0x1.2354aep126F); }

    /**
     * This color constant "Sienna" has RGBA8888 code {@code 7F0000FF}, intensity 0.09411765, protan 0.6666667, tritan 0.5686275, alpha 1.0, hue 0.0039865244, and saturation 0.34076682.
     * It can be represented as a packed float with the constant {@code -0x1.23543p126F}.
     * <pre>
     * <font style='background-color: #7F0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F0000; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F0000'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F0000'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F0000'>&nbsp;@&nbsp;</font><font style='background-color: #7F0000; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F0000;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F0000; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SIENNA = -0x1.23543p126F;
    static { NAMED.put("Sienna", -0x1.23543p126F); LIST.add(-0x1.23543p126F); }

    /**
     * This color constant "Seal Brown" has RGBA8888 code {@code 551414FF}, intensity 0.1254902, protan 0.58431375, tritan 0.53333336, alpha 1.0, hue 0.0019744337, and saturation 0.17003328.
     * It can be represented as a packed float with the constant {@code -0x1.112a4p126F}.
     * <pre>
     * <font style='background-color: #551414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #551414; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #551414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #551414'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #551414'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #551414'>&nbsp;@&nbsp;</font><font style='background-color: #551414; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #551414;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #551414; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SEAL_BROWN = -0x1.112a4p126F;
    static { NAMED.put("Seal Brown", -0x1.112a4p126F); LIST.add(-0x1.112a4p126F); }

    /**
     * This color constant "Mummy Brown" has RGBA8888 code {@code 7F3F00FF}, intensity 0.23529412, protan 0.57254905, tritan 0.6509804, alpha 1.0, hue 0.089695, and saturation 0.35443527.
     * It can be represented as a packed float with the constant {@code -0x1.4d2478p126F}.
     * <pre>
     * <font style='background-color: #7F3F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F3F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F3F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F3F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F3F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F3F00'>&nbsp;@&nbsp;</font><font style='background-color: #7F3F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F3F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F3F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MUMMY_BROWN = -0x1.4d2478p126F;
    static { NAMED.put("Mummy Brown", -0x1.4d2478p126F); LIST.add(-0x1.4d2478p126F); }

    /**
     * This color constant "Fawn" has RGBA8888 code {@code BF7F3FFF}, intensity 0.4862745, protan 0.57254905, tritan 0.6509804, alpha 1.0, hue 0.08973911, and saturation 0.35443792.
     * It can be represented as a packed float with the constant {@code -0x1.4d24f8p126F}.
     * <pre>
     * <font style='background-color: #BF7F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF7F3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF7F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BF7F3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BF7F3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BF7F3F'>&nbsp;@&nbsp;</font><font style='background-color: #BF7F3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BF7F3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BF7F3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FAWN = -0x1.4d24f8p126F;
    static { NAMED.put("Fawn", -0x1.4d24f8p126F); LIST.add(-0x1.4d24f8p126F); }

    /**
     * This color constant "Orange" has RGBA8888 code {@code FF7F00FF}, intensity 0.47843137, protan 0.6509804, tritan 0.8039216, alpha 1.0, hue 0.0881967, and saturation 0.7201127.
     * It can be represented as a packed float with the constant {@code -0x1.9b4cf4p126F}.
     * <pre>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF7F00'>&nbsp;@&nbsp;</font><font style='background-color: #FF7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORANGE = -0x1.9b4cf4p126F;
    static { NAMED.put("Orange", -0x1.9b4cf4p126F); LIST.add(-0x1.9b4cf4p126F); }

    /**
     * This color constant "Peach" has RGBA8888 code {@code FFBF81FF}, intensity 0.7411765, protan 0.57254905, tritan 0.64705884, alpha 1.0, hue 0.08860295, and saturation 0.3477667.
     * It can be represented as a packed float with the constant {@code -0x1.4b257ap126F}.
     * <pre>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFBF81'>&nbsp;@&nbsp;</font><font style='background-color: #FFBF81; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFBF81;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFBF81; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEACH = -0x1.4b257ap126F;
    static { NAMED.put("Peach", -0x1.4b257ap126F); LIST.add(-0x1.4b257ap126F); }

    /**
     * This color constant "Cream" has RGBA8888 code {@code FFFFBFFF}, intensity 0.9411765, protan 0.49019608, tritan 0.6156863, alpha 1.0, hue 0.16771047, and saturation 0.1848656.
     * It can be represented as a packed float with the constant {@code -0x1.3afbep126F}.
     * <pre>
     * <font style='background-color: #FFFFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFBF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFFBF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFFBF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFFBF'>&nbsp;@&nbsp;</font><font style='background-color: #FFFFBF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFFBF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFFBF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CREAM = -0x1.3afbep126F;
    static { NAMED.put("Cream", -0x1.3afbep126F); LIST.add(-0x1.3afbep126F); }

    /**
     * This color constant "Yellow" has RGBA8888 code {@code FFFF00FF}, intensity 0.76862746, protan 0.4627451, tritan 0.96862745, alpha 1.0, hue 0.16640237, and saturation 0.747449.
     * It can be represented as a packed float with the constant {@code -0x1.eeed88p126F}.
     * <pre>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFFF00'>&nbsp;@&nbsp;</font><font style='background-color: #FFFF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFFF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFFF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float YELLOW = -0x1.eeed88p126F;
    static { NAMED.put("Yellow", -0x1.eeed88p126F); LIST.add(-0x1.eeed88p126F); }

    /**
     * This color constant "Earwax" has RGBA8888 code {@code BFBF3FFF}, intensity 0.6313726, protan 0.48235294, tritan 0.73333335, alpha 1.0, hue 0.16566627, and saturation 0.3733759.
     * It can be represented as a packed float with the constant {@code -0x1.76f742p126F}.
     * <pre>
     * <font style='background-color: #BFBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BFBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BFBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BFBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #BFBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BFBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BFBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EARWAX = -0x1.76f742p126F;
    static { NAMED.put("Earwax", -0x1.76f742p126F); LIST.add(-0x1.76f742p126F); }

    /**
     * This color constant "Umber" has RGBA8888 code {@code 7F7F00FF}, intensity 0.38039216, protan 0.48235294, tritan 0.73333335, alpha 1.0, hue 0.16562468, and saturation 0.37337375.
     * It can be represented as a packed float with the constant {@code -0x1.76f6c2p126F}.
     * <pre>
     * <font style='background-color: #7F7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F7F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F7F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F7F00'>&nbsp;@&nbsp;</font><font style='background-color: #7F7F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F7F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F7F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float UMBER = -0x1.76f6c2p126F;
    static { NAMED.put("Umber", -0x1.76f6c2p126F); LIST.add(-0x1.76f6c2p126F); }

    /**
     * This color constant "Ivy Green" has RGBA8888 code {@code 007F00FF}, intensity 0.28627452, protan 0.3137255, tritan 0.6627451, alpha 1.0, hue 0.32103685, and saturation 0.36101106.
     * It can be represented as a packed float with the constant {@code -0x1.52a092p126F}.
     * <pre>
     * <font style='background-color: #007F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007F00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007F00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007F00'>&nbsp;@&nbsp;</font><font style='background-color: #007F00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007F00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007F00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float IVY_GREEN = -0x1.52a092p126F;
    static { NAMED.put("Ivy Green", -0x1.52a092p126F); LIST.add(-0x1.52a092p126F); }

    /**
     * This color constant "Jade" has RGBA8888 code {@code 3FBF3FFF}, intensity 0.5372549, protan 0.3137255, tritan 0.6627451, alpha 1.0, hue 0.32103896, and saturation 0.36110628.
     * It can be represented as a packed float with the constant {@code -0x1.52a112p126F}.
     * <pre>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3FBF3F'>&nbsp;@&nbsp;</font><font style='background-color: #3FBF3F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3FBF3F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3FBF3F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float JADE = -0x1.52a112p126F;
    static { NAMED.put("Jade", -0x1.52a112p126F); LIST.add(-0x1.52a112p126F); }

    /**
     * This color constant "Green" has RGBA8888 code {@code 00FF00FF}, intensity 0.5764706, protan 0.12941177, tritan 0.827451, alpha 1.0, hue 0.3196938, and saturation 0.7239012.
     * It can be represented as a packed float with the constant {@code -0x1.a64326p126F}.
     * <pre>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00FF00'>&nbsp;@&nbsp;</font><font style='background-color: #00FF00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00FF00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00FF00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GREEN = -0x1.a64326p126F;
    static { NAMED.put("Green", -0x1.a64326p126F); LIST.add(-0x1.a64326p126F); }

    /**
     * This color constant "Celadon" has RGBA8888 code {@code AFFFAFFF}, intensity 0.8666667, protan 0.38431373, tritan 0.6, alpha 1.0, hue 0.3223212, and saturation 0.22280985.
     * It can be represented as a packed float with the constant {@code -0x1.32c5bap126F}.
     * <pre>
     * <font style='background-color: #AFFFAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFFFAF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFFFAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AFFFAF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AFFFAF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AFFFAF'>&nbsp;@&nbsp;</font><font style='background-color: #AFFFAF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AFFFAF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AFFFAF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CELADON = -0x1.32c5bap126F;
    static { NAMED.put("Celadon", -0x1.32c5bap126F); LIST.add(-0x1.32c5bap126F); }

    /**
     * This color constant "Puce" has RGBA8888 code {@code BCAFC0FF}, intensity 0.70980394, protan 0.5176471, tritan 0.4745098, alpha 1.0, hue 0.7671268, and saturation 0.04948449.
     * It can be represented as a packed float with the constant {@code -0x1.f3096ap125F}.
     * <pre>
     * <font style='background-color: #BCAFC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCAFC0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCAFC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BCAFC0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BCAFC0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BCAFC0'>&nbsp;@&nbsp;</font><font style='background-color: #BCAFC0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BCAFC0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BCAFC0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PUCE = -0x1.f3096ap125F;
    static { NAMED.put("Puce", -0x1.f3096ap125F); LIST.add(-0x1.f3096ap125F); }

    /**
     * This color constant "Beige" has RGBA8888 code {@code CBAA89FF}, intensity 0.6627451, protan 0.5372549, tritan 0.5764706, alpha 1.0, hue 0.089265324, and saturation 0.18020916.
     * It can be represented as a packed float with the constant {@code -0x1.271352p126F}.
     * <pre>
     * <font style='background-color: #CBAA89;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBAA89; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBAA89;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #CBAA89'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #CBAA89'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #CBAA89'>&nbsp;@&nbsp;</font><font style='background-color: #CBAA89; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #CBAA89;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #CBAA89; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BEIGE = -0x1.271352p126F;
    static { NAMED.put("Beige", -0x1.271352p126F); LIST.add(-0x1.271352p126F); }

    /**
     * This color constant "Wet Stone" has RGBA8888 code {@code A6A090FF}, intensity 0.6156863, protan 0.5058824, tritan 0.5294118, alpha 1.0, hue 0.121402934, and saturation 0.05796379.
     * It can be represented as a packed float with the constant {@code -0x1.0f033ap126F}.
     * <pre>
     * <font style='background-color: #A6A090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6A090; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6A090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A6A090'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A6A090'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A6A090'>&nbsp;@&nbsp;</font><font style='background-color: #A6A090; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A6A090;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A6A090; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WET_STONE = -0x1.0f033ap126F;
    static { NAMED.put("Wet Stone", -0x1.0f033ap126F); LIST.add(-0x1.0f033ap126F); }

    /**
     * This color constant "Slow Creek" has RGBA8888 code {@code 7E9494FF}, intensity 0.5647059, protan 0.47058824, tritan 0.4862745, alpha 1.0, hue 0.50984204, and saturation 0.062877715.
     * It can be represented as a packed float with the constant {@code -0x1.f8f12p125F}.
     * <pre>
     * <font style='background-color: #7E9494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E9494; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E9494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E9494'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E9494'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E9494'>&nbsp;@&nbsp;</font><font style='background-color: #7E9494; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E9494;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E9494; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SLOW_CREEK = -0x1.f8f12p125F;
    static { NAMED.put("Slow Creek", -0x1.f8f12p125F); LIST.add(-0x1.f8f12p125F); }

    /**
     * This color constant "Slate Gray" has RGBA8888 code {@code 6E8287FF}, intensity 0.49803922, protan 0.4745098, tritan 0.47843137, alpha 1.0, hue 0.54265803, and saturation 0.070955575.
     * It can be represented as a packed float with the constant {@code -0x1.f4f2fep125F}.
     * <pre>
     * <font style='background-color: #6E8287;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E8287; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E8287;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6E8287'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6E8287'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6E8287'>&nbsp;@&nbsp;</font><font style='background-color: #6E8287; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6E8287;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6E8287; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SLATE_GRAY = -0x1.f4f2fep125F;
    static { NAMED.put("Slate Gray", -0x1.f4f2fep125F); LIST.add(-0x1.f4f2fep125F); }

    /**
     * This color constant "Light Skin 1" has RGBA8888 code {@code 7E6E60FF}, intensity 0.43137255, protan 0.5176471, tritan 0.53333336, alpha 1.0, hue 0.08556285, and saturation 0.080446005.
     * It can be represented as a packed float with the constant {@code -0x1.1108dcp126F}.
     * <pre>
     * <font style='background-color: #7E6E60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E6E60; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E6E60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7E6E60'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7E6E60'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7E6E60'>&nbsp;@&nbsp;</font><font style='background-color: #7E6E60; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7E6E60;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7E6E60; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_1 = -0x1.1108dcp126F;
    static { NAMED.put("Light Skin 1", -0x1.1108dcp126F); LIST.add(-0x1.1108dcp126F); }

    /**
     * This color constant "Light Skin 2" has RGBA8888 code {@code A0695FFF}, intensity 0.44313726, protan 0.5686275, tritan 0.54901963, alpha 1.0, hue 0.033767797, and saturation 0.17565101.
     * It can be represented as a packed float with the constant {@code -0x1.1922e2p126F}.
     * <pre>
     * <font style='background-color: #A0695F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0695F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0695F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A0695F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A0695F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A0695F'>&nbsp;@&nbsp;</font><font style='background-color: #A0695F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A0695F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A0695F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_2 = -0x1.1922e2p126F;
    static { NAMED.put("Light Skin 2", -0x1.1922e2p126F); LIST.add(-0x1.1922e2p126F); }

    /**
     * This color constant "Light Skin 3" has RGBA8888 code {@code C07872FF}, intensity 0.5176471, protan 0.5921569, tritan 0.54901963, alpha 1.0, hue 0.017522682, and saturation 0.20727247.
     * It can be represented as a packed float with the constant {@code -0x1.192f08p126F}.
     * <pre>
     * <font style='background-color: #C07872;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C07872; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C07872;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C07872'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C07872'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C07872'>&nbsp;@&nbsp;</font><font style='background-color: #C07872; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C07872;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C07872; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_3 = -0x1.192f08p126F;
    static { NAMED.put("Light Skin 3", -0x1.192f08p126F); LIST.add(-0x1.192f08p126F); }

    /**
     * This color constant "Light Skin 4" has RGBA8888 code {@code D08A74FF}, intensity 0.57254905, protan 0.5882353, tritan 0.5764706, alpha 1.0, hue 0.044583127, and saturation 0.24871808.
     * It can be represented as a packed float with the constant {@code -0x1.272d24p126F}.
     * <pre>
     * <font style='background-color: #D08A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D08A74; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D08A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D08A74'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D08A74'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D08A74'>&nbsp;@&nbsp;</font><font style='background-color: #D08A74; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D08A74;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D08A74; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_4 = -0x1.272d24p126F;
    static { NAMED.put("Light Skin 4", -0x1.272d24p126F); LIST.add(-0x1.272d24p126F); }

    /**
     * This color constant "Light Skin 5" has RGBA8888 code {@code E19B7DFF}, intensity 0.6313726, protan 0.58431375, tritan 0.5921569, alpha 1.0, hue 0.057435546, and saturation 0.27014393.
     * It can be represented as a packed float with the constant {@code -0x1.2f2b42p126F}.
     * <pre>
     * <font style='background-color: #E19B7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E19B7D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E19B7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E19B7D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E19B7D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E19B7D'>&nbsp;@&nbsp;</font><font style='background-color: #E19B7D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E19B7D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E19B7D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_5 = -0x1.2f2b42p126F;
    static { NAMED.put("Light Skin 5", -0x1.2f2b42p126F); LIST.add(-0x1.2f2b42p126F); }

    /**
     * This color constant "Light Skin 6" has RGBA8888 code {@code EBAA8CFF}, intensity 0.6862745, protan 0.5803922, tritan 0.5882353, alpha 1.0, hue 0.05768174, and saturation 0.25820082.
     * It can be represented as a packed float with the constant {@code -0x1.2d295ep126F}.
     * <pre>
     * <font style='background-color: #EBAA8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBAA8C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBAA8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBAA8C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBAA8C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBAA8C'>&nbsp;@&nbsp;</font><font style='background-color: #EBAA8C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBAA8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBAA8C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_6 = -0x1.2d295ep126F;
    static { NAMED.put("Light Skin 6", -0x1.2d295ep126F); LIST.add(-0x1.2d295ep126F); }

    /**
     * This color constant "Light Skin 7" has RGBA8888 code {@code F5B99BFF}, intensity 0.7411765, protan 0.57254905, tritan 0.5882353, alpha 1.0, hue 0.06325165, and saturation 0.24766082.
     * It can be represented as a packed float with the constant {@code -0x1.2d257ap126F}.
     * <pre>
     * <font style='background-color: #F5B99B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5B99B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5B99B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5B99B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5B99B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5B99B'>&nbsp;@&nbsp;</font><font style='background-color: #F5B99B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5B99B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5B99B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_7 = -0x1.2d257ap126F;
    static { NAMED.put("Light Skin 7", -0x1.2d257ap126F); LIST.add(-0x1.2d257ap126F); }

    /**
     * This color constant "Light Skin 8" has RGBA8888 code {@code F6C8AFFF}, intensity 0.79607844, protan 0.5568628, tritan 0.5686275, alpha 1.0, hue 0.06291001, and saturation 0.19321322.
     * It can be represented as a packed float with the constant {@code -0x1.231d96p126F}.
     * <pre>
     * <font style='background-color: #F6C8AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6C8AF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6C8AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6C8AF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6C8AF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6C8AF'>&nbsp;@&nbsp;</font><font style='background-color: #F6C8AF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6C8AF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6C8AF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_8 = -0x1.231d96p126F;
    static { NAMED.put("Light Skin 8", -0x1.231d96p126F); LIST.add(-0x1.231d96p126F); }

    /**
     * This color constant "Light Skin 9" has RGBA8888 code {@code F5E1D2FF}, intensity 0.88235295, protan 0.52156866, tritan 0.5372549, alpha 1.0, hue 0.081579976, and saturation 0.09239429.
     * It can be represented as a packed float with the constant {@code -0x1.130bc2p126F}.
     * <pre>
     * <font style='background-color: #F5E1D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E1D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E1D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F5E1D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F5E1D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F5E1D2'>&nbsp;@&nbsp;</font><font style='background-color: #F5E1D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F5E1D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F5E1D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIGHT_SKIN_9 = -0x1.130bc2p126F;
    static { NAMED.put("Light Skin 9", -0x1.130bc2p126F); LIST.add(-0x1.130bc2p126F); }

    /**
     * This color constant "Dark Skin 1" has RGBA8888 code {@code 573B3BFF}, intensity 0.2509804, protan 0.53333336, tritan 0.5137255, alpha 1.0, hue 0.0042016, and saturation 0.06815572.
     * It can be represented as a packed float with the constant {@code -0x1.07108p126F}.
     * <pre>
     * <font style='background-color: #573B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573B3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #573B3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #573B3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #573B3B'>&nbsp;@&nbsp;</font><font style='background-color: #573B3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573B3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573B3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_SKIN_1 = -0x1.07108p126F;
    static { NAMED.put("Dark Skin 1", -0x1.07108p126F); LIST.add(-0x1.07108p126F); }

    /**
     * This color constant "Dark Skin 2" has RGBA8888 code {@code 73413CFF}, intensity 0.28627452, protan 0.5647059, tritan 0.5372549, alpha 1.0, hue 0.021786617, and saturation 0.15035844.
     * It can be represented as a packed float with the constant {@code -0x1.132092p126F}.
     * <pre>
     * <font style='background-color: #73413C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73413C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73413C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73413C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73413C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73413C'>&nbsp;@&nbsp;</font><font style='background-color: #73413C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73413C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73413C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_SKIN_2 = -0x1.132092p126F;
    static { NAMED.put("Dark Skin 2", -0x1.132092p126F); LIST.add(-0x1.132092p126F); }

    /**
     * This color constant "Dark Skin 3" has RGBA8888 code {@code 8E5555FF}, intensity 0.3764706, protan 0.57254905, tritan 0.5294118, alpha 1.0, hue 0.0033460404, and saturation 0.14755207.
     * It can be represented as a packed float with the constant {@code -0x1.0f24cp126F}.
     * <pre>
     * <font style='background-color: #8E5555;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E5555; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E5555;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8E5555'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8E5555'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8E5555'>&nbsp;@&nbsp;</font><font style='background-color: #8E5555; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8E5555;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8E5555; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_SKIN_3 = -0x1.0f24cp126F;
    static { NAMED.put("Dark Skin 3", -0x1.0f24cp126F); LIST.add(-0x1.0f24cp126F); }

    /**
     * This color constant "Pink Skin 1" has RGBA8888 code {@code AB7373FF}, intensity 0.49411765, protan 0.57254905, tritan 0.5294118, alpha 1.0, hue 0.00339641, and saturation 0.14755312.
     * It can be represented as a packed float with the constant {@code -0x1.0f24fcp126F}.
     * <pre>
     * <font style='background-color: #AB7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB7373; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB7373'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB7373'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB7373'>&nbsp;@&nbsp;</font><font style='background-color: #AB7373; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB7373; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_SKIN_1 = -0x1.0f24fcp126F;
    static { NAMED.put("Pink Skin 1", -0x1.0f24fcp126F); LIST.add(-0x1.0f24fcp126F); }

    /**
     * This color constant "Pink Skin 2" has RGBA8888 code {@code C78F8FFF}, intensity 0.6039216, protan 0.57254905, tritan 0.5294118, alpha 1.0, hue 0.0034433454, and saturation 0.14755416.
     * It can be represented as a packed float with the constant {@code -0x1.0f2534p126F}.
     * <pre>
     * <font style='background-color: #C78F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78F8F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C78F8F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C78F8F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C78F8F'>&nbsp;@&nbsp;</font><font style='background-color: #C78F8F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78F8F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78F8F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_SKIN_2 = -0x1.0f2534p126F;
    static { NAMED.put("Pink Skin 2", -0x1.0f2534p126F); LIST.add(-0x1.0f2534p126F); }

    /**
     * This color constant "Pink Skin 3" has RGBA8888 code {@code E3ABABFF}, intensity 0.7137255, protan 0.57254905, tritan 0.5294118, alpha 1.0, hue 0.003490442, and saturation 0.14755547.
     * It can be represented as a packed float with the constant {@code -0x1.0f256cp126F}.
     * <pre>
     * <font style='background-color: #E3ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3ABAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #E3ABAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3ABAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_SKIN_3 = -0x1.0f256cp126F;
    static { NAMED.put("Pink Skin 3", -0x1.0f256cp126F); LIST.add(-0x1.0f256cp126F); }

    /**
     * This color constant "Pink Skin 4" has RGBA8888 code {@code F8D2DAFF}, intensity 0.85882354, protan 0.54901963, tritan 0.5058824, alpha 1.0, hue 0.9660893, and saturation 0.09528059.
     * It can be represented as a packed float with the constant {@code -0x1.0319b6p126F}.
     * <pre>
     * <font style='background-color: #F8D2DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8D2DA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8D2DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8D2DA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8D2DA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8D2DA'>&nbsp;@&nbsp;</font><font style='background-color: #F8D2DA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8D2DA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8D2DA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_SKIN_4 = -0x1.0319b6p126F;
    static { NAMED.put("Pink Skin 4", -0x1.0319b6p126F); LIST.add(-0x1.0319b6p126F); }

    /**
     * This color constant "Bronze Skin 4" has RGBA8888 code {@code E3C7ABFF}, intensity 0.7764706, protan 0.5294118, tritan 0.5647059, alpha 1.0, hue 0.0924293, and saturation 0.14964932.
     * It can be represented as a packed float with the constant {@code -0x1.210f8cp126F}.
     * <pre>
     * <font style='background-color: #E3C7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C7AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3C7AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3C7AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3C7AB'>&nbsp;@&nbsp;</font><font style='background-color: #E3C7AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C7AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C7AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE_SKIN_4 = -0x1.210f8cp126F;
    static { NAMED.put("Bronze Skin 4", -0x1.210f8cp126F); LIST.add(-0x1.210f8cp126F); }

    /**
     * This color constant "Bronze Skin 3" has RGBA8888 code {@code C49E73FF}, intensity 0.60784316, protan 0.5411765, tritan 0.6, alpha 1.0, hue 0.09648005, and saturation 0.22552049.
     * It can be represented as a packed float with the constant {@code -0x1.331536p126F}.
     * <pre>
     * <font style='background-color: #C49E73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C49E73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C49E73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C49E73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C49E73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C49E73'>&nbsp;@&nbsp;</font><font style='background-color: #C49E73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C49E73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C49E73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE_SKIN_3 = -0x1.331536p126F;
    static { NAMED.put("Bronze Skin 3", -0x1.331536p126F); LIST.add(-0x1.331536p126F); }

    /**
     * This color constant "Bronze Skin 2" has RGBA8888 code {@code 8F7357FF}, intensity 0.44705883, protan 0.5294118, tritan 0.5647059, alpha 1.0, hue 0.092292555, and saturation 0.14964584.
     * It can be represented as a packed float with the constant {@code -0x1.210ee4p126F}.
     * <pre>
     * <font style='background-color: #8F7357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F7357; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F7357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F7357'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F7357'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F7357'>&nbsp;@&nbsp;</font><font style='background-color: #8F7357; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F7357;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F7357; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE_SKIN_2 = -0x1.210ee4p126F;
    static { NAMED.put("Bronze Skin 2", -0x1.210ee4p126F); LIST.add(-0x1.210ee4p126F); }

    /**
     * This color constant "Bronze Skin 1" has RGBA8888 code {@code 73573BFF}, intensity 0.3372549, protan 0.5294118, tritan 0.5647059, alpha 1.0, hue 0.092247, and saturation 0.14964455.
     * It can be represented as a packed float with the constant {@code -0x1.210eacp126F}.
     * <pre>
     * <font style='background-color: #73573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73573B'>&nbsp;@&nbsp;</font><font style='background-color: #73573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRONZE_SKIN_1 = -0x1.210eacp126F;
    static { NAMED.put("Bronze Skin 1", -0x1.210eacp126F); LIST.add(-0x1.210eacp126F); }

    /**
     * This color constant "Taupe" has RGBA8888 code {@code 3B2D1FFF}, intensity 0.17254902, protan 0.5137255, tritan 0.53333336, alpha 1.0, hue 0.096455276, and saturation 0.075173184.
     * It can be represented as a packed float with the constant {@code -0x1.110658p126F}.
     * <pre>
     * <font style='background-color: #3B2D1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B2D1F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B2D1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B2D1F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B2D1F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B2D1F'>&nbsp;@&nbsp;</font><font style='background-color: #3B2D1F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B2D1F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B2D1F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAUPE = -0x1.110658p126F;
    static { NAMED.put("Taupe", -0x1.110658p126F); LIST.add(-0x1.110658p126F); }

    /**
     * This color constant "Drab Green" has RGBA8888 code {@code 414123FF}, intensity 0.22745098, protan 0.49411765, tritan 0.5529412, alpha 1.0, hue 0.1728189, and saturation 0.08534251.
     * It can be represented as a packed float with the constant {@code -0x1.1afc74p126F}.
     * <pre>
     * <font style='background-color: #414123;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #414123; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #414123;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #414123'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #414123'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #414123'>&nbsp;@&nbsp;</font><font style='background-color: #414123; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #414123;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #414123; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRAB_GREEN = -0x1.1afc74p126F;
    static { NAMED.put("Drab Green", -0x1.1afc74p126F); LIST.add(-0x1.1afc74p126F); }

    /**
     * This color constant "Lizard Scales" has RGBA8888 code {@code 73733BFF}, intensity 0.4, protan 0.49019608, tritan 0.6, alpha 1.0, hue 0.17021461, and saturation 0.16042328.
     * It can be represented as a packed float with the constant {@code -0x1.32faccp126F}.
     * <pre>
     * <font style='background-color: #73733B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73733B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73733B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73733B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73733B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73733B'>&nbsp;@&nbsp;</font><font style='background-color: #73733B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73733B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73733B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LIZARD_SCALES = -0x1.32faccp126F;
    static { NAMED.put("Lizard Scales", -0x1.32faccp126F); LIST.add(-0x1.32faccp126F); }

    /**
     * This color constant "Cricket" has RGBA8888 code {@code 8F8F57FF}, intensity 0.50980395, protan 0.49019608, tritan 0.6, alpha 1.0, hue 0.17025642, and saturation 0.16046512.
     * It can be represented as a packed float with the constant {@code -0x1.32fb04p126F}.
     * <pre>
     * <font style='background-color: #8F8F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8F57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8F57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8F57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8F57'>&nbsp;@&nbsp;</font><font style='background-color: #8F8F57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8F57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CRICKET = -0x1.32fb04p126F;
    static { NAMED.put("Cricket", -0x1.32fb04p126F); LIST.add(-0x1.32fb04p126F); }

    /**
     * This color constant "Olive Oil" has RGBA8888 code {@code A2A255FF}, intensity 0.5647059, protan 0.4862745, tritan 0.6392157, alpha 1.0, hue 0.17032705, and saturation 0.2233828.
     * It can be represented as a packed float with the constant {@code -0x1.46f92p126F}.
     * <pre>
     * <font style='background-color: #A2A255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2A255; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2A255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2A255'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2A255'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2A255'>&nbsp;@&nbsp;</font><font style='background-color: #A2A255; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2A255;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2A255; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_OIL = -0x1.46f92p126F;
    static { NAMED.put("Olive Oil", -0x1.46f92p126F); LIST.add(-0x1.46f92p126F); }

    /**
     * This color constant "Dun" has RGBA8888 code {@code B5B572FF}, intensity 0.64705884, protan 0.49019608, tritan 0.62352943, alpha 1.0, hue 0.16651447, and saturation 0.19705254.
     * It can be represented as a packed float with the constant {@code -0x1.3efb4ap126F}.
     * <pre>
     * <font style='background-color: #B5B572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5B572; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5B572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B5B572'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B5B572'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B5B572'>&nbsp;@&nbsp;</font><font style='background-color: #B5B572; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B5B572;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B5B572; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUN = -0x1.3efb4ap126F;
    static { NAMED.put("Dun", -0x1.3efb4ap126F); LIST.add(-0x1.3efb4ap126F); }

    /**
     * This color constant "Corn Silk" has RGBA8888 code {@code C7C78FFF}, intensity 0.7294118, protan 0.49019608, tritan 0.6, alpha 1.0, hue 0.17033853, and saturation 0.16054821.
     * It can be represented as a packed float with the constant {@code -0x1.32fb74p126F}.
     * <pre>
     * <font style='background-color: #C7C78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7C78F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7C78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7C78F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7C78F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7C78F'>&nbsp;@&nbsp;</font><font style='background-color: #C7C78F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7C78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7C78F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CORN_SILK = -0x1.32fb74p126F;
    static { NAMED.put("Corn Silk", -0x1.32fb74p126F); LIST.add(-0x1.32fb74p126F); }

    /**
     * This color constant "Tan" has RGBA8888 code {@code DADAABFF}, intensity 0.8117647, protan 0.49019608, tritan 0.58431375, alpha 1.0, hue 0.17403138, and saturation 0.13634229.
     * It can be represented as a packed float with the constant {@code -0x1.2afb9ep126F}.
     * <pre>
     * <font style='background-color: #DADAAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DADAAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DADAAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DADAAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DADAAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DADAAB'>&nbsp;@&nbsp;</font><font style='background-color: #DADAAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DADAAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DADAAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAN = -0x1.2afb9ep126F;
    static { NAMED.put("Tan", -0x1.2afb9ep126F); LIST.add(-0x1.2afb9ep126F); }

    /**
     * This color constant "Straw" has RGBA8888 code {@code EDEDC7FF}, intensity 0.89411765, protan 0.49411765, tritan 0.5686275, alpha 1.0, hue 0.16809142, and saturation 0.109832525.
     * It can be represented as a packed float with the constant {@code -0x1.22fdc8p126F}.
     * <pre>
     * <font style='background-color: #EDEDC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EDEDC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EDEDC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EDEDC7'>&nbsp;@&nbsp;</font><font style='background-color: #EDEDC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EDEDC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EDEDC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRAW = -0x1.22fdc8p126F;
    static { NAMED.put("Straw", -0x1.22fdc8p126F); LIST.add(-0x1.22fdc8p126F); }

    /**
     * This color constant "Honeydew" has RGBA8888 code {@code C7E3ABFF}, intensity 0.81960785, protan 0.45490196, tritan 0.58431375, alpha 1.0, hue 0.24538839, and saturation 0.15708166.
     * It can be represented as a packed float with the constant {@code -0x1.2ae9a2p126F}.
     * <pre>
     * <font style='background-color: #C7E3AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7E3AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7E3AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7E3AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7E3AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7E3AB'>&nbsp;@&nbsp;</font><font style='background-color: #C7E3AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7E3AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7E3AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HONEYDEW = -0x1.2ae9a2p126F;
    static { NAMED.put("Honeydew", -0x1.2ae9a2p126F); LIST.add(-0x1.2ae9a2p126F); }

    /**
     * This color constant "Tarnish" has RGBA8888 code {@code ABC78FFF}, intensity 0.70980394, protan 0.45490196, tritan 0.58431375, alpha 1.0, hue 0.24536592, and saturation 0.15704006.
     * It can be represented as a packed float with the constant {@code -0x1.2ae96ap126F}.
     * <pre>
     * <font style='background-color: #ABC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABC78F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABC78F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABC78F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABC78F'>&nbsp;@&nbsp;</font><font style='background-color: #ABC78F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABC78F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TARNISH = -0x1.2ae96ap126F;
    static { NAMED.put("Tarnish", -0x1.2ae96ap126F); LIST.add(-0x1.2ae96ap126F); }

    /**
     * This color constant "Pea Soup" has RGBA8888 code {@code 8EBE55FF}, intensity 0.6117647, protan 0.41960785, tritan 0.6666667, alpha 1.0, hue 0.23737082, and saturation 0.3049844.
     * It can be represented as a packed float with the constant {@code -0x1.54d738p126F}.
     * <pre>
     * <font style='background-color: #8EBE55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8EBE55; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8EBE55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8EBE55'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8EBE55'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8EBE55'>&nbsp;@&nbsp;</font><font style='background-color: #8EBE55; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8EBE55;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8EBE55; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEA_SOUP = -0x1.54d738p126F;
    static { NAMED.put("Pea Soup", -0x1.54d738p126F); LIST.add(-0x1.54d738p126F); }

    /**
     * This color constant "Marsh" has RGBA8888 code {@code 738F57FF}, intensity 0.49019608, protan 0.45490196, tritan 0.58431375, alpha 1.0, hue 0.24532175, and saturation 0.15695685.
     * It can be represented as a packed float with the constant {@code -0x1.2ae8fap126F}.
     * <pre>
     * <font style='background-color: #738F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #738F57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #738F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #738F57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #738F57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #738F57'>&nbsp;@&nbsp;</font><font style='background-color: #738F57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #738F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #738F57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MARSH = -0x1.2ae8fap126F;
    static { NAMED.put("Marsh", -0x1.2ae8fap126F); LIST.add(-0x1.2ae8fap126F); }

    /**
     * This color constant "Asparagus" has RGBA8888 code {@code 587D3EFF}, intensity 0.40392157, protan 0.4392157, tritan 0.5921569, alpha 1.0, hue 0.26307002, and saturation 0.17825899.
     * It can be represented as a packed float with the constant {@code -0x1.2ee0cep126F}.
     * <pre>
     * <font style='background-color: #587D3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #587D3E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #587D3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #587D3E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #587D3E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #587D3E'>&nbsp;@&nbsp;</font><font style='background-color: #587D3E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #587D3E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #587D3E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ASPARAGUS = -0x1.2ee0cep126F;
    static { NAMED.put("Asparagus", -0x1.2ee0cep126F); LIST.add(-0x1.2ee0cep126F); }

    /**
     * This color constant "Peat Bog" has RGBA8888 code {@code 465032FF}, intensity 0.2784314, protan 0.48235294, tritan 0.54901963, alpha 1.0, hue 0.21790677, and saturation 0.08621475.
     * It can be represented as a packed float with the constant {@code -0x1.18f68ep126F}.
     * <pre>
     * <font style='background-color: #465032;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #465032; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #465032;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #465032'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #465032'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #465032'>&nbsp;@&nbsp;</font><font style='background-color: #465032; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #465032;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #465032; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PEAT_BOG = -0x1.18f68ep126F;
    static { NAMED.put("Peat Bog", -0x1.18f68ep126F); LIST.add(-0x1.18f68ep126F); }

    /**
     * This color constant "Deep Jungle" has RGBA8888 code {@code 191E0FFF}, intensity 0.101960786, protan 0.49019608, tritan 0.52156866, alpha 1.0, hue 0.23309304, and saturation 0.039124794.
     * It can be represented as a packed float with the constant {@code -0x1.0afa34p126F}.
     * <pre>
     * <font style='background-color: #191E0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #191E0F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #191E0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #191E0F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #191E0F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #191E0F'>&nbsp;@&nbsp;</font><font style='background-color: #191E0F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #191E0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #191E0F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_JUNGLE = -0x1.0afa34p126F;
    static { NAMED.put("Deep Jungle", -0x1.0afa34p126F); LIST.add(-0x1.0afa34p126F); }

    /**
     * This color constant "Pine Green" has RGBA8888 code {@code 235037FF}, intensity 0.25882354, protan 0.43529412, tritan 0.5176471, alpha 1.0, hue 0.41090202, and saturation 0.12230289.
     * It can be represented as a packed float with the constant {@code -0x1.08de84p126F}.
     * <pre>
     * <font style='background-color: #235037;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #235037; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #235037;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #235037'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #235037'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #235037'>&nbsp;@&nbsp;</font><font style='background-color: #235037; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #235037;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #235037; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINE_GREEN = -0x1.08de84p126F;
    static { NAMED.put("Pine Green", -0x1.08de84p126F); LIST.add(-0x1.08de84p126F); }

    /**
     * This color constant "Olive Green" has RGBA8888 code {@code 3B573BFF}, intensity 0.29411766, protan 0.45882353, tritan 0.53333336, alpha 1.0, hue 0.3302685, and saturation 0.07580784.
     * It can be represented as a packed float with the constant {@code -0x1.10ea96p126F}.
     * <pre>
     * <font style='background-color: #3B573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B573B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B573B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B573B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B573B'>&nbsp;@&nbsp;</font><font style='background-color: #3B573B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B573B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B573B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLIVE_GREEN = -0x1.10ea96p126F;
    static { NAMED.put("Olive Green", -0x1.10ea96p126F); LIST.add(-0x1.10ea96p126F); }

    /**
     * This color constant "Gray Green" has RGBA8888 code {@code 506450FF}, intensity 0.36078432, protan 0.47058824, tritan 0.5254902, alpha 1.0, hue 0.32201508, and saturation 0.05680242.
     * It can be represented as a packed float with the constant {@code -0x1.0cf0b8p126F}.
     * <pre>
     * <font style='background-color: #506450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #506450; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #506450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #506450'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #506450'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #506450'>&nbsp;@&nbsp;</font><font style='background-color: #506450; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #506450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #506450; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAY_GREEN = -0x1.0cf0b8p126F;
    static { NAMED.put("Gray Green", -0x1.0cf0b8p126F); LIST.add(-0x1.0cf0b8p126F); }

    /**
     * This color constant "Maidenhair Fern" has RGBA8888 code {@code 3B7349FF}, intensity 0.37254903, protan 0.41960785, tritan 0.54509807, alpha 1.0, hue 0.3684827, and saturation 0.1483404.
     * It can be represented as a packed float with the constant {@code -0x1.16d6bep126F}.
     * <pre>
     * <font style='background-color: #3B7349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7349; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B7349'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B7349'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B7349'>&nbsp;@&nbsp;</font><font style='background-color: #3B7349; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7349;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7349; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAIDENHAIR_FERN = -0x1.16d6bep126F;
    static { NAMED.put("Maidenhair Fern", -0x1.16d6bep126F); LIST.add(-0x1.16d6bep126F); }

    /**
     * This color constant "Kelly Green" has RGBA8888 code {@code 578F57FF}, intensity 0.46666667, protan 0.41568628, tritan 0.5686275, alpha 1.0, hue 0.32959995, and saturation 0.15575135.
     * It can be represented as a packed float with the constant {@code -0x1.22d4eep126F}.
     * <pre>
     * <font style='background-color: #578F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #578F57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #578F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #578F57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #578F57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #578F57'>&nbsp;@&nbsp;</font><font style='background-color: #578F57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #578F57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #578F57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float KELLY_GREEN = -0x1.22d4eep126F;
    static { NAMED.put("Kelly Green", -0x1.22d4eep126F); LIST.add(-0x1.22d4eep126F); }

    /**
     * This color constant "Dusty Green" has RGBA8888 code {@code 73AB73FF}, intensity 0.5764706, protan 0.41568628, tritan 0.5686275, alpha 1.0, hue 0.32960004, and saturation 0.15579307.
     * It can be represented as a packed float with the constant {@code -0x1.22d526p126F}.
     * <pre>
     * <font style='background-color: #73AB73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73AB73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73AB73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73AB73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73AB73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73AB73'>&nbsp;@&nbsp;</font><font style='background-color: #73AB73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73AB73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73AB73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUSTY_GREEN = -0x1.22d526p126F;
    static { NAMED.put("Dusty Green", -0x1.22d526p126F); LIST.add(-0x1.22d526p126F); }

    /**
     * This color constant "Garter Snake" has RGBA8888 code {@code 64C082FF}, intensity 0.627451, protan 0.36862746, tritan 0.56078434, alpha 1.0, hue 0.3831734, and saturation 0.24443969.
     * It can be represented as a packed float with the constant {@code -0x1.1ebd4p126F}.
     * <pre>
     * <font style='background-color: #64C082;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64C082; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64C082;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #64C082'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #64C082'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #64C082'>&nbsp;@&nbsp;</font><font style='background-color: #64C082; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64C082;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64C082; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GARTER_SNAKE = -0x1.1ebd4p126F;
    static { NAMED.put("Garter Snake", -0x1.1ebd4p126F); LIST.add(-0x1.1ebd4p126F); }

    /**
     * This color constant "Silver Green" has RGBA8888 code {@code 8FC78FFF}, intensity 0.6862745, protan 0.41568628, tritan 0.5686275, alpha 1.0, hue 0.32959977, and saturation 0.15583473.
     * It can be represented as a packed float with the constant {@code -0x1.22d55ep126F}.
     * <pre>
     * <font style='background-color: #8FC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FC78F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FC78F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FC78F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FC78F'>&nbsp;@&nbsp;</font><font style='background-color: #8FC78F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FC78F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FC78F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_GREEN = -0x1.22d55ep126F;
    static { NAMED.put("Silver Green", -0x1.22d55ep126F); LIST.add(-0x1.22d55ep126F); }

    /**
     * This color constant "Pistachio" has RGBA8888 code {@code A2D8A2FF}, intensity 0.75686276, protan 0.41960785, tritan 0.5686275, alpha 1.0, hue 0.32382378, and saturation 0.15355742.
     * It can be represented as a packed float with the constant {@code -0x1.22d782p126F}.
     * <pre>
     * <font style='background-color: #A2D8A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2D8A2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2D8A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A2D8A2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A2D8A2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A2D8A2'>&nbsp;@&nbsp;</font><font style='background-color: #A2D8A2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A2D8A2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A2D8A2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PISTACHIO = -0x1.22d782p126F;
    static { NAMED.put("Pistachio", -0x1.22d782p126F); LIST.add(-0x1.22d782p126F); }

    /**
     * This color constant "Angel Wing" has RGBA8888 code {@code E1F8FAFF}, intensity 0.95686275, protan 0.46666667, tritan 0.48235294, alpha 1.0, hue 0.51630557, and saturation 0.07481724.
     * It can be represented as a packed float with the constant {@code -0x1.f6efe8p125F}.
     * <pre>
     * <font style='background-color: #E1F8FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1F8FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1F8FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1F8FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1F8FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1F8FA'>&nbsp;@&nbsp;</font><font style='background-color: #E1F8FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1F8FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1F8FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ANGEL_WING = -0x1.f6efe8p125F;
    static { NAMED.put("Angel Wing", -0x1.f6efe8p125F); LIST.add(-0x1.f6efe8p125F); }

    /**
     * This color constant "Sage Green" has RGBA8888 code {@code B4EECAFF}, intensity 0.85882354, protan 0.41568628, tritan 0.53333336, alpha 1.0, hue 0.3930307, and saturation 0.15793687.
     * It can be represented as a packed float with the constant {@code -0x1.10d5b6p126F}.
     * <pre>
     * <font style='background-color: #B4EECA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4EECA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4EECA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B4EECA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B4EECA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B4EECA'>&nbsp;@&nbsp;</font><font style='background-color: #B4EECA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B4EECA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B4EECA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAGE_GREEN = -0x1.10d5b6p126F;
    static { NAMED.put("Sage Green", -0x1.10d5b6p126F); LIST.add(-0x1.10d5b6p126F); }

    /**
     * This color constant "Dried Sage" has RGBA8888 code {@code ABE3C5FF}, intensity 0.81960785, protan 0.41960785, tritan 0.52156866, alpha 1.0, hue 0.41143894, and saturation 0.15219206.
     * It can be represented as a packed float with the constant {@code -0x1.0ad7a2p126F}.
     * <pre>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3C5'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRIED_SAGE = -0x1.0ad7a2p126F;
    static { NAMED.put("Dried Sage", -0x1.0ad7a2p126F); LIST.add(-0x1.0ad7a2p126F); }

    /**
     * This color constant "Artichoke" has RGBA8888 code {@code 87B48EFF}, intensity 0.6392157, protan 0.43529412, tritan 0.5411765, alpha 1.0, hue 0.35701698, and saturation 0.11875725.
     * It can be represented as a packed float with the constant {@code -0x1.14df46p126F}.
     * <pre>
     * <font style='background-color: #87B48E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87B48E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87B48E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #87B48E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #87B48E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #87B48E'>&nbsp;@&nbsp;</font><font style='background-color: #87B48E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #87B48E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #87B48E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ARTICHOKE = -0x1.14df46p126F;
    static { NAMED.put("Artichoke", -0x1.14df46p126F); LIST.add(-0x1.14df46p126F); }

    /**
     * This color constant "Viridian" has RGBA8888 code {@code 507D5FFF}, intensity 0.42745098, protan 0.43529412, tritan 0.5294118, alpha 1.0, hue 0.38435927, and saturation 0.12052187.
     * It can be represented as a packed float with the constant {@code -0x1.0ededap126F}.
     * <pre>
     * <font style='background-color: #507D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #507D5F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #507D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #507D5F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #507D5F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #507D5F'>&nbsp;@&nbsp;</font><font style='background-color: #507D5F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #507D5F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #507D5F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIRIDIAN = -0x1.0ededap126F;
    static { NAMED.put("Viridian", -0x1.0ededap126F); LIST.add(-0x1.0ededap126F); }

    /**
     * This color constant "Floral Foam" has RGBA8888 code {@code 0F6946FF}, intensity 0.3137255, protan 0.3764706, tritan 0.5137255, alpha 1.0, hue 0.4338374, and saturation 0.2365487.
     * It can be represented as a packed float with the constant {@code -0x1.06c0ap126F}.
     * <pre>
     * <font style='background-color: #0F6946;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F6946; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F6946;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F6946'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F6946'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F6946'>&nbsp;@&nbsp;</font><font style='background-color: #0F6946; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F6946;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F6946; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FLORAL_FOAM = -0x1.06c0ap126F;
    static { NAMED.put("Floral Foam", -0x1.06c0ap126F); LIST.add(-0x1.06c0ap126F); }

    /**
     * This color constant "Hunter Green" has RGBA8888 code {@code 1E2D23FF}, intensity 0.15686275, protan 0.47843137, tritan 0.50980395, alpha 1.0, hue 0.38435167, and saturation 0.04017912.
     * It can be represented as a packed float with the constant {@code -0x1.04f45p126F}.
     * <pre>
     * <font style='background-color: #1E2D23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E2D23; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E2D23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1E2D23'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1E2D23'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1E2D23'>&nbsp;@&nbsp;</font><font style='background-color: #1E2D23; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1E2D23;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1E2D23; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HUNTER_GREEN = -0x1.04f45p126F;
    static { NAMED.put("Hunter Green", -0x1.04f45p126F); LIST.add(-0x1.04f45p126F); }

    /**
     * This color constant "Dark Teal" has RGBA8888 code {@code 234146FF}, intensity 0.23529412, protan 0.45882353, tritan 0.47058824, alpha 1.0, hue 0.53346896, and saturation 0.1053856.
     * It can be represented as a packed float with the constant {@code -0x1.f0ea78p125F}.
     * <pre>
     * <font style='background-color: #234146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #234146; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #234146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #234146'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #234146'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #234146'>&nbsp;@&nbsp;</font><font style='background-color: #234146; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #234146;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #234146; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_TEAL = -0x1.f0ea78p125F;
    static { NAMED.put("Dark Teal", -0x1.f0ea78p125F); LIST.add(-0x1.f0ea78p125F); }

    /**
     * This color constant "Kyanite" has RGBA8888 code {@code 3B7373FF}, intensity 0.40784314, protan 0.42352942, tritan 0.46666667, alpha 1.0, hue 0.5067094, and saturation 0.15948784.
     * It can be represented as a packed float with the constant {@code -0x1.eed8dp125F}.
     * <pre>
     * <font style='background-color: #3B7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7373; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B7373'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B7373'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B7373'>&nbsp;@&nbsp;</font><font style='background-color: #3B7373; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B7373;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B7373; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float KYANITE = -0x1.eed8dp125F;
    static { NAMED.put("Kyanite", -0x1.eed8dp125F); LIST.add(-0x1.eed8dp125F); }

    /**
     * This color constant "Spearmint" has RGBA8888 code {@code 64ABABFF}, intensity 0.61960787, protan 0.40392157, tritan 0.45882353, alpha 1.0, hue 0.5058057, and saturation 0.19918323.
     * It can be represented as a packed float with the constant {@code -0x1.eacf3cp125F}.
     * <pre>
     * <font style='background-color: #64ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64ABAB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #64ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #64ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #64ABAB'>&nbsp;@&nbsp;</font><font style='background-color: #64ABAB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #64ABAB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #64ABAB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SPEARMINT = -0x1.eacf3cp125F;
    static { NAMED.put("Spearmint", -0x1.eacf3cp125F); LIST.add(-0x1.eacf3cp125F); }

    /**
     * This color constant "Amazonite" has RGBA8888 code {@code 8FC7C7FF}, intensity 0.7372549, protan 0.42352942, tritan 0.46666667, alpha 1.0, hue 0.5065791, and saturation 0.15948468.
     * It can be represented as a packed float with the constant {@code -0x1.eed978p125F}.
     * <pre>
     * <font style='background-color: #8FC7C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FC7C7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FC7C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FC7C7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FC7C7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FC7C7'>&nbsp;@&nbsp;</font><font style='background-color: #8FC7C7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FC7C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FC7C7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AMAZONITE = -0x1.eed978p125F;
    static { NAMED.put("Amazonite", -0x1.eed978p125F); LIST.add(-0x1.eed978p125F); }

    /**
     * This color constant "Pastel Sky" has RGBA8888 code {@code ABE3E3FF}, intensity 0.84705883, protan 0.42352942, tritan 0.46666667, alpha 1.0, hue 0.5065356, and saturation 0.15948337.
     * It can be represented as a packed float with the constant {@code -0x1.eed9bp125F}.
     * <pre>
     * <font style='background-color: #ABE3E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABE3E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABE3E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABE3E3'>&nbsp;@&nbsp;</font><font style='background-color: #ABE3E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABE3E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABE3E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PASTEL_SKY = -0x1.eed9bp125F;
    static { NAMED.put("Pastel Sky", -0x1.eed9bp125F); LIST.add(-0x1.eed9bp125F); }

    /**
     * This color constant "Aquamarine" has RGBA8888 code {@code C7F1F1FF}, intensity 0.9137255, protan 0.44313726, tritan 0.4745098, alpha 1.0, hue 0.50783366, and saturation 0.11978519.
     * It can be represented as a packed float with the constant {@code -0x1.f2e3d2p125F}.
     * <pre>
     * <font style='background-color: #C7F1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7F1F1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7F1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C7F1F1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C7F1F1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C7F1F1'>&nbsp;@&nbsp;</font><font style='background-color: #C7F1F1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C7F1F1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C7F1F1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AQUAMARINE = -0x1.f2e3d2p125F;
    static { NAMED.put("Aquamarine", -0x1.f2e3d2p125F); LIST.add(-0x1.f2e3d2p125F); }

    /**
     * This color constant "Dust Bunny" has RGBA8888 code {@code BED2F0FF}, intensity 0.8352941, protan 0.4745098, tritan 0.43137255, alpha 1.0, hue 0.60013497, and saturation 0.1510368.
     * It can be represented as a packed float with the constant {@code -0x1.dcf3aap125F}.
     * <pre>
     * <font style='background-color: #BED2F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BED2F0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BED2F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BED2F0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BED2F0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BED2F0'>&nbsp;@&nbsp;</font><font style='background-color: #BED2F0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BED2F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BED2F0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUST_BUNNY = -0x1.dcf3aap125F;
    static { NAMED.put("Dust Bunny", -0x1.dcf3aap125F); LIST.add(-0x1.dcf3aap125F); }

    /**
     * This color constant "Patina" has RGBA8888 code {@code ABC7E3FF}, intensity 0.78431374, protan 0.46666667, tritan 0.43137255, alpha 1.0, hue 0.5888696, and saturation 0.1615771.
     * It can be represented as a packed float with the constant {@code -0x1.dcef9p125F}.
     * <pre>
     * <font style='background-color: #ABC7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABC7E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABC7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABC7E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABC7E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABC7E3'>&nbsp;@&nbsp;</font><font style='background-color: #ABC7E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABC7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABC7E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PATINA = -0x1.dcef9p125F;
    static { NAMED.put("Patina", -0x1.dcef9p125F); LIST.add(-0x1.dcef9p125F); }

    /**
     * This color constant "Chipped Granite" has RGBA8888 code {@code A8B9DCFF}, intensity 0.74509805, protan 0.48235294, tritan 0.42352942, alpha 1.0, hue 0.61646533, and saturation 0.15384465.
     * It can be represented as a packed float with the constant {@code -0x1.d8f77cp125F}.
     * <pre>
     * <font style='background-color: #A8B9DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8B9DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8B9DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A8B9DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A8B9DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A8B9DC'>&nbsp;@&nbsp;</font><font style='background-color: #A8B9DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A8B9DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A8B9DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHIPPED_GRANITE = -0x1.d8f77cp125F;
    static { NAMED.put("Chipped Granite", -0x1.d8f77cp125F); LIST.add(-0x1.d8f77cp125F); }

    /**
     * This color constant "Blue Smoke" has RGBA8888 code {@code 8FABC7FF}, intensity 0.6745098, protan 0.46666667, tritan 0.43137255, alpha 1.0, hue 0.58891183, and saturation 0.1615783.
     * It can be represented as a packed float with the constant {@code -0x1.dcef58p125F}.
     * <pre>
     * <font style='background-color: #8FABC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FABC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FABC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8FABC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8FABC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8FABC7'>&nbsp;@&nbsp;</font><font style='background-color: #8FABC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8FABC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8FABC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE_SMOKE = -0x1.dcef58p125F;
    static { NAMED.put("Blue Smoke", -0x1.dcef58p125F); LIST.add(-0x1.dcef58p125F); }

    /**
     * This color constant "Air Force Blue" has RGBA8888 code {@code 578FC7FF}, intensity 0.5686275, protan 0.43137255, tritan 0.3647059, alpha 1.0, hue 0.5870948, and saturation 0.32246226.
     * It can be represented as a packed float with the constant {@code -0x1.badd22p125F}.
     * <pre>
     * <font style='background-color: #578FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #578FC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #578FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #578FC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #578FC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #578FC7'>&nbsp;@&nbsp;</font><font style='background-color: #578FC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #578FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #578FC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AIR_FORCE_BLUE = -0x1.badd22p125F;
    static { NAMED.put("Air Force Blue", -0x1.badd22p125F); LIST.add(-0x1.badd22p125F); }

    /**
     * This color constant "Cold Iron" has RGBA8888 code {@code 57738FFF}, intensity 0.45490196, protan 0.46666667, tritan 0.43137255, alpha 1.0, hue 0.5889964, and saturation 0.16158062.
     * It can be represented as a packed float with the constant {@code -0x1.dceee8p125F}.
     * <pre>
     * <font style='background-color: #57738F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57738F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57738F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57738F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57738F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57738F'>&nbsp;@&nbsp;</font><font style='background-color: #57738F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57738F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57738F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COLD_IRON = -0x1.dceee8p125F;
    static { NAMED.put("Cold Iron", -0x1.dceee8p125F); LIST.add(-0x1.dceee8p125F); }

    /**
     * This color constant "Dreary Blue" has RGBA8888 code {@code 3B5773FF}, intensity 0.34509805, protan 0.46666667, tritan 0.43137255, alpha 1.0, hue 0.5890387, and saturation 0.16158167.
     * It can be represented as a packed float with the constant {@code -0x1.dceebp125F}.
     * <pre>
     * <font style='background-color: #3B5773;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B5773; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B5773;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B5773'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B5773'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B5773'>&nbsp;@&nbsp;</font><font style='background-color: #3B5773; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B5773;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B5773; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DREARY_BLUE = -0x1.dceebp125F;
    static { NAMED.put("Dreary Blue", -0x1.dceebp125F); LIST.add(-0x1.dceebp125F); }

    /**
     * This color constant "Murk" has RGBA8888 code {@code 0F192DFF}, intensity 0.10980392, protan 0.4862745, tritan 0.45490196, alpha 1.0, hue 0.60781056, and saturation 0.09519152.
     * It can be represented as a packed float with the constant {@code -0x1.e8f838p125F}.
     * <pre>
     * <font style='background-color: #0F192D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F192D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F192D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F192D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F192D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F192D'>&nbsp;@&nbsp;</font><font style='background-color: #0F192D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F192D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F192D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MURK = -0x1.e8f838p125F;
    static { NAMED.put("Murk", -0x1.e8f838p125F); LIST.add(-0x1.e8f838p125F); }

    /**
     * This color constant "Ninja" has RGBA8888 code {@code 1F1F3BFF}, intensity 0.14901961, protan 0.5019608, tritan 0.44705883, alpha 1.0, hue 0.65797186, and saturation 0.08745845.
     * It can be represented as a packed float with the constant {@code -0x1.e5004cp125F}.
     * <pre>
     * <font style='background-color: #1F1F3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F1F3B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F1F3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1F1F3B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1F1F3B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1F1F3B'>&nbsp;@&nbsp;</font><font style='background-color: #1F1F3B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1F1F3B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1F1F3B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NINJA = -0x1.e5004cp125F;
    static { NAMED.put("Ninja", -0x1.e5004cp125F); LIST.add(-0x1.e5004cp125F); }

    /**
     * This color constant "Watercolor Black" has RGBA8888 code {@code 3B3B57FF}, intensity 0.25882354, protan 0.5019608, tritan 0.44705883, alpha 1.0, hue 0.65789443, and saturation 0.08745736.
     * It can be represented as a packed float with the constant {@code -0x1.e50084p125F}.
     * <pre>
     * <font style='background-color: #3B3B57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B3B57; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B3B57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3B3B57'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3B3B57'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3B3B57'>&nbsp;@&nbsp;</font><font style='background-color: #3B3B57; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3B3B57;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3B3B57; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WATERCOLOR_BLACK = -0x1.e50084p125F;
    static { NAMED.put("Watercolor Black", -0x1.e50084p125F); LIST.add(-0x1.e50084p125F); }

    /**
     * This color constant "Iolite" has RGBA8888 code {@code 494973FF}, intensity 0.3254902, protan 0.5019608, tritan 0.41960785, alpha 1.0, hue 0.65557563, and saturation 0.13417259.
     * It can be represented as a packed float with the constant {@code -0x1.d700a6p125F}.
     * <pre>
     * <font style='background-color: #494973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494973; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #494973'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #494973'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #494973'>&nbsp;@&nbsp;</font><font style='background-color: #494973; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #494973;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #494973; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float IOLITE = -0x1.d700a6p125F;
    static { NAMED.put("Iolite", -0x1.d700a6p125F); LIST.add(-0x1.d700a6p125F); }

    /**
     * This color constant "Boysenberry" has RGBA8888 code {@code 57578FFF}, intensity 0.39215687, protan 0.5058824, tritan 0.39607844, alpha 1.0, hue 0.66166914, and saturation 0.16894415.
     * It can be represented as a packed float with the constant {@code -0x1.cb02c8p125F}.
     * <pre>
     * <font style='background-color: #57578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57578F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #57578F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #57578F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #57578F'>&nbsp;@&nbsp;</font><font style='background-color: #57578F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #57578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #57578F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOYSENBERRY = -0x1.cb02c8p125F;
    static { NAMED.put("Boysenberry", -0x1.cb02c8p125F); LIST.add(-0x1.cb02c8p125F); }

    /**
     * This color constant "Watercolor Gray" has RGBA8888 code {@code 736EAAFF}, intensity 0.49019608, protan 0.5137255, tritan 0.3882353, alpha 1.0, hue 0.67480534, and saturation 0.18056786.
     * It can be represented as a packed float with the constant {@code -0x1.c706fap125F}.
     * <pre>
     * <font style='background-color: #736EAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #736EAA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #736EAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #736EAA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #736EAA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #736EAA'>&nbsp;@&nbsp;</font><font style='background-color: #736EAA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #736EAA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #736EAA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WATERCOLOR_GRAY = -0x1.c706fap125F;
    static { NAMED.put("Watercolor Gray", -0x1.c706fap125F); LIST.add(-0x1.c706fap125F); }

    /**
     * This color constant "Blue Steel" has RGBA8888 code {@code 7676CAFF}, intensity 0.5411765, protan 0.50980395, tritan 0.34117648, alpha 1.0, hue 0.66268104, and saturation 0.25710422.
     * It can be represented as a packed float with the constant {@code -0x1.af0514p125F}.
     * <pre>
     * <font style='background-color: #7676CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7676CA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7676CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7676CA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7676CA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7676CA'>&nbsp;@&nbsp;</font><font style='background-color: #7676CA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7676CA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7676CA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE_STEEL = -0x1.af0514p125F;
    static { NAMED.put("Blue Steel", -0x1.af0514p125F); LIST.add(-0x1.af0514p125F); }

    /**
     * This color constant "Twilight Cloud" has RGBA8888 code {@code 8F8FC7FF}, intensity 0.6117647, protan 0.5058824, tritan 0.39607844, alpha 1.0, hue 0.66158915, and saturation 0.16894197.
     * It can be represented as a packed float with the constant {@code -0x1.cb0338p125F}.
     * <pre>
     * <font style='background-color: #8F8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8FC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #8F8FC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F8FC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TWILIGHT_CLOUD = -0x1.cb0338p125F;
    static { NAMED.put("Twilight Cloud", -0x1.cb0338p125F); LIST.add(-0x1.cb0338p125F); }

    /**
     * This color constant "Smog" has RGBA8888 code {@code ABABE3FF}, intensity 0.72156864, protan 0.5058824, tritan 0.39607844, alpha 1.0, hue 0.66154927, and saturation 0.16894072.
     * It can be represented as a packed float with the constant {@code -0x1.cb037p125F}.
     * <pre>
     * <font style='background-color: #ABABE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABABE3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABABE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #ABABE3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #ABABE3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #ABABE3'>&nbsp;@&nbsp;</font><font style='background-color: #ABABE3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #ABABE3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ABABE3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SMOG = -0x1.cb037p125F;
    static { NAMED.put("Smog", -0x1.cb037p125F); LIST.add(-0x1.cb037p125F); }

    /**
     * This color constant "Tropic Mist" has RGBA8888 code {@code D0DAF8FF}, intensity 0.8745098, protan 0.49019608, tritan 0.43529412, alpha 1.0, hue 0.6269259, and saturation 0.12328243.
     * It can be represented as a packed float with the constant {@code -0x1.defbbep125F}.
     * <pre>
     * <font style='background-color: #D0DAF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0DAF8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0DAF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D0DAF8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D0DAF8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D0DAF8'>&nbsp;@&nbsp;</font><font style='background-color: #D0DAF8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D0DAF8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D0DAF8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TROPIC_MIST = -0x1.defbbep125F;
    static { NAMED.put("Tropic Mist", -0x1.defbbep125F); LIST.add(-0x1.defbbep125F); }

    /**
     * This color constant "Feather Down" has RGBA8888 code {@code E3E3FFFF}, intensity 0.91764706, protan 0.5019608, tritan 0.44705883, alpha 1.0, hue 0.6574307, and saturation 0.08745074.
     * It can be represented as a packed float with the constant {@code -0x1.e501d4p125F}.
     * <pre>
     * <font style='background-color: #E3E3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3E3FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3E3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3E3FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3E3FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3E3FF'>&nbsp;@&nbsp;</font><font style='background-color: #E3E3FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3E3FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3E3FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FEATHER_DOWN = -0x1.e501d4p125F;
    static { NAMED.put("Feather Down", -0x1.e501d4p125F); LIST.add(-0x1.e501d4p125F); }

    /**
     * This color constant "Mild Violet" has RGBA8888 code {@code AB8FC7FF}, intensity 0.6313726, protan 0.5411765, tritan 0.4117647, alpha 1.0, hue 0.7347431, and saturation 0.16028708.
     * It can be represented as a packed float with the constant {@code -0x1.d31542p125F}.
     * <pre>
     * <font style='background-color: #AB8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB8FC7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB8FC7'>&nbsp;@&nbsp;</font><font style='background-color: #AB8FC7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB8FC7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB8FC7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MILD_VIOLET = -0x1.d31542p125F;
    static { NAMED.put("Mild Violet", -0x1.d31542p125F); LIST.add(-0x1.d31542p125F); }

    /**
     * This color constant "Violet Cushions" has RGBA8888 code {@code 8F57C7FF}, intensity 0.4862745, protan 0.5882353, tritan 0.32156864, alpha 1.0, hue 0.7391068, and saturation 0.32735392.
     * It can be represented as a packed float with the constant {@code -0x1.a52cf8p125F}.
     * <pre>
     * <font style='background-color: #8F57C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F57C7; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F57C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F57C7'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F57C7'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F57C7'>&nbsp;@&nbsp;</font><font style='background-color: #8F57C7; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F57C7;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F57C7; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIOLET_CUSHIONS = -0x1.a52cf8p125F;
    static { NAMED.put("Violet Cushions", -0x1.a52cf8p125F); LIST.add(-0x1.a52cf8p125F); }

    /**
     * This color constant "Dull Violet" has RGBA8888 code {@code 73578FFF}, intensity 0.4117647, protan 0.5411765, tritan 0.4117647, alpha 1.0, hue 0.73479164, and saturation 0.16037029.
     * It can be represented as a packed float with the constant {@code -0x1.d314d2p125F}.
     * <pre>
     * <font style='background-color: #73578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73578F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73578F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73578F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73578F'>&nbsp;@&nbsp;</font><font style='background-color: #73578F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73578F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_VIOLET = -0x1.d314d2p125F;
    static { NAMED.put("Dull Violet", -0x1.d314d2p125F); LIST.add(-0x1.d314d2p125F); }

    /**
     * This color constant "Royal Violet" has RGBA8888 code {@code 573B73FF}, intensity 0.3019608, protan 0.5411765, tritan 0.4117647, alpha 1.0, hue 0.73481625, and saturation 0.1604118.
     * It can be represented as a packed float with the constant {@code -0x1.d3149ap125F}.
     * <pre>
     * <font style='background-color: #573B73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573B73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573B73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #573B73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #573B73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #573B73'>&nbsp;@&nbsp;</font><font style='background-color: #573B73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #573B73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #573B73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROYAL_VIOLET = -0x1.d3149ap125F;
    static { NAMED.put("Royal Violet", -0x1.d3149ap125F); LIST.add(-0x1.d3149ap125F); }

    /**
     * This color constant "Eminence" has RGBA8888 code {@code 3C233CFF}, intensity 0.18039216, protan 0.53333336, tritan 0.46666667, alpha 1.0, hue 0.8053379, and saturation 0.07101995.
     * It can be represented as a packed float with the constant {@code -0x1.ef105cp125F}.
     * <pre>
     * <font style='background-color: #3C233C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C233C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C233C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C233C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C233C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C233C'>&nbsp;@&nbsp;</font><font style='background-color: #3C233C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C233C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C233C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMINENCE = -0x1.ef105cp125F;
    static { NAMED.put("Eminence", -0x1.ef105cp125F); LIST.add(-0x1.ef105cp125F); }

    /**
     * This color constant "Prune" has RGBA8888 code {@code 463246FF}, intensity 0.22745098, protan 0.5254902, tritan 0.47058824, alpha 1.0, hue 0.78970087, and saturation 0.060334787.
     * It can be represented as a packed float with the constant {@code -0x1.f10c74p125F}.
     * <pre>
     * <font style='background-color: #463246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #463246; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #463246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #463246'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #463246'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #463246'>&nbsp;@&nbsp;</font><font style='background-color: #463246; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #463246;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #463246; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PRUNE = -0x1.f10c74p125F;
    static { NAMED.put("Prune", -0x1.f10c74p125F); LIST.add(-0x1.f10c74p125F); }

    /**
     * This color constant "Dusty Grape" has RGBA8888 code {@code 724072FF}, intensity 0.33333334, protan 0.5686275, tritan 0.43529412, alpha 1.0, hue 0.8120651, and saturation 0.14017266.
     * It can be represented as a packed float with the constant {@code -0x1.df22aap125F}.
     * <pre>
     * <font style='background-color: #724072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #724072; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #724072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #724072'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #724072'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #724072'>&nbsp;@&nbsp;</font><font style='background-color: #724072; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #724072;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #724072; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUSTY_GRAPE = -0x1.df22aap125F;
    static { NAMED.put("Dusty Grape", -0x1.df22aap125F); LIST.add(-0x1.df22aap125F); }

    /**
     * This color constant "Pink Violet" has RGBA8888 code {@code 8F578FFF}, intensity 0.43529412, protan 0.5803922, tritan 0.42745098, alpha 1.0, hue 0.81718266, and saturation 0.15916464.
     * It can be represented as a packed float with the constant {@code -0x1.db28dep125F}.
     * <pre>
     * <font style='background-color: #8F578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F578F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8F578F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8F578F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8F578F'>&nbsp;@&nbsp;</font><font style='background-color: #8F578F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8F578F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8F578F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_VIOLET = -0x1.db28dep125F;
    static { NAMED.put("Pink Violet", -0x1.db28dep125F); LIST.add(-0x1.db28dep125F); }

    /**
     * This color constant "Ripe Plum" has RGBA8888 code {@code AB57ABFF}, intensity 0.48235294, protan 0.61960787, tritan 0.3882353, alpha 1.0, hue 0.8131097, and saturation 0.24278027.
     * It can be represented as a packed float with the constant {@code -0x1.c73cf6p125F}.
     * <pre>
     * <font style='background-color: #AB57AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB57AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB57AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB57AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB57AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB57AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB57AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB57AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB57AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RIPE_PLUM = -0x1.c73cf6p125F;
    static { NAMED.put("Ripe Plum", -0x1.c73cf6p125F); LIST.add(-0x1.c73cf6p125F); }

    /**
     * This color constant "Mauve" has RGBA8888 code {@code AB73ABFF}, intensity 0.54509807, protan 0.5803922, tritan 0.42745098, alpha 1.0, hue 0.81718, and saturation 0.1591233.
     * It can be represented as a packed float with the constant {@code -0x1.db2916p125F}.
     * <pre>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AB73AB'>&nbsp;@&nbsp;</font><font style='background-color: #AB73AB; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AB73AB;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AB73AB; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MAUVE = -0x1.db2916p125F;
    static { NAMED.put("Mauve", -0x1.db2916p125F); LIST.add(-0x1.db2916p125F); }

    /**
     * This color constant "Ham" has RGBA8888 code {@code EBACE1FF}, intensity 0.76862746, protan 0.5882353, tritan 0.43529412, alpha 1.0, hue 0.84215355, and saturation 0.15999508.
     * It can be represented as a packed float with the constant {@code -0x1.df2d88p125F}.
     * <pre>
     * <font style='background-color: #EBACE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBACE1; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBACE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #EBACE1'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #EBACE1'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #EBACE1'>&nbsp;@&nbsp;</font><font style='background-color: #EBACE1; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #EBACE1;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #EBACE1; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HAM = -0x1.df2d88p125F;
    static { NAMED.put("Ham", -0x1.df2d88p125F); LIST.add(-0x1.df2d88p125F); }

    /**
     * This color constant "Cotton Candy" has RGBA8888 code {@code FFDCF5FF}, intensity 0.9098039, protan 0.54901963, tritan 0.47058824, alpha 1.0, hue 0.8627392, and saturation 0.08973235.
     * It can be represented as a packed float with the constant {@code -0x1.f119dp125F}.
     * <pre>
     * <font style='background-color: #FFDCF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDCF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDCF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFDCF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFDCF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFDCF5'>&nbsp;@&nbsp;</font><font style='background-color: #FFDCF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFDCF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFDCF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float COTTON_CANDY = -0x1.f119dp125F;
    static { NAMED.put("Cotton Candy", -0x1.f119dp125F); LIST.add(-0x1.f119dp125F); }

    /**
     * This color constant "Silver Pink" has RGBA8888 code {@code E3C7E3FF}, intensity 0.827451, protan 0.5372549, tritan 0.4627451, alpha 1.0, hue 0.8052681, and saturation 0.07913804.
     * It can be represented as a packed float with the constant {@code -0x1.ed13a6p125F}.
     * <pre>
     * <font style='background-color: #E3C7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C7E3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E3C7E3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E3C7E3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E3C7E3'>&nbsp;@&nbsp;</font><font style='background-color: #E3C7E3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E3C7E3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E3C7E3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SILVER_PINK = -0x1.ed13a6p125F;
    static { NAMED.put("Silver Pink", -0x1.ed13a6p125F); LIST.add(-0x1.ed13a6p125F); }

    /**
     * This color constant "Tea Rose" has RGBA8888 code {@code E1B9D2FF}, intensity 0.7764706, protan 0.5529412, tritan 0.4745098, alpha 1.0, hue 0.88058275, and saturation 0.09796983.
     * It can be represented as a packed float with the constant {@code -0x1.f31b8cp125F}.
     * <pre>
     * <font style='background-color: #E1B9D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1B9D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1B9D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E1B9D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E1B9D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E1B9D2'>&nbsp;@&nbsp;</font><font style='background-color: #E1B9D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E1B9D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E1B9D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TEA_ROSE = -0x1.f31b8cp125F;
    static { NAMED.put("Tea Rose", -0x1.f31b8cp125F); LIST.add(-0x1.f31b8cp125F); }

    /**
     * This color constant "Old Rose" has RGBA8888 code {@code D7A0BEFF}, intensity 0.69411767, protan 0.57254905, tritan 0.4745098, alpha 1.0, hue 0.8997242, and saturation 0.13587046.
     * It can be represented as a packed float with the constant {@code -0x1.f32562p125F}.
     * <pre>
     * <font style='background-color: #D7A0BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A0BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A0BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7A0BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7A0BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7A0BE'>&nbsp;@&nbsp;</font><font style='background-color: #D7A0BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A0BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A0BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OLD_ROSE = -0x1.f32562p125F;
    static { NAMED.put("Old Rose", -0x1.f32562p125F); LIST.add(-0x1.f32562p125F); }

    /**
     * This color constant "Dusty Pink" has RGBA8888 code {@code C78FB9FF}, intensity 0.6392157, protan 0.5764706, tritan 0.4509804, alpha 1.0, hue 0.85640633, and saturation 0.13977832.
     * It can be represented as a packed float with the constant {@code -0x1.e72746p125F}.
     * <pre>
     * <font style='background-color: #C78FB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78FB9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78FB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C78FB9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C78FB9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C78FB9'>&nbsp;@&nbsp;</font><font style='background-color: #C78FB9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C78FB9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C78FB9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DUSTY_PINK = -0x1.e72746p125F;
    static { NAMED.put("Dusty Pink", -0x1.e72746p125F); LIST.add(-0x1.e72746p125F); }

    /**
     * This color constant "Roseate Spoonbill" has RGBA8888 code {@code C87DA0FF}, intensity 0.5764706, protan 0.6, tritan 0.4745098, alpha 1.0, hue 0.9136197, and saturation 0.18893218.
     * It can be represented as a packed float with the constant {@code -0x1.f33326p125F}.
     * <pre>
     * <font style='background-color: #C87DA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C87DA0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C87DA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C87DA0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C87DA0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C87DA0'>&nbsp;@&nbsp;</font><font style='background-color: #C87DA0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C87DA0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C87DA0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROSEATE_SPOONBILL = -0x1.f33326p125F;
    static { NAMED.put("Roseate Spoonbill", -0x1.f33326p125F); LIST.add(-0x1.f33326p125F); }

    /**
     * This color constant "Thulian Pink" has RGBA8888 code {@code C35A91FF}, intensity 0.48235294, protan 0.6431373, tritan 0.45490196, alpha 1.0, hue 0.904909, and saturation 0.2692102.
     * It can be represented as a packed float with the constant {@code -0x1.e948f6p125F}.
     * <pre>
     * <font style='background-color: #C35A91;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C35A91; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C35A91;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C35A91'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C35A91'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C35A91'>&nbsp;@&nbsp;</font><font style='background-color: #C35A91; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C35A91;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C35A91; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THULIAN_PINK = -0x1.e948f6p125F;
    static { NAMED.put("Thulian Pink", -0x1.e948f6p125F); LIST.add(-0x1.e948f6p125F); }

    /**
     * This color constant "Brown Velvet" has RGBA8888 code {@code 4B2837FF}, intensity 0.19607843, protan 0.54509807, tritan 0.49019608, alpha 1.0, hue 0.91895974, and saturation 0.085493505.
     * It can be represented as a packed float with the constant {@code -0x1.fb1664p125F}.
     * <pre>
     * <font style='background-color: #4B2837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2837; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B2837'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B2837'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B2837'>&nbsp;@&nbsp;</font><font style='background-color: #4B2837; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B2837;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B2837; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BROWN_VELVET = -0x1.fb1664p125F;
    static { NAMED.put("Brown Velvet", -0x1.fb1664p125F); LIST.add(-0x1.fb1664p125F); }

    /**
     * This color constant "Nightshade" has RGBA8888 code {@code 321623FF}, intensity 0.11764706, protan 0.5372549, tritan 0.49019608, alpha 1.0, hue 0.912392, and saturation 0.07037425.
     * It can be represented as a packed float with the constant {@code -0x1.fb123cp125F}.
     * <pre>
     * <font style='background-color: #321623;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #321623; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #321623;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #321623'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #321623'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #321623'>&nbsp;@&nbsp;</font><font style='background-color: #321623; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #321623;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #321623; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NIGHTSHADE = -0x1.fb123cp125F;
    static { NAMED.put("Nightshade", -0x1.fb123cp125F); LIST.add(-0x1.fb123cp125F); }

    /**
     * This color constant "Scribe Ink" has RGBA8888 code {@code 280A1EFF}, intensity 0.078431375, protan 0.5411765, tritan 0.47843137, alpha 1.0, hue 0.87412596, and saturation 0.07611954.
     * It can be represented as a packed float with the constant {@code -0x1.f51428p125F}.
     * <pre>
     * <font style='background-color: #280A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #280A1E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #280A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #280A1E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #280A1E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #280A1E'>&nbsp;@&nbsp;</font><font style='background-color: #280A1E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #280A1E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #280A1E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SCRIBE_INK = -0x1.f51428p125F;
    static { NAMED.put("Scribe Ink", -0x1.f51428p125F); LIST.add(-0x1.f51428p125F); }

    /**
     * This color constant "Varnish" has RGBA8888 code {@code 401811FF}, intensity 0.11764706, protan 0.54901963, tritan 0.53333336, alpha 1.0, hue 0.030923437, and saturation 0.12260288.
     * It can be represented as a packed float with the constant {@code -0x1.11183cp126F}.
     * <pre>
     * <font style='background-color: #401811;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #401811; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #401811;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #401811'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #401811'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #401811'>&nbsp;@&nbsp;</font><font style='background-color: #401811; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #401811;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #401811; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VARNISH = -0x1.11183cp126F;
    static { NAMED.put("Varnish", -0x1.11183cp126F); LIST.add(-0x1.11183cp126F); }

    /**
     * This color constant "Cedar Wood" has RGBA8888 code {@code 621800FF}, intensity 0.1254902, protan 0.5921569, tritan 0.58431375, alpha 1.0, hue 0.04749214, and saturation 0.26733145.
     * It can be represented as a packed float with the constant {@code -0x1.2b2e4p126F}.
     * <pre>
     * <font style='background-color: #621800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #621800; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #621800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #621800'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #621800'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #621800'>&nbsp;@&nbsp;</font><font style='background-color: #621800; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #621800;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #621800; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CEDAR_WOOD = -0x1.2b2e4p126F;
    static { NAMED.put("Cedar Wood", -0x1.2b2e4p126F); LIST.add(-0x1.2b2e4p126F); }

    /**
     * This color constant "Hot Sauce" has RGBA8888 code {@code A5140AFF}, intensity 0.1764706, protan 0.6862745, tritan 0.59607846, alpha 1.0, hue 0.015737703, and saturation 0.41383362.
     * It can be represented as a packed float with the constant {@code -0x1.315e5ap126F}.
     * <pre>
     * <font style='background-color: #A5140A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5140A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5140A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A5140A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A5140A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A5140A'>&nbsp;@&nbsp;</font><font style='background-color: #A5140A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A5140A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A5140A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HOT_SAUCE = -0x1.315e5ap126F;
    static { NAMED.put("Hot Sauce", -0x1.315e5ap126F); LIST.add(-0x1.315e5ap126F); }

    /**
     * This color constant "Lurid Red" has RGBA8888 code {@code DA2010FF}, intensity 0.2509804, protan 0.7411765, tritan 0.6313726, alpha 1.0, hue 0.018679397, and saturation 0.5476782.
     * It can be represented as a packed float with the constant {@code -0x1.437a8p126F}.
     * <pre>
     * <font style='background-color: #DA2010;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA2010; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA2010;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA2010'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA2010'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA2010'>&nbsp;@&nbsp;</font><font style='background-color: #DA2010; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA2010;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA2010; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LURID_RED = -0x1.437a8p126F;
    static { NAMED.put("Lurid Red", -0x1.437a8p126F); LIST.add(-0x1.437a8p126F); }

    /**
     * This color constant "Brick" has RGBA8888 code {@code D5524AFF}, intensity 0.4117647, protan 0.67058825, tritan 0.5882353, alpha 1.0, hue 0.015929867, and saturation 0.37940845.
     * It can be represented as a packed float with the constant {@code -0x1.2d56d2p126F}.
     * <pre>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D5524A'>&nbsp;@&nbsp;</font><font style='background-color: #D5524A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D5524A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D5524A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BRICK = -0x1.2d56d2p126F;
    static { NAMED.put("Brick", -0x1.2d56d2p126F); LIST.add(-0x1.2d56d2p126F); }

    /**
     * This color constant "Red" has RGBA8888 code {@code FF3C0AFF}, intensity 0.33333334, protan 0.7490196, tritan 0.69803923, alpha 1.0, hue 0.039655425, and saturation 0.67167187.
     * It can be represented as a packed float with the constant {@code -0x1.657eaap126F}.
     * <pre>
     * <font style='background-color: #FF3C0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF3C0A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF3C0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF3C0A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF3C0A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF3C0A'>&nbsp;@&nbsp;</font><font style='background-color: #FF3C0A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF3C0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF3C0A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RED = -0x1.657eaap126F;
    static { NAMED.put("Red", -0x1.657eaap126F); LIST.add(-0x1.657eaap126F); }

    /**
     * This color constant "Embers" has RGBA8888 code {@code F55A32FF}, intensity 0.43137255, protan 0.69803923, tritan 0.65882355, alpha 1.0, hue 0.040145278, and saturation 0.5364255.
     * It can be represented as a packed float with the constant {@code -0x1.5164dcp126F}.
     * <pre>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F55A32'>&nbsp;@&nbsp;</font><font style='background-color: #F55A32; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F55A32;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F55A32; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMBERS = -0x1.5164dcp126F;
    static { NAMED.put("Embers", -0x1.5164dcp126F); LIST.add(-0x1.5164dcp126F); }

    /**
     * This color constant "Salmon" has RGBA8888 code {@code FF6262FF}, intensity 0.5019608, protan 0.7058824, tritan 0.58431375, alpha 1.0, hue 0.0037691426, and saturation 0.42016602.
     * It can be represented as a packed float with the constant {@code -0x1.2b69p126F}.
     * <pre>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6262'>&nbsp;@&nbsp;</font><font style='background-color: #FF6262; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6262;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6262; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SALMON = -0x1.2b69p126F;
    static { NAMED.put("Salmon", -0x1.2b69p126F); LIST.add(-0x1.2b69p126F); }

    /**
     * This color constant "Taxicab Yellow" has RGBA8888 code {@code F6BD31FF}, intensity 0.654902, protan 0.5529412, tritan 0.7882353, alpha 1.0, hue 0.12299676, and saturation 0.5616685.
     * It can be represented as a packed float with the constant {@code -0x1.931b4ep126F}.
     * <pre>
     * <font style='background-color: #F6BD31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6BD31; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6BD31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F6BD31'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F6BD31'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F6BD31'>&nbsp;@&nbsp;</font><font style='background-color: #F6BD31; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F6BD31;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F6BD31; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TAXICAB_YELLOW = -0x1.931b4ep126F;
    static { NAMED.put("Taxicab Yellow", -0x1.931b4ep126F); LIST.add(-0x1.931b4ep126F); }

    /**
     * This color constant "Apricot" has RGBA8888 code {@code FFA53CFF}, intensity 0.61960787, protan 0.6039216, tritan 0.7411765, alpha 1.0, hue 0.0944756, and saturation 0.5500944.
     * It can be represented as a packed float with the constant {@code -0x1.7b353cp126F}.
     * <pre>
     * <font style='background-color: #FFA53C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA53C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA53C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFA53C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFA53C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFA53C'>&nbsp;@&nbsp;</font><font style='background-color: #FFA53C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFA53C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFA53C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float APRICOT = -0x1.7b353cp126F;
    static { NAMED.put("Apricot", -0x1.7b353cp126F); LIST.add(-0x1.7b353cp126F); }

    /**
     * This color constant "Burnt Yellow" has RGBA8888 code {@code D79B0FFF}, intensity 0.5254902, protan 0.5568628, tritan 0.7921569, alpha 1.0, hue 0.12151282, and saturation 0.5736112.
     * It can be represented as a packed float with the constant {@code -0x1.951d0cp126F}.
     * <pre>
     * <font style='background-color: #D79B0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D79B0F; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D79B0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D79B0F'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D79B0F'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D79B0F'>&nbsp;@&nbsp;</font><font style='background-color: #D79B0F; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D79B0F;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D79B0F; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BURNT_YELLOW = -0x1.951d0cp126F;
    static { NAMED.put("Burnt Yellow", -0x1.951d0cp126F); LIST.add(-0x1.951d0cp126F); }

    /**
     * This color constant "Dry Pepper" has RGBA8888 code {@code DA6E0AFF}, intensity 0.41960785, protan 0.627451, tritan 0.74509805, alpha 1.0, hue 0.08610619, and saturation 0.58838636.
     * It can be represented as a packed float with the constant {@code -0x1.7d40d6p126F}.
     * <pre>
     * <font style='background-color: #DA6E0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA6E0A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA6E0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA6E0A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA6E0A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA6E0A'>&nbsp;@&nbsp;</font><font style='background-color: #DA6E0A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA6E0A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA6E0A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRY_PEPPER = -0x1.7d40d6p126F;
    static { NAMED.put("Dry Pepper", -0x1.7d40d6p126F); LIST.add(-0x1.7d40d6p126F); }

    /**
     * This color constant "Redwood" has RGBA8888 code {@code B45A00FF}, intensity 0.3372549, protan 0.6039216, tritan 0.7137255, alpha 1.0, hue 0.08916507, and saturation 0.5033756.
     * It can be represented as a packed float with the constant {@code -0x1.6d34acp126F}.
     * <pre>
     * <font style='background-color: #B45A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B45A00; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B45A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B45A00'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B45A00'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B45A00'>&nbsp;@&nbsp;</font><font style='background-color: #B45A00; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B45A00;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B45A00; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REDWOOD = -0x1.6d34acp126F;
    static { NAMED.put("Redwood", -0x1.6d34acp126F); LIST.add(-0x1.6d34acp126F); }

    /**
     * This color constant "Koa" has RGBA8888 code {@code A04B05FF}, intensity 0.29411766, protan 0.6, tritan 0.6745098, alpha 1.0, hue 0.08152203, and saturation 0.4313682.
     * It can be represented as a packed float with the constant {@code -0x1.593296p126F}.
     * <pre>
     * <font style='background-color: #A04B05;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A04B05; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A04B05;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A04B05'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A04B05'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A04B05'>&nbsp;@&nbsp;</font><font style='background-color: #A04B05; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A04B05;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A04B05; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float KOA = -0x1.593296p126F;
    static { NAMED.put("Koa", -0x1.593296p126F); LIST.add(-0x1.593296p126F); }

    /**
     * This color constant "Ochre" has RGBA8888 code {@code 5F3214FF}, intensity 0.20392157, protan 0.5529412, tritan 0.5803922, alpha 1.0, hue 0.07468466, and saturation 0.20795827.
     * It can be represented as a packed float with the constant {@code -0x1.291a68p126F}.
     * <pre>
     * <font style='background-color: #5F3214;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F3214; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F3214;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5F3214'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5F3214'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5F3214'>&nbsp;@&nbsp;</font><font style='background-color: #5F3214; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5F3214;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5F3214; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float OCHRE = -0x1.291a68p126F;
    static { NAMED.put("Ochre", -0x1.291a68p126F); LIST.add(-0x1.291a68p126F); }

    /**
     * This color constant "Dull Green" has RGBA8888 code {@code 53500AFF}, intensity 0.2509804, protan 0.49411765, tritan 0.627451, alpha 1.0, hue 0.15987618, and saturation 0.20899272.
     * It can be represented as a packed float with the constant {@code -0x1.40fc8p126F}.
     * <pre>
     * <font style='background-color: #53500A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53500A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53500A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #53500A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #53500A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #53500A'>&nbsp;@&nbsp;</font><font style='background-color: #53500A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #53500A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #53500A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_GREEN = -0x1.40fc8p126F;
    static { NAMED.put("Dull Green", -0x1.40fc8p126F); LIST.add(-0x1.40fc8p126F); }

    /**
     * This color constant "Army Green" has RGBA8888 code {@code 626200FF}, intensity 0.29411766, protan 0.4862745, tritan 0.6784314, alpha 1.0, hue 0.16588454, and saturation 0.28521127.
     * It can be represented as a packed float with the constant {@code -0x1.5af896p126F}.
     * <pre>
     * <font style='background-color: #626200;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626200; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626200;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #626200'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #626200'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #626200'>&nbsp;@&nbsp;</font><font style='background-color: #626200; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #626200;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #626200; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ARMY_GREEN = -0x1.5af896p126F;
    static { NAMED.put("Army Green", -0x1.5af896p126F); LIST.add(-0x1.5af896p126F); }

    /**
     * This color constant "Driftwood" has RGBA8888 code {@code 8C805AFF}, intensity 0.4745098, protan 0.50980395, tritan 0.5764706, alpha 1.0, hue 0.1309171, and saturation 0.14331675.
     * It can be represented as a packed float with the constant {@code -0x1.2704f2p126F}.
     * <pre>
     * <font style='background-color: #8C805A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C805A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C805A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C805A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C805A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C805A'>&nbsp;@&nbsp;</font><font style='background-color: #8C805A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C805A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C805A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRIFTWOOD = -0x1.2704f2p126F;
    static { NAMED.put("Driftwood", -0x1.2704f2p126F); LIST.add(-0x1.2704f2p126F); }

    /**
     * This color constant "Dry Brush" has RGBA8888 code {@code AC9400FF}, intensity 0.4627451, protan 0.50980395, tritan 0.78431374, alpha 1.0, hue 0.14543693, and saturation 0.49702296.
     * It can be represented as a packed float with the constant {@code -0x1.9104ecp126F}.
     * <pre>
     * <font style='background-color: #AC9400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC9400; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC9400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #AC9400'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #AC9400'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #AC9400'>&nbsp;@&nbsp;</font><font style='background-color: #AC9400; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #AC9400;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #AC9400; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DRY_BRUSH = -0x1.9104ecp126F;
    static { NAMED.put("Dry Brush", -0x1.9104ecp126F); LIST.add(-0x1.9104ecp126F); }

    /**
     * This color constant "Mush" has RGBA8888 code {@code B1B10AFF}, intensity 0.5411765, protan 0.4745098, tritan 0.80784315, alpha 1.0, hue 0.16707402, and saturation 0.49083492.
     * It can be represented as a packed float with the constant {@code -0x1.9cf314p126F}.
     * <pre>
     * <font style='background-color: #B1B10A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1B10A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1B10A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B1B10A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B1B10A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B1B10A'>&nbsp;@&nbsp;</font><font style='background-color: #B1B10A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B1B10A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B1B10A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MUSH = -0x1.9cf314p126F;
    static { NAMED.put("Mush", -0x1.9cf314p126F); LIST.add(-0x1.9cf314p126F); }

    /**
     * This color constant "Banana Pudding" has RGBA8888 code {@code E6D55AFF}, intensity 0.7372549, protan 0.5058824, tritan 0.73333335, alpha 1.0, hue 0.14705883, and saturation 0.40499723.
     * It can be represented as a packed float with the constant {@code -0x1.770378p126F}.
     * <pre>
     * <font style='background-color: #E6D55A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6D55A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6D55A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E6D55A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E6D55A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E6D55A'>&nbsp;@&nbsp;</font><font style='background-color: #E6D55A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E6D55A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E6D55A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BANANA_PUDDING = -0x1.770378p126F;
    static { NAMED.put("Banana Pudding", -0x1.770378p126F); LIST.add(-0x1.770378p126F); }

    /**
     * This color constant "Saffron" has RGBA8888 code {@code FFD510FF}, intensity 0.6862745, protan 0.5254902, tritan 0.8862745, alpha 1.0, hue 0.14027965, and saturation 0.69162154.
     * It can be represented as a packed float with the constant {@code -0x1.c50d5ep126F}.
     * <pre>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFD510'>&nbsp;@&nbsp;</font><font style='background-color: #FFD510; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFD510;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFD510; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SAFFRON = -0x1.c50d5ep126F;
    static { NAMED.put("Saffron", -0x1.c50d5ep126F); LIST.add(-0x1.c50d5ep126F); }

    /**
     * This color constant "Pencil Yellow" has RGBA8888 code {@code FFEA4AFF}, intensity 0.7882353, protan 0.5058824, tritan 0.8039216, alpha 1.0, hue 0.14804006, and saturation 0.5251245.
     * It can be represented as a packed float with the constant {@code -0x1.9b0392p126F}.
     * <pre>
     * <font style='background-color: #FFEA4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEA4A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEA4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FFEA4A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FFEA4A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FFEA4A'>&nbsp;@&nbsp;</font><font style='background-color: #FFEA4A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FFEA4A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FFEA4A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PENCIL_YELLOW = -0x1.9b0392p126F;
    static { NAMED.put("Pencil Yellow", -0x1.9b0392p126F); LIST.add(-0x1.9b0392p126F); }

    /**
     * This color constant "Chartreuse" has RGBA8888 code {@code C8FF41FF}, intensity 0.78431374, protan 0.4, tritan 0.81960785, alpha 1.0, hue 0.20988058, and saturation 0.5528817.
     * It can be represented as a packed float with the constant {@code -0x1.a2cd9p126F}.
     * <pre>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C8FF41'>&nbsp;@&nbsp;</font><font style='background-color: #C8FF41; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C8FF41;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C8FF41; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHARTREUSE = -0x1.a2cd9p126F;
    static { NAMED.put("Chartreuse", -0x1.a2cd9p126F); LIST.add(-0x1.a2cd9p126F); }

    /**
     * This color constant "Absinthe" has RGBA8888 code {@code 9BF046FF}, intensity 0.72156864, protan 0.3647059, tritan 0.7647059, alpha 1.0, hue 0.2417194, and saturation 0.4887647.
     * It can be represented as a packed float with the constant {@code -0x1.86bb7p126F}.
     * <pre>
     * <font style='background-color: #9BF046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BF046; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BF046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9BF046'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9BF046'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9BF046'>&nbsp;@&nbsp;</font><font style='background-color: #9BF046; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9BF046;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9BF046; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ABSINTHE = -0x1.86bb7p126F;
    static { NAMED.put("Absinthe", -0x1.86bb7p126F); LIST.add(-0x1.86bb7p126F); }

    /**
     * This color constant "Infection" has RGBA8888 code {@code 96DC19FF}, intensity 0.6313726, protan 0.38039216, tritan 0.81960785, alpha 1.0, hue 0.22017044, and saturation 0.56434405.
     * It can be represented as a packed float with the constant {@code -0x1.a2c342p126F}.
     * <pre>
     * <font style='background-color: #96DC19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96DC19; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96DC19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #96DC19'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #96DC19'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #96DC19'>&nbsp;@&nbsp;</font><font style='background-color: #96DC19; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #96DC19;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #96DC19; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float INFECTION = -0x1.a2c342p126F;
    static { NAMED.put("Infection", -0x1.a2c342p126F); LIST.add(-0x1.a2c342p126F); }

    /**
     * This color constant "Frog Green" has RGBA8888 code {@code 73C805FF}, intensity 0.5411765, protan 0.36078432, tritan 0.8117647, alpha 1.0, hue 0.23178057, and saturation 0.5637115.
     * It can be represented as a packed float with the constant {@code -0x1.9eb914p126F}.
     * <pre>
     * <font style='background-color: #73C805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73C805; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73C805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #73C805'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #73C805'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #73C805'>&nbsp;@&nbsp;</font><font style='background-color: #73C805; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #73C805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #73C805; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FROG_GREEN = -0x1.9eb914p126F;
    static { NAMED.put("Frog Green", -0x1.9eb914p126F); LIST.add(-0x1.9eb914p126F); }

    /**
     * This color constant "Avocado" has RGBA8888 code {@code 6AA805FF}, intensity 0.4627451, protan 0.39607844, tritan 0.7647059, alpha 1.0, hue 0.22316703, and saturation 0.47023407.
     * It can be represented as a packed float with the constant {@code -0x1.86caecp126F}.
     * <pre>
     * <font style='background-color: #6AA805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AA805; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AA805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6AA805'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6AA805'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6AA805'>&nbsp;@&nbsp;</font><font style='background-color: #6AA805; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AA805;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AA805; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float AVOCADO = -0x1.86caecp126F;
    static { NAMED.put("Avocado", -0x1.86caecp126F); LIST.add(-0x1.86caecp126F); }

    /**
     * This color constant "Woodlands" has RGBA8888 code {@code 3C6E14FF}, intensity 0.3137255, protan 0.41960785, tritan 0.63529414, alpha 1.0, hue 0.2538926, and saturation 0.25639704.
     * It can be represented as a packed float with the constant {@code -0x1.44d6ap126F}.
     * <pre>
     * <font style='background-color: #3C6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C6E14; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C6E14'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C6E14'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C6E14'>&nbsp;@&nbsp;</font><font style='background-color: #3C6E14; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C6E14;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C6E14; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WOODLANDS = -0x1.44d6ap126F;
    static { NAMED.put("Woodlands", -0x1.44d6ap126F); LIST.add(-0x1.44d6ap126F); }

    /**
     * This color constant "Dark Pine" has RGBA8888 code {@code 283405FF}, intensity 0.15294118, protan 0.4745098, tritan 0.5764706, alpha 1.0, hue 0.21335015, and saturation 0.13319024.
     * It can be represented as a packed float with the constant {@code -0x1.26f24ep126F}.
     * <pre>
     * <font style='background-color: #283405;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #283405; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #283405;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #283405'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #283405'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #283405'>&nbsp;@&nbsp;</font><font style='background-color: #283405; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #283405;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #283405; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DARK_PINE = -0x1.26f24ep126F;
    static { NAMED.put("Dark Pine", -0x1.26f24ep126F); LIST.add(-0x1.26f24ep126F); }

    /**
     * This color constant "Moss Green" has RGBA8888 code {@code 204608FF}, intensity 0.1882353, protan 0.4392157, tritan 0.5921569, alpha 1.0, hue 0.2630397, and saturation 0.17817724.
     * It can be represented as a packed float with the constant {@code -0x1.2ee06p126F}.
     * <pre>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #204608'>&nbsp;@&nbsp;</font><font style='background-color: #204608; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #204608;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #204608; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MOSS_GREEN = -0x1.2ee06p126F;
    static { NAMED.put("Moss Green", -0x1.2ee06p126F); LIST.add(-0x1.2ee06p126F); }

    /**
     * This color constant "Fern Green" has RGBA8888 code {@code 0C5C0CFF}, intensity 0.22745098, protan 0.38431373, tritan 0.6, alpha 1.0, hue 0.32231358, and saturation 0.22256732.
     * It can be represented as a packed float with the constant {@code -0x1.32c474p126F}.
     * <pre>
     * <font style='background-color: #0C5C0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C5C0C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C5C0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0C5C0C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0C5C0C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0C5C0C'>&nbsp;@&nbsp;</font><font style='background-color: #0C5C0C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C5C0C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C5C0C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FERN_GREEN = -0x1.32c474p126F;
    static { NAMED.put("Fern Green", -0x1.32c474p126F); LIST.add(-0x1.32c474p126F); }

    /**
     * This color constant "Forest Glen" has RGBA8888 code {@code 149605FF}, intensity 0.36078432, protan 0.30980393, tritan 0.69411767, alpha 1.0, hue 0.3030791, and saturation 0.41181758.
     * It can be represented as a packed float with the constant {@code -0x1.629eb8p126F}.
     * <pre>
     * <font style='background-color: #149605;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #149605; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #149605;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #149605'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #149605'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #149605'>&nbsp;@&nbsp;</font><font style='background-color: #149605; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #149605;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #149605; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FOREST_GLEN = -0x1.629eb8p126F;
    static { NAMED.put("Forest Glen", -0x1.629eb8p126F); LIST.add(-0x1.629eb8p126F); }

    /**
     * This color constant "Malachite" has RGBA8888 code {@code 0AD70AFF}, intensity 0.5019608, protan 0.2, tritan 0.7607843, alpha 1.0, hue 0.32164037, and saturation 0.5793923.
     * It can be represented as a packed float with the constant {@code -0x1.8467p126F}.
     * <pre>
     * <font style='background-color: #0AD70A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0AD70A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0AD70A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0AD70A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0AD70A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0AD70A'>&nbsp;@&nbsp;</font><font style='background-color: #0AD70A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0AD70A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0AD70A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MALACHITE = -0x1.8467p126F;
    static { NAMED.put("Malachite", -0x1.8467p126F); LIST.add(-0x1.8467p126F); }

    /**
     * This color constant "Apple Green" has RGBA8888 code {@code 14E60AFF}, intensity 0.54509807, protan 0.19215687, tritan 0.78431374, alpha 1.0, hue 0.31448677, and saturation 0.62037235.
     * It can be represented as a packed float with the constant {@code -0x1.906316p126F}.
     * <pre>
     * <font style='background-color: #14E60A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14E60A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14E60A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #14E60A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #14E60A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #14E60A'>&nbsp;@&nbsp;</font><font style='background-color: #14E60A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #14E60A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #14E60A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float APPLE_GREEN = -0x1.906316p126F;
    static { NAMED.put("Apple Green", -0x1.906316p126F); LIST.add(-0x1.906316p126F); }

    /**
     * This color constant "Celery" has RGBA8888 code {@code 7DFF73FF}, intensity 0.7764706, protan 0.30980393, tritan 0.68235296, alpha 1.0, hue 0.31016648, and saturation 0.3937971.
     * It can be represented as a packed float with the constant {@code -0x1.5c9f8cp126F}.
     * <pre>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DFF73'>&nbsp;@&nbsp;</font><font style='background-color: #7DFF73; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DFF73;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DFF73; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CELERY = -0x1.5c9f8cp126F;
    static { NAMED.put("Celery", -0x1.5c9f8cp126F); LIST.add(-0x1.5c9f8cp126F); }

    /**
     * This color constant "Mint Green" has RGBA8888 code {@code 4BF05AFF}, intensity 0.68235296, protan 0.2627451, tritan 0.68235296, alpha 1.0, hue 0.3366296, and saturation 0.42991275.
     * It can be represented as a packed float with the constant {@code -0x1.5c875cp126F}.
     * <pre>
     * <font style='background-color: #4BF05A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4BF05A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4BF05A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4BF05A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4BF05A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4BF05A'>&nbsp;@&nbsp;</font><font style='background-color: #4BF05A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4BF05A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4BF05A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MINT_GREEN = -0x1.5c875cp126F;
    static { NAMED.put("Mint Green", -0x1.5c875cp126F); LIST.add(-0x1.5c875cp126F); }

    /**
     * This color constant "Emerald" has RGBA8888 code {@code 00C514FF}, intensity 0.4627451, protan 0.21568628, tritan 0.7137255, alpha 1.0, hue 0.33926445, and saturation 0.51580566.
     * It can be represented as a packed float with the constant {@code -0x1.6c6eecp126F}.
     * <pre>
     * <font style='background-color: #00C514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00C514; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00C514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00C514'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00C514'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00C514'>&nbsp;@&nbsp;</font><font style='background-color: #00C514; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00C514;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00C514; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EMERALD = -0x1.6c6eecp126F;
    static { NAMED.put("Emerald", -0x1.6c6eecp126F); LIST.add(-0x1.6c6eecp126F); }

    /**
     * This color constant "Prase" has RGBA8888 code {@code 05B450FF}, intensity 0.48235294, protan 0.25490198, tritan 0.58431375, alpha 1.0, hue 0.40063295, and saturation 0.460348.
     * It can be represented as a packed float with the constant {@code -0x1.2a82f6p126F}.
     * <pre>
     * <font style='background-color: #05B450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #05B450; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #05B450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #05B450'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #05B450'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #05B450'>&nbsp;@&nbsp;</font><font style='background-color: #05B450; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #05B450;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #05B450; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PRASE = -0x1.2a82f6p126F;
    static { NAMED.put("Prase", -0x1.2a82f6p126F); LIST.add(-0x1.2a82f6p126F); }

    /**
     * This color constant "Eucalyptus" has RGBA8888 code {@code 1C8C4EFF}, intensity 0.40784314, protan 0.34117648, tritan 0.54901963, alpha 1.0, hue 0.40574968, and saturation 0.29922032.
     * It can be represented as a packed float with the constant {@code -0x1.18aedp126F}.
     * <pre>
     * <font style='background-color: #1C8C4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C8C4E; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C8C4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #1C8C4E'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #1C8C4E'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #1C8C4E'>&nbsp;@&nbsp;</font><font style='background-color: #1C8C4E; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #1C8C4E;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #1C8C4E; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EUCALYPTUS = -0x1.18aedp126F;
    static { NAMED.put("Eucalyptus", -0x1.18aedp126F); LIST.add(-0x1.18aedp126F); }

    /**
     * This color constant "Zucchini" has RGBA8888 code {@code 123832FF}, intensity 0.18431373, protan 0.44705883, tritan 0.4862745, alpha 1.0, hue 0.48409876, and saturation 0.10446866.
     * It can be represented as a packed float with the constant {@code -0x1.f8e45ep125F}.
     * <pre>
     * <font style='background-color: #123832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #123832; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #123832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #123832'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #123832'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #123832'>&nbsp;@&nbsp;</font><font style='background-color: #123832; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #123832;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #123832; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ZUCCHINI = -0x1.f8e45ep125F;
    static { NAMED.put("Zucchini", -0x1.f8e45ep125F); LIST.add(-0x1.f8e45ep125F); }

    /**
     * This color constant "Soft Teal" has RGBA8888 code {@code 129880FF}, intensity 0.4745098, protan 0.31764707, tritan 0.46666667, alpha 1.0, hue 0.4739861, and saturation 0.35759228.
     * It can be represented as a packed float with the constant {@code -0x1.eea2f2p125F}.
     * <pre>
     * <font style='background-color: #129880;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #129880; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #129880;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #129880'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #129880'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #129880'>&nbsp;@&nbsp;</font><font style='background-color: #129880; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #129880;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #129880; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOFT_TEAL = -0x1.eea2f2p125F;
    static { NAMED.put("Soft Teal", -0x1.eea2f2p125F); LIST.add(-0x1.eea2f2p125F); }

    /**
     * This color constant "Medium Teal" has RGBA8888 code {@code 06C491FF}, intensity 0.5803922, protan 0.24313726, tritan 0.4862745, alpha 1.0, hue 0.45655748, and saturation 0.49846685.
     * It can be represented as a packed float with the constant {@code -0x1.f87d28p125F}.
     * <pre>
     * <font style='background-color: #06C491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #06C491; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #06C491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #06C491'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #06C491'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #06C491'>&nbsp;@&nbsp;</font><font style='background-color: #06C491; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #06C491;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #06C491; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUM_TEAL = -0x1.f87d28p125F;
    static { NAMED.put("Medium Teal", -0x1.f87d28p125F); LIST.add(-0x1.f87d28p125F); }

    /**
     * This color constant "Spring Green" has RGBA8888 code {@code 00DE6AFF}, intensity 0.6, protan 0.19215687, tritan 0.5882353, alpha 1.0, hue 0.4089358, and saturation 0.5809622.
     * It can be represented as a packed float with the constant {@code -0x1.2c6332p126F}.
     * <pre>
     * <font style='background-color: #00DE6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DE6A; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DE6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00DE6A'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00DE6A'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00DE6A'>&nbsp;@&nbsp;</font><font style='background-color: #00DE6A; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00DE6A;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00DE6A; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SPRING_GREEN = -0x1.2c6332p126F;
    static { NAMED.put("Spring Green", -0x1.2c6332p126F); LIST.add(-0x1.2c6332p126F); }

    /**
     * This color constant "Turquoise" has RGBA8888 code {@code 2DEBA8FF}, intensity 0.7176471, protan 0.23921569, tritan 0.5137255, alpha 1.0, hue 0.44197485, and saturation 0.5017905.
     * It can be represented as a packed float with the constant {@code -0x1.067b6ep126F}.
     * <pre>
     * <font style='background-color: #2DEBA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2DEBA8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2DEBA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2DEBA8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2DEBA8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2DEBA8'>&nbsp;@&nbsp;</font><font style='background-color: #2DEBA8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2DEBA8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2DEBA8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float TURQUOISE = -0x1.067b6ep126F;
    static { NAMED.put("Turquoise", -0x1.067b6ep126F); LIST.add(-0x1.067b6ep126F); }

    /**
     * This color constant "Seafoam" has RGBA8888 code {@code 3CFEA5FF}, intensity 0.76862746, protan 0.23137255, tritan 0.5529412, alpha 1.0, hue 0.42171994, and saturation 0.5108137.
     * It can be represented as a packed float with the constant {@code -0x1.1a7788p126F}.
     * <pre>
     * <font style='background-color: #3CFEA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3CFEA5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3CFEA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3CFEA5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3CFEA5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3CFEA5'>&nbsp;@&nbsp;</font><font style='background-color: #3CFEA5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3CFEA5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3CFEA5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SEAFOAM = -0x1.1a7788p126F;
    static { NAMED.put("Seafoam", -0x1.1a7788p126F); LIST.add(-0x1.1a7788p126F); }

    /**
     * This color constant "Variscite" has RGBA8888 code {@code 6AFFCDFF}, intensity 0.84313726, protan 0.29411766, tritan 0.5058824, alpha 1.0, hue 0.44527215, and saturation 0.39702934.
     * It can be represented as a packed float with the constant {@code -0x1.0297aep126F}.
     * <pre>
     * <font style='background-color: #6AFFCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AFFCD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AFFCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6AFFCD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6AFFCD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6AFFCD'>&nbsp;@&nbsp;</font><font style='background-color: #6AFFCD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6AFFCD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6AFFCD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VARISCITE = -0x1.0297aep126F;
    static { NAMED.put("Variscite", -0x1.0297aep126F); LIST.add(-0x1.0297aep126F); }

    /**
     * This color constant "Refreshing Mist" has RGBA8888 code {@code 91EBFFFF}, intensity 0.8745098, protan 0.38431373, tritan 0.4117647, alpha 1.0, hue 0.53710145, and saturation 0.3056156.
     * It can be represented as a packed float with the constant {@code -0x1.d2c5bep125F}.
     * <pre>
     * <font style='background-color: #91EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91EBFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #91EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #91EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #91EBFF'>&nbsp;@&nbsp;</font><font style='background-color: #91EBFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #91EBFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #91EBFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float REFRESHING_MIST = -0x1.d2c5bep125F;
    static { NAMED.put("Refreshing Mist", -0x1.d2c5bep125F); LIST.add(-0x1.d2c5bep125F); }

    /**
     * This color constant "Shining Sky" has RGBA8888 code {@code 55E6FFFF}, intensity 0.8156863, protan 0.30980393, tritan 0.36862746, alpha 1.0, hue 0.5316286, and saturation 0.47915676.
     * It can be represented as a packed float with the constant {@code -0x1.bc9fap125F}.
     * <pre>
     * <font style='background-color: #55E6FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55E6FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55E6FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #55E6FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #55E6FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #55E6FF'>&nbsp;@&nbsp;</font><font style='background-color: #55E6FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #55E6FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #55E6FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHINING_SKY = -0x1.bc9fap125F;
    static { NAMED.put("Shining Sky", -0x1.bc9fap125F); LIST.add(-0x1.bc9fap125F); }

    /**
     * This color constant "Steam" has RGBA8888 code {@code 7DD7F0FF}, intensity 0.8, protan 0.38431373, tritan 0.4, alpha 1.0, hue 0.5441386, and saturation 0.32563704.
     * It can be represented as a packed float with the constant {@code -0x1.ccc598p125F}.
     * <pre>
     * <font style='background-color: #7DD7F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DD7F0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DD7F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7DD7F0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7DD7F0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7DD7F0'>&nbsp;@&nbsp;</font><font style='background-color: #7DD7F0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7DD7F0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7DD7F0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STEAM = -0x1.ccc598p125F;
    static { NAMED.put("Steam", -0x1.ccc598p125F); LIST.add(-0x1.ccc598p125F); }

    /**
     * This color constant "Robin Egg Blue" has RGBA8888 code {@code 08DED5FF}, intensity 0.7019608, protan 0.21568628, tritan 0.39607844, alpha 1.0, hue 0.4980145, and saturation 0.5656618.
     * It can be represented as a packed float with the constant {@code -0x1.ca6f66p125F}.
     * <pre>
     * <font style='background-color: #08DED5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #08DED5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #08DED5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #08DED5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #08DED5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #08DED5'>&nbsp;@&nbsp;</font><font style='background-color: #08DED5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #08DED5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #08DED5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROBIN_EGG_BLUE = -0x1.ca6f66p125F;
    static { NAMED.put("Robin Egg Blue", -0x1.ca6f66p125F); LIST.add(-0x1.ca6f66p125F); }

    /**
     * This color constant "Denim Blue" has RGBA8888 code {@code 109CDEFF}, intensity 0.5686275, protan 0.32156864, tritan 0.29803923, alpha 1.0, hue 0.5591293, and saturation 0.58347595.
     * It can be represented as a packed float with the constant {@code -0x1.98a522p125F}.
     * <pre>
     * <font style='background-color: #109CDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #109CDE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #109CDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #109CDE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #109CDE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #109CDE'>&nbsp;@&nbsp;</font><font style='background-color: #109CDE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #109CDE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #109CDE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DENIM_BLUE = -0x1.98a522p125F;
    static { NAMED.put("Denim Blue", -0x1.98a522p125F); LIST.add(-0x1.98a522p125F); }

    /**
     * This color constant "Deep Teal" has RGBA8888 code {@code 055A5CFF}, intensity 0.2901961, protan 0.3882353, tritan 0.44705883, alpha 1.0, hue 0.51111495, and saturation 0.24028781.
     * It can be represented as a packed float with the constant {@code -0x1.e4c694p125F}.
     * <pre>
     * <font style='background-color: #055A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #055A5C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #055A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #055A5C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #055A5C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #055A5C'>&nbsp;@&nbsp;</font><font style='background-color: #055A5C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #055A5C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #055A5C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DEEP_TEAL = -0x1.e4c694p125F;
    static { NAMED.put("Deep Teal", -0x1.e4c694p125F); LIST.add(-0x1.e4c694p125F); }

    /**
     * This color constant "Navy Blue" has RGBA8888 code {@code 162C52FF}, intensity 0.19215687, protan 0.4745098, tritan 0.41568628, alpha 1.0, hue 0.6080481, and saturation 0.17773776.
     * It can be represented as a packed float with the constant {@code -0x1.d4f262p125F}.
     * <pre>
     * <font style='background-color: #162C52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #162C52; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #162C52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #162C52'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #162C52'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #162C52'>&nbsp;@&nbsp;</font><font style='background-color: #162C52; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #162C52;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #162C52; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float NAVY_BLUE = -0x1.d4f262p125F;
    static { NAMED.put("Navy Blue", -0x1.d4f262p125F); LIST.add(-0x1.d4f262p125F); }

    /**
     * This color constant "Blueberry" has RGBA8888 code {@code 0F377DFF}, intensity 0.2509804, protan 0.45490196, tritan 0.34509805, alpha 1.0, hue 0.609367, and saturation 0.3242139.
     * It can be represented as a packed float with the constant {@code -0x1.b0e88p125F}.
     * <pre>
     * <font style='background-color: #0F377D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F377D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F377D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0F377D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0F377D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0F377D'>&nbsp;@&nbsp;</font><font style='background-color: #0F377D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0F377D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0F377D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUEBERRY = -0x1.b0e88p125F;
    static { NAMED.put("Blueberry", -0x1.b0e88p125F); LIST.add(-0x1.b0e88p125F); }

    /**
     * This color constant "Prussian Blue" has RGBA8888 code {@code 004A9CFF}, intensity 0.30980393, protan 0.4117647, tritan 0.30588236, alpha 1.0, hue 0.5920638, and saturation 0.44892055.
     * It can be represented as a packed float with the constant {@code -0x1.9cd29ep125F}.
     * <pre>
     * <font style='background-color: #004A9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #004A9C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #004A9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #004A9C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #004A9C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #004A9C'>&nbsp;@&nbsp;</font><font style='background-color: #004A9C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #004A9C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #004A9C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PRUSSIAN_BLUE = -0x1.9cd29ep125F;
    static { NAMED.put("Prussian Blue", -0x1.9cd29ep125F); LIST.add(-0x1.9cd29ep125F); }

    /**
     * This color constant "Desert Rain" has RGBA8888 code {@code 326496FF}, intensity 0.4, protan 0.4392157, tritan 0.3764706, alpha 1.0, hue 0.58850145, and saturation 0.2919029.
     * It can be represented as a packed float with the constant {@code -0x1.c0e0ccp125F}.
     * <pre>
     * <font style='background-color: #326496;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #326496; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #326496;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #326496'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #326496'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #326496'>&nbsp;@&nbsp;</font><font style='background-color: #326496; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #326496;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #326496; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DESERT_RAIN = -0x1.c0e0ccp125F;
    static { NAMED.put("Desert Rain", -0x1.c0e0ccp125F); LIST.add(-0x1.c0e0ccp125F); }

    /**
     * This color constant "Electric Blue" has RGBA8888 code {@code 0052F6FF}, intensity 0.4117647, protan 0.4117647, tritan 0.14901961, alpha 1.0, hue 0.6141525, and saturation 0.7158676.
     * It can be represented as a packed float with the constant {@code -0x1.4cd2d2p125F}.
     * <pre>
     * <font style='background-color: #0052F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0052F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0052F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0052F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0052F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0052F6'>&nbsp;@&nbsp;</font><font style='background-color: #0052F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0052F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0052F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ELECTRIC_BLUE = -0x1.4cd2d2p125F;
    static { NAMED.put("Electric Blue", -0x1.4cd2d2p125F); LIST.add(-0x1.4cd2d2p125F); }

    /**
     * This color constant "Hidden Blue" has RGBA8888 code {@code 186ABDFF}, intensity 0.43137255, protan 0.4, tritan 0.29803923, alpha 1.0, hue 0.588248, and saturation 0.478077.
     * It can be represented as a packed float with the constant {@code -0x1.98ccdcp125F}.
     * <pre>
     * <font style='background-color: #186ABD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #186ABD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #186ABD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #186ABD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #186ABD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #186ABD'>&nbsp;@&nbsp;</font><font style='background-color: #186ABD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #186ABD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #186ABD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HIDDEN_BLUE = -0x1.98ccdcp125F;
    static { NAMED.put("Hidden Blue", -0x1.98ccdcp125F); LIST.add(-0x1.98ccdcp125F); }

    /**
     * This color constant "Dull Azure" has RGBA8888 code {@code 2378DCFF}, intensity 0.49803922, protan 0.4, tritan 0.26666668, alpha 1.0, hue 0.5945765, and saturation 0.5314659.
     * It can be represented as a packed float with the constant {@code -0x1.88ccfep125F}.
     * <pre>
     * <font style='background-color: #2378DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2378DC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2378DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #2378DC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #2378DC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #2378DC'>&nbsp;@&nbsp;</font><font style='background-color: #2378DC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #2378DC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #2378DC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float DULL_AZURE = -0x1.88ccfep125F;
    static { NAMED.put("Dull Azure", -0x1.88ccfep125F); LIST.add(-0x1.88ccfep125F); }

    /**
     * This color constant "Ripped Denim" has RGBA8888 code {@code 699DC3FF}, intensity 0.6117647, protan 0.43529412, tritan 0.4, alpha 1.0, hue 0.5753597, and saturation 0.25712866.
     * It can be represented as a packed float with the constant {@code -0x1.ccdf38p125F}.
     * <pre>
     * <font style='background-color: #699DC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #699DC3; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #699DC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #699DC3'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #699DC3'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #699DC3'>&nbsp;@&nbsp;</font><font style='background-color: #699DC3; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #699DC3;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #699DC3; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RIPPED_DENIM = -0x1.ccdf38p125F;
    static { NAMED.put("Ripped Denim", -0x1.ccdf38p125F); LIST.add(-0x1.ccdf38p125F); }

    /**
     * This color constant "Calm Sky" has RGBA8888 code {@code 4AA4FFFF}, intensity 0.65882355, protan 0.39215687, tritan 0.2784314, alpha 1.0, hue 0.58899975, and saturation 0.52198315.
     * It can be represented as a packed float with the constant {@code -0x1.8ec95p125F}.
     * <pre>
     * <font style='background-color: #4AA4FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4AA4FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4AA4FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4AA4FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4AA4FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4AA4FF'>&nbsp;@&nbsp;</font><font style='background-color: #4AA4FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4AA4FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4AA4FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CALM_SKY = -0x1.8ec95p125F;
    static { NAMED.put("Calm Sky", -0x1.8ec95p125F); LIST.add(-0x1.8ec95p125F); }

    /**
     * This color constant "Vapor" has RGBA8888 code {@code 90B0FFFF}, intensity 0.7372549, protan 0.46666667, tritan 0.33333334, alpha 1.0, hue 0.62060565, and saturation 0.3284197.
     * It can be represented as a packed float with the constant {@code -0x1.aaef78p125F}.
     * <pre>
     * <font style='background-color: #90B0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90B0FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90B0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #90B0FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #90B0FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #90B0FF'>&nbsp;@&nbsp;</font><font style='background-color: #90B0FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #90B0FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #90B0FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VAPOR = -0x1.aaef78p125F;
    static { NAMED.put("Vapor", -0x1.aaef78p125F); LIST.add(-0x1.aaef78p125F); }

    /**
     * This color constant "Powder Blue" has RGBA8888 code {@code 5AC5FFFF}, intensity 0.74509805, protan 0.3647059, tritan 0.32941177, alpha 1.0, hue 0.5648877, and saturation 0.47211438.
     * It can be represented as a packed float with the constant {@code -0x1.a8bb7cp125F}.
     * <pre>
     * <font style='background-color: #5AC5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AC5FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AC5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5AC5FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5AC5FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5AC5FF'>&nbsp;@&nbsp;</font><font style='background-color: #5AC5FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5AC5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5AC5FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float POWDER_BLUE = -0x1.a8bb7cp125F;
    static { NAMED.put("Powder Blue", -0x1.a8bb7cp125F); LIST.add(-0x1.a8bb7cp125F); }

    /**
     * This color constant "Suds" has RGBA8888 code {@code BEB9FAFF}, intensity 0.7882353, protan 0.5137255, tritan 0.38039216, alpha 1.0, hue 0.6731396, and saturation 0.19257355.
     * It can be represented as a packed float with the constant {@code -0x1.c30792p125F}.
     * <pre>
     * <font style='background-color: #BEB9FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEB9FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEB9FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BEB9FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BEB9FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BEB9FA'>&nbsp;@&nbsp;</font><font style='background-color: #BEB9FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BEB9FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BEB9FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SUDS = -0x1.c30792p125F;
    static { NAMED.put("Suds", -0x1.c30792p125F); LIST.add(-0x1.c30792p125F); }

    /**
     * This color constant "Strong Cyan" has RGBA8888 code {@code 00BFFFFF}, intensity 0.6666667, protan 0.25490198, tritan 0.27058825, alpha 1.0, hue 0.54867494, and saturation 0.7197816.
     * It can be represented as a packed float with the constant {@code -0x1.8a8354p125F}.
     * <pre>
     * <font style='background-color: #00BFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00BFFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00BFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #00BFFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #00BFFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #00BFFF'>&nbsp;@&nbsp;</font><font style='background-color: #00BFFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #00BFFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #00BFFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float STRONG_CYAN = -0x1.8a8354p125F;
    static { NAMED.put("Strong Cyan", -0x1.8a8354p125F); LIST.add(-0x1.8a8354p125F); }

    /**
     * This color constant "Sharp Azure" has RGBA8888 code {@code 007FFFFF}, intensity 0.52156866, protan 0.34901962, tritan 0.19215687, alpha 1.0, hue 0.5886907, and saturation 0.72677636.
     * It can be represented as a packed float with the constant {@code -0x1.62b30ap125F}.
     * <pre>
     * <font style='background-color: #007FFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007FFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007FFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #007FFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #007FFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #007FFF'>&nbsp;@&nbsp;</font><font style='background-color: #007FFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #007FFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #007FFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHARP_AZURE = -0x1.62b30ap125F;
    static { NAMED.put("Sharp Azure", -0x1.62b30ap125F); LIST.add(-0x1.62b30ap125F); }

    /**
     * This color constant "Blue Eye" has RGBA8888 code {@code 4B7DC8FF}, intensity 0.52156866, protan 0.44313726, tritan 0.33333334, alpha 1.0, hue 0.6036511, and saturation 0.3600425.
     * It can be represented as a packed float with the constant {@code -0x1.aae30ap125F}.
     * <pre>
     * <font style='background-color: #4B7DC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B7DC8; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B7DC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4B7DC8'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4B7DC8'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4B7DC8'>&nbsp;@&nbsp;</font><font style='background-color: #4B7DC8; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4B7DC8;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4B7DC8; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLUE_EYE = -0x1.aae30ap125F;
    static { NAMED.put("Blue Eye", -0x1.aae30ap125F); LIST.add(-0x1.aae30ap125F); }

    /**
     * This color constant "Subtlety" has RGBA8888 code {@code 786EF0FF}, intensity 0.5568628, protan 0.5294118, tritan 0.2627451, alpha 1.0, hue 0.6751065, and saturation 0.38365573.
     * It can be represented as a packed float with the constant {@code -0x1.870f1cp125F}.
     * <pre>
     * <font style='background-color: #786EF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #786EF0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #786EF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #786EF0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #786EF0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #786EF0'>&nbsp;@&nbsp;</font><font style='background-color: #786EF0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #786EF0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #786EF0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SUBTLETY = -0x1.870f1cp125F;
    static { NAMED.put("Subtlety", -0x1.870f1cp125F); LIST.add(-0x1.870f1cp125F); }

    /**
     * This color constant "Rough Sapphire" has RGBA8888 code {@code 4A5AFFFF}, intensity 0.49019608, protan 0.49803922, tritan 0.18431373, alpha 1.0, hue 0.6501722, and saturation 0.5398628.
     * It can be represented as a packed float with the constant {@code -0x1.5efefap125F}.
     * <pre>
     * <font style='background-color: #4A5AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A5AFF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A5AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #4A5AFF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #4A5AFF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #4A5AFF'>&nbsp;@&nbsp;</font><font style='background-color: #4A5AFF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #4A5AFF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #4A5AFF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROUGH_SAPPHIRE = -0x1.5efefap125F;
    static { NAMED.put("Rough Sapphire", -0x1.5efefap125F); LIST.add(-0x1.5efefap125F); }

    /**
     * This color constant "Iris" has RGBA8888 code {@code 6241F6FF}, intensity 0.44313726, protan 0.5647059, tritan 0.18039216, alpha 1.0, hue 0.6900935, and saturation 0.53168005.
     * It can be represented as a packed float with the constant {@code -0x1.5d20e2p125F}.
     * <pre>
     * <font style='background-color: #6241F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6241F6; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6241F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6241F6'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6241F6'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6241F6'>&nbsp;@&nbsp;</font><font style='background-color: #6241F6; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6241F6;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6241F6; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float IRIS = -0x1.5d20e2p125F;
    static { NAMED.put("Iris", -0x1.5d20e2p125F); LIST.add(-0x1.5d20e2p125F); }

    /**
     * This color constant "Cornflower Blue" has RGBA8888 code {@code 3C3CF5FF}, intensity 0.40392157, protan 0.52156866, tritan 0.15686275, alpha 1.0, hue 0.66298676, and saturation 0.55495954.
     * It can be represented as a packed float with the constant {@code -0x1.510acep125F}.
     * <pre>
     * <font style='background-color: #3C3CF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C3CF5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C3CF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #3C3CF5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #3C3CF5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #3C3CF5'>&nbsp;@&nbsp;</font><font style='background-color: #3C3CF5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #3C3CF5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #3C3CF5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CORNFLOWER_BLUE = -0x1.510acep125F;
    static { NAMED.put("Cornflower Blue", -0x1.510acep125F); LIST.add(-0x1.510acep125F); }

    /**
     * This color constant "Polished Sapphire" has RGBA8888 code {@code 101CDAFF}, intensity 0.27450982, protan 0.5058824, tritan 0.14117648, alpha 1.0, hue 0.6542346, and saturation 0.60273564.
     * It can be represented as a packed float with the constant {@code -0x1.49028cp125F}.
     * <pre>
     * <font style='background-color: #101CDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #101CDA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #101CDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #101CDA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #101CDA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #101CDA'>&nbsp;@&nbsp;</font><font style='background-color: #101CDA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #101CDA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #101CDA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float POLISHED_SAPPHIRE = -0x1.49028cp125F;
    static { NAMED.put("Polished Sapphire", -0x1.49028cp125F); LIST.add(-0x1.49028cp125F); }

    /**
     * This color constant "Royal Blue" has RGBA8888 code {@code 0010BDFF}, intensity 0.20784314, protan 0.5019608, tritan 0.16862746, alpha 1.0, hue 0.65235204, and saturation 0.5612904.
     * It can be represented as a packed float with the constant {@code -0x1.57006ap125F}.
     * <pre>
     * <font style='background-color: #0010BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0010BD; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0010BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0010BD'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0010BD'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0010BD'>&nbsp;@&nbsp;</font><font style='background-color: #0010BD; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0010BD;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0010BD; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROYAL_BLUE = -0x1.57006ap125F;
    static { NAMED.put("Royal Blue", -0x1.57006ap125F); LIST.add(-0x1.57006ap125F); }

    /**
     * This color constant "Indigo" has RGBA8888 code {@code 231094FF}, intensity 0.19607843, protan 0.5411765, tritan 0.26666668, alpha 1.0, hue 0.68525416, and saturation 0.3846453.
     * It can be represented as a packed float with the constant {@code -0x1.891464p125F}.
     * <pre>
     * <font style='background-color: #231094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #231094; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #231094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #231094'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #231094'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #231094'>&nbsp;@&nbsp;</font><font style='background-color: #231094; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #231094;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #231094; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float INDIGO = -0x1.891464p125F;
    static { NAMED.put("Indigo", -0x1.891464p125F); LIST.add(-0x1.891464p125F); }

    /**
     * This color constant "Space Blue" has RGBA8888 code {@code 0C2148FF}, intensity 0.14901961, protan 0.4745098, tritan 0.41568628, alpha 1.0, hue 0.60806316, and saturation 0.17773816.
     * It can be represented as a packed float with the constant {@code -0x1.d4f24cp125F}.
     * <pre>
     * <font style='background-color: #0C2148;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C2148; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C2148;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #0C2148'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #0C2148'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #0C2148'>&nbsp;@&nbsp;</font><font style='background-color: #0C2148; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #0C2148;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #0C2148; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SPACE_BLUE = -0x1.d4f24cp125F;
    static { NAMED.put("Space Blue", -0x1.d4f24cp125F); LIST.add(-0x1.d4f24cp125F); }

    /**
     * This color constant "Thick Amethyst" has RGBA8888 code {@code 5010B0FF}, intensity 0.25490198, protan 0.6039216, tritan 0.23921569, alpha 1.0, hue 0.72406375, and saturation 0.4639027.
     * It can be represented as a packed float with the constant {@code -0x1.7b3482p125F}.
     * <pre>
     * <font style='background-color: #5010B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5010B0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5010B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5010B0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5010B0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5010B0'>&nbsp;@&nbsp;</font><font style='background-color: #5010B0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5010B0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5010B0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THICK_AMETHYST = -0x1.7b3482p125F;
    static { NAMED.put("Thick Amethyst", -0x1.7b3482p125F); LIST.add(-0x1.7b3482p125F); }

    /**
     * This color constant "Juicy Grape" has RGBA8888 code {@code 6010D0FF}, intensity 0.29803923, protan 0.627451, tritan 0.1882353, alpha 1.0, hue 0.72572863, and saturation 0.5564815.
     * It can be represented as a packed float with the constant {@code -0x1.614098p125F}.
     * <pre>
     * <font style='background-color: #6010D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6010D0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6010D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #6010D0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #6010D0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #6010D0'>&nbsp;@&nbsp;</font><font style='background-color: #6010D0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #6010D0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #6010D0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float JUICY_GRAPE = -0x1.614098p125F;
    static { NAMED.put("Juicy Grape", -0x1.614098p125F); LIST.add(-0x1.614098p125F); }

    /**
     * This color constant "Blacklight Glow" has RGBA8888 code {@code 8732D2FF}, intensity 0.40392157, protan 0.6313726, tritan 0.2509804, alpha 1.0, hue 0.7441058, and saturation 0.46179682.
     * It can be represented as a packed float with the constant {@code -0x1.8142cep125F}.
     * <pre>
     * <font style='background-color: #8732D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8732D2; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8732D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8732D2'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8732D2'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8732D2'>&nbsp;@&nbsp;</font><font style='background-color: #8732D2; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8732D2;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8732D2; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BLACKLIGHT_GLOW = -0x1.8142cep125F;
    static { NAMED.put("Blacklight Glow", -0x1.8142cep125F); LIST.add(-0x1.8142cep125F); }

    /**
     * This color constant "Purple Freesia" has RGBA8888 code {@code 9C41FFFF}, intensity 0.49803922, protan 0.6431373, tritan 0.2, alpha 1.0, hue 0.7364659, and saturation 0.547444.
     * It can be represented as a packed float with the constant {@code -0x1.6748fep125F}.
     * <pre>
     * <font style='background-color: #9C41FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C41FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C41FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #9C41FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #9C41FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #9C41FF'>&nbsp;@&nbsp;</font><font style='background-color: #9C41FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #9C41FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #9C41FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLE_FREESIA = -0x1.6748fep125F;
    static { NAMED.put("Purple Freesia", -0x1.6748fep125F); LIST.add(-0x1.6748fep125F); }

    /**
     * This color constant "Thin Amethyst" has RGBA8888 code {@code 7F00FFFF}, intensity 0.32941177, protan 0.69803923, tritan 0.09803922, alpha 1.0, hue 0.73886454, and saturation 0.73730576.
     * It can be represented as a packed float with the constant {@code -0x1.3364a8p125F}.
     * <pre>
     * <font style='background-color: #7F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F00FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #7F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #7F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #7F00FF'>&nbsp;@&nbsp;</font><font style='background-color: #7F00FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #7F00FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #7F00FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THIN_AMETHYST = -0x1.3364a8p125F;
    static { NAMED.put("Thin Amethyst", -0x1.3364a8p125F); LIST.add(-0x1.3364a8p125F); }

    /**
     * This color constant "Orchid" has RGBA8888 code {@code BD62FFFF}, intensity 0.59607846, protan 0.6392157, tritan 0.25882354, alpha 1.0, hue 0.75138193, and saturation 0.4542136.
     * It can be represented as a packed float with the constant {@code -0x1.85473p125F}.
     * <pre>
     * <font style='background-color: #BD62FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD62FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD62FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD62FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD62FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD62FF'>&nbsp;@&nbsp;</font><font style='background-color: #BD62FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD62FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD62FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ORCHID = -0x1.85473p125F;
    static { NAMED.put("Orchid", -0x1.85473p125F); LIST.add(-0x1.85473p125F); }

    /**
     * This color constant "Lavender" has RGBA8888 code {@code B991FFFF}, intensity 0.69803923, protan 0.5647059, tritan 0.31764707, alpha 1.0, hue 0.7168188, and saturation 0.3195089.
     * It can be represented as a packed float with the constant {@code -0x1.a32164p125F}.
     * <pre>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #B991FF'>&nbsp;@&nbsp;</font><font style='background-color: #B991FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #B991FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #B991FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LAVENDER = -0x1.a32164p125F;
    static { NAMED.put("Lavender", -0x1.a32164p125F); LIST.add(-0x1.a32164p125F); }

    /**
     * This color constant "Lilac" has RGBA8888 code {@code D7A5FFFF}, intensity 0.7647059, protan 0.5764706, tritan 0.36078432, alpha 1.0, hue 0.7472593, and saturation 0.2597437.
     * It can be represented as a packed float with the constant {@code -0x1.b92786p125F}.
     * <pre>
     * <font style='background-color: #D7A5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A5FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7A5FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7A5FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7A5FF'>&nbsp;@&nbsp;</font><font style='background-color: #D7A5FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7A5FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7A5FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float LILAC = -0x1.b92786p125F;
    static { NAMED.put("Lilac", -0x1.b92786p125F); LIST.add(-0x1.b92786p125F); }

    /**
     * This color constant "Soap" has RGBA8888 code {@code D7C3FAFF}, intensity 0.83137256, protan 0.53333336, tritan 0.40784314, alpha 1.0, hue 0.71783704, and saturation 0.1616624.
     * It can be represented as a packed float with the constant {@code -0x1.d111a8p125F}.
     * <pre>
     * <font style='background-color: #D7C3FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7C3FA; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7C3FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #D7C3FA'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #D7C3FA'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #D7C3FA'>&nbsp;@&nbsp;</font><font style='background-color: #D7C3FA; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #D7C3FA;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #D7C3FA; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SOAP = -0x1.d111a8p125F;
    static { NAMED.put("Soap", -0x1.d111a8p125F); LIST.add(-0x1.d111a8p125F); }

    /**
     * This color constant "Pink Tutu" has RGBA8888 code {@code F8C6FCFF}, intensity 0.8627451, protan 0.57254905, tritan 0.42745098, alpha 1.0, hue 0.80531067, and saturation 0.15439469.
     * It can be represented as a packed float with the constant {@code -0x1.db25b8p125F}.
     * <pre>
     * <font style='background-color: #F8C6FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C6FC; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C6FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #F8C6FC'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #F8C6FC'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #F8C6FC'>&nbsp;@&nbsp;</font><font style='background-color: #F8C6FC; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #F8C6FC;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #F8C6FC; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_TUTU = -0x1.db25b8p125F;
    static { NAMED.put("Pink Tutu", -0x1.db25b8p125F); LIST.add(-0x1.db25b8p125F); }

    /**
     * This color constant "Thistle" has RGBA8888 code {@code E673FFFF}, intensity 0.6627451, protan 0.6666667, tritan 0.30588236, alpha 1.0, hue 0.7887474, and saturation 0.39760524.
     * It can be represented as a packed float with the constant {@code -0x1.9d5552p125F}.
     * <pre>
     * <font style='background-color: #E673FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E673FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E673FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E673FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E673FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E673FF'>&nbsp;@&nbsp;</font><font style='background-color: #E673FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E673FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E673FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float THISTLE = -0x1.9d5552p125F;
    static { NAMED.put("Thistle", -0x1.9d5552p125F); LIST.add(-0x1.9d5552p125F); }

    /**
     * This color constant "Heliotrope" has RGBA8888 code {@code FF52FFFF}, intensity 0.60784316, protan 0.7490196, tritan 0.27450982, alpha 1.0, hue 0.81678915, and saturation 0.49448538.
     * It can be represented as a packed float with the constant {@code -0x1.8d7f36p125F}.
     * <pre>
     * <font style='background-color: #FF52FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF52FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF52FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF52FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF52FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF52FF'>&nbsp;@&nbsp;</font><font style='background-color: #FF52FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF52FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF52FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float HELIOTROPE = -0x1.8d7f36p125F;
    static { NAMED.put("Heliotrope", -0x1.8d7f36p125F); LIST.add(-0x1.8d7f36p125F); }

    /**
     * This color constant "Purple" has RGBA8888 code {@code DA20E0FF}, intensity 0.4392157, protan 0.76862746, tritan 0.24705882, alpha 1.0, hue 0.812229, and saturation 0.54848444.
     * It can be represented as a packed float with the constant {@code -0x1.7f88ep125F}.
     * <pre>
     * <font style='background-color: #DA20E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA20E0; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA20E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #DA20E0'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #DA20E0'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #DA20E0'>&nbsp;@&nbsp;</font><font style='background-color: #DA20E0; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #DA20E0;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #DA20E0; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PURPLE = -0x1.7f88ep125F;
    static { NAMED.put("Purple", -0x1.7f88ep125F); LIST.add(-0x1.7f88ep125F); }

    /**
     * This color constant "Wisteria" has RGBA8888 code {@code BD29FFFF}, intensity 0.46666667, protan 0.72156864, tritan 0.18431373, alpha 1.0, hue 0.7687273, and saturation 0.6177739.
     * It can be represented as a packed float with the constant {@code -0x1.5f70eep125F}.
     * <pre>
     * <font style='background-color: #BD29FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD29FF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD29FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD29FF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD29FF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD29FF'>&nbsp;@&nbsp;</font><font style='background-color: #BD29FF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD29FF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD29FF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float WISTERIA = -0x1.5f70eep125F;
    static { NAMED.put("Wisteria", -0x1.5f70eep125F); LIST.add(-0x1.5f70eep125F); }

    /**
     * This color constant "Medium Plum" has RGBA8888 code {@code BD10C5FF}, intensity 0.35686275, protan 0.7490196, tritan 0.2627451, alpha 1.0, hue 0.81087035, and saturation 0.5127584.
     * It can be represented as a packed float with the constant {@code -0x1.877eb6p125F}.
     * <pre>
     * <font style='background-color: #BD10C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD10C5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD10C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD10C5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD10C5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD10C5'>&nbsp;@&nbsp;</font><font style='background-color: #BD10C5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD10C5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD10C5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MEDIUM_PLUM = -0x1.877eb6p125F;
    static { NAMED.put("Medium Plum", -0x1.877eb6p125F); LIST.add(-0x1.877eb6p125F); }

    /**
     * This color constant "Violet" has RGBA8888 code {@code 8C14BEFF}, intensity 0.32156864, protan 0.6784314, tritan 0.2509804, alpha 1.0, hue 0.7706846, and saturation 0.48947668.
     * It can be represented as a packed float with the constant {@code -0x1.815aa4p125F}.
     * <pre>
     * <font style='background-color: #8C14BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C14BE; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C14BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #8C14BE'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #8C14BE'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #8C14BE'>&nbsp;@&nbsp;</font><font style='background-color: #8C14BE; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #8C14BE;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #8C14BE; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float VIOLET = -0x1.815aa4p125F;
    static { NAMED.put("Violet", -0x1.815aa4p125F); LIST.add(-0x1.815aa4p125F); }

    /**
     * This color constant "Grape Lollipop" has RGBA8888 code {@code 5A187BFF}, intensity 0.23529412, protan 0.59607846, tritan 0.3529412, alpha 1.0, hue 0.7621339, and saturation 0.28358305.
     * It can be represented as a packed float with the constant {@code -0x1.b53078p125F}.
     * <pre>
     * <font style='background-color: #5A187B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A187B; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A187B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #5A187B'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #5A187B'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #5A187B'>&nbsp;@&nbsp;</font><font style='background-color: #5A187B; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #5A187B;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #5A187B; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAPE_LOLLIPOP = -0x1.b53078p125F;
    static { NAMED.put("Grape Lollipop", -0x1.b53078p125F); LIST.add(-0x1.b53078p125F); }

    /**
     * This color constant "Mulberry" has RGBA8888 code {@code 641464FF}, intensity 0.21176471, protan 0.6156863, tritan 0.39607844, alpha 1.0, hue 0.8177325, and saturation 0.22846016.
     * It can be represented as a packed float with the constant {@code -0x1.cb3a6cp125F}.
     * <pre>
     * <font style='background-color: #641464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #641464; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #641464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #641464'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #641464'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #641464'>&nbsp;@&nbsp;</font><font style='background-color: #641464; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #641464;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #641464; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float MULBERRY = -0x1.cb3a6cp125F;
    static { NAMED.put("Mulberry", -0x1.cb3a6cp125F); LIST.add(-0x1.cb3a6cp125F); }

    /**
     * This color constant "Grape Soda" has RGBA8888 code {@code 410062FF}, intensity 0.13725491, protan 0.59607846, tritan 0.3529412, alpha 1.0, hue 0.76214266, and saturation 0.2836202.
     * It can be represented as a packed float with the constant {@code -0x1.b53046p125F}.
     * <pre>
     * <font style='background-color: #410062;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #410062; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #410062;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #410062'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #410062'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #410062'>&nbsp;@&nbsp;</font><font style='background-color: #410062; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #410062;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #410062; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float GRAPE_SODA = -0x1.b53046p125F;
    static { NAMED.put("Grape Soda", -0x1.b53046p125F); LIST.add(-0x1.b53046p125F); }

    /**
     * This color constant "Eggplant" has RGBA8888 code {@code 320A46FF}, intensity 0.1254902, protan 0.5568628, tritan 0.40784314, alpha 1.0, hue 0.757076, and saturation 0.17575425.
     * It can be represented as a packed float with the constant {@code -0x1.d11c4p125F}.
     * <pre>
     * <font style='background-color: #320A46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #320A46; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #320A46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #320A46'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #320A46'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #320A46'>&nbsp;@&nbsp;</font><font style='background-color: #320A46; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #320A46;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #320A46; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float EGGPLANT = -0x1.d11c4p125F;
    static { NAMED.put("Eggplant", -0x1.d11c4p125F); LIST.add(-0x1.d11c4p125F); }

    /**
     * This color constant "Cherry Syrup" has RGBA8888 code {@code 551937FF}, intensity 0.16862746, protan 0.5803922, tritan 0.4745098, alpha 1.0, hue 0.9045996, and saturation 0.15121266.
     * It can be represented as a packed float with the constant {@code -0x1.f32856p125F}.
     * <pre>
     * <font style='background-color: #551937;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #551937; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #551937;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #551937'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #551937'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #551937'>&nbsp;@&nbsp;</font><font style='background-color: #551937; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #551937;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #551937; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CHERRY_SYRUP = -0x1.f32856p125F;
    static { NAMED.put("Cherry Syrup", -0x1.f32856p125F); LIST.add(-0x1.f32856p125F); }

    /**
     * This color constant "Plum Juice" has RGBA8888 code {@code A01982FF}, intensity 0.29411766, protan 0.6901961, tritan 0.38039216, alpha 1.0, hue 0.8582268, and saturation 0.34849447.
     * It can be represented as a packed float with the constant {@code -0x1.c36096p125F}.
     * <pre>
     * <font style='background-color: #A01982;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A01982; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A01982;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #A01982'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #A01982'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #A01982'>&nbsp;@&nbsp;</font><font style='background-color: #A01982; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #A01982;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #A01982; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PLUM_JUICE = -0x1.c36096p125F;
    static { NAMED.put("Plum Juice", -0x1.c36096p125F); LIST.add(-0x1.c36096p125F); }

    /**
     * This color constant "Fruit Punch" has RGBA8888 code {@code C80078FF}, intensity 0.25882354, protan 0.7764706, tritan 0.3882353, alpha 1.0, hue 0.8918639, and saturation 0.5163661.
     * It can be represented as a packed float with the constant {@code -0x1.c78c84p125F}.
     * <pre>
     * <font style='background-color: #C80078;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C80078; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C80078;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #C80078'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #C80078'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #C80078'>&nbsp;@&nbsp;</font><font style='background-color: #C80078; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #C80078;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #C80078; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FRUIT_PUNCH = -0x1.c78c84p125F;
    static { NAMED.put("Fruit Punch", -0x1.c78c84p125F); LIST.add(-0x1.c78c84p125F); }

    /**
     * This color constant "Bubble Gum" has RGBA8888 code {@code FF50BFFF}, intensity 0.54509807, protan 0.7411765, tritan 0.39215687, alpha 1.0, hue 0.885552, and saturation 0.44870842.
     * It can be represented as a packed float with the constant {@code -0x1.c97b16p125F}.
     * <pre>
     * <font style='background-color: #FF50BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF50BF; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF50BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF50BF'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF50BF'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF50BF'>&nbsp;@&nbsp;</font><font style='background-color: #FF50BF; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF50BF;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF50BF; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BUBBLE_GUM = -0x1.c97b16p125F;
    static { NAMED.put("Bubble Gum", -0x1.c97b16p125F); LIST.add(-0x1.c97b16p125F); }

    /**
     * This color constant "Pink Lemonade" has RGBA8888 code {@code FF6AC5FF}, intensity 0.6117647, protan 0.7058824, tritan 0.4117647, alpha 1.0, hue 0.8883072, and saturation 0.3835891.
     * It can be represented as a packed float with the constant {@code -0x1.d36938p125F}.
     * <pre>
     * <font style='background-color: #FF6AC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6AC5; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6AC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FF6AC5'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FF6AC5'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FF6AC5'>&nbsp;@&nbsp;</font><font style='background-color: #FF6AC5; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FF6AC5;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FF6AC5; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float PINK_LEMONADE = -0x1.d36938p125F;
    static { NAMED.put("Pink Lemonade", -0x1.d36938p125F); LIST.add(-0x1.d36938p125F); }

    /**
     * This color constant "Shrimp" has RGBA8888 code {@code FAA0B9FF}, intensity 0.7176471, protan 0.61960787, tritan 0.5019608, alpha 1.0, hue 0.95169055, and saturation 0.23105192.
     * It can be represented as a packed float with the constant {@code -0x1.013d6ep126F}.
     * <pre>
     * <font style='background-color: #FAA0B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAA0B9; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAA0B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FAA0B9'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FAA0B9'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FAA0B9'>&nbsp;@&nbsp;</font><font style='background-color: #FAA0B9; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FAA0B9;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FAA0B9; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float SHRIMP = -0x1.013d6ep126F;
    static { NAMED.put("Shrimp", -0x1.013d6ep126F); LIST.add(-0x1.013d6ep126F); }

    /**
     * This color constant "Flamingo" has RGBA8888 code {@code FC3A8CFF}, intensity 0.44705883, protan 0.7647059, tritan 0.45490196, alpha 1.0, hue 0.92558634, and saturation 0.50401974.
     * It can be represented as a packed float with the constant {@code -0x1.e986e4p125F}.
     * <pre>
     * <font style='background-color: #FC3A8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC3A8C; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC3A8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #FC3A8C'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #FC3A8C'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #FC3A8C'>&nbsp;@&nbsp;</font><font style='background-color: #FC3A8C; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #FC3A8C;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #FC3A8C; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float FLAMINGO = -0x1.e986e4p125F;
    static { NAMED.put("Flamingo", -0x1.e986e4p125F); LIST.add(-0x1.e986e4p125F); }

    /**
     * This color constant "Rose" has RGBA8888 code {@code E61E78FF}, intensity 0.34901962, protan 0.77254903, tritan 0.44313726, alpha 1.0, hue 0.9201528, and saturation 0.51736057.
     * It can be represented as a packed float with the constant {@code -0x1.e38ab2p125F}.
     * <pre>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #E61E78'>&nbsp;@&nbsp;</font><font style='background-color: #E61E78; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #E61E78;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #E61E78; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float ROSE = -0x1.e38ab2p125F;
    static { NAMED.put("Rose", -0x1.e38ab2p125F); LIST.add(-0x1.e38ab2p125F); }

    /**
     * This color constant "Carmine" has RGBA8888 code {@code BD1039FF}, intensity 0.22745098, protan 0.7294118, tritan 0.5176471, alpha 1.0, hue 0.95983154, and saturation 0.4457646.
     * It can be represented as a packed float with the constant {@code -0x1.097474p126F}.
     * <pre>
     * <font style='background-color: #BD1039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD1039; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD1039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #BD1039'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #BD1039'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #BD1039'>&nbsp;@&nbsp;</font><font style='background-color: #BD1039; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #BD1039;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #BD1039; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float CARMINE = -0x1.097474p126F;
    static { NAMED.put("Carmine", -0x1.097474p126F); LIST.add(-0x1.097474p126F); }

    /**
     * This color constant "Bologna" has RGBA8888 code {@code 98344DFF}, intensity 0.3019608, protan 0.6313726, tritan 0.50980395, alpha 1.0, hue 0.95955217, and saturation 0.25515613.
     * It can be represented as a packed float with the constant {@code -0x1.05429ap126F}.
     * <pre>
     * <font style='background-color: #98344D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98344D; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98344D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #98344D'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #98344D'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #98344D'>&nbsp;@&nbsp;</font><font style='background-color: #98344D; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #98344D;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #98344D; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float BOLOGNA = -0x1.05429ap126F;
    static { NAMED.put("Bologna", -0x1.05429ap126F); LIST.add(-0x1.05429ap126F); }

    /**
     * This color constant "Raspberry" has RGBA8888 code {@code 911437FF}, intensity 0.20392157, protan 0.6666667, tritan 0.5019608, alpha 1.0, hue 0.9509436, and saturation 0.32213032.
     * It can be represented as a packed float with the constant {@code -0x1.015468p126F}.
     * <pre>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #000000'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #888888; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #ffffff; color: #911437'>&nbsp;@&nbsp;</font><font style='background-color: #911437; color: #888888'>&nbsp;@&nbsp;</font>
     * <font style='background-color: #911437;'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #000000; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #888888; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #ffffff; color: #000000'>&nbsp;&nbsp;&nbsp;</font><font style='background-color: #911437; color: #ffffff'>&nbsp;@&nbsp;</font>
     * </pre>
     */
    public static final float RASPBERRY = -0x1.015468p126F;
    static { NAMED.put("Raspberry", -0x1.015468p126F); LIST.add(-0x1.015468p126F); }
    
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
                return Float.compare(hue(NAMED.get(o1, TRANSPARENT)), hue(NAMED.get(o2, TRANSPARENT)));
            }
        });
        NAMES_BY_LIGHTNESS.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Float.compare(intensity(NAMED.get(o1, TRANSPARENT)), intensity(NAMED.get(o2, TRANSPARENT)));
            }
        });
    }
}
